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
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.Page;
import com.javatodo.core.tools.T;

import admin.home.Index.CommonController;
import admin.union.Renovation.GoodsCategoryController;
import admin.union.Renovation.IncomeController;
import admin.union.Renovation.MoneyLogController;
import admin.union.Renovation.OrderController;
import admin.union.Renovation.SearchController;
import admin.union.Renovation.TeamController;
import common.database.RENOVATION;
import freemarker.template.TemplateException;

public class BaseRenovation extends CommonController {

	protected String type = "";
	protected String platform = "";
	protected boolean need_default = false;

	public void indexPage() throws SQLException, IOException, ServletException, TemplateException {
		Map<String, W> where = new HashMap<String, W>();
		where.put(RENOVATION.type, new W("eq", this.type));
		where.put(RENOVATION.is_del, new W("eq", 0));
		where.put(RENOVATION.platform, new W("eq", this.platform));
		where.put(RENOVATION.website_id, new W("eq", session("website_id")));
		long count = new MU(RENOVATION._table_name).where(where).count();
		Page page = this.page(count, 16);
		List<Map<String, Object>> list = new MU(RENOVATION._table_name).where(where).limit(page.firstRow + "," + page.listRows).order(RENOVATION.id + " desc").field(RENOVATION.id + "," + RENOVATION.addtime + "," + RENOVATION.title + "," + RENOVATION.name + "," + RENOVATION.is_default + "," + RENOVATION.last_edit_time).select();
		this.assign("page", page.show());
		this.assign("list", JSONArray.parse(JSON.toJSONString(list)));
		this.display("Renovation/BaseRenovation/index");
	}

