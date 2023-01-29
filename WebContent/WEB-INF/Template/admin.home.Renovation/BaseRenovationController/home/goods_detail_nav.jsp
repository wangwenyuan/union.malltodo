<%@page import="common.Functions"%>
<%@page import="common.AdminMenuConfig"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.javatodo.core.tools.T"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div class="menu_box" onclick="show_widgets_window('基础组件', '<%=T.U("Renovation/ShowWidget/index", "category=base", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">基础组件</div>
<div class="menu_box" onclick="show_widgets_window('搜索组件', '<%=T.U("Renovation/ShowWidget/index", "category=search", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">搜索组件</div>
<div class="menu_box" onclick="show_widgets_window('商品轮播图组件', '<%=T.U("Renovation/ShowWidget/index", "category=goodsBanner", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">商品轮播图</div>
<div class="menu_box" onclick="show_widgets_window('商品名称价格组件', '<%=T.U("Renovation/ShowWidget/index", "category=goodsName", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">商品名称价格</div>
<div class="menu_box" onclick="show_widgets_window('商品优惠券组件', '<%=T.U("Renovation/ShowWidget/index", "category=goodsCoupon", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">商品优惠券</div>
<div class="menu_box" onclick="show_widgets_window('商品详情组件', '<%=T.U("Renovation/ShowWidget/index", "category=goodsDetail", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">商品详情</div>
<div class="menu_box" onclick="show_widgets_window('商品详情页专用底部菜单组件', '<%=T.U("Renovation/ShowWidget/index", "category=goodsBottom", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">专用底部菜单</div>
<div class="menu_box" onclick="show_widgets_window('底部菜单', '<%=T.U("Renovation/ShowWidget/index", "category=bottom", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">底部菜单</div>