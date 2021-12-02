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
import java.util.HashMap;
import java.util.Map;

import com.javatodo.core.model.W;

import common.MU;
import common.database.MEMBER;
import common.database.MONEY_LOG;
import common.database.UNION_ORDER;
import common.database.WITHDRAWALS;

public class Balance {
	public static final Integer AdministratorAdjustment = 1;// 管理员调整
	public static final Integer Recharge = 2;// 充值
	public static final Integer Payment = 3;// 支付
	public static final Integer Reward = 4;// 奖励
	public static final Integer Withdrawal = 5;// 提现
	public static final Integer Refund = 6;// 退款

	public static void updateUserMoney(String uid) throws SQLException {
		Map<String, W> where = new HashMap<>();
		where.put(MONEY_LOG.uid, new W("eq", uid));
		where.put(MONEY_LOG.status, new W("neq", -1));
		Object user_money_obj = new MU(MONEY_LOG._table_name).where(where).getField("sum(" + MONEY_LOG.money + ")");
		String user_money = "0";
		if (user_money_obj != null) {
			user_money = user_money_obj.toString();
		}
		where.put(MONEY_LOG.money_type, new W("eq", 0));
		Object user_money_0_obj = new MU(MONEY_LOG._table_name).where(where).getField("sum(" + MONEY_LOG.money + ")");
		String user_money_0 = "0";
		if (user_money_0_obj != null) {
			user_money_0 = user_money_0_obj.toString();
		}
		where.put(MONEY_LOG.money_type, new W("eq", 1));
		Object user_money_1_obj = new MU(MONEY_LOG._table_name).where(where).getField("sum(" + MONEY_LOG.money + ")");
		String user_money_1 = "0";
		if (user_money_1_obj != null) {
			user_money_1 = user_money_1_obj.toString();
		}

		Map<String, W> userWhere = new HashMap<>();
		userWhere.put(MEMBER.id, new W("eq", uid));
		Map<String, Object> save_data = new HashMap<>();
		save_data.put(MEMBER.money, user_money);
		save_data.put(MEMBER.money_0, user_money_0);
		save_data.put(MEMBER.money_1, user_money_1);
		new MU(MEMBER._table_name).where(userWhere).save(save_data);
	}

	private static void addBalanceLog(String uid, String admin_id, String money, String money_type, String table_name, String row_id, Integer type, Integer status, String log) throws SQLException {
		Map<String, Object> map = new HashMap<>();
		map.put(MONEY_LOG.uid, uid);
		map.put(MONEY_LOG.admin_id, admin_id);
		map.put(MONEY_LOG.money, money);
		map.put(MONEY_LOG.money_type, money_type);
		map.put(MONEY_LOG.table_name, table_name);
		map.put(MONEY_LOG.row_id, row_id);
		map.put(MONEY_LOG.type, type);
		map.put(MONEY_LOG.status, status);
		map.put(MONEY_LOG.log, log);
		map.put(MONEY_LOG.addtime, System.currentTimeMillis());
		new MU(MONEY_LOG._table_name).data(map).add();
		updateUserMoney(uid);
	}

	// 管理员调整用户余额
	public static void AdministratorAdjustmentBalance(String money, String uid, String admin_id) throws SQLException {
		addBalanceLog(uid, admin_id, money, "0", "", "", Balance.AdministratorAdjustment, 1, "管理员调整用户余额");
	}

	// 用户提现申请
	public static void memberWithdrawal(String money, String uid, String withdrawals_id) throws SQLException {
		addBalanceLog(uid, "0", "-" + money, "0", WITHDRAWALS._table_name, withdrawals_id, 5, 0, "用户提现");
	}

