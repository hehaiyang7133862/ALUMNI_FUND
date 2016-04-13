<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<%@ include file="head.jsp"%>
<script type="text/javascript">
function myLoad(){
	top.location.href="<%=basePath%>common/login.jsp";
}
window.onload=function (){
	myLoad();
}
</script>
</head>
<body>

</body>
</html>
