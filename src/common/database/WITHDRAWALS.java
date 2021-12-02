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

public class WITHDRAWALS {
	public static String _table_name = "withdrawals";
	public static String id = "id";
	public static String uid = "uid";
	public static String money = "money";
	public static String examine_admin_id = "examine_admin_id";
	public static String examine_status = "examine_status";
	public static String no_pass_reason = "no_pass_reason";
	public static String serial_number = "serial_number";
	public static String addtime = "addtime";
	public static String examine_time = "examine_time";
	public Map<String, Object> map = new HashMap<>();

	public WITHDRAWALS() {
		map.put("uid", 0);
		map.put("money", 0);
		map.put("examine_admin_id", 0);
		map.put("examine_status", "0");
		map.put("no_pass_reason", "");
		map.put("serial_number", "");
		map.put("addtime", 0);
		map.put("examine_time", 0);
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
				if (key.equals("money")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("提现金额最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("examine_admin_id")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("执行审核的管理员id最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("examine_status")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("审核状态 1 审核通过 0 未审核 -1 审核不通过最长255个字符");
					}
				}
				if (key.equals("no_pass_reason")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("审核不通过原因最长255个字符");
					}
				}
				if (key.equals("serial_number")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("支付流水号最长255个字符");
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
				if (key.equals("examine_time")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("examine_time最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
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
				if (key.equals("money")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("提现金额最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("examine_admin_id")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("执行审核的管理员id最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("examine_status")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("审核状态 1 审核通过 0 未审核 -1 审核不通过最长255个字符");
					}
				}
				if (key.equals("no_pass_reason")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("审核不通过原因最长255个字符");
					}
				}
				if (key.equals("serial_number")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("支付流水号最长255个字符");
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
				if (key.equals("examine_time")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("examine_time最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				new_data.put(key, data.get(key));
			}
		}
		return Common.success(new_data);
	}
}
