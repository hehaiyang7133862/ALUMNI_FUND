<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<%=basePath%>UI/css/common.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.tmp_btn_login{
	background:url(<%=basePath%>UI/images/btn_login.gif);
	background-repeat: no-repeat;
	color: #FFFFFF!important;
	padding-right: 6px;
	padding-left: 6px;
	background-position:left top;
	height: 23px;
	line-height:23px;
	vertical-align:middle;
	width:56px;
	text-align:center;
	border:0px;
	text-decoration:none;
}
.tmp_btn_getpwd{
	background:url(<%=basePath%>UI/images/btn_getpwd.gif);
	background-repeat: no-repeat;
	color: #FFFFFF!important;
	padding-right: 6px;
	padding-left: 6px;
	background-position:left top;
	margin: 0px;
	padding-top: 0px;
	height: 23px;
	line-height:23px;
	vertical-align:middle;
	width:70px;
	text-align:center;
	border:0px;
	text-decoration:none;
}
</style>
<script language="javascript">
// 验证表单
function checkForm(){
	formFormat("formId");
	
	document.getElementById("hidId").value="0";
	document.getElementById("formId").target="_blank";
	setTimeout("mySubmit()",2000);
	return true;
}
function mySubmit(){
	document.getElementById("hidId").value="1";
	document.getElementById("formId").target="_self";
	document.getElementById("formId").submit();
}
function formFormat(sid){
	//input
	var eles1=document.getElementById(sid).getElementsByTagName("input");
	for(var i=0;i<eles1.length;i++){
		if(eles1[i].type=="text"){
			eles1[i].value=eles1[i].value.replace(/(^\s*)|(\s*$)/g, "");
			eles1[i].style.backgroundColor="";
		}
	}
	//textarea
	var eles2=document.getElementById(sid).getElementsByTagName("textarea");
	for(var i=0;i<eles2.length;i++){
		eles2[i].value=eles2[i].value.replace(/(^\s*)|(\s*$)/g, "");
		eles2[i].style.backgroundColor="";
	}
}
</script>
</head>
<body>
<c:if test="${null!=user_type}">
	<br />
	<div style="text-align:center;">
	<span style="font:14px;">欢迎您：</span>
    <span style="font:14px;"><c:out value="${user_name}" /></span>
	<br />
    <br />
    <input type="button" value="进入" onClick="window.open('<%=basePath%>common/manage.jsp','_blank');" class="tmp_btn_login" />
  	<input type="button" value="退出" class="tmp_btn_login" onClick="location.href='<%=basePath%>xyexit.action'" style="margin-left:5px;" />
    </div>
</c:if>
<c:if test="${null==user_type}">
	<br />
	<form id="formId" method="post" action="<%=basePath%>xylogin.action" onSubmit="return checkForm(this)">
	<input id="hidId" type="hidden" name="fd" value="1" />
	<table border="0" cellpadding="0" cellspacing="0" style="padding-right:8px;">
	<tr>
	<td colspan="2" align="center" valign="top">
    	<c:choose>
    	<c:when test="${null==type || type==0}">
    		<input type="radio" name="type" value="0" checked />校友
        </c:when>
		<c:otherwise>
			<input type="radio" name="type" value="0" />校友
		</c:otherwise>
		</c:choose>
        <c:choose>
    	<c:when test="${type==1}">
			<input type="radio" name="type" value="1" style="margin-left:20px;" checked />校内
        </c:when>
		<c:otherwise>
        	<input type="radio" name="type" value="1" style="margin-left:20px;" />校内
        </c:otherwise>
		</c:choose>
	</td>
	</tr>
	<tr>
    <td width="55" height="30" align="right">用 户&nbsp;&nbsp;</td>
    <td width="137" align="right">
		<input type="text" id="Token1" name="Token1" value="${Token1}" style="width:135px;" />
    </td>
    </tr>
	<tr>
    <td height="30" align="right">密 码&nbsp;&nbsp;</td>
    <td align="right">
    	<input type="password" id="Token2" name="Token2" style="width:135px;" /></td>
    </tr>
	<tr>
    <td colspan="2" align="right">
		<input type="submit" value="登录" class="tmp_btn_login"/>
		<input type="button"  value="找回密码" onClick="window.open('<%=basePath%>emailPwd!pre.action','_blank');" style="margin-left:6px;" class="tmp_btn_getpwd" />
    </td>
    </tr>
    <tr>
    <td colspan="2" align="right" height="30">
    	<img alt="" src="<%=basePath%>UI/images/notice_xylogin.gif" width="130"/>
		<input type="button" value="注册" onClick="window.open('<%=basePath%>student/registerNote.jsp','_blank');" class="tmp_btn_login" />
    </td>
    </tr>
	</table>
	</form>
</c:if>
</body>
</html>