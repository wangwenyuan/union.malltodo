package index.Index;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.controller.Controller;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.T;

import common.Functions;
import common.MU;
import common.database.CATEGORY;
import common.database.WEBSITE;

public class BaseController extends Controller {
	public JSONObject webRequestParam = new JSONObject();

	public Boolean init() {
		if (session("website_id") == null || session("website_id").equals("")) {
			String domain = T.getHost(request).replace("http://", "").replace("https://", "");
			// 判断该域名是否存在
			try {
				Map<String, W> where = new HashMap<>();
				where.put(WEBSITE.website_host, new W("eq", domain));
				where.put(WEBSITE.is_del, new W("eq", 0));
				Map<String, Object> website_info = new MU(WEBSITE._table_name).where(where).find();
				if (website_info == null) {// 说明当前访问网址不存在
					// 如果访问的网址不存在，就以第一个站点做为默认站点
					String website_id = Functions.getHomeDefaultWebsiteId();
					session("website_id", website_id);
				} else {
					session("website_id", website_info.get(WEBSITE.id).toString());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		webRequestParam = JSONObject.parseObject(JSON.toJSONString(I()));
		return true;
	}

	public void homePagePage(String type) throws SQLException, IOException {
		Map<String, W> where = new HashMap<>();
		where.put(CATEGORY.type, new W("eq", type));
		where.put(CATEGORY.is_del, new W("eq", 0));
		Map<String, Object> info = new MU(CATEGORY._table_name).where(where).order(CATEGORY.id + " asc").find();
		if (info == null) {
			return;
		} else {
			redirect(T.U("Index/Index/category", "id=" + info.get(CATEGORY.id).toString(), "index.jsp"));
			return;
		}
	}
}
