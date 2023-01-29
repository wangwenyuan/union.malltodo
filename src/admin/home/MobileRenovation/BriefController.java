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
package admin.home.MobileRenovation;

import java.io.IOException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import common.BaseRenovation;
import common.MobileWidget;

public class BriefController extends BaseRenovation {
	public Boolean init() {
		Boolean b = super.init();
		if (b) {
			this.type = "Index/Brief/index";
			this.platform = "mobile";
			this.assign("page_action", "公司简介栏目");
			return true;
		} else {
			return false;
		}
	}

	public static JSONObject getBriefListDom(String root, String rootUrl, JSONObject doms) throws IOException {
		JSONObject object = new JSONObject();
		JSONArray doms_sort = new JSONArray();
		for (String key : doms.keySet()) {
			if (doms.getJSONObject(key).getString("category").equals("teamList")) {
				object.put(key, doms.getJSONObject(key));
				doms_sort.add(key);
				break;
			}
		}
		String html = MobileWidget.buildHtmlCSSTemplate(root, rootUrl, object, doms_sort);
		JSONObject ret = new JSONObject();
		ret.put("list_dom", object.toString());
		ret.put("list_html", html);
		return ret;
	}
}
