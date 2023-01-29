package common;

import java.sql.SQLException;

import com.javatodo.config.C;
import com.javatodo.core.model.M;

public class DB {

	public static String check_flag = "_zyx__";

	public static boolean createTable(String table_name) {
		return createTable(table_name, 0);
	}

	public static boolean createTable(String table_name, Integer dbIndex) {
		if (dbIndex > C.table_pre.length - 1) {
			return false;
		} else {
			M m = new M(dbIndex);
			table_name = C.table_pre[dbIndex] + check_flag + table_name;
			try {
				m.transaction();
				String sql = "CREATE TABLE `" + table_name + "` ( `id` char(25) NOT NULL DEFAULT '', `addtime` bigint(20) NOT NULL, `modify_time` bigint(20) NOT NULL, `admin_id` char(25) NOT NULL, `uid` char(25) NOT NULL, `is_del` tinyint(4) NOT NULL DEFAULT '0', PRIMARY KEY (`id`), KEY `is_del` (`is_del`)) ENGINE=InnoDB DEFAULT CHARSET=utf8";
				m.execute(sql);
				m.commit();
				return true;
			} catch (SQLException e) {
				// TODO: handle exception
				m.rollback();
				return false;
			}
		}
	}

	// public static boolean addField(String table_name, String field_name, String
	// field_type) {

	// }
}
