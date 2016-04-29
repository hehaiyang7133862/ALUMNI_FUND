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
		<%@ include file="../common/navigation.jsp"%>
		<%@ include file="foundationMenuReadOnly.jsp"%>
		<div>
			<table class="lyt_view">
				<tr>
					<td class="lyt_view_note" style="width:80px;">项目名称 </td>
					<td colspan="3">
						<c:out value="${bean.foundName}" />
					</td>
					<td class="lyt_view_note" style="width: 80px;">创建时间</td>
					<td style="width:160px;">
						<c:out value='${bean.foundCreateDate}'/>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px;">项目编号</td>
					<td style="width: 160px;">
						<c:out value="${bean.foundCode}" />
					</td>
					<td class="lyt_view_note" style="width: 80px;">项目类别</td>
					<td>
						<c:choose>
							<c:when test="${bean.foundtypeFid=='462'}">单项</c:when>
							<c:when test="${bean.foundtypeFid=='463'}">专项 </c:when>
							<c:when test="${bean.foundtypeFid=='641'}">年度 </c:when>
							<c:otherwise>其他</c:otherwise>
						</c:choose>
					</td>
					<td class="lyt_view_note" style="width: 80px;">项目级别</td>
					<td>
					<c:choose>
						<c:when test="${bean.foundLevel=='622'}">校级 </c:when>
						<c:when test="${bean.foundLevel=='623'}">院级 </c:when>
						<c:otherwise>其他</c:otherwise>
					</c:choose>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px;">所属分类</td>
					<td colspan="3">
						<c:forEach items="${typeList}" var="temp" varStatus="status">
							<c:if test="${temp.typeId==bean.foundType}">
								<c:out value="${temp.typeName}" />
							</c:if>
						</c:forEach>
					</td>
					<td class="lyt_view_note" style="width: 80px;">是否留本</td>
					<td style="width: 160px;">
						<c:choose>
							<c:when test="${bean.isKeep=='1'}">是</c:when>
							<c:otherwise>否</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px;">受惠对象</td>
					<td colspan="3">
						<c:choose>
							<c:when test="${bean.foundObject=='482'}">学院</c:when>
							<c:when test="${bean.foundObject=='483'}">学校</c:when>
							<c:when test="${bean.foundObject=='484'}">学生</c:when>
							<c:when test="${bean.foundObject=='485'}">教师</c:when>
							<c:otherwise>其他</c:otherwise>
						</c:choose>
					</td>
					<td class="lyt_view_note" style="width: 80px;">项目状态</td>
					<td>
						<c:choose>
							<c:when test="${bean.foundStatus=='663'}">筹备中</c:when>
							<c:when test="${bean.foundStatus=='664'}">进行中</c:when>
							<c:when test="${bean.foundStatus=='665'}">休眠</c:when>
							<c:when test="${bean.foundStatus=='666'}">结束</c:when>
							<c:otherwise>其他</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area" style="width:80px;">项目摘要</td>
					<td colspan="5">
						<span style="width:670px; display:block"><c:out value='${bean.foundDis}' /></span>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area" style="width: 80px;">项目说明</td>
					<td colspan="5">
						<iframe style="width:670px;height:300px" src="getFoundationMemo.action?foundationId=${bean.foundId}"></iframe>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area" style="width:80px;">附件</td>
					<td colspan="5">
						<iframe class="frm_file" marginheight="0" frameborder="0"
						src="<%=basePath %>fileListReadOnly.action?name=TbFoundation&id=${foundId}">
					</iframe>
					</td>
				</tr>
			</table>
		</div>
		<my:timeView idField="foundId" table="TbFoundation" value="${bean.foundId}"></my:timeView>
	</div>
</body>
</html>