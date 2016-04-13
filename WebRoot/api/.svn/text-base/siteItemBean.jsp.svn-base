<%@ page language="java" pageEncoding="UTF-8"%><%@ include file="../common/include.jsp"%><% String basePath = request.getScheme()+"://"+request.getServerName(); if(("http".equals(request.getScheme().toLowerCase()) && request.getServerPort()==80) || ("https".equals(request.getScheme().toLowerCase()) && request.getServerPort()==443)){ basePath += request.getContextPath()+"/"; }else{ basePath += ":"+request.getServerPort()+request.getContextPath()+"/"; } %>
{
	result:'success',
	id:'<c:out value="${bean.itemId}"/>',
	mgtRange:'<c:out value="${bean.mgtRange}"/>',
	groupCode:'<c:out value="${bean.groupCode}"/>',
	groupItemOrder:'<fmt:formatNumber value="${bean.groupItemOrder}" pattern="0" type="number"/>',
	name:'<c:out value="${bean.itemName}"/>',
	type:'<c:out value="${bean.itemType}"/>',
	linkUrl:'<c:out value="${bean.linkUrl}"/>',
	publish:'<c:out value="${bean.publishFlag}"/>',
	memo:'<my:escapeHtml value="${bean.memo}" removeTarget="false" changePath="false"/>'
}