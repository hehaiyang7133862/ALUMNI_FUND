<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<%@ include file="../common/head.jsp"%>
<%@ include file="../common/include.jsp"%>
<%@ include file="../common/kindeditor.jsp"%>

<style type="text/css">
.divTitle {
	width: 875px;
	height: 25px;
	line-height: 25px;
	margin: 5px 0px 0px 5px;
	font-weight: bold;
	background-color: #d0ddf1;
	overflow: hidden;
}
</style>
<script>
	function detailOn(obj){
		$('#detailTitleUl').children().css({'color':'','background':'url(<%=basePath%>UI/images/nav_point.png) no-repeat top left'});
		$('#detailContentDiv').children().hide();
		
		var titleObj = $(obj);
		var contentObj = $('#detailContentDiv table:eq('+titleObj.prevAll().length+')');
		
		titleObj.css({'color':'#fff','background':'url(<%=basePath%>UI/images/nav_point_on.png) no-repeat top left'});
		contentObj.show();
		contentObj.find('input[name="detailTitle"]').select();
	}
</script>
</head>
<body>
	<%@ include file="../common/navigation.jsp"%>
	<c:if test="${not empty bean.commId}">
		<%@ include file="commodityHeadReadOnly.jsp"%>
	</c:if>
	<div class="float">
		<input id="commId" name="commId" type="hidden"
			value="<c:out value='${bean.commId}' />" />
		<table border="0" cellspacing="0" cellpadding="0" class="lyt_view">
			<tr>
				<td class="lyt_view_note" style="width:100px;">商品名称</td>
				<td style="width:300px"><c:out value='${bean.commName}' /></td>
				<td class="lyt_view_note" style="width:80px">商品类别</td>
				<td style="width:350px"><c:forEach items="${ctList}" var="temp" varStatus="status">
						<c:if test="${temp.typeId==bean.commType}">
							<c:out value="${temp.typeName}" />
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_note">是否热门</td>
				<td><c:choose>
						<c:when test="${bean.isHot=='1'}">是（排序：<c:out value='${bean.hotOrder}' />） </c:when>
						<c:otherwise>否</c:otherwise>
					</c:choose>
				</td>
				<td class="lyt_view_note">商品类型</td>
				<td><c:choose>
						<c:when test="${bean.commStyle=='1'}">实物商品 </c:when>
						<c:otherwise>虚拟商品</c:otherwise>
					</c:choose></td>
			</tr>
			<c:if test="${bean.commStyle=='1'}">
				<tr>
					<td class="lyt_view_note" style="wdith:100px">需付运费</td>
					<td><c:choose>
							<c:when test="${bean.isShipping=='1'}">是（运费：<fmt:formatNumber value='${bean.shippingFee}' pattern='0.##' type='number' />&nbsp;元） </c:when>
							<c:otherwise>否</c:otherwise>
						</c:choose>
					</td>
					<td class="lyt_view_area">运费说明</td>
					<td><c:out value="${bean.shippingMemo}" /></td>
				</tr>
			</c:if>
			<tr>
				<td class="lyt_view_note">自动捐赠</td>
				<td><c:choose>
						<c:when test="${bean.isDonation=='1'}">是 （捐赠金额：<fmt:formatNumber value='${bean.donationFee}' pattern='0.##' type='number' />&nbsp;元）</c:when>
						<c:otherwise>否</c:otherwise>
					</c:choose>
				</td>
				<td class="lyt_view_area">捐赠说明</td>
				<td><c:out value="${bean.donationMemo}" /></td>
			</tr>
			<tr>
				<td class="lyt_view_area">项目关键字标签</td>
				<td><c:out value="${bean.searchKey}" /></td>
				<td class="lyt_view_note">是否上架</td>
				<td><c:choose>
						<c:when test="${bean.isShelves=='1'}">是 </c:when>
						<c:otherwise>否</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td class="lyt_view_area">商品简介</td>
				<td colspan="3"><span style="width:760px; display:block"><c:out
							value='${bean.commIntro}' />
				</span></td>
			</tr>
		</table>

		<div class="divTitle">
			<div style="float:left;padding-left:8px;overflow:hidden;">商品详情</div>
		</div>
		<table width="875" style="margin-left:5px;margin-top:5px;" border="0"
			cellpadding="0" cellspacing="0">
			<tr>
				<td class="nav_GA_left" width="2" height="28"></td>
				<td class="nav_GA_center">
					<div class="nav_GA">
						<ul id="detailTitleUl">
							<li onclick="detailOn(this);"
								style="line-height:22px;text-align:center;cursor:pointer;color:#fff;background:url(<%=basePath%>UI/images/nav_point_on.png) no-repeat top left;"><c:out
									value="${bean.detailTitle1}" /> <c:if
									test="${empty bean.detailTitle1}">&nbsp;</c:if></li>
							<c:if test="${not empty bean.detailTitle2}">
								<li onclick="detailOn(this);"
									style="line-height:22px;text-align:center;cursor:pointer;"><c:out
										value="${bean.detailTitle2}" /></li>
							</c:if>
							<c:if test="${not empty bean.detailTitle3}">
								<li onclick="detailOn(this);"
									style="line-height:22px;text-align:center;cursor:pointer;"><c:out
										value="${bean.detailTitle3}" /></li>
							</c:if>
							<c:if test="${not empty bean.detailTitle4}">
								<li onclick="detailOn(this);"
									style="line-height:22px;text-align:center;cursor:pointer;"><c:out
										value="${bean.detailTitle4}" /></li>
							</c:if>
							<c:if test="${not empty bean.detailTitle5}">
								<li onclick="detailOn(this);"
									style="line-height:22px;text-align:center;cursor:pointer;"><c:out
										value="${bean.detailTitle5}" /></li>
							</c:if>
							<c:if test="${not empty bean.detailTitle6}">
								<li onclick="detailOn(this);"
									style="line-height:22px;text-align:center;cursor:pointer;"><c:out
										value="${bean.detailTitle6}" /></li>
							</c:if>
							<c:if test="${not empty bean.detailTitle7}">
								<li onclick="detailOn(this);"
									style="line-height:22px;text-align:center;cursor:pointer;"><c:out
										value="${bean.detailTitle7}" /></li>
							</c:if>
							<c:if test="${not empty bean.detailTitle8}">
								<li onclick="detailOn(this);"
									style="line-height:22px;text-align:center;cursor:pointer;"><c:out
										value="${bean.detailTitle8}" /></li>
							</c:if>
							<c:if test="${not empty bean.detailTitle9}">
								<li onclick="detailOn(this);"
									style="line-height:22px;text-align:center;cursor:pointer;"><c:out
										value="${bean.detailTitle9}" /></li>
							</c:if>
							<c:if test="${not empty bean.detailTitle10}">
								<li onclick="detailOn(this);"
									style="line-height:22px;text-align:center;cursor:pointer;"><c:out
										value="${bean.detailTitle10}" /></li>
							</c:if>
						</ul>
					</div>
				</td>
				<td class="nav_GA_right" width="2"></td>
			</tr>
		</table>

		<div id="detailContentDiv" style="overflow:hidden;">
			<table class="lyt_view">
				<tr>	
					<td class="lyt_view_note" style="width:100px;">标签名称 </td>
					<td style="width:210px;">
						<c:out value="${bean.detailTitle1}" />
						<c:choose>
							<c:when test="${bean.detailPublish1=='1'}">&nbsp;（对外显示）</c:when>
							<c:otherwise>&nbsp;（不对外显示）</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<iframe style="width:870px;height:300px" src="getCommDetail.action?commId=${bean.commId}&index=1"></iframe>
					</td>
				</tr>
			</table>
			<c:if test="${not empty bean.detailTitle2}">
				<table class="lyt_view" style="display:none;">
					<tr>	
						<td class="lyt_view_note" style="width:100px;">标签名称 </td>
						<td style="width:210px;">
							<c:out value="${bean.detailTitle2}" />
							<c:choose>
								<c:when test="${bean.detailPublish2=='1'}">&nbsp;（对外显示）</c:when>
								<c:otherwise>&nbsp;（不对外显示）</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<iframe style="width:870px;height:300px" src="getCommDetail.action?commId=${bean.commId}&index=2"></iframe>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${not empty bean.detailTitle3}">
				<table class="lyt_view" style="display:none;">
					<tr>	
						<td class="lyt_view_note" style="width:100px;">标签名称 </td>
						<td style="width:210px;">
							<c:out value="${bean.detailTitle3}" />
							<c:choose>
								<c:when test="${bean.detailPublish3=='1'}">&nbsp;（对外显示）</c:when>
								<c:otherwise>&nbsp;（不对外显示）</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<iframe style="width:870px;height:300px" src="getCommDetail.action?commId=${bean.commId}&index=3"></iframe>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${not empty bean.detailTitle4}">
				<table class="lyt_view" style="display:none;">
					<tr>	
						<td class="lyt_view_note" style="width:100px;">标签名称 </td>
						<td style="width:210px;">
							<c:out value="${bean.detailTitle4}" />
							<c:choose>
								<c:when test="${bean.detailPublish4=='1'}">&nbsp;（对外显示）</c:when>
								<c:otherwise>&nbsp;（不对外显示）</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<iframe style="width:870px;height:300px" src="getCommDetail.action?commId=${bean.commId}&index=4"></iframe>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${not empty bean.detailTitle5}">
				<table class="lyt_view" style="display:none;">
					<tr>	
						<td class="lyt_view_note" style="width:100px;">标签名称 </td>
						<td style="width:210px;">
							<c:out value="${bean.detailTitle5}" />
							<c:choose>
								<c:when test="${bean.detailPublish5=='1'}">&nbsp;（对外显示）</c:when>
								<c:otherwise>&nbsp;（不对外显示）</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<iframe style="width:870px;height:300px" src="getCommDetail.action?commId=${bean.commId}&index=5"></iframe>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${not empty bean.detailTitle6}">
				<table class="lyt_view" style="display:none;">
					<tr>	
						<td class="lyt_view_note" style="width:100px;">标签名称 </td>
						<td style="width:210px;">
							<c:out value="${bean.detailTitle6}" />
							<c:choose>
								<c:when test="${bean.detailPublish6=='1'}">&nbsp;（对外显示）</c:when>
								<c:otherwise>&nbsp;（不对外显示）</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<iframe style="width:870px;height:300px" src="getCommDetail.action?commId=${bean.commId}&index=6"></iframe>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${not empty bean.detailTitle7}">
				<table class="lyt_view" style="display:none;">
					<tr>	
						<td class="lyt_view_note" style="width:100px;">标签名称 </td>
						<td style="width:210px;">
							<c:out value="${bean.detailTitle7}" />
							<c:choose>
								<c:when test="${bean.detailPublish7=='1'}">&nbsp;（对外显示）</c:when>
								<c:otherwise>&nbsp;（不对外显示）</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<iframe style="width:870px;height:300px" src="getCommDetail.action?commId=${bean.commId}&index=7"></iframe>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${not empty bean.detailTitle8}">
				<table class="lyt_view" style="display:none;">
					<tr>	
						<td class="lyt_view_note" style="width:100px;">标签名称 </td>
						<td style="width:210px;">
							<c:out value="${bean.detailTitle8}" />
							<c:choose>
								<c:when test="${bean.detailPublish8=='1'}">&nbsp;（对外显示）</c:when>
								<c:otherwise>&nbsp;（不对外显示）</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<iframe style="width:870px;height:300px" src="getCommDetail.action?commId=${bean.commId}&index=8"></iframe>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${not empty bean.detailTitle9}">
				<table class="lyt_view" style="display:none;">
					<tr>	
						<td class="lyt_view_note" style="width:100px;">标签名称 </td>
						<td style="width:210px;">
							<c:out value="${bean.detailTitle9}" />
							<c:choose>
								<c:when test="${bean.detailPublish9=='1'}">&nbsp;（对外显示）</c:when>
								<c:otherwise>&nbsp;（不对外显示）</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<iframe style="width:870px;height:300px" src="getCommDetail.action?commId=${bean.commId}&index=9"></iframe>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${not empty bean.detailTitle10}">
				<table class="lyt_view" style="display:none;">
					<tr>	
						<td class="lyt_view_note" style="width:100px;">标签名称 </td>
						<td style="width:210px;">
							<c:out value="${bean.detailTitle10}" />
							<c:choose>
								<c:when test="${bean.detailPublish10=='1'}">&nbsp;（对外显示）</c:when>
								<c:otherwise>&nbsp;（不对外显示）</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<iframe style="width:870px;height:300px" src="getCommDetail.action?commId=${bean.commId}&index=10"></iframe>
						</td>
					</tr>
				</table>
			</c:if>
			
		</div>

		<div class="divTitle">
			<div style="float:left;padding-left:8px;overflow:hidden;">其他</div>
		</div>

		<table border="0" cellspacing="0" cellpadding="0" class="lyt_view">
			<tr>
				<td class="lyt_view_area">备注说明</td>
				<td colspan="3">
					<span style="width:770px; display:block"><c:out value='${bean.memo}' /></span>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_area">附件</td>
				<td colspan="5">
					<iframe class="frm_file" marginheight="0" frameborder="0"
						src="<%=basePath %>fileListReadOnly.action?name=TbCommodity&id=${bean.commId}">
					</iframe>
				</td>
			</tr>
		</table>
		<my:timeView idField="commId" table="TbCommodity" value="${bean.commId}"></my:timeView>
	</div>
</body>
</html>
