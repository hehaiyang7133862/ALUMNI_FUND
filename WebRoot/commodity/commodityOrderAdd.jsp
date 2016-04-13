<%@ include file="../common/include.jsp"%>
<%@ page language="java"
	import="java.util.*,com.laungee.proj.common.util.*"
	pageEncoding="UTF-8"%>
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
<script language="javascript">
function checkForm(form){
	formFormat("formId");
	var msg="";
	//所属商品型号
	if(form.commDetailId.value==""){
		msg+="请选择所属商品型号\n";
		form.commDetailId.style.backgroundColor=error;
	}
	// 商品单价
	if(form.commFee.value=="" || form.commFee.value<=0){
		msg+="商品单价要大于0元\n";
		form.commFee.style.backgroundColor=error;
	}
	// 商品数量
	if(form.commNum.value==""){
		msg+="请输入商品数量\n";
		form.commNum.style.backgroundColor=error;
	}else{
		if(isNaN(form.commNum.value)){
			msg+="请输入正确的商品数量\n";
			form.commNum.style.backgroundColor=error;
		}
	}
	// 捐赠金额
	if(form.donationFee.value!="" && form.donationFee.value<=0){
		msg+="捐赠金额要大于等于0元\n";
		form.donationFee.style.backgroundColor=error;
	}
	if(form.donationFee.value!=""&&isNaN(form.donationFee.value)){
		msg+="请输入正确的捐赠金额\n";
		form.donationFee.style.backgroundColor=error;
	}
	// 收货方式
	if(form.shippingType.value==2){
		// 买家姓名
		if(form.buyerName.value==""){
			msg+="请输入买家姓名\n";
			form.buyerName.style.backgroundColor=error;
		}
		// 买家电话、手机
		if(form.buyerPhone.value==""&&form.buyerMobile.value==""){
			msg+="请输入买家电话或手机\n";
			form.buyerPhone.style.backgroundColor=error;
			form.buyerMobile.style.backgroundColor=error;
		}
		// 买家邮箱
		if(form.buyerEmail.value==""){
			msg+="请输入买家邮箱\n";
			form.buyerEmail.style.backgroundColor=error;
		}
		/* // 买家邮编
		if(form.buyerZipcode.value==""){
			msg+="请输入买家邮编\n";
			form.buyerZipcode.style.backgroundColor=error;
		} */
		// 买家地址
		if(form.buyerAddress.value==""){
			msg+="请输入买家地址\n";
			form.buyerAddress.style.backgroundColor=error;
		}
		// 物流运费
		if(form.shippingFee.value==""){
			msg+="物流运费不能为空\n";
			form.shippingFee.style.backgroundColor=error;
		}
	}
	// 实付金额
	if(form.orderFee.value=="" || form.orderFee.value<0){
		msg+="实付金额要大于等于0元\n";
		form.orderFee.style.backgroundColor=error;
	}
	// 买家留言
	if(!(getLength(form.orderMemo.value)<=2000)){
		msg+="买家留言不能超过1000个字\n";
		form.orderMemo.style.backgroundColor=error;
	}
	// 买件评论说明
	if(!(getLength(form.starMemo.value)<=2000)){
		msg+="买家留言不能超过1000个字\n";
		form.starMemo.style.backgroundColor=error;
	}
	// 备注
	if(!(getLength(form.memo.value)<=2000)){
		msg+="备注不能超过1000个字\n";
		form.memo.style.backgroundColor=error;
	}
	// 返回
	if(msg!=""){
		alert(msg);
		return false;
	}
	else{
		return true;
	}
}
$(document).ready(function(){
   	<c:if test="${not empty alert}">
   		//刷新
   		setTimeout(myRefresh(),2000);
   	</c:if>
   });
