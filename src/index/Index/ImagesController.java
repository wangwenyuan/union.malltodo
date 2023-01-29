package index.Index;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import com.javatodo.core.controller.Controller;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.Http;
import com.javatodo.core.tools.Page;

import common.MU;
import common.database.FILE;
import freemarker.template.TemplateException;

public class ImagesController extends Controller {

	private String admin_id = "";
	private String store_id = "";
	private String uid = "";
	private String agent_id = "";
	private String merchant_id = "";

	public Boolean init() {
		try {
			admin_id = session("admin_id").toString().trim();
			store_id = session("store_id").toString().trim();
			uid = session("uid").toString().trim();
			agent_id = session("agent_id").toString().trim();
			merchant_id = session("merchant_id").toString().trim();
			if (admin_id.equals("") && store_id.equals("") && uid.equals("") && agent_id.equals("") && merchant_id.equals("")) {
				this.error("无权进入");
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public void indexPage() throws SQLException, IOException, ServletException, TemplateException {
		Map<String, W> where = new HashMap<>();
		if (!admin_id.equals("")) {
			where.put(FILE.admin_id, new W("eq", admin_id));
		}
		if (!store_id.equals("")) {
			where.put(FILE.store_id, new W("eq", store_id));
		}
		if (!uid.equals("")) {
			where.put(FILE.uid, new W("eq", uid));
		}
		if (!agent_id.equals("")) {
			where.put(FILE.agent_id, new W("eq", agent_id));
		}
		if (!merchant_id.equals("")) {
			where.put(FILE.merchant_id, new W("eq", merchant_id));
		}
		Long count = new MU(FILE._table_name).where(where).count();
		Page page = this.page(count, 24);
		List<Map<String, Object>> list = new MU(FILE._table_name).where(where).order(FILE.id + " desc").limit(page.firstRow + "," + page.listRows).select();
		this.assign("list", list);
		if (IS_POST) {
			this.jsonDisplay();
		} else {
			this.display();
		}
	}

	public void freePage() throws SQLException, IOException, ServletException, TemplateException {
		if (IS_POST) {
			Http http = new Http();
			String html = http.get("http://img.malltodo.com/images.json");
			this.jsonDisplay(html);
		} else {
			this.display("index");
		}
	}
}
