<%@page import="common.database.UNION_ORDER"%>
<%@page import="common.database.WITHDRAWALS"%>
<%@page import="common.database.MEMBER"%>
<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="header.jsp"%>
            <div style="padding:15px;">
            <script src="<%=request.getAttribute("PUBLIC").toString() %>/js/echarts.js"></script>
<style>
	body{ font-size:14px;}
	.index_main {
		width: auto;
		height: auto;
	}

	.main_left {
		width: 50%;
		height: auto;
		float: left;
	}

	.main_right {
		width: 50%;
		height: auto;
		float: right;
	}

	.box_div_6,
	.box_div_3,
	.box_div_2,
	.box_div_1 {
		border: #EFEFEF 1px solid;
		border-radius: 2px;
		background: #FFF;
		padding: 20px;
		float: left;
		margin-right: 10px;
		margin-bottom: 10px;
	}

	.div_icon {
		width: 60px;
		height: 48px;
		padding-top:12px;
		border-radius: 30px;
		line-height: 65px;
		text-align: center;
		overflow: hidden;
		float: left;
		color: #FFF;
	}

	.box_div {
		border: #EFEFEF 1px solid;
		border-radius: 2px;
		background: #FFF;
		padding: 15px;
		float: left;
		margin-right: 10px;
		margin-bottom: 10px;
		width: auto;
	}

	.box_div_title {
		width: auto;
		height: 35px;
		border-bottom: #EFEFEF 1px solid;
		overflow: hidden;
	}

	.box_div_icon {
		width: 30px;
		height: 25px;
		padding-top:5px;
		border-radius: 15px;
		line-height: 33px;
		text-align: center;
		overflow: hidden;
		float: left;
		color: #FFF;
	}

	.box_clear {
		clear: both;
	}

	.span_btn {
		float: right;
		margin-left: 10px;
		color: #333;
		line-height: 30px;
		cursor: pointer;
	}

	.span_select {
		color: #39C;
		font-weight: bold;
	}

	.div_list {
		width: auto;
		height: 40px;
		font-size: 15px;
		line-height: 40px;
		border: #EFEFEF 1px solid;
		border-top: none;
		padding-left: 15px
	}
</style>
<div style="background: rgb(255, 255, 255); position: fixed; z-index: 999999; width: 100%; height: 100%; left: 0px; right: 0px;"
 id="page_show"></div>
