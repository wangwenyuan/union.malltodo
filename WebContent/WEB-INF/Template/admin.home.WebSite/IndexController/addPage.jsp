<%@page import="common.database.WEBSITE"%>
<%@page import="common.database.LINKS"%>
<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="common.database.DETAIL"%>
<%@page import="common.database.CATEGORY"%>
<%@page import="com.alibaba.fastjson.JSON"%>
<%@page import="common.database.ADMIN"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="common.Common"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../../admin.home.Index/IndexController/sub_header.jsp"%>

<style>
.ui-c-element{ float:left}
.ui-c-note{ height:30px; line-height:30px; font-size:12px; float:left; padding-left:10px;}
</style>
<div style="padding:15px;">
	<div class="main_title">站点信息设置</div>
	<div class="clear_5px"></div>
	<div>
<%
Map<String, Object> info = (HashMap)request.getAttribute("info");
%>

<form method="post" action="" id="html_form">
    <table width="100%" cellpadding="0" cellspacing="0" class="small_main_table">
        <tr><td align="right" width="150px">站点名称：</td><td align="left"><%=Common.template.htmlWidget("text_widget", WEBSITE.website_name, T.getString(WEBSITE.website_name, info))%><span class="ui-c-note">（必填）</span></td></tr>
        <tr><td align="right" width="150px">站点域名：</td><td align="left"><%=Common.template.htmlWidget("text_widget", WEBSITE.website_host, T.getString(WEBSITE.website_host, info))%><span class="ui-c-note">（必填）</span></td></tr>
        <tr><td align="right" width="150px">统计代码：</td><td align="left"><%=Common.template.htmlWidget("textarea_widget", WEBSITE.statistics_code, T.getString(WEBSITE.statistics_code, info)) %></td></tr>
        <tr><td></td><td><input type="button" class="anniu" id="add" value="提交" /></td></tr>
    </table>
</form>
	</div>
</div>

<%@include file="../../admin.home.Index/IndexController/sub_bottom.jsp"%>