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

public class ADMIN_GROUP {
	public static String _table_name = "admin_group";
	public static String id = "id";
	public static String pid = "pid";
	public static String name = "name";
	public static String pids = "pids";
	public static String subids = "subids";
	public static String sort = "sort";
	public static String is_del = "is_del";
	public Map<String, Object> map = new HashMap<>();

	public ADMIN_GROUP() {
		map.put("pid", 0);
		map.put("name", "");
		map.put("pids", "");
		map.put("subids", "");
		map.put("sort", 0);
		map.put("is_del", 0);
	}

	public JSONObject check_add(Map<String, Object> data) {
		for (String key : data.keySet()) {
			if (map.containsKey(key)) {
				if (key.equals("pid")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("pid最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("name")) {
					if (data.get(key).toString().length() > 50) {
						return Common.error("name最长50个字符");
					}
				}
				if (key.equals("pids")) {
					if (data.get(key).toString().length() > 65535) {
						return Common.error("pids最长65535个字符");
					}
				}
				if (key.equals("subids")) {
					if (data.get(key).toString().length() > 65535) {
						return Common.error("subids最长65535个字符");
					}
				}
				if (key.equals("sort")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("排序最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("is_del")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("is_del最大值2147483647");
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
				if (key.equals("pid")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("pid最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("name")) {
					if (data.get(key).toString().length() > 50) {
						return Common.error("name最长50个字符");
					}
				}
				if (key.equals("pids")) {
					if (data.get(key).toString().length() > 65535) {
						return Common.error("pids最长65535个字符");
					}
				}
				if (key.equals("subids")) {
					if (data.get(key).toString().length() > 65535) {
						return Common.error("subids最长65535个字符");
					}
				}
				if (key.equals("sort")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("排序最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("is_del")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("is_del最大值2147483647");
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
