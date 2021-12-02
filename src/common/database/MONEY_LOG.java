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
package common.database;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.tools.T;

import common.Common;

public class MONEY_LOG {
	public static String _table_name = "money_log";
	public static String id = "id";
	public static String uid = "uid";
	public static String admin_id = "admin_id";
	public static String money = "money";
	public static String money_type = "money_type";
	public static String addtime = "addtime";
	public static String table_name = "table_name";
	public static String row_id = "row_id";
	public static String type = "type";
	public static String status = "status";
	public static String log = "log";
	public Map<String, Object> map = new HashMap<>();

	public MONEY_LOG() {
		map.put("uid", 0);
		map.put("admin_id", 0);
		map.put("money", 0);
		map.put("money_type", 0);
		map.put("addtime", 0);
		map.put("table_name", "");
		map.put("row_id", 0);
		map.put("type", 0);
		map.put("status", 0);
		map.put("log", "");
	}

	public JSONObject check_add(Map<String, Object> data) {
		for (String key : data.keySet()) {
			if (map.containsKey(key)) {
				if (key.equals("uid")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("uid最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("admin_id")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("admin_id最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("money")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("money最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("money_type")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("0：可提现；1：不可提现最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("addtime")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("addtime最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("table_name")) {
					if (data.get(key).toString().length() > 32) {
						return Common.error("table_name最长32个字符");
					}
				}
				if (key.equals("row_id")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("row_id最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("type")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("1 管理员调整 2 充值 3 支付订单 4 奖励 5 提现 6 订单退还金额 7 会员降级 8 查看贸易信息联系电话最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("status")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("-1 拒绝 0 未处理 1 已处理最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("log")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("log最长255个字符");
					}
				}
				this.map.put(key, data.get(key));
			}
		}
		return Common.success(this.map);
	}

	public JSONObject check_edit(Map<String, Object> data) {
		Map<String, Object> new_data = new HashMap<>();
		for (String key : data.keySet()) {
			if (map.containsKey(key)) {
				if (key.equals("uid")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("uid最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("admin_id")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("admin_id最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("money")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("money最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("money_type")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("0：可提现；1：不可提现最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("addtime")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("addtime最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("table_name")) {
					if (data.get(key).toString().length() > 32) {
						return Common.error("table_name最长32个字符");
					}
				}
				if (key.equals("row_id")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("row_id最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("type")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("1 管理员调整 2 充值 3 支付订单 4 奖励 5 提现 6 订单退还金额 7 会员降级 8 查看贸易信息联系电话最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("status")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("-1 拒绝 0 未处理 1 已处理最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("log")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("log最长255个字符");
					}
				}
				new_data.put(key, data.get(key));
			}
		}
		return Common.success(new_data);
	}
}
