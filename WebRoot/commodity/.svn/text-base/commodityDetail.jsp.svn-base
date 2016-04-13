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
		formFormat("formId");
		return true;
	}
</script>
</head>
<body>
<%@ include file="../common/navigation.jsp"%>
<%@ include file="commodityHead.jsp"%>
	<form id="formId" action="<%=basePath%>commodity!info.action" method="get" onsubmit="return doSubmit()">
		<input name="commId" type="hidden" value="${bean.commId}" />
		<input name="num" type="hidden" value="${num}" />
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="lyt_search">
			<tr>
				<td class="lyt_search_note" style="width:60px;">型号名称</td>
				<td width="150">
					<input type="text" style="width:150px;" name="detailName" value="<c:out value='${detailName}' />" maxlength="20" />
				</td>
				<td class="lyt_search_note" style="width:60px;">库存</td>
				<td width="60">
					<select name="stockNum" style="width:60px;">
						<option value="">全部</option>
						<option value="1" <c:if test="${stockNum==1}">selected="selected"</c:if>>有货</option>
						<option value="0" <c:if test="${stockNum==0}">selected="selected"</c:if>>缺货</option>
					</select>
				</td>
				<td class="lyt_search_note" style="width:60px;">上架</td>
				<td width="60">
					<select name="isShelves" style="width:60px;">
						<option value="">全部</option>
						<option value="1" <c:if test="${isShelves==1}">selected="selected"</c:if>>是</option>
						<option value="0" <c:if test="${isShelves==0}">selected="selected"</c:if>>否</option>
					</select>
				</td>
				<td align="right" class="lyt_search_opt">
					<div class="lyt_button">
						<input type="submit" value="查询" /> 
			 			<input type="button" onclick="MyFormWin.showMyWin('新增型号','<%=basePath%>commodity!operationDetail.action?commId=${bean.commId}',580,400);return false;" value="新增" />
					</div>
				</td>
		</tr>
		</table>
	</form>
	<table width="100%" border="0" cellspacing="0" class="lyt_result">
		<tr>
			<th width="40">序号</th>
			<th>型号名称</th>
			<th width="80">上架</th>
			<th width="100">库存</th>
			<th width="100">成本价（元）</th>
			<th width="100">售价（元）</th>
			<th width="60">操作</th>
		</tr>
		<c:forEach items="${beanList }" var="temp" varStatus="status">
		<tr>
			<td>
				<b><c:out value="${(p.curr-1)*p.size+status.count}" /></b>
			</td>
			<td>
				<a onclick="MyFormWin.showMyWin('修改型号','<%=basePath%>commodity!operationDetail.action?detailId=${temp.detailId}&commId=${bean.commId}',580,450);return false;" href="javascript:;"><c:out value="${temp.detailName }" /></a>
			</td>
			<td>
				<c:choose>
					<c:when test="${temp.isShelves=='1'}">是</c:when>
					<c:otherwise>否</c:otherwise>
				</c:choose>
			</td>
			<td><fmt:formatNumber value='${temp.stockNum}' pattern='0' type='number'/></td>
			<td><fmt:formatNumber value='${temp.costFee}' pattern='0.##' type='number'/></td>
			<td><fmt:formatNumber value='${temp.saleFee}' pattern='0.##' type='number'/></td>
			<td>
				<a onclick="MyFormWin.showMyWin('修改型号','<%=basePath%>commodity!operationDetail.action?detailId=${temp.detailId}&commId=${bean.commId}',580,450);return false;" href="javascript:;">编辑</a>
				<a onclick="MyMsg.confirm('您确定要删除吗？','<%=basePath%>commodity!deleteDetail.action?detailId=${temp.detailId}&${fn:replace(p.pars,'detailId','radom')}');" href="javascript:;">删除</a>
			</td>
		</tr>
		</c:forEach>
	</table>
	<my:page action="commodity!info.action"></my:page>	
</body>
</html>