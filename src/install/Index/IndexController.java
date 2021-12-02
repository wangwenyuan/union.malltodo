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
package install.Index;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import com.javatodo.core.controller.Controller;
import com.javatodo.core.model.M;
import com.javatodo.core.tools.RSATools;
import com.javatodo.core.tools.T;

import common.Common;
import common.Functions;
import common.MU;
import common.database.ADMIN;
import freemarker.template.TemplateException;

public class IndexController extends Controller {
	public void indexPage() throws IOException, ServletException, TemplateException {
		this.display();
	}

	public void secondPage() throws IOException, ServletException, TemplateException {
		this.display();
	}

	public void thirdPage() throws IOException, ServletException, TemplateException {
		if (IS_POST) {
			Map<String, String> map = RSATools.createGenerateKeyPair();
			String db_host = I("db_host").trim();
			if (db_host.equals("")) {
				this.error("数据库主机地址不能为空");
				return;
			}
			String db_username = I("db_username").trim();
			if (db_username.equals("")) {
				this.error("数据库用户名不能为空");
				return;
			}
			String db_password = I("db_password").trim();
			if (db_password.equals("")) {
				this.error("数据库密码不能为空");
				return;
			}
			String db_name = I("db_name").trim();
			if (db_name.equals("")) {
				this.error("数据库名不能为空");
				return;
			}
			String db_port = I("db_port").trim();
			if (db_port.equals("")) {
				this.error("数据库端口不能为空");
				return;
			}

			String admin_username = I("admin_username").trim();
			String admin_password = I("admin_password").trim();
			if (admin_username.equals("")) {
				this.error("用户名不能为空");
				return;
			}

			if (admin_password.equals("")) {
				this.error("密码不能为空");
				return;
			}

			T.coverFile(servlet.getServletContext().getRealPath("/") + "WEB-INF/config/db.property", "");
			T.writeFile(servlet.getServletContext().getRealPath("/") + "WEB-INF/config/db.property", "privateKey=" + map.get("privateKey") + "\n");
			T.writeFile(servlet.getServletContext().getRealPath("/") + "WEB-INF/config/db.property", "db_host=" + RSATools.encrypt(map.get("publicKey"), db_host) + "\n");
			T.writeFile(servlet.getServletContext().getRealPath("/") + "WEB-INF/config/db.property", "db_username=" + RSATools.encrypt(map.get("publicKey"), db_username) + "\n");
			T.writeFile(servlet.getServletContext().getRealPath("/") + "WEB-INF/config/db.property", "db_password=" + RSATools.encrypt(map.get("publicKey"), db_password) + "\n");
			T.writeFile(servlet.getServletContext().getRealPath("/") + "WEB-INF/config/db.property", "db_name=" + RSATools.encrypt(map.get("publicKey"), db_name) + "\n");
			T.writeFile(servlet.getServletContext().getRealPath("/") + "WEB-INF/config/db.property", "db_port=" + RSATools.encrypt(map.get("publicKey"), db_port) + "\n");

			try {
				// 连接数据库
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = null;
				conn = DriverManager.getConnection("jdbc:mysql://" + db_host + ":" + db_port + "/" + db_name + "?zeroDateTimeBehavior=convertToNull", db_username, db_password);
				// 安装数据库
				Common.Init(request, servlet);
				File file = new File(servlet.getServletContext().getRealPath("/") + "WEB-INF/db/");
				if (file.isDirectory()) {
					File[] files = file.listFiles();
					for (Integer i = 0; i < files.length; i = i + 1) {
						String file_name = files[i].getName();
						if (file_name.contains(".sql")) {
							String sql = T.readFile(servlet.getServletContext().getRealPath("/") + "WEB-INF/db/" + file_name, StandardCharsets.UTF_8.toString());
							new M().execute(sql);
						}
					}
				}
				// 初始化用户名密码
				Map<String, Object> data = new HashMap<String, Object>();
				data.put(ADMIN.username, admin_username);
				data.put(ADMIN.password, Functions.create_password(admin_password));
				new MU(ADMIN._table_name).data(data).add();
				// 生成lock文件
				T.coverFile(servlet.getServletContext().getRealPath("/") + "WEB-INF/Runtime/lock", "");
				// 提示安装成功，跳转后台
				this.success("安装成功", T.U("Index/Index/index", "admin.jsp"));
			} catch (SQLException | ClassNotFoundException e) {
				// TODO: handle exception
				// 删除数据库配置文件
				T.deleteFile(servlet.getServletContext().getRealPath("/") + "WEB-INF/config/db.property");
				this.error("数据库连接失败");
			}
			return;
		} else {
			this.display();
		}
	}

	public static String dirCanRead(String dir_path) {
		File file = new File(dir_path);
		if (file.canRead()) {
			return "<font color=\"green\">可读</font>";
		} else {
			return "<font color=\"red\">不可读</font>";
		}
	}

	public static String dirCanWrite(String dir_path) {
		File file = new File(dir_path);
		if (file.canWrite()) {
			return "<font color=\"green\">可写</font>";
		} else {
			return "<font color=\"red\">不可写</font>";
		}
	}

}
