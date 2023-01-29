package common;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.T;

import common.database.CATEGORY;
import common.database.MENU_CACHE;

public class MenuCache {

	public static void init(String websiteId) throws SQLException {// 初始化系统栏目
		Map<String, W> where = new HashMap<>();
		where.put(CATEGORY.website_id, new W("eq", websiteId));
		Long count = new MU(CATEGORY._table_name).where(where).count();
		if (count == 0) {
			Map<String, Object> data = new HashMap<>();
			for (String key : Common.home_menu.keySet()) {
				data.clear();
				data.put(CATEGORY.category_name, Common.home_menu.get(key));
				data.put(CATEGORY.type, key);
				data.put(CATEGORY.pid, 0);
				data.put(CATEGORY.website_id, websiteId);
				new MU(CATEGORY._table_name).data(data).add();
			}
		}
	}

	public static String create(String websiteId) throws SQLException {
		init(websiteId);
		JSONArray list = new JSONArray();
		JSONObject Top = new JSONObject();
		Top.put("id", 0);
		Top.put("pId", -1);
		Top.put("name", "网站栏目（下方栏目可拖拽变更排序）--0");
		Top.put("open", true);
		Top.put("drag", true);
		Top.put("childOuter", false);
		Top.put("type", "Index/Category/index");
		Top.put("is_hidden", 0);
		Top.put("url", "");
		list.add(Top);

		Map<String, W> where = new HashMap<>();
		where.put(CATEGORY.is_del, new W("eq", 0));
		where.put(CATEGORY.website_id, new W("eq", websiteId));
		List<Map<String, Object>> menu_list = new MU(CATEGORY._table_name).where(where).order(CATEGORY.sort + " asc, " + CATEGORY.id + " asc").select();
		for (Integer i = 0; i < menu_list.size(); i = i + 1) {
			JSONObject object = new JSONObject();
			object.put("id", menu_list.get(i).get(CATEGORY.id).toString());
			object.put("pId", menu_list.get(i).get(CATEGORY.pid).toString());
			object.put("name", menu_list.get(i).get(CATEGORY.category_name).toString() + "--" + menu_list.get(i).get(CATEGORY.id).toString());
			object.put("open", true);
			object.put("drag", true);
			object.put("childOuter", false);
			object.put("type", menu_list.get(i).get(CATEGORY.type).toString());
			object.put("is_hidden", T.toInt(menu_list.get(i).get(CATEGORY.is_hidden).toString()));
			object.put("url", T.toInt(menu_list.get(i).get(CATEGORY.url).toString()));
			list.add(object);
		}
		String json = list.toJSONString();
		Map<String, Object> save_data = new HashMap<String, Object>();
		save_data.put(MENU_CACHE.menu, json);
		save_data.put(MENU_CACHE.website_id, websiteId);
		Map<String, W> menu_cache_where = new HashMap<>();
		menu_cache_where.put(MENU_CACHE.website_id, new W("eq", websiteId));
		Map<String, Object> info = new MU(MENU_CACHE._table_name).where(menu_cache_where).order(MENU_CACHE.id + " desc").find();
		if (info == null) {
			new MU(MENU_CACHE._table_name).data(save_data).add();
		} else {
			Map<String, W> cache_where = new HashMap<>();
			cache_where.put(MENU_CACHE.id, new W("eq", T.toInt(info.get(MENU_CACHE.id).toString())));
			new MU(MENU_CACHE._table_name).where(cache_where).save(save_data);
		}
		return json;
	}

	public static String get(String websiteId) throws SQLException {
		Map<String, W> where = new HashMap<>();
		where.put(MENU_CACHE.website_id, new W("eq", websiteId));
		Map<String, Object> info = new MU(MENU_CACHE._table_name).where(where).order(MENU_CACHE.id + " desc").find();
		if (info == null) {
			return create(websiteId);
		} else {
			return info.get(MENU_CACHE.menu).toString();
		}
	}

	private static Map<String, JSONArray> MapMenuList = null;// 前台显示的栏目

	private static Map<String, JSONArray> map_admin_menu_list = null;// 后台显示的栏目

	synchronized public static void clean(String websiteId) {
		if (MapMenuList != null && MapMenuList.containsKey(websiteId)) {
			MapMenuList.remove(websiteId);
		}
		if (map_admin_menu_list != null && map_admin_menu_list.containsKey(websiteId)) {
			map_admin_menu_list.remove(websiteId);
		}
	}

