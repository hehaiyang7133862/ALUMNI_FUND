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
	}
	function myRefresh(){
		location='<%=basePath%>donate!companyList.action';
	}
	
	$(document).ready(function(){
    	<c:if test="${not empty alert}">
    		//刷新
    		setTimeout(myRefresh(),200);
    	</c:if>
    });
	</script>
</head>
	<body>
		<div class="page">
		<%@ include file="../common/navigation.jsp"%>
			<form id="adviseForm" action="<%=basePath%>donate!companyList.action" method="post" onsubmit="return doSubmit()">
				<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0" class="lyt_search">
					<tr>
						<td class="lyt_search_note" width="80px">
							单位名称
						</td>
						<td width="211">
							<input style="width: 204px;" type="text" name="comName" value="<c:out value='${comName}' />" maxlength="100" />
						</td>
						<td class="lyt_search_note">
							主要业务活动
						</td>
						<td width="250">
							<input style="width: 204px;" type="text" name="comBusi" value="<c:out value='${comBusi}' />" maxlength="100" />
						</td>
						<td class="lyt_search_note">
							登记注册类型
						</td>
						<td>
							<select name="comType" style="width: 204px;">
								<option value="">全部</option>
								<my:fundField type="OPTION" lead="DONATION_BUSI" value="${comType}"></my:fundField>
							</select>
						</td>
					</tr>
					<tr>
						<td class="lyt_search_note">
							所属行业
						</td>
						<td>
							<select name="industryFid" style="width: 204px;">
								<option value="">全部</option>
								<my:field type="OPTION" lead="INDUSTRY" value="${industryFid}"></my:field>
							</select>
						</td>
						<td class="lyt_search_note">
							详细地址
						</td>
						<td>
							<input style="width: 204px;" type="text" name="comAddress" value="<c:out value='${comAddress}' />" maxlength="100" />
						</td>
						<td class="lyt_search_note">
							单位负责人
						</td>
						<td width="211">
							<input style="width: 204px;" type="text" name="responseName" value="<c:out value='${responseName}' />" maxlength="100" />
						</td>
					</tr>
					<tr>
						<td align="right" class="lyt_search_opt" colspan="6">
							<div class="lyt_button">
								<input id="saveForm" name="submit" value="查询" type="submit" />
								<input name="" value="新增" type="button" onClick="location.href='<%=basePath%>donate!toCompanyModifyPage.action';return false;" />
							</div>
						</td>
					</tr>
				</table>
			</form>
			<table width="100%" border="0" cellspacing="0" class="lyt_result">
				<tr>
					<th width="60">序号</th>
					<th width="80">操作</th>
					<th>单位名称</th>
					<th>主要业务活动</th>
					<th width="60">登记注册类型</th>
					<th width="60">所属行业</th>
					<th>详细地址</th>
					<th width="80">单位负责人</th>
					<th width="80">操作</th>
				</tr>
				<c:forEach items="${companyList}" var="temp" varStatus="status">
					<tr>
						<td>
							<b><c:out value="${(p.curr-1)*p.size+status.count}" /></b>
						</td>
						<td>
							<a href="javascript:;" onClick="location='<%=basePath%>donate!toCompanyModifyPage.action?comId=${temp.comId}';return false;">编辑</a>
							<a href="javascript:;" onclick="if(confirm('是否确定删除')){location='<%=basePath%>donate!delCompany.action?comId=${temp.comId}'}">删除</a>
						</td>
						<td>
							<a href="javascript:;" onClick="location='<%=basePath%>donate!toCompanyModifyPage.action?comId=${temp.comId}';return false;"><c:out value="${temp.comName}"></c:out></a>
						</td>
						<td>
							<c:out value="${temp.comBusi}"></c:out>
						</td>
						<td>
							<my:fundField type="VIEW" lead="DONATION_BUSI" value="${temp.comType}"></my:fundField>
						</td>
						<td>
							<my:fieldView value="${temp.industryFid}"></my:fieldView>
						</td>
						<td>
							<c:out value="${temp.comAddress}"></c:out>
						</td>
						<td>
							<c:out value="${temp.responseName}"></c:out>
						</td>
						<td>
							<a href="javascript:;" onClick="location='<%=basePath%>donate!toCompanyModifyPage.action?comId=${temp.comId}';return false;">编辑</a>
							<a href="javascript:;" onclick="if(confirm('是否确定删除')){location='<%=basePath%>donate!delCompany.action?comId=${temp.comId}'}">删除</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<c:if test="${not empty p}">
			<my:page action="donate!companyList.action"></my:page>
			</c:if>
		</div>
	</body>
</html>