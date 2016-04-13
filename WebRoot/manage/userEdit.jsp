<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<style type="text/css">
.tmp_btn{
	border:#728eb8 1px double;
	height:18px;width:60px !important;
	background-color:#f4f7fd;
}
</style>
<script language="javascript">
function isShow(){
	if($("#starId").val()=="0"){
		$("#trPwdId").css("display","");
	}
	else{
		$("#trPwdId").css("display","none");
	}
}
function closeother(sid){
	 var checkarr=document.getElementsByName("organId"); 
     for(var i=0;i<checkarr.length;i++){
		 if(sid.checked==true){
		        if(sid.value!=checkarr[i].value){
				  checkarr[i].checked=false;
		        }
	    }else{
		   if(sid.value!=checkarr[i].value){
			   checkarr[i].disabled=false;
		   }	
	}
	}
}
function closeother1(sid){
	 var checkarr=document.getElementsByName("organId"); 
	 if(sid.checked==true){
	             if(sid.value!=checkarr[0].value){
			        checkarr[0].disabled=true;
			        checkarr[0].checked=false;
			      }
	 }
	var temp="";
	for(var i=1;i<checkarr.length;i++){
	   if(checkarr[0].value!=checkarr[i].value){
	       if(checkarr[i].checked==true){
	          temp="1";
	       }
	   }
	}
	if(temp==""){
	   checkarr[0].disabled=false;
	}
}
function checkForm(form){
	formFormat("formId");
	var msg="";
	// 帐号
	if(form.userCard.value==""){
		msg+="请输入帐号\n";
		form.userCard.style.backgroundColor=error;
	}
	// 密码
	if(form.starCid.value==0 && form.userPwd.value==""){
		msg+="请输入密码\n";
		form.userPwd.style.backgroundColor=error;
	}
	// 请输入确认密码
	if(form.starCid.value==0 && form.userPwd_try.value==""){
		msg+="请输入确认密码\n";
		form.userPwd_try.style.backgroundColor=error;
	}
	// 请输入确认密码
	if(form.starCid.value==0 && form.userPwd.value!="" && form.userPwd_try.value!="" && form.userPwd.value!=form.userPwd_try.value){
		msg+="两次密码不一致\n";
		form.userPwd.style.backgroundColor=error;
		form.userPwd_try.style.backgroundColor=error;
	}
	// 邮箱
	if(form.relEmail.value!=""){
		if(!/^(\S)+[@]{1}(\S)+[.]{1}(\w)+$/.test(form.relEmail.value)){
			msg+="邮箱格式错误\n"
			form.relEmail.style.backgroundColor=error;
		}
	}
	// 备注
	if(!(getLength(form.memo.value)<=1000)){
		msg+="备注不能超过1000字节\n";
		form.memo.style.backgroundColor=error;
	}
	// 返回
	if(msg!=""){
		alert(msg);
		return false;
	}else{
		MyMask();
	   return true;
	}
}
function doUser(){
    var url ="ajax!doUser.action";
	var id1=document.getElementById("userCardId").value;
	if(id1==""){
		alert("请输入帐号!");
		return;
	}
	$("#showId").html("");
	var pars ={id1:id1};
	jQuery.get(
		url,
		pars,
		function(xml){
			document.getElementById("showId").innerHTML=xml;
		},
		"text"
	);
}
$(document).ready(function(){
    isShow();
	var checkarr=document.getElementsByName("organId");
	closeother(checkarr[0]);
	if(!($("#eleId").val()=="")){
		$("#userCardId").attr("readonly","readonly");
		$("#userCardId").css("background","#F0FFE8");
		$("#checkId").css("display","none");
	}
});
</script>
</head> 
<body>
<div class="page">
<form id="formId" action="<%=basePath%>userEdit.action" method="post" onSubmit="return checkForm(this)">
<input id="eleId" name="id" type="hidden" value="${bean.userId}"/>
<table border="0" cellspacing="0" cellpadding="0" class="lyt_view">
  <tr>
    <td class="lyt_view_note" >帐号</td>
    <td>
      <input   style="width:200px;" id="userCardId" name="userCard" type="text" value="<c:out value='${bean.userCard}' />" maxlength="50" />
      <input type="button" name="button"  id="checkId" value="检测账号" class="tmp_btn" onClick="doUser()">
    </td>
     <td class="lyt_view_note">姓名</td>
    <td><input name="userName" type="text" value="<c:out value='${bean.userName}' />" maxlength="30" style="width:200px;"/></td>
     <td class="lyt_view_note">有效性</td>
    <td>
    <select name="isopen" style="width:200px;">
    <option value="1"  <c:if test="${bean.userOpen==1}">selected</c:if>>启用</option>
    <option value="2" <c:if test="${bean.userOpen==2}">selected</c:if>>停用</option>
    </select>
    </td>
  </tr>
  <tr>
    <td style="height:auto;" colspan="6"><span id="showId" style="color:#F00;"></span></td>
  </tr>
  <tr>
    <td class="lyt_view_note">类别</td>
    <td>
	<select id="starId" name="starCid" onChange="isShow()" style="width:200px;">
  		<option value="0" <c:if test="${bean.starCid=='0'}">selected</c:if>>非统一身份认证用户</option>
  		<option value="1" <c:if test="${bean.starCid=='1'}">selected</c:if>>统一身份认证用户</option>
	</select>
    </td>
     <td class="lyt_view_note">邮箱</td>
    <td colspan="3"><input name="relEmail" type="text" value="<c:out value='${bean.relEmail}' />" maxlength="50" style="width:200px;"/></td>
  </tr>
  <tr id="trPwdId" style="display:none;">
    <td class="lyt_view_note" >密码</td>
    <td>
      <input name="userPwd" type="password" <c:if test="${bean!=null}">value="******"</c:if>  maxlength="30" style="width:200px;"/>
    </td>
      <td class="lyt_view_note">确认密码</td>
    <td colspan="3">
      <input name="userPwd_try" type="password" <c:if test="${bean!=null}">value="******"</c:if>  maxlength="30" style="width:200px;"/>
    </td>
  </tr>
  <tr>
    <td class="lyt_view_note">手机</td>
    <td><input name="relHandset" type="text" value="<c:out value='${bean.relHandset}' />" maxlength="20" style="width:200px;"/></td>
      <td class="lyt_view_note">电话</td>
    <td colspan="3"><input name="relTelephone" type="text" value="<c:out value='${bean.relTelephone}' />" maxlength="20" style="width:200px;"/></td>
  </tr>
  <tr>
    <td class="lyt_view_area">备注</td>
    <td colspan="5"><textarea name="memo" style="width:800px;height:60px;"><c:out value='${bean.memo}' /></textarea></td>
  </tr>
</table>
<div class="lyt_submit">
	<input type="submit" value="确定" />
    <input type="button" value="取消" onClick="location.href='<%=basePath%>userList.action'"/>
</div>
</form>
</div>
</body>
</html>