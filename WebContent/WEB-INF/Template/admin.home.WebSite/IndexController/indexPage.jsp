<%@page import="common.database.WEBSITE"%>
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
            <div class="main_title">站点管理</div>
            <a onclick="malltodoJs.sub_window('新建站点', '<%=T.U(MODULE_NAME+"/"+CONTROLLER_NAME+"/add", "admin.jsp", request)%>')" class="main_button">新建</a>
            <div class="clear_5px"></div>
              <table width="100%" cellpadding="0" cellspacing="0" class="main_table">
                <tr class="main_table_header">
                  <td>站点名称</td>
                  <td>站点域名</td>
                  <td width="100px">操作</td>
                </tr>
                <%
                JSONArray list = (JSONArray)request.getAttribute("list");
                                                if(list.size() == 0){
                                                	out.println("<tr>");
                                                	out.println("<td colspan=3>尚未创建任何信息</td>");
                                                	out.println("</tr>");
                                                }
                                                for(Integer i=0; i<list.size(); i=i+1){
                                                	JSONObject jsonObject = list.getJSONObject(i);
                                                	out.println("<tr>");
                                                	out.println("<td>"+jsonObject.getString(WEBSITE.website_name)+"</td>");
                                                	out.println("<td>"+jsonObject.getString(WEBSITE.website_host)+"</td>");
                                                	out.println("<td><a href=\"javascript:malltodoJs.sub_window('编辑', '"+T.U(MODULE_NAME+"/"+CONTROLLER_NAME+"/edit", "id="+jsonObject.getString("id"), "admin.jsp", request)+"')\">修改</a> <a href=\"javascript:malltodoJs.del('"+T.U(MODULE_NAME+"/"+CONTROLLER_NAME+"/del", "admin.jsp", request)+"','"+jsonObject.getString("id")+"')\">删除</a></td>");
                                                	out.println("</tr>");
                                                }
                %>
                </table>
                <div class="page"><%=request.getAttribute("page").toString() %></div>
            </div>
<%@include file="../../admin.home.Index/IndexController/bottom.jsp"%>