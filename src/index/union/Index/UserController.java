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
package index.union.Index;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import com.javatodo.core.model.W;
import com.javatodo.core.tools.SMS;
import com.javatodo.core.tools.T;

import common.Functions;
import common.MU;
import common.database.AGREEMENT;
import common.database.ALISMS;
import common.database.MEMBER;
import freemarker.template.TemplateException;

public class UserController extends CommonController {

	private static Map<String, Map<String, Integer>> code_map = new HashMap<>();

	public void indexPage() throws IOException {
		redirect(U("User/Index/index"));
		return;
	}

	public void loginPage() throws IOException, ServletException, TemplateException, SQLException {
		if (IS_POST) {
			if (IS_POST) {
				String mobile = I("mobile").trim();
				if (mobile.equals("")) {
					this.error("手机号不能为空");
					return;
				}
				String password = I("password").trim();
				if (password.equals("")) {
					this.error("密码不能为空");
					return;
				}
				Map<String, W> where = new HashMap<>();
				where.put(MEMBER.mobile, new W("eq", mobile));
				where.put(MEMBER.password, new W("eq", Functions.create_password(password)));
				where.put(MEMBER.is_del, new W("eq", 0));
				Map<String, Object> info = new MU(MEMBER._table_name).where(where).find();
				if (info == null) {
					this.error("用户名或密码有误");
					return;
				} else {
					session("uid", info.get(MEMBER.id).toString());
					session("username", info.get(MEMBER.username).toString());
					this.success("登录成功", T.U("Index/Index/index", "index.union.jsp", request));
					return;
				}
			}
		} else {
			this.display();
		}
	}

	public void registerPage() throws IOException, ServletException, TemplateException, SQLException {
		if (IS_POST) {
			String mobile = I("mobile").trim();
			String password = I("password").trim();
			String code = I("code").trim();
			String xieyi = I("xieyi").trim();
			if (!xieyi.toLowerCase().equals("true")) {
				this.error("必须要同意协议才能注册");
				return;
			}
			if (!T.detect(mobile, "mobile")) {
				this.error("手机号格式有误");
				return;
			}
			if (password.trim().equals("")) {
				this.error("密码不能为空");
				return;
			}
			if (!Functions.check_password(password)) {
				this.error("密码至少要8位，且至少要包含一个大写字母一个小写字母和一个数字");
				return;
			}
			if (code.trim().equals("")) {
				this.error("短信码不能为空");
			}
			// 检测短信码是否正确
			// 验证短信码
			if (!code_map.containsKey(mobile)) {
				this.error("手机号遭到篡改");
				return;
			} else {
				if (!code_map.get(mobile).get("code").toString().equals(code)) {
					this.error("短信码有误");
					return;
				}

				Map<String, Object> sms_info = new MU(ALISMS._table_name).order(ALISMS.id + " asc").find();
				if (sms_info == null) {
					this.error("系统尚未配置短信参数，无法注册");
					return;
				}

				Integer sms_period_of_validity = T.toInt(sms_info.get(ALISMS.sms_period_of_validity).toString());
				if (code_map.get(mobile).get("send_time") < ((int) (System.currentTimeMillis() / 1000) - sms_period_of_validity)) {
					this.error("短信码已过期，短信码有效期为" + (sms_period_of_validity / 60) + "分钟");
					return;
				}
			}
			code_map.remove(mobile);
			// 判断手机号是否存在
			if (this.checkMobileIsExist(mobile)) {
				this.error("该手机号已存在");
				return;
			}
			// 添加会员
			Map<String, Object> data = new HashMap<>();
			data.put(MEMBER.mobile, mobile);
			data.put(MEMBER.password, Functions.create_password(password));
			data.put(MEMBER.username, "tel" + mobile);
			new MU(MEMBER._table_name).data(data).add();
			this.success("注册成功", T.U("Index/Index/index", "index.union.jsp", request));
			return;
		} else {
			this.display();
		}
	}

	public void agreementPage() throws SQLException, IOException, ServletException, TemplateException {
		Map<String, Object> agreement_info = new MU(AGREEMENT._table_name).order(AGREEMENT.id + " asc").find();
		String agreement = "";
		if (agreement_info != null) {
			agreement = agreement_info.get(AGREEMENT.agreement).toString();
		}
		this.assign("agreement", agreement);
		this.display();
	}

	public void send_register_smsPage() throws IOException, ServletException, TemplateException, SQLException {
		if (IS_POST) {
			String verify = I("verify").trim();
			if (!this.check_verify(verify)) {
				this.error("验证码输入错误");
				return;
			}
			String mobile = I("mobile").trim();
			if (mobile.equals("")) {
				this.error("手机号不能为空");
				return;
			}
			if (!T.detect(mobile, "mobile")) {
				this.error("您输入的手机号格式有误");
				return;
			}
			// 判断手机号是否存在
			if (this.checkMobileIsExist(mobile)) {
				this.error("该手机已注册");
				return;
			}
			// 发送短信
			// 生成短信码
			Integer mathCode = (int) (Math.random() * 8999) + 1000;
			Map<String, Integer> map = new HashMap<>();
			map.put("code", mathCode);
			map.put("send_time", (int) (System.currentTimeMillis() / 1000));
			// 发送短信码
			Map<String, Object> sms_info = new MU(ALISMS._table_name).order(ALISMS.id + " asc").find();
			if (sms_info == null) {
				this.error("系统尚未配置短信参数，无法注册");
				return;
			}
			SMS sms = new SMS(sms_info.get(ALISMS.alisms_access_key_id).toString(), sms_info.get(ALISMS.alisms_access_key_secret).toString());
			String json_code = "{\"code\":\"" + mathCode + "\"}";
			try {
				if (sms.send_sms(mobile, json_code, sms_info.get(ALISMS.alisms_signname).toString(), sms_info.get(ALISMS.alisms_template_code).toString())) {
					code_map.put(mobile, map);
					this.success("发送成功");
				} else {
					this.error("发送失败");
				}
			} catch (Exception e) {
				// TODO: handle exception
				this.error("发送失败");
			}
		} else {
			this.display();
		}
	}

