var malltodo_soft = 'admin';
var malltodoJs = {};

malltodoJs.ajax = function(url, input_data) {
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

malltodoJs.del = function(url, id) {
	layer.msg('您确定要删除？', {
		time: 0,
		btn: ['确定', '取消'],
		yes: function(index) {
			layer.close(index);
			var input_data = {
				id: id
			}
			var ret = malltodoJs.ajax(url, input_data);
			if (ret.status) {
				if (ret.url) {
					document.location.href = ret.url
				} else {
					window.location.reload();
				}
			} else {
				layer.msg(ret.info)
			}
		}
	});
}

malltodoJs.sub_window = function(title, url) {
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

malltodoJs.max_sub_window = function(title, url) {
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

malltodoJs.organization_window_index = null;

malltodoJs.show_organization_window = function(title, organization_type, selected_node, exclude_node){
	var url = "./admin.system.jsp?m=Organization&c=Organization&a=index&organization_type="+organization_type+"&selected_node="+selected_node+"&exclude_node="+exclude_node;
	malltodoJs.organization_window_index = layer.open({
		type: 2,
		title: title,
		shadeClose: true,
		shade: false,
		maxmin: true,
		area: ['893px', '600px'],
		content: [url],
	});
}

malltodoJs.close_organization_window = function(organization_list){
	parent.layer.close(parent.malltodoJs.organization_window_index);
	if(typeof parent.close_organization_window_after == "function"){
		parent.close_organization_window_after(organization_list);
	}
}

malltodoJs.animation = function() {
	$(function() {
		var len = $("[javatodo-animate]").length;
		$(window).on('scroll', function() {
			for (var i = 0; i < len; i = i + 1) {
				if ($(window).scrollTop() + $(window).height() >= $("[javatodo-animate]").eq(i).offset().top - 50) {
					if (!$("[javatodo-animate]").eq(i).hasClass($("[javatodo-animate]").eq(i).attr("javatodo-animate"))) {
						$("[javatodo-animate]").eq(i).addClass("animated");
						$("[javatodo-animate]").eq(i).addClass($("[javatodo-animate]").eq(i).attr("javatodo-animate"));
					}
				}
			}
		})
	})
}();

malltodoJs.is_mobile = function() {
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

malltodoJs.css_storage_list = {};
malltodoJs.css_storage = function(sign, document_dom) {
	var arr = new Array();
	if (typeof window.getComputedStyle == undefined || typeof window.getComputedStyle == "undefined") {
		arr = document_dom.currentStyle();
	} else {
		arr = window.getComputedStyle(document_dom);
	}
	var temp = {};
	for (var key in arr) {
		var val = arr[key];
		if (typeof val != "function" && (parseInt(key) == 0 || Number.isNaN(parseInt(key))) && key != "0") {
			temp[key] = val;
		}
	}
	malltodoJs.css_storage_list[sign] = temp;
}

malltodoJs.css_recovery = function(sign, jquery_dom) {
	jquery_dom.css(malltodoJs.css_storage_list[sign]);
}

malltodoJs.show_images_window = function(fun_name, id_val){
	window_layer = layer.open({
		type: 2,
		title: "选择图片",
		shadeClose: true,
		shade: false,
		maxmin: true,
		area: ['98%', '98%'],
		content: ["./index.jsp?m=Index&c=Images&a=index&fun_name="+fun_name+"&id_val="+id_val],
	});
}

malltodoJs.images_selected = function(id, pic) {
	layer.close(window_layer);
	$("#" + id).val(pic);
	$("#" + id + "_javatodo_upload_file_outerbox").css({'background-image':'url('+pic+')'});
};

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

function open_call_window(seat_name, seat_id) {
	layer.msg(seat_name + "桌台呼叫", {
		time: 0,
		btn: ['确定', '取消'],
		yes: function(index) {
			layer.close(index);
			if (typeof MalltodoApp != "undefined") {
				MalltodoApp.yes_btn_click(seat_id);
			}
		},
		btn2: function(index) {
			layer.close(index);
			if (typeof MalltodoApp != "undefined") {
				MalltodoApp.cancel_btn_click(seat_id);
			}
		}
	});
	return "0";
}

malltodoJs.build_qqmap = function(js_dom, lat, lng) {
	if(lat == "" || lat == undefined || lat == NaN || lng == "" || lng == undefined || lng == NaN){
		lat = "40.00286734916531";
		lng = "116.32294822667086";
	}
	var center = new TMap.LatLng(lat,lng);
	var map = new TMap.Map(js_dom, {
	    center: center,//设置地图中心点坐标
	    zoom:17.2,   //设置地图缩放级别
	});
	var geometries = [{
	    "id": "1",
	    "position": new TMap.LatLng(lat, lng),
	    "properties": {
	        "title": "marker1"
	    }
	}]
	var marker = new TMap.MultiMarker({
	    map: map,  
	    geometries: geometries
	});
}

$(function(){
	var len = $(".javatodo-map").length;
	for(var i=0; i<len; i=i+1){
		var lat = $(".javatodo-map").eq(i).attr("javatodo-map-lat");
		var lng = $(".javatodo-map").eq(i).attr("javatodo-map-lng");
		malltodoJs.build_qqmap(document.getElementsByClassName("javatodo-map")[i], lat, lng);
	}
})