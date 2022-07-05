package index.Index;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import common.MU;
import common.database.AGREEMENT;
import freemarker.template.TemplateException;

public class IndexController extends CommonController {
	public void indexPage() throws IOException, ServletException, TemplateException, SQLException {
		List<Map<String, Object>> list = new MU(AGREEMENT._table_name).order("id desc").limit("41").select();
		this.assign("list", list);
		this.display();
	}
}
