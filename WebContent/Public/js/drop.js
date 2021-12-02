var drop = {};

drop.p = 1;
drop.lock = false;
drop.is_do = false;

drop.reset = function(){
	drop.p = 1;
	drop.lock = false;
	drop.is_do = false;
}

drop.get = function(url, length_fn, fn) {
	if (drop.lock) {
		$("#javatodo_drop_tips").hide();
		layer.msg("已到最底部");
		return ;
	}
	if (!drop.is_do) {
		drop.is_do = true;
	} else {
		return ;
	}
	drop.p = drop.p + 1;
	http.get(url + "&p=" + drop.p, function(data) {
		//隐藏加载提示信息
		$("#javatodo_drop_tips").hide();
		//检测是否已经到底部
		if(length_fn(data) == 0){
			drop.lock = true;
			return ;
		}
		fn(data);
		drop.is_do = false;
	}, "text")
}

drop.scroll = function(url, length_fn, fn) {
	$(window).on('scroll', function() {
		if ($(window).scrollTop() >= $(document).height() - $(window).height() - 5) {
			drop.tips();
			drop.get(url, length_fn, fn);
		}
	})
}

drop.tips = function() {
	var html = '<div id="javatodo_drop_tips" style="text-align: center; width: 9.375rem; height: 3.125rem; line-height: 3.125rem; font-size: 0.875rem; color: white; position: fixed; border-radius: 0.125rem;">正在加载，请稍后...</div>';
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
		if(drop.lock){
			return ;
		}
		$("#javatodo_drop_tips").show();
		$("#javatodo_drop_tips").fadeTo(100, 0.7);
	}
}
