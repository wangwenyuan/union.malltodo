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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.tools.T;

import common.Common;
import common.Functions;
import common.database.GOODS;

public class BaseGoodsModel {
	public JSONObject parameter = new JSONObject(new LinkedHashMap<>());

	public BaseGoodsModel() {
		// TODO Auto-generated constructor stub
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("_title", "商品排序方式");
		orderby.put(GOODS.goods_id + " desc", "商品倒序");
		orderby.put(GOODS.goods_id + " asc", "商品正序");
		orderby.put(GOODS.coupon_discount + " desc", "优惠券面额从大到小");
		orderby.put(GOODS.coupon_discount + " asc", "优惠券面额从小到大");
		orderby.put(GOODS.price + " desc", "券后价从高到低");
		orderby.put(GOODS.price + " asc", "券后价从低到高");
		orderby.put(GOODS.original_price + " desc", "原价从高到低");
		orderby.put(GOODS.original_price + " asc", "原价从低到高");

		parameter.put("orderby", orderby);

		Map<String, String> platform = new LinkedHashMap<String, String>();
		platform.put("_title", "所属平台");
		platform.put("all", "所有平台");
		platform.put("pdd", "拼多多");
		platform.put("jd", "京东");
		parameter.put("platform", platform);
	}

	// selfParameter：自身参数，也就是类中的parameter属性的值
	// webRequestParam：需要传入的其他参数，比如栏目的id或者商品的id 搜索的关键字等等
	public JSONObject getValue(JSONObject selfParameter, JSONObject webRequestParam) throws SQLException, UnsupportedEncodingException {
		JSONObject map = new JSONObject();
		for (String key : selfParameter.keySet()) {
			map.put(key, selfParameter.get(key));
		}
		for (String key : webRequestParam.keySet()) {
			map.put(key, webRequestParam.get(key));
		}

		String param = "";
		String uid = "";
		for (String key : map.keySet()) {
			if (key.equals("uid")) {
				uid = map.getString(key);
			} else {
				param = param + "&" + key + "=" + URLEncoder.encode(map.getString(key), StandardCharsets.UTF_8.toString());
			}
		}
		if (uid.equals("")) {
			T.create_log("goods_list_error.log", "缺失uid");
			T.create_log("goods_list_error.log", "其他参数：" + webRequestParam.toJSONString());
			return null;
		}
		JSONArray goods_list = Functions.getGoodsList(Common.unionHost + "/api.jsp?m=Index&c=GoodsList&a=index" + param, uid);
		if (goods_list == null) {
			return null;
		} else {
			JSONObject object = new JSONObject();
			object.put("goods_list", goods_list);
			return object;
		}
	}
}
