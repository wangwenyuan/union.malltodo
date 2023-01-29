package admin.home.Detail;

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
import common.MU;
import common.database.MESSAGE;
import freemarker.template.TemplateException;

public class MessageController extends CommonController {
	public Boolean init() {
		Boolean b = super.init();
		if (b) {
			this.assign("page_action", "客户留言");
			return true;
		} else {
			return false;
		}
	}

	public void indexPage() throws SQLException, IOException, ServletException, TemplateException {
		Map<String, W> where = new HashMap<>();
		where.put(MESSAGE.is_del, new W("eq", 0));
		Long count = new MU(MESSAGE._table_name).where(where).count();
		Page page = this.page(count, 16);
		this.assign("page", page.show());
		List<Map<String, Object>> list = new MU(MESSAGE._table_name).where(where).limit(page.firstRow + "," + page.listRows).order(MESSAGE.id + " desc").select();
		this.assign("list", JSONArray.parse(JSON.toJSONString(list)));
		this.display();
	}
}