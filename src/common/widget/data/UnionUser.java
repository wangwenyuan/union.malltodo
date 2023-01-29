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

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.model.W;

import common.MU;
import common.database.MEMBER;
import common.database.MONEY_LOG;
import common.database.UNION_ORDER;
import model.Balance;

public class UnionUser {
	public JSONObject parameter = new JSONObject(new LinkedHashMap<>());

	public UnionUser() {
	}

	// selfParameter：自身参数，也就是类中的parameter属性的值
	// webRequestParam：需要传入的其他参数，比如栏目的id或者商品的id 搜索的关键字等等
	public JSONObject getValue(JSONObject selfParameter, JSONObject webRequestParam, HttpServletRequest request) throws SQLException {
		String uid = webRequestParam.getString("uid");
		Map<String, W> where = new HashMap<>();
		where.put(MEMBER.id, new W("eq", uid));
		Map<String, Object> info = new MU(MEMBER._table_name).where(where).find();
		if (info == null) {
			return null;
		} else {
			// 获取上级推荐人
			String pid = info.get(MEMBER.pid).toString();
			Map<String, W> pidwhere = new HashMap<>();
			pidwhere.put(MEMBER.id, new W("eq", pid));
			Map<String, Object> pidInfo = new MU(MEMBER._table_name).where(pidwhere).find();
			JSONObject object = new JSONObject();
			Object total_revenue_1 = new MU(UNION_ORDER._table_name).where("level_1_id = " + uid).getField("sum(" + UNION_ORDER.level_1_money + ")");
			if (total_revenue_1 == null) {
				total_revenue_1 = 0;
			}
			Object total_revenue_2 = new MU(UNION_ORDER._table_name).where("level_2_id = " + uid).getField("sum(" + UNION_ORDER.level_2_money + ")");
			if (total_revenue_2 == null) {
				total_revenue_2 = 0;
			}
			Object total_revenue_3 = new MU(UNION_ORDER._table_name).where("level_3_id = " + uid).getField("sum(" + UNION_ORDER.level_3_money + ")");
			if (total_revenue_3 == null) {
				total_revenue_3 = 0;
			}
			object.put("total_revenue", new BigDecimal(total_revenue_1.toString()).add(new BigDecimal(total_revenue_2.toString())).add(new BigDecimal(total_revenue_3.toString())));// 总收益
			object.put("username", info.get(MEMBER.username).toString());// 用户名
			if (pidInfo == null) {
				object.put("recommender", "平台");// 推荐人
			} else {
				object.put("recommender", pidInfo.get(MEMBER.username).toString());// 推荐人
			}
			if (info.get(MEMBER.pic).toString().equals("")) {
				object.put("head_img", "./Public/images/head_pic.png");// 用户头像
			} else {
				object.put("head_img", info.get(MEMBER.pic));// 用户头像
			}

			// 获取已提现金额
			Map<String, W> money_log_where = new HashMap<>();
			money_log_where.put(MONEY_LOG.uid, new W("eq", uid));
			money_log_where.put(MONEY_LOG.status, new W("eq", 1));
			money_log_where.put(MONEY_LOG.type, new W("eq", Balance.Withdrawal));

			Object withdrawn_cash = new MU(MONEY_LOG._table_name).where(money_log_where).getField("sum(" + MONEY_LOG.money + ")");
			if (withdrawn_cash == null) {
				withdrawn_cash = 0;
			}
			object.put("withdrawn_cash", new BigDecimal(0).subtract(new BigDecimal(withdrawn_cash.toString())));// 已提现金额
			return object;
		}
	}
}
