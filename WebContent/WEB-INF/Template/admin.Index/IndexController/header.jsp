<%@page import="common.AdminMenuConfig"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.javatodo.core.tools.T"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理后台</title>
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("PUBLIC") %>/admin/css/css.css"/>
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/js/jquery.slimscroll.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("PUBLIC") %>/admin/css/pintuer.css" />
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/js/jquery.cookie.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("PUBLIC") %>/admin/css/zTreeStyle/zTreeStyle.css" />
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/js/jquery.ztree.all.min.js"></script>
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/js/layer.js"></script>
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/js/js.js"></script>
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/js/http.js"></script>
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/js/drop.js"></script>
<link rel="stylesheet" href="<%=request.getAttribute("PUBLIC") %>/admin/editor/themes/default/default.css" />
<link rel="stylesheet" href="<%=request.getAttribute("PUBLIC") %>/admin/editor/plugins/code/prettify.css" />
<script charset="utf-8" src="<%=request.getAttribute("PUBLIC") %>/admin/editor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="<%=request.getAttribute("PUBLIC") %>/admin/editor/lang/zh-CN.js"></script>
<script charset="utf-8" src="<%=request.getAttribute("PUBLIC") %>/admin/editor/plugins/code/prettify.js"></script>
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/admin/jedate/jquery.jedate.js"></script>
<link type="text/css" rel="stylesheet" href="<%=request.getAttribute("PUBLIC") %>/admin/jedate/skin/jedate.css">

<script src="<%=request.getAttribute("PUBLIC") %>/ui-c/ui-c.js"></script>
<link href="<%=request.getAttribute("PUBLIC") %>/ui-c/form/form.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getAttribute("PUBLIC") %>/ui-c/form/select.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getAttribute("PUBLIC") %>/ui-c/form/radio.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getAttribute("PUBLIC") %>/ui-c/form/checkbox.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getAttribute("PUBLIC") %>/ui-c/form/open.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/ui-c/form/form.js"></script>
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/ui-c/form/select.js"></script>
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/ui-c/form/radio.js"></script>
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/ui-c/form/checkbox.js"></script>
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/ui-c/form/open.js"></script>
</head>

<body>
<%
Map<String, String> input = (HashMap)request.getAttribute("input");
%>
<div id="main_content">
  <table width="100%" cellpadding="0" cellspacing="0">
    <tr>
      <td colspan="2"><div class="header">
          <div class="logo"><strong id="logo_name">MALLTODO</strong></div>
          <div class="top_menu"><a href="<%=T.U("Index/Index/index", "admin.jsp")%>">工作面板</a></div>
          <div class="header_right">当前管理员： <a href="javascript:shop.sub_window('修改资料', '<%=T.U("SystemSet/Admin/material", "admin.jsp") %>')"><%=request.getSession().getAttribute("admin_name").toString() %></a> &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:sign_out()">退出</a></div>
        <div class="clear"></div>
        </div></td>
    </tr>
    
<script>
function sign_out(){
	layer.msg('您确定要退出？', {
        time: 0,
        btn: ['确定', '取消'],
        yes: function (index) {
            layer.close(index);
            var url = "<%=T.U("Index/Index/signOut", "admin.jsp") %>";
    		$.ajax({
    			async : true,
    			type : "POST",
    			dataType : "json",
    			url : url,
    			data : {
    			},
    			success : function(data){
    				if(data["url"]){
    					window.location.href = data["url"];
    				}
    			},
    			error : function(){
    				ret = 'error';
    			}
    		});
        }
    });
}
</script>
    
    <tr>
      <td id="zuoce" valign="top"><div class="zuo" id="zuo">
          <div class="zuo_kuang_tou">
            <div> <font id="zuo_main_lanmu">电商联盟</font> <span class="icon-arrow-circle-left zuo_main_icon"></span> </div>
          </div>
          <div class="clear"></div>
          <div class="zuo_kuang">
<%
JSONObject menu = AdminMenuConfig.getMenu();
for (Map.Entry entry : menu.entrySet()) {
	String key = entry.getKey().toString();//类似于Merchant
	JSONObject subMenu = menu.getJSONObject(key);
	if(subMenu.getBooleanValue("_isshow")){
		out.print("<div class=\"zuo_ul\"><div class=\"zuo_tou ul_guanbi\">"+subMenu.getString("_name")+"</div>");
	}else{
		continue ;
	}
	for(Map.Entry entry_key:subMenu.entrySet()){
		String k = entry_key.getKey().toString();//类似于Index
		if(k.equals("_name")||k.equals("_isshow") || k.equals("_auth") || k.equals("_icon")){
			continue ;
		}else{
	    	JSONObject subSubMenu = subMenu.getJSONObject(k);
	    	if(!subSubMenu.getBooleanValue("_isshow")){
	    		continue;
	    	}
	    	out.print("<div class=\"zuo_li "+key+"_"+k+"\" data-url='"+T.U(key+"/"+k+"/index", "admin.jsp")+"'>"+subSubMenu.getString("index")+"</div>");
	    }
	}
	if(subMenu.getBooleanValue("_isshow")){
		out.print("</div>");
	}
}
%>
          </div>
        </div>
        <div class="zuo_xiao" id="zuo_xiao">
          <div class="zuo_kuang_tou" style="width:50px; height:45px;"> <span class="icon-arrow-circle-right zuo_main_icon"></span> </div>
          <div class="clear"></div>
          <div class="zuo_kuang">
<%
for (Map.Entry entry : menu.entrySet()) {
	String key = entry.getKey().toString();//类似于Merchant
	JSONObject subMenu = menu.getJSONObject(key);
	if(subMenu.getBooleanValue("_isshow")){
		out.print("<div class=\"small_div\"><span class=\""+subMenu.getString("_icon")+" small_tubiao\"></span><div class=\"small_nei_div\"><div class=\"small_a_div small_head_div\">"+subMenu.getString("_name")+"</div>");
	}else{
		continue ;
	}
	for(Map.Entry entry_key:subMenu.entrySet()){
		String k = entry_key.getKey().toString();//类似于Index
		if(k.equals("_name")||k.equals("_isshow") || k.equals("_auth") || k.equals("_icon")){
			continue ;
		}else{
	    	JSONObject subSubMenu = subMenu.getJSONObject(k);
	    	if(!subSubMenu.getBooleanValue("_isshow")){
	    		continue;
	    	}
	    	out.print("<div class=\"small_a_div\"><a href='"+T.U(key+"/"+k+"/index", "admin.jsp")+"'>"+subSubMenu.getString("index")+"</a></div>");
	    }
	}
	if(subMenu.getBooleanValue("_isshow")){
		out.print("<div class=\"clear\"></div></div></div>");
	}
}
%>
          </div>
        </div></td>
      <td id="youce" valign="top"><div class="you" id="you">
      <div id="you_content">