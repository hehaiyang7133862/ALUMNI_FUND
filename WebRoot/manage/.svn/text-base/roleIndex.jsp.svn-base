<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript">
var glbRootPath = "<%=basePath%>";
var glbName="<c:out value='${bean_role.roleName}' />[<c:out value='${bean_role.usercount}' />]";
var glbId="<c:out value='${bean_role.roleId}' />";
function myRefresh(){
	window.frames["myFrame"].myRefresh();
	MyFormWin.close();
}
</script>
<script type="text/javascript" src="<%=basePath%>dwr/engine.js"></script>
<script type="text/javascript" src="<%=basePath%>dwr/util.js"></script>
<!-- 需替换 -->
<script type="text/javascript" src="<%=basePath%>dwr/interface/RoleManager.js"></script>
<script type="text/javascript" src="<%=basePath%>manage/roleIndex.js"></script>
<script language="javascript">
$(document).ready(function(){
	document.getElementById("iFrameId").src="<%=basePath%>roleIFrame.action?id=<c:out value='${bean_role.roleId}' />&grandId=<c:out value='${bean_role.roleId}' />";
});
</script>
</head>
<body>
<div class="page">
<%@ include file="../common/navigation.jsp"%>
<div style="margin-bottom:5px;margin-top:2px;">
<b>用户所属角色：</b>
<c:forEach items="${list_role}" var="ele" varStatus="i">
<a href="<%=basePath %>roleList!pre.action?roleId=<c:out value='${ele.roleId}' />">
	<c:out value="${ele.roleName}" /></a>&nbsp;&nbsp;
</c:forEach>
</div>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<tr>
<td width="270" valign="top">
<div id="tree-panel" style="width:260px;height:90%;position:absolute;top:50px;left:5px;"></div>
</td>
<td valign="top">
<iframe name="myFrame" id="iFrameId" width="100%" height="100%" marginheight="0" frameborder="0"  
	src="" 
    onload="setFrmHeight(this)" ></iframe>
</td>
</tr>
</table>
</div>
</body>
</html>