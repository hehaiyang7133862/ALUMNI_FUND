<%@ include file="include.jsp"%>
<%
String pathHead = request.getContextPath();
String basePathHead = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathHead+"/";
%>
<script language="javascript">
var glbPath="<%=basePathHead%>";
</script>
<%@ page language="java" pageEncoding="UTF-8"%>
<title><my:titleTag/>综合业务管理系统</title>
<!-- JQUERY -->
<script src="<%=basePathHead%>UI/jquery/jquery-1.6.2.min.js" type="text/javascript"></script>
<script src="<%=basePathHead%>UI/jquery/json.js" type="text/javascript"></script>
<!-- EXT -->
<link href="<%=basePathHead%>UI/ext/resources/css/ext-all.css" type="text/css" rel="stylesheet" />
<script src="<%=basePathHead%>UI/ext/adapter/ext/ext-base.js" type="text/javascript"></script>
<script src="<%=basePathHead%>UI/ext/ext-all.js" type="text/javascript"></script>
<script src="<%=basePathHead%>UI/ext/adapter/treenodecheckui/TreeCheckNodeUI.js" type="text/javascript"></script>
<script src="<%=basePathHead%>UI/js/ext-my.js" type="text/javascript"></script>

<!-- 自定义 -->
<link href="<%=basePathHead%>UI/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=basePathHead%>UI/css/menu.css" rel="stylesheet" type="text/css" />
<link href="<%=basePathHead%>UI/css/com_nav.css" rel="stylesheet" type="text/css" />
<script src="<%=basePathHead%>UI/js/common.js" type="text/javascript"></script>
<script src="<%=basePathHead%>UI/js/jquery.colorbox.js" type="text/javascript"></script>
<script src="<%=basePathHead%>UI/calendar/WdatePicker.js" type="text/javascript"></script>

<script src="<%=basePathHead%>UI/js/window-resize.js" type="text/javascript"></script>

<link href="<%=basePathHead%>UI/tableopt/css.css" rel="stylesheet" type="text/css" />
<script src="<%=basePathHead%>UI/tableopt/opt-all.js" type="text/javascript"></script>

<!-- TEMP -->
<link href="../UI/css/common.css" rel="stylesheet" type="text/css" />
<link href="../UI/css/menu.css" rel="stylesheet" type="text/css" />
<c:if test="${not empty alert}">
<script language="javascript">
Ext.onReady(function(){
	MyMsg.alert("<c:out value='${alert}' />");
});
</script>
</c:if>