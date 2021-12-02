/**
 * malltodo
 * ============================================================================
 * * 版权所有 2021-2071 郑州掌勺信息技术有限公司，并保留所有权利。
 * 网站地址: http://www.malltodo.com
 * ----------------------------------------------------------------------------
 * 这不是一个自由软件！您只能在不用于商业目的的前提下对程序代码进行修改和使用 .
 * 不允许对程序代码以任何形式任何目的的再发布。
 * 如果商业用途务必到官方购买正版授权, 以免引起不必要的法律纠纷.
 * ============================================================================
 * 郑州掌勺信息技术有限公司 2021-09-01
 * 业务电话：13598851835（微信同号） 
 */
package union;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.Http;
import com.javatodo.core.tools.T;

import common.Functions;
import common.MU;
import common.database.COMMISSION_SET;
import common.database.GOODS;
import common.database.PDD;
import common.database.UNION_ORDER;
import common.database.UNION_ORDER_GOODS;
import model.Balance;

public class Pdd {

	private String client_id = "";
	private String client_secret = "";
	private String pid = "";

	public Pdd() {
		Map<String, Object> info;
		try {
			info = new MU(PDD._table_name).order(PDD.id + " desc").find();
			if (info != null) {
				this.client_id = info.get(PDD.client_id).toString();
				this.client_secret = info.get(PDD.client_secret).toString();
				this.pid = info.get(PDD.pid).toString();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public JSONObject getGoodsList(JSONArray union_goods_list) {
		JSONArray goods_sign_list = new JSONArray();
		for (Integer i = 0; i < union_goods_list.size(); i = i + 1) {
			if (union_goods_list.getJSONObject(i).getString(GOODS.platform).equals("pdd")) {
				String goods_sign = union_goods_list.getJSONObject(i).getString(GOODS.goods_sign);
				goods_sign_list.add(goods_sign);
			}
		}
		JSONObject map = new JSONObject();
		if (goods_sign_list.size() == 0) {
			return map;
		}
		Map<String, String> query = new HashMap();
		query.put("type", "pdd.ddk.goods.search");
		query.put("client_id", client_id);
		query.put("timestamp", String.valueOf((T.time() / 1000)));
		query.put("use_customized", "false");
		query.put("with_coupon", "true");
		query.put("page_size", "100");
		query.put("page", "1");
		query.put("goods_sign_list", goods_sign_list.toJSONString());
		String sign = getSign(query, client_secret);
		query.put("sign", sign);
		Http http = new Http();
		String ret = http.postJson("https://gw-api.pinduoduo.com/api/router", JSON.toJSONString(query));
		JSONObject object = JSONObject.parseObject(ret);
		if (object.containsKey("error_response")) {
			System.out.println(ret + "\n");
			T.create_log("pdd_error.log", ret + "\n");
			return map;
		} else {
			JSONArray list = object.getJSONObject("goods_search_response").getJSONArray("goods_list");
			for (Integer i = 0; i < list.size(); i = i + 1) {
				JSONObject subObject = list.getJSONObject(i);
				// 此处获取商品信息
				String goods_sign = subObject.getString("goods_sign");
				String[] goods_sign_arr = goods_sign.split("_");
				String goods_id = goods_sign_arr[goods_sign_arr.length - 1];
				String goods_name = subObject.getString("goods_name");
				goods_id = "pdd_" + goods_id;
				String goods_pic = subObject.getString("goods_thumbnail_url");
				String category_name = subObject.getString("category_name");
				String mall_name = subObject.getString("mall_name");
				Long mall_id = subObject.getLong("mall_id");
				String brand_name = subObject.getString("brand_name");
				// promotion_rate:佣金比例，千分比，已转化为小数
				BigDecimal promotion_rate = new BigDecimal(subObject.getLong("promotion_rate")).divide(new BigDecimal(1000));
				String sales_tip = subObject.getString("sales_tip");// 售卖数量
				BigDecimal original_price = new BigDecimal(subObject.getLong("min_group_price")).divide(new BigDecimal(100));// 商品售价
				BigDecimal coupon_discount = new BigDecimal(subObject.getLong("coupon_discount")).divide(new BigDecimal(100));// 优惠券面额
				BigDecimal price = original_price.subtract(coupon_discount);
				BigDecimal promotion_money = price.multiply(promotion_rate);
				String search_id = object.getJSONObject("goods_search_response").getString("search_id");
				Map<String, Object> data = new HashMap<>();
				data.put(GOODS.goods_name, goods_name);
				data.put(GOODS.goods_pic, goods_pic);
				data.put(GOODS.goods_id, goods_id);
				data.put(GOODS.goods_sign, goods_sign);
				data.put(GOODS.mall_name, mall_name);
				data.put(GOODS.mall_id, mall_id);
				data.put(GOODS.brand_name, brand_name);
				data.put(GOODS.promotion_rate, promotion_rate);
				data.put(GOODS.promotion_money, promotion_money);
				data.put(GOODS.sales_tip, sales_tip);
				data.put(GOODS.original_price, original_price);
				data.put(GOODS.coupon_discount, coupon_discount);
				data.put(GOODS.price, price);
				data.put(GOODS.search_id, search_id);
				data.put(GOODS.platform, "pdd");
				data.put(GOODS.coupon_url, "");
				data.put(GOODS.category_name, category_name);
				map.put(goods_sign, data);
			}
			return map;
		}
	}

	public JSONObject getGoodsDetail(String goods_id, String goods_sign, String uid, String search_id) {
		Map<String, String> map = new HashMap<>();
		map.put("type", "pdd.ddk.goods.detail");
		map.put("client_id", client_id);
		map.put("timestamp", String.valueOf((T.time() / 1000)));
		JSONObject custom_parameters = new JSONObject();
		custom_parameters.put("uid", "malltodo_" + uid);
		map.put("custom_parameters", custom_parameters.toJSONString());
		map.put("goods_sign", goods_sign);
		map.put("pid", pid);
		map.put("search_id", search_id);
		map.put("sign", getSign(map, client_secret));
		Http http = new Http();
		String ret = http.postJson("https://gw-api.pinduoduo.com/api/router", JSON.toJSONString(map));
		JSONObject object = JSONObject.parseObject(ret);
		JSONObject retjson = new JSONObject();
		if (object.containsKey("goods_detail_response")) {
			object = object.getJSONObject("goods_detail_response");
			if (object.containsKey("goods_details")) {
				JSONArray array = object.getJSONArray("goods_details");
				object = array.getJSONObject(0);
				// 获取商品焦点图
				JSONArray pics = object.getJSONArray("goods_gallery_urls");
				JSONObject info = new JSONObject();
				info.put(GOODS.banner, new JSONArray());
				for (Integer i = 0; i < pics.size(); i = i + 1) {
					JSONObject picObj = new JSONObject();
					picObj.put("pic", pics.getString(i));
					info.getJSONArray(GOODS.banner).add(picObj);
				}
				info.put(GOODS.goods_detail, object.getString("goods_desc"));
				info.put(GOODS.goods_name, object.getString("goods_name"));
				info.put(GOODS.goods_pic, object.getString("goods_image_url"));
				info.put(GOODS.category_name, object.getString("category_name"));
				info.put(GOODS.mall_name, object.getString("mall_name"));
				info.put(GOODS.mall_id, object.getLong("mall_id"));
				info.put(GOODS.brand_name, object.getString("brand_name"));
				info.put(GOODS.promotion_rate, new BigDecimal(object.getLong("promotion_rate")).divide(new BigDecimal(1000)));
				info.put(GOODS.sales_tip, object.getString("sales_tip"));
				info.put(GOODS.original_price, new BigDecimal(object.getLong("min_group_price")).divide(new BigDecimal(100)));
				info.put(GOODS.coupon_discount, new BigDecimal(object.getLong("coupon_discount")).divide(new BigDecimal(100)));
				info.put(GOODS.price, info.getBigDecimal(GOODS.original_price).subtract(info.getBigDecimal(GOODS.coupon_discount)));
				info.put(GOODS.platform, "拼多多");
				info.put(GOODS.url, this.getGoodsUrl(goods_sign, uid, search_id));

				String cache = T.S(goods_id);
				JSONObject cacheObject = JSONObject.parseObject(cache);

				info.put(GOODS.promotion_money, cacheObject.getBigDecimal(GOODS.promotion_money));
				retjson.put("status", 1);
				retjson.put("info", info);
			} else {
				retjson.put("status", 0);
				retjson.put("info", "该商品已下架");
			}
		} else {
			retjson.put("status", 0);
			retjson.put("info", "该商品已下架");
		}
		return retjson;
	}

	private String getGoodsUrl(String goods_sign, String uid, String search_id) {
		Map<String, String> map = new HashMap<>();
		map.put("type", "pdd.ddk.goods.promotion.url.generate");
		map.put("client_id", client_id);
		map.put("timestamp", String.valueOf((T.time() / 1000)));
		JSONObject custom_parameters = new JSONObject();
		custom_parameters.put("uid", "malltodo_" + uid);
		map.put("custom_parameters", custom_parameters.toJSONString());
		map.put("generate_authority_url", "true");
		JSONArray goods_sign_list = new JSONArray();
		goods_sign_list.add(goods_sign);
		map.put("goods_sign_list", goods_sign_list.toJSONString());
		map.put("generate_schema_url", "true");
		map.put("p_id", pid);
		map.put("search_id", search_id);
		map.put("sign", getSign(map, client_secret));
		Http http = new Http();
		String ret = http.postJson("https://gw-api.pinduoduo.com/api/router", JSON.toJSONString(map));
		JSONObject object = JSONObject.parseObject(ret);
		if (object.containsKey("goods_promotion_url_generate_response")) {
			object = object.getJSONObject("goods_promotion_url_generate_response");
			if (object.containsKey("goods_promotion_url_list")) {
				object = object.getJSONArray("goods_promotion_url_list").getJSONObject(0);
				return object.getString("mobile_url");
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	private String getSign(Map<String, String> map, String client_secret) {
		String result = "";
		try {
			List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(map.entrySet());
			// 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
			Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
				public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
					return (o1.getKey()).toString().compareTo(o2.getKey());
				}
			});
			// 构造签名键值对的格式
			StringBuilder sb = new StringBuilder();
			for (Map.Entry<String, String> item : infoIds) {
				if (item.getKey() != null || item.getKey() != "") {
					String key = item.getKey();
					String val = item.getValue();
					if (!key.equals("sign") && !val.equals("") && !val.equals(null)) {
						sb.append(key + val);
					}
				}
			}
			result = sb.toString();
			result = client_secret + result + client_secret;
			// 进行MD5加密
			result = T.md5(result).toUpperCase();
		} catch (Exception e) {
			return null;
		}
		return result;
	}

	// 获取拼多多订单
	public void getOrderList(String startTime, String endTime, Integer page) {
		Map<String, String> map = new HashMap<>();
		map.put("type", "pdd.ddk.order.list.increment.get");
		map.put("client_id", client_id);
		map.put("timestamp", String.valueOf((T.time() / 1000)));
		try {
			map.put("start_update_time", String.valueOf(T.strtotime(startTime, "") / 1000));
			map.put("end_update_time", String.valueOf(T.strtotime(endTime, "") / 1000));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("page", page + "");
		map.put("page_size", 50 + "");
		map.put("return_count", "false");
		map.put("sign", getSign(map, client_secret));
		Http http = new Http();
		String ret = http.postJson("https://gw-api.pinduoduo.com/api/router", JSON.toJSONString(map));
		T.create_log("pdd_order.txt", ret);
		JSONObject object = JSONObject.parseObject(ret);
		if (object == null) {
			return;
		}
		if (object.containsKey("order_list_get_response")) {
			object = object.getJSONObject("order_list_get_response");
			if (object.containsKey("order_list")) {
				JSONArray list = object.getJSONArray("order_list");
				// 对订单进行处理
				for (Integer i = 0; i < list.size(); i = i + 1) {
					try {
						this.handleOrder(list.getJSONObject(i));
					} catch (SQLException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				// 订单翻页
				if (list.size() > 0) {
					getOrderList(startTime, endTime, page + 1);
				}
			}
		}
		return;
	}

	private void handleOrder(JSONObject order) throws SQLException {
		// 处理订单
		Map<String, Object> object = new HashMap<>();
		object.put(UNION_ORDER.order_sn, order.getString("order_sn"));
		object.put(UNION_ORDER.pay_time, order.getString("order_pay_time") + "000");
		object.put(UNION_ORDER.type, "pdd");
		String order_status = order.getString("order_status");
		if (order_status.equals("0") || order_status.equals("1") || order_status.equals("2") || order_status.equals("3")) {
			object.put(UNION_ORDER.status, 0);
		}
		if (order_status.equals("5")) {
			object.put(UNION_ORDER.status, 1);
		}
		if (order_status.equals("4")) {
			object.put(UNION_ORDER.note, "审核失败（不可提现）");
			object.put(UNION_ORDER.status, -1);
		}
		if (order_status.equals("10")) {
			object.put(UNION_ORDER.note, "已处罚");
			object.put(UNION_ORDER.status, -1);
		}
		long order_amount = order.getLongValue("order_amount");
		BigDecimal order_money = new BigDecimal(order_amount).divide(new BigDecimal(100));
		object.put(UNION_ORDER.order_money, order_money);
		long promotion_amount = order.getLongValue("promotion_amount");
		BigDecimal commission = new BigDecimal(promotion_amount).divide(new BigDecimal(100));
		object.put(UNION_ORDER.commission, commission);
		JSONObject commission_ret = Functions.commission(commission, "pdd");
		String member_id = order.getJSONObject("custom_parameters").getString("uid");
		member_id = member_id.replace("malltodo_", "");
		JSONObject pid_obj = Functions.getPids(member_id);
		object.put(UNION_ORDER.uid, member_id);
		if (commission_ret.getInteger(COMMISSION_SET.is_Internal_purchase) == 0) {
			if (pid_obj.getString("pid").equals("0")) {
				object.put(UNION_ORDER.level_1_id, "0");
				object.put(UNION_ORDER.level_1_money, "0");
			} else {
				object.put(UNION_ORDER.level_1_id, pid_obj.getString("pid"));
				object.put(UNION_ORDER.level_1_money, commission_ret.getBigDecimal("level_1_money"));
			}
			if (pid_obj.getString("ppid").equals("0")) {
				object.put(UNION_ORDER.level_2_id, "0");
				object.put(UNION_ORDER.level_2_money, "0");
			} else {
				object.put(UNION_ORDER.level_2_id, pid_obj.getString("ppid"));
				object.put(UNION_ORDER.level_2_money, commission_ret.getBigDecimal("level_2_money"));
			}
			if (pid_obj.getString("pppid").equals("0")) {
				object.put(UNION_ORDER.level_3_id, "0");
				object.put(UNION_ORDER.level_3_money, "0");
			} else {
				object.put(UNION_ORDER.level_3_id, pid_obj.getString("pppid"));
				object.put(UNION_ORDER.level_3_money, commission_ret.getBigDecimal("level_3_money"));
			}
		} else {
			object.put(UNION_ORDER.level_1_id, member_id);
			object.put(UNION_ORDER.level_1_money, commission_ret.getBigDecimal("level_1_money"));

			if (pid_obj.getString("pid").equals("0")) {
				object.put(UNION_ORDER.level_2_id, "0");
				object.put(UNION_ORDER.level_2_money, "0");
			} else {
				object.put(UNION_ORDER.level_2_id, pid_obj.getString("pid"));
				object.put(UNION_ORDER.level_2_money, commission_ret.getBigDecimal("level_2_money"));
			}
			if (pid_obj.getString("ppid").equals("0")) {
				object.put(UNION_ORDER.level_3_id, "0");
				object.put(UNION_ORDER.level_3_money, "0");
			} else {
				object.put(UNION_ORDER.level_3_id, pid_obj.getString("ppid"));
				object.put(UNION_ORDER.level_3_money, commission_ret.getBigDecimal("level_3_money"));
			}
		}

		BigDecimal platform_money = new BigDecimal(0);
		platform_money = commission.subtract(new BigDecimal(object.get(UNION_ORDER.level_1_money).toString())).subtract(new BigDecimal(object.get(UNION_ORDER.level_2_money).toString())).subtract(new BigDecimal(object.get(UNION_ORDER.level_3_money).toString()));

		object.put(UNION_ORDER.platform_money, platform_money);

		Map<String, W> where = new HashMap<>();
		where.put(UNION_ORDER.order_sn, new W("eq", order.getString("order_sn")));
		Map<String, Object> info = new MU(UNION_ORDER._table_name).where(where).find();
		Object union_order_id = null;
		if (info == null) {
			union_order_id = new MU(UNION_ORDER._table_name).data(object).add();
		} else {
			new MU(UNION_ORDER._table_name).where(where).save(object);
			union_order_id = info.get(UNION_ORDER.id);
		}
		// 处理订单中的商品
		Map<String, W> order_goods_where = new HashMap<>();
		order_goods_where.put(UNION_ORDER_GOODS.order_sn, new W("eq", order.getString("order_sn")));
		Map<String, Object> save_data = new HashMap<>();
		save_data.put(UNION_ORDER_GOODS.status, -1);
		new MU(UNION_ORDER_GOODS._table_name).where(order_goods_where).save(save_data);

		Map<String, Object> goods = new HashMap<>();
		goods.put(UNION_ORDER_GOODS.goods_id, order.getString("goods_id"));
		goods.put(UNION_ORDER_GOODS.goods_name, order.getString("goods_name"));
		goods.put(UNION_ORDER_GOODS.goods_number, order.getString("goods_quantity"));
		goods.put(UNION_ORDER_GOODS.goods_pic, order.getString("goods_thumbnail_url"));
		goods.put(UNION_ORDER_GOODS.order_sn, order.getString("order_sn"));
		goods.put(UNION_ORDER_GOODS.type, "pdd");
		goods.put(UNION_ORDER_GOODS.status, 1);

		Map<String, W> goods_where = new HashMap<>();
		goods_where.put(UNION_ORDER_GOODS.goods_id, new W("eq", order.getString("goods_id")));
		goods_where.put(UNION_ORDER_GOODS.type, new W("eq", "pdd"));

		Map<String, Object> goods_info = new MU(UNION_ORDER_GOODS._table_name).where(goods_where).find();
		if (goods_info == null) {
			new MU(UNION_ORDER_GOODS._table_name).data(goods).add();
		} else {
			new MU(UNION_ORDER_GOODS._table_name).where(goods_where).save(goods);
		}
		// 开始分佣
		if (object.get(UNION_ORDER.status).toString().equals("1")) {
			Balance.unionOrderCommissionAddIntoMoneylog(union_order_id.toString());
		}
	}

}
