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
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.Http;
import com.javatodo.core.tools.T;

import common.Functions;
import common.MU;
import common.database.COMMISSION_SET;
import common.database.GOODS;
import common.database.JD;
import common.database.UNION_ORDER;
import common.database.UNION_ORDER_GOODS;
import model.Balance;

public class Jd {

	private String app_key = "";

	private String app_secret = "";

	private String site_id = "";

	public Jd() {
		Map<String, Object> info;
		try {
			info = new MU(JD._table_name).order(JD.id + " desc").find();
			if (info != null) {
				this.app_key = info.get(JD.app_key).toString();
				this.app_secret = info.get(JD.app_secret).toString();
				this.site_id = info.get(JD.site_id).toString();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public JSONObject getGoodsList(JSONArray union_goods_list) {
		String goods_sign_list = "";
		JSONObject map = new JSONObject();
		for (Integer i = 0; i < union_goods_list.size(); i = i + 1) {
			if (union_goods_list.getJSONObject(i).getString(GOODS.platform).equals("jd")) {
				long goods_sign = union_goods_list.getJSONObject(i).getLongValue(GOODS.goods_sign);
				map.put(goods_sign + "", union_goods_list.getJSONObject(i));
				if (goods_sign_list.equals("")) {
					goods_sign_list = goods_sign + "";
				} else {
					goods_sign_list = goods_sign_list + "," + goods_sign;
				}
			}
		}

		if (goods_sign_list.trim().equals("")) {
			return map;
		}

		JSONObject object = new JSONObject();
		object.put("skuIds", goods_sign_list);
		String ret = buildRequest(object, "jd.union.open.goods.promotiongoodsinfo.query");
		JSONObject sampleJson = JSONObject.parseObject(ret);
		if (sampleJson == null) {
			return map;
		}

		if (sampleJson.containsKey("jd_union_open_goods_promotiongoodsinfo_query_responce")) {
			sampleJson = sampleJson.getJSONObject("jd_union_open_goods_promotiongoodsinfo_query_responce");
		} else {
			return map;
		}
		if (sampleJson.containsKey("queryResult")) {
			sampleJson = sampleJson.getJSONObject("queryResult");
		} else {
			return map;
		}
		if (sampleJson.containsKey("code") && sampleJson.getInteger("code") == 200) {
			JSONArray samArray = sampleJson.getJSONArray("data");
			if (samArray.size() > 0) {
				for (Integer i = 0; i < samArray.size(); i = i + 1) {
					sampleJson = samArray.getJSONObject(i);
					// 这里
					String skuid = sampleJson.getString("skuId");
					JSONObject info = map.getJSONObject(skuid);
					info.put(GOODS.promotion_rate, new BigDecimal(sampleJson.getLong("commisionRatioWl")).divide(new BigDecimal(100)));
					BigDecimal price = info.getBigDecimal(GOODS.price);
					info.put(GOODS.promotion_money, price.multiply(info.getBigDecimal(GOODS.promotion_rate)));
					map.put(skuid, info);
				}
				return map;
			} else {
				return map;
			}
		} else {
			return map;
		}

	}

	public JSONObject getGoodsDetail(String goods_id, String goods_sign, String uid, String search_id) {
		String cache = T.S(goods_id);
		JSONObject cacheObject = JSONObject.parseObject(cache);
		JSONObject res = new JSONObject();
		res.put("status", 0);
		res.put("info", "该商品已下架");
		// 获取商品简情
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("skuIds", goods_sign);
		String jString = buildRequest(jsonObject, "jd.union.open.goods.promotiongoodsinfo.query");
		// System.out.print(jString + "\n\n");
		JSONObject sampleJson = JSONObject.parseObject(jString);
		if (sampleJson == null) {
			return res;
		}
		if (sampleJson.containsKey("jd_union_open_goods_promotiongoodsinfo_query_responce")) {
			sampleJson = sampleJson.getJSONObject("jd_union_open_goods_promotiongoodsinfo_query_responce");
		} else {
			return res;
		}
		if (sampleJson.containsKey("queryResult")) {
			sampleJson = sampleJson.getJSONObject("queryResult");
		} else {
			return res;
		}
		if (sampleJson.containsKey("code") && sampleJson.getInteger("code") == 200) {
			JSONArray samArray = sampleJson.getJSONArray("data");
			if (samArray.size() > 0) {
				sampleJson = samArray.getJSONObject(0);
			} else {
				return res;
			}
		} else {
			return res;
		}
		// 获取商品详情
		JSONObject object = new JSONObject();
		object.put("skuIds", new JSONArray());
		object.getJSONArray("skuIds").add(goods_sign);
		JSONObject json = new JSONObject();
		json.put("goodsReq", object);
		String ret = buildRequest(json, "jd.union.open.goods.bigfield.query");
		// System.out.print(ret);
		JSONObject retjson = JSONObject.parseObject(ret);
		if (retjson == null) {
			return res;
		}
		if (retjson.containsKey("jd_union_open_goods_bigfield_query_responce")) {
			retjson = retjson.getJSONObject("jd_union_open_goods_bigfield_query_responce");
			if (retjson.containsKey("queryResult")) {
				retjson = retjson.getJSONObject("queryResult");
				if (retjson.containsKey("code") && retjson.getInteger("code") == 200) {
					JSONArray array = retjson.getJSONArray("data");
					retjson = array.getJSONObject(0);

					JSONObject info = new JSONObject();
					info.put(GOODS.banner, new JSONArray());
					for (Integer i = 0; i < retjson.getJSONObject("imageInfo").getJSONArray("imageList").size(); i = i + 1) {
						JSONObject picObj = new JSONObject();
						picObj.put("pic", retjson.getJSONObject("imageInfo").getJSONArray("imageList").getJSONObject(i).getString("url"));
						info.getJSONArray(GOODS.banner).add(picObj);
					}
					info.put(GOODS.goods_detail, retjson.getJSONObject("baseBigFieldInfo").getString("wdis"));
					info.put(GOODS.goods_name, retjson.getString("skuName"));
					info.put(GOODS.goods_pic, sampleJson.getString("imgUrl"));
					info.put(GOODS.category_name, retjson.getJSONObject("categoryInfo").getString("cid3Name"));
					info.put(GOODS.mall_name, "");
					info.put(GOODS.mall_id, "");
					info.put(GOODS.brand_name, "");
					info.put(GOODS.promotion_rate, new BigDecimal(sampleJson.getLong("commisionRatioWl")).divide(new BigDecimal(100)));
					info.put(GOODS.sales_tip, sampleJson.getString("inOrderCount"));
					info.put(GOODS.original_price, new BigDecimal(sampleJson.getLong("unitPrice")));
					info.put(GOODS.coupon_discount, cacheObject.getBigDecimal(GOODS.coupon_discount));
					info.put(GOODS.price, info.getBigDecimal(GOODS.original_price).subtract(info.getBigDecimal(GOODS.coupon_discount)));
					info.put(GOODS.platform, "京东");
					info.put(GOODS.url, this.getGoodsUrl(goods_id, sampleJson.getString("materialUrl"), uid));
					info.put(GOODS.promotion_money, cacheObject.getBigDecimal(GOODS.promotion_money));
					res.put("status", 1);
					res.put("info", info);
					return res;
				}
			}
		}
		return res;
	}

	public String getGoodsUrl(String goods_id, String item_url, String uid) {
		String cache = T.S(goods_id);
		JSONObject cacheObject = JSONObject.parseObject(cache);
		JSONObject object = new JSONObject();
		object.put("materialId", item_url);
		object.put("siteId", this.site_id);
		object.put("positionId", uid);
		object.put("couponUrl", cacheObject.getString(GOODS.coupon_url));
		JSONObject json = new JSONObject();
		json.put("promotionCodeReq", object);
		String ret = buildRequest(json, "jd.union.open.promotion.common.get");
		// System.out.print("\n\n" + ret);
		JSONObject retjson = JSONObject.parseObject(ret);
		if (retjson == null) {
			return null;
		}
		if (retjson.containsKey("jd_union_open_promotion_common_get_responce")) {
			retjson = retjson.getJSONObject("jd_union_open_promotion_common_get_responce");
			if (retjson.containsKey("getResult")) {
				retjson = retjson.getJSONObject("getResult");
				if (retjson.containsKey("code") && retjson.getInteger("code") == 200) {
					retjson = retjson.getJSONObject("data");
					return retjson.getString("clickURL");
				}
			}
		}
		return null;
	}

	private String buildRequest(JSONObject json, String methodName) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("360buy_param_json", json);
			map.put("method", methodName);
			map.put("app_key", app_key);
			map.put("timestamp", T.now());
			map.put("format", "json");
			map.put("v", "1.0");
			map.put("sign_method", "md5");
			map.put("sign", getSign(map));

			String url = "https://api.jd.com/routerjson?";
			String url_param = "";
			for (String key : map.keySet()) {
				if (url_param.trim().equals("")) {
					url_param = key + "=" + URLEncoder.encode(map.get(key).toString(), java.nio.charset.StandardCharsets.UTF_8.toString());
				} else {
					url_param = url_param + "&" + key + "=" + URLEncoder.encode(map.get(key).toString(), java.nio.charset.StandardCharsets.UTF_8.toString());
				}
			}
			Http http = new Http();
			url = url + url_param;
			String ret = http.get(url);
			return ret;
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}

	private String getSign(Map<String, Object> map) {
		String result = "";
		try {
			List<Map.Entry<String, Object>> infoIds = new ArrayList<Map.Entry<String, Object>>(map.entrySet());
			// 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
			Collections.sort(infoIds, new Comparator<Map.Entry<String, Object>>() {
				public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
					return (o1.getKey()).toString().compareTo(o2.getKey());
				}
			});
			// 构造签名键值对的格式
			StringBuilder sb = new StringBuilder();
			for (Map.Entry<String, Object> item : infoIds) {
				if (item.getKey() != null || item.getKey() != "") {
					String key = item.getKey();
					String val = item.getValue().toString();
					if (!key.equals("sign") && !val.equals("") && !val.equals(null)) {
						sb.append(key + val);
					}
				}
			}
			result = sb.toString();
			result = this.app_secret + result + this.app_secret;
			// 进行MD5加密
			result = T.md5(result).toUpperCase();
		} catch (Exception e) {
			return null;
		}
		return result;
	}

	// 获取京东订单
	/*
	 * type=3:表示按照更新时间拉取订单，type=1表示按照下单时间拉取订单
	 */
	public void getOrderList(String startTime, String endTime, Integer page, Integer type) {
		JSONObject object = new JSONObject();
		object.put("pageIndex", page);
		object.put("pageSize", 500);
		object.put("type", type);
		object.put("startTime", startTime);
		object.put("endTime", endTime);
		object.put("fields", "goodsInfo,categoryInfo");
		JSONObject json = new JSONObject();
		json.put("orderReq", object);
		String ret = buildRequest(json, "jd.union.open.order.row.query");
		T.create_log("jd_order.txt", ret);
		JSONObject retjson = JSONObject.parseObject(ret);
		if (retjson == null) {
			return;
		} else {
			if (retjson.containsKey("jd_union_open_order_row_query_responce")) {
				retjson = retjson.getJSONObject("jd_union_open_order_row_query_responce");
				if (retjson.containsKey("queryResult")) {
					retjson = retjson.getJSONObject("queryResult");
					if (retjson.containsKey("code") && retjson.getInteger("code") == 200) {
						if (retjson.containsKey("data")) {
							JSONArray list = retjson.getJSONArray("data");
							// 对订单进行处理
							for (Integer i = 0; i < list.size(); i = i + 1) {
								try {
									handleOrder(list.getJSONObject(i));
								} catch (SQLException | ParseException e) {
									// TODO: handle exception
									e.printStackTrace();
								}
							}
							// 订单翻页
							if (list.size() > 0) {
								getOrderList(startTime, endTime, page + 1, type);
							}
						}
					}
				}
			}
			return;
		}
	}

	// 获取京东订单
	public void getSettlementOrderList(Integer page) {
		// 获取所有的未结算的京东订单
		Map<String, W> where = new HashMap<>();
		where.put(UNION_ORDER.type, new W("eq", "jd"));
		where.put(UNION_ORDER.status, new W("eq", 0));
		where.put(UNION_ORDER.pay_time, new W("lt", System.currentTimeMillis() - 1000 * 60 * 60 * 24 * 20));
		try {
			List<Map<String, Object>> order_list = new MU(UNION_ORDER._table_name).where(where).select();
			for (Integer i = 0; i < order_list.size(); i = i + 1) {
				long pay_time = Long.valueOf(order_list.get(i).get(UNION_ORDER.pay_time).toString());
				long start_time_chuo = pay_time - 29 * 60 * 1000;
				long end_time_chuo = pay_time + 29 * 60 * 1000;
				this.getOrderList(T.date("yyyy-MM-dd HH:mm:ss", start_time_chuo), T.date("yyyy-MM-dd HH:mm:ss", end_time_chuo), page, 1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void handleOrder(JSONObject order) throws SQLException, ParseException {
		// 处理订单
		Map<String, Object> object = new HashMap<>();
		object.put(UNION_ORDER.order_sn, order.getString("id"));
		object.put(UNION_ORDER.pay_time, T.strtotime(order.getString("orderTime"), ""));
		object.put(UNION_ORDER.type, "jd");
		String order_status = order.getString("validCode");
		if (order_status.equals("-1")) {// 未知
			return;
		}
		if (order_status.equals("2")) {// 拆单
			return;
		}
		if (order_status.equals("3")) {// 取消订单
			object.put(UNION_ORDER.note, "取消订单");
			object.put(UNION_ORDER.status, -1);
		}
		if (order_status.equals("4")) {// 无效-京东帮帮主订单
			object.put(UNION_ORDER.note, "无效-京东帮帮主订单");
			object.put(UNION_ORDER.status, -1);
		}
		if (order_status.equals("5")) {// 5.无效-账号异常
			object.put(UNION_ORDER.note, "无效-账号异常");
			object.put(UNION_ORDER.status, -1);
		}
		if (order_status.equals("6")) {// 6.无效-赠品类目不返佣
			object.put(UNION_ORDER.note, "无效-赠品类目不返佣");
			object.put(UNION_ORDER.status, -1);
		}
		if (order_status.equals("7")) {// 7.无效-校园订单
			object.put(UNION_ORDER.note, "无效-校园订单");
			object.put(UNION_ORDER.status, -1);
		}
		if (order_status.equals("8")) {// 8.无效-企业订单
			object.put(UNION_ORDER.note, "无效-企业订单");
			object.put(UNION_ORDER.status, -1);
		}
		if (order_status.equals("9")) {// 9.无效-团购订单
			object.put(UNION_ORDER.note, "无效-团购订单");
			object.put(UNION_ORDER.status, -1);
		}
		if (order_status.equals("11")) {// 11.无效-乡村推广员下单
			object.put(UNION_ORDER.note, "无效-乡村推广员下单");
			object.put(UNION_ORDER.status, -1);
		}
		if (order_status.equals("13")) {// 13.无效-违规订单
			object.put(UNION_ORDER.note, "无效-违规订单");
			object.put(UNION_ORDER.status, -1);
		}
		if (order_status.equals("14")) {// 14.无效-来源与备案网址不符
			object.put(UNION_ORDER.note, "无效-来源与备案网址不符");
			object.put(UNION_ORDER.status, -1);
		}
		if (order_status.equals("15")) {// 15.待付款
			return;
		}
		if (order_status.equals("16")) {// 16.已付款
			object.put(UNION_ORDER.status, 0);
		}
		if (order_status.equals("17")) {// 17.已完成（购买用户确认收货）
			object.put(UNION_ORDER.status, 0);
		}
		// 每个月20日结算上一个自然月的金额
		// 1)判断今天是否是20日以后
		if (T.toInt(T.date("d", System.currentTimeMillis())) > 20) {
			// 判断该订单的月份是否小于当月或者该订单的年份小于当前年份
			long order_time_chuo = T.strtotime(order.getString("orderTime"), "");
			if (T.toInt(T.date("yyyy", System.currentTimeMillis())) > T.toInt(T.date("yyyy", order_time_chuo)) || T.toInt(T.date("M", System.currentTimeMillis())) > T.toInt(T.date("M", order_time_chuo))) {
				if (order_status.equals("17")) {
					object.put(UNION_ORDER.status, 1);
				}
			}
		}

		if (order_status.equals("20")) {// 20.无效-此复购订单对应的首购订单无效
			object.put(UNION_ORDER.note, "无效-此复购订单对应的首购订单无效");
			object.put(UNION_ORDER.status, -1);
		}
		if (order_status.equals("21")) {// 21.无效-云店订单
			object.put(UNION_ORDER.note, "无效-云店订单");
			object.put(UNION_ORDER.status, -1);
		}
		if (order_status.equals("22")) {// 22.无效-PLUS会员佣金比例为0
			object.put(UNION_ORDER.note, "无效-PLUS会员佣金比例为0");
			object.put(UNION_ORDER.status, -1);
		}
		if (order_status.equals("23")) {// 23.无效-支付有礼
			object.put(UNION_ORDER.note, "无效-支付有礼");
			object.put(UNION_ORDER.status, -1);
		}
		if (order_status.equals("24")) {// 24.已付定金
			object.put(UNION_ORDER.status, 0);
		}
		if (order.getString("actualCosPrice").equals("0") || order.getString("actualCosPrice").equals("0.00")) {
			object.put(UNION_ORDER.order_money, order.getString("estimateCosPrice"));
		} else {
			object.put(UNION_ORDER.order_money, order.getString("actualCosPrice"));
		}

		if (order.getString("actualFee").equals("0") || order.getString("actualFee").equals("0.00")) {
			object.put(UNION_ORDER.commission, order.getString("estimateFee"));
		} else {
			object.put(UNION_ORDER.commission, order.getString("actualFee"));
		}
		BigDecimal commission = new BigDecimal(object.get(UNION_ORDER.commission).toString());
		JSONObject commission_ret = Functions.commission(commission, "jd");
		String member_id = order.getString("positionId");
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
		where.put(UNION_ORDER.order_sn, new W("eq", order.getString("id")));
		where.put(UNION_ORDER.type, new W("eq", "jd"));
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
		order_goods_where.put(UNION_ORDER_GOODS.order_sn, new W("eq", order.getString("id")));
		Map<String, Object> save_data = new HashMap<>();
		save_data.put(UNION_ORDER_GOODS.status, -1);
		new MU(UNION_ORDER_GOODS._table_name).where(order_goods_where).save(save_data);

		Map<String, Object> goods = new HashMap<>();
		goods.put(UNION_ORDER_GOODS.goods_id, order.getString("skuId"));
		goods.put(UNION_ORDER_GOODS.goods_name, order.getString("skuName"));
		goods.put(UNION_ORDER_GOODS.goods_number, order.getString("skuNum"));
		goods.put(UNION_ORDER_GOODS.goods_pic, order.getJSONObject("goodsInfo").getString("imageUrl"));
		goods.put(UNION_ORDER_GOODS.order_sn, order.getString("id"));
		goods.put(UNION_ORDER_GOODS.type, "jd");
		goods.put(UNION_ORDER_GOODS.status, 1);

		Map<String, W> goods_where = new HashMap<>();
		goods_where.put(UNION_ORDER_GOODS.goods_id, new W("eq", order.getString("skuId")));
		goods_where.put(UNION_ORDER_GOODS.type, new W("eq", "jd"));

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
