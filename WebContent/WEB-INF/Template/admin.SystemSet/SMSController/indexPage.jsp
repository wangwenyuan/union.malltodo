<%@page import="common.database.ALISMS"%>
<%@page import="common.Common"%>
<%@page import="common.database.WEIXIN"%>
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

<style>
.ui-c-element{ float:left}
.ui-c-note{ height:30px; line-height:30px; font-size:12px; float:left; padding-left:10px;}
</style>

<div style="padding:15px;">
	<div class="main_title">短信参数设置</div>
	<div class="clear_5px"></div>
	<div>
<%
Map<String, Object> info = (HashMap)request.getAttribute("info");
%>
<div style="padding:15px; font-size:14px; color:red; line-height:20px;">
注意：目前仅支持阿里云短信
</div>
<form method="post" action="" id="html_form">
    <table width="100%" cellpadding="0" cellspacing="0" class="small_main_table">
        <tr><td align="right" width="150px">签名名称：</td><td align="left"><%=Common.template.htmlWidget("text_widget", ALISMS.alisms_signname, T.getString(ALISMS.alisms_signname, info)) %></td></tr>
        <tr><td align="right" width="150px">AccessKey ID：</td><td align="left"><%=Common.template.htmlWidget("text_widget", ALISMS.alisms_access_key_id, T.getString(ALISMS.alisms_access_key_id, info)) %></td></tr>
        <tr><td align="right" width="150px">AccessKey Secret：</td><td align="left"><%=Common.template.htmlWidget("text_widget", ALISMS.alisms_access_key_secret, T.getString(ALISMS.alisms_access_key_secret, info)) %></td></tr>
        <tr><td align="right" width="150px">模版CODE：</td><td align="left"><%=Common.template.htmlWidget("text_widget", ALISMS.alisms_template_code, T.getString(ALISMS.alisms_template_code, info)) %></td></tr>
        <tr><td align="right" width="150px">验证码有效期：</td><td align="left"><%=Common.template.htmlWidget("text_widget", ALISMS.sms_period_of_validity, T.getString(ALISMS.sms_period_of_validity, info)) %> <span class="ui-c-note"> （秒） </span> </td></tr>
        <tr><td></td><td><input type="button" class="anniu" id="add" value="提交" /></td></tr>
    </table>
</form>
	</div>
</div>

<%@include file="../../admin.Index/IndexController/bottom.jsp"%>
