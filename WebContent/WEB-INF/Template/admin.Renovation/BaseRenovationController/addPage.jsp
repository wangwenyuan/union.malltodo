<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../../admin.Index/IndexController/sub_header.jsp"%>
<link href="<%=request.getAttribute("PUBLIC") %>/admin/css/renovation.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/admin/js/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("PUBLIC") %>/admin/css/jquery-ui.css" />

<script>
	function color_to_hex(color) {
		if(color.indexOf("#") == 0 ){
			return color;
		}else{
			color = color.replace(/rgba/, "");
			color = color.replace(/rgb/, "");
			color = color.replace(/\(/, "");
			color = color.replace(/\)/, "");
			var color_arr = color.split(",");
			color = "rgb("+color_arr[0] +","+ color_arr[1] +","+ color_arr[2]+")";
			var reg = /^(rgb|RGB)/;
			if (reg.test(color)) {
				var strHex = "#";
				var colorArr = color.replace(/(?:\(|\)|rgb|RGB)*/g, "").split(",");
				for (var i = 0; i < colorArr.length; i++) {
					var hex = Number(colorArr[i]).toString(16);
					if (hex.length < 2) {
						//hex = "0"+""+hex;
						strHex = strHex + "0"+""+hex;
					}else{
						strHex = strHex + hex;
					}
				}
				return strHex;
			} else {
				return String(color);
			}
		}
	};
