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
package admin.union.Index;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.T;

import admin.home.Index.CommonController;
import common.MU;
import common.database.MEMBER;
import common.database.UNION_ORDER;
import common.database.WITHDRAWALS;
import freemarker.template.TemplateException;
import model.Statistics;

public class IndexController extends CommonController {

	public void indexPage() throws IOException, ServletException, TemplateException, ParseException, SQLException {
		// 获取今日的收入
		String today = T.date("yyyy-MM-dd", T.time());
		String start_time = today + " 00:00:00";
		String end_time = today + " 23:59:59";
		String today_commission = Statistics.getCommission(start_time, end_time, "");
		String today_platform_money = Statistics.getPlatformMoney(start_time, end_time, "");
		this.assign("today_commission", today_commission);
		this.assign("today_platform_money", today_platform_money);

		// 获取昨日的收入
		String yestoday = T.date("yyyy-MM-dd", T.time() - 24 * 3600 * 1000);
		start_time = yestoday + " 00:00:00";
		end_time = yestoday + " 23:59:59";
		String yestoday_commission = Statistics.getCommission(start_time, end_time, "");
		String yestoday_platform_money = Statistics.getPlatformMoney(start_time, end_time, "");
		this.assign("yestoday_commission", yestoday_commission);
		this.assign("yestoday_platform_money", yestoday_platform_money);

		// 获取近7日的收入
		String day_7 = T.date("yyyy-MM-dd", T.time() - 7 * 24 * 3600 * 1000);
		start_time = day_7 + " 00:00:00";
		end_time = today + " 23:59:59";
		String day_7_commission = Statistics.getCommission(start_time, end_time, "");
		String day_7_platform_money = Statistics.getPlatformMoney(start_time, end_time, "");
		this.assign("day_7_commission", day_7_commission);
		this.assign("day_7_platform_money", day_7_platform_money);

		// 获取当年的收入
		String year = T.date("yyyy", T.time());
		start_time = year + "-01-01 00:00:00";
		end_time = today + " 23:59:59";
		String year_commission = Statistics.getCommission(start_time, end_time, "");
		String year_platform_money = Statistics.getPlatformMoney(start_time, end_time, "");
		this.assign("year_commission", year_commission);
		this.assign("year_platform_money", year_platform_money);

		// 获取最近未处理的用户提现
		Map<String, W> where = new HashMap<>();
		where.put("w." + WITHDRAWALS.examine_status, new W("eq", 0));
		List<Map<String, Object>> withdrawals_list = new MU(WITHDRAWALS._table_name).alias("w").join(MEMBER._table_name, "as m on w.uid = m.id").where(where).limit("5").order(WITHDRAWALS.id + " desc").field("w.*, m." + MEMBER.username + ", " + MEMBER.pic).select();
		this.assign("withdrawals_list", JSONArray.parseArray(JSON.toJSONString(withdrawals_list)));

		// 获取最新订单
		Map<String, W> order_where = new HashMap<>();
		order_where.put(UNION_ORDER.status, new W("neq", -1));
		List<Map<String, Object>> order_list = new MU(UNION_ORDER._table_name).alias("o").join(MEMBER._table_name, "as m on o.uid = m.id").where(order_where).limit("5").order(UNION_ORDER.id + " desc").field("o.*, m." + MEMBER.username + ", " + MEMBER.pic).select();
		this.assign("order_list", JSONArray.parseArray(JSON.toJSONString(order_list)));

		this.display();
	}
}
