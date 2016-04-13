<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>  
<title>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript">
	// 信息保存成功后，刷新父节点
	this.parent.FormEditWin.reloadNavNode();
</script>
</head>
<body>
<div class="page" style="width:600px;">
<br />
<br />
<br />
<br />
<br />
<br />
<center><h4>操作成功</h4></center>
<br />
<center onClick="window.parent.FormEditWin.close();"><b style="cursor:pointer;">关 闭</b></center>
</div>
</body>
</html>