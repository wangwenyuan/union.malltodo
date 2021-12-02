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

public class GOODS {
	public static String _table_name = "goods";
	public static String goods_id = "goods_id";
	public static String goods_name = "goods_name";
	public static String goods_pic = "goods_pic";
	public static String goods_sign = "goods_sign";
	public static String category_name = "category_name";
	public static String mall_name = "mall_name";
	public static String mall_id = "mall_id";
	public static String brand_name = "brand_name";
	public static String promotion_rate = "promotion_rate";
	public static String promotion_money = "promotion_money";
	public static String sales_tip = "sales_tip";
	public static String original_price = "original_price";
	public static String coupon_discount = "coupon_discount";
	public static String price = "price";
	public static String search_id = "search_id";
	public static String platform = "platform";
	public static String cat1 = "cat1";
	public static String cat2 = "cat2";
	public static String cat3 = "cat3";
	public static String url = "url";
	public static String banner = "banner";
	public static String goods_detail = "goods_detail";
	public static String coupon_url = "coupon_url";
	public Map<String, Object> map = new HashMap<>();

	public GOODS() {
		map.put("goods_id", "");
		map.put("goods_name", "");
		map.put("goods_pic", "");
		map.put("goods_sign", "");
		map.put("category_name", "");
		map.put("mall_name", "");
		map.put("mall_id", "");
		map.put("brand_name", "");
		map.put("promotion_rate", "");
		map.put("promotion_money", 0);
		map.put("sales_tip", "");
		map.put("original_price", 0);
		map.put("coupon_discount", 0);
		map.put("price", 0);
		map.put("search_id", "");
		map.put("platform", "");
		map.put("cat1", "");
		map.put("cat2", "");
		map.put("cat3", "");
		map.put("url", "");
		map.put("banner", "");
		map.put("goods_detail", "");
		map.put("coupon_url", "");
	}

	public JSONObject check_add(Map<String, Object> data) {
		for (String key : data.keySet()) {
			if (map.containsKey(key)) {
				if (key.equals("goods_id")) {
					if (data.get(key).toString().length() > 25) {
						return Common.error("商品唯一标识最长25个字符");
					}
				}
				if (key.equals("goods_name")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("商品标题最长255个字符");
					}
				}
				if (key.equals("goods_pic")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("商品图片最长255个字符");
					}
				}
				if (key.equals("goods_sign")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("拼多多商品goods_sign最长99个字符");
					}
				}
				if (key.equals("category_name")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("所属栏目名称最长99个字符");
					}
				}
				if (key.equals("mall_name")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("所属商户名称最长99个字符");
					}
				}
				if (key.equals("mall_id")) {
					if (data.get(key).toString().length() > 25) {
						return Common.error("所属商户id最长25个字符");
					}
				}
				if (key.equals("brand_name")) {
					if (data.get(key).toString().length() > 25) {
						return Common.error("商标名称最长25个字符");
					}
				}
				if (key.equals("promotion_money")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("佣金最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("sales_tip")) {
					if (data.get(key).toString().length() > 25) {
						return Common.error("售卖数量最长25个字符");
					}
				}
				if (key.equals("original_price")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("商品原价最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("coupon_discount")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("优惠券面额最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("price")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("商品价格最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("search_id")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("拼多多平台的搜索id最长99个字符");
					}
				}
				if (key.equals("platform")) {
					if (data.get(key).toString().length() > 25) {
						return Common.error("商品所属平台： pdd jd vip meituan最长25个字符");
					}
				}
				if (key.equals("cat1")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("cat1最长99个字符");
					}
				}
				if (key.equals("cat2")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("cat2最长99个字符");
					}
				}
				if (key.equals("cat3")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("cat3最长99个字符");
					}
				}
				if (key.equals("url")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("url最长255个字符");
					}
				}
				if (key.equals("banner")) {
					if (data.get(key).toString().length() > 65535) {
						return Common.error("banner最长65535个字符");
					}
				}
				if (key.equals("goods_detail")) {
					if (data.get(key).toString().length() > 65535) {
						return Common.error("goods_detail最长65535个字符");
					}
				}
				if (key.equals("coupon_url")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("coupon_url最长255个字符");
					}
				}
				this.map.put(key, data.get(key));
			}
		}
		return Common.success(this.map);
	}

	public JSONObject check_edit(Map<String, Object> data) {
		Map<String, Object> new_data = new HashMap<>();
		for (String key : data.keySet()) {
			if (map.containsKey(key)) {
				if (key.equals("goods_id")) {
					if (data.get(key).toString().length() > 25) {
						return Common.error("商品唯一标识最长25个字符");
					}
				}
				if (key.equals("goods_name")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("商品标题最长255个字符");
					}
				}
				if (key.equals("goods_pic")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("商品图片最长255个字符");
					}
				}
				if (key.equals("goods_sign")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("拼多多商品goods_sign最长99个字符");
					}
				}
				if (key.equals("category_name")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("所属栏目名称最长99个字符");
					}
				}
				if (key.equals("mall_name")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("所属商户名称最长99个字符");
					}
				}
				if (key.equals("mall_id")) {
					if (data.get(key).toString().length() > 25) {
						return Common.error("所属商户id最长25个字符");
					}
				}
				if (key.equals("brand_name")) {
					if (data.get(key).toString().length() > 25) {
						return Common.error("商标名称最长25个字符");
					}
				}
				if (key.equals("promotion_money")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("佣金最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("sales_tip")) {
					if (data.get(key).toString().length() > 25) {
						return Common.error("售卖数量最长25个字符");
					}
				}
				if (key.equals("original_price")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("商品原价最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("coupon_discount")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("优惠券面额最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("price")) {
					if (T.toInt(data.get(key).toString()) > 2147483647) {
						return Common.error("商品价格最大值2147483647");
					}
					if (data.get(key) == null || data.get(key).toString().trim().equals("")) {
						data.put(key, 0);
					}
				}
				if (key.equals("search_id")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("拼多多平台的搜索id最长99个字符");
					}
				}
				if (key.equals("platform")) {
					if (data.get(key).toString().length() > 25) {
						return Common.error("商品所属平台： pdd jd vip meituan最长25个字符");
					}
				}
				if (key.equals("cat1")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("cat1最长99个字符");
					}
				}
				if (key.equals("cat2")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("cat2最长99个字符");
					}
				}
				if (key.equals("cat3")) {
					if (data.get(key).toString().length() > 99) {
						return Common.error("cat3最长99个字符");
					}
				}
				if (key.equals("url")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("url最长255个字符");
					}
				}
				if (key.equals("banner")) {
					if (data.get(key).toString().length() > 65535) {
						return Common.error("banner最长65535个字符");
					}
				}
				if (key.equals("goods_detail")) {
					if (data.get(key).toString().length() > 65535) {
						return Common.error("goods_detail最长65535个字符");
					}
				}
				if (key.equals("coupon_url")) {
					if (data.get(key).toString().length() > 255) {
						return Common.error("coupon_url最长255个字符");
					}
				}
				new_data.put(key, data.get(key));
			}
		}
		return Common.success(new_data);
	}
}
