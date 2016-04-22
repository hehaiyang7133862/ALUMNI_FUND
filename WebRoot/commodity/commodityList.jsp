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
</head>
<script type="text/javascript">
	$(document).ready(function(){
		windowResizeFn.add(function(){
				$('#resultDiv').width(document.body.clientWidth);
				$('#resultDiv').height(document.body.clientHeight-120-$('.lyt_nav').height());
				if($('.lyt_result').width()>$('#resultDiv').width()){
					$('.lyt_result').css('width','1060px');
				}else{
					$('.lyt_result').css('width','100%');
				}
			});
		});
	function doSubmit(){
		formFormat("formId");
		return true;
	}
	function goNew(){
		top.uploadTab('商品管理 > 新建商品','新建商品','<%=basePath%>commodity!operation.action');
	}
	function goEdit(id){
		top.uploadTab('商品【'+id+'】管理','商品【'+id+'】管理','<%=basePath%>commodity!operation.action?commId='+id);
	}
	function goTupianMgt(id){
		top.uploadTab('商品【'+id+'】管理','商品【'+id+'】管理','<%=basePath%>commodity!info.action?commId='+id+'&num=1');
	}
	function goCanshuMgt(id){
		top.uploadTab('商品【'+id+'】管理','商品【'+id+'】管理','<%=basePath%>commodity!info.action?commId='+id+'&num=2');
	}
	function goXinghaoMgt(id){
		top.uploadTab('商品【'+id+'】管理','商品【'+id+'】管理','<%=basePath%>commodity!info.action?commId='+id+'&num=3');
	}
	function goDingdanMgt(id){
		top.uploadTab('商品【'+id+'】管理','商品【'+id+'】管理','<%=basePath%>commodity!info.action?commId='+id+'&num=4');
	}
	function goDelete(id){
		MyMsg.confirmFn('确定要删除该商品？',function(){
			location='<%=basePath%>commodity!delete.action?commId='+id+'&${fn:replace(p.pars,'commId','radom')}';
		});
	}
</script>
<body>
	<div class="page">
	<%@ include file="../common/navigation.jsp"%>
		<form id="formId" action="<%=basePath%>commodity!list.action" method="get" onsubmit="return doSubmit()">
			<input type="hidden" name="commType" value="${commType}" />
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="lyt_search">
				<tr>
					<td class="lyt_search_note" style="width:60px;">商品名称</td>
					<td width="150">
						<input type="text" name="commName" value="<c:out value='${commName}' />" style="width:150px;" maxlength="20" />
					</td>
					<td class="lyt_search_note" style="width:60px;">库存</td>
					<td width="60">
						<select name="stockNum" style=" width:60px">
							<option value="">全部</option>
							<option value="1" <c:if test="${stockNum==1}">selected="selected"</c:if>>有货</option>
							<option value="0" <c:if test="${stockNum==0}">selected="selected"</c:if>>缺货</option>
						</select>
					</td>
					<td class="lyt_search_note" style="width:60px;">上架</td>
					<td width="60">
						<select name="isShelves" style=" width:60px">
								<option value="">全部</option>
								<option value="1" <c:if test="${isShelves==1}">selected="selected"</c:if>>是</option>
								<option value="0" <c:if test="${isShelves==0}">selected="selected"</c:if>>否</option>
						</select>
					</td>
					<td class="lyt_search_note" style="width:60px;">热门</td>
					<td width="60">
						<select name="isHot" style=" width:60px">
								<option value="">全部</option>
								<option value="1" <c:if test="${isHot==1}">selected="selected"</c:if>>是</option>
								<option value="0" <c:if test="${isHot==0}">selected="selected"</c:if>>否</option>
						</select>
					</td>
				<td align="right" class="lyt_search_opt">
					<div class="lyt_button">
						<input type="submit" value="查询" /> 
						<input type="button" onclick="goNew();" value="新增" />
					</div>
				</td>
			</tr>
			</table>
	
		</form>
		<div id="resultDiv" style="overflow:auto;">
		<table class="lyt_result" style="width:1060px">
			<tr>
				<th width="40px">序号</th>
				<th width="400px">商品名称</th>
				<th width="100px">库存</th>
				<th width="100px">运费</th>
				<th width="100px">捐赠</th>
				<th width="40px">上架</th>
				<th width="80px">热门</th>
				<th width="200px">操作</th>
			</tr>
			<c:forEach items="${beanList }" var="bean" varStatus="status">
				<tr>
					<td>
						<b><c:out value="${(p.curr-1)*p.size+status.count}" /></b>
					</td>
					<td>
						<a onclick="goEdit('${bean.commId}');" href="javascript:;"><c:out value="${bean.commName }" /></a>
					</td>
					<td><c:choose>
							<c:when test="${not empty bean and not empty bean.limitNum and bean.limitNum == 1 }"><c:choose>
								<c:when test="${not empty bean and not empty bean.stockNum and bean.stockNum >= 1 }">有货（<c:out value="${bean.stockNum }" />）</c:when>
								<c:otherwise>缺货</c:otherwise>
							</c:choose></c:when>
								<c:otherwise>不限量</c:otherwise>
								</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${bean.isShipping=='1' }">免运费</c:when>
							<c:otherwise>
								运费（<fmt:formatNumber value='${bean.shippingFee}' pattern='0.##' type='number'/>元）
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${bean.isDonation=='0' }">不捐赠</c:when>
							<c:otherwise>
								捐赠（<fmt:formatNumber value='${bean.donationFee}' pattern='0.##' type='number'/>元）
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${bean.isShelves=='1'}">是</c:when>
							<c:otherwise>否</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${bean.isHot=='1'}">是（<c:out value="${bean.hotOrder}"></c:out>）</c:when>
							<c:otherwise>否</c:otherwise>
						</c:choose>
					</td>
					<td>
						<a onclick="goEdit('${bean.commId}');" href="javascript:;">编辑</a>
						<a onclick="goTupianMgt('${bean.commId}');" href="javascript:;">图片</a>
						<a onclick="goCanshuMgt('${bean.commId}');" href="javascript:;">参数</a>
						<a onclick="goXinghaoMgt('${bean.commId}');" href="javascript:;">型号</a>
						<a onclick="goDingdanMgt('${bean.commId}');" href="javascript:;">订单</a>
						<a onclick="goDelete('${bean.commId}');" href="javascript:;">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		</div>
		<my:page action="commodity!list.action"></my:page>	
	</div>
</body>
</html>