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
package admin.system.Index;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.Page;

import admin.home.Index.CommonController;
import common.Functions;
import common.MU;
import common.database.ADMIN;
import common.database.ROLE;
import freemarker.template.TemplateException;

public class AdminController extends CommonController {
	public void indexPage() throws SQLException, IOException, ServletException, TemplateException {
		Map<String, W> where = new HashMap<>();
		if (!I(ADMIN.username).trim().equals("")) {
			where.put("a." + ADMIN.username, new W("like", "%" + I(ADMIN.username).trim() + "%"));
		}
		if (!I(ADMIN.mobile).trim().equals("")) {
			where.put("a." + ADMIN.mobile, new W("like", "%" + I(ADMIN.mobile).trim() + "%"));
		}
		where.put("a." + ADMIN.is_del, new W("eq", 0));
		long count = new MU(ADMIN._table_name).alias("a").where(where).count();
		Page page = this.page(count, 16);
		List<Map<String, Object>> list = new MU(ADMIN._table_name).alias("a").join(ROLE._table_name, "as ag on a.role_id = ag.id", "left").where(where).order(ADMIN.id + " desc").field("a.*, ag." + ROLE.role_name + " as group_name").limit(page.firstRow + "," + page.listRows).select();
		this.assign("page", page.show());
		this.assign("list", JSONArray.parseArray(JSON.toJSONString(list)));
		this.display();
	}

	public void addPage() throws SQLException, IOException, ServletException, TemplateException {
		String id = I("id").toString();
		Map<String, Object> info = new HashMap<>();
		Map<String, W> where = new HashMap<>();
		if (!id.equals("")) {
			where.put(ADMIN.id, new W("eq", id));
			where.put(ADMIN.is_del, new W("eq", 0));
			info = new MU(ADMIN._table_name).where(where).find();
			if (info == null) {
				this.error("该成员不存在或已被删除");
				return;
			}
		}

		Map<String, Object> map = new LinkedHashMap();

		// 获取所有的权限名称
		Map<String, W> role_where = new HashMap<>();
		role_where.put(ROLE.is_del, new W("eq", 0));
		List<Map<String, Object>> role_list = new MU(ROLE._table_name).where(role_where).select();
		map.put("0", "超级管理员");
		for (Integer i = 0; i < role_list.size(); i = i + 1) {
			String _id = role_list.get(i).get(ROLE.id).toString();
			String _name = role_list.get(i).get(ROLE.role_name).toString();
			map.put(_id, _name);
		}

		if (IS_POST) {
			Map<String, Object> data = Functions.I_TO_MAP(I());
			// 检测用户名是否重复
			String username = data.get(ADMIN.username).toString();
			Map<String, W> check_where = new HashMap<>();
			check_where.put(ADMIN.username, new W("eq", username));
			check_where.put(ADMIN.is_del, new W("eq", 0));
			if (!id.equals("")) {
				check_where.put(ADMIN.id, new W("neq", id));
			}
			Map<String, Object> check = new MU(ADMIN._table_name).where(check_where).find();
			if (check != null) {
				this.error("该用户名已存在");
				return;
			}
			// 检测密码
			if (id.equals("")) {// 说明是新建成员
				if (I(ADMIN.password).trim().equals("")) {
					this.error("密码不能为空");
					return;
				}
				data.put(ADMIN.password, Functions.create_password(I(ADMIN.password).trim()));
			} else {
				if (I(ADMIN.password).trim().equals("")) {
					data.remove(ADMIN.password);
				} else {
					data.put(ADMIN.password, Functions.create_password(I(ADMIN.password).trim()));
				}
			}
			// 检测选择的管理员组是否正确
			if (!map.containsKey(I(ADMIN.role_id).trim())) {
				this.error("不存在该岗位");
				return;
			}
			// 记入数据库
			if (id.equals("")) {
				new MU(ADMIN._table_name).data(data).add();
				this.success("新建成功");
			} else {
				new MU(ADMIN._table_name).where(where).save(data);
				this.success("修改成功");
			}
		} else {
			this.assign("info", JSONObject.parseObject(JSON.toJSONString(info)));
			this.assign("map", map);
			this.display("add");
		}
	}

	public void editPage() throws SQLException, IOException, ServletException, TemplateException {
		this.addPage();
	}

	public void delPage() throws IOException, SQLException {
		if (IS_POST) {
			String id = I("id").trim();
			if (id.equals("1")) {
				this.error("主管理员用户不能删除");
				return;
			}
			Map<String, W> where = new HashMap<>();
			where.put(ADMIN.id, new W("eq", id));
			Map<String, Object> data = new HashMap<>();
			data.put(ADMIN.is_del, 1);
			new MU(ADMIN._table_name).where(where).save(data);
			this.success("删除成功");
		}
	}

	public void materialPage() throws SQLException, IOException, ServletException, TemplateException {
		String id = session("admin_id").toString().trim();
		Map<String, Object> info = new HashMap<>();
		Map<String, W> where = new HashMap<>();
		if (!id.equals("")) {
			where.put(ADMIN.id, new W("eq", id));
			where.put(ADMIN.is_del, new W("eq", 0));
			info = new MU(ADMIN._table_name).where(where).find();
			if (info == null) {
				this.error("该成员不存在或已被删除");
				return;
			}
		}

		if (IS_POST) {
			Map<String, Object> data = Functions.I_TO_MAP(I());
			// 检测用户名是否重复
			String username = data.get(ADMIN.username).toString();
			Map<String, W> check_where = new HashMap<>();
			check_where.put(ADMIN.username, new W("eq", username));
			check_where.put(ADMIN.is_del, new W("eq", 0));
			if (!id.equals("")) {
				check_where.put(ADMIN.id, new W("neq", id));
			}
			Map<String, Object> check = new MU(ADMIN._table_name).where(check_where).find();
			if (check != null) {
				this.error("该用户名已存在");
				return;
			}
			// 检测密码
			if (id.equals("")) {// 说明是新建成员
				if (I(ADMIN.password).trim().equals("")) {
					this.error("密码不能为空");
					return;
				}
				data.put(ADMIN.password, Functions.create_password(I(ADMIN.password).trim()));
			} else {
				if (I(ADMIN.password).trim().equals("")) {
					data.remove(ADMIN.password);
				} else {
					data.put(ADMIN.password, Functions.create_password(I(ADMIN.password).trim()));
				}
			}
			// 不能修改所属的管理员组
			if (data.containsKey(ADMIN.role_id)) {
				data.remove(ADMIN.role_id);
			}
			// 记入数据库
			if (id.equals("")) {
				new MU(ADMIN._table_name).data(data).add();
				this.success("新建成功");
			} else {
				new MU(ADMIN._table_name).where(where).save(data);
				this.success("修改成功");
			}
		} else {
			this.assign("info", JSONObject.parseObject(JSON.toJSONString(info)));
			this.display();
		}
	}

}
