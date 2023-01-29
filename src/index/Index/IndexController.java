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
package index.Index;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import com.javatodo.core.model.W;
import com.javatodo.core.tools.T;

import common.Common;
import common.MU;
import common.MobileWidget;
import common.database.CATEGORY;
import common.database.DETAIL;
import common.database.RENOVATION;
import freemarker.template.TemplateException;

public class IndexController extends BaseController {
	public void indexPage() throws SQLException, IOException, ServletException, TemplateException {
		Map<String, W> where = new HashMap<String, W>();
		where.put(RENOVATION.type, new W("eq", "Index/Index/index"));
		where.put(RENOVATION.is_default, new W("eq", 1));
		where.put(RENOVATION.is_del, new W("eq", 0));
		if (T.isMobile(request)) {
			if (Common.hasMobile) {
				where.put(RENOVATION.platform, new W("eq", "mobile"));
			} else {
				where.put(RENOVATION.platform, new W("eq", "pc"));
			}
		} else {
			where.put(RENOVATION.platform, new W("eq", "pc"));
		}
		where.put(RENOVATION.website_id, new W("eq", session("website_id")));
		Map<String, Object> map = new MU(RENOVATION._table_name).where(where).find();
		if (map != null) {
			where.clear();
			where.put(CATEGORY.type, new W("eq", "Index/Index/index"));
			where.put(CATEGORY.is_del, new W("eq", 0));
			where.put(CATEGORY.website_id, new W("eq", session("website_id")));
			Map<String, Object> category = new MU(CATEGORY._table_name).where(where).find();
			String title = category.get(CATEGORY.seo_title).toString().trim();
			String keywords = category.get(CATEGORY.seo_keywords).toString().trim();
			String description = category.get(CATEGORY.seo_description).toString().trim();
			String html = MobileWidget.buildPage(map.get(RENOVATION.id).toString(), webRequestParam, request, title, keywords, description);
			this.htmlDisplay(html);
		} else {
			this.htmlDisplay(MobileWidget.noTemplateNotice("尚未配置首页默认模板"));
		}
	}

	public void categoryPage() throws SQLException, UnsupportedEncodingException {
		String id = I("id");
		Map<String, W> where = new HashMap<String, W>();
		where.put(CATEGORY.is_del, new W("eq", 0));
		where.put(CATEGORY.id, new W("eq", id));
		where.put(CATEGORY.website_id, new W("eq", session("website_id")));
		Map<String, Object> category = new MU(CATEGORY._table_name).where(where).find();
		if (category == null) {
			this.htmlDisplay(MobileWidget.noTemplateNotice("尚未创建栏目模板或当前栏目未绑定栏目模板"));
			return;
		}

		// 页面类型，0 普通类型，列表页+详情页； 1 单页面； 2 自定义页面
		String category_type = category.get(CATEGORY.category_type).toString().trim();
		String pc_list_renovation_id = category.get(CATEGORY.pc_list_renovation_id).toString().trim();
		String mobile_list_renovation_id = category.get(CATEGORY.mobile_list_renovation_id).toString().trim();
		String pc_custom_id = category.get(CATEGORY.pc_custom_id).toString().trim();
		String mobile_custom_id = category.get(CATEGORY.mobile_custom_id).toString().trim();

		String pc_renovation_id = "0";// pc端列表页模板
		String mobile_renovation_id = "0";// mobile端列表页模板
		String type = category.get(CATEGORY.type).toString().trim();
		// 获取页面模板
		// 1)普通类型
		if (category_type.equals("0")) {
			pc_renovation_id = pc_list_renovation_id;
			mobile_renovation_id = mobile_list_renovation_id;
		}

		if (category_type.equals("1")) {
			pc_renovation_id = pc_list_renovation_id;
			mobile_renovation_id = mobile_list_renovation_id;
		}

		if (category_type.equals("2")) {
			pc_renovation_id = pc_custom_id;
			mobile_renovation_id = mobile_custom_id;
			if (pc_renovation_id.equals("0")) {// 未选择自定义页面模板
				this.htmlDisplay(MobileWidget.noTemplateNotice("尚未创建栏目模板或当前栏目未绑定栏目模板"));
				return;
			}
		}

		Map<String, Object> renovation = null;
		where.clear();
		if (!category_type.equals("2")) {
			where.put(RENOVATION.type, new W("eq", type));
		}
		if (T.isMobile(request)) {
			if (Common.hasMobile) {
				where.put(RENOVATION.platform, new W("eq", "mobile"));
				where.put(RENOVATION.id, new W("eq", mobile_renovation_id));
			} else {
				where.put(RENOVATION.platform, new W("eq", "pc"));
				where.put(RENOVATION.id, new W("eq", pc_renovation_id));
			}
		} else {
			where.put(RENOVATION.platform, new W("eq", "pc"));
			where.put(RENOVATION.id, new W("eq", pc_renovation_id));
		}
		where.put(RENOVATION.website_id, new W("eq", session("website_id")));
		renovation = new MU(RENOVATION._table_name).where(where).find();
		if (renovation == null) {
			this.htmlDisplay(MobileWidget.noTemplateNotice("尚未创建栏目模板或当前栏目未绑定栏目模板"));
			return;
		} else {
			if (T.isMobile(request) && T.toInt(I("p")) > 1) {
				String html = MobileWidget.buildList(renovation.get(RENOVATION.id).toString(), webRequestParam, request);
				this.htmlDisplay(html);
			} else {
				String title = category.get(CATEGORY.seo_title).toString().trim();
				String keywords = category.get(CATEGORY.seo_keywords).toString().trim();
				String description = category.get(CATEGORY.seo_description).toString().trim();
				String html = MobileWidget.buildPage(renovation.get(RENOVATION.id).toString(), webRequestParam, request, title, keywords, description);
				this.htmlDisplay(html);
			}
		}
	}

