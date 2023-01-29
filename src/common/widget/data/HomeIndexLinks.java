package common.widget.data;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.model.W;

import common.Common;
import common.MU;
import common.database.LINKS;

public class HomeIndexLinks {
	protected String type = "";
	public JSONObject parameter = new JSONObject(new LinkedHashMap<>());

	public HomeIndexLinks() {
		Map<String, String> is_pic = new LinkedHashMap<String, String>();
		is_pic.put("_title", "只调含展示图片的链接");
		is_pic.put("0", "否");
		is_pic.put("1", "是");
		parameter.put("is_pic", is_pic);

		Map<String, String> recommend_level = new LinkedHashMap<String, String>();
		recommend_level.put("_title", "调用的链接的推荐等级");
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
		Map<String, W> where = new HashMap<>();
		where.put(LINKS.is_del, new W("eq", 0));
		String is_pic = selfParameter.getString("is_pic");
		if (!is_pic.equals("0")) {
			where.put(LINKS.pic, new W("neq", ""));
		}
		String recommend_level = selfParameter.getString("recommend_level");
		if (!recommend_level.equals("0")) {
			where.put(LINKS.recommend_level, new W("eq", recommend_level));
		}
		String page_size = "100";
		if (bind_loop_list != null && bind_loop_list.size() > 0) {
			String bind_loop = bind_loop_list.get(0);
			String[] arr = bind_loop.split(":");
			page_size = arr[1] + "";
		}
		List<Map<String, Object>> list = new MU(LINKS._table_name).where(where).limit(page_size).order(LINKS.id + " desc").select();
		JSONObject object = new JSONObject();
		object.put("list", list);
		return object;
	}
}
