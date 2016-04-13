<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<%@ include file="head.jsp"%>
<script type="text/javascript">
// 信息保存成功后，刷新父节点
this.parent.FormEditWin.reloadNavNode();
window.parent.FormEditWin.close();
</script>
<style type="text/css">
body{
	text-align:center;
}
</style>
</head>
<body>
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<div class="ico_wait">
</div>
<br />
<br />
<br />
<br />
<span style="font-size:14px;">请稍候...</span>
</body>
</html>