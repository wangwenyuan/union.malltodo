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
package index.union.User;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import com.javatodo.core.tools.T;

import common.MU;
import common.database.MEMBER;
import common.database.WITHDRAWALS;
import common.database.WITHDRAWALS_SET;
import freemarker.template.TemplateException;
import model.Balance;
import model.Member;

public class WithdrawalController extends UserCommonController {
	public void indexPage() throws IOException, ServletException, TemplateException, SQLException {
		Map<String, Object> withdrawals_set_info = new MU(WITHDRAWALS_SET._table_name).order(WITHDRAWALS_SET.id + " asc").find();
		BigDecimal min_money = new BigDecimal(withdrawals_set_info.get(WITHDRAWALS_SET.min_money).toString());

		// 更新会员余额
		Balance.updateUserMoney(session("uid").toString());

		Member member = new Member();
		Map<String, Object> member_info = member.get_member(session("uid").toString());
		BigDecimal money_0 = new BigDecimal(member_info.get(MEMBER.money_0).toString());
		if (IS_POST) {
			// 提现金额是否为空
			String _money = I("money").trim();
			if (_money.equals("")) {
				this.error("请输入提现金额");
				return;
			}
			// 是否达到最小提现金额
			BigDecimal money = new BigDecimal(T.toFloat(_money));
			if (money.compareTo(min_money) < 0) {
				this.error("未达到最小提现金额");
				return;
			}
			if (money.compareTo(new BigDecimal("0.01")) < 0) {
				this.error("您输入的金额有误");
				return;
			}
			// 该会员余额是否足够
			if (money.compareTo(money_0) > -1) {
				this.error("余额不足");
				return;
			}
			// 写入提现表
			Map<String, Object> data = new HashMap<String, Object>();
			data.put(WITHDRAWALS.uid, session("uid").toString());
			data.put(WITHDRAWALS.money, money);
			data.put(WITHDRAWALS.addtime, System.currentTimeMillis());
			Object _withdrawals_id = new MU(WITHDRAWALS._table_name).data(data).add();
			// 写入资金日志表
			Balance.memberWithdrawal(money.toString(), session("uid").toString(), _withdrawals_id.toString());
			// 申请成功，请等待管理员审核
			this.success("申请成功，请等待管理员审核", U("User/MoneyLog/index"));
			return;
		} else {
			this.assign("money", money_0);
			this.display();
		}
	}
}
