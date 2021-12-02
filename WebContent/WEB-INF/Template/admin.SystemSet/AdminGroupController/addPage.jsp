<%@page import="com.alibaba.fastjson.JSON"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="common.database.ADMIN_GROUP"%>
<%@page import="common.Common"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../../admin.Index/IndexController/sub_header.jsp"%>
<%
JSONObject info = (JSONObject)request.getAttribute("info");
Map map = (LinkedHashMap)request.getAttribute("map");
%>

<form method="post" action="" id="html_form">
    <table width="100%" cellpadding="0" cellspacing="0" class="small_main_table">
        <tr><td align="right" width="150px">岗位名称：</td><td align="left"><%=Common.template.htmlWidget("text_widget", ADMIN_GROUP.name, T.getString(ADMIN_GROUP.name, info)) %></td></tr>
        <tr><td align="right" width="150px">上级岗位：</td><td align="left"><%=Common.template.htmlWidget("select_widget", ADMIN_GROUP.pid, T.getString(ADMIN_GROUP.pid, info), JSON.toJSONString(map)) %></td></tr>
        <tr><td align="right" width="150px">岗位排序：</td><td align="left"><%=Common.template.htmlWidget("text_widget", ADMIN_GROUP.sort, T.getString(ADMIN_GROUP.sort, info)) %></td></tr>
        <tr><td></td><td><input type="button" class="anniu" id="add" value="提交" /></td></tr>
    </table>
</form>

<%@include file="../../admin.Index/IndexController/sub_bottom.jsp"%>