<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="header.jsp"%>
            <div style="padding:15px;" id="malltodo_notice"></div>
            <script>
            http.get("./admin.jsp?m=Index&c=Index&a=main", function(data){
            	$("#malltodo_notice").html(data);
            }, "text")
            </script>
<%@include file="bottom.jsp"%>