	public static void reward(String table_name, String row_id, String uid, String money, String log) throws SQLException {
		Map<String, W> where = new HashMap<>();
		where.put(MONEY_LOG.table_name, new W("eq", table_name));
		where.put(MONEY_LOG.row_id, new W("eq", row_id));
		where.put(MONEY_LOG.uid, new W("eq", uid));
		where.put(MONEY_LOG.type, new W("eq", Balance.Reward));
		Map<String, Object> info = new MU(MONEY_LOG._table_name).where(where).find();
		if (info == null) {
			addBalanceLog(uid, "0", money, "0", table_name, row_id, Balance.Reward, 1, log);
		} else {
			if (!money.equals(info.get(MONEY_LOG.money).toString())) {
				Map<String, Object> save_data = new HashMap<>();
				save_data.put(MONEY_LOG.money, money);
				save_data.put(MONEY_LOG.log, log);
				new MU(MONEY_LOG._table_name).where(where).save(save_data);
				updateUserMoney(uid);
			}
		}
	}

	// 联盟订单的佣金加入用户余额中
	public static void unionOrderCommissionAddIntoMoneylog(String union_order_id) throws SQLException {
		Map<String, W> where = new HashMap<>();
		where.put(UNION_ORDER.id, new W("eq", union_order_id));
		where.put(UNION_ORDER.status, new W("eq", 1));
		Map<String, Object> info = new MU(UNION_ORDER._table_name).where(where).find();
		if (info == null) {
			return;
		}
		if (!info.get(UNION_ORDER.level_1_id).toString().equals("0")) {
			reward(UNION_ORDER._table_name, union_order_id, info.get(UNION_ORDER.level_1_id).toString(), info.get(UNION_ORDER.level_1_money).toString(), "联盟订单一级奖励，订单号：" + info.get(UNION_ORDER.order_sn).toString());
		}
		if (!info.get(UNION_ORDER.level_2_id).toString().equals("0")) {
			reward(UNION_ORDER._table_name, union_order_id, info.get(UNION_ORDER.level_2_id).toString(), info.get(UNION_ORDER.level_2_money).toString(), "联盟订单二级奖励，订单号：" + info.get(UNION_ORDER.order_sn).toString());
		}
		if (!info.get(UNION_ORDER.level_3_id).toString().equals("0")) {
			reward(UNION_ORDER._table_name, union_order_id, info.get(UNION_ORDER.level_3_id).toString(), info.get(UNION_ORDER.level_3_money).toString(), "联盟订单三级奖励，订单号：" + info.get(UNION_ORDER.order_sn).toString());
		}
	}

	// 管理员审核用户提现申请
	public static boolean AdminMemberWithdrawal(String admin_id, String withdrawal_id, String examine_status, String no_pass_reason) throws SQLException {
		if (examine_status.equals("0")) {
			return false;
		}
		if (!examine_status.equals("1") && no_pass_reason.equals("")) {
			return false;
		}
		Map<String, W> where = new HashMap<>();
		where.put(WITHDRAWALS.id, new W("eq", withdrawal_id));
		where.put(WITHDRAWALS.examine_status, new W("eq", "0"));
		Map<String, Object> info = new MU(WITHDRAWALS._table_name).where(where).find();
		if (info == null) {
			return false;
		}
		// 修改提现记录
		Map<String, Object> data = new HashMap<>();
		data.put(WITHDRAWALS.examine_admin_id, admin_id);
		data.put(WITHDRAWALS.examine_status, examine_status);
		data.put(WITHDRAWALS.no_pass_reason, no_pass_reason);
		data.put(WITHDRAWALS.examine_time, System.currentTimeMillis());
		new MU(WITHDRAWALS._table_name).where(where).save(data);
		// 修改资金日志记录
		where.clear();
		where.put(MONEY_LOG.table_name, new W("eq", WITHDRAWALS._table_name));
		where.put(MONEY_LOG.row_id, new W("eq", withdrawal_id));
		Map<String, Object> save_data = new HashMap<>();
		save_data.put(MONEY_LOG.status, examine_status);
		new MU(MONEY_LOG._table_name).where(where).save(save_data);
		return true;
	}
}
