<%@ page language="java" pageEncoding="UTF-8"%><%@ include file="../common/include.jsp"%><% String basePath = request.getScheme()+"://"+request.getServerName(); if(("http".equals(request.getScheme().toLowerCase()) && request.getServerPort()==80) || ("https".equals(request.getScheme().toLowerCase()) && request.getServerPort()==443)){ basePath += request.getContextPath()+"/"; }else{ basePath += ":"+request.getServerPort()+request.getContextPath()+"/"; } %>
{
	result:'success',
	count:'<c:out value="${count}"/>',
	page:'<c:out value="${page}"/>',
	<c:choose>
	<c:when test="${page=='1'}">
	pageSize:'<c:out value="${pageSize}"/>',
	pageCount:'<c:out value="${pageCount}"/>',
	pageNum:'<c:out value="${pageNum}"/>',
	</c:when>
	<c:otherwise>
	num:'<c:out value="${num}"/>',
	size:'<c:out value="${size}"/>',
	</c:otherwise>
	</c:choose>
	type:'<c:out value="${type}"/>',
	shelves:'<c:out value="${shelves}"/>',
	hot:'<c:out value="${hot}"/>',
	ship:'<c:out value="${ship}"/>',
	name:'<c:out value="${name}"/>',
	order:'<c:out value="${order}"/>',
	orderType:'<c:out value="${orderType}"/>',
	list:[
	<c:forEach items="${beanList}" var="temp" varStatus="status">
	<c:if test="${status.index>0}">,</c:if>
	{
		id:'<c:out value="${temp.commId}"/>',
		name:'<c:out value="${temp.commName}"/>',
		type:'<c:out value="${temp.commType}"/>',
		shelves:'<c:out value="${temp.isShelves}"/>',
		hot:'<c:out value="${temp.isHot}"/>',
		hotOrder:'<c:out value="${temp.hotOrder}"/>',
		pic:[
		<c:forEach items="${temp.tbCommodityPics}" var="picTemp" varStatus="picStatus">
		<c:if test="${picStatus.index>0}">,</c:if>
		{
			id:'<c:out value="${picTemp.picId}"/>',
			alt:'<my:escapeHtml value="${picTemp.picAlt}" removeTarget="false" changePath="false"/>',
			path:'<c:if test="${not empty picTemp.picPath}"><%=basePath%><c:out value="${picTemp.picPath}"/></c:if>'
		}
		</c:forEach>
		],
		picLarge:[
		<c:forEach items="${temp.tbCommodityLargePics}" var="picTemp" varStatus="picStatus">
		<c:if test="${picStatus.index>0}">,</c:if>
		{
			id:'<c:out value="${picTemp.picId}"/>',
			alt:'<my:escapeHtml value="${picTemp.picAlt}" removeTarget="false" changePath="false"/>',
			path:'<c:if test="${not empty picTemp.picPath}"><%=basePath%><c:out value="${picTemp.picPath}"/></c:if>'
		}
		</c:forEach>
		],
		picNormal:[
		<c:forEach items="${temp.tbCommodityNormalPics}" var="picTemp" varStatus="picStatus">
		<c:if test="${picStatus.index>0}">,</c:if>
		{
			id:'<c:out value="${picTemp.picId}"/>',
			alt:'<my:escapeHtml value="${picTemp.picAlt}" removeTarget="false" changePath="false"/>',
			path:'<c:if test="${not empty picTemp.picPath}"><%=basePath%><c:out value="${picTemp.picPath}"/></c:if>'
		}
		</c:forEach>
		],
		picSmall:[
		<c:forEach items="${temp.tbCommoditySmallPics}" var="picTemp" varStatus="picStatus">
		<c:if test="${picStatus.index>0}">,</c:if>
		{
			id:'<c:out value="${picTemp.picId}"/>',
			alt:'<my:escapeHtml value="${picTemp.picAlt}" removeTarget="false" changePath="false"/>',
			path:'<c:if test="${not empty picTemp.picPath}"><%=basePath%><c:out value="${picTemp.picPath}"/></c:if>'
		}
		</c:forEach>
		],
		param:[
		<c:forEach items="${temp.tbCommodityParams}" var="paramTemp" varStatus="paramStatus">
		<c:if test="${paramStatus.index>0}">,</c:if>
		{
			id:'<c:out value="${paramTemp.paramId}"/>',
			name:'<c:out value="${paramTemp.paramName}"/>',
			value:'<c:out value="${paramTemp.paramValue}"/>'
		}
		</c:forEach>
		],
		option:[
		<c:forEach items="${temp.tbCommodityPubDetails}" var="optionTemp" varStatus="optionStatus">
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
		intro:'<my:escapeHtml value="${temp.commIntro}" removeTarget="false" changePath="false"/>',
		ship:'<c:out value="${temp.isShipping}"/>',
		shipFee:'<fmt:formatNumber value="${temp.shippingFee}" pattern="0.##" type="number"/>',
		shipMemo:'<my:escapeHtml value="${temp.shippingMemo}" removeTarget="false" changePath="false"/>',
		donation:'<c:out value="${temp.isDonation}"/>',
		donationFee:'<fmt:formatNumber value="${temp.donationFee}" pattern="0.##" type="number"/>',
		donationMemo:'<c:out value="${temp.donationMemo}"/>',
		createTime:'<c:out value="${fn:substring(temp.creationTime,0,16)}"/>',
		stockCount:'<fmt:formatNumber value="${temp.stockCount}" pattern="0" type="number"/>',
		saledCount:'<fmt:formatNumber value="${temp.saledCount}" pattern="0" type="number"/>',
		saleFeeMin:'<fmt:formatNumber value="${temp.saleFeeMin}" pattern="0.##" type="number"/>',
		saleFeeMax:'<fmt:formatNumber value="${temp.saleFeeMax}" pattern="0.##" type="number"/>'
	}
	</c:forEach>
	]
}