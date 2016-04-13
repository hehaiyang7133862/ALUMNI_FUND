<%@ page language="java" pageEncoding="UTF-8"%>
<%
String pathMenu = request.getContextPath();
String basePathMenu  = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathMenu+"/";
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
<c:set var="grandId" value='<%=request.getParameter("grandId")%>'></c:set>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="nav_GA_center">
		<div class="nav_GA">
		    <ul>
		    <li><a href="<%=basePathMenu%>commodity!operation.action?commId=${bean.commId}&num=0">商品信息</a></li>
		    <li><a href="<%=basePathMenu%>commodity!info.action?commId=${bean.commId}&num=1">商品图片</a></li>
		    <li><a href="<%=basePathMenu%>commodity!info.action?commId=${bean.commId}&num=2">商品参数</a></li>
		    <li><a href="<%=basePathMenu%>commodity!info.action?commId=${bean.commId}&num=3">商品型号</a></li>
		    <li><a href="<%=basePathMenu%>commodity!info.action?commId=${bean.commId}&num=4">商品订单</a></li>
		    </ul>
		</div>
    </td>
  </tr>
</table>