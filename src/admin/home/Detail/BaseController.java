package admin.home.Detail;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import common.Common;
import common.Functions;
import common.MU;
import common.MenuCache;
import common.database.CATEGORY;
import common.database.DETAIL;
import common.database.RENOVATION;
import freemarker.template.TemplateException;

public class BaseController extends CommonController {

	protected String categoryType = "";
	protected String detailType = "";

	public void indexPage() throws IOException, ServletException, TemplateException, SQLException {
		Map<String, W> where = new HashMap<String, W>();
		where.put("n." + DETAIL.is_del, new W("eq", 0));
		where.put("n." + DETAIL.type, new W("eq", this.detailType));
		where.put("n." + DETAIL.website_id, new W("eq", session("website_id")));
		Long count = new MU(DETAIL._table_name).alias("n").where(where).count();
		Page page = this.page(count, 16);
		List<Map<String, Object>> list = new MU(DETAIL._table_name).alias("n").join(CATEGORY._table_name, " as c on n.category_id = c.id", "left").where(where).limit(page.firstRow + "," + page.listRows).order(DETAIL.id + " desc").field("n.*, c.category_name").select();
		this.assign("list", JSONArray.parseArray(JSON.toJSONString(list)));
		this.assign("page", page.show());
		this.display("Detail/Base/index");
	}

