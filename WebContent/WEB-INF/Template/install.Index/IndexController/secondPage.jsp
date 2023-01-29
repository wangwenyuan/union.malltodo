<%@page import="install.Index.IndexController"%>
<%@page import="common.Functions"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.javatodo.core.tools.T"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>程序安装-MALLTODO智慧点餐系统 V1.0</title>
    <script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/js/jquery-1.12.4.min.js"></script>
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
        <strong style="font-size:18px;">环境检测</strong>
    </div>

    <div class="install_text">
        <table width="100%" cellpadding="0" cellspacing="0" class="main_table">
                <tr class="main_table_header"><td>参数</td><td>值</td></tr>
                <tr><td>服务器域名</td><td><%=request.getServerName() %></td></tr>
                <tr><td>服务器操作系统</td><td><%=System.getProperty("os.name") %></td></tr>
                <tr><td>java版本</td><td><%=System.getProperty("java.version") %></td></tr>
                <tr><td>系统安装目录</td><td><%=this.getServletContext().getRealPath("/") %></td></tr>
        </table>
        <div style="height:20px;"></div>
        <table width="100%" cellpadding="0" cellspacing="0" class="main_table">
                <tr class="main_table_header"><td>目录名</td><td>读取权限</td><td>写入权限</td></tr>
                <tr><td>uploadFiles</td><td><%=IndexController.dirCanRead(this.getServletContext().getRealPath("/")+"uploadFiles") %></td><td><%=IndexController.dirCanWrite(this.getServletContext().getRealPath("/")+"uploadFiles") %></td></tr>
                <tr><td>uploads</td><td><%=IndexController.dirCanRead(this.getServletContext().getRealPath("/")+"uploads") %></td><td><%=IndexController.dirCanWrite(this.getServletContext().getRealPath("/")+"uploads") %></td></tr>
                <tr><td>json</td><td><%=IndexController.dirCanRead(this.getServletContext().getRealPath("/")+"json") %></td><td><%=IndexController.dirCanWrite(this.getServletContext().getRealPath("/")+"json") %></td></tr>
                <tr><td>WEB-INF/Runtime</td><td><%=IndexController.dirCanRead(this.getServletContext().getRealPath("/")+"WEB-INF/Runtime") %></td><td><%=IndexController.dirCanWrite(this.getServletContext().getRealPath("/")+"WEB-INF/Runtime") %></td></tr>
                <tr><td>WEB-INF/Template</td><td><%=IndexController.dirCanRead(this.getServletContext().getRealPath("/")+"WEB-INF/Template") %></td><td><%=IndexController.dirCanWrite(this.getServletContext().getRealPath("/")+"WEB-INF/Template") %></td></tr>
                <tr><td>WEB-INF/config</td><td><%=IndexController.dirCanRead(this.getServletContext().getRealPath("/")+"WEB-INF/config") %></td><td><%=IndexController.dirCanWrite(this.getServletContext().getRealPath("/")+"WEB-INF/config") %></td></tr>
        </table>
    </div>

    <div class="install_btn">
        <div id="tijiao" style="float:left" onclick="window.location.href = '<%=T.U("Index/Index/index", "index.jsp", request) %>'">
            上一步
        </div>
        <div id="tijiao" style="float:right" onclick="window.location.href = '<%=T.U("Index/Index/third", "index.jsp", request) %>'">
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
</script>
</body>
</html>