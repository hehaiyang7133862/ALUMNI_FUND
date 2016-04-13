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
	<c:if test="${empty bean}">
	// 名称
	if(form.pic.value==""){
		msg+="请选择图片\n";
		form.pic.style.backgroundColor=error;
	}
	if(form.pic.value!=""&&!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(form.pic.value)){
		msg+="图片类型必须是.gif,jpeg,jpg,png中的一种\n";
		form.pic.style.backgroundColor=error;
	}
	</c:if>
	
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
function myRefresh(){
	location.href="<%=basePath%>found!operationPic.action?picId=${bean.picId}&proId=${proId}&num=${num}";
}
$(document).ready(function(){
   	<c:if test="${not empty alert}">
   		//刷新
   		setTimeout(myRefresh(),2000);
   	</c:if>
});
</script>

</head>
<body>
	<div class="float">
		<form id="formId" enctype="multipart/form-data" action="<%=basePath%>found!saveOrUpdatePic.action"
			method="post" onSubmit="return checkForm(this)">
			<input type="hidden" name="foundId" value="${foundId }" />
			<input type="hidden" name="proId" value="${proId }" />
			<input name="num" type="hidden" value="<c:out value='${num}' />" />
			<input name="picId" type="hidden" value="<c:out value='${bean.picId}' />" />
			<input name="proType" type="hidden" value="<c:out value='${proType}' />" />
			<table border="0" cellspacing="0" cellpadding="0" class="lyt_view">
				<c:if test="${not empty bean}">
					<tr>
						<td class="lyt_view_note">当前图片</td>
						<td><img width="100px" height="100px" src="<%=basePath%>upload/image/${bean.picPath}" alt="${pic.picAlt}" /></td>
					</tr>
				</c:if>
				<tr>
					<td class="lyt_view_note">选择图片</td>
					<td><input type="file" id="pic" name="pic" /><c:if test="${empty bean}"><span class="fnt_warn">*</span></c:if></td>
				</tr>
				<tr>
					<td class="lyt_view_note">图片描述</td>
					<td><input style="width:420px;" type="text" name="picAlt" id="picAlt" value="<c:out value='${bean.picAlt}'/>"
						maxlength="200" /></td>
				</tr>
				<tr>
					<td class="lyt_view_area">备注</td>
					<td><textarea name="memo"><c:out value='${bean.memo}'/></textarea></td>
				</tr>
			</table>
			<div class="lyt_submit">
				<input type="submit" value="保存" /> <input type="button" value="取消"
					onClick="if(confirm('您确定要放弃此操作吗？'))parent.location='<%=basePath%>found!protocolPic.action?proId=${proId}&num=${num}';" />
			</div>
		</form>
	</div>
</body>
</html>
