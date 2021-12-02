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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.T;

import admin.Index.CommonController;
import common.MU;
import common.database.ADMIN_GROUP;
import freemarker.template.TemplateException;

public class AdminGroupController extends CommonController {
	private static List<Map<String, Object>> admin_categorys = new ArrayList<>();// 后台用到的数据

	public static List<Map<String, Object>> getAdminCategorys() {
		return admin_categorys;
	}

	public void indexPage() throws SQLException, IOException, ServletException, TemplateException {
		if (admin_categorys.size() == 0) {
			refreshCategorys();
		}
		this.assign("list", JSONArray.parseArray(JSON.toJSONString(admin_categorys)));
		this.display();
	}

	public void addPage() throws SQLException, IOException, ServletException, TemplateException {
		String id = I("id");
		Map<String, Object> info = null;
		Map<String, W> where = new HashMap<String, W>();
		if (!"".equals(id)) {
			where.put(ADMIN_GROUP.id, new W("eq", id));
			where.put(ADMIN_GROUP.is_del, new W("eq", 0));
			info = new MU(ADMIN_GROUP._table_name).where(where).find();
			if (info == null) {
				this.error("不存在该信息或已被删除");
				return;
			}
		}
		if (IS_POST) {
			if (I("name").trim().equals("")) {
				this.error("岗位名称不能为空");
				return;
			}
			Map<String, Object> save_data = new HashMap();
			save_data.put(ADMIN_GROUP.name, I("name").trim());
			if (I("pid").trim().equals("")) {
				save_data.put(ADMIN_GROUP.pid, 0);
			} else {
				save_data.put(ADMIN_GROUP.pid, I("pid").trim());
			}
			if (!I("sort").equals("")) {
				save_data.put(ADMIN_GROUP.sort, T.toInt(I("sort").trim()));
			}
			if ("".equals(id)) {
				new MU(ADMIN_GROUP._table_name).data(save_data).add();
				refreshCategorys();
				this.success("添加成功");
				return;
			} else {
				if (this.check(id, I("pid"))) {
					new MU(ADMIN_GROUP._table_name).where(where).save(save_data);
					refreshCategorys();
					this.success("修改成功");
				} else {
					this.error("您选择的上级岗位有误");
				}
				return;
			}
		} else {
			this.assign("info", JSONObject.parse(JSON.toJSONString(info)));

			Map<String, Object> map = new LinkedHashMap();
			map.put("0", "顶级岗位");
			for (Integer i = 0; i < admin_categorys.size(); i = i + 1) {
				String _id = admin_categorys.get(i).get(ADMIN_GROUP.id).toString();
				String _name = admin_categorys.get(i).get(ADMIN_GROUP.name).toString();
				Integer level = T.toInt(admin_categorys.get(i).get("level").toString());
				_name = "—— " + _name;
				for (Integer n = 0; n < level; n = n + 1) {
					_name = "——" + _name;
				}
				map.put(_id, _name);
			}

			this.assign("map", map);
			this.display("add");
		}
	}

	public void editPage() throws SQLException, IOException, ServletException, TemplateException {
		this.addPage();
	}

	public void delPage() throws SQLException, IOException {
		if (IS_POST) {
			if (admin_categorys.size() == 0) {
				refreshCategorys();
			}
			String id = I("id").trim();
			Map<String, W> where = new HashMap<>();
			where.put(ADMIN_GROUP.id, new W("eq", id));
			Map<String, Object> category_info = new MU(ADMIN_GROUP._table_name).where(where).find();
			if (category_info == null) {
				return;
			}
			String pid = category_info.get(ADMIN_GROUP.pid).toString();
			// 删除当前栏目
			Map<String, Object> data = new HashMap<>();
			data.put(ADMIN_GROUP.is_del, 1);
			new MU(ADMIN_GROUP._table_name).where(where).save(data);
			// 将当前栏目的子栏目的父级栏目设置为当前栏目的父栏目
			where.clear();
			where.put(ADMIN_GROUP.pid, new W("eq", id));
			data.clear();
			data.put(ADMIN_GROUP.pid, pid);
			new MU(ADMIN_GROUP._table_name).where(where).save(data);
			refreshCategorys();
			this.success("删除成功");
			return;
		}
	}

	private void refreshCategorys() throws SQLException {
		admin_categorys = new ArrayList<>();
		buildCategorys("0", 0);
	}

	private JSONArray buildCategorys(String pid, Integer level) throws SQLException {
		Map<String, W> where = new HashMap<>();
		where.put(ADMIN_GROUP.pid, new W("eq", pid));
		where.put(ADMIN_GROUP.is_del, new W("eq", 0));
		List<Map<String, Object>> _list = new MU(ADMIN_GROUP._table_name).where(where).order(ADMIN_GROUP.sort + " asc, id asc").select();
		JSONArray array = new JSONArray();
		for (Integer i = 0; i < _list.size(); i = i + 1) {
			_list.get(i).put("level", level);
			admin_categorys.add(_list.get(i));
			_list.get(i).put("subcategorys", this.buildCategorys(_list.get(i).get(ADMIN_GROUP.id).toString(), level + 1));
			array.add(_list.get(i));
		}
		return array;
	}

	private boolean check(String id, String pid) throws SQLException {
		id = id.trim();
		pid = pid.trim();
		if (id.equals(pid)) {
			return false;
		}
		while (true) {
			Map<String, W> where = new HashMap();
			where.put(ADMIN_GROUP.id, new W("eq", pid));
			where.put(ADMIN_GROUP.is_del, new W("eq", 0));
			Object _pid = new MU(ADMIN_GROUP._table_name).where(where).getField(ADMIN_GROUP.pid);
			if (_pid == null) {
				return true;
			}
			if (_pid.toString().equals("0")) {
				return true;
			}
			if (_pid.toString().equals(id)) {
				return false;
			}
			pid = _pid.toString();
		}
	}
}
