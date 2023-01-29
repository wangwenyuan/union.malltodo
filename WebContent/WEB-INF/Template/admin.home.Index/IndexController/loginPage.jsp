<%@page import="common.Functions"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.javatodo.core.tools.T"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>管理后台</title>
    <%=Functions.header(T.getHost(request)) %>
    <script>
        if (window.top != window) {
            window.top.location.href = document.location.href;
        }
    </script>
    <style>
        html,body {
            width: 100%;
            height: 100%;
           background-color: rgb(209, 3, 3);
            background-repeat: no-repeat;
            background-size: cover;
            overflow: hidden;
        }
        #login {
            position: fixed;
            background: #FFF;
            width: 420px;
            height: 410px;
            border-radius: 5px;
            -moz-box-shadow:0px 0px 8px #F0F0F0;
            -webkit-box-shadow:0px 0px 8px #F0F0F0;
            box-shadow:0px 0px 8px #F0F0F0;
        }
        
        #logo_img{ width:580px; height:410px; position: fixed; background-image: url('<%=request.getAttribute("PUBLIC") %>/images/logo_img.png');}
        
        .logo{
		    font-size: none;
		    line-height: none;
		    padding-left: none;
		    background: none;
		    float:none;
		    overflow:none;
            width: 420px;
            height: 110px;
            margin: 10px auto;
            text-align: center;
            line-height: 110px;
            color: rgb(209, 10, 10);
        }
        .logo img {
            width: 100%;
            height: auto;
            display: block
        }
        .login-input{
            width: 430px;
            height: 40px;
            padding-top: 10px;
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
            border:1px solid rgb(209, 10, 10);
            text-align:center;
            width:320px;
            margin:auto;
            line-height:40px;
            color:#FFF;
            background:rgb(209, 3, 3);
        }
        #tijiao:hover{
            background: rgb(209, 10, 10);
        }
        
        #yanzheng {
            cursor: pointer;
            float:left;
            margin-left:5px;
        }
    </style>
</head>
<body onkeydown="keydown(event)">

<div id="logo_img"></div>

<div id="login">
    <div class="logo">
        <strong style="font-size:18px;">管理后台</strong>
    </div>

    <div class="login-input">
        <input type="text" class="input_text" name="username" placeholder="用户名"/>
    </div>

    <div class="login-input">
        <input type="password" class="input_text" name="password" placeholder="密码"/>
    </div>

    <div class="login-input">
        <input type="text" class="input_text" name="yanzheng" placeholder="验证码" style="width:200px; float:left"/>
        <img id="yanzheng" src="<%=T.U("Index/Index/verify", "admin.jsp", request) %>" height="41px;" width="95px;"/>
    </div>

    <div class="login-input">
        <div id="tijiao">
            登录
        </div>
    </div>

</div>
<script>
    //固定login位置
    function size() {
        var loginwidth = $(window).width();
        var loginheight = $(window).height();
        var width1 = parseInt((loginwidth - 420) / 2);
        var height1 = parseInt((loginheight - 410) / 2);
        var width2 = width1 - 290;
        width1 = width1 + 290;
        $('#logo_img').css({'left': width2, 'top': height1});
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
        var shuju = {
            username: $('input[name=username]').val(),
            password: $('input[name=password]').val(),
            verify: $('input[name=yanzheng]').val(),
        }
        if (!shuju.username) {
            layer.msg('用户名不能为空');
            return '';
        }
        if (!shuju.password) {
            layer.msg('密码不能为空');
            return '';
        }
        if (!shuju.verify) {
            layer.msg('验证码不能为空');
            return '';
        }
        var lianjie = '';
        $.ajax({
            async: true,
            type: "POST",
            dataType: "json",
            url: lianjie,
            data: shuju,
            success: function (data) {
            	if (data["status"] == 1) {
                    layer.msg(data["info"], {
                        time: 2000
                    }, function () {
                        if (data["url"] != "") {
                            document.location.href = data["url"];
                        }
                    })
                } else {
                    layer.msg(data["info"]);
                    qiehuan();
                }
            },
            error: function () {
            	layer.msg("网络错误");
            	qiehuan();
            }
        });
    });
    //刷新验证码
    $('#yanzheng').click(function () {
        qiehuan();
    })
    function qiehuan() {
        $('#yanzheng').attr('src', '');
        var lianjie = "<%=T.U("Index/Index/verify", "admin.jsp", request) %>";
        lianjie = lianjie + '&time=' + Math.random();
        $('#yanzheng').attr('src', lianjie);
    }
    //enter提交
    function keydown(event) {
        if (event.keyCode == 13) {
            $("#tijiao").click();
            return false;
        }
    }
</script>
</body>
</html><!--The copyright of this system belongs to Zhengzhou Zhangshao Information Technology Co., Ltd--><!--All rights reserved Powered by MALLTODO, Website: www.malltodo.com-->