package index.Index;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import com.javatodo.core.model.W;
import com.javatodo.core.tools.Page;

import common.MU;
import common.database.AGREEMENT;
import freemarker.template.TemplateException;

public class ArticleController extends CommonController {
	public void indexPage() throws IOException, ServletException, TemplateException, SQLException {
		long count = new MU(AGREEMENT._table_name).count();
		Page page = this.page(count, 16);
		List<Map<String, Object>> list = new MU(AGREEMENT._table_name).order(page.firstRow + "," + page.listRows).order(AGREEMENT.id + " desc").select();
		this.assign("list", list);
		this.assign("page", page.show());
		this.display();
	}

	public void detailPage() throws IOException, ServletException, TemplateException, SQLException {
		String article_id = I("id");
		Map<String, W> where = new HashMap<>();
		where.put(AGREEMENT.id, new W("eq", article_id));
		Map<String, Object> info = new MU(AGREEMENT._table_name).where(where).find();
		this.assign("info", info);
		this.display();
	}
}
