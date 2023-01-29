<%@page import="common.Functions"%>
<%@page import="common.AdminMenuConfig"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.javatodo.core.tools.T"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div class="menu_box" onclick="show_widgets_window('基础组件', '<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowWidget/index", "category=home_base_pc", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">基础组件</div>
<!-- <div class="menu_box" onclick="show_widgets_window('信息检索', '<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowWidget/index", "category=home_index_search_pc", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">信息检索</div> -->
<div class="menu_box" onclick="show_widgets_window('栏目组件', '<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowWidget/index", "category=home_menu_pc", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">栏目组件</div>
<div class="menu_box" onclick="show_widgets_window('公司简介', '<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowWidget/index", "category=home_index_brief_pc", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">公司简介</div>
<div class="menu_box" onclick="show_widgets_window('商品组件', '<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowWidget/index", "category=home_index_products_pc", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">商品组件</div>
<div class="menu_box" onclick="show_widgets_window('新闻组件', '<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowWidget/index", "category=home_index_news_pc", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">新闻组件</div>
<div class="menu_box" onclick="show_widgets_window('业务范围', '<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowWidget/index", "category=home_index_business_pc", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">业务范围</div>
<div class="menu_box" onclick="show_widgets_window('应用案例', '<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowWidget/index", "category=home_index_case_pc", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">应用案例</div>
<div class="menu_box" onclick="show_widgets_window('公司相册', '<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowWidget/index", "category=home_index_album_pc", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">公司相册</div>
<div class="menu_box" onclick="show_widgets_window('人力招聘', '<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowWidget/index", "category=home_index_job_pc", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">人力招聘</div>
<div class="menu_box" onclick="show_widgets_window('联系我们', '<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowWidget/index", "category=home_index_contactus_pc", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">联系我们</div>
<!-- <div class="menu_box" onclick="show_widgets_window('客户留言', '<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowWidget/index", "category=home_index_message_pc", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">客户留言</div> -->
<div class="menu_box" onclick="show_widgets_window('友情链接', '<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowWidget/index", "category=home_index_links_pc", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">友情链接</div>
<div class="menu_box" onclick="show_widgets_window('底部菜单', '<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowWidget/index", "category=home_bottom_menu_pc", "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>')">底部菜单</div>