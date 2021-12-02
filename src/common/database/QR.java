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

public class QR {
	public static String _table_name = "qr";
	public static String id = "id";
	public static String bgimg = "bgimg";
	public static String bgimg_width = "bgimg_width";
	public static String bgimg_height = "bgimg_height";
	public static String qrimg_width = "qrimg_width";
	public static String qrimg_height = "qrimg_height";
	public static String qrimg_left = "qrimg_left";
	public static String qrimg_top = "qrimg_top";
	public Map<String, Object> map = new HashMap<>();

	public QR() {
		map.put("bgimg", "");
		map.put("bgimg_width", 0);
		map.put("bgimg_height", 0);
		map.put("qrimg_width", 0);
		map.put("qrimg_height", 0);
		map.put("qrimg_left", 0);
		map.put("qrimg_top", 0);
	}

	public JSONObject check_add(Map<String, Object> data) {
		for (String key : data.keySet()) {
			if (map.containsKey(key)) {
				if (key.equals("bgimg")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("bgimg最长255个字符");
					}
				}
				if (key.equals("bgimg_width")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("bgimg_width最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("bgimg_height")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("bgimg_height最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("qrimg_width")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("qrimg_width最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("qrimg_height")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("qrimg_height最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("qrimg_left")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("qrimg_left最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("qrimg_top")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("qrimg_top最大值2147483647");
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
				if (key.equals("bgimg")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("bgimg最长255个字符");
					}
				}
				if (key.equals("bgimg_width")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("bgimg_width最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("bgimg_height")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("bgimg_height最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("qrimg_width")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("qrimg_width最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("qrimg_height")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("qrimg_height最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("qrimg_left")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("qrimg_left最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("qrimg_top")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("qrimg_top最大值2147483647");
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
