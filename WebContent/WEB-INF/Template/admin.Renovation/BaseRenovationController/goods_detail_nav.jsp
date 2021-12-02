<%@page import="common.AdminMenuConfig"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.javatodo.core.tools.T"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div class="menu_box" onclick="show_widgets_window('基础组件', '<%=T.U("Renovation/ShowWidget/index", "category=base", "admin.jsp") %>')">基础组件</div>
<div class="menu_box" onclick="show_widgets_window('搜索组件', '<%=T.U("Renovation/ShowWidget/index", "category=search", "admin.jsp") %>')">搜索组件</div>
<div class="menu_box" onclick="show_widgets_window('商品轮播图组件', '<%=T.U("Renovation/ShowWidget/index", "category=goodsBanner", "admin.jsp") %>')">商品轮播图</div>
<div class="menu_box" onclick="show_widgets_window('商品名称价格组件', '<%=T.U("Renovation/ShowWidget/index", "category=goodsName", "admin.jsp") %>')">商品名称价格</div>
<div class="menu_box" onclick="show_widgets_window('商品优惠券组件', '<%=T.U("Renovation/ShowWidget/index", "category=goodsCoupon", "admin.jsp") %>')">商品优惠券</div>
<div class="menu_box" onclick="show_widgets_window('商品详情组件', '<%=T.U("Renovation/ShowWidget/index", "category=goodsDetail", "admin.jsp") %>')">商品详情</div>
<div class="menu_box" onclick="show_widgets_window('商品详情页专用底部菜单组件', '<%=T.U("Renovation/ShowWidget/index", "category=goodsBottom", "admin.jsp") %>')">专用底部菜单</div>
<div class="menu_box" onclick="show_widgets_window('底部菜单', '<%=T.U("Renovation/ShowWidget/index", "category=bottom", "admin.jsp") %>')">底部菜单</div>