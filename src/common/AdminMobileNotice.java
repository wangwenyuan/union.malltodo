package common;

import java.sql.SQLException;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.javatodo.core.tools.SMS;
import com.javatodo.core.tools.T;

import common.database.ALISMS;

public class AdminMobileNotice {
	public static Boolean registerNotice(String mobile) throws SQLException, ClientException {
		Map<String, Object> info = new MU(ALISMS._table_name).order(ALISMS.id + " desc").find();
		if (info == null) {
			T.create_log("sms_error.log", "未配置短信信息");
			return false;
		}
		SMS sms = new SMS(info.get(ALISMS.alisms_access_key_id).toString(), info.get(ALISMS.alisms_access_key_secret).toString());
		JSONObject object = new JSONObject();
		object.put("tel", mobile);
		String json_code = object.toJSONString();
		Boolean ret = sms.send_sms("13073723586", json_code, "MALLTODO", "SMS_245120170");
		if (ret) {
			return true;
		} else {
			T.create_log("sms_error.log", sms.err_code);
			return false;
		}
	}

	public static Boolean bugNotice(String merchant, String program) throws ClientException, SQLException {
		Map<String, Object> info = new MU(ALISMS._table_name).order(ALISMS.id + " desc").find();
		if (info == null) {
			T.create_log("sms_error.log", "未配置短信信息");
			return false;
		}
		SMS sms = new SMS(info.get(ALISMS.alisms_access_key_id).toString(), info.get(ALISMS.alisms_access_key_secret).toString());
		JSONObject object = new JSONObject();
		object.put("merchant_id", merchant);
		object.put("product", program);
		String json_code = object.toJSONString();
		Boolean ret = sms.send_sms("13073723586", json_code, "MALLTODO", "SMS_245175167");
		if (ret) {
			return true;
		} else {
			T.create_log("sms_error.log", sms.err_code);
			return false;
		}
	}
}
