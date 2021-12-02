// JavaScript Document
var shop = {};

shop.ajax = function(url, input_data) {
	var ret;
	$.ajax({
		async: false,
		type: "POST",
		dataType: "json",
		url: url,
		data: input_data,
		success: function(data) {
			ret = data
		},
		error: function() {
			ret = 'error';
		}
	});
	return ret;
}

shop.del = function(url, id) {
	layer.msg('您确定要删除？', {
		time: 0,
		btn: ['确定', '取消'],
		yes: function(index) {
			layer.close(index);
			var input_data = {
				id: id
			}
			var ret = shop.ajax(url, input_data);
			if (ret.status) {
				if (ret.url) {
					document.location.href = ret.url
				} else {
					window.location.reload();
				}
			} else {
				layer.alert(ret.info)
			}
		}
	});
}

shop.sub_window = function(title, url) {
	var index = layer.open({
		type: 2,
		title: title,
		shadeClose: true,
		shade: false,
		maxmin: true,
		area: ['893px', '600px'],
		content: [url],
	});
}

shop.max_sub_window = function(title, url) {
	var index = layer.open({
		type: 2,
		title: title,
		shadeClose: true,
		shade: false,
		maxmin: true,
		area: ['98%', '98%'],
		content: [url],
	});
}

shop.sign_out = function(module) {

	layer.msg('您确定要退出？', {
		time: 0,
		btn: ['确定', '取消'],
		yes: function(index) {
			layer.close(index);
			var url = "";
			if (module == "company") {
				url = "./index.jsp?m=company&c=Index&a=sign_out";
			} else if (module == "merchant") {
				url = "./index.jsp?m=merchant&c=Index&a=sign_out";
			} else if (module == "admin") {
				url = "./index.jsp?m=admin&c=Index&a=sign_out";
			}
			$.ajax({
				async: true,
				type: "POST",
				dataType: "json",
				url: url,
				data: {},
				success: function(data) {
					if (data["url"]) {
						window.location.href = data["url"];
					}
				},
				error: function() {
					ret = 'error';
				}
			});
		}
	});
}

shop.is_mobile = function() {
	var user_agent_info = navigator.userAgent;
	user_agent_info = user_agent_info.toLowerCase();
	var arr = ["240x320",
		"acer",
		"acoon",
		"acs-",
		"abacho",
		"ahong",
		"airness",
		"alcatel",
		"amoi",
		"android",
		"anywhereyougo.com",
		"applewebkit/525",
		"applewebkit/532",
		"asus",
		"audio",
		"au-mic",
		"avantogo",
		"becker",
		"benq",
		"bilbo",
		"bird",
		"blackberry",
		"blazer",
		"bleu",
		"cdm-",
		"compal",
		"coolpad",
		"danger",
		"dbtel",
		"dopod",
		"elaine",
		"eric",
		"etouch",
		"fly ",
		"fly_",
		"fly-",
		"go.web",
		"goodaccess",
		"gradiente",
		"grundig",
		"haier",
		"hedy",
		"hitachi",
		"htc",
		"huawei",
		"hutchison",
		"inno",
		"ipad",
		"ipaq",
		"iphone",
		"ipod",
		"jbrowser",
		"kddi",
		"kgt",
		"kwc",
		"lenovo",
		"lg ",
		"lg2",
		"lg3",
		"lg4",
		"lg5",
		"lg7",
		"lg8",
		"lg9",
		"lg-",
		"lge-",
		"lge9",
		"longcos",
		"maemo",
		"mercator",
		"meridian",
		"micromax",
		"midp",
		"mini",
		"mitsu",
		"mmm",
		"mmp",
		"mobi",
		"mot-",
		"moto",
		"nec-",
		"netfront",
		"newgen",
		"nexian",
		"nf-browser",
		"nintendo",
		"nitro",
		"nokia",
		"nook",
		"novarra",
		"obigo",
		"palm",
		"panasonic",
		"pantech",
		"philips",
		"phone",
		"pg-",
		"playstation",
		"pocket",
		"pt-",
		"qc-",
		"qtek",
		"rover",
		"sagem",
		"sama",
		"samu",
		"sanyo",
		"samsung",
		"sch-",
		"scooter",
		"sec-",
		"sendo",
		"sgh-",
		"sharp",
		"siemens",
		"sie-",
		"softbank",
		"sony",
		"spice",
		"sprint",
		"spv",
		"symbian",
		"tablet",
		"talkabout",
		"tcl-",
		"teleca",
		"telit",
		"tianyu",
		"tim-",
		"toshiba",
		"tsm",
		"up.browser",
		"utec",
		"utstar",
		"verykool",
		"virgin",
		"vk-",
		"voda",
		"voxtel",
		"vx",
		"wap",
		"wellco",
		"wig browser",
		"wii",
		"windows ce",
		"wireless",
		"xda",
		"xde",
		"zte"
	];
	var flag = false;
	for (var i = 0; i < arr.length; i = i + 1) {
		if (user_agent_info.indexOf(arr[i]) > -1) {
			flag = true;
			break;
		}
	}
	return flag;
}

function I(a) {
	var url = document.location.href;
	url = decodeURI(url);
	if (url.indexOf("?") == -1) {
		url = url + "?";
	}
	var url_arr = url.split("?");
	url = url_arr[1];
	if (url.indexOf("&") == -1) {
		url = url + "&";
	}
	url_arr = url.split("&");
	var map = new Array();
	for (var i = 0; i < url_arr.length; i = i + 1) {
		var param = url_arr[i];
		if (param.indexOf("=") != -1) {
			var param_arr = param.split("=");
			if (param_arr[0].trim() != "") {
				map[param_arr[0].trim()] = param_arr[1];
			}
		}
	}
	if (a == undefined || a == "") {
		return map;
	} else {
		if (map.hasOwnProperty(a)) {
			return map[a];
		} else {
			return "";
		}
	}
}

function U(map, entrance) {
	var url = "";
	for (var k in map) {
		if (url == "") {
			url = k + "=" + map[k];
		} else {
			url = url + "&" + k + "=" + map[k];
		}
	}
	if (url == "") {
		url = "./" + entrance;
	} else {
		url = "./" + entrance + "?" + url;
	}
	return encodeURI(url);
}
