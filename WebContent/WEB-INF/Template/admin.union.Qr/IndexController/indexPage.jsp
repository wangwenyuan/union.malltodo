<%@page import="common.database.QR"%>
<%@page import="common.database.RENOVATION"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../../admin.home.Index/IndexController/header.jsp"%>
<link href="<%=request.getAttribute("PUBLIC") %>/css/renovation.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/js/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("PUBLIC") %>/css/jquery-ui.css" />

<link href="<%=request.getAttribute("PUBLIC") %>/ui-c/drag/drag.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getAttribute("PUBLIC") %>/ui-c/resizebox/resizebox.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/ui-c/drag/drag.js"></script>
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/ui-c/resizebox/resizebox.js"></script>
<%
Map<String, Object> info = (HashMap)request.getAttribute("info");
%>
<div style="padding:15px;">
	<div class="main_title">海报设计</div>
	<div class="clear_5px"></div>
	<div>
		<style>
			.JAVATODO{ display: none; width: 100%; position: absolute;}
			.malltodo{ cursor: pointer;}
			.malltodo-drag{ cursor: move; border: #0099CC 2px dashed;}
			.ui-c-box { padding: 0.3125rem; clear: both; }
			.ui-c-box:hover { background-color: #EFEFEF; }
			.ui-c-box-left { width: 10.625rem; font-size: 0.875rem; color: #333333; line-height: 1.875rem; float: left; }
			.ui-c-box-right { width: 21.875rem; font-size: 0.875rem; color: #333333; float: left; }
			.ui-c-slider { margin-top: 0.5rem; }
			.ui-c-upload { background: center center no-repeat url(./Public/images/pic_beijing.jpg); background-size: cover; width: 100px; height: 100px; }
			.main_del_button{ height: 25px; line-height: 25px; display: block; text-align: center; color: #FFF; background: #FF0000; border: #FF0000 2px solid; float: left; padding-left: 10px; padding-right: 10px; font-size: 14px; text-decoration: none; cursor: pointer; border-radius: 2px;}
		</style>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top" width="400px">
						<div class="ui-c-renovation-phone">
							<div class="ui-c-renovation-phone-head"></div>
							<div>
								<div class="ui-c-renovation-phone-title">分享海报</div>
								<div class="ui-c-renovation-phone-body">
									<div id="phone_body" class="ui-c-drag-region">
										<img class="JAVATODO" src="<%=T.getString(QR.bgimg, info) %>" />
										<div class="ui-c-resizebox ui-c-drag" style="width: <%=T.getString(QR.qrimg_width, info) %>px; height: <%=T.getString(QR.qrimg_height, info) %>px; margin-left: <%=T.getString(QR.qrimg_left, info) %>px; margin-top: <%=T.getString(QR.qrimg_top, info) %>px; background-color: #0099CC;">
											<img id="qrcode" src="<%=request.getAttribute("PUBLIC") %>/images/qrcode.png" style="width: 100%; height: 100%;" />
										</div>
									</div>
								</div>
							</div>
							<div class="ui-c-renovation-phone-foot"></div>
						</div>
					</td>
					<td valign="top">
						<div id="system_widget_body">
							<div class="ui-c-box" style="font-size: 0.875rem"><strong>分享二维码设置</strong></div>
							<div class="ui-c-box">
								<div class="ui-c-box-left" id="JAVATODO_title">设置背景图</div>
								<div class="ui-c-box-right">
									<input accept="image/*" name="JAVATODO_src" type="file" style="display: none;" id="JAVATODO_src" onchange="src_filechange('JAVATODO')">
									<div id="JAVATODO_src_btn" onclick="src.uploadfile('JAVATODO')" class="ui-c-upload"></div>
								</div>
								<div class="ui-c-clear"></div>
							</div>
							<script>
								if (typeof src == "undefined") {
									var src = {};
									src.init = function(id) {
										var src_url = $("." + id).attr("src");
										if (src_url != "" && src_url != undefined) {
											$("#" + id + "_src_btn").css('background-image', "url(" + src_url + ")");
											$("." + id).show();
											$("." + id).load(function(){
												$(".ui-c-renovation-phone-body").css("min-height", $("." + id).height()+"px");
												$("#phone_body").css("min-height", $("." + id).height()+"px");
											});
										}
									};
									src.uploadfile = function(id) {
										document.getElementById(id + "_src").click();
									};
									src_filechange = function(id) {
										var file = document.getElementById(id + "_src").files[0];
										var r = new FileReader();
										var base64 = "";
										r.readAsDataURL(file);
										var base64 = "";
										//console.log(r.result);
										r.onload = function() {
											base64 = r.result;
											http.post('./index.jsp?m=Index&c=Uploads&a=base64', {
												"base64": base64
											}, function(data) {
												if (data["error"] == 1) {
													layer.msg("上传失败");
												} else {
													$("#" + id + "_src_btn").css('background-image', "url(" + data["url"] + ")");
													src.todo(id, data["url"]);
												}
											})
										}
									};
									src.todo = function(id, url) {
										$("." + id).attr("src", url);
										$("." + id).show();
										$("." + id).load(function(){
											$(".ui-c-renovation-phone-body").css("min-height", $("." + id).height()+"px");
											$("#phone_body").css("min-height", $("." + id).height()+"px");
										});
									};
								}
								src.init("JAVATODO");
							</script>
						</div>
						<div style="padding-top:50px">
							<div class="renovation_submit_btn" onclick="malltodo_qr_submit()">保存</div>
						</div>
					</td>
				</tr>
			</table>
	</div>
</div>

<script>
	ui_c.build_resizebox_dom();
	ui_c.build_drag_dom();
	function malltodo_qr_submit(){
		if($(".JAVATODO").attr("src") == ""){
			layer.msg("背景图不能为空");
			return ;
		}
		loading = layer.load(2, {
			shade: [0.2, '#000']
		});
		http.post("", {
			"bgimg":$(".JAVATODO").attr("src"),
			"bgimg_width":$(".JAVATODO").width(),
			"bgimg_height":$(".JAVATODO").height(),
			"qrimg_width":$("#qrcode").width(),
			"qrimg_height":$("#qrcode").height(),
			"qrimg_left": parseInt($("#qrcode").parent().css("margin-left")),
			"qrimg_top": parseInt($("#qrcode").parent().css("margin-top")),
		}, function(ret){
			layer.close(loading);
			layer.msg(ret.info,{
				time:2000
			},function(){
				if(parseInt(ret.status)){
					window.location.reload();
				}
			})
		});
	}
</script>

<%@include file="../../admin.home.Index/IndexController/bottom.jsp"%>
