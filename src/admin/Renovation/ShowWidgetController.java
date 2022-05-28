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
package admin.Renovation;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;

import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.tools.T;

import admin.Index.CommonController;
import common.Functions;
import common.MobileWidget;
import common.SystemWidget;
import freemarker.template.TemplateException;

public class ShowWidgetController extends CommonController {
	public void indexPage() throws IOException, ServletException, TemplateException {
		String category = I("category");
		Map<String, JSONObject> widget_map = MobileWidget.getWidgets(servlet.getServletContext().getRealPath("/"), Functions.getRootUrl(request), category);
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (String key : widget_map.keySet()) {
			String css = widget_map.get(key).getString("css");
			String html = widget_map.get(key).getString("html");
			html = "<style>\n" + css + "\n</style>\n" + html;
			map.put(key, html);
		}
		this.assign("map", map);
		this.assign("category", category);
		this.display();
	}

	public void createPage() throws IOException {
		String category = I("category").trim();
		String name = I("name").trim();
		String sign = I("sign").trim();
		String json = I("json").trim();
		if (!json.equals("")) {
			json = T.htmlspecialchars_decode(json);
		}
		JSONObject object = MobileWidget.getBaseWidget(servlet.getServletContext().getRealPath("/"), Functions.getRootUrl(request), category, name, sign, json);

		String html = object.getString("html");
		String css = object.getString("css");
		html = "<style>\n" + css + "\n</style>\n" + html;
		object.put("html", html);

		String system_html = SystemWidget.buildSystemWidgetPage(servlet.getServletContext().getRealPath("/"), object.getJSONObject("dom"));
		system_html = "<div class=\"ui-c-box\"><div class=\"ui-c-box-left\" style=\"font-size: 1rem\"><strong>" + name + "</strong></div><div class=\"ui-c-box-right\"><a class=\"main_del_button\" onclick=\"del_dom('" + object.getJSONObject("dom").getString("sign") + "')\">删除</a></div><div class=\"ui-c-clear\"></div></div>" + system_html;

		// 处理绑定其他模块的组件（结束）

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
