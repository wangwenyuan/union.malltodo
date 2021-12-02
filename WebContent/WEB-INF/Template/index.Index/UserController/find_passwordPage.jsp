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
		<title>找回密码</title>
		<meta name="keywords" content="找回密码-MALLTODO" />
		<meta name="description" content="" />
		<script src="<%=request.getAttribute("PUBLIC").toString() %>/js/jquery.js"></script>
		<script src="<%=request.getAttribute("PUBLIC").toString() %>/js/layer.js"></script>
		<script src="<%=request.getAttribute("PUBLIC").toString() %>/js/http.js"></script>
		<script src="<%=request.getAttribute("PUBLIC").toString() %>/js/js.js"></script>
		<script src="<%=request.getAttribute("PUBLIC").toString() %>/js/send_sms.js"></script>
		<style>
			.input_box{ width:80%; margin:auto; padding-bottom:0.625rem;}
			.input_text{ width:100%; height:1.875rem; font-size:0.875rem; color:#666; line-height:1.875rem; border:#CCC 0.0625rem solid; border-radius:0.125rem; outline:none;}
			.footer a{ color:#1991EB; text-decoration:none;}
			.footer a:hover{ text-decoration:none;}
			.zhuce{ font-size: 0.875rem; line-height: 1rem;}
			.zhuce a{ color: #333333;}
			#send_sms_btn{ text-align: center; line-height: 1.875rem; font-size: 0.875rem; background-color: #FF3D6F; color: white; border-radius: 0.125rem;}
		</style>
	</head>
	<body style="background:#FFF;">
		<font class="icon-angle-left" style="float:left; margin-left:0.625rem; font-size:1.6875rem; position:absolute; line-height:2.8125rem; color:#666;"></font>
		<div style="height:2.8125rem; text-align:center; line-height:2.8125rem; font-size:1rem; color:#666;"><span>找回密码</span></div>

		<div style="text-align:center; padding-top:2.5rem; padding-bottom:2.5rem;"><img id="logo_img" src="" style="overflow:hidden; width: 6.25rem; height: 6.25rem; border-radius:6rem" /></div>
		<form action="" method="post" id="form_html">
			<div class="input_box"><input class="input_text" style="border:none; border-bottom:#CCC 0.0625rem solid;" type="text"
				 name="mobile" id="mobile" placeholder="请输入手机号" /></div>
			<div class="input_box"><input class="input_text" style="border:none; border-bottom:#CCC 0.0625rem solid;" type="password"
				 name="password" id="password" placeholder="请输入新密码" /></div>
			<div class="input_box">
				<div style="width:60%; float:left; overflow:hidden">
					<input class="input_text" style="border:none; border-bottom:#CCC 0.0625rem solid; width:100%" type="text" name="verify" id="verify" placeholder="验证码" />
				</div>
				<div style="width:39%; float:right; overflow:hidden">
					<img src="" id="send_sms_yanzheng" style="width:100%; float:right; height: 1.875rem;" />
				</div>
				<div style="clear:both"></div>
			</div>
			<div class="input_box">
				<div style="width:60%; float:left; overflow:hidden">
					<input class="input_text" style="border:none; border-bottom:#CCC 0.0625rem solid; width:100%" type="text" name="sms_code" id="sms_code" placeholder="短信码" />
				</div>
				<div id="send_sms_btn" style="width:39%; float:right; height: 1.875rem;">获取验证码</div>
				<div style="clear:both"></div>
			</div>
			<div class="input_box">
				<div class="input_text" style="text-align:center; background:#eee; border:#eee 0.0625rem solid;" id="denglu" onclick="find_password()">找回密码</div>
			</div>
		</form>
		
		<div class="input_box zhuce"><span style="float: left;"><a href="<%=T.U("Index/User/login", "index.jsp") %>">登录</a></span><span style="float: right;"><a href="<%=T.U("Index/User/register", "index.jsp") %>">注册</a></span></div>
		
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
			function load_verify_pic(){
				$("#send_sms_yanzheng").attr("src", "<%=T.U("Index/User/verify", "index.jsp") %>"+"&t="+Math.random());
			}
			load_verify_pic();
			$("#send_sms_yanzheng").click(function(){
				load_verify_pic();
			})
			send_sms("<%=T.U("Index/User/send_find_password_sms", "index.jsp") %>");
			
			var find_password_click_flag = 0;
			function find_password(){
				if(find_password_click_flag == 1){
					return ;
				}else{
					find_password_click_flag = 1;
				}
				var shuju = {
					"mobile":$("#mobile").val(),
					"password":$("#password").val(),
					"code":$("#sms_code").val()
				}
				http.post("<%=T.U("Index/User/find_password", "index.jsp") %>", shuju, function(data){
					layer.msg(data["info"], {
					    time: 2000
					}, function () {
					    if (data["url"] != "") {
					        document.location.href = data["url"];
					    }else{
					    	find_password_click_flag = 0;
					    }
					})
				})
			}
		</script>
	</body>
</html>