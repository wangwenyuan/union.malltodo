<%@page import="common.database.ADMIN_GROUP"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../../admin.Index/IndexController/header.jsp"%>
<div style="padding:15px;">
            <div class="main_title">岗位管理</div>
            <a onclick="shop.sub_window('新建岗位', '<%=T.U("SystemSet/AdminGroup/add", "admin.jsp") %>')" class="main_button">新建</a>
            <div class="clear_5px"></div>
              <table width="100%" cellpadding="0" cellspacing="0" class="main_table">
                <tr class="main_table_header">
                  <td>岗位名称</td>
                  <td width="100px">排序</td>
                  <td width="100px">权限设置</td>
                  <td width="100px">操作</td>
                </tr>
                <%
                JSONArray list = (JSONArray)request.getAttribute("list");
                if(list.size() == 0){
                	out.println("<tr>");
                	out.println("<td colspan=4>尚未创建岗位</td>");
                	out.println("</tr>");
                }
                for(Integer i=0; i<list.size(); i=i+1){
                	JSONObject jsonObject = list.getJSONObject(i);
                	out.println("<tr>");
                	Integer level = jsonObject.getInteger("level");
                	String name = jsonObject.getString(ADMIN_GROUP.name);
                	name = " "+name;
                	for(Integer n=0; n<level; n=n+1){
                		name = "——"+name;
                	}
                	out.println("<td>"+name+"</td>");
                	out.println("<td>"+jsonObject.getString(ADMIN_GROUP.sort)+"</td>");
                	out.println("<td><a href=\"javascript:shop.sub_window('权限设置', '"+T.U("SystemSet/Role/index", "gid="+jsonObject.getString("id"), "admin.jsp")+"')\">权限设置</a></td>");
                	out.println("<td><a href=\"javascript:shop.sub_window('编辑', '"+T.U("SystemSet/AdminGroup/edit", "id="+jsonObject.getString("id"), "admin.jsp")+"')\">修改</a> <a href=\"javascript:shop.del('"+T.U("SystemSet/AdminGroup/del", "admin.jsp")+"','"+jsonObject.getString("id")+"')\">删除</a></td>");
                	out.println("</tr>");
                }
                %>
                </table>
            </div>
<%@include file="../../admin.Index/IndexController/bottom.jsp"%>