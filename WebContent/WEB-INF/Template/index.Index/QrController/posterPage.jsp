<%@page import="common.database.QR"%>
<%@page import="com.javatodo.core.tools.T"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0">
		<title>MALLTODO</title>
		<meta name="keywords" content="MALLTODO，MALLTODO联盟">
		<meta name="description" content="MALLTODO是由郑州掌勺信息技术有限公司开发的一套导购类商城系统">
		<script src="/Public/js/jquery.js"></script>
		<script src="/Public/js/js.js"></script>
		<style>
			body {
				margin: 0rem;
				padding: 0rem;
			}
			#hide_poster{
				display: none;
			}
		</style>
	</head>
	<body>
		<script>
			<%
			JSONObject info = (JSONObject)request.getAttribute("info");
			%>
		</script>
		
		<canvas id="hide_poster" width="<%=info.getInteger(QR.bgimg_width)*3 %>" height="<%=info.getInteger(QR.bgimg_height)*3 %>"></canvas>
		<img id="poster" width="100%" height="auto" />
		<script>
			var hide_poster = document.getElementById("hide_poster");
			var poster_context = hide_poster.getContext("2d");
			var poster_bg = new Image();
			poster_bg.src = "<%=info.getString(QR.bgimg) %>";
			poster_bg.setAttribute('crossOrigin', 'anonymous');
			poster_bg.onload = function() {
				//绘制背景
				poster_context.drawImage(poster_bg, 0, 0, <%=info.getInteger(QR.bgimg_width)*3 %>, <%=info.getInteger(QR.bgimg_height)*3 %>);
				//绘制二维码
				var qrcode = new Image();
				qrcode.src = "<%=info.getString("qrcode") %>";
				qrcode.setAttribute('crossOrigin', 'anonymous');
				qrcode.onload = function() {
					poster_context.drawImage(qrcode, <%=info.getInteger(QR.qrimg_left)*3 %>, <%=info.getInteger(QR.qrimg_top)*3 %>, <%=info.getInteger(QR.qrimg_width)*3 %>, <%=info.getInteger(QR.qrimg_height)*3 %>);
					var poster = document.getElementById("poster");
					poster.src = hide_poster.toDataURL();
				}
			}
		</script>
	</body>
</html>