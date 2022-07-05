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
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.T;

import common.database.RENOVATION;

public class MobileWidget {
	public static Map<String, JSONObject> getWidgets(String root, String rootUrl, String category) throws FileNotFoundException, IOException {
		Map<String, JSONObject> map = new LinkedHashMap<String, JSONObject>();
		String nameString = "";
		try {
			Class<?> widget = Class.forName(Common.template.getClassName());
			if (null == widget) {
				return null;
			}
			Object widget_object = widget.newInstance();
			Class<?>[] widget_args = { String.class, String.class };
			Object[] args = { root, category };
			Method get_widget = widget.getMethod("getWidgets", widget_args);
			nameString = (String) get_widget.invoke(widget_object, args);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			// TODO: handle exception
			e.printStackTrace();
			T.javatodo_error_log(e);
		}

		String[] nameArr = nameString.split("-");
		for (int i = 0; i < nameArr.length; i = i + 1) {
			JSONObject content = getBaseWidget(root, rootUrl, category, nameArr[i], "", "");
			map.put(nameArr[i], content);
		}

		return map;
	}

	public static JSONObject getWidget(String root, String rootUrl, String widget_category, String widget_name, String widget_sign, String widget_json) throws IOException {
		return getBaseWidget(root, rootUrl, widget_category, widget_name, widget_sign, widget_json);
	}

