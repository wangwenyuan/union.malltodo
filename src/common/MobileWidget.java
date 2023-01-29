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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.T;

import common.database.RENOVATION;
import common.database.WEBSITE;

public class MobileWidget {
	public static Map<String, JSONObject> getWidgets(String root, String rootUrl, String category) throws FileNotFoundException, IOException {
		Map<String, JSONObject> map = new LinkedHashMap<String, JSONObject>();
		try {
			Class<?> widget = Class.forName(Common.template.getClassName());
			if (null == widget) {
				return null;
			}
			Object widget_object = widget.newInstance();
			Class<?>[] widget_args = { String.class, String.class, String.class };
			Object[] args = { root, rootUrl, category };
			Method get_widget = widget.getMethod("getWidgets", widget_args);
			map = (Map<String, JSONObject>) get_widget.invoke(widget_object, args);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			// TODO: handle exception
			e.printStackTrace();
			T.javatodo_error_log(e);
		}
		return map;
	}

	public static JSONObject getWidget(String root, String rootUrl, String widget_category, String widget_name, String widget_sign, String widget_json) throws IOException {
		return getBaseWidget(root, rootUrl, widget_category, widget_name, widget_sign, widget_json);
	}

	public static JSONObject getBaseWidget(String root, String rootUrl, String widget_category, String widget_name, String shijian, String json) throws IOException {
		JSONObject object = new JSONObject();
		try {
			Class<?> widget = Class.forName(Common.template.getClassName());
			if (null == widget) {
				return null;
			}
			Object widget_object = widget.newInstance();
			Class<?>[] widget_args = { String.class, String.class, String.class, String.class, String.class, String.class };
			Object[] args = { root, rootUrl, widget_category, widget_name, shijian, json };
			Method get_widget = widget.getMethod("getWidget", widget_args);
			object = (JSONObject) get_widget.invoke(widget_object, args);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			// TODO: handle exception
			e.printStackTrace();
			T.javatodo_error_log(e);
		}
		return object;
	}

	// 根据页面的doms和doms_sort生成页面模版缓存。
	public static String buildHtmlCSSTemplate(String root, String rootUrl, JSONObject doms, JSONArray doms_sort) throws IOException {
		try {
			Class<?> widget = Class.forName(Common.template.getClassName());
			if (null == widget) {
				return null;
			}
			Object widget_object = widget.newInstance();
			// String html, JSONObject doms, JSONArray doms_sort, Map<String, JSONObject>
			// bindDataMap, Integer type
			Class<?>[] widget_args = { String.class, String.class, String.class, String.class };
			Object[] args = { root, rootUrl, doms.toString(), doms_sort.toString() };
			Method get_widget = widget.getMethod("buildHtmlCSSTemplate", widget_args);
			return (String) get_widget.invoke(widget_object, args);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			// TODO: handle exception
			e.printStackTrace();
			T.javatodo_error_log(e);
			return "";
		}
	}

	// 生成页面
	// webRequestParam：需要传入的其他参数，比如栏目的id或者商品的id 搜索的关键字等等
	public static String buildPage(String id, JSONObject webRequestParam, HttpServletRequest request) throws UnsupportedEncodingException, SQLException {
		return buildPage(id, webRequestParam, request, "", "", "");
	}

