<%
String pathKindEditor = request.getContextPath();
String basePathKindEditor = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathKindEditor+"/";
%>
<%@ page language="java" pageEncoding="UTF-8"%>
<script language="JavaScript" type="text/javascript" src="<%=basePathKindEditor%>UI/kindeditor/kindeditor-min.js"></script>