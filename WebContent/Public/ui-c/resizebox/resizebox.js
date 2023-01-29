/*
 * UI-C
 * Copyright (c) 2022 http://ui-c.com All rights reserved.
 * Licensed ( http://www.apache.org/licenses/LICENSE-2.0 )
 * Author: wangwenyuan <827287829@qq.com>
 */
ui_c.build_resizebox_dom = function(css_class) {
	function build(obj) {
		if (obj.children('.ui-c-resizebox-horizontal-handle').length > 0) {
			return;
		}
		var html = "";
		if(!css_class){
			html = '<div class="ui-c-resizebox-horizontal-handle" onmousedown="ui_c.on_resizebox_resize()"></div>';
			html = html + '<div class="ui-c-resizebox-vertical-handle" onmousedown="ui_c.on_resizebox_resize()"></div>';
		}else{
			html = '<div class="ui-c-resizebox-horizontal-handle" onmousedown="ui_c.on_resizebox_resize(\''+css_class+'\')"></div>';
			html = html + '<div class="ui-c-resizebox-vertical-handle" onmousedown="ui_c.on_resizebox_resize(\''+css_class+'\')"></div>';
		}
		obj.prepend(html);
		var height = obj.height();
		obj.children('.ui-c-resizebox-horizontal-handle').height(height);
		var width = obj.width();
		obj.children('.ui-c-resizebox-vertical-handle').width(width);
	}
	var len = $(".ui-c-resizebox").length;
	for (var i = 0; i < len; i = i + 1) {
		build($(".ui-c-resizebox").eq(i));
	}
}

ui_c.on_resizebox_resize = function(css_class) {
	var that = event.currentTarget;
	var obj = $(that);
	var is_resize = true;
	var x = event.clientX;
	var y = event.clientY;
	var width = $(that).parent().width();
	var height = $(that).parent().height();
	$(document).on('mousemove', function(e) {
		if (!is_resize) {
			return;
		}
		nwidth = width + e.clientX - x;
		nheight = height + e.clientY - y;
		obj.parent().width(nwidth);
		obj.parent().height(nheight);
		obj.parent().children(".ui-c-resizebox-horizontal-handle").height(nheight);
		obj.parent().children(".ui-c-resizebox-vertical-handle").width(nwidth);
		if(css_class){
			$(css_class).width(nwidth);
			$(css_class).height(nheight);
		}
	}).on('mouseup', function(e) {
		is_resize = false;
	});
}
