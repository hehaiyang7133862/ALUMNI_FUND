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
		<script type="text/javascript">
		// 验证表单
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
		<form id="formId" name="formId" action="<%=basePath%>zcProjEdit!picUpdate.action" method="post" enctype="multipart/form-data" onsubmit="return checkForm(this);">
			<input type="hidden" name="projId" value="${bean.projId}" />
			<input type="hidden" name="picId" value="${bean.picId}" />
			<table class="lyt_view">
				<c:if test="${not empty bean.picId}">
				<tr>
					<td class="lyt_view_area">
						当前图片
					</td>
					<td>
						<div style="width:400px;overflow:hidden;">
							<img alt="" src="<%=basePath%>${bean.picPath}" height="100"/>
						</div>
					</td>
				</tr>
				</c:if>
				<tr>
					<td class="lyt_view_note" style="width:80px;">
						图片
						<span>*</span>
					</td>
					<td width="240">
						<input style="width:400px;" id="pic" name="pic" type="file" />
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width:54px;">
						图片参数
					</td>
					<td width="80">
						<input style="width:296px" id="numOrder" name="numOrder" type="text" value='<c:out value="${bean.numOrder}" />' maxlength="50" />
						<select name="picPublish" style="width:100px;">
							<option value="1" <c:if test="${bean.picPublish=='1'}">selected</c:if>>对外显示</option>
							<option value="0" <c:if test="${bean.picPublish=='0'}">selected</c:if>>不对外显示</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">
						图片说明
					</td>
					<td>
						<textarea id="picAlt" name="picAlt" style="width:400px;height:60px;" onclick="myArea('picAlt',1000);" onkeyup="myArea('picAlt',1000);" onkeydown="myArea('picAlt',1000);" onchange="myArea('picAlt',1000);"><c:out value='${bean.picAlt}' /></textarea>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">
						备注说明
					</td>
					<td>
						<textarea id="memo" name="memo" style="width:400px;height:60px;" onclick="myArea('memo',1000);" onkeyup="myArea('memo',1000);" onkeydown="myArea('memo',1000);" onchange="myArea('memo',1000);"><c:out value='${bean.memo}' /></textarea>
					</td>
				</tr>
			</table>
			<my:timeView idField="picId" table="TbZcprojPic" value="${bean.picId}"></my:timeView>
			<div class="lyt_submit" style="width: 490px; text-align: right;">
				<input type="submit" value="提交" />
				<input type="button" value="取消" onclick="parent.MyFormWin.close();return false;" />
			</div>
		</form>
		</div>
	</body>
</html>