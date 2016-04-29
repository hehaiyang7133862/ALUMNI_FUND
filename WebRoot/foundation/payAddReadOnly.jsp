<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<div class="page">
		<table class="lyt_view">
			<tr>
				<td class="lyt_view_note">项目名称</td>
				<td colspan="3">
					<c:out value="${foundBean.foundName}" />
				</td>
			</tr>
			<tr>
				<td class="lyt_view_note">基金用途</td>
				<td colspan="3">
					<my:fundField type="VIEW" lead="TYPE_FUND" value="${bean.tbFields.parentId}" />-&gt;<my:fundField type="VIEW" parentId="${bean.tbFields.parentId}" value="${bean.foundType}" />
				</td>
			</tr>
			<tr>
				<td class="lyt_view_note" style="width: 80px;">支出金额</td>
				<td style="width: 200px;">
					<c:out value="${bean.payAmount}" />&nbsp;元</td>
				<td class="lyt_view_note" style="width: 60px;">支出日期</td>
				<td style="width: 180px;">
					<c:out value="${bean.payDate}" />
				</td>
			</tr>
			<tr>
				<td class="lyt_view_note">可支出金额</td>
				<td colspan="3"> 
					<span id="canPayAmount" style="font-family: Constantia,Georgia;font-size: 22px;font-style: italic;font-weight: 700;">
						<fmt:formatNumber value="${realAllAmount-payAllAmount-bean.payAmount}" /> 
					</span>&nbsp;元
				</td>
			</tr>
			<tr>
				<td class="lyt_view_note">支出账户</td>
				<td colspan="3">
					<c:forEach items="${accList}" var="temp" varStatus="status">
						<c:if test="${temp.accountId==bean.tbDonationAccout.accountId}">
							<c:out value="${temp.accountName}" />:<c:out value="${temp.accountNum}" />
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_note">接受单位</td>
				<td colspan="3">
					<c:forEach items="${acceptList}" var="temp" varStatus="status">
						<c:if test="${temp.comId==bean.jsdwObject}">
							<c:out value="${temp.comName}" />-<c:out value="${temp.relationName}" />-<c:out value="${temp.accountNum}" />
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_area">支出说明</td>
				<td colspan="3">
					<span style="width:450px; display:block"><c:out value='${bean.payMemo}' /></span>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_area">附件</td>
				<td colspan="3">
					<iframe class="frm_file" marginheight="0" frameborder="0" src="<%=basePath %>fileListReadOnly.action?name=TbProPay&id=${bean.payId}">
					</iframe>
				</td>
			</tr>
		</table>
		<my:timeView idField="payId" table="TbProPay" value="${bean.payId}"></my:timeView>
	</div>
</body>
</html>