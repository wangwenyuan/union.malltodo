<%@page import="common.Common"%>
<%@page import="common.database.WITHDRAWALS_SET"%>
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
	<div class="main_title">提现设置</div>
	<div class="clear_5px"></div>
	<div>
<%
Map<String, Object> info = (HashMap)request.getAttribute("info");
%>

<form method="post" action="" id="html_form">
    <table width="100%" cellpadding="0" cellspacing="0" class="small_main_table">
        <tr><td align="right" width="150px">可提现最小金额：</td><td align="left"><%=Common.template.htmlWidget("text_widget", WITHDRAWALS_SET.min_money, T.getString(WITHDRAWALS_SET.min_money, info)) %></td></tr>
        <tr><td></td><td><input type="button" class="anniu" id="add" value="提交" /></td></tr>
    </table>
</form>
	</div>
</div>

<%@include file="../../admin.Index/IndexController/bottom.jsp"%>
