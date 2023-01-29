<%@page import="common.database.ROLE"%>
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
.renovation_type_0{ display:none}
.renovation_type_1{ display:none}
</style>
<div style="padding:15px;">
	<div>
<%
Map<String, Object> info = (HashMap)request.getAttribute("info");
%>

<form method="post" action="" id="html_form">
    <table width="100%" cellpadding="0" cellspacing="0" class="small_main_table">
        <tr><td align="right" width="150px">名称：</td><td align="left"><%=Common.template.htmlWidget("text_widget", ROLE.role_name, T.getString(ROLE.role_name, info))%>（必填）</td></tr>
        <tr><td></td><td><input type="button" class="anniu" id="add" value="提交" /></td></tr>
    </table>    
</form>
	</div>
</div>

<%@include file="../../admin.home.Index/IndexController/sub_bottom.jsp"%>