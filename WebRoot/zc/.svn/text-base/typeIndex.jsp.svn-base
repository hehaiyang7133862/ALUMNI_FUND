<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<link   type="text/css" href="<%=basePath%>UI/ext/resources/css/ext-all.css" rel="stylesheet" />
<script type="text/javascript">
	var glbPath = "<%=basePath%>";
	var glbId="${bean.typeId}";
	var glbName="众筹项目分类";
</script>
<script type="text/javascript" src="<%=basePath%>dwr/engine.js"></script>
<script type="text/javascript" src="<%=basePath%>dwr/util.js"></script>

<!-- 需替换 -->
<script type="text/javascript" src="<%=basePath%>dwr/interface/ZcProjTypeManager.js"></script>
<script type="text/javascript" src="<%=basePath%>zc/typeIndex.js"></script>

</head>
<body>
<div class="page">
<%@ include file="../common/navigation.jsp"%>
<div id="tree-panel" style="margin-left:5px;width:750px;"></div>
</div>
</body>
</html>
