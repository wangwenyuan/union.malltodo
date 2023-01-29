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
import com.javatodo.core.tools.T;

import common.MU;
import common.MenuCache;
import common.database.CATEGORY;
import common.database.DETAIL;

public class BaseIndexDetail {
	public JSONObject parameter = new JSONObject(new LinkedHashMap<>());

	public BaseIndexDetail() {

	}

	// selfParameter：自身参数，也就是类中的parameter属性的值
	// webRequestParam：需要传入的其他参数，比如栏目的id或者商品的id 搜索的关键字等等
	// List<String> bind_loop_list：形如<"list:4",
	// "sub_list:6">的列表，其中"list"表示"参数名",后面的数字表示循环的次数，中间用":"隔开。
	public JSONObject getValue(JSONObject selfParameter, JSONObject webRequestParam, HttpServletRequest request, List<String> bind_loop_list) throws SQLException {
		String id = webRequestParam.getString("id");
		Map<String, W> where = new HashMap<>();
		where.put(DETAIL.id, new W("eq", id));
		where.put(DETAIL.is_del, new W("eq", 0));
		Map<String, Object> detail = new MU(DETAIL._table_name).where(where).find();
		if (detail == null) {
			return null;
		}

		where.clear();
		String category_id = detail.get(DETAIL.category_id).toString();
		// 栏目内容
		where.put(CATEGORY.is_del, new W("eq", 0));
		where.put(CATEGORY.id, new W("eq", category_id));
		Map<String, Object> category = new MU(CATEGORY._table_name).where(where).find();
		if (category == null) {
			return null;
		}

		JSONObject object = new JSONObject();
		// 文章信息
		object.put("info", detail);
		object.put(DETAIL.category_id, detail.get(DETAIL.category_id));
		object.put(DETAIL.detail, T.htmlspecialchars_decode(detail.get(DETAIL.detail).toString()));
		object.put(DETAIL.pic, detail.get(DETAIL.pic));
		long release_time = Long.valueOf(detail.get(DETAIL.release_time).toString());
		object.put(DETAIL.release_time, T.date("yyyy-MM-dd HH:mm:ss", release_time));
		object.put(DETAIL.smalltext, detail.get(DETAIL.smalltext));
		object.put(DETAIL.title, detail.get(DETAIL.title));
		object.put(DETAIL.views, detail.get(DETAIL.views));

		Object websiteId = request.getSession().getAttribute("website_id");

		object.put("pre", this.getPre(detail, category, websiteId.toString()));
		object.put("next", this.getNext(detail, category, websiteId.toString()));

		object.put("category", category);
		object.put(CATEGORY.category_name, category.get(CATEGORY.category_name).toString());
		object.put(CATEGORY.category_sub_name, category.get(CATEGORY.category_sub_name).toString());
		object.put("category_detail", category.get(CATEGORY.detail).toString());
		object.put("category_pic", category.get(CATEGORY.pic).toString());
		object.put("category_pid", category.get(CATEGORY.pid).toString());
		object.put("category_smalltext", category.get(CATEGORY.smalltext).toString());
		object.put("left_menus", BaseIndexList.getLeftMenus(category));
		return object;
	}

	private String getPre(Map<String, Object> detail, Map<String, Object> category, String websiteId) throws SQLException {
		String detail_id = detail.get(DETAIL.id).toString();
		String category_id = category.get(CATEGORY.id).toString();
		long release_time = Long.valueOf(detail.get(DETAIL.release_time).toString());
		Map<String, W> where = new HashMap<>();
		where.put(DETAIL.is_del, new W("eq", 0));
		List<String> category_ids = new ArrayList<String>();
		JSONArray all_category_list = MenuCache.getAdminMenuList(websiteId);
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

		String order_by = category.get(CATEGORY.order_by).toString();
		if (order_by.equals("id desc")) {
			where.put(DETAIL.id, new W("gt", detail_id));
		} else if (order_by.equals("id asc")) {
			where.put(DETAIL.id, new W("lt", detail_id));
		} else if (order_by.equals("release_time desc")) {
			where.put(DETAIL.release_time, new W("gt", release_time));
		} else if (order_by.equals("release_time asc")) {
			where.put(DETAIL.release_time, new W("lt", release_time));
		}
		Map<String, Object> map = new MU(DETAIL._table_name).where(where).order(order_by).find();
		if (map == null) {
			return "<a target='_blank' href='index.jsp?m=Index&c=Index&a=category&id=" + category_id + "'>返回列表</a>";
		} else {
			return "<a target='_blank' href='index.jsp?m=Index&c=Index&a=detail&id=" + map.get(DETAIL.id).toString() + "'>" + map.get(DETAIL.title) + "</a>";
		}
	}

	private String getNext(Map<String, Object> detail, Map<String, Object> category, String websiteId) throws SQLException {
		String detail_id = detail.get(DETAIL.id).toString();
		String category_id = category.get(CATEGORY.id).toString();
		long release_time = Long.valueOf(detail.get(DETAIL.release_time).toString());
		Map<String, W> where = new HashMap<>();
		where.put(DETAIL.is_del, new W("eq", 0));
		List<String> category_ids = new ArrayList<String>();
		JSONArray all_category_list = MenuCache.getAdminMenuList(websiteId);
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

		String order_by = category.get(CATEGORY.order_by).toString();
		if (order_by.equals("id desc")) {
			where.put(DETAIL.id, new W("lt", detail_id));
		} else if (order_by.equals("id asc")) {
			where.put(DETAIL.id, new W("gt", detail_id));
		} else if (order_by.equals("release_time desc")) {
			where.put(DETAIL.release_time, new W("lt", release_time));
		} else if (order_by.equals("release_time asc")) {
			where.put(DETAIL.release_time, new W("gt", release_time));
		}
		Map<String, Object> map = new MU(DETAIL._table_name).where(where).order(order_by).find();
		if (map == null) {
			return "<a target='_blank' href='index.jsp?m=Index&c=Index&a=category&id=" + category_id + "'>返回列表</a>";
		} else {
			return "<a target='_blank' href='index.jsp?m=Index&c=Index&a=detail&id=" + map.get(DETAIL.id).toString() + "'>" + map.get(DETAIL.title) + "</a>";
		}
	}
}