	public void addPage() throws IOException, ServletException, TemplateException, SQLException {
		String id = I("id");
		Map<String, Object> info = new HashMap<>();
		Map<String, W> where = new HashMap<>();
		if (!id.equals("")) {
			where.put(RENOVATION.id, new W("eq", id));
			where.put(RENOVATION.type, new W("eq", this.type));
			where.put(RENOVATION.is_del, new W("eq", 0));
			where.put(RENOVATION.platform, new W("eq", this.platform));
			where.put(RENOVATION.website_id, new W("eq", session("website_id")));
			info = new MU(RENOVATION._table_name).where(where).find();
			if (info == null) {
				this.error("该信息不存在");
				return;
			}
		}
		if (IS_POST) {
			if (I("name").equals("")) {
				this.error("模板名称不能为空");
				return;
			}
			// 清理缓存
			if (this.type.equals("Index/Index/menu")) {
				// MenuController.clearCache();
			}
			Map<String, Object> data = new HashMap<>();
			data.put(RENOVATION.type, this.type);
			data.put(RENOVATION.doms, I("doms"));
			data.put(RENOVATION.doms_sort, I("doms_sort"));
			data.put(RENOVATION.html, MobileWidget.buildHtmlCSSTemplate(servlet.getServletContext().getRealPath("/"), T.getRootUrl(request), JSONObject.parseObject(T.htmlspecialchars_decode(I("doms"))), JSONArray.parseArray(T.htmlspecialchars_decode(I("doms_sort")))));
			data.put(RENOVATION.name, I("name"));
			data.put(RENOVATION.title, I("title"));
			data.put(RENOVATION.keywords, I("keywords"));
			data.put(RENOVATION.description, I("description"));
			data.put(RENOVATION.background_color, I("background_color"));
			data.put(RENOVATION.background_image, I("background_image"));
			data.put(RENOVATION.background_repeat, I("background_repeat"));
			data.put(RENOVATION.bottom_id, I("bottom_id"));
			data.put(RENOVATION.header_id, I("header_id"));
			data.put(RENOVATION.platform, this.platform);

			// 商品栏目页面
			if (this.type.equals("Union/Index/category")) {
				JSONObject categoryExtraObject = GoodsCategoryController.getGoodsListDom(servlet.getServletContext().getRealPath("/"), T.getRootUrl(request), JSONObject.parseObject(T.htmlspecialchars_decode(I("doms"))));
				data.put(RENOVATION.is_list, 1);
				data.put(RENOVATION.list_dom, categoryExtraObject.getString("list_dom"));
				data.put(RENOVATION.list_html, categoryExtraObject.getString("list_html"));
			}
			// 商品搜索页面
			if (this.type.equals("Union/Index/search")) {
				JSONObject searchExtraObject = SearchController.getGoodsListDom(servlet.getServletContext().getRealPath("/"), T.getRootUrl(request), JSONObject.parseObject(T.htmlspecialchars_decode(I("doms"))));
				data.put(RENOVATION.is_list, 1);
				data.put(RENOVATION.list_dom, searchExtraObject.getString("list_dom"));
				data.put(RENOVATION.list_html, searchExtraObject.getString("list_html"));
			}
			// 我的团队页面
			if (this.type.equals("User/Team/index")) {
				JSONObject searchExtraObject = TeamController.getTeamListDom(servlet.getServletContext().getRealPath("/"), T.getRootUrl(request), JSONObject.parseObject(T.htmlspecialchars_decode(I("doms"))));
				data.put(RENOVATION.is_list, 1);
				data.put(RENOVATION.list_dom, searchExtraObject.getString("list_dom"));
				data.put(RENOVATION.list_html, searchExtraObject.getString("list_html"));
			}
			// 我的团队页面
			if (this.type.equals("User/MoneyLog/index")) {
				JSONObject searchExtraObject = MoneyLogController.getMoneyLogListDom(servlet.getServletContext().getRealPath("/"), T.getRootUrl(request), JSONObject.parseObject(T.htmlspecialchars_decode(I("doms"))));
				data.put(RENOVATION.is_list, 1);
				data.put(RENOVATION.list_dom, searchExtraObject.getString("list_dom"));
				data.put(RENOVATION.list_html, searchExtraObject.getString("list_html"));
			}
			// 我的订单页面
			if (this.type.equals("User/Order/index")) {
				JSONObject searchExtraObject = OrderController.getOrderListDom(servlet.getServletContext().getRealPath("/"), T.getRootUrl(request), JSONObject.parseObject(T.htmlspecialchars_decode(I("doms"))));
				data.put(RENOVATION.is_list, 1);
				data.put(RENOVATION.list_dom, searchExtraObject.getString("list_dom"));
				data.put(RENOVATION.list_html, searchExtraObject.getString("list_html"));
			}
			// 我的收益页面
			if (this.type.equals("User/Income/index")) {
				JSONObject searchExtraObject = IncomeController.getIncomeListDom(servlet.getServletContext().getRealPath("/"), T.getRootUrl(request), JSONObject.parseObject(T.htmlspecialchars_decode(I("doms"))));
				data.put(RENOVATION.is_list, 1);
				data.put(RENOVATION.list_dom, searchExtraObject.getString("list_dom"));
				data.put(RENOVATION.list_html, searchExtraObject.getString("list_html"));
			}

			if (!id.equals("")) {
				data.put(RENOVATION.last_edit_time, System.currentTimeMillis());
				if (data.containsKey(RENOVATION.is_default)) {
					data.remove(RENOVATION.is_default);
				}
				new MU(RENOVATION._table_name).where(where).save(data);
				this.success("修改成功");
			} else {
				data.put(RENOVATION.addtime, System.currentTimeMillis());
				data.put(RENOVATION.last_edit_time, System.currentTimeMillis());

				// 判断模版数量，如果是第一个则直接设置为默认模版
				where.clear();
				where.put(RENOVATION.website_id, new W("eq", session("website_id")));
				where.put(RENOVATION.type, new W("eq", this.type));
				where.put(RENOVATION.is_default, new W("eq", 1));
				where.put(RENOVATION.is_del, new W("eq", 0));
				where.put(RENOVATION.platform, new W("eq", this.platform));
				long count = new MU(RENOVATION._table_name).where(where).count();
				if (count == 0) {
					if (this.need_default) {
						data.put(RENOVATION.is_default, 1);
					} else {
						data.put(RENOVATION.is_default, 0);
					}
				} else {
					data.put(RENOVATION.is_default, 0);
				}
				data.put(RENOVATION.website_id, session("website_id"));
				new MU(RENOVATION._table_name).data(data).add();
				this.success("添加成功");
			}
		} else {
			this.assign("info", JSONObject.parse(JSON.toJSONString(info)));
			if (this.platform.equals("pc")) {
				this.display("Renovation/BaseRenovation/add");
			} else {
				this.display("Renovation/BaseRenovation/mobile_add");
			}
		}
	}

	public void editPage() throws IOException, ServletException, TemplateException, SQLException {
		this.addPage();
	}

