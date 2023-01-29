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
package common.widget.data;

import java.sql.SQLException;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.tools.Http;

import common.Common;

//栏目页栏目标题组件
public class UnionCategoryName {
	public JSONObject parameter = new JSONObject(new LinkedHashMap<>());

	public UnionCategoryName() {

	}

	// selfParameter：自身参数，也就是类中的parameter属性的值
	// webRequestParam：需要传入的其他参数，比如栏目的id或者商品的id 搜索的关键字等等
	public JSONObject getValue(JSONObject selfParameter, JSONObject webRequestParam, HttpServletRequest request) throws SQLException {
		Http http = new Http();
		String ret = http.get(Common.unionHost + "/Public/category.json");
		JSONObject retObject = JSONObject.parseObject(ret);
		JSONArray category_list = retObject.getJSONArray("list");
		String category_id = webRequestParam.getString("category_id");
		String category_name = "";
		for (Integer i = 0; i < category_list.size(); i = i + 1) {
			if (!category_name.equals("")) {
				break;
			}
			if (category_list.getJSONObject(i).getString("id").equals(category_id)) {
				category_name = category_list.getJSONObject(i).getString("name");
				break;
			}
			JSONArray sub_category_list = category_list.getJSONObject(i).getJSONArray("subcategorys");
			for (Integer ii = 0; ii < sub_category_list.size(); ii = ii + 1) {
				if (!category_name.equals("")) {
					break;
				}
				if (sub_category_list.getJSONObject(ii).getString("id").equals(category_id)) {
					category_name = sub_category_list.getJSONObject(ii).getString("name");
					break;
				}
				JSONArray sub_sub_category_list = sub_category_list.getJSONObject(ii).getJSONArray("subcategorys");
				for (Integer iii = 0; iii < sub_sub_category_list.size(); iii = iii + 1) {
					if (!category_name.equals("")) {
						break;
					}
					if (sub_sub_category_list.getJSONObject(iii).getString("id").equals(category_id)) {
						category_name = sub_sub_category_list.getJSONObject(iii).getString("name");
						break;
					}
				}
			}
		}
		JSONObject object = new JSONObject();
		object.put("category_name", category_name);
		return object;
	}
}
