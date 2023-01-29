<%@page import="common.AdminMenuConfig"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.javatodo.core.tools.T"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div class="menu_box" onclick="show_widgets_window('基础组件', '<%=T.U("Renovation/ShowWidget/index", "category=union_base_mobile", "admin.union.jsp", request) %>')">基础组件</div>
<div class="menu_box" onclick="show_widgets_window('搜索组件', '<%=T.U("Renovation/ShowWidget/index", "category=union_search_mobile", "admin.union.jsp", request) %>')">搜索组件</div>
<div class="menu_box" onclick="show_widgets_window('团队成员列表', '<%=T.U("Renovation/ShowWidget/index", "category=union_teamList_mobile", "admin.union.jsp", request) %>')">团队成员列表</div>
<div class="menu_box" onclick="show_widgets_window('底部菜单', '<%=T.U("Renovation/ShowWidget/index", "category=union_bottom_mobile", "admin.union.jsp", request) %>')">底部菜单</div>