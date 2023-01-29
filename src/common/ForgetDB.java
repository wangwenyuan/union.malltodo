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
package common;

import java.io.File;
import java.util.Map;

import com.javatodo.core.tools.RSATools;
import com.javatodo.core.tools.T;

public class ForgetDB {
	public static void getDBConfig(String db_property_path) {
		File db_config = new File(db_property_path);
		if (!db_config.exists()) {
			System.out.println("配置文件不存在或路径错误");
			return;
		}
		String privateKey = T.getProperties(db_property_path, "privateKey");
		if (privateKey == null || privateKey.equals("")) {
			return;
		}
		String db_host = T.getProperties(db_property_path, "db_host");
		if (db_host == null || db_host.equals("")) {
			return;
		}
		String db_username = T.getProperties(db_property_path, "db_username");
		if (db_username == null || db_username.equals("")) {
			return;
		}
		String db_password = T.getProperties(db_property_path, "db_password");
		if (db_password == null || db_password.equals("")) {
			return;
		}
		String db_name = T.getProperties(db_property_path, "db_name");
		if (db_name == null || db_name.equals("")) {
			return;
		}
		String db_port = T.getProperties(db_property_path, "db_port");
		if (db_port == null || db_port.equals("")) {
			return;
		}
		db_host = RSATools.decrypt(privateKey, db_host);
		db_username = RSATools.decrypt(privateKey, db_username);
		db_password = RSATools.decrypt(privateKey, db_password);
		db_name = RSATools.decrypt(privateKey, db_name);
		db_port = RSATools.decrypt(privateKey, db_port);

		System.out.println("数据库地址：" + db_host);
		System.out.println("数据库端口：" + db_port);
		System.out.println("数据库名：" + db_name);
		System.out.println("数据库用户名：" + db_username);
		System.out.println("数据库密码：" + db_password);
	}

	public static void createNewDBConfig(String db_host, String db_port, String db_name, String db_username, String db_password) {
		Map<String, String> map = RSATools.createGenerateKeyPair();
		T.coverFile("./db.property", "");
		T.writeFile("./db.property", "privateKey=" + map.get("privateKey") + "\n");
		T.writeFile("./db.property", "db_host=" + RSATools.encrypt(map.get("publicKey"), db_host) + "\n");
		T.writeFile("./db.property", "db_username=" + RSATools.encrypt(map.get("publicKey"), db_username) + "\n");
		T.writeFile("./db.property", "db_password=" + RSATools.encrypt(map.get("publicKey"), db_password) + "\n");
		T.writeFile("./db.property", "db_name=" + RSATools.encrypt(map.get("publicKey"), db_name) + "\n");
		T.writeFile("./db.property", "db_port=" + RSATools.encrypt(map.get("publicKey"), db_port) + "\n");
	}

	public static void main(String[] args) {
		/* 忘记数据库用户名密码时可执行下面的方法进行查看 */ // 注：数据库配置文件的默认路径是："项目目录/WEB-INF/config/db.property"
		// ForgetDB.getDBConfig("数据库配置文件路径");
		/* 当数据库用户名密码等信息修改时执行下面的方法 */ // 注：该方法在项目根目录生成一个新的db.property文件，然后使用该文件替换原来的db.property文件
		createNewDBConfig("127.0.0.1", "3306", "db_name", "db_username", "db_password");
	}
}
