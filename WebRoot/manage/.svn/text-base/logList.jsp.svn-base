<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<script src="<%=basePathHead%>UI/js/calendar.js" type="text/javascript"></script>
<script language="javascript">
window.onload=function(){
	myLoad();
}
function checkForm(form){
	// 格化表单
	formFormat("recordid");
	var msg="";
	// 返回
	var logindate1=form.logindate1.value;
	var logindate2=form.logindate2.value;
	if((""==logindate1&&""!=logindate2)||(""!=logindate1&&""==logindate2)){
		msg+="登录日期必须成对出现!<br/>";
		form.logindate1.style.backgroundColor=error;
		form.logindate2.style.backgroundColor=error;
	}
	if(msg!=""){
		Ext.Msg.alert("错误信息",msg);
		return false;
	}
	else{
		return true;
	}
}
function refresh(){ 
     document.getElementById("recordid").submit();
}
</script>
</head> 
<body>
<div class="page">
<%@ include file="../common/navigation.jsp"%>
<form id="recordid" action="<%=basePath%>dolistlog!dolistlog.action" method="post" onSubmit="return checkForm(this)">
<table class="lyt_search" width="100%">
 <tr>
    <td align="right" class="lyt_search_note">时间</td>
    <td colspan="3" >
    	<input style="width:90px;" type="text" name="logindate1" value='<c:out value="${logindate1}" />'
		onClick="new Calendar('1900','<%=Calendar.getInstance().get(1)%>', 0).show(this)"  readonly="true" class="ele_date" />
		&nbsp;至&nbsp;
        <input style="width:90px;" type="text" name="logindate2" value='<c:out value="${logindate2}" />'
		onClick="new Calendar('1900','<%=Calendar.getInstance().get(1)%>', 0).show(this)"  readonly="true" class="ele_date"/>
	</td>
    <td class="lyt_search_note" >姓名</td>
    <td class="lyt_search_txt" style="width:80px;">
    	<input style="width:80px;" name="name1" type="text" value="<c:out value='${name}' />" maxlength="50" />
    </td>
    <td class="lyt_search_note" >登录IP</td>
    <td >
      <input name="landip1" style="width:90px;" type="text" value="<c:out value='${landip}' />" maxlength="50" />
    </td>
    <td class="lyt_search_note" >操作类型</td>
    <td >
      <select name="handType" style="width:100px;">
      	<option value="" <c:if test="${handType=='' }">selected</c:if>>--请选择--</option>
      	<option value="1" <c:if test="${handType=='1' }">selected</c:if>>登陆</option>
      	<option value="2" <c:if test="${handType=='2' }">selected</c:if>>保存</option>
      	<option value="3" <c:if test="${handType=='3' }">selected</c:if>>更新</option>
      	<option value="4" <c:if test="${handType=='4' }">selected</c:if>>删除</option>
      </select>
    </td>
    <td width="260px">
    	<div class="lyt_button" style="float:right">
			<input type="submit" value="查询" />
		    <input type="button" value="重置" onClick="formReset('recordid')" />
		    <input type="button" value="删除" onclick='MyFormWin.showWin("删除","<%=basePath%>dolistlog!dolistlog.action?tag=del");return false;'/>
		</div>
    </td>
  </table>

</form>
<table width="100%" border="0" cellspacing="0" class="lyt_result">
  <tr>
    <th width="60">日志ID</th>
    <th>姓名</th>
    <th>登录IP</th>
    <th>操作时间</th>
    <th>操作类型</th>
    <th>操作标识</th>
  </tr>
  <c:forEach items="${list_log}" var="tmp" varStatus="status">
  <tr>
    <td><b><c:out value="${tmp.loginId}" /></b>&nbsp;</td>
    <td><c:out value="${tmp.loginName}" />&nbsp;</td>
    <td>
    	<c:out value="${tmp.loginIp}" />
    </td>
    <td>
    	<fmt:formatDate  pattern="yyyy-MM-dd HH:mm:ss" value="${tmp.updateTime}"/>
 	</td>
    <td>
    	<c:out value="${tmp.handType}"></c:out>
    </td>
    <td>
    	<c:out value="${tmp.handSign}"></c:out>
    </td>
  </tr>
  </c:forEach>
</table>
<my:page action="dolistlog!dolistlog.action"></my:page>
</div>
</body>
</html>
