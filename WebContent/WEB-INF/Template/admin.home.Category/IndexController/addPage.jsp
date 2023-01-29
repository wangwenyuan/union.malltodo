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
</style>
<div style="padding:15px;">
	<div class="main_title">栏目设置</div>
	<div class="clear_5px"></div>
	<div>
<%
Map<String, Object> info = (HashMap)request.getAttribute("info");
JSONObject json = new JSONObject(new LinkedHashMap<String, Object>());
json.put("0", "普通类型（即列表页+详情页）");
json.put("1", "单页面");
json.put("2", "自定义页面");

JSONObject order_by_json = new JSONObject(new LinkedHashMap<String, Object>());
order_by_json.put("id desc", "添加的先后次序，倒序");
order_by_json.put("id asc", "添加的先后次序，正序");
order_by_json.put("release_time desc", "发布时间的先后次序，倒序");
order_by_json.put("release_time asc", "发布时间的先后次序，正序");

JSONObject _menu_json = (JSONObject)request.getAttribute("_menu_json");
JSONObject menu_json = (JSONObject)request.getAttribute("menu_json");
%>

<form method="post" action="" id="html_form">
    <table width="100%" cellpadding="0" cellspacing="0" class="small_main_table">
        <tr><td align="right" width="150px">SEO标题：</td><td align="left"><%=Common.template.htmlWidget("text_widget", CATEGORY.seo_title, T.getString(CATEGORY.seo_title, info)) %></td></tr>
        <tr><td align="right" width="150px">SEO关键字：</td><td align="left"><%=Common.template.htmlWidget("text_widget", CATEGORY.seo_keywords, T.getString(CATEGORY.seo_keywords, info)) %></td></tr>
        <tr><td align="right" width="150px">SEO描述：</td><td align="left"><%=Common.template.htmlWidget("textarea_widget", CATEGORY.seo_description, T.getString(CATEGORY.seo_description, info)) %></td></tr>
        <tr><td align="right" width="150px">栏目名称：</td><td align="left"><%=Common.template.htmlWidget("text_widget", CATEGORY.category_name, T.getString(CATEGORY.category_name, info)) %></td></tr>
        <tr><td align="right" width="150px">栏目别名：</td><td align="left"><%=Common.template.htmlWidget("text_widget", CATEGORY.category_sub_name, T.getString(CATEGORY.category_sub_name, info)) %></td></tr>
        <tr><td align="right" width="150px">所属模型：</td><td align="left"><%=Common.template.htmlWidget("select_widget", CATEGORY.type, T.getString(CATEGORY.type, info)+"", JSON.toJSONString(Common.home_menu)) %></td></tr>
        <tr class="type_no_index"><td align="right" width="150px">上级栏目：</td><td align="left"><%=Common.template.htmlWidget("select_widget", CATEGORY.pid, T.getString(CATEGORY.pid, info)+"", _menu_json.toJSONString()) %></td></tr>
        <tr><td align="right" width="150px">栏目图片：</td><td align="left"><%=Common.template.htmlWidget("upload_widget", CATEGORY.pic, T.getString(CATEGORY.pic, info)) %></td></tr>
        <tr><td align="right" width="150px">排序：</td><td align="left"><%=Common.template.htmlWidget("text_widget", CATEGORY.sort, T.getString(CATEGORY.sort, info)) %>（值越小栏目越靠前）</td></tr>
        <tr><td align="right" width="150px">外部跳转链接：</td><td align="left"><%=Common.template.htmlWidget("text_widget", CATEGORY.url, T.getString(CATEGORY.url, info)) %>（如果该链接不为空，则进入该栏目时自动跳转至该链接上。链接前面请添加“http://”或“https://”）</td></tr>
        <tr class="type_no_index"><td align="right" width="150px">栏目类型：</td><td align="left"><%=Common.template.htmlWidget("select_widget", CATEGORY.category_type, T.getString(CATEGORY.category_type, info)+"", json.toJSONString()) %></td></tr>
        <tr class="type_no_index category_type_0 category_type_1"><td align="right" width="150px">电脑端栏目页模版：</td><td align="left"><%=Common.template.htmlWidget("select_widget", CATEGORY.pc_list_renovation_id, T.getString(CATEGORY.pc_list_renovation_id, info)+"", "{}") %></td></tr>
        <tr class="type_no_index category_type_0"><td align="right" width="150px">电脑端详情页模版：</td><td align="left"><%=Common.template.htmlWidget("select_widget", CATEGORY.pc_page_renovation_id, T.getString(CATEGORY.pc_page_renovation_id, info)+"", "{}") %></td></tr>
        <%
        if(Common.hasMobile){
        %>
        	<tr class="type_no_index category_type_0 category_type_1"><td align="right" width="150px">手机端栏目页模版：</td><td align="left"><%=Common.template.htmlWidget("select_widget", CATEGORY.mobile_list_renovation_id, T.getString(CATEGORY.mobile_list_renovation_id, info)+"", "{}") %></td></tr>
        	<tr class="type_no_index category_type_0"><td align="right" width="150px">手机端详情页模版：</td><td align="left"><%=Common.template.htmlWidget("select_widget", CATEGORY.mobile_page_renovation_id, T.getString(CATEGORY.mobile_page_renovation_id, info)+"", "{}") %></td></tr>
        <%
        }
        %>
        <tr class="type_no_index category_type_0"><td align="right" width="150px">排序方式：</td><td align="left"><%=Common.template.htmlWidget("select_widget", CATEGORY.order_by, T.getString(CATEGORY.order_by, info)+"", order_by_json.toJSONString()) %></td></tr>
        <tr class="type_no_index category_type_1"><td align="right" width="150px">栏目简介：</td><td align="left"><%=Common.template.htmlWidget("textarea_widget", CATEGORY.smalltext, T.getString(CATEGORY.smalltext, info)) %></td></tr>
        <tr class="type_no_index category_type_1"><td align="right" width="150px">栏目详情：</td><td align="left"><div style="max-width:800px"><%=Common.template.htmlWidget("editor_widget", CATEGORY.detail, T.htmlspecialchars_decode(T.getString(CATEGORY.detail, info))) %></div></td></tr>
        <tr class="type_no_index category_type_2"><td align="right" width="150px">电脑端自定义页模版：</td><td align="left"><%=Common.template.htmlWidget("select_widget", CATEGORY.pc_custom_id, T.getString(CATEGORY.pc_custom_id, info)+"", "{}") %></td></tr>
        <%
        if(Common.hasMobile){
        %>
        <tr class="type_no_index category_type_2"><td align="right" width="150px">手机端自定义页模版：</td><td align="left"><%=Common.template.htmlWidget("select_widget", CATEGORY.mobile_custom_id, T.getString(CATEGORY.mobile_custom_id, info)+"", "{}") %></td></tr>
        <%
        }
        %>
        <tr><td></td><td><input type="button" class="anniu" id="add" value="提交" /></td></tr>
    </table>
    
    <script>
    function category_type_fun(){
    	$(".category_type_0").hide();
    	$(".category_type_1").hide();
    	$(".category_type_2").hide();
    	var zhi = $("#category_type").val();
    	$(".category_type_"+zhi).show();
    }
    category_type_fun();
    $("#category_type").change(function(){
    	category_type_fun();
    })
    
    var pc_renovation = <%=request.getAttribute("pc_renovation").toString() %>;
    var mobile_renovation = <%=request.getAttribute("mobile_renovation").toString() %>;
    var menu_json = <%=request.getAttribute("menu_json").toString() %>;
    
    function renovation_change(){
    	
    	var zhi = $("#type").val();
    	if(zhi == "Index/Index/index"){
    		$(".type_no_index").hide();
    	}else{
    		$(".type_no_index").show();
    		category_type_fun();
    	}
    	
    	$("#pc_list_renovation_id").html("");
    	$("#pc_page_renovation_id").html("");
    	$("#mobile_list_renovation_id").html("");
    	$("#mobile_page_renovation_id").html("");
    	var zhi_arr = zhi.split("/");
    	var list_sign = zhi_arr[0]+"/"+zhi_arr[1]+"/index";
    	var detail_sign = zhi_arr[0]+"/"+zhi_arr[1]+"/detail";
    	
    	var pc_list_html = "<option value=''>请选择模板</option>";
    	var mobile_list_html = "<option value=''>请选择模板</option>";
    	var pc_detail_html = "<option value=''>请选择模板</option>";
    	var mobile_detail_html = "<option value=''>请选择模板</option>";
    	
    	for(var id in pc_renovation){
    		if(pc_renovation[id]["type"] == list_sign){
    			pc_list_html = pc_list_html + "<option value='"+id+"'>"+pc_renovation[id]["name"]+"</option>";
    		}
    		if(pc_renovation[id]["type"] == detail_sign){
    			pc_detail_html = pc_detail_html + "<option value='"+id+"'>"+pc_renovation[id]["name"]+"</option>";
    		}
    	}
    	
    	for(var id in mobile_renovation){
    		if(mobile_renovation[id]["type"] == list_sign){
    			mobile_list_html = mobile_list_html + "<option value='"+id+"'>"+mobile_renovation[id]["name"]+"</option>";
    		}
    		if(mobile_renovation[id]["type"] == detail_sign){
    			mobile_detail_html = mobile_detail_html + "<option value='"+id+"'>"+mobile_renovation[id]["name"]+"</option>";
    		}
    	}
    	$("#pc_list_renovation_id").html(pc_list_html);
    	$("#pc_page_renovation_id").html(pc_detail_html);
    	$("#mobile_list_renovation_id").html(mobile_list_html);
    	$("#mobile_page_renovation_id").html(mobile_detail_html);
    	
    	var pc_list_renovation_id = "<%=T.getString(CATEGORY.pc_list_renovation_id, info) %>";
    	if(pc_list_renovation_id != ""){
    		$("#pc_list_renovation_id").val(pc_list_renovation_id);
    	}
    	
    	var pc_page_renovation_id = "<%=T.getString(CATEGORY.pc_page_renovation_id, info) %>";
    	if(pc_page_renovation_id != ""){
    		$("#pc_page_renovation_id").val(pc_page_renovation_id);
    	}
    	
    	var mobile_list_renovation_id = "<%=T.getString(CATEGORY.mobile_list_renovation_id, info) %>";
    	if(mobile_list_renovation_id != ""){
    		$("#mobile_list_renovation_id").val(mobile_list_renovation_id);
    	}
    	
    	var mobile_page_renovation_id = "<%=T.getString(CATEGORY.mobile_page_renovation_id, info) %>";
    	if(mobile_page_renovation_id != ""){
    		$("#mobile_page_renovation_id").val(mobile_page_renovation_id);
    	}
    	
    	//修改栏目上级可选内容
    	var pid_option_html = '<option value="0">顶级栏目</option>';
    	var pid_arr = [0];
    	for(var id in menu_json){
    		if(menu_json[id]["type"] == $("#type").val()){
    			pid_option_html = pid_option_html + '<option value="'+id+'">'+menu_json[id]["name"]+'</option>';
    			pid_arr.push(id);
    		}
    	}
    	$('#pid').html(pid_option_html);

    	if(pid_arr.includes('<%=T.getString(CATEGORY.pid, info) %>')){
    		$('#pid').val("<%=T.getString(CATEGORY.pid, info) %>");
    	}else{
    		$('#pid').val("0");
    	}
    	ui_c.render();
    }
    
    renovation_change();
    
    $("#type").change(function(){
    	renovation_change();
    })
    
    function custom_init(){
    	var sign = "Index/Index/custom";
    	var pc_custom_html = "<option value=''>请选择模板</option>";
    	var mobile_custom_html = "<option value=''>请选择模板</option>";
    	for(var id in pc_renovation){
    		if(pc_renovation[id]["type"] == sign){
    			pc_custom_html = pc_custom_html + "<option value='"+id+"'>"+pc_renovation[id]["name"]+"</option>";
    		}
    	}
    	
    	for(var id in mobile_renovation){
    		if(mobile_renovation[id]["type"] == sign){
    			mobile_custom_html = mobile_custom_html + "<option value='"+id+"'>"+mobile_renovation[id]["name"]+"</option>";
    		}
    	}

    	$("#pc_custom_id").html("");
    	$("#mobile_custom_id").html("");
    	$("#pc_custom_id").html(pc_custom_html);
    	$("#mobile_custom_id").html(mobile_custom_html);
    	
    	var pc_custom_id = "<%=T.getString(CATEGORY.pc_custom_id, info) %>";
    	if(pc_custom_id != ""){
    		$("#pc_custom_id").val(pc_custom_id);
    	}
    	
    	var mobile_custom_id = "<%=T.getString(CATEGORY.mobile_custom_id, info) %>";
    	if(mobile_custom_id != ""){
    		$("#mobile_custom_id").val(mobile_custom_id);
    	}
    	
    	ui_c.render();
    }
    custom_init();
    </script>
    
</form>
	</div>
</div>

<%@include file="../../admin.home.Index/IndexController/sub_bottom.jsp"%>