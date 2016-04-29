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
				<td class="lyt_view_note" style="width:80px;">参数名称 </td>
				<td style="width:150px">
					<c:out value='${bean.paramName}'/>
				</td>
				<td class="lyt_view_note" style="width:60px">参数值</td>
				<td>
					<c:out value='${bean.paramValue}'/>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_note">是否对外显示</td>
				<td>
					<c:choose>
						<c:when test="${bean.paramPublish=='1'}">是 </c:when>
						<c:otherwise>否</c:otherwise>
					</c:choose>
				</td>
				<td class="lyt_view_note">参数排序</td>
				<td>
					<c:out value='${bean.numOrder}'/>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_area">备注</td>
				<td colspan="3">
					<span style="width:400px; display:block"><c:out value='${bean.memo}' /></span>
				</td>
			</tr>
		</table>
		<my:timeView idField="paramId" table="TbCommodityParam" value="${bean.paramId}"></my:timeView>
	</div>
</body>
</html>
