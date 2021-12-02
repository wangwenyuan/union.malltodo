<%@page import="install.Index.IndexController"%>
<%@page import="common.Functions"%>
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
            background: #FFF;
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
            background: #FFF;
        }
        .logo img {
            width: 100%;
            height: auto;
            display: block
        }
        .install_text{
            width: 860px;
            height: 460px;
            overflow:hidden;
            overflow-y:scroll;
            padding-top: 10px;
            margin:auto;
            font-size:14px;
            border: 1px solid #EFEFEF;
			padding: 20px;
        }
        .install_btn{
        	width:340px;
        	padding:20px;
        	margin:auto;
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
            width:160px;
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
        .input_text{ width:250px; height:30px; padding-left:5px; padding-right:5px; border: 1px soild #EFEFEF; margin-left:0px;}
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
        <strong style="font-size:18px;">参数配置</strong>
    </div>

    <div class="install_text">
    	<form method="post" action="" id="html_form">
        <table width="100%" cellpadding="0" cellspacing="0" class="main_table">
                <tr class="main_table_header"><td>参数</td><td>值</td></tr>
                <tr><td>数据库主机：</td><td><input type="text" name="db_host" class="input_text" value="localhost" /></td></tr>
                <tr><td>数据库用户：</td><td><input type="text" name="db_username" class="input_text" value="" /></td></tr>
                <tr><td>数据库密码：</td><td><input type="password" name="db_password" class="input_text" value="" /></td></tr>
                <tr><td>数据库名称：</td><td><input type="text" name="db_name" class="input_text" value="" /></td></tr>
                <tr><td>数据库端口：</td><td><input type="text" name="db_port" class="input_text" value="3306" /></td></tr>
                <tr><td>管理员用户名：</td><td><input type="text" name="admin_username" class="input_text" value="" /></td></tr>
                <tr><td>管理员密码：</td><td><input type="password" name="admin_password" class="input_text" value="" /></td></tr>
        </table>
        </form>
    </div>

    <div class="install_btn">
        <div id="tijiao" style="float:left" onclick="window.location.href = '<%=T.U("Index/Index/second", "index.jsp") %>'">
            上一步
        </div>
        <div id="tijiao" style="float:right" onclick="set_db()">
            下一步
        </div>
        <div style="clear:both;"></div>
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
    
    var flag = false;
    function set_db(){
    	if(flag){
    		return ;
    	}else{
    		flag = true;
    	}
    	$.ajax({
            async: true,
            type: "POST",
            dataType: "json",
            url: "",
            data: $("#html_form").serialize(),
            success: function (data) {
                layer.msg(data.info, {
                    time: 2000
                }, function () {
                	if(data.status == 0){
                		window.location.reload();
                	}else{
                		if ((data.url).trim() != "") {
                            document.location.href = (data.url).trim()
                        }
                	}
                })
            },
            error: function () {
                jieguo = 'error';
                layer.msg("网络错误");
            }
        });
    }
</script>
</body>
</html>