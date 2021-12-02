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
package admin.Order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.Page;

import admin.Index.CommonController;
import common.MU;
import common.database.MEMBER;
import common.database.UNION_ORDER;
import common.database.UNION_ORDER_GOODS;
import freemarker.template.TemplateException;
import model.Platform;

public class IndexController extends CommonController {
	public void indexPage() throws IOException, ServletException, TemplateException, SQLException {
		Map<String, W> where = new HashMap<String, W>();
		long count = new MU(UNION_ORDER._table_name).where(where).count();
		Page page = this.page(count, 16);
		List<Map<String, Object>> list = new MU(UNION_ORDER._table_name).where(where).limit(page.firstRow + "," + page.listRows).order(UNION_ORDER.id + " desc").select();
		for (Integer i = 0; i < list.size(); i = i + 1) {
			JSONObject object = this.getOrder(list.get(i));
			list.get(i).put("extra", object);
			list.get(i).put("platform", Platform.map.get(list.get(i).get(UNION_ORDER.type).toString()));
		}
		this.assign("list", JSONArray.parseArray(JSON.toJSONString(list)));
		this.assign("page", page.show());
		this.display();
	}

	private JSONObject getOrder(Map<String, Object> order) throws SQLException {
		String order_sn = order.get(UNION_ORDER.order_sn).toString();
		Map<String, W> where = new HashMap<>();
		where.put(UNION_ORDER_GOODS.order_sn, new W("eq", order_sn));
		where.put(UNION_ORDER_GOODS.status, new W("eq", 1));
		List<Map<String, Object>> list = new MU(UNION_ORDER_GOODS._table_name).where(where).select();

		String level_1_id = order.get(UNION_ORDER.level_1_id).toString();
		Map<String, W> level_1_where = new HashMap<>();
		level_1_where.put(MEMBER.id, new W("eq", level_1_id));
		level_1_where.put(MEMBER.is_del, new W("eq", 0));
		Map<String, Object> member_1 = new MU(MEMBER._table_name).where(level_1_where).find();

		String level_2_id = order.get(UNION_ORDER.level_2_id).toString();
		Map<String, W> level_2_where = new HashMap<>();
		level_2_where.put(MEMBER.id, new W("eq", level_2_id));
		level_2_where.put(MEMBER.is_del, new W("eq", 0));
		Map<String, Object> member_2 = new MU(MEMBER._table_name).where(level_2_where).find();

		String level_3_id = order.get(UNION_ORDER.level_3_id).toString();
		Map<String, W> level_3_where = new HashMap<>();
		level_3_where.put(MEMBER.id, new W("eq", level_3_id));
		level_3_where.put(MEMBER.is_del, new W("eq", 0));
		Map<String, Object> member_3 = new MU(MEMBER._table_name).where(level_3_where).find();

		JSONObject object = new JSONObject();
		object.put("goods_list", list);
		object.put("member_1", member_1);
		object.put("member_2", member_2);
		object.put("member_3", member_3);

		return object;
	}
}
