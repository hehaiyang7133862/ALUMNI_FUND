<%@ page language="java" pageEncoding="UTF-8"%>
<%
String pathMenu = request.getContextPath();
String basePathMenu = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathMenu+"/";
%>
<script language="javascript">
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
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="nav_GA_left" width="2" height="28"></td>
    <td class="nav_GA_center">
<div class="nav_GA">
    <ul>
   <li><a href="<%=basePathMenu%>menuForward.action?code=menu&num=0">菜单管理</a></li>
   <li><a href="<%=basePathMenu%>menuForward.action?code=button&num=1">权限管理</a></li>
    </ul>
</div>
    </td>
    <td width="2"  class="nav_GA_right"></td>
  </tr>
</table>
