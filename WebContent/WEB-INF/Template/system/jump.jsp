<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>提示信息</title>
</head>
<body>

<%
if(request.getAttribute("type").toString().equals("success")){
%>
    <h1 style="text-align: center">成功：<%=request.getAttribute("msg").toString() %></h1>
<%
}else{
%>
    <h1 style="text-align: center">失败：<%=request.getAttribute("msg").toString() %></h1>
<%
}
%>

<br>
<p style="text-align: center">页面自动 <a id="href" href="<% out.print(request.getAttribute("url").toString()); %>">跳转</a> 等待时间： <b id="wait"><% out.print(Integer.parseInt(request.getAttribute("seconds").toString())); %></b></p>
<script type="text/javascript">
(function(){
var wait = document.getElementById('wait'),href = document.getElementById('href').href;
var interval = setInterval(function(){
	var time = --wait.innerHTML;
	if(time <= 0) {
		location.href = href;
		clearInterval(interval);
	};
}, 1000);
})();
</script>
</body>
</html>