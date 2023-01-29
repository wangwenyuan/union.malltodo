<%@page import="common.Functions"%>
<%@page import="common.AdminMenuConfig"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.javatodo.core.tools.T"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div class="menu_box" onclick="show_widgets_window('网站底部', '<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowWidget/index", "category=home_bottom_menu_pc", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">网站底部</div>