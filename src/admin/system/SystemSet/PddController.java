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
package admin.system.SystemSet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import com.javatodo.core.model.W;

import admin.home.Index.CommonController;
import common.Functions;
import common.MU;
import common.database.PDD;
import freemarker.template.TemplateException;

public class PddController extends CommonController {
	public void indexPage() throws SQLException, IOException, ServletException, TemplateException {
		Map<String, Object> info = new MU(PDD._table_name).order(PDD.id + " desc").find();
		if (IS_POST) {
			Map<String, Object> data = Functions.I_TO_MAP(I());
			if (info == null) {
				new MU(PDD._table_name).data(data).add();
			} else {
				Map<String, W> where = new HashMap<>();
				where.put(PDD.id, new W("eq", info.get(PDD.id)));
				new MU(PDD._table_name).where(where).save(data);
			}
			this.success("设置成功");
		} else {
			this.assign("info", info);
			this.display();
		}
	}
}