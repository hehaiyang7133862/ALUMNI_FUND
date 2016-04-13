<%@ include file="include.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="head.jsp"%>
<link href="<%=basePathHead%>common/manageTop.css" rel="stylesheet" type="text/css" />
<link href="manageTop.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript">

</script>
</head> 
<body style="background-color:transparent;">
				<c:forEach items="${list_menu}" var="tmp" varStatus="status">
				<c:if test="${fn:contains(role_menu,tmp.code)}">
				<div class="header_menus">
	                 <a href="<%=basePath%>frame!left.action?id=${tmp.menuId}" target="manageLeft"  onClick="parent.MyMask.showHtml('正在加载数据,请稍候..');" ><span class="fnt_span"><c:out value="${tmp.menuName}" /></span></a>
	             </div>
	             </c:if>
</c:forEach>
				


</body>
</html>