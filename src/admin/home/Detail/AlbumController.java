package admin.home.Detail;

public class AlbumController extends BaseController {
	public Boolean init() {
		Boolean b = super.init();
		if (b) {
			this.categoryType = "Index/Album/index";
			this.detailType = "Index/Album/detail";
			this.assign("page_action", "公司相册");
			return true;
		} else {
			return false;
		}
	}
}