	public void find_passwordPage() throws IOException, ServletException, TemplateException, SQLException {
		if (IS_POST) {
			String mobile = I("mobile").trim();
			String code = I("code").trim();
			String password = I("password").trim();
			if (mobile.equals("")) {
				this.error("手机号不能为空");
			}
			if (!T.detect(mobile, "mobile")) {
				this.error("手机号格式有误");
				return;
			}
			if (password.trim().equals("")) {
				this.error("密码不能为空");
				return;
			}
			// 检测密码是否符合要求
			if (!Functions.check_password(password)) {
				this.error("密码至少要8位，且至少要包含一个大写字母一个小写字母和一个数字");
				return;
			}
			if (code.trim().equals("")) {
				this.error("短信码不能为空");
			}
			// 检测短信码是否正确
			// 验证短信码
			if (!code_map.containsKey(mobile)) {
				this.error("手机号遭到篡改");
				return;
			} else {
				if (!code_map.get(mobile).get("code").toString().equals(code)) {
					this.error("短信码有误");
					return;
				}
				Map<String, Object> sms_info = new MU(ALISMS._table_name).order(ALISMS.id + " asc").find();
				if (sms_info == null) {
					this.error("系统尚未配置短信参数，无法注册");
					return;
				}

				Integer sms_period_of_validity = T.toInt(sms_info.get(ALISMS.sms_period_of_validity).toString());
				if (code_map.get(mobile).get("send_time") < ((int) (System.currentTimeMillis() / 1000) - sms_period_of_validity)) {
					this.error("短信码已过期，短信码有效期为" + (sms_period_of_validity / 60) + "分钟");
					return;
				}
			}
			code_map.remove(mobile);
			// 判断手机号是否存在
			if (!this.checkMobileIsExist(mobile)) {
				this.error("该手机号尚未注册");
				return;
			}
			// 修改密码
			Map<String, W> where = new HashMap<>();
			where.put(MEMBER.mobile, new W("eq", mobile));
			where.put(MEMBER.is_del, new W("eq", 0));
			Map<String, Object> data = new HashMap<>();
			data.put(MEMBER.password, Functions.create_password(password));
			new MU(MEMBER._table_name).where(where).save(data);
			this.success("修改成功，请重新登录", U("Index/User/login"));
		} else {
			this.display();
		}
	}

	public void send_find_password_smsPage() throws IOException, ServletException, TemplateException, SQLException {
		if (IS_POST) {
			String verify = I("verify").trim();
			if (!this.check_verify(verify)) {
				this.error("验证码输入错误");
				return;
			}
			String mobile = I("mobile").trim();
			if (mobile.equals("")) {
				this.error("手机号不能为空");
				return;
			}
			if (!T.detect(mobile, "mobile")) {
				this.error("您输入的手机号格式有误");
				return;
			}
			// 判断手机号是否存在
			if (!this.checkMobileIsExist(mobile)) {
				this.error("该手机号尚未注册");
				return;
			}
			// 发送短信
			// 生成短信码
			Integer mathCode = (int) (Math.random() * 8999) + 1000;
			Map<String, Integer> map = new HashMap<>();
			map.put("code", mathCode);
			map.put("send_time", (int) (System.currentTimeMillis() / 1000));
			// 发送短信码

			Map<String, Object> sms_info = new MU(ALISMS._table_name).order(ALISMS.id + " asc").find();
			if (sms_info == null) {
				this.error("系统尚未配置短信参数，无法注册");
				return;
			}
			SMS sms = new SMS(sms_info.get(ALISMS.alisms_access_key_id).toString(), sms_info.get(ALISMS.alisms_access_key_secret).toString());
			String json_code = "{\"code\":\"" + mathCode + "\"}";
			try {
				if (sms.send_sms(mobile, json_code, sms_info.get(ALISMS.alisms_signname).toString(), sms_info.get(ALISMS.alisms_template_code).toString())) {
					code_map.put(mobile, map);
					this.success("发送成功");
				} else {
					this.error("发送失败");
				}
			} catch (Exception e) {
				// TODO: handle exception
				this.error("发送失败");
			}
		}
	}

	public void verifyPage() throws IOException {
		super.Verify();
	}

	private boolean checkMobileIsExist(String mobile) throws SQLException {
		Map<String, W> where = new HashMap<String, W>();
		where.put(MEMBER.mobile, new W("eq", mobile));
		where.put(MEMBER.is_del, new W("eq", 0));
		Map<String, Object> info = new MU(MEMBER._table_name).where(where).find();
		if (info == null) {
			return false;
		} else {
			return true;
		}
	}
}
