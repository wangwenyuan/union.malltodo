/*
 * UI-C
 * Copyright (c) 2022 http://ui-c.com All rights reserved.
 * Licensed ( http://www.apache.org/licenses/LICENSE-2.0 )
 * Author: wangwenyuan <827287829@qq.com>
 */
ui_c.build_select_dom = function() {
	function build(obj) {
		var html = "<div class=\"ui-c-select-arrow\" onclick=\"ui_c.on_select_click()\">▼</div>";
		var text = obj.find("option:selected").text();
		if (obj.prop("disabled")) {
			html = html + "<input readonly type=\"text\" value=\"" + text +
				"\" class=\"ui-c-select-title\" placeholder=\"请选择\" />";
		} else {
			html = html + "<input readonly type=\"text\" value=\"" + text +
				"\" class=\"ui-c-select-title\" onclick=\"ui_c.on_select_click()\" placeholder=\"请选择\" />";
		}
		html = html + "<div class=\"ui-c-select-box\">";
		var subObjs = obj.children();
		var len = subObjs.length;
		for (var i = 0; i < len; i = i + 1) {
			var o = subObjs.eq(i);
			if (o[0].tagName.toLowerCase() == "optgroup") {
				var text = o[0].label;
				html = html + "<div class=\"ui-c-optgroup\">" + text + "</div>";
				var so = o.children();
				for (var n = 0; n < so.length; n = n + 1) {
					var s = so.eq(n);
					var text = s[0].text;
					var value = s[0].value;
					var selected = "";
					var option_null = "";
					if (s[0].selected) {
						selected = "ui-c-option-selected";
					}
					if (value == "") {
						option_null = "ui-c-option-null";
					}
					if(selected != ""){
						option_null = "";
					}
					if (s[0].disabled) {
						html = html + "<div class=\"ui-c-option " + selected + " " + option_null + " ui-c-option-disabled\">" + text +
							"</div>";
					} else {
						html = html + "<div class=\"ui-c-option " + selected + " " + option_null + "\" data-value=\"" + value +
							"\" onclick=\"ui_c.on_option_click()\">" + text + "</div>"
					}
				}
			} else if (o[0].tagName.toLowerCase() == "option") {
				var text = o[0].text;
				var value = o[0].value;
				var selected = "";
				var option_null = "";
				if (o[0].selected) {
					selected = "ui-c-option-selected";
				}
				if (value == "") {
					option_null = "ui-c-option-null";
				}
				if(selected != ""){
					option_null = "";
				}
				if (o[0].disabled) {
					html = html + "<div class=\"ui-c-option " + selected + " " + option_null + " ui-c-option-disabled\">" + text +
						"</div>";
				} else {
					html = html + "<div class=\"ui-c-option " + selected + " " + option_null + "\" data-value=\"" + value +
						"\" onclick=\"ui_c.on_option_click()\">" + text + "</div>"
				}
			}
		}
		html = html + "</div>";
		html = "<div class=\"ui-c-select-div\">" + html + "</div>";
		obj.after(html);
	}
	$(".ui-c-select-div").remove();
	var len = $(".ui-c-select").length;
	for (var i = 0; i < len; i = i + 1) {
		var obj = $(".ui-c-select").eq(i);
		build(obj);
	}
}

ui_c.on_select_click = function() {
	var that = event.currentTarget;
	if ($(that).siblings(".ui-c-select-box").css("display") == "none") {
		//如果当前select未展开，先关闭其他已经展开的select
		$(".ui-c-select-box").css({
			"display": "none"
		})
		$(that).siblings(".ui-c-select-box").css({
			"display": "block"
		});
	} else {
		$(that).siblings(".ui-c-select-box").css({
			"display": "none"
		});
	}
}

ui_c.on_option_click = function() {
	var that = event.currentTarget;
	$(that).siblings().removeClass("ui-c-option-selected");
	$(that).addClass("ui-c-option-selected");
	var v = $(that).data("value");
	var text = $(that).text();
	$(that).parent().parent().prev(".ui-c-select").val(v);
	$(that).parent().parent().prev(".ui-c-select").click();
	$(that).parent().parent().prev(".ui-c-select").change();
	/* 局部更新模式
	$(that).parent(".ui-c-select-box").prev(".ui-c-select-title").val(text);
	$(that).parent(".ui-c-select-box").hide();
	*/
	ui_c.build_select_dom(); //全局更新
}
