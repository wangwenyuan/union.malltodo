<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>欢迎使用javatodo</title>
</head>
<body>
<h1 style="text-align: center"><%=request.getAttribute("welcome").toString() %></h1>
<br>
<p style="text-align: center">欢迎使用javatodo，javatodo官方网址：<a target="_blank" href="http://www.javatodo.com">www.javatodo.com</a></p>
</body>
</html>