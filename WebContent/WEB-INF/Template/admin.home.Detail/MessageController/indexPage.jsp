<%@page import="common.database.MESSAGE"%>
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
String page_action = request.getAttribute("page_action").toString();
%>
<div style="padding:15px;">
            <div class="main_title"><%=page_action %>管理</div>
            <a onclick="malltodoJs.sub_window('新建<%=page_action %>', '<%=T.U(MODULE_NAME+"/"+CONTROLLER_NAME+"/add", "admin.jsp", request)%>')" class="main_button">新建</a>
            <div class="clear_5px"></div>
              <table width="100%" cellpadding="0" cellspacing="0" class="main_table">
                <tr class="main_table_header">
                  <td>姓名</td>
                  <td>手机号</td>
                  <td>邮箱</td>
                  <td>IP</td>
                  <td>提交时间</td>
                  <td width="50px">操作</td>
                </tr>
                <%
                JSONArray list = (JSONArray)request.getAttribute("list");
                                                if(list.size() == 0){
                                                	out.println("<tr>");
                                                	out.println("<td colspan=6>尚未有任何留言信息</td>");
                                                	out.println("</tr>");
                                                }
                                                for(Integer i=0; i<list.size(); i=i+1){
                                                	JSONObject jsonObject = list.getJSONObject(i);
                                                	out.println("<tr>");
                                                	out.println("<td>"+jsonObject.getString(MESSAGE.name)+"</td>");
                                                	out.println("<td>"+jsonObject.getString(MESSAGE.tel)+"</td>");
                                                	out.println("<td>"+jsonObject.getString(MESSAGE.email)+"</td>");
                                                	out.println("<td>"+jsonObject.getString(MESSAGE.ip)+"</td>");
                                                	out.println("<td>"+T.date("yyyy-MM-dd HH:mm:ss", Long.valueOf(jsonObject.getString(MESSAGE.addtime)))+"</td>");
                                                	out.println("<td><a href=\"javascript:malltodoJs.del('"+T.U(MODULE_NAME+"/"+CONTROLLER_NAME+"/del", "admin.jsp", request)+"','"+jsonObject.getString("id")+"')\">删除</a></td>");
                                                	out.println("</tr>");
                                                	out.println("<tr>");
                                                	out.println("<td colspan=6>留言内容："+jsonObject.getString(MESSAGE.message)+"</td>");
                                                	out.println("</tr>");
                                                }
                %>
                </table>
                <div class="page"><%=request.getAttribute("page").toString() %></div>
            </div>
<%@include file="../../admin.home.Index/IndexController/bottom.jsp"%>