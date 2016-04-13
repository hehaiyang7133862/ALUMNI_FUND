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
	if($("#paramName").val()==""){
		MyMsg.alertFn('请输入参数名称！',function(){
			$("#paramName").focus();
		});
		return false;
	}
	if($("#memo").val().length > 1000){
		MyMsg.alertFn('备注说明必须在1000字以内！',function(){
			$("#memo").focus();
		});
		return false;
	}
}
</script>

</head>
<body>
	<div class="float">
		<form id="formId" action="<%=basePath%>commodity!saveOrUpdateParam.action" method="post" onSubmit="return checkForm(this)">
			<input name="commId" type="hidden" value="${bean.commId}" />
			<input name="paramId" type="hidden" value="${bean.paramId}" />
			<table border="0" cellspacing="0" cellpadding="0" class="lyt_view">
				<tr>
					<td class="lyt_view_note" style="width:80px;">
						参数名称
						<span>*</span>
					</td>
					<td><input type="text" style="width:400px;" id="paramName" name="paramName" value="<c:out value='${bean.paramName}'/>" /></td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						参数值
					</td>
					<td><input type="text" style="width:400px;" name="paramValue" id="paramValue" value="<c:out value='${bean.paramValue}'/>" maxlength="200" /></td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						参数排序
					</td>
					<td><input type="text" style="width:400px;" name="numOrder" id="numOrder" value="<c:out value='${bean.numOrder}'/>" maxlength="50" /></td>
				</tr>
				<tr>
					<td class="lyt_view_area">备注</td>
					<td><textarea id="memo" name="memo" style="width:400px;height:80px;" onclick="myArea('memo',1000);" onkeyup="myArea('memo',1000);" onkeydown="myArea('memo',1000);" onchange="myArea('memo',1000);"><c:out value='${bean.memo}'/></textarea></td>
				</tr>
			</table>
			<my:timeView idField="paramId" table="TbCommodityParam" value="${bean.paramId}"></my:timeView>
			<div class="lyt_submit" style="width: 490px; text-align: right;">
				<input type="submit" value="提交" />
				<input type="button" value="取消" onclick="parent.MyFormWin.close();return false;" />
			</div>
		</form>
	</div>
</body>
</html>
