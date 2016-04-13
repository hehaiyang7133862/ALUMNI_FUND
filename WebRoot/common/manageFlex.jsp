<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<script src="<%=basePath%>UI/js/jquery.js" type="text/javascript" ></script>
<script src="<%=basePath%>UI/js/common.js" type="text/javascript"></script>
<script src="<%=basePath%>common/manageFlex.js" type="text/javascript" ></script>
<script language="javascript">
var glbRootPath="<%=basePath%>";
</script>
<style type="text/css">
<!--
body {
	background: #F2F9FD;
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 2px;
	margin-bottom: 0px;
}
-->
</style>

</head>
<body scroll="no">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="280">&nbsp;</td>
  </tr>
  <tr>
    <td valign="top">
    <img border="0" src="<%=basePath%>UI/images/flex_close.png" id="closeId" alt="隐藏左栏" style="cursor:pointer" onClick="switchLeft(1)"
    	 onMouseOver="switchLeft(2)" onMouseOut="switchLeft(3)" /></td>
  </tr>
</table>
</body>
</html>
