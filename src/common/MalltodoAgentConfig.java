package common;

import java.util.LinkedHashMap;

import com.alibaba.fastjson.JSONObject;

public class MalltodoAgentConfig {
	private static JSONObject menu = null;

	public static JSONObject getMenu() {
		if (MalltodoAgentConfig.menu == null) {
			MalltodoAgentConfig.menu = new JSONObject(new LinkedHashMap<>());

			JSONObject Custom = new JSONObject(new LinkedHashMap<>());
			Custom.put("_name", "我的客户");
			Custom.put("_isshow", true);
			Custom.put("_auth", true);
			Custom.put("_icon", "icon-building");
			Custom.put("Index", new JSONObject(new LinkedHashMap<>()));
			Custom.getJSONObject("Index").put("_isshow", true);
			Custom.getJSONObject("Index").put("_auth", true);
			Custom.getJSONObject("Index").put("_name", "我的客户");
			Custom.getJSONObject("Index").put("index", "我的客户");

			Custom.put("Renew", new JSONObject(new LinkedHashMap<>()));
			Custom.getJSONObject("Renew").put("_isshow", true);
			Custom.getJSONObject("Renew").put("_auth", true);
			Custom.getJSONObject("Renew").put("_name", "急需续费的服务");
			Custom.getJSONObject("Renew").put("index", "急需续费的服务");

			menu.put("Custom", Custom);

			JSONObject Order = new JSONObject(new LinkedHashMap<>());
			Order.put("_name", "客户订单");
			Order.put("_isshow", true);
			Order.put("_auth", true);
			Order.put("_icon", "icon-building");
			Order.put("Index", new JSONObject(new LinkedHashMap<>()));
			Order.getJSONObject("Index").put("_isshow", true);
			Order.getJSONObject("Index").put("_auth", true);
			Order.getJSONObject("Index").put("_name", "订单列表");
			Order.getJSONObject("Index").put("index", "订单列表");
			menu.put("Order", Order);

			JSONObject Finance = new JSONObject(new LinkedHashMap<>());
			Finance.put("_name", "资金记录");
			Finance.put("_isshow", true);
			Finance.put("_auth", true);
			Finance.put("_icon", "icon-building");
			Finance.put("Index", new JSONObject(new LinkedHashMap<>()));
			Finance.getJSONObject("Index").put("_isshow", true);
			Finance.getJSONObject("Index").put("_auth", true);
			Finance.getJSONObject("Index").put("_name", "资金记录");
			Finance.getJSONObject("Index").put("index", "资金记录");
			menu.put("Finance", Finance);

			JSONObject Account = new JSONObject(new LinkedHashMap<>());
			Account.put("_name", "账户中心");
			Account.put("_isshow", true);
			Account.put("_auth", true);
			Account.put("_icon", "icon-building");
			Account.put("Index", new JSONObject(new LinkedHashMap<>()));
			Account.getJSONObject("Index").put("_isshow", true);
			Account.getJSONObject("Index").put("_auth", true);
			Account.getJSONObject("Index").put("_name", "资料设置");
			Account.getJSONObject("Index").put("index", "资料设置");

			Account.put("Account", new JSONObject(new LinkedHashMap<>()));
			Account.getJSONObject("Account").put("_isshow", true);
			Account.getJSONObject("Account").put("_auth", true);
			Account.getJSONObject("Account").put("_name", "手机号修改");
			Account.getJSONObject("Account").put("index", "手机号修改");

			Account.put("Password", new JSONObject(new LinkedHashMap<>()));
			Account.getJSONObject("Password").put("_isshow", true);
			Account.getJSONObject("Password").put("_auth", true);
			Account.getJSONObject("Password").put("_name", "密码修改");
			Account.getJSONObject("Password").put("index", "密码修改");

			menu.put("Account", Account);

		}
		return menu;
	}
}
