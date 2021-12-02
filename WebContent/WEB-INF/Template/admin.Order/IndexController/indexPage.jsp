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
            <div class="main_title">订单列表</div>
            <div class="clear_5px"></div>
              <table width="100%" cellpadding="0" cellspacing="0" class="main_table">
                <tr class="main_table_header">
                  <td>订单号</td>
                  <td>平台</td>
                  <td>金额</td>
                  <td>总佣金</td>
                  <td>利润</td>
                  <td>状态</td>
                </tr>
                <%
                JSONArray list = (JSONArray)request.getAttribute("list");
                if(list.size() == 0){
                	out.println("<tr>");
                	out.println("<td colspan=6>没有更多的订单了</td>");
                	out.println("</tr>");
                }
                for(Integer i=0; i<list.size(); i=i+1){
                	JSONObject jsonObject = list.getJSONObject(i);
                	out.println("<tr>");
                	out.println("<td>"+jsonObject.getString(UNION_ORDER.order_sn)+"</td>");
                	out.println("<td>"+jsonObject.getString("platform")+"</td>");
                	out.println("<td>￥"+jsonObject.getString(UNION_ORDER.order_money)+"</td>");
                	out.println("<td>￥"+jsonObject.getString(UNION_ORDER.commission)+"</td>");
                	out.println("<td>￥"+jsonObject.getString(UNION_ORDER.platform_money)+"</td>");
                	if(jsonObject.getString(UNION_ORDER.status).equals("-1")){
                		out.println("<td>已失效</td>");
                	}
                	if(jsonObject.getString(UNION_ORDER.status).equals("0")){
                		out.println("<td>即将收益</td>");
                	}
                	if(jsonObject.getString(UNION_ORDER.status).equals("1")){
                		out.println("<td>已收益</td>");
                	}
                	out.println("</tr>");
                	JSONArray goods_list = jsonObject.getJSONObject("extra").getJSONArray("goods_list");
                	out.println("<tr>");
                	out.println("<td colspan=6>");
                	for(Integer n=0; n<goods_list.size(); n=n+1){
                		out.println("<div style=\"padding-top:5px; padding-bottom:5px;\"><span style=\"float:left\"><img src=\""+goods_list.getJSONObject(n).getString(UNION_ORDER_GOODS.goods_pic)+"\" width=\"50px\" /></span><span style=\"line-height:50px; padding-left:10px; float:left\">"+goods_list.getJSONObject(n).getString(UNION_ORDER_GOODS.goods_name)+"&nbsp;&nbsp;&nbsp;&nbsp;数量："+goods_list.getJSONObject(n).getString(UNION_ORDER_GOODS.goods_number)+"</span> <div style=\"clear:both\"></div> </div>");
                	}
                	out.println("<div style=\"border-top:1px #DDDDDD dashed\"></div>");
                	if(jsonObject.getJSONObject("extra").getJSONObject("member_1") != null){
                		JSONObject member_1 = jsonObject.getJSONObject("extra").getJSONObject("member_1");
                		out.println("<div style=\"padding-top:5px; padding-bottom:5px\"><div style=\"float:left; overflow:hidden; border-radius:30px\"><img src=\""+member_1.getString(MEMBER.pic)+"\" width=\"30px\" /></div><span style=\"line-height:30px; padding-left:10px; float:left\">"+member_1.getString(MEMBER.username)+"&nbsp;&nbsp;&nbsp;&nbsp;金额：￥"+jsonObject.getString(UNION_ORDER.level_1_money)+"（一级分佣）</span> <div style=\"clear:both\"></div> </div>");
                	}
                	if(jsonObject.getJSONObject("extra").getJSONObject("member_2") != null){
                		JSONObject member_2 = jsonObject.getJSONObject("extra").getJSONObject("member_2");
                		out.println("<div style=\"padding-top:5px; padding-bottom:5px\"><div style=\"float:left; overflow:hidden; border-radius:30px\"><img src=\""+member_2.getString(MEMBER.pic)+"\" width=\"30px\" /></div><span style=\"line-height:30px; padding-left:10px; float:left\">"+member_2.getString(MEMBER.username)+"&nbsp;&nbsp;&nbsp;&nbsp;金额：￥"+jsonObject.getString(UNION_ORDER.level_2_money)+"（二级分佣）</span> <div style=\"clear:both\"></div> </div>");
                	}
                	if(jsonObject.getJSONObject("extra").getJSONObject("member_3") != null){
                		JSONObject member_3 = jsonObject.getJSONObject("extra").getJSONObject("member_3");
                		out.println("<div style=\"padding-top:5px; padding-bottom:5px\"><div style=\"float:left; overflow:hidden; border-radius:30px\"><img src=\""+member_3.getString(MEMBER.pic)+"\" width=\"30px\" /></div><span style=\"line-height:30px; padding-left:10px; float:left\">"+member_3.getString(MEMBER.username)+"&nbsp;&nbsp;&nbsp;&nbsp;金额：￥"+jsonObject.getString(UNION_ORDER.level_3_money)+"（三级分佣）</span> <div style=\"clear:both\"></div> </div>");
                	}
                	out.println("</td>");
                	out.println("</tr>");
                }
                %>
                </table>
                <div class="page"><%=request.getAttribute("page").toString() %></div>
            </div>
<%@include file="../../admin.Index/IndexController/bottom.jsp"%>