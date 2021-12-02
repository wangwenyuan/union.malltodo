<%@page import="com.javatodo.core.router.RC"%>
<%@page import="java.io.File"%>
<%@page import="common.Common"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.javatodo.core.JavaTodo" %>
<%@ page import="com.javatodo.config.C" %>
<%
File file = new File(this.getServletContext().getRealPath("/")+ "WEB-INF/Runtime/lock");
JavaTodo javaTodo = null;
if(file.exists()){
	javaTodo = new JavaTodo("index");
	Common.Init(request, this);
}else{
	javaTodo=new JavaTodo("install");
	new RC("Index", "index");
}
request.setCharacterEncoding(C.default_encoding);
response.setCharacterEncoding(C.default_encoding);
response.setHeader("Content-type", "text/html;charset="+C.default_encoding);
javaTodo.setRequestAndResponse(request, response,this);
out.clear();
out = pageContext.pushBody();
%>