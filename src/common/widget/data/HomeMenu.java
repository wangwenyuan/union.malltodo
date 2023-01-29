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
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import common.MenuCache;

//栏目页栏目体组件
public class HomeMenu {
	public JSONObject parameter = new JSONObject(new LinkedHashMap<>());

	public HomeMenu() {

	}

	// selfParameter：自身参数，也就是类中的parameter属性的值
	// webRequestParam：需要传入的其他参数，比如栏目的id或者商品的id 搜索的关键字等等
	// List<String> bind_loop_list：形如<"list:4",
	// "sub_list:6">的列表，其中"list"表示"参数名",后面的数字表示循环的次数，中间用":"隔开。
	public JSONObject getValue(JSONObject selfParameter, JSONObject webRequestParam, HttpServletRequest request, List<String> bind_loop_list) throws SQLException {
		JSONObject object = new JSONObject();
		Object websiteId = request.getSession().getAttribute("website_id");
		JSONArray menu_list = MenuCache.getMenuList(websiteId.toString());
		object.put("menu_list", menu_list);
		return object;
	}
}
