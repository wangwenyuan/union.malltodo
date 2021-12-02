<%@page import="common.Common"%>
<%@page import="common.database.JD"%>
<%@page import="common.database.PDD"%>
<%@page import="common.database.QR"%>
<%@page import="common.database.RENOVATION"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../../admin.Index/IndexController/header.jsp"%>
<link href="<%=request.getAttribute("PUBLIC") %>/admin/css/renovation.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/admin/js/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("PUBLIC") %>/admin/css/jquery-ui.css" />
<div style="padding:15px;">
	<div class="main_title">京东参数设置</div>
	<div class="clear_5px"></div>
	<div>
<%
Map<String, Object> info = (HashMap)request.getAttribute("info");
%>

<form method="post" action="" id="html_form">
    <table width="100%" cellpadding="0" cellspacing="0" class="small_main_table">
        <tr><td align="right" width="150px">appkey：</td><td align="left"><%=Common.template.htmlWidget("text_widget", JD.app_key, T.getString(JD.app_key, info)) %></td></tr>
        <tr><td align="right" width="150px">secretkey：</td><td align="left"><%=Common.template.htmlWidget("text_widget", JD.app_secret, T.getString(JD.app_secret, info)) %></td></tr>
        <tr><td align="right" width="150px">网站ID/APP ID：</td><td align="left"><%=Common.template.htmlWidget("text_widget", JD.site_id, T.getString(JD.site_id, info)) %> <br> <span style="color:red">入口：京东联盟-推广管理-网站管理/APP管理-查看网站ID/APP ID（1、接口禁止使用导购媒体id；2、投放链接的网址或应用必须与传入的网站ID/AppID备案一致，否则订单会判“无效-来源与备案网址不符”）</span> </td></tr>
        <tr><td></td><td><input type="button" class="anniu" id="add" value="提交" /></td></tr>
    </table>
</form>
	</div>
</div>

<%@include file="../../admin.Index/IndexController/bottom.jsp"%>
