package admin.home.Detail;

public class ContactUsController extends BaseController {
	public Boolean init() {
		Boolean b = super.init();
		if (b) {
			this.categoryType = "Index/ContactUs/index";
			this.detailType = "Index/ContactUs/detail";
			this.assign("page_action", "联系我们");
			return true;
		} else {
			return false;
		}
	}
}