<%@ include file="../common/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<link   type="text/css" href="<%=basePath%>UI/ext/resources/css/ext-all.css" rel="stylesheet" />
<script type="text/javascript">
	var glbRootPath = "<%=basePath%>";
	var sid=0;
	var glbName=""
	var glbWidth=220;
</script>
<script type="text/javascript">var glbRootPath = "<%=basePath%>";</script>
<script type="text/javascript" src="<%=basePath%>dwr/engine.js"></script>
<script type="text/javascript" src="<%=basePath%>dwr/util.js"></script>
<script type="text/javascript" src="<%=basePath%>UI/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%=basePath%>UI/ext/ext-all.js"></script>
<!-- 需替换 -->
<script type="text/javascript" src="<%=basePath%>dwr/interface/ButtonManager.js"></script>
<script type="text/javascript" src="<%=basePath%>manage/buttonIndex.js"></script>
</head>
<body>
<%@ include file="../common/navigation.jsp"%>
<%@ include file="menuInfos.jsp"%>

</body>
</html>