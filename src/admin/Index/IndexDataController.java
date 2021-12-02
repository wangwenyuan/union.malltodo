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
package admin.Index;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.javatodo.core.tools.T;

import model.Platform;
import model.Statistics;

public class IndexDataController extends CommonController {
	public void get_serven_day_sales_ranking_of_productsPage() throws ParseException, SQLException {
		String today = T.date("yyyy-MM-dd", T.time());
		String day_7 = T.date("yyyy-MM-dd", T.time() - 7 * 24 * 3600 * 1000);
		String start_time = day_7 + " 00:00:00";
		String end_time = today + " 23:59:59";
		this.common(start_time, end_time);
	}

	public void get_today_sales_ranking_of_productsPage() throws ParseException, SQLException {
		String today = T.date("yyyy-MM-dd", T.time());
		String start_time = today + " 00:00:00";
		String end_time = today + " 23:59:59";
		this.common(start_time, end_time);
	}

	public void get_yesterday_sales_ranking_of_productsPage() throws ParseException, SQLException {
		String today = T.date("yyyy-MM-dd", T.time() - 24 * 3600 * 1000);
		String start_time = today + " 00:00:00";
		String end_time = today + " 23:59:59";
		this.common(start_time, end_time);
	}

	private void common(String start_time, String end_time) throws ParseException, SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (String key : Platform.map.keySet()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("platform", Platform.map.get(key).toString());
			map.put("number", Statistics.getNumber(start_time, end_time, key));
			map.put("commission", Statistics.getCommission(start_time, end_time, key));
			map.put("platform_money", Statistics.getPlatformMoney(start_time, end_time, key));
			list.add(map);
		}
		this.assign("list", list);
		this.jsonDisplay();
	}
}
