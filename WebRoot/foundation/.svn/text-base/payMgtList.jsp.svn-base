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
	<%@ include file="../common/calendar.jsp"%>
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
			<form id="formId" action="<%=basePath%>found!payMgt.action" method="get" onsubmit="return doSubmit()">
				<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0" class="lyt_search">
					<tr>
						<td class="lyt_search_note" width="80px">
							项目名称
						</td>
						<td width="211">
							<input style="width: 204px;" type="text" name="foundName" value="<c:out value='${foundName}' />" maxlength="100" />
						</td>
						<td class="lyt_search_note">
							基金类型
						</td>
						<td width="250">
							<select style="width: 120px;" name="parentType" id="parentType" onclick='ajaxDDLF("fieldAjax!fundExecute.action","parentType","foundType")'>
								<option value="">全部</option>
								<c:forEach var="temp" items="${fieldList}">
								<option value="${temp.fieldId}" <c:if test="${temp.fieldId==parentType}">selected</c:if>><c:out value="${temp.fieldName}"/></option>
								</c:forEach>
							</select>
							<select style="width: 120px;" name="foundType" id="foundType">
								<option value="">全部</option>
								<my:fundField type="OPTION" parentId="${parentType}" value="${foundType}" />
							</select>
						</td>
						<td class="lyt_search_note">
							支出账户
						</td>
						<td width="250">
							<select style="width: 245px;" name="payAccount" id="payAccount">
								<option value="">全部</option>
								<c:forEach var="temp" items="${accList}">
								<option value="${temp.accountId}" <c:if test="${temp.accountId==payAccount}">selected</c:if>><c:out value="${temp.accountName}"/><c:out value="${temp.accountNum}"/></option>
								</c:forEach>
							</select>
						</td>
						<td align="right" class="lyt_search_opt" colspan="4">
							<div class="lyt_button">
								<input id="saveForm" name="submit" value="查询" type="submit" />
								<input name="" value="新增" type="button" onClick="MyFormWin.showMyWin('请先选择基金项目','<%=basePath%>found!chooseFound.action',650,398);return false;" />
							</div>
						</td>
					</tr>
					<tr>
						<td class="lyt_search_note">
							支出金额
						</td>
						<td>
							<select name="paySign" style="width: 80px;">
								<option value=">=" <c:if test="${paySign=='>='}">selected</c:if>>大于等于</option>
								<option value=">" <c:if test="${paySign=='>'}">selected</c:if>>大于</option>
								<option value="=" <c:if test="${paySign=='='}">selected</c:if>>等于</option>
								<option value="&lt;" <c:if test="${paySign=='<'}">selected</c:if>>小于</option>
								<option value="&lt;=" <c:if test="${paySign=='<='}">selected</c:if>>小于等于</option>
							</select>
							<input style="width: 120px;" type="text" onkeyup="limit(this)" name="payAmount" value="<c:out value='${payAmount}' />" maxlength="100" />
						</td>
						<td class="lyt_search_note">
							支出日期
						</td>
						<td>
							<input style="width: 92px;" type="text" name="startDate" id="startDate" readonly="true" class="lyt_search_date" value='${startDate}' />
							<img onClick="WdatePicker({el:'startDate'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle" />
								到
							<input style="width: 92px;" type="text" name="endDate" id="endDate" readonly="true" class="lyt_search_date" value='${endDate}' />
							<img onClick="WdatePicker({el:'endDate'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle" />
						</td>
						<td class="lyt_search_note">
							接受单位
						</td>
						<td width="250">
							<select style="width: 245px;" name="jsdwObject" id="jsdwObject">
								<option value="">全部</option>
								<c:forEach var="temp" items="${acceptList}">
								<option value="${temp.comId}" <c:if test="${temp.comId==jsdwObject}">selected</c:if>><c:out value="${temp.comName}" /></option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
			</form>
			<table width="100%" border="0" cellspacing="0" class="lyt_result">
				<tr>
					<th width="60">序号</th>
					<th>项目名称</th>
					<th>基金用途</th>
					<th>
						<my:order action="found!payMgt.action" title="支出金额（元）" name="a.payAmount" />
					</th>
					<th>支出账户</th>
					<th>
						<my:order action="found!payMgt.action" title="支出日期" name="a.payDate" />
					</th>
					<th>接受单位</th>
					<th width="60">操作</th>
				</tr>
				<c:forEach items="${payList}" var="temp" varStatus="status">
					<tr>
						<td>
							<b><c:out value="${(p.curr-1)*p.size+status.count}" /></b>
						</td>
						<td>
							<c:out value="${temp.tbFoundation.foundName}"></c:out>
						</td>
						<td>
							<c:if test="${not empty temp.foundType}">
							<my:fundField type="VIEW" lead="TYPE_FUND" value="${temp.tbFields.parentId}" />-&gt;<my:fundField type="VIEW" parentId="${temp.tbFields.parentId}" value="${temp.foundType}" />
							</c:if>
						</td>
						<td>
							<fmt:formatNumber value="${temp.payAmount}" />
						</td>
						<td>
							<c:if test="${not empty temp.tbDonationAccout}">
							<c:out value="${temp.tbDonationAccout.accountName}"></c:out> ****<c:out value="${fn:substring(temp.tbDonationAccout.accountNum,fn:length(temp.tbDonationAccout.accountNum)-4,fn:length(temp.tbDonationAccout.accountNum))}"></c:out>
							</c:if>
						</td>
						<td>
							<c:out value="${temp.payDate}"></c:out>
						</td>
						<td>
							<c:out value="${temp.tbAcceptCompany.comName}" />
						</td>
						<td>
							<a href="javascript:;" onClick="MyFormWin.showMyWin('项目支出管理详细','<%=basePath%>found!toProPayPage.action?payId=${temp.payId}&foundId=${temp.foundId}',650,398);return false;">编辑</a>
							<a href="javascript:;" onclick="MyMsg.confirm('是否确定删除','<%=basePath%>found!delProPayMgt.action?payId=${temp.payId}&${fn:replace(p.pars,'payId','radom')}');">删除</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<my:page action="found!payMgt.action"></my:page>
		</div>
	</body>
</html>