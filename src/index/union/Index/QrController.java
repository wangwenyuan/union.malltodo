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
package index.union.Index;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.WriterException;
import com.javatodo.core.controller.Controller;
import com.javatodo.core.tools.T;

import common.MU;
import common.database.QR;
import freemarker.template.TemplateException;

public class QrController extends Controller {
	public void indexPage() throws SQLException, IOException, ServletException, TemplateException {
		if (I("pid").equals("")) {
			redirect(T.U("Index/Qr/poster", "pid=" + session("uid"), "index.union.jsp", request));
		} else {
			redirect(T.U("Index/Qr/poster", "pid=" + I("pid"), "index.union.jsp", request));
		}
		return;
	}

	public void posterPage() throws SQLException, IOException, ServletException, TemplateException {
		Map<String, Object> info = new MU(QR._table_name).order(QR.id + " desc").find();
		if (info == null) {
			info = new HashMap<>();
			info.put(QR.bgimg, T.getRootUrl(request) + "/Public/images/poster.jpg");
			info.put(QR.bgimg_width, 304);
			info.put(QR.bgimg_height, 456);
			info.put(QR.qrimg_width, 120);
			info.put(QR.qrimg_height, 120);
			info.put(QR.qrimg_left, 92);
			info.put(QR.qrimg_top, 250);
		}
		info.put("qrcode", T.U("Index/Qr/qrcode", "pid=" + I("pid"), "index.union.jsp", request));
		this.assign("info", JSONObject.parse(JSON.toJSONString(info)));
		this.display();
	}

	public void qrcodePage() throws IOException, WriterException {
		response.setContentType("image/png");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		String pid = I("pid");
		com.javatodo.core.tools.QR.EncodeToStream(T.getRootUrl(request) + "?pid=" + pid, response.getOutputStream());
	}
}
