<%@ page language="java" pageEncoding="UTF-8"%><%@ include file="../common/include.jsp"%><% String basePath = request.getScheme()+"://"+request.getServerName(); if(("http".equals(request.getScheme().toLowerCase()) && request.getServerPort()==80) || ("https".equals(request.getScheme().toLowerCase()) && request.getServerPort()==443)){ basePath += request.getContextPath()+"/"; }else{ basePath += ":"+request.getServerPort()+request.getContextPath()+"/"; } %>
{
	result:'success',
	id:'<c:out value="${bean.picId}"/>',
	comm:'<c:out value="${bean.commId}"/>',
	code:'<c:out value="${bean.numOrder}"/>',
	alt:'<my:escapeHtml value="${bean.picAlt}" removeTarget="false" changePath="false"/>',
	path:'<c:if test="${not empty bean.picPath}"><%=basePath%><c:out value="${bean.picPath}"/></c:if>',
	memo:'<my:escapeHtml value="${bean.memo}" removeTarget="false" changePath="false"/>'
}