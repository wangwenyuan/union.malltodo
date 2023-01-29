package common;

import java.util.Map;

public class HtmlTemplate {
	public static String text(String name, String value) {
		String html = "<div class=\"ui-c-element\"><input type=\"text\" class=\"ui-c-input\" name=\"" + name + "\" value=\"" + value + "\" id=\"" + name + "\" /></div>";
		return html;
	}

	public static String password(String name, String value) {
		String html = "<div class=\"ui-c-element\"><input type=\"password\" class=\"ui-c-input\" name=\"" + name + "\" value=\"" + value + "\" id=\"" + name + "\" /></div>";
		return html;
	}

	public static String textarea(String name, String value) {
		String html = "<textarea name=\"" + name + "\" id=\"" + name + "\" class=\"input_textarea\">" + value + "</textarea>";
		return html;
	}

	public static String date(String name, String value) {
		String html = "<input type=\"text\" class=\"ui-c-input\" style=\"width:225px;\" name=\"" + name + "\" value=\"" + value + "\" id=\"" + name + "\" />";
		html = html + "<script>\r\n" + "    	    $(\"#" + name + "\").jeDate({\r\n" + "                multiPane:true,\r\n" + "                onClose:false,\r\n" + "                minDate: \"1900-01-01 00:00:00\", //\r\n" + "                maxDate: \"2099-12-31 23:59:59\", //\r\n" + "                format: \"YYYY-MM-DD hh:mm:ss\"\r\n" + "            });\r\n" + "    	</script>";
		return html;
	}

	public static String upload(String name, String value) {
		String html = "<input type=\"file\" id=\"" + name + "_javatodo_upload_file\" accept=\"image/*\" multiple style=\"display:none\" />";
		if (value == "") {
			html = html + "<div id=\"" + name + "_javatodo_upload_file_outerbox\" onclick=\"$('#" + name + "_javatodo_upload_file').click()\" style=\"width: 100px; height:100px; background: center center no-repeat; background-size: cover; background-image: url(./Public/images/upload.jpg);\">";
		} else {
			html = html + "<div id=\"" + name + "_javatodo_upload_file_outerbox\" onclick=\"$('#" + name + "_javatodo_upload_file').click()\" style=\"width: 100px; height:100px; background: center center no-repeat; background-size: cover; background-image: url(" + value + ");\">";
		}
		html = html + "<input type=\"hidden\" name=\"" + name + "\" id=\"" + name + "\" value=\"" + value + "\" />";
		html = html + "</div>";
		html = html + "<script>";
		html = html + "$(\"#" + name + "_javatodo_upload_file\").change(function () {";
		// 创建FormData对象
		html = html + "var data =new FormData();";
		// 为FormData对象添加数据
		html = html + "$.each($('#" + name + "_javatodo_upload_file')[0].files, function(i, file) {";
		html = html + "data.append('imgFile', file);";
		html = html + "});";
		html = html + "var objUrl = getObjectURL(this.files[0]);";
		html = html + "if (objUrl) {";
		html = html + "$.ajax({";
		html = html + "url:\"upload链接\",";
		html = html + "type:'post',";
		html = html + "data:data,";
		html = html + "dataType: \"json\",";
		html = html + "cache: false,";
		html = html + "contentType: false,";
		html = html + "processData: false,";
		html = html + "success:function(data){";
		html = html + "$('#" + name + "').val(data.url);";
		html = html + "$('#" + name + "_javatodo_upload_file_outerbox').css({'background-image':'url('+data.url+')'});";
		html = html + "},";
		html = html + "error:function(){";
		html = html + "alert('上传出错');";
		html = html + "}";
		html = html + "});";
		html = html + "}else {";
		html = html + "alert(\"没有获取到要上传的文件信息\");";
		html = html + "}";
		html = html + "});";
		html = html + "</script>";
		return html;
	}

	public static String editor(String name, String value) {
		String html = "<script id=\"" + name + "\" name=\"" + name + "\" type=\"text/plain\">\n" + value + "\n</script>\n";
		html = html + "<script type=\"text/javascript\">\nvar ue = UE.getEditor(\"" + name + "\", {\n    serverUrl: \"controller链接\"\n});\n</script>";
		return html;
	}

	public static String select(String name, String value, Map<String, String> option_map) {
		String html = "<div class=\"ui-c-element\"><select class=\"ui-c-select\" id=\"" + name + "\" name=\"" + name + "\">";
		for (String key : option_map.keySet()) {
			if (key.equals(value)) {
				html = html + "<option selected=\"selected\" value=\"" + key + "\">" + option_map.get(key) + "</option>";
			} else {
				html = html + "<option value=\"" + key + "\">" + option_map.get(key) + "</option>";
			}
		}
		html = html + "</select></div>";
		return html;
	}

	public static String date_part(String name, String start_datetime, String end_datetime) {
		String html = "<input type=\"text\" class=\"ui-c-input\" style=\"width:200px;\" name=\"" + name + "_1\" value=\"" + start_datetime + "\" id=\"" + name + "_1\"  placeholder=\"开始时间\" />-";
		html = html + "<input type=\"text\" class=\"ui-c-input\" style=\"width:200px;\" name=\"" + name + "_2\" value=\"" + end_datetime + "\" id=\"" + name + "_2\" placeholder=\"结束时间\" />";
		html = html + "<script>\r\n" + "    	    $(\"#" + name + "_1\").jeDate({\r\n" + "                multiPane:true,\r\n" + "                onClose:false,\r\n" + "                minDate: \"1900-01-01 00:00:00\", //最小日期\r\n" + "                maxDate: \"2099-12-31 23:59:59\", //最大日期\r\n" + "                format: \"YYYY-MM-DD hh:mm:ss\"\r\n" + "            });\r\n" + "            $(\"#" + name + "_2\").jeDate({\r\n" + "                multiPane:true,\r\n" + "                onClose:false,\r\n" + "                minDate: \"1900-01-01 00:00:00\", //最小日期\r\n" + "                maxDate: \"2099-12-31 23:59:59\", //最大日期\r\n" + "                format: \"YYYY-MM-DD hh:mm:ss\"\r\n" + "            });\r\n" + "    	</script>";
		return html;
	}

	public static String address(String province_id, String province_id_val, String city_id, String city_id_val, String area_id, String area_id_val) {
		String html = "省份：<select name=\"" + province_id + "\" id=\"" + province_id + "\" class=\"input_select\"></select> &nbsp;&nbsp; 城市：<select name=\"" + city_id + "\" id=\"" + city_id + "\" class=\"input_select\"></select> &nbsp;&nbsp; 区县：<select name=\"" + area_id + "\" id=\"" + area_id + "\" class=\"input_select\"></select>";
		html = html + "<script>";
		html = html + "malltodoJs.address.init(\"" + province_id + "\", \"" + province_id_val + "\", \"" + city_id + "\", \"" + city_id_val + "\", \"" + area_id + "\", \"" + area_id_val + "\");";
		html = html + "</script>";
		return html;
	}

}
