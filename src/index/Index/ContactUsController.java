package index.Index;

import java.io.IOException;
import java.sql.SQLException;

public class ContactUsController extends BaseController {
	public void indexPage() throws SQLException, IOException {
		this.homePagePage("Index/ContactUs/index");
	}
}
