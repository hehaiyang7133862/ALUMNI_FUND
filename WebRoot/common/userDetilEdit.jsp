<%@ include file="../common/include.jsp"%>
<%@ page language="java" isELIgnored="false" import="java.util.*"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<title>编辑</title>
<%@ include file="../common/head.jsp"%>
<script language="javascript" src="<%=basePath%>UI/fckeditor/fckeditor.js"></script>
<script language="javascript">
   function validateFrmAdd(form){
   formFormat("form1Id");
	var msg="";
	if(""==form.username.value){
		msg=msg+"请输入中文姓名<br />";
		form.username.style.backgroundColor=error;
	}
	if(""==form.telephone.value){
		msg=msg+"请输入常用电话<br />";
		form.telephone.style.backgroundColor=error;
	}
	if(""==form.mobilephone.value){
		msg=msg+"请输入常用手机<br />";
		form.mobilephone.style.backgroundColor=error;
	}
	if(""==form.firstmail.value){
		msg=msg+"请输入常用邮箱<br />";
		form.firstmail.style.backgroundColor=error;
	}
	// 说明
	if(!(getLength(form.memo.value)<=1000)){
		msg+="备注不能超过1000字节<br />";
		form.memo.style.backgroundColor=error;
	}
	
	if(msg!=""){
		Ext.Msg.alert("错误信息",msg);
		return false;
	}else{
	   return true;
	}
	
}
      
</script>
</head>
<body>
<!-- ### -->
<div class="float">
<form action="<%=basePath%>doModifyuserDetil!doModifyuserDetil.action" method="post" " id="form1Id" onSubmit="return validateFrmAdd(this)" target="">
<c:if test="${flag==1}">
<table   border="0" cellpadding="0" cellspacing="0" class="lyt_view">
		  
<tr>
    <td  align="right" class="lyt_view_note">中文姓名</td>
    <td  align="left">
    <input type="text" name="username" style="width:150px;" maxlength="50" value="<c:out value='${tbAlumniSt.nameCn}'/>">
  </td>
  </tr>
 <tr>
    <td align="right"  class="lyt_view_note">性别</td>
    <td align="left">
    <select name="sexCid" id="select" style="width:150px;">
             <option value="1" <c:if test="${tbAlumniSt.sexCid=='1'}">selected</c:if>>男</option>
             <option value="2" <c:if test="${tbAlumniSt.sexCid=='2'}">selected</c:if>>女</option>
             </select>
   </td>
  </tr>
   <tr>
    <td align="right"  class="lyt_view_note">常用电话</td>
    <td align="left"><input type="text" name="telephone" style="width:150px;" maxlength="50" value="<c:out value='${tbAlumniSt.telephoneFirst}'/>"></td>
  </tr>
  <tr>
    <td align="right"  class="lyt_view_note">常用手机</td>
    <td align="left"> <input type="text" name="mobilephone" style="width:150px;" maxlength="50" value="<c:out value='${tbAlumniSt.handsetFirst}'/>"></td>
  </tr>
  <tr>
    <td align="right"  class="lyt_view_note">常用邮箱</td>
     <td align="left"><input type="text" name="firstmail" style="width:150px;" maxlength="50" value="<c:out value='${tbAlumniSt.mailFirst}'/>"></td>
  </tr>
  <tr>
    <td align="right" valign="top" class="lyt_view_note">备注</td>
    <td align="left"><textarea name="memo" id="textarea2" cols="70" rows="5"><c:out value='${tbAlumniSt.selfBase}'/></textarea></td>
  </tr>
</table>
</c:if>
<c:if test="${flag==2}">
<table   border="0" cellpadding="0" cellspacing="0" class="lyt_view">	  
<tr>
    <td  align="right" class="lyt_view_note">中文姓名</td>
    <td  align="left">
    <input type="text" name="username" style="width:150px;" maxlength="50" value="<c:out value='${tbUnAlumniSt.nameCn}'/>">
  </td>
  </tr>
  <tr>
    <td align="right"  class="lyt_view_note">密码</td>
    <td align="left"> <input type="password" name="passwd"  style="width:150px;" maxlength="50" value="<c:out value='${userPwd}'/>"></td>
  </tr>
 <tr>
    <td align="right"  class="lyt_view_note">性别</td>
    <td align="left">
    <select name="sexCid" id="select" style="width:150px;">
             <option value="1" <c:if test="${tbUnAlumniSt.sexCid=='1'}">selected</c:if>>男</option>
             <option value="2" <c:if test="${tbUnAlumniSt.sexCid=='2'}">selected</c:if>>女</option>
             </select>
   </td>
  </tr>
   <tr>
    <td align="right"  class="lyt_view_note">常用电话</td>
    <td align="left"><input type="text" name="telephone" style="width:150px;" maxlength="50" value="<c:out value='${tbUnAlumniSt.telephoneFirst}'/>"></td>
  </tr>
  <tr>
    <td align="right"  class="lyt_view_note">常用手机</td>
    <td align="left"> <input type="text" name="mobilephone" style="width:150px;" maxlength="50" value="<c:out value='${tbUnAlumniSt.handsetFirst}'/>"></td>
  </tr>
  <tr>
    <td align="right"  class="lyt_view_note">常用邮箱</td>
     <td align="left"><input type="text" name="firstmail" style="width:150px;" maxlength="50" value="<c:out value='${tbUnAlumniSt.mailFirst}'/>"></td>
  </tr>
  <tr>
    <td align="right" valign="top" class="lyt_view_note">备注</td>
    <td align="left"><textarea name="memo" id="textarea2" cols="70" rows="5"><c:out value='${tbUnAlumniSt.selfBase}'/></textarea></td>
  </tr>
</table>
</c:if>
 <div  class="lyt_submit" >
<input type="submit" value="保存" class="btn_m" />
<input name="" type="reset" value="重置" />
</div>
</form>
</div>
	</body>
</html>
