<%@page import="admin.home.WebSite.IndexController"%>
<%@page import="common.Common"%>
<%@page import="common.Functions"%>
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
<%=Functions.header(T.getHost(request)) %>
<style>
.top_menu{ width:470px;}
.tab_website .ui-c-element{ width:100%;}
</style>
</head>

<body>
<%
Map<String, String> _input = (HashMap<String, String>)request.getAttribute("input");
Map<String, Object> input = Functions.I_TO_MAP(_input);
String menu_category_name = Functions.getMenuCategoryName(request);
String menu_category_show = Functions.getMenuCategoryShow(request);
%>
<div id="main_content">
  <table width="100%" cellpadding="0" cellspacing="0">
    <tr>
      <td colspan="2"><div class="header">
          <div class="logo"><strong id="logo_name">MALLTODO</strong></div>
          <div class="top_menu"><%@include file="menu.jsp"%></div>
          
          <%
          if(menu_category_name.equals("home")){
          %>
          <div class="tab_website" style="float:left; width:350px;">
          	<div style="width:80px; float:left; font-size:14px; line-height:50px;">当前站点：</div><div style="width:240px; padding-top:10px; float:left;"><%=Common.template.htmlWidget("select_widget", "current_website_id", request.getSession().getAttribute("website_id").toString(), IndexController.getWebSiteJSONString())%></div>
          	<script>
          		$("#current_website_id").change(function(){
          			http.post("<%=T.U("WebSite/Index/switch_websites", "admin.home.jsp", request) %>", {"website_id":$(this).val()}, function(data){
          				layer.msg(data["info"], {
                            time: 2000
                        }, function () {
                            window.location.reload();
                        })
          			});
          		})
          	</script>
          </div>
          <%
          }
          %>
          
<style>
@media screen and (max-width:1290px) {
	.header_right{ display:none;}
}
</style>      
          
          <%
          if(request.getSession().getAttribute("admin_id").toString().equals("master")){
          %>
          	<div class="header_right">当前管理员： <%=request.getSession().getAttribute("admin_name").toString() %> &nbsp;&nbsp;&nbsp;&nbsp;<a target="_blank" href="/">官网首页</a> &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:sign_out()">退出</a></div>
          <%	  
          }else{
          %>
          	<div class="header_right">当前管理员： <a href="javascript:malltodoJs.sub_window('修改资料', '<%=T.U("SystemSet/Admin/material", "admin.jsp", request) %>')"><%=request.getSession().getAttribute("admin_name").toString() %></a> &nbsp;&nbsp;&nbsp;&nbsp;<a target="_blank" href="/">官网首页</a> &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:sign_out()">退出</a></div>
          <%
          }
          %>
          
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
            var url = "<%=T.U("Index/Index/signOut", "admin.jsp", request) %>";
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

$(".admin_<%=menu_category_name %>").css({"color":"#09C", "font-weight":"bold"});
</script>
    
    <tr>
      <td id="zuoce" valign="top"><div class="zuo" id="zuo">
          <div class="zuo_kuang_tou">
            <div> <font id="zuo_main_lanmu"><%=menu_category_show %></font> <span class="icon-arrow-circle-left zuo_main_icon"></span> </div>
          </div>
          <div class="clear"></div>
          <div class="zuo_kuang">
<%
JSONObject menu = AdminMenuConfig.getMenu(menu_category_name);
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
	    	out.print("<div class=\"zuo_li "+key+"_"+k+"\" data-url='"+T.U(key+"/"+k+"/index", "admin."+menu_category_name+".jsp", request)+"'>"+subSubMenu.getString("index")+"</div>");
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
	    	out.print("<div class=\"small_a_div\"><a href='"+T.U(key+"/"+k+"/index", "admin."+menu_category_name+".jsp", request)+"'>"+subSubMenu.getString("index")+"</a></div>");
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