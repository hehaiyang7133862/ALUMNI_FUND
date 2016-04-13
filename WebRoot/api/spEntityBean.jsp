<%@ page language="java" pageEncoding="UTF-8"%><%@ include file="../common/include.jsp"%><% String basePath = request.getScheme()+"://"+request.getServerName(); if(("http".equals(request.getScheme().toLowerCase()) && request.getServerPort()==80) || ("https".equals(request.getScheme().toLowerCase()) && request.getServerPort()==443)){ basePath += request.getContextPath()+"/"; }else{ basePath += ":"+request.getServerPort()+request.getContextPath()+"/"; } %>
{
	result:'success',
	id:'<c:out value="${bean.commId}"/>',
	name:'<c:out value="${bean.commName}"/>',
	type:'<c:out value="${bean.commType}"/>',
	shelves:'<c:out value="${bean.isShelves}"/>',
	hot:'<c:out value="${bean.isHot}"/>',
	hotOrder:'<c:out value="${bean.hotOrder}"/>',
	pic:[
	<c:forEach items="${bean.tbCommodityPics}" var="picTemp" varStatus="picStatus">
	<c:if test="${picStatus.index>0}">,</c:if>
	{
		id:'<c:out value="${picTemp.picId}"/>',
		alt:'<my:escapeHtml value="${picTemp.picAlt}" removeTarget="false" changePath="false"/>',
		path:'<c:if test="${not empty picTemp.picPath}"><%=basePath%><c:out value="${picTemp.picPath}"/></c:if>'
	}
	</c:forEach>
	],
	picLarge:[
	<c:forEach items="${bean.tbCommodityLargePics}" var="picTemp" varStatus="picStatus">
	<c:if test="${picStatus.index>0}">,</c:if>
	{
		id:'<c:out value="${picTemp.picId}"/>',
		alt:'<my:escapeHtml value="${picTemp.picAlt}" removeTarget="false" changePath="false"/>',
		path:'<c:if test="${not empty picTemp.picPath}"><%=basePath%><c:out value="${picTemp.picPath}"/></c:if>'
	}
	</c:forEach>
	],
	picNormal:[
	<c:forEach items="${bean.tbCommodityNormalPics}" var="picTemp" varStatus="picStatus">
	<c:if test="${picStatus.index>0}">,</c:if>
	{
		id:'<c:out value="${picTemp.picId}"/>',
		alt:'<my:escapeHtml value="${picTemp.picAlt}" removeTarget="false" changePath="false"/>',
		path:'<c:if test="${not empty picTemp.picPath}"><%=basePath%><c:out value="${picTemp.picPath}"/></c:if>'
	}
	</c:forEach>
	],
	picSmall:[
	<c:forEach items="${bean.tbCommoditySmallPics}" var="picTemp" varStatus="picStatus">
	<c:if test="${picStatus.index>0}">,</c:if>
	{
		id:'<c:out value="${picTemp.picId}"/>',
		alt:'<my:escapeHtml value="${picTemp.picAlt}" removeTarget="false" changePath="false"/>',
		path:'<c:if test="${not empty picTemp.picPath}"><%=basePath%><c:out value="${picTemp.picPath}"/></c:if>'
	}
	</c:forEach>
	],
	param:[
	<c:forEach items="${bean.tbCommodityParams}" var="paramTemp" varStatus="paramStatus">
	<c:if test="${paramStatus.index>0}">,</c:if>
	{
		id:'<c:out value="${paramTemp.paramId}"/>',
		name:'<c:out value="${paramTemp.paramName}"/>',
		value:'<c:out value="${paramTemp.paramValue}"/>'
	}
	</c:forEach>
	],
	option:[
	<c:forEach items="${bean.tbCommodityPubDetails}" var="optionTemp" varStatus="optionStatus">
	<c:if test="${optionStatus.index>0}">,</c:if>
	{
		id:'<c:out value="${optionTemp.detailId}"/>',
		name:'<c:out value="${optionTemp.detailName}"/>',
		pic:'<c:if test="${not empty optionTemp.detailPic}"><%=basePath%><c:out value="${optionTemp.detailPic}"/></c:if>',
		intro:'<my:escapeHtml value="${optionTemp.detailIntro}" removeTarget="false" changePath="false"/>',
		stock:'<fmt:formatNumber value="${optionTemp.stockNum}" pattern="0" type="number"/>',
		costFee:'<fmt:formatNumber value="${optionTemp.costFee}" pattern="0.##" type="number"/>',
		saleFee:'<fmt:formatNumber value="${optionTemp.saleFee}" pattern="0.##" type="number"/>'
	}
	</c:forEach>
	],
	intro:'<my:escapeHtml value="${bean.commIntro}" removeTarget="false" changePath="false"/>',
	<c:if test="${exceptDetail!='1' && exceptDetail!='true'}">
	content:'<my:escapeHtml value="${bean.commDetail}" removeTarget="false" changePath="true"/>',
	</c:if>
	ship:'<c:out value="${bean.isShipping}"/>',
	shipFee:'<fmt:formatNumber value="${bean.shippingFee}" pattern="0.##" type="number"/>',
	shipMemo:'<my:escapeHtml value="${bean.shippingMemo}" removeTarget="false" changePath="false"/>',
	donation:'<c:out value="${bean.isDonation}"/>',
	donationFee:'<fmt:formatNumber value="${bean.donationFee}" pattern="0.##" type="number"/>',
	donationMemo:'<c:out value="${bean.donationMemo}"/>',
	createTime:'<c:out value="${fn:substring(bean.creationTime,0,16)}"/>',
	stockCount:'<fmt:formatNumber value="${bean.stockCount}" pattern="0" type="number"/>',
	saledCount:'<fmt:formatNumber value="${bean.saledCount}" pattern="0" type="number"/>',
	saleFeeMin:'<fmt:formatNumber value="${bean.saleFeeMin}" pattern="0.##" type="number"/>',
	saleFeeMax:'<fmt:formatNumber value="${bean.saleFeeMax}" pattern="0.##" type="number"/>'
}