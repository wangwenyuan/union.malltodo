package admin.home.Category;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.model.W;

import admin.home.Index.CommonController;
import common.Common;
import common.Functions;
import common.MU;
import common.MenuCache;
import common.database.CATEGORY;
import common.database.RENOVATION;
import freemarker.template.TemplateException;

public class IndexController extends CommonController {
	public void indexPage() throws SQLException, IOException, ServletException, TemplateException {
		MenuCache.init(session("website_id").toString());
		JSONArray admin_menu_list = MenuCache.getAdminMenuList(session("website_id").toString());
		this.assign("list", admin_menu_list);
		this.display();
	}

	public void addPage() throws SQLException, IOException, ServletException, TemplateException {
		Map<String, W> where = new HashMap<>();
		Map<String, Object> info = new HashMap<>();
		if (!I(CATEGORY.id).toString().trim().equals("")) {
			where.put(CATEGORY.id, new W("eq", I(CATEGORY.id).toString().trim()));
			where.put(CATEGORY.is_del, new W("eq", 0));
			info = new MU(CATEGORY._table_name).where(where).find();
			if (info == null) {
				this.error("不存在该栏目或已被删除");
				return;
			}
		}
		// 获取所有的pc端模版
		Map<String, W> pc_renovation_where = new HashMap<>();
		pc_renovation_where.put(RENOVATION.platform, new W("eq", "pc"));
		pc_renovation_where.put(RENOVATION.is_del, new W("eq", 0));
		pc_renovation_where.put(RENOVATION.website_id, new W("eq", session("website_id").toString()));
		List<Map<String, Object>> pc_renovation_list = new MU(RENOVATION._table_name).where(pc_renovation_where).select();
		JSONObject pc_renovation = new JSONObject(new LinkedHashMap<String, Object>());
		for (Integer i = 0; i < pc_renovation_list.size(); i = i + 1) {
			JSONObject object = new JSONObject();
			object.put(RENOVATION.name, pc_renovation_list.get(i).get(RENOVATION.name));
			object.put(RENOVATION.type, pc_renovation_list.get(i).get(RENOVATION.type));
			pc_renovation.put(pc_renovation_list.get(i).get(RENOVATION.id).toString(), object);
		}
		// 获取所有的手机端模版
		Map<String, W> mobile_renovation_where = new HashMap<>();
		mobile_renovation_where.put(RENOVATION.platform, new W("eq", "mobile"));
		mobile_renovation_where.put(RENOVATION.is_del, new W("eq", 0));
		mobile_renovation_where.put(RENOVATION.website_id, new W("eq", session("website_id").toString()));
		List<Map<String, Object>> mobile_renovation_list = new MU(RENOVATION._table_name).where(mobile_renovation_where).select();
		JSONObject mobile_renovation = new JSONObject(new LinkedHashMap<String, Object>());
		for (Integer i = 0; i < mobile_renovation_list.size(); i = i + 1) {
			JSONObject object = new JSONObject();
			object.put(RENOVATION.name, mobile_renovation_list.get(i).get(RENOVATION.name));
			object.put(RENOVATION.type, mobile_renovation_list.get(i).get(RENOVATION.type));
			mobile_renovation.put(mobile_renovation_list.get(i).get(RENOVATION.id).toString(), object);
		}

		JSONArray admin_menu_list = MenuCache.getAdminMenuList(session("website_id").toString());
		JSONObject menu_json = new JSONObject(new LinkedHashMap<>());
		JSONObject _menu_json = new JSONObject(new LinkedHashMap<>());
		_menu_json.put("0", "顶级栏目");

		for (Integer i = 0; i < admin_menu_list.size(); i = i + 1) {
			JSONObject jsonObject = admin_menu_list.getJSONObject(i);
			String sign = "";
			for (Integer n = 0; n < jsonObject.getIntValue("level"); n = n + 1) {
				sign = sign + "——";
			}
			String id = jsonObject.getString(CATEGORY.id);
			String name = jsonObject.getString(CATEGORY.category_name);
			name = sign + name;
			String type = jsonObject.getString(CATEGORY.type);
			JSONObject object = new JSONObject();
			object.put("id", id);
			object.put("name", name);
			object.put("type", type);
			_menu_json.put(id, name);
			menu_json.put(id, object);
		}

		// 获取栏目页
		if (IS_POST) {
			Map<String, String> _input = Functions.trim_array(I());
			Map<String, Object> data = Functions.I_TO_MAP(_input);
			if (data.get(CATEGORY.category_name).toString().equals("")) {
				this.error("栏目名称不能为空");
				return;
			}
			if (data.get(CATEGORY.seo_title).toString().equals("")) {
				data.put(CATEGORY.seo_title, data.get(CATEGORY.category_name).toString());
			}
			if (data.get(CATEGORY.seo_keywords).toString().equals("")) {
				data.put(CATEGORY.seo_keywords, data.get(CATEGORY.category_name).toString());
			}
			if (data.get(CATEGORY.seo_description).toString().equals("")) {
				data.put(CATEGORY.seo_description, data.get(CATEGORY.category_name).toString());
			}
			String type = data.get(CATEGORY.type).toString();
			if (!Common.home_menu.containsKey(type)) {
				this.error("您选择的模型有误");
				return;
			}

			// 下级栏目的模型与上级栏目应该一致
			String pid = I(CATEGORY.pid);
			if (!pid.equals("") && !pid.equals("0")) {
				// 获取上级的栏目模型
				String pid_type = menu_json.getJSONObject(pid).getString("type").trim();
				String id_type = I(CATEGORY.type).trim();
				if (!pid_type.equals(id_type)) {
					this.error("下级栏目模型要与上级栏目模型一致");
					return;
				}
			}

			if (type.equals("Index/Index/index")) {
				data.put(CATEGORY.pid, "0");
				data.put(CATEGORY.category_type, "0");
				data.put(CATEGORY.pc_list_renovation_id, "0");
				data.put(CATEGORY.pc_page_renovation_id, "0");
				data.put(CATEGORY.mobile_list_renovation_id, "0");
				data.put(CATEGORY.mobile_page_renovation_id, "0");
				data.put(CATEGORY.mobile_custom_id, "0");
				data.put(CATEGORY.pc_custom_id, "0");
			}

			// 加入数据库
			if (!I(CATEGORY.id).toString().trim().equals("")) {// 修改
				// 检测上级id是否符合规定
				if (!check(I(CATEGORY.id).toString().trim(), pid)) {
					this.error("上级id选择错误");
					return;
				}
				new MU(CATEGORY._table_name).where(where).save(data);
			} else {// 添加
				data.put(CATEGORY.website_id, session("website_id"));
				new MU(CATEGORY._table_name).data(data).add();
			}
			MenuCache.clean(session("website_id").toString());
			this.success("设置成功");
			return;
		} else {
			this.assign("info", info);
			this.assign("pc_renovation", pc_renovation.toJSONString());
			this.assign("mobile_renovation", mobile_renovation.toJSONString());
			this.assign("_menu_json", _menu_json);
			this.assign("menu_json", menu_json);
			this.display("add");
		}
	}

