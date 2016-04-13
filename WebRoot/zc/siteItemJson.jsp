<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("enterChar","\r\n");
%>
<c:set var="len" value="${fn:length(list)-1}" ></c:set>
[
<c:choose>
<c:when test="${type=='group'}">
	<c:forEach items="${list}" var="ele" varStatus="i">
	{
		id:'<c:out value="${type}"/><c:out value="${ele.fieldId}"/>',
		name:'<c:out value="${ele.fieldName}"/>',
		text:'<c:out value="${ele.fieldName}"/>（<c:out value="${ele.code}"/>）',
		title:'分组名称：<c:out value="${ele.fieldName}"/>&#10;分组编码：<c:out value="${ele.code}"/>&#10;栏目数量：<c:out value="${countList[i.index]}"/>&#10;备注说明：<c:out value="${fn:replace(ele.memo,enterChar,'')}"/>',
		singleClickExpand : true,
		expanded:<c:choose><c:when test="${countList[i.index]==0}">true</c:when><c:otherwise>false</c:otherwise></c:choose>
	}
	<c:if test="${i.index<len}">,</c:if>
	</c:forEach>
</c:when>
<c:otherwise>
	<c:forEach items="${list}" var="ele" varStatus="i">
	{
		id:'<c:out value="${type}"/><c:out value="${ele.itemId}"/>',
		name:'<c:out value="${ele.itemName}"/>',
		text:'<c:out value="${ele.itemName}"/>（<c:choose><c:when test="${ele.itemType=='1'}">正文</c:when><c:when test="${ele.itemType=='2'}">列表</c:when><c:when test="${ele.itemType=='9'}">链接</c:when></c:choose>）',
		title:'栏目名称：<c:out value="${ele.itemName}"/>&#10;栏目类型：<c:choose><c:when test="${ele.itemType=='1'}">正文</c:when><c:when test="${ele.itemType=='2'}">列表</c:when><c:when test="${ele.itemType=='9'}">链接，<c:out value="${ele.linkUrl}"/></c:when></c:choose>&#10;对外发布：<c:choose><c:when test="${ele.publishFlag=='1'}">是</c:when><c:otherwise>否</c:otherwise></c:choose>&#10;备注说明：<c:out value="${fn:replace(ele.memo,enterChar,'')}"/>',
		singleClickExpand : true,
		expanded:true
	}
	<c:if test="${i.index<len}">,</c:if>
	</c:forEach>
</c:otherwise>
</c:choose>
]