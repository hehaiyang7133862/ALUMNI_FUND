<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<script language="javascript">
</script>
</head>
<body>
<div class="page">
<iframe width="100%" height="100%" scrolling="auto" frameborder="0" src="<%=basePath %>fileList.action?tab=TbManage&id=-1"></iframe>
</div>
</body>
</html>