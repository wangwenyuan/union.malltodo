/*
 * UI-C
 * Copyright (c) 2022 http://ui-c.com All rights reserved.
 * Licensed ( http://www.apache.org/licenses/LICENSE-2.0 )
 * Author: wangwenyuan <827287829@qq.com>
 */
ui_c.build_radio_dom = function() {
	function build(obj) {
		var html = "";
		var title = obj[0].title;
		if (obj[0].checked) {
			html = '<div class="ui-c-radio-box">' +
				'<div class="ui-c-radio-box-ring ui-c-radio-checked">' +
				'<div class="ui-c-radio-box-point"></div>' +
				'</div>' +
				'<span class="ui-c-radio-box-title">' + title + '</span>' +
				'<div class="ui-c-clear"></div>' +
				'</div>';
		} else if (obj[0].disabled) {
			html = '<div class="ui-c-radio-box ui-c-radio-disabled">' +
				'<div class="ui-c-radio-box-ring-disabled">' +
				'</div>' +
				'<span class="ui-c-radio-box-title">' + title + '</span>' +
				'<div class="ui-c-clear"></div>' +
				'</div>';
		} else {
			html = '<div class="ui-c-radio-box" onclick="ui_c.on_radio_click()">' +
				'<div class="ui-c-radio-box-ring">' +
				'</div>' +
				'<span class="ui-c-radio-box-title">' + title + '</span>' +
				'<div class="ui-c-clear"></div>' +
				'</div>';
		}
		obj.after(html);
	}
	$(".ui-c-radio-box").remove();
	var len = $(".ui-c-radio").length;
	for (var i = 0; i < len; i = i + 1) {
		var obj = $(".ui-c-radio").eq(i);
		build(obj);
	}
}

ui_c.on_radio_click = function(){
	var that = event.currentTarget;
	$(that).prev().click();
	ui_c.build_radio_dom();
}