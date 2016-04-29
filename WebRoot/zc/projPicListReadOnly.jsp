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
		<%@ include file="../common/calendar.jsp"%>
		<%@ include file="../common/kindeditor.jsp"%>
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
			<%@ include file="projMenuReadOnly.jsp"%>
			</c:if>
			<form id="formId" action="<%=basePath%>zcProjEditReadOnly!picList.action" method="get" onsubmit="return doSubmit()">
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
						<td width="170">提示：项目图片不少于1个！</td>
						<td align="right" class="lyt_search_opt">
							<div class="lyt_button">
								<input value="刷新" type="submit" style="margin-right:20px"/>
							</div>
						</td>
					</tr>
				</table>
			</form>
			<table border="1" cellspacing="0" cellpadding="0" class="lyt_result">
				<tr>
					<th width="40">序号</th>
					<th width="200">图片</th>
					<th width="100">参数值</th>
					<th>描述信息</th>
					<th>备注信息</th>
					<th width="60">操作</th>
				</tr>
				<c:forEach items="${picList}" var="temp" varStatus="status">
				<tr>
					<td>
						<b><c:out value="${status.count}" /></b>
					</td>
					<td style="height:100px;">
						<div style="width:200px;overflow:hidden;">
							<img alt="" src="<%=basePath%>${temp.picPath}" height="100"/>
						</div>
					</td>
					<td>
						<b><c:out value="${temp.numOrder}" /></b>
						<br/><br/>
						<c:choose><c:when test="${temp.picPublish=='1'}"><b>对外显示</b></c:when><c:otherwise>不对外显示</c:otherwise></c:choose>
					</td>
					<td><c:out value="${temp.picAlt}" /></td>
					<td><c:out value="${temp.memo}" /></td>
					<td>
						<a href="javascript:;" onclick="MyFormWin.showMyWin('图片详情','<%=basePath%>zcProjEditReadOnly!picEdit.action?projId=${bean.projId}&picId=${temp.picId}',580,310);return false;">详情</a>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</html>