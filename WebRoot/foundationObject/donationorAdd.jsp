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
	if($("#nameCn").val() == ""){
		$("#nameCn").focus();
		form.nameCn.style.backgroundColor=error;
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
	$("input[type='button'][class='btn_save']").click(function(){
		var form=$("#formId");
		if(checkForm(form[0])){
			MyMask.showHtml("正在提交...");
			//ajax提交
			$.ajax({
				type:"POST",
				url:"<%=basePath%>donate!saveOrUpdateDor.action",
				data:form.serialize(),
				dataType:"json",
				success:function(result){
					if(result){
						if(result.code=='error'){
							MyMsg.alert(result.msg);
						}else{
							MyMsg.alertFn(result.msg,function(){
								parent.myRefresh();
								setTimeout(parent.MyFormWin.close(),20);
							});
						}
					}
				},
				complete:function(){
					MyMask.hide();
				}
			});
		}
	});
});
</script>
	</head>
	<body>
		<div class="page">
		<form id="formId" name="formId" method="post">
			<input type="hidden" name="memId" value="${memId}" style="display: block;" />
			<table class="lyt_view">
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						姓名
					</td>
					<td style="width: 160px;">
						<c:choose>
						<c:when test="${not empty bean.tbAlumni}">
							<input style="width: 155px;" type="text" name="nameCn" id="nameCn" value='<c:out value="${bean.tbAlumni.nameCn}" />' />
						</c:when>
						<c:otherwise>
							<input style="width: 155px;" type="text" name="nameCn" id="nameCn" value='<c:out value="${bean.nameCn}" />' />
						</c:otherwise>
						</c:choose>
					</td>
					<td class="lyt_view_note" style="width: 80px;">
						职务
					</td>
					<td>
						<c:choose>
						<c:when test="${not empty bean.tbWork}">
							<input style="width: 155px" id="dutyName" name="dutyName" type="text" value='<c:out value="${bean.tbWork.dutyName}" />' maxlength="100" />
						</c:when>
						<c:otherwise>
							<input style="width: 155px" id="dutyName" name="dutyName" type="text" value='<c:out value="${bean.dutyName}" />' maxlength="100" />
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						工作单位
					</td>
					<td style="width: 160px;">
						<c:choose>
						<c:when test="${not empty bean.tbWork}">
							<input style="width: 155px;" type="text" name="company" id="company" value='<c:out value="${bean.tbWork.companyName}" />' />
						</c:when>
						<c:otherwise>
							<input style="width: 155px;" type="text" name="company" id="company" value='<c:out value="${bean.company}" />' />
						</c:otherwise>
						</c:choose>
					</td>
					<td class="lyt_view_note" style="width: 80px;">
						行业
					</td>
					<td>
						<c:choose>
						<c:when test="${not empty bean.tbWork}">
							<select style="width: 155px;" name="industryFid">
								<option value="">--请选择--</option>
								<my:field type="OPTION" lead="INDUSTRY" value="${bean.tbWork.industryFid}"></my:field>
							</select>
						</c:when>
						<c:otherwise>
							<select style="width: 155px;" name="industryFid">
								<option value="">--请选择--</option>
								<my:field type="OPTION" lead="INDUSTRY" value="${bean.industryFid}"></my:field>
							</select>
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						办公电话
					</td>
					<td style="width: 160px;">
						<c:choose>
						<c:when test="${not empty bean.tbAlumni}">
							<input style="width: 155px;" type="text" name="telephone" id="telephone" value='<c:out value="${bean.tbAlumni.telephoneFirst}" />' maxlength="100"/>
						</c:when>
						<c:otherwise>
							<input style="width: 155px;" type="text" name="telephone" id="telephone" value='<c:out value="${bean.telephone}" />' maxlength="100"/>
						</c:otherwise>
						</c:choose>
					</td>
					<td class="lyt_view_note" style="width: 80px;">
						手机
					</td>
					<td style="width: 160px;">
						<c:choose>
						<c:when test="${not empty bean.tbAlumni}">
							<input style="width: 155px;" type="text" name="handset" id="handset" value='<c:out value="${bean.tbAlumni.handsetFirst}" />' maxlength="100"/>
						</c:when>
						<c:otherwise>
							<input style="width: 155px;" type="text" name="handset" id="handset" value='<c:out value="${bean.handset}" />' maxlength="100"/>
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						邮箱
					</td>
					<td style="width: 160px;">
						<c:choose>
						<c:when test="${not empty bean.tbAlumni}">
							<input style="width: 155px;" type="text" name="mail" id="mail" value='<c:out value="${bean.tbAlumni.mailFirst}" />' maxlength="100"/>
						</c:when>
						<c:otherwise>
							<input style="width: 155px;" type="text" name="mail" id="mail" value='<c:out value="${bean.mail}" />' maxlength="100"/>
						</c:otherwise>
						</c:choose>
					</td>
					<td class="lyt_view_note" style="width: 80px;">
						性别
					</td>
					<td style="width: 160px;">
						<c:choose>
						<c:when test="${not empty bean.tbAlumni}">
							<select style="width: 155px;" name="sexCid" id="sexCid">
								<option value="">--请选择--</option>
								<option value="1" <c:if test="${bean.tbAlumni.sexCid=='1'}">selected</c:if>>男</option>
								<option value="2" <c:if test="${bean.tbAlumni.sexCid=='2'}">selected</c:if>>女</option>
							</select>
						</c:when>
						<c:otherwise>
							<select style="width: 155px;" name="sexCid" id="sexCid">
								<option value="">--请选择--</option>
								<option value="1" <c:if test="${bean.sexCid=='1'}">selected</c:if>>男</option>
								<option value="2" <c:if test="${bean.sexCid=='2'}">selected</c:if>>女</option>
							</select>
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						是否校友
					</td>
					<td style="width: 160px;">
						<c:choose>
						<c:when test="${not empty bean.tbAlumni}">
							<c:set var="typeFid" value="${bean.tbAlumni.typeFid}" />
							<c:set var="typeFidIndex" value="${myFn:lastIndexOf(typeFid,',')+1}" />
							<select style="width: 155px;" name="isStu" id="isStu">
								<option value="">--请选择--</option>
								<option value="0" <c:if test="${bean.isStu=='0'}">selected</c:if>>否</option>
								<my:field type="OPTION" lead="ALUMNITYPE" value="${fn:substring(typeFid,typeFidIndex,fn:length(typeFid))}" />
							</select>
						</c:when>
						<c:otherwise>
							<select style="width: 155px;" name="isStu" id="isStu">
								<option value="">--请选择--</option>
								<option value="0" <c:if test="${bean.isStu=='0'}">selected</c:if>>否</option>
								<my:field type="OPTION" lead="ALUMNITYPE" value="${bean.isStu}" />
							</select>
						</c:otherwise>
						</c:choose>
					</td>
					<td class="lyt_view_note" style="width: 80px;">
						人物属性
					</td>
					<td style="width: 160px;">
						<c:choose>
						<c:when test="${not empty bean.tbAlumni}">
							<c:set var="gradeFid" value="${bean.tbAlumni.gradeFid}" />
							<c:set var="gradeFidIndex" value="${myFn:lastIndexOf(gradeFid,',')+1}" />
							<select style="width: 75px;" name="parentId" id="parentId" onChange='ajaxDDL("fieldAjax.action","parentId","gradeFid")'>
								<option value="">--请选择--</option>
								<c:forEach var="temp" items="${fieldList}">
								<option value="${temp.fieldId}" <c:if test="${temp.fieldId==parentField.parentId}">selected</c:if>><c:out value="${temp.fieldName}"/></option>
								</c:forEach>
							</select>
							<select style="width: 75px;" name="gradeFid" id="gradeFid">
								<option value="">--请选择--</option><my:field type="OPTION" parentId="${parentField.parentId}" value="${fn:substring(gradeFid,gradeFidIndex,fn:length(gradeFid))}" />
							</select>
						</c:when>
						<c:otherwise>
							<select style="width: 75px;" name="parentId" id="parentId" onChange='ajaxDDL("fieldAjax.action","parentId","gradeFid")'>
								<option value="">--请选择--</option>
								<c:forEach var="temp" items="${fieldList}">
								<option value="${temp.fieldId}" <c:if test="${temp.fieldId==parentField.parentId}">selected</c:if>><c:out value="${temp.fieldName}"/></option>
								</c:forEach>
							</select>
							<select style="width: 75px;" name="gradeFid" id="gradeFid">
								<option value="">--请选择--</option><my:field type="OPTION" parentId="${parentField.parentId}" value="${bean.gradeFid}" />
							</select>
						</c:otherwise>
						</c:choose>
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
			<my:timeView idField="memId" table="TbDonationor" value="${bean.memId}"></my:timeView>
			<div class="lyt_submit" style="width: 550px; text-align: right;">
				<input type="button" class="btn_save" value="提交" />
				&nbsp;
				<input type="button" onclick="parent.MyFormWin.close();" value="取消" />
			</div>
		</form>
		</div>
	</body>
</html>