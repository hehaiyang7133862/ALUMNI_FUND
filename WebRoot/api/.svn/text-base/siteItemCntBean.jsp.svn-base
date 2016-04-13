<%@ page language="java" pageEncoding="UTF-8"%><%@ include file="../common/include.jsp"%><% String basePath = request.getScheme()+"://"+request.getServerName(); if(("http".equals(request.getScheme().toLowerCase()) && request.getServerPort()==80) || ("https".equals(request.getScheme().toLowerCase()) && request.getServerPort()==443)){ basePath += request.getContextPath()+"/"; }else{ basePath += ":"+request.getServerPort()+request.getContextPath()+"/"; } %>
{
	result:'success',
	id:'<c:out value="${bean.contentId}"/>',
	item:'<c:out value="${bean.itemId}"/>',
	logo:'<c:if test="${not empty bean.contentLogo}"><%=basePath%><c:out value="${bean.contentLogo}"/></c:if>',
	title:'<c:out value="${bean.contentTitle}"/>',
	intro:'<my:escapeHtml value="${bean.contentIntro}" removeTarget="false" changePath="false"/>',
	type:'<c:out value="${bean.contentType}"/>',
	linkUrl:'<c:out value="${bean.linkUrl}"/>',
	content:'<my:escapeHtml value="${bean.contentCnt}" removeTarget="false" changePath="true"/>',
	publish:'<c:out value="${bean.publishFlag}"/>',
	publishTime:'<fmt:formatDate value="${bean.publishTime}" pattern="yyyy-MM-dd HH:mm"/>',
	memo:'<my:escapeHtml value="${bean.memo}" removeTarget="false" changePath="false"/>'
}