	public void addPage() throws IOException, ServletException, TemplateException, SQLException, ParseException {
		String id = I("id").trim();
		Map<String, W> where = new HashMap<>();
		Map<String, Object> info = new HashMap<>();
		if (!id.equals("")) {
			where.put(DETAIL.id, new W("eq", id));
			where.put(DETAIL.is_del, new W("eq", 0));
			where.put(DETAIL.type, new W("eq", this.detailType));
			where.put(DETAIL.website_id, new W("eq", session("website_id")));
			info = new MU(DETAIL._table_name).where(where).find();
			if (info == null) {
				this.error("该内容不存在或已被删除");
				return;
			}
			info.put(DETAIL.detail, T.htmlspecialchars_decode(info.get(DETAIL.detail).toString()));
			Long time = Long.valueOf(info.get(DETAIL.release_time).toString());
			info.put(DETAIL.release_time, T.date("yyyy-MM-dd HH:mm:ss", time));
		}

		// 获取栏目
		JSONArray admin_menu_list = MenuCache.getAdminMenuList(session("website_id").toString());
		JSONObject menu_json = new JSONObject(new LinkedHashMap<>());
		for (Integer i = 0; i < admin_menu_list.size(); i = i + 1) {
			JSONObject jsonObject = admin_menu_list.getJSONObject(i);
			String sign = "";
			for (Integer n = 0; n < jsonObject.getIntValue("level"); n = n + 1) {
				sign = sign + "——";
			}
			String _id = jsonObject.getString(CATEGORY.id);
			String name = jsonObject.getString(CATEGORY.category_name);
			name = sign + name;
			String type = jsonObject.getString(CATEGORY.type);
			if (type.equals(this.categoryType)) {
				menu_json.put(_id, name);
			}
		}

		// 获取所有的默认模板
		where.clear();
		where.put(RENOVATION.type, new W("eq", this.detailType));
		where.put(RENOVATION.is_del, new W("eq", 0));
		where.put(RENOVATION.website_id, new W("eq", session("website_id")));
		List<Map<String, Object>> _renovation_list = new MU(RENOVATION._table_name).where(where).order(RENOVATION.id + " asc").select();
		Map<String, String> pc_renovation_map = new LinkedHashMap();
		Map<String, String> mobile_renovation_map = new LinkedHashMap();
		pc_renovation_map.put("0", "默认模板");
		mobile_renovation_map.put("0", "默认模板");
		for (Integer i = 0; i < _renovation_list.size(); i = i + 1) {
			if (_renovation_list.get(i).get(RENOVATION.id).toString().equals("pc")) {
				pc_renovation_map.put(_renovation_list.get(i).get(RENOVATION.id).toString(), _renovation_list.get(i).get(RENOVATION.name).toString());
			} else {
				mobile_renovation_map.put(_renovation_list.get(i).get(RENOVATION.id).toString(), _renovation_list.get(i).get(RENOVATION.name).toString());
			}
		}
		// 获取所有的自定义模板
		where.clear();
		where.put(RENOVATION.type, new W("eq", "Index/Index/custom"));
		where.put(RENOVATION.is_del, new W("eq", 0));
		where.put(RENOVATION.website_id, new W("eq", session("website_id")));
		List<Map<String, Object>> _custom_list = new MU(RENOVATION._table_name).where(where).order(RENOVATION.id + " asc").select();
		Map<String, String> pc_custom_map = new LinkedHashMap();
		Map<String, String> mobile_custom_map = new LinkedHashMap();
		for (Integer i = 0; i < _custom_list.size(); i = i + 1) {
			if (_renovation_list.get(i).get(RENOVATION.id).toString().equals("pc")) {
				pc_custom_map.put(_custom_list.get(i).get(RENOVATION.id).toString(), _custom_list.get(i).get(RENOVATION.name).toString());
			} else {
				mobile_custom_map.put(_custom_list.get(i).get(RENOVATION.id).toString(), _custom_list.get(i).get(RENOVATION.name).toString());
			}
		}
		if (IS_POST) {
			Map<String, String> _data = Functions.trim_array(I());
			String category_id = I("category_id").trim();
			if (!menu_json.containsKey(category_id)) {
				this.error("请选择正确的栏目");
				return;
			}
			String title = I(DETAIL.title).trim();
			if (title.equals("")) {
				this.error("标题不能为空");
				return;
			}
			if (_data.get(DETAIL.seo_title).equals("")) {
				_data.put(DETAIL.seo_title, title);
			}
			if (_data.get(DETAIL.seo_keywords).equals("")) {
				_data.put(DETAIL.seo_keywords, title);
			}
			if (_data.get(DETAIL.seo_description).equals("")) {
				_data.put(DETAIL.seo_description, title);
			}
			if (_data.get(DETAIL.release_time).equals("")) {
				_data.put(DETAIL.release_time, String.valueOf(T.time()));
			} else {
				_data.put(DETAIL.release_time, String.valueOf(T.strtotime(_data.get(DETAIL.release_time), "yyyy-MM-dd HH:mm:ss")));
			}
			_data.put(DETAIL.sort, T.toInt(I("sort")).toString());
			_data.put(DETAIL.admin_id, session("admin_id").toString());
			Integer renovation_type = T.toInt(I("renovation_type"));
			if (renovation_type == 0) {// 普通模板
				String pc_renovation_id = I("pc_renovation_id").trim();
				String mobile_renovation_id = I("mobile_renovation_id").trim();
				if (!pc_renovation_map.containsKey(pc_renovation_id)) {
					this.error("电脑端模板选择有误");
					return;
				}
				if (Common.hasMobile) {
					if (!mobile_renovation_map.containsKey(mobile_renovation_id)) {
						this.error("手机端模板选择有误");
						return;
					}
				}
				_data.put(DETAIL.renovation_type, renovation_type.toString());
				_data.put(DETAIL.pc_renovation_id, T.toInt(pc_renovation_id).toString());
				_data.put(DETAIL.mobile_renovation_id, T.toInt(mobile_renovation_id).toString());
				_data.put(DETAIL.pc_custom_id, "0");
				_data.put(DETAIL.mobile_custom_id, "0");
			} else {// 自定义模板
				String pc_custom_id = I(DETAIL.pc_custom_id).trim();
				String mobile_custom_id = I(DETAIL.mobile_custom_id).trim();
				if (!pc_custom_map.containsKey(pc_custom_id)) {
					this.error("电脑端自定义模板选择有误");
					return;
				}
				if (Common.hasMobile) {
					if (!mobile_custom_map.containsKey(mobile_custom_id)) {
						this.error("手机端自定义模板选择有误");
						return;
					}
				}
				_data.put(DETAIL.renovation_type, renovation_type.toString());
				_data.put(DETAIL.pc_renovation_id, "0");
				_data.put(DETAIL.mobile_renovation_id, "0");
				_data.put(DETAIL.pc_custom_id, pc_custom_id);
				_data.put(DETAIL.mobile_custom_id, mobile_custom_id);
			}
			_data.put(DETAIL.type, detailType);
			if (id.equals("")) {
				_data.put(DETAIL.website_id, session("website_id").toString());
				new MU(DETAIL._table_name).data(Functions.I_TO_MAP(_data)).add();
				this.success("添加成功");
				return;
			} else {
				where.clear();
				where.put(DETAIL.id, new W("eq", id));
				where.put(DETAIL.website_id, new W("eq", session("website_id")));
				new MU(DETAIL._table_name).where(where).save(Functions.I_TO_MAP(_data));
				this.success("修改成功");
				return;
			}
		} else {
			this.assign("category_map", menu_json);
			this.assign("pc_renovation", JSON.toJSON(pc_renovation_map).toString());
			this.assign("mobile_renovation", JSON.toJSON(mobile_renovation_map).toString());
			this.assign("pc_custom", JSON.toJSON(pc_custom_map).toString());
			this.assign("mobile_custom", JSON.toJSON(mobile_custom_map).toString());

			this.assign("info", info);
			this.display("Detail/Base/add");
		}
	}

	public void editPage() throws IOException, ServletException, TemplateException, SQLException, ParseException {
		this.addPage();
	}

	public void delPage() throws SQLException, IOException {
		if (IS_POST) {
			String id = I("id").trim();
			Map<String, W> where = new HashMap<>();
			where.put(DETAIL.id, new W("eq", id));
			where.put(DETAIL.website_id, new W("eq", session("website_id")));
			Map<String, Object> data = new HashMap<>();
			data.put(DETAIL.is_del, 1);
			new MU(DETAIL._table_name).where(where).save(data);
			this.success("删除成功");
		}
	}
}
