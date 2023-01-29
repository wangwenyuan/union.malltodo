<%@page import="common.Functions"%>
<%@page import="com.javatodo.core.tools.T"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<a class="admin_home" href="<%=T.U("Index/Index/index", "admin.jsp", request)%>">营销型官网</a>
<a class="admin_union" href="<%=T.U("Index/Index/index", "admin.union.jsp", request)%>">电商联盟</a>
<!--  
<a class="admin_operation" href="<%=T.U("Index/Index/index", "admin.operation.jsp", request)%>">运营中心</a>
<a class="admin_marketing" href="<%=T.U("Index/Index/index", "admin.marketing.jsp", request)%>">营销中心</a>
<a class="admin_crm" href="<%=T.U("Index/Index/index", "admin.crm.jsp", request)%>">会员中心</a>
<a class="admin_report" href="<%=T.U("Index/Index/index", "admin.report.jsp", request)%>">报表中心</a>
-->
<a class="admin_system" href="<%=T.U("Index/Index/index", "admin.system.jsp", request)%>">设置</a>