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
<script type="text/javascript">
	function changeStyle(){
		if($("#commStyle").val()=='2'){
			$("#id_TR_Shipping").hide();
		}
		else{
			$("#id_TR_Shipping").show();
		}
	}
	
	function changeHot(){
		if($('#isHot').val()=='1'){
			$('#hotOrder').prop('readonly',false).css('background-color','transparent').focus();
		}
		else{
			$('#hotOrder').prop('readonly',true).css('background-color','#E5E5E5');
		}
	}
	function changeShip(){
		if($('#isShipping').val()=='1'){
			$('#shippingFee').prop('readonly',false).css('background-color','transparent').focus();
			$('#shippingMemo').prop('readonly',false).css('background-color','transparent');
		}
		else{
			$('#shippingFee').prop('readonly',true).css('background-color','#E5E5E5');
			$('#shippingMemo').prop('readonly',true).css('background-color','#E5E5E5');
		}
	}
	function changeDonation(){
		if($('#isDonation').val()=='1'){
			$('#donationFee').prop('readonly',false).css('background-color','transparent').focus();
			$('#donationMemo').prop('readonly',false).css('background-color','transparent');
		}
		else{
			$('#donationFee').prop('readonly',true).css('background-color','#E5E5E5');
			$('#donationMemo').prop('readonly',true).css('background-color','#E5E5E5');
		}
	}
	
	//商品参数
	//新增商品参数
	function paramAdd() {
		$('#paramTable tbody').append('<tr>'
								+ '<td class="lyt_view_note" style="width:100px;"><span id="paramIndex">'
								+ ($('#paramTable tr').length + 1)
								+ '</span>）参数名称\n<span>*</span></td>'
								+ '<td style="210px"><input style="width:200px" name="paramName" type="text" maxlength="100" /></td>'
								+ '<td class="lyt_view_note" style="width:62px;">参数值 <span>*</span></td>'
								+ '<td style="width:110px"><input style="width:100px" name="paramValue" type="text" maxlength="50" /></td>'
								+ '<td class="lyt_view_note" style="width:62px;">备注信息</td>'
								+ '<td style="width:220px"><input style="width:210px" name="paramMemo" type="text" maxlength="1000" /></td>'
								+ '<td><a href="javascript:;" onclick="paramRemove(this);">移除</a></td>'
								+ '</tr>');
	}
	
	//移除商品参数
	function paramRemove(obj) {
		$(obj).parent().parent().remove();
		$('span[id="paramIndex"]').each(function(i) {
			$(this).text(i + 1);
		});
	}

	//商品图片
	//新增商品图片
	function picAdd() {
		$('#picTable tbody').append('<tr>'
								+ '<td class="lyt_view_note" style="width:100px;"><span id="picIndex">'
								+ ($('#picTable tr').length + 1)
								+ '</span>）选择图片 <span>*</span></td>'
								+ '<td style="width:210px;"><input style="width:200px;" name="picPath" type="file" /></td>'
								+ '<td class="lyt_view_note" style="width:62px;">图片参数 <span>*</span></td>'
								+ '<td style="width:110px;"><input style="width:100px" name="picOrder" type="text" maxlength="50" /></td>'
								+ '<td class="lyt_view_note" style="width:62px;">图片描述</td>'
								+ '<td style="width:220px;"><input style="width:210px" name="picMemo" type="text" maxlength="100" /></td>'
								+ '<td><a href="javascript:;" onclick="picRemove(this);">移除</a></td>'
								+ '</tr>');
	}
	//移除商品图片
	function picRemove(obj) {
		$(obj).parent().parent().remove();
		$('span[id="picIndex"]').each(function(i) {
			$(this).text(i + 1);
		});
	}
	
	function typeAdd(){
		$('#typeTable tbody').append('<tr>'
								+ '<td class="lyt_view_note" style="width:100px;"><span id="typeIndex">'
								+ ($('#typeTable tr').length + 1)
								+ '</span>）型号名称 <span>*</span></td>'
								+ '<td style="width:100px;"><input style="width:100px" name="typeName" type="text" maxlength="100" /></td>'
								+ '<td class="lyt_view_note" style="width:40px;">限量 <span>*</span></td>'
								+ '<td style="width:45px"><select name="typeIsLimitNum" style="width:45px;"><option value="1" selected>是</option><option value="0">否</option></select></td>'
								+ '<td class="lyt_view_note" style="width:40px;">库存 <span>*</span></td>'
								+ '<td style="width:40px"><input style="width:40px" name="typeStockNum" type="text" maxlength="50" /></td>'
								+ '<td class="lyt_view_note" style="width:40px;">单位</td>'
								+ '<td style="width:40px"><input style="width:40px" name="typeUnitName" value="件" type="text" /></td>'
								+ '<td class="lyt_view_note" style="width:50px;">成本价 <span>*</span></td>'
							    + '<td style="width:60px"><input style="width:40px;" type="text" name="typeCostFee" />&nbsp;&nbsp;元</td>'
								+ '<td class="lyt_view_note" style="width:40px;">售价 <span>*</span></td>'
								+ '<td style="width:95px"><input style="width:40px;" type="text" name="typeSaleFee" />&nbsp;&nbsp;元</td>'
								+ '<td><a href="javascript:;" onclick="typeRemove(this);">移除</a></td>'
								+ '</tr>');
	}
	function typeRemove(obj){
		$(obj).parent().parent().remove();
		$('span[id="typeIndex"]').each(function(i) {
			$(this).text(i + 1);
		});
	}
	
	function checkForm(form){
	// 名称
	if($("#commName").val()==""){
		MyMsg.alertFn('请输入商品名称！',function(){
			$("#commName").focus();
		});
		return false;
	}
	// 商品类别
	if($("#commType").val()==""){
		MyMsg.alert('请选择商品类别！');
		return false;
	}
	// 是否热门
	if($("#isHot").val() == ""){
		MyMsg.alert('请选择商品是否热门！');
		return false;
	}
	// 是否上架
	if($("#isShelves").val() == ""){
		MyMsg.alert('请选择商品是否上架！');
		return false;
	}
	// 商品类型
	if($("#commStyle").val()==""){
		MyMsg.alert('请选择商品类型！');
		return false;
	}
	
	if($("#commStyle").val()!="2")
	{
		// 是否运费
		if($("#isShipping").val() == ""){
			MyMsg.alert('请选择商品是否需要运费！');
			return false;
		}
	}
	
	// 是否捐赠
	if($("#isDonation").val() == ""){
		MyMsg.alert('请选择商品是否自动捐赠！');
		return false;
	}
	
	// 商品简介
	if($("#commIntro").val().length > 1000){
		MyMsg.alertFn('商品简介必须在1000字以内！',function(){
			$("#commIntro").focus();
		});
		return false;
	}
	
	<c:if test="${empty bean.commId}">
		//商品图片
		//选择图片
		var obj,msg = '';
		$('input[name="picPath"]').each(function(){
			obj = $(this);
			var val = obj.val();
			if(val == ""){
				msg = "请选择需要上传的商品图片，或者移除不需要的商品图片编辑框！";
			return false;}
			else{
				var valType = val.split(".")[val.split(".").length-1].toLowerCase();
				if(valType!="gif" && valType!="jpg" && valType!="jpeg" && valType!="png" && valType!="bmp"){
					msg = "商品图片只允许jpg,png,bmp,jpeg,gif格式!";
				return false;}}
		});
		
		if(msg!=''){
			MyMsg.alertFn(msg,function(){obj.focus();});
			return false;
		}
	
		//图片排序	
		$('input[name="picOrder"]').each(function(){
			obj = $(this);
			if(obj.val() == ""){
				msg = "请输入商品图片排序值，或者移除不需要的商品图片编辑框！";
				return false;
			}
		});
		if(msg!=''){
			MyMsg.alertFn(msg,function(){obj.focus();});
			return false;
		}

		//商品参数
		//参数名称
		$('input[name="paramName"]').each(function(){
			obj = $(this);
			if(obj.val() == ""){
				msg = "请输入商品参数名称，或者移除不需要的商品参数编辑框！";
				return false;}
		});
		if(msg!=''){
			MyMsg.alertFn(msg,function(){obj.focus();});
			return false;
		}
		
		//参数值
		$('input[name="paramValue"]').each(function(){
			obj = $(this);
			if(obj.val() == ""){
				msg = "请输入商品参数值，或者移除不需要的商品参数编辑框！";
				return false;}
		});
		if(msg!=''){
			MyMsg.alertFn(msg,function(){obj.focus();});
			return false;
		}
		
		//商品型号
		//型号名称
		$('input[name="typeName"]').each(function(){
			obj = $(this);
			if(obj.val() == ""){
				msg = "请输入商品型号名称，或者移除不需要的商品型号编辑框！";
				return false;}
		});
		if(msg!=''){
			MyMsg.alertFn(msg,function(){obj.focus();});
			return false;
		}
		
		//是否限量
		$('select[name="typeIsLimitNum"]').each(function(){
			obj = $(this);
			if(obj.val() == ""){
				msg = "请选择商品型号是否限量，或者移除不需要的商品型号编辑框！";
				return false;}
			else{
				if(obj.val() == "1")
				{
					var objStockNum = obj.parent().parent().find('input[name="typeStockNum"]').first();
					obj = objStockNum;
					if(objStockNum.val() == ""){
						msg = "请输入商品型号库存数量，或者移除不需要的商品型号编辑框！";
						return false;
					}
				}
			}
		});
		if(msg!=''){
			MyMsg.alertFn(msg,function(){obj.focus();});
			return false;
		}
		
		//成本价
		$('input[name="typeCostFee"]').each(function(){
			obj = $(this);
			if(obj.val() == "" || !/^\d+(.\d{1,2})?$/.test(obj.val())){
				msg = "请输入商品型号成本价（最多两位小数的正整数）！";
				return false;}
		});
		if(msg!=''){
			MyMsg.alertFn(msg,function(){obj.focus();});
			return false;
		}
		
		//售价
		$('input[name="typeSaleFee"]').each(function(){
			obj = $(this);
			if(obj.val() == "" || !/^\d+(.\d{1,2})?$/.test(obj.val())){
				msg = "请输入商品型号售价（最多两位小数的正整数）！";
				return false;}
		});
		if(msg!=''){
			MyMsg.alertFn(msg,function(){obj.focus();});
			return false;
		}
	</c:if>
	
	// 其他
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
		<form id="formId" action="<%=basePath%>saveOrUpdate.action" method="post" onSubmit="return checkForm(this)" enctype="multipart/form-data">
			<input id="commId" name="commId" type="hidden" value="<c:out value='${bean.commId}' />" />
			<table border="0" cellspacing="0" cellpadding="0" class="lyt_view">
				<tr>
					<td class="lyt_view_note">
						商品名称 <span>*</span>
					</td>
					<td colspan="3">
						<input style="width:320px" type="text" name="commName" id="commName" value="<c:out value='${bean.commName}'/>" maxlength="100" />
					</td>
					<td class="lyt_view_note" style="width:80px">
						商品类别 <span>*</span>
					</td>
					<td colspan="3">
						<select id="commType" name="commType" style="width:300px">
							<c:forEach items="${ctList}" var="temp" varStatus="status">
								<option value="${temp.typeId}"
									<c:if test="${bean.commType==temp.typeId}">selected="selected"</c:if>>${temp.typeName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width:100px;">
						是否热门 <span>*</span>
					</td>
					<td style="width:90px">
						<select id="isHot" name="isHot" style="width:80px;" onchange="changeHot();">
							<option value="1" <c:if test="${bean.isHot==1}">selected</c:if>>是</option>
							<option value="0" <c:if test="${bean.isHot==0}">selected</c:if>>否</option>
						</select>
					</td>
					<td class="lyt_view_note" style="width:80px">排序 </td>
					<td style="width:90px">
						<input style="width:80px;" type="text" name="hotOrder" id="hotOrder" value="<c:out value='${bean.hotOrder}'/>" maxlength="50"
						<c:if test="${bean.isHot=='0'}">readonly="readonly"</c:if>/>
					</td>
					<td class="lyt_view_note" style="width:80px">
						是否上架 <span>*</span>
					</td>
					<td style="width:90px">
						<select id="isShelves" name="isShelves" style="width:80px;">
							<option value="1" <c:if test="${bean.isShelves==1}">selected</c:if>>是</option>
							<option value="0" <c:if test="${bean.isShelves==0}">selected</c:if>>否</option>
						</select>
					</td>
					<td class="lyt_view_note" style="width:80px">
						商品类型 <span>*</span>
					</td>
					<td style="width:100px">
						<select id="commStyle" name="commStyle" onchange="changeStyle();" style="width:85px">
							<option value="1" <c:if test="${bean.commStyle=='1'}">selected</c:if>>实物商品</option>
							<option value="2" <c:if test="${bean.commStyle!='1'}">selected</c:if>>虚拟商品</option>
						</select>
					</td>
				</tr>
				<tr id="id_TR_Shipping" <c:if test='${bean.commStyle!="1"}'>style="display:none"</c:if>>
					<td class="lyt_view_note" style="wdith:100px">
						需付运费 <span>*</span>
					</td>
					<td>
						<select id="isShipping" name="isShipping" style="width:80px;" onchange="changeShip();">
							<option value="1" <c:if test="${bean.isShipping=='1'}">selected</c:if>>是</option>
							<option value="0" <c:if test="${bean.isShipping=='0'}">selected</c:if>>否</option>
						</select>
					</td>
					<td class="lyt_view_note" style="width:80px">运费 </td>
					<td>
						<input style="width:80px;" type="text" name="shippingFee" id="shippingFee" value="<fmt:formatNumber value='${bean.shippingFee}' pattern='0.##' type='number'/>"
						<c:if test="${bean.isShipping=='0'}">readonly="readonly"</c:if> />&nbsp;&nbsp;元
					</td>
					<td class="lyt_view_area">
						运费说明
					</td>
					<td colspan="3">
						<input style="width:300px" name="shippingMemo" type="text" maxlength="100" value="<c:out value="${bean.shippingMemo}" />"/>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" >
						自动捐赠 <span>*</span>
					</td>
					<td style="width:20px">
						<select id="isDonation" name="isDonation" style="width:80px;" onchange="changeDonation();">
							<option value="1" <c:if test="${bean.isDonation=='1'}">selected</c:if>>是</option>
							<option value="0" <c:if test="${bean.isDonation=='0'}">selected</c:if>>否</option>
						</select>
					</td>
					<td class="lyt_view_area">
						捐赠
					</td>
					<td>
						<input style="width:80px;" type="text" name="donationFee" id="donationFee" value="<fmt:formatNumber value='${bean.donationFee}' pattern='0.##' type='number'/>"
						<c:if test="${bean.isDonation=='0'}">readonly="readonly"</c:if> />&nbsp;&nbsp;元
					</td>
					<td class="lyt_view_area">捐赠说明</td>
					<td colspan="3">
						<input style="width:300px" name="donationMemo" type="text" maxlength="100" value="<c:out value="${bean.donationMemo}" />"/>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">
						项目关键字标签
					</td>
					<td colspan="7">
						<input style="width:660px" id="searchKey" name="searchKey" type="text" value='<c:out value="${bean.searchKey}" />' maxlength="1000" /> 
						<span style="color:#999;">（以逗号间隔）</span>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">
						商品简介
					</td>
					<td colspan="7">
						<textarea style="width:760px; height:50px;" name="commIntro" id="commIntro" onclick="myArea('commIntro',1000);" onkeyup="myArea('commIntro',1000);" onkeydown="myArea('commIntro',1000);" onchange="myArea('commIntro',1000);"><c:out value='${bean.commIntro}' /></textarea>
					</td>
				</tr>
			</table>

			<c:if test="${empty bean.commId}">
				<div class="divTitle">
					<div style="float:left;padding-left:8px;overflow:hidden;">
						商品图片<span style="font-weight:normal;">（注意：项目图片不少于1个）</span>
					</div>
					<div style="float:right;padding-right:12px;overflow:hidden;">
						<a href="javascript:;" onclick="picAdd();">添加图片</a>
					</div>
				</div>
				<table id="picTable" class="lyt_view">
					<tr>
						<td class="lyt_view_note" style="width:100px;">
							<span id="picIndex">1</span>）选择图片 <span>*</span>
						</td>
						<td style="width:210px;">
							<input style="width:200px;" name="picPath" type="file" />
						</td>
						<td class="lyt_view_note" style="width:62px;">
							图片参数 <span>*</span>
						</td>
						<td style="width:110px;">
							<input style="width:100px" name="picOrder" type="text" maxlength="50" />
						</td>
						<td class="lyt_view_note" style="width:62px;">图片描述 </td>
						<td style="width:220px;">
							<input style="width:210px" name="picMemo" type="text" maxlength="100" />
						</td>
						<td></td>
					</tr>
				</table>
				
				<div class="divTitle">
					<div style="float:left;padding-left:8px;overflow:hidden;">商品参数</div>
					<div style="float:right;padding-right:12px;overflow:hidden;">
						<a href="javascript:;" onclick="paramAdd();">添加参数</a>
					</div>
				</div>
				<table id="paramTable" class="lyt_view">
					<tr>
						<td class="lyt_view_note" style="width:100px;">
							<span id="paramIndex">1</span>）参数名称 <span>*</span>
						</td>
						<td style="width:210px">
							<input style="width:200px" name="paramName" type="text" maxlength="100" />
						</td>
						<td class="lyt_view_note" style="width:62px;">
							参数值 <span>*</span>
						</td>
						<td style="width:110px">
							<input style="width:100px" name="paramValue" type="text" maxlength="50" />
						</td>
						<td class="lyt_view_note" style="width:62px;">备注信息</td>
						<td style="width:220px">
							<input style="width:210px" name="paramMemo" type="text" maxlength="1000" />
						</td>
						<td>
							<a href="javascript:;" onclick="paramRemove(this);">移除</a>
						</td>
					</tr>
				</table>
				
				<div class="divTitle">
					<div style="float:left;padding-left:8px;overflow:hidden;">商品型号</div>
					<div style="float:right;padding-right:12px;overflow:hidden;">
						<a href="javascript:;" onclick="typeAdd();">添加型号</a>
					</div>
				</div>
				<table id="typeTable" class="lyt_view" >
					<tr>
						<td class="lyt_view_note" style="width:100px;">
							<span id="typeIndex">1</span>）型号名称 <span>*</span>
						</td>
						<td style="width:100px;">
							<input style="width:100px" name="typeName" type="text" maxlength="100" />
						</td>
						<td class="lyt_view_note" style="width:40px;">
							限量 <span>*</span>
						</td>
						<td style="width:45px">
							<select name="typeIsLimitNum" style="width:45px;">
								<option value="1" selected>是</option>
								<option value="0">否</option>
							</select>
						</td>
						<td class="lyt_view_note" style="width:40px;">
							库存 <span>*</span>
						</td>
						<td style="width:40px">
							<input style="width:40px" name="typeStockNum" type="text" maxlength="50" />
						</td>
						<td class="lyt_view_note" style="width:40px;">单位</td>
						<td style="width:40px">
							<input style="width:40px" name="typeUnitName" value="件" type="text" />
						</td>
						<td class="lyt_view_note" style="width:50px;">
							成本价 <span>*</span>
						</td>
						<td style="width:60px">
							<input style="width:40px;" type="text" name="typeCostFee" />&nbsp;&nbsp;元
						</td>
						<td class="lyt_view_note" style="width:40px;">
							售价 <span>*</span>
						</td>
						<td style="width:95px">
							<input style="width:40px;" type="text" name="typeSaleFee" />&nbsp;&nbsp;元
						</td>
						<td>
							<a href="javascript:;" onclick="typeRemove(this);">移除</a>
						</td>
					</tr>
				</table>
			</c:if>

			<div
				style="width:875px;height:30px;line-height:30px;margin:5px 0px 0px 5px;font-weight:bold;background-color:#d0ddf1;overflow:hidden;">
				<div style="float:left;padding-left:8px;overflow:hidden;">商品详情</div>
				<div style="float:right;padding-right:12px;overflow:hidden;">
					<a href="javascript:;" onclick="detailAdd();">添加标签</a>
				</div>
			</div>
			<table width="875" style="margin-left:5px;margin-top:5px;" border="0" cellpadding="0" cellspacing="0">
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
						<td class="lyt_view_note" style="width:100px;">标签名称 <span>*</span>
						</td>
						<td style="width:210px;"><input style="width:200px"
							name="detailTitle" type="text"
							value="<c:out value="${bean.detailTitle1}"/>" maxlength="100"
							onchange="detailTitleChange(this);" />
						</td>
						<td><select name="detailPublish" style="width:90px;">
								<option value="1"
									<c:if test="${bean.detailPublish1=='1'}">selected</c:if>>对外显示</option>
								<option value="0"
									<c:if test="${bean.detailPublish1=='0'}">selected</c:if>>不对外显示</option>
						</select> &nbsp; <a href="javascript:;" onclick="detailLeft(this);">左移</a>
							<a href="javascript:;" onclick="detailRight(this);">右移</a> <a
							href="javascript:;" onclick="detailRemove(this);">移除</a>
						</td>
					</tr>
					<tr>
						<td colspan="3"><textarea id="detailContent1"
								name="detailContent"
								style="width:875px;height:300px;visibility:hidden;">
								<c:out value="${bean.detailContent1}" />
							</textarea>
						</td>
					</tr>
				</table>
				<c:if test="${not empty bean.detailTitle2}">
					<table class="lyt_view" style="display:none;">
						<tr>
							<td class="lyt_view_note" style="width:100px;">标签名称 <span>*</span>
							</td>
							<td style="width:210px;"><input style="width:200px"
								name="detailTitle" type="text"
								value="<c:out value="${bean.detailTitle2}"/>" maxlength="100"
								onchange="detailTitleChange(this);" />
							</td>
							<td><select name="detailPublish" style="width:90px;">
									<option value="1"
										<c:if test="${bean.detailPublish2=='1'}">selected</c:if>>对外显示</option>
									<option value="0"
										<c:if test="${bean.detailPublish2=='0'}">selected</c:if>>不对外显示</option>
							</select> &nbsp; <a href="javascript:;" onclick="detailLeft(this);">左移</a>
								<a href="javascript:;" onclick="detailRight(this);">右移</a> <a
								href="javascript:;" onclick="detailRemove(this);">移除</a>
							</td>
						</tr>
						<tr>
							<td colspan="3"><textarea id="detailContent2"
									name="detailContent"
									style="width:875px;height:300px;visibility:hidden;">
									<c:out value="${bean.detailContent2}" />
								</textarea>
							</td>
						</tr>
					</table>
				</c:if>
				<c:if test="${not empty bean.detailTitle3}">
					<table class="lyt_view" style="display:none;">
						<tr>
							<td class="lyt_view_note" style="width:100px;">标签名称 <span>*</span>
							</td>
							<td style="width:210px;"><input style="width:200px"
								name="detailTitle" type="text"
								value="<c:out value="${bean.detailTitle3}"/>" maxlength="100"
								onchange="detailTitleChange(this);" />
							</td>
							<td><select name="detailPublish" style="width:90px;">
									<option value="1"
										<c:if test="${bean.detailPublish3=='1'}">selected</c:if>>对外显示</option>
									<option value="0"
										<c:if test="${bean.detailPublish3=='0'}">selected</c:if>>不对外显示</option>
							</select> &nbsp; <a href="javascript:;" onclick="detailLeft(this);">左移</a>
								<a href="javascript:;" onclick="detailRight(this);">右移</a> <a
								href="javascript:;" onclick="detailRemove(this);">移除</a>
							</td>
						</tr>
						<tr>
							<td colspan="3"><textarea id="detailContent3"
									name="detailContent"
									style="width:875px;height:300px;visibility:hidden;">
									<c:out value="${bean.detailContent3}" />
								</textarea>
							</td>
						</tr>
					</table>
				</c:if>
				<c:if test="${not empty bean.detailTitle4}">
					<table class="lyt_view" style="display:none;">
						<tr>
							<td class="lyt_view_note" style="width:100px;">标签名称 <span>*</span>
							</td>
							<td style="width:210px;"><input style="width:200px"
								name="detailTitle" type="text"
								value="<c:out value="${bean.detailTitle4}"/>" maxlength="100"
								onchange="detailTitleChange(this);" />
							</td>
							<td><select name="detailPublish" style="width:90px;">
									<option value="1"
										<c:if test="${bean.detailPublish4=='1'}">selected</c:if>>对外显示</option>
									<option value="0"
										<c:if test="${bean.detailPublish4=='0'}">selected</c:if>>不对外显示</option>
							</select> &nbsp; <a href="javascript:;" onclick="detailLeft(this);">左移</a>
								<a href="javascript:;" onclick="detailRight(this);">右移</a> <a
								href="javascript:;" onclick="detailRemove(this);">移除</a>
							</td>
						</tr>
						<tr>
							<td colspan="3"><textarea id="detailContent4"
									name="detailContent"
									style="width:875px;height:300px;visibility:hidden;">
									<c:out value="${bean.detailContent4}" />
								</textarea>
							</td>
						</tr>
					</table>
				</c:if>
				<c:if test="${not empty bean.detailTitle5}">
					<table class="lyt_view" style="display:none;">
						<tr>
							<td class="lyt_view_note" style="width:100px;">标签名称 <span>*</span>
							</td>
							<td style="width:210px;"><input style="width:200px"
								name="detailTitle" type="text"
								value="<c:out value="${bean.detailTitle5}"/>" maxlength="100"
								onchange="detailTitleChange(this);" />
							</td>
							<td><select name="detailPublish" style="width:90px;">
									<option value="1"
										<c:if test="${bean.detailPublish5=='1'}">selected</c:if>>对外显示</option>
									<option value="0"
										<c:if test="${bean.detailPublish5=='0'}">selected</c:if>>不对外显示</option>
							</select> &nbsp; <a href="javascript:;" onclick="detailLeft(this);">左移</a>
								<a href="javascript:;" onclick="detailRight(this);">右移</a> <a
								href="javascript:;" onclick="detailRemove(this);">移除</a>
							</td>
						</tr>
						<tr>
							<td colspan="3"><textarea id="detailContent5"
									name="detailContent"
									style="width:875px;height:300px;visibility:hidden;">
									<c:out value="${bean.detailContent5}" />
								</textarea>
							</td>
						</tr>
					</table>
				</c:if>
				<c:if test="${not empty bean.detailTitle6}">
					<table class="lyt_view" style="display:none;">
						<tr>
							<td class="lyt_view_note" style="width:100px;">标签名称 <span>*</span>
							</td>
							<td style="width:210px;"><input style="width:200px"
								name="detailTitle" type="text"
								value="<c:out value="${bean.detailTitle6}"/>" maxlength="100"
								onchange="detailTitleChange(this);" />
							</td>
							<td><select name="detailPublish" style="width:90px;">
									<option value="1"
										<c:if test="${bean.detailPublish6=='1'}">selected</c:if>>对外显示</option>
									<option value="0"
										<c:if test="${bean.detailPublish6=='0'}">selected</c:if>>不对外显示</option>
							</select> &nbsp; <a href="javascript:;" onclick="detailLeft(this);">左移</a>
								<a href="javascript:;" onclick="detailRight(this);">右移</a> <a
								href="javascript:;" onclick="detailRemove(this);">移除</a>
							</td>
						</tr>
						<tr>
							<td colspan="3"><textarea id="detailContent6"
									name="detailContent"
									style="width:875px;height:300px;visibility:hidden;">
									<c:out value="${bean.detailContent6}" />
								</textarea>
							</td>
						</tr>
					</table>
				</c:if>
				<c:if test="${not empty bean.detailTitle7}">
					<table class="lyt_view" style="display:none;">
						<tr>
							<td class="lyt_view_note" style="width:100px;">标签名称 <span>*</span>
							</td>
							<td style="width:210px;"><input style="width:200px"
								name="detailTitle" type="text"
								value="<c:out value="${bean.detailTitle7}"/>" maxlength="100"
								onchange="detailTitleChange(this);" />
							</td>
							<td><select name="detailPublish" style="width:90px;">
									<option value="1"
										<c:if test="${bean.detailPublish7=='1'}">selected</c:if>>对外显示</option>
									<option value="0"
										<c:if test="${bean.detailPublish7=='0'}">selected</c:if>>不对外显示</option>
							</select> &nbsp; <a href="javascript:;" onclick="detailLeft(this);">左移</a>
								<a href="javascript:;" onclick="detailRight(this);">右移</a> <a
								href="javascript:;" onclick="detailRemove(this);">移除</a>
							</td>
						</tr>
						<tr>
							<td colspan="3"><textarea id="detailContent7"
									name="detailContent"
									style="width:875px;height:300px;visibility:hidden;">
									<c:out value="${bean.detailContent7}" />
								</textarea>
							</td>
						</tr>
					</table>
				</c:if>
				<c:if test="${not empty bean.detailTitle8}">
					<table class="lyt_view" style="display:none;">
						<tr>
							<td class="lyt_view_note" style="width:100px;">标签名称 <span>*</span>
							</td>
							<td style="width:210px;"><input style="width:200px"
								name="detailTitle" type="text"
								value="<c:out value="${bean.detailTitle8}"/>" maxlength="100"
								onchange="detailTitleChange(this);" />
							</td>
							<td><select name="detailPublish" style="width:90px;">
									<option value="1"
										<c:if test="${bean.detailPublish8=='1'}">selected</c:if>>对外显示</option>
									<option value="0"
										<c:if test="${bean.detailPublish8=='0'}">selected</c:if>>不对外显示</option>
							</select> &nbsp; <a href="javascript:;" onclick="detailLeft(this);">左移</a>
								<a href="javascript:;" onclick="detailRight(this);">右移</a> <a
								href="javascript:;" onclick="detailRemove(this);">移除</a>
							</td>
						</tr>
						<tr>
							<td colspan="3"><textarea id="detailContent8"
									name="detailContent"
									style="width:875px;height:300px;visibility:hidden;">
									<c:out value="${bean.detailContent8}" />
								</textarea>
							</td>
						</tr>
					</table>
				</c:if>
				<c:if test="${not empty bean.detailTitle9}">
					<table class="lyt_view" style="display:none;">
						<tr>
							<td class="lyt_view_note" style="width:100px;">标签名称 <span>*</span>
							</td>
							<td style="width:210px;"><input style="width:200px"
								name="detailTitle" type="text"
								value="<c:out value="${bean.detailTitle9}"/>" maxlength="100"
								onchange="detailTitleChange(this);" />
							</td>
							<td><select name="detailPublish" style="width:90px;">
									<option value="1"
										<c:if test="${bean.detailPublish9=='1'}">selected</c:if>>对外显示</option>
									<option value="0"
										<c:if test="${bean.detailPublish9=='0'}">selected</c:if>>不对外显示</option>
							</select> &nbsp; <a href="javascript:;" onclick="detailLeft(this);">左移</a>
								<a href="javascript:;" onclick="detailRight(this);">右移</a> <a
								href="javascript:;" onclick="detailRemove(this);">移除</a>
							</td>
						</tr>
						<tr>
							<td colspan="3"><textarea id="detailContent9"
									name="detailContent"
									style="width:875px;height:300px;visibility:hidden;">
									<c:out value="${bean.detailContent9}" />
								</textarea>
							</td>
						</tr>
					</table>
				</c:if>
				<c:if test="${not empty bean.detailTitle10}">
					<table class="lyt_view" style="display:none;">
						<tr>
							<td class="lyt_view_note" style="width:100px;">标签名称 <span>*</span>
							</td>
							<td style="width:210px;"><input style="width:200px"
								name="detailTitle" type="text"
								value="<c:out value="${bean.detailTitle10}"/>" maxlength="100"
								onchange="detailTitleChange(this);" />
							</td>
							<td><select name="detailPublish" style="width:90px;">
									<option value="1"
										<c:if test="${bean.detailPublish10=='1'}">selected</c:if>>对外显示</option>
									<option value="0"
										<c:if test="${bean.detailPublish10=='0'}">selected</c:if>>不对外显示</option>
							</select> &nbsp; <a href="javascript:;" onclick="detailLeft(this);">左移</a>
								<a href="javascript:;" onclick="detailRight(this);">右移</a> <a
								href="javascript:;" onclick="detailRemove(this);">移除</a>
							</td>
						</tr>
						<tr>
							<td colspan="3"><textarea id="detailContent10"
									name="detailContent"
									style="width:875px;height:300px;visibility:hidden;">
									<c:out value="${bean.detailContent10}" />
								</textarea>
							</td>
						</tr>
					</table>
				</c:if>
			</div>

			<script>
			  	KindEditor.ready(function(K) {
			  		$('textarea[name="detailContent"]').each(function(){
						K.create('#'+$(this).attr('id'), {
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
							afterBlur: function(){ this.sync(); }
						});
			  		});
				});
				function detailOn(obj){
					$('#detailTitleUl').children().css({
						'color':'',
						'background':'url(<%=basePath%>UI/images/nav_point.png) no-repeat top left'
					});
					$('#detailContentDiv').children().hide();
					var titleObj = $(obj);
					var contentObj = $('#detailContentDiv table:eq('+titleObj.prevAll().length+')');
					titleObj.css({
						'color':'#fff',
						'background':'url(<%=basePath%>UI/images/nav_point_on.png) no-repeat top left'
					});
					contentObj.show();
					contentObj.find('input[name="detailTitle"]').select();
				}
				var detailIndex = 10;
				
				function detailAdd(){
					detailIndex += 1;
					$('#detailTitleUl').append('<li onclick="detailOn(this);" style="line-height:22px;text-align:center;cursor:pointer;">&nbsp;</li>');
					$('#detailContentDiv').append('<table class="lyt_view" style="display:none;">' + 
						'<tr>' + 
							'<td class="lyt_view_note" style="width:100px;">标签名称\n<span>*</span></td>' + 
							'<td style="width:210px;"><input style="width:200px" name="detailTitle" type="text" maxlength="100" onchange="detailTitleChange(this);" /></td>' + 
							'<td>' + 
								'<select name="detailPublish" style="width:90px;"><option value="1">对外显示</option><option value="0">不对外显示</option></select>\n&nbsp;\n' + 
								'<a href="javascript:;" onclick="detailLeft(this);">左移</a>\n' + 
								'<a href="javascript:;" onclick="detailRight(this);">右移</a>\n' + 
								'<a href="javascript:;" onclick="detailRemove(this);">移除</a>\n' + 
							'</td>' + 
						'</tr>' + 
						'<tr>' + 
							'<td colspan="3"><textarea id="detailContent'+detailIndex+'" name="detailContent" style="width:875px;height:300px;visibility:hidden;"></textarea></td>' + 
						'</tr>' + 
					'</table>');
					$('#detailTitleUl li:last').click();
					KindEditor.create('#detailContent'+detailIndex, {
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
						afterBlur: function(){ this.sync(); }
					});
				}
				function detailTitleChange(obj){
					var thisObj = $(obj);
					if(thisObj.val()!=''){
						$('#detailTitleUl li:eq('+thisObj.parents('table').prevAll().length+')').text(thisObj.val());
					}else{
						$('#detailTitleUl li:eq('+thisObj.parents('table').prevAll().length+')').html('&nbsp;');
					}
				}
				function detailLeft(obj){
					var contentObj = $(obj).parents('table');
					var index = contentObj.prevAll().length;
					if(index>0){
						var titleObj = $('#detailTitleUl li:eq('+index+')');
						titleObj.after(titleObj.prev());
						contentObj.after(contentObj.prev());
					}
				}
				function detailRight(obj){
					var contentObj = $(obj).parents('table');
					var index = contentObj.prevAll().length;
					if(contentObj.nextAll().length>0){
						var titleObj = $('#detailTitleUl li:eq('+index+')');
						titleObj.before(titleObj.next());
						contentObj.before(contentObj.next());
					}
				}
				function detailRemove(obj){
					var contentObj = $(obj).parents('table');
					var titleObj = $('#detailTitleUl li:eq('+contentObj.prevAll().length+')');
					titleObj.remove();
					contentObj.remove();
					
					detailOn($("#detailTitleUl").find("ul li").first());
				}
			</script>
			
			<div class="divTitle">
				<div style="float:left;padding-left:8px;overflow:hidden;">其他</div>
			</div>

			<table border="0" cellspacing="0" cellpadding="0" class="lyt_view">
				<tr>
					<td class="lyt_view_area">备注说明</td>
					<td colspan="3"><textarea style="width:770px;height:60px;"
							id="memo" name="memo" onclick="myArea('memo',1000);"
							onkeyup="myArea('memo',1000);" onkeydown="myArea('memo',1000);"
							onchange="myArea('memo',1000);"><c:out value='${bean.memo }' /></textarea>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area" style="width:80px;">附件</td>
					<td colspan="3"><input id="relIds" name="relIds" type="hidden"
						value="" /> <iframe class="frm_file" marginheight="0"
							frameborder="0"
							src="<%=basePath %>fileList.action?name=TbCommodity&ele=relIds&id=${bean.commId}">
						</iframe></td>
				</tr>
			</table>

			<div class="lyt_submit" style="width:872px;text-align:right;">
				<input type="submit" value="提交" /> <input type="reset" value="重置" />
			</div>
		</form>
	</div>
</body>
</html>
