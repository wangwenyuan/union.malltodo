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
package common;

import java.util.LinkedHashMap;

import com.alibaba.fastjson.JSONObject;

public class AdminMenuConfig {
	private static JSONObject menu = null;

	public static JSONObject getMenu() {
		if (AdminMenuConfig.menu == null) {
			AdminMenuConfig.menu = new JSONObject(new LinkedHashMap<>());

			JSONObject Order = new JSONObject(new LinkedHashMap<>());
			Order.put("_name", "订单管理");
			Order.put("_isshow", true);
			Order.put("_auth", true);
			Order.put("_icon", "icon-truck");
			Order.put("Index", new JSONObject(new LinkedHashMap<>()));
			Order.getJSONObject("Index").put("_isshow", true);
			Order.getJSONObject("Index").put("_auth", true);
			Order.getJSONObject("Index").put("_name", "订单管理");
			Order.getJSONObject("Index").put("index", "订单列表");
			menu.put("Order", Order);

			JSONObject Member = new JSONObject(new LinkedHashMap<>());
			Member.put("_name", "会员管理");
			Member.put("_isshow", true);
			Member.put("_auth", true);
			Member.put("_icon", "icon-user");
			Member.put("Index", new JSONObject(new LinkedHashMap<>()));
			Member.getJSONObject("Index").put("_isshow", true);
			Member.getJSONObject("Index").put("_auth", true);
			Member.getJSONObject("Index").put("_name", "会员管理");
			Member.getJSONObject("Index").put("index", "会员管理");
			Member.getJSONObject("Index").put("balance", "会员余额调整");
			menu.put("Member", Member);

			JSONObject Qr = new JSONObject(new LinkedHashMap<>());
			Qr.put("_name", "推广海报");
			Qr.put("_isshow", true);
			Qr.put("_auth", true);
			Qr.put("_icon", "icon-qrcode");
			Qr.put("Index", new JSONObject(new LinkedHashMap<>()));
			Qr.getJSONObject("Index").put("_isshow", true);
			Qr.getJSONObject("Index").put("_auth", true);
			Qr.getJSONObject("Index").put("_name", "海报设计");
			Qr.getJSONObject("Index").put("index", "海报设计");
			menu.put("Qr", Qr);

			JSONObject Commission = new JSONObject(new LinkedHashMap<>());
			Commission.put("_name", "三级分销");
			Commission.put("_isshow", true);
			Commission.put("_auth", true);
			Commission.put("_icon", "icon-sitemap");
			Commission.put("Pdd", new JSONObject(new LinkedHashMap<>()));
			Commission.getJSONObject("Pdd").put("_isshow", true);
			Commission.getJSONObject("Pdd").put("_auth", true);
			Commission.getJSONObject("Pdd").put("_name", "拼多多分销设置");
			Commission.getJSONObject("Pdd").put("index", "拼多多分销设置");
			Commission.put("Jd", new JSONObject(new LinkedHashMap<>()));
			Commission.getJSONObject("Jd").put("_isshow", true);
			Commission.getJSONObject("Jd").put("_auth", true);
			Commission.getJSONObject("Jd").put("_name", "京东分销设置");
			Commission.getJSONObject("Jd").put("index", "京东分销设置");
			Commission.put("Meituan", new JSONObject(new LinkedHashMap<>()));
			Commission.getJSONObject("Meituan").put("_isshow", true);
			Commission.getJSONObject("Meituan").put("_auth", true);
			Commission.getJSONObject("Meituan").put("_name", "美团分销设置");
			Commission.getJSONObject("Meituan").put("index", "美团分销设置");
			menu.put("Commission", Commission);

			JSONObject Finance = new JSONObject(new LinkedHashMap<>());
			Finance.put("_name", "财务管理");
			Finance.put("_isshow", true);
			Finance.put("_auth", true);
			Finance.put("_icon", "icon-cny");
			Finance.put("Index", new JSONObject(new LinkedHashMap<>()));
			Finance.getJSONObject("Index").put("_isshow", true);
			Finance.getJSONObject("Index").put("_auth", true);
			Finance.getJSONObject("Index").put("_name", "财务管理");
			Finance.getJSONObject("Index").put("index", "会员提现列表");
			Finance.getJSONObject("Index").put("examine", "会员提现审核");
			menu.put("Finance", Finance);

			JSONObject Renovation = new JSONObject(new LinkedHashMap<>());
			Renovation.put("_name", "店铺装修");
			Renovation.put("_isshow", true);
			Renovation.put("_auth", true);
			Renovation.put("_icon", "icon-building");
			Renovation.put("Index", new JSONObject(new LinkedHashMap<>()));
			Renovation.getJSONObject("Index").put("_isshow", true);
			Renovation.getJSONObject("Index").put("_auth", true);
			Renovation.getJSONObject("Index").put("_name", "商城首页");
			Renovation.getJSONObject("Index").put("index", "首页页面设计");
			Renovation.getJSONObject("Index").put("add", "新增首页页面设计");
			Renovation.getJSONObject("Index").put("edit", "编辑首页页面设计");
			Renovation.getJSONObject("Index").put("del", "删除首页页面设计");
			Renovation.getJSONObject("Index").put("setDefault", "模板开启开关");

			Renovation.put("Menu", new JSONObject(new LinkedHashMap<>()));
			Renovation.getJSONObject("Menu").put("_isshow", true);
			Renovation.getJSONObject("Menu").put("_auth", true);
			Renovation.getJSONObject("Menu").put("_name", "栏目页面");
			Renovation.getJSONObject("Menu").put("index", "栏目页面设计");
			Renovation.getJSONObject("Menu").put("add", "新增栏目页面设计");
			Renovation.getJSONObject("Menu").put("edit", "编辑栏目页面设计");
			Renovation.getJSONObject("Menu").put("del", "删除栏目页面设计");
			Renovation.getJSONObject("Menu").put("setDefault", "模板开启开关");

			Renovation.put("GoodsCategory", new JSONObject(new LinkedHashMap<>()));
			Renovation.getJSONObject("GoodsCategory").put("_isshow", true);
			Renovation.getJSONObject("GoodsCategory").put("_auth", true);
			Renovation.getJSONObject("GoodsCategory").put("_name", "商品列表页面");
			Renovation.getJSONObject("GoodsCategory").put("index", "商品列表页面设计");
			Renovation.getJSONObject("GoodsCategory").put("add", "新增商品列表页面设计");
			Renovation.getJSONObject("GoodsCategory").put("edit", "编辑商品列表页面设计");
			Renovation.getJSONObject("GoodsCategory").put("del", "删除商品列表页面设计");
			Renovation.getJSONObject("GoodsCategory").put("setDefault", "模板开启开关");

			Renovation.put("Search", new JSONObject(new LinkedHashMap<>()));
			Renovation.getJSONObject("Search").put("_isshow", true);
			Renovation.getJSONObject("Search").put("_auth", true);
			Renovation.getJSONObject("Search").put("_name", "搜索页面");
			Renovation.getJSONObject("Search").put("index", "搜索页面设计");
			Renovation.getJSONObject("Search").put("add", "新增搜索页面设计");
			Renovation.getJSONObject("Search").put("edit", "编辑搜索页面设计");
			Renovation.getJSONObject("Search").put("del", "删除搜索页面设计");
			Renovation.getJSONObject("Search").put("setDefault", "模板开启开关");

			Renovation.put("GoodsDetail", new JSONObject(new LinkedHashMap<>()));
			Renovation.getJSONObject("GoodsDetail").put("_isshow", true);
			Renovation.getJSONObject("GoodsDetail").put("_auth", true);
			Renovation.getJSONObject("GoodsDetail").put("_name", "商品详情页面");
			Renovation.getJSONObject("GoodsDetail").put("index", "商品详情页面设计");
			Renovation.getJSONObject("GoodsDetail").put("add", "新增商品详情页面设计");
			Renovation.getJSONObject("GoodsDetail").put("edit", "编辑商品详情页面设计");
			Renovation.getJSONObject("GoodsDetail").put("del", "删除商品详情页面设计");
			Renovation.getJSONObject("GoodsDetail").put("setDefault", "模板开启开关");

			Renovation.put("User", new JSONObject(new LinkedHashMap<>()));
			Renovation.getJSONObject("User").put("_isshow", true);
			Renovation.getJSONObject("User").put("_auth", true);
			Renovation.getJSONObject("User").put("_name", "会员中心页面");
			Renovation.getJSONObject("User").put("index", "会员中心页面设计");
			Renovation.getJSONObject("User").put("add", "新增会员中心页面设计");
			Renovation.getJSONObject("User").put("edit", "编辑会员中心页面设计");
			Renovation.getJSONObject("User").put("del", "删除会员中心页面设计");
			Renovation.getJSONObject("User").put("setDefault", "模板开启开关");

			Renovation.put("Order", new JSONObject(new LinkedHashMap<>()));
			Renovation.getJSONObject("Order").put("_isshow", true);
			Renovation.getJSONObject("Order").put("_auth", true);
			Renovation.getJSONObject("Order").put("_name", "我的订单页面");
			Renovation.getJSONObject("Order").put("index", "我的订单页面设计");
			Renovation.getJSONObject("Order").put("add", "新增我的订单页面设计");
			Renovation.getJSONObject("Order").put("edit", "编辑我的订单页面设计");
			Renovation.getJSONObject("Order").put("del", "删除我的订单页面设计");
			Renovation.getJSONObject("Order").put("setDefault", "模板开启开关");

			Renovation.put("Team", new JSONObject(new LinkedHashMap<>()));
			Renovation.getJSONObject("Team").put("_isshow", true);
			Renovation.getJSONObject("Team").put("_auth", true);
			Renovation.getJSONObject("Team").put("_name", "我的团队页面");
			Renovation.getJSONObject("Team").put("index", "我的团队页面设计");
			Renovation.getJSONObject("Team").put("add", "新增我的团队页面设计");
			Renovation.getJSONObject("Team").put("edit", "编辑我的团队页面设计");
			Renovation.getJSONObject("Team").put("del", "删除我的团队页面设计");
			Renovation.getJSONObject("Team").put("setDefault", "模板开启开关");

			Renovation.put("Income", new JSONObject(new LinkedHashMap<>()));
			Renovation.getJSONObject("Income").put("_isshow", true);
			Renovation.getJSONObject("Income").put("_auth", true);
			Renovation.getJSONObject("Income").put("_name", "我的收益页面");
			Renovation.getJSONObject("Income").put("index", "我的收益页面设计");
			Renovation.getJSONObject("Income").put("add", "新增我的收益页面设计");
			Renovation.getJSONObject("Income").put("edit", "编辑我的收益页面设计");
			Renovation.getJSONObject("Income").put("del", "删除我的收益页面设计");
			Renovation.getJSONObject("Income").put("setDefault", "模板开启开关");

			Renovation.put("MoneyLog", new JSONObject(new LinkedHashMap<>()));
			Renovation.getJSONObject("MoneyLog").put("_isshow", true);
			Renovation.getJSONObject("MoneyLog").put("_auth", true);
			Renovation.getJSONObject("MoneyLog").put("_name", "资金记录页面");
			Renovation.getJSONObject("MoneyLog").put("index", "资金记录页面设计");
			Renovation.getJSONObject("MoneyLog").put("add", "新增资金记录页面设计");
			Renovation.getJSONObject("MoneyLog").put("edit", "编辑资金记录页面设计");
			Renovation.getJSONObject("MoneyLog").put("del", "删除资金记录页面设计");
			Renovation.getJSONObject("MoneyLog").put("setDefault", "模板开启开关");

			Renovation.put("Custom", new JSONObject(new LinkedHashMap<>()));
			Renovation.getJSONObject("Custom").put("_isshow", true);
			Renovation.getJSONObject("Custom").put("_auth", true);
			Renovation.getJSONObject("Custom").put("_name", "自定义页面");
			Renovation.getJSONObject("Custom").put("index", "自定义页面设计");
			Renovation.getJSONObject("Custom").put("add", "新增自定义页面设计");
			Renovation.getJSONObject("Custom").put("edit", "编辑自定义页面设计");
			Renovation.getJSONObject("Custom").put("del", "删除自定义页面设计");

			Renovation.put("Bottom", new JSONObject(new LinkedHashMap<>()));
			Renovation.getJSONObject("Bottom").put("_isshow", true);
			Renovation.getJSONObject("Bottom").put("_auth", true);
			Renovation.getJSONObject("Bottom").put("_name", "底部菜单");
			Renovation.getJSONObject("Bottom").put("index", "底部菜单设计");
			Renovation.getJSONObject("Bottom").put("add", "新增底部菜单设计");
			Renovation.getJSONObject("Bottom").put("edit", "编辑底部菜单设计");
			Renovation.getJSONObject("Bottom").put("del", "删除底部菜单设计");
			menu.put("Renovation", Renovation);

			JSONObject SystemSet = new JSONObject(new LinkedHashMap<>());
			SystemSet.put("_name", "系统设置");
			SystemSet.put("_isshow", true);
			SystemSet.put("_auth", true);
			SystemSet.put("_icon", "icon-gears");

			SystemSet.put("SMS", new JSONObject(new LinkedHashMap<>()));
			SystemSet.getJSONObject("SMS").put("_isshow", true);
			SystemSet.getJSONObject("SMS").put("_auth", true);
			SystemSet.getJSONObject("SMS").put("_name", "短信设置");
			SystemSet.getJSONObject("SMS").put("index", "短信设置");

			SystemSet.put("Weixin", new JSONObject(new LinkedHashMap<>()));
			SystemSet.getJSONObject("Weixin").put("_isshow", true);
			SystemSet.getJSONObject("Weixin").put("_auth", true);
			SystemSet.getJSONObject("Weixin").put("_name", "微信参数设置");
			SystemSet.getJSONObject("Weixin").put("index", "微信参数设置");

			SystemSet.put("Pdd", new JSONObject(new LinkedHashMap<>()));
			SystemSet.getJSONObject("Pdd").put("_isshow", true);
			SystemSet.getJSONObject("Pdd").put("_auth", true);
			SystemSet.getJSONObject("Pdd").put("_name", "拼多多参数设置");
			SystemSet.getJSONObject("Pdd").put("index", "拼多多参数设置");

			SystemSet.put("Jd", new JSONObject(new LinkedHashMap<>()));
			SystemSet.getJSONObject("Jd").put("_isshow", true);
			SystemSet.getJSONObject("Jd").put("_auth", true);
			SystemSet.getJSONObject("Jd").put("_name", "京东参数设置");
			SystemSet.getJSONObject("Jd").put("index", "京东参数设置");

			SystemSet.put("Meituan", new JSONObject(new LinkedHashMap<>()));
			SystemSet.getJSONObject("Meituan").put("_isshow", true);
			SystemSet.getJSONObject("Meituan").put("_auth", true);
			SystemSet.getJSONObject("Meituan").put("_name", "美团参数设置");
			SystemSet.getJSONObject("Meituan").put("index", "美团参数设置");

			SystemSet.put("AdminGroup", new JSONObject(new LinkedHashMap<>()));
			SystemSet.getJSONObject("AdminGroup").put("_isshow", true);
			SystemSet.getJSONObject("AdminGroup").put("_auth", true);
			SystemSet.getJSONObject("AdminGroup").put("_name", "岗位设置");
			SystemSet.getJSONObject("AdminGroup").put("index", "岗位设置");

			SystemSet.put("Role", new JSONObject(new LinkedHashMap<>()));
			SystemSet.getJSONObject("Role").put("_isshow", false);
			SystemSet.getJSONObject("Role").put("_auth", true);
			SystemSet.getJSONObject("Role").put("_name", "权限设置");
			SystemSet.getJSONObject("Role").put("index", "权限设置");

			SystemSet.put("Admin", new JSONObject(new LinkedHashMap<>()));
			SystemSet.getJSONObject("Admin").put("_isshow", true);
			SystemSet.getJSONObject("Admin").put("_auth", true);
			SystemSet.getJSONObject("Admin").put("_name", "成员设置");
			SystemSet.getJSONObject("Admin").put("index", "成员设置");

			SystemSet.put("Withdrawals", new JSONObject(new LinkedHashMap<>()));
			SystemSet.getJSONObject("Withdrawals").put("_isshow", true);
			SystemSet.getJSONObject("Withdrawals").put("_auth", true);
			SystemSet.getJSONObject("Withdrawals").put("_name", "提现设置");
			SystemSet.getJSONObject("Withdrawals").put("index", "提现设置");

			SystemSet.put("Agreement", new JSONObject(new LinkedHashMap<>()));
			SystemSet.getJSONObject("Agreement").put("_isshow", true);
			SystemSet.getJSONObject("Agreement").put("_auth", true);
			SystemSet.getJSONObject("Agreement").put("_name", "注册协议设置");
			SystemSet.getJSONObject("Agreement").put("index", "注册协议设置");

			menu.put("SystemSet", SystemSet);

		}
		return menu;
	}
}