function change(){
	$.getJSON('<%=basePath%>commodity!ajax.action?commId='+$('#commId').val(),function(data){
		if(data.result=="success"){
			if(data.detail==""){
				$("#commDetailId").html("<option value=''>所属商品型号暂无</option>");
			}else{
				$("#commDetailId").html(data.detail);
			}
		}
	});
}
function changeType(obj){
	var type = obj.value;
	if(type==1){
		$(".buyer").each(function(){
		    $(this).hide();
		});
	}else if(type==2){
		$(".buyer").each(function(){
		    $(this).show();
		});
	}
	calcSum();
}
function changeDetail(obj){
	var type = obj.value;
	if(type!=''){
		$.getJSON('<%=basePath%>commodity!ajaxDetail.action?detailId='+type,function(data){
			if(data.result=="success"){
				$("#commFee").val(data.saleFee);
				$("#shippingFee").val(data.shippingFee);
			}
		});
	}else{
		$("#commFee").val("");
		$("#shippingFee").val("");
		$("#orderFee").val("");
	}
}
function calcSum(){
	//单价
	var commFee = $("#commFee").val();
	//数量
	var commNum = $("#commNum").val();
	//捐赠金额
	var donationFee = $("#donationFee").val();
	//收货方式
	var shippingType = $("#shippingType").val();
	//运费
	var shippingFee = $("#shippingFee").val();
	var orderFee = 0;
	if(commFee!=""&&!isNaN(commFee)){
		if(commNum!=""&&!isNaN(commNum)){
			commNum = Number(commNum).toFixed(2);
			if(isNaN(commNum)){
				commNum = 0;
			}
			orderFee = Number(orderFee)+Number(commFee*commNum);
		}
		if(donationFee!=""){
			donationFee = Number(donationFee).toFixed(2);
			if(isNaN(donationFee)){
				donationFee = 0;
			}
			orderFee = Number(orderFee)+Number(donationFee);
		}
		if(shippingType==2){
			if(shippingFee!=""&&!isNaN(shippingFee)){
				orderFee = Number(orderFee)+Number(shippingFee);
			}
		}
	}
	$("#orderFee").val(orderFee.toFixed());
}
$(document).ready(function(){
	if(document.getElementById("commDetailId").value==""){
		$("#commFee").val("");
	}
	changeType(document.getElementById("shippingType"));
});
</script>

