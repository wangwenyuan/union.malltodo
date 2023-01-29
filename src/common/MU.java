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

import java.sql.SQLException;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.model.M;

public class MU extends M {
	private String table_name = "";

	private String id = "";

	public MU() {
		super();
	}

	public MU(String table_name) {
		super(table_name);
		this.table_name = table_name;
	}

	public MU(String table_name, Integer dbIndex) {
		super(table_name, dbIndex);
		this.table_name = table_name;
	}

	public MU table(String table_name) {
		this.table_name = table_name;
		super.table(table_name);
		return this;
	}

	public MU data(Map<String, Object> data) {
		JSONObject ret = TableData.checkAddData(table_name, data);
		data = ret.getJSONObject("info").getInnerMap();
		if (!data.containsKey("id") || data.get("id").toString().trim().equals("")) {
			String _id = Functions.createPriKey();
			data.put("id", _id);
			this.id = _id;
		} else {
			this.id = data.get("id").toString().trim();
		}
		super.data(data);
		return this;
	}

	public Object add() throws SQLException {
		super.add();
		return this.id;
	}

	public Integer save(Map<String, Object> data) throws SQLException {
		JSONObject ret = TableData.checkEditData(table_name, data);
		data = ret.getJSONObject("info").getInnerMap();
		if (data.size() > 0) {
			super.data(data);
			return super.save(data);
		} else {
			super.clear();
			return 0;
		}
	}
}
