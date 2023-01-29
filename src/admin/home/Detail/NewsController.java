package admin.home.Detail;

public class NewsController extends BaseController {
	public Boolean init() {
		Boolean b = super.init();
		if (b) {
			this.categoryType = "Index/News/index";
			this.detailType = "Index/News/detail";
			this.assign("page_action", "新闻");
			return true;
		} else {
			return false;
		}
	}
}
