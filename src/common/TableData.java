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
package common;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import common.database.ADMIN;
import common.database.ADMIN_AUTH;
import common.database.ADMIN_GROUP;
import common.database.AGREEMENT;
import common.database.ALISMS;
import common.database.COMMISSION_SET;
import common.database.FILE;
import common.database.GOODS;
import common.database.JD;
import common.database.MEITUAN;
import common.database.MEMBER;
import common.database.MONEY_LOG;
import common.database.PDD;
import common.database.QR;
import common.database.RENOVATION;
import common.database.UNION_ORDER;
import common.database.UNION_ORDER_GOODS;
import common.database.WEIXIN;
import common.database.WEIXIN_MEMBER;
import common.database.WITHDRAWALS;
import common.database.WITHDRAWALS_SET;

public class TableData {
	public static JSONObject checkAddData(String table_name, Map<String, Object> data) {
		if (table_name.equals("admin")) {
			ADMIN admin = new ADMIN();
			return admin.check_add(data);
		}
		if (table_name.equals("admin_auth")) {
			ADMIN_AUTH admin_auth = new ADMIN_AUTH();
			return admin_auth.check_add(data);
		}
		if (table_name.equals("admin_group")) {
			ADMIN_GROUP admin_group = new ADMIN_GROUP();
			return admin_group.check_add(data);
		}
		if (table_name.equals("agreement")) {
			AGREEMENT agreement = new AGREEMENT();
			return agreement.check_add(data);
		}
		if (table_name.equals("alisms")) {
			ALISMS alisms = new ALISMS();
			return alisms.check_add(data);
		}
		if (table_name.equals("commission_set")) {
			COMMISSION_SET commission_set = new COMMISSION_SET();
			return commission_set.check_add(data);
		}
		if (table_name.equals("file")) {
			FILE file = new FILE();
			return file.check_add(data);
		}
		if (table_name.equals("goods")) {
			GOODS goods = new GOODS();
			return goods.check_add(data);
		}
		if (table_name.equals("jd")) {
			JD jd = new JD();
			return jd.check_add(data);
		}
		if (table_name.equals("meituan")) {
			MEITUAN meituan = new MEITUAN();
			return meituan.check_add(data);
		}
		if (table_name.equals("member")) {
			MEMBER member = new MEMBER();
			return member.check_add(data);
		}
		if (table_name.equals("money_log")) {
			MONEY_LOG money_log = new MONEY_LOG();
			return money_log.check_add(data);
		}
		if (table_name.equals("pdd")) {
			PDD pdd = new PDD();
			return pdd.check_add(data);
		}
		if (table_name.equals("qr")) {
			QR qr = new QR();
			return qr.check_add(data);
		}
		if (table_name.equals("renovation")) {
			RENOVATION renovation = new RENOVATION();
			return renovation.check_add(data);
		}
		if (table_name.equals("union_order")) {
			UNION_ORDER union_order = new UNION_ORDER();
			return union_order.check_add(data);
		}
		if (table_name.equals("union_order_goods")) {
			UNION_ORDER_GOODS union_order_goods = new UNION_ORDER_GOODS();
			return union_order_goods.check_add(data);
		}
		if (table_name.equals("weixin")) {
			WEIXIN weixin = new WEIXIN();
			return weixin.check_add(data);
		}
		if (table_name.equals("weixin_member")) {
			WEIXIN_MEMBER weixin_member = new WEIXIN_MEMBER();
			return weixin_member.check_add(data);
		}
		if (table_name.equals("withdrawals")) {
			WITHDRAWALS withdrawals = new WITHDRAWALS();
			return withdrawals.check_add(data);
		}
		if (table_name.equals("withdrawals_set")) {
			WITHDRAWALS_SET withdrawals_set = new WITHDRAWALS_SET();
			return withdrawals_set.check_add(data);
		}
		return null;
	}

	public static JSONObject checkEditData(String table_name, Map<String, Object> data) {
		if (table_name.equals("admin")) {
			ADMIN admin = new ADMIN();
			return admin.check_edit(data);
		}
		if (table_name.equals("admin_auth")) {
			ADMIN_AUTH admin_auth = new ADMIN_AUTH();
			return admin_auth.check_edit(data);
		}
		if (table_name.equals("admin_group")) {
			ADMIN_GROUP admin_group = new ADMIN_GROUP();
			return admin_group.check_edit(data);
		}
		if (table_name.equals("agreement")) {
			AGREEMENT agreement = new AGREEMENT();
			return agreement.check_edit(data);
		}
		if (table_name.equals("alisms")) {
			ALISMS alisms = new ALISMS();
			return alisms.check_edit(data);
		}
		if (table_name.equals("commission_set")) {
			COMMISSION_SET commission_set = new COMMISSION_SET();
			return commission_set.check_edit(data);
		}
		if (table_name.equals("file")) {
			FILE file = new FILE();
			return file.check_edit(data);
		}
		if (table_name.equals("goods")) {
			GOODS goods = new GOODS();
			return goods.check_edit(data);
		}
		if (table_name.equals("jd")) {
			JD jd = new JD();
			return jd.check_edit(data);
		}
		if (table_name.equals("meituan")) {
			MEITUAN meituan = new MEITUAN();
			return meituan.check_edit(data);
		}
		if (table_name.equals("member")) {
			MEMBER member = new MEMBER();
			return member.check_edit(data);
		}
		if (table_name.equals("money_log")) {
			MONEY_LOG money_log = new MONEY_LOG();
			return money_log.check_edit(data);
		}
		if (table_name.equals("pdd")) {
			PDD pdd = new PDD();
			return pdd.check_edit(data);
		}
		if (table_name.equals("qr")) {
			QR qr = new QR();
			return qr.check_edit(data);
		}
		if (table_name.equals("renovation")) {
			RENOVATION renovation = new RENOVATION();
			return renovation.check_edit(data);
		}
		if (table_name.equals("union_order")) {
			UNION_ORDER union_order = new UNION_ORDER();
			return union_order.check_edit(data);
		}
		if (table_name.equals("union_order_goods")) {
			UNION_ORDER_GOODS union_order_goods = new UNION_ORDER_GOODS();
			return union_order_goods.check_edit(data);
		}
		if (table_name.equals("weixin")) {
			WEIXIN weixin = new WEIXIN();
			return weixin.check_edit(data);
		}
		if (table_name.equals("weixin_member")) {
			WEIXIN_MEMBER weixin_member = new WEIXIN_MEMBER();
			return weixin_member.check_edit(data);
		}
		if (table_name.equals("withdrawals")) {
			WITHDRAWALS withdrawals = new WITHDRAWALS();
			return withdrawals.check_edit(data);
		}
		if (table_name.equals("withdrawals_set")) {
			WITHDRAWALS_SET withdrawals_set = new WITHDRAWALS_SET();
			return withdrawals_set.check_edit(data);
		}
		return null;
	}
}
