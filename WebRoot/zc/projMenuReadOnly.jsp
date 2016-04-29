<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String pathMenu = request.getContextPath();
String basePathMenu = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathMenu+"/";
%>
<script language="javascript">
$(document).ready(function(){
	$('#topMenu_nav_GA li').each(function(i){
		$(this).click(function(){
			$('#topMenu_nav_GA li').css("background","url(UI/images/nav_point.png) no-repeat top left");
			$('#topMenu_nav_GA li').find("a").css("color","#333");
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
<c:out value="${bean.projName}"/>（显示发布时间：<fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${bean.shelvesTime}'/>； 目标金额：<fmt:formatNumber value='${bean.targetAmout}' pattern='0.##' type='number'/> 元； 众筹时间：<fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${bean.begTime}'/> ~ <fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${bean.endTime}'/>； <c:choose><c:when test="${bean.shelvesFlag=='1'}">已上架； <c:choose><c:when test="${bean.zcStatus=='1'}">即将开始</c:when><c:when test="${bean.zcStatus=='2'}">众筹中</c:when><c:when test="${bean.zcStatus=='3'}">已结束</c:when></c:choose>； </c:when><c:otherwise>未上架； </c:otherwise></c:choose><c:if test="${bean.hotFlag=='1'}">热门； </c:if>众筹进度：<fmt:formatNumber value='${bean.zcedPercent}' pattern='0.00' type='number'/> %<c:if test="${bean.zcedPercent>0}">； 已筹得金额：<fmt:formatNumber value='${bean.zcedAmout}' pattern='0.##' type='number'/> 元【<fmt:formatNumber value='${bean.zcedCount}' pattern='0' type='number'/> 份】</c:if>）
</div>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="nav_GA_left" width="2" height="28"></td>
    <td class="nav_GA_center">
	<div id="topMenu_nav_GA" class="nav_GA" style="position: relative;">
	    <ul>
		    <li><a href="<%=basePathMenu%>zcProjEditReadOnly.action?projId=${bean.projId}&num=0">基本信息</a></li>
		    <li><a href="<%=basePathMenu%>zcProjEditReadOnly!picList.action?projId=${bean.projId}&num=1">项目图片</a></li>
		    <li><a href="<%=basePathMenu%>zcProjEditReadOnly!optionList.action?projId=${bean.projId}&num=2">项目选项</a></li>
		    <li><a href="<%=basePathMenu%>zcProjEditReadOnly!gressList.action?projId=${bean.projId}&num=3">项目进展</a></li>
		    <li><a href="<%=basePathMenu%>zcProjEditReadOnly!orderList.action?projId=${bean.projId}&num=4">捐赠列表</a></li>
	    </ul>
	</div>
    </td>
    <td width="2"  class="nav_GA_right">
    </td>
  </tr>
</table>


