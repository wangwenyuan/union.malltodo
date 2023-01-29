package common.widget.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.model.W;

import common.MU;
import common.database.CATEGORY;
import common.database.DETAIL;

public class HomeIndexBread {
	public JSONObject parameter = new JSONObject(new LinkedHashMap<>());

	public HomeIndexBread() {

	}

	// selfParameter：自身参数，也就是类中的parameter属性的值
	// webRequestParam：需要传入的其他参数，比如栏目的id或者商品的id 搜索的关键字等等
	// List<String> bind_loop_list：形如<"list:4",
	// "sub_list:6">的列表，其中"list"表示"参数名",后面的数字表示循环的次数，中间用":"隔开。
	public JSONObject getValue(JSONObject selfParameter, JSONObject webRequestParam, HttpServletRequest request, List<String> bind_loop_list) throws SQLException {
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
		if (!map.containsKey("a")) {
			return null;
		}
		String category_id = "";
		if (map.get("a").trim().equals("category")) {// 说明是栏目
			category_id = webRequestParam.getString("id");
		} else if (map.get("a").trim().equals("detail")) {// 说明是内容
			String detail_id = webRequestParam.getString("id");
			Map<String, W> _where = new HashMap<>();
			_where.put(DETAIL.id, new W("eq", detail_id));
			_where.put(DETAIL.is_del, new W("eq", 0));
			Map<String, Object> detail = new MU(DETAIL._table_name).where(_where).find();
			if (detail == null) {
				return null;
			}
			category_id = detail.get(DETAIL.category_id).toString();
		}

		Map<String, W> where = new HashMap<>();
		where.put(CATEGORY.id, new W("eq", category_id));
		where.put(CATEGORY.is_del, new W("eq", 0));
		Map<String, Object> category = new MU(CATEGORY._table_name).where(where).find();
		if (category == null) {
			return null;
		}
		JSONObject object = new JSONObject();
		object.put("category", category);
		object.put("url", "./index.jsp?m=Index&c=Index&a=category&id=" + category_id);
		object.put("category_name", category.get(CATEGORY.category_name).toString());
		object.put("category_sub_name", category.get(CATEGORY.category_sub_name).toString());
		object.put("bread", getBread(category_id, category.get(CATEGORY.category_name).toString().trim(), category.get(CATEGORY.pid).toString().trim(), "-&gt"));
		return object;
	}

	public static String getBread(String category_id, String separator) throws SQLException {
		Map<String, W> where = new HashMap<>();
		where.put(CATEGORY.id, new W("eq", category_id));
		where.put(CATEGORY.is_del, new W("eq", 0));
		Map<String, Object> category = new MU(CATEGORY._table_name).where(where).find();
		return getBread(category_id, category.get(CATEGORY.category_name).toString(), category.get(CATEGORY.pid).toString(), separator);
	}

	public static String getBread(String category_id, String category_name, String category_pid, String separator) throws SQLException {
		return "<a href='./index.jsp' target='_blank'>首页</a>" + new HomeIndexBread()._getBread(category_id, category_name, category_pid, separator);
	}

	private List<String> _category_ids = new ArrayList<>();

	private String _getBread(String category_id, String category_name, String category_pid, String separator) throws SQLException {
		if (_category_ids.contains(category_id)) {
			return "";
		} else {
			_category_ids.add(category_id);
		}
		String html = "<a href='./index.jsp?m=Index&c=Index&a=category&id=" + category_id + "' target='_blank'>" + category_name + "</a>";
		if (separator.equals("")) {
			separator = "-&gt";
		}
		if (category_pid.equals("0")) {
			return separator + html;
		} else {
			Map<String, W> where = new HashMap<>();
			where.put(CATEGORY.id, new W("eq", category_pid));
			where.put(CATEGORY.is_del, new W("eq", 0));
			Map<String, Object> category = new MU(CATEGORY._table_name).where(where).find();
			if (category == null) {
				return "";
			} else {
				category_id = category.get(CATEGORY.id).toString().trim();
				category_name = category.get(CATEGORY.category_name).toString().trim();
				category_pid = category.get(CATEGORY.pid).toString().trim();
				return _getBread(category_id, category_name, category_pid, separator);
			}
		}
	}
}