<div class="index_main">
	<div style="font-size:18px; color:#666; margin-bottom:10px;">工作面板</div>
	<div class="main_left">
		<div class="box_div_3">
			<div class="div_icon" style="background:#23C6C8;"><span class="icon-cny" style="font-size:40px;"></span></div>
			<span style="padding-left:15px; line-height:30px;">
				<font style="font-size:25px; color:#23C6C8"><%=request.getAttribute("today_commission").toString() %></font>
			</span><br />
			<span style="padding-left:15px; line-height:30px;">
				<font style="font-size:14px; color:#AAA;">今日预估收入（元）</font>
			</span>
		</div>
		<div class="box_div_3">
			<div class="div_icon" style="background:#D62C40;"><span class="icon-cny" style="font-size:40px;"></span></div>
			<span style="padding-left:15px; line-height:30px;">
				<font style="font-size:25px; color:#D62C40"><%=request.getAttribute("today_platform_money").toString() %></font>
			</span><br />
			<span style="padding-left:15px; line-height:30px;">
				<font style="font-size:14px; color:#AAA;">今日预估利润（元）</font>
			</span>
		</div>
		<div class="box_clear"></div>
		<div class="box_div_3">
			<div class="div_icon" style="background:rgb(12,171,134);"><span class="icon-cny" style="font-size:40px;"></span></div>
			<span style="padding-left:15px; line-height:30px;">
				<font style="font-size:25px; color:background:rgb(12,171,134);"><%=request.getAttribute("yestoday_commission").toString() %></font>
			</span><br />
			<span style="padding-left:15px; line-height:30px;">
				<font style="font-size:14px; color:#AAA;">昨日预估收入（元）</font>
			</span>
		</div>
		<div class="box_div_3">
			<div class="div_icon" style="background:rgb(89, 164, 240);"><span class="icon-cny" style="font-size:40px;"></span></div>
			<span style="padding-left:15px; line-height:30px;">
				<font style="font-size:25px; color:rgb(89, 164, 240);"><%=request.getAttribute("yestoday_platform_money").toString() %></font>
			</span><br />
			<span style="padding-left:15px; line-height:30px;">
				<font style="font-size:14px; color:#AAA;">昨日预估利润（元）</font>
			</span>
		</div>
		<div class="box_clear"></div>
		<div class="box_div_6">
			<div class="box_div_title">
				<div class="box_div_icon" style="background:rgb(255, 75, 27);"><span class="icon-signal" style="font-size:20px;"></span></div>
				<span style="padding-left:15px; line-height:30px; font-size:16px; color:#333;">各平台销量排行榜</span> <span class="span_btn"
				 onclick="get_sales_ranking_of_products(-1)">昨日</span> <span class="span_btn" onclick="get_sales_ranking_of_products(1)">今日</span>
				<span class="span_btn span_select" onclick="get_sales_ranking_of_products(7)">近七日</span>
			</div>
			<div class="xiaoliang_img" id="xiaoliang_img" style="width:auto; height:250px; padding-top:10px;"></div>
		</div>
		<div class="box_clear"></div>
		<div class="box_div_6">
			<div class="box_div_title">
				<div class="box_div_icon" style="background:#0D79B5;"><span class="icon-pencil-square-o" style="font-size:20px;"></span></div>
				<span style="padding-left:15px; line-height:30px; font-size:16px; color:#333;">未处理的提现申请</span> <span class="span_btn span_select"
				 onclick="window.location.href = './admin.jsp?m=Finance&c=Index&a=index'">前去处理 </span>
			</div>
			<div style="width:auto;">
				<table width="100%" cellpadding="0" cellspacing="0" class="main_table">
					<tr class="main_table_header">
	                  <td width="40px">头像</td>
	                  <td>用户名</td>
	                  <td>申请时间</td>
	                  <td>提现金额</td>
	                </tr>
	                <%
	                JSONArray withdrawals_list = (JSONArray)request.getAttribute("withdrawals_list");
	                for(Integer i=0; i<withdrawals_list.size(); i=i+1){
	                	out.println("<tr>");
	                	out.println("<td><img src='"+withdrawals_list.getJSONObject(i).getString(MEMBER.pic)+"' width=\"30px\" /></td>");
	                	out.println("<td>"+withdrawals_list.getJSONObject(i).getString(MEMBER.username)+"</td>");
	                	out.println("<td>"+T.date("yyyy-MM-dd HH:mm:ss", withdrawals_list.getJSONObject(i).getLongValue(WITHDRAWALS.addtime))+"</td>");
	                	out.println("<td>￥"+withdrawals_list.getJSONObject(i).getString(WITHDRAWALS.money)+"</td>");
	                	out.println("</tr>");
	                }
	                %>
				</table>
			</div>
		</div>
	</div>
	<div class="main_right">
		<div class="box_div_3">
			<div class="div_icon" style="background:#23C6C8;"><span class="icon-cny" style="font-size:40px;"></span></div>
			<span style="padding-left:15px; line-height:30px;">
				<font style="font-size:25px; color:#23C6C8"><%=request.getAttribute("day_7_commission").toString() %></font>
			</span><br />
			<span style="padding-left:15px; line-height:30px;">
				<font style="font-size:14px; color:#AAA;">近七日预估收入（元）</font>
			</span>
		</div>
		<div class="box_div_3">
			<div class="div_icon" style="background:#D62C40;"><span class="icon-cny" style="font-size:40px;"></span></div>
			<span style="padding-left:15px; line-height:30px;">
				<font style="font-size:25px; color:#D62C40"><%=request.getAttribute("day_7_platform_money").toString() %></font>
			</span><br />
			<span style="padding-left:15px; line-height:30px;">
				<font style="font-size:14px; color:#AAA;">近7日预估利润（元）</font>
			</span>
		</div>
		<div class="box_clear"></div>
		<div class="box_div_3">
			<div class="div_icon" style="background:rgb(12,171,134);"><span class="icon-cny" style="font-size:40px;"></span></div>
			<span style="padding-left:15px; line-height:30px;">
				<font style="font-size:25px; color:background:rgb(12,171,134);"><%=request.getAttribute("year_commission").toString() %></font>
			</span><br />
			<span style="padding-left:15px; line-height:30px;">
				<font style="font-size:14px; color:#AAA;">本年度预估收入（元）</font>
			</span>
		</div>
		<div class="box_div_3">
			<div class="div_icon" style="background:rgb(89, 164, 240);"><span class="icon-cny" style="font-size:40px;"></span></div>
			<span style="padding-left:15px; line-height:30px;">
				<font style="font-size:25px; color:rgb(89, 164, 240);"><%=request.getAttribute("year_platform_money").toString() %></font>
			</span><br />
			<span style="padding-left:15px; line-height:30px;">
				<font style="font-size:14px; color:#AAA;">本年度预估利润（元）</font>
			</span>
		</div>
		<div class="box_clear"></div>
		<div class="box_div_6">
			<div class="box_div_title">
				<div class="box_div_icon" style="background:rgb(255, 75, 27);"><span class="icon-signal" style="font-size:20px;"></span></div>
				<span style="padding-left:15px; line-height:30px; font-size:16px; color:#333;">各平台收益排行榜</span> <span class="span_btn"
				 onclick="get_income(-1)">昨日</span> <span class="span_btn" onclick="get_income(1)">今日</span>
				<span class="span_btn span_select" onclick="get_income(7)">近七日</span>
			</div>
			<div class="shouyi_img" id="shouyi_img" style="width:auto; height:250px; padding-top:10px;"></div>
		</div>
		<div class="box_clear"></div>

		<div class="box_div_6">
			<div class="box_div_title">
				<div class="box_div_icon" style="background:#FFB825;"><span class="icon-list-alt" style="font-size:20px;"></span></div>
				<span style="padding-left:15px; line-height:30px; font-size:16px; color:#333;">最新订单</span>
			</div>
			<div style="width:auto;">
				<table width="100%" cellpadding="0" cellspacing="0" class="main_table">
					<tr class="main_table_header">
	                  <td width="40px">头像</td>
	                  <td>用户名</td>
	                  <td>时间</td>
	                  <td>订单金额</td>
	                  <td>总佣金</td>
	                  <td>利润</td>
	                </tr>
	                <%
	                JSONArray order_list = (JSONArray)request.getAttribute("order_list");
	                for(Integer i=0; i<order_list.size(); i=i+1){
	                	out.println("<tr>");
	                	out.println("<td><img src='"+order_list.getJSONObject(i).getString(MEMBER.pic)+"' width=\"30px\" /></td>");
	                	out.println("<td>"+order_list.getJSONObject(i).getString(MEMBER.username)+"</td>");
	                	out.println("<td>"+T.date("yyyy-MM-dd HH:mm:ss", order_list.getJSONObject(i).getLongValue(UNION_ORDER.pay_time))+"</td>");
	                	out.println("<td>￥"+order_list.getJSONObject(i).getString(UNION_ORDER.order_money)+"</td>");
	                	out.println("<td>￥"+order_list.getJSONObject(i).getString(UNION_ORDER.commission)+"</td>");
	                	out.println("<td>￥"+order_list.getJSONObject(i).getString(UNION_ORDER.platform_money)+"</td>");
	                	out.println("</tr>");
	                }
	                %>
				</table>
			</div>
		</div>

	</div>
