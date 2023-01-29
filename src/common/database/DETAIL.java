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
public class DETAIL {
	public static String _table_name = "detail";
	public static String id = "id";
	public static String website_id = "website_id";
	public static String admin_id = "admin_id";
	public static String type = "type";
	public static String category_id = "category_id";
	public static String url = "url";
	public static String seo_title = "seo_title";
	public static String seo_keywords = "seo_keywords";
	public static String seo_description = "seo_description";
	public static String title = "title";
	public static String pic = "pic";
	public static String smalltext = "smalltext";
	public static String release_time = "release_time";
	public static String detail = "detail";
	public static String sort = "sort";
	public static String renovation_type = "renovation_type";
	public static String pc_renovation_id = "pc_renovation_id";
	public static String pc_custom_id = "pc_custom_id";
	public static String mobile_renovation_id = "mobile_renovation_id";
	public static String mobile_custom_id = "mobile_custom_id";
	public static String views = "views";
	public static String recommend_level = "recommend_level";
	public static String is_del = "is_del";
public Map<String, Object> map = new HashMap<>();
public DETAIL() {
map.put("id", "");
map.put("website_id", "");
map.put("admin_id", "");
map.put("type", "");
map.put("category_id", "");
map.put("url", "");
map.put("seo_title", "");
map.put("seo_keywords", "");
map.put("seo_description", "");
map.put("title", "");
map.put("pic", "");
map.put("smalltext", "");
map.put("release_time", 0);
map.put("detail", "");
map.put("sort", 0);
map.put("renovation_type", 0);
map.put("pc_renovation_id", "");
map.put("pc_custom_id", "");
map.put("mobile_renovation_id", "");
map.put("mobile_custom_id", "");
map.put("views", 0);
map.put("recommend_level", 0);
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
if (key.equals("admin_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("管理员id最长25个字符");
}
}
if (key.equals("type")) {
if (data.get(key).toString().length() > 99) {
return Common.error("type最长99个字符");
}
}
if (key.equals("category_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("所属的栏目id最长25个字符");
}
}
if (key.equals("url")) {
if (data.get(key).toString().length() > 255) {
return Common.error("如果不为空，则跳转至该链接最长255个字符");
}
}
if (key.equals("seo_title")) {
if (data.get(key).toString().length() > 255) {
return Common.error("网页标题最长255个字符");
}
}
if (key.equals("seo_keywords")) {
if (data.get(key).toString().length() > 255) {
return Common.error("seo关键字最长255个字符");
}
}
if (key.equals("seo_description")) {
if (data.get(key).toString().length() > 255) {
return Common.error("seo_description最长255个字符");
}
}
if (key.equals("title")) {
if (data.get(key).toString().length() > 255) {
return Common.error("文章标题最长255个字符");
}
}
if (key.equals("pic")) {
if (data.get(key).toString().length() > 255) {
return Common.error("标题图片链接最长255个字符");
}
}
if (key.equals("smalltext")) {
if (data.get(key).toString().length() > 65535) {
return Common.error("简介最长65535个字符");
}
}
if (key.equals("release_time")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("发布时间最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("detail")) {
if (data.get(key).toString().length() > 16777215) {
return Common.error("内容详情最长16777215个字符");
}
}
if (key.equals("sort")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("排序，值越大越靠前最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("renovation_type")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("模板类型（0 普通模板 1 自定义模板）最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("pc_renovation_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("0 表示默认模板；非0表示其他模板最长25个字符");
}
}
if (key.equals("pc_custom_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("电脑端自定义模版，如果是0则用新闻详情页对应的模版，如果不是0，则用指定的自定义模版最长25个字符");
}
}
if (key.equals("mobile_renovation_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("0 表示默认模板；非0表示其他模板最长25个字符");
}
}
if (key.equals("mobile_custom_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("手机端自定义模版最长25个字符");
}
}
if (key.equals("views")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("查看次数最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("recommend_level")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("推荐等级（1-9）最大值2147483647");}
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
if (key.equals("admin_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("管理员id最长25个字符");
}
}
if (key.equals("type")) {
if (data.get(key).toString().length() > 99) {
return Common.error("type最长99个字符");
}
}
if (key.equals("category_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("所属的栏目id最长25个字符");
}
}
if (key.equals("url")) {
if (data.get(key).toString().length() > 255) {
return Common.error("如果不为空，则跳转至该链接最长255个字符");
}
}
if (key.equals("seo_title")) {
if (data.get(key).toString().length() > 255) {
return Common.error("网页标题最长255个字符");
}
}
if (key.equals("seo_keywords")) {
if (data.get(key).toString().length() > 255) {
return Common.error("seo关键字最长255个字符");
}
}
if (key.equals("seo_description")) {
if (data.get(key).toString().length() > 255) {
return Common.error("seo_description最长255个字符");
}
}
if (key.equals("title")) {
if (data.get(key).toString().length() > 255) {
return Common.error("文章标题最长255个字符");
}
}
if (key.equals("pic")) {
if (data.get(key).toString().length() > 255) {
return Common.error("标题图片链接最长255个字符");
}
}
if (key.equals("smalltext")) {
if (data.get(key).toString().length() > 65535) {
return Common.error("简介最长65535个字符");
}
}
if (key.equals("release_time")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("发布时间最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("detail")) {
if (data.get(key).toString().length() > 16777215) {
return Common.error("内容详情最长16777215个字符");
}
}
if (key.equals("sort")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("排序，值越大越靠前最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("renovation_type")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("模板类型（0 普通模板 1 自定义模板）最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("pc_renovation_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("0 表示默认模板；非0表示其他模板最长25个字符");
}
}
if (key.equals("pc_custom_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("电脑端自定义模版，如果是0则用新闻详情页对应的模版，如果不是0，则用指定的自定义模版最长25个字符");
}
}
if (key.equals("mobile_renovation_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("0 表示默认模板；非0表示其他模板最长25个字符");
}
}
if (key.equals("mobile_custom_id")) {
if (data.get(key).toString().length() > 25) {
return Common.error("手机端自定义模版最长25个字符");
}
}
if (key.equals("views")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("查看次数最大值2147483647");}
if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
data.put(key, 0);
}
}
if (key.equals("recommend_level")) {
if (T.toInt(data.get(key).toString()) > 2147483647) {
return Common.error("推荐等级（1-9）最大值2147483647");}
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
