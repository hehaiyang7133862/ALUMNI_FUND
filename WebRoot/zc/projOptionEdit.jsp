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
		function ChangeLimitCount(){
			if($('#limitCount').val()=='1'){
				$('#optionCount').removeAttr('readonly').css('background-color','#fff').focus();
			}else{
				$('#optionCount').attr('readonly','readonly').css('background-color','#ccc');
			}
		}
		// 验证表单
		function checkForm(form){
			formFormat("formId");
			if($("#optionName").val() == ""){
				MyMsg.alertFn('请输入选项名称！',function(){
					$("#optionName").focus();
				});
				return false;
			}
			if($("#optionAmount").val() == "" || !/^\d+(.\d{1,2})?$/.test($("#optionAmount").val())){
				MyMsg.alertFn('请输入选项金额（最多两位小数的正整数）！',function(){
					$("#optionAmount").focus();
				});
				return false;
			}
			if($("#unitName").val() == ""){
				MyMsg.alertFn('请输入选项数量计量单位！',function(){
					$("#unitName").focus();
				});
				return false;
			}
			if($('#limitCount').val()=='1'){
				if($("#optionCount").val() == "" || !/^\d+\$/.test($("#optionCount").val())){
					MyMsg.alertFn('请输入选项剩余数量（正整数）！',function(){
						$("#optionCount").focus();
					});
					return false;
				}
			}
			if($("#optionMemo").val().length > 1000){
				MyMsg.alertFn('选项说明必须在1000字以内！',function(){
					$("#optionMemo").focus();
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
		<form id="formId" name="formId" action="<%=basePath%>zcProjEdit!optionUpdate.action" method="post" onsubmit="return checkForm(this);">
			<input type="hidden" name="projId" value="${bean.projId}" />
			<input type="hidden" name="optionId" value="${bean.optionId}" />
			<table class="lyt_view">
				<tr>
					<td class="lyt_view_note">
						选项名称
						<span>*</span>
					</td>
					<td colspan="3">
						<input style="width:230px" id="optionName" name="optionName" type="text" value='<c:out value="${bean.optionName}" />' maxlength="100" />
					</td>
					<td class="lyt_view_note">
						选项编码
					</td>
					<td>
						<input style="width:90px" id="optionCode" name="optionCode" type="text" value='<c:out value="${bean.optionCode}" />' maxlength="100" />
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width:80px;">
						选项金额
						<span>*</span>
					</td>
					<td width="80">
						<input style="width:55px" id="optionAmount" name="optionAmount" type="text" value='<fmt:formatNumber value='${bean.optionAmount}' pattern='0.##' type='number'/>' maxlength="50" />
						元
					</td>
					<td class="lyt_view_note" style="width:60px;">
						计量单位
						<span>*</span>
					</td>
					<td width="60">
						<input style="width:62px;" id="unitName" name="unitName" type="text" value='<c:out value="${bean.unitName}" default="份"/>' maxlength="50" />
					</td>
					<td class="lyt_view_note" style="width:60px;">
						选项排序
					</td>
					<td width="80">
						<input style="width:90px" id="optionOrder" name="optionOrder" type="text" value='<c:out value="${bean.optionOrder}" />' maxlength="50" />
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						限制数量
						<span>*</span>
					</td>
					<td>
						<select id="limitCount" name="limitCount" style="width:55px" onchange="ChangeLimitCount();">
							<option value="1" <c:if test="${bean.limitCount=='1'}">selected</c:if>>是</option>
							<option value="0" <c:if test="${bean.limitCount!='1'}">selected</c:if>>否</option>
						</select>
					</td>
					<td class="lyt_view_note">
						剩余数量
						<span>*</span>
					</td>
					<td>
						<input style="width:62px;<c:if test="${bean.limitCount!='1'}">background-color:#ccc;</c:if>" id="optionCount" name="optionCount" type="text" value='<fmt:formatNumber value='${bean.optionCount}' pattern='0' type='number'/>' maxlength="50" <c:if test="${bean.limitCount!='1'}">readonly="readonly"</c:if> />
					</td>
					<td class="lyt_view_note">
						对外显示
						<span>*</span>
					</td>
					<td>
						<select name="optionPublish" style="width:90px">
							<option value="1" <c:if test="${bean.optionPublish=='1'}">selected</c:if>>对外显示</option>
							<option value="0" <c:if test="${bean.optionPublish=='0'}">selected</c:if>>不对外显示</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">
						选项说明
					</td>
					<td colspan="5">
						<textarea id="optionMemo" name="optionMemo" style="width:405px;height:60px;" onclick="myArea('optionMemo',1000);" onkeyup="myArea('optionMemo',1000);" onkeydown="myArea('optionMemo',1000);" onchange="myArea('optionMemo',1000);"><c:out value='${bean.optionMemo}' /></textarea>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">
						备注说明
					</td>
					<td colspan="5">
						<textarea id="memo" name="memo" style="width:405px;height:60px;" onclick="myArea('memo',1000);" onkeyup="myArea('memo',1000);" onkeydown="myArea('memo',1000);" onchange="myArea('memo',1000);"><c:out value='${bean.memo}' /></textarea>
					</td>
				</tr>
			</table>
			<my:timeView idField="optionId" table="TbZcprojOption" value="${bean.optionId}"></my:timeView>
			<div class="lyt_submit" style="width: 495px; text-align: right;">
				<input type="submit" value="提交" />
				<input type="button" value="取消" onclick="parent.MyFormWin.close();return false;" />
			</div>
		</form>
		</div>
	</body>
</html>