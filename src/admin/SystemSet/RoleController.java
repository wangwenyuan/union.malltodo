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
package admin.SystemSet;

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

import admin.Index.CommonController;
import common.MU;
import common.database.ADMIN_AUTH;
import common.database.ADMIN_GROUP;
import freemarker.template.TemplateException;

public class RoleController extends CommonController {
	public void indexPage() throws IOException, SQLException, ServletException, TemplateException {
		String gid = I("gid");
		if (gid.equals("0") || gid.equals("")) {
			this.error("请选择岗位");
			return;
		}
		Map<String, W> where = new HashMap<>();
		where.put(ADMIN_GROUP.id, new W("eq", gid));
		where.put(ADMIN_GROUP.is_del, new W("eq", 0));
		Map<String, Object> admin_group = new MU(ADMIN_GROUP._table_name).where(where).find();
		if (admin_group == null) {
			this.error("不存在该岗位");
			return;
		}
		if (IS_POST) {
			Map<String, W> auth_where = new HashMap<>();
			auth_where.put(ADMIN_AUTH.group_id, new W("eq", gid));
			new MU(ADMIN_AUTH._table_name).where(auth_where).delete();
			Map<String, String[]> map = request.getParameterMap();
			JSONObject object = JSONObject.parseObject(JSON.toJSONString(map));
			JSONArray list = object.getJSONArray("r[]");
			for (Integer i = 0; i < list.size(); i = i + 1) {
				String string = list.getString(i);
				String[] strings = string.split("\\+");
				Map<String, Object> data = new HashMap<>();
				data.put(ADMIN_AUTH.group_id, gid);
				if (strings.length > 0) {
					data.put(ADMIN_AUTH.m, strings[0]);
				}
				if (strings.length > 1) {
					data.put(ADMIN_AUTH.c, strings[1]);
				}
				if (strings.length > 2) {
					data.put(ADMIN_AUTH.a, strings[2]);
				}
				new MU(ADMIN_AUTH._table_name).data(data).add();
			}
			this.success("设置成功");
			return;
		} else {
			Map<String, W> auth_where = new HashMap<>();
			auth_where.put(ADMIN_AUTH.group_id, new W("eq", gid));
			List<Map<String, Object>> list = new MU(ADMIN_AUTH._table_name).where(auth_where).select();
			JSONArray role_arr = new JSONArray();
			for (Integer i = 0; i < list.size(); i = i + 1) {
				String string = "";
				if (!list.get(i).get(ADMIN_AUTH.m).toString().equals("")) {
					string = list.get(i).get(ADMIN_AUTH.m).toString();
				}
				if (!list.get(i).get(ADMIN_AUTH.c).toString().equals("")) {
					string = list.get(i).get(ADMIN_AUTH.m).toString() + "+" + list.get(i).get(ADMIN_AUTH.c).toString();
				}
				if (!list.get(i).get(ADMIN_AUTH.a).toString().equals("")) {
					string = list.get(i).get(ADMIN_AUTH.m).toString() + "+" + list.get(i).get(ADMIN_AUTH.c).toString() + "+" + list.get(i).get(ADMIN_AUTH.a).toString();
				}
				role_arr.add(string);
			}
			this.assign("role_list", role_arr);
			this.display();
		}
	}
}
