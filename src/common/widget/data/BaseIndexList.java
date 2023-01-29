package common.widget.data;

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
import com.javatodo.core.tools.Page;
import com.javatodo.core.tools.T;

import common.MU;
import common.MenuCache;
import common.database.CATEGORY;
import common.database.DETAIL;

public class BaseIndexList {

	public JSONObject parameter = new JSONObject(new LinkedHashMap<>());

	public BaseIndexList() {

	}

	// selfParameter：自身参数，也就是类中的parameter属性的值
	// webRequestParam：需要传入的其他参数，比如栏目的id或者商品的id 搜索的关键字等等
	// List<String> bind_loop_list：形如<"list:4",
	// "sub_list:6">的列表，其中"list"表示"参数名",后面的数字表示循环的次数，中间用":"隔开。
	public JSONObject getValue(JSONObject selfParameter, JSONObject webRequestParam, HttpServletRequest request, List<String> bind_loop_list) throws SQLException {
		// JSONArray list = new JSONArray();
		Integer p = 1;
		if (webRequestParam.containsKey("p")) {
			p = webRequestParam.getInteger("p");
		}
		if (p == 0) {
			p = 1;
		}
		String category_id = webRequestParam.getString("id");
		Map<String, W> where = new HashMap<>();
		// 栏目内容
		where.put(CATEGORY.is_del, new W("eq", 0));
		where.put(CATEGORY.id, new W("eq", category_id));
		Map<String, Object> category = new MU(CATEGORY._table_name).where(where).find();
		if (category == null) {
			return null;
		}

		// 信息内容
		where.clear();
		where.put(DETAIL.is_del, new W("eq", 0));
		List<String> category_ids = new ArrayList<String>();
		Object websiteId = request.getSession().getAttribute("website_id");
		JSONArray all_category_list = MenuCache.getAdminMenuList(websiteId.toString());
		Integer pointer_category_level = 0;
		Integer open = 0;// 是否装载的开关
		for (Integer i = 0; i < all_category_list.size(); i = i + 1) {
			String cur_category_id = all_category_list.getJSONObject(i).getString("id");
			Integer cur_level = all_category_list.getJSONObject(i).getInteger("level");
			if (cur_category_id.equals(category_id)) {
				pointer_category_level = cur_level;
				open = 1;
				category_ids.add(cur_category_id);
				continue;
			}
			if (open == 1) {// 说明开关处于开启状态
				if (cur_level > pointer_category_level) {// 说明当前栏目是目标栏目的子栏目
					category_ids.add(cur_category_id);
				} else {// 说明当前栏目不是目标栏目的子栏目
					open = 0;
					break;
				}
			}
		}
		where.put(DETAIL.category_id, new W("in", category_ids));
		Long count = new MU(DETAIL._table_name).where(where).count();
		Map<String, String> map = new HashMap<>();
		Map<String, String[]> queryMap = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : queryMap.entrySet()) {
			String[] value_arr = entry.getValue();
			if (value_arr == null) {
				continue;
			} else {
				if (value_arr.length == 0) {
					continue;
				}
			}
			map.put(entry.getKey(), value_arr[0]);
		}
		map.put("p", p.toString());
		Integer page_size = 100;
		if (bind_loop_list != null && bind_loop_list.size() > 0) {
			for (Integer i = 0; i < bind_loop_list.size(); i = i + 1) {
				String bind_loop = bind_loop_list.get(i);
				String[] arr = bind_loop.split(":");
				if (arr[0].trim().equals("list")) {
					page_size = T.toInt(arr[1] + "");
					break;
				}
			}
		}
		if (webRequestParam.containsKey("page_size")) {
			page_size = webRequestParam.getInteger("page_size");
		}
		Page page = new Page(count, page_size, "index.jsp", map);
		List<Map<String, Object>> list = new MU(DETAIL._table_name).where(where).limit(page.firstRow + "," + page.listRows).order(category.get(CATEGORY.order_by).toString()).select();
		for (Integer i = 0; i < list.size(); i = i + 1) {
			Long release_time = Long.valueOf(list.get(i).get(DETAIL.release_time).toString());
			list.get(i).put(DETAIL.release_time, T.date("yyyy-MM-dd HH:mm:ss", release_time));
			list.get(i).put("release_time_year", T.date("yyyy", release_time));
			list.get(i).put("release_time_month", T.date("MM", release_time));
			list.get(i).put("release_time_day", T.date("dd", release_time));
			list.get(i).put("release_time_hour", T.date("HH", release_time));
			list.get(i).put("release_time_minute", T.date("mm", release_time));
			list.get(i).put("release_time_second", T.date("ss", release_time));
			list.get(i).remove(DETAIL.detail);
			if (list.get(i).get(DETAIL.url).toString().trim().equals("")) {
				String url = "./index.jsp?m=Index&c=Index&a=detail&id=" + list.get(i).get(DETAIL.id);
				list.get(i).put(DETAIL.url, url);
			}
		}
		JSONObject object = new JSONObject();
		object.put("category", category);
		object.put(CATEGORY.id, category.get(CATEGORY.id).toString());
		object.put(CATEGORY.category_name, category.get(CATEGORY.category_name).toString());
		object.put(CATEGORY.category_sub_name, category.get(CATEGORY.category_sub_name).toString());
		object.put(CATEGORY.detail, T.htmlspecialchars_decode(category.get(CATEGORY.detail).toString()));
		object.put(CATEGORY.pic, category.get(CATEGORY.pic).toString());
		object.put(CATEGORY.pid, category.get(CATEGORY.pid).toString());
		object.put(CATEGORY.smalltext, category.get(CATEGORY.smalltext).toString());
		object.put("list", list);
		object.put("page", page.show());
		object.put("bread", HomeIndexBread.getBread(category_id, category.get(CATEGORY.category_name).toString().trim(), category.get(CATEGORY.pid).toString().trim(), "-&gt"));
		object.put("left_menus", BaseIndexList.getLeftMenus(category));
		// System.out.println(object);
		return object;
	}

	// 获取页面左侧的栏目：如果栏目的上级是0，那么显示该栏目和该栏目的子栏目，如果上级不是0，那么显示该栏目的上级和所有平级栏目
	public static List<Map<String, Object>> getLeftMenus(Map<String, Object> category) throws SQLException {
		List<Map<String, Object>> list = new ArrayList();
		String pid = category.get(CATEGORY.pid).toString();
		List<Map<String, Object>> _list = new ArrayList<>();
		if (!pid.equals("0")) {
			// 获取该栏目的父栏目
			Map<String, W> where = new HashMap<>();
			where.put(CATEGORY.id, new W("eq", category.get(CATEGORY.pid).toString()));
			where.put(CATEGORY.is_del, new W("eq", 0));
			where.put(CATEGORY.is_hidden, new W("eq", 0));
			Map<String, Object> pid_category = new MU(CATEGORY._table_name).where(where).find();
			if (pid_category != null) {
				category = pid_category;
			}
		}

		if (category.get(CATEGORY.url).toString().trim().equals("")) {
			String url = "./index.jsp?m=Index&c=Index&a=category&id=" + category.get(CATEGORY.id).toString().trim();
			category.put(CATEGORY.url, url);
		} else {
			String url = category.get(CATEGORY.url).toString();
			url = T.htmlspecialchars_decode(url);
			category.put(CATEGORY.url, url);
		}
		list.add(category);
		// 获取该栏目的所有子栏目
		Map<String, W> where = new HashMap<>();
		where.put(CATEGORY.pid, new W("eq", category.get(CATEGORY.id).toString()));
		where.put(CATEGORY.is_del, new W("eq", 0));
		where.put(CATEGORY.is_hidden, new W("eq", 0));
		_list = new MU(CATEGORY._table_name).where(where).order(CATEGORY.sort + " asc").select();
		for (Integer i = 0; i < _list.size(); i = i + 1) {
			if (_list.get(i).get(CATEGORY.url).toString().trim().equals("")) {
				String url = "./index.jsp?m=Index&c=Index&a=category&id=" + _list.get(i).get(CATEGORY.id).toString().trim();
				_list.get(i).put(CATEGORY.url, url);
				list.add(_list.get(i));
			} else {
				String url = _list.get(i).get(CATEGORY.url).toString().trim();
				url = T.htmlspecialchars_decode(url);
				_list.get(i).put(CATEGORY.url, url);
				list.add(_list.get(i));
			}
		}
		return list;
	}
}
