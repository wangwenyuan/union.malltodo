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
import java.util.LinkedHashMap;
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

	public static boolean hasMobile = false;

	public static void Init(String config_file_path) {
		_init(config_file_path);
	}

	public static void Init(HttpServletRequest request, HttpServlet servlet) {
		_init(servlet.getServletContext().getRealPath("/") + "WEB-INF/config/db.property");
		if (template == null && request != null && servlet != null) {
			template = new Template(request, servlet);
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

	public static Map<String, String> home_menu = new LinkedHashMap<String, String>() {
		{
			put("Index/Index/index", "首页");
			put("Index/News/index", "新闻中心");
			put("Index/Product/index", "产品中心");
			put("Index/Brief/index", "公司简介");
			put("Index/Business/index", "业务范围");
			put("Index/Case/index", "应用案例");
			put("Index/Album/index", "公司相册");
			put("Index/Message/index", "客户留言");
			put("Index/Job/index", "人力招聘");
			put("Index/ContactUs/index", "联系我们");
			put("Index/Category/index", "其他栏目");
		}
	};

	public static Map<String, String> order_dish_menu = new LinkedHashMap<String, String>() {
		{
			put("Index/Index/index", "点餐首页");
			put("Index/Foods/index", "菜品列表");
			put("Index/Cart/index", "购物车");
			put("Index/User/index", "会员中心");
			put("Index/Order/index", "订单中心");
			put("Index/Coupon/index", "我的优惠券");
			put("Index/CouponCenter/index", "领券中心");
			put("Index/Set/index", "会员信息设置");
			put("Index/MoneyLog/index", "资金记录");
		}
	};

	public static JSONObject detail_recommend_level_json = new JSONObject(new LinkedHashMap<String, Object>()) {
		{
			put("0", "不推荐");
			put("1", "一级推荐");
			put("2", "二级推荐");
			put("3", "三级推荐");
			put("4", "四级推荐");
			put("5", "五级推荐");
			put("6", "六级推荐");
			put("7", "七级推荐");
			put("8", "八级推荐");
			put("9", "九级推荐");
		}
	};

	// root_domain中必须是根域名，不能带有"www"
	public static JSONObject root_domain = new JSONObject(new LinkedHashMap<>()) {
		{
			put("malltodo.com", new JSONObject(new LinkedHashMap<String, Object>()) {
				{
					put("api_key", "nSHclRr6omJ5f4hZIDIyu8ZUHM1A4Z7j");
					put("url", "http://127.0.0.1:8888/");
					put("sourceApplication", "/malltodo");
					put("master_domain", "www.malltodo.com");
				}
			});
			put("xn--c-qq1dw6c.cn", new JSONObject(new LinkedHashMap<String, Object>()) {
				{
					put("api_key", "TEG2B6JoZ4yx0ybePPcSCpmOt2DKA1dt");
					put("url", "http://127.0.0.1:8888/");
					put("sourceApplication", "/malltodo");
					put("master_domain", "malltodo.xn--c-qq1dw6c.cn");
				}
			});
			put("127.0.0.1", new JSONObject(new LinkedHashMap<String, Object>()) {
				{
					put("api_key", "TEG2B6JoZ4yx0ybePPcSCpmOt2DKA1dt");
					put("url", "http://malltodo.xn--c-qq1dw6c.cn:8888/");
					put("sourceApplication", "/malltodo");
					put("master_domain", "127.0.0.1");
				}
			});
		}
	};

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