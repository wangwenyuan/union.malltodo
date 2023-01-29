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
package common.database;
import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
import com.javatodo.core.tools.T;
import common.Common;
public class WEIXIN_MEMBER {
	public static String _table_name = "weixin_member";
	public static String id = "id";
	public static String uid = "uid";
	public static String openid = "openid";
	public static String unionid = "unionid";
	public static String sex = "sex";
	public static String country = "country";
	public static String province = "province";
	public static String city = "city";
public Map<String, Object> map = new HashMap<>();
public WEIXIN_MEMBER() {
map.put("id", "");
map.put("uid", "");
map.put("openid", "");
map.put("unionid", "");
map.put("sex", "");
map.put("country", "");
map.put("province", "");
map.put("city", "");
}
public JSONObject check_add(Map<String, Object> data) {
for (String key : data.keySet()) {
if (map.containsKey(key)) {
if (key.equals("id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("id最长25个字符");
}
}
if (key.equals("uid")) {
if (data.get(key).toString().length() > 25) {
return Common.error("uid最长25个字符");
}
}
if (key.equals("openid")) {
if (data.get(key).toString().length() > 99) {
return Common.error("openid最长99个字符");
}
}
if (key.equals("unionid")) {
if (data.get(key).toString().length() > 99) {
return Common.error("unionid最长99个字符");
}
}
if (key.equals("sex")) {
if (data.get(key).toString().length() > 25) {
return Common.error("sex最长25个字符");
}
}
if (key.equals("country")) {
if (data.get(key).toString().length() > 25) {
return Common.error("country最长25个字符");
}
}
if (key.equals("province")) {
if (data.get(key).toString().length() > 25) {
return Common.error("province最长25个字符");
}
}
if (key.equals("city")) {
if (data.get(key).toString().length() > 25) {
return Common.error("city最长25个字符");
}
}
this.map.put(key, data.get(key));
}
}
return Common.success(this.map);
}
public JSONObject check_edit(Map<String, Object> data) {
Map<String, Object> new_data = new HashMap<>();for (String key : data.keySet()) {
if (map.containsKey(key)) {
if (key.equals("id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("id最长25个字符");
}
}
if (key.equals("uid")) {
if (data.get(key).toString().length() > 25) {
return Common.error("uid最长25个字符");
}
}
if (key.equals("openid")) {
if (data.get(key).toString().length() > 99) {
return Common.error("openid最长99个字符");
}
}
if (key.equals("unionid")) {
if (data.get(key).toString().length() > 99) {
return Common.error("unionid最长99个字符");
}
}
if (key.equals("sex")) {
if (data.get(key).toString().length() > 25) {
return Common.error("sex最长25个字符");
}
}
if (key.equals("country")) {
if (data.get(key).toString().length() > 25) {
return Common.error("country最长25个字符");
}
}
if (key.equals("province")) {
if (data.get(key).toString().length() > 25) {
return Common.error("province最长25个字符");
}
}
if (key.equals("city")) {
if (data.get(key).toString().length() > 25) {
return Common.error("city最长25个字符");
}
}
new_data.put(key, data.get(key));
}
}
return Common.success(new_data);
}
}
