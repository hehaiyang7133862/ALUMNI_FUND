<%@ include file="../common/include.jsp"%>
<%@ page language="java"
	import="java.util.*,com.laungee.proj.common.util.*"
	pageEncoding="UTF-8"%>
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
	<div class="float">
			<table border="0" cellspacing="0" cellpadding="0" class="lyt_view">
				<tr>
					<td class="lyt_view_note" style="width:80px;">型号名称</td>
					<td colspan="3" width="400">
						<c:out value='${bean.detailName}'/>
					</td>
				</tr>
				<c:if test="${not empty bean.detailPic}">
					<tr>
						<td class="lyt_view_note">型号图片</td>
						<td><img width="100px" height="100px" src="<%=basePath%>${bean.detailPic}" alt="当前型号图片" /></td>
					</tr>
				</c:if>
				<tr>
					<td class="lyt_view_note">型号简介</td>
					<td colspan="3">
						<span style="width:400px; display:block"><c:out value='${bean.detailIntro}' /></span>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">是否限量 </td>
					<td colspan="3">
						<c:choose>
							<c:when test="${bean.limitNum=='1'}">是（库存：<c:out value='${bean.stockNum}' />） </c:when>
							<c:otherwise>否</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width:80px">成本价 </td>
					<td style="width:150px">
						<fmt:formatNumber value='${bean.costFee}' pattern='0.##' type='number'/>&nbsp;元
					</td>
					<td class="lyt_view_note" style="width:45px">售价 </td>
					<td style="width:150px">
						<fmt:formatNumber value='${bean.saleFee}' pattern='0.##' type='number'/>&nbsp;元
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">排序值</td>
					<td>
						<c:out value='${bean.numOrder}'/>
					</td>
					<td class="lyt_view_note">编码</td>
					<td>
						<c:out value='${bean.detailCode}'/>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">是否上架 </td>
					<td>
						<c:choose>
							<c:when test="${bean.isShelves=='1'}">是 </c:when>
							<c:otherwise>否</c:otherwise>
						</c:choose>
					</td>
					<td class="lyt_view_note">单位</td>
					<td>
						<c:out value='${bean.unitName}'/>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">备注</td>
					<td colspan="3">
						<span style="width:400px; display:block"><c:out value='${bean.memo}' /></span>
					</tr>
			</table>
			<my:timeView idField="detailId" table="TbCommodityDetail" value="${bean.detailId}"></my:timeView>
	</div>
</body>
</html>
