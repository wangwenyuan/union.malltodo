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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.javatodo.config.C;
import com.javatodo.core.router.RC;
import com.javatodo.core.tools.NativeUtils;
import com.javatodo.core.tools.RSATools;
import com.javatodo.core.tools.T;
import com.javatodo.core.tools.Template;

public class Common {

	static {
		try {
			String osName = System.getProperty("os.name");
			String bitString = System.getProperty("sun.arch.data.model");
			if (osName.toUpperCase().contains("WINDOWS")) {
				if (bitString.contains("64")) {
					NativeUtils.loadLibraryFromJar("/libjavatodotemplate/libjavatodotemplate_64.dll");
				}
				if (bitString.contains("32")) {
					NativeUtils.loadLibraryFromJar("/libjavatodotemplate/libjavatodotemplate_32.dll");
				}
			}

			if (osName.toUpperCase().contains("LINUX")) {
				if (bitString.contains("64")) {
					NativeUtils.loadLibraryFromJar("/libjavatodotemplate/libjavatodotemplate_64.so");
				}
				if (bitString.contains("32")) {
					NativeUtils.loadLibraryFromJar("/libjavatodotemplate/libjavatodotemplate_32.so");
				}
			}

		} catch (IOException e) {
			// This is probably not the best way to handle exception :-)
			e.printStackTrace();
		}
	}

	public static String unionHost = "http://union.malltodo.com";

	public static Template template = null;

	public static void Init(String config_file_path) {
		_init(config_file_path);
	}

	public static void Init(HttpServletRequest request, HttpServlet servlet) {
		_init(servlet.getServletContext().getRealPath("/") + "WEB-INF/config/db.property");
		if (template == null && request != null && servlet != null) {
			template = new Template(request, servlet);
			if (!Common.template.getClassName().equals("common.Widget")) {
				Common.unionHost = "http://ceshi.union.malltodo.com";
			}
		}
	}

	private static void _init(String config_file_path) {
		if (C.db_host.length == 0) {
			File db_config = new File(config_file_path);
			if (!db_config.exists()) {
				return;
			}
			String privateKey = T.getProperties(config_file_path, "privateKey");
			if (privateKey == null || privateKey.equals("")) {
				return;
			}
			String db_host = T.getProperties(config_file_path, "db_host");
			if (db_host == null || db_host.equals("")) {
				return;
			}
			String db_username = T.getProperties(config_file_path, "db_username");
			if (db_username == null || db_username.equals("")) {
				return;
			}
			String db_password = T.getProperties(config_file_path, "db_password");
			if (db_password == null || db_password.equals("")) {
				return;
			}
			String db_name = T.getProperties(config_file_path, "db_name");
			if (db_name == null || db_name.equals("")) {
				return;
			}
			String db_port = T.getProperties(config_file_path, "db_port");
			if (db_port == null || db_port.equals("")) {
				return;
			}
			db_host = RSATools.decrypt(privateKey, db_host);
			db_username = RSATools.decrypt(privateKey, db_username);
			db_password = RSATools.decrypt(privateKey, db_password);
			db_name = RSATools.decrypt(privateKey, db_name);
			db_port = RSATools.decrypt(privateKey, db_port);

			C.db_host = null;
			C.db_host = new String[] { db_host };
			C.db_name = null;
			C.db_name = new String[] { db_name };
			C.db_username = null;
			C.db_username = new String[] { db_username };
			C.db_password = null;
			C.db_password = new String[] { db_password };
			C.db_port = new String[] { db_port };
			C.table_pre = null;
			C.table_pre = new String[] { "javatodo_" };
			C.UploadMaxSize = 1024 * 1024 * 50;
		}
		new RC("Index", "index");
	}

	public static JSONObject success(Object object) {
		Map<String, Object> map = new HashMap<>();
		map.put("status", "1");
		map.put("info", object);
		return new JSONObject(map);
	}

	public static JSONObject error(Object object) {
		Map<String, Object> map = new HashMap<>();
		map.put("status", "0");
		map.put("info", object);
		return new JSONObject(map);
	}
}