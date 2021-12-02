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
package common.database;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.tools.T;

import common.Common;

public class RENOVATION {
	public static String _table_name = "renovation";
	public static String id = "id";
	public static String name = "name";
	public static String title = "title";
	public static String keywords = "keywords";
	public static String description = "description";
	public static String page_pic = "page_pic";
	public static String background_color = "background_color";
	public static String type = "type";
	public static String html = "html";
	public static String doms = "doms";
	public static String doms_sort = "doms_sort";
	public static String bottom_id = "bottom_id";
	public static String is_list = "is_list";
	public static String list_dom = "list_dom";
	public static String list_html = "list_html";
	public static String is_default = "is_default";
	public static String addtime = "addtime";
	public static String last_edit_time = "last_edit_time";
	public static String is_del = "is_del";
	public Map<String, Object> map = new HashMap<>();

	public RENOVATION() {
		map.put("name", "");
		map.put("title", "");
		map.put("keywords", "");
		map.put("description", "");
		map.put("page_pic", "");
		map.put("background_color", "");
		map.put("type", "");
		map.put("html", "");
		map.put("doms", "");
		map.put("doms_sort", "");
		map.put("bottom_id", 0);
		map.put("is_list", 0);
		map.put("list_dom", "");
		map.put("list_html", "");
		map.put("is_default", 0);
		map.put("addtime", 0);
		map.put("last_edit_time", 0);
		map.put("is_del", 0);
	}

	public JSONObject check_add(Map<String, Object> data) {
		for (String key : data.keySet()) {
			if (map.containsKey(key)) {
				if (key.equals("name")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("页面名称最长99个字符");
					}
				}
				if (key.equals("title")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("title最长99个字符");
					}
				}
				if (key.equals("keywords")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("keywords最长255个字符");
					}
				}
				if (key.equals("description")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("description最长255个字符");
					}
				}
				if (key.equals("page_pic")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("page_pic最长255个字符");
					}
				}
				if (key.equals("background_color")) {
					if (data.get(key).toString().length() > 25) {
						return Common.error("background_color最长25个字符");
					}
				}
				if (key.equals("type")) {
					if (data.get(key).toString().length() > 25) {
						return Common.error("页面路径，例如：Index/Index/index最长25个字符");
					}
				}
				if (key.equals("html")) {
					if (data.get(key).toString().length() > 16777215) {
						return Common.error("页面缓存最长16777215个字符");
					}
				}
				if (key.equals("doms")) {
					if (data.get(key).toString().length() > 16777215) {
						return Common.error("json数据最长16777215个字符");
					}
				}
				if (key.equals("doms_sort")) {
					if (data.get(key).toString().length() > 16777215) {
						return Common.error("doms排序最长16777215个字符");
					}
				}
				if (key.equals("bottom_id")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("底部菜单id最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("is_list")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("是否是列表页最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("list_dom")) {
					if (data.get(key).toString().length() > 16777215) {
						return Common.error("list_dom 下拉加载部分的json数据最长16777215个字符");
					}
				}
				if (key.equals("list_html")) {
					if (data.get(key).toString().length() > 16777215) {
						return Common.error("下拉加载部分的页面缓存最长16777215个字符");
					}
				}
				if (key.equals("is_default")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("是否默认模版最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("addtime")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("创建时间最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("last_edit_time")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("最后修改时间最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("is_del")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("is_del最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				this.map.put(key, data.get(key));
			}
		}
		return Common.success(this.map);
	}

	public JSONObject check_edit(Map<String, Object> data) {
		Map<String, Object> new_data = new HashMap<>();
		for (String key : data.keySet()) {
			if (map.containsKey(key)) {
				if (key.equals("name")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("页面名称最长99个字符");
					}
				}
				if (key.equals("title")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("title最长99个字符");
					}
				}
				if (key.equals("keywords")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("keywords最长255个字符");
					}
				}
				if (key.equals("description")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("description最长255个字符");
					}
				}
				if (key.equals("page_pic")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("page_pic最长255个字符");
					}
				}
				if (key.equals("background_color")) {
					if (data.get(key).toString().length() > 25) {
						return Common.error("background_color最长25个字符");
					}
				}
				if (key.equals("type")) {
					if (data.get(key).toString().length() > 25) {
						return Common.error("页面路径，例如：Index/Index/index最长25个字符");
					}
				}
				if (key.equals("html")) {
					if (data.get(key).toString().length() > 16777215) {
						return Common.error("页面缓存最长16777215个字符");
					}
				}
				if (key.equals("doms")) {
					if (data.get(key).toString().length() > 16777215) {
						return Common.error("json数据最长16777215个字符");
					}
				}
				if (key.equals("doms_sort")) {
					if (data.get(key).toString().length() > 16777215) {
						return Common.error("doms排序最长16777215个字符");
					}
				}
				if (key.equals("bottom_id")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("底部菜单id最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("is_list")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("是否是列表页最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("list_dom")) {
					if (data.get(key).toString().length() > 16777215) {
						return Common.error("list_dom 下拉加载部分的json数据最长16777215个字符");
					}
				}
				if (key.equals("list_html")) {
					if (data.get(key).toString().length() > 16777215) {
						return Common.error("下拉加载部分的页面缓存最长16777215个字符");
					}
				}
				if (key.equals("is_default")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("是否默认模版最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("addtime")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("创建时间最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("last_edit_time")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("最后修改时间最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("is_del")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("is_del最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				new_data.put(key, data.get(key));
			}
		}
		return Common.success(new_data);
	}
}
