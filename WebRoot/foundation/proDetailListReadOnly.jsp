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
	<script type="text/javascript">
	function refresh(){
		location.reload();
	}
    </script>
	</head>
	<body>
		<div class="page">
			<%@ include file="../common/navigation.jsp"%>
			<c:if test="${not empty proId}">
			<%@ include file="proMenuReadOnly.jsp"%>
			</c:if>
			<form action="<%=basePath%>foundReadOnly!proDetailList.action" method="get">
				<input name="fundId" value="${fundId}" type="hidden" />
				<input name="proId" value="${proId}" type="hidden" />
				<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0" class="lyt_search">
					<tr>
						<td align="right" class="lyt_search_opt">
							<div class="lyt_button">
								<input value="刷新" type="submit" />
							</div>
						</td>
					</tr>
				</table>
				<table width="100%" border="0" cellspacing="0" class="lyt_result">
					<tr>
						<th width="40">序号</th>
						<th width="80">
							<my:order action="found!proDetailList.action" title="承诺日期" name="a.dealDate" />
						</th>
						<th>
							<my:order action="found!proDetailList.action" title="承诺金额（元）" name="a.dealAmount" />
						</th>
						<th>到账账户</th>
						<th width="70">
							<my:order action="found!proDetailList.action" title="到账日期" name="a.toDate" />
						</th>
						<th>
							<my:order action="found!proDetailList.action" title="到账金额（元）" name="a.toAmount" />
						</th>
						<th>指定用途</th>
						<th width="60">操作</th>
					</tr>
					<c:set var="dealTotal" value="0"/>
					<c:set var="toTotal" value="0"/>
					<c:forEach items="${proDetailList}" var="temp" varStatus="status">
						<tr>
							<td>
								<b><c:out value="${status.count}" /></b>
							</td>
							<td>
								<c:out value="${temp.dealDate}"></c:out>
							</td>
							<td>
								<fmt:formatNumber value="${temp.dealAmount}" />
								<c:set var="dealTotal" value="${dealTotal+temp.dealAmount}" />
							</td>
							<td>
								<c:if test="${not empty temp.tbDonationAccout}">
								<c:out value="${temp.tbDonationAccout.accountName}"></c:out> ****<c:out value="${fn:substring(temp.tbDonationAccout.accountNum,fn:length(temp.tbDonationAccout.accountNum)-4,fn:length(temp.tbDonationAccout.accountNum))}"></c:out>
								</c:if>
							</td>
							<td>
								<c:out value="${temp.toDate}"></c:out>
							</td>
							<td>
								<fmt:formatNumber value="${temp.toAmount}" />
								<c:set var="toTotal" value="${toTotal+temp.toAmount}" />
							</td>
							<td>
								<c:if test="${not empty temp.proUse}">
								<my:fundField type="VIEW" lead="TYPE_FUND" value="${temp.tbFields.parentId}" />-&gt;<my:fundField type="VIEW" parentId="${temp.tbFields.parentId}" value="${temp.proUse}" />
								</c:if>
							</td>
							<td>
								<a href="javascript:;" onclick="MyFormWin.showMyWin('捐赠明细','<%=basePath%>foundReadOnly!toProDetailPage.action?proId=${proId}&detailId=${temp.detailId}',550,280);return false;">详情</a>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${not empty proDetailList}">
						<td>合计</td>
						<td></td>
						<td><fmt:formatNumber value="${dealTotal}" /></td>
						<td></td>
						<td></td>
						<td><fmt:formatNumber value="${toTotal}" /></td>
						<td></td>
						<td></td>
					</c:if>
				</table>
			</form>
		</div>
	</body>
</html>