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
	proj:'<c:out value="${proj}"/>',
	alumniUn:'<c:out value="${alumniUn}"/>',
	orderStatus:'<c:out value="${orderStatus}"/>',
	order:'<c:out value="${order}"/>',
	orderType:'<c:out value="${orderType}"/>',
	list:[
	<c:forEach items="${beanList}" var="temp" varStatus="status">
	<c:if test="${status.index>0}">,</c:if>
	{
		id:'<c:out value="${temp.orderId}"/>',
		proj:'<c:out value="${temp.projId}"/>',
		projName:'<c:out value="${temp.projName}"/>',
		projPic:'<c:if test="${not empty temp.projPic}"><%=basePath%><c:out value="${temp.projPic}"/></c:if>',
		option:'<c:out value="${temp.optionId}"/>',
		optionName:'<c:out value="${temp.optionName}"/>',
		optionCount:'<fmt:formatNumber value="${temp.optionCount}" pattern="0" type="number"/>',
		orderAmountType:'<c:out value="${temp.orderAmountType}"/>',
		orderAmountView:'<fmt:formatNumber value="${temp.orderAmountView}" pattern="0.##" type="number"/>',
		orderAmountUnit:'<c:out value="${temp.orderAmountUnit}"/>',
		orderAmount:'<fmt:formatNumber value="${temp.orderAmount}" pattern="0.##" type="number"/>',
		orderNo:'<c:out value="${temp.orderNum}"/>',
		orderType:'<c:out value="${temp.orderType}"/>',
		orderStatus:'<c:out value="${temp.orderStatus}"/>',
		orderTime:'<fmt:formatDate value="${temp.orderTime}" pattern="yyyy-MM-dd HH:mm"/>',
		orderOkTime:'<fmt:formatDate value="${temp.orderOkTime}" pattern="yyyy-MM-dd HH:mm"/>',
		suportIndex:'<c:out value="${temp.suportIndex}"/>',
		needZhengshu:'<c:out value="${temp.needZhengshu}"/>',
		needFapiao:'<c:out value="${temp.needFapiao}"/>',
		personType:'<c:out value="${temp.personType}"/>',
		personCount:'<c:out value="${temp.personCount}" default="1"/>',
		personName:'<c:out value="${temp.personName}"/>',
		niMing:'<c:out value="${temp.niMing}" default="0"/>',
		personIp:'<c:out value="${temp.personIp}"/>',
		personSex:'<c:out value="${temp.personSex}"/>',
		personEmail:'<c:out value="${temp.personEmail}"/>',
		personPhone:'<c:out value="${temp.personPhone}"/>',
		personTel:'<c:out value="${temp.personTel}"/>',
		addressZip:'<c:out value="${temp.addressZip}"/>',
		addressDetail:'<c:if test="${not empty temp.addressSheng}"><c:out value="${temp.addressSheng}"/>（省）</c:if><c:if test="${not empty temp.addressShi}"><c:out value="${temp.addressShi}"/>（市）</c:if><c:if test="${not empty temp.addressQu}"><c:out value="${temp.addressQu}"/>（区）</c:if><c:if test="${not empty temp.addressDetail}"><c:out value="${temp.addressDetail}"/></c:if>',
		alumniFlag:'<c:out value="${temp.alumniFlag}"/>',
		alumniUn:'<c:out value="${temp.unAlumniId}"/>',
		studyYearin:'<c:out value="${temp.studyYearin}"/>',
		studyYearover:'<c:out value="${temp.studyYearover}"/>',
		studyAcademy:'<c:out value="${temp.studyAcademy}"/>',
		studyMajor:'<c:out value="${temp.studyMajor}"/>',
		studyClass:'<c:out value="${temp.studyClass}"/>',
		studyNum:'<c:out value="${temp.studyNum}"/>',
		studyDegree:'<c:out value="${temp.studyDegree}"/>',
		workCompany:'<c:out value="${temp.workCompany}"/>',
		workDepart:'<c:out value="${temp.workDepart}"/>',
		workDuty:'<c:out value="${temp.workDuty}"/>',
		orderMemo:'<my:escapeHtml value="${temp.orderMemo}" removeTarget="false" changePath="false"/>',
		receipt:'<my:escapeHtml value="${temp.receipt}" removeTarget="false" changePath="true"/>'
	}
	</c:forEach>
	]
}