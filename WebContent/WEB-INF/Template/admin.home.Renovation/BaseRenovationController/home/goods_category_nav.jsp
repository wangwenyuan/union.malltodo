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
<div class="menu_box" onclick="show_widgets_window('栏目名称组件', '<%=T.U("Renovation/ShowWidget/index", "category=categoryName", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">栏目名称组件</div>
<div class="menu_box" onclick="show_widgets_window('商品列表', '<%=T.U("Renovation/ShowWidget/index", "category=categoryGoodsList", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">商品列表</div>
<div class="menu_box" onclick="show_widgets_window('底部菜单', '<%=T.U("Renovation/ShowWidget/index", "category=bottom", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">底部菜单</div>