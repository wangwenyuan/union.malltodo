<%@page import="common.database.ADMIN"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../../admin.home.Index/IndexController/header.jsp"%>
<div style="padding:15px;">
            <div class="main_title">成员管理</div>
            <a onclick="malltodoJs.sub_window('新建成员', '<%=T.U("Index/Admin/add", "admin.system.jsp", request) %>')" class="main_button">新建</a>
            <div class="clear_5px"></div>
              <table width="100%" cellpadding="0" cellspacing="0" class="main_table">
                <tr class="main_table_header">
                  <td>登录用户名</td>
                  <td>权限</td>
                  <td>联系方式</td>
                  <td width="100px">操作</td>
                </tr>
                <%
                JSONArray list = (JSONArray)request.getAttribute("list");
                if(list.size() == 0){
                	out.println("<tr>");
                	out.println("<td colspan=4>尚未创建成员</td>");
                	out.println("</tr>");
                }
                for(Integer i=0; i<list.size(); i=i+1){
                	JSONObject jsonObject = list.getJSONObject(i);
                	if(jsonObject.getString(ADMIN.role_id).equals("0")){
                		jsonObject.put("group_name", "超级管理员");
                	}
                	out.println("<tr>");
                	out.println("<td>"+jsonObject.getString(ADMIN.username)+"</td>");
                	out.println("<td>"+jsonObject.getString("group_name")+"</td>");
                	out.println("<td>"+jsonObject.getString(ADMIN.mobile)+"</td>");
                	out.println("<td><a href=\"javascript:malltodoJs.sub_window('编辑', '"+T.U("Index/Admin/edit", "id="+jsonObject.getString("id"), "admin.system.jsp", request)+"')\">修改</a> <a href=\"javascript:malltodoJs.del('"+T.U("Index/Admin/del", "admin.system.jsp", request)+"','"+jsonObject.getString("id")+"')\">删除</a></td>");
                	out.println("</tr>");
                }
                %>
                </table>
            </div>
<%@include file="../../admin.home.Index/IndexController/bottom.jsp"%>