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
function refreshFrame(){
	$("#iframeMem").attr("src","<%=basePath%>donate!companyMemberList.action?comId=${comId}");
}
// 验证表单
function checkForm(form){
	formFormat("formId");
	if($("#comName").val() == ""){
		$("#comName").focus();
		form.comName.style.backgroundColor=error;
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
				url:"<%=basePath%>donate!saveOrUpdateCom.action",
				data:form.serialize(),
				dataType:"json",
				success:function(result){
					if(result){
						if(result.code=='error'){
							MyMsg.alert(result.msg);
						}else{
							MyMsg.alertFn(result.msg,function(){
								location="<%=basePath%>donate!toCompanyModifyPage.action?comId="+result.id;
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
		<%@ include file="../common/navigation.jsp"%>
		<form id="formId" name="formId" method="post">
			<input type="hidden" name="comId" value="${comId}" style="display: block;" />
			<table class="lyt_view">
				<tr>
					<td class="lyt_view_note" width="80px">
						单位名称
					</td>
					<td width="290">
						<input style="width: 285px;" type="text" name="comName" id="comName" value="<c:out value='${bean.comName}' />" maxlength="100" />
					</td>
					<td class="lyt_view_note" width="80px">
						主要业务活动
					</td>
					<td width="290">
						<input style="width: 285px;" type="text" name="comBusi" value="<c:out value='${bean.comBusi}' />" maxlength="100" />
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						登记注册类型
					</td>
					<td>
						<select name="comType" style="width: 285px;">
							<option value="">全部</option>
							<my:fundField type="OPTION" lead="DONATION_BUSI" value="${bean.comType}"></my:fundField>
						</select>
					</td>
					<td class="lyt_view_note">
						所属行业
					</td>
					<td>
						<select name="industryFid" style="width: 285px;">
							<option value="">全部</option>
							<my:field type="OPTION" lead="INDUSTRY" value="${bean.industryFid}"></my:field>
						</select>
					</td>
				</tr>
				<tr>
					<td class="lyt_search_note">
							详细地址
					</td>
					<td>
						<input style="width: 285px;" type="text" name="comAddress" value="<c:out value='${bean.comAddress}' />" maxlength="100" />
					</td>
					<td class="lyt_search_note">
						单位负责人
					</td>
					<td width="211">
						<input style="width: 285px;" type="text" name="responseName" value="<c:out value='${bean.responseName}' />" maxlength="100" />
					</td>
				</tr>
				
				<c:if test="${empty comId}">
				<tr>
					<td class="lyt_view_note">
						主要联系人
					</td>
					<td colspan="3">
						请先创建捐赠单位
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty comId}">
				<tr>
					<td colspan="4" class="lyt_view_note">
						<span style="text-align: right; float: left;">主要联系人</span>
						<span style="text-align: right; float: right;">
						<a href="javascript:;" onclick="MyFormWin.showMyWin('单位人员详细','<%=basePath%>donate!toMember.action?comId=${comId}',580,380);return false;">新增</a>
						</span>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<iframe name="iframeMem" id="iframeMem" width="100%" height="100%" frameborder="0" scrolling="auto" src="<%=basePath%>donate!companyMemberList.action?comId=${comId}"></iframe>
					</td>
				</tr>
				</c:if>
			</table>
			<my:timeView idField="comId" table="TbDonationCompany" value="${bean.comId}"></my:timeView>
			<div class="lyt_submit" style="width: 770px; text-align: right;">
				<input type="button" class="btn_save" value="提交" />
				&nbsp;
				<input type="button" onclick="location.href='<%=basePath%>donate!companyList.action'" value="取消" />
			</div>
		</form>
		</div>
	</body>
</html>