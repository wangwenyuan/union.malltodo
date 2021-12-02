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

public class COMMISSION_SET {
	public static String _table_name = "commission_set";
	public static String id = "id";
	public static String level_1 = "level_1";
	public static String level_2 = "level_2";
	public static String level_3 = "level_3";
	public static String platform = "platform";
	public static String is_Internal_purchase = "is_Internal_purchase";
	public Map<String, Object> map = new HashMap<>();

	public COMMISSION_SET() {
		map.put("level_1", 0);
		map.put("level_2", 0);
		map.put("level_3", 0);
		map.put("platform", "");
		map.put("is_Internal_purchase", 0);
	}

	public JSONObject check_add(Map<String, Object> data) {
		for (String key : data.keySet()) {
			if (map.containsKey(key)) {
				if (key.equals("level_1")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("level_1最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("level_2")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("level_2最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("level_3")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("level_3最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("platform")) {
					if (data.get(key).toString().length() > 25) {
						return Common.error("platform最长25个字符");
					}
				}
				if (key.equals("is_Internal_purchase")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("0 不开启内购 1 开启内购最大值2147483647");
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
				if (key.equals("level_1")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("level_1最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("level_2")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("level_2最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("level_3")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("level_3最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("platform")) {
					if (data.get(key).toString().length() > 25) {
						return Common.error("platform最长25个字符");
					}
				}
				if (key.equals("is_Internal_purchase")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("0 不开启内购 1 开启内购最大值2147483647");
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
