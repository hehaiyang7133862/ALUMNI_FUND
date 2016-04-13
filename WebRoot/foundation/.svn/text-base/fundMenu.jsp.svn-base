<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	
	//计算长度
	var midWidth=$(".css3div").width();
	$("#mainDiv").css("width",midWidth+20);
});
</script>
<s:token></s:token>
<div style="padding:2px 10px 0px 10px;height:34px;line-height:34px;color:#15406d;font-weight:bold;overflow:hidden;">
<my:fundTip value="${foundId}" />
</div>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="nav_GA_left" width="2" height="28"></td>
    <td class="nav_GA_center">
	<div class="nav_GA" style="position: relative;">
	    <ul>
		    <li><a href="<%=basePathMenu%>found!toPage.action?foundId=${foundId }&num=0">基本信息</a></li>
		    <li><a href="<%=basePathMenu%>found!procl.action?foundId=${foundId }&num=1">协议管理</a></li>
	    	<li><a href="<%=basePathMenu%>found!pay.action?foundId=${foundId }&num=2">支出管理</a></li>
	   		<li><a href="<%=basePathMenu%>found!doc.action?foundId=${foundId }&num=3">执行文档</a></li>
	    </ul>
	</div>
    </td>
    <td width="2"  class="nav_GA_right">
    </td>
  </tr>
</table>


