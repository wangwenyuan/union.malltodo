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
package admin.Finance;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.Page;

import admin.Index.CommonController;
import common.Functions;
import common.MU;
import common.database.MEMBER;
import common.database.WITHDRAWALS;
import freemarker.template.TemplateException;
import model.Balance;
import model.Weixin;

public class IndexController extends CommonController {
	public void indexPage() throws IOException, ServletException, TemplateException, SQLException {
		Map<String, W> where = new HashMap<String, W>();
		long count = new MU(WITHDRAWALS._table_name).alias("w").where(where).count();
		Page page = this.page(count, 16);
		List<Map<String, Object>> list = new MU(WITHDRAWALS._table_name).alias("w").join(MEMBER._table_name, "as m on w.uid = m.id", "left").where(where).limit(page.firstRow + "," + page.listRows).order(WITHDRAWALS.id + " desc").field("w.*, m.username").select();
		this.assign("list", JSONArray.parseArray(JSON.toJSONString(list)));
		this.assign("page", page.show());
		this.display();
	}

	public void examinePage() throws SQLException, IOException, ServletException, TemplateException, UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
		String id = I("id");
		Map<String, W> where = new HashMap<>();
		where.put("w." + WITHDRAWALS.id, new W("eq", id));
		Map<String, Object> info = new MU(WITHDRAWALS._table_name).alias("w").join(MEMBER._table_name, "as m on w.uid = m.id", "left").field("w.*, m.username").where(where).find();
		if (info == null) {
			this.error("不存在该提现申请");
			return;
		}
		if (!info.get(WITHDRAWALS.examine_status).toString().equals("0")) {
			this.error("该提现已进行过审核操作");
			return;
		}
		if (IS_POST) {
			Map<String, String> save_data = Functions.trim_array(I());
			if (save_data.get(WITHDRAWALS.examine_status).toString().equals("1")) {
				save_data.put(WITHDRAWALS.no_pass_reason, "");
			}
			if (save_data.get(WITHDRAWALS.examine_status).toString().equals("-1")) {
				if (save_data.get(WITHDRAWALS.no_pass_reason).toString().trim().equals("")) {
					this.error("请填写不通过原因");
					return;
				}
			}
			save_data.put(WITHDRAWALS.examine_admin_id, session("admin_id").toString());
			save_data.put(WITHDRAWALS.examine_status, save_data.get(WITHDRAWALS.examine_status).toString());
			save_data.put(WITHDRAWALS.examine_time, String.valueOf(System.currentTimeMillis()));
			if (save_data.get(WITHDRAWALS.examine_status).equals("1")) {
				JSONObject ret = Weixin.wechat_mch_pay(info.get(WITHDRAWALS.uid).toString(), info.get(WITHDRAWALS.id).toString(), servlet.getServletContext().getRealPath("/") + "WEB-INF" + "/config/apiclient_cert.p12");
				if (ret.getString("status").equals("0")) {
					this.error(ret.getString("info"));
					return;
				} else {
					save_data.put(WITHDRAWALS.serial_number, "");
				}
			}
			Balance.AdminMemberWithdrawal(save_data.get(WITHDRAWALS.examine_admin_id), info.get(WITHDRAWALS.id).toString(), save_data.get(WITHDRAWALS.examine_status), save_data.get(WITHDRAWALS.no_pass_reason));
			this.success("审核完成");
		} else {
			this.assign("info", JSONObject.parseObject(JSON.toJSONString(info)));
			this.display();
		}
	}
}
