package admin.home.Detail;

public class BusinessController extends BaseController {
	public Boolean init() {
		Boolean b = super.init();
		if (b) {
			this.categoryType = "Index/Business/index";
			this.detailType = "Index/Business/detail";
			this.assign("page_action", "业务范围");
			return true;
		} else {
			return false;
		}
	}
}