	// 生成页面
	// webRequestParam：需要传入的其他参数，比如栏目的id或者商品的id 搜索的关键字等等
	public static String buildPage(String id, JSONObject webRequestParam, HttpServletRequest request, String seo_title, String seo_keywords, String seo_description) throws SQLException, UnsupportedEncodingException {
		Map<String, W> where = new HashMap<>();
		where.put(RENOVATION.id, new W("eq", id));
		where.put(RENOVATION.is_del, new W("eq", 0));
		Map<String, Object> map = new MU(RENOVATION._table_name).where(where).find();
		if (map == null) {
			return "";
		}
		String body_html = buildHtmlCSS(id, webRequestParam, 0, request);
		String header_html = buildHtmlCSS(map.get(RENOVATION.header_id).toString(), webRequestParam, 0, request);
		String bottom_html = buildHtmlCSS(map.get(RENOVATION.bottom_id).toString(), webRequestParam, 0, request);

		// 获取页面的标题、关键字、描述等信息
		String title = seo_title;
		String keywords = seo_keywords;
		String description = seo_description;
		if (seo_title.trim().equals("") && seo_keywords.trim().equals("") && seo_description.trim().equals("")) {
			title = map.get(RENOVATION.title).toString();
			keywords = map.get(RENOVATION.keywords).toString();
			description = map.get(RENOVATION.description).toString();
		}

		String body_color = map.get(RENOVATION.background_color).toString();
		String body_image = map.get(RENOVATION.background_image).toString();
		String body_repeat = map.get(RENOVATION.background_repeat).toString();

		// 初始化body样式
		if (!body_image.equals("")) {
			String background_image = "";
			background_image = "background-image:" + T.htmlspecialchars_decode(body_image) + ";";
			if (body_repeat.equals("repeat")) {
				background_image = "background-repeat:repeat; background-size:auto; " + background_image;
			} else {
				background_image = "background:center center no-repeat; background-size:cover; background-repeat:no-repeat; " + background_image;
			}

			body_color = "		<style>\r\n" + "			body {\r\n" + background_image + "				margin: 0rem;\r\n" + "				padding: 0rem;\r\n" + "			}\r\n" + "		</style>\r\n";
		} else {
			body_color = "		<style>\r\n" + "			body {\r\n" + "				background: " + body_color + ";\r\n" + "				margin: 0rem;\r\n" + "				padding: 0rem;\r\n" + "			}\r\n" + "		</style>\r\n";
		}

		// 组装页面
		String string = "<!DOCTYPE html>\r\n" + "<html>\r\n" + "	<head>\r\n" + "		<meta charset=\"utf-8\">\r\n" + "		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=0\">\r\n" + "		<title>" + title + "</title>\r\n" + "		<meta name=\"keywords\" content=\"" + keywords + "\">\r\n" + "		<meta name=\"description\" content=\"" + description + "\">\r\n" + "		<script src=\"./Public/js/jquery-1.12.4.min.js\"></script>\r\n" + "		<script src=\"./Public/js/layer.js\"></script>\r\n" + "		<script src=\"./Public/js/http.js\"></script>\r\n" + "		<script src=\"./Public/js/js.js\"></script>\r\n" + "		<script src=\"./Public/js/drop.js\"></script>\r\n" + "		<link href=\"./Public/css/swiper.min.css\" rel=\"stylesheet\" />\r\n" + "		<script src=\"./Public/js/swiper.min.js\"></script>\r\n" + "		<script src=\"./Public/js/vue.min.js\"></script>\r\n		<script charset=\"utf-8\" src=\"https://map.qq.com/api/gljs?v=1.exp&libraries=service&key=JYZBZ-7B2AX-GY24G-7GSPN-I2R36-KOFRO\"></script>\r\n  " + body_color + "	</head>\r\n" + "	<body>\r\n";
		string = string + "<style>\r\n";
		string = string + "#malltodo_mask{ position: fixed; top: 0rem; left: 0rem; background: center center no-repeat #FFFFFF url(./Public/images/loading.gif); background-size: auto; z-index: 100000;} \r\n";
		string = string + "</style>\r\n";
		string = string + "<link rel=\"stylesheet\" type=\"text/css\" href=\"./Public/css/index.css\" />\r\n";
		string = string + "<link rel=\"stylesheet\" type=\"text/css\" href=\"./Public/css/pintuer.css\" />\r\n";
		string = string + "<link rel=\"stylesheet\" type=\"text/css\" href=\"./Public/css/animate.min.css\" />\r\n";
		string = string + "<script>\r\n";
		string = string + "var malltodo_windows_width = parseInt($(window).width());\r\n";
		if (T.isMobile(request)) {
			string = string + "$('html').css({'font-size': (malltodo_windows_width / 304)*16 + 'px'});\r\n";
		}
		string = string + "</script>\r\n";
		string = string + "<div id=\"malltodo_mask\"></div>\r\n";
		string = string + "<script>\r\n";
		string = string + "$(\"#malltodo_mask\").width($(window).width());$(\"#malltodo_mask\").height($(window).height());\r\n";
		string = string + "$(function(){$(\"#malltodo_mask\").fadeOut(500, function(){$(\"#malltodo_mask\").hide();})})\r\n";
		string = string + "var malltodo_soft = 'index';\r\n";
		string = string + "</script>\r\n";
		string = string + header_html + body_html + bottom_html;

		// 获取统计代码
		String code = "\r\n<div style='display:none'>\r\n";

		String websiteId = request.getSession().getAttribute("website_id").toString();
		Map<String, W> website_where = new HashMap<>();
		website_where.put(WEBSITE.id, new W("eq", websiteId));
		Map<String, Object> websiteInfo = new MU(WEBSITE._table_name).where(website_where).find();
		if (websiteInfo != null) {
			code = code + T.htmlspecialchars_decode(websiteInfo.get(WEBSITE.statistics_code).toString());
		}
		code = code + "\r\n</div>\r\n";
		string = string + code;

		string = string + "</body>\r\n" + "</html>";
		string = string.replaceAll("javatodo-navigation", "a");// 替换a标签
		return string;
	}

