var http = {};
http.get = function(url, fn, data_type) {
	var apptoken = "";
	if(typeof MalltodoApp != "undefined"){
		apptoken = MalltodoApp.get_app_token();
	}
	if (data_type == "" || data_type == undefined) {
		data_type = "json";
	}
	$.ajax({
		headers: {
			'APPTOKEN': apptoken,
		},
		async: true,
		type: "GET",
		dataType: data_type,
		url: url,
		success: function(data) {
			fn(data);
		},
		error: function() {
			layer.msg("网络连接错误");
		}
	});
}

http.post = function(url, post_data, fn, data_type) {
	var apptoken = "";
	if(typeof MalltodoApp != "undefined"){
		apptoken = MalltodoApp.get_app_token();
	}
	if (data_type == "" || data_type == undefined) {
		data_type = "json";
	}
	$.ajax({
		headers: {
			'APPTOKEN': apptoken,
		},
		async: true,
		type: "POST",
		dataType: data_type,
		url: url,
		data: post_data,
		success: function(data) {
			fn(data);
		},
		error: function() {
			layer.msg("网络连接错误");
		}
	});
}
