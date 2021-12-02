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
          	<td>活动名称</td>
          	<td>描述</td>
          	<td>佣金比例</td>
          	<td>有效期</td>
          	<td width="50px">操作</td>
      	</tr>
      	<%
      	JSONArray list = (JSONArray)request.getAttribute("list");
      	if(list == null){
      		out.println("<tr><td colspan='5'>请检测服务器网络是否连通</td></tr>");
      	}
      	if(list.size() == 0){
      		out.println("<tr><td colspan='5'>暂时没有美团联盟活动</td></tr>");
      	}
      	for(Integer i=0; i<list.size(); i=i+1){
      		JSONObject object = list.getJSONObject(i);
      		out.println("<tr>");
            out.println("<td>"+object.getString("title")+"</td>");
            out.println("<td>"+object.getString("detail")+"</td>");
            out.println("<td>"+object.getString("commission")+"</td>");
            out.println("<td>"+object.getString("period")+"</td>");
            out.println("<td><a style=\"cursor:pointer\" onclick=\"xuanze_meituan('"+object.get("id")+"')\">选中</a></td>");
            out.println("</tr>");
      	}
      	%>
</table>

<script>
function xuanze_meituan(id){
	var url = "<%=T.U("Index/Meituan/index", "index.jsp") %>&id="+id;
	link_selected(url);
}
</script>