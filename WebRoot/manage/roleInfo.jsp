<%@ page language="java" pageEncoding="UTF-8"%>
<%
String pathMenu = request.getContextPath();
String basePathMenu  = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathMenu+"/";
%>
<script language="javascript">
function flex(){
	var display=document.getElementById("info1Id").style.display;
	if(display=="none"){
		document.getElementById("info1Id").style.display="block";
		document.getElementById("info2Id").style.display="block";
		document.getElementById("info3Id").style.display="block";
	}
	else{
		document.getElementById("info1Id").style.display="none";
		document.getElementById("info2Id").style.display="none";
		document.getElementById("info3Id").style.display="none";
	}
	setFrmHeight(parent.document.getElementById("iFrameId"));
}
<c:if test='${code=="menu"}'>
	<c:set var="num" value="0" />
</c:if>
<c:if test='${code=="button"}'>
	<c:set var="num" value="1" />
</c:if>
$(document).ready(function(){
	$('.nav_GA li').each(function(i){
		$(this).click(function(){
			$('.nav_GA li').css("background","url(UI/images/nav_point.png) no-repeat top left");
			$('.nav_GA li').find("a").css("color","#333");
			$(this).css("background","url(UI/images/nav_point_on.png) no-repeat top left");
			$(this).find("a").css("color","#FFF");
		});
		if((""=="${num}" && i==0)||(""!="${num}" && (i+"")=="${num}")){
			$(this).css("background","url(UI/images/nav_point_on.png) no-repeat top left");
			$(this).find("a").css("color","#FFF");
		}
	});
});
</script>
<table border="0" cellpadding="0" cellspacing="0" class="lyt_view" width="100%">
  <tr>
    <td class="lyt_view_note" width="80">角色名称</td>
    <td>${bean_role.roleName}</td>
  </tr>
</table>
<c:set var="grandId" value='<%=request.getParameter("grandId")%>'></c:set>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="nav_GA_left" width="2" height="28"></td>
    <td class="nav_GA_center">
<div class="nav_GA">
    <ul>
    <li><a href="<%=basePathMenu%>menuPrivilege!pre.action?roleId=${bean_role.roleId}&grandId=${grandId}&code=menu&num=0">菜单分配</a></li>
    <c:if test="${bean_role.code!='ALUMNI'&&bean_role.code!='CHECK'}">
    <li><a href="<%=basePathMenu%>prebutton!prebutton.action?roleId=${bean_role.roleId}&grandId=${grandId}&code=button&num=1">权限分配</a></li>
    <li><a href="<%=basePathMenu%>userInList.action?roleId=${bean_role.roleId}&grandId=${grandId}&num=2">人员分配</a></li>
     </c:if>
    </ul>
</div>
    </td>
    <td width="2"  class="nav_GA_right"></td>
  </tr>
</table>