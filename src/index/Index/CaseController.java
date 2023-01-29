package index.Index;

import java.io.IOException;
import java.sql.SQLException;

public class CaseController extends BaseController {
	public void indexPage() throws SQLException, IOException {
		this.homePagePage("Index/Case/index");
	}
}
