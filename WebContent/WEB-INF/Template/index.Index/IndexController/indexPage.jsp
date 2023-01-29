<%@page import="com.javatodo.core.tools.T"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<title>MALLTODO——智慧商业服务商，零代码建站平台</title>
		<meta name="keywords" content="MALLTODO，零代码，低代码，建站平台，网站建设">
		<meta name="description" content="MALLTODO零代码建站平台可以帮助用户快速搭建个性化的企业官网，无需专业的编程知识，提供可视化设计器及管理后台，购买后自动开通，无需安装，在线操作，网站的制作过程就像搭积木一样把需要的组件拖拽过来。真正做到“会打字就能建网站”，且所生成的网站均为响应式，支持电脑端，平板，手机等所有设备，还可以嵌入微信公众号，小程序，抖音，快手等平台。">
		<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("PUBLIC") %>/css/pintuer.css" />
		<link rel="stylesheet" href="<%=request.getAttribute("PUBLIC") %>/malltodo.merchant/css/index.css">
		<link rel="stylesheet" href="./Public/css/swiper.min.css" />
		<script src="<%=request.getAttribute("PUBLIC") %>/js/jquery-1.12.4.min.js"></script>
		<script src="<%=request.getAttribute("PUBLIC") %>/malltodo.merchant/js/jquery.mousewheel.min.js"></script>
		<script src="<%=request.getAttribute("PUBLIC") %>/js/layer.js"></script>
		<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/js/js.js"></script>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=2qd4eTyWcIArUvAW08WiRlvzx3HTIsqr"></script>
		<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/js/swiper.min.js"></script>
		<script>
			var window_width = parseInt($(window).width());
			var window_height = parseInt($(window).height());
		</script>
		<style>
		#banner_box{ width:100%; height:100%; background:#333333; position:absolute; z-index:-1;}
		.slider_pic{ width:100%; height:100%; background: center center no-repeat; background-size: cover;}
		.menu_b{ cursor:pointer; color:red;}
		#form_box_div{ display:none;}
		</style>
	</head>
	<body>
		

<div id="header">
	<div id="header_box">
		<div id="logo" style=""><a href="/">MALLTODO</a></div>
		<a class="menu_b" style="color:red">登录/注册</a>
		<a class="menu_a">联系我们</a>
		<a class="menu_a">代理加盟</a>
		<a class="menu_a">选择我们</a>
		<a class="menu_a">核心优势</a>
		<a class="menu_a">产品特色</a>
		<a class="menu_a">首页</a>
		<div class="clear"></div>
	</div>
