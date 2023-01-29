var send_sms_anniu_val=0;
var send_sms_timer;
var send_sms_times=0;
function send_sms_changeStatus(){
	if(send_sms_times==0){
		$('#send_sms_btn').html('');
		$('#send_sms_btn').html('剩余'+(60-send_sms_times)+'秒');
	}
	if(send_sms_times!=0 && send_sms_times<60){
		$('#send_sms_btn').html('');
		$('#send_sms_btn').html('剩余'+(60-send_sms_times)+'秒');
	}
	if(send_sms_times==60){
		clearInterval(send_sms_timer);
		$('#send_sms_btn').html('');
		$('#send_sms_btn').html('发送短信');
		send_sms_anniu_val=0;
		return ;
	}
	if(send_sms_times>60){
		clearInterval(send_sms_timer);
		$('#send_sms_btn').html('');
		$('#send_sms_btn').html('发送短信');
		send_sms_anniu_val=0;
		return ;
	}
	send_sms_times=send_sms_times+1;
}

function send_sms(lianjie){
	$('#send_sms_btn').click(function(){
		if(send_sms_anniu_val==1){
			return ;
		}else{
			send_sms_times=0;
		}
		var shuju={
			mobile:$('#mobile').val(),
			verify:$('#verify').val()
		}
		
		if(shuju.mobile == ""){
			layer.msg("手机号不能为空");
			return ;
		}
		
		if(shuju.verify == ""){
			layer.msg("验证码不能为空");
			return ;
		}
		http.post(lianjie, shuju, function(data){
			if (data.status == 1) {
				send_sms_timer = setInterval('send_sms_changeStatus()',1000);
				send_sms_anniu_val=1;
			    layer.msg(data.info,{
					time:2000
				},function(){
					if((data.url).trim()!=""){
						document.location.href=(data.url).trim()
					}
				})
			} else {
			    layer.msg(data.info);
			    $("#send_sms_yanzheng").click();
			}
		})
	})
}