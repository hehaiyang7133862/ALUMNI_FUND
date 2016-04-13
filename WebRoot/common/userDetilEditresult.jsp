<%@ include file="../common/include.jsp"%>
<%@ page language="java" isELIgnored="false" import="java.util.*"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<script src="<%=basePathHead%>UI/js/calendar.js" type="text/javascript"></script>
<script language="javascript">
window.opener.opener=null;
window.opener.close();
window.close();
</script>
</head>
<body>
<!-- ### -->
<div class="float" >
<center>修改成功!</center>
</div>
	</body>
</html>
