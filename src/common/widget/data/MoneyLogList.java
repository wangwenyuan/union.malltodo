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
import common.database.MONEY_LOG;

public class MoneyLogList {
	public JSONObject parameter = new JSONObject(new LinkedHashMap<>());

	public MoneyLogList() {

	}

	// selfParameter：自身参数，也就是类中的parameter属性的值
	// webRequestParam：需要传入的其他参数，比如栏目的id或者商品的id 搜索的关键字等等
	public JSONObject getValue(JSONObject selfParameter, JSONObject webRequestParam) throws SQLException {
		JSONArray money_log_list = new JSONArray();
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
		Map<String, W> where = new HashMap<>();
		where.put(MONEY_LOG.uid, new W("eq", uid));
		where.put(MONEY_LOG.status, new W("gt", -1));

		Long count = new MU(MONEY_LOG._table_name).where(where).count();
		Map<String, String> map = new HashMap<>();
		map.put("p", p.toString());
		Page page = new Page(count, 16, "index.jsp", map);
		List<Map<String, Object>> list = new MU(MONEY_LOG._table_name).where(where).limit(page.firstRow + "," + page.listRows).order(MONEY_LOG.id + " desc").select();
		for (Integer i = 0; i < list.size(); i = i + 1) {
			list.get(i).put(MONEY_LOG.addtime, T.date("yyyy-MM-dd HH:mm:ss", Long.valueOf(list.get(i).get(MONEY_LOG.addtime).toString())));
		}
		JSONObject object = new JSONObject();
		object.put("money_log_list", list);
		return object;
	}
}