</div>
		<div class="content" id="box">
			<div class="content step_box">
				<div class="content_box" style="background-color: rgb(209, 3, 3);">
					
					<div id="banner_box">
						<div class="swiper-wrapper" style="height: 100%;">
							<div class="swiper-slide" style="width: 100%; height: 100%;">
								<div class="slider_pic" style="background-image: url(./Public/images/banner001.jpeg)"></div>
							</div>
							<div class="swiper-slide" style="width: 100%; height: 100%;">
								<div class="slider_pic" style="background-image: url(./Public/images/banner002.jpeg)"></div>
							</div>
							<div class="swiper-slide" style="width: 100%; height: 100%;">
								<div class="slider_pic" style="background-image: url(./Public/images/banner003.jpeg)"></div>
							</div>
						</div>
						<div class="swiper-button-next"></div>
						<div class="swiper-button-prev"></div>
					</div>
					
					<div class="middle_content">
						<div id="form_box_div">
							<form id="login_form">
								<div class="action_box" id="login">
									<div class="login_title" style="padding-top: 25px;">
										<div><a href="javascript:action_box_show('login')" style="color:rgb(209, 3, 3);">商户登陆</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:action_box_show('worker_login')">商户员工登陆</a></div>
									</div>
									<div class="login-input">
										<input type="text" class="input_text" name="mobile" placeholder="手机号" />
									</div>
									<div class="login-input">
										<input type="password" class="input_text" name="password" placeholder="密码" />
									</div>
									<div class="login-input">
										<input type="text" class="input_text" name="verify" placeholder="验证码" style="width:200px; float:left" />
										<img id="login_yanzheng" class="yanzheng" height="41px;" width="95px;" />
									</div>
									<div class="login-input">
										<div class="tijiao" id="login_tijiao">
											商户登录
										</div>
									</div>
									<div class="login-input">
										<div class="login_a">
											<a href="javascript:action_box_show('register')" style="float: left;">没有账号，立即注册</a>
											<a href="javascript:action_box_show('find_password')" style="float: right;">找回密码</a>
										</div>
									</div>
								</div>
							</form>
							
							<form id="worker_login_form">
								<div class="action_box" id="worker_login">
									<div class="login_title" style="padding-top: 25px;">
										<div><a href="javascript:action_box_show('login')">商户登陆</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:action_box_show('worker_login')" style="color:rgb(209, 3, 3);">商户员工登陆</a></div>
									</div>
									<div class="login-input">
										<input type="text" class="input_text" name="username" placeholder="用户名（形如：abc@company1600000000000）" />
									</div>
									<div class="login-input">
										<input type="password" class="input_text" name="password" placeholder="密码" />
									</div>
									<div class="login-input">
										<input type="text" class="input_text" name="verify" placeholder="验证码" style="width:200px; float:left" />
										<img id="worker_login_yanzheng" class="yanzheng" height="41px;" width="95px;" />
									</div>
									<div class="login-input">
										<div class="tijiao" id="worker_login_tijiao">
											商户员工登录
										</div>
									</div>
								</div>
							</form>

							<form id="register_form">
								<div class="action_box" id="register">
									<div class="login_title" style="height:85px; overflow:hidden">
										<div><a href="javascript:action_box_show('register')">注册</a></div>
									</div>
									<div class="login-input">
										<input type="text" class="input_text" name="mobile" id="register_mobile" placeholder="手机号" />
									</div>
									<div class="login-input">
										<input type="password" class="input_text" id="register_password" name="password" placeholder="密码" />
									</div>
									<script>
									$("#register_password").click(function(){
										layer.tips("密码必需包含大小写字母和数字以及特殊字符，且密码要大于8位", $(this));
									})
									</script>
									<div class="login-input">
										<input type="text" class="input_text" name="verify" id="register_yanzhengma" placeholder="验证码" style="width:200px; float:left" />
										<img id="register_yanzheng" class="yanzheng" height="41px;" width="95px;" />
									</div>
									<div class="login-input">
										<input type="text" class="input_text" name="code" id="register_sms_code" placeholder="短信码" style="width:200px; float:left" />
										<div id="register_send_sms_btn">发送短信</div>
									</div>
									<div class="clear" style="margin-top:5px; height:25px; line-height:25px;">
										<input type="checkbox" class="input_checkbox" name="xieyi" id="xieyi" checked="checked" /> <span class="xieyi"><a href="./xieyi.html" target="_blank">我已阅读并同意《MALLTODO商户服务许可协议》</a></span>
									</div>
									<div class="login-input" style="padding-top:2px;">
										<div class="tijiao" id="register_tijiao">
											注册
										</div>
									</div>
									<div class="login-input">
										<div class="login_a">
											<a href="javascript:action_box_show('login')" style="float: left;">登录</a>
											<a href="javascript:action_box_show('find_password')" style="float: right;">找回密码</a>
										</div>
									</div>
								</div>
							</form>
							<form id="find_password_form">
								<div class="action_box" id="find_password">
									<div class="login_title">
										<div><a href="javascript:action_box_show('find_password')">重置密码</a></div>
									</div>
									<div class="login-input">
										<input type="text" class="input_text" name="mobile" id="find_password_mobile" placeholder="手机号" />
									</div>
									<div class="login-input">
										<input type="password" class="input_text" id="find_password_password" name="password" placeholder="新密码" />
									</div>
									<script>
									$("#find_password_password").click(function(){
										layer.tips("密码必需包含大小写字母和数字以及特殊字符，且密码要大于8位", $(this));
									})
									</script>
									<div class="login-input">
										<input type="text" class="input_text" name="verify" id="find_password_yanzhengma" placeholder="验证码" style="width:200px; float:left" />
										<img id="find_password_yanzheng" class="yanzheng" height="41px;" width="95px;" />
									</div>
									<div class="login-input">
										<input type="text" class="input_text" name="code" id="find_password_sms_code" placeholder="短信码" style="width:200px; float:left" />
										<div id="find_password_send_sms_btn">发送短信</div>
									</div>
									<div class="login-input">
										<div class="tijiao" id="find_password_tijiao">
											重置密码
										</div>
									</div>
									<div class="login-input">
										<div class="login_a">
											<a href="javascript:action_box_show('login')" style="float: left;">登录</a>
											<a href="javascript:action_box_show('register')" style="float: right;">注册</a>
										</div>
									</div>
								</div>
							</form>
						</div>
						<div class="clear"></div>
					</div>
				</div>
				<div class="zhegai"></div>
			</div>	
			
			<script>
			
			
			var banner = new Swiper('#banner_box', {
				autoplay:{
					delay: 5000,
				},	
				navigation: {
					nextEl: ".swiper-button-next",
				    prevEl: ".swiper-button-prev",
				},
			});
			
			
				function action_box_show(id) {
					$(".action_box").hide();
					$("#" + id).show();
					$("#" + id + "_yanzheng").attr("src", "<%=T.U("Index/Index/verify", "malltodo.merchant.jsp", request) %>&time=" + Math.random());
				}
				$(".yanzheng").click(function() {
					$(this).attr("src", "<%=T.U("Index/Index/verify", "malltodo.merchant.jsp", request) %>&time=" + Math.random());
				})
				action_box_show("login");
				
				var form_submit_flag = true;
				function form_submit(url, form_id, yanzhengma){
					if(form_submit_flag){
						form_submit_flag = false;
					}else{
						return ;
					}
					var data=$("#"+form_id).serialize();
					var ret=malltodoJs.ajax(url,data);
					if(ret=='error'){
						$("#" + yanzhengma).attr("src", "<%=T.U("Index/Index/verify", "malltodo.merchant.jsp", request) %>&time=" + Math.random());
						layer.msg('网络错误');
					}else{
						layer.msg(ret.info,{
							time:2000
						},function(){
							if(parseInt(ret.status)){
								if(ret.url){
									window.location.href = ret.url;
								}else{
									window.location.reload();
								}
							}else{
								$("#" + yanzhengma).attr("src", "<%=T.U("Index/Index/verify", "malltodo.merchant.jsp", request) %>&time=" + Math.random());
							}
						})
					}
					form_submit_flag = true;
				}
				$("#login_tijiao").click(function() {
					form_submit("<%=T.U("Index/Index/login", "malltodo.merchant.jsp", request) %>", "login_form", "login_yanzheng");
				})
				$("#worker_login_tijiao").click(function() {
					form_submit("<%=T.U("Index/Index/worker_login", "malltodo.merchant.jsp", request) %>", "worker_login_form", "worker_login_yanzheng");
				})
				$("#register_tijiao").click(function() {
					form_submit("<%=T.U("Index/Index/register", "malltodo.merchant.jsp", request) %>", "register_form", "register_yanzheng");
				})
				$("#find_password_tijiao").click(function() {
					form_submit("<%=T.U("Index/Index/find_password", "malltodo.merchant.jsp", request) %>", "find_password_form", "find_password_yanzheng");
				})
				$("#android_download_btn").mouseenter(function(){
					//$(".android_qr").show();
				})
				$("#android_download_btn").mouseleave(function(){
					//$(".android_qr").hide();
				})
			</script>
			<script src="<%=request.getAttribute("PUBLIC") %>/malltodo.merchant/js/send_sms.js?t=001"></script>
			<script>
			send_register_sms("<%=T.U("Index/Index/send_register_sms", "malltodo.merchant.jsp", request) %>");
			send_find_password_sms("<%=T.U("Index/Index/send_find_password_sms", "malltodo.merchant.jsp", request) %>");
			</script>
				
			<div class="content step_box">
				<div class="content_box" style="background-color: rgb(11, 105, 179);">
					<div class="index_content">
						<div class="index_title" style="color:white">MALLTODO的4大特色</div>
						<div class="index_news_div">
							<table width="100%">
								<tr>
									<td width="25%">
										<div class="tedian_box" style="background:url(<%=request.getAttribute("PUBLIC") %>/malltodo.merchant/images/001.jpg) center center no-repeat; background-size: cover;">
											<div class="tedian_big_title" style="line-height:430px; color:white;">可拖拽式页面组件</div>
										</div>
										<div class="tedian_box_yincang">
											<div class="tedian_big_title">可拖拽式页面组件</div>
											<div class="tedian_title">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;无需专业技术，人人都是程序员，仅需通过简单的拖拽便可组装出丰富多彩的页面，避免与其他网站雷同或千篇一律。<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;传统的网站如果要修改页面需要由程序人员先对页面进行修改，然后上传至服务器，MALLTODO对所有的页面打散至一个一个的组件，只需要拖拽相应的组件，然后调整距离、颜色、背景等等即可实现你想要的页面效果。</div>
										</div>
									</td>
									<td width="25%">
										<div class="tedian_box" style="background:url(<%=request.getAttribute("PUBLIC") %>/malltodo.merchant/images/002.jpg) center center no-repeat; background-size: cover;">
											<div class="tedian_big_title" style="line-height:430px; color:white;">营销型网站</div>
										</div>
										<div class="tedian_box_yincang">
											<div class="tedian_big_title">营销型网站</div>
											<div class="tedian_title">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;MALLTODO零代码建站平台基于公司独立研发的模板引擎制作，该模板引擎具有可拖拽式，组件化，对数据和样式可随意调整，所见即所得且能够生成实实在在的html代码。所以通过MALLTODO零代码建站平台建立的网站并非虚拟dom，制作的网站完全可以被搜索引擎收录，并可以对网站内容进行SEO优化以获取更好的排名。</div>
										</div>
									</td>
									<td width="25%">
										<div class="tedian_box" style="background:url(<%=request.getAttribute("PUBLIC") %>/malltodo.merchant/images/003.jpg) center center no-repeat; background-size: cover;">
											<div class="tedian_big_title" style="line-height:430px; color:white;">多平台全渠道多终端覆盖</div>
										</div>
										<div class="tedian_box_yincang">
											<div class="tedian_big_title">多平台全渠道多终端覆盖</div>
											<div class="tedian_title">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;除了电脑和手机浏览器之外，系统还可接入APP 微信公众号 微信小程序 抖音 快手等...</div>
										</div>
									</td>
									<td width="25%">
										<div class="tedian_box" style="background:url(<%=request.getAttribute("PUBLIC") %>/malltodo.merchant/images/004.jpg) center center no-repeat; background-size: cover;">
											<div class="tedian_big_title" style="line-height:430px; color:white;">支持定制开发</div>
										</div>
										<div class="tedian_box_yincang">
											<div class="tedian_big_title">支持定制开发</div>
											<div class="tedian_title">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;专业的研发团队和丰富的开发经验为您的项目保驾护航</div>
										</div>
									</td>
								</tr>
							</table>
							<div class="clear"></div>
						</div>
					</div>
				</div>
				<div class="zhegai"></div>
			</div>
			
			<div class="content step_box">
				<div class="content_box" style="background-color: #007aff;">
					<div class="index_content">
						<div class="index_title" style="color:white">8大核心优势 打造一站式营销生态系统</div>
						<div class="index_news_div">
							<table width="100%">
								<tr>
									<td width="25%">
										<div class="youshi_box">
											<div style="width:66px; margin:auto;"><img src="<%=request.getAttribute("PUBLIC") %>/malltodo.merchant/images/001.png" width="66px" style="margin:auto;" /></div>
											<div class="youshi_big_title">SAAS解决方案</div>
											<div class="youshi_title">即买即用，无需安装部署等繁琐工作</div>
										</div>
									</td>
									<td width="25%">
										<div class="youshi_box">
											<div style="width:66px; margin:auto;"><img src="<%=request.getAttribute("PUBLIC") %>/malltodo.merchant/images/002.png" width="66px" style="margin:auto;" /></div>
											<div class="youshi_big_title">全渠道多终端覆盖</div>
											<div class="youshi_title">PC+平板+手机+公众号+小程序+H5等全覆盖</div>
										</div>
									</td>
									<td width="25%">
										<div class="youshi_box">
											<div style="width:66px; margin:auto;"><img src="<%=request.getAttribute("PUBLIC") %>/malltodo.merchant/images/003.png" width="66px" style="margin:auto;" /></div>
											<div class="youshi_big_title">拖拽式页面设计</div>
											<div class="youshi_title">无需专业技术，人人可设计</div>
										</div>
									</td>
									<td width="25%">
										<div class="youshi_box">
											<div style="width:66px; margin:auto;"><img src="<%=request.getAttribute("PUBLIC") %>/malltodo.merchant/images/004.png" width="66px" style="margin:auto;" /></div>
											<div class="youshi_big_title">丰富营销玩法</div>
											<div class="youshi_title">轻松实现拓客引流，留存转化</div>
										</div>
									</td>
								</tr>
								<tr>
									<td width="25%">
										<div class="youshi_box">
											<div style="width:66px; margin:auto;"><img src="<%=request.getAttribute("PUBLIC") %>/malltodo.merchant/images/005.png" width="66px" style="margin:auto;" /></div>
											<div class="youshi_big_title">专业系统研发团队</div>
											<div class="youshi_title">保障系统稳定数据安全</div>
										</div>
									</td>
									<td width="25%">
										<div class="youshi_box">
											<div style="width:66px; margin:auto;"><img src="<%=request.getAttribute("PUBLIC") %>/malltodo.merchant/images/006.png" width="66px" style="margin:auto;" /></div>
											<div class="youshi_big_title">优质售后运维服务</div>
											<div class="youshi_title">提供高效及时运维支持</div>
										</div>
									</td>
									<td width="25%">
										<div class="youshi_box">
											<div style="width:66px; margin:auto;"><img src="<%=request.getAttribute("PUBLIC") %>/malltodo.merchant/images/007.png" width="66px" style="margin:auto;" /></div>
											<div class="youshi_big_title">完善的帮助文档</div>
											<div class="youshi_title">系统便捷使用保驾护航</div>
										</div>
									</td>
									<td width="25%">
										<div class="youshi_box">
											<div style="width:66px; margin:auto;"><img src="<%=request.getAttribute("PUBLIC") %>/malltodo.merchant/images/008.png" width="66px" style="margin:auto;" /></div>
											<div class="youshi_big_title">全方位售后保障</div>
											<div class="youshi_title">专业服务指导，专属客服答疑</div>
										</div>
									</td>
								</tr>
							</table>
							
							<div class="clear"></div>
						</div>
					</div>
				</div>
				<div class="zhegai"></div>
			</div>
			<div class="content step_box">
				<div class="content_box" style="background:#00a560">
					<div class="index_content">
						<div class="index_title" style="color:white">为什么选择MALLTODO</div>
						<div class="index_news_div">
							<table width="100%">
								<tr>
									<td width="50%">
										<div class="youshi_box">
											<div class="xuanzemall"><img src="<%=request.getAttribute("PUBLIC") %>/malltodo.merchant/images/009.png" /></div>
											<div class="xuanzetitle">专注网站领域</div>
											<div class="xuanzetext">专注于网站系统的研发，坚持精雕细琢，致力于为企业打造高品质网站系统</div>
											<div class="clear"></div>
										</div>
									</td>
									<td width="50%">
										<div class="youshi_box">
											<div class="xuanzemall"><img src="<%=request.getAttribute("PUBLIC") %>/malltodo.merchant/images/010.png" /></div>
											<div class="xuanzetitle">主流技术研发</div>
											<div class="xuanzetext">基于JAVA+MYSQL、JQuery等国际主流技术研发</div>
											<div class="clear"></div>
										</div>
									</td>
								</tr>
								<tr>
									<td width="50%">
										<div class="youshi_box">
											<div class="xuanzemall"><img src="<%=request.getAttribute("PUBLIC") %>/malltodo.merchant/images/011.png" /></div>
											<div class="xuanzetitle">标准开发规范</div>
											<div class="xuanzetext">始终坚持用严苛的技术标准打造软件品牌价值，深入软件开发的每一个细节</div>
											<div class="clear"></div>
										</div>
									</td>
									<td width="50%">
										<div class="youshi_box">
											<div class="xuanzemall"><img src="<%=request.getAttribute("PUBLIC") %>/malltodo.merchant/images/012.png" /></div>
											<div class="xuanzetitle">赋能企业发展</div>
											<div class="xuanzetext">服务上市企业100+，签约商业客户累计超过5000+，为10万+商家提供网站解决方案</div>
											<div class="clear"></div>
										</div>
									</td>
								</tr>
							</table>
							
							<div class="clear"></div>
						</div>
					</div>
				</div>
				<div class="zhegai"></div>
			</div>
			<div class="content step_box">
				<div class="content_box" style="background:orange">
					<div class="index_content">
						<div class="index_title" style="color:white">代理加盟</div>
						<div class="index_news_div" style="color: white;">
							<p>1、产品丰富多样：公司自主研发多种产品，加盟我们可获得公司旗下所有商品的代理权。</p>
							<p>2、低成本 高回报：超高的利润空间，让您的投资入超所值坐等收钱。润空间，让您的投资物超所值。</p>
							<p>3、完善的产品体系：采取“技术+运营+渠道”三位一体的创新服务模式，为品牌企业提供高品质的电商营销产品和服务，构建独特核心竞争优势。</p>
							<p>4、优秀服务：为渠道商提供专属的运营服务客服支持，帮助渠道商解决技术、产品、销售等市场问题迅速稳定高效的渠道销售团队。</p>
							<p>5、权益保障：优先处理和研发代理商反馈的需求定制研发和各种服务，确保代理商的需求优先权。</p>
							<p>6、资源共享：全面从产品，技术，服务，市场多维助推，满足加盟伙伴的市场及生态资源需求。</p>
							<p>业务联系电话：13598851835</p>
							<div class="clear"></div>
						</div>
					</div>
				</div>
				<div class="zhegai"></div>
			</div>
			<div class="content step_box">
				<div class="content_box" style="background:rgb(209, 3, 3)">
					<div class="index_content">
						<div class="index_title" style="color:white">联系我们</div>
						<div class="index_news_div" style="color: white;">
							<div id="allmap" style="height: 400px;"></div>
							<p>公司地址：河南省郑州高新技术产业开发区科学大道53号中原广告产业园7号楼2单元2层0132号</p>
							<p>业务经理：王经理</p>
							<p>业务联系电话：13598851835</p>
							<div class="clear"></div>
						</div>
					</div>
				</div>
				<div class="zhegai"></div>
			</div>
		</div>
		<div id="scroll_point">
			<div class="point_border point_select">
				<div class="point"></div>
			</div>
			<div class="point_border">
				<div class="point"></div>
			</div>
			<div class="point_border">
				<div class="point"></div>
			</div>
			<div class="point_border">
				<div class="point"></div>
			</div>
			<div class="point_border">
				<div class="point"></div>
			</div>
			<div class="point_border">
				<div class="point"></div>
			</div>
		</div>
		<script>
			//js参数
			var step = 0;
			var scroll_flag = 0;

			//动画特效
			function window_animate() {
				$(".point_border").removeClass("point_select");
				$(".point_border").eq(step).addClass("point_select");
				$(".step_box").eq(0).animate({
					"margin-top": (0 - step * window_height) + "px"
				}, "10", function() {
					scroll_flag = 0;
				})
				if(step == 3){
					$("#copyright").show();
				}else{
					$("#copyright").show();
				}
			}

			//背景图调整
			function box_img_resize() {
				for (var i = 0; i < $(".box_img").length; i = i + 1) {
					var img_width = $(".box_img").eq(i).width();
					var img_height = $(".box_img").eq(i).height();
					if ((window_width / window_height) > (img_width / img_height)) {
						$('.box_img').eq(i).css({
							'width': "100%",
							'height': 'auto',
							"margin-top": (0 - (window_width / img_width * img_height - window_height) / 2) + "px",
							"margin-left": "0px",
						});
					} else {
						$('.box_img').eq(i).css({
							'width': 'auto',
							'height': "100%",
							"margin-top": "0px",
							"margin-left": (0 - (window_height / img_height * img_width - window_width) / 2) + "px",
						});
					}
				}
			}
			
			function banner_resize(){
				var height = parseInt($(window).height());
				height = height - $("#header_box").height();
				$("#banner_box").height(height);
				$("#banner_box").css("margin-top", $("#header_box").height()+"px");
			}

			//页面调整
			function window_resize() {
				window_width = parseInt($(window).width());
				window_height = parseInt($(window).height());
				
				if(window_width < 768){
					return ;
				}
				
				//$("#header").width(window_width);
				$(".content").width(window_width);
				$(".content").height(window_height);
				$(".zhegai").fadeTo(10, 0.5);
				var pointer_height = $(".point_border").length * 46;
				$("#scroll_point").css({
					"margin-top": (window_height - pointer_height) / 2 + "px"
				})
				var bg1_main_height = (window_height - parseInt($("#header").height()));
				$("#form_box_div").css({
					"margin-top": ((bg1_main_height - 450) / 3) + "px"
				});
				$("#index_product").css({
					"margin-left":(window_width-$("#index_product").width())/2+"px",
					"margin-top":($("#header").height()+(window_height - $("#index_product").height() - $("#header").height())/2)+"px"
				})
				$(".index_content").css({
					"margin-top":($("#header").height()+(window_height - $(".index_content").height() - $("#header").height())/2)+"px"
				})
				box_img_resize();
				banner_resize();
			}
			window_resize();
			window_resize();
			$(window).resize(function() {
				window_resize();
				window_resize();
				$(".step_box").eq(0).css({
					"margin-top": (0 - step * window_height) + "px"
				});
			})

			//滑动框架
			$("#box").mousewheel(function(e) {
				
				window_width = parseInt($(window).width());
				window_height = parseInt($(window).height());
				
				if(window_width < 768){
					return ;
				}
				
				if (scroll_flag == 0) {
					scroll_flag = 1;
				} else {
					return;
				}
				if (e.deltaY == -1) {
					step = step + 1;
					if ($(".step_box").length - 1 < step) {
						step = $(".step_box").length - 1;
					}
				} else {
					step = step - 1;
					if (step < 0) {
						step = 0;
					}
				}
				window_animate();
			})

			//滚动点点击
			$(".point_border").click(function() {
				var index = $(this).index();
				step = index;
				window_animate();
			})
			
			$(".menu_a").click(function(){
				var index = $(this).index() - 1;
				index = $(".menu_a").length - index;
				step = index;
				window_animate();
			})
		</script>
		<script>
			$(".tedian_box").mouseenter(function(){
				$(this).hide();
				$(this).next('.tedian_box_yincang').show();
				$(this).next('.tedian_box_yincang').animate({height:'430px'});
			})
			$(".tedian_box_yincang").mouseleave(function(){
				var that = $(this);
				that.animate({height:'0px', display:'none'}, 500, function(){
					that.hide();
					that.prev('.tedian_box').show();
				});
			})
		</script>
		<script type="text/javascript">
			var map = new BMap.Map("allmap");
			map.setMapStyle({
				style: 'grayscale'
			});
			var point1 = new BMap.Point(113.58246901298108, 34.812438897485144);
			map.centerAndZoom(point1, 18);
			var marker1 = new BMap.Marker(point1);
			map.addOverlay(marker1);
			//点标注信息
			var opts1 = {
				width: 200,
				height: 100,
				title: "郑州掌勺信息技术有限公司",
			}
			var infoWindow1 = new BMap.InfoWindow("河南省郑州高新技术产业开发区科学大道53号中原广告产业园7号楼2单元2层0132号", opts1); // 创建信息窗口对象
			marker1.addEventListener("click", function() {
				map.openInfoWindow(infoWindow1, point1);
			});
			marker1.setAnimation(BMAP_ANIMATION_BOUNCE);
			map.enableScrollWheelZoom();
		</script>
		<div id="copyright" style="display:block">
		<div style="width:1000px; margin:auto">
		<div style="float:left">版权所有：郑州掌勺信息技术有限公司&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a target="_blank" href="https://beian.miit.gov.cn/">豫ICP备19044543号-2</a></div>
		<div style="float:right"><div style="width:300px;">
		 		<a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=41019702002751" style="display:inline-block;text-decoration:none;height:20px;line-height:20px;"><img src="<%=request.getAttribute("PUBLIC") %>/images/beian.png" style="float:left;"/><p style="float:left;height:20px;line-height:20px;margin: 0px 0px 0px 5px; color:white;">豫公网安备 41019702002820号</p></a>
		 		<a target="_blank" href="https://zzlz.gsxt.gov.cn/businessCheck/verifKey.do?showType=p&serial=91410100MA47Q53L7Y-SAIC_SHOW_10000091410100MA47Q53L7Y1601125015309&signData=MEQCIEeFoa4vCCT7hHJkKG5Ot3NZsXj0Wgwv/0Pfo6Ays5+UAiCWaZMXPnVqR0x3TwEzkPqj/0YYBh0mcii/PhHjELz1Qg==" style="display:inline-block;text-decoration:none;height:20px;line-height:20px;"><img src="<%=request.getAttribute("PUBLIC") %>/images/liangzhao.png" style="float:left;"/><p style="float:left;height:20px;line-height:20px;margin: 0px 0px 0px 5px; color:white;"></p></a>
		 	</div></div>
		<div class="clear"></div>
		</div>
		</div>
		<div style="display:none">
		    <script>
                var _hmt = _hmt || [];
                (function() {
                  var hm = document.createElement("script");
                  hm.src = "https://hm.baidu.com/hm.js?68e438e21eee77948dc43be94ed022ea";
                  var s = document.getElementsByTagName("script")[0]; 
                  s.parentNode.insertBefore(hm, s);
                })();
            </script>
		</div>
		
		<script>
		$(".menu_b").click(function(){
			if($("#form_box_div").css("display") == "none"){
				$(".point_border").eq(0).click();
				$("#form_box_div").fadeIn(1000);
			}else{
				$("#form_box_div").fadeOut(1000);
			}
		})
		</script>
		
		<!-- 宣传视频 -->
		<style>
		#mask{ width:100%; height:100%; position:fixed; left:0px; top:0px; background:black; opacity: 0.7; z-index: 99999;}
		#mask_close{ width:50px; height:50px; float:right; font-size:50px; color:white; text-align:center; line-height:50px; cursor:pointer;}
		#video_layer{ width:990; height:696px; margin:auto; position:fixed; z-index: 100000; background:white;}
		</style>
		<div id="mask">
			<div id="mask_close" class="icon-times"></div>
		</div>
		<div id="video_layer">
			<video autoplay="autoplay" id="_video" width="990px" height="696px" controls="controls" muted>
				<source src="http://www.malltodo.com/Public/images/video.mp4" type="video/mp4"></source>
			</video>
		</div>
		<script>
		$("#mask_close").click(function(){
			$("#mask").hide();
			$("#video_layer").remove();
		})
		function video_adjust(){
			var width = $(window).width();
			var height = $(window).height();
			var _width = 990;
			var _height = 696;
			$("#video_layer").css({"top":(height-_height)/2, "left":(width-_width)/2});
		}
		video_adjust();
		</script>
	</body>
</html>