	public void detailPage() throws SQLException, UnsupportedEncodingException {
		String id = I("id");
		Map<String, W> where = new HashMap<String, W>();
		where.put(DETAIL.is_del, new W("eq", 0));
		where.put(DETAIL.id, new W("eq", id));
		where.put(DETAIL.website_id, new W("eq", session("website_id")));
		Map<String, Object> detail = new MU(DETAIL._table_name).where(where).find();

		if (detail == null) {
			this.htmlDisplay(MobileWidget.noTemplateNotice("尚未创建详情页模板或当前栏目未绑定详情页模板"));
			return;
		}

		// 模板类型：0 普通模板； 1 自定义模板
		String renovation_type = detail.get(DETAIL.renovation_type).toString().trim();
		String type = detail.get(DETAIL.type).toString().trim();

		String pc_renovation_id = "0";
		String mobile_renovation_id = "0";

		if (renovation_type.equals("0")) {
			pc_renovation_id = detail.get(DETAIL.pc_renovation_id).toString();
			mobile_renovation_id = detail.get(DETAIL.mobile_renovation_id).toString();
		}

		if (renovation_type.equals("1")) {
			pc_renovation_id = detail.get(DETAIL.pc_custom_id).toString();
			mobile_renovation_id = detail.get(DETAIL.mobile_custom_id).toString();
			if (pc_renovation_id.equals("0")) {
				this.htmlDisplay(MobileWidget.noTemplateNotice("尚未创建详情页模板或当前栏目未绑定详情页模板"));
				return;
			}
		}

		Map<String, Object> category = null;
		if (pc_renovation_id.equals("0") || mobile_renovation_id.equals("0")) {
			where.clear();
			String category_id = detail.get(DETAIL.category_id).toString();
			where.put(CATEGORY.id, new W("eq", category_id));
			where.put(CATEGORY.is_del, new W("eq", 0));
			where.put(CATEGORY.website_id, new W("eq", session("website_id")));
			category = new MU(CATEGORY._table_name).where(where).find();
			if (category == null) {
				this.htmlDisplay(MobileWidget.noTemplateNotice("尚未创建详情页模板或当前栏目未绑定详情页模板"));
				return;
			}
			if (pc_renovation_id.equals("0")) {
				pc_renovation_id = category.get(CATEGORY.pc_page_renovation_id).toString();
			}
			if (mobile_renovation_id.equals("0")) {
				mobile_renovation_id = category.get(CATEGORY.mobile_page_renovation_id).toString();
			}
		}

		where.clear();
		if (renovation_type.equals("0")) {
			where.put(RENOVATION.type, new W("eq", type));
		}
		if (T.isMobile(request)) {
			if (Common.hasMobile) {
				where.put(RENOVATION.platform, new W("eq", "mobile"));
				where.put(RENOVATION.id, new W("eq", mobile_renovation_id));
			} else {
				where.put(RENOVATION.platform, new W("eq", "pc"));
				where.put(RENOVATION.id, new W("eq", pc_renovation_id));
			}
		} else {
			where.put(RENOVATION.platform, new W("eq", "pc"));
			where.put(RENOVATION.id, new W("eq", pc_renovation_id));
		}
		where.put(RENOVATION.is_del, new W("eq", 0));
		where.put(RENOVATION.website_id, new W("eq", session("website_id")));
		Map<String, Object> renovation = new MU(RENOVATION._table_name).where(where).find();
		if (renovation == null) {
			this.htmlDisplay(MobileWidget.noTemplateNotice("尚未创建详情页模板或当前栏目未绑定详情页模板"));
			return;
		} else {
			pc_renovation_id = renovation.get(RENOVATION.id).toString().trim();
			String title = detail.get(DETAIL.seo_title).toString().trim();
			String keywords = detail.get(DETAIL.seo_keywords).toString().trim();
			String description = detail.get(DETAIL.seo_description).toString().trim();
			String html = MobileWidget.buildPage(pc_renovation_id, webRequestParam, request, title, keywords, description);
			this.htmlDisplay(html);
		}
	}

	public void verifyPage() throws IOException {
		super.Verify();
	}
}
