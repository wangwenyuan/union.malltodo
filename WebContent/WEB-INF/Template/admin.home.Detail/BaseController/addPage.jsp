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
JSONObject category_map = (JSONObject)request.getAttribute("category_map");
%>

<form method="post" action="" id="html_form">
    <table width="100%" cellpadding="0" cellspacing="0" class="small_main_table">
        <tr><td align="right" width="150px">SEO标题：</td><td align="left"><%=Common.template.htmlWidget("text_widget", DETAIL.seo_title, T.getString(DETAIL.seo_title, info))%></td></tr>
        <tr><td align="right" width="150px">SEO关键字：</td><td align="left"><%=Common.template.htmlWidget("text_widget", DETAIL.seo_keywords, T.getString(DETAIL.seo_keywords, info))%></td></tr>
        <tr><td align="right" width="150px">SEO描述：</td><td align="left"><%=Common.template.htmlWidget("textarea_widget", DETAIL.seo_description, T.getString(DETAIL.seo_description, info))%></td></tr>
        <tr><td align="right" width="150px">内容标题：</td><td align="left"><%=Common.template.htmlWidget("text_widget", DETAIL.title, T.getString(DETAIL.title, info))%>（必填）</td></tr>
        <tr><td align="right" width="150px">展示图片：</td><td align="left"><%=Common.template.htmlWidget("upload_widget", DETAIL.pic, T.getString(DETAIL.pic, info))%></td></tr>
        <tr><td align="right" width="150px">外部跳转链接：</td><td align="left"><%=Common.template.htmlWidget("text_widget", DETAIL.url, T.getString(DETAIL.url, info))%>（如果该链接不为空，则进入该栏目时自动跳转至该链接上。链接前面请添加“http://”或“https://”）</td></tr>
        <tr><td align="right" width="150px">所属栏目：</td><td align="left"><%=Common.template.htmlWidget("select_widget", DETAIL.category_id, T.getString(DETAIL.category_id, info)+"", category_map.toJSONString())%>（必选）</td></tr>
        <tr><td align="right" width="150px">内容简介：</td><td align="left"><%=Common.template.htmlWidget("textarea_widget", DETAIL.smalltext, T.getString(DETAIL.smalltext, info))%></td></tr>
        <tr><td align="right" width="150px">内容详情：</td><td align="left"><div style="width:600px"><%=Common.template.htmlWidget("editor_widget", DETAIL.detail, T.htmlspecialchars_decode(T.getString(DETAIL.detail, info)))%></div></td></tr>
        <tr><td align="right" width="150px">模板类型：</td><td align="left"><%=Common.template.htmlWidget("select_widget", DETAIL.renovation_type, T.getString(DETAIL.renovation_type, info)+"", "{\"0\":\"普通模板\", \"1\":\"自定义模板\"}")%></td></tr>
        <tr class="renovation_type_0"><td align="right" width="150px">电脑端模版：</td><td align="left"><%=Common.template.htmlWidget("select_widget", DETAIL.pc_renovation_id, T.getString(DETAIL.pc_renovation_id, info)+"", request.getAttribute("pc_renovation").toString())%></td></tr>
        <%
        if(Common.hasMobile){
        %>
        <tr class="renovation_type_0"><td align="right" width="150px">手机端模版：</td><td align="left"><%=Common.template.htmlWidget("select_widget", DETAIL.mobile_renovation_id, T.getString(DETAIL.mobile_renovation_id, info)+"", request.getAttribute("mobile_renovation").toString())%></td></tr>
        <%
        }
        %>
        <tr class="renovation_type_1"><td align="right" width="150px">电脑端自定义页模版：</td><td align="left"><%=Common.template.htmlWidget("select_widget", DETAIL.pc_custom_id, T.getString(DETAIL.pc_custom_id, info)+"", request.getAttribute("pc_custom").toString())%></td></tr>
        <%
        if(Common.hasMobile){
        %>
        <tr class="renovation_type_1"><td align="right" width="150px">手机端自定义页模版：</td><td align="left"><%=Common.template.htmlWidget("select_widget", DETAIL.mobile_custom_id, T.getString(DETAIL.mobile_custom_id, info)+"", request.getAttribute("mobile_custom").toString())%></td></tr>
        <%
        }
        %>
        <tr><td align="right" width="150px">推荐等级：</td><td align="left"><%=Common.template.htmlWidget("select_widget", DETAIL.recommend_level, T.getString(DETAIL.recommend_level, info)+"", Common.detail_recommend_level_json.toString())%></td></tr>
        <tr><td align="right" width="150px">排序：</td><td align="left"><%=Common.template.htmlWidget("text_widget", DETAIL.sort, T.getString(DETAIL.sort, info))%>（值越大越靠前）</td></tr>
        <tr><td align="right" width="150px">发布时间：</td><td align="left"><%=Common.template.htmlWidget("date_widget", DETAIL.release_time, T.getString(DETAIL.release_time, info))%></td></tr>
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