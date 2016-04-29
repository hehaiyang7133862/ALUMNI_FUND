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
		<c:if test="${not empty bean.proId}">
			<%@ include file="proMenuReadOnly.jsp"%>
		</c:if>
		<table class="lyt_view">
			<tr>
				<td class="lyt_view_note">所属项目 </td>
				<td colspan="5">
					<c:out value="${beanFound.foundName}" />
				</td>
			</tr>
			<tr>
				<td class="lyt_view_note">协议编号</td>
				<td colspan="3">
					<c:out value="${bean.proCode}" />
				</td>
				<td class="lyt_view_note">协议状态</td>
				<td>
					<c:choose>
						<c:when test="${bean.proStatus=='502'}">有效</c:when>
						<c:when test="${bean.proStatus=='503'}">中止</c:when>
						<c:when test="${bean.proStatus=='504'}">中断</c:when>
						<c:when test="${bean.proStatus=='505'}">续签</c:when>
						<c:otherwise>其他</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_note">协议名称</td>
				<td colspan="3">
					<c:out value="${bean.proName}" />
				</td>
				<td class="lyt_view_note">捐赠方性质</td>
				<td>
					<c:choose>
						<c:when test="${bean.jzfxz=='1'}">单位</c:when>
						<c:when test="${bean.jzfxz=='2'}">个人</c:when>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_note">捐赠方</td>
				<td colspan="3">
					<c:out value="${jzfObject}"/>
				</td>
				<td class="lyt_view_note">捐赠类型</td>
				<td>
					<c:choose>
						<c:when test="${bean.jzType=='402'}">现金</c:when>
						<c:when test="${bean.jzType=='403'}">动产</c:when>
						<c:when test="${bean.jzType=='521'}">不动产</c:when>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_note">受赠方</td>
				<td colspan="3">
					<c:choose>
						<c:when test="${not empty proid}">
							<c:out value="${bean.szfObject}" />
						</c:when>
						<c:otherwise>
							<my:titleTag/>
						</c:otherwise>
					</c:choose></td>
				<td class="lyt_view_note">捐赠来源</td>
				<td>
					<c:choose>
						<c:when test="${bean.jzSource=='487'}">国内</c:when>
						<c:when test="${bean.jzSource=='488'}">港澳台</c:when>
						<c:when test="${bean.jzSource=='489'}">外资</c:when>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_area">使用方</td>
				<td colspan="5">
					<div style="width: 690px;border: 1px solid #BDC7D8;background-color:#E5E5E5;height: 50px;line-height: 20px;float: left;overflow:auto;">
						<c:forEach var="temp" items="${syfList}">
							<a href="javascript:;" onclick="MyFormWin.showMyWin('接受单位详细','<%=basePath%>donate!toAcceptAddReadOnly.action?comId=${temp.comId}',580,380);return false;"
								style="padding-left: 5px;">${temp.comName}-${temp.relationName}</a>
						</c:forEach>
					</div>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_note" style="width: 80px;">协议金额</td>
				<td>
					<fmt:formatNumber value='${bean.proAmount}' pattern='0.##' type='number' />&nbsp;元
				</td>
				<td class="lyt_view_note" style="width: 60px;">签约日期</td>
				<td>
					<c:out value="${bean.dealDate}" />
				</td>
				<td class="lyt_view_note" style="width: 80px;">执行日期</td>
				<td style="width: 245px;">
					<c:out value="${bean.startTime}" /> 至 <c:out value="${bean.endTime}" />
				</td>
			</tr>
			<tr>
				<td class="lyt_view_area">协议主要内容</td>
				<td colspan="5">
					<iframe style="width:690px;height:260px" src="getProtocolContent.action?proId=${bean.proId}"></iframe>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_area">附件</td>
				<td colspan="5">
					<iframe class="frm_file" marginheight="0" frameborder="0"
						src="<%=basePath %>fileListReadOnly.action?name=TbProtocol&ele=updateId&id=${bean.proId}">
					</iframe>
				</td>
			</tr>
		</table>
		<my:timeView idField="proId" table="TbProtocol" value="${bean.proId}"></my:timeView>
	</div>
</body>
</html>