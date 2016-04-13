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
<!-- kindeditor -->
<script src="<%=basePathHead%>UI/kindeditor/kindeditor.js" type="text/javascript"></script>
</head>
<script type="text/javascript">
$(document).ready(function(){
	// 商品详情
	var sjmsEditor;
	$(document).ready(function(){
	  	KindEditor.ready(function(K) {
			sjmsEditor = K.create("textarea[name='commDetail']", {
				items : [
					'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'cut', 'copy', 'paste',
					'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
					'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
					'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
					'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
					'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
					'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'map', 'link', 'unlink'
				],
				langType : 'zh_CN',
				resizeType : 1,
				themeType : 'simple',
				allowFileManager : false,
				imageTabIndex : 1,
				/* 禁用 */
				readonlyMode : true
			});
		});
	});
});
</script>
<body>
<%@ include file="../common/navigation.jsp"%>
<c:if test="${not empty bean }">
<%@ include file="commodityHead.jsp"%>
</c:if>
	<div class="float">
		<table border="0" cellspacing="0" cellpadding="0" class="lyt_view">
			<tr>
				<td class="lyt_view_note">商品名称</td>
				<td style="width:267px;">${bean.commName}</td>
				<td class="lyt_view_note">商品类型</td>
				<td style="width:267px;">${bean.tbCommodityType.typeName }
				</td>
				<td><a href="<%=basePath%>commodity!operation.action?commId=${bean.commId}">编辑</a></td>
			</tr>
			<tr>
				<td class="lyt_view_note">商品简介</td>
				<td colspan="3"><textarea style="width:620px;" readonly="readonly">${bean.commIntro}</textarea></td>
			</tr>
			<tr>
				<td class="lyt_view_note">商品详情</td>
				<td colspan="3"><textarea style="width:620px;height:160px;" name="commDetail" readonly="readonly">${bean.commDetail}</textarea></td>
			</tr>
			<tr>
				<td class="lyt_view_note">是否热门</td>
				<td colspan="3">
					<c:if test="${bean.isHot=='1'}">是</c:if>
					<c:if test="${bean.isHot=='0'}">否</c:if>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_note">运费</td>
				<td style="width:267px;">
					<c:if test="${bean.isShipping=='1'}">免运费</c:if>
					<c:if test="${bean.isShipping=='0'}">${bean.shippingFee}</c:if>
				</td>
				<td class="lyt_view_note">捐赠</td>
				<td style="width:267px;">
					<c:if test="${bean.isDonation=='1'}">
						${bean.donationFee }
					</c:if>
					<c:if test="${bean.isDonation=='0'}">
						不捐赠
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_area">运费说明</td>
				<td colspan="3"><textarea style="width:620px;" readonly="readonly">${bean.shippingMemo }</textarea></td>
			</tr>
			<tr>
				<td class="lyt_view_area">捐赠说明</td>
				<td colspan="3"><textarea style="width:620px;" readonly="readonly">${bean.donationMemo }</textarea></td>
			</tr>
			<tr>
				<td class="lyt_view_area">库存</td>
				<td colspan="3">
					<c:choose>
						<c:when test="${not empty bean.stockNum}">
							<fmt:formatNumber value="${bean.stockNum}" />
						</c:when>
						<c:otherwise>暂无库存</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_area">成本价</td>
				<td>
					<c:choose>
						<c:when test="${not empty bean.minCostFee}">
							<fmt:formatNumber value="${bean.minCostFee}" />~
							<fmt:formatNumber value="${bean.maxCostFee}" />
						</c:when>
						<c:otherwise>暂无成本价</c:otherwise>
					</c:choose>
				</td>
				<td class="lyt_view_area">出售价</td>
				<td>
					<c:choose>
						<c:when test="${not empty bean.minSaleFee}">
							<fmt:formatNumber value="${bean.minSaleFee}" />~
							<fmt:formatNumber value="${bean.maxSaleFee}" />
						</c:when>
						<c:otherwise>暂无出售价</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="lyt_view_area">备注</td>
				<td colspan="3"><textarea style="width:620px;" readonly="readonly">${bean.memo }</textarea></td>
			</tr>
		</table>
	</div>
</body>
</html>