<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.javatodo.core.tools.T"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("PUBLIC") %>/admin/css/css.css"/>
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/js/jquery.slimscroll.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("PUBLIC") %>/admin/css/pintuer.css" />
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/js/jquery.cookie.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("PUBLIC") %>/admin/css/zTreeStyle/zTreeStyle.css" />
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/js/jquery.ztree.all.min.js"></script>
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/js/layer.js"></script>
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/js/js.js"></script>
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/js/http.js"></script>
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/js/drop.js"></script>
<link rel="stylesheet" href="<%=request.getAttribute("PUBLIC") %>/admin/editor/themes/default/default.css" />
<link rel="stylesheet" href="<%=request.getAttribute("PUBLIC") %>/admin/editor/plugins/code/prettify.css" />
<script charset="utf-8" src="<%=request.getAttribute("PUBLIC") %>/admin/editor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="<%=request.getAttribute("PUBLIC") %>/admin/editor/lang/zh-CN.js"></script>
<script charset="utf-8" src="<%=request.getAttribute("PUBLIC") %>/admin/editor/plugins/code/prettify.js"></script>
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/admin/jedate/jquery.jedate.js"></script>
<link type="text/css" rel="stylesheet" href="<%=request.getAttribute("PUBLIC") %>/admin/jedate/skin/jedate.css">
<link href="<%=request.getAttribute("PUBLIC") %>/ui-c/form/form.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getAttribute("PUBLIC") %>/ui-c/form/select.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getAttribute("PUBLIC") %>/ui-c/form/radio.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getAttribute("PUBLIC") %>/ui-c/form/checkbox.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getAttribute("PUBLIC") %>/ui-c/form/open.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getAttribute("PUBLIC") %>/ui-c/ui-c.js"></script>
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/ui-c/form/form.js"></script>
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/ui-c/form/select.js"></script>
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/ui-c/form/radio.js"></script>
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/ui-c/form/checkbox.js"></script>
<script type="text/javascript" src="<%=request.getAttribute("PUBLIC") %>/ui-c/form/open.js"></script>
</head>

<body style="background-color: #FFFFFF;">
<div id="inner_content">