<%@page import="common.database.QR"%>
<%@page import="com.javatodo.core.tools.T"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="x-dns-prefetch-control" content="on" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<meta content="telephone=no" name="format-detection" />
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="apple-touch-icon" href="/touch-icon-iphone.png" />
		<link href="/favicon.ico" rel="shortcut icon" />
		<link href="/favicon.ico" rel="icon">
		<link href="/touch-icon-iphone.png" rel="Bookmark" />
		<title>美团联盟</title>
		<meta name="keywords" content="美团联盟" />
		<meta name="description" content="" />
		<script src="<%=request.getAttribute("PUBLIC").toString() %>/js/jquery.js"></script>
		<script src="<%=request.getAttribute("PUBLIC").toString() %>/js/layer.js"></script>
		<script src="<%=request.getAttribute("PUBLIC").toString() %>/js/js.js"></script>
		<style>
			.input_box{ width:80%; margin:auto; padding-bottom:0.625rem;}
			.input_text{ width:100%; height:1.875rem; font-size:0.875rem; color:#666; line-height:1.875rem; border:#CCC 0.0625rem solid; border-radius:0.125rem; outline:none;}
			.footer a{ color:#1991EB; text-decoration:none;}
			.footer a:hover{ text-decoration:none;}
			.zhuce{ font-size: 0.875rem; line-height: 1rem;}
			.zhuce a{ color: #333333;}
		</style>
	</head>
	<body style="background:#FFF; padding-top:100px;">
		<div style="text-align:center"><p>长按识别下面二维码</p></div>
		
		<div>
			<div style="width:200px; height:200px; margin:auto">
				<img src="<%=request.getAttribute("url").toString() %>" width="200px" height="200px" />
			</div>
		</div>
		
	</body>
</html>