	public void editPage() throws SQLException, IOException, ServletException, TemplateException {
		this.addPage();
	}

	public void adjustHiddenPage() throws SQLException, IOException {
		if (IS_POST) {
			String id = I("id").toString().trim();
			Map<String, W> where = new HashMap<>();
			where.put(CATEGORY.id, new W("eq", id));
			Map<String, Object> info = new MU(CATEGORY._table_name).where(where).find();
			if (info == null) {
				this.error("设置失败");
				return;
			}
			Map<String, Object> save_data = new HashMap<>();
			if (info.get(CATEGORY.is_hidden).toString().equals("0")) {
				save_data.put(CATEGORY.is_hidden, 1);
			} else {
				save_data.put(CATEGORY.is_hidden, 0);
			}
			new MU(CATEGORY._table_name).where(where).save(save_data);
			MenuCache.clean(session("website_id").toString());
			this.success("设置成功");
		}
	}

	public void adjustSortPage() throws SQLException, IOException {
		if (IS_POST) {
			String id = I("id").toString().trim();
			String sort = I("sort").toString().trim();
			Map<String, W> where = new HashMap<>();
			where.put(CATEGORY.id, new W("eq", id));
			Map<String, Object> save_data = new HashMap<>();
			save_data.put(CATEGORY.sort, sort);
			new MU(CATEGORY._table_name).where(where).save(save_data);
			MenuCache.clean(session("website_id").toString());
			this.success("设置成功");
		}
	}

	public void delPage() throws SQLException, IOException {
		if (IS_POST) {
			String id = I("id").trim();
			Map<String, W> where = new HashMap<>();
			where.put(CATEGORY.id, new W("eq", id));
			Map<String, Object> info = new MU(CATEGORY._table_name).where(where).find();
			String pid = info.get(CATEGORY.pid).toString();
			// 删除当前栏目
			Map<String, Object> data = new HashMap<>();
			data.put(CATEGORY.is_del, 1);
			new MU(CATEGORY._table_name).where(where).save(data);
			// 将当前栏目的所有子栏目转移到父栏目下
			where.clear();
			where.put(CATEGORY.pid, new W("eq", id));
			data.clear();
			data.put(CATEGORY.pid, pid);
			new MU(CATEGORY._table_name).where(where).save(data);
			MenuCache.clean(session("website_id").toString());
			this.success("删除成功");
		}
	}

	private boolean check(String id, String pid) throws SQLException {
		id = id.trim();
		pid = pid.trim();
		if (id.equals(pid)) {
			return false;
		}
		while (true) {
			Map<String, W> where = new HashMap();
			where.put(CATEGORY.id, new W("eq", pid));
			where.put(CATEGORY.is_del, new W("eq", 0));
			Object _pid = new MU(CATEGORY._table_name).where(where).getField(CATEGORY.pid);
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
