<%@ page language="java" pageEncoding="UTF-8"%><%@ include file="../common/include.jsp"%><% String basePath = request.getScheme()+"://"+request.getServerName(); if(("http".equals(request.getScheme().toLowerCase()) && request.getServerPort()==80) || ("https".equals(request.getScheme().toLowerCase()) && request.getServerPort()==443)){ basePath += request.getContextPath()+"/"; }else{ basePath += ":"+request.getServerPort()+request.getContextPath()+"/"; } %>
{
	result:'success',
	id:'<c:out value="${bean.gressId}"/>',
	proj:'<c:out value="${bean.projId}"/>',
	time:'<fmt:formatDate value="${bean.gressTime}" pattern="yyyy-MM-dd"/>',
	intro:'<my:escapeHtml value="${bean.gressIntro}" removeTarget="false" changePath="false"/>',
	content:'<my:escapeHtml value="${bean.gressContent}" removeTarget="false" changePath="true"/>'
}