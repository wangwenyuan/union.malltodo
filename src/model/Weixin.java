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
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.model.W;
import com.javatodo.core.tools.T;
import com.javatodo.core.tools.XmlTools;

import common.MU;
import common.database.WEIXIN;
import common.database.WEIXIN_MEMBER;
import common.database.WITHDRAWALS;

public class Weixin {
	public static final String WXPAYSDK_VERSION = "WXPaySDK/3.0.9";
	public static final String USER_AGENT = WXPAYSDK_VERSION + " (" + System.getProperty("os.arch") + " " + System.getProperty("os.name") + " " + System.getProperty("os.version") + ") Java/" + System.getProperty("java.version") + " HttpClient/" + HttpClient.class.getPackage().getImplementationVersion();

	public static JSONObject wechat_mch_pay(String uid, String withdrawals_id, String apiclient_cert_path, HttpServletRequest request) throws SQLException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException, KeyManagementException {
		JSONObject ret = new JSONObject();
		// 获取微信基本配置信息
		Map<String, Object> weixin_info = new MU(WEIXIN._table_name).order(WEIXIN.id + " asc").find();
		if (weixin_info == null) {
			ret.put("status", "0");
			ret.put("info", "尚未配置微信支付参数");
			ret.put("url", T.U("SystemSet/Weixin/index", "admin.jsp", request));
			return ret;
		}
		String appid = weixin_info.get(WEIXIN.appid).toString().trim();
		String appsecret = weixin_info.get(WEIXIN.appsecret).toString().trim();
		String mchid = weixin_info.get(WEIXIN.mchid).toString().trim();
		String wxpay_key = weixin_info.get(WEIXIN.wxpay_key).toString().trim();

		if (appid.equals("") || appsecret.toString().equals("") || mchid.equals("") || wxpay_key.toString().equals("")) {
			ret.put("status", "0");
			ret.put("info", "微信支付参数未配置完整");
			ret.put("url", T.U("SystemSet/Weixin/index", "admin.jsp", request));
			return ret;
		}
		// 获取提现订单信息
		Map<String, W> where = new HashMap<String, W>();
		where.put(WITHDRAWALS.id, new W("eq", withdrawals_id));
		where.put(WITHDRAWALS.examine_status, new W("eq", 0));
		Map<String, Object> order_info = new MU(WITHDRAWALS._table_name).where(where).find();
		if (order_info == null) {
			ret.put("status", "0");
			ret.put("info", "该订单无法提现");
			return ret;
		}
		String order_sn = "malltodowithdrawals" + withdrawals_id;
		BigDecimal money = new BigDecimal(order_info.get(WITHDRAWALS.money).toString());
		// 获取用户openid信息
		Map<String, W> weixin_member_where = new HashMap<>();
		weixin_member_where.put(WEIXIN_MEMBER.uid, new W("eq", uid));
		Map<String, Object> weixin_member = new MU(WEIXIN_MEMBER._table_name).where(weixin_member_where).find();
		if (weixin_member == null) {
			ret.put("status", "0");
			ret.put("info", "不存在该用户的微信openid");
			return ret;
		}
		String openid = weixin_member.get(WEIXIN_MEMBER.openid).toString();
		if (openid.equals("")) {
			ret.put("status", "0");
			ret.put("info", "不存在该用户的微信openid为空");
			return ret;
		}

		// cert文件路径
		File cert_file = new File(apiclient_cert_path);
		if (!cert_file.exists()) {
			ret.put("status", "0");
			ret.put("info", "缺少apiclient_cert.p12文件");
			return ret;
		}

		Map<String, Object> params = new HashMap<>();
		params.put("mch_appid", appid);
		params.put("openid", openid);
		params.put("mchid", mchid);
		params.put("nonce_str", T.md5(T.time() + ""));
		params.put("partner_trade_no", order_sn);
		params.put("check_name", "NO_CHECK");
		params.put("amount", money.multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_DOWN).toString());
		params.put("desc", "提现");

		List<Map.Entry<String, Object>> infoIds = new ArrayList<Map.Entry<String, Object>>(params.entrySet());
		// 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Object>>() {
			public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
				return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});
		// 构造签名键值对的格式
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Object> item : infoIds) {
			if (item.getKey() != null || item.getKey() != "") {
				String key = item.getKey();
				String val = item.getValue().toString();
				if (!key.equals("sign") && !val.equals("") && !val.equals(null)) {
					if (sb.toString().equals("")) {
						sb.append(key + "=" + val);
					} else {
						sb.append("&" + key + "=" + val);
					}
				}
			}
		}
		String sign = sb.toString();
		sign = sign + "&key=" + wxpay_key;
		sign = T.md5(sign).toUpperCase();
		params.put("sign", sign);

		String xml = "<xml>";
		for (String key : params.keySet()) {
			if (key.equals("mchid") || key.equals("amount")) {
				xml = xml + "<" + key + ">" + params.get(key).toString() + "</" + key + ">";
			} else {
				xml = xml + "<" + key + "><![CDATA[" + params.get(key).toString() + "]]></" + key + ">";
			}
		}
		xml = xml + "</xml>";

		String url = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
		T.create_log("wechat_mch_pay.log", "请求数据：" + xml);

		char[] password = mchid.toCharArray();

		InputStream certStream = new FileInputStream(new File(apiclient_cert_path));

		// InputStream certStream =
		// ReadPKCS12File.class.getClassLoader().getResourceAsStream(apiclient_cert_path);

		KeyStore ks = KeyStore.getInstance("PKCS12");
		ks.load(certStream, password);
		// 实例化密钥库 & 初始化密钥工厂
		KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		kmf.init(ks, password);

		// 创建 SSLContext
		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());

		SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1" }, null, new DefaultHostnameVerifier());
		BasicHttpClientConnectionManager connManager = new BasicHttpClientConnectionManager(RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslConnectionSocketFactory).build(), null, null, null);
		HttpClient httpClient = HttpClientBuilder.create().setConnectionManager(connManager).build();
		HttpPost httpPost = new HttpPost(url);

		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(8000).setConnectTimeout(6000).build();
		httpPost.setConfig(requestConfig);

		StringEntity postEntity = new StringEntity(xml, "UTF-8");
		httpPost.addHeader("Content-Type", "text/xml");
		httpPost.addHeader("User-Agent", USER_AGENT + " " + mchid);
		httpPost.setEntity(postEntity);

		HttpResponse httpResponse = httpClient.execute(httpPost);
		HttpEntity httpEntity = httpResponse.getEntity();
		String xmlString = EntityUtils.toString(httpEntity, "UTF-8");
		T.create_log("wechat_mch_pay.log", "返回数据：" + xmlString);

		XmlTools tools = new XmlTools(xmlString);
		Map<String, String> map = tools.parseToMap();

		if (map.get("result_code").equals("SUCCESS")) {
			ret.put("status", "1");
			ret.put("info", "提现成功");
			return ret;
		} else {
			ret.put("status", "0");
			ret.put("info", map.get("return_msg"));
			return ret;
		}
	}
}
