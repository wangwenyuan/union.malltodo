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
public class CATEGORY {
	public static String _table_name = "category";
	public static String id = "id";
	public static String pid = "pid";
	public static String website_id = "website_id";
	public static String type = "type";
	public static String seo_title = "seo_title";
	public static String seo_keywords = "seo_keywords";
	public static String seo_description = "seo_description";
	public static String category_name = "category_name";
	public static String category_sub_name = "category_sub_name";
	public static String pic = "pic";
	public static String url = "url";
	public static String smalltext = "smalltext";
	public static String detail = "detail";
	public static String category_type = "category_type";
	public static String pc_list_renovation_id = "pc_list_renovation_id";
	public static String mobile_list_renovation_id = "mobile_list_renovation_id";
	public static String pc_page_renovation_id = "pc_page_renovation_id";
	public static String mobile_page_renovation_id = "mobile_page_renovation_id";
	public static String pc_custom_id = "pc_custom_id";
	public static String mobile_custom_id = "mobile_custom_id";
	public static String order_by = "order_by";
	public static String is_hidden = "is_hidden";
	public static String sort = "sort";
	public static String is_del = "is_del";
public Map<String, Object> map = new HashMap<>();
public CATEGORY() {
map.put("id", "");
map.put("pid", "");
map.put("website_id", "");
map.put("type", "");
map.put("seo_title", "");
map.put("seo_keywords", "");
map.put("seo_description", "");
map.put("category_name", "");
map.put("category_sub_name", "");
map.put("pic", "");
map.put("url", "");
map.put("smalltext", "");
map.put("detail", "");
map.put("category_type", 0);
map.put("pc_list_renovation_id", "");
map.put("mobile_list_renovation_id", "");
map.put("pc_page_renovation_id", "");
map.put("mobile_page_renovation_id", "");
map.put("pc_custom_id", "");
map.put("mobile_custom_id", "");
map.put("order_by", "");
map.put("is_hidden", 0);
map.put("sort", 0);
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
if (key.equals("pid")) {
if (data.get(key).toString().length() > 25) {
return Common.error("pid最长25个字符");
}
}
if (key.equals("website_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("website_id最长25个字符");
}
}
if (key.equals("type")) {
if (data.get(key).toString().length() > 25) {
return Common.error("页面路径最长25个字符");
}
}
if (key.equals("seo_title")) {
if (data.get(key).toString().length() > 255) {
return Common.error("seo_title最长255个字符");
}
}
if (key.equals("seo_keywords")) {
if (data.get(key).toString().length() > 255) {
return Common.error("seo_keywords最长255个字符");
}
}
if (key.equals("seo_description")) {
if (data.get(key).toString().length() > 255) {
return Common.error("seo_description最长255个字符");
}
}
if (key.equals("category_name")) {
if (data.get(key).toString().length() > 99) {
return Common.error("栏目名称最长99个字符");
}
}
if (key.equals("category_sub_name")) {
if (data.get(key).toString().length() > 99) {
return Common.error("栏目别名最长99个字符");
}
}
if (key.equals("pic")) {
if (data.get(key).toString().length() > 255) {
return Common.error("栏目图片最长255个字符");
}
}
if (key.equals("url")) {
if (data.get(key).toString().length() > 255) {
return Common.error("如果不为空，则跳转至该链接最长255个字符");
}
}
if (key.equals("smalltext")) {
if (data.get(key).toString().length() > 65535) {
return Common.error("摘要最长65535个字符");
}
}
if (key.equals("detail")) {
if (data.get(key).toString().length() > 16777215) {
return Common.error("详情最长16777215个字符");
}
}
if (key.equals("category_type")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("栏目类型，0 普通类型，即列表页+详情页；1 单页面；2 自定义页面最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("pc_list_renovation_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("pc_list_renovation_id最长25个字符");
}
}
if (key.equals("mobile_list_renovation_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("mobile_list_renovation_id最长25个字符");
}
}
if (key.equals("pc_page_renovation_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("pc_page_renovation_id最长25个字符");
}
}
if (key.equals("mobile_page_renovation_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("mobile_page_renovation_id最长25个字符");
}
}
if (key.equals("pc_custom_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("pc_custom_id最长25个字符");
}
}
if (key.equals("mobile_custom_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("mobile_custom_id最长25个字符");
}
}
if (key.equals("order_by")) {
if (data.get(key).toString().length() > 99) {
return Common.error("order_by最长99个字符");
}
}
if (key.equals("is_hidden")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("是否隐藏 0:否；1:是最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("sort")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("排序，越小越靠前最大值2147483647");}
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
if (key.equals("pid")) {
if (data.get(key).toString().length() > 25) {
return Common.error("pid最长25个字符");
}
}
if (key.equals("website_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("website_id最长25个字符");
}
}
if (key.equals("type")) {
if (data.get(key).toString().length() > 25) {
return Common.error("页面路径最长25个字符");
}
}
if (key.equals("seo_title")) {
if (data.get(key).toString().length() > 255) {
return Common.error("seo_title最长255个字符");
}
}
if (key.equals("seo_keywords")) {
if (data.get(key).toString().length() > 255) {
return Common.error("seo_keywords最长255个字符");
}
}
if (key.equals("seo_description")) {
if (data.get(key).toString().length() > 255) {
return Common.error("seo_description最长255个字符");
}
}
if (key.equals("category_name")) {
if (data.get(key).toString().length() > 99) {
return Common.error("栏目名称最长99个字符");
}
}
if (key.equals("category_sub_name")) {
if (data.get(key).toString().length() > 99) {
return Common.error("栏目别名最长99个字符");
}
}
if (key.equals("pic")) {
if (data.get(key).toString().length() > 255) {
return Common.error("栏目图片最长255个字符");
}
}
if (key.equals("url")) {
if (data.get(key).toString().length() > 255) {
return Common.error("如果不为空，则跳转至该链接最长255个字符");
}
}
if (key.equals("smalltext")) {
if (data.get(key).toString().length() > 65535) {
return Common.error("摘要最长65535个字符");
}
}
if (key.equals("detail")) {
if (data.get(key).toString().length() > 16777215) {
return Common.error("详情最长16777215个字符");
}
}
if (key.equals("category_type")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("栏目类型，0 普通类型，即列表页+详情页；1 单页面；2 自定义页面最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("pc_list_renovation_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("pc_list_renovation_id最长25个字符");
}
}
if (key.equals("mobile_list_renovation_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("mobile_list_renovation_id最长25个字符");
}
}
if (key.equals("pc_page_renovation_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("pc_page_renovation_id最长25个字符");
}
}
if (key.equals("mobile_page_renovation_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("mobile_page_renovation_id最长25个字符");
}
}
if (key.equals("pc_custom_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("pc_custom_id最长25个字符");
}
}
if (key.equals("mobile_custom_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("mobile_custom_id最长25个字符");
}
}
if (key.equals("order_by")) {
if (data.get(key).toString().length() > 99) {
return Common.error("order_by最长99个字符");
}
}
if (key.equals("is_hidden")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("是否隐藏 0:否；1:是最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("sort")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("排序，越小越靠前最大值2147483647");}
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
