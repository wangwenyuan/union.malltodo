package common.widget.data;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.T;

import common.Common;
import common.MU;
import common.database.DETAIL;

public class BaseIndex {
	protected String type = "";
	public JSONObject parameter = new JSONObject(new LinkedHashMap<>());

	public BaseIndex() {
		Map<String, String> is_pic = new LinkedHashMap<String, String>();
		is_pic.put("_title", "只调含标题图片的信息");
		is_pic.put("0", "否");
		is_pic.put("1", "是");
		parameter.put("is_pic", is_pic);

		Map<String, String> recommend_level = new LinkedHashMap<String, String>();
		recommend_level.put("_title", "调用的信息的推荐等级");
		for (String key : Common.detail_recommend_level_json.keySet()) {
			recommend_level.put(key, Common.detail_recommend_level_json.getString(key));
		}
		parameter.put("recommend_level", recommend_level);
	}

	// selfParameter：自身参数，也就是类中的parameter属性的值
	// webRequestParam：需要传入的其他参数，比如栏目的id或者商品的id 搜索的关键字等等
	// List<String> bind_loop_list：形如<"list:4",
	// "sub_list:6">的列表，其中"list"表示"参数名",后面的数字表示循环的次数，中间用":"隔开。
	public JSONObject getValue(JSONObject selfParameter, JSONObject webRequestParam, HttpServletRequest request, List<String> bind_loop_list) throws SQLException {
		if (selfParameter == null) {
			return null;
		}

		Map<String, W> where = new HashMap<>();
		where.put(DETAIL.is_del, new W("eq", 0));
		where.put(DETAIL.type, new W("eq", type));
		String is_pic = selfParameter.getString("is_pic");
		if (is_pic == null) {
			return null;
		}
		if (!is_pic.equals("0")) {
			where.put(DETAIL.pic, new W("neq", ""));
		}
		String recommend_level = selfParameter.getString("recommend_level");
		if (recommend_level == null) {
			return null;
		}
		if (!recommend_level.equals("0")) {
			where.put(DETAIL.recommend_level, new W("eq", recommend_level));
		}
		String page_size = "100";
		if (bind_loop_list != null && bind_loop_list.size() > 0) {
			for (Integer i = 0; i < bind_loop_list.size(); i = i + 1) {
				String bind_loop = bind_loop_list.get(i);
				String[] arr = bind_loop.split(":");
				if (arr[0].trim().equals("list")) {
					page_size = arr[1] + "";
					break;
				}
			}
		}

		List<Map<String, Object>> list = new MU(DETAIL._table_name).where(where).limit(page_size).order(DETAIL.id + " desc").select();
		for (Integer i = 0; i < list.size(); i = i + 1) {
			list.get(i).remove(DETAIL.detail);
			Long release_time = Long.valueOf(list.get(i).get(DETAIL.release_time).toString());
			list.get(i).put(DETAIL.release_time, T.date("yyyy-MM-dd HH:mm:ss", release_time));
			list.get(i).put("release_time_year", T.date("yyyy", release_time));
			list.get(i).put("release_time_month", T.date("MM", release_time));
			list.get(i).put("release_time_day", T.date("dd", release_time));
			list.get(i).put("release_time_hour", T.date("HH", release_time));
			list.get(i).put("release_time_minute", T.date("mm", release_time));
			list.get(i).put("release_time_second", T.date("ss", release_time));
			if (list.get(i).get(DETAIL.url).toString().trim().equals("")) {
				String url = "./index.jsp?m=Index&c=Index&a=detail&id=" + list.get(i).get(DETAIL.id);
				list.get(i).put(DETAIL.url, url);
			}
		}
		JSONObject object = new JSONObject();
		object.put("list", list);
		return object;
	}
}
