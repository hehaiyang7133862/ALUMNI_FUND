<%@ page language="java" import="java.util.*,com.laungee.proj.common.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>  
<%@ include file="../common/head.jsp"%>
<script language="javascript">
function checkItemType(){
	if($('input[name="itemType"]:checked').val()=='9'){
		$('#linkUrlTr').show();
		$('#linkUrl').focus();
	}else{
		$('#linkUrlTr').hide();
	}
}
function checkForm(form){
	formFormat("formId");
	if($('#group').val()==""){
		MyMsg.alertFn('请选择所属分组！',function(){$('#group').focus();});
		return false;
	}
	if($('#itemName').val()==""){
		MyMsg.alertFn('请输入栏目名称！',function(){$('#itemName').focus();});
		return false;
	}
	if($('input[name="itemType"]:checked').length==0){
		MyMsg.alert('请选择栏目类型！');
		return false;
	}
	if($('input[name="itemType"]:checked').val()=='9'){
		if($('#linkUrl').val()==""){
			MyMsg.alertFn('请输入链接地址！',function(){$('#linkUrl').focus();});
			return false;
		}
	}
	// 备注
	if($('#memo').length>1000){
		MyMsg.alertFn('备注不能超过1000字！',function(){$('#memo').focus();});
		return false;
	}
	return true;
}
</script>
</head>
<body>
<div class="float">
	<form id="formId" action="<%=basePath%>siteItem!doEdit.action" method="post" onSubmit="return checkForm(this)">
		<input name="itemId" type="hidden" value="<c:out value='${bean.itemId}' />" />
		<table border="0" cellspacing="0" cellpadding="0" class="lyt_view">
			<tr>
		    	<td class="lyt_view_note">所属分组</td>
		    	<td>
		    		<select id="group" name="group" style="width:420px;">
			    		<c:forEach items="${groupList}" var="temp">
			    		<option value="${temp.fieldId}" <c:if test="${bean.groupCode==temp.code}">selected</c:if>><c:out value="${temp.fieldName}"/>（<c:out value="${temp.code}"/>）</option>
			    		</c:forEach>
		    		</select>
		    		<span class="fnt_warn">*</span>
		    	</td>
		  	</tr>
		  	<tr>
		    	<td class="lyt_view_note">栏目名称</td>
		    	<td>
		    		<input type="text" id="itemName" name="itemName" value="<c:out value="${bean.itemName}"/>" style="width:420px;" maxlength="100" />
		    		<span class="fnt_warn">*</span>
		    	</td>
		  	</tr>
		  	<tr>
		    	<td class="lyt_view_note">栏目类型</td>
		    	<td>
		    		<label for="itemType1">
		    			<input type="radio" id="itemType1" name="itemType" value="1" <c:if test="${bean.itemType=='1'}">checked</c:if> onclick="checkItemType();" class="checkbox" style="width:auto;height:auto;" />
		    			正文
		    		</label>
		    		<label for="itemType2">
		    			<input type="radio" id="itemType2" name="itemType" value="2" <c:if test="${bean.itemType!='1' && bean.itemType!='9'}">checked</c:if> onclick="checkItemType();" class="checkbox" style="width:auto;height:auto;" />
		    			列表
		    		</label>
		    		<label for="itemType9">
		    			<input type="radio" id="itemType9" name="itemType" value="9" <c:if test="${bean.itemType=='9'}">checked</c:if> onclick="checkItemType();" class="checkbox" style="width:auto;height:auto;" />
		    			链接
		    		</label>
		    		<span class="fnt_warn">*</span>
		    	</td>
		  	</tr>
		  	<tr id="linkUrlTr" <c:if test="${bean.itemType!='9'}">style="display:none;"</c:if>>
		    	<td class="lyt_view_note">链接地址</td>
		    	<td>
		    		<input type="text" id="linkUrl" name="linkUrl" value="<c:out value="${bean.linkUrl}"/>" style="width:420px;" maxlength="100" />
		    		<span class="fnt_warn">*</span>
		    	</td>
		  	</tr>
		  	<tr>
		    	<td class="lyt_view_note">栏目发布</td>
		    	<td>
		    		<label for="publishFlag">
		    			<input type="checkbox" id="publishFlag" name="publishFlag" value="1" <c:if test="${bean.publishFlag=='1'}">checked</c:if> class="checkbox" style="width:auto;height:auto;" />
		    			栏目对外显示发布
		    		</label>
		    		<span class="fnt_warn">*</span>
		    	</td>
		  	</tr>
		  	<tr>
		    <td class="lyt_view_note">备注</td>
		    <td>
		      <textarea id="memo" name="memo" onclick="myArea('memo',1000);" onkeyup="myArea('memo',1000);" onkeydown="myArea('memo',1000);" onchange="myArea('memo',1000);"><c:out value="${bean.memo}" /></textarea></td>
		  </tr>
		</table>
		<my:timeView idField="itemId" table="TbSiteItem" value="${bean.itemId}"></my:timeView>
		<div class="lyt_submit">
		<input type="submit" value="保存" />
		<input type="button" value="取消"
			onClick="if(confirm('您确定要放弃此操作吗？'))window.parent.FormEditWin.close();" />
		</div>
	</form>
</div>
</body>
</html>
