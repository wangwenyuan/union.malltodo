<%@page import="common.Functions"%>
<%@page import="common.AdminMenuConfig"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.javatodo.core.tools.T"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div class="menu_box" onclick="show_widgets_window('基础组件', '<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowWidget/index", "category=home_base_pc", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">基础组件</div>
<!-- <div class="menu_box" onclick="show_widgets_window('搜索组件', '<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowWidget/index", "category=home_search_pc", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">搜索组件</div> -->
<div class="menu_box" onclick="show_widgets_window('栏目组件', '<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowWidget/index", "category=home_menu_pc", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">栏目组件</div>
<div class="menu_box" onclick="show_widgets_window('面包屑导航', '<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowWidget/index", "category=home_index_bread_pc", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">面包屑导航</div>
<div class="menu_box" onclick="show_widgets_window('联系我们组件', '<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowWidget/index", "category=home_index_contactus_detail_pc", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">联系我们组件</div>
<div class="menu_box" onclick="show_widgets_window('底部菜单', '<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowWidget/index", "category=home_bottom_menu_pc", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">底部菜单</div>