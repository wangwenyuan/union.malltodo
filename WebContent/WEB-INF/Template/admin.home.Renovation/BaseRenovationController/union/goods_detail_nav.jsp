<%@page import="common.AdminMenuConfig"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.javatodo.core.tools.T"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div class="menu_box" onclick="show_widgets_window('基础组件', '<%=T.U("Renovation/ShowWidget/index", "category=union_base_mobile", "admin.union.jsp", request) %>')">基础组件</div>
<div class="menu_box" onclick="show_widgets_window('搜索组件', '<%=T.U("Renovation/ShowWidget/index", "category=union_search_mobile", "admin.union.jsp", request) %>')">搜索组件</div>
<div class="menu_box" onclick="show_widgets_window('商品轮播图组件', '<%=T.U("Renovation/ShowWidget/index", "category=union_goodsBanner_mobile", "admin.union.jsp", request) %>')">商品轮播图</div>
<div class="menu_box" onclick="show_widgets_window('商品名称价格组件', '<%=T.U("Renovation/ShowWidget/index", "category=union_goodsName_mobile", "admin.union.jsp", request) %>')">商品名称价格</div>
<div class="menu_box" onclick="show_widgets_window('商品优惠券组件', '<%=T.U("Renovation/ShowWidget/index", "category=union_goodsCoupon_mobile", "admin.union.jsp", request) %>')">商品优惠券</div>
<div class="menu_box" onclick="show_widgets_window('商品详情组件', '<%=T.U("Renovation/ShowWidget/index", "category=union_goodsDetail_mobile", "admin.union.jsp", request) %>')">商品详情</div>
<div class="menu_box" onclick="show_widgets_window('商品详情页专用底部菜单组件', '<%=T.U("Renovation/ShowWidget/index", "category=union_goodsBottom_mobile", "admin.union.jsp", request) %>')">专用底部菜单</div>
<div class="menu_box" onclick="show_widgets_window('底部菜单', '<%=T.U("Renovation/ShowWidget/index", "category=union_bottom_mobile", "admin.union.jsp", request) %>')">底部菜单</div>