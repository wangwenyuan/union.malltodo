<%@page import="union.Meituan"%>
<%@page import="com.javatodo.core.tools.T"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="com.javatodo.core.router.RC"%>
<%@page import="java.io.File"%>
<%@page import="common.Common"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.javatodo.core.JavaTodo" %>
<%@ page import="com.javatodo.config.C" %>
<%
JSONObject ret = new JSONObject();
File file = new File(this.getServletContext().getRealPath("/")+ "WEB-INF/Runtime/lock");
if(file.exists()){
	Common.Init(request, this);
	if (C.is_debug && C.log_file_path.equals("")) {
		C.log_file_path = this.getServletContext().getRealPath("/") + "WEB-INF/Runtime/log/";
	}
	if (C.cache_path.equals("")) {
		C.cache_path = this.getServletContext().getRealPath("/") + "WEB-INF/Runtime/cache/";
	}
	request.setCharacterEncoding(C.default_encoding);
	response.setCharacterEncoding(C.default_encoding);
	response.setHeader("Content-type", "text/html;charset="+C.default_encoding);
	if(request.getMethod().toUpperCase().equals("POST")){
		String callback_information = T.getPostData(request.getInputStream(), request.getContentLength(), C.default_encoding);
		T.create_log("meituan_order.txt", "notify:"+callback_information);
		JSONObject object = JSONObject.parseObject(callback_information);
		if(object != null){
			final Integer actId = T.toInt(object.getString("actId"));
			long time = object.getLongValue("paytime");
			final String start_time = T.date("yyyy-MM-dd HH:mm:ss", time * 1000 - 15 * 60 * 1000);
			final String end_time = T.date("yyyy-MM-dd HH:mm:ss",  time * 1000 + 15 * 60 * 1000);
			new Thread(new Runnable() {
				public void run() {
					Meituan meituan = new Meituan();
					meituan.getOrderList(actId, start_time, end_time, 1);
				}
			}).start();
			ret.put("errcode", "0");
			ret.put("errmsg", "ok");
			out.println(ret.toJSONString());
			return ;
		}else{
			ret.put("errcode", "1");
			ret.put("errmsg", "err");
			out.println(ret.toJSONString());
			return ;
		}
	}else{
		ret.put("errcode", "1");
		ret.put("errmsg", "err");
		out.println(ret.toJSONString());
		return ;
	}
}else{
	ret.put("errcode", "1");
	ret.put("errmsg", "err");
	out.println(ret.toJSONString());
	return ;
}

%>