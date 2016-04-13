<%@ include file="../common/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript">
	var glbPath = "<%=basePath%>";
	var glbId=<c:out value='${bean_menu.menuId}' />;
	var glbName="<c:out value='${bean_menu.menuName}' /> [<c:out value='${bean_menu.code}' />]";
	var glbWidth=500;
	var glbCode="<%=request.getParameter("code")%>";
</script>
<link   type="text/css" href="<%=basePath%>UI/ext/resources/css/ext-all.css" rel="stylesheet" />
<script type="text/javascript">var glbRootPath = "<%=basePath%>";</script>
<script type="text/javascript" src="<%=basePath%>dwr/engine.js"></script>
<script type="text/javascript" src="<%=basePath%>dwr/util.js"></script>

<!-- 需替换 -->
<script type="text/javascript" src="<%=basePath%>dwr/interface/MenuManager.js"></script>
<script type="text/javascript" src="<%=basePath%>manage/menuIndex.js"></script>
</head>
<body>
<div class="page">
<%@ include file="../common/navigation.jsp"%>
<%@ include file="menuInfos.jsp"%>
<div id="tree-panel" style="margin-left:5px;width:750px;"></div>
</div>
</body>
</html>