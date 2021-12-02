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

public class WITHDRAWALS_SET {
	public static String _table_name = "withdrawals_set";
	public static String id = "id";
	public static String min_money = "min_money";
	public Map<String, Object> map = new HashMap<>();

	public WITHDRAWALS_SET() {
		map.put("min_money", 0);
	}

	public JSONObject check_add(Map<String, Object> data) {
		for (String key : data.keySet()) {
			if (map.containsKey(key)) {
				if (key.equals("min_money")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("最少提现金额最大值2147483647");
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
				if (key.equals("min_money")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("最少提现金额最大值2147483647");
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
