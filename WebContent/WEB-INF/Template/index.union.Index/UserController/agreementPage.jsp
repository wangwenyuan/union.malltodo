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
		<title>注册协议</title>
		<meta name="keywords" content="注册协议" />
		<meta name="description" content="" />
		<script src="<%=request.getAttribute("PUBLIC").toString() %>/js/jquery.js"></script>
		<script src="<%=request.getAttribute("PUBLIC").toString() %>/js/layer.js"></script>
		<script src="<%=request.getAttribute("PUBLIC").toString() %>/js/http.js"></script>
		<script src="<%=request.getAttribute("PUBLIC").toString() %>/js/js.js"></script>
	</head>
	<body style="background:#FFF;">
		<div style="padding:10px;">
			<div>
				<%
				String agreement = request.getAttribute("agreement").toString();
				agreement = T.htmlspecialchars_decode(agreement);
				%>
				<%=agreement %>
			</div>
		</div>
	</body>
</html>