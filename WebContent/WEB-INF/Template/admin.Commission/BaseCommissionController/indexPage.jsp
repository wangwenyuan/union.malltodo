<%@page import="common.Common"%>
<%@page import="common.database.COMMISSION_SET"%>
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
<div style="padding:15px;">
<%
String page_action = request.getAttribute("page_action").toString();
%>
	<div class="main_title"><%=page_action %></div>
	<div class="clear_5px"></div>
	<div>
<%
JSONObject info = (JSONObject)request.getAttribute("info");
JSONObject internal_purchase_json = new JSONObject();
internal_purchase_json.put("0", "不开启");
internal_purchase_json.put("1", "开启");
%>

<style>
.ui-c-element{ float:left}
.ui-c-note{ height:30px; line-height:30px; font-size:12px; float:left; padding-left:10px;}
</style>

<div style="padding:15px; font-size:14px; color:red; line-height:20px;">
注意：<br />
1、以每个订单所对应的平台返佣作为分销总金额。设置每个层级的分佣比例。分佣剩余金额即为利润。比如拼多多的一个订单，拼多多的返佣平台（招财进宝平台，网址：https://jinbao.pinduoduo.com/）返佣10元，您所设置的第一层级分销比例是40%，第二层级分销比例是20%，第三层级分销比例是10%，那么会分别给其第一层级的会员分佣4元，给第二层级的会员分佣2元，给第三层级的会员分佣1元，剩下的3元是您的利润。<br />
2、三个层级的分销比例总和不能超过100%。（如果您设置的分销总比例是100%，那么您将会没有利润）<br />
3、如果开启内购，那么购买人（或推广人）自身是第一层级，其上级是第二层级，上级的上级是第三层级，上级的上级的上级不获利；如果不开启内购，那么购买人（或推广人）的上级是第一层级，上级的上级是第二层级，上级的上级的上级是第三层级。购买人（或推广人）自身不获利。
</div>

<form method="post" action="" id="html_form">
    <table width="100%" cellpadding="0" cellspacing="0" class="small_main_table">
        <tr><td align="right" width="150px">第一层级分销比例：</td><td align="left"><%=Common.template.htmlWidget("text_widget", COMMISSION_SET.level_1, T.getString(COMMISSION_SET.level_1, info)) %> <span class="ui-c-note"> % </span> </td></tr>
        <tr><td align="right" width="150px">第二层级分销比例：</td><td align="left"><%=Common.template.htmlWidget("text_widget", COMMISSION_SET.level_2, T.getString(COMMISSION_SET.level_2, info)) %> <span class="ui-c-note"> % </span> </td></tr>
        <tr><td align="right" width="150px">第三层级分销比例：</td><td align="left"><%=Common.template.htmlWidget("text_widget", COMMISSION_SET.level_3, T.getString(COMMISSION_SET.level_3, info)) %> <span class="ui-c-note"> % </span> </td></tr>
        <tr><td align="right" width="150px">是否开启内购：</td><td align="left"><%=Common.template.htmlWidget("select_widget", COMMISSION_SET.is_Internal_purchase, T.getString(COMMISSION_SET.is_Internal_purchase, info), internal_purchase_json.toJSONString()) %>
        <div style="clear:both; padding-top:5px;">
        开启内购：购买人（或推广人）自身是第一层级，其上级是第二层级，上级的上级是第三层级，上级的上级的上级不获利。<br />
        不开启内购：购买人（或推广人）的上级是第一层级，上级的上级是第二层级，上级的上级的上级是第三层级。购买人（或推广人）自身不获利。
        </div> 
        </td></tr>
        <tr><td></td><td><input type="button" class="anniu" id="add" value="提交" /></td></tr>
    </table>
</form>
	</div>
</div>

<%@include file="../../admin.Index/IndexController/bottom.jsp"%>
