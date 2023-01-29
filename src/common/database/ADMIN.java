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
public class ADMIN {
	public static String _table_name = "admin";
	public static String id = "id";
	public static String mobile = "mobile";
	public static String username = "username";
	public static String password = "password";
	public static String name = "name";
	public static String alias = "alias";
	public static String hader_img = "hader_img";
	public static String department = "department";
	public static String role_id = "role_id";
	public static String sort = "sort";
	public static String position = "position";
	public static String gender = "gender";
	public static String email = "email";
	public static String biz_mail = "biz_mail";
	public static String telephone = "telephone";
	public static String is_leader_in_dept = "is_leader_in_dept";
	public static String direct_leader = "direct_leader";
	public static String external_position = "external_position";
	public static String address = "address";
	public static String main_department = "main_department";
	public static String apptoken = "apptoken";
	public static String is_del = "is_del";
public Map<String, Object> map = new HashMap<>();
public ADMIN() {
map.put("id", "");
map.put("mobile", "");
map.put("username", "");
map.put("password", "");
map.put("name", "");
map.put("alias", "");
map.put("hader_img", "");
map.put("department", "");
map.put("role_id", "0");
map.put("sort", 0);
map.put("position", "");
map.put("gender", 1);
map.put("email", "");
map.put("biz_mail", "");
map.put("telephone", "");
map.put("is_leader_in_dept", "");
map.put("direct_leader", "");
map.put("external_position", "");
map.put("address", "");
map.put("main_department", "");
map.put("apptoken", "");
map.put("is_del", 0);
}
public JSONObject check_add(Map<String, Object> data) {
for (String key : data.keySet()) {
if (map.containsKey(key)) {
if (key.equals("id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("id最长25个字符");
}
}
if (key.equals("mobile")) {
if (data.get(key).toString().length() > 25) {
return Common.error("mobile最长25个字符");
}
}
if (key.equals("username")) {
if (data.get(key).toString().length() > 99) {
return Common.error("username最长99个字符");
}
}
if (key.equals("password")) {
if (data.get(key).toString().length() > 32) {
return Common.error("password最长32个字符");
}
}
if (key.equals("name")) {
if (data.get(key).toString().length() > 64) {
return Common.error("name最长64个字符");
}
}
if (key.equals("alias")) {
if (data.get(key).toString().length() > 64) {
return Common.error("alias最长64个字符");
}
}
if (key.equals("hader_img")) {
if (data.get(key).toString().length() > 255) {
return Common.error("hader_img最长255个字符");
}
}
if (key.equals("department")) {
if (data.get(key).toString().length() > 65535) {
return Common.error("department最长65535个字符");
}
}
if (key.equals("role_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("role_id最长25个字符");
}
}
if (key.equals("sort")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("sort最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("position")) {
if (data.get(key).toString().length() > 128) {
return Common.error("position最长128个字符");
}
}
if (key.equals("gender")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("gender最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("email")) {
if (data.get(key).toString().length() > 64) {
return Common.error("email最长64个字符");
}
}
if (key.equals("biz_mail")) {
if (data.get(key).toString().length() > 64) {
return Common.error("biz_mail最长64个字符");
}
}
if (key.equals("telephone")) {
if (data.get(key).toString().length() > 32) {
return Common.error("telephone最长32个字符");
}
}
if (key.equals("is_leader_in_dept")) {
if (data.get(key).toString().length() > 65535) {
return Common.error("is_leader_in_dept最长65535个字符");
}
}
if (key.equals("direct_leader")) {
if (data.get(key).toString().length() > 150) {
return Common.error("direct_leader最长150个字符");
}
}
if (key.equals("external_position")) {
if (data.get(key).toString().length() > 36) {
return Common.error("external_position最长36个字符");
}
}
if (key.equals("address")) {
if (data.get(key).toString().length() > 128) {
return Common.error("address最长128个字符");
}
}
if (key.equals("main_department")) {
if (data.get(key).toString().length() > 25) {
return Common.error("main_department最长25个字符");
}
}
if (key.equals("apptoken")) {
if (data.get(key).toString().length() > 99) {
return Common.error("apptoken最长99个字符");
}
}
if (key.equals("is_del")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("is_del最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
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
if (key.equals("mobile")) {
if (data.get(key).toString().length() > 25) {
return Common.error("mobile最长25个字符");
}
}
if (key.equals("username")) {
if (data.get(key).toString().length() > 99) {
return Common.error("username最长99个字符");
}
}
if (key.equals("password")) {
if (data.get(key).toString().length() > 32) {
return Common.error("password最长32个字符");
}
}
if (key.equals("name")) {
if (data.get(key).toString().length() > 64) {
return Common.error("name最长64个字符");
}
}
if (key.equals("alias")) {
if (data.get(key).toString().length() > 64) {
return Common.error("alias最长64个字符");
}
}
if (key.equals("hader_img")) {
if (data.get(key).toString().length() > 255) {
return Common.error("hader_img最长255个字符");
}
}
if (key.equals("department")) {
if (data.get(key).toString().length() > 65535) {
return Common.error("department最长65535个字符");
}
}
if (key.equals("role_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("role_id最长25个字符");
}
}
if (key.equals("sort")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("sort最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("position")) {
if (data.get(key).toString().length() > 128) {
return Common.error("position最长128个字符");
}
}
if (key.equals("gender")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("gender最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("email")) {
if (data.get(key).toString().length() > 64) {
return Common.error("email最长64个字符");
}
}
if (key.equals("biz_mail")) {
if (data.get(key).toString().length() > 64) {
return Common.error("biz_mail最长64个字符");
}
}
if (key.equals("telephone")) {
if (data.get(key).toString().length() > 32) {
return Common.error("telephone最长32个字符");
}
}
if (key.equals("is_leader_in_dept")) {
if (data.get(key).toString().length() > 65535) {
return Common.error("is_leader_in_dept最长65535个字符");
}
}
if (key.equals("direct_leader")) {
if (data.get(key).toString().length() > 150) {
return Common.error("direct_leader最长150个字符");
}
}
if (key.equals("external_position")) {
if (data.get(key).toString().length() > 36) {
return Common.error("external_position最长36个字符");
}
}
if (key.equals("address")) {
if (data.get(key).toString().length() > 128) {
return Common.error("address最长128个字符");
}
}
if (key.equals("main_department")) {
if (data.get(key).toString().length() > 25) {
return Common.error("main_department最长25个字符");
}
}
if (key.equals("apptoken")) {
if (data.get(key).toString().length() > 99) {
return Common.error("apptoken最长99个字符");
}
}
if (key.equals("is_del")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("is_del最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
new_data.put(key, data.get(key));
}
}
return Common.success(new_data);
}
}
