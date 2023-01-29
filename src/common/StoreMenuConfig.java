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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class StoreMenuConfig {
	private static JSONObject operation = null;
	private static JSONObject marketing = null;
	private static JSONObject crm = null;
	private static JSONObject report = null;

	public static JSONObject getMenu() {
		JSONArray list = getAllMenu();
		JSONObject object = new JSONObject(new LinkedHashMap<>());
		for (Integer i = 0; i < list.size(); i = i + 1) {
			JSONObject subObject = list.getJSONObject(i);
			for (String key : subObject.keySet()) {
				object.put(key, subObject.getJSONObject(key));
			}
		}
		return object;
	}

	public static JSONArray getAllMenu() {
		JSONArray list = new JSONArray();
		getMenu("operation");
		list.add(operation);
		list.add(marketing);
		list.add(crm);
		list.add(report);
		return list;
	}

	public static JSONObject getMenu(String menu_category_name) {
		if (StoreMenuConfig.operation == null) {
			StoreMenuConfig.operation = new JSONObject(new LinkedHashMap<>());

			JSONObject Food = new JSONObject(new LinkedHashMap<>());
			Food.put("_name", "菜品管理");
			Food.put("_isshow", true);
			Food.put("_auth", true);
			Food.put("_icon", "icon-building");
			Food.put("Category", new JSONObject(new LinkedHashMap<>()));
			Food.getJSONObject("Category").put("_isshow", true);
			Food.getJSONObject("Category").put("_auth", true);
			Food.getJSONObject("Category").put("_name", "菜品分类");
			Food.getJSONObject("Category").put("index", "菜品分类");
			Food.getJSONObject("Category").put("add", "新建菜品分类");
			Food.getJSONObject("Category").put("edit", "编辑菜品分类");
			Food.getJSONObject("Category").put("del", "删除菜品分类");

			Food.put("Index", new JSONObject(new LinkedHashMap<>()));
			Food.getJSONObject("Index").put("_isshow", true);
			Food.getJSONObject("Index").put("_auth", true);
			Food.getJSONObject("Index").put("_name", "菜品管理");
			Food.getJSONObject("Index").put("index", "菜品列表");
			Food.getJSONObject("Index").put("add", "新建菜品");
			Food.getJSONObject("Index").put("edit", "编辑菜品");
			Food.getJSONObject("Index").put("del", "删除菜品");

			Food.put("Order", new JSONObject(new LinkedHashMap<>()));
			Food.getJSONObject("Order").put("_isshow", true);
			Food.getJSONObject("Order").put("_auth", true);
			Food.getJSONObject("Order").put("_name", "订单管理");
			Food.getJSONObject("Order").put("index", "订单列表");

			/*
			 * Food.put("Manage", new JSONObject(new LinkedHashMap<>()));
			 * Food.getJSONObject("Manage").put("_isshow", true);
			 * Food.getJSONObject("Manage").put("_auth", true);
			 * Food.getJSONObject("Manage").put("_name", "时段菜单");
			 * Food.getJSONObject("Manage").put("index", "时段菜单列表");
			 * Food.getJSONObject("Manage").put("add", "新建时段菜单");
			 * Food.getJSONObject("Manage").put("edit", "编辑时段菜单");
			 * Food.getJSONObject("Manage").put("del", "删除时段菜单");
			 * 
			 * Food.put("Banquet", new JSONObject(new LinkedHashMap<>()));
			 * Food.getJSONObject("Banquet").put("_isshow", true);
			 * Food.getJSONObject("Banquet").put("_auth", true);
			 * Food.getJSONObject("Banquet").put("_name", "宴会套餐");
			 * Food.getJSONObject("Banquet").put("index", "宴会套餐列表");
			 * Food.getJSONObject("Banquet").put("add", "新建宴会套餐");
			 * Food.getJSONObject("Banquet").put("edit", "编辑宴会套餐");
			 * Food.getJSONObject("Banquet").put("del", "删除宴会套餐");
			 */

			operation.put("Food", Food);

			JSONObject Seat = new JSONObject(new LinkedHashMap<>());
			Seat.put("_name", "餐台管理");
			Seat.put("_isshow", true);
			Seat.put("_auth", true);
			Seat.put("_icon", "icon-building");
			Seat.put("SeatArea", new JSONObject(new LinkedHashMap<>()));
			Seat.getJSONObject("SeatArea").put("_isshow", true);
			Seat.getJSONObject("SeatArea").put("_auth", true);
			Seat.getJSONObject("SeatArea").put("_name", "餐台区域管理");
			Seat.getJSONObject("SeatArea").put("index", "餐台区域");
			Seat.getJSONObject("SeatArea").put("add", "新建餐台区域");
			Seat.getJSONObject("SeatArea").put("edit", "编辑餐台取悦");
			Seat.getJSONObject("SeatArea").put("del", "删除餐台区域");

			Seat.put("Index", new JSONObject(new LinkedHashMap<>()));
			Seat.getJSONObject("Index").put("_isshow", false);
			Seat.getJSONObject("Index").put("_auth", true);
			Seat.getJSONObject("Index").put("_name", "餐台管理");
			Seat.getJSONObject("Index").put("index", "餐台列表");
			Seat.getJSONObject("Index").put("add", "新建餐台");
			Seat.getJSONObject("Index").put("edit", "编辑餐台");
			Seat.getJSONObject("Index").put("del", "删除餐台");

			Seat.put("Qr", new JSONObject(new LinkedHashMap<>()));
			Seat.getJSONObject("Qr").put("_isshow", true);
			Seat.getJSONObject("Qr").put("_auth", true);
			Seat.getJSONObject("Qr").put("_name", "桌贴设计");
			Seat.getJSONObject("Qr").put("index", "桌贴设计");

			Seat.put("DownLoad", new JSONObject(new LinkedHashMap<>()));
			Seat.getJSONObject("DownLoad").put("_isshow", true);
			Seat.getJSONObject("DownLoad").put("_auth", true);
			Seat.getJSONObject("DownLoad").put("_name", "桌贴下载");
			Seat.getJSONObject("DownLoad").put("index", "桌贴下载");
			Seat.getJSONObject("DownLoad").put("add", "新建下载");

			operation.put("Seat", Seat);

			JSONObject Spend = new JSONObject(new LinkedHashMap<>());
			Spend.put("_name", "餐厅支出");
			Spend.put("_isshow", true);
			Spend.put("_auth", true);
			Spend.put("_icon", "icon-building");
			Spend.put("Index", new JSONObject(new LinkedHashMap<>()));
			Spend.getJSONObject("Index").put("_isshow", true);
			Spend.getJSONObject("Index").put("_auth", true);
			Spend.getJSONObject("Index").put("_name", "支出管理");
			Spend.getJSONObject("Index").put("index", "支出管理");
			Spend.getJSONObject("Index").put("add", "新建支出");
			Spend.getJSONObject("Index").put("edit", "编辑支出");
			Spend.getJSONObject("Index").put("del", "删除支出");
			operation.put("Spend", Spend);

			JSONObject Balance = new JSONObject(new LinkedHashMap<>());
			Balance.put("_name", "结算管理");
			Balance.put("_isshow", true);
			Balance.put("_auth", true);
			Balance.put("_icon", "icon-building");
			Balance.put("Index", new JSONObject(new LinkedHashMap<>()));
			Balance.getJSONObject("Index").put("_isshow", true);
			Balance.getJSONObject("Index").put("_auth", true);
			Balance.getJSONObject("Index").put("_name", "结算管理");
			Balance.getJSONObject("Index").put("index", "结算管理");
			operation.put("Balance", Balance);

			JSONObject SystemSet = new JSONObject(new LinkedHashMap<>());
			SystemSet.put("_name", "系统设置");
			SystemSet.put("_isshow", true);
			SystemSet.put("_auth", true);
			SystemSet.put("_icon", "icon-gears");

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

			SystemSet.put("Restaurant", new JSONObject(new LinkedHashMap<>()));
			SystemSet.getJSONObject("Restaurant").put("_isshow", true);
			SystemSet.getJSONObject("Restaurant").put("_auth", true);
			SystemSet.getJSONObject("Restaurant").put("_name", "餐厅设置");
			SystemSet.getJSONObject("Restaurant").put("index", "餐厅设置");

			SystemSet.put("Printer", new JSONObject(new LinkedHashMap<>()));
			SystemSet.getJSONObject("Printer").put("_isshow", true);
			SystemSet.getJSONObject("Printer").put("_auth", true);
			SystemSet.getJSONObject("Printer").put("_name", "后厨打印机设置");
			SystemSet.getJSONObject("Printer").put("index", "后厨打印机设置");
			SystemSet.getJSONObject("Printer").put("add", "添加后厨打印机");
			SystemSet.getJSONObject("Printer").put("edit", "编辑后厨打印机");
			SystemSet.getJSONObject("Printer").put("del", "删除后厨打印机");

			operation.put("SystemSet", SystemSet);

			/*
			 * JSONObject OrderDish = new JSONObject(new LinkedHashMap<>());
			 * OrderDish.put("_name", "手机点餐"); OrderDish.put("_isshow", true);
			 * OrderDish.put("_auth", true); OrderDish.put("_icon", "icon-building");
			 * OrderDish.put("Index", new JSONObject(new LinkedHashMap<>()));
			 * OrderDish.getJSONObject("Index").put("_isshow", true);
			 * OrderDish.getJSONObject("Index").put("_auth", true);
			 * OrderDish.getJSONObject("Index").put("_name", "点餐首页模版");
			 * OrderDish.getJSONObject("Index").put("index", "点餐首页模版");
			 * OrderDish.getJSONObject("Index").put("add", "新建点餐首页");
			 * OrderDish.getJSONObject("Index").put("edit", "编辑点餐首页");
			 * OrderDish.getJSONObject("Index").put("del", "删除点餐首页");
			 * OrderDish.getJSONObject("Index").put("setDefault", "设置为默认模版");
			 * 
			 * OrderDish.put("FoodDetail", new JSONObject(new LinkedHashMap<>()));
			 * OrderDish.getJSONObject("FoodDetail").put("_isshow", true);
			 * OrderDish.getJSONObject("FoodDetail").put("_auth", true);
			 * OrderDish.getJSONObject("FoodDetail").put("_name", "菜品详情页模版");
			 * OrderDish.getJSONObject("FoodDetail").put("index", "菜品详情页模版");
			 * OrderDish.getJSONObject("FoodDetail").put("add", "新建菜品详情页");
			 * OrderDish.getJSONObject("FoodDetail").put("edit", "编辑菜品详情页");
			 * OrderDish.getJSONObject("FoodDetail").put("del", "删除菜品详情页");
			 * OrderDish.getJSONObject("FoodDetail").put("setDefault", "设置为默认模版");
			 * 
			 * OrderDish.put("Cart", new JSONObject(new LinkedHashMap<>()));
			 * OrderDish.getJSONObject("Cart").put("_isshow", true);
			 * OrderDish.getJSONObject("Cart").put("_auth", true);
			 * OrderDish.getJSONObject("Cart").put("_name", "购物车页面模版");
			 * OrderDish.getJSONObject("Cart").put("index", "购物车页面模版");
			 * OrderDish.getJSONObject("Cart").put("add", "新建购物车页面");
			 * OrderDish.getJSONObject("Cart").put("edit", "编辑购物车页面");
			 * OrderDish.getJSONObject("Cart").put("del", "删除购物车页面");
			 * OrderDish.getJSONObject("Cart").put("setDefault", "设置为默认模版");
			 * 
			 * operation.put("OrderDish", OrderDish);
			 * 
			 * JSONObject Takeaway = new JSONObject(new LinkedHashMap<>());
			 * Takeaway.put("_name", "外卖管理"); Takeaway.put("_isshow", true);
			 * Takeaway.put("_auth", true); Takeaway.put("_icon", "icon-building");
			 * Takeaway.put("Meituan", new JSONObject(new LinkedHashMap<>()));
			 * Takeaway.getJSONObject("Meituan").put("_isshow", true);
			 * Takeaway.getJSONObject("Meituan").put("_auth", true);
			 * Takeaway.getJSONObject("Meituan").put("_name", "美团外卖管理");
			 * Takeaway.getJSONObject("Meituan").put("index", "美团外卖管理");
			 * 
			 * Takeaway.put("MeituanCategory", new JSONObject(new LinkedHashMap<>()));
			 * Takeaway.getJSONObject("MeituanCategory").put("_isshow", true);
			 * Takeaway.getJSONObject("MeituanCategory").put("_auth", true);
			 * Takeaway.getJSONObject("MeituanCategory").put("_name", "美团外卖菜品分类");
			 * Takeaway.getJSONObject("MeituanCategory").put("index", "美团外卖菜品分类");
			 * Takeaway.getJSONObject("MeituanCategory").put("add", "新增菜品分类");
			 * Takeaway.getJSONObject("MeituanCategory").put("edit", "编辑菜品分类");
			 * Takeaway.getJSONObject("MeituanCategory").put("del", "删除菜品分类");
			 * 
			 * Takeaway.put("MeituanFood", new JSONObject(new LinkedHashMap<>()));
			 * Takeaway.getJSONObject("MeituanFood").put("_isshow", true);
			 * Takeaway.getJSONObject("MeituanFood").put("_auth", true);
			 * Takeaway.getJSONObject("MeituanFood").put("_name", "美团外卖菜品管理");
			 * Takeaway.getJSONObject("MeituanFood").put("index", "美团外卖菜品管理");
			 * Takeaway.getJSONObject("MeituanFood").put("add", "新建菜品");
			 * Takeaway.getJSONObject("MeituanFood").put("edit", "编辑菜品");
			 * Takeaway.getJSONObject("MeituanFood").put("del", "删除菜品");
			 * 
			 * Takeaway.put("Eleme", new JSONObject(new LinkedHashMap<>()));
			 * Takeaway.getJSONObject("Eleme").put("_isshow", true);
			 * Takeaway.getJSONObject("Eleme").put("_auth", true);
			 * Takeaway.getJSONObject("Eleme").put("_name", "饿了么外卖管理");
			 * Takeaway.getJSONObject("Eleme").put("index", "饿了么外卖管理");
			 * 
			 * Takeaway.put("ElemeCategory", new JSONObject(new LinkedHashMap<>()));
			 * Takeaway.getJSONObject("ElemeCategory").put("_isshow", true);
			 * Takeaway.getJSONObject("ElemeCategory").put("_auth", true);
			 * Takeaway.getJSONObject("ElemeCategory").put("_name", "饿了么外卖菜品分类");
			 * Takeaway.getJSONObject("ElemeCategory").put("index", "饿了么外卖菜品分类");
			 * Takeaway.getJSONObject("ElemeCategory").put("add", "新增菜品分类");
			 * Takeaway.getJSONObject("ElemeCategory").put("edit", "编辑菜品分类");
			 * Takeaway.getJSONObject("ElemeCategory").put("del", "删除菜品分类");
			 * 
			 * Takeaway.put("ElemeFood", new JSONObject(new LinkedHashMap<>()));
			 * Takeaway.getJSONObject("ElemeFood").put("_isshow", true);
			 * Takeaway.getJSONObject("ElemeFood").put("_auth", true);
			 * Takeaway.getJSONObject("ElemeFood").put("_name", "饿了么外卖菜品管理");
			 * Takeaway.getJSONObject("ElemeFood").put("index", "饿了么外卖菜品管理");
			 * Takeaway.getJSONObject("ElemeFood").put("add", "新建菜品");
			 * Takeaway.getJSONObject("ElemeFood").put("edit", "编辑菜品");
			 * Takeaway.getJSONObject("ElemeFood").put("del", "删除菜品");
			 * 
			 * operation.put("Takeaway", Takeaway);
			 * 
			 * JSONObject Finance = new JSONObject(); Finance.put("_name", "财务管理");
			 * Finance.put("_isshow", true); Finance.put("_auth", true);
			 * Finance.put("_icon", "icon-building"); Finance.put("Index", new
			 * JSONObject(new LinkedHashMap<>()));
			 * Finance.getJSONObject("Index").put("_isshow", true);
			 * Finance.getJSONObject("Index").put("_auth", true);
			 * Finance.getJSONObject("Index").put("_name", "收入记录");
			 * Finance.getJSONObject("Index").put("index", "收入记录");
			 * 
			 * operation.put("Finance", Finance);
			 * 
			 * JSONObject SystemSet = new JSONObject(new LinkedHashMap<>());
			 * SystemSet.put("_name", "系统设置"); SystemSet.put("_isshow", true);
			 * SystemSet.put("_auth", true); SystemSet.put("_icon", "icon-gears");
			 * 
			 * SystemSet.put("AdminGroup", new JSONObject(new LinkedHashMap<>()));
			 * SystemSet.getJSONObject("AdminGroup").put("_isshow", true);
			 * SystemSet.getJSONObject("AdminGroup").put("_auth", true);
			 * SystemSet.getJSONObject("AdminGroup").put("_name", "岗位设置");
			 * SystemSet.getJSONObject("AdminGroup").put("index", "岗位设置");
			 * 
			 * SystemSet.put("Role", new JSONObject(new LinkedHashMap<>()));
			 * SystemSet.getJSONObject("Role").put("_isshow", false);
			 * SystemSet.getJSONObject("Role").put("_auth", true);
			 * SystemSet.getJSONObject("Role").put("_name", "权限设置");
			 * SystemSet.getJSONObject("Role").put("index", "权限设置");
			 * 
			 * SystemSet.put("Admin", new JSONObject(new LinkedHashMap<>()));
			 * SystemSet.getJSONObject("Admin").put("_isshow", true);
			 * SystemSet.getJSONObject("Admin").put("_auth", true);
			 * SystemSet.getJSONObject("Admin").put("_name", "成员设置");
			 * SystemSet.getJSONObject("Admin").put("index", "成员设置");
			 * 
			 * operation.put("SystemSet", SystemSet);
			 */

		}

		if (StoreMenuConfig.marketing == null) {
			StoreMenuConfig.marketing = new JSONObject(new LinkedHashMap<>());

			JSONObject Marketing = new JSONObject(new LinkedHashMap<>());
			Marketing.put("_name", "会员营销");
			Marketing.put("_isshow", true);
			Marketing.put("_auth", true);
			Marketing.put("_icon", "icon-building");

			Marketing.put("Coupon", new JSONObject(new LinkedHashMap<>()));
			Marketing.getJSONObject("Coupon").put("_isshow", true);
			Marketing.getJSONObject("Coupon").put("_auth", true);
			Marketing.getJSONObject("Coupon").put("_name", "优惠券管理");
			Marketing.getJSONObject("Coupon").put("index", "优惠券列表");
			Marketing.getJSONObject("Coupon").put("add", "新增优惠券");
			Marketing.getJSONObject("Coupon").put("edit", "编辑优惠券");
			Marketing.getJSONObject("Coupon").put("del", "删除优惠券");
			marketing.put("Marketing", Marketing);

			/*
			 * JSONObject Discount = new JSONObject(new LinkedHashMap<>());
			 * Discount.put("_name", "促销活动"); Discount.put("_isshow", true);
			 * Discount.put("_auth", true); Discount.put("_icon", "icon-building");
			 * 
			 * Discount.put("Index", new JSONObject(new LinkedHashMap<>()));
			 * Discount.getJSONObject("Index").put("_isshow", true);
			 * Discount.getJSONObject("Index").put("_auth", true);
			 * Discount.getJSONObject("Index").put("_name", "促销活动");
			 * Discount.getJSONObject("Index").put("index", "促销活动列表");
			 * Discount.getJSONObject("Index").put("add", "新增促销活动");
			 * Discount.getJSONObject("Index").put("edit", "编辑促销活动");
			 * Discount.getJSONObject("Index").put("del", "删除促销活动");
			 * 
			 * marketing.put("Discount", Discount);
			 * 
			 * JSONObject Marketing = new JSONObject(new LinkedHashMap<>());
			 * Marketing.put("_name", "会员营销"); Marketing.put("_isshow", true);
			 * Marketing.put("_auth", true); Marketing.put("_icon", "icon-building");
			 * 
			 * Marketing.put("Coupon", new JSONObject(new LinkedHashMap<>()));
			 * Marketing.getJSONObject("Coupon").put("_isshow", true);
			 * Marketing.getJSONObject("Coupon").put("_auth", true);
			 * Marketing.getJSONObject("Coupon").put("_name", "优惠券管理");
			 * Marketing.getJSONObject("Coupon").put("index", "优惠券列表");
			 * Marketing.getJSONObject("Coupon").put("add", "新增优惠券");
			 * Marketing.getJSONObject("Coupon").put("edit", "编辑优惠券");
			 * Marketing.getJSONObject("Coupon").put("del", "删除优惠券");
			 * marketing.put("Marketing", Marketing);
			 * 
			 * JSONObject CreditsExchange = new JSONObject(new LinkedHashMap<>());
			 * CreditsExchange.put("_name", "积分兑换"); CreditsExchange.put("_isshow", true);
			 * CreditsExchange.put("_auth", true); CreditsExchange.put("_icon",
			 * "icon-building");
			 * 
			 * CreditsExchange.put("Index", new JSONObject(new LinkedHashMap<>()));
			 * CreditsExchange.getJSONObject("Index").put("_isshow", true);
			 * CreditsExchange.getJSONObject("Index").put("_auth", true);
			 * CreditsExchange.getJSONObject("Index").put("_name", "积分兑换方案");
			 * CreditsExchange.getJSONObject("Index").put("index", "积分兑换列表");
			 * CreditsExchange.getJSONObject("Index").put("add", "新建方案");
			 * CreditsExchange.getJSONObject("Index").put("edit", "编辑方案");
			 * CreditsExchange.getJSONObject("Index").put("del", "删除方案");
			 * 
			 * CreditsExchange.put("Record", new JSONObject(new LinkedHashMap<>()));
			 * CreditsExchange.getJSONObject("Record").put("_isshow", true);
			 * CreditsExchange.getJSONObject("Record").put("_auth", true);
			 * CreditsExchange.getJSONObject("Record").put("_name", "积分兑换记录");
			 * CreditsExchange.getJSONObject("Record").put("index", "积分兑换记录");
			 * 
			 * marketing.put("CreditsExchange", CreditsExchange);
			 */
		}

		if (StoreMenuConfig.crm == null) {
			StoreMenuConfig.crm = new JSONObject(new LinkedHashMap<>());

			/*
			 * JSONObject BasicCrm = new JSONObject(new LinkedHashMap<>());
			 * BasicCrm.put("_name", "基础业务设置"); BasicCrm.put("_isshow", true);
			 * BasicCrm.put("_auth", true); BasicCrm.put("_icon", "icon-building");
			 * 
			 * BasicCrm.put("MemberLevel", new JSONObject(new LinkedHashMap<>()));
			 * BasicCrm.getJSONObject("MemberLevel").put("_isshow", true);
			 * BasicCrm.getJSONObject("MemberLevel").put("_auth", true);
			 * BasicCrm.getJSONObject("MemberLevel").put("_name", "会员等级设置");
			 * BasicCrm.getJSONObject("MemberLevel").put("index", "会员等级列表");
			 * BasicCrm.getJSONObject("MemberLevel").put("add", "新增会员等级");
			 * BasicCrm.getJSONObject("MemberLevel").put("edit", "编辑会员等级");
			 * BasicCrm.getJSONObject("MemberLevel").put("del", "删除会员等级");
			 * 
			 * BasicCrm.put("IntelligenceDeposit", new JSONObject(new LinkedHashMap<>()));
			 * BasicCrm.getJSONObject("IntelligenceDeposit").put("_isshow", true);
			 * BasicCrm.getJSONObject("IntelligenceDeposit").put("_auth", true);
			 * BasicCrm.getJSONObject("IntelligenceDeposit").put("_name", "储值套餐管理");
			 * BasicCrm.getJSONObject("IntelligenceDeposit").put("index", "储值套餐列表");
			 * BasicCrm.getJSONObject("IntelligenceDeposit").put("add", "新增储值套餐");
			 * BasicCrm.getJSONObject("IntelligenceDeposit").put("edit", "编辑储值套餐");
			 * BasicCrm.getJSONObject("IntelligenceDeposit").put("del", "删除储值套餐");
			 * 
			 * BasicCrm.put("RightsPackage", new JSONObject(new LinkedHashMap<>()));
			 * BasicCrm.getJSONObject("RightsPackage").put("_isshow", true);
			 * BasicCrm.getJSONObject("RightsPackage").put("_auth", true);
			 * BasicCrm.getJSONObject("RightsPackage").put("_name", "权益包管理");
			 * BasicCrm.getJSONObject("RightsPackage").put("index", "权益包列表");
			 * BasicCrm.getJSONObject("RightsPackage").put("add", "新增权益包");
			 * BasicCrm.getJSONObject("RightsPackage").put("edit", "编辑权益包");
			 * BasicCrm.getJSONObject("RightsPackage").put("del", "删除权益包");
			 * 
			 * crm.put("BasicCrm", BasicCrm);
			 * 
			 * JSONObject Member = new JSONObject(new LinkedHashMap<>());
			 * Member.put("_name", "会员管理"); Member.put("_isshow", true); Member.put("_auth",
			 * true); Member.put("_icon", "icon-building");
			 * 
			 * Member.put("Index", new JSONObject(new LinkedHashMap<>()));
			 * Member.getJSONObject("Index").put("_isshow", true);
			 * Member.getJSONObject("Index").put("_auth", true);
			 * Member.getJSONObject("Index").put("_name", "会员列表");
			 * Member.getJSONObject("Index").put("index", "会员列表");
			 * Member.getJSONObject("Index").put("add", "新增会员");
			 * Member.getJSONObject("Index").put("edit", "编辑会员");
			 * Member.getJSONObject("Index").put("del", "删除会员");
			 * 
			 * Member.put("Label", new JSONObject(new LinkedHashMap<>()));
			 * Member.getJSONObject("Label").put("_isshow", true);
			 * Member.getJSONObject("Label").put("_auth", true);
			 * Member.getJSONObject("Label").put("_name", "会员标签");
			 * Member.getJSONObject("Label").put("index", "标签列表");
			 * Member.getJSONObject("Label").put("add", "新增标签");
			 * Member.getJSONObject("Label").put("edit", "编辑标签");
			 * Member.getJSONObject("Label").put("del", "删除标签");
			 * 
			 * Member.put("SMS", new JSONObject(new LinkedHashMap<>()));
			 * Member.getJSONObject("SMS").put("_isshow", true);
			 * Member.getJSONObject("SMS").put("_auth", true);
			 * Member.getJSONObject("SMS").put("_name", "群发短信");
			 * Member.getJSONObject("SMS").put("index", "群发短信");
			 * Member.getJSONObject("SMS").put("add", "新增群发短信");
			 * 
			 * crm.put("Member", Member);
			 * 
			 * JSONObject MemberAnalyze = new JSONObject(new LinkedHashMap<>());
			 * MemberAnalyze.put("_name", "会员分析"); MemberAnalyze.put("_isshow", true);
			 * MemberAnalyze.put("_auth", true); MemberAnalyze.put("_icon",
			 * "icon-building");
			 * 
			 * MemberAnalyze.put("Index", new JSONObject(new LinkedHashMap<>()));
			 * MemberAnalyze.getJSONObject("Index").put("_isshow", true);
			 * MemberAnalyze.getJSONObject("Index").put("_auth", true);
			 * MemberAnalyze.getJSONObject("Index").put("_name", "会员概览");
			 * MemberAnalyze.getJSONObject("Index").put("index", "会员概览");
			 * 
			 * MemberAnalyze.put("Increase", new JSONObject(new LinkedHashMap<>()));
			 * MemberAnalyze.getJSONObject("Increase").put("_isshow", true);
			 * MemberAnalyze.getJSONObject("Increase").put("_auth", true);
			 * MemberAnalyze.getJSONObject("Increase").put("_name", "会员新增统计");
			 * MemberAnalyze.getJSONObject("Increase").put("index", "会员新增统计");
			 * 
			 * MemberAnalyze.put("Ranking", new JSONObject(new LinkedHashMap<>()));
			 * MemberAnalyze.getJSONObject("Ranking").put("_isshow", true);
			 * MemberAnalyze.getJSONObject("Ranking").put("_auth", true);
			 * MemberAnalyze.getJSONObject("Ranking").put("_name", "最有价值会员");
			 * MemberAnalyze.getJSONObject("Ranking").put("index", "最有价值会员");
			 * 
			 * MemberAnalyze.put("Property", new JSONObject(new LinkedHashMap<>()));
			 * MemberAnalyze.getJSONObject("Property").put("_isshow", true);
			 * MemberAnalyze.getJSONObject("Property").put("_auth", true);
			 * MemberAnalyze.getJSONObject("Property").put("_name", "会员画像");
			 * MemberAnalyze.getJSONObject("Property").put("index", "会员画像");
			 * 
			 * MemberAnalyze.put("ConsumeAnalysis", new JSONObject(new LinkedHashMap<>()));
			 * MemberAnalyze.getJSONObject("ConsumeAnalysis").put("_isshow", true);
			 * MemberAnalyze.getJSONObject("ConsumeAnalysis").put("_auth", true);
			 * MemberAnalyze.getJSONObject("ConsumeAnalysis").put("_name", "消费分析");
			 * MemberAnalyze.getJSONObject("ConsumeAnalysis").put("index", "消费分析");
			 * 
			 * MemberAnalyze.put("MemberDistribution", new JSONObject(new
			 * LinkedHashMap<>()));
			 * MemberAnalyze.getJSONObject("MemberDistribution").put("_isshow", true);
			 * MemberAnalyze.getJSONObject("MemberDistribution").put("_auth", true);
			 * MemberAnalyze.getJSONObject("MemberDistribution").put("_name", "会员分布");
			 * MemberAnalyze.getJSONObject("MemberDistribution").put("index", "会员分布");
			 * 
			 * crm.put("MemberAnalyze", MemberAnalyze);
			 * 
			 * JSONObject StoredBalance = new JSONObject(new LinkedHashMap<>());
			 * StoredBalance.put("_name", "储值消费分析"); StoredBalance.put("_isshow", true);
			 * StoredBalance.put("_auth", true); StoredBalance.put("_icon",
			 * "icon-building");
			 * 
			 * StoredBalance.put("Index", new JSONObject(new LinkedHashMap<>()));
			 * StoredBalance.getJSONObject("Index").put("_isshow", true);
			 * StoredBalance.getJSONObject("Index").put("_auth", true);
			 * StoredBalance.getJSONObject("Index").put("_name", "储值分析");
			 * StoredBalance.getJSONObject("Index").put("index", "储值分析");
			 * 
			 * StoredBalance.put("SummaryStore", new JSONObject(new LinkedHashMap<>()));
			 * StoredBalance.getJSONObject("SummaryStore").put("_isshow", true);
			 * StoredBalance.getJSONObject("SummaryStore").put("_auth", true);
			 * StoredBalance.getJSONObject("SummaryStore").put("_name", "储值消费汇总表");
			 * StoredBalance.getJSONObject("SummaryStore").put("index", "储值消费汇总表");
			 * 
			 * StoredBalance.put("SummaryPayment", new JSONObject(new LinkedHashMap<>()));
			 * StoredBalance.getJSONObject("SummaryPayment").put("_isshow", true);
			 * StoredBalance.getJSONObject("SummaryPayment").put("_auth", true);
			 * StoredBalance.getJSONObject("SummaryPayment").put("_name", "储值支付对账表");
			 * StoredBalance.getJSONObject("SummaryPayment").put("index", "储值支付对账表");
			 * 
			 * StoredBalance.put("IntegralBalance", new JSONObject(new LinkedHashMap<>()));
			 * StoredBalance.getJSONObject("IntegralBalance").put("_isshow", true);
			 * StoredBalance.getJSONObject("IntegralBalance").put("_auth", true);
			 * StoredBalance.getJSONObject("IntegralBalance").put("_name", "积分分析");
			 * StoredBalance.getJSONObject("IntegralBalance").put("index", "积分分析");
			 * 
			 * StoredBalance.put("SummaryIntegral", new JSONObject(new LinkedHashMap<>()));
			 * StoredBalance.getJSONObject("SummaryIntegral").put("_isshow", true);
			 * StoredBalance.getJSONObject("SummaryIntegral").put("_auth", true);
			 * StoredBalance.getJSONObject("SummaryIntegral").put("_name", "积分汇总表");
			 * StoredBalance.getJSONObject("SummaryIntegral").put("index", "积分汇总表");
			 * 
			 * StoredBalance.put("RightsPackage", new JSONObject(new LinkedHashMap<>()));
			 * StoredBalance.getJSONObject("RightsPackage").put("_isshow", true);
			 * StoredBalance.getJSONObject("RightsPackage").put("_auth", true);
			 * StoredBalance.getJSONObject("RightsPackage").put("_name", "权益包售卖汇总表");
			 * StoredBalance.getJSONObject("RightsPackage").put("index", "权益包售卖汇总表");
			 * 
			 * crm.put("StoredBalance", StoredBalance);
			 */
		}

		if (StoreMenuConfig.report == null) {
			StoreMenuConfig.report = new JSONObject(new LinkedHashMap<>());

			JSONObject BusinessReport = new JSONObject(new LinkedHashMap<>());
			BusinessReport.put("_name", "经营分析");
			BusinessReport.put("_isshow", true);
			BusinessReport.put("_auth", true);
			BusinessReport.put("_icon", "icon-building");

			BusinessReport.put("Index", new JSONObject(new LinkedHashMap<>()));
			BusinessReport.getJSONObject("Index").put("_isshow", true);
			BusinessReport.getJSONObject("Index").put("_auth", true);
			BusinessReport.getJSONObject("Index").put("_name", "营业概览");
			BusinessReport.getJSONObject("Index").put("index", "营业概览");

			BusinessReport.put("FoodAnalyze", new JSONObject(new LinkedHashMap<>()));
			BusinessReport.getJSONObject("FoodAnalyze").put("_isshow", true);
			BusinessReport.getJSONObject("FoodAnalyze").put("_auth", true);
			BusinessReport.getJSONObject("FoodAnalyze").put("_name", "菜品分析");
			BusinessReport.getJSONObject("FoodAnalyze").put("index", "菜品分析");

			BusinessReport.put("SaleAnalyze", new JSONObject(new LinkedHashMap<>()));
			BusinessReport.getJSONObject("SaleAnalyze").put("_isshow", true);
			BusinessReport.getJSONObject("SaleAnalyze").put("_auth", true);
			BusinessReport.getJSONObject("SaleAnalyze").put("_name", "菜品销售分析");
			BusinessReport.getJSONObject("SaleAnalyze").put("index", "菜品销售分析");

			BusinessReport.put("Receiving", new JSONObject(new LinkedHashMap<>()));
			BusinessReport.getJSONObject("Receiving").put("_isshow", true);
			BusinessReport.getJSONObject("Receiving").put("_auth", true);
			BusinessReport.getJSONObject("Receiving").put("_name", "收款分析");
			BusinessReport.getJSONObject("Receiving").put("index", "收款分析");

			BusinessReport.put("Spending", new JSONObject(new LinkedHashMap<>()));
			BusinessReport.getJSONObject("Spending").put("_isshow", true);
			BusinessReport.getJSONObject("Spending").put("_auth", true);
			BusinessReport.getJSONObject("Spending").put("_name", "支出分析");
			BusinessReport.getJSONObject("Spending").put("index", "支出分析");

			BusinessReport.put("Stock", new JSONObject(new LinkedHashMap<>()));
			BusinessReport.getJSONObject("Stock").put("_isshow", true);
			BusinessReport.getJSONObject("Stock").put("_auth", true);
			BusinessReport.getJSONObject("Stock").put("_name", "剩余库存");
			BusinessReport.getJSONObject("Stock").put("index", "剩余库存");

			report.put("BusinessReport", BusinessReport);

			JSONObject OrderReport = new JSONObject(new LinkedHashMap<>());
			OrderReport.put("_name", "订单报表");
			OrderReport.put("_isshow", true);
			OrderReport.put("_auth", true);
			OrderReport.put("_icon", "icon-building");

			OrderReport.put("Index", new JSONObject(new LinkedHashMap<>()));
			OrderReport.getJSONObject("Index").put("_isshow", true);
			OrderReport.getJSONObject("Index").put("_auth", true);
			OrderReport.getJSONObject("Index").put("_name", "订单明细");
			OrderReport.getJSONObject("Index").put("index", "订单明细");
			report.put("OrderReport", OrderReport);
			/*
			 * BusinessReport.put("Target", new JSONObject(new LinkedHashMap<>()));
			 * BusinessReport.getJSONObject("Target").put("_isshow", true);
			 * BusinessReport.getJSONObject("Target").put("_auth", true);
			 * BusinessReport.getJSONObject("Target").put("_name", "营运目标");
			 * BusinessReport.getJSONObject("Target").put("index", "营运目标");
			 * 
			 * BusinessReport.put("SaleAnalyze", new JSONObject(new LinkedHashMap<>()));
			 * BusinessReport.getJSONObject("SaleAnalyze").put("_isshow", true);
			 * BusinessReport.getJSONObject("SaleAnalyze").put("_auth", true);
			 * BusinessReport.getJSONObject("SaleAnalyze").put("_name", "菜品销售分析");
			 * BusinessReport.getJSONObject("SaleAnalyze").put("index", "菜品销售分析");
			 * 
			 * BusinessReport.put("FoodAnalyze", new JSONObject(new LinkedHashMap<>()));
			 * BusinessReport.getJSONObject("FoodAnalyze").put("_isshow", true);
			 * BusinessReport.getJSONObject("FoodAnalyze").put("_auth", true);
			 * BusinessReport.getJSONObject("FoodAnalyze").put("_name", "菜品分析");
			 * BusinessReport.getJSONObject("FoodAnalyze").put("index", "菜品分析");
			 * 
			 * BusinessReport.put("Receiving", new JSONObject(new LinkedHashMap<>()));
			 * BusinessReport.getJSONObject("Receiving").put("_isshow", true);
			 * BusinessReport.getJSONObject("Receiving").put("_auth", true);
			 * BusinessReport.getJSONObject("Receiving").put("_name", "收款分析");
			 * BusinessReport.getJSONObject("Receiving").put("index", "收款分析");
			 * 
			 * report.put("BusinessReport", BusinessReport);
			 * 
			 * JSONObject OrderReport = new JSONObject(new LinkedHashMap<>());
			 * OrderReport.put("_name", "订单报表"); OrderReport.put("_isshow", true);
			 * OrderReport.put("_auth", true); OrderReport.put("_icon", "icon-building");
			 * 
			 * OrderReport.put("Index", new JSONObject(new LinkedHashMap<>()));
			 * OrderReport.getJSONObject("Index").put("_isshow", true);
			 * OrderReport.getJSONObject("Index").put("_auth", true);
			 * OrderReport.getJSONObject("Index").put("_name", "订单明细");
			 * OrderReport.getJSONObject("Index").put("index", "订单明细");
			 * 
			 * OrderReport.put("Takeaway", new JSONObject(new LinkedHashMap<>()));
			 * OrderReport.getJSONObject("Takeaway").put("_isshow", true);
			 * OrderReport.getJSONObject("Takeaway").put("_auth", true);
			 * OrderReport.getJSONObject("Takeaway").put("_name", "外卖订单明细");
			 * OrderReport.getJSONObject("Takeaway").put("index", "外卖订单明细");
			 * 
			 * OrderReport.put("PickUp", new JSONObject(new LinkedHashMap<>()));
			 * OrderReport.getJSONObject("PickUp").put("_isshow", true);
			 * OrderReport.getJSONObject("PickUp").put("_auth", true);
			 * OrderReport.getJSONObject("PickUp").put("_name", "自提订单明细");
			 * OrderReport.getJSONObject("PickUp").put("index", "自提订单明细");
			 * 
			 * OrderReport.put("Reserve", new JSONObject(new LinkedHashMap<>()));
			 * OrderReport.getJSONObject("Reserve").put("_isshow", true);
			 * OrderReport.getJSONObject("Reserve").put("_auth", true);
			 * OrderReport.getJSONObject("Reserve").put("_name", "预订单明细");
			 * OrderReport.getJSONObject("Reserve").put("index", "预订单明细");
			 * 
			 * report.put("OrderReport", OrderReport);
			 */

		}

		if (menu_category_name.equals("operation")) {
			return operation;
		} else if (menu_category_name.equals("marketing")) {
			return marketing;
		} else if (menu_category_name.equals("crm")) {
			return crm;
		} else if (menu_category_name.equals("report")) {
			return report;
		} else if (menu_category_name.equals("all")) {
			JSONObject object = new JSONObject(new LinkedHashMap<>());
			object.put("operation", operation);
			object.put("marketing", marketing);
			object.put("crm", crm);
			object.put("report", report);
			return object;
		} else {
			return new JSONObject();
		}
	}
}
