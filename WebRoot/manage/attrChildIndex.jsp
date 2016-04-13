<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*,com.laungee.proj.common.util.*" pageEncoding="UTF-8"%>
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
	var glbName="<c:out value='${bean_attr.fieldName}' />";
	var glbId="<c:out value='${bean_attr.fieldId}' />";
	var glbWidth=<%=request.getParameter("glbChildWidth")%>;
</script>
<script type="text/javascript" src="<%=basePath%>dwr/engine.js"></script>
<script type="text/javascript" src="<%=basePath%>dwr/util.js"></script>
<script type="text/javascript" src="<%=basePath%>UI/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%=basePath%>UI/ext/ext-all.js"></script>
<!-- 需替换 -->
<script type="text/javascript" src="<%=basePath%>dwr/interface/AttrManager.js"></script>
<script type="text/javascript" src="<%=basePath%>manage/attrChildIndex.js"></script>
</head>
<body>
 
</body>
</html>