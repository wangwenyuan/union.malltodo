<%@page import="common.Common"%>
<%@page import="common.database.WEIXIN"%>
<%@page import="common.database.PDD"%>
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
<style>
.ui-c-element{ float:left}
.ui-c-note{ height:30px; line-height:30px; font-size:12px; float:left; padding-left:10px;}
</style>
<div style="padding:15px;">
	<div class="main_title">微信参数设置</div>
	<div class="clear_5px"></div>
	<div>
<%
Map<String, Object> info = (HashMap)request.getAttribute("info");
%>

<form method="post" action="" id="html_form">
    <table width="100%" cellpadding="0" cellspacing="0" class="small_main_table">
        <tr><td></td><td>微信公众账号设置</td></tr>
        <tr><td align="right" width="150px">开发者ID(AppID)：</td><td align="left"><%=Common.template.htmlWidget("text_widget", WEIXIN.appid, T.getString(WEIXIN.appid, info)) %></td></tr>
        <tr><td align="right" width="150px">开发者密码(AppSecret)：</td><td align="left"><%=Common.template.htmlWidget("text_widget", WEIXIN.appsecret, T.getString(WEIXIN.appsecret, info)) %></td></tr>
        <tr><td></td><td>微信支付设置</td></tr>
        <tr><td align="right" width="150px">商户号：</td><td align="left"><%=Common.template.htmlWidget("text_widget", WEIXIN.mchid, T.getString(WEIXIN.mchid, info)) %></td></tr>
        <tr><td align="right" width="150px">API密钥：</td><td align="left"><%=Common.template.htmlWidget("text_widget", WEIXIN.wxpay_key, T.getString(WEIXIN.wxpay_key, info)) %></td></tr>
        <tr><td align="right" width="150px">CERT证书文件：</td><td align="left"><input type="text" class="input_text" name="apiclient_cert" value="<%=T.getString("apiclient_cert", info) %>" id="apiclient_cert" style="float:left" /><span class="edit_anniu icon-upload" style="margin-left:10px;" id="apiclient_cert_upload"></span> <span class="ui-c-note"> （下载证书 cert.zip 中的 apiclient_cert.p12 文件，<font color="red">注意：不要上传apiclient_cert.pem文件</font>） </span> </td></tr>
        <tr><td></td><td><input type="button" class="anniu" id="add" value="提交" /></td></tr>
    </table>
</form>
	</div>
</div>
<script>
	KindEditor.ready(function(K) {
		var editor = K.editor({
			allowFileManager : true,
			uploadJson: "<%=T.U("SystemSet/Weixin/file", "admin.system.jsp", request) %>",
            fileManagerJson: "<%=T.U("SystemSet/Weixin/manager", "admin.system.jsp", request) %>",
		});
		K('#apiclient_cert_upload').click(function() {
			editor.loadPlugin('insertfile', function() {
				editor.plugin.fileDialog({
					fileUrl : K('#apiclient_cert').val(),
					clickFn : function(url, title) {
						K('#apiclient_cert').val(url);
						editor.hideDialog();
					}
				});
			});
		});
	});
</script>
<%@include file="../../admin.home.Index/IndexController/bottom.jsp"%>
