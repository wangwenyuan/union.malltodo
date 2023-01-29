//发送注册短信
var register_anniu_val=0;
var register_timer;
var register_times=0;
function register_changeStatus(){
	if(register_times==0){
		$('#register_send_sms_btn').html('');
		$('#register_send_sms_btn').html('剩余'+(60-register_times)+'秒');
	}
	if(register_times!=0 && register_times<60){
		$('#register_send_sms_btn').html('');
		$('#register_send_sms_btn').html('剩余'+(60-register_times)+'秒');
	}
	if(register_times==60){
		clearInterval(register_timer);
		$('#register_send_sms_btn').html('');
		$('#register_send_sms_btn').html('发送短信');
		register_anniu_val=0;
		return ;
	}
	if(register_times>60){
		clearInterval(register_timer);
		$('#register_send_sms_btn').html('');
		$('#register_send_sms_btn').html('发送短信');
		register_anniu_val=0;
		return ;
	}
	register_times=register_times+1;
}

function send_register_sms(lianjie){
	$('#register_send_sms_btn').click(function(){
		if(register_anniu_val==1){
			return ;
		}else{
			register_times=0;
		}
		var shuju={
			mobile:$('#register_mobile').val(),
			verify:$('#register_yanzhengma').val()
		}
		
		if(shuju.mobile == ""){
			layer.msg("手机号不能为空");
			return ;
		}
		
		if(shuju.verify == ""){
			layer.msg("验证码不能为空");
			return ;
		}
		var jieguo=malltodoJs.ajax(lianjie, shuju);
		if (jieguo != 'error') {
            if (jieguo.status == 1) {
				register_timer = setInterval('register_changeStatus()',1000);
				register_anniu_val=1;
                layer.msg(jieguo.info,{
        			time:2000
        		},function(){
        			if((jieguo.url).trim()!=""){
        				document.location.href=(jieguo.url).trim()
        			}
        		})
            } else {
                layer.msg(jieguo.info);
                $("#register_yanzheng").click();
            }
        } else {
        	$("#register_yanzheng").click();
        }
	})
}


//发送找回密码短信
var find_password_anniu_val=0;
var find_password_timer;
var find_password_times=0;
function find_password_changeStatus(){
	if(find_password_times==0){
		$('#find_password_send_sms_btn').html('');
		$('#find_password_send_sms_btn').html('剩余'+(60-find_password_times)+'秒');
	}
	if(find_password_times!=0 && find_password_times<60){
		$('#find_password_send_sms_btn').html('');
		$('#find_password_send_sms_btn').html('剩余'+(60-find_password_times)+'秒');
	}
	if(find_password_times==60){
		clearInterval(find_password_timer);
		$('#find_password_send_sms_btn').html('');
		$('#find_password_send_sms_btn').html('发送短信');
		find_password_anniu_val=0;
		return ;
	}
	if(find_password_times>60){
		clearInterval(find_password_timer);
		$('#find_password_send_sms_btn').html('');
		$('#find_password_send_sms_btn').html('发送短信');
		find_password_anniu_val=0;
		return ;
	}
	find_password_times=find_password_times+1;
}

function send_find_password_sms(lianjie){
	$('#find_password_send_sms_btn').click(function(){
		if(find_password_anniu_val==1){
			return ;
		}else{
			find_password_times=0;
		}
		var shuju={
			mobile:$('#find_password_mobile').val(),
			verify:$('#find_password_yanzhengma').val()
		}
		
		if(shuju.mobile == ""){
			layer.msg("手机号不能为空");
			return ;
		}
		
		if(shuju.verify == ""){
			layer.msg("验证码不能为空");
			return ;
		}
		var jieguo=malltodoJs.ajax(lianjie, shuju);
		console.log(jieguo);
		if (jieguo != 'error') {
            if (jieguo.status == 1) {
				find_password_timer = setInterval('find_password_changeStatus()',1000);
				find_password_anniu_val=1;
                layer.msg(jieguo.info,{
        			time:2000
        		},function(){
        			if((jieguo.url).trim()!=""){
        				document.location.href=(jieguo.url).trim()
        			}
        		})
            } else {
                layer.msg(jieguo.info);
                $("#find_password_yanzheng").click();
            }
        } else {
        	$("#find_password_yanzheng").click();
        }
	})
}
