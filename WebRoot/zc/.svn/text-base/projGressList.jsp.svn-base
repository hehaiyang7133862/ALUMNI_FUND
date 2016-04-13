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
		<style>
</style>
		<%@ include file="../common/head.jsp"%>
		<script type="text/javascript">
		function doSubmit(){
			formFormat("formId");
			return true;
		}
		</script>
	</head>
	<body>
		<div class="page">
			<%@ include file="../common/navigation.jsp"%>
			<c:if test="${not empty bean.projId}">
			<%@ include file="projMenu.jsp"%>
			</c:if>
			<form id="formId" action="<%=basePath%>zcProjEdit!gressList.action" method="get" onsubmit="return doSubmit()">
				<input name="projId" value="${bean.projId}" type="hidden" />
				<input name="num" value="${num}" type="hidden" />
				<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0" class="lyt_search">
					<tr>
						<td align="right" class="lyt_search_opt">
							<div class="lyt_button">
								<input value="刷新" type="submit" />
								<input value="新增" type="button" onclick="MyFormWin.showMyWin('新增进展','<%=basePath%>zcProjEdit!gressEdit.action?projId=${bean.projId}',800,400);return false;" />
							</div>
						</td>
					</tr>
				</table>
			</form>
			<table border="1" cellspacing="0" cellpadding="0" class="lyt_result">
				<tr>
					<th width="40">序号</th>
					<th width="100">进展时间</th>
					<th>进展详情</th>
					<th>备注信息</th>
					<th width="60">操作</th>
				</tr>
				<c:forEach items="${gressList}" var="temp" varStatus="status">
				<tr>
					<td>
						<b><c:out value="${(p.curr-1)*p.size+status.count}" /></b>
					</td>
					<td>
						<fmt:formatDate pattern='yyyy-MM-dd' value='${temp.gressTime}'/>
					</td>
					<td>
						${temp.gressContent}
					</td>
					<td>
						<c:out value="${temp.memo}" />
					</td>
					<td>
						<a href="javascript:;" onclick="MyFormWin.showMyWin('编辑进展','<%=basePath%>zcProjEdit!gressEdit.action?projId=${bean.projId}&gressId=${temp.gressId}',800,400);return false;">编辑</a>
						<a href="javascript:;" onclick="MyMsg.confirm('确定要删除该进展？','<%=basePath%>zcProjEdit!gressDel.action?gressId=${temp.gressId}&${fn:replace(p.pars,'gressId','radom')}');">删除</a>
					</td>
				</tr>
				</c:forEach>
			</table>
			<my:page action="zcProjEdit!gressList.action"></my:page>
		</div>
	</body>
</html>