<%
String pathHead = request.getContextPath();
String basePathHead = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathHead+"/";
%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!-- 自定义 -->
<link href="<%=basePathHead%>UI/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=basePathHead%>UI/css/menu.css" rel="stylesheet" type="text/css" />
<script src="<%=basePathHead%>UI/js/jquery.js" type="text/javascript"></script>
<script src="<%=basePathHead%>UI/js/common.js" type="text/javascript"></script>
<!-- EXT -->
<link href="<%=basePathHead%>UI/ext/resources/css/ext-all.css" type="text/css" rel="stylesheet" />
<script src="<%=basePathHead%>UI/ext/adapter/ext/ext-base.js" type="text/javascript"></script>
<script src="<%=basePathHead%>UI/ext/ext-all.js" type="text/javascript"></script>
<script src="<%=basePathHead%>UI/js/ext-my.js" type="text/javascript"></script>
<!-- TEMP -->
<link href="../UI/css/common.css" rel="stylesheet" type="text/css" />
<link href="../UI/css/menu.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body{
	background-color:#DFDFDF;
}
</style>
<c:if test="${not empty alert}">
<script language="javascript">
$(document).ready(function(){
	alert('<c:out value="${alert}" />');
});
</script>
</c:if>