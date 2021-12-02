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
package admin.Index;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.javatodo.core.controller.Controller;
import com.javatodo.core.model.W;

import common.MU;
import common.database.ADMIN_AUTH;

public class CommonController extends Controller {
	public Boolean init() {
		try {
			if (CONTROLLER_NAME.equals("Index") && (ACTION_NAME.equals("login") || ACTION_NAME.equals("signOut") || ACTION_NAME.equals("verify"))) {
				return true;
			}
			if (session("admin_id") == null || session("admin_id").equals("")) {
				redirect(U("Index/Index/login"));
				return false;
			} else {
				return this.auth();
			}
		} catch (Exception e) {
			return false;
		}
	}

	private Boolean auth() throws SQLException, IOException {
		String group_id = session("group_id").toString().trim();
		if (group_id.equals("0")) {
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
		where.put(ADMIN_AUTH.group_id, new W("eq", group_id));
		where.put(ADMIN_AUTH.m, new W("eq", MODULE_NAME));
		where.put(ADMIN_AUTH.c, new W("eq", CONTROLLER_NAME));
		where.put(ADMIN_AUTH.a, new W("eq", ACTION_NAME));
		Map<String, Object> info = new MU(ADMIN_AUTH._table_name).where(where).find();
		if (info == null) {
			this.error("您没有该权限，无法执行此操作");
			return false;
		} else {
			return true;
		}
	}
}
