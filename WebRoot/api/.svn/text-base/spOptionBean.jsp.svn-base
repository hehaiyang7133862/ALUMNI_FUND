<%@ page language="java" pageEncoding="UTF-8"%><%@ include file="../common/include.jsp"%><% String basePath = request.getScheme()+"://"+request.getServerName(); if(("http".equals(request.getScheme().toLowerCase()) && request.getServerPort()==80) || ("https".equals(request.getScheme().toLowerCase()) && request.getServerPort()==443)){ basePath += request.getContextPath()+"/"; }else{ basePath += ":"+request.getServerPort()+request.getContextPath()+"/"; } %>
{
	result:'success',
	id:'<c:out value="${bean.detailId}"/>',
	comm:'<c:out value="${bean.commId}"/>',
	name:'<c:out value="${bean.detailName}"/>',
	pic:'<c:if test="${not empty bean.detailPic}"><%=basePath%><c:out value="${bean.detailPic}"/></c:if>',
	intro:'<my:escapeHtml value="${bean.detailIntro}" removeTarget="false" changePath="false"/>',
	stock:'<fmt:formatNumber value="${bean.stockNum}" pattern="0" type="number"/>',
	costFee:'<fmt:formatNumber value="${bean.costFee}" pattern="0.##" type="number"/>',
	saleFee:'<fmt:formatNumber value="${bean.saleFee}" pattern="0.##" type="number"/>',
	shelves:'<c:out value="${bean.isShelves}"/>',
	order:'<c:out value="${bean.numOrder}"/>',
	memo:'<my:escapeHtml value="${bean.memo}" removeTarget="false" changePath="false"/>'
}