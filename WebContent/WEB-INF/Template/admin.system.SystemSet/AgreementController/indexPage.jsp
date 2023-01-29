<%@page import="common.database.AGREEMENT"%>
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
<%@include file="../../admin.home.Index/IndexController/header.jsp"%>
<link href="<%=request.getAttribute("PUBLIC") %>/css/renovation.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/js/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("PUBLIC") %>/css/jquery-ui.css" />
<div style="padding:15px;">
	<div class="main_title">注册协议设置</div>
	<div class="clear_5px"></div>
	<div>
<%
Map<String, Object> info = (HashMap)request.getAttribute("info");
%>

<form method="post" action="" id="html_form">
    <table width="100%" cellpadding="0" cellspacing="0" class="small_main_table">
        <tr><td align="right" width="150px">注册协议：</td><td align="left"><div style="max-width:800px;"><%=Common.template.htmlWidget("editor_widget", AGREEMENT.agreement, T.getString(AGREEMENT.agreement, info)) %></div></td></tr>
        <tr><td></td><td><input type="button" class="anniu" id="add" value="提交" /></td></tr>
    </table>
</form>
	</div>
</div>

<%@include file="../../admin.home.Index/IndexController/bottom.jsp"%>
