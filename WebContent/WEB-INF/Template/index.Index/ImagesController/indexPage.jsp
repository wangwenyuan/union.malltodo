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

.image_box{ width:250px; float:left;}
.image{ width:230px; height:auto; margin-left:10px; position: relative; margin-bottom:10px; cursor:pointer;}
.image_mask{ width:100%; height:100%; position:absolute; background:black; opacity: 0.3; display:none; z-index:100;}
.free_span_div{ clear:both; font-size:14px; color:#333333;}
.free_span_div span{ float:left; margin-left:10px; margin-bottom:10px; cursor:pointer;}
.select_span{ color:red}
</style>
	<div style="padding:15px">
		<div style="padding:5px;">
			<%
			Map<String, String> urlMap = (HashMap)request.getAttribute("input");
			String fun_name = "";
			if(urlMap.containsKey("fun_name")){
				fun_name = urlMap.get("fun_name").toString().trim();
			}
			String id_val = "";
			if(urlMap.containsKey("id_val")){
				id_val = urlMap.get("id_val").toString().trim();
			}
			String ACTION_NAME = request.getAttribute("ACTION_NAME").toString();
			if(ACTION_NAME.equals("index")){
			%>
				<a class="selected_button" href="<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/Images/index", "fun_name="+fun_name+"&id_val="+id_val, "index.jsp", request) %>">我的图片</a>
			<%
			}else{
			%>
				<a class="main_button" href="<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/Images/index", "fun_name="+fun_name+"&id_val="+id_val, "index.jsp", request) %>">我的图片</a>
			<% 	
			}
			%>
			
			<%
			if(ACTION_NAME.equals("free")){
			%>
				<a class="selected_button" href="<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/Images/free", "fun_name="+fun_name+"&id_val="+id_val, "index.jsp", request) %>">图库图片</a>
			<%
			}else{
			%>
				<a class="main_button" href="<%=T.U(request.getAttribute("MODULE_NAME").toString()+"/Images/free", "fun_name="+fun_name+"&id_val="+id_val, "index.jsp", request) %>">图库图片</a>
			<% 	
			}
			%>
			
			<%
			if(ACTION_NAME.equals("free")){
			%>
				<div class="free_span_div"><span class="select_span">人物</span> <span>风景</span> <span>美图</span></div>
			<%
			}
			%>
			
			<div style="clear:both"></div>
		</div>
		<div id="image_content">
			<!--
			<div class="image_box">
				<div class="image" onmouseenter="$(this).children('.image_mask').show()" onmouseleave="$(this).children('.image_mask').hide()" onclick="images_selected('./Public/images/widget/002.jpg')">
					<div class="image_mask">选择该图片</div>
					<img src="./Public/images/widget/002.jpg" width="100%" />
				</div>
				
				<div class="image" onclick="images_selected('./Public/images/widget/002.jpg')">
					<div class="image_mask">选择该图片</div>
					<img src="./Public/images/widget/002.jpg" width="100%" />
				</div>
			</div>
			-->
			
			<div class="image_clear"></div>
		</div>
	</div>
<script>
	function images_selected(pic){
		var fun_name = "<%=urlMap.get("fun_name").toString().trim() %>";
		var id_val = "<%=urlMap.get("id_val").toString().trim() %>";
		if(fun_name == "background.images_selected"){
			parent.background.images_selected(id_val, pic);
		}
		if(fun_name == "src.images_selected"){
			parent.src.images_selected(id_val, pic);
		}
		if(fun_name == "malltodoJs.images_selected"){
			parent.malltodoJs.images_selected(id_val, pic);
		}
	}
	
	//瀑布流
	function add_image(show_pic, pic) {
		var len = $(".image_box").length;
		var min_height = $(".image_box").eq(0).height();
		var min_i = 0;
		for (var i = 1; i < len; i = i + 1) {
			console.log("i:"+i+"----height:"+$(".image_box").eq(i).height());
			if ($(".image_box").eq(i).height() < min_height) {
				min_height = $(".image_box").eq(i).height();
				min_i = i;
			}
			console.log("min_i:"+min_i+"----min_height:"+min_height);
		}
		var html = "<div class=\"image\" onmouseenter=\"$(this).children('.image_mask').show()\" onmouseleave=\"$(this).children('.image_mask').hide()\" onclick=\"images_selected('"+pic+"')\">";
			html = html +"<div class=\"image_mask\">选择该图片</div>"
			html = html +"<img src=\""+show_pic+"\" width=\"100%\" />";
			html = html +"</div>";
		$(".image_box").eq(min_i).append(html);
	}
	
	function water_fall_init(jquery_dom){
		var width = jquery_dom.width();
		var len = parseInt(width/250);
		jquery_dom.html("");
		for(var i=0; i<len; i=i+1){
			jquery_dom.append("<div class=\"image_box\"></div>");
		}
		jquery_dom.append("<div class=\"image_clear\"></div>");
		var image_box_width = parseInt(width/len);
		$(".image_box").css("width", parseInt(100/len)+"%");
		//$(".image").width(image_box_width-20);
	}
	
	water_fall_init($("#image_content"));
	
	drop.p = 0;
	<%
	if(ACTION_NAME.equals("index")){
	%>
	drop.scroll("<%=T.U("Index/Images/"+ACTION_NAME, "index.jsp", request) %>", function(data) {
		return data["list"].length;
	}, function(data) {
		var list = data["list"];
		for(var i=0; i<list.length; i=i+1){
			add_image(list[i]["show_pic"], list[i]["url"]);
		}
	}, "", "", "post", {}, "json");
	<%
	}
	%>
	
	<%
	if(ACTION_NAME.equals("free")){
	%>
	var json = null;
	var category_id = 0;
	var pic_i = 0;
	var pic_size = 24;
	var is_end = 0;
	http.post("<%=T.U("Index/Images/"+ACTION_NAME, "index.jsp", request) %>", {}, function(data){
		json = data;
		$(".free_span_div").html("");
		var html = "";
		for(var i=0; i<json.length; i=i+1){
			if(i == 0){
				html = "<span onclick=\"select_span_click("+i+")\" class=\"select_span\">"+json[i]["category"]+"</span>";
			}else{
				html = html + "<span onclick=\"select_span_click("+i+")\">"+json[i]["category"]+"</span>";
			}
		}
		$(".free_span_div").html(html);
		$(function(){
			load_img();
		})
	})
	
	$(window).on('scroll', function() {
		if ($(window).scrollTop() >= $(document).height() - $(window).height() - 5) {
			console.log(123);
			if(is_end == 1){
				layer.msg("已到最底部");
			}else{
				load_img();
			}
		}
	})
	
	function select_span_click(val){
		if(val == category_id){
			return ;
		}
		category_id = val;
		pic_i = 0;
		is_end = 0;
		$(".free_span_div").children("span").removeClass("select_span");
		$(".free_span_div").children("span").eq(category_id).addClass("select_span");
		$(".image_box").html("");
		load_img();
	}
	
	function min(a, b){
		if(a > b){
			return b;
		}else{
			return a;
		}
	}
	
	function load_img(){
		var pic_list = json[category_id]["pic"];
		var cur_i = pic_i;
		for(; pic_i < min(pic_size + cur_i, pic_list.length); pic_i = pic_i + 1){
			add_image(pic_list[pic_i], pic_list[pic_i]);
		}
		if(pic_i < pic_list.length){
			return ;
		}else{
			is_end = 1;
		}
	}

	<%
	}
	%>
</script>

<%@include file="../../admin.home.Index/IndexController/sub_bottom.jsp"%>