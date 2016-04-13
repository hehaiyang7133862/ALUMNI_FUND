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
	// 返回
	if(msg!=""){
		alert(msg);
		return false;
	}else{
		MyMask();
	   return true;
	}
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
<div class="float">
<form id="formId" action="<%=basePath%>updatepassword!updatepassword.action" method="post" onSubmit="return checkForm(this)">
<input id="eleId" name="id" type="hidden" value="${bean.userId}"/>
<table border="0" cellspacing="0" cellpadding="0" class="lyt_view">
  <tr>
    <td class="lyt_view_note" >帐号</td>
    <td>
      <input   style="width:200px;" id="userCardId" name="userCard" type="text" value="<c:out value='${bean.userCard}' />" maxlength="50" disabled/>
    </td>
     <td class="lyt_view_note">姓名</td>
    <td><input name="userName" type="text" value="<c:out value='${bean.userName}' />" maxlength="30" style="width:200px;" disabled/></td>
  </tr>
  <tr>
    <td style="height:auto;" colspan="6"><span id="showId" style="color:#F00;"></span></td>
  </tr>
  <tr>
    <td class="lyt_view_note">类别</td>
    <td>
	<select id="starId" name="starCid" onChange="isShow()" style="width:200px;" disabled>
    <option value="0" <c:if test="${bean.starCid=='0'}">selected</c:if>>非统一身份认证用户</option>
  		<option value="1" <c:if test="${bean.starCid=='1'}">selected</c:if>>统一身份认证用户</option>
  		
	</select>
    </td>
     <td class="lyt_view_note">邮箱</td>
    <td><input name="relEmail" type="text" value="<c:out value='${bean.relEmail}' />" maxlength="50" style="width:200px;"/></td>
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
    <td><input name="relTelephone" type="text" value="<c:out value='${bean.relTelephone}' />" maxlength="20" style="width:200px;"/></td>
    
  </tr>
</table>
<div class="lyt_submit">
	<input type="submit" value="确定" />
    <input type="button" value="取消" onClick="parent.MyFormWin.close();"/>
</div>
</form>
</div>
</body>
</html>