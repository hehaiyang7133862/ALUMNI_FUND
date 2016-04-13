<%@ include file="../common/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript">
function checkAcademy(){
	if($('#academyCheck').attr('checked')){
		$('#schoolCheck').attr('disabled',false);
	}else{
		$('#schoolCheck').attr('disabled',true);
		$('#schoolCheck').attr('checked',false);
	}
}
$(document).ready(function(){
	checkAcademy();
});
</script>
</head>
<body>
<div class="page">
	<%@ include file="../common/navigation.jsp"%>
	<%@ include file="menuInfos.jsp"%>
	<form action="<%=basePath%>alumniCheckSet!upload.action" method="post">
	<div class="alumniCheckSet">
		<input type="checkbox" id="freeCheck" name="freeCheck" class="checkbox" value="1" <c:if test="${freeCheck==1}">checked="checked"</c:if>/><label for="freeCheck" class="checkbox">自由注册</label>
	</div>
	<div class="alumniCheckSetMemo">
		说明：注册校友无限制，自动审核通过
	</div>
	<div class="alumniCheckSet">
		<input type="checkbox" id="autoCheck" name="autoCheck" class="checkbox" value="1" <c:if test="${autoCheck==1}">checked="checked"</c:if>/><label for="autoCheck" class="checkbox">智能审核</label>
	</div>
	<div class="alumniCheckSetMemo">
		说明：注册校友激活账号时候，如果该校友注册信息中提供的“姓名”、“入学年”、“熟悉的同学”（至少一名）在“校友名册”中存在，则自动通过审核
	</div>
	<div class="alumniCheckSet">
		<input type="checkbox" id="academyCheck" name="academyCheck" class="checkbox" value="1" <c:if test="${academyCheck==1}">checked="checked"</c:if> onclick="checkAcademy();"/><label for="academyCheck" class="checkbox">院系审核</label>
		→
		<input type="checkbox" id="schoolCheck" name="schoolCheck" class="checkbox" value="1" <c:if test="${schoolCheck==1}">checked="checked"</c:if>/><label for="schoolCheck" class="checkbox">校友总会审核</label>
	</div>
	<div class="alumniCheckSetMemo">说明：</div>
	<div class="alumniCheckSetMemo1">
		：管理员有菜单权限就能审核
	</div>
	<div class="alumniCheckSetMemo2">
		：相应院系管理员可以审核该校友；校友总会管理员可以审核全部校友
	</div>
	<div class="alumniCheckSetMemo3">
		：相应院系管理员审核后，还须校友总会进行审核
	</div>
	<div class="alumniCheckSet" style="padding-top:30px;">
		<input type="hidden" name="num" value="<c:out value="${num}"/>"/>
		<input type="submit" class="btn_com" value="保存"/>
	</div>
	</form>
</div>
</body>
</html>