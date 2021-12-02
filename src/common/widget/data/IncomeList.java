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
package common.widget.data;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.Page;
import com.javatodo.core.tools.T;

import common.MU;
import common.database.MEMBER;
import common.database.UNION_ORDER;

public class IncomeList {
	public JSONObject parameter = new JSONObject(new LinkedHashMap<>());

	public IncomeList() {

	}

	// selfParameter：自身参数，也就是类中的parameter属性的值
	// webRequestParam：需要传入的其他参数，比如栏目的id或者商品的id 搜索的关键字等等
	public JSONObject getValue(JSONObject selfParameter, JSONObject webRequestParam) throws SQLException {
		JSONArray income_list = new JSONArray();
		Integer p = 1;
		if (webRequestParam.containsKey("p")) {
			p = webRequestParam.getInteger("p");
		}
		String uid = "";
		if (webRequestParam.containsKey("uid")) {
			uid = webRequestParam.getString("uid");
		}
		if (uid.equals("") || uid.equals("0")) {
			return null;
		}
		String status = "0";
		if (webRequestParam.containsKey("status")) {
			status = webRequestParam.getString("status");
			if (T.toInt(status) < -1 || T.toInt(status) > 1) {
				status = "0";
			}
		}

		String team_type = "1";
		if (webRequestParam.containsKey("team_type")) {
			team_type = webRequestParam.getString("team_type");
			if (T.toInt(team_type) < 1 || T.toInt(team_type) > 3) {
				team_type = "1";
			}
		}

		Map<String, W> where = new HashMap<>();
		where.put("uo." + UNION_ORDER.status, new W("eq", status));
		if (team_type.equals("1")) {
			where.put("uo." + UNION_ORDER.level_1_id, new W("eq", uid));
		}
		if (team_type.equals("2")) {
			where.put("uo." + UNION_ORDER.level_2_id, new W("eq", uid));
		}
		if (team_type.equals("3")) {
			where.put("uo." + UNION_ORDER.level_3_id, new W("eq", uid));
		}

		Long count = new MU(UNION_ORDER._table_name).alias("uo").where(where).count();
		Map<String, String> map = new HashMap<>();
		map.put("p", p.toString());
		Page page = new Page(count, 16, "index.jsp", map);
		List<Map<String, Object>> list = new MU(UNION_ORDER._table_name).alias("uo").join(MEMBER._table_name, "as m on m." + MEMBER.id + "=uo." + UNION_ORDER.uid, "left").where(where).limit(page.firstRow + "," + page.listRows).order(UNION_ORDER.id + " desc").field("uo.*, m.username as team_username").select();
		for (Integer i = 0; i < list.size(); i = i + 1) {
			String income = "0";
			if (team_type.equals("1")) {
				income = list.get(i).get(UNION_ORDER.level_1_money).toString();
			}
			if (team_type.equals("2")) {
				income = list.get(i).get(UNION_ORDER.level_2_money).toString();
			}
			if (team_type.equals("3")) {
				income = list.get(i).get(UNION_ORDER.level_3_money).toString();
			}
			if (list.get(i).get("status").toString().equals("0")) {
				list.get(i).put("status", "即将收益");
			}
			if (list.get(i).get("status").toString().equals("1")) {
				list.get(i).put("status", "已收益");
			}
			if (list.get(i).get("status").toString().equals("-1")) {
				list.get(i).put("status", "已失效");
			}
			list.get(i).put("income", income);
		}
		JSONObject object = new JSONObject();
		object.put("income_list", list);
		return object;
	}
}
