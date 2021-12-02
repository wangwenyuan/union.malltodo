<%@page import="common.database.WITHDRAWALS"%>
<%@page import="common.database.UNION_ORDER_GOODS"%>
<%@page import="common.database.UNION_ORDER"%>
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
            <div class="main_title">提现申请列表</div>
            <div class="clear_5px"></div>
              <table width="100%" cellpadding="0" cellspacing="0" class="main_table">
                <tr class="main_table_header">
                  <td>用户</td>
                  <td>提现金额</td>
                  <td>申请时间</td>
                  <td>审核时间</td>
                  <td>审核状态</td>
                  <td>审核</td>
                </tr>
                <%
                JSONArray list = (JSONArray)request.getAttribute("list");
                if(list.size() == 0){
                	out.println("<tr>");
                	out.println("<td colspan=6>没有更多的提现申请了</td>");
                	out.println("</tr>");
                }
                for(Integer i=0; i<list.size(); i=i+1){
                	JSONObject jsonObject = list.getJSONObject(i);
                	out.println("<tr>");
                	out.println("<td>"+jsonObject.getString(MEMBER.username)+"</td>");
                	out.println("<td>"+jsonObject.getString(WITHDRAWALS.money)+"</td>");
                	out.println("<td>"+T.date("yyyy-MM-dd HH:mm:ss", jsonObject.getLongValue(WITHDRAWALS.addtime))+"</td>");
                	if(jsonObject.getLongValue(WITHDRAWALS.examine_time) == 0){
                		out.println("<td>未审核</td>");
                	}else{
                		out.println("<td>"+T.date("yyyy-MM-dd HH:mm:ss", jsonObject.getLongValue(WITHDRAWALS.examine_time))+"</td>");
                	}
                	if(jsonObject.getInteger(WITHDRAWALS.examine_status) == 0){
                		out.println("<td>未审核</td>");
                	}
                	if(jsonObject.getInteger(WITHDRAWALS.examine_status) == 1){
                		out.println("<td>审核通过</td>");
                	}
                	if(jsonObject.getInteger(WITHDRAWALS.examine_status) == -1){
                		out.println("<td>审核不通过</td>");
                	}
                	if(jsonObject.getInteger(WITHDRAWALS.examine_status) == 0){
                		out.println("<td><a href=\"javascript:shop.sub_window('审核', '"+T.U("Finance/Index/examine", "id="+jsonObject.getString("id"), "admin.jsp")+"')\">审核</a></td>");
                	}else{
                		out.println("<td>已审核</td>");
                	}
                	out.println("</tr>");
                	if(jsonObject.getInteger(WITHDRAWALS.examine_status) == -1){
                		out.println("<tr>");
                    	out.println("<td colspan=6><span style=\"color:red\">不通过原因："+jsonObject.getString(WITHDRAWALS.no_pass_reason)+"</span></td>");
                    	out.println("</tr>");
                	}
                }
                %>
                </table>
                <div class="page"><%=request.getAttribute("page").toString() %></div>
            </div>
<%@include file="../../admin.Index/IndexController/bottom.jsp"%>