</div>
<script>
	function main_size() {
		var main_width = parseInt($('.index_main').width());
		var main_left_width = parseInt(main_width / 2);
		var main_right_width = parseInt(main_width / 2);
		main_left_width = main_left_width - 10;
		main_right_width = main_right_width - 10;
		$('.main_left').width(main_left_width);
		$('.main_right').width(main_right_width);
		$('.box_div_6').width(main_left_width - 42 - 10);
		$('.box_div_3').width((parseInt(main_left_width / 2) - 42 - 10));
		$('.box_div_2').width((parseInt(main_left_width / 3) - 42 - 10));
		$('.box_div_1').width((parseInt(main_left_width / 6) - 42 - 10));
		$("#page_show").fadeOut("fast", function() {
			$("#page_show").remove();
		})
	}
	main_size();
	main_size();
	$(window).resize(function() {
		main_size();
	})

	$('.span_btn').click(function() {
		$(this).siblings().removeClass('span_select');
		$(this).addClass('span_select');
	})

	//商品销量排行
	function build_products_volum(json_data) {
		var myChart = echarts.init(document.getElementById('xiaoliang_img'));
		option = {
			tooltip: {},
			legend: {
				data: ['各平台销量/收益']
			},
			xAxis: {
				type: 'category',
				data: []
			},
			yAxis: {
				type: 'value'
			},
			series: [{
				name: '销量',
				data: [],
				type: 'bar'
			}]
		};
		json_data = json_data["list"];
		for (var i = 0; i < json_data.length; i = i + 1) {
			option.xAxis.data[i] = json_data[i]['platform'];
			option.series[0].data[i] = json_data[i]['number'];
		}
		myChart.setOption(option);
	}

	function get_sales_ranking_of_products(day_num) {
		var sales_ranking_of_products_url = "";
		if (day_num == 7) {
			//说明是近7天数据
			sales_ranking_of_products_url = "<%=T.U("Index/IndexData/get_serven_day_sales_ranking_of_products", "admin.jsp") %>";
		} else if (day_num == 1) {
			//说明是今天数据
			sales_ranking_of_products_url = "<%=T.U("Index/IndexData/get_today_sales_ranking_of_products", "admin.jsp") %>";
		} else if (day_num == -1) {
			//说明是昨天数据
			sales_ranking_of_products_url = "<%=T.U("Index/IndexData/get_yesterday_sales_ranking_of_products", "admin.jsp") %>";
		}
		http.post(sales_ranking_of_products_url, {}, function(data){
			build_products_volum(data);
		}, "");
	}
	get_sales_ranking_of_products(7);
	
	
	//各平台收益排行
	function build_platform_income(json_data) {
		var myChart = echarts.init(document.getElementById('shouyi_img'));
		option = {
			tooltip: {},
			legend: {
				data: ['各平台收益']
			},
			xAxis: {
				type: 'category',
				data: []
			},
			yAxis: {
				type: 'value'
			},
			series: [{
				name: '总佣金',
				data: [],
				type: 'bar'
			},{
				name: '利润',
				data: [],
				type: 'bar'
			}]
		};
		json_data = json_data["list"];
		for (var i = 0; i < json_data.length; i = i + 1) {
			option.xAxis.data[i] = json_data[i]['platform'];
			option.series[0].data[i] = json_data[i]['commission'];
			option.series[1].data[i] = json_data[i]['platform_money'];
		}
		myChart.setOption(option);
	}

	function get_income(day_num) {
		var sales_ranking_of_products_url = "";
		if (day_num == 7) {
			//说明是近7天数据
			sales_ranking_of_products_url = "<%=T.U("Index/IndexData/get_serven_day_sales_ranking_of_products", "admin.jsp") %>";
		} else if (day_num == 1) {
			//说明是今天数据
			sales_ranking_of_products_url = "<%=T.U("Index/IndexData/get_today_sales_ranking_of_products", "admin.jsp") %>";
		} else if (day_num == -1) {
			//说明是昨天数据
			sales_ranking_of_products_url = "<%=T.U("Index/IndexData/get_yesterday_sales_ranking_of_products", "admin.jsp") %>";
		}
		http.post(sales_ranking_of_products_url, {}, function(data){
			build_platform_income(data);
		}, "");
	}
	get_income(7);
</script>
            </div>
<%@include file="bottom.jsp"%>