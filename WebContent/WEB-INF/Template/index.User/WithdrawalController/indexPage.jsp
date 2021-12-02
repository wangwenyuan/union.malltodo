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
		<title>我要提现</title>
		<meta name="keywords" content="">
		<meta name="description" content="">
		<script src="<%=request.getAttribute("PUBLIC").toString() %>/js/jquery.js"></script>
		<script src="<%=request.getAttribute("PUBLIC").toString() %>/js/layer.js"></script>
		<script src="<%=request.getAttribute("PUBLIC").toString() %>/js/http.js"></script>
		<script src="<%=request.getAttribute("PUBLIC").toString() %>/js/js.js"></script>
		<script src="<%=request.getAttribute("PUBLIC").toString() %>/js/drop.js"></script>
		<link href="<%=request.getAttribute("PUBLIC").toString() %>/css/swiper.min.css" rel="stylesheet" />
		<script src="<%=request.getAttribute("PUBLIC").toString() %>/js/swiper.min.js"></script>
		<style>
			body {
				background: #FFFFFF;
				margin: 0rem;
				padding: 0rem;
			}
		</style>
	</head>
	<body>
		<style>
			#malltodo_mask {
				position: fixed;
				top: 0rem;
				left: 0rem;
				background: center center no-repeat #FFFFFF url(<%=request.getAttribute("PUBLIC").toString() %>/images/loading.gif);
				background-size: inherit;
				z-index: 100000;
			}
		</style>
		<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("PUBLIC").toString() %>/css/pintuer.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("PUBLIC").toString() %>/ui-c/form/form.css" />
		<script>
			var malltodo_windows_width = parseInt($(window).width());
			$('html').css({
				'font-size': (malltodo_windows_width / 304) * 16 + 'px'
			});
		</script>
		<div id="malltodo_mask"></div>
		<script>
			$("#malltodo_mask").width($(window).width());
			$("#malltodo_mask").height($(window).height());
			$(function() {
				$("#malltodo_mask").fadeOut(500, function() {
					$("#malltodo_mask").hide();
				})
			})
		</script>
		<style>
	.withdrawal_header {
		width: 100%;
		height: 2.5rem;
		display: flex;
		background: rgb(210, 20, 13);
		position: fixed;
		top: 0rem;
		z-index: 100001;
	}

	.withdrawal_top {
		width: auto;
		height: 2.5rem;
	}

	.withdrawal_header_title {
		flex: 1;
		height: 1.7rem;
		margin-right: 0.5rem;
		margin-top: 0.4rem;
		margin-left: 0.5rem;
		border-radius: 0.125rem;
		text-align: center;
		line-height: 1.7rem;
		color: white;
	}

	.withdrawal_header_return {
		height: 1.7rem;
		width: 1.7rem;
		margin-left: 0.2rem;
		background-color: #FFFFFF;
		box-sizing: border-box;
		flex-direction: column;
		justify-content: center;
		margin-top: 0.4rem;
		background: center center no-repeat url(<%=request.getAttribute("PUBLIC").toString() %>/template/001/return.png);
		background-size: cover;
	}

	.withdrawal_header_right {
		height: 1.7rem;
		width: 1.7rem;
		margin-right: 0.2rem;
		box-sizing: border-box;
		flex-direction: column;
		justify-content: center;
		margin-top: 0.4rem;
		background-size: cover;
	}
</style>
<div class="withdrawal_header">
	<div class="withdrawal_header_return" onclick="history.go(-1)"></div>
	<div class="withdrawal_header_title">
		我要提现
	</div>
	<div class="withdrawal_header_right"></div>
</div>
<div class="withdrawal_top"></div>

<div style="padding:1rem">
	<div style="font-size:0.875rem">
	注意：<br />
	1、提现请在每月25号以后提交。<br />
	</div>
	<div style="padding-top:1rem">
		<div style="font-size:0.875rem; line-height:25px;">当前可提现金额：￥<%=request.getAttribute("money").toString() %></div>
		<input type="text" name="money" id="money" placeholder="请输入提现金额" class="ui-c-input" />
		<div style="padding-top:1rem">
			<div onclick="tijiao()" style="color:white; background: rgb(210, 20, 13); height:30px; line-height:30px; text-align:center; border-radius:2px; font-size:0.875rem">
				提交
			</div>
		</div>
	</div>
</div>

<script>
function tijiao(){
	if(confirm("请再次确认所要提现的金额是否正确？")){
		var money = $("#money").val();
		if(money=="" || money<0.01){
			alert("您输入的提现金额有误");
			return ;
		}
		http.post("", {"money":$("#money").val()}, function(data){
			alert(data["info"]);
			if(data["url"] != ""){
				window.location.href = data["url"];
			}else{
				window.location.reload();
			}
		}, "");
	}
}
</script>

	</body>
</html>
