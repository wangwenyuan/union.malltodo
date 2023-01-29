<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.javatodo.core.tools.T"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>            
      </div>
      </div></td>
    </tr>
  </table>
</div>
<script>
function admin_size(){
	var kuandu=$(window).width();
	var gaodu=$(window).height();
	var you_kuandu=kuandu-200;
	var zuo_gaodu=gaodu-50;
	var you_gaodu=gaodu-50;
	$('#zuo').height(zuo_gaodu);
	$('#zuo_xiao').height(zuo_gaodu);
	$('#you').height(you_gaodu);
	$('#content').height(you_gaodu);
	$('#you_content').slimScroll({
        height: you_gaodu+'px',
        alwaysVisible: true
    });
	$('.zuo_kuang').slimScroll({
        height: (zuo_gaodu-45)+'px',
        alwaysVisible: true
    });
	
	if($('#zuzhijiagou_box').length>0){
		$('#zuzhijiagou_box').height(you_gaodu-140);

	}
	
}
function window_resize(){
	admin_size();
	$(window).resize(function(){
		admin_size();
	})
}
window_resize();

var zuo_tou_init=1;
$('.zuo_tou').click(function(){
	if($(this).hasClass('ul_guanbi')){
		$('.ul_dakai').parent().animate({'height':'33px'},'fast');
		$('.ul_dakai').addClass('ul_guanbi');
		$('.ul_dakai').removeClass('ul_dakai');
		$(this).removeClass('ul_guanbi');
		$(this).addClass('ul_dakai');
		var geshu=$(this).parent().children('.zuo_li').length;
		var gaodu=(geshu+1)*35;
		if(zuo_tou_init==1){
			$(this).parent().css({'height':gaodu+'px'});
			zuo_tou_init=0;
		}else{
			$(this).parent().animate({'height':gaodu+'px'},'fast');
		}
	}else{
		$(this).removeClass('ul_dakai');
		$(this).addClass('ul_guanbi');
		$(this).parent().animate({'height':'33px'},'fast');
	}
})
$('.zuo_li').mouseenter(function(){
	if(!$(this).hasClass('zuo_li_dianji')){
		$(this).css({'background':'#7C7C7C'});
	}
})
$('.zuo_li').mouseleave(function(){
	if(!$(this).hasClass('zuo_li_dianji')){
		$(this).css({'background':'none'});
	}
})
$('.zuo_li').click(function(){
	$('.zuo_li').removeClass('zuo_li_dianji');
	$('.zuo_li').css({'background':'none'});
	$('.zuo_li').css({'background':'none','color':'#FFF'});
	$(this).css({'background':'url(<%=request.getAttribute("PUBLIC") %>/images/arrow.png)','color':'#333'});
	$(this).addClass('zuo_li_dianji');
	var url=$(this).data('url');
	document.location.href=url;
})

$('.zuo_main_icon').click(function(){
	var zuo_kuandu=$('#zuoce').width();
	if(zuo_kuandu<80){
		$('.zuo_xiao').css({'display':'none'});
		$('.zuo').css({'display':'block'});
		$('#zuo').animate({width:175});
		$('.logo').animate({width:161});
		$('#zuoce').width(175);
		//$('.zuo_kuang_tou').css({'background':'none'});
		$('.zuo_main_icon').removeClass('icon-arrow-circle-right');
		$('.zuo_main_icon').addClass('icon-arrow-circle-left');
		$("#logo_name").text("MALLTODO");
		$.cookie('is_zhankai','dakai');
	}else{
		$('#zuo').animate({width:50},'fast','swing',function(){
			$('.zuo').css({'display':'none'});
			$('.zuo_xiao').css({'display':'block'});
		});
		$('.logo').animate({width:36},'fast','swing');
		$('#zuoce').width(50);
		//$('.zuo_kuang_tou').css({'background':'#069'});
		$('.zuo_main_icon').removeClass('icon-arrow-circle-left');
		$('.zuo_main_icon').addClass('icon-arrow-circle-right');
		$("#logo_name").text("M");
		$.cookie('is_zhankai','guanbi');
	}
})

$('.small_div').mouseenter(function(){
	$('.small_nei_div').css({'display':'none'});
	var _position=$(this).offset();
	$(this).children('.small_nei_div').css({'display':'block','top':_position.top+0});
	var div_height=0+45*($(this).children('.small_nei_div').eq(0).children('.small_a_div').length);
	var window_height=parseInt($(window).height());
	if(_position.top+0+div_height+4>window_height){
		var nei_gao=window_height-(_position.top+0+div_height);
		nei_gao=0-nei_gao;
		var new_gao=_position.top+0-nei_gao-5
		$(this).children('.small_nei_div').css({'display':'block','top':new_gao,'bottom':5});
	}else{
		$(this).children('.small_nei_div').css({'display':'block','top':_position.top+0,'bottom':window_height-(_position.top+0+div_height)});
	}
})

var touch_small_div=-1;

function xiaoshi(){
	if(touch_small_div==-1){
		return ;
	}
	$('.small_div').eq(touch_small_div).children('.small_nei_div').css({'display':'none'})
}

$('.small_nei_div').mouseenter(function(){
	touch_small_div=-1;
})

$('.small_div').mouseleave(function(){
	touch_small_div=$(this).index();
	setTimeout("xiaoshi()",500);
})

$('.main_table tr').mouseenter(function(){
	if(!$(this).hasClass('main_table_header')){
		$(this).children('td').css({'background':'#F8F8F8'}); 
	}
})
$('.main_table tr').mouseleave(function(){
	if(!$(this).hasClass('main_table_header')){
		$(this).children('td').css({'background':'#FFF'}); 
	}	    
})
<%
Map<String, String> bottom_input = (HashMap)request.getAttribute("input");
%>
$('.<%=bottom_input.get("m")%>_<%=bottom_input.get("c")%>').parent().children('.zuo_tou').click();
$('.<%=bottom_input.get("m")%>_<%=bottom_input.get("c")%>').css({'background':'url(<%=request.getAttribute("PUBLIC") %>/images/arrow.png)','color':'rgb(51,51,51)'}).addClass('zuo_li_dianji');
if($.cookie('is_zhankai')=='guanbi'){
	$('.zuo_main_icon').click();
}
//$('.zuo_main_icon').click();

var add_flag = false;

$("#add").click(function () {
	if(!add_flag){
		add_flag = true;
	}else{
		return ;
	}
    loading = layer.load(2, {
        shade: [0.2, '#000']
    });
	var url=document.location.href;
	var data=$("#html_form").serialize();
	var ret=malltodoJs.ajax(url,data);
	if(ret=='error'){
        layer.close(loading);
		layer.msg('网络错误');
		add_flag = false;
	}else{
        layer.close(loading);
		layer.msg(ret.info,{
			time:2000
		},function(){
			if(parseInt(ret.status)){
				parent.location.reload();
			}else{
				add_flag = false;
			}
		})
	}
})

</script>
</body>
</html>