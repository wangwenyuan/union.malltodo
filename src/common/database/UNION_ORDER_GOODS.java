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
public class UNION_ORDER_GOODS {
	public static String _table_name = "union_order_goods";
	public static String id = "id";
	public static String order_sn = "order_sn";
	public static String type = "type";
	public static String goods_id = "goods_id";
	public static String goods_name = "goods_name";
	public static String goods_number = "goods_number";
	public static String goods_pic = "goods_pic";
	public static String status = "status";
public Map<String, Object> map = new HashMap<>();
public UNION_ORDER_GOODS() {
map.put("id", "");
map.put("order_sn", "");
map.put("type", "");
map.put("goods_id", "");
map.put("goods_name", "");
map.put("goods_number", 0);
map.put("goods_pic", "");
map.put("status", 1);
}
public JSONObject check_add(Map<String, Object> data) {
for (String key : data.keySet()) {
if (map.containsKey(key)) {
if (key.equals("id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("id最长25个字符");
}
}
if (key.equals("order_sn")) {
if (data.get(key).toString().length() > 99) {
return Common.error("order_sn最长99个字符");
}
}
if (key.equals("type")) {
if (data.get(key).toString().length() > 25) {
return Common.error("type最长25个字符");
}
}
if (key.equals("goods_id")) {
if (data.get(key).toString().length() > 32) {
return Common.error("goods_id最长32个字符");
}
}
if (key.equals("goods_name")) {
if (data.get(key).toString().length() > 255) {
return Common.error("goods_name最长255个字符");
}
}
if (key.equals("goods_number")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("goods_number最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("goods_pic")) {
if (data.get(key).toString().length() > 255) {
return Common.error("goods_pic最长255个字符");
}
}
if (key.equals("status")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("1 订单中的正常商品  -1 订单中已经去掉的商品最大值2147483647");}
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
if (key.equals("order_sn")) {
if (data.get(key).toString().length() > 99) {
return Common.error("order_sn最长99个字符");
}
}
if (key.equals("type")) {
if (data.get(key).toString().length() > 25) {
return Common.error("type最长25个字符");
}
}
if (key.equals("goods_id")) {
if (data.get(key).toString().length() > 32) {
return Common.error("goods_id最长32个字符");
}
}
if (key.equals("goods_name")) {
if (data.get(key).toString().length() > 255) {
return Common.error("goods_name最长255个字符");
}
}
if (key.equals("goods_number")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("goods_number最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("goods_pic")) {
if (data.get(key).toString().length() > 255) {
return Common.error("goods_pic最长255个字符");
}
}
if (key.equals("status")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("1 订单中的正常商品  -1 订单中已经去掉的商品最大值2147483647");}
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
