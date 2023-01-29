<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<table width="100%" cellpadding="0" cellspacing="0" class="main_table">
      	<tr class="main_table_header">
          	<td width="100px">页面名称</td>
          	<td>链接</td>
          	<td width="50px">操作</td>
      	</tr>
      	<%
      	Map<String, String>linkMap = (HashMap)request.getAttribute("linkMap");
      	for(String key:linkMap.keySet()){
      		out.println("<tr>");
             	out.println("<td>"+key+"</td>");
             	out.println("<td>"+linkMap.get(key)+"</td>");
             	out.println("<td><a style=\"cursor:pointer\" onclick=\"link_selected('"+linkMap.get(key)+"')\">选中</a></td>");
             	out.println("</tr>");
      	}
      	%>
</table>
