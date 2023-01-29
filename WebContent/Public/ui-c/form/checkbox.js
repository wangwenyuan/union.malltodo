/*
 * UI-C
 * Copyright (c) 2022 http://ui-c.com All rights reserved.
 * Licensed ( http://www.apache.org/licenses/LICENSE-2.0 )
 * Author: wangwenyuan <827287829@qq.com>
 */
ui_c.build_checkbox_dom = function() {
	function build(obj) {
		var html = "";
		var title = obj[0].title;
		if (obj[0].checked) {
			html = '<div class="ui-c-checkbox-box" onclick="ui_c.on_checkbox_click()">' +
				'<div class="ui-c-checkbox-div ui-c-checkbox-checked">' +
				'<strong>√</strong>' +
				'</div>' +
				'<span class="ui-c-checkbox-title">' + title + '</span>' +
				'<div class="ui-c-clear"></div>' +
				'</div>';
		} else if (obj[0].disabled) {
			html = '<div class="ui-c-checkbox-box ui-c-checkbox-div-disabled">' +
				'<div class="ui-c-checkbox-disabled"></div>' +
				'<span class="ui-c-checkbox-title">' + title + '</span>' +
				'<div class="ui-c-clear"></div>' +
				'</div>';
		} else {
			html = '<div class="ui-c-checkbox-box" onclick="ui_c.on_checkbox_click()">' +
				'<div class="ui-c-checkbox-div"></div>' +
				'<span class="ui-c-checkbox-title">' + title + '</span>' +
				'<div class="ui-c-clear"></div>' +
				'</div>';
		}
		obj.after(html);
	}
	$(".ui-c-checkbox-box").remove();
	var len = $(".ui-c-checkbox").length;
	for (var i = 0; i < len; i = i + 1) {
		var obj = $(".ui-c-checkbox").eq(i);
		build(obj);
	}
}

ui_c.on_checkbox_click = function() {
	var that = event.currentTarget;
	$(that).prev().click();
	ui_c.build_checkbox_dom();
}
