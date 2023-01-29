<%@page import="java.util.LinkedHashMap"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../../admin.home.Index/IndexController/sub_header.jsp"%>
<link href="<%=request.getAttribute("PUBLIC") %>/css/renovation.css" rel="stylesheet" type="text/css" />
<%
JSONObject info = (JSONObject)request.getAttribute("info");
%>
<style>
	td {
	    background: #FFF;
	    padding-top: 9px;
	    padding-bottom: 9px;
	    padding-left: 15px;
	    padding-right: 15px;
	}
	.widget_box{ width:90%; height:auto; margin:auto; border: #0D79B5 0.25rem dashed;}
	.widget_box:hover{ border: #0D79B5 0.25rem solid; cursor: pointer;}
	.widget_title{ width: auto; height: 1.875rem; margin: auto; line-height: 1.875rem; font-size: 0.875rem; text-align: center;}
</style>
	<%
	Map<String, String> map = (LinkedHashMap)request.getAttribute("map");
	String category = (String)request.getAttribute("category");
	%>
	
	<div style="padding-top:20px">
		<%
		for(String key:map.keySet()){
			String html = map.get(key);
			
		%>
			<div style="width:auto; height:auto; margin:auto; margin-bottom:30px" onclick="widget_selected('<%=category %>', '<%=key %>')">
				<div class="widget_box"><%=html %></div>
				<div class="widget_title"><%=key %></div>
			</div>
		<%
		}
		%>
	</div>

<script>	
	$(".widget_box").children().removeAttr('onclick');
	function widget_selected(widget_category, widget_name){
		parent.get_widget(widget_category, widget_name);
	}
</script>

<%@include file="../../admin.home.Index/IndexController/sub_bottom.jsp"%>