	public static JSONObject getBaseWidget(String root, String rootUrl, String widget_category, String widget_name, String shijian, String json) throws IOException {
		String html = "";
		String css = "";
		if (shijian.trim().equals("")) {
			shijian = "" + System.currentTimeMillis();
		}
		try {
			Class<?> widget = Class.forName(Common.template.getClassName());
			if (null == widget) {
				return null;
			}
			Object widget_object = widget.newInstance();
			Class<?>[] widget_args = { String.class, String.class, String.class, String.class, String.class };
			Object[] args = { root, rootUrl, widget_category, widget_name, shijian };
			Method get_widget = widget.getMethod("getWidget", widget_args);
			JSONObject object = (JSONObject) get_widget.invoke(widget_object, args);
			html = object.getString("html");
			css = object.getString("css");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			// TODO: handle exception
			e.printStackTrace();
			T.javatodo_error_log(e);
		}
		Document document = Jsoup.parse(html);
		JSONObject initJSON = null;
		if (!json.trim().equals("")) {
			initJSON = new JSONObject();
			initJSON = JSONObject.parseObject(json);
		}

		// 处理样式
		String class_id = "javatodocss" + shijian;
		Elements elements = document.getElementsByAttribute("javatodo-style");
		for (Integer i = 0; i < elements.size(); i = i + 1) {
			Element element = elements.get(i);
			element.addClass(class_id + "_" + i);
		}

		// 处理javatodo-bind-text
		Elements bind_text_elements = document.getElementsByAttribute("javatodo-bind-text");
		for (Integer i = 0; i < bind_text_elements.size(); i = i + 1) {
			String word = bind_text_elements.get(i).text();
			String[] word_attr = word.split("\\|");
			if (word_attr.length > 1) {
				String key = "malltodo" + word;
			}
		}
		// 处理javatodo-bind-loop
		Elements bind_loop_elements = document.getElementsByAttribute("javatodo-bind-loop");
		JSONObject javatodo_bind_loop_json = new JSONObject(new LinkedHashMap<>());

		for (Integer i = 0; i < bind_loop_elements.size(); i = i + 1) {
			Element bind_loop_element = bind_loop_elements.get(i);
			String bind_loop_class = "javatodobindloop" + shijian;
			if (initJSON != null) {
				if (initJSON.containsKey("javatodo-bind-loop")) {
					String word = bind_loop_element.attr("javatodo-bind-loop");
					String words = word + "!!!!" + word;
					bind_loop_elements.get(i).attr("javatodo-bind-loop", words);
				}
			}

			String bind_loop_word = bind_loop_element.attr("javatodo-bind-loop");

			String[] bind_loop_word_arr = bind_loop_word.split("\\|");

			if (bind_loop_word_arr.length == 3) {
				JSONObject object = new JSONObject();
				javatodo_bind_loop_json.put(bind_loop_class + "_" + (i + 3), object);
			}
			if (bind_loop_word_arr.length == 2) {
				JSONObject object = new JSONObject();
				javatodo_bind_loop_json.put(bind_loop_class + "_" + (i + 2), object);
			}
			if (bind_loop_word_arr.length == 1) {
				JSONObject object = new JSONObject();
				javatodo_bind_loop_json.put(bind_loop_class + "_" + (i + 1), object);
			}
		}

		for (Integer i = bind_loop_elements.size() - 1; i > -1; i = i - 1) {
			Element bind_loop_element = bind_loop_elements.get(i);
			String bind_loop_word = bind_loop_element.attr("javatodo-bind-loop");
			String[] bind_loop_word_arr = bind_loop_word.split("\\|");

			if (bind_loop_word_arr.length > 1) {
				Integer bind_loop_times = T.toInt(bind_loop_word_arr[1]);
				String key = bind_loop_word_arr[0];

				Element copy_bind_loop_element = copyElement(bind_loop_element);
				copy_bind_loop_element.removeAttr("javatodo-bind-loop");

				for (Integer n = 1; n < bind_loop_times; n = n + 1) {
					Element copy_element = copyElement(copy_bind_loop_element);
					copy_element.attr("javatodo-bind-id", key + "-|-" + n);
					bind_loop_element.after(copy_element);
					bind_loop_element = bind_loop_element.nextElementSibling();
				}
			}
		}

		// 处理属性
		class_id = "javatodoattr" + shijian;
		Elements attrele = document.getElementsByAttribute("javatodo-attr");
		for (Integer i = 0; i < attrele.size(); i = i + 1) {
			Element element = attrele.get(i);
			element.addClass(class_id + "_" + i);
			element.addClass("javatodo-template");
		}
		// 处理内容
		class_id = "javatodotext" + shijian;
		Elements contentele = document.getElementsByAttribute("javatodo-text");
		for (Integer i = 0; i < contentele.size(); i = i + 1) {
			Element element = contentele.get(i);
			element.attr("javatodo-text", element.text());
			element.addClass(class_id + "_" + i);
			element.addClass("javatodo-template");
		}
		// 处理循环
		String loop_class = "javatodoloop" + shijian;
		Elements loop_elements = document.getElementsByAttribute("javatodo-loop");
		Integer size = loop_elements.size();
		for (Integer i = 0; i < size; i = i + 1) {
			Element element = loop_elements.get(size - i - 1);
			if (!element.hasClass("javatodo-template")) {
				element.addClass("javatodo-template");
			}
			element.addClass(loop_class + "_" + i);
			String loop_attr = element.attr("javatodo-loop");
			String[] loop_string = loop_attr.split("\\|");
			Integer count = T.toInt(loop_string[1]);
			if (initJSON != null) {
				count = initJSON.getJSONObject(loop_class + "_" + i).getInteger("loop-times");
				count = count + 2;
			}
		}

		// 更改类名，为重复的类名增加编号
		Elements classeles = document.getElementsByClass("javatodo-template");
		for (Integer i = 0; i < classeles.size(); i = i + 1) {
			Element element = classeles.get(i);
			Set<String> class_name_set = element.classNames();
			for (String class_name : class_name_set) {
				if (class_name.contains("javatodotext")) {
					Elements class_elements = document.getElementsByClass(class_name);
					if (class_elements.size() > 1) {
						for (Integer n = 0; n < class_elements.size(); n = n + 1) {
							class_elements.get(n).removeClass(class_name);
							class_elements.get(n).addClass(class_name + "_" + n);
						}
					}
				}
			}
		}
		// 生成json数据
		JSONObject object = new JSONObject(new LinkedHashMap<>());
		object.put("category", widget_category);
		object.put("name", widget_name);
		object.put("sign", shijian);
		Elements eles = document.getElementsByClass("javatodo-template");

		for (Integer i = 0; i < eles.size(); i = i + 1) {
			Element element = eles.get(i);
			Set<String> class_name_set = element.classNames();
			for (String class_name : class_name_set) {
				JSONObject obj = new JSONObject();
				if (class_name.contains("javatodocss")) {// 样式
					obj.put("type", "javatodo-style");

					String javatodo_style_string = element.attr("javatodo-style");
					String[] javatodo_style_arr = javatodo_style_string.split(":");
					if (javatodo_style_arr.length == 0) {
						obj.put("javatodo-style", "");
						obj.put("style-title", "");
					}
					if (javatodo_style_arr.length == 1) {
						obj.put("javatodo-style", javatodo_style_arr[0]);
						obj.put("style-title", "");
					}
					if (javatodo_style_arr.length > 1) {
						obj.put("javatodo-style", javatodo_style_arr[0]);
						obj.put("style-title", javatodo_style_arr[1]);
					}
					obj.put("css", new JSONObject());
					if (initJSON != null) {
						if (initJSON.containsKey(class_name)) {
							if (initJSON.getJSONObject(class_name).getString("type").equals("javatodo-style")) {
								obj.put("css", initJSON.getJSONObject(class_name).getJSONObject("css"));
								if (initJSON.getJSONObject(class_name).containsKey("animate")) {
									if (!initJSON.getJSONObject(class_name).getString("animate").trim().equals("")) {
										element.attr("javatodo-animate", initJSON.getJSONObject(class_name).getString("animate"));
										obj.put("animate", initJSON.getJSONObject(class_name).getString("animate"));
									} else {
										element.removeAttr("javatodo-animate");
									}
								} else {
									element.removeAttr("javatodo-animate");
								}
							}
						}
					}
				} else if (class_name.contains("javatodoloop")) {// 循环
					obj.put("type", "javatodo-loop");
					String loop_string = element.attr("javatodo-loop");
					String[] loop_strings = loop_string.split("\\|");
					if (loop_strings.length > 0) {
						obj.put("loop-title", loop_strings[0]);
						obj.put("loop-times", loop_strings.length);
					} else {
						obj.put("loop-title", "");
						obj.put("loop-times", 1);
					}
				} else if (class_name.contains("javatodoattr")) {// 属性
					obj.put("type", "javatodo-attr");
					String attr_string = element.attr("javatodo-attr");
					String[] attr_arr = attr_string.split(":");
					if (attr_arr.length > 1 && attr_arr[0].equals("src") || attr_arr[0].equals("href")) {
						obj.put("javatodo-src_html", attr_arr[0] + "-!-html-!src");
						obj.put("javatodo-href_html", attr_arr[1] + "-!-html-！href");
					} else {
						obj.put("javatodo-attr", attr_arr[0]);
						if (attr_arr[0].equals("href")) {
							obj.put("javatodo-attr-title", "跳转链接");
						} else if (attr_arr[0].equals("src")) {
							obj.put("javatodo-attr-title", "上传");
						}
					}

					obj.put("attr", new Object());
					if (initJSON != null) {
						if (initJSON.containsKey(class_name)) {
							if (initJSON.getJSONObject(class_name).getString("type").equals("javatodo-attr")) {
								obj.put("attr", initJSON.getJSONObject(class_name).getJSONObject("attr"));
								if (obj.getString("javatodo-attr").equals("src")) {
									if (obj.getJSONObject("attr").containsKey("src")) {
										if (!obj.getJSONObject("attr").getString("src").equals("")) {
											element.attr("src", obj.getJSONObject("attr").getString("src"));
										}
									}
								}
								if (obj.getString("javatodo-attr").equals("href")) {
									if (obj.getJSONObject("attr").containsKey("href")) {
										if (!obj.getJSONObject("attr").getString("href").equals("")) {
											element.attr("href", obj.getJSONObject("attr").getString("href"));
										}
									}
								}
							}
						}
					}
				} else if (class_name.contains("javatodotext")) {// 内容体
					obj.put("type", "javatodo-text");
					String content_string = element.html();
					String[] content_strings = content_string.split("\\|");
					if (content_strings.length > 0) {
						obj.put("content-value", content_strings[0]);
						if (content_strings.length > 1) {
							obj.put("content-title", "malltodo" + content_strings[1]);
						} else {
							obj.put("content-title", "");
							obj.put("content-type", "");
						}
						if (content_strings.length > 2) {
							obj.put("content-title", "malltodo" + content_strings[1]);
							obj.put("content-type", "malltodo" + content_strings[2]);
						}
					} else {
						obj.put("content-value", "");
						obj.put("content-title", "");
						obj.put("content-type", "");
					}

					if (initJSON != null) {
						if (initJSON.containsKey(class_name)) {
							if (initJSON.getJSONObject(class_name).getString("type").equals("javatodo-text")) {
								obj = initJSON.getJSONObject(class_name);
							}
						}
					}

					element.text(obj.getString("content-value"));
				} else {
					continue;
				}
				obj.put("extra", new Object());
				if (initJSON != null) {
					if (initJSON.containsKey(class_name)) {
						obj.put("extra", initJSON.getJSONObject(class_name).getJSONObject("extra"));
					}
				}
				object.put(class_name, obj);
			}
		}
		html = document.getElementsByTag("body").html();
		JSONObject ret_obj = new JSONObject();
		ret_obj.put("dom", object);
		ret_obj.put("html", html);
		ret_obj.put("css", css);
		return ret_obj;
	}

