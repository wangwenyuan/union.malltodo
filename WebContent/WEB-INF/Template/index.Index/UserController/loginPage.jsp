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
		<title>登录-MALLTODO</title>
		<meta name="keywords" content="登录，MALLTODO" />
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
	<body style="background:#FFF;">
		<font class="icon-angle-left" style="float:left; margin-left:0.625rem; font-size:1.6875rem; position:absolute; line-height:2.8125rem; color:#666;"></font>
		<div style="height:2.8125rem; text-align:center; line-height:2.8125rem; font-size:1rem; color:#666;"><span>登录</span></div>
		<div style="text-align:center; padding-top:2.5rem; padding-bottom:2.5rem;"><img id="logo_img" src="" style="overflow:hidden; width: 6.25rem; height: 6.25rem; border-radius:6rem" /></div>
		<form action="" method="post" id="form_html">
			<div class="input_box"><input class="input_text" style="border:none; border-bottom:#CCC 0.0625rem solid;" type="text"
				 name="mobile" id="mobile" placeholder="请输入手机号" /></div>
			<div class="input_box"><input class="input_text" style="border:none; border-bottom:#CCC 0.0625rem solid;" type="password"
				 name="password" id="password" placeholder="请输入密码" /></div>
			<div class="input_box">
				<div class="input_text" style="text-align:center; background:#eee; border:#eee 0.0625rem solid;" id="denglu" onclick="login()">登录</div>
			</div>
		</form>
		<div class="input_box zhuce"><span style="float: left;"><a href="<%=T.U("Index/User/register", "index.jsp") %>">注册</a></span><span style="float: right;"><a href="<%=T.U("Index/User/find_password", "index.jsp") %>">找回密码</a></span></div>
		<style>
			#zhegai {
				background: #000;
				position: fixed;
				top: 0px;
				left: 0px;
				z-index: 100000;
				opacity: 0.7;
				display: none;
			}

			#progress {
				width: 80%;
				height: 4rem;
				margin: auto;
				background: #FFF;
				position: fixed;
				z-index: 100001;
				display: none;
				top: 0px;
				left: 0px;
			}

			#progress_title {
				width: auto;
				padding-left: 1rem;
				padding-right: 1rem;
				line-height: 2rem;
				font-size: 1rem;
			}

			#progress_bar_box {
				width: 90%;
				margin: auto;
				height: 1rem;
				margin-top: 0.5rem;
				background: #CCC;
			}

			#progress_bar {
				background: #0CF;
				height: 100%;
				width: 0%;
			}
		</style>
		<div id="zhegai"></div>

		<script>
			var login_click_flag = 0;
			function login(){
				if(login_click_flag == 1){
					return ;
				}else{
					login_click_flag = 1;
				}
				http.post(U("Index/User/login"), {"mobile":$("#mobile").val(), "password":$("#password").val()}, function(data){
					layer.msg(data["info"], {
	                    time: 2000
	                }, function () {
	                    if (data["url"] != "") {
	                        document.location.href = data["url"];
	                    }else{
	                    	window.location.reload();
	                    }
	                })
				});
			}
		</script>
	</body>
</html>