<%@page import="common.database.RENOVATION"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../../admin.home.Index/IndexController/header.jsp"%>
<div style="padding:15px;">
<%
String MODULE_NAME = request.getAttribute("MODULE_NAME").toString();
String CONTROLLER_NAME = request.getAttribute("CONTROLLER_NAME").toString();
String page_action = request.getAttribute("page_action").toString();
%>
            <div class="main_title"><%=page_action %>设计</div>
            
            <a onclick="malltodoJs.max_sub_window('新建<%=page_action %>设计', '<%=T.U(MODULE_NAME+"/"+CONTROLLER_NAME+"/add", "admin."+menu_category_name+".jsp", request) %>')" class="main_button">新建</a>
            <div class="clear_5px"></div>
              <table width="100%" cellpadding="0" cellspacing="0" class="main_table">
                <tr class="main_table_header">
                  <td>页面名称</td>
                  <td>创建时间</td>
                  <td>最后修改时间</td>
                  <%
                  if(AdminMenuConfig.getMenu().getJSONObject(MODULE_NAME).getJSONObject(CONTROLLER_NAME).containsKey("setDefault")){
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
                	if(AdminMenuConfig.getMenu().getJSONObject(MODULE_NAME).getJSONObject(CONTROLLER_NAME).containsKey("setDefault")){
                		out.println("<td><div><input onclick=\"changeDefault('"+jsonObject.getString("id")+"')\" class=\"ui-c-open\" type=\"checkbox\" name=\"open\" "+checked+" /></div></td>");
                	}
                	out.println("<td><a href=\"javascript:malltodoJs.max_sub_window('编辑', '"+T.U(MODULE_NAME+"/"+CONTROLLER_NAME+"/edit", "id="+jsonObject.getString("id"), "admin."+Functions.getMenuCategoryName(request)+".jsp", request)+"')\">修改</a> <a href=\"javascript:malltodoJs.del('"+T.U(MODULE_NAME+"/"+CONTROLLER_NAME+"/del", "admin."+Functions.getMenuCategoryName(request)+".jsp", request)+"','"+jsonObject.getString("id")+"')\">删除</a></td>");
                	out.println("</tr>");
                }
                if(list.size() == 0){
                	out.println("<tr>");
                	if(AdminMenuConfig.getMenu().getJSONObject(MODULE_NAME).getJSONObject(CONTROLLER_NAME).containsKey("setDefault")){
                		out.println("<td colspan=5>尚未创建任何模板</td>");
                	}else{
                		out.println("<td colspan=4>尚未创建任何模板</td>");
                	}
                	out.println("</tr>");
                }
                %>
                </table>
                <div class="page"><%=request.getAttribute("page").toString() %></div>
            </div>
            
<script>
function changeDefault(id){
	var url = "<%=T.U(MODULE_NAME+"/"+CONTROLLER_NAME+"/setDefault", "admin."+Functions.getMenuCategoryName(request)+".jsp", request)%>";
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
            
<%@include file="../../admin.home.Index/IndexController/bottom.jsp"%>