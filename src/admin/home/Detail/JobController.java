package admin.home.Detail;

public class JobController extends BaseController {
	public Boolean init() {
		Boolean b = super.init();
		if (b) {
			this.categoryType = "Index/Job/index";
			this.detailType = "Index/Job/detail";
			this.assign("page_action", "人力招聘");
			return true;
		} else {
			return false;
		}
	}
}