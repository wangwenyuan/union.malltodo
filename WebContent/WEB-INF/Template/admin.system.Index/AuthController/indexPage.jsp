<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="common.AdminMenuConfig"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="common.Common"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../../admin.home.Index/IndexController/sub_header.jsp"%>
<%
JSONObject info = (JSONObject)request.getAttribute("info");
JSONArray role_list = (JSONArray)request.getAttribute("role_list");
%>

<form method="post" action="" id="html_form">
    <table width="100%" cellpadding="0" cellspacing="0" class="small_main_table">
        
        <%
        JSONArray list = AdminMenuConfig.getAllMenu();
        for(Integer i=0; i<list.size(); i=i+1){
        	JSONObject menu = list.getJSONObject(i);
            for (Map.Entry entry : menu.entrySet()) {
            	String k = entry.getKey().toString();//类似于Merchant
            	JSONObject v = menu.getJSONObject(k);
            	if(k.equals("_name") || k.equals("_isshow")||k.equals("_auth")||k.equals("_icon")){
            		continue;
            	}
            	for (Map.Entry entry2 : v.entrySet()) {
            		String kk = entry2.getKey().toString();
            		if(kk.equals("_name") || kk.equals("_isshow")||kk.equals("_auth")||kk.equals("_icon")){
                		continue;
                	}
            		JSONObject vv = v.getJSONObject(kk);
            		out.println("<tr><td align=\"right\" width=\"100px\" valign=\"top\">"+vv.getString("_name")+"：</td><td align=\"left\">");
            		
            		for (Map.Entry entry3 : vv.entrySet()) {
                		String kkk = entry3.getKey().toString();
                		String vvv = vv.getString(kkk);
                		if(kkk.equals("_isshow")||kkk.equals("_auth")||kkk.equals("_icon")){
                    		continue;
                    	}
                		if(kkk.equals("_name")){
                			String checked = "";
                			if(role_list.contains(k+"+"+kk)){
                				checked = "checked = \"checked\"";
                			}
                			out.println("<input type=\"checkbox\" name=\"r[]\" value=\"" +k+"+"+kk+"\" class=\""+k+" "+k+"_"+kk+"\" title=\""+vvv+"\" data-level=\"2\" "+checked+" >全选<br />");
                		}else{
                			String checked = "";
                			if(role_list.contains(k+"+"+kk+"+"+kkk)){
                				checked = "checked = \"checked\"";
                			}
                			out.println("<input type=\"checkbox\" name=\"r[]\" value=\""  +k+"+"+kk+"+"+kkk+"\" class=\""+k+" "+k+"_"+kk+" "+k+"_"+kk+"_"+kkk+"\" title=\""+vvv+"\" data-level=\"3\" "+checked+" >"+vvv+"&nbsp;&nbsp;");
                		}
            		}
            		out.println("</td></tr>");
            	}
            }
        }
        %>
        <tr><td></td><td><input type="button" class="anniu" id="add" value="提交" /></td></tr>
    </table>
</form>
<script>
$("input[type='checkbox']").change(function(){
	var zhi = $(this).val();
	var zhi_arr = new Array();
	zhi_arr = zhi.split('+');
	if(zhi_arr.length == 2){
		if($(this).prop('checked')){
			$('.'+zhi_arr[0]+"_"+zhi_arr[1]).prop('checked', true);
		}else{
			$('.'+zhi_arr[0]+"_"+zhi_arr[1]).prop('checked', false);
		}
	}
})
</script>
<%@include file="../../admin.home.Index/IndexController/sub_bottom.jsp"%>