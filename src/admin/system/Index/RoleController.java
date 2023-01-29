package admin.system.Index;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.Page;

import admin.home.Index.CommonController;
import common.Functions;
import common.MU;
import common.database.ROLE;
import freemarker.template.TemplateException;

public class RoleController extends CommonController {

	public void indexPage() throws SQLException, IOException, ServletException, TemplateException {
		Map<String, W> where = new HashMap<>();
		where.put(ROLE.is_del, new W("eq", 0));
		long count = new MU(ROLE._table_name).where(where).count();
		Page page = this.page(count, 16);
		List<Map<String, Object>> list = new MU(ROLE._table_name).where(where).order(page.firstRow + "," + page.listRows).order(ROLE.id + " desc").select();
		this.assign("list", JSONArray.parse(JSON.toJSONString(list)));
		this.assign("page", page.show());
		this.display();
	}

	public void addPage() throws SQLException, IOException, ServletException, TemplateException {
		String id = I("id").trim();
		Map<String, W> where = new HashMap<>();
		Map<String, Object> info = new HashMap<>();
		if (!id.equals("")) {
			where.put(ROLE.id, new W("eq", id));
			where.put(ROLE.is_del, new W("eq", 0));
			info = new MU(ROLE._table_name).where(where).find();
		}
		if (IS_POST) {
			Map<String, String> data = Functions.trim_array(I());
			if (data.get(ROLE.role_name).equals("")) {
				this.error("名称不能为空");
				return;
			}
			if (id.equals("")) {
				new MU(ROLE._table_name).data(Functions.I_TO_MAP(data)).add();
			} else {
				new MU(ROLE._table_name).where(where).save(Functions.I_TO_MAP(data));
			}
			this.success("设置成功");
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
			where.put(ROLE.id, new W("eq", id));
			Map<String, Object> data = new HashMap<>();
			data.put(ROLE.is_del, 1);
			new MU(ROLE._table_name).where(where).save(data);
			this.success("删除成功");
		}
	}
}
