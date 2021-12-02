<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.javatodo.core.tools.T"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>程序安装-MALLTODO电商联盟系统 V1.0</title>
    <script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/js/layer.js"></script>
    <script>
        if (window.top != window) {
            window.top.location.href = document.location.href;
        }
    </script>
    <style>
        html,body {
            width: 100%;
            height: 100%;
            background: rgb(24 159 146);
            background-repeat: no-repeat;
            background-size: cover;
            overflow: hidden;
        }
        #login {
            position: fixed;
            background: #FFFFFF;
            width: 1000px;
            height: 700px;
            border-radius: 5px;
            -moz-box-shadow:0px 0px 8px #F0F0F0;
            -webkit-box-shadow:0px 0px 8px #F0F0F0;
            box-shadow:0px 0px 8px #F0F0F0;
        }
        .logo{
            width: 1000px;
            height: 80px;
            margin: 10px auto;
            text-align: center;
            line-height: 80px;
            color: rgb(24,159,146);
            background: #FFFFFF;
        }
        .logo img {
            width: 100%;
            height: auto;
            display: block
        }
        .install_text{
            width: 860px;
            height: 440px;
            overflow:hidden;
            overflow-y:scroll;
            padding-top: 10px;
            margin:auto;
            font-size:14px;
            border: 1px solid #EFEFEF;
			padding: 20px;
        }
        .install_btn{
        	padding:20px;
        }
        .input_text {
            width: 300px;
            height: 38px;
            background: #FFF;
            border: 1px solid;
            border-color: rgb(221, 221, 221);
            margin-left: 55px;
            padding-left: 10px;
            padding-right: 10px;
            line-height: 40px;
        }
        #tijiao {
            cursor: pointer;
            border:1px solid #0781C7;
            text-align:center;
            width:320px;
            margin:auto;
            line-height:40px;
            color:#FFF;
            background:rgb(24,159,146);
        }
        #tijiao:hover{
            background: rgb(14,149,136);
        }
        #yanzheng {
            cursor: pointer;
            float:left;
            margin-left:5px;
        }
        .main_table {
			background: #E2E2E2;
			width: auto;
			width: 100%;
			font-size: 12px;
			line-height: 20px;
			border: #E2E2E2 1px solid;
			border-bottom: none;
			color: #666;
		}
		
		.main_table td {
			background: #FFF;
			padding-top: 9px;
			padding-bottom: 9px;
			padding-left: 15px;
			padding-right: 15px;
			border-bottom: #E2E2E2 1px solid;
		}
		
		.main_table td a {
			color: #09C;
			margin-right: 5px;
			text-decoration: none;
		}
		
		.main_table_header td {
			background: #F2F2F2;
			font-weight: bold;
		}
		
		.main_title {
			width: 500px;
			height: 30px;
			float: left;
			border-left: #88B7E0 4px solid;
			font-size: 25px;
			line-height: 30px;
			padding-left: 10px;
			margin-bottom: 10px;
		}
		
		.main_button {
			height: 25px;
			line-height: 25px;
			display: block;
			text-align: center;
			color: #FFF;
			background: #09C;
			border: #09C 2px solid;
			float: right;
			padding-left: 10px;
			padding-right: 10px;
			font-size: 14px;
			text-decoration: none;
			cursor: pointer;
		}
		
		.main_button:hover {
			background: #28B5D6;
		}
    </style>
