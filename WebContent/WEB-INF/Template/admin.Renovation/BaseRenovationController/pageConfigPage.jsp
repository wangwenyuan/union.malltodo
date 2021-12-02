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
		 onkeyup="set_malltodo_config_value('name')" /></div>
	<div class="ui-c-clear"></div>
</div>
<%
if(!CONTROLLER_NAME.equals("Bottom")){
%>
<div class="ui-c-box">
	<div class="ui-c-box-left">页面标题</div>
	<div class="ui-c-box-right"><input id="malltodo_title" type="text" class="ui-c-input" placeholder="请输入" value="<%=T.getString("title", info)%>"
		 onkeyup="set_malltodo_config_value('title')" /></div>
	<div class="ui-c-clear"></div>
</div>
<div class="ui-c-box">
	<div class="ui-c-box-left">页面关键字</div>
	<div class="ui-c-box-right"><input id="malltodo_keywords" type="text" class="ui-c-input" placeholder="请输入" value="<%=T.getString("keywords", info)%>"
		 onkeyup="set_malltodo_config_value('keywords')" /></div>
	<div class="ui-c-clear"></div>
</div>
<div class="ui-c-box">
	<div class="ui-c-box-left">页面描述</div>
	<div class="ui-c-box-right">
		<textarea id="malltodo_description" class="ui-c-input" style="height:200px;" onkeyup="set_malltodo_config_value('description')"><%=T.getString("description", info)%></textarea>
	</div>
	<div class="ui-c-clear"></div>
</div>
<div class="ui-c-box">
	<div class="ui-c-box-left">页面背景色：</div>
	<div class="ui-c-box-right">
		<input id="malltodo_background_color" style="margin-top: 0.125rem;" name="background_color" type="color" value="<%=(T.getString("background_color", info).equals("") ? "#FFFFFF": T.getString("background_color", info))%>" onchange="set_malltodo_config_value('background_color')" /></div>
	<div class="ui-c-clear"></div>
</div>
<%JSONArray arr = (JSONArray)request.getAttribute("bottom_list"); %>
<div class="ui-c-box">
	<div class="ui-c-box-left">底部菜单：</div>
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
		$(".ui-c-renovation-phone-body").css('background-color', zhi);
		$("#phone_body").css('background-color', zhi);
	}else if(id == "title"){
		$(".ui-c-renovation-phone-title").html("");
		$(".ui-c-renovation-phone-title").html(zhi);
	}
	malltodo_page_config[id] = zhi;
}
</script>