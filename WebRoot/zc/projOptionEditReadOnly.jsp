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
	<div class="float">
		<table class="lyt_view">
			<tr>
				<td class="lyt_view_note">选项名称 </td>
				<td colspan="3">
					<c:out value="${bean.optionName}" />
				</td>
				<td class="lyt_view_note">选项编码</td>
				<td>
					<c:out value="${bean.optionCode}" />
				</td>
			</tr>
			<tr>
				<td class="lyt_view_note" style="width:80px;">选项金额 </span>
				</td>
				<td width="80">
					<fmt:formatNumber value='${bean.optionAmount}' pattern='0.##' type='number'/>&nbsp; 元
				</td>
				<td class="lyt_view_note" style="width:60px;">计量单位 </span>
				</td>
				<td width="60">
					<c:out value="${bean.unitName}" default="份"/></td>
				<td class="lyt_view_note" style="width:60px;">选项排序</td>
				<td width="80">
					<c:out value="${bean.optionOrder}" />
				</td>
			</tr>
			<tr>
				<td class="lyt_view_note">限制数量 </td>
				<td>
					<c:choose>
						<c:when test="${bean.limitCount=='1'}">&nbsp;是</c:when>
						<c:otherwise>&nbsp;否</c:otherwise>
					</c:choose>
				</td>
				<td class="lyt_view_note">剩余数量</td>
				<td>
					<fmt:formatNumber value='${bean.optionCount}' pattern='0' type='number'/>
				</td>
				<td class="lyt_view_note">对外显示 </td>
				<td>
					<c:choose>
						<c:when test="${bean.optionPublish=='1'}">&nbsp;是</c:when>
						<c:otherwise>&nbsp;否</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_area">选项说明</td>
				<td colspan="5">
					<span style="width:405px; display:block"><c:out value='${bean.optionMemo}' /></span>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_area">备注说明</td>
				<td colspan="5">
					<span style="width:405px; display:block"><c:out value='${bean.memo}' /></span>
				</td>
			</tr>
		</table>
		<my:timeView idField="optionId" table="TbZcprojOption" value="${bean.optionId}"></my:timeView>
	</div>
</body>
</html>