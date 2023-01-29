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
import com.alibaba.fastjson.parser.Feature;

/*所有的"模块/控制器/方法"名称不能相同
 * */
public class AdminMenuConfig {
	private static JSONObject system = null;
	private static JSONObject home = null;
	private static JSONObject union = null;

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
		getMenu("home");
		list.add(home);
		list.add(union);
		list.add(system);
		return list;
	}

	public static JSONObject getMenu(String menu_category_name) {
		if (AdminMenuConfig.home == null) {
			AdminMenuConfig.home = new JSONObject(new LinkedHashMap<>());

			JSONObject WebSite = new JSONObject(new LinkedHashMap<>());
			WebSite.put("_name", "站群设置");
			WebSite.put("_isshow", true);
			WebSite.put("_auth", true);
			WebSite.put("_icon", "icon-building");

			WebSite.put("Index", new JSONObject(new LinkedHashMap<>()));
			WebSite.getJSONObject("Index").put("_isshow", true);
			WebSite.getJSONObject("Index").put("_auth", true);
			WebSite.getJSONObject("Index").put("_name", "站点管理");
			WebSite.getJSONObject("Index").put("index", "站点管理");
			WebSite.getJSONObject("Index").put("add", "新增站点");
			WebSite.getJSONObject("Index").put("edit", "编辑站点");
			WebSite.getJSONObject("Index").put("del", "删除站点");
			WebSite.getJSONObject("Index").put("switch_websites", "切换站点");

			home.put("WebSite", WebSite);

			JSONObject PcRenovation = new JSONObject(new LinkedHashMap<>());
			PcRenovation.put("_name", "响应式模版");
			PcRenovation.put("_isshow", true);
			PcRenovation.put("_auth", true);
			PcRenovation.put("_icon", "icon-building");

			PcRenovation.put("Header", new JSONObject(new LinkedHashMap<>()));
			PcRenovation.getJSONObject("Header").put("_isshow", true);
			PcRenovation.getJSONObject("Header").put("_auth", true);
			PcRenovation.getJSONObject("Header").put("_name", "顶部模块");
			PcRenovation.getJSONObject("Header").put("index", "顶部模块设计");
			PcRenovation.getJSONObject("Header").put("add", "新增顶部模块设计");
			PcRenovation.getJSONObject("Header").put("edit", "编辑顶部模块设计");
			PcRenovation.getJSONObject("Header").put("del", "删除顶部模块设计");

			PcRenovation.put("Bottom", new JSONObject(new LinkedHashMap<>()));
			PcRenovation.getJSONObject("Bottom").put("_isshow", true);
			PcRenovation.getJSONObject("Bottom").put("_auth", true);
			PcRenovation.getJSONObject("Bottom").put("_name", "底部模块");
			PcRenovation.getJSONObject("Bottom").put("index", "底部模块设计");
			PcRenovation.getJSONObject("Bottom").put("add", "新增底部模块设计");
			PcRenovation.getJSONObject("Bottom").put("edit", "编辑底部模块设计");
			PcRenovation.getJSONObject("Bottom").put("del", "删除底部模块设计");

			PcRenovation.put("Index", new JSONObject(new LinkedHashMap<>()));
			PcRenovation.getJSONObject("Index").put("_isshow", true);
			PcRenovation.getJSONObject("Index").put("_auth", true);
			PcRenovation.getJSONObject("Index").put("_name", "官网首页");
			PcRenovation.getJSONObject("Index").put("index", "首页模版");
			PcRenovation.getJSONObject("Index").put("add", "新增首页模版");
			PcRenovation.getJSONObject("Index").put("edit", "编辑首页模版");
			PcRenovation.getJSONObject("Index").put("del", "删除首页模版");
			PcRenovation.getJSONObject("Index").put("setDefault", "模板开启开关");

			PcRenovation.put("News", new JSONObject(new LinkedHashMap<>()));
			PcRenovation.getJSONObject("News").put("_isshow", true);
			PcRenovation.getJSONObject("News").put("_auth", true);
			PcRenovation.getJSONObject("News").put("_name", "新闻栏目页面");
			PcRenovation.getJSONObject("News").put("index", "新闻栏目模版");
			PcRenovation.getJSONObject("News").put("add", "新增新闻栏目模版");
			PcRenovation.getJSONObject("News").put("edit", "编辑新闻栏目模版");
			PcRenovation.getJSONObject("News").put("del", "删除新闻栏目模版");

			PcRenovation.put("NewsDetail", new JSONObject(new LinkedHashMap<>()));
			PcRenovation.getJSONObject("NewsDetail").put("_isshow", true);
			PcRenovation.getJSONObject("NewsDetail").put("_auth", true);
			PcRenovation.getJSONObject("NewsDetail").put("_name", "新闻详情页面");
			PcRenovation.getJSONObject("NewsDetail").put("index", "新闻详情模版");
			PcRenovation.getJSONObject("NewsDetail").put("add", "新增新闻详情模版");
			PcRenovation.getJSONObject("NewsDetail").put("edit", "编辑新闻详情模版");
			PcRenovation.getJSONObject("NewsDetail").put("del", "删除新闻详情模版");

			PcRenovation.put("Product", new JSONObject(new LinkedHashMap<>()));
			PcRenovation.getJSONObject("Product").put("_isshow", true);
			PcRenovation.getJSONObject("Product").put("_auth", true);
			PcRenovation.getJSONObject("Product").put("_name", "产品栏目页面");
			PcRenovation.getJSONObject("Product").put("index", "产品栏目模版");
			PcRenovation.getJSONObject("Product").put("add", "新增产品栏目模版");
			PcRenovation.getJSONObject("Product").put("edit", "编辑产品栏目模版");
			PcRenovation.getJSONObject("Product").put("del", "删除产品栏目模版");

			PcRenovation.put("ProductDetail", new JSONObject(new LinkedHashMap<>()));
			PcRenovation.getJSONObject("ProductDetail").put("_isshow", true);
			PcRenovation.getJSONObject("ProductDetail").put("_auth", true);
			PcRenovation.getJSONObject("ProductDetail").put("_name", "产品详情页面");
			PcRenovation.getJSONObject("ProductDetail").put("index", "产品详情模版");
			PcRenovation.getJSONObject("ProductDetail").put("add", "新增产品详情模版");
			PcRenovation.getJSONObject("ProductDetail").put("edit", "编辑产品详情模版");
			PcRenovation.getJSONObject("ProductDetail").put("del", "删除产品详情模版");

			PcRenovation.put("Brief", new JSONObject(new LinkedHashMap<>()));
			PcRenovation.getJSONObject("Brief").put("_isshow", true);
			PcRenovation.getJSONObject("Brief").put("_auth", true);
			PcRenovation.getJSONObject("Brief").put("_name", "公司简介栏目页面");
			PcRenovation.getJSONObject("Brief").put("index", "公司简介栏目模版");
			PcRenovation.getJSONObject("Brief").put("add", "新增公司简介栏目模版");
			PcRenovation.getJSONObject("Brief").put("edit", "编辑公司简介栏目模版");
			PcRenovation.getJSONObject("Brief").put("del", "删除公司简介栏目模版");

			PcRenovation.put("BriefDetail", new JSONObject(new LinkedHashMap<>()));
			PcRenovation.getJSONObject("BriefDetail").put("_isshow", true);
			PcRenovation.getJSONObject("BriefDetail").put("_auth", true);
			PcRenovation.getJSONObject("BriefDetail").put("_name", "公司简介详情页面");
			PcRenovation.getJSONObject("BriefDetail").put("index", "公司简介详情模版");
			PcRenovation.getJSONObject("BriefDetail").put("add", "新增公司简介详情模版");
			PcRenovation.getJSONObject("BriefDetail").put("edit", "编辑公司简介详情模版");
			PcRenovation.getJSONObject("BriefDetail").put("del", "删除公司简介详情模版");

			PcRenovation.put("Business", new JSONObject(new LinkedHashMap<>()));
			PcRenovation.getJSONObject("Business").put("_isshow", true);
			PcRenovation.getJSONObject("Business").put("_auth", true);
			PcRenovation.getJSONObject("Business").put("_name", "业务范围栏目页面");
			PcRenovation.getJSONObject("Business").put("index", "业务范围栏目模版");
			PcRenovation.getJSONObject("Business").put("add", "新增业务范围栏目模版");
			PcRenovation.getJSONObject("Business").put("edit", "编辑业务范围栏目模版");
			PcRenovation.getJSONObject("Business").put("del", "删除业务范围栏目模版");

			PcRenovation.put("BusinessDetail", new JSONObject(new LinkedHashMap<>()));
			PcRenovation.getJSONObject("BusinessDetail").put("_isshow", true);
			PcRenovation.getJSONObject("BusinessDetail").put("_auth", true);
			PcRenovation.getJSONObject("BusinessDetail").put("_name", "业务范围详情页面");
			PcRenovation.getJSONObject("BusinessDetail").put("index", "业务范围详情模版");
			PcRenovation.getJSONObject("BusinessDetail").put("add", "新增业务范围详情模版");
			PcRenovation.getJSONObject("BusinessDetail").put("edit", "编辑业务范围详情模版");
			PcRenovation.getJSONObject("BusinessDetail").put("del", "删除业务范围详情模版");

			PcRenovation.put("Case", new JSONObject(new LinkedHashMap<>()));
			PcRenovation.getJSONObject("Case").put("_isshow", true);
			PcRenovation.getJSONObject("Case").put("_auth", true);
			PcRenovation.getJSONObject("Case").put("_name", "应用案例栏目页面");
			PcRenovation.getJSONObject("Case").put("index", "应用案例栏目模版");
			PcRenovation.getJSONObject("Case").put("add", "新增应用案例栏目模版");
			PcRenovation.getJSONObject("Case").put("edit", "编辑应用案例栏目模版");
			PcRenovation.getJSONObject("Case").put("del", "删除应用案例栏目模版");

			PcRenovation.put("CaseDetail", new JSONObject(new LinkedHashMap<>()));
			PcRenovation.getJSONObject("CaseDetail").put("_isshow", true);
			PcRenovation.getJSONObject("CaseDetail").put("_auth", true);
			PcRenovation.getJSONObject("CaseDetail").put("_name", "应用案例详情页面");
			PcRenovation.getJSONObject("CaseDetail").put("index", "应用案例详情模版");
			PcRenovation.getJSONObject("CaseDetail").put("add", "新增应用案例详情模版");
			PcRenovation.getJSONObject("CaseDetail").put("edit", "编辑应用案例详情模版");
			PcRenovation.getJSONObject("CaseDetail").put("del", "删除应用案例详情模版");

			PcRenovation.put("Album", new JSONObject(new LinkedHashMap<>()));
			PcRenovation.getJSONObject("Album").put("_isshow", true);
			PcRenovation.getJSONObject("Album").put("_auth", true);
			PcRenovation.getJSONObject("Album").put("_name", "公司相册栏目页面");
			PcRenovation.getJSONObject("Album").put("index", "公司相册栏目模版");
			PcRenovation.getJSONObject("Album").put("add", "新增公司相册栏目模版");
			PcRenovation.getJSONObject("Album").put("edit", "编辑公司相册栏目模版");
			PcRenovation.getJSONObject("Album").put("del", "删除公司相册栏目模版");

			PcRenovation.put("AlbumDetail", new JSONObject(new LinkedHashMap<>()));
			PcRenovation.getJSONObject("AlbumDetail").put("_isshow", true);
			PcRenovation.getJSONObject("AlbumDetail").put("_auth", true);
			PcRenovation.getJSONObject("AlbumDetail").put("_name", "公司相册详情页面");
			PcRenovation.getJSONObject("AlbumDetail").put("index", "公司相册详情模版");
			PcRenovation.getJSONObject("AlbumDetail").put("add", "新增公司相册详情模版");
			PcRenovation.getJSONObject("AlbumDetail").put("edit", "编辑公司相册详情模版");
			PcRenovation.getJSONObject("AlbumDetail").put("del", "删除公司相册详情模版");

			PcRenovation.put("Message", new JSONObject(new LinkedHashMap<>()));
			PcRenovation.getJSONObject("Message").put("_isshow", true);
			PcRenovation.getJSONObject("Message").put("_auth", true);
			PcRenovation.getJSONObject("Message").put("_name", "客户留言页面");
			PcRenovation.getJSONObject("Message").put("index", "客户留言模版");
			PcRenovation.getJSONObject("Message").put("add", "新增客户留言模版");
			PcRenovation.getJSONObject("Message").put("edit", "编辑客户留言模版");
			PcRenovation.getJSONObject("Message").put("del", "删除客户留言模版");
			PcRenovation.getJSONObject("Message").put("setDefault", "模板开启开关");

			PcRenovation.put("Job", new JSONObject(new LinkedHashMap<>()));
			PcRenovation.getJSONObject("Job").put("_isshow", true);
			PcRenovation.getJSONObject("Job").put("_auth", true);
			PcRenovation.getJSONObject("Job").put("_name", "招聘栏目页面");
			PcRenovation.getJSONObject("Job").put("index", "招聘栏目模版");
			PcRenovation.getJSONObject("Job").put("add", "新增招聘栏目模版");
			PcRenovation.getJSONObject("Job").put("edit", "编辑招聘栏目模版");
			PcRenovation.getJSONObject("Job").put("del", "删除招聘栏目模版");

			PcRenovation.put("JobDetail", new JSONObject(new LinkedHashMap<>()));
			PcRenovation.getJSONObject("JobDetail").put("_isshow", true);
			PcRenovation.getJSONObject("JobDetail").put("_auth", true);
			PcRenovation.getJSONObject("JobDetail").put("_name", "招聘详情页面");
			PcRenovation.getJSONObject("JobDetail").put("index", "招聘详情模版");
			PcRenovation.getJSONObject("JobDetail").put("add", "新增招聘详情模版");
			PcRenovation.getJSONObject("JobDetail").put("edit", "编辑招聘详情模版");
			PcRenovation.getJSONObject("JobDetail").put("del", "删除招聘详情模版");

			PcRenovation.put("ContactUs", new JSONObject(new LinkedHashMap<>()));
			PcRenovation.getJSONObject("ContactUs").put("_isshow", true);
			PcRenovation.getJSONObject("ContactUs").put("_auth", true);
			PcRenovation.getJSONObject("ContactUs").put("_name", "联系我们栏目页面");
			PcRenovation.getJSONObject("ContactUs").put("index", "联系我们栏目模版");
			PcRenovation.getJSONObject("ContactUs").put("add", "新增联系我们栏目模版");
			PcRenovation.getJSONObject("ContactUs").put("edit", "编辑联系我们栏目模版");
			PcRenovation.getJSONObject("ContactUs").put("del", "删除联系我们栏目模版");

			PcRenovation.put("ContactUsDetail", new JSONObject(new LinkedHashMap<>()));
			PcRenovation.getJSONObject("ContactUsDetail").put("_isshow", true);
			PcRenovation.getJSONObject("ContactUsDetail").put("_auth", true);
			PcRenovation.getJSONObject("ContactUsDetail").put("_name", "联系我们详情页面");
			PcRenovation.getJSONObject("ContactUsDetail").put("index", "联系我们详情模版");
			PcRenovation.getJSONObject("ContactUsDetail").put("add", "新增联系我们详情模版");
			PcRenovation.getJSONObject("ContactUsDetail").put("edit", "编辑联系我们详情模版");
			PcRenovation.getJSONObject("ContactUsDetail").put("del", "删除联系我们详情模版");

			PcRenovation.put("Custom", new JSONObject(new LinkedHashMap<>()));
			PcRenovation.getJSONObject("Custom").put("_isshow", true);
			PcRenovation.getJSONObject("Custom").put("_auth", true);
			PcRenovation.getJSONObject("Custom").put("_name", "自定义页面");
			PcRenovation.getJSONObject("Custom").put("index", "自定义模版");
			PcRenovation.getJSONObject("Custom").put("add", "新增自定义模版");
			PcRenovation.getJSONObject("Custom").put("edit", "编辑自定义模版");
			PcRenovation.getJSONObject("Custom").put("del", "删除自定义模版");
			home.put("PcRenovation", PcRenovation);

			JSONObject MobileRenovation = new JSONObject(new LinkedHashMap<>());
			MobileRenovation = JSONObject.parseObject(PcRenovation.toJSONString(), Feature.OrderedField);
			MobileRenovation.put("_name", "手机端模版");
			MobileRenovation.put("_isshow", true);
			MobileRenovation.put("_auth", true);
			MobileRenovation.put("_icon", "icon-building");

			if (Common.hasMobile) {
				home.put("MobileRenovation", MobileRenovation);
			}

			JSONObject Category = new JSONObject(new LinkedHashMap<>());
			Category.put("_name", "栏目管理");
			Category.put("_isshow", true);
			Category.put("_auth", true);
			Category.put("_icon", "icon-building");

			Category.put("Index", new JSONObject(new LinkedHashMap<>()));
			Category.getJSONObject("Index").put("_isshow", true);
			Category.getJSONObject("Index").put("_auth", true);
			Category.getJSONObject("Index").put("_name", "栏目管理");
			Category.getJSONObject("Index").put("index", "栏目管理");
			Category.getJSONObject("Index").put("add", "新增栏目");
			Category.getJSONObject("Index").put("edit", "编辑栏目");
			Category.getJSONObject("Index").put("del", "删除栏目");

			home.put("Category", Category);

			JSONObject Detail = new JSONObject(new LinkedHashMap<>());
			Detail.put("_name", "内容管理");
			Detail.put("_isshow", true);
			Detail.put("_auth", true);
			Detail.put("_icon", "icon-qrcode");
			Detail.put("News", new JSONObject(new LinkedHashMap<>()));
			Detail.getJSONObject("News").put("_isshow", true);
			Detail.getJSONObject("News").put("_auth", true);
			Detail.getJSONObject("News").put("_name", "新闻管理");
			Detail.getJSONObject("News").put("index", "新闻管理");
			Detail.getJSONObject("News").put("add", "新增新闻");
			Detail.getJSONObject("News").put("edit", "编辑新闻");
			Detail.getJSONObject("News").put("del", "删除新闻");

			Detail.put("Products", new JSONObject(new LinkedHashMap<>()));
			Detail.getJSONObject("Products").put("_isshow", true);
			Detail.getJSONObject("Products").put("_auth", true);
			Detail.getJSONObject("Products").put("_name", "产品管理");
			Detail.getJSONObject("Products").put("index", "产品管理");
			Detail.getJSONObject("Products").put("add", "新增产品");
			Detail.getJSONObject("Products").put("edit", "编辑产品");
			Detail.getJSONObject("Products").put("del", "删除产品");

			Detail.put("Brief", new JSONObject(new LinkedHashMap<>()));
			Detail.getJSONObject("Brief").put("_isshow", true);
			Detail.getJSONObject("Brief").put("_auth", true);
			Detail.getJSONObject("Brief").put("_name", "公司简介");
			Detail.getJSONObject("Brief").put("index", "公司简介");
			Detail.getJSONObject("Brief").put("add", "新建公司简介");
			Detail.getJSONObject("Brief").put("edit", "编辑公司简介");
			Detail.getJSONObject("Brief").put("del", "删除公司简介");

			Detail.put("Business", new JSONObject(new LinkedHashMap<>()));
			Detail.getJSONObject("Business").put("_isshow", true);
			Detail.getJSONObject("Business").put("_auth", true);
			Detail.getJSONObject("Business").put("_name", "业务范围");
			Detail.getJSONObject("Business").put("index", "业务范围");
			Detail.getJSONObject("Business").put("add", "新建业务范围");
			Detail.getJSONObject("Business").put("edit", "编辑业务范围");
			Detail.getJSONObject("Business").put("del", "删除业务范围");

			Detail.put("Case", new JSONObject(new LinkedHashMap<>()));
			Detail.getJSONObject("Case").put("_isshow", true);
			Detail.getJSONObject("Case").put("_auth", true);
			Detail.getJSONObject("Case").put("_name", "应用案例");
			Detail.getJSONObject("Case").put("index", "应用案例");
			Detail.getJSONObject("Case").put("add", "新建应用案例");
			Detail.getJSONObject("Case").put("edit", "编辑应用案例");
			Detail.getJSONObject("Case").put("del", "删除应用案例");

			Detail.put("Album", new JSONObject(new LinkedHashMap<>()));
			Detail.getJSONObject("Album").put("_isshow", true);
			Detail.getJSONObject("Album").put("_auth", true);
			Detail.getJSONObject("Album").put("_name", "公司相册");
			Detail.getJSONObject("Album").put("index", "公司相册");
			Detail.getJSONObject("Album").put("add", "新建公司相册");
			Detail.getJSONObject("Album").put("edit", "编辑公司相册");
			Detail.getJSONObject("Album").put("del", "删除公司相册");

			Detail.put("Message", new JSONObject(new LinkedHashMap<>()));
			Detail.getJSONObject("Message").put("_isshow", true);
			Detail.getJSONObject("Message").put("_auth", true);
			Detail.getJSONObject("Message").put("_name", "客户留言");
			Detail.getJSONObject("Message").put("index", "客户留言");

			Detail.put("Job", new JSONObject(new LinkedHashMap<>()));
			Detail.getJSONObject("Job").put("_isshow", true);
			Detail.getJSONObject("Job").put("_auth", true);
			Detail.getJSONObject("Job").put("_name", "人力招聘");
			Detail.getJSONObject("Job").put("index", "人力招聘");
			Detail.getJSONObject("Job").put("add", "新建人力招聘");
			Detail.getJSONObject("Job").put("edit", "编辑人力招聘");
			Detail.getJSONObject("Job").put("del", "删除人力招聘");

			Detail.put("ContactUs", new JSONObject(new LinkedHashMap<>()));
			Detail.getJSONObject("ContactUs").put("_isshow", true);
			Detail.getJSONObject("ContactUs").put("_auth", true);
			Detail.getJSONObject("ContactUs").put("_name", "联系我们");
			Detail.getJSONObject("ContactUs").put("index", "联系我们");
			Detail.getJSONObject("ContactUs").put("add", "新建联系我们");
			Detail.getJSONObject("ContactUs").put("edit", "编辑联系我们");
			Detail.getJSONObject("ContactUs").put("del", "删除联系我们");

			Detail.put("Links", new JSONObject(new LinkedHashMap<>()));
			Detail.getJSONObject("Links").put("_isshow", true);
			Detail.getJSONObject("Links").put("_auth", true);
			Detail.getJSONObject("Links").put("_name", "友情链接");
			Detail.getJSONObject("Links").put("index", "友情链接");
			Detail.getJSONObject("Links").put("add", "新建友情链接");
			Detail.getJSONObject("Links").put("edit", "编辑友情链接");
			Detail.getJSONObject("Links").put("del", "删除友情链接");
			home.put("Detail", Detail);
		}

		if (AdminMenuConfig.union == null) {
			AdminMenuConfig.union = new JSONObject(new LinkedHashMap<>());

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
			union.put("Order", Order);

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
			union.put("Member", Member);

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
			union.put("Qr", Qr);

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
			union.put("Commission", Commission);

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
			union.put("Finance", Finance);

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
			union.put("Renovation", Renovation);
		}

		if (AdminMenuConfig.system == null) {
			AdminMenuConfig.system = new JSONObject(new LinkedHashMap<>());

			JSONObject Index = new JSONObject(new LinkedHashMap<>());
			Index.put("_name", "管理员管理");
			Index.put("_isshow", true);
			Index.put("_auth", true);
			Index.put("_icon", "icon-gears");

			Index.put("Role", new JSONObject(new LinkedHashMap<>()));
			Index.getJSONObject("Role").put("_isshow", true);
			Index.getJSONObject("Role").put("_auth", true);
			Index.getJSONObject("Role").put("_name", "权限分配");
			Index.getJSONObject("Role").put("index", "权限分配");

			Index.put("Auth", new JSONObject(new LinkedHashMap<>()));
			Index.getJSONObject("Auth").put("_isshow", false);
			Index.getJSONObject("Auth").put("_auth", true);
			Index.getJSONObject("Auth").put("_name", "权限设置");
			Index.getJSONObject("Auth").put("index", "权限设置");

			Index.put("Admin", new JSONObject(new LinkedHashMap<>()));
			Index.getJSONObject("Admin").put("_isshow", true);
			Index.getJSONObject("Admin").put("_auth", true);
			Index.getJSONObject("Admin").put("_name", "管理员设置");
			Index.getJSONObject("Admin").put("index", "管理员设置");

			system.put("Index", Index);

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
			system.put("SystemSet", SystemSet);
		}

		if (menu_category_name.equals("home")) {
			return home;
		} else if (menu_category_name.equals("system")) {
			return system;
		} else if (menu_category_name.equals("union")) {
			return union;
		} else if (menu_category_name.equals("all")) {
			JSONObject object = new JSONObject(new LinkedHashMap<>());
			object.put("home", home);
			object.put("union", union);
			return object;
		} else {
			return new JSONObject();
		}
	}
}
