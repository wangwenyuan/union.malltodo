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

public class MEMBER {
	public static String _table_name = "member";
	public static String id = "id";
	public static String username = "username";
	public static String mobile = "mobile";
	public static String password = "password";
	public static String pic = "pic";
	public static String pid = "pid";
	public static String ppid = "ppid";
	public static String pppid = "pppid";
	public static String recommend_code = "recommend_code";
	public static String web_poster = "web_poster";
	public static String createtime = "createtime";
	public static String money = "money";
	public static String money_0 = "money_0";
	public static String money_1 = "money_1";
	public static String is_del = "is_del";
	public Map<String, Object> map = new HashMap<>();

	public MEMBER() {
		map.put("username", "");
		map.put("mobile", "");
		map.put("password", "");
		map.put("pic", "");
		map.put("pid", 0);
		map.put("ppid", 0);
		map.put("pppid", 0);
		map.put("recommend_code", "");
		map.put("web_poster", "");
		map.put("createtime", 0);
		map.put("money", 0.00);
		map.put("money_0", 0.00);
		map.put("money_1", 0.00);
		map.put("is_del", 0);
	}

	public JSONObject check_add(Map<String, Object> data) {
		for (String key : data.keySet()) {
			if (map.containsKey(key)) {
				if (key.equals("username")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("username最长99个字符");
					}
				}
				if (key.equals("mobile")) {
					if (data.get(key).toString().length() > 25) {
						return Common.error("mobile最长25个字符");
					}
				}
				if (key.equals("password")) {
					if (data.get(key).toString().length() > 32) {
						return Common.error("password最长32个字符");
					}
				}
				if (key.equals("pic")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("会员头像最长255个字符");
					}
				}
				if (key.equals("pid")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("pid最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("ppid")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("ppid最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("pppid")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("pppid最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("recommend_code")) {
					if (data.get(key).toString().length() > 25) {
						return Common.error("推荐码最长25个字符");
					}
				}
				if (key.equals("web_poster")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("普通网页二维码海报链接最长255个字符");
					}
				}
				if (key.equals("createtime")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("createtime最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("money")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("余额最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("money_0")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("可提现的余额最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("money_1")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("不可提现的余额最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("is_del")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("0 未删除 1 已删除最大值2147483647");
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
				if (key.equals("username")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("username最长99个字符");
					}
				}
				if (key.equals("mobile")) {
					if (data.get(key).toString().length() > 25) {
						return Common.error("mobile最长25个字符");
					}
				}
				if (key.equals("password")) {
					if (data.get(key).toString().length() > 32) {
						return Common.error("password最长32个字符");
					}
				}
				if (key.equals("pic")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("会员头像最长255个字符");
					}
				}
				if (key.equals("pid")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("pid最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("ppid")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("ppid最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("pppid")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("pppid最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("recommend_code")) {
					if (data.get(key).toString().length() > 25) {
						return Common.error("推荐码最长25个字符");
					}
				}
				if (key.equals("web_poster")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("普通网页二维码海报链接最长255个字符");
					}
				}
				if (key.equals("createtime")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("createtime最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("money")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("余额最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("money_0")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("可提现的余额最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("money_1")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("不可提现的余额最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("is_del")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("0 未删除 1 已删除最大值2147483647");
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
