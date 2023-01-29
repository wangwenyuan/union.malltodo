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
package admin.home.Index;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.javatodo.core.controller.Controller;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.T;

import common.Functions;
import common.MU;
import common.database.ROLE_AUTH;
import common.database.WEBSITE;

public class CommonController extends Controller {
	public Boolean init() {
		try {
			if (CONTROLLER_NAME.equals("Index") && (ACTION_NAME.equals("auth_login") || ACTION_NAME.equals("check_username_password") || ACTION_NAME.equals("login") || ACTION_NAME.equals("signOut") || ACTION_NAME.equals("verify"))) {
				return true;
			}

			String domain = T.getHost(request).replace("http://", "").replace("https://", "");
			if (session("website_id") == null || session("website_id").equals("")) {
				Map<String, W> where = new HashMap<>();
				where.put(WEBSITE.website_host, new W("eq", domain));
				where.put(WEBSITE.is_del, new W("eq", 0));
				Map<String, Object> website_info = new MU(WEBSITE._table_name).where(where).find();
				if (website_info == null) {// 说明当前访问网址不存在
					// 如果访问的网址不存在，就以第一个站点做为默认站点
					String website_id = Functions.getHomeDefaultWebsiteId();
					session("website_id", website_id);
				} else {
					session("website_id", website_info.get(WEBSITE.id).toString());
				}
			}

			if (session("admin_id") == null || session("admin_id").equals("")) {
				redirect(T.U("Index/Index/login", "admin.jsp"));
				return false;
			} else {
				return this.auth();
			}
		} catch (Exception e) {
			return false;
		}
	}

	private Boolean auth() throws SQLException, IOException {
		String role_id = session("role_id").toString().trim();
		if (role_id.equals("0")) {
			return true;
		}
		if (MODULE_NAME.equals("Renovation") && ACTION_NAME.equals("pageConfig")) {
			return true;
		}
		if (MODULE_NAME.equals("SystemSet") && CONTROLLER_NAME.equals("Admin") && ACTION_NAME.equals("material")) {
			return true;
		}
		if (MODULE_NAME.equals("Index") && CONTROLLER_NAME.equals("Index") && ACTION_NAME.equals("index")) {
			return true;
		}
		Map<String, W> where = new HashMap<>();
		where.put(ROLE_AUTH.role_id, new W("eq", role_id));
		where.put(ROLE_AUTH.m, new W("eq", MODULE_NAME));
		where.put(ROLE_AUTH.c, new W("eq", CONTROLLER_NAME));
		where.put(ROLE_AUTH.a, new W("eq", ACTION_NAME));
		Map<String, Object> info = new MU(ROLE_AUTH._table_name).where(where).find();
		if (info == null) {
			this.error("您没有该权限，无法执行此操作");
			return false;
		} else {
			return true;
		}
	}
}
