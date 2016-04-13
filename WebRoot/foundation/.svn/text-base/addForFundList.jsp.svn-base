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
	function doSubmit(){
		 onclick="parent.location='<%=basePath%>found!toProPage.action?foundId=${temp.foundId}&sub=all'"
	}
	function funback(foundId,foundName){
	    window.parent.setFound(foundId,foundName);
		window.parent.parent.MyFormWin.restore();
		window.parent.MyFormWin.close();
	}
	</script>
</head>
	<body>
		<div class="page">
			<%@ include file="../common/navigation.jsp"%>
			<form id="adviseForm" action="<%=basePath%>found!chooseFound.action" method="post" onsubmit="return doSubmit()">
				<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0" class="lyt_search">
					<tr>
						<td class="lyt_search_note">
							项目编号
						</td>
						<td width="125">
							<input style="width: 120px;" type="text" name="foundCode" value="<c:out value='${foundCode}' />" maxlength="100" />
						</td>
						<td class="lyt_search_note">
							项目名称
						</td>
						<td width="125">
							<input style="width: 120px;" type="text" name="foundName" value="<c:out value='${foundName}' />" maxlength="100" />
						</td>
						<td align="right" class="lyt_search_opt">
							<div class="lyt_button">
								<input id="saveForm" name="submit" value="查询" type="submit" />
							</div>
						</td>
					</tr>
				</table>
			</form>
			<table width="100%" border="0" cellspacing="0" class="lyt_result">
				<tr>
					<th width="60">序号</th>
					<th>项目编号</th>
					<th>项目名称</th>
					<th>项目级别</th>
					<th>项目类别</th>
					<th width="60">是否留本</th>
					<th width="80">操作</th>
				</tr>
				<c:forEach items="${foundList}" var="temp" varStatus="status">
					<tr>
						<td>
							<b><c:out value="${(p.curr-1)*p.size+status.count}" /></b>
						</td>
						<td>
							<c:out value="${temp.foundCode}"></c:out>
						</td>
						<td>
							<c:if test="${flag=='pro'}">
							<a href="javascript:;" onclick="parent.location='<%=basePath%>found!toProPage.action?foundId=${temp.foundId}&sub=all'"><c:out value="${temp.foundName}"></c:out></a>
							</c:if>
							<c:if test="${empty flag}">
							<a href="javascript:;" onclick="location='<%=basePath%>found!toProPayPage.action?foundId=${temp.foundId}'"><c:out value="${temp.foundName}"></c:out></a>
							</c:if>
						</td>
						<td>
							<my:fundField type="VIEW" lead="FOUNDATION_TYPE" value="${temp.foundLevel}"></my:fundField>
						</td>
						<td>
							<c:if test="${temp.foundtypeFid=='1'}">单项</c:if>
							<c:if test="${temp.foundtypeFid=='2'}">专项</c:if>
						</td>
						<td>
							<c:if test="${temp.isKeep=='1'}">是</c:if>
							<c:if test="${temp.isKeep=='2'}">否</c:if>
						</td>
						<td>
							<c:if test="${flag=='pro'}">
							<a href="javascript:;" onclick="funback('${temp.foundId}','${temp.foundName}');">选择</a>
							</c:if>
							<c:if test="${empty flag}">
							<a href="javascript:;" onclick="location='<%=basePath%>found!toProPayPage.action?foundId=${temp.foundId}'">选择</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
			<my:page action="found!chooseFound.action"></my:page>
		</div>
	</body>
</html>