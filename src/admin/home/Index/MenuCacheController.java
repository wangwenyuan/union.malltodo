package admin.home.Index;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.T;

import common.Common;
import common.MU;
import common.MenuCache;
import common.database.CATEGORY;

public class MenuCacheController extends CommonController {

	public void indexPage() throws SQLException {
		this.jsonDisplay(MenuCache.create(session("website_id").toString()));
	}

	public void optPage() throws SQLException, IOException {
		if (IS_POST) {
			String opt = I("opt").trim();
			String type = I("type").trim();
			if (!Common.home_menu.containsKey(type) && !type.equals("Index/Category/index")) {
				this.error("栏目类型有误");
				return;
			}
			String table_pid = I("pid").trim();
			String table_id = I("id").trim();
			String opt_value = I("opt_value").trim();
			if (opt.equals("add")) {
				this.add(type, table_pid, opt_value, session("website_id").toString());
			} else {
				this.edit(type, table_id, table_pid, opt_value, session("website_id").toString());
			}
			MenuCache.clean(session("website_id").toString());
			this.success("设置成功");
		}
	}

	public void hidePage() throws SQLException, IOException {
		if (IS_POST) {
			String table_id = I("id").trim();
			Map<String, W> where = new HashMap<>();
			where.put(CATEGORY.id, new W("eq", table_id));
			where.put(CATEGORY.website_id, new W("eq", session("website_id")));
			Map<String, Object> data = new HashMap<>();
			data.put(CATEGORY.is_hidden, 1);
			new MU(CATEGORY._table_name).where(where).save(data);
			MenuCache.clean(session("website_id").toString());
			this.success("设置成功");
		}
	}

	public void showPage() throws SQLException, IOException {
		if (IS_POST) {
			String table_id = I("id").trim();
			Map<String, W> where = new HashMap<>();
			where.put(CATEGORY.id, new W("eq", table_id));
			where.put(CATEGORY.website_id, new W("eq", session("website_id")));
			Map<String, Object> data = new HashMap<>();
			data.put(CATEGORY.is_hidden, 0);
			new MU(CATEGORY._table_name).where(where).save(data);
			MenuCache.clean(session("website_id").toString());
			this.success("设置成功");
		}
	}

	public void delPage() throws SQLException, IOException {
		if (IS_POST) {
			String table_id = I("id").trim();
			Map<String, W> where = new HashMap<>();
			where.put(CATEGORY.id, new W("eq", table_id));
			where.put(CATEGORY.website_id, new W("eq", session("website_id")));
			Map<String, Object> info = new MU(CATEGORY._table_name).where(where).find();
			if (info != null && info.get(CATEGORY.pid).toString().equals("0")) {// pid=0时，只有"Index/Category/index"可删除
				String type = info.get(CATEGORY.type).toString();
				if (!type.equals("Index/Category/index")) {
					this.error("该栏目只允许隐藏，不能允许删除");
					return;
				}
			}
			Map<String, Object> data = new HashMap<>();
			data.put(CATEGORY.is_del, 1);
			new MU(CATEGORY._table_name).where(where).save(data);
			// 该栏目的下级子栏目上移一级
			where.clear();
			where.put(CATEGORY.pid, new W("eq", table_id));
			where.put(CATEGORY.website_id, new W("eq", session("website_id")));
			data.clear();
			data.put(CATEGORY.pid, info.get(CATEGORY.pid).toString());
			new MU(CATEGORY._table_name).where(where).save(data);
			MenuCache.clean(session("website_id").toString());
			this.success("设置成功");
		}
	}

	public void adjustPage() throws SQLException, IOException {
		if (IS_POST) {
			String ids = I("ids");
			ids = T.htmlspecialchars_decode(ids);
			JSONArray list = JSONArray.parseArray(ids);
			for (Integer i = 0; i < list.size(); i = i + 1) {
				String id = list.get(i).toString();
				if (id.equals("0")) {
					continue;
				} else {
					Map<String, W> where = new HashMap<>();
					where.put(CATEGORY.id, new W("eq", id));
					where.put(CATEGORY.website_id, new W("eq", session("website_id")));
					Map<String, Object> data = new HashMap<>();
					data.put(CATEGORY.sort, i);
					new MU(CATEGORY._table_name).where(where).save(data);
				}
			}
			MenuCache.clean(session("website_id").toString());
			this.success("调整成功");
		}
	}

	private void add(String type, String table_pid, String category_name, String website_id) throws SQLException {
		// 一个type只允许存在一个pid=0的数据，Index/Category/index类型的除外
		if ((table_pid.equals("0") || table_pid.equals("")) && !type.equals("Index/Category/index")) {
			table_pid = "0";
			Map<String, W> where = new HashMap<>();
			where.put(CATEGORY.pid, new W("eq", table_pid));
			where.put(CATEGORY.is_del, new W("eq", 0));
			where.put(CATEGORY.type, new W("eq", type));
			where.put(CATEGORY.website_id, new W("eq", website_id));
			Map<String, Object> info = new MU(CATEGORY._table_name).where(where).find();
			if (info == null) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put(CATEGORY.category_name, category_name);
				data.put(CATEGORY.pid, table_pid);
				data.put(CATEGORY.type, type);
				data.put(CATEGORY.website_id, website_id);
				new MU(CATEGORY._table_name).data(data).add();
			}
		} else {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put(CATEGORY.category_name, category_name);
			data.put(CATEGORY.pid, table_pid);
			data.put(CATEGORY.type, type);
			data.put(CATEGORY.website_id, website_id);
			new MU(CATEGORY._table_name).data(data).add();
		}
	}

	private void edit(String type, String table_id, String table_pid, String category_name, String website_id) throws SQLException {
		if (table_id.equals("")) {
			table_id = "0";
		}
		if (table_pid.equals("")) {
			table_pid = "0";
		}
		Map<String, W> where = new HashMap<>();
		where.put(CATEGORY.id, new W("eq", table_id));
		where.put(CATEGORY.type, new W("eq", type));
		where.put(CATEGORY.website_id, new W("eq", website_id));
		Map<String, Object> info = new MU(CATEGORY._table_name).where(where).find();
		if (info == null) {
			this.add(type, table_pid, category_name, website_id);
		} else {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("category_name", category_name);
			data.put("pid", table_pid);
			data.put(CATEGORY.website_id, website_id);
			new MU(CATEGORY._table_name).where(where).save(data);
		}
	}
}
