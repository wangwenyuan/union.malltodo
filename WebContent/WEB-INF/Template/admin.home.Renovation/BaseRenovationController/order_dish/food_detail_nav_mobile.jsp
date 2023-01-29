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
<div class="menu_box" onclick="show_widgets_window('菜品轮播图组件', '<%=T.U("OrderDish/ShowWidget/index", "category=order_dish_food_banner_mobile", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">菜品轮播图</div>
<div class="menu_box" onclick="show_widgets_window('菜品名称价格组件', '<%=T.U("OrderDish/ShowWidget/index", "category=order_dish_food_name_mobile", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">菜品名称价格</div>
<div class="menu_box" onclick="show_widgets_window('菜品详情组件', '<%=T.U("OrderDish/ShowWidget/index", "category=order_dish_food_detail_mobile", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">菜品详情</div>
<div class="menu_box" onclick="show_widgets_window('购物车按钮组件', '<%=T.U("OrderDish/ShowWidget/index", "category=order_dish_food_addcart_mobile", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">购物车按钮</div>
<div class="menu_box" onclick="show_widgets_window('底部菜单', '<%=T.U("OrderDish/ShowWidget/index", "category=home_bottom_mobile", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">底部菜单</div>