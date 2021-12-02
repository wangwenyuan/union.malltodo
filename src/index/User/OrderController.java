/**
 * malltodo
 * ============================================================================
 * * 版权所有 2021-2071 郑州掌勺信息技术有限公司，并保留所有权利。
 * 网站地址: http://www.malltodo.com
 * ----------------------------------------------------------------------------
 * 这不是一个自由软件！您只能在不用于商业目的的前提下对程序代码进行修改和使用 .
 * 不允许对程序代码以任何形式任何目的的再发布。
 * 如果商业用途务必到官方购买正版授权, 以免引起不必要的法律纠纷.
 * ============================================================================
 * 郑州掌勺信息技术有限公司 2021-09-01
 * 业务电话：13598851835（微信同号） 
 */
package index.User;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.javatodo.core.model.W;

import common.MU;
import common.MobileWidget;
import common.database.RENOVATION;

public class OrderController extends UserCommonController {
	public void indexPage() throws SQLException, UnsupportedEncodingException {
		Map<String, W> where = new HashMap<String, W>();
		where.put(RENOVATION.type, new W("eq", "User/Order/index"));
		where.put(RENOVATION.is_default, new W("eq", 1));
		where.put(RENOVATION.is_del, new W("eq", 0));
		Map<String, Object> map = new MU(RENOVATION._table_name).where(where).find();
		if (map != null) {
			String html = MobileWidget.buildPage(map.get(RENOVATION.id).toString(), webRequestParam);
			this.htmlDisplay(html);
		} else {
			this.htmlDisplay(MobileWidget.noTemplateNotice());
		}
	}

	public void orderListPage() throws SQLException, UnsupportedEncodingException {
		Map<String, W> where = new HashMap<String, W>();
		where.put(RENOVATION.type, new W("eq", "User/Order/index"));
		where.put(RENOVATION.is_default, new W("eq", 1));
		where.put(RENOVATION.is_del, new W("eq", 0));
		Map<String, Object> map = new MU(RENOVATION._table_name).where(where).find();
		if (map != null) {
			String html = MobileWidget.buildList(map.get(RENOVATION.id).toString(), webRequestParam);
			this.htmlDisplay(html);
		} else {
			this.htmlDisplay(MobileWidget.noTemplateNotice());
		}
	}
}
