<%@page import="common.database.RENOVATION"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../../admin.Index/IndexController/header.jsp"%>
<div style="padding:15px;">
<%
String CONTROLLER_NAME = request.getAttribute("CONTROLLER_NAME").toString();
String page_action = request.getAttribute("page_action").toString();
%>
            <div class="main_title"><%=page_action %>设计</div>
            <a onclick="shop.max_sub_window('新建<%=page_action %>设计', '<%=T.U("Renovation/"+CONTROLLER_NAME+"/add", "admin.jsp") %>')" class="main_button">新建</a>
            <div class="clear_5px"></div>
              <table width="100%" cellpadding="0" cellspacing="0" class="main_table">
                <tr class="main_table_header">
                  <td>页面名称</td>
                  <td>创建时间</td>
                  <td>最后修改时间</td>
                  <%
                  if(!CONTROLLER_NAME.equals("Bottom") && !CONTROLLER_NAME.equals("Custom")){
                  %>
                	  <td>是否开启</td>
                  <%
                  }
                  %>
                  <td>操作</td>
                </tr>
                <%
                JSONArray list = (JSONArray)request.getAttribute("list");
                for(Integer i=0; i<list.size(); i=i+1){
                	JSONObject jsonObject = list.getJSONObject(i);
                	out.println("<tr>");
                	out.println("<td>"+jsonObject.getString(RENOVATION.name)+"</td>");
                	out.println("<td>"+T.date("yyyy-MM-dd HH:mm:ss", jsonObject.getLongValue(RENOVATION.addtime))+"</td>");
                	out.println("<td>"+T.date("yyyy-MM-dd HH:mm:ss", jsonObject.getLongValue(RENOVATION.last_edit_time))+"</td>");
                	String checked = "";
                	if(jsonObject.getString(RENOVATION.is_default).equals("1")){
                		checked = "checked=\"checked\"";
                	}
                	if(!CONTROLLER_NAME.equals("Bottom") && !CONTROLLER_NAME.equals("Custom")){
                		out.println("<td><div><input onclick=\"changeDefault("+jsonObject.getString("id")+")\" class=\"ui-c-open\" type=\"checkbox\" name=\"open\" "+checked+" /></div></td>");
                	}
                	out.println("<td><a href=\"javascript:shop.max_sub_window('编辑', '"+T.U("Renovation/"+CONTROLLER_NAME+"/edit", "id="+jsonObject.getString("id"), "admin.jsp")+"')\">修改</a> <a href=\"javascript:shop.del('"+T.U("Renovation/"+CONTROLLER_NAME+"/del", "admin.jsp")+"','"+jsonObject.getString("id")+"')\">删除</a></td>");
                	out.println("</tr>");
                }
                %>
                </table>
                <div class="page"><%=request.getAttribute("page").toString() %></div>
            </div>
            
<script>
function changeDefault(id){
	var url = "<%=T.U("Renovation/"+CONTROLLER_NAME+"/setDefault", "admin.jsp")%>";
	$.ajax({
		async : true,
		type : "POST",
		dataType : "json",
		url : url,
		data : {
			id:id
		},
		success : function(data){
			layer.msg(data["info"], {
                time: 2000
            }, function () {
            	window.location.reload();
            })
		},
		error : function(){
			layer.msg("网络错误", {
                time: 2000
            }, function () {
            	window.location.reload();
            })
		}
	});
}
</script>
            
<%@include file="../../admin.Index/IndexController/bottom.jsp"%>