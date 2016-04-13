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
function goMark(id){
	MyFormWin.close();
	MyFormWin.showMyWin('订单标记','<%=basePath%>commodity!orderMark.action?orderId='+id,800,400);
}
function goView(id){
	MyFormWin.close();
	MyFormWin.showMyWin('订单详情','<%=basePath%>commodity!orderView.action?orderId='+id,580,310);
}
</script>
</head>
<body>
	<%@ include file="../common/navigation.jsp"%>
	<c:if test="${not empty commId}">
	<%@ include file="commodityHead.jsp"%>
	</c:if>
	<form id="formId" action="<%=basePath%>commodity!info.action" method="post" onsubmit="return doSubmit();">
		<input name="commId" type="hidden" value="<c:out value='${commId}' />" />
		<input name="num" type="hidden" value="<c:out value='${num}' />" />
		<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0" class="lyt_search">
			<tr>
				<td class="lyt_search_note" style="width:60px;">
					订单号
				</td>
				<td width="80">
					<input type="text" name="orderNo" style="width:80px;" value="<c:out value='${orderNo}' />" maxlength="100" />
				</td>
				<td class="lyt_search_note" style="width:60px;">
					支付方式
				</td>
				<td width="80">
					<input type="text" name="orderType" style="width:80px;" value="<c:out value='${orderType}' />" maxlength="100" />
				</td>
				<td class="lyt_search_note" style="width:60px;">
					支付状态
				</td>
				<td width="70">
					<select name="orderStatus" style="width:70px;">
						<option value="">全部</option>
						<option value="1" <c:if test="${orderStatus=='1'}">selected</c:if>>已付款</option>
						<option value="0" <c:if test="${orderStatus=='0'}">selected</c:if>>待付款</option>
					</select>
				</td>
				<td class="lyt_search_note" style="width:40px;">
					标记
				</td>
				<td width="55">
					<select name="markFlag" style="width:55px;">
						<option value="">全部</option>
						<option value="1" <c:if test="${markFlag=='1'}">selected</c:if>>是</option>
						<option value="0" <c:if test="${markFlag=='0'}">selected</c:if>>否</option>
					</select>
				</td>
				<td class="lyt_search_note" style="width:60px;">
					标记包含
				</td>
				<td width="80">
					<input type="text" name="mark" style="width:80px;" value="<c:out value='${mark}' />" maxlength="100" />
				</td>
				<td align="right" class="lyt_search_opt">
					<div class="lyt_button">
						<input value="查询" type="submit" />
					</div>
				</td>
			</tr>
		</table>
	</form>
	<table border="1" cellspacing="0" cellpadding="0" class="lyt_result">
		<tr>
			<th width="40">序号</th>
			<th width="80">订单号</th>
			<th width="55">商品型号</th>
			<th width="30">单价（元）</th>
			<th width="30">数量</th>
			<th width="30">运费（元）</th>
			<th width="30">捐赠（元）</th>
			<th width="45">总金额（元）</th>
			<th width="80">支付方式</th>
			<th width="50">支付状态</th>
			<th width="80">提交时间</th>
			<th width="80">付款时间</th>
			<th width="50">买家</th>
			<th width="130">买家信息</th>
			<th width="130">校友资料</th>
			<th width="60">标记</th>
			<th width="80">操作</th>
		</tr>
		<c:forEach items="${orderList}" var="temp" varStatus="status">
		<tr>
			<td>
				<b><c:out value="${(p.curr-1)*p.size+status.count}" /></b>
			</td>
			<td>
				<c:out value="${temp.orderNo}" />
			</td>
			<td>
				<c:out value="${temp.commDetailName}" />
			</td>
			<td>
				<fmt:formatNumber value='${temp.commSalefee}' pattern='0.##' type='number'/>
			</td>
			<td>
				<c:out value="${temp.commNum}" />
			</td>
			<td>
				<fmt:formatNumber value='${temp.shippingFee}' pattern='0.##' type='number'/>
			</td>
			<td>
				<fmt:formatNumber value='${temp.donationFee}' pattern='0.##' type='number'/>
			</td>
			<td>
				<fmt:formatNumber value='${temp.orderFee}' pattern='0.##' type='number'/>
			</td>
			<td>
				<c:out value="${temp.orderType}" />
			</td>
			<td>
				<c:choose><c:when test="${temp.orderStatus=='1'}">已付款</c:when><c:otherwise>待付款</c:otherwise></c:choose>
			</td>
			<td>
				<fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${temp.orderTime}'/>
			</td>
			<td>
				<fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${temp.orderOkTime}'/>
			</td>
			<td>
				<c:out value="${temp.buyerName}" />
			</td>
			<td>
				<c:choose><c:when test="${temp.buyerSex=='1'}">性别：男；</c:when><c:when test="${temp.buyerSex=='2'}">性别：女；</c:when></c:choose><c:if test="${not empty temp.buyerMobile}">手机：<c:out value="${temp.buyerMobile}" />；</c:if><c:if test="${not empty temp.buyerPhone}">电话：<c:out value="${temp.buyerPhone}" />；</c:if><c:if test="${not empty temp.buyerEmail}">邮箱：<c:out value="${temp.buyerEmail}" />；</c:if><c:if test="${not empty temp.buyerSheng || not empty temp.buyerShi || not empty temp.buyerQu || not empty temp.buyerAddress}">地址：<c:if test="${not empty temp.buyerSheng}"><c:out value="${temp.buyerSheng}" />（省） </c:if><c:if test="${not empty temp.buyerShi}"><c:out value="${temp.buyerShi}" />（市） </c:if><c:if test="${not empty temp.buyerQu}"><c:out value="${temp.buyerQu}" />（区） </c:if><c:if test="${not empty temp.buyerAddress}"><c:out value="${temp.buyerAddress}" /></c:if>；</c:if><c:if test="${not empty temp.buyerZipcode}">邮编：<c:out value="${temp.buyerZipcode}" />；</c:if>
			</td>
			<td>
				<c:if test="${temp.alumniFlag=='1'}">
				校友；<c:if test="${not empty temp.studyYearin}">入学年：<c:out value="${temp.studyYearin}" />；</c:if><c:if test="${not empty temp.studyYearover}">离校年：<c:out value="${temp.studyYearover}" />；</c:if><c:if test="${not empty temp.studyAcademy}">院系：<c:out value="${temp.studyAcademy}" />；</c:if><c:if test="${not empty temp.studyMajor}">专业：<c:out value="${temp.studyMajor}" />；</c:if><c:if test="${not empty temp.studyClass}">班级：<c:out value="${temp.studyClass}" />；</c:if><c:if test="${not empty temp.studyNum}">学号：<c:out value="${temp.studyNum}" />；</c:if><c:if test="${not empty temp.studyDegree}">学历：<c:out value="${temp.studyDegree}" />；</c:if>
				</c:if>
			</td>
			<td>
				<c:out value="${temp.mark}" />
			</td>
			<td>
				<a href="javascript:;" onclick="goView('${temp.orderId}');">详情</a>
				<a href="javascript:;" onclick="goMark('${temp.orderId}');">标记</a>
				<a href="javascript:;" onclick="MyMsg.confirm('确定要删除该捐赠？','<%=basePath%>commodity!deleteOrder.action?orderId=${temp.orderId}&${fn:replace(p.pars,'orderId','radom')}');">删除</a>
			</td>
		</tr>
		</c:forEach>
	</table>
	<my:page action="commodity!info.action"></my:page>	
</body>
</html>