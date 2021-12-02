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
package admin.Commission;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.T;

import admin.Index.CommonController;
import common.MU;
import common.database.COMMISSION_SET;
import freemarker.template.TemplateException;

public class BaseCommissionController extends CommonController {
	protected String platform = "";

	public void indexPage() throws SQLException, IOException, ServletException, TemplateException {
		if (this.platform.equals("")) {
			this.error("请指定平台");
			return;
		}
		Map<String, W> where = new HashMap<String, W>();
		where.put(COMMISSION_SET.platform, new W("eq", this.platform));
		Map<String, Object> info = new MU(COMMISSION_SET._table_name).where(where).find();
		if (IS_POST) {
			Map<String, Object> data = new HashMap<>();
			BigDecimal level_1 = new BigDecimal(T.toFloat(I(COMMISSION_SET.level_1).trim()));
			BigDecimal level_2 = new BigDecimal(T.toFloat(I(COMMISSION_SET.level_2).trim()));
			BigDecimal level_3 = new BigDecimal(T.toFloat(I(COMMISSION_SET.level_3).trim()));
			BigDecimal he = level_1.add(level_2).add(level_3);
			if (he.compareTo(new BigDecimal(100)) == 1) {
				this.error("三个层级的分销比例之和不能大于100%");
				return;
			}
			data.put(COMMISSION_SET.level_1, I(COMMISSION_SET.level_1).trim());
			data.put(COMMISSION_SET.level_2, I(COMMISSION_SET.level_2).trim());
			data.put(COMMISSION_SET.level_3, I(COMMISSION_SET.level_3).trim());
			data.put(COMMISSION_SET.platform, platform);
			data.put(COMMISSION_SET.is_Internal_purchase, I(COMMISSION_SET.is_Internal_purchase).trim());
			if (info == null) {
				// System
				new MU(COMMISSION_SET._table_name).data(data).add();
			} else {
				where = new HashMap<>();
				where.put(COMMISSION_SET.id, new W("eq", info.get(COMMISSION_SET.id).toString()));
				new MU(COMMISSION_SET._table_name).where(where).save(data);
			}
			this.success("设置成功");
		} else {
			this.assign("info", JSONObject.parse(JSON.toJSONString(info)));
			this.display("Commission/BaseCommission/index");
		}
	}
}
