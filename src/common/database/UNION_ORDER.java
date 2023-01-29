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
public class UNION_ORDER {
	public static String _table_name = "union_order";
	public static String id = "id";
	public static String order_sn = "order_sn";
	public static String pay_time = "pay_time";
	public static String type = "type";
	public static String status = "status";
	public static String order_money = "order_money";
	public static String commission = "commission";
	public static String uid = "uid";
	public static String level_1_id = "level_1_id";
	public static String level_1_money = "level_1_money";
	public static String level_2_id = "level_2_id";
	public static String level_2_money = "level_2_money";
	public static String level_3_id = "level_3_id";
	public static String level_3_money = "level_3_money";
	public static String platform_money = "platform_money";
	public static String note = "note";
	public static String is_add_into_money_log = "is_add_into_money_log";
public Map<String, Object> map = new HashMap<>();
public UNION_ORDER() {
map.put("id", "");
map.put("order_sn", "");
map.put("pay_time", 0);
map.put("type", "");
map.put("status", 0);
map.put("order_money", 0.00);
map.put("commission", 0.00);
map.put("uid", "");
map.put("level_1_id", "");
map.put("level_1_money", 0.00);
map.put("level_2_id", "");
map.put("level_2_money", 0.00);
map.put("level_3_id", "");
map.put("level_3_money", 0.00);
map.put("platform_money", 0);
map.put("note", "");
map.put("is_add_into_money_log", 0);
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
if (key.equals("pay_time")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("支付时间最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("type")) {
if (data.get(key).toString().length() > 25) {
return Common.error("type最长25个字符");
}
}
if (key.equals("status")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("0 即将收益 1 收益中 -1 已失效最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("order_money")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("order_money最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("commission")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("佣金最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("uid")) {
if (data.get(key).toString().length() > 25) {
return Common.error("uid最长25个字符");
}
}
if (key.equals("level_1_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("level_1_id最长25个字符");
}
}
if (key.equals("level_1_money")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("level_1_money最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("level_2_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("level_2_id最长25个字符");
}
}
if (key.equals("level_2_money")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("level_2_money最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("level_3_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("level_3_id最长25个字符");
}
}
if (key.equals("level_3_money")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("level_3_money最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("platform_money")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("平台利润最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("note")) {
if (data.get(key).toString().length() > 255) {
return Common.error("订单无效的原因最长255个字符");
}
}
if (key.equals("is_add_into_money_log")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("0 未加入；1 已加入；-1 订单已取消最大值2147483647");}
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
if (key.equals("pay_time")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("支付时间最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("type")) {
if (data.get(key).toString().length() > 25) {
return Common.error("type最长25个字符");
}
}
if (key.equals("status")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("0 即将收益 1 收益中 -1 已失效最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("order_money")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("order_money最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("commission")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("佣金最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("uid")) {
if (data.get(key).toString().length() > 25) {
return Common.error("uid最长25个字符");
}
}
if (key.equals("level_1_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("level_1_id最长25个字符");
}
}
if (key.equals("level_1_money")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("level_1_money最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("level_2_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("level_2_id最长25个字符");
}
}
if (key.equals("level_2_money")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("level_2_money最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("level_3_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("level_3_id最长25个字符");
}
}
if (key.equals("level_3_money")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("level_3_money最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("platform_money")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("平台利润最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("note")) {
if (data.get(key).toString().length() > 255) {
return Common.error("订单无效的原因最长255个字符");
}
}
if (key.equals("is_add_into_money_log")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("0 未加入；1 已加入；-1 订单已取消最大值2147483647");}
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
