var drop = {};
drop.p = 1;
drop.lock = false;
drop.is_do = false;

drop.reset = function() {
	drop.p = 1;
	drop.lock = false;
	drop.is_do = false;
}

drop.get = function(url, length_fn, fn, data_type) {
	if (url == "") {
		url = window.location.href;
	}
	if (data_type == "" || data_type == null || data_type == undefined) {
		data_type = "text";
	}
	if (drop.lock) {
		$("#javatodo_drop_tips").hide();
		layer.msg("已到最底部");
		return;
	}
	if (!drop.is_do) {
		drop.is_do = true;
	} else {
		return;
	}
	drop.p = drop.p + 1;
	if (url.indexOf("?") == -1) {
		url = url + "?p=" + drop.p;
	} else {
		url = url + "&p=" + drop.p;
	}
	http.get(url, function(data) {
		//隐藏加载提示信息
		$("#javatodo_drop_tips").hide();
		//检测是否已经到底部
		if (length_fn(data) == 0) {
			drop.lock = true;
			return;
		}
		fn(data);
		drop.is_do = false;
	}, data_type)
}

drop.post = function(url, length_fn, fn, post_data, data_type) {
	if (url == "") {
		url = window.location.href;
	}
	if (data_type == "" || data_type == null || data_type == undefined) {
		data_type = "text";
	}
	if (post_data == "" || post_data == null || post_data == undefined) {
		post_data = {};
	}
	if (drop.lock) {
		$("#javatodo_drop_tips").hide();
		layer.msg("已到最底部");
		return;
	}
	if (!drop.is_do) {
		drop.is_do = true;
	} else {
		return;
	}
	drop.p = drop.p + 1;
	if (url.indexOf("?") == -1) {
		url = url + "?p=" + drop.p;
	} else {
		url = url + "&p=" + drop.p;
	}
	http.post(url, post_data, function(data) {
		//隐藏加载提示信息
		$("#javatodo_drop_tips").hide();
		//检测是否已经到底部
		if (length_fn(data) == 0) {
			drop.lock = true;
			return;
		}
		fn(data);
		drop.is_do = false;
	}, data_type)
}

drop.scroll = function(url_fun, length_fn, fn, element_id, inner_element_id, http_type, post_data, data_type) {
	if (http_type == "" || http_type == null || http_type == undefined) {
		http_type = "get";
	}
	if (post_data == "" || post_data == null || post_data == undefined) {
		post_data = {};
	}
	if (data_type == "" || data_type == null || data_type == undefined) {
		data_type = "text";
	}
	drop.tips();
	var url = "";
	if (typeof url_fun == "function") {
		url = url_fun();
	} else {
		url = url_fun;
	}
	if (http_type == "get") {
		drop.get(url, length_fn, fn, data_type);
	} else {
		drop.post(url, length_fn, fn, post_data, data_type);
	}
	if (element_id == "" || element_id == null || element_id == undefined) {
		$(window).on('scroll', function() {
			if ($(window).scrollTop() >= $(document).height() - $(window).height() - 5) {
				if (http_type == "get") {
					drop.get(url, length_fn, fn, data_type);
				} else {
					drop.post(url, length_fn, fn, post_data, data_type);
				}
			}
		})
	} else {
		$(element_id).on('scroll', function() {
			if ($(element_id).scrollTop() >= $(inner_element_id).height() - $(element_id).height() - 5) {
				if (http_type == "get") {
					drop.get(url, length_fn, fn, data_type);
				} else {
					drop.post(url, length_fn, fn, post_data, data_type);
				}
			}
		})
	}
}

drop.tips = function() {
	if (drop.lock) {
		return;
	}
	var html =
		'<div id="javatodo_drop_tips" style="text-align: center; width: 9.375rem; height: 3.125rem; line-height: 3.125rem; font-size: 0.875rem; color: white; position: fixed; border-radius: 0.125rem;">正在加载，请稍后...</div>';
	if ($("#javatodo_drop_tips").length == 0) {
		var windows_width = parseInt($(window).width());
		var windows_height = parseInt($(window).height());
		$('body').append(html);
		var box_width = parseInt($("#javatodo_drop_tips").width());
		var box_height = parseInt($("#javatodo_drop_tips").height());
		var left = (windows_width - box_width) / 2;
		var top = (windows_height - box_height) / 2;
		$("#javatodo_drop_tips").width(box_width);
		$("#javatodo_drop_tips").height(box_height);
		$("#javatodo_drop_tips").css({
			'background-color': 'black',
			'left': left + 'px',
			'top': top + 'px'
		});
		$("#javatodo_drop_tips").fadeTo(100, 0.7);
	} else {
		$("#javatodo_drop_tips").show();
		$("#javatodo_drop_tips").fadeTo(100, 0.7);
	}
}
