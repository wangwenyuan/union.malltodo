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

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.Page;
import com.javatodo.core.tools.T;

import common.MU;
import common.database.UNION_ORDER;
import common.database.UNION_ORDER_GOODS;
import model.Platform;

public class UnionOrderList {
	public JSONObject parameter = new JSONObject(new LinkedHashMap<>());

	public UnionOrderList() {

	}

	// selfParameter：自身参数，也就是类中的parameter属性的值
	// webRequestParam：需要传入的其他参数，比如栏目的id或者商品的id 搜索的关键字等等
	public JSONObject getValue(JSONObject selfParameter, JSONObject webRequestParam, HttpServletRequest request) throws SQLException {
		JSONArray order_list = new JSONArray();
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

		Map<String, W> where = new HashMap<>();
		where.put(UNION_ORDER.status, new W("eq", status));
		where.put(UNION_ORDER.uid, new W("eq", uid));

		Long count = new MU(UNION_ORDER._table_name).where(where).count();
		Map<String, String> map = new HashMap<>();
		map.put("p", p.toString());
		Page page = new Page(count, 16, "index.jsp", map);
		List<Map<String, Object>> list = new MU(UNION_ORDER._table_name).where(where).limit(page.firstRow + "," + page.listRows).order(UNION_ORDER.id + " desc").select();
		for (Integer i = 0; i < list.size(); i = i + 1) {
			if (list.get(i).get(UNION_ORDER.status).toString().equals("0")) {
				list.get(i).put(UNION_ORDER.status, "未分佣");
			}
			if (list.get(i).get(UNION_ORDER.status).toString().equals("1")) {
				list.get(i).put(UNION_ORDER.status, "已分佣");
			}
			if (list.get(i).get(UNION_ORDER.status).toString().equals("-1")) {
				list.get(i).put(UNION_ORDER.status, "已失效");
			}
			Map<String, W> goods_where = new HashMap<>();
			goods_where.put(UNION_ORDER_GOODS.order_sn, new W("eq", list.get(i).get(UNION_ORDER.order_sn).toString()));
			goods_where.put(UNION_ORDER_GOODS.status, new W("eq", 1));
			List<Map<String, Object>> goods_list = new MU(UNION_ORDER_GOODS._table_name).where(goods_where).select();
			if (list.get(i).get(UNION_ORDER.type).toString().equals("meituan")) {
				for (Integer n = 0; n < goods_list.size(); n = n + 1) {
					goods_list.get(n).put(UNION_ORDER_GOODS.goods_pic, "./Public/images/meituan.png");
				}
			}
			list.get(i).put("goods_list", goods_list);
			list.get(i).put("platform", Platform.map.get(list.get(i).get(UNION_ORDER.type).toString()));
		}
		JSONObject object = new JSONObject();
		object.put("order_list", list);

		return object;
	}
}
