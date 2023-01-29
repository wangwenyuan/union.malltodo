<%@page import="common.database.LINKS"%>
<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="common.database.DETAIL"%>
<%@page import="common.database.CATEGORY"%>
<%@page import="com.alibaba.fastjson.JSON"%>
<%@page import="common.database.ADMIN"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="common.Common"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../../admin.home.Index/IndexController/sub_header.jsp"%>

<style>
.ui-c-element{ float:left}
.ui-c-note{ height:30px; line-height:30px; font-size:12px; float:left; padding-left:10px;}
.renovation_type_0{ display:none}
.renovation_type_1{ display:none}
</style>
<div style="padding:15px;">
	<div class="main_title"><%=request.getAttribute("page_action").toString() %>内容</div>
	<div class="clear_5px"></div>
	<div>
<%
Map<String, Object> info = (HashMap)request.getAttribute("info");
%>

<form method="post" action="" id="html_form">
    <table width="100%" cellpadding="0" cellspacing="0" class="small_main_table">
        <tr><td align="right" width="150px">链接名称：</td><td align="left"><%=Common.template.htmlWidget("text_widget", LINKS.name, T.getString(LINKS.name, info))%>（必填）</td></tr>
        <tr><td align="right" width="150px">展示图片：</td><td align="left"><%=Common.template.htmlWidget("upload_widget", LINKS.pic, T.getString(LINKS.pic, info))%></td></tr>
        <tr><td align="right" width="150px">跳转链接：</td><td align="left"><%=Common.template.htmlWidget("text_widget", LINKS.url, T.getString(LINKS.url, info))%>（链接前面请添加“http://”或“https://”）</td></tr>
        <tr><td align="right" width="150px">推荐等级：</td><td align="left"><%=Common.template.htmlWidget("select_widget", LINKS.recommend_level, T.getString(LINKS.recommend_level, info)+"", Common.detail_recommend_level_json.toString())%></td></tr>
        <tr><td align="right" width="150px">排序：</td><td align="left"><%=Common.template.htmlWidget("text_widget", LINKS.sort, T.getString(LINKS.sort, info))%>（值越大越靠前）</td></tr>
        <tr><td></td><td><input type="button" class="anniu" id="add" value="提交" /></td></tr>
    </table>
    
    <script>
    function change_renovation_type(){
    	$(".renovation_type_0").hide();
    	$(".renovation_type_1").hide();
    	var zhi = $("#renovation_type").val();
    	if(zhi == 0){
    		$(".renovation_type_0").show();
    	}else{
    		$(".renovation_type_1").show();
    	}
    }
    change_renovation_type();
    $("#renovation_type").change(function(){
    	change_renovation_type();
    })
    </script>
    
</form>
	</div>
</div>

<%@include file="../../admin.home.Index/IndexController/sub_bottom.jsp"%>