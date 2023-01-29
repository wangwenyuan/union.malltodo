package admin.home.WebSite;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.Page;

import admin.home.Index.CommonController;
import common.MU;
import common.database.WEBSITE;
import freemarker.template.TemplateException;

public class IndexController extends CommonController {
	private static JSONObject website_map = null;

	synchronized private static void clearWebSiteMap() {
		website_map = null;
	}

	synchronized public static String getWebSiteJSONString() throws SQLException {
		if (website_map == null) {
			Map<String, W> where = new HashMap<String, W>();
			where.put(WEBSITE.is_del, new W("eq", 0));
			List<Map<String, Object>> list = new MU(WEBSITE._table_name).where(where).select();
			website_map = new JSONObject();
			for (Integer i = 0; i < list.size(); i = i + 1) {
				website_map.put(list.get(i).get(WEBSITE.id).toString(), list.get(i).get(WEBSITE.website_name).toString());
			}
		}
		return website_map.toJSONString();
	}

	public void indexPage() throws SQLException, IOException, ServletException, TemplateException {
		Map<String, W> where = new HashMap<>();
		where.put(WEBSITE.is_del, new W("eq", 0));
		long count = new MU(WEBSITE._table_name).where(where).count();
		Page page = this.page(count, 16);
		List<Map<String, Object>> list = new MU(WEBSITE._table_name).where(where).order(WEBSITE.id + " desc").select();
		this.assign("page", page.show());
		this.assign("list", JSONArray.parseArray(JSONArray.toJSONString(list)));
		this.display();
	}

	public void addPage() throws SQLException, IOException, ServletException, TemplateException {
		String id = I("id").trim();
		Map<String, W> where = new HashMap<>();
		Map<String, Object> info = new HashMap<>();
		if (!id.equals("")) {
			where.put(WEBSITE.id, new W("eq", id));
			where.put(WEBSITE.is_del, new W("eq", 0));
			info = new MU(WEBSITE._table_name).where(where).find();
			if (info == null) {
				this.error("该站点不存在或已被删除");
				return;
			}
		}
		if (IS_POST) {
			Map<String, Object> data = new HashMap<>();

			String website_name = I(WEBSITE.website_name).trim();
			String website_host = I(WEBSITE.website_host).trim();
			website_host = website_host.replace("http://", "").replace("https://", "").replace("/", "");

			if (website_name.equals("")) {
				this.error("站点名称不能为空");
				return;
			}
			if (website_host.equals("")) {
				this.error("站点域名不能为空");
				return;
			}

			// 判断该域名是否已存在
			Map<String, W> check_where = new HashMap<>();
			check_where.put(WEBSITE.is_del, new W("eq", 0));
			check_where.put(WEBSITE.website_host, new W("eq", website_host));
			if (!id.equals("")) {// 说明是新建
				check_where.put(WEBSITE.id, new W("neq", id));
			}
			Map<String, Object> checkInfo = new MU(WEBSITE._table_name).where(check_where).find();
			if (checkInfo != null) {
				this.error("该域名已存在");
				return;
			}

			data.put(WEBSITE.website_name, website_name);
			data.put(WEBSITE.website_host, website_host);
			data.put(WEBSITE.statistics_code, I(WEBSITE.statistics_code));

			data.put(WEBSITE.admin_id, session("admin_id"));
			if (id.equals("")) {
				data.put(WEBSITE.addtime, System.currentTimeMillis());
				new MU(WEBSITE._table_name).data(data).add();
				clearWebSiteMap();
				this.success("添加成功");
				return;
			} else {
				new MU(WEBSITE._table_name).where(where).save(data);
				clearWebSiteMap();
				this.success("修改成功");
				return;
			}
		} else {
			this.assign("info", info);
			this.display("add");
		}
	}

	public void editPage() throws SQLException, IOException, ServletException, TemplateException {
		this.addPage();
	}

	public void delPage() throws SQLException, IOException {
		if (IS_POST) {
			String id = I("id").trim();
			Map<String, W> where = new HashMap<>();
			where.put(WEBSITE.id, new W("eq", id));
			Map<String, Object> data = new HashMap<>();
			data.put(WEBSITE.is_del, 1);
			new MU(WEBSITE._table_name).where(where).save(data);
			if (session("website_id") == id) {
				session("website_id", null);
			}
			clearWebSiteMap();
			this.success("删除成功");
		}
	}

	public void switch_websitesPage() throws SQLException, IOException {
		if (IS_POST) {
			String website_id = I("website_id");
			Map<String, W> where = new HashMap<>();
			where.put(WEBSITE.id, new W("eq", website_id));
			where.put(WEBSITE.is_del, new W("eq", 0));
			Map<String, Object> website_info = new MU(WEBSITE._table_name).where(where).find();
			if (website_info == null) {
				this.error("该站点不存在或已被删除");
				return;
			} else {
				session("website_id", website_id);
				this.success("站点切换成功");
			}
		}
	}
}
