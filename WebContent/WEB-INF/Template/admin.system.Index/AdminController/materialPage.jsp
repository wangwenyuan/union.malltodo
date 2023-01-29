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
<%
JSONObject info = (JSONObject)request.getAttribute("info");
%>

<form method="post" action="" id="html_form">
    <table width="100%" cellpadding="0" cellspacing="0" class="small_main_table">
        <tr><td align="right" width="150px">登录用户名：</td><td align="left"><%=Common.template.htmlWidget("text_widget", ADMIN.username, T.getString(ADMIN.username, info)) %></td></tr>
        <tr><td align="right" width="150px">登录密码：</td><td align="left"><%=Common.template.htmlWidget("password_widget", ADMIN.password, "") %> (留空表示不修改密码)</td></tr>
        <tr><td align="right" width="150px">联系方式：</td><td align="left"><%=Common.template.htmlWidget("text_widget", ADMIN.mobile, T.getString(ADMIN.mobile, info)) %></td></tr>
        <tr><td></td><td><input type="button" class="anniu" id="add" value="提交" /></td></tr>
    </table>
</form>

<%@include file="../../admin.home.Index/IndexController/sub_bottom.jsp"%>