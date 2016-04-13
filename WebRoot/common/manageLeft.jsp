<%@ include file="include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="head.jsp"%>
<link href="<%=basePathHead%>common/manageLeft.css" rel="stylesheet" type="text/css" />
<link href="manageLeft.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript">
function showHide(obj){
	var flag=$(obj).next(".tmp_flex").is(":hidden");
	// 全部关闭
	$(document).find(".tmp_flex").css("display","none");
	// 显示
	if(flag){
		$(obj).nextAll(".tmp_flex").css("display","");
	}
	else{
		$(obj).nextAll(".tmp_flex").css("display","none");
	}
	// 设置表格宽度
	if($(window).height()!=$(document).height()){
		$("table").attr("width","200");
	}
	else{
		$("table").attr("width","200");
	}
}
// 设置表格宽度
$(document).ready(function(){
	if($(window).height()!=$(document).height()){
		$("table").attr("width","200");
	}
	else{
		$("table").attr("width","200");
	}
	// 全部关闭
	// $(document).find(".tmp_flex").css("display","none");
	// $(document).find("table:first").find(".tmp_flex").css("display","block");
	$(".tmp_flex").attr("clicked",false);
	$(".tmp_flex").mouseover(function(){
		if($(this).attr("clicked") == true){
			return;
		}
		var i = 0;
		$(this).children("td").each(function(){
			if(i == 1){
				$(this).addClass("menu_l_over");
			}
			if(i == 2){
				$(this).addClass("menu_r_over");
			}
			if(i == 3){
				$(this).removeClass("menu_r");
				$(this).addClass("menu_r_over");
			}
			i++;
		});
	}).mouseout(function(){
		if($(this).attr("clicked") == true){
			return;
		}
		var i = 0;
		$(this).children("td").each(function(){
			if(i == 1){
				$(this).removeClass("menu_l_over");
			}
			if(i == 2){
				$(this).removeClass("menu_r_over");
			}
			if(i == 3){
				$(this).removeClass("menu_r_over");
				$(this).addClass("menu_r");
			}
			i++;
		});
	});
	$(".tmp_flex").children().children().click(function(){
		if($(this).parent().parent().attr("clicked") == true){
			return;
		}
		$(this).parent().parent().parent().parent().parent().children("table").each(function(){
			$(this).children().children(".tmp_flex").each(function(){
				$(this).attr("clicked",false);
			});
		});
		$(this).parent().parent().attr("clicked",true);
		$(this).parent().parent().parent().parent().parent().children("table").each(function(){
			$(this).children().children(".tmp_flex").each(function(){
				if($(this).attr("clicked") == false && $(this).children("td:gt(0)").hasClass("menu_l_over")){
					var i = 0;
					$(this).children("td").each(function(){
						if(i == 1){
							$(this).removeClass("menu_l_over");
						}
						if(i == 2){
							$(this).removeClass("menu_r_over");
						}
						if(i == 3){
							$(this).removeClass("menu_r_over");
							$(this).addClass("menu_r");
						}
						i++;
					});
				}
			});
		});
	});
});
function addTab(nav, tit, link) {
	parent.addTab(nav, tit, link);
}	
</script>
<style type="text/css">
.fnt_span{
	color:#0099FF;
	text-decoration:none;
}
</style>
</head>
<body style="overflow-x:hidden;">
<div class="lyt_menu">
<c:forEach items="${list_node}" var="node" varStatus="status">
<c:if test='${obj_id==node.parentId &&fn:contains(role_menu,node.code)}'>
<table width="200" border="0" cellspacing="0" cellpadding="0" class="m_menu" style="background-color:#FFFFFF;">
<tr>
<td colspan="4" style="background-color:#d1ecf3;" height="2"></td>
</tr>
<tr>
<td class="menu_tl" width="5" height="5"></td>
<td class="menu_t" width="50"></td>
<td class="menu_t"></td>
<td class="menu_tr" width="5"></td>
</tr>
<tr onclick='showHide(this)'>
<td class="menu_l">
</td>
<td valign="top" width="50">
	<img width="48" height="48" src="<c:out value='${node.urlIcon}' />" />
</td>
<td>
	<h2 class="folded" style="margin-top:5px;"><c:out value="${node.menuName}"/></h2>
	<div style="margin:0px 0px 0px 3px;color:#009933;"><c:out value='${node.memo}' /></div>
</td>
<td class="menu_r" >
</td>
</tr>
<c:forEach items="${list_leaf}" var="leaf" varStatus="status2">
<c:if test="${leaf.parentId==node.menuId}">
<c:if test='${fn:contains(role_menu,leaf.code)}'>
<tr class="tmp_flex">
<td class="menu_l">
</td>
<td align="right" valign="middle" style="width:50px;" onclick="addTab('<c:out value="${node.menuName}"/> > <c:out value="${leaf.menuName}"/>','<c:out value="${leaf.menuName}"/>','<%=basePath%>nav.action?url=<c:out value="${leaf.urlLink}"/>&code=<c:out value="${leaf.menuId}"/>')">
	<div style="margin-right:8px;"><img src="UI/icons/sub.png" /></div>
</td>
<td valign="middle" onclick="addTab('<c:out value="${node.menuName}"/> > <c:out value="${leaf.menuName}"/>','<c:out value="${leaf.menuName}"/>','<%=basePath%>nav.action?url=<c:out value="${leaf.urlLink}"/>&code=<c:out value="${leaf.menuId}"/>')">
	<div style="margin-top:2px;">
	<span class="fnt_span"><c:out value="${leaf.menuName}" /></span>
    </div>
</td>
<td class="menu_r" onclick="addTab('<c:out value="${node.menuName}"/> > <c:out value="${leaf.menuName}"/>','<c:out value="${leaf.menuName}"/>','<%=basePath%>nav.action?url=<c:out value="${leaf.urlLink}"/>&code=<c:out value="${leaf.menuId}"/>')">
</td>
</tr>
</c:if>
</c:if>
</c:forEach>
<tr>
<td class="menu_bl" height="5">
</td>
<td class="menu_b"></td>
<td class="menu_b"></td>
<td class="menu_br">
</td>
</tr>
</table>
</c:if>
</c:forEach>
</div>
</body>
</html>