	private static Element copyElement(Element element) {
		String html = element.toString();
		Document document = Jsoup.parse(html);
		return document.getElementsByTag("body").get(0).child(0);
	}

	// 根据页面的doms和doms_sort生成页面模版缓存。
	public static String buildHtmlCSSTemplate(String root, String rootUrl, JSONObject doms, JSONArray doms_sort) throws IOException {
		String html = "";
		String CSS = "";
		for (Integer i = 0; i < doms_sort.size(); i = i + 1) {
			String malltodosign = doms_sort.getString(i);
			JSONObject dom = doms.getJSONObject(malltodosign);
			// System.out.print(dom.toJSONString());
			JSONObject widget_object = MobileWidget.getBaseWidget(root, rootUrl, dom.getString("category"), dom.getString("name"), dom.getString("sign"), dom.toJSONString());
			html = html + widget_object.getString("html");
			CSS = CSS + "\n" + widget_object.getString("css");
			for (String key : dom.keySet()) {
				if (key.equals("category")) {
					continue;
				}
				if (key.equals("name")) {
					continue;
				}
				if (key.equals("sign")) {
					continue;
				}
				if (dom.getJSONObject(key).getString("type").equals("javatodo-style")) {
					JSONObject _css = dom.getJSONObject(key).getJSONObject("css");
					CSS = CSS + "\n." + key + "{";
					for (String _k : _css.keySet()) {
						CSS = CSS + _k + ":" + _css.getString(_k) + "; ";
					}
					CSS = CSS + "}";
				}
			}
		}
		html = "\n<style>\n" + CSS + "\n</style>\n" + html + "\n";
		return html;
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

		// 初始化body样式
		if (body_color.equals("")) {
			body_color = "<style>\r\n" + "			body {\r\n" + "				margin: 0rem;\r\n" + "				padding: 0rem;\r\n" + "			}\r\n" + "		</style>";
		} else {
			body_color = "<style>\r\n" + "			body {\r\n" + "				background: " + body_color + ";\r\n" + "				margin: 0rem;\r\n" + "				padding: 0rem;\r\n" + "			}\r\n" + "		</style>";
		}

		// 组装页面
		String string = "<!DOCTYPE html>\r\n" + "<html>\r\n" + "	<head>\r\n" + "		<meta charset=\"utf-8\">\r\n" + "		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=0\">\r\n" + "		<title>" + title + "</title>\r\n" + "		<meta name=\"keywords\" content=\"" + keywords + "\">\r\n" + "		<meta name=\"description\" content=\"" + description + "\">\r\n" + "		<script src=\"./Public/js/jquery-1.12.4.min.js\"></script>\r\n" + "		<script src=\"./Public/js/layer.js\"></script>\r\n" + "		<script src=\"./Public/js/http.js\"></script>\r\n" + "		<script src=\"./Public/js/js.js\"></script>\r\n" + "		<script src=\"./Public/js/drop.js\"></script>\r\n" + "		<link href=\"./Public/css/swiper.min.css\" rel=\"stylesheet\" />\r\n" + "		<script src=\"./Public/js/swiper.min.js\"></script>\r\n" + "		<script src=\"./Public/js/vue.min.js\"></script>\r\n" + body_color + "	</head>\r\n" + "	<body>\r\n";
		string = string + "<style>\r\n";
		string = string + "#malltodo_mask{ position: fixed; top: 0rem; left: 0rem; background: center center no-repeat #FFFFFF url(./Public/images/loading.gif); background-size: inherit; z-index: 100000;} \r\n";
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

		Document document = Jsoup.parse(html);

		if (type == 1) {// 如果是下拉列表则删除css和js等内容
			document.getElementsByTag("style").remove();
			document.getElementsByTag("script").remove();
		}

		if (doms == null) {
			System.out.print("\n doms为null \n");
		}

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
			JSONObject dom = doms.getJSONObject(key);
			Elements elements = document.getElementsByAttributeValue("data-malltodosign", key);
			if (elements.size() > 0) {
				Element element = elements.get(0);
				Element check = getDomHtml(element, dom, webRequestParam, request);
				if (check == null) {
					element.remove();
				}
			}
		}
		document.getElementsByAttribute("data-malltodosign").removeAttr("onclick").removeAttr("data-malltodosign");
		document.getAllElements().removeAttr("javatodo-bind-loop").removeAttr("javatodo-bind-id").removeAttr("javatodo-bind-text").removeAttr("javatodo-bind").removeAttr("javatodo-style").removeAttr("javatodo-loop").removeAttr("javatodo-attr").removeAttr("javatodo-text");
		return "<style>\n" + document.getElementsByTag("style").html() + "\n</style>" + document.getElementsByTag("body").html();
	}

	// 解析模版设计页面的菜单（菜单专用）

	public static String parseTemplateMenuDom(String html, JSONObject doms, HttpServletRequest request) throws UnsupportedEncodingException, SQLException {
		Document document = Jsoup.parse(html);
		if (doms == null) {
			System.out.print("\n doms为null \n");
		}
		JSONObject dom = doms;
		String category = dom.getString("category");
		if (category.equals("base")) {
			return "";
		}
		Elements elements = document.getElementsByAttributeValue("data-malltodosign", "malltodo" + dom.getString("sign"));
		if (elements.size() > 0) {
			Element element = elements.get(0);
			Element check = getDomHtml(element, dom, null, request);
			if (check == null) {
				element.remove();
			}
		}
		return document.getElementsByTag("body").html();
	}

	// webRequestParam：需要传入的其他参数，比如栏目的id或者商品的id 搜索的关键字等等
	private static Element getDomHtml(Element element, JSONObject dom, JSONObject webRequestParam, HttpServletRequest request) throws SQLException, UnsupportedEncodingException {
		JSONObject object = new JSONObject();

		// 获取绑定的数据的循环次数
		JSONObject bind_loop = dom.getJSONObject("javatodo-bind-loop");
		List<String> bind_loop_list = new ArrayList<>();
		Integer _loop_param_length = bind_loop.size();
		for (Integer i = 0; i < _loop_param_length; i = i + 1) {
			JSONObject _obj = bind_loop.getJSONObject("javatodobindloop" + dom.getString("sign") + "_" + i);
			String loop_param = _obj.getString("bind_param") + ":" + _obj.getString("loop-times");
			bind_loop_list.add(loop_param);
		}
		// 处理循环
		Elements elements = element.getElementsByAttribute("javatodo-bind-id");
		for (Integer i = elements.size() - 1; i > -1; i = i - 1) {
			String bind_id = elements.get(i).attr("javatodo-bind-id");
			String[] bind_id_arr = bind_id.split("\\-\\|\\-");
			JSONArray arr = new JSONArray();
			String k = "";
			String kn = "";
			for (Integer n = 0; n < bind_id_arr.length; n = n + 2) {
				if (n % 2 == 0) {
					k = bind_id_arr[n];
					kn = bind_id_arr[n + 1];
					if (arr.size() < T.toInt(kn) + 1) {
						elements.get(i).remove();
						break;
					}
				}
			}
		}
		// 处理数据绑定
		Elements bind_elements = element.getElementsByAttribute("javatodo-bind");
		for (Integer i = 0; i < bind_elements.size(); i = i + 1) {
			String bind = bind_elements.get(i).attr("javatodo-bind");
			String[] bind_id_arr = bind.split("\\-\\|\\-");
			bind_id_arr[0] = "malltodo";
		}
		return element;
	}

	// 没有模版时输出提醒页面
	public static String noTemplateNotice() {
		return "<!DOCTYPE html>\r\n" + "<html>\r\n" + "	<head>\r\n" + "		<meta charset=\"utf-8\">\r\n" + "		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=0\">\r\n" + "		<title>尚未配置默认模板</title>\r\n" + "	</head>\r\n" + "	<body>\r\n" + "		<div style=\"text-align: center; padding-top: 200px;\">\r\n" + "			尚未配置默认模版\r\n" + "		</div>\r\n" + "	</body>\r\n" + "</html>";
	}

}
