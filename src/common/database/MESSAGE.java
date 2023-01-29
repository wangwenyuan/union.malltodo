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
public class MESSAGE {
	public static String _table_name = "message";
	public static String id = "id";
	public static String website_id = "website_id";
	public static String name = "name";
	public static String tel = "tel";
	public static String email = "email";
	public static String ip = "ip";
	public static String message = "message";
	public static String addtime = "addtime";
	public static String is_del = "is_del";
public Map<String, Object> map = new HashMap<>();
public MESSAGE() {
map.put("id", "");
map.put("website_id", "");
map.put("name", "");
map.put("tel", "");
map.put("email", "");
map.put("ip", "");
map.put("message", "");
map.put("addtime", 0);
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
if (key.equals("website_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("website_id最长25个字符");
}
}
if (key.equals("name")) {
if (data.get(key).toString().length() > 99) {
return Common.error("name最长99个字符");
}
}
if (key.equals("tel")) {
if (data.get(key).toString().length() > 25) {
return Common.error("tel最长25个字符");
}
}
if (key.equals("email")) {
if (data.get(key).toString().length() > 99) {
return Common.error("email最长99个字符");
}
}
if (key.equals("ip")) {
if (data.get(key).toString().length() > 25) {
return Common.error("ip最长25个字符");
}
}
if (key.equals("message")) {
if (data.get(key).toString().length() > 65535) {
return Common.error("message最长65535个字符");
}
}
if (key.equals("addtime")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("addtime最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
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
if (key.equals("website_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("website_id最长25个字符");
}
}
if (key.equals("name")) {
if (data.get(key).toString().length() > 99) {
return Common.error("name最长99个字符");
}
}
if (key.equals("tel")) {
if (data.get(key).toString().length() > 25) {
return Common.error("tel最长25个字符");
}
}
if (key.equals("email")) {
if (data.get(key).toString().length() > 99) {
return Common.error("email最长99个字符");
}
}
if (key.equals("ip")) {
if (data.get(key).toString().length() > 25) {
return Common.error("ip最长25个字符");
}
}
if (key.equals("message")) {
if (data.get(key).toString().length() > 65535) {
return Common.error("message最长65535个字符");
}
}
if (key.equals("addtime")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("addtime最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
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
