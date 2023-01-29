/*
 * UI-C
 * Copyright (c) 2022 http://ui-c.com All rights reserved.
 * Licensed ( http://www.apache.org/licenses/LICENSE-2.0 )
 * Author: wangwenyuan <827287829@qq.com>
 */
ui_c.build_drag_dom = function() {
	function build(obj) {
		if (obj.children('.ui-c-drag-box').length > 0) {
			return;
		}
		var width = parseInt(obj.width());
		var height = parseInt(obj.height());
		if (width > height) {
			width = height;
		} else {
			height = width;
		}
		width = width / 3;
		height = height / 3;
		if (width > 50) {
			width = 50;
			height = 50;
		}
		var html = "";
		html = '<div class="ui-c-drag-box" style="width:' + width + 'px; height:' + height +
			'px"><div class="ui-c-drag-triangle"><div class="ui-c-drag-text">拖拽</div></div></div>';
		obj.prepend(html);
		obj.children(".ui-c-drag-box").attr("draggable", true);
		obj.children(".ui-c-drag-box").attr("ondragstart", "ui_c.on_drag_start(event)");
		obj.parent(".ui-c-drag-region").attr("ondrop", "ui_c.on_drag_end(event)");
		obj.parent(".ui-c-drag-region").attr("ondragover", "ui_c.on_drag_move(event)");

	}
	var len = $(".ui-c-drag").length;
	for (var i = 0; i < len; i = i + 1) {
		build($(".ui-c-drag").eq(i));
	}
}

ui_c.on_drag_start = function(event) {
	ui_c.on_drag_dom_adjust(event, 0);
}

ui_c.on_drag_end = function(event) {
	event.preventDefault();
	ui_c.on_drag_dom_adjust(event, 1);
	ui_c.drag_dom_x = 0;
	ui_c.drag_dom_y = 0;
	ui_c.drag_event_clent_x = 0;
	ui_c.drag_event_clent_y = 0;
}

ui_c.on_drag_move = function(event) {
	event.preventDefault();
	ui_c.on_drag_dom_adjust(event, 1);
}

ui_c.drag_dom_x = 0;
ui_c.drag_dom_y = 0;
ui_c.drag_event_clent_x = 0;
ui_c.drag_event_clent_y = 0;

ui_c.on_drag_dom_adjust = function(event, type) {
	var that = event.currentTarget;
	if (type == 0) {
		ui_c.drag_dom_x = 0;
		ui_c.drag_dom_y = 0;
		ui_c.drag_event_clent_x = 0;
		ui_c.drag_event_clent_y = 0;
		var obj = $(that).parent(".ui-c-drag");
		var width = parseInt(obj.width());
		var height = parseInt(obj.height());
		if (width > height) {
			width = height;
		} else {
			height = width;
		}
		width = width / 3;
		height = height / 3;
		if (width > 50) {
			width = 50;
			height = 50;
		}
		obj.children(".ui-c-drag-box").width(width);
		obj.children(".ui-c-drag-box").height(height);
	} else {
		var obj = $(that).children(".ui-c-drag");
	}
	ui_c.drag_dom_x = parseInt(obj.css("margin-left"));
	ui_c.drag_dom_y = parseInt(obj.css("margin-top"));
	if (ui_c.drag_event_clent_x == 0) {
		ui_c.drag_event_clent_x = event.clientX;
		ui_c.drag_event_clent_y = event.clientY;
	} else {
		var x = event.clientX - ui_c.drag_event_clent_x;
		var y = event.clientY - ui_c.drag_event_clent_y;
		obj.css("margin-left", (ui_c.drag_dom_x + x) + "px");
		obj.css("margin-top", (ui_c.drag_dom_y + y) + "px");
		ui_c.drag_event_clent_x = event.clientX;
		ui_c.drag_event_clent_y = event.clientY;
	}
}