	public void setDefaultPage() throws SQLException, IOException {
		if (IS_POST) {
			// 清理缓存
			if (this.type.equals("Index/Index/menu")) {
				// MenuController.clearCache();
			}
			String id = I("id");
			Map<String, W> where = new HashMap<>();
			where.put(RENOVATION.id, new W("eq", id));
			where.put(RENOVATION.is_del, new W("eq", 0));
			where.put(RENOVATION.website_id, new W("eq", session("website_id")));
			Map<String, Object> info = new MU(RENOVATION._table_name).where(where).find();
			if (info == null) {
				this.error("不存在该模版");
				return;
			}
			if (info.get(RENOVATION.is_default).toString().equals("1")) {
				this.error("该模版已是默认模版");
				return;
			} else {
				// 该模版设置为默认模版
				Map<String, Object> data = new HashMap<>();
				data.put(RENOVATION.is_default, 1);
				new MU(RENOVATION._table_name).where(where).save(data);
				// 其他模版设置为非默认模版
				where.clear();
				where.put(RENOVATION.is_del, new W("eq", 0));
				where.put(RENOVATION.type, new W("eq", info.get(RENOVATION.type).toString()));
				where.put(RENOVATION.id, new W("neq", id));
				where.put(RENOVATION.website_id, new W("eq", session("website_id")));
				data.clear();
				data.put(RENOVATION.is_default, 0);
				new MU(RENOVATION._table_name).where(where).save(data);
				this.success("设置成功");
				return;
			}
		}
	}

	public void delPage() throws SQLException, IOException {
		if (IS_POST) {
			// 清理缓存
			if (this.type.equals("Index/Index/menu")) {
				// MenuController.clearCache();
			}
			String id = I("id");
			Map<String, W> where = new HashMap<>();
			where.put(RENOVATION.id, new W("eq", id));
			where.put(RENOVATION.type, new W("eq", this.type));
			where.put(RENOVATION.platform, new W("eq", platform));
			where.put(RENOVATION.website_id, new W("eq", session("website_id")));
			Map<String, Object> data = new HashMap<>();
			if (this.need_default) {
				Object is_default = new MU(RENOVATION._table_name).where(where).getField(RENOVATION.is_default);
				if (is_default.toString().equals("1")) {
					this.error("当前模板是默认模板，请先取消该默认模板后再删除");
					return;
				}
			}
			data.put(RENOVATION.is_del, 1);
			new MU(RENOVATION._table_name).where(where).save(data);
			this.success("删除成功");
		}
	}

	public void pageConfigPage() throws SQLException, IOException, ServletException, TemplateException {
		String id = I("id");
		Map<String, Object> info = new HashMap<>();
		if (!id.equals("")) {
			Map<String, W> where = new HashMap<>();
			where.put(RENOVATION.id, new W("eq", id));
			info = new MU(RENOVATION._table_name).where(where).find();
		}
		this.assign("info", JSONObject.parse(JSON.toJSONString(info)));
		// 获取所有的顶部模块
		Map<String, W> header_where = new HashMap<>();
		header_where.put(RENOVATION.is_del, new W("eq", 0));
		header_where.put(RENOVATION.type, new W("eq", "header"));
		header_where.put(RENOVATION.platform, new W("eq", this.platform));
		header_where.put(RENOVATION.website_id, new W("eq", session("website_id")));
		List<Map<String, Object>> header_list = new MU(RENOVATION._table_name).where(header_where).select();
		JSONArray header_arr = new JSONArray();
		JSONObject header_object = new JSONObject();
		header_object.put("id", 0);
		header_object.put("name", "自定义顶部模块");
		header_arr.add(header_object);
		for (Integer i = 0; i < header_list.size(); i = i + 1) {
			JSONObject obj = new JSONObject();
			obj.put("id", header_list.get(i).get(RENOVATION.id));
			obj.put("name", header_list.get(i).get(RENOVATION.name));
			header_arr.add(obj);
		}
		this.assign("header_list", header_arr);
		// 获取所有的底部菜单
		Map<String, W> bottom_where = new HashMap<>();
		bottom_where.put(RENOVATION.is_del, new W("eq", 0));
		bottom_where.put(RENOVATION.type, new W("eq", "bottom"));
		bottom_where.put(RENOVATION.platform, new W("eq", this.platform));
		bottom_where.put(RENOVATION.website_id, new W("eq", session("website_id")));
		List<Map<String, Object>> list = new MU(RENOVATION._table_name).where(bottom_where).select();
		JSONArray arr = new JSONArray();
		JSONObject object = new JSONObject();
		object.put("id", 0);
		object.put("name", "自定义底部模块");
		arr.add(object);
		for (Integer i = 0; i < list.size(); i = i + 1) {
			JSONObject obj = new JSONObject();
			obj.put("id", list.get(i).get(RENOVATION.id));
			obj.put("name", list.get(i).get(RENOVATION.name));
			arr.add(obj);
		}
		this.assign("bottom_list", arr);
		this.display("Renovation/BaseRenovation/pageConfig");
	}
}
