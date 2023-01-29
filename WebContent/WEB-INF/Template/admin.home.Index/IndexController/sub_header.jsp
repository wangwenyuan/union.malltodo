<%@page import="common.Functions"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.javatodo.core.tools.T"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<%=Functions.header(T.getHost(request)) %>
<%
String menu_category_name = Functions.getMenuCategoryName(request);
String menu_category_show = Functions.getMenuCategoryShow(request);
Map<String, String> _input = (HashMap<String, String>)request.getAttribute("input");
Map<String, Object> input = Functions.I_TO_MAP(_input);
%>
<style>
body{background-color:#FFFFFF}
</style>
</head>

<body>
<div id="inner_content">