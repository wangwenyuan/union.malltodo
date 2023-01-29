<%@page import="common.Common"%>
<%@page import="common.database.CATEGORY"%>
<%@page import="common.database.RENOVATION"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../../admin.home.Index/IndexController/header.jsp"%>
<style>
.ui-c-element {
    width: 3rem;
}
.ui-c-input{
	text-align: center;
	padding-left: 0;
}
</style>
<div style="padding:15px;">
<%
String MODULE_NAME = request.getAttribute("MODULE_NAME").toString();
String CONTROLLER_NAME = request.getAttribute("CONTROLLER_NAME").toString();
%>
            <div class="main_title">栏目设置</div>
            <a onclick="malltodoJs.max_sub_window('新建栏目', '<%=T.U(MODULE_NAME+"/"+CONTROLLER_NAME+"/add", "admin."+menu_category_name+".jsp", request) %>')" class="main_button">新建</a>
            <div class="clear_5px"></div>
              <table width="100%" cellpadding="0" cellspacing="0" class="main_table">
                <tr class="main_table_header">
                  <td>栏目名称</td>
                  <td>类型</td>
                  <td>是否隐藏</td>
                  <td>排序</td>
                  <td>操作</td>
                </tr>
                <%
                JSONArray list = (JSONArray)request.getAttribute("list");
                for(Integer i=0; i<list.size(); i=i+1){
                	JSONObject jsonObject = list.getJSONObject(i);
                	out.println("<tr>");
                	String sign = "";
                	for(Integer n=0; n<jsonObject.getIntValue("level"); n=n+1){
                		sign = sign+"——";
                	}
                	out.println("<td>"+sign+" "+jsonObject.getString(CATEGORY.category_name)+"</td>");
                	out.println("<td>"+Common.home_menu.get(jsonObject.getString(CATEGORY.type))+"</td>");
                	String is_hidden_word = "隐藏，<a href='javascript:adjust(\""+jsonObject.getString(CATEGORY.id)+"\")'>设置为显示</a>";
                	if(jsonObject.getString(CATEGORY.is_hidden).toString().equals("0")){
                		is_hidden_word = "显示，<a href='javascript:adjust(\""+jsonObject.getString(CATEGORY.id)+"\")'>设置为隐藏</a>";
                	}
                	out.println("<td>"+is_hidden_word+"</td>");
                	out.println("<td>"+Common.template.htmlWidget("text_widget", jsonObject.getString("id")+"_"+CATEGORY.sort, T.getString(CATEGORY.sort, jsonObject))+"</td>");
                	out.println("<td><a href=\"javascript:malltodoJs.max_sub_window('编辑', '"+T.U(MODULE_NAME+"/"+CONTROLLER_NAME+"/edit", "id="+jsonObject.getString("id"), "admin."+Functions.getMenuCategoryName(request)+".jsp", request)+"')\">修改</a> <a href=\"javascript:malltodoJs.del('"+T.U(MODULE_NAME+"/"+CONTROLLER_NAME+"/del", "admin."+Functions.getMenuCategoryName(request)+".jsp", request)+"','"+jsonObject.getString("id")+"')\">删除</a></td>");
                	out.println("</tr>");
                }
                %>
                </table>
            </div>
<script>
$(".ui-c-input").blur(function(){
	var id = $(this).attr("id");
	var arr = id.split("_");
	id = arr[0];
	var zhi = $(this).val();
	http.post("<%=T.U("Category/Index/adjustSort", "admin."+Functions.getMenuCategoryName(request)+".jsp") %>", {"id":id, "sort":zhi}, function(data){
		if(data["status"] == 1){
			window.location.reload();
		}
	})
})

function adjust(id){
	http.post("<%=T.U("Category/Index/adjustHidden", "admin."+Functions.getMenuCategoryName(request)+".jsp") %>", {"id":id}, function(data){
		if(data["status"] == 1){
			window.location.reload();
		}
	})
}
</script>
<%@include file="../../admin.home.Index/IndexController/bottom.jsp"%>