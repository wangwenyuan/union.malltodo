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

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.tools.T;

public class Widget {
	public JSONObject getWidget(String root, String rootUrl, String widget_category, String widget_name, String shijian) {
		File jspFile = new File(root + "WEB-INF/Template/admin.Index/IndexController/loginPage.jsp");
		if (!jspFile.exists()) {
			return null;
		}
		if (!rootUrl.contains("http") && !rootUrl.contains("https")) {
			return null;
		}
		String code = "";
		String html = "";
		String css = "";
		code = Common.template.htmlWidget(T.base64_encode(widget_category), T.base64_encode(widget_name));
		code = T.base64_decode(code);
		Document document = Jsoup.parse(code);
		html = document.getElementsByTag("body").html();
		css = document.getElementsByTag("style").html();
		// 替换__PUBLIC__
		html = html.replace("__PUBLIC__", rootUrl + "/Public");
		css = css.replace("__PUBLIC__", rootUrl + "/Public");

		html = html.replace("[!--", "<font javatodo-bind-text=''>");
		html = html.replace("--]", "</font>");

		html = html.replace("{{", "<font javatodo-text=''>");
		html = html.replace("}}", "</font>");

		html = html.replace("javatodo_css_class", "javatodo_jss_" + shijian);
		css = css.replace("javatodo_css_class", "javatodo_jss_" + shijian);

		JSONObject object = new JSONObject();
		object.put("html", html);
		object.put("css", css);
		return object;
	}

	public String getWidgets(String root, String category) {
		File jspFile = new File(root + "WEB-INF/Template/admin.Index/IndexController/loginPage.jsp");
		if (!jspFile.exists()) {
			return null;
		}
		String nameString = Common.template.htmlWidget(T.base64_encode(category));
		nameString = T.base64_decode(nameString);
		return nameString;
	}

	public String getSystemWidget(String root, String widget_name, String widget_id) {
		File jspFile = new File(root + "WEB-INF/Template/admin.Index/IndexController/loginPage.jsp");
		if (!jspFile.exists()) {
			return null;
		}
		String html = Common.template.htmlWidget(T.base64_encode("system"), T.base64_encode(widget_name));
		html = T.base64_decode(html);
		html = html.replace("JAVATODO", widget_id);
		return html;
	}
}
