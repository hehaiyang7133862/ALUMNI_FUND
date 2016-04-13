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
			if($("#payAmount").val()!='' && !isNaN(parseInt($("#payAmount").val()))){
				var syje=$("#canPayValue").val()-$("#payAmount").val();
				if(syje<0){
					$("#canPayAmount").text("支出金额超出，请重新填写");
					return false;
				}else{
					$("#canPayAmount").text(syje);
				}
			}
			return true;
			
		}
		$(document).ready(function(){
			$("#payAmount").keyup(function(){
				var value=$(this).val();
				if(value==""){
					value="0";
				}
				if(!isNaN(parseInt(value))){
					var canPayAmount=$("#canPayValue").val();
					var syje=canPayAmount-value;
					if(syje<0){
						$("#canPayAmount").text("支出金额超出，请重新填写");
					}else{
						$("#canPayAmount").text(syje);
					}
				}
			});
			$("#jsdwObject").change(function(){
				$("#jsdwAccount").val($(this).find("option:selected").attr("refvalue"));
			});
		});
</script>
	</head>
	<body>
		<div class="page">
		<form id="formId" name="formId" method="post" action="<%=basePath%>found!saveOrUpdateProPay.action" onsubmit="return checkForm(formId);">
			<input type="hidden" name="foundId" value="${foundId}" style="display: block;" />
			<input type="hidden" name="payId" value="${payId}" style="display: block;" />
			<table class="lyt_view">
				<tr>
					<td class="lyt_view_note">
						项目名称
					</td>
					<td colspan="3">
						<input style="width: 450px" readonly="readonly" id="" name="" type="text" value='<c:out value="${foundBean.foundName}" />' maxlength="100" />
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						基金用途
					</td>
					<td colspan="3">
						<select style="width: 210px;" name="parentType" id="parentType" onChange='ajaxDDL("fieldAjax!fundExecute.action","parentType","foundType")'>
							<option value="">--请选择--</option>
							<c:forEach var="temp" items="${fieldList}">
							<option value="${temp.fieldId}" <c:if test="${temp.fieldId==bean.tbFields.parentId}">selected</c:if>><c:out value="${temp.fieldName}"/></option>
							</c:forEach>
						</select>
						<select style="width: 237px;" name="foundType" id="foundType">
							<option value="">--请选择--</option>
							<my:fundField type="OPTION" parentId="${bean.tbFields.parentId}" value="${bean.foundType}" />
						</select>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						支出金额
					</td>
					<td style="width: 200px;">
						<%-- <input style="width: 138px" id="payAmount" onkeyup="limit(this)" name="payAmount" type="text" value='<c:out value="${bean.payAmount}" />' maxlength="100" />元 --%>
						<input style="width: 138px" id="payAmount" name="payAmount" type="text" value='<c:out value="${bean.payAmount}" />' maxlength="100" />
						元
					</td>
					<td class="lyt_view_note" style="width: 60px;">
						支出日期
					</td>
					<td style="width: 180px;">
						<input style="width: 147px;" type="text" name="payDate" id="payDate" readonly="true" class="lyt_search_date" value='<c:out value="${bean.payDate}" />' />
						<img onClick="WdatePicker({el:'payDate'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle" />
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						可支出金额
					</td>
					<td colspan="3">
						<input type="hidden" id="canPayValue" value="${realAllAmount-payAllAmount}" />
						<span id="canPayAmount" style="font-family: Constantia,Georgia;font-size: 22px;font-style: italic;font-weight: 700;"><fmt:formatNumber value="${realAllAmount-payAllAmount-bean.payAmount}" /></span> 元
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						支出账户
					</td>
					<td  colspan="3">
						<select name="payAccount" id="payAccount" style="width:450px;">
							<option value="">--请选择--</option>
							<c:forEach var="temp" items="${accList}">
							<option value="${temp.accountId}" <c:if test="${temp.accountId==bean.tbDonationAccout.accountId}">selected</c:if>><c:out value="${temp.accountName}"/><c:out value="${temp.accountNum}"/></option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						接受单位
					</td>
					<td colspan="3">
						<select style="width: 282px;" name="jsdwObject" id="jsdwObject">
							<option value="">请选择[单位名称-单位联系人-银行账号]</option>
							<c:forEach var="temp" items="${acceptList}">
							<option refValue="${temp.accountNum}" value="${temp.comId}" <c:if test="${temp.comId==bean.jsdwObject}">selected</c:if>><c:out value="${temp.comName}" />-<c:out value="${temp.relationName}" />-<c:out value="${temp.accountNum}" /></option>
							</c:forEach>
						</select>
						<input type="text" id="jsdwAccount" name="jsdwAccount" readonly="readonly" style="width: 164px;" value="${bean.jsdwAccount}" />
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">
						支出说明
					</td>
					<td colspan="3">
						<textarea name="payMemo" style="width: 450px;height: 100px;"><c:out value="${bean.payMemo}" /></textarea>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">
						附件
					</td>
					<td colspan="3">
						<input id="updateId" name="relIds" type="hidden" value="" />
						<iframe class="frm_file" marginheight="0" frameborder="0" src="<%=basePath %>fileList.action?name=TbProPay&ele=updateId&id=${bean.payId}">
						</iframe>
					</td>
				</tr>
			</table>
			<my:timeView idField="payId" table="TbProPay" value="${bean.payId}"></my:timeView>
			<div class="lyt_submit" style="width: 90%; text-align: right;">
				<input type="submit" class="btn_save" value="提交" />
				&nbsp;
				<input type="button" onclick="parent.MyFormWin.close();" value="取消" />
			</div>
		</form>
		</div>
	</body>
</html>