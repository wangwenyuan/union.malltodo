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

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.Http;
import com.javatodo.core.tools.T;

import admin.home.Index.CommonController;
import common.database.RENOVATION;
import freemarker.template.TemplateException;

public class ShowLink extends CommonController {

	protected String platform = "";

	public void indexPage() throws IOException, ServletException, TemplateException {
		Map<String, String> linkMap = new LinkedHashMap();
		// 官网相关链接
		for (String key : Common.home_menu.keySet()) {
			linkMap.put(Common.home_menu.get(key), T.U(key, "index.jsp"));
		}
		this.assign("linkMap", linkMap);
		this.listPage();
	}

	public void unionPage() throws IOException, ServletException, TemplateException {
		Map<String, String> linkMap = new LinkedHashMap();
		linkMap.put("首页", T.U("Index/Index/index", "index.union.jsp", request));
		linkMap.put("商品栏目", T.U("Index/Index/menu", "index.union.jsp", request));
		linkMap.put("会员中心", T.U("User/Index/index", "index.union.jsp", request));
		linkMap.put("我的订单", T.U("User/Order/index", "index.union.jsp", request));
		linkMap.put("推广海报", T.U("Index/Qr/index", "index.union.jsp", request));
		linkMap.put("我的团队", T.U("User/Team/index", "index.union.jsp", request));
		linkMap.put("我的收益", T.U("User/Income/index", "index.union.jsp", request));
		linkMap.put("资金记录", T.U("User/MoneyLog/index", "index.union.jsp", request));
		linkMap.put("我要提现", T.U("User/Withdrawal/index", "index.union.jsp", request));
		linkMap.put("基本信息设置", T.U("User/Set/index", "index.union.jsp", request));
		this.assign("linkMap", linkMap);
		this.listPage();
	}

	public void categoryPage() throws IOException, ServletException, TemplateException {
		this.listPage();
	}

	public void meituanPage() throws IOException, ServletException, TemplateException {
		Http http = new Http();
		String meituan_json_string = http.get(Common.unionHost + "/api.jsp?m=Index&c=Meituan&a=index");
		JSONArray list = JSONArray.parseArray(meituan_json_string);
		this.assign("list", list);
		this.listPage();
	}

	public void customPage() throws SQLException, IOException, ServletException, TemplateException {
		Map<String, W> where = new HashMap<String, W>();
		where.put(RENOVATION.is_del, new W("eq", 0));
		where.put(RENOVATION.type, new W("eq", "Index/Index/custom"));
		where.put(RENOVATION.platform, new W("eq", "pc"));
		List<Map<String, Object>> list = new MU(RENOVATION._table_name).where(where).order(RENOVATION.id + " desc").field(RENOVATION.id + "," + RENOVATION.name).select();
		JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
		this.assign("list", jsonArray);
		this.listPage();
	}

	public void unioncustomPage() throws SQLException, IOException, ServletException, TemplateException {
		Map<String, W> where = new HashMap<String, W>();
		where.put(RENOVATION.is_del, new W("eq", 0));
		where.put(RENOVATION.type, new W("eq", "Union/Index/custom"));
		where.put(RENOVATION.platform, new W("eq", "mobile"));
		List<Map<String, Object>> list = new MU(RENOVATION._table_name).where(where).order(RENOVATION.id + " desc").field(RENOVATION.id + "," + RENOVATION.name).select();
		JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
		this.assign("list", jsonArray);
		this.listPage();
	}

	private void listPage() throws IOException, ServletException, TemplateException {
		String href_dom_id = I("href_dom_id");
		this.assign("href_dom_id", href_dom_id);
		this.display("Renovation/ShowLink/list");
	}
}
