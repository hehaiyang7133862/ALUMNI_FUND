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
		<table class="lyt_view">
			<c:if test="${not empty bean.picId}">
				<tr>
					<td class="lyt_view_area">当前图片</td>
					<td colspan="3">
						<div style="width:400px;overflow:hidden;">
							<img alt="" src="<%=basePath%>${bean.picPath}" height="100" />
						</div>
					</td>
				</tr>
			</c:if>
			<tr>
				<td class="lyt_view_note" style="width:80px;">图片排序</td>
				<td width="80">
					<c:out value="${bean.numOrder}" />
				</td>
				<td class="lyt_view_note" style="width:50px;">是否对外显示</td>
				<td width="80">
					<c:choose>
						<c:when test="${bean.picPublish=='1'}">是 </c:when>
						<c:otherwise>否</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_area">图片说明</td>
				<td colspan="3">
					<span style="width:400px; display:block"><c:out value='${bean.picAlt}' /></span>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_area">备注说明</td>
				<td colspan="3">
					<span style="width:400px; display:block"><c:out value='${bean.memo}' /></span>
				</td>
			</tr>
		</table>
		<my:timeView idField="picId" table="TbCommodityPic" value="${bean.picId}"></my:timeView>
	</div>
</body>
</html>
