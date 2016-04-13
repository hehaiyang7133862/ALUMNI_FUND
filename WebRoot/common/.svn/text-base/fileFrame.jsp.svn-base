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
<script language="javascript">
function checkForm(form){
	formFormat("formId");
	var msg="";
	// 上传文件
	if(form.upload.value==""){
		msg+="请选择上传文件\n";
	}
	// 返回
	if(msg!=""){
		alert(msg);
		return false;
	}
	else{
		MyMask.show();
		return true;
	}
}
function myLoad(){
	if("0"=="${fd}"){
		window.parent.document.getElementById("updateId").value="${id}";
	}
	MyMask.hide();
}
</script>
<style type="text/css">
<!--
body,td,th {
	font-size: 12px;
	line-height:18px;
}
-->
</style>
</head>
<body onLoad="myLoad()">
<form id="formId" enctype="multipart/form-data" action="<%=basePath%>fileLoad.action" method="post" onSubmit="return checkForm(this)">
<input name="tab" type="hidden" value="<c:out value='${tab}'></c:out>" />
<input name="id" type="hidden"   value="<c:out value='${id}'></c:out>" />
<input name="fd" type="hidden"  value="<c:out value='${fd}'></c:out>" />
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<tr>
<td>
	<input name="upload" type="file" style="height:21px;" /><input type="submit" value="上传" style="width:50px;height:21px;" />
</td>
</tr>
<c:forEach items="${list_file}" var="tmp" varStatus="status">
<tr>
<td>
	<c:out value="${status.count}" />.
    <a href="<%=basePath%>fileDown.action?delsId=<c:out value='${tmp.tbFile.fileId}'></c:out>&tab=<c:out value='${tab}'></c:out>&id=<c:out value='${id}'></c:out>&fd=<c:out value='${fd}'></c:out>">
    	<c:out value="${tmp.tbFile.fileName}" />
    </a>
    (
    <c:choose>
    <c:when test="${tmp.tbFile.fileSize<1024}" >
        <fmt:formatNumber value="${tmp.tbFile.fileSize}" pattern="#.##" type="number"/>B
    </c:when>
    <c:when test="${tmp.tbFile.fileSize<(1024*1024)}" >
        <fmt:formatNumber value="${tmp.tbFile.fileSize/1024}" pattern="#.##" type="number"/>KB
    </c:when>
    <c:otherwise>
        <fmt:formatNumber value="${tmp.tbFile.fileSize/(1024*1024)}" pattern="#.##" type="number"/>MB
    </c:otherwise>
    </c:choose>          
    )
    <a href="<%=basePath%>fileList!del.action?delsId=<c:out value='${tmp.relId}'></c:out>&tab=<c:out value='${tab}'></c:out>&id=<c:out value='${id}'></c:out>&fd=<c:out value='${fd}'></c:out>">删除</a>
</td>
</tr>
</c:forEach>
</table>
</form>
</body>
</html>