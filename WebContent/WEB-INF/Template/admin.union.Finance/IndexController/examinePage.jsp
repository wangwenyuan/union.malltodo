<%@page import="com.alibaba.fastjson.JSON"%>
<%@page import="common.database.WITHDRAWALS"%>
<%@page import="common.database.MEMBER"%>
<%@page import="common.database.ADMIN"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="common.Common"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../../admin.home.Index/IndexController/sub_header.jsp"%>
<%
Map<String, Object>map = new HashMap();
map.put("1", "通过");
map.put("-1", "不通过");
JSONObject info = (JSONObject)request.getAttribute("info");
%>

<form method="post" action="" id="html_form">
    <table width="100%" cellpadding="0" cellspacing="0" class="small_main_table">
    	<tr><td align="right" width="150px">用户名：</td><td align="left"><%=info.getString(MEMBER.username) %> </td></tr>
    	<tr><td align="right" width="150px">金额：</td><td align="left"><%=info.getString(WITHDRAWALS.money) %> </td></tr>
        <tr><td align="right" width="150px">审核状态：</td><td align="left"><%=Common.template.htmlWidget("select_widget", WITHDRAWALS.examine_status, "1", JSON.toJSONString(map)) %></td></tr>
        <tr id="a2" style="display:none"><td align="right" width="150px">不通过原因：</td><td align="left"><%=Common.template.htmlWidget("textarea_widget", WITHDRAWALS.no_pass_reason, "") %></td></tr>
        <tr><td></td><td><input type="button" class="anniu" id="add1" value="提交" /> <input type="hidden" id="add"> </td></tr>
    </table>
</form>

<script>

$('#add1').click(function(){
	var examine_status = $('#examine_status').val();
	var no_pass_reason = $('#no_pass_reason').val();
	if(examine_status == -1){
		if(no_pass_reason == ""){
			layer.msg('不通过原因不能为空');
			return ;
		}else{
			$('#add').click();
		}
	}
	if(examine_status == 1){
		if(confirm('您确定同意该会员提现？提现金额将打进该会员微信余额')){
			$('#add').click();
		}
	}
})

$('#examine_status').change(function(){
	var examine_status_val = $('#examine_status').val();
	if(examine_status_val == 1){
		$('#a2').hide();
	}
	if(examine_status_val == -1){
		$('#a2').show();
	}
})
</script>
<%@include file="../../admin.home.Index/IndexController/sub_bottom.jsp"%>