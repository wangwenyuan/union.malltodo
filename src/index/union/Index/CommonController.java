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
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.controller.Controller;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.Http;
import com.javatodo.core.tools.T;

import common.MU;
import common.database.MEMBER;
import common.database.WEIXIN;
import common.database.WEIXIN_MEMBER;

public class CommonController extends Controller {
	public JSONObject webRequestParam = new JSONObject();

	public Boolean init() {
		try {
			Boolean weixinBoolean = this.weixin();
			if (!weixinBoolean) {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		webRequestParam = JSONObject.parseObject(JSON.toJSONString(I()));
		webRequestParam.put("uid", session("uid").toString());
		return true;
	}

	private boolean weixin() throws SQLException {
		if (T.isWeixin(request)) {
			if (session("uid") == null || session("uid").toString().equals("")) {
				Map<String, Object> weixin = new MU(WEIXIN._table_name).order(WEIXIN.id + " desc").find();
				// 此处跳转至微信获取openid
				if (I("code").equals("")) {
					String return_back_url = "";
					if (request.getQueryString() == null) {
						return_back_url = T.getRootUrl(request) + "/index.jsp";
					} else {
						return_back_url = T.getRootUrl(request) + "/index.jsp" + "?" + (request.getQueryString());
					}
					try {
						return_back_url = URLEncoder.encode(return_back_url, "UTF-8");
						String redirect_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + weixin.get(WEIXIN.appid) + "&redirect_uri=" + return_back_url + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
						T.create_log("wx_login.txt", "跳转链接：" + redirect_url);
						this.redirect(redirect_url);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return false;
				} else {
					String code = I("code");
					T.create_log("wx_login.txt", "code：" + code);
					String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + weixin.get(WEIXIN.appid) + "&secret=" + weixin.get(WEIXIN.appsecret) + "&code=" + code + "&grant_type=authorization_code";
					Http http = new Http();
					String html = http.get(url);
					T.create_log("wx_login.txt", "code转accesstoken：" + html);
					if (html.equals("")) {
						T.create_log("wx_login.txt", "获取微信信息为空，有可能不是正规code");
						T.create_log("wx_login.txt", "退出007");
						return false;
					}
					JSONObject jsonObject = JSONObject.parseObject(html);
					if (jsonObject == null) {
						T.create_log("wx_login.txt", "获取微信信息使用json解析失败");
						T.create_log("wx_login.txt", "退出008");
						return false;
					}
					String openid = "";
					String access_token = "";
					if (jsonObject.containsKey("openid")) {
						openid = jsonObject.getString("openid").toString();
					} else {
						T.create_log("wx_login.txt", "微信解析后的信息不存在openid");
						T.create_log("wx_login.txt", "退出009");
						return false;
					}
					if (jsonObject.containsKey("access_token")) {
						access_token = jsonObject.getString("access_token").toString();
					} else {
						T.create_log("wx_login.txt", "微信解析后的信息不存在access_token");
						T.create_log("wx_login.txt", "退出010");
						return false;
					}
					T.create_log("wx_login.txt", "access_token：" + access_token);
					T.create_log("wx_login.txt", "openid：" + openid);
					Http wx_http = new Http();
					String wx_html = wx_http.get("https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid + "&lang=zh_CN");
					T.create_log("wx_login.txt", "accesstoken转用户信息：" + wx_html);
					JSONObject wx_obj = JSONObject.parseObject(wx_html);
					String unionid = "";
					String nickname = "";
					String sex = "";
					String headimgurl = "";
					String country = "";
					String province = "";
					String city = "";
					if (!wx_obj.containsKey("errcode")) {
						if (wx_obj.containsKey("unionid")) {
							unionid = wx_obj.getString("unionid");
						}
						nickname = wx_obj.getString("nickname");
						sex = wx_obj.getString("sex");
						headimgurl = wx_obj.getString("headimgurl");
						country = wx_obj.getString("country");
						province = wx_obj.getString("province");
						city = wx_obj.getString("city");
					} else {
						return false;
					}
					// 判断是否存在该用户
					Map<String, W> where = new HashMap<>();
					where.put(WEIXIN_MEMBER.openid, new W("eq", openid));
					Map<String, Object> weixin_info = new MU(WEIXIN_MEMBER._table_name).where(where).find();
					Object new_uid = null;
					if (weixin_info == null) {
						// 新增用户
						Map<String, Object> data = new HashMap<>();
						data.put(MEMBER.username, nickname);
						data.put(MEMBER.createtime, System.currentTimeMillis());
						data.put(MEMBER.pic, headimgurl);
						Map<String, String> pids = this.getPids(I("pid"));
						data.put(MEMBER.pid, pids.get("pid"));
						data.put(MEMBER.ppid, pids.get("ppid"));
						data.put(MEMBER.pppid, pids.get("pppid"));
						new_uid = new MU(MEMBER._table_name).data(data).add();
						Map<String, Object> weixin_data = new HashMap<>();
						weixin_data.put(WEIXIN_MEMBER.openid, openid);
						weixin_data.put(WEIXIN_MEMBER.uid, new_uid);
						weixin_data.put(WEIXIN_MEMBER.unionid, unionid);
						weixin_data.put(WEIXIN_MEMBER.sex, sex);
						weixin_data.put(WEIXIN_MEMBER.country, country);
						weixin_data.put(WEIXIN_MEMBER.province, province);
						weixin_data.put(WEIXIN_MEMBER.city, city);
						new MU(WEIXIN_MEMBER._table_name).data(weixin_data).add();
					} else {
						// 修改用户微信信息
						Map<String, Object> weixin_data = new HashMap<>();
						weixin_data.put(WEIXIN_MEMBER.openid, openid);
						weixin_data.put(WEIXIN_MEMBER.unionid, unionid);
						weixin_data.put(WEIXIN_MEMBER.sex, sex);
						weixin_data.put(WEIXIN_MEMBER.country, country);
						weixin_data.put(WEIXIN_MEMBER.province, province);
						weixin_data.put(WEIXIN_MEMBER.city, city);
						new MU(WEIXIN_MEMBER._table_name).where(WEIXIN_MEMBER.id + "=" + weixin_info.get(WEIXIN_MEMBER.id).toString()).save(weixin_data);
						new_uid = weixin_info.get(WEIXIN_MEMBER.uid);
						// 修改用户用户名和头像
						Map<String, Object> data = new HashMap<>();
						data.put(MEMBER.username, nickname);
						data.put(MEMBER.pic, headimgurl);
						Map<String, W> member_where = new HashMap<>();
						member_where.put(MEMBER.id, new W("eq", new_uid));
						new MU(MEMBER._table_name).where(member_where).save(data);
					}
					session("uid", new_uid);
				}
			}
		}
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
