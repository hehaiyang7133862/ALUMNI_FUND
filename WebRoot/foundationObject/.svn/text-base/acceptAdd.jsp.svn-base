<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<style>
</style>
		<%@ include file="../common/head.jsp"%>
		<%@ include file="../common/calendar.jsp"%>
		<script type="text/javascript"
			src="<%=basePath%>UI/kindeditor/kindeditor-min.js"></script>
		<script type="text/javascript" src="<%=basePath%>UI/js/kind-editor.js"></script>
		<script type="text/javascript">
// 验证表单
function checkForm(form){
	formFormat("formId");
	if($("#comName").val() == ""){
		$("#comName").focus();
		form.comName.style.backgroundColor=error;
		return false;
	}
	if($("#handset").val() == ""){
		$("#handset").focus();
		form.handset.style.backgroundColor=error;
		return false;
	}
	return true;
	
}
function getByteLen(str) {    //传入一个字符串
   var len = 0;
   if(typeof(str)!="undefined"){
	   var len=0;
		if(str =='') {
			return len;
		} else {
			var out = str.replace(/[^\x00-\xff]/g,'**');
			len=out.length;
			return len;
		}
   }
   return len;
}
$(document).ready(function(){
});
</script>
	</head>
	<body>
		<div class="page">
		<form id="formId" name="formId" method="post" action="<%=basePath%>donate!saveOrUpdateAccept.action" onsubmit="return checkForm(this)">
			<input type="hidden" name="comId" value="${comId}" style="display: block;" />
			<table class="lyt_view">
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						单位名称
					</td>
					<td style="width: 160px;" colspan="3">
						<input style="width: 155px;" type="text" name="comName" id="comName" value='<c:out value="${bean.comName}" />' />
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						账户名称
					</td>
					<td style="width: 160px;">
						<input style="width: 155px;" type="text" name="accountName" id="accountName" value='<c:out value="${bean.accountName}" />' />
					</td>
					<td class="lyt_view_note" style="width: 80px;">
						账号
					</td>
					<td>
						<input style="width: 155px;" type="text" name="accountNum" id="accountNum" value='<c:out value="${bean.accountNum}" />' />
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						单位负责人
					</td>
					<td style="width: 160px;">
						<input style="width: 155px;" type="text" name="responseName" id="responseName" value='<c:out value="${bean.responseName}" />' maxlength="100"/>
					</td>
					<td class="lyt_view_note" style="width: 80px;">
						联系电话
					</td>
					<td style="width: 160px;">
						<input style="width: 155px;" type="text" name="responseTel" id="responseTel" value='<c:out value="${bean.responseTel}" />' maxlength="100"/>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						联系手机
					</td>
					<td style="width: 160px;">
						<input style="width: 155px;" type="text" name="responseHandset" id="responseHandset" value='<c:out value="${bean.responseHandset}" />' maxlength="100"/>
					</td>
					<td class="lyt_view_note" style="width: 80px;">
						联系邮箱
					</td>
					<td style="width: 160px;">
						<input style="width: 155px;" type="text" name="responseMail" id="responseMail" value='<c:out value="${bean.responseMail}" />' maxlength="100"/>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						单位联系人
					</td>
					<td style="width: 160px;">
						<input style="width: 155px;" type="text" name="relationName" id="relationName" value='<c:out value="${bean.relationName}" />' maxlength="100"/>
					</td>
					<td class="lyt_view_note" style="width: 80px;">
						联系电话
					</td>
					<td style="width: 160px;">
						<input style="width: 155px;" type="text" name="relationTel" id="relationTel" value='<c:out value="${bean.relationTel}" />' maxlength="100"/>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						联系手机
					</td>
					<td style="width: 160px;">
						<input style="width: 155px;" type="text" name="relationHandset" id="relationHandset" value='<c:out value="${bean.relationHandset}" />' maxlength="100"/>
					</td>
					<td class="lyt_view_note" style="width: 80px;">
						联系邮箱
					</td>
					<td style="width: 160px;">
						<input style="width: 155px;" type="text" name="relationMail" id="relationMail" value='<c:out value="${bean.relationMail}" />' maxlength="100"/>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px; vertical-align: top; padding-top: 10px;">
						备注
					</td>
					<td colspan="3">
						<textarea name="memo"><c:out value="${bean.memo}" /></textarea>
					</td>
				</tr>
			</table>
			<my:timeView idField="comId" table="TbAcceptCompany" value="${bean.comId}"></my:timeView>
			<div class="lyt_submit" style="width: 550px; text-align: right;">
				<input type="submit" class="btn_save" value="提交" />
				&nbsp;
				<input type="button" onclick="parent.MyFormWin.close();" value="取消" />
			</div>
		</form>
		</div>
	</body>
</html>