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
<%@ include file="../common/kindeditor.jsp"%>
<script language="javascript">
var commDetailEditor;
$(document).ready(function(){
  	KindEditor.ready(function(K) {
		commDetailEditor = K.create("textarea[name='commDetail']", {
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
			imageTabIndex : 1
		});
	});
});
function changeHot(){
	if($('#isHot').val()=='1'){
		$('#hotOrder').attr('readonly',false).css('background-color','transparent').focus();
	}else{
		$('#hotOrder').attr('readonly',true).css('background-color','#E5E5E5');
	}
}
function changeShip(){
	if($('#isShipping').val()=='1'){
		$('#shippingFee').attr('readonly',false).css('background-color','transparent').focus();
		$('#shippingMemo').attr('readonly',false).css('background-color','transparent');
	}else{
		$('#shippingFee').attr('readonly',true).css('background-color','#E5E5E5');
		$('#shippingMemo').attr('readonly',true).css('background-color','#E5E5E5');
	}
}
function changeDonation(){
	if($('#isDonation').val()=='1'){
		$('#donationFee').attr('readonly',false).css('background-color','transparent').focus();
		$('#donationMemo').attr('readonly',false).css('background-color','transparent');
	}else{
		$('#donationFee').attr('readonly',true).css('background-color','#E5E5E5');
		$('#donationMemo').attr('readonly',true).css('background-color','#E5E5E5');
	}
}
function checkForm(form){
	formFormat("formId");
	// 名称
	if($("#commName").val()==""){
		MyMsg.alertFn('请输入商品名称！',function(){
			$("#commName").focus();
		});
		return false;
	}
	// 所属分类
	if($("#commType").val()==""){
		MyMsg.alert('请选择商品所属分类！');
		return false;
	}
	<c:if test="${empty bean.commId}">
	// 商品图片
	var detailPicVal = $('#detailPic').val();
	if(detailPicVal == ""){
		MyMsg.alert("请选择需要上传商品图片！");
		return false;
	}else{
  		var detailPicValType = detailPicVal.split(".")[detailPicVal.split(".").length-1].toLowerCase();
		if(detailPicValType!="gif" && detailPicValType!="jpg" && detailPicValType!="jpeg" && detailPicValType!="png" && detailPicValType!="bmp"){
			MyMsg.alert("商品图片只允许jpg,png,bmp,jpeg,gif格式!");
			return false;
		}
	}
	// 库存
	if($("#stockNum").val() == "" || !/^\d+$/.test($("#stockNum").val())){
		MyMsg.alertFn('请输入商品库存量（正整数）！',function(){
			$("#stockNum").focus();
		});
		return false;
	}
	// 成本价
	if($("#costFee").val() == "" || !/^\d+(.\d{1,2})?$/.test($("#costFee").val())){
		MyMsg.alertFn('请输入商品成本价（最多两位小数的正整数）！',function(){
			$("#costFee").focus();
		});
		return false;
	}
	// 售价
	if($("#saleFee").val() == "" || !/^\d+(.\d{1,2})?$/.test($("#saleFee").val())){
		MyMsg.alertFn('请输入商品售价（最多两位小数的正整数）！',function(){
			$("#saleFee").focus();
		});
		return false;
	}
	</c:if>
	// 商品简介
	if($("#commIntro").val().length > 1000){
		MyMsg.alertFn('商品简介必须在1000字以内！',function(){
			$("#commIntro").focus();
		});
		return false;
	}
	// 是否上架
	if($("#isShelves").val() == ""){
		MyMsg.alert('请选择商品是否上架！');
		return false;
	}
	// 是否热门
	if($("#isHot").val() == ""){
		MyMsg.alert('请选择商品是否热门！');
		return false;
	}
	// 是否运费
	if($("#isShipping").val() == ""){
		MyMsg.alert('请选择商品是否需要运费！');
		return false;
	}
	// 运费
	if($("#isShipping").val() == "1" && ($("#shippingFee").val() == "" || !/^\d+(.\d{1,2})?$/.test($("#shippingFee").val()))){
		MyMsg.alertFn('请输入商品运费（最多两位小数的正整数）！',function(){
			$("#shippingFee").focus();
		});
		return false;
	}
	// 运费说明
	if($("#shippingMemo").val().length > 1000){
		MyMsg.alertFn('运费说明必须在1000字以内！',function(){
			$("#shippingMemo").focus();
		});
		return false;
	}
	// 是否捐赠
	if($("#isDonation").val() == ""){
		MyMsg.alert('请选择商品是否自动捐赠！');
		return false;
	}
	// 捐赠
	if($("#isDonation").val() == "1" && ($("#donationFee").val() == "" || !/^\d+(.\d{1,2})?$/.test($("#donationFee").val()))){
		MyMsg.alertFn('请输入商品自动捐赠金额（最多两位小数的正整数）！',function(){
			$("#shippingFee").focus();
		});
		return false;
	}
	// 捐赠说明
	if($("#donationMemo").val().length > 1000){
		MyMsg.alertFn('捐赠说明必须在1000字以内！',function(){
			$("#donationMemo").focus();
		});
		return false;
	}
	// 备注说明
	if($("#memo").val().length > 1000){
		MyMsg.alertFn('备注说明必须在1000字以内！',function(){
			$("#memo").focus();
		});
		return false;
	}
	return true;
}
</script>
</head>
<body>
<%@ include file="../common/navigation.jsp"%>
<c:if test="${not empty bean.commId}">
<%@ include file="commodityHead.jsp"%>
</c:if>
	<div class="float">
		<form id="formId" action="<%=basePath%>commodity!saveOrUpdate.action"
			method="post" onSubmit="return checkForm(this)" enctype="multipart/form-data">
			<input name="commId" type="hidden" value="<c:out value='${bean.commId}' />" />
			<table border="0" cellspacing="0" cellpadding="0" class="lyt_view">
				<tr>
					<td class="lyt_view_note" style="width:80px;">
						商品名称
						<span>*</span>
					</td>
					<td width="400">
						<input style="width:400px" type="text" name="commName" id="commName" value="<c:out value='${bean.commName}'/>" maxlength="100" />
					</td>
					<td class="lyt_view_note" style="width:80px;">
						所属分类
						<span>*</span>
					</td>
					<td width="240">
						<select id="commType" name="commType" style="width:234px">
							<option value="">--请选择--</option>
							<c:forEach items="${ctList}" var="temp" varStatus="status">
								<option value="${temp.typeId}" <c:if test="${bean.commType==temp.typeId}">selected="selected"</c:if>>${temp.typeName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<c:if test="${empty bean.commId}">
				<tr>
					<td class="lyt_view_note" style="width:80px;">
						商品图片
						<span>*</span>
					</td>
					<td colspan="3">
						<input style="width:400px" type="file" id="detailPic" name="detailPic"/>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						库存
						<span>*</span>
					</td>
					<td colspan="3">
						<input style="width:120px;" type="text" id="stockNum" name="stockNum" value="<fmt:formatNumber value='${stockNum}' pattern='0' type='number'/>"/>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						成本价
						<span>*</span>
					</td>
					<td colspan="3">
						<input style="width:120px;" type="text" id="costFee" name="costFee" value="<fmt:formatNumber value='${costFee}' pattern='0.##' type='number'/>"/>
						元
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						售价
						<span>*</span>
					</td>
					<td colspan="3">
						<input style="width:120px;" type="text" id="saleFee" name="saleFee" value="<fmt:formatNumber value='${saleFee}' pattern='0.##' type='number'/>"/>
						元
					</td>
				</tr>
				</c:if>
				<tr>
					<td class="lyt_view_area">商品简介</td>
					<td colspan="3">
						<textarea style="width:740px;height:60px;" name="commIntro" id="commIntro" onclick="myArea('commIntro',1000);" onkeyup="myArea('commIntro',1000);" onkeydown="myArea('commIntro',1000);" onchange="myArea('commIntro',1000);"><c:out value='${bean.commIntro}'/></textarea>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">商品详情</td>
					<td colspan="3">
						<textarea style="width:740px;height:200px;visibility:hidden;" name="commDetail" id="commDetail"><c:out value='${bean.commDetail}'/></textarea>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width:80px;">
						是否上架
						<span>*</span>
					</td>
					<td colspan="3">
						<select id="isShelves" name="isShelves" style="width:256px;">
							<option value="">--请选择--</option>
							<option value="1" <c:if test="${bean.isShelves==1}">selected</c:if>>是</option>
							<option <c:if test="${bean.isShelves==0}">selected</c:if> value="0">否</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						是否热门
						<span>*</span>
					</td>
					<td colspan="3">
						<select id="isHot" name="isHot" style="width:100px;" onchange="changeHot();">
							<option value="">--请选择--</option>
							<option value="1" <c:if test="${bean.isHot==1}">selected</c:if>>是</option>
							<option <c:if test="${bean.isHot==0}">selected</c:if> value="0">否</option>
						</select>
						&nbsp;排序
						<input style="width:120px;" type="text" name="hotOrder" id="hotOrder" value="<c:out value='${bean.hotOrder}'/>"<c:if test="${bean.isHot=='0'}">readonly="readonly"</c:if> maxlength="50" />
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						需付运费
						<span>*</span>
					</td>
					<td colspan="3">
						<select id="isShipping" name="isShipping" style="width:100px;" onchange="changeShip();">
							<option value="">--请选择--</option>
							<option value="1" <c:if test="${bean.isShipping=='1'}">selected</c:if>>是</option>
							<option value="0" <c:if test="${bean.isShipping=='0'}">selected</c:if>>否</option>
						</select>
						&nbsp;运费
						<input style="width:120px;" type="text" name="shippingFee" id="shippingFee" value="<fmt:formatNumber value='${bean.shippingFee}' pattern='0.##' type='number'/>" <c:if test="${bean.isShipping=='0'}">readonly="readonly"</c:if> maxlength="100" />
						元
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">运费说明</td>
					<td colspan="3">
						<textarea style="width:740px;height:50px;" name="shippingMemo" id="shippingMemo" onclick="myArea('shippingMemo',1000);" onkeyup="myArea('shippingMemo',1000);" onkeydown="myArea('shippingMemo',1000);" onchange="myArea('shippingMemo',1000);"><c:out value='${bean.shippingMemo }'/></textarea>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						自动捐赠
						<span>*</span>
					</td>
					<td colspan="3">
						<select id="isDonation" name="isDonation" style="width:100px;" onchange="changeDonation();">
							<option value="">--请选择--</option>
							<option value="1" <c:if test="${bean.isDonation=='1'}">selected</c:if>>是</option>
							<option value="0" <c:if test="${bean.isDonation=='0'}">selected</c:if>>否</option>
						</select>
						&nbsp;捐赠
						<input style="width:120px;" type="text" name="donationFee" id="donationFee" value="<fmt:formatNumber value='${bean.donationFee}' pattern='0.##' type='number'/>" <c:if test="${bean.isDonation=='0'}">readonly="readonly"</c:if> maxlength="100" />
						元
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">捐赠说明</td>
					<td colspan="3">
						<textarea style="width:740px;height:50px;" name="donationMemo" id="donationMemo" onclick="myArea('donationMemo',1000);" onkeyup="myArea('donationMemo',1000);" onkeydown="myArea('donationMemo',1000);" onchange="myArea('donationMemo',1000);"><c:out value='${bean.donationMemo }'/></textarea>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">备注说明</td>
					<td colspan="3">
						<textarea style="width:740px;height:60px;" id="memo" name="memo" onclick="myArea('memo',1000);" onkeyup="myArea('memo',1000);" onkeydown="myArea('memo',1000);" onchange="myArea('memo',1000);"><c:out value='${bean.memo }'/></textarea>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area" style="width:80px;">
						附件
					</td>
					<td colspan="3">
						<input id="relIds" name="relIds" type="hidden" value="" />
						<iframe class="frm_file" marginheight="0" frameborder="0" src="<%=basePath %>fileList.action?name=TbCommodity&ele=relIds&id=${bean.commId}">
						</iframe>
					</td>
				</tr>
			</table>
			<div class="lyt_submit" style="width: 830px; text-align: right;">
				<input type="submit" value="提交" />
				<input type="reset" value="重置" />
			</div>
		</form>
	</div>
</body>
</html>
