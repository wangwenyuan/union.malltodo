package common;

import java.util.LinkedHashMap;

import com.alibaba.fastjson.JSONObject;

public class MalltodoMerchantConfig {
	private static JSONObject menu = null;

	public static JSONObject getMenu() {
		if (MalltodoMerchantConfig.menu == null) {
			MalltodoMerchantConfig.menu = new JSONObject(new LinkedHashMap<>());

			JSONObject Service = new JSONObject(new LinkedHashMap<>());
			Service.put("_name", "我的服务");
			Service.put("_isshow", true);
			Service.put("_auth", true);
			Service.put("_icon", "icon-building");
			Service.put("Index", new JSONObject(new LinkedHashMap<>()));
			Service.getJSONObject("Index").put("_isshow", true);
			Service.getJSONObject("Index").put("_auth", true);
			Service.getJSONObject("Index").put("_name", "我的服务");
			Service.getJSONObject("Index").put("index", "可用的服务");

			Service.put("All", new JSONObject(new LinkedHashMap<>()));
			Service.getJSONObject("All").put("_isshow", true);
			Service.getJSONObject("All").put("_auth", true);
			Service.getJSONObject("All").put("_name", "所有服务");
			Service.getJSONObject("All").put("index", "所有服务");

			Service.put("Renew", new JSONObject(new LinkedHashMap<>()));
			Service.getJSONObject("Renew").put("_isshow", true);
			Service.getJSONObject("Renew").put("_auth", true);
			Service.getJSONObject("Renew").put("_name", "急需续费的服务");
			Service.getJSONObject("Renew").put("index", "急需续费的服务");

			menu.put("Service", Service);

			JSONObject Order = new JSONObject(new LinkedHashMap<>());
			Order.put("_name", "我的订单");
			Order.put("_isshow", true);
			Order.put("_auth", true);
			Order.put("_icon", "icon-building");
			Order.put("Index", new JSONObject(new LinkedHashMap<>()));
			Order.getJSONObject("Index").put("_isshow", true);
			Order.getJSONObject("Index").put("_auth", true);
			Order.getJSONObject("Index").put("_name", "订单列表");
			Order.getJSONObject("Index").put("index", "订单列表");
			menu.put("Order", Order);

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
