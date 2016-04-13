<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String pathMenu1 = request.getContextPath();
String basePathMenu1 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathMenu1+"/";
%>
<script language="javascript">
$(document).ready(function(){
	$('#nav_GA li').each(function(i){
		$(this).click(function(){
			$('.nav_GA li').css("background","url(UI/images/nav_point.png) no-repeat top left");
			$('.nav_GA li').find("a").css("color","#333");
			$(this).css("background","url(UI/images/nav_point_on.png) no-repeat top left");
			$(this).find("a").css("color","#FFF");
		});
		if((""=="${num}" && i==0)||(""!="${num}" && (i+5)=="${num}")){
			$(this).css("background","url(UI/images/nav_point_on.png) no-repeat top left");
			$(this).find("a").css("color","#FFF");
		}
	});
});
</script>
<s:token></s:token>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="nav_GA_left" width="2" height="28"></td>
    <td class="nav_GA_center">
	<div id="nav_GA" class="nav_GA" style="position: relative;">
	    <ul>
	    	<c:if test="${proType=='1'}">
		   		<li><a href="<%=basePathMenu1%>found!toProPage.action?proType=1&foundId=${foundId}&proId=${proId}&num=5">基本信息</a></li>
	    	</c:if>
	    	<c:if test="${proType=='2'}">
		   		<li><a href="<%=basePathMenu1%>found!toProZcPage.action?proType=2&foundId=${foundId}&proId=${proId}&num=5">基本信息</a></li>
	    	</c:if>
	   		<c:if test="${not empty proId}">
		   		<li><a href="<%=basePathMenu1%>found!protocolPic.action?proType=${proType}&foundId=${foundId }&proId=${proId}&num=6">协议图片</a></li>
		   		<li><a href="<%=basePathMenu1%>found!protocolParam.action?proType=${proType}&foundId=${foundId }&proId=${proId}&num=7">协议参数</a></li>
	   		</c:if>
	    </ul>
	</div>
    </td>
    <td width="2"  class="nav_GA_right">
    </td>
  </tr>
</table>


