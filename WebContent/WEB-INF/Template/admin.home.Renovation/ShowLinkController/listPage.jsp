<%@page import="java.util.LinkedHashMap"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../../admin.home.Index/IndexController/sub_header.jsp"%>
<link href="<%=request.getAttribute("PUBLIC") %>/css/renovation.css" rel="stylesheet" type="text/css" />
<style>
.selected_button{
	height: 25px;
	line-height: 25px;
	display: block;
	text-align: center;
	color: #FFF;
	background: red;
	border: red 2px solid;
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	margin-bottom: 10px;
	padding-left: 10px;
	padding-right: 10px;
	font-size: 14px;
	text-decoration: none;
	cursor: pointer;
}
.main_button{
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	margin-bottom: 10px;
}
</style>
	<div style="padding:15px">
		<div>
			<%
			Map<String, String> urlMap = (HashMap)request.getAttribute("input");
			String href_dom_id = urlMap.get("href_dom_id").toString();
			String ACTION_NAME = request.getAttribute("ACTION_NAME").toString();
			
			if(ACTION_NAME.equals("index")){
			%>
				<a class="selected_button" href="<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowLink/index", "href_dom_id="+href_dom_id, "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>">官网页面</a>
			<%
			}else{
			%>
				<a class="main_button" href="<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowLink/index", "href_dom_id="+href_dom_id, "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>">官网页面</a>
			<% 	
			}
			%>
			
			<%
			if(ACTION_NAME.equals("union")){
			%>
				<a class="selected_button" href="<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowLink/union", "href_dom_id="+href_dom_id, "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>">电商联盟</a>
			<%
			}else{
			%>
				<a class="main_button" href="<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowLink/union", "href_dom_id="+href_dom_id, "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>">电商联盟</a>
			<% 	
			}
			%>
			
			<%
			if(ACTION_NAME.equals("meituan")){
			%>
				<a class="selected_button" href="<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowLink/meituan", "href_dom_id="+href_dom_id, "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>">美团</a>
			<%
			}else{
			%>
				<a class="main_button" href="<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowLink/meituan", "href_dom_id="+href_dom_id, "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>">美团</a>
			<% 	
			}
			%>
			
			<%
			if(ACTION_NAME.equals("custom")){
			%>
				<a class="selected_button" href="<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowLink/custom", "href_dom_id="+href_dom_id, "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>">官网自定义页面</a>
			<%
			}else{
			%>
				<a class="main_button" href="<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowLink/custom", "href_dom_id="+href_dom_id, "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>">官网自定义页面</a>
			<% 	
			}
			%>
			
			<%
			if(ACTION_NAME.equals("unioncustom")){
			%>
				<a class="selected_button" href="<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowLink/unioncustom", "href_dom_id="+href_dom_id, "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>">联盟自定义页面</a>
			<%
			}else{
			%>
				<a class="main_button" href="<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/ShowLink/unioncustom", "href_dom_id="+href_dom_id, "admin."+Functions.getMenuCategoryName(request)+".jsp", request) %>">联盟自定义页面</a>
			<% 	
			}
			%>
			
			<div style="clear:both"></div>
		</div>
		<div>
		
			<%
			if(ACTION_NAME.equals("index")){
			%>
				<%@include file="./index.jsp"%>
			<%
			}
			%>
			
			<%
			if(ACTION_NAME.equals("union")){
			%>
				<%@include file="./union.jsp"%>
			<%
			}
			%>
			
			<%
			if(ACTION_NAME.equals("meituan")){
			%>
				<%@include file="./meituan.jsp"%>
			<%
			}
			%>
			
			<%
			if(ACTION_NAME.equals("custom")){
			%>
				<%@include file="./custom.jsp"%>
			<%
			}
			%>
			
			<%
			if(ACTION_NAME.equals("unioncustom")){
			%>
				<%@include file="./unioncustom.jsp"%>
			<%
			}
			%>

		</div>
	</div>
<script>
	function link_selected(url){
		parent.get_link("<%=request.getAttribute("href_dom_id").toString() %>", url);
	}
</script>

<%@include file="../../admin.home.Index/IndexController/sub_bottom.jsp"%>