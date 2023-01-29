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
package admin.union.Qr;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import com.javatodo.core.model.W;
import com.javatodo.core.tools.T;

import admin.home.Index.CommonController;
import common.MU;
import common.database.QR;
import freemarker.template.TemplateException;

public class IndexController extends CommonController {
	public void indexPage() throws IOException, ServletException, TemplateException, SQLException {
		Map<String, Object> info = new MU(QR._table_name).order(QR.id + " desc").find();
		if (IS_POST) {
			Map<String, Object> data = new HashMap<>();
			data.put(QR.bgimg, I(QR.bgimg));
			data.put(QR.bgimg_height, I(QR.bgimg_height));
			data.put(QR.bgimg_width, I(QR.bgimg_width));
			data.put(QR.qrimg_height, I(QR.qrimg_height));
			data.put(QR.qrimg_width, I(QR.qrimg_width));
			data.put(QR.qrimg_left, I(QR.qrimg_left));
			data.put(QR.qrimg_top, I(QR.qrimg_top));
			if (info == null) {
				new MU(QR._table_name).data(data).add();
			} else {
				Map<String, W> where = new HashMap<>();
				where.put(QR.id, new W("eq", info.get(QR.id)));
				new MU(QR._table_name).where(where).save(data);
			}
			this.success("设置成功");
		} else {
			if (info == null) {
				info = new HashMap<>();
				info.put(QR.bgimg, T.getRootUrl(request) + "/Public/images/poster.jpg");
				info.put(QR.qrimg_width, 120);
				info.put(QR.qrimg_height, 120);
				info.put(QR.qrimg_left, 92);
				info.put(QR.qrimg_top, 250);
			}
			this.assign("info", info);
			this.display();
		}
	}
}
