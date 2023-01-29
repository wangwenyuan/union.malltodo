package common;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

import common.widget.data.HomeIndexAlbum;
import common.widget.data.HomeIndexAlbumDetail;
import common.widget.data.HomeIndexAlbumList;
import common.widget.data.HomeIndexBread;
import common.widget.data.HomeIndexBrief;
import common.widget.data.HomeIndexBriefDetail;
import common.widget.data.HomeIndexBriefList;
import common.widget.data.HomeIndexBusiness;
import common.widget.data.HomeIndexBusinessDetail;
import common.widget.data.HomeIndexBusinessList;
import common.widget.data.HomeIndexCase;
import common.widget.data.HomeIndexCaseDetail;
import common.widget.data.HomeIndexCaseList;
import common.widget.data.HomeIndexContactUs;
import common.widget.data.HomeIndexContactUsDetail;
import common.widget.data.HomeIndexContactUsList;
import common.widget.data.HomeIndexJob;
import common.widget.data.HomeIndexJobDetail;
import common.widget.data.HomeIndexJobList;
import common.widget.data.HomeIndexLinks;
import common.widget.data.HomeIndexMessage;
import common.widget.data.HomeIndexMessageList;
import common.widget.data.HomeIndexNews;
import common.widget.data.HomeIndexNewsDetail;
import common.widget.data.HomeIndexNewsList;
import common.widget.data.HomeIndexProducts;
import common.widget.data.HomeIndexProductsDetail;
import common.widget.data.HomeIndexProductsList;
import common.widget.data.HomeIndexSearch;
import common.widget.data.HomeIndexSearchList;
import common.widget.data.HomeMenu;
import common.widget.data.UnionCategory;
import common.widget.data.UnionCategoryGoodsList;
import common.widget.data.UnionCategoryName;
import common.widget.data.UnionGoods;
import common.widget.data.UnionGoodsBanner;
import common.widget.data.UnionGoodsBottom;
import common.widget.data.UnionGoodsCoupon;
import common.widget.data.UnionGoodsDetail;
import common.widget.data.UnionGoodsName;
import common.widget.data.UnionIncomeList;
import common.widget.data.UnionMenu;
import common.widget.data.UnionMoneyLogList;
import common.widget.data.UnionOrderList;
import common.widget.data.UnionSearchGoodsList;
import common.widget.data.UnionTeamList;
import common.widget.data.UnionUser;

public class BindData {