</head>
<body>
	<div class="float">
		<form id="formId" action="<%=basePath%>commodity!saveOrUpdateOrder.action"
			method="post" onSubmit="return checkForm(this)">
			<c:if test="${not empty comm}">
				<input name="commId" type="hidden" value="<c:out value='${comm.commId}' />" />
			</c:if>
			<input name="orderSource" type="hidden" value="<c:out value='线下购买' />" />
			<input name="detailId" type="hidden" value="<c:out value='${detailId}' />" />
			<input name="num" type="hidden" value="<c:out value='4' />" />
			<input name="orderId" type="hidden" value="<c:out value='${bean.orderId }' />" />
			<table border="0" cellspacing="0" cellpadding="0" class="lyt_view">
				<c:choose>
					<c:when test="${not empty comm}">
						<tr>
							<td class="lyt_view_note">商品名称</td>
							<td><input readonly="readonly" type="text" id="commName" name="commName" value="<c:out value='${comm.commName}'/>" /><span class="fnt_warn">*</span></td>
						</tr>
					</c:when>
					<c:when test="${empty comm}">
						<tr>
							<td class="lyt_view_note">所属商品</td>
							<c:if test="${not empty commList}">
							<td>
								<select style="width:300px;" name="commId" id="commId" onclick="change()">
									<c:forEach items="${commList}" var="comm">
										<option value="${comm.commId}">${comm.commName}</option>
									</c:forEach>
								</select>
								<span class="fnt_warn">*</span>
							</td>
							</c:if>
							<c:if test="${empty commList}">
							<td>暂无商品</td>
							</c:if>
						</tr>
					</c:when>
				</c:choose>
				<tr>
					<td class="lyt_view_note">所属商品型号</td>
					<td>
						<c:choose>
							<c:when test="${empty detailList}">
								<span style="color:#5e5e5e">暂无商品型号，请在对应的商品中增加型号!</span>
							</c:when>
							<c:otherwise>
								<select style="width:300px;" name="commDetailId" id="commDetailId" onchange="changeDetail(this)">
									<option value="">请选择商品型号</option>
									<c:forEach items="${detailList}" var="detailTemp">
										<option value="${detailTemp.detailId}" <c:if test="${detailTemp.detailId==bean.commDetailId}">selected="selected"</c:if>>${detailTemp.detailName}</option>
									</c:forEach>
								</select>
								<span class="fnt_warn">*</span>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">商品单价</td>
					<td><input readonly="readonly" type="text" id="commFee" name="commFee" value="<c:out value='${detail.saleFee}'/>" /><span class="fnt_warn">*</span></td>
				</tr>
				<tr>
					<td class="lyt_view_note">商品数量</td>
					<td><input onchange="calcSum()" type="text" id="commNum" name="commNum" value="<c:out value='${bean.commNum}'/>" /><span class="fnt_warn">*</span></td>
				</tr>
				<tr>
					<td class="lyt_view_note">捐赠金额</td>
					<td><input onchange="calcSum()" type="text" id="donationFee" name="donationFee" value="<c:out value='${bean.donationFee}'/>" /><span class="fnt_warn">*</span></td>
				</tr>
				<tr>
					<td class="lyt_view_note">收货方式</td>
					<td>
						<select id="shippingType" name="shippingType" onchange="changeType(this)">
							<option value="1" <c:if test="${bean.shippingType=='1'}">selected="selected"</c:if>>买家自提</option>
							<option value="2" <c:if test="${bean.shippingType!='1'}">selected="selected"</c:if>>物流发货</option>
						</select>
						<span class="fnt_warn">*</span>
					</td>
				</tr>
				<tr class="buyer">
					<td class="lyt_view_note">买家姓名</td>
					<td><input type="text" id="buyerName" name="buyerName" value="<c:out value='${bean.buyerName}'/>" /><span class="fnt_warn">*</span></td>
				</tr>
				<tr class="buyer">
					<td class="lyt_view_note">买家电话</td>
					<td><input type="text" id="buyerPhone" name="buyerPhone" value="<c:out value='${bean.buyerPhone}'/>" /><span class="fnt_warn">*</span></td>
				</tr>
				<tr class="buyer">
					<td class="lyt_view_note">买家手机</td>
					<td><input type="text" id="buyerMobile" name="buyerMobile" value="<c:out value='${bean.buyerMobile}'/>" /><span class="fnt_warn">*</span></td>
				</tr>
				<tr class="buyer">
					<td class="lyt_view_note">买家邮箱</td>
					<td><input type="text" id="buyerEmail" name="buyerEmail" value="<c:out value='${bean.buyerEmail}'/>" /><span class="fnt_warn">*</span></td>
				</tr>
				<tr class="buyer">
					<td class="lyt_view_note">买家邮编</td>
					<td><input type="text" id="buyerZipcode" name="buyerZipcode" value="<c:out value='${bean.buyerZipcode}'/>" /></td>
				</tr>
				<tr class="buyer">
					<td class="lyt_view_note">买家地址</td>
					<td><input type="text" id="buyerAddress" name="buyerAddress" value="<c:out value='${bean.buyerAddress}'/>" /><span class="fnt_warn">*</span></td>
				</tr>
				<tr class="buyer">
					<td class="lyt_view_note">物流运费</td>
					<td><input readonly="readonly" type="text" id="shippingFee" name="shippingFee" value="<c:out value='${bean.shippingFee}'/>" /><span class="fnt_warn">*</span></td>
				</tr>
				<tr>
					<td class="lyt_view_note">实付金额</td>
					<td><input type="text" id="orderFee" name="orderFee" value="<c:out value='${bean.orderFee}'/>" /><span class="fnt_warn">*</span></td>
				</tr>
				<tr>
					<td class="lyt_view_note">订单状态</td>
					<td>
						<select name="orderStatus">
							<option value="1" <c:if test="${bean.orderStatus==1}">selected="selected"</c:if>>等待买家付款</option>
							<option value="2" <c:if test="${bean.orderStatus==2}">selected="selected"</c:if>>买家已付款</option>
							<option value="3" <c:if test="${bean.orderStatus==3}">selected="selected"</c:if>>已发货</option>
							<option value="4" <c:if test="${bean.orderStatus==4}">selected="selected"</c:if>>已收货</option>
							<option value="5" <c:if test="${bean.orderStatus==5}">selected="selected"</c:if>>评价</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">买家评价</td>
					<td>
						<select name="starNum">
							<option value="1" <c:if test="${bean.starNum==1}">selected="selected"</c:if>>一星</option>
							<option value="2" <c:if test="${bean.starNum==2}">selected="selected"</c:if>>二星</option>
							<option value="3" <c:if test="${bean.starNum==3}">selected="selected"</c:if>>三星</option>
							<option value="4" <c:if test="${bean.starNum==4}">selected="selected"</c:if>>四星</option>
							<option value="5" <c:if test="${bean.starNum==5}">selected="selected"</c:if>>五星</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">买家留言</td>
					<td><textarea name="orderMemo"><c:out value='${bean.orderMemo}'/></textarea></td>
				</tr>
				<tr>
					<td class="lyt_view_note">买家评价说明</td>
					<td><textarea name="starMemo"><c:out value='${bean.starMemo}'/></textarea></td>
				</tr>
				<tr>
					<td class="lyt_view_area">备注</td>
					<td><textarea name="memo"><c:out value='${bean.memo}'/></textarea></td>
				</tr>
			</table>
			<div class="lyt_submit">
				<input type="submit" value="保存" /> <input type="button" value="取消"
					onClick="if(confirm('您确定要放弃此操作吗？'))parent.location='<%=basePath%>commodity!info.action?commId=${commId}&num=4';" />
			</div>
		</form>
	</div>
</body>
</html>
