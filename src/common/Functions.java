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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javatodo.config.C;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.Http;
import com.javatodo.core.tools.T;

import common.database.COMMISSION_SET;
import common.database.GOODS;
import common.database.MEMBER;
import common.database.WEBSITE;
import union.Jd;
import union.Pdd;

public class Functions {
	public static String header(String host) {
		String ext = "";
		if (C.is_debug) {
			ext = "?t=006";
		}
		String resource = "";
		resource = "<link rel=\"stylesheet\" type=\"text/css\" href=\"" + host + "/Public/css/css.css" + ext + "\"/>\n";
		resource = resource + "<script type=\"text/javascript\" src=\"" + host + "/Public/js/jquery-1.12.4.min.js" + ext + "\"></script>\n";
		resource = resource + "<script type=\"text/javascript\" src=\"" + host + "/Public/js/jquery.slimscroll.min.js" + ext + "\"></script>\n";
		resource = resource + "<link rel=\"stylesheet\" type=\"text/css\" href=\"" + host + "/Public/css/pintuer.css" + ext + "\" />\n";
		resource = resource + "<link rel=\"stylesheet\" type=\"text/css\" href=\"" + host + "/Public/css/animate.min.css" + ext + "\" />\n";
		resource = resource + "<script type=\"text/javascript\" src=\"" + host + "/Public/js/jquery.cookie.js" + ext + "\"></script>\n";
		resource = resource + "<link rel=\"stylesheet\" type=\"text/css\" href=\"" + host + "/Public/css/zTreeStyle/zTreeStyle.css" + ext + "\" />\n";
		resource = resource + "<script type=\"text/javascript\" src=\"" + host + "/Public/js/jquery.ztree.all.min.js" + ext + "\"></script>\n";
		resource = resource + "<script type=\"text/javascript\" src=\"" + host + "/Public/js/layer.js" + ext + "\"></script>\n";
		resource = resource + "<script type=\"text/javascript\" src=\"" + host + "/Public/js/http.js" + ext + "\"></script>\n";
		resource = resource + "<script type=\"text/javascript\" src=\"" + host + "/Public/js/drop.js" + ext + "\"></script>\n";
		resource = resource + "<script type=\"text/javascript\" src=\"" + host + "/Public/js/js.js" + ext + "\"></script>\n";
		resource = resource + "<script type=\"text/javascript\" src=\"" + host + "/Public/jedate/jquery.jedate.js" + ext + "\"></script>\n";
		resource = resource + "<link type=\"text/css\" rel=\"stylesheet\" href=\"" + host + "/Public/jedate/skin/jedate.css" + ext + "\">\n";
		resource = resource + "<link href=\"" + host + "/Public/ui-c/form/form.css" + ext + "\" rel=\"stylesheet\" type=\"text/css\" />\n";
		resource = resource + "<link href=\"" + host + "/Public/ui-c/form/select.css" + ext + "\" rel=\"stylesheet\" type=\"text/css\" />\n";
		resource = resource + "<link href=\"" + host + "/Public/ui-c/form/radio.css" + ext + "\" rel=\"stylesheet\" type=\"text/css\" />\n";
		resource = resource + "<link href=\"" + host + "/Public/ui-c/form/checkbox.css" + ext + "\" rel=\"stylesheet\" type=\"text/css\" />\n";
		resource = resource + "<link href=\"" + host + "/Public/ui-c/form/open.css" + ext + "\" rel=\"stylesheet\" type=\"text/css\" />\n";
		resource = resource + "<script src=\"" + host + "/Public/ui-c/ui-c.js" + ext + "\"></script>\n";
		resource = resource + "<script type=\"text/javascript\" src=\"" + host + "/Public/ui-c/form/form.js" + ext + "\"></script>\n";
		resource = resource + "<script type=\"text/javascript\" src=\"" + host + "/Public/ui-c/form/select.js" + ext + "\"></script>\n";
		resource = resource + "<script type=\"text/javascript\" src=\"" + host + "/Public/ui-c/form/radio.js" + ext + "\"></script>\n";
		resource = resource + "<script type=\"text/javascript\" src=\"" + host + "/Public/ui-c/form/checkbox.js" + ext + "\"></script>\n";
		resource = resource + "<script type=\"text/javascript\" src=\"" + host + "/Public/ui-c/form/open.js" + ext + "\"></script>\n";
		resource = resource + "<script type=\"text/javascript\" src=\"" + host + "/Public/js/jquery-ui.min.js" + ext + "\"></script>\n";
		resource = resource + "<link rel=\"stylesheet\" type=\"text/css\" href=\"" + host + "/Public/css/jquery-ui.css" + ext + "\" />\n";
		resource = resource + "<script type=\"text/javascript\" src=\"" + host + "/Public/editor/ueditor.config.js" + ext + "\"></script>\n";
		resource = resource + "<script type=\"text/javascript\" src=\"" + host + "/Public/editor/ueditor.all.js" + ext + "\"></script>\n";
		resource = resource + "<script type=\"text/javascript\" src=\"" + host + "/Public/js/vue.min.js" + ext + "\"></script>\n";
		resource = resource + "<script>\n";
		resource = resource + "function getObjectURL(file) {\n";
		resource = resource + "var url = null;\n";
		resource = resource + "if (window.createObjectURL != undefined) { // basic\n";
		resource = resource + "url = window.createObjectURL(file);\n";
		resource = resource + "} else if (window.URL != undefined) { // mozilla(firefox)\n";
		resource = resource + "url = window.URL.createObjectURL(file);\n";
		resource = resource + "} else if (window.webkitURL != undefined) { // webkit or chrome\n";
		resource = resource + "url = window.webkitURL.createObjectURL(file);\n";
		resource = resource + "}\n";
		resource = resource + "return url;\n";
		resource = resource + "}\n";
		resource = resource + "</script>\n";
		return resource;
	}