	synchronized public static JSONArray getMenuList(String websiteId) throws SQLException {
		if (MapMenuList == null) {
			map_admin_menu_list = null;
			MapMenuList = new HashMap<>();
		}
		if (!MapMenuList.containsKey(websiteId)) {
			JSONArray MenuList = BuildMenuList(websiteId, "0");
			MapMenuList.put(websiteId, MenuList);
		}
		return MapMenuList.get(websiteId);
	}

	synchronized public static JSONArray getAdminMenuList(String websiteId) throws SQLException {
		if (map_admin_menu_list == null) {
			map_admin_menu_list = new HashMap<>();
		}
		if (!map_admin_menu_list.containsKey(websiteId)) {
			BuildAdminMenuList(websiteId, "0", 0);
		}
		return map_admin_menu_list.get(websiteId);
	}

	private static JSONArray BuildMenuList(String websiteId, String pid) throws SQLException {
		init(websiteId);
		Map<String, W> where = new HashMap<>();
		where.put(CATEGORY.is_del, new W("eq", 0));
		where.put(CATEGORY.is_hidden, new W("eq", 0));
		where.put(CATEGORY.pid, new W("eq", pid));
		where.put(CATEGORY.website_id, new W("eq", websiteId));
		List<Map<String, Object>> _menu_list = new MU(CATEGORY._table_name).where(where).order(CATEGORY.sort + " asc, " + CATEGORY.id + " asc").select();
		JSONArray menu_list = JSONArray.parseArray(JSON.toJSONString(_menu_list));
		for (Integer i = 0; i < menu_list.size(); i = i + 1) {
			JSONObject object = menu_list.getJSONObject(i);
			String url = object.getString(CATEGORY.url).trim();
			if (url.equals("")) {
				if (object.getString(CATEGORY.type).equals("Index/Index/index")) {
					url = T.U("Index/Index/index", "index.jsp");
				} else {
					url = T.U("Index/Index/category", "id=" + object.getString(CATEGORY.id).toString().trim(), "index.jsp");
				}
			} else {
				url = T.htmlspecialchars_decode(url);
			}
			object.put(CATEGORY.url, url);
			object.put("sub_menu", BuildMenuList(websiteId, object.getString(CATEGORY.id).toString().trim()));
		}
		return menu_list;
	}

	private static JSONArray BuildAdminMenuList(String websiteId, String pid, Integer level) throws SQLException {
		init(websiteId);
		Map<String, W> where = new HashMap<>();
		where.put(CATEGORY.is_del, new W("eq", 0));
		where.put(CATEGORY.pid, new W("eq", pid));
		where.put(CATEGORY.website_id, new W("eq", websiteId));
		List<Map<String, Object>> _menu_list = new MU(CATEGORY._table_name).where(where).order(CATEGORY.sort + " asc, " + CATEGORY.id + " asc").select();
		JSONArray menu_list = JSONArray.parseArray(JSON.toJSONString(_menu_list));
		if (map_admin_menu_list == null) {
			map_admin_menu_list = new HashMap<>();
		}
		if (!map_admin_menu_list.containsKey(websiteId)) {
			map_admin_menu_list.put(websiteId, new JSONArray());
		}
		for (Integer i = 0; i < menu_list.size(); i = i + 1) {
			JSONObject object = menu_list.getJSONObject(i);
			String url = object.getString(CATEGORY.url).trim();
			if (url.equals("")) {
				if (!object.getString(CATEGORY.type).toString().trim().equals("Index/Category/index")) {
					if (object.getString(CATEGORY.pid).toString().trim().equals("0")) {
						url = T.U(object.getString(CATEGORY.type).toString().trim(), "index.jsp");
					} else {
						url = T.U(object.getString(CATEGORY.type).toString().trim(), "id=" + object.getString(CATEGORY.id).toString(), "index.jsp");
					}
				} else {
					url = T.U("Index/Category/index", "id=" + object.getString(CATEGORY.id).toString().trim(), "index.jsp");
				}
			}
			object.put(CATEGORY.url, url);
			object.put("level", level);
			map_admin_menu_list.get(websiteId).add(object);
			object.put("sub_menu", BuildAdminMenuList(websiteId, object.getString(CATEGORY.id).toString().trim(), level + 1));
		}
		return menu_list;
	}
}
