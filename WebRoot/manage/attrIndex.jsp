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
	var glbRootPath = "<%=basePath%>";
	var glbWidth=220;
	var glbChildWidth=300;
window.onload=function(){
    var width=document.body.clientWidth;
    document.getElementById("iFrame2Id").width=width-glbWidth-10;
    glbChildWidth=width-glbWidth-10-10;
}
</script>
<script type="text/javascript" src="<%=basePath%>dwr/engine.js"></script>
<script type="text/javascript" src="<%=basePath%>dwr/util.js"></script>
<script type="text/javascript" src="<%=basePath%>UI/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%=basePath%>UI/ext/ext-all.js"></script>
<!-- 需替换 -->
<script type="text/javascript" src="<%=basePath%>dwr/interface/AttrManager.js"></script>
<script type="text/javascript" src="<%=basePath%>manage/attrIndex.js"></script>
</head>
<body>
<div class="page">
<%@ include file="../common/navigation.jsp"%>
<%@ include file="menuInfo.jsp"%>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<tr>
<td width="40%">
    <div id="tree-panel1" style="width:100%;height:100%;margin-left:5px;">
	</div>
</td>
<td width="60%">
	<iframe id="iFrame2Id" width="300" height="100%" style="float:right;" marginheight="0" frameborder="0"  
	src=""></iframe>
</td>
</tr>
</table>
</div>
</body>
</html>
