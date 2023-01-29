package index.Index;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import com.javatodo.core.tools.T;

import common.Functions;
import common.MU;
import common.database.MESSAGE;

public class MessageController extends BaseController {
	public void indexPage() throws IOException, SQLException {
		if (IS_POST) {
			String name = I("name").trim();
			if (name.equals("")) {
				this.error("姓名不能为空");
				return;
			}
			String tel = I("tel").trim();
			if (tel.equals("")) {
				this.error("手机号不能为空");
				return;
			}
			if (!T.detect(tel, "mobile")) {
				this.error("手机号格式有误");
				return;
			}
			String email = I("email").trim();
			String yanzhengma = I("yanzhengma").trim();
			if (!this.check_verify(yanzhengma)) {
				this.error("验证码输入错误");
				return;
			}
			String message = I("message").trim();
			if (message.equals("")) {
				this.error("留言内容不能为空");
				return;
			}

			// 存入数据库
			Map<String, String> data = Functions.trim_array(I());
			data.put(MESSAGE.ip, T.getClientIp(request));
			data.put(MESSAGE.addtime, System.currentTimeMillis() + "");
			data.put(MESSAGE.website_id, session("website_id").toString());
			Object id = new MU(MESSAGE._table_name).data(Functions.I_TO_MAP(data)).add();
			if (id == null) {
				this.error("提交失败");
				return;
			} else {
				this.success("提交成功");
				return;
			}
		} else {
			this.homePagePage("Index/Message/index");
		}
	}
}
