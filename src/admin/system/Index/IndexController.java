package admin.system.Index;

import java.io.IOException;

import admin.home.Index.CommonController;

public class IndexController extends CommonController {
	public void indexPage() throws IOException {
		redirect(U("Index/Admin/index"));
		return;
	}
}
