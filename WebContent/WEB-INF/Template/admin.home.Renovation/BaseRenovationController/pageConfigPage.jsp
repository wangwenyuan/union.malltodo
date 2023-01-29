<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="com.javatodo.core.tools.T"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
JSONObject info = (JSONObject)request.getAttribute("info");
String CONTROLLER_NAME = request.getAttribute("CONTROLLER_NAME").toString();
%>
<div class="ui-c-box" style="font-size: 0.9375rem"><strong>页面设置</strong></div>
<div class="ui-c-box">
	<div class="ui-c-box-left">模版名称</div>
	<div class="ui-c-box-right"><input id="malltodo_name" type="text" class="ui-c-input" placeholder="请输入" value="<%=T.getString("name", info)%>"
		 onkeyup="set_malltodo_config_value('name')" onchange="set_malltodo_config_value('name')" /></div>
	<div class="ui-c-clear"></div>
</div>
<%
if(!CONTROLLER_NAME.equals("Bottom") && !CONTROLLER_NAME.equals("Header")){
%>
<div class="ui-c-box">
	<div class="ui-c-box-left">页面标题</div>
	<div class="ui-c-box-right"><input id="malltodo_title" type="text" class="ui-c-input" placeholder="请输入" value="<%=T.getString("title", info)%>"
		 onkeyup="set_malltodo_config_value('title')" onchange="set_malltodo_config_value('title')" /></div>
	<div class="ui-c-clear"></div>
</div>
<div class="ui-c-box">
	<div class="ui-c-box-left">页面关键字</div>
	<div class="ui-c-box-right"><input id="malltodo_keywords" type="text" class="ui-c-input" placeholder="请输入" value="<%=T.getString("keywords", info)%>"
		 onkeyup="set_malltodo_config_value('keywords')" onchange="set_malltodo_config_value('keywords')" /></div>
	<div class="ui-c-clear"></div>
</div>
<div class="ui-c-box">
	<div class="ui-c-box-left">页面描述</div>
	<div class="ui-c-box-right">
		<textarea id="malltodo_description" class="ui-c-input" style="height:200px;" onkeyup="set_malltodo_config_value('description')" onchange="set_malltodo_config_value('description')"><%=T.getString("description", info)%></textarea>
	</div>
	<div class="ui-c-clear"></div>
</div>

<div class="ui-c-box">
	<div class="ui-c-box-left">页面背景类型：</div>
	<div class="ui-c-box-right"><select id="malltodo_background_0" class="ui-c-select" onchange="malltodo_background.background_select()">
			<option value="1">颜色背景</option>
			<option value="2">图片背景</option>
		</select></div>
	<div class="ui-c-clear"></div>
</div>

<div class="ui-c-box" id="malltodo_background_1">
	<div class="ui-c-box-left">页面背景色：</div>
	<div class="ui-c-box-right">
		<input id="malltodo_background_color" style="margin-top: 0.125rem;" name="malltodo_background_color" type="color" value="<%=(T.getString("background_color", info).equals("") ? "#FFFFFF": T.getString("background_color", info))%>" onchange="malltodo_background.change_background_color()" /></div>
	<div class="ui-c-clear"></div>
</div>

<div class="ui-c-box" id="malltodo_background_2">
	<div class="ui-c-box-left">背景图片：</div>
	<div class="ui-c-box-right">
		<div style="clear:both; height:10px;"></div>
		<input accept="image/*" name="malltodo_background_2_image" type="file" style="display: none;" id="malltodo_background_2_image"
		 onchange="malltodo_background.change_background_image()">
		<div id="malltodo_background_2_pic" onclick="malltodo_background.upload_background_image()" class="ui-c-upload"></div>
	</div>
	<div class="ui-c-clear"></div>
	<div style="height:10px;"></div>
	<div class="ui-c-box-left">背景图是否重复：</div>
	<div class="ui-c-box-right"><select id="malltodo_background_repeat" class="ui-c-select" onchange="malltodo_background.css()">
			<option value="no-repeat">不重复</option>
			<option value="repeat">重复</option>
		</select></div>
	<div class="ui-c-clear"></div>
</div>

<script>
if (typeof malltodo_background == "undefined") {
	var malltodo_background = {};
	malltodo_background.background_select = function() {
		var zhi = $("#malltodo_background_0").val();
		if(zhi == 1){
			$("#malltodo_background_1").show();
			$("#malltodo_background_2").hide();
		}else{
			$("#malltodo_background_1").hide();
			$("#malltodo_background_2").show();
		}
	};
	malltodo_background.color_to_hex = function(color) {
		return color_to_hex(color);
	};
	malltodo_background.change_background_color = function() {
		malltodo_background.css();
	};
	malltodo_background.change_background_image = function() {
		var file = document.getElementById("malltodo_background_2_image").files[0];
		var r = new FileReader();
		var base64 = "";
		r.readAsDataURL(file);
		var base64 = "";
		r.onload = function() {
			base64 = r.result;
			http.post('./index.jsp?m=Index&c=Upload&a=base64', {
				"base64": base64
			}, function(data) {
				if (data["error"] == 1) {
					layer.msg("上传失败");
				} else {
					$("#malltodo_background_2_pic").css('background-image', "url(" + data["url"] + ")");
					malltodo_background.css();
				}
			})
		}
	};
	malltodo_background.upload_background_image = function() {
		document.getElementById("malltodo_background_2_image").click();
	}
	malltodo_background.init = function() {
		var color = malltodo_page_config["background_color"];
		if(color === "rgba(0, 0, 0, 0)"){
			color = "#FFFFFF";
		}else{
			color = malltodo_background.color_to_hex(color);
		}
		$("#malltodo_background_color").val(color);
		var pic = malltodo_page_config["background_image"];
		if (!(pic == 'none' || pic == "" || pic == undefined)) {
			$("#malltodo_background_2_pic").css('background-image', pic);
			$("#malltodo_background_0").val(2);
			var repeat_value = malltodo_page_config["background_repeat"];
			if(repeat_value == "no-repeat"){
				$("#malltodo_background_repeat").val("no-repeat");
				$("body").css('background', "center center no-repeat");
				$("body").css('background-size', "cover");
				$("body").css('background-repeat', "no-repeat");
				$("body").css('background-color', 'none');
			}else{
				$("#malltodo_background_repeat").val("repeat");
				$("body").css('background', "repeat");
				$("body").css('background-repeat', "repeat");
				$("body").css('background-size', "auto");
				$("body").css('background-color', 'none');
			}
			$("body").css('background-image', pic);
		} else {
			$("#malltodo_background_0").val(1);
			$("body").css('background-color', color);
		}
		malltodo_background.background_select();
	};
	malltodo_background.css = function() {
		var zhi = $("#malltodo_background_0").val();
		if (zhi == 1) {
			$("body").css('background-color', $("#malltodo_background_color").val());
			$("body").css('background-image', 'none');
			malltodo_page_config["background_color"] = $("#malltodo_background_color").val();
			malltodo_page_config["background_image"] = "";
			malltodo_page_config["background_repeat"] = "";
		} else {
			var repeat_value = $("#malltodo_background_repeat").val();
			if(repeat_value == "no-repeat"){
				$("body").css('background', "center center no-repeat");
				$("body").css('background-size', "cover");
				$("body").css('background-repeat', "no-repeat");
				$("body").css('background-image', $("#malltodo_background_2_pic").css('background-image'));
				$("body").css('background-color', 'none');
				
				malltodo_page_config["background_color"] = "";
				malltodo_page_config["background_image"] = $("#malltodo_background_2_pic").css('background-image');
				malltodo_page_config["background_repeat"] = "no-repeat";
			}else{
				$("body").css('background', "repeat");
				$("body").css('background-repeat', "repeat");
				$("body").css('background-size', "auto");
				$("body").css('background-image', $("#malltodo_background_2_pic").css('background-image'));
				$("body").css('background-color', 'none');

				malltodo_page_config["background_color"] = "";
				malltodo_page_config["background_image"] = $("#malltodo_background_2_pic").css('background-image');
				malltodo_page_config["background_repeat"] = "repeat";
			}
			
		}
	};
}
malltodo_background.init();

</script>


<%JSONArray header_list = (JSONArray)request.getAttribute("header_list"); %>
<div class="ui-c-box">
	<div class="ui-c-box-left">顶部模块：</div>
	<div class="ui-c-box-right">
		<select id="malltodo_header_id" name="header_id" class="ui-c-select"  onchange="set_malltodo_config_value('header_id')">
			<%
			for(Integer i=0; i<header_list.size(); i=i+1){
				out.println("<option value=\""+header_list.getJSONObject(i).getString("id")+"\">"+header_list.getJSONObject(i).getString("name")+"</option>");
			}
			%>
		</select>
	</div>
	<div class="ui-c-clear"></div>
</div>
<%JSONArray arr = (JSONArray)request.getAttribute("bottom_list"); %>
<div class="ui-c-box">
	<div class="ui-c-box-left">底部模块：</div>
	<div class="ui-c-box-right">
		<select id="malltodo_bottom_id" name="bottom_id" class="ui-c-select"  onchange="set_malltodo_config_value('bottom_id')">
			<%
			for(Integer i=0; i<arr.size(); i=i+1){
				out.println("<option value=\""+arr.getJSONObject(i).getString("id")+"\">"+arr.getJSONObject(i).getString("name")+"</option>");
			}
			%>
		</select>
	</div>
	<div class="ui-c-clear"></div>
</div>
<%
}
%>
<script>
function set_malltodo_config_value(id){
	var zhi = $("#malltodo_"+id).val();
	if(id == "background_color"){
		zhi = color_to_hex(zhi);
		if($(".ui-c-renovation-phone-body").length == 0){
			$('body').css('background-color', zhi);
			$("#phone_body").css('background-color', zhi);
		}else{
			$(".ui-c-renovation-phone-body").css('background-color', zhi);
			$("#phone_body").css('background-color', zhi);
		}
	}else if(id == "title"){
		$(".ui-c-renovation-phone-title").html("");
		$(".ui-c-renovation-phone-title").html(zhi);
	}
	malltodo_page_config[id] = zhi;
}
</script>