	public static Map<String, Object> I_TO_MAP(Map<String, String> map) {
		Map<String, Object> data = new HashMap<>();
		for (String key : map.keySet()) {
			data.put(key, map.get(key).trim());
		}
		return data;
	}

	public static String create_password(String password) {
		return T.md5("malltodo" + T.md5("javatodo" + password));
	}

	public static boolean check_password(String password) {
		if (password.length() < 8) {
			return false;
		}
		String[] daxie = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		String[] xiaoxie = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
		String[] shuzi = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		boolean daxie_flag = false;
		boolean xiaoxie_flag = false;
		boolean shuzi_flag = false;
		for (Integer i = 0; i < daxie.length; i = i + 1) {
			if (password.contains(daxie[i])) {
				daxie_flag = true;
				break;
			}
		}
		for (Integer i = 0; i < xiaoxie.length; i = i + 1) {
			if (password.contains(xiaoxie[i])) {
				xiaoxie_flag = true;
				break;
			}
		}
		for (Integer i = 0; i < shuzi.length; i = i + 1) {
			if (password.contains(shuzi[i])) {
				shuzi_flag = true;
				break;
			}
		}
		if (daxie_flag && xiaoxie_flag && shuzi_flag) {
			return true;
		} else {
			return false;
		}
	}

	public static Map<String, String> trim_array(Map<String, String> map) {
		Map<String, String> _map = new HashMap<>();
		for (String key : map.keySet()) {
			_map.put(key, map.get(key).trim());
		}
		return _map;
	}

	public static String getMenuCategoryName(HttpServletRequest request) {
		String jsp_file = request.getRequestURI();
		// System.out.println(jsp_file);
		String menu_category_name = "home";
		if (jsp_file.contains("admin.home")) {
			menu_category_name = "home";
		} else if (jsp_file.contains("store.home") || jsp_file.contains("store.jsp")) {
			menu_category_name = "operation";
		} else if (jsp_file.contains("admin.union") || jsp_file.contains("store.union")) {
			menu_category_name = "union";
		} else if (jsp_file.contains("admin.system") || jsp_file.contains("store.system")) {
			menu_category_name = "system";
		}
		return menu_category_name;
	}

