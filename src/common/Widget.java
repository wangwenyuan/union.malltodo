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
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Widget {
	public JSONObject getWidget(String root, String rootUrl, String widget_category, String widget_name, String shijian, String json) {
		File jspFile = new File(root + "WEB-INF/Template/admin.home.Index/IndexController/loginPage.jsp");
		if (!jspFile.exists()) {
			return null;
		}
		if (!rootUrl.contains("http") && !rootUrl.contains("https")) {
			return null;
		}
		try {
			return MicroService.getBaseWidget(rootUrl, widget_category, widget_name, shijian, json);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public Map<String, JSONObject> getWidgets(String root, String rootUrl, String category) throws UnsupportedEncodingException {
		File jspFile = new File(root + "WEB-INF/Template/admin.home.Index/IndexController/loginPage.jsp");
		if (!jspFile.exists()) {
			return null;
		}
		return MicroService.getWidgets(rootUrl, category);
	}

	public String getSystemWidget(String root, String widget_name, String widget_id) throws UnsupportedEncodingException {
		File jspFile = new File(root + "WEB-INF/Template/admin.home.Index/IndexController/loginPage.jsp");
		if (!jspFile.exists()) {
			return null;
		}
		return MicroService.getSystemWidget(widget_name, widget_id);
	}

	public List<String> getBindLoopList(JSONObject dom) throws UnsupportedEncodingException {
		return MicroService.getBindLoopList(dom);
	}

	public String buildOneWidgetHtmlCSS(String html, JSONObject doms, JSONArray doms_sort, Map<String, JSONObject> bindDataMap, Integer type) throws UnsupportedEncodingException {
		String htmlString = html;
		String domJSONString = doms.toString();
		String domsSort = doms_sort.toString();
		String bindDataMapString = JSON.toJSONString(bindDataMap);
		String typeString = type + "";
		return MicroService.buildOneWidgetHtmlCSS(htmlString, domJSONString, domsSort, bindDataMapString, typeString);
	}

	public String parseTemplateMenuDom(String htmlString, String domJSONString, String bindDataJSONString) throws UnsupportedEncodingException {
		String string = MicroService.parseTemplateMenuDom(htmlString, domJSONString, bindDataJSONString);
		return string;
	}

	public String buildHtmlCSSTemplate(String root, String domain, String domsJSONString, String domsSortString) throws UnsupportedEncodingException {
		return MicroService.buildHtmlCSSTemplate(domain, domsJSONString, domsSortString);
	}
}
