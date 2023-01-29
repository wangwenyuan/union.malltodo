package admin.home.Detail;

public class ProductsController extends BaseController {
	public Boolean init() {
		Boolean b = super.init();
		if (b) {
			this.categoryType = "Index/Product/index";
			this.detailType = "Index/Product/detail";
			this.assign("page_action", "产品");
			return true;
		} else {
			return false;
		}
	}
}
