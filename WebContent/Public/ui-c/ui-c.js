/*
 * UI-C
 * Copyright (c) 2022 http://ui-c.com All rights reserved.
 * Licensed ( http://www.apache.org/licenses/LICENSE-2.0 )
 * Author: wangwenyuan <827287829@qq.com>
 */
var ui_c = new Object();

ui_c.render = function(){
	ui_c.build_select_dom();
	ui_c.build_radio_dom();
	ui_c.build_checkbox_dom();
	ui_c.build_open_dom();
	//ui_c.build_scrollbar_dom();
	//ui_c.build_resizebox_dom();
}

$(function(){
	ui_c.render();
})






