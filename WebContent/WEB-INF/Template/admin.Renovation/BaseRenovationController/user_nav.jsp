<%@page import="common.AdminMenuConfig"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.javatodo.core.tools.T"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div class="menu_box" onclick="show_widgets_window('基础组件', '<%=T.U("Renovation/ShowWidget/index", "category=base", "admin.jsp") %>')">基础组件</div>
<div class="menu_box" onclick="show_widgets_window('会员信息组件', '<%=T.U("Renovation/ShowWidget/index", "category=user", "admin.jsp") %>')">会员信息</div>
<div class="menu_box" onclick="show_widgets_window('搜索组件', '<%=T.U("Renovation/ShowWidget/index", "category=search", "admin.jsp") %>')">搜索组件</div>
<div class="menu_box" onclick="show_widgets_window('商品组件', '<%=T.U("Renovation/ShowWidget/index", "category=goods", "admin.jsp") %>')">商品组件</div>
<div class="menu_box" onclick="show_widgets_window('底部菜单', '<%=T.U("Renovation/ShowWidget/index", "category=bottom", "admin.jsp") %>')">底部菜单</div>