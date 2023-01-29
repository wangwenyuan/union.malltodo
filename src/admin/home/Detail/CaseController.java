package admin.home.Detail;

public class CaseController extends BaseController {
	public Boolean init() {
		Boolean b = super.init();
		if (b) {
			this.categoryType = "Index/Case/index";
			this.detailType = "Index/Case/detail";
			this.assign("page_action", "应用案例");
			return true;
		} else {
			return false;
		}
	}
}