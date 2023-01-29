/*
 * UI-C
 * Copyright (c) 2022 http://ui-c.com All rights reserved.
 * Licensed ( http://www.apache.org/licenses/LICENSE-2.0 )
 * Author: wangwenyuan <827287829@qq.com>
 */
ui_c.build_open_dom = function() {
	function build(obj) {
		var html = "";
		var title = obj[0].title;
		if (obj[0].checked) {
			html = '<div class="ui-c-open-box" onclick="ui_c.on_open_click()">' +
				'<div class="ui-c-open-off ui-c-open-on">' +
				'<div class="ui-c-open-title-off ui-c-open-title-on">ON</div>' +
				'<div class="ui-c-open-point-off ui-c-open-point-on"></div>' +
				'<div class="ui-c-clear"></div>' +
				'</div>' +
				'</div>';
		} else {
			html = '<div class="ui-c-open-box" onclick="ui_c.on_open_click()">' +
				'<div class="ui-c-open-off">' +
				'<div class="ui-c-open-point-off"></div>' +
				'<div class="ui-c-open-title-off">OFF</div>' +
				'<div class="ui-c-clear"></div>' +
				'</div>' +
				'</div>';
		}
		obj.after(html);
	}
	$(".ui-c-open-box").remove();
	var len = $(".ui-c-open").length;
	for (var i = 0; i < len; i = i + 1) {
		var obj = $(".ui-c-open").eq(i);
		build(obj);
	}
}

ui_c.on_open_click = function() {
	var that = event.currentTarget;
	$(that).prev().click();
	//ui_c.build_open_dom();
}
