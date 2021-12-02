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
package model;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.javatodo.core.model.W;
import com.javatodo.core.tools.T;

import common.MU;
import common.database.UNION_ORDER;

public class Statistics {
	public static String getCommission(String start_time, String end_time, String platform) throws ParseException, SQLException {
		Map<String, W> where = new HashMap<>();
		List<Long> where_list = new ArrayList<Long>();
		where_list.add(T.strtotime(start_time, "yyyy-MM-dd HH:mm:ss"));
		where_list.add(T.strtotime(end_time, "yyyy-MM-dd HH:mm:ss"));
		where.put(UNION_ORDER.pay_time, new W("between", where_list));
		where.put(UNION_ORDER.status, new W("neq", -1));
		if (!platform.equals("")) {
			where.put(UNION_ORDER.type, new W("eq", platform));
		}
		Object commission = new MU(UNION_ORDER._table_name).where(where).getField("sum(" + UNION_ORDER.commission + ")");
		if (commission == null) {
			return "0";
		} else {
			return commission.toString();
		}
	}

	public static String getPlatformMoney(String start_time, String end_time, String platform) throws ParseException, SQLException {
		Map<String, W> where = new HashMap<>();
		List<Long> where_list = new ArrayList<Long>();
		where_list.add(T.strtotime(start_time, "yyyy-MM-dd HH:mm:ss"));
		where_list.add(T.strtotime(end_time, "yyyy-MM-dd HH:mm:ss"));
		where.put(UNION_ORDER.pay_time, new W("between", where_list));
		where.put(UNION_ORDER.status, new W("neq", -1));
		if (!platform.equals("")) {
			where.put(UNION_ORDER.type, new W("eq", platform));
		}
		Object platform_money = new MU(UNION_ORDER._table_name).where(where).getField("sum(" + UNION_ORDER.platform_money + ")");
		if (platform_money == null) {
			return "0";
		} else {
			return platform_money.toString();
		}
	}

	public static String getNumber(String start_time, String end_time, String platform) throws ParseException, SQLException {
		Map<String, W> where = new HashMap<>();
		List<Long> where_list = new ArrayList<Long>();
		where_list.add(T.strtotime(start_time, "yyyy-MM-dd HH:mm:ss"));
		where_list.add(T.strtotime(end_time, "yyyy-MM-dd HH:mm:ss"));
		where.put(UNION_ORDER.pay_time, new W("between", where_list));
		where.put(UNION_ORDER.status, new W("neq", -1));
		if (!platform.equals("")) {
			where.put(UNION_ORDER.type, new W("eq", platform));
		}
		long count = new MU(UNION_ORDER._table_name).where(where).count();
		return String.valueOf(count);
	}

}
