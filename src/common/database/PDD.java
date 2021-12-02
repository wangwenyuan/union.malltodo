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

import common.Common;

public class PDD {
	public static String _table_name = "pdd";
	public static String id = "id";
	public static String client_id = "client_id";
	public static String client_secret = "client_secret";
	public static String pid = "pid";
	public Map<String, Object> map = new HashMap<>();

	public PDD() {
		map.put("client_id", "");
		map.put("client_secret", "");
		map.put("pid", "");
	}

	public JSONObject check_add(Map<String, Object> data) {
		for (String key : data.keySet()) {
			if (map.containsKey(key)) {
				if (key.equals("client_id")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("client_id最长255个字符");
					}
				}
				if (key.equals("client_secret")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("client_secret最长255个字符");
					}
				}
				if (key.equals("pid")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("pid最长99个字符");
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
				if (key.equals("client_id")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("client_id最长255个字符");
					}
				}
				if (key.equals("client_secret")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("client_secret最长255个字符");
					}
				}
				if (key.equals("pid")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("pid最长99个字符");
					}
				}
				new_data.put(key, data.get(key));
			}
		}
		return Common.success(new_data);
	}
}
