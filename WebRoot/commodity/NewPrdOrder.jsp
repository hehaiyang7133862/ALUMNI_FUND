<%@ include file="../common/include.jsp"%>
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
<%@ include file="../common/calendar.jsp"%>
<%@ include file="../common/kindeditor.jsp"%>

<style type="text/css">
.divTitle {
	width: 780px;
	height: 30px;
	line-height: 30px;
	margin: 5px 0px 0px 5px;
	font-weight: bold;
	background-color: #d0ddf1;
	overflow: hidden;
}

.checkbox {
	vertical-align: inherit;
}

.checkbox input {
	width: auto;
	height: auto;
	vertical-align: -2px;
	margin-right: 3px;
}
</style>
</head>
<body>
	<div class="page">
		<%@ include file="../common/navigation.jsp"%>
		<form id="formId" name="formId" action="<%=basePath%>SaveOrUpdatePrdOrder.action" method="post" onsubmit="return checkForm(this);">
			<input type="hidden" name="commId" value="${bean.commId}" /> 
			<input type="hidden" name="orderId" value="${bean.orderId}" />
			<table class="lyt_view">
				<c:if test="${not empty bean.orderId}">
					<tr>
						<td class="lyt_view_note" style="width:80px">订单号</td>
						<td colspan="7"><c:out value="${bean.orderNo}" /></td>
					</tr>
				</c:if>
				<tr>
					<td class="lyt_view_note">商品名称</td>
					<td colspan="7"><c:out value="${bean.commName}" /></td>
				</tr>
				<tr>
					<td class="lyt_view_note" >
						商品型号<span>*</span><br /> 
						<a href="javascript:;" onclick="optionDefine();">自定义</a> 
					</td>
					<td colspan="7">
						<select id="detailId" name="detailId" style="width:265px;" onchange="detailChange();">
							<c:if test="${not empty bean.commDetailId && not empty bean.commDetailName}">
								<optgroup label="当前">
									<option value="${bean.commDetailId}" selected>
										<c:out value="${bean.commDetailName}"/>
									</option>
								</optgroup>
							</c:if>
							<optgroup label="系统">
								<c:forEach items="${optionList}" var="opttemp">
									<option value="${opttemp.detailId}">
										<c:out value="${opttemp.detailName}"/>
									</option>
								</c:forEach>
							</optgroup>
						</select>
						<input type="hidden" id="detailName" name="detailName" value="<c:choose><c:when test="${not empty bean.commDetailName}"><c:out value="${bean.commDetailName}"/></c:when><c:when test="${not empty optionList}"><c:out value="${optionList[0].detailName}"/></c:when></c:choose>" />
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width:80px">
						成本价<span>*</span>
					</td>
					<td style="width:90px">
						<input id="commCostfee" name="commCostfee" type="text" style="width:70px" value="<c:out value='${bean.commCostfee}'/>"/>&nbsp;&nbsp;元
					</td>
					<td class="lyt_view_note" style="width:80px">
						售价<span>*</span>
					</td>
					<td style="width:90px">
						<input id="commSalefee" name="commSalefee" type="text" style="width:70px" value="<c:out value='${bean.commSalefee}'/>"/>&nbsp;&nbsp;元
					</td>
					<td class="lyt_view_note" style="width:80px">
						商品数量<span>*</span>
					</td>
					<td style="width:90px">
						<input id="commNum" name="commNum" type="text" style="width:70px" value="<c:out value='${bean.commNum}'/>"/>&nbsp;&nbsp;件
					</td>
					<td class="lyt_view_note" style="width:80px">
						订单总金额<span>*</span>
					</td>
					<td style="width:90px">
						<input id="orderFee" name="orderFee" type="text" style="width:70px" value="<c:out value='${bean.orderFee}'/>"/>&nbsp;&nbsp;元
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">取货方式</td>
					<td colspan="3">
						<label for="shippingType1" class="checkbox" onclick="shippingTypeChange();" style="width:80px"> 
							<input type="radio" id="shippingType1" name="shippingType" value="1"
							<c:if test="${bean.shippingType=='1'}">checked="checked"</c:if> />买家自提
						</label> 
						&nbsp;&nbsp; 
						<label for="shippingType0" class="checkbox" onclick="shippingTypeChange()" style="width:80px"> 
							<input type="radio" id="shippingType0" name="shippingType" value="0"
							<c:if test="${bean.shippingType!='1'}">checked="checked"</c:if> />物流运输
						</label>
					</td> 
					<td class="lyt_view_note" id="shippingFeeTd0" <c:if test="${bean.shippingType =='1'}">style="display:none"</c:if>>
						运费<span>*</span>
					</td>
					<td id="shippingFeeTd1" <c:if test="${bean.shippingType =='1'}">style="display:none"</c:if>>
						<input id="shippingFee" name="shippingFee" type="text" style="width:70px" value="<c:out value='${bean.shippingFee}'/>"/>&nbsp;&nbsp;元
					</td>
					<td class="lyt_view_note" >捐赠金额<span>*</span></td>
					<td >
						<input id="donationFee" name="donationFee" type="text" style="width:70px" value="<c:out value='${bean.donationFee}'/>"/>&nbsp;&nbsp;元
					</td>
				</tr>
				<tr>	
					<td class="lyt_view_note">支付状态</td>
					<td colspan="3">
						<label for="orderStatus1" class="checkbox" onclick="orderStatusChange();" style="width:80px"> 
							<input type="radio" id="orderStatus1" name="orderStatus" value="1"
							<c:if test="${bean.orderStatus=='1'}">checked="checked"</c:if> />已付款
						</label> 
						&nbsp;&nbsp; 
						<label for="orderStatus0" class="checkbox" onclick="orderStatusChange();" style="width:80px"> 
							<input type="radio" id="orderStatus0" name="orderStatus" value="0"
							<c:if test="${bean.orderStatus!='1'}">checked="checked"</c:if> />待付款
						</label>
					</td>
					<td class="lyt_view_note">提交时间</td>
					<td colspan="3">
						<input type="text" id="orderTime" name="orderTime" class="txt" style="width:265px;"
						value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${bean.orderTime}'/>" readonly="readonly"/> 
						<img onClick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm',el:'orderTime'})" 
						src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle"/>
					</td>
				</tr>
				<tr id="orderTypeTr" <c:if test="${bean.orderStatus=='1'}">style="display:none"</c:if>>
					<td class="lyt_view_note">支付方式</td>
					<td colspan="3">
						<input id="orderType" name="orderType" type="text" style="width:265px" value="<c:out value='${bean.orderType}'/>"/>
					</td>
					<td class="lyt_view_note">付款时间</td>
					<td colspan="3">
						<input type="text" id="orderOkTime" name="orderOkTime" class="txt" style="width:265px;"
						value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${bean.orderOkTime}'/>" readonly="readonly"/> 
						<img onClick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm',el:'orderOkTime'})"
						src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle"/>
					</td>
				</tr>
			</table>
			<div class="divTitle">		
				<div style="float:left;padding-left:8px;overflow:hidden;">买家信息</div>
			</div>
			<table class="lyt_view">
				<tr>
					<td class="lyt_view_area" style="width:70px;">
						姓名<span>*</span>
					</td>
					<td style="line-height:24px;">
						<input type="text" id="person" name="person" class="txt" style="width:260px;"
						value="<c:out value="${bean.buyerName}" />" maxlength="50" /> 
						<label for="personSex1" class="checkbox" style="margin-left:30px;">
							<input type="radio" id="personSex1" name="personSex" value="1"
							<c:if test="${bean.buyerSex=='1'}">checked="checked"</c:if> />先生
						</label> 
						&nbsp;&nbsp; 
						<label for="personSex2" class="checkbox"> 
							<input type="radio" id="personSex2" name="personSex" value="2"
							<c:if test="${bean.buyerSex=='2'}">checked="checked"</c:if> />女士
						</label>
						
						<label for="niMing" class="checkbox" style="margin-left:30px;" title="匿名：您所填写的个人信息将以匿名的方式展示在捐赠信息栏中。注意：匿名也要填写其他信息包括姓名联系方式等，只是不对外显示">
							<input type="checkbox" id="niMing" name="niMing" value="1" <c:if test="${bean.niMing=='1'}">checked="checked"</c:if>/>匿名购买
						</label>
						
						<label for="alumniFlag1" class="checkbox" onclick="alumniEnable();" style="margin-left:30px;"> 
							<input type="radio" id="alumniFlag1" name="alumniFlag" value="1"
							<c:if test="${bean.alumniFlag=='1'}">checked="checked"</c:if> />校友
						</label> 
						&nbsp;&nbsp; 
						<label for="alumniFlag0" class="checkbox" onclick="alumniEnable();"> 
							<input type="radio"id="alumniFlag0" name="alumniFlag" value="0"
							<c:if test="${bean.alumniFlag!='1'}">checked="checked"</c:if> />非校友
						</label>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">邮箱<span>*</span></td>
					<td style="line-height:24px;"><input type="text"
						id="personEmail" name="personEmail" class="txt"
						style="width:260px;" value="<c:out value="${bean.buyerEmail}" />"
						maxlength="50" /> <span style="margin-left:30px;font-size:13px;">手机：</span>
						<input type="text" id="personPhone" name="personPhone" class="txt"
						style="width:140px;" value="<c:out value="${bean.buyerPhone}" />"
						maxlength="50" /> <span style="color:#ff0000;">*</span> <span
						style="margin-left:30px;font-size:13px;">电话：</span> <input
						type="text" id="personTel" name="personTel" class="txt"
						style="width:129px;" value="<c:out value="${bean.buyerMobile}" />"
						maxlength="50" /></td>
				</tr>
				<tr>
					<td class="lyt_view_area">地址</td>
					<td style="line-height:24px;"><input type="text" id="sheng"
						name="sheng" class="txt" style="width:60px;"
						value="<c:out value="${bean.buyerSheng}" />" maxlength="50" /> <span
						style="color:#777;">省</span> &nbsp;&nbsp; <input type="text"
						id="shi" name="shi" class="txt" style="width:60px;"
						value="<c:out value="${bean.buyerShi}" />" maxlength="50" /> <span
						style="color:#777;">市</span> &nbsp;&nbsp; <input type="text"
						id="qu" name="qu" class="txt" style="width:60px;"
						value="<c:out value="${bean.buyerQu}" />" maxlength="50" /> <span
						style="color:#777;">区/县</span> <span
						style="color:#777;margin-left:16px;">详细：</span> <input type="text"
						id="address" name="address" class="txt" style="width:241px;"
						value="<c:out value="${bean.buyerAddress}" />" maxlength="50" />
						&nbsp;&nbsp; <span style="color:#777;">邮编：</span> <input
						type="text" id="zip" name="zip" class="txt" style="width:60px;"
						value="<c:out value="${bean.buyerZipcode}" />" maxlength="50" /></td>
				</tr>
				<tr id="academyTr"
					<c:if test="${bean.alumniFlag!='1'}">style="display:none;"</c:if>>
					<td class="lyt_view_area">校友资料</td>
					<td style="line-height:24px;"><input type="text" id="academy"
						name="academy" class="txt" style="width:244px;"
						value="<c:out value="${bean.studyAcademy}" />" maxlength="50" /> <span
						style="color:#777;">院系</span> <span
						style="color:#777;margin-left:21px;">专业：</span> <input type="text"
						id="major" name="major" class="txt" style="width:120px;"
						value="<c:out value="${bean.studyMajor}" />" maxlength="50" />
						&nbsp;&nbsp; <span style="color:#777;">班级：</span> <input
						type="text" id="clazz" name="clazz" class="txt"
						style="width:65px;" value="<c:out value="${bean.studyClass}" />"
						maxlength="50" /> &nbsp;&nbsp; <span style="color:#777;">学号：</span>
						<input type="text" id="studyno" name="studyno" class="txt"
						style="width:60px;" value="<c:out value="${bean.studyNum}" />"
						maxlength="50" />
						<div style="height: 4px;"></div> <select id="academyBeg"
						name="academyBeg" style="width:113px;">
							<option value="">入学年</option>
							<c:forEach begin="1900" end="${nowYear}" var="temp"
								varStatus="status">
								<c:set var="iyear" value="${nowYear+1900-temp}" />
								<option value="${iyear}"
									<c:if test="${bean.studyYearin==iyear}">selected</c:if>>${iyear}年</option>
							</c:forEach>
					</select> ~ <select id="academyEnd" name="academyEnd" style="width:114px;">
							<option value="">离校年</option>
							<c:forEach begin="1900" end="${nowYear}" var="temp"
								varStatus="status">
								<c:set var="iyear" value="${nowYear+1900-temp}" />
								<option value="${iyear}"
									<c:if test="${bean.studyYearover==iyear}">selected</c:if>>${iyear}年</option>
							</c:forEach>
					</select> <span style="color:#777;margin-left:25px;">校友身份：</span> <input
						type="text" id="academyDegree" name="academyDegree" class="txt"
						style="width:357px;" value="<c:out value="${bean.studyDegree}" />"
						maxlength="50" /></td>
				</tr>
				<tr>
					<td class="lyt_view_area">工作资料</td>
					<td style="line-height:24px;"><input type="text" id="company"
						name="company" class="txt" style="width:244px;"
						value="<c:out value="${bean.workCompany}" />" maxlength="50" /> <span
						style="color:#777;">单位</span> <span
						style="color:#777;margin-left:21px;">部门：</span> <input type="text"
						id="department" name="department" class="txt" style="width:140px;"
						value="<c:out value="${bean.workDepart}" />" maxlength="50" /> <span
						style="color:#777;margin-left:33px;">职务：</span> <input type="text"
						id="duty" name="duty" class="txt" style="width:140px;"
						value="<c:out value="${bean.workDuty}" />" maxlength="50" /></td>
				</tr>
				<tr>
					<td class="lyt_view_area">其他信息/<br />捐赠留言</td>
					<td style="line-height:24px;"><textarea id="orderMemo"
							name="orderMemo" style="width:694px;height:60px;"
							onclick="myArea('orderMemo',1000);"
							onkeyup="myArea('orderMemo',1000);"
							onkeydown="myArea('orderMemo',1000);"
							onchange="myArea('orderMemo',1000);"><c:out value="${bean.orderMemo}" /></textarea>
					</td>
				</tr>
			</table>
			<div class="divTitle">
				<div style="float:left;padding-left:8px;overflow:hidden;">
					标记信息</div>
			</div>
			<table class="lyt_view">
				<tr>
					<td class="lyt_view_area" style="width:70px;"><b
						style="color:#ff0000;">标记信息</b></td>
					<td><textarea id="mark" name="mark"
							style="width:694px;height:60px;" onclick="myArea('mark',1000);"
							onkeyup="myArea('mark',1000);" onkeydown="myArea('mark',1000);"
							onchange="myArea('mark',1000);"><c:out value="${bean.mark}" /></textarea>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area"><b style="color:#ff0000;">回执信息</b></td>
					<td><textarea id="receipt" name="receipt"
							style="width:692px;height:200px;"><c:out value="${bean.receipt}" /></textarea>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area"><b style="color:#ff0000;">备注说明</b></td>
					<td><textarea id="memo" name="memo"
							style="width:694px;height:60px;" onclick="myArea('memo',1000);"
							onkeyup="myArea('memo',1000);" onkeydown="myArea('memo',1000);"
							onchange="myArea('memo',1000);"><c:out value="${bean.memo}" /></textarea>
					</td>
				</tr>
			</table>
			<div class="lyt_submit" style="padding-left:645px;">
				<input type="submit" value="保存" /> 
				<input type="button" value="取消" onclick="parent.MyFormWin.close();return false;" />
			</div>
		</form>
	</div>
	<script type="text/javascript">
		var receiptEditor;
		$(document).ready(
				function() {
					KindEditor.ready(function(K) {
						receiptEditor = K.create("textarea[name='receipt']",
								{
									items : [ 'source', '|', 'undo', 'redo',
											'|', 'preview', 'print', 'cut',
											'copy', 'paste', 'plainpaste',
											'wordpaste', '|', 'justifyleft',
											'justifycenter', 'justifyright',
											'justifyfull', 'insertorderedlist',
											'insertunorderedlist', 'indent',
											'outdent', 'subscript',
											'superscript', 'clearhtml',
											'quickformat', 'selectall', '|',
											'fullscreen', '/', 'formatblock',
											'fontname', 'fontsize', '|',
											'forecolor', 'hilitecolor', 'bold',
											'italic', 'underline',
											'strikethrough', 'lineheight',
											'removeformat', '|', 'image',
											'flash', 'media', 'insertfile',
											'table', 'hr', 'emoticons', 'map',
											'link', 'unlink' ],
									langType : 'zh_CN',
									resizeType : 1,
									themeType : 'simple',
									allowFileManager : false,
									imageTabIndex : 1
								});
					});
				});
		
		function optionDefine() {
			Ext.Msg.buttonText.ok = '确定';
			Ext.Msg.buttonText.cancel = "取消";
			Ext.Msg.prompt("自定义型号", "请输入自定义型号名称（50字以内）：", function(btn, text) {
				if (btn == 'ok') {
					var value = text.replace(/(^\s*)|(\s*$)/g, "");
					if (value.length == 0) {
						MyMsg.alertFn("请输入自定义型号名称!", function() {
							optionDefine();
						});
					} else if (value.length > 50) {
						MyMsg.alertFn("自定义型号名称不能超过50字!", function() {
							optionDefine();
						});
					} else {
						if ($('#detailId > optgroup[label="自定义"]').length == 0) {
							$('#detailId').append('<optgroup label="自定义"></optgroup>');
						}
						$('#detailId > optgroup[label="自定义"]').append('<option value="" selected>'+ value + '</option>');
				        $('#detailName').val(value);
					}
				}
			}, this, false, null);
		}
		
		function detailChange(){
			$('#detailName').val($('#detailId option:selected').text());
		}
		
		function shippingTypeChange(){
			if($('#shippingType1').attr('checked'))
			{
				$('#shippingFeeTd0').css("display","none");
				$('#shippingFeeTd1').css("display","none");
			}
			if($('#shippingType0').attr('checked'))
			{
				$('#shippingFeeTd0').css("display","inline");
				$('#shippingFeeTd1').css("display","inline");
			}
		}
		
		function alumniEnable() {
			if ($('#alumniFlag1').attr('checked')) {
				$('#academyTr').show();
			}
			if ($('#alumniFlag0').attr('checked')) {
				$('#academyTr').hide();
			}
		}
		function orderStatusChange() {
			if ($('#orderStatus1').attr('checked')) {
				$('#orderTypeTr').hide();
			}
			if ($('#orderStatus0').attr('checked')) {
				$('#orderTypeTr').show();
			}
		}

		function checkForm() {
			formFormat("formId");
		
			if ($("#orderFee").val() == ''
					|| !/^\d+(.\d{1,2})?$/.test($("#orderFee").val())) {
				MyMsg.alertFn('请输入订单总金额（最多两位小数的正数）！', function() {
					$("#orderFee").focus();
				});
				return false;
			}
		
			if ($("#commCostfee").val() == ''
					|| !/^\d+(.\d{1,2})?$/.test($("#commCostfee").val())) {
				MyMsg.alertFn('请输入商品成本价（最多两位小数的正数）！', function() {
					$("#commCostfee").focus();
				});
				return false;
			}
		
			if ($("#commSalefee").val() == ''
					|| !/^\d+(.\d{1,2})?$/.test($("#commSalefee").val())) {
				MyMsg.alertFn('请输入商品售价（最多两位小数的正数）！', function() {
					$("#commSalefee").focus();
				});
				return false;
			}
			
			if ($("#commNum").val() == ''
					|| !/^\d+$/.test($("#commNum").val())) {
				MyMsg.alertFn('请输入商品数量（正整数）！', function() {
					$("#commNum").focus();
				});
				return false;
			}
			
			if($('#shippingType0').attr('checked')){		
			if ($("#shippingFee").val() == ''
					|| !/^\d+(.\d{1,2})?$/.test($("#shippingFee").val())) {
				MyMsg.alertFn('请输入运费（最多两位小数的正数）！', function() {
					$("#shippingFee").focus();
				});
				return false;
			}
			}
			
			if ($("#donationFee").val() == ''|| !/^\d+(.\d{1,2})?$/.test($("#donationFee").val())) {
				MyMsg.alertFn('请输入捐赠金额（最多两位小数的正数）！', function() {
						$("#donationFee").focus();});
				return false;
			}			
			
			if ($('#person').val() == '') {
				MyMsg.alertFn('请输入捐赠人姓名！', function() {
					$('#person').focus();
				});
				return false;
			}
			if ($('#personEmail').val() == '') {
				MyMsg.alertFn('请输入捐赠人电子邮箱！', function() {
					$('#personEmail').focus();
				});
				return false;
			}
			if ($('#personPhone').val() == '') {
				MyMsg.alertFn('请输入捐赠人手机！', function() {
					$('#personPhone').focus();
				});
				return false;
			}
			if ($("#orderMemo").val().length > 1000) {
				MyMsg.alertFn('其他信息/捐赠留言必须在1000字以内！', function() {
					$("#orderMemo").focus();
				});
				return false;
			}
			if ($("#mark").val().length > 1000) {
				MyMsg.alertFn('标记信息必须在1000字以内！', function() {
					$("#mark").focus();
				});
				return false;
			}
			if ($("#memo").val().length > 1000) {
				MyMsg.alertFn('备注说明必须在1000字以内！', function() {
					$("#memo").focus();
				});
				return false;
			}
			receiptEditor.sync();
			return true;
		}
	</script>
</body>
</html>
