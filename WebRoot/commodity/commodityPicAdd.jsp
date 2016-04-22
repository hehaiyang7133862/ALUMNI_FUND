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
<script src="<%=basePathHead%>UI/kindeditor/kindeditor.js"
	type="text/javascript"></script>
<script language="javascript">
function checkForm(form){
	formFormat("formId");
	<c:if test="${empty bean.picId}">
	var picVal = $('#pic').val();
	if(picVal == ""){
		MyMsg.alert("请选择需要上传的图片！");
		return false;
	}else{
  			var picValType = picVal.split(".")[picVal.split(".").length-1].toLowerCase();
		if(picValType!="gif" && picValType!="jpg" && picValType!="jpeg" && picValType!="png" && picValType!="bmp"){
			MyMsg.alert("图片只允许jpg,png,bmp,jpeg,gif格式!");
			return false;
		}
	}
	</c:if>
	if($("#picAlt").val().length > 1000){
		MyMsg.alertFn('图片说明必须在1000字以内！',function(){
			$("#picAlt").focus();
		});
		return false;
	}
	if($("#memo").val().length > 1000){
		MyMsg.alertFn('备注说明必须在1000字以内！',function(){
			$("#memo").focus();
		});
		return false;
	}
	return true;
}
</script>
</head>
<body>
	<div class="float">
		<form id="formId" enctype="multipart/form-data"
			action="<%=basePath%>commodity!saveOrUpdatePic.action" method="post"
			onSubmit="return checkForm(this)">
			<input name="commId" type="hidden" value="${bean.commId}" /> <input
				name="picId" type="hidden" value="${bean.picId}" />
			<table class="lyt_view">
				<c:if test="${not empty bean.picId}">
					<tr>
						<td class="lyt_view_area">当前图片</td>
						<td colspan="3">
							<div style="width:400px;overflow:hidden;">
								<img alt="" src="<%=basePath%>${bean.picPath}" height="100" />
							</div>
						</td>
					</tr>
				</c:if>
				<tr>
					<td class="lyt_view_note" style="width:80px;">图片 <span>*</span>
					</td>
					<td colspan="3" width="240"><input style="width: 240px"
						id="pic" name="pic" type="file" />
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width:80px;">图片排序</td>
					<td width="80"><input style="width:80px" id="numOrder"
						name="numOrder" type="text"
						value='<c:out value="${bean.numOrder}" />' maxlength="50" />
					</td>
					<td class="lyt_view_note" style="width:80px;">是否对外显示<span>*</span>
					</td>
					<td width="80"><select name="picPublish" style="width:100px;">
							<option value="1" <c:if test="${bean.picPublish=='1'}">selected</c:if>>对外显示</option>
							<option value="0" <c:if test="${bean.picPublish=='0'}">selected</c:if>>不对外显示</option>
					</select>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">图片说明</td>
					<td colspan="3"><textarea id="picAlt" name="picAlt"
							style="width:400px;height:80px;" onclick="myArea('picAlt',1000);"
							onkeyup="myArea('picAlt',1000);"
							onkeydown="myArea('picAlt',1000);"
							onchange="myArea('picAlt',1000);"><c:out value='${bean.picAlt}' /></textarea>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">备注说明</td>
					<td colspan="3"><textarea id="memo" name="memo"
							style="width:400px;height:80px;" onclick="myArea('memo',1000);"
							onkeyup="myArea('memo',1000);" onkeydown="myArea('memo',1000);"
							onchange="myArea('memo',1000);"><c:out value='${bean.memo}' /></textarea>
					</td>
				</tr>
			</table>
			<my:timeView idField="picId" table="TbCommodityPic"
				value="${bean.picId}"></my:timeView>
			<div class="lyt_submit" style="width: 490px; text-align: right;">
				<input type="submit" value="提交" /> <input type="button" value="取消"
					onclick="parent.MyFormWin.close();return false;" />
			</div>
		</form>
	</div>
</body>
</html>
