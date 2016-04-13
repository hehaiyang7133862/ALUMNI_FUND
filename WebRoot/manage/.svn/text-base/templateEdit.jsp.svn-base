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
		<script type="text/javascript" src="<%=basePath%>UI/kindeditor/kindeditor-min.js"></script>
		<script type="text/javascript" src="<%=basePath%>UI/js/kind-editor.js"></script>
<script type="text/javascript">
function myRefresh(){
	location="<%=basePath%>adviseSave!save.action";
	return false;
}
// 验证表单
function checkForm(form){
	formFormat("formId");
	
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
</script>
	</head>
	<body>
<%@ include file="../common/navigation.jsp"%>
		<form id="formId" action="<%=basePath%>tpDoEdit.action" method="post"
			onSubmit="return checkForm(this)">
			<input type="hidden" name="id" value="${id }" />
			<table class="lyt_view">
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						模版名称
					</td>
					<td>
						<input style="width: 305px;" id="tempName" name="tempName" type="text" value='<c:out value="${tbTemp.memo }"/>'
							maxlength="100"/>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						生日祝福主题
					</td>
					<td>
						<input style="width: 305px;" id="tempTitle" name="tempTitle" type="text" value='<c:out value="${tbTemp.tempTitle }"/>'
							maxlength="100"/>
						<label><input style="margin: 0 0 0 200px; padding: 0px;width: 20px" type="checkbox" name="isFlag" value="1" <c:if test="${tbTemp.flag==1 }">checked</c:if> />是否用作发送模版</label>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" valign="top" style="width: 80px;padding-top:10px;">
						生日祝福内容
					</td>
					<td>
						<textarea name="tempContent" id="tempContent" style="width:652px;height:450px;visibility:hidden;"><c:out value="${tbTemp.tempContent }"/></textarea><script type="text/javascript">MyEditer("tempContent");</script>
					</td>
				</tr>
			</table>
			<div class="lyt_submit" style="width: 760px; text-align: right;">
				<input type="submit" class="btn_save" value="保存" />
				&nbsp;
				<input type="button" onclick="if(confirm('确定要取消当前操作?')){location='<%=basePath %>/tpList.action'}" value="取消" />
			</div>
		</form>
	</body>
</html>