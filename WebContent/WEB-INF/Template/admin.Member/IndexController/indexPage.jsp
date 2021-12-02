<%@page import="common.database.MEMBER"%>
<%@page import="common.database.ADMIN"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../../admin.Index/IndexController/header.jsp"%>
<div style="padding:15px;">
            <div class="main_title">会员管理</div>
            <div class="clear_5px"></div>
              <table width="100%" cellpadding="0" cellspacing="0" class="main_table">
                <tr class="main_table_header">
                  <td>头像</td>
                  <td>用户名</td>
                  <td>联系方式</td>
                  <td>余额</td>
                  <td>注册时间</td>
                  <td>修改余额</td>
                  <td width="100px">操作</td>
                </tr>
                <%
                JSONArray list = (JSONArray)request.getAttribute("list");
                if(list.size() == 0){
                	out.println("<tr>");
                	out.println("<td colspan=7>没有更多的会员了</td>");
                	out.println("</tr>");
                }
                for(Integer i=0; i<list.size(); i=i+1){
                	JSONObject jsonObject = list.getJSONObject(i);
                	out.println("<tr>");
                	out.println("<td><img src=\""+jsonObject.getString(MEMBER.pic)+"\" width=\"40px\" /></td>");
                	out.println("<td>"+jsonObject.getString(MEMBER.username)+"</td>");
                	out.println("<td>"+jsonObject.getString(MEMBER.mobile)+"</td>");
                	out.println("<td>"+jsonObject.getString(MEMBER.money)+"</td>");
                	out.println("<td>"+T.date("yyyy-MM-dd HH:mm:ss", jsonObject.getLong(MEMBER.createtime))+"</td>");
                	out.println("<td><a href=\"javascript:shop.sub_window('调整余额', '"+T.U("Member/Index/balance", "id="+jsonObject.getString("id"), "admin.jsp")+"')\">调整余额</a></td>");
                	out.println("<td><a href=\"javascript:shop.del('"+T.U("Member/Index/del", "admin.jsp")+"','"+jsonObject.getString("id")+"')\">删除</a></td>");
                	out.println("</tr>");
                }
                %>
                </table>
                <div class="page"><%=request.getAttribute("page").toString() %></div>
            </div>
<%@include file="../../admin.Index/IndexController/bottom.jsp"%>