<%@page import="common.Functions"%>
<%@page import="common.database.RENOVATION"%>
<%@page import="com.javatodo.core.tools.T"%>
<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<table width="100%" cellpadding="0" cellspacing="0" class="main_table">
      	<tr class="main_table_header">
          	<td>自定义页面名称</td>
          	<td>链接</td>
          	<td width="50px">操作</td>
      	</tr>
      	<%
      	JSONArray list = (JSONArray)request.getAttribute("list");
      	if(list.size() == 0){
      		out.println("<tr><td colspan='5'>您尚未创建自定义页面，<a target='_blank' href='"+T.U(request.getAttribute("MODULE_NAME").toString()+"/Custom/index", "admin."+Functions.getMenuCategoryName(request)+".jsp", request)+"'>前去创建自定义页面</a></td></tr>");
      	}
      	for(Integer i=0; i<list.size(); i=i+1){
      		JSONObject object = list.getJSONObject(i);
      		out.println("<tr>");
            out.println("<td>"+object.getString(RENOVATION.name)+"</td>");
            out.println("<td>"+T.U("Index/Index/custom", "index", request)+"&id="+object.getString("id")+"</td>");
            out.println("<td><a style=\"cursor:pointer\" onclick=\"link_selected('"+T.U("Index/Index/custom", "index", request)+"&id="+object.getString("id")+"')\">选中</a></td>");
            out.println("</tr>");
      	}
      	%>
</table>