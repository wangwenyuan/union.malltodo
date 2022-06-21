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
package index.Index;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.javatodo.core.controller.Controller;

import common.MU;
import common.database.MEMBER;

public class CommonController extends Controller {
	public Boolean init() {
		return true;
	}

	private Map<String, String> getPids(String pid) throws SQLException {
		if (pid.equals("") || pid.equals("0") || pid == null) {
			pid = "0";
		}
		Map<String, String> map = new HashMap<>();
		map.put(MEMBER.pid, pid);
		if (pid.equals("0")) {
			map.put(MEMBER.ppid, "0");
			map.put(MEMBER.pppid, "0");
			return map;
		}
		Object ppid = new MU(MEMBER._table_name).where("id=" + pid).getField(MEMBER.pid);
		if (ppid == null) {
			ppid = "0";
		}
		Object pppid = new MU(MEMBER._table_name).where("id=" + ppid).getField(MEMBER.pid);
		if (pppid == null) {
			pppid = "0";
		}
		map.put("ppid", ppid.toString());
		map.put("pppid", pppid.toString());
		return map;
	}
}
