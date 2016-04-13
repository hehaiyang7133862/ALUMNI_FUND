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
		function doQuery(){
			$('#formId').submit();
		}
		function myRefresh(){
			location.reload();
		}
		</script>
	</head>
	<body>
		<div class="page">
			<%@ include file="../common/navigation.jsp"%>
			<c:if test="${not empty bean.projId}">
			<%@ include file="projMenu.jsp"%>
			</c:if>
			<form id="formId" action="<%=basePath%>zcProjEdit!optionList.action" method="get" onsubmit="return doSubmit()">
				<input name="projId" value="${bean.projId}" type="hidden" />
				<input name="num" value="${num}" type="hidden" />
				<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0" class="lyt_search">
					<tr>
						<td width="110">
							<select name="publish" style="width:100px;" onchange="doQuery();">
								<option value="">全部</option>
								<option value="1" <c:if test="${publish=='1'}">selected</c:if>>对外显示</option>
								<option value="0" <c:if test="${publish=='0'}">selected</c:if>>不对外显示</option>
							</select>
						</td>
						<td align="right" class="lyt_search_opt">
							<div class="lyt_button">
								<input value="刷新" type="submit" />
								<input value="新增" type="button" onclick="MyFormWin.showMyWin('新增选项','<%=basePath%>zcProjEdit!optionEdit.action?projId=${bean.projId}',580,310);return false;" />
							</div>
						</td>
					</tr>
				</table>
			</form>
			<table border="1" cellspacing="0" cellpadding="0" class="lyt_result">
				<tr>
					<th width="40">序号</th>
					<th width="150">选项名称</th>
					<th width="100">选项编码</th>
					<th width="100">选项金额</th>
					<th width="80">计量单位</th>
					<th width="80">选项排序</th>
					<th width="80">对外显示</th>
					<th width="80">数量限制</th>
					<th width="80">剩余数量</th>
					<th>选项说明</th>
					<th>备注信息</th>
					<th width="60">操作</th>
				</tr>
				<c:forEach items="${optionList}" var="temp" varStatus="status">
				<tr>
					<td>
						<b><c:out value="${status.count}" /></b>
					</td>
					<td>
						<c:out value="${temp.optionName}" />
					</td>
					<td>
						<c:out value="${temp.optionCode}" />
					</td>
					<td>
						<fmt:formatNumber value='${temp.optionAmount}' pattern='0.##' type='number'/> 元
					</td>
					<td>
						<c:out value="${temp.unitName}" default="份"/>
					</td>
					<td>
						<c:out value="${temp.optionOrder}" />
					</td>
					<td>
						<c:choose><c:when test="${temp.optionPublish=='1'}"><b>对外显示</b></c:when><c:otherwise>不对外显示</c:otherwise></c:choose>
					</td>
					<td>
						<c:choose><c:when test="${temp.limitCount=='1'}"><b>是</b></c:when><c:otherwise>否</c:otherwise></c:choose>
					</td>
					<td>
						<c:if test="${temp.limitCount=='1'}"><fmt:formatNumber value='${temp.optionCount}' pattern='0' type='number'/></c:if>
					</td>
					<td>
						<c:out value="${temp.optionMemo}" />
					</td>
					<td>
						<c:out value="${temp.memo}" />
					</td>
					<td>
						<a href="javascript:;" onclick="MyFormWin.showMyWin('编辑选项','<%=basePath%>zcProjEdit!optionEdit.action?projId=${bean.projId}&optionId=${temp.optionId}',580,310);return false;">编辑</a>
						<a href="javascript:;" onclick="MyMsg.confirm('确定要删除该选项？','<%=basePath%>zcProjEdit!optionDel.action?projId=${bean.projId}&optionId=${temp.optionId}&num=${num}');">删除</a>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</html>