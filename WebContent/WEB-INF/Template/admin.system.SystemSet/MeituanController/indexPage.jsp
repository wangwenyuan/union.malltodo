<%@page import="common.Functions"%>
<%@page import="common.database.MEITUAN"%>
<%@page import="common.Common"%>
<%@page import="common.database.PDD"%>
<%@page import="common.database.QR"%>
<%@page import="common.database.RENOVATION"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../../admin.home.Index/IndexController/header.jsp"%>
<link href="<%=request.getAttribute("PUBLIC") %>/css/renovation.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/js/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("PUBLIC") %>/css/jquery-ui.css" />
<div style="padding:15px;">
	<div class="main_title">美团参数设置</div>
	<div class="clear_5px"></div>
	<div>
<%
Map<String, Object> info = (HashMap)request.getAttribute("info");
%>

<div style="padding:15px; font-size:14px; color:red; line-height:20px;">
回调地址：<%=T.getRootUrl(request) %>/meituan_union_notify.jsp <br />
请将此回调地址填入美团联盟“我要推广”->“联盟API接口”中的“回调地址”一栏。
</div>

<form method="post" action="" id="html_form">
    <table width="100%" cellpadding="0" cellspacing="0" class="small_main_table">
        <tr><td align="right" width="150px">媒体Appkey：</td><td align="left"><%=Common.template.htmlWidget("text_widget", MEITUAN.app_key, T.getString(MEITUAN.app_key, info)) %></td></tr>
        <tr><td align="right" width="150px">签名密钥：</td><td align="left"><%=Common.template.htmlWidget("text_widget", MEITUAN.app_secret, T.getString(MEITUAN.app_secret, info)) %></td></tr>
        <tr><td></td><td><input type="button" class="anniu" id="add" value="提交" /></td></tr>
    </table>
</form>
	</div>
</div>

<%@include file="../../admin.home.Index/IndexController/bottom.jsp"%>
