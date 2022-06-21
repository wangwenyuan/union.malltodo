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
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import com.javatodo.core.model.W;

import common.Functions;
import common.MU;
import common.database.ADMIN;
import freemarker.template.TemplateException;

public class IndexController extends CommonController {

	public void indexPage() throws IOException, ServletException, TemplateException, ParseException, SQLException {

		this.display();
	}

	public void loginPage() throws IOException, ServletException, TemplateException, SQLException {
		if (IS_POST) {
			String verify = I("verify");
			if (!this.check_verify(verify)) {
				this.error("验证码输入错误");
				return;
			}
			String username = I("username").trim();
			if (username.equals("")) {
				this.error("用户名不能为空");
				return;
			}
			String password = I("password").trim();
			if (password.equals("")) {
				this.error("密码不能为空");
				return;
			}
			Map<String, W> where = new HashMap<String, W>();
			where.put(ADMIN.username, new W("eq", username));
			where.put(ADMIN.password, new W("eq", Functions.create_password(password)));
			Map<String, Object> info = new MU(ADMIN._table_name).where(where).find();
			if (info == null) {
				this.error("用户名或密码错误");
			} else {
				session("admin_id", info.get(ADMIN.id).toString());
				session("admin_name", info.get(ADMIN.username).toString());
				session("group_id", info.get(ADMIN.group_id).toString());
				this.success("登录成功", U("Index/Index/index"));
			}
		} else {
			this.display();
		}
	}

	public void verifyPage() throws IOException {
		super.Verify();
	}

	public void signOutPage() throws IOException {
		session(null);
		this.success("退出成功", U("index/Index/login"));
	}
}
