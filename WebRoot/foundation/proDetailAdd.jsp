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
		<%@ include file="../common/kindeditor.jsp"%>
		<script type="text/javascript">
		// 验证表单
		function checkForm(form){
			formFormat("formId");
			if($("#toAmount").val()!="0" && $("#sfpb").val()==""){
				MyMsg.alert("请选择是否配比!");
				return false;
			}
			return true;
			
		}
		$(document).ready(function(){
			var sfpbValue=$("#sfpb").val();
			if(sfpbValue=="1"){
				$("*[id=disTable]").show();
				$(".txtt1").text('状态');
				$(".txtt2").show();
				$(".txtt3").hide();
			}else{
				$("*[id=disTable]").hide();
				$(".txtt2").hide();
				$(".txtt3").show();
			}
			$("#sfpb").change(function(){
				var value=$("#sfpb").val();
				if(value=="1"){
					$("*[id=disTable]").show();
					$(".txtt1").text('状态');
					$(".txtt2").show();
					$(".txtt3").hide();
				}else{
					$("*[id=disTable]").hide();
					$(".txtt2").hide();
					$(".txtt3").show();
				}
			});
			$("#parentUse").change(function(){
				var v=$(this).val();
				if(v==""){
					$("#proUse").val("");
				}
			});
		});
		function changeObj(obj){
			if(obj.value=="0"){
				$("#sfpb").val("");
				$("#sfpb").attr("disabled",true);
			}else{
				$("#sfpb").attr("disabled",false);
			}
		}
		</script>
	</head>
	<body>
		<div class="page">
		<form id="formId" action="<%=basePath%>found!saveOrUpdateProDetail.action" method="post" onsubmit="return checkForm(this);">
			<input type="hidden" name="proId" value="${bean.proId}" />
			<input type="hidden" name="detailId" value="${bean.detailId}" />
			<table class="lyt_view">
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						承诺日期
					</td>
					<td style="width: 160px;">
						<input style="width: 132px;" type="text" name="dealDate" id="dealDate" readonly="true" class="lyt_search_date" value='<c:out value="${bean.dealDate}" />' />
						<img onClick="WdatePicker({el:'dealDate'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle" />
					</td>
					<td class="lyt_view_note" style="width: 80px;">
						承诺金额
					</td>
					<td style="width: 160px;">
						<input style="width: 138px" id="dealAmount" name="dealAmount" type="text" value='<c:out value="${bean.dealAmount}" />' maxlength="100" />元
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						到账日期
					</td>
					<td style="width: 160px;">
						<input style="width: 132px;" type="text" name="toDate" id="toDate" readonly="true" class="lyt_search_date" value='<c:out value="${bean.toDate}" />' />
						<img onClick="WdatePicker({el:'toDate'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle" />
					</td>
					<td class="lyt_view_note" style="width: 80px;">
						到账金额
					</td>
					<td style="width: 160px;">
						<input style="width: 138px" id="toAmount" onchange="changeObj(this)" name="toAmount" type="text" value='<c:out value="${bean.toAmount}" />' maxlength="100" />元
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						到账账户
					</td>
					<td colspan="3">
						<select name="toAccount" id="toAccount" style="width:414px;">
							<option value="">--请选择--</option>
							<c:forEach var="temp" items="${accList}">
							<option value="${temp.accountId}" <c:if test="${temp.accountId==bean.tbDonationAccout.accountId}">selected</c:if>><c:out value="${temp.accountName}"/><c:out value="${temp.accountNum}"/></option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						指定用途
					</td>
					<td colspan="3">
						<select name="parentUse" id="parentUse" onChange='ajaxDDL("fieldAjax!fundExecute.action","parentUse","proUse")'>
							<option value="">--请选择--</option>
							<c:forEach var="temp" items="${fieldList}">
							<option value="${temp.fieldId}" <c:if test="${temp.fieldId==bean.tbFields.parentId}">selected</c:if>><c:out value="${temp.fieldName}"/></option>
							</c:forEach>
						</select>
						<select name="proUse" id="proUse">
							<option value="">--请选择--</option>
							<my:fundField type="OPTION" parentId="${bean.tbFields.parentId}" value="${bean.proUse}" />
						</select>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						是否配比
					</td>
					<td id="sfpbTd" style="width: 160px;">
						<select name="sfpb" id="sfpb">
							<option value="">--请选择--</option>
							<option value="1" <c:if test="${bean.sfpb=='1'}">selected</c:if>>是</option>
							<option value="2" <c:if test="${bean.sfpb=='2'}">selected</c:if> <c:if test="${empty bean}">selected</c:if>>否</option>
						</select>
					</td>
					<td id="disTable" class="lyt_view_note txtt1" style="width: 80px;display: none;">
						状态
					</td>
					<td id="disTable" class="txtt2" style="width: 160px;display: none;">
						<select name="status" id="status">
							<option value="">--请选择--</option>
							<option value="1" <c:if test="${bean.status=='1'}">selected</c:if>>未申报</option>
							<option value="2" <c:if test="${bean.status=='2'}">selected</c:if>>申报中</option>
							<option value="3" <c:if test="${bean.status=='3'}">selected</c:if>>申报成功</option>
							<option value="4" <c:if test="${bean.status=='4'}">selected</c:if>>申报失败</option>
						</select>
					</td>
					<td class="txtt3">
					</td>
				</tr>
				<tr id="disTable" style="display: none;">
					<td class="lyt_view_note" style="width: 80px;">
						申报金额
					</td>
					<td>
						<input style="width: 138px" id="amount" onkeyup="limit(this)" name="amount" type="text" value='<c:out value="${bean.amount}" />' maxlength="100" />元
					</td>
					<td class="lyt_view_note" style="width: 80px;">
						实际金额
					</td>
					<td>
						<input style="width: 138px" id="realAmount" name="realAmount" type="text" value='<c:out value="${bean.realAmount}" />' maxlength="100" />元
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px; vertical-align: top; padding-top: 10px;">
						备注
					</td>
					<td colspan="3">
						<textarea name="memo" style="width:414px;"><c:out value="${bean.memo}" /></textarea>
					</td>
				</tr>
			</table>
			<my:timeView idField="detailId" table="TbProDetail" value="${bean.detailId}"></my:timeView>
			<div class="lyt_submit" style="width: 520px; text-align: right;">
				<input type="submit" class="btn_save" value="提交" />
				&nbsp;
				<input type="button" onclick="parent.MyFormWin.close();" value="取消" />
			</div>
		</form>
		</div>
	</body>
</html>