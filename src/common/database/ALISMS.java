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

public class ALISMS {
	public static String _table_name = "alisms";
	public static String id = "id";
	public static String alisms_signname = "alisms_signname";
	public static String alisms_access_key_id = "alisms_access_key_id";
	public static String alisms_access_key_secret = "alisms_access_key_secret";
	public static String alisms_template_code = "alisms_template_code";
	public static String sms_period_of_validity = "sms_period_of_validity";
	public Map<String, Object> map = new HashMap<>();

	public ALISMS() {
		map.put("alisms_signname", "");
		map.put("alisms_access_key_id", "");
		map.put("alisms_access_key_secret", "");
		map.put("alisms_template_code", "");
		map.put("sms_period_of_validity", 0);
	}

	public JSONObject check_add(Map<String, Object> data) {
		for (String key : data.keySet()) {
			if (map.containsKey(key)) {
				if (key.equals("alisms_signname")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("短信签名最长99个字符");
					}
				}
				if (key.equals("alisms_access_key_id")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("alisms_access_key_id最长99个字符");
					}
				}
				if (key.equals("alisms_access_key_secret")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("alisms_access_key_secret最长99个字符");
					}
				}
				if (key.equals("alisms_template_code")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("alisms_template_code最长99个字符");
					}
				}
				if (key.equals("sms_period_of_validity")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("验证码有效期（秒）最大值2147483647");
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
				if (key.equals("alisms_signname")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("短信签名最长99个字符");
					}
				}
				if (key.equals("alisms_access_key_id")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("alisms_access_key_id最长99个字符");
					}
				}
				if (key.equals("alisms_access_key_secret")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("alisms_access_key_secret最长99个字符");
					}
				}
				if (key.equals("alisms_template_code")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("alisms_template_code最长99个字符");
					}
				}
				if (key.equals("sms_period_of_validity")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("验证码有效期（秒）最大值2147483647");
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