	// webRequestParam：需要传入的其他参数，比如栏目的id或者商品的id 搜索的关键字等等
	public static JSONObject bind(JSONObject dom, JSONObject webRequestParam, HttpServletRequest request, List<String> bind_loop_list) throws SQLException, UnsupportedEncodingException {
		JSONObject object = new JSONObject();
		if (dom.getString("category").equals("home_menu_pc") || dom.getString("category").equals("home_menu_mobile") || dom.getString("category").equals("home_bottom_menu_pc") || dom.getString("category").equals("home_bottom_menu_mobile")) {
			object = new HomeMenu().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}

		if (dom.getString("category").equals("home_index_bread_pc")) {
			object = new HomeIndexBread().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}

		if (dom.getString("category").equals("home_index_products_pc") || dom.getString("category").equals("home_index_products_mobile")) {
			object = new HomeIndexProducts().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}
		if (dom.getString("category").equals("home_index_products_list_pc") || dom.getString("category").equals("home_index_products_list_mobile") || dom.getString("category").equals("home_index_products_page_pc") || dom.getString("category").equals("home_index_products_page_mobile")) {
			object = new HomeIndexProductsList().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}
		if (dom.getString("category").equals("home_index_products_detail_pc") || dom.getString("category").equals("home_index_products_detail_mobile")) {
			object = new HomeIndexProductsDetail().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}

		if (dom.getString("category").equals("home_index_news_pc") || dom.getString("category").equals("home_index_news_mobile")) {
			object = new HomeIndexNews().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}
		if (dom.getString("category").equals("home_index_news_list_pc") || dom.getString("category").equals("home_index_news_list_mobile") || dom.getString("category").equals("home_index_news_page_pc") || dom.getString("category").equals("home_index_news_page_mobile")) {
			object = new HomeIndexNewsList().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}
		if (dom.getString("category").equals("home_index_news_detail_pc") || dom.getString("category").equals("home_index_news_detail_mobile")) {
			object = new HomeIndexNewsDetail().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}

		if (dom.getString("category").equals("home_index_business_pc") || dom.getString("category").equals("home_index_business_mobile")) {
			object = new HomeIndexBusiness().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}
		if (dom.getString("category").equals("home_index_business_list_pc") || dom.getString("category").equals("home_index_business_list_mobile") || dom.getString("category").equals("home_index_business_page_pc") || dom.getString("category").equals("home_index_business_page_mobile")) {
			object = new HomeIndexBusinessList().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}
		if (dom.getString("category").equals("home_index_business_detail_pc") || dom.getString("category").equals("home_index_business_detail_mobile")) {
			object = new HomeIndexBusinessDetail().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}

		if (dom.getString("category").equals("home_index_album_pc") || dom.getString("category").equals("home_index_album_mobile")) {
			object = new HomeIndexAlbum().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}
		if (dom.getString("category").equals("home_index_album_list_pc") || dom.getString("category").equals("home_index_album_list_mobile") || dom.getString("category").equals("home_index_album_page_pc") || dom.getString("category").equals("home_index_album_page_mobile")) {
			object = new HomeIndexAlbumList().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}
		if (dom.getString("category").equals("home_index_album_detail_pc") || dom.getString("category").equals("home_index_album_detail_mobile")) {
			object = new HomeIndexAlbumDetail().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}

		if (dom.getString("category").equals("home_index_job_pc") || dom.getString("category").equals("home_index_job_mobile")) {
			object = new HomeIndexJob().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}
		if (dom.getString("category").equals("home_index_job_list_pc") || dom.getString("category").equals("home_index_job_list_mobile") || dom.getString("category").equals("home_index_job_page_pc") || dom.getString("category").equals("home_index_job_page_mobile")) {
			object = new HomeIndexJobList().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}
		if (dom.getString("category").equals("home_index_job_detail_pc") || dom.getString("category").equals("home_index_job_detail_mobile")) {
			object = new HomeIndexJobDetail().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}

		if (dom.getString("category").equals("home_index_message_pc") || dom.getString("category").equals("home_index_message_mobile")) {
			object = new HomeIndexMessage().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}
		if (dom.getString("category").equals("home_index_message_list_pc") || dom.getString("category").equals("home_index_message_list_mobile")) {
			object = new HomeIndexMessageList().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}

		if (dom.getString("category").equals("home_index_search_pc") || dom.getString("category").equals("home_index_search_mobile")) {
			object = new HomeIndexSearch().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}
		if (dom.getString("category").equals("home_index_search_list_pc") || dom.getString("category").equals("home_index_search_list_mobile")) {
			object = new HomeIndexSearchList().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}

		if (dom.getString("category").equals("home_index_case_pc") || dom.getString("category").equals("home_index_case_mobile")) {
			object = new HomeIndexCase().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}
		if (dom.getString("category").equals("home_index_case_list_pc") || dom.getString("category").equals("home_index_case_list_mobile") || dom.getString("category").equals("home_index_case_page_pc") || dom.getString("category").equals("home_index_case_page_mobile")) {
			object = new HomeIndexCaseList().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}
		if (dom.getString("category").equals("home_index_case_detail_pc") || dom.getString("category").equals("home_index_case_detail_mobile")) {
			object = new HomeIndexCaseDetail().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}

		if (dom.getString("category").equals("home_index_links_pc") || dom.getString("category").equals("home_index_links_mobile")) {
			object = new HomeIndexLinks().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}

		if (dom.getString("category").equals("home_index_brief_pc") || dom.getString("category").equals("home_index_brief_mobile")) {
			object = new HomeIndexBrief().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}
		if (dom.getString("category").equals("home_index_brief_list_pc") || dom.getString("category").equals("home_index_brief_list_mobile") || dom.getString("category").equals("home_index_brief_page_pc") || dom.getString("category").equals("home_index_brief_page_mobile")) {
			object = new HomeIndexBriefList().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}
		if (dom.getString("category").equals("home_index_brief_detail_pc") || dom.getString("category").equals("home_index_brief_detail_mobile")) {
			object = new HomeIndexBriefDetail().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}

		if (dom.getString("category").equals("home_index_contactus_pc") || dom.getString("category").equals("home_index_contactus_mobile")) {
			object = new HomeIndexContactUs().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}
		if (dom.getString("category").equals("home_index_contactus_list_pc") || dom.getString("category").equals("home_index_contactus_list_mobile") || dom.getString("category").equals("home_index_contactus_page_pc") || dom.getString("category").equals("home_index_contactus_page_mobile")) {
			object = new HomeIndexContactUsList().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}
		if (dom.getString("category").equals("home_index_contactus_detail_pc") || dom.getString("category").equals("home_index_contactus_detail_mobile")) {
			object = new HomeIndexContactUsDetail().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request, bind_loop_list);
		}

		// ------------数据绑定之数据获取开始-------------
		if (dom.getString("category").equals("union_category_mobile")) {
			object = new UnionCategory().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request);
		}

		if (dom.getString("category").equals("union_menu_mobile")) {
			object = new UnionMenu().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request);
		}

		if (dom.getString("category").equals("union_goods_mobile")) {
			object = new UnionGoods().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request);
		}

		if (dom.getString("category").equals("union_categoryName_mobile")) {
			object = new UnionCategoryName().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request);
		}

		if (dom.getString("category").equals("union_categoryGoodsList_mobile")) {
			object = new UnionCategoryGoodsList().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request);
		}

		if (dom.getString("category").equals("union_searchGoodsList_mobile")) {
			object = new UnionSearchGoodsList().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request);
		}

		if (dom.getString("category").equals("union_goodsBanner_mobile")) {
			object = new UnionGoodsBanner().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request);
		}

		if (dom.getString("category").equals("union_goodsBottom_mobile")) {
			object = new UnionGoodsBottom().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request);
		}

		if (dom.getString("category").equals("union_goodsCoupon_mobile")) {
			object = new UnionGoodsCoupon().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request);
		}

		if (dom.getString("category").equals("union_goodsDetail_mobile")) {
			object = new UnionGoodsDetail().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request);
		}

		if (dom.getString("category").equals("union_goodsName_mobile")) {
			object = new UnionGoodsName().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request);
		}

		if (dom.getString("category").equals("union_user_mobile")) {
			object = new UnionUser().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request);
		}

		if (dom.getString("category").equals("union_teamList_mobile")) {
			object = new UnionTeamList().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request);
		}

		if (dom.getString("category").equals("union_moneyLogList_mobile")) {
			object = new UnionMoneyLogList().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request);
		}
		if (dom.getString("category").equals("union_orderList_mobile")) {
			object = new UnionOrderList().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request);
		}
		if (dom.getString("category").equals("union_incomeList_mobile")) {
			object = new UnionIncomeList().getValue(dom.getJSONObject("javatodo-bind-param"), webRequestParam, request);
		}

		// ------------数据绑定之数据获取结束-------------

		return object;
	}
}