	// 生成列表（主要用于加载下拉列表时候使用）
	// webRequestParam：需要传入的其他参数，比如栏目的id或者商品的id 搜索的关键字等等
	public static String buildList(String id, JSONObject webRequestParam, HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
		Map<String, W> where = new HashMap<>();
		where.put(RENOVATION.id, new W("eq", id));
		where.put(RENOVATION.is_del, new W("eq", 0));
		Map<String, Object> map = new MU(RENOVATION._table_name).where(where).find();
		if (map == null) {
			return "";
		}
		String body_html = buildHtmlCSS(id, webRequestParam, 1, request);
		return body_html;
	}

	// 生成页面
	// webRequestParam：需要传入的其他参数，比如栏目的id或者商品的id 搜索的关键字等等
	// type:0 表示对整个页面进行解析， 1 表示对下拉列表等部分dom进行解析
	private static String buildHtmlCSS(String id, JSONObject webRequestParam, Integer type, HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
		Map<String, W> where = new HashMap<>();
		where.put(RENOVATION.id, new W("eq", id));
		where.put(RENOVATION.is_del, new W("eq", 0));
		Map<String, Object> map = new MU(RENOVATION._table_name).where(where).find();
		if (map == null) {
			return "";
		}

		String html = "";
		String doms_string = "";
		JSONObject doms = new JSONObject();
		if (type == 0) {
			html = map.get("html").toString();
			html = T.htmlspecialchars_decode(html);
			doms_string = map.get("doms").toString();
			doms_string = T.htmlspecialchars_decode(doms_string);
			doms = JSONObject.parseObject(doms_string);
		}

		if (type == 1) {// 说明是对下拉列表等部分dom进行解析
			html = map.get("list_html").toString();
			html = T.htmlspecialchars_decode(html);
			doms_string = map.get("list_dom").toString();
			doms = JSONObject.parseObject(doms_string);
		}

		Map<String, JSONObject> bindDataMap = new HashMap<>();
		JSONArray doms_sort = new JSONArray();

		for (String key : doms.keySet()) {
			if (key.equals("category")) {
				continue;
			}
			if (key.equals("name")) {
				continue;
			}
			if (key.equals("sign")) {
				continue;
			}
			if (key.equals("javatodo-bind-loop")) {
				continue;
			}
			if (key.equals("javatodo-bind-param")) {
				continue;
			}

			JSONObject dom = doms.getJSONObject(key);
			String category = dom.getString("category");
			if (category.equals("base")) {
				continue;
			}
			doms_sort.add(key);
			List<String> bind_loop_list = getBindLoopList(dom);

			JSONObject bindData = BindData.bind(dom, webRequestParam, request, bind_loop_list);
			bindDataMap.put(key, bindData);
		}

		return buildOneWidgetHtmlCSS(html, doms, doms_sort, bindDataMap, type);
	}