	public static String getMenuCategoryShow(HttpServletRequest request) {
		String jsp_file = request.getRequestURI();
		String menu_category_show = "营销型官网";
		if (jsp_file.contains("admin.home")) {
			menu_category_show = "营销型官网";
		} else if (jsp_file.contains("admin.union") || jsp_file.contains("store.union")) {
			menu_category_show = "电商联盟";
		} else if (jsp_file.contains("admin.system") || jsp_file.contains("store.system")) {
			menu_category_show = "设置";
		}
		return menu_category_show;
	}

	public static String createPriKey() {
		return T.getPriKey();
	}

	public static String createOrderSN(String store_id, String pre) {
		if (pre.equals("")) {
			pre = "MA";
		}
		String order_sn = pre + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "S" + store_id;
		order_sn = order_sn + "R";
		for (Integer i = 0; i < 5; i = i + 1) {
			Integer mathCode = (int) (Math.random() * 8) + 1;
			order_sn = order_sn + "" + mathCode;
		}
		return order_sn;
	}

	public static Map<String, String> getALLHeader(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key.toLowerCase(), value);
		}
		return map;
	}

	public static String getHeader(String name, HttpServletRequest request) {
		Map<String, String> map = getALLHeader(request);
		if (map.containsKey(name.toLowerCase())) {
			return map.get(name.toLowerCase());
		} else {
			return null;
		}
	}

	public static void createZip(ZipOutputStream zip, File file, String dir) throws IOException {
		if (file.exists()) {
			// 如果当前的是文件夹，则进行进一步处理
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				zip.putNextEntry(new ZipEntry(dir + "/"));
				dir = dir.length() == 0 ? "" : dir + "/";
				for (int i = 0; i < files.length; i++) {
					if (files[i].isDirectory()) {
						createZip(zip, files[i], dir + files[i].getName());
					} else {
						createZip(zip, files[i], dir);
					}
				}
			} else { // 当前的是文件，打包处理
				BufferedInputStream bis = null;
				try {
					bis = new BufferedInputStream(new FileInputStream(file));
					ZipEntry entry = new ZipEntry(dir + "/" + file.getName());
					zip.putNextEntry(entry);
					zip.write(org.apache.commons.io.FileUtils.readFileToByteArray(file));
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (bis != null) {
						bis.close();
					}
					if (zip != null) {
						zip.flush();
						zip.closeEntry();
					}
				}
			}
		}
	}

	public static String getMalltodoNotice(HttpServletRequest request) {
		String host = T.getHost(request);
		Http http = new Http();
		if (host.contains("127.0.0.1") || host.contains("localhost") || host.contains("demo.") || host.contains("ceshi.")) {
			return http.get(host + "/malltodo_notice/admin.home.html");
		} else {
			return http.get("http://www.malltodo.com/malltodo_notice/admin.home.html");
		}
	}

	public static String getDomain(HttpServletRequest request) {
		return T.getHost(request).replace(request.getScheme() + "://", "").replace(":" + request.getServerPort(), "");
	}

	public static String getAuthSign(String string, Long time) {
		return T.md5("malltodo" + T.md5("malltodo" + string + time + "malltodo") + "javatodo");
	}

	public static Boolean checkAuthSign(String string, Long time, String sign) {
		if (System.currentTimeMillis() - time > 5000) {// 不能超过3秒
			return false;
		} else {
			if (T.md5("malltodo" + T.md5("malltodo" + string + time + "malltodo") + "javatodo").equals(sign)) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static String getHomeDefaultWebsiteId() throws SQLException {
		Map<String, W> where = new HashMap<>();
		where.put(WEBSITE.is_del, new W("eq", 0));
		Map<String, Object> website_info = new MU(WEBSITE._table_name).where(where).order(WEBSITE.id + " asc").find();
		if (website_info == null) {
			Map<String, Object> data = new HashMap<>();
			data.put(WEBSITE.website_host, "");
			data.put(WEBSITE.website_name, "默认站点");
			data.put(WEBSITE.addtime, System.currentTimeMillis());
			Object id = new MU(WEBSITE._table_name).data(data).add();
			return id.toString();
		} else {
			return website_info.get(WEBSITE.id).toString();
		}
	}

	public static JSONObject commission(BigDecimal money, String platform) throws SQLException {
		JSONObject ret = new JSONObject();
		ret.put("level_1_money", new BigDecimal(0));
		ret.put("level_2_money", new BigDecimal(0));
		ret.put("level_3_money", new BigDecimal(0));
		ret.put(COMMISSION_SET.is_Internal_purchase, 0);
		Map<String, W> where = new HashMap<>();
		where.put(COMMISSION_SET.platform, new W("eq", platform));
		Map<String, Object> info = new MU(COMMISSION_SET._table_name).where(where).find();
		if (info == null) {
			return ret;
		} else {
			BigDecimal level_1 = new BigDecimal(info.get(COMMISSION_SET.level_1).toString());
			BigDecimal level_2 = new BigDecimal(info.get(COMMISSION_SET.level_2).toString());
			BigDecimal level_3 = new BigDecimal(info.get(COMMISSION_SET.level_3).toString());
			BigDecimal level_1_money = money.multiply(level_1).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_DOWN);
			BigDecimal level_2_money = money.multiply(level_2).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_DOWN);
			BigDecimal level_3_money = money.multiply(level_3).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_DOWN);
			ret.put("level_1_money", level_1_money);
			ret.put("level_2_money", level_2_money);
			ret.put("level_3_money", level_3_money);
			ret.put(COMMISSION_SET.is_Internal_purchase, new BigDecimal(info.get(COMMISSION_SET.is_Internal_purchase).toString()));
			return ret;
		}
	}

	public static JSONObject getPids(String member_id) throws SQLException {
		JSONObject ret = new JSONObject();
		String pid = "0";
		String ppid = "0";
		String pppid = "0";
		ret.put("pid", pid);
		ret.put("ppid", ppid);
		ret.put("pppid", pppid);
		Map<String, W> where = new HashMap<>();
		where.put(MEMBER.id, new W("eq", member_id));
		where.put(MEMBER.is_del, new W("eq", 0));
		Object pid_obj = new MU(MEMBER._table_name).where(where).getField(MEMBER.pid);
		if (pid_obj != null) {
			pid = pid_obj.toString();
		}
		where.clear();
		where.put(MEMBER.id, new W("eq", pid));
		where.put(MEMBER.is_del, new W("eq", 0));
		Object ppid_obj = new MU(MEMBER._table_name).where(where).getField(MEMBER.pid);
		if (ppid_obj != null) {
			ppid = ppid_obj.toString();
		}
		where.clear();
		where.put(MEMBER.id, new W("eq", ppid));
		where.put(MEMBER.is_del, new W("eq", 0));
		Object pppid_obj = new MU(MEMBER._table_name).where(where).getField(MEMBER.pid);
		if (pppid_obj != null) {
			pppid = pppid_obj.toString();
		}
		ret.put("pid", pid);
		ret.put("ppid", ppid);
		ret.put("pppid", pppid);
		return ret;
	}

	public static JSONObject getGoodsDetail(String goods_id, String goods_sign, String uid, String search_id, String platform) {
		if (T.S(platform + "_" + uid + "_" + goods_id + T.date("yyyy-MM-dd", System.currentTimeMillis())).equals("")) {
			JSONObject retObj = new JSONObject();
			if (platform.equals("pdd")) {
				retObj = new Pdd().getGoodsDetail(goods_id, goods_sign, uid, search_id);
			}
			if (platform.equals("jd")) {
				retObj = new Jd().getGoodsDetail(goods_id, goods_sign, uid, search_id);
			}
			if (retObj == null) {
				return null;
			}
			if (retObj.getInteger("status") == 1) {
				T.S(platform + "_" + uid + "_" + goods_id + T.date("yyyy-MM-dd", System.currentTimeMillis()), retObj.toJSONString());
			} else {
				return retObj;
			}
		}
		String string = T.S(platform + "_" + uid + "_" + goods_id + T.date("yyyy-MM-dd", System.currentTimeMillis()));
		JSONObject object = JSONObject.parseObject(string);
		return object;
	}

	public static JSONArray getGoodsList(String url, String uid, HttpServletRequest request) throws SQLException {
		Http http = new Http();
		String ret = http.get(url);
		JSONObject retObj = JSONObject.parseObject(ret);
		// System.out.print(retObj);
		if (retObj == null) {
			return null;
		}
		JSONArray union_goods_list = retObj.getJSONArray("info");
		JSONObject pdd_json = new Pdd().getGoodsList(union_goods_list);
		JSONObject jd_json = new Jd().getGoodsList(union_goods_list);

		JSONArray goods_list = new JSONArray();
		for (Integer i = 0; i < union_goods_list.size(); i = i + 1) {
			JSONObject union_goods = union_goods_list.getJSONObject(i);
			String union_platform = union_goods.getString(GOODS.platform);
			String union_goods_sign = union_goods.getString(GOODS.goods_sign);
			if (union_platform.equals("pdd") && pdd_json.containsKey(union_goods_sign)) {
				goods_list.add(pdd_json.getJSONObject(union_goods_sign));
			}
			if (union_platform.equals("jd") && jd_json.containsKey(union_goods_sign)) {
				goods_list.add(jd_json.getJSONObject(union_goods_sign));
			}
		}

		// JSONArray goods_list = retObj.getJSONArray("info");
		// T.create_log("goods_list", goods_list.toJSONString());
		for (Integer i = 0; i < goods_list.size(); i = i + 1) {
			JSONObject goods = goods_list.getJSONObject(i);
			String _url = T.U("Index/Index/detail", "goods_id=" + goods.getString(GOODS.goods_id) + "&goods_sign=" + goods.getString(GOODS.goods_sign) + "&uid=" + uid + "&search_id=" + goods.getString(GOODS.search_id) + "&platform=" + goods.getString(GOODS.platform), "index.jsp", request);
			String platform = goods.getString(GOODS.platform);

			BigDecimal promotion_money = goods.getBigDecimal(GOODS.promotion_money);
			BigDecimal level_1 = new BigDecimal(0);
			Map<String, Object> commission_set = null;

			Map<String, W> where = new HashMap<>();

			if (platform.equals("pdd")) {
				where.put(COMMISSION_SET.platform, new W("eq", "pdd"));
				platform = "拼多多";
			}
			if (platform.equals("jd")) {
				where.put(COMMISSION_SET.platform, new W("eq", "jd"));
				platform = "京东";
			}
			if (platform.equals("vip")) {
				where.put(COMMISSION_SET.platform, new W("eq", "vip"));
				platform = "唯品会";
			}
			if (platform.equals("meituan")) {
				where.put(COMMISSION_SET.platform, new W("eq", "meituan"));
				platform = "美团";
			}

			commission_set = new MU(COMMISSION_SET._table_name).where(where).find();
			if (commission_set == null) {
				promotion_money = new BigDecimal(0);
			} else {
				level_1 = new BigDecimal(commission_set.get(COMMISSION_SET.level_1).toString());
				promotion_money = promotion_money.multiply(level_1).divide(new BigDecimal(100));
				promotion_money = promotion_money.setScale(2, BigDecimal.ROUND_DOWN);
			}

			goods_list.getJSONObject(i).put(GOODS.url, _url);
			goods_list.getJSONObject(i).put(GOODS.platform, platform);
			goods_list.getJSONObject(i).put(GOODS.promotion_money, promotion_money);

			JSONObject saveobj = new JSONObject();
			saveobj.put(GOODS.promotion_money, promotion_money);
			saveobj.put(GOODS.coupon_discount, goods.getBigDecimal(GOODS.coupon_discount));
			saveobj.put(GOODS.coupon_url, goods.getString(GOODS.coupon_url));
			T.S(goods.getString(GOODS.goods_id), saveobj.toJSONString());

		}
		return goods_list;
	}
}
