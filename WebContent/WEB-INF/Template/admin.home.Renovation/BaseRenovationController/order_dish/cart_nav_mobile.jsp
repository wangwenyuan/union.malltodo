<%@page import="common.Functions"%>
<%@page import="common.AdminMenuConfig"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.javatodo.core.tools.T"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div class="menu_box" onclick="show_widgets_window('基础组件', '<%=T.U("OrderDish/ShowWidget/index", "category=home_base_mobile", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">基础组件</div>
<div class="menu_box" onclick="show_widgets_window('搜索组件', '<%=T.U("OrderDish/ShowWidget/index", "category=order_dish_search_mobile", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">搜索组件</div>
<div class="menu_box" onclick="show_widgets_window('菜品组件', '<%=T.U("OrderDish/ShowWidget/index", "category=order_dish_cart_mobile", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">购物车组件</div>
<div class="menu_box" onclick="show_widgets_window('底部菜单', '<%=T.U("OrderDish/ShowWidget/index", "category=home_bottom_mobile", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">底部菜单</div>