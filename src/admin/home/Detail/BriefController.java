package admin.home.Detail;

public class BriefController extends BaseController {
	public Boolean init() {
		Boolean b = super.init();
		if (b) {
			this.categoryType = "Index/Brief/index";
			this.detailType = "Index/Brief/detail";
			this.assign("page_action", "公司简介");
			return true;
		} else {
			return false;
		}
	}
}