</script>

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
	.malltodo{ cursor: pointer;}
	.malltodo-drag{ cursor: move; border: #0099CC 2px dashed;}
	.ui-c-box { padding: 0.3125rem; clear: both; background-color: #FFFFFF; }
	.ui-c-box:hover { background-color: #EFEFEF; }
	.ui-c-box-left { width: 10.625rem; font-size: 0.875rem; color: #333333; line-height: 1.875rem; float: left; }
	.ui-c-box-right { width: 21.875rem; font-size: 0.875rem; color: #333333; float: left; }
	.ui-c-slider { margin-top: 0.5rem; }
	.ui-c-upload { background: center center no-repeat url(./Public/images/pic_beijing.jpg); background-size: cover; width: 100px; height: 100px; }
	.main_del_button{ height: 25px; line-height: 25px; display: block; text-align: center; color: #FFF; background: #FF0000; border: #FF0000 2px solid; float: left; padding-left: 10px; padding-right: 10px; font-size: 14px; text-decoration: none; cursor: pointer; border-radius: 2px;}
	.ui-c-split{ height:250px;}
	.ui-c-loop-split{ font-size:0.9rem; margin-top:3rem; color:#1289a3; font-weight:bold;}
</style>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td width="200px">
				<div id="renovation_menu_content">
					<div id="renovation_menu_box">
						<div class="menu_box" onclick="malltodo_page_config_init()">页面设置</div>
						<%
						String CONTROLLER_NAME = request.getAttribute("CONTROLLER_NAME").toString();
						if(CONTROLLER_NAME.equals("Index")){
						%>
							<%@include file="./index_nav.jsp"%>
						<%
						}else if(CONTROLLER_NAME.equals("Custom")){
						%>
							<%@include file="./index_nav.jsp"%>
						<%
						}else if(CONTROLLER_NAME.equals("Bottom")){
						%>
							<%@include file="./bottom_nav.jsp"%>
						<%
						}else if(CONTROLLER_NAME.equals("Menu")){
						%>
							<%@include file="./menu_nav.jsp"%>					
						<%
						}else if(CONTROLLER_NAME.equals("GoodsCategory")){
						%>
							<%@include file="./goods_category_nav.jsp"%>					
						<%
						}else if(CONTROLLER_NAME.equals("GoodsDetail")){
						%>
							<%@include file="./goods_detail_nav.jsp"%>					
						<%
						}else if(CONTROLLER_NAME.equals("Search")){
						%>
							<%@include file="./search_nav.jsp"%>					
						<%
						}else if(CONTROLLER_NAME.equals("User")){
						%>
							<%@include file="./user_nav.jsp"%>					
						<%
						}else if(CONTROLLER_NAME.equals("Team")){
						%>
							<%@include file="./team_nav.jsp"%>					
						<%
						}else if(CONTROLLER_NAME.equals("MoneyLog")){
						%>
							<%@include file="./money_log_nav.jsp"%>					
						<%
						}else if(CONTROLLER_NAME.equals("Order")){
						%>
							<%@include file="./order_nav.jsp"%>					
						<%
						}else if(CONTROLLER_NAME.equals("Income")){
						%>
							<%@include file="./income_nav.jsp"%>					
						<%
						}
						%>
					</div>
				</div>
			</td>
			<td valign="top" width="400px">
				<div class="ui-c-renovation-phone">
					<div class="ui-c-renovation-phone-head"></div>
					<div>
						<div class="ui-c-renovation-phone-title">页面标题</div>
						<div class="ui-c-renovation-phone-body" id="phone_box">
							<div id="phone_body">
								<%=T.htmlspecialchars_decode(T.getString("html", info)) %>
							</div>
						</div>
					</div>
					<div class="ui-c-renovation-phone-foot"></div>
				</div>
			</td>
			<td valign="top">
				<div id="system_widget_body"></div>
				<div style="padding-top:50px">
					<div class="renovation_submit_btn" onclick="malltodo_renovation_submit()">保存模板</div>
				</div>
			</td>
		</tr>
	</table>
<script>

$('#phone_box').slimScroll({
    height: '545px',
    alwaysVisible: true
});

$("#system_widget_body").slimScroll({
    height: '545px',
    alwaysVisible: true
});

//清除phone_body中的链接
$("#phone_body a").attr("href", "#");
</script>

<script>
var cur_dom_pointer = ""; //当前所编辑的dom
var doms_sort = [];
<%
if(T.getString("doms", info).equals("")){
	out.println("var malltodo_doms = {}; //dom数组");
}else{
	out.println("var malltodo_doms = "+T.htmlspecialchars_decode(T.getString("doms", info))+"; //dom数组");
}
%>

var malltodo_page_config = {
	"name":"<%=T.getString("name", info)%>",
	"title":"<%=T.getString("title", info)%>",
	"keywords":"<%=T.getString("keywords", info)%>",
	"description":"<%=T.getString("description", info)%>",
	"background_color":"<%=(T.getString("background_color", info).equals("") ? "#FFFFFF": T.getString("background_color", info))%>",
	"bottom_id":"<%=T.getString("bottom_id", info)%>",
};

function malltodo_page_config_init(){
	if(malltodo_page_config["background_color"] != ""){
		$(".ui-c-renovation-phone-body").css('background-color', malltodo_page_config["background_color"]);
		$("#phone_body").css('background-color', malltodo_page_config["background_color"]);
	}
	var malltodo_page_config_url = "<%=T.U("Renovation/"+CONTROLLER_NAME+"/pageConfig", "id="+T.getString("id", info), "admin.jsp")%>";
	http.get(malltodo_page_config_url, function(data){
		$("#system_widget_body").html("");
		$("#system_widget_body").html(data+"<div class='ui-c-split'></div>");
		if(malltodo_page_config["bottom_id"] == ""){
			malltodo_page_config["bottom_id"] = 0;
		}
		$("#malltodo_name").val(malltodo_page_config["name"]);
		$("#malltodo_title").val(malltodo_page_config["title"]);
		$("#malltodo_keywords").val(malltodo_page_config["keywords"]);
		$("#malltodo_description").val(malltodo_page_config["description"]);
		$("#malltodo_background_color").val(malltodo_page_config["background_color"]);
		$("#malltodo_bottom_id").val(malltodo_page_config["bottom_id"]);
		ui_c.render();
		malltodo_system_widget_load_after();
	}, "html")
}
malltodo_page_config_init();

function malltodo_dom_click(sign){
	$(".malltodo").removeClass("malltodo-drag");
	$(".malltodo-"+sign).addClass("malltodo-drag");
	cur_dom_pointer = "malltodo"+sign;
	var sub_json = malltodo_doms[cur_dom_pointer];
	get_widget(sub_json["category"], sub_json["name"], sub_json["sign"], JSON.stringify(sub_json));
}

var window_layer = null;
function show_widgets_window(window_title, url){
	window_layer = layer.open({
	  type: 2,
	  title: window_title,
	  shadeClose: true,
	  shade: false,
	  maxmin: true,
	  area: ['850px', '600px'],
	  content: [url],
	});
}

function get_widget(widget_category, widget_name, widget_sign, widget_json){
	layer.close(window_layer);
	var url = "<%=T.U("Renovation/ShowWidget/create", "admin.jsp")%>"+"&category="+widget_category+"&name="+widget_name;
	if(widget_sign != undefined && widget_sign != "undefined"){
		url = url + "&sign="+widget_sign;
	}
	http.post(url, {"json":widget_json}, function(data){
		malltodo_doms["malltodo"+data["dom"]["sign"]] = data["dom"];
		cur_dom_pointer = "malltodo"+data["dom"]["sign"];
		//if(widget_sign == "" || widget_sign == undefined){
			//malltodo_doms.push(data["dom"]);
		//}
		var html = data["html"];
		//html = "<div data-malltodosign='malltodo"+data["dom"]["sign"]+"' class='malltodo malltodo-"+data["dom"]["sign"]+"' onclick='malltodo_dom_click(\""+data["dom"]["sign"]+"\")'>"+html+"</div>";
		if(widget_sign == "" || widget_sign == undefined){
			$("#phone_body").append(html);
			$(".malltodo").removeClass("malltodo-drag");
			$(".malltodo-"+data["dom"]["sign"]).addClass("malltodo-drag");
		}else{
			$(".malltodo-"+data["dom"]["sign"]).html('');
			$(".malltodo-"+data["dom"]["sign"]).html(data['html']);
		}
		
		var system_html = data["system_html"];
		$("#system_widget_body").html("");
		$("#system_widget_body").html(system_html+"<div class='ui-c-split'></div>");
		$("#del_dom_btn").show();
		
		if(widget_json != undefined && widget_json != "undefined" && widget_json != null && widget_json != ""){
			var sub_json = $.parseJSON(widget_json);
			console.log(sub_json);
			if(sub_json.hasOwnProperty("javatodo-bind-param")){
				for(var k in sub_json["javatodo-bind-param"]){
					var _id_name = "javatodomodel"+k+sub_json["sign"];
					$("#"+_id_name).val(sub_json["javatodo-bind-param"][k]);
				}
			}
		}
		
		//清除phone_body中的链接
		$("#phone_body a").attr("href", "#");
		
		ui_c.render();
		malltodo_system_widget_load_after();
	});
}

function show_link_window(href_dom_javatodo){
	window_layer = layer.open({
	  type: 2,
	  title: "选择链接",
	  shadeClose: true,
	  shade: false,
	  maxmin: true,
	  area: ['850px', '600px'],
	  content: ["./admin.jsp?m=Renovation&c=ShowLink&a=index&href_dom_id="+href_dom_javatodo],
	});
}

function get_link(href_dom_javatodo, url){
	layer.close(window_layer);
	$("#"+href_dom_javatodo+"_href").val(url);
	href.todo(href_dom_javatodo)
}

</script>


<!--修改循环次数--开始-->
<div id="malltodo_loop_box" style="position: fixed; top: 25%; left: 25%; background-color: white; border: 1px solid #D2D2D2; z-index: 100000; display: none; box-shadow: 0 2px 4px rgba(0, 0, 0, .12);">
	<div id="box_title" style="width: auto; height: 50px; padding-left: 15px; border-bottom: 1px solid #E2E2E2; font-size: 16px; line-height: 50px;"><span>修改循环次数</span>
		<span style="float: right; font-size: 14px; margin-right: 15px; cursor: pointer;" onclick="close_malltodo_loop_box()">关闭</span>
	</div>
	<div id="malltodo_loop_box_content" style="padding: 10px;">
		<div><input id="malltodo_loop_times_value" type="text" class="ui-c-input" placeholder="请输入新的循环次数" value="" /></div>
		<div style="margin-top: 10px;">
			<a onclick="update_malltodo_dom()" class="main_button" style="float: none; margin: auto;">确定</a>
		</div>
	</div>
</div>

<script>
var loop_id = "";
var bind_loop_id = "";
function show_malltodo_loop_box(id){
	loop_id = id;
	bind_loop_id = "";
	var width = parseInt($(window).width());
	var height = parseInt($(window).height());
	$("#malltodo_loop_box").width(width*0.5);
	$("#malltodo_loop_box").height(height*0.5)
	var box_height = parseInt($("#malltodo_loop_box").height());
	var box_content_height = (box_height - 50);
	$("#malltodo_loop_box_content").height(box_content_height);
	$("#malltodo_loop_times_value").val("");
	$("#malltodo_loop_box").show();
}
function show_malltodo_bind_loop_box(id){
	loop_id = "";
	bind_loop_id = id;
	var width = parseInt($(window).width());
	var height = parseInt($(window).height());
	$("#malltodo_loop_box").width(width*0.5);
	$("#malltodo_loop_box").height(height*0.5)
	var box_height = parseInt($("#malltodo_loop_box").height());
	var box_content_height = (box_height - 50);
	$("#malltodo_loop_box_content").height(box_content_height);
	$("#malltodo_loop_times_value").val("");
	$("#malltodo_loop_box").show();
}
function close_malltodo_loop_box(){
	loop_id = "";
	bind_loop_id = "";
	$("#malltodo_loop_box").hide();
}
function update_malltodo_dom(){
	var zhi = $("#malltodo_loop_times_value").val();
	if(zhi == ""){
		layer.msg("请输入循环次数");
		return ;
	}
	zhi = parseInt(zhi);
	if(zhi == 0){
		layer.msg("循环次数不能为0");
		return ;
	}
	if(loop_id != ""){
		malltodo_doms[cur_dom_pointer][loop_id]["loop-times"] = zhi;
	}else{
		malltodo_doms[cur_dom_pointer]["javatodo-bind-loop"][bind_loop_id]["loop-times"] = zhi;
	}
	var category = malltodo_doms[cur_dom_pointer]["category"];
	var name = malltodo_doms[cur_dom_pointer]["name"];
	var sign = malltodo_doms[cur_dom_pointer]["sign"];
	var json = malltodo_doms[cur_dom_pointer];
	json = JSON.stringify(json);
	get_widget(category, name, sign, json);
	close_malltodo_loop_box();
}
</script>
<!--修改循环次数--结束-->

<script>
$(function() {
	$("#phone_body").sortable({});
});

function del_dom(sign){
	layer.msg('您确定要删除？', {
		time: 0,
		btn: ['确定', '取消'],
		yes: function(index) {
			layer.close(index);
			$('.malltodo-'+sign).remove();
			delete(malltodo_doms["malltodo"+sign]);
			cur_dom_pointer = "";
			$("#system_widget_body").html('');
		}
	});
}

function change_model_param(sign, key){
	var zhi = $("#javatodomodel"+key+sign).val();
	malltodo_doms[cur_dom_pointer]["javatodo-bind-param"][key] = zhi;
}

function malltodo_renovation_submit(){
	var doms_sort = [];
	var len = $("#phone_body").children('.malltodo').length;
	for(var i=0; i<len; i=i+1){
		var malltodosign = $("#phone_body").children('.malltodo').eq(i).data("malltodosign");
		doms_sort[i] = malltodosign;
	}
	var post_data = {
		"doms": JSON.stringify(malltodo_doms),
		"doms_sort": JSON.stringify(doms_sort),
		"name":malltodo_page_config["name"],
		"title":malltodo_page_config["title"],
		"keywords":malltodo_page_config["keywords"],
		"description":malltodo_page_config["description"],
		"background_color":malltodo_page_config["background_color"],
		"bottom_id":malltodo_page_config["bottom_id"]
	};
	http.post('', post_data, function(data){
		layer.msg(data["info"], {
            time: 2000
        }, function () {
        	if(data["status"] == 1){
        		parent.location.reload();
        	}
        })
	});
}

function malltodo_system_widget_load_after(){
	var num = 5;
	for(var i=1; i<num; i=i+1){
		if($(".ui-c-"+i).length == 0){
			break;
		}else{
			$(".ui-c-"+i).eq(0).before("<div class=\"ui-c-loop-split\">————第"+i+"组————</div>");
		}
		if(i == num-1){
			num = num*2;
		}
	}
}
</script>

<%@include file="../../admin.Index/IndexController/sub_bottom.jsp"%>