package common;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;

import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.tools.T;

import admin.home.Index.CommonController;
import common.widget.data.HomeIndexAlbum;
import common.widget.data.HomeIndexBusiness;
import common.widget.data.HomeIndexCase;
import common.widget.data.HomeIndexJob;
import common.widget.data.HomeIndexLinks;
import common.widget.data.HomeIndexNews;
import common.widget.data.HomeIndexProducts;
import common.widget.data.HomeMenu;
import freemarker.template.TemplateException;

public class BaseShowWidget extends CommonController {
	public void indexPage() throws IOException, ServletException, TemplateException {
		String category = I("category");
		Map<String, JSONObject> widget_map = MobileWidget.getWidgets(servlet.getServletContext().getRealPath("/"), T.getRootUrl(request), category);
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (String key : widget_map.keySet()) {
			String css = widget_map.get(key).getString("css");
			String html = widget_map.get(key).getString("html");
			html = "<style>\n" + css + "\n</style>\n" + T.htmlspecialchars_decode(html);
			map.put(key, html);
		}
		this.assign("map", map);
		this.assign("category", category);
		this.display();
	}

	public void createPage() throws IOException, SQLException {
		String category = I("category").trim();
		String name = I("name").trim();
		String sign = I("sign").trim();
		String json = I("json").trim();
		if (!json.equals("")) {
			json = T.htmlspecialchars_decode(json);
		}
		JSONObject object = MobileWidget.getBaseWidget(servlet.getServletContext().getRealPath("/"), T.getRootUrl(request), category, name, sign, json);
		String html = object.getString("html");
		html = T.htmlspecialchars_decode(html);

		String system_html = object.getString("system_html");
		if (category.equals("home_menu_mobile") || category.equals("home_menu_pc")) {
			String menu_system_html = SystemWidget.getSystemWidget(servlet.getServletContext().getRealPath("/"), "menu", sign);
			system_html = menu_system_html + system_html;
		}
		system_html = "<div class=\"ui-c-box\"><div class=\"ui-c-box-left\" style=\"font-size: 1rem\"><strong>" + name + "</strong></div><div class=\"ui-c-box-right\"><a class=\"main_del_button\" onclick=\"del_dom('" + object.getJSONObject("dom").getString("sign") + "')\">删除</a></div><div class=\"ui-c-clear\"></div></div>" + system_html;
		// 处理绑定其他模块的组件（开始）
		JSONObject jsonObject = null;
		if (category.equals("home_menu_mobile") || category.equals("home_menu_pc") || category.equals("home_bottom_menu_mobile") || category.equals("home_bottom_menu_pc")) {
			jsonObject = new HomeMenu().parameter;
		}

		if (category.equals("home_index_products_pc")) {
			jsonObject = new HomeIndexProducts().parameter;
		}

		if (category.equals("home_index_news_pc")) {
			jsonObject = new HomeIndexNews().parameter;
		}

		if (category.equals("home_index_business_pc")) {
			jsonObject = new HomeIndexBusiness().parameter;
		}

		if (category.equals("home_index_album_pc")) {
			jsonObject = new HomeIndexAlbum().parameter;
		}

		if (category.equals("home_index_job_pc")) {
			jsonObject = new HomeIndexJob().parameter;
		}

		if (category.equals("home_index_case_pc")) {
			jsonObject = new HomeIndexCase().parameter;
		}

		if (category.equals("home_index_links_pc")) {
			jsonObject = new HomeIndexLinks().parameter;
		}

		if (jsonObject != null) {
			system_html = system_html + this.getModelSystemHtml(jsonObject, object.getJSONObject("dom").getString("sign"));
		}
		// 处理绑定其他模块的组件（结束）
		if (category.equals("home_menu_mobile") || category.equals("home_menu_pc") || category.equals("home_bottom_menu_pc") || category.equals("home_bottom_menu_mobile")) {
			html = MobileWidget.parseTemplateMenuDom(html, object.getJSONObject("dom"), request);
		}

		String css = object.getString("css");

		html = "<style>\n" + css + "\n</style>\n" + html;
		object.put("html", html);
		object.put("system_html", system_html);
		this.jsonDisplay(object.toJSONString());
	}

	private String getModelSystemHtml(JSONObject param, String sign) {
		if (param.size() == 0) {
			return "";
		}
		String system_html = "<div class=\"ui-c-box\" style=\"font-size: 0.9375rem\">\r\n" + " <strong>数据参数</strong>\r\n" + "</div>";
		for (String key : param.keySet()) {
			JSONObject obj = param.getJSONObject(key);
			String title = obj.getString("_title");
			system_html = system_html + "<div class=\"ui-c-box\">\r\n" + "	<div class=\"ui-c-box-left\">" + title + "：</div>\r\n" + "	<div class=\"ui-c-box-right\">";
			system_html = system_html + "<select class=\"ui-c-select\" id=\"javatodomodel" + key + sign + "\"  onchange=\"change_model_param('" + sign + "', '" + key + "')\">";
			for (String k : obj.keySet()) {
				if (k.equals("_title")) {
					continue;
				} else {
					system_html = system_html + "<option value=\"" + k + "\">" + obj.getString(k) + "</option>";
				}
			}
			system_html = system_html + "</select>";
			system_html = system_html + "</div>\r\n" + "	<div class=\"ui-c-clear\"></div>\r\n" + "</div>";
			system_html = system_html + "<script>change_model_param('" + sign + "', '" + key + "')</script>";
		}
		return system_html;
	}
}