	private static String buildOneWidgetHtmlCSS(String html, JSONObject doms, JSONArray doms_sort, Map<String, JSONObject> bindDataMap, Integer type) throws SQLException, UnsupportedEncodingException {
		try {
			Class<?> widget = Class.forName(Common.template.getClassName());
			if (null == widget) {
				return null;
			}
			Object widget_object = widget.newInstance();
			Class<?>[] widget_args = { String.class, JSONObject.class, JSONArray.class, Map.class, Integer.class };
			Object[] args = { html, doms, doms_sort, bindDataMap, type };
			Method get_widget = widget.getMethod("buildOneWidgetHtmlCSS", widget_args);
			return (String) get_widget.invoke(widget_object, args);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			// TODO: handle exception
			e.printStackTrace();
			T.javatodo_error_log(e);
			return "";
		}
	}

	// 解析模版设计页面的菜单（菜单专用）

	public static String parseTemplateMenuDom(String html, JSONObject dom, HttpServletRequest request) throws UnsupportedEncodingException, SQLException {
		if (dom == null) {
			System.out.print("\n dom为null \n");
		}
		List<String> bind_loop_list = getBindLoopList(dom);
		JSONObject bindData = BindData.bind(dom, null, request, bind_loop_list);
		String category = dom.getString("category");
		if (category.equals("base")) {
			return "";
		}

		try {
			Class<?> widget = Class.forName(Common.template.getClassName());
			if (null == widget) {
				return null;
			}
			Object widget_object = widget.newInstance();
			Class<?>[] widget_args = { String.class, String.class, String.class };
			Object[] args = { html, dom.toString(), bindData.toString() };
			Method get_widget = widget.getMethod("parseTemplateMenuDom", widget_args);
			return (String) get_widget.invoke(widget_object, args);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			// TODO: handle exception
			e.printStackTrace();
			T.javatodo_error_log(e);
			return "";
		}
	}

	private static List<String> getBindLoopList(JSONObject dom) {
		List<String> bind_loop_list = new ArrayList<>();
		try {
			Class<?> widget = Class.forName(Common.template.getClassName());
			if (null == widget) {
				return null;
			}
			Object widget_object = widget.newInstance();
			Class<?>[] widget_args = { JSONObject.class };
			Object[] args = { dom };
			Method get_widget = widget.getMethod("getBindLoopList", widget_args);
			bind_loop_list = (List<String>) get_widget.invoke(widget_object, args);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			// TODO: handle exception
			e.printStackTrace();
			T.javatodo_error_log(e);
		}
		return bind_loop_list;
	}

	// 没有模版时输出提醒页面
	public static String noTemplateNotice() {
		String string = "尚未配置默认模版";
		return "<!DOCTYPE html>\r\n" + "<html>\r\n" + "	<head>\r\n" + "		<meta charset=\"utf-8\">\r\n" + "		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=0\">\r\n" + "		<title>" + string + "</title>\r\n" + "	</head>\r\n" + "	<body>\r\n" + "		<div style=\"text-align: center; padding-top: 200px;\">\r\n" + "			" + string + "\r\n" + "		</div>\r\n" + "	</body>\r\n" + "</html>";
	}

	public static String noTemplateNotice(String string) {
		return "<!DOCTYPE html>\r\n" + "<html>\r\n" + "	<head>\r\n" + "		<meta charset=\"utf-8\">\r\n" + "		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=0\">\r\n" + "		<title>" + string + "</title>\r\n" + "	</head>\r\n" + "	<body>\r\n" + "		<div style=\"text-align: center; padding-top: 200px;\">\r\n" + "			" + string + "\r\n" + "		</div>\r\n" + "	</body>\r\n" + "</html>";
	}

}
