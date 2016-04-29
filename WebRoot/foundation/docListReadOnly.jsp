<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<%@ include file="../common/head.jsp"%>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<div class="page">
		<%@ include file="../common/navigation.jsp"%>
		<c:choose>
		<c:when test="${not empty foundId}">
		<%@ include file="foundationMenuReadOnly.jsp"%>
		<iframe class="frm_file" style="height: 82%;width: 96%;margin:6px 6px;" marginheight="0" frameborder="0" src="<%=basePath %>fileListReadOnly.action?name=TbFoundationDoc&ele=updateId&id=${foundId}"></iframe>
		</c:when>
		<c:otherwise>
			<div style="font-size: 16px;padding: 10px;">请先创建项目</div>
		</c:otherwise>
		</c:choose>
		</div>
	</body>
</html>