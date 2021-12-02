<%@page import="com.alibaba.fastjson.JSON"%>
<%@page import="common.database.MEMBER"%>
<%@page import="common.database.ADMIN"%>
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
Map<String, Object>map = new HashMap();
map.put("1", "增加");
map.put("2", "减少");
%>

<form method="post" action="" id="html_form">
    <table width="100%" cellpadding="0" cellspacing="0" class="small_main_table">
        <tr><td align="right" width="150px">登录用户名：</td><td align="left"><%=Common.template.htmlWidget("select_widget", "type", "1", JSON.toJSONString(map))%></td></tr>
        <tr><td align="right" width="150px">金额：</td><td align="left"><%=Common.template.htmlWidget("text_widget", MEMBER.money, "")%> </td></tr>
        <tr><td></td><td><input type="button" class="anniu" id="add" value="提交" /></td></tr>
    </table>
</form>

<%@include file="../../admin.Index/IndexController/sub_bottom.jsp"%>