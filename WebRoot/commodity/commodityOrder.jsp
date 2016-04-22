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
	$(document).ready(function(){
		windowResizeFn.add(function(){
				$('#resultDiv').width(document.body.clientWidth);
				$('#resultDiv').height(document.body.clientHeight-120-$('.lyt_nav').height());
				if($('.lyt_result').width()>$('#resultDiv').width()){
					$('.lyt_result').css('width','1510px');
				}else{
					$('.lyt_result').css('width','100%');
				}
			});
		});
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
function add(commId){
	MyFormWin.close();
	MyFormWin.showMyWin('新增订单','<%=basePath%>EditOrCreatePrdOrder.action?commId='+commId,850,500);
}
function edit(orderId){
	MyFormWin.close();
	MyFormWin.showMyWin('编辑订单','<%=basePath%>EditOrCreatePrdOrder.action?orderId='+orderId,850,500);
}
	function exportAll(){
		MyMask.showHtml("正在导出捐赠信息,请稍后....");
		
		$.ajax({ 
			url:"<%=basePath%>Ajax_PrdOrderExport.action", 
			    type:"post",
			    dataType:'text',
			    cache:false,
			    data:$('#formId').serialize(),
			    success:function(data){
					var json = JSON.parse(data);
					if(json.result=="success"){
						setTimeout(function(){
							location.href = "<%=basePath%>fileDown!downImp.action?name=商品【${bean.commId}】订单信息.xls&path=" + json.path;
						},0);
					}else{
						MyMsg.alert("订单信息导出失败！");
					}
					setTimeout(function(){
						MyMask.hide();
					},0);
				}
			 });
		}
		function downModel(){
			MyMask.showHtml("正在下载导入模板,请稍后....");
			$.ajax({ 
			    url:"<%=basePath%>Ajax_PrdOrderExport.action?type=1", 
			    type:"post",
			    dataType:'text',
			    cache:false,
			    data:$('#formId').serialize(),
			    success:function(data){
					var json = JSON.parse(data);
					if(json.result=="success"){
						setTimeout(function(){
							location.href = "<%=basePath%>fileDown!downImp.action?name=商品【${bean.commId}】订单信息导入模板.xls&path=" + json.path;
						},0);
					}else{
						MyMsg.alert("导入模板下载失败！");
					}
					setTimeout(function(){
						MyMask.hide();
					},0);
				}
			 });
		}
		function importModel(){
			parent.addTab("商品【${bean.commId}】订单信息导入","商品【${bean.commId}】订单信息导入","openPrdOrderImportPage.action?commId=${commId}");
		}
</script>
</head>
<body>
	<%@ include file="../common/navigation.jsp"%>
	<c:if test="${not empty commId}">
	<%@ include file="commodityHead.jsp"%>
	</c:if>
	<form id="formId" action="<%=basePath%>commodity!info.action" method="post" onsubmit="return doSubmit();">
		<input name="commId" id="commId" type="hidden" value="<c:out value='${commId}' />" />
		<input name="num" type="hidden" value="<c:out value='${num}' />" />
		<table style="width: 100%;" border="0" cellpadding="0" cellspacing="0" class="lyt_search">
			<tr>
				<td class="lyt_search_note" style="width:60px;">订单号</td>
				<td width="80"><input type="text" name="orderNo"
					style="width:80px;" value="<c:out value='${orderNo}' />"
					maxlength="100" />
				</td>
				<td></td>
				<td></td>
				<td class="lyt_search_note" style="width:60px;">商品型号</td>
				<td width="80">
					<input type="text" name="detailName" style="width:80px;" value="<c:out value='${detailName}' />" maxlength="100" />
				</td>
				<td class="lyt_search_note" style="width:60px;">买家姓名</td>
				<td width="80"><input type="text" name="buyerName"
					style="width:80px;" value="<c:out value='${buyerName}' />"
					maxlength="100" />
				</td>
				<td width="60px" class="lyt_search_note">
					提交时间
				</td>
				<td width="320px">
					<div style="margin-top:4px;overflow:hidden;">
						<input type="text" id="orderTimeBeg" name="orderTimeBeg" class="txt" style="width:110px;" value="<c:out value='${orderTimeBeg}'/>" readonly="true"/>
						<img onClick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm',el:'orderTimeBeg'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle"/>
						至
						<input type="text" id="orderTimeEnd" name="orderTimeEnd" class="txt" style="width:110px;" value="<c:out value='${orderTimeEnd}'/>" readonly="true"/>
						<img onClick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm',el:'orderTimeEnd'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle"/>
					</div>
				</td>
			</tr>
			<tr>
				<td class="lyt_search_note" style="width:60px;">支付方式</td>
				<td width="80"><input type="text" name="orderType"
					style="width:80px;" value="<c:out value='${orderType}' />"
					maxlength="100" />
				</td>
				<td class="lyt_search_note" style="width:60px;">支付状态</td>
				<td width="70"><select name="orderStatus" style="width:70px;">
						<option value="">全部</option>
						<option value="1" <c:if test="${orderStatus=='1'}">selected</c:if>>已付款</option>
						<option value="0" <c:if test="${orderStatus=='0'}">selected</c:if>>待付款</option>
				</select>
				</td><td class="lyt_search_note" style="width:70px;">标记</td>
				<td width="55"><select name="markFlag" style="width:70px;">
						<option value="">全部</option>
						<option value="1" <c:if test="${markFlag=='1'}">selected</c:if>>是</option>
						<option value="0" <c:if test="${markFlag=='0'}">selected</c:if>>否</option>
				</select>
				</td>
				<td class="lyt_search_note" style="width:60px;">标记包含</td>
				<td width="80"><input type="text" name="mark"
					style="width:80px;" value="<c:out value='${mark}' />"
					maxlength="100" />
				</td>
				<td width="60px" class="lyt_search_note">付款时间</td>
				<td width="320px">
					<div style="margin-top:4px;overflow:hidden;">
					<input type="text" id="orderOkTimeBeg" name="orderOkTimeBeg" class="txt" style="width:110px;" value="<c:out value='${orderOkTimeBeg}'/>" readonly="true"/>
					<img onClick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm',el:'orderOkTimeBeg'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle"/>
					至
					<input type="text" id="orderOkTimeEnd" name="orderOkTimeEnd" class="txt" style="width:110px;" value="<c:out value='${orderOkTimeEnd}'/>" readonly="true"/>
					<img onClick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm',el:'orderOkTimeEnd'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle"/>
					</div>
				</td>
				<td align="right" class="lyt_search_opt">
					<div class="lyt_button">
						<input value="查询" type="submit" />
						<input value="新增" type="button" onclick="add(<c:out value='${commId}'/>);"/>
						<input value="导出" type="button" onclick="exportAll();"/>
					</div>
				</td>
			</tr>
		</table>
	</form>
	<div id="resultDiv" style="height:60px;overflow:auto;">
	<table style="width:1510px;" class="lyt_result">
		<tr>
			<th width="40px">序号</th>
			<th width="220px">订单号</th>
			<th width="80px">商品型号</th>
			<th width="100px">操作</th>
			<th width="50px">成本价（元）</th>
			<th width="50px">售价（元）</th>
			<th width="50px">数量</th>
			<th width="50px">运费（元）</th>
			<th width="50px">捐赠（元）</th>
			<th width="50px">总金额（元）</th>
			<th width="100px">支付方式</th>
			<th width="60px">支付状态</th>
			<th width="120px">提交时间</th>
			<th width="120px">付款时间</th>
			<th width="50px">买家</th>
			<th width="130px">买家信息</th>
			<th width="130px">校友资料</th>
			<th width="60px">标记</th>
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
				<a href="javascript:;" onclick="goView('${temp.orderId}');">详情</a>
				<a href="javascript:;" onclick="goMark('${temp.orderId}');">标记</a>
				<a href="javascript:;" onclick="edit('${temp.orderId}');">编辑</a>
				<a href="javascript:;" onclick="MyMsg.confirm('确定要删除该捐赠？','<%=basePath%>commodity!deleteOrder.action?orderId=${temp.orderId}&${fn:replace(p.pars,'orderId','radom')}');">删除</a>
			</td>
			<td>
				<fmt:formatNumber value='${temp.commCostfee}'pattern='0.##' type='number' />
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
		</tr>
		</c:forEach>
	</table>
	</div>
	<my:page action="commodity!info.action"></my:page>
	<div class="lyt_submit" style="margin-top:15px;padding:10px 10px 0px 10px;">
	<div style="float:left;color:blue;font-weight:bold;overflow:hidden;">
		合计：<fmt:formatNumber value='${orderCount}' pattern='#,##0' type='number'/>条，其中已付款：<fmt:formatNumber value='${orderOkCount}' pattern='#,##0' type='number'/>条， 合计金额：<fmt:formatNumber value='${orderOkAmount}' pattern='#,##0.##' type='number'/>元
				</div>
				<div style="float:right;overflow:hidden;">
					<a href="javascript:;" onclick="downModel();">下载导入模板</a>
					<a href="javascript:;" onclick="importModel();">订单信息导入</a>
				</div>
	  		</div>	
</body>
</html>