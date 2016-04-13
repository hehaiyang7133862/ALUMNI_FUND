<%@ include file="../common/include.jsp"%>
<%@ page language="java"
	import="java.util.*,com.laungee.proj.common.util.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<!-- kindeditor -->
<script src="<%=basePathHead%>UI/kindeditor/kindeditor.js" type="text/javascript"></script>
<script language="javascript">
function checkForm(form){
	formFormat("formId");
	var msg="";
	// 名称
	if(form.paramName.value==""){
		msg+="请输入参数名称\n";
		form.paramName.style.backgroundColor=error;
	}
	// 备注
	if(!(getLength(form.memo.value)<=2000)){
		msg+="备注不能超过1000个字\n";
		form.memo.style.backgroundColor=error;
	}
	// 返回
	if(msg!=""){
		alert(msg);
		return false;
	}
	else{
		return true;
	}
}
</script>

</head>
<body>
	<div class="float">
		<form id="formId" action="<%=basePath%>found!saveOrUpdateParam.action"
			method="post" onSubmit="return checkForm(this)">
			<input type="hidden" name="foundId" value="<c:out value='${foundId}' />" />
			<input name="proId" type="hidden" value="<c:out value='${proId}' />" />
			<input name="paramId" type="hidden" value="<c:out value='${bean.paramId}' />" />
			<input name="proType" type="hidden" value="<c:out value='${proType}' />" />
			<input name="num" type="hidden" value="<c:out value='${num}' />" />
			<table border="0" cellspacing="0" cellpadding="0" class="lyt_view">
				<tr>
					<td class="lyt_view_note">参数名称</td>
					<td><input type="text" id="paramName" name="paramName" value="<c:out value='${bean.paramName}'/>" /><span class="fnt_warn">*</span></td>
				</tr>
				<tr>
					<td class="lyt_view_note">参数值</td>
					<td><input type="text" name="paramValue" id="paramValue" value="<c:out value='${bean.paramValue}'/>"
						maxlength="200" /></td>
				</tr>
				<tr>
					<td class="lyt_view_area">备注</td>
					<td><textarea name="memo"><c:out value='${bean.memo}'/></textarea></td>
				</tr>
			</table>
			<div class="lyt_submit">
				<input type="submit" value="保存" /> <input type="button" value="取消"
					onClick="if(confirm('您确定要放弃此操作吗？'))parent.location='<%=basePath%>found!protocolParam.action?commId=${commId}&num=${num}';" />
			</div>
		</form>
	</div>
</body>
</html>