</head>
<body>
<div id="login">
    <div class="logo">
        <strong style="font-size:18px;">阅读许可协议</strong>
    </div>

    <div class="install_text">
        <p>版权所有 (R) MALLTODO 保留所有权利。 </p>
				<p>感谢您选择MALLTODO电商联盟系统（以下简称MALLTODO），MALLTODO由郑州掌勺信息技术有限公司开发，是目前国内强大、稳定的导购返利类网站建设解决方案之一，基于 JAVA + MySQL   的技术开发，全部源码开放。</p>
				<p>MALLTODO 的官方网址是： <a href="http://www.malltodo.com" target="_blank">www.malltodo.com</a></p>
				<p>为了使你正确并合法的使用本软件，请你在使用前务必阅读清楚下面的协议条款：</p>
			<strong>一、本授权协议适用且仅适用于 MALLTODO V1.x 版本，MALLTODO官方对本授权协议的最终解释权。</strong>
			<strong>二、协议许可的权利 </strong>
				<p>1、您可以在完全遵守本最终用户授权协议的基础上，将本软件应用于非商业用途，而不必支付软件版权授权费用。 </p>
				<p>2、您可以在协议规定的约束和限制范围内修改 MALLTODO 源代码或界面风格以适应您的网站要求。 </p>
				<p>3、您拥有使用本软件构建的网站全部内容所有权，并独立承担与这些内容的相关法律义务。 </p>
				<p>4、获得商业授权之后，您可以将本软件应用于商业用途，同时依据所购买的授权类型中确定的技术支持内容，自购买时刻起，在技术支持期限内拥有通过指定的方式获得指定范围内的技术支持服务。商业授权用户享有反映和提出意见的权力，相关意见将被作为首要考虑，但没有一定被采纳的承诺或保证。 </p>
			<strong>二、协议规定的约束和限制 </strong>
				<p>1、未获商业授权之前，不得将本软件用于商业用途（包括但不限于企业网站、经营性网站、以营利为目的或实现盈利的网站）。购买商业授权请登录   <a href="http://www.malltodo.com" target="_blank">www.malltodo.com</a> 了解最新说明。</p>
				<p>2、未经官方许可，不得对本软件或与之关联的商业授权进行出租、出售、抵押或发放子许可证。</p>
				<p>3、不管你的网站是否整体使用 MALLTODO ，还是部分栏目使用 MALLTODO，在你使用了 MALLTODO 的网站主页上必须加上 MALLTODO   官方网址(<a href="http://www.malltodo.com" target="_blank">www.malltodo.com</a>)的链接。</p>
				<p>4、未经官方许可，禁止在 MALLTODO   的整体或任何部分基础上以发展任何派生版本、修改版本或第三方版本用于重新分发。</p>
				<p>5、如果您未能遵守本协议的条款，您的授权将被终止，所被许可的权利将被收回，并承担相应法律责任。 </p>
			<strong>三、有限担保和免责声明 </strong>
				<p>1、本软件及所附带的文件是作为不提供任何明确的或隐含的赔偿或担保的形式提供的。 </p>
				<p>2、用户出于自愿而使用本软件，您必须了解使用本软件的风险，在尚未购买产品技术服务之前，我们不承诺对免费用户提供任何形式的技术支持、使用担保，也不承担任何因使用本软件而产生问题的相关责任。 </p>
				<p>3、电子文本形式的授权协议如同双方书面签署的协议一样，具有完全的和等同的法律效力。您一旦开始确认本协议并安装   MALLTODO，即被视为完全理解并接受本协议的各项条款，在享有上述条款授予的权力的同时，受到相关的约束和限制。协议许可范围以外的行为，将直接违反本授权协议并构成侵权，我们有权随时终止授权，责令停止损害，并保留追究相关责任的权力。</p>
				<p>4、如果本软件带有其它软件的整合API示范例子包，这些文件版权不属于本软件官方，并且这些文件是没经过授权发布的，请参考相关软件的使用许可合法的使用。</p>
				<p><b>协议发布时间：</b> 2021年09月01日</p>
				<p><b>版本最新更新：</b> 2021年09月01日 By malltodo.com</p>
    </div>
    
    <div style="width:900px; margin:auto; height:20px; line-height:20px; font-size:14px; padding-top:10px;"><input type="checkbox" id="xieyi" name="xieyi" /> 我已经阅读并同意此协议</div>

    <div class="install_btn">
        <div id="tijiao">
            下一步
        </div>
    </div>

</div>
<script>
    //固定login位置
    function size() {
        var loginwidth = $(window).width();
        var loginheight = $(window).height();
        var width1 = parseInt((loginwidth - 1000) / 2);
        var height1 = parseInt((loginheight - 700) / 2);
        $('#login').css({'left': width1, 'top': height1});
    }
    function loginresize() {
        $(window).resize(function () {
            size();
        })
    }
    size();
    loginresize();
    //登录提交
    $('#tijiao').click(function () {
        if($("#xieyi").prop("checked")){
        	window.location.href = "<%=T.U("Index/Index/second", "index.jsp") %>";
        }else{
        	layer.msg("您必须同意软件许可协议才能安装！")
        	return ;
        }
    });
</script>
</body>
</html>