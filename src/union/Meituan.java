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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.Http;
import com.javatodo.core.tools.T;

import common.Functions;
import common.MU;
import common.database.COMMISSION_SET;
import common.database.MEITUAN;
import common.database.UNION_ORDER;
import common.database.UNION_ORDER_GOODS;
import model.Balance;

public class Meituan {
	private String app_key = "";

	private String app_secret = "";

	private String meituan_miniprogram_appid = "wxde8ac0a21135c07d";

	public Meituan() {
		Map<String, Object> info;
		try {
			info = new MU(MEITUAN._table_name).order(MEITUAN.id + " desc").find();
			if (info != null) {
				this.app_key = info.get(MEITUAN.app_key).toString();
				this.app_secret = info.get(MEITUAN.app_secret).toString();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String genSign(Map<String, String> params) {
		String result = "";
		try {
			List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(params.entrySet());
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
			result = this.app_secret + result + this.app_secret;
			// 进行MD5加密
			result = T.md5(result);
		} catch (Exception e) {
			return null;
		}
		return result;
	}

	/*
	 * @param String meituan_act_id：活动id，可以在联盟活动列表中查看获取
	 * 
	 * @param Integer link_type：链接类型，1 h5链接；2 deeplink(唤起)链接；3 中间页唤起链接；4 微信小程序唤起路径
	 */
	public String getLink(String meituan_act_id, String uid, Integer link_type) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("actId", meituan_act_id);
		params.put("appkey", this.app_key);
		params.put("sid", uid);
		params.put("linkType", link_type + "");
		params.put("sign", this.genSign(params));
		String url = "https://openapi.meituan.com/api/generateLink?";
		String param = "";
		for (String key : params.keySet()) {
			if (param.equals("")) {
				param = key + "=" + params.get(key);
			} else {
				param = param + "&" + key + "=" + params.get(key);
			}
		}
		url = url + param;
		Http http = new Http();
		String ret = http.get(url);
		if (ret.equals("")) {
			return null;
		} else {
			JSONObject object = JSONObject.parseObject(ret);
			if (object.containsKey("data")) {
				return object.getString("data");
			} else {
				return null;
			}
		}
	}

	public String getMiniProgramQr(String meituan_act_id, String uid) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("actId", meituan_act_id);
		params.put("appkey", this.app_key);
		params.put("sid", uid);
		params.put("sign", this.genSign(params));
		String url = "https://openapi.meituan.com/api/miniCode?";
		String param = "";
		for (String key : params.keySet()) {
			if (param.equals("")) {
				param = key + "=" + params.get(key);
			} else {
				param = param + "&" + key + "=" + params.get(key);
			}
		}
		url = url + param;
		Http http = new Http();
		String ret = http.get(url);
		if (ret.equals("")) {
			return null;
		} else {
			JSONObject object = JSONObject.parseObject(ret);
			if (object.containsKey("data")) {
				return object.getString("data");
			} else {
				return null;
			}
		}
	}

	// 获取美团订单
	public void getOrderList(String startTime, String endTime, Integer page) {
		// this.getOrderList("2", startTime, endTime, page);
		// this.getOrderList("4", startTime, endTime, page);
		// this.getOrderList("5", startTime, endTime, page);
		// this.getOrderList("6", startTime, endTime, page);
		// this.getOrderList("8", startTime, endTime, page);
	}

	// 获取美团订单
	/**
	 * @param order_type:2
	 *            酒店订单；4 外卖订单；5 话费&团好货订单；6 闪购订单；8 优选订单
	 **/
	public void getOrderList(Integer actId, String startTime, String endTime, Integer page) {
		Map<String, String> map = new HashMap<>();
		map.put("appkey", this.app_key);
		map.put("ts", (System.currentTimeMillis() / 1000) + "");
		map.put("actId", actId + "");
		try {
			map.put("startTime", String.valueOf(T.strtotime(startTime, "") / 1000));
			map.put("endTime", String.valueOf(T.strtotime(endTime, "") / 1000));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("page", page + "");
		map.put("limit", "100");
		map.put("queryTimeType", "1");
		map.put("sign", this.genSign(map));
		String url = "https://openapi.meituan.com/api/orderList?";
		String param = "";
		for (String key : map.keySet()) {
			if (param.equals("")) {
				param = key + "=" + map.get(key);
			} else {
				param = param + "&" + key + "=" + map.get(key);
			}
		}
		url = url + param;
		Http http = new Http();
		String ret = http.get(url);
		T.create_log("meituan_order.txt", ret);
		if (ret == null) {
			return;
		}
		if (ret.equals("")) {
			return;
		} else {
			JSONObject object = JSONObject.parseObject(ret);
			if (object.containsKey("dataList")) {
				JSONArray list = object.getJSONArray("dataList");
				for (Integer i = 0; i < list.size(); i = i + 1) {
					try {
						this.handleOrder(list.getJSONObject(i));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// 订单翻页
				if (list.size() > 0) {
					this.getOrderList(actId, startTime, endTime, page + 1);
				} else {
					return;
				}
			} else {
				return;
			}
		}
	}

	private void handleOrder(JSONObject order) throws SQLException {
		// 处理订单
		Map<String, Object> object = new HashMap<>();
		object.put(UNION_ORDER.order_sn, order.getString("orderid"));
		object.put(UNION_ORDER.pay_time, order.getString("paytime") + "000");
		object.put(UNION_ORDER.type, "meituan");
		object.put(UNION_ORDER.order_money, order.getString("payprice"));
		String order_status = order.getString("status");
		if (order_status.equals("1")) {// 已付款
			object.put(UNION_ORDER.status, 0);
		}
		if (order_status.equals("8")) {// 已完成
			object.put(UNION_ORDER.status, 1);
		}
		if (order_status.equals("9")) {// 已退款或风控
			object.put(UNION_ORDER.note, "已退款或风控");
			object.put(UNION_ORDER.status, -1);
		}
		BigDecimal profit = new BigDecimal(0);
		if (order.containsKey("profit") && order.get("profit") != null) {
			profit = new BigDecimal(order.getString("profit"));
		}
		BigDecimal cpaProfit = new BigDecimal(0);
		if (order.containsKey("cpaProfit") && order.get("cpaProfit") != null) {
			cpaProfit = new BigDecimal(order.getString("cpaProfit"));
		}
		BigDecimal refundprofit = new BigDecimal(0);
		if (order.containsKey("refundprofit") && order.get("refundprofit") != null) {
			refundprofit = new BigDecimal(order.getString("refundprofit"));
		}
		BigDecimal cpaRefundProfit = new BigDecimal(0);
		if (order.containsKey("cpaRefundProfit") && order.get("cpaRefundProfit") != null) {
			cpaRefundProfit = new BigDecimal(order.getString("cpaRefundProfit"));
		}

		BigDecimal commission = profit.add(cpaProfit).subtract(refundprofit).subtract(cpaRefundProfit);
		if (commission.compareTo(new BigDecimal(0)) == -1) {
			commission = new BigDecimal(0);
		}
		object.put(UNION_ORDER.commission, commission);
		JSONObject commission_ret = Functions.commission(commission, "meituan");
		String member_id = order.getString("sid");
		member_id = member_id.replace("malltodo", "");
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
		where.put(UNION_ORDER.order_sn, new W("eq", order.getString("orderid")));
		where.put(UNION_ORDER.type, new W("eq", "meituan"));
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
		order_goods_where.put(UNION_ORDER_GOODS.order_sn, new W("eq", order.getString("orderid")));
		Map<String, Object> save_data = new HashMap<>();
		save_data.put(UNION_ORDER_GOODS.status, -1);
		new MU(UNION_ORDER_GOODS._table_name).where(order_goods_where).save(save_data);

		Map<String, Object> goods = new HashMap<>();
		goods.put(UNION_ORDER_GOODS.goods_id, order.getString("orderid"));
		goods.put(UNION_ORDER_GOODS.goods_name, order.getString("smstitle"));
		goods.put(UNION_ORDER_GOODS.goods_number, 1);
		goods.put(UNION_ORDER_GOODS.goods_pic, "");
		goods.put(UNION_ORDER_GOODS.order_sn, order.getString("orderid"));
		goods.put(UNION_ORDER_GOODS.type, "meituan");
		goods.put(UNION_ORDER_GOODS.status, 1);

		Map<String, W> goods_where = new HashMap<>();
		goods_where.put(UNION_ORDER_GOODS.goods_id, new W("eq", order.getString("orderid")));
		goods_where.put(UNION_ORDER_GOODS.type, new W("eq", "meituan"));

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
