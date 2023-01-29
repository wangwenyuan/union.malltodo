<%@page import="common.database.ROLE"%>
<%@page import="common.database.LINKS"%>
<%@page import="common.database.CATEGORY"%>
<%@page import="common.database.DETAIL"%>
<%@page import="common.database.ADMIN"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../../admin.home.Index/IndexController/header.jsp"%>
<%
String MODULE_NAME = request.getAttribute("MODULE_NAME").toString();
String CONTROLLER_NAME = request.getAttribute("CONTROLLER_NAME").toString();
%>
<div style="padding:15px;">
            <div class="main_title">权限分配</div>
            <a onclick="malltodoJs.sub_window('新建', '<%=T.U(MODULE_NAME+"/"+CONTROLLER_NAME+"/add", "admin.system.jsp", request)%>')" class="main_button">新建</a>
            <div class="clear_5px"></div>
              <table width="100%" cellpadding="0" cellspacing="0" class="main_table">
                <tr class="main_table_header">
                  <td>名称</td>
                  <td width="100px">权限设置</td>
                  <td width="100px">操作</td>
                </tr>
                <%
                JSONArray list = (JSONArray)request.getAttribute("list");
                                                if(list.size() == 0){
                                                	out.println("<tr>");
                                                	out.println("<td colspan=3>尚未创建任何内容</td>");
                                                	out.println("</tr>");
                                                }
                                                for(Integer i=0; i<list.size(); i=i+1){
                                                	JSONObject jsonObject = list.getJSONObject(i);
                                                	out.println("<tr>");
                                                	out.println("<td>"+jsonObject.getString(ROLE.role_name)+"</td>");
                                                	out.println("<td><a href=\"javascript:malltodoJs.sub_window('权限设置', '"+T.U(MODULE_NAME+"/Auth/index", "rid="+jsonObject.getString("id"), "admin.system.jsp", request)+"')\">权限设置</a></td>");
                                                	out.println("<td><a href=\"javascript:malltodoJs.sub_window('编辑', '"+T.U(MODULE_NAME+"/"+CONTROLLER_NAME+"/edit", "id="+jsonObject.getString("id"), "admin.system.jsp", request)+"')\">修改</a> <a href=\"javascript:malltodoJs.del('"+T.U(MODULE_NAME+"/"+CONTROLLER_NAME+"/del", "admin.system.jsp", request)+"','"+jsonObject.getString("id")+"')\">删除</a></td>");
                                                	out.println("</tr>");
                                                }
                %>
                </table>
                <div class="page"><%=request.getAttribute("page").toString() %></div>
            </div>
<%@include file="../../admin.home.Index/IndexController/bottom.jsp"%>