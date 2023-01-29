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
	.widget_box{ width:306px; height:auto; margin:auto; border: #0D79B5 0.25rem dashed;}
	.widget_box:hover{ border: #0D79B5 0.25rem solid; cursor: pointer;}
	.widget_title{ width: 306px; height: 1.875rem; margin: auto; line-height: 1.875rem; font-size: 0.875rem; text-align: center;}
	.widget_div{ width: 49.9%; float: left;}
	#javatodo_container{ height:0px; overflow:hidden;}
</style>
	<%
	Map<String, String> map = (LinkedHashMap)request.getAttribute("map");
	String category = (String)request.getAttribute("category");
	%>
	<div id="javatodo_container">
		<%
		for(String key:map.keySet()){
			String html = map.get(key);
			
		%>
			<div style="width:306px; height:auto; margin:auto;" onclick="widget_selected('<%=category %>', '<%=key %>')">
				<div class="widget_box"><%=html %></div>
				<div class="widget_title"><%=key %></div>
			</div>
		<%
		}
		%>
	</div>
	
	<div style="padding-top:20px">
		<div class="widget_div"></div>
		<div class="widget_div"></div>
	</div>

<script>
	$(function(){
		$(".widget_box").children().removeAttr('onclick');
		var len = $("#javatodo_container").children().length;
		for(var i=0; i<len; i=i+1){
			if(i%2 == 0){
				$(".widget_div").eq(0).append($("#javatodo_container").children().eq(0))
			}else{
				$(".widget_div").eq(1).append($("#javatodo_container").children().eq(0))
			}
		}
	})
	
	function widget_selected(widget_category, widget_name){
		parent.get_widget(widget_category, widget_name);
	}

</script>

<%@include file="../../admin.home.Index/IndexController/sub_bottom.jsp"%>