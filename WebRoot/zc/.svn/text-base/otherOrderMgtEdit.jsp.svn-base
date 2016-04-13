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
		<%@ include file="../common/kindeditor.jsp"%>
		<style type="text/css">
		.checkbox{
			vertical-align: inherit;
		}
		.checkbox input{
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
			<form id="formId" name="formId" action="<%=basePath%>zcOtherOrder!doAddOrEdit.action" method="post" onsubmit="return checkForm(this);">
				<input type="hidden" name="orderId" value="${bean.orderId}" />
				<table class="lyt_view">
					<c:if test="${not empty bean.orderId}">
					<tr>
						<td class="lyt_view_note">
							捐赠单号
						</td>
						<td colspan="3">
							<c:out value="${bean.orderNum}" />
						</td>
					</tr>
					</c:if>
					<tr>
						<td class="lyt_view_note">
							捐赠项目<span>*</span>
						</td>
						<td colspan="3">
							<input type="text" id="projName" name="projName" class="txt" style="width:694px;" value="<c:out value="${bean.projName}" />" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td class="lyt_view_note" style="width:70px;">
							项目链接
						</td>
						<td style="width:380px;">
							<input type="text" id="projUrl" name="projUrl" class="txt" style="width:360px;" value="<c:out value="${bean.projUrl}" />" maxlength="100" />
						</td>
						
						<td class="lyt_view_note" style="width:70px;">
							捐赠币种<span>*</span>
						</td>
						<td style="width:200px;">
							<input type="text" id="amountType" name="amountType" class="txt" style="width:200px;" value="<c:out value='${bean.orderAmountType}' default='人民币'/>" maxlength="100" autocomplete="OFF"/>
						</td>
					</tr>
					<tr>
						<td class="lyt_view_note">
							捐赠币种<span>*</span><br/>总金额
						</td>
						<td>
							<input type="text" id="amountView" name="amountView" class="txt" style="width:266px;" value="<fmt:formatNumber value='${bean.orderAmountView}' pattern='0.##' type='number'/>" maxlength="50" autocomplete="OFF"/>
							<input type="text" id="amountUnit" name="amountUnit" class="txt" style="width:90px;" value="<c:out value='${bean.orderAmountUnit}' default='元'/>" maxlength="100" autocomplete="OFF"/>
						</td>
						<td class="lyt_view_note">
							实算总金额<span>*</span>
						</td>
						<td style="width:200px;">
							<input type="text" id="amount" name="amount" class="txt" style="width:200px;" value="<fmt:formatNumber value='${bean.orderAmount}' pattern='0.##' type='number'/>" maxlength="50" autocomplete="OFF"/>
							 元
						</td>
					</tr>
					<tr>
						<td class="lyt_view_note">
							捐赠状态
						</td>
						<td>
							<label for="orderStatus1" class="checkbox" onclick="orderStatusEnable();">
								<input type="radio" id="orderStatus1" name="orderStatus" value="1" <c:if test="${bean.orderStatus=='1'}">checked="checked"</c:if>/>已付款
							</label>
							&nbsp;&nbsp;
							<label for="orderStatus0" class="checkbox" onclick="orderStatusEnable();">
								<input type="radio" id="orderStatus0" name="orderStatus" value="0" <c:if test="${bean.orderStatus!='1'}">checked="checked"</c:if>/>待付款
							</label>
						</td>
						<td class="lyt_view_note">
							提交时间
						</td>
						<td>
							<input type="text" id="orderTime" name="orderTime" class="txt" style="width:200px;" value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${bean.orderTime}'/>" readonly="true"/>
							<img onClick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm',el:'orderTime'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle"/>
						</td>
					</tr>
					<tr id="orderTypeTr" <c:if test="${bean.orderStatus!='1'}">style="display:none;"</c:if>>
						<td class="lyt_view_note">
							捐赠方式
						</td>
						<td>
							<input type="text" id="orderType" name="orderType" class="txt" style="width:355px;" value="<c:out value="${bean.orderType}"/>" maxlength="50"/>
						</td>
						<td class="lyt_view_note">
							付款时间
						</td>
						<td>
							<input type="text" id="orderOkTime" name="orderOkTime" class="txt" style="width:200px;" value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${bean.orderOkTime}'/>" readonly="true"/>
							<img onClick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm',el:'orderOkTime'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle"/>
						</td>
					</tr>
					<tr>
						<td class="lyt_view_note">
							证书
						</td>
						<td>
							<label for="needZhengshu1" class="checkbox">
								<input type="radio" id="needZhengshu1" name="needZhengshu" value="1" <c:if test="${bean.needZhengshu=='1'}">checked="checked"</c:if>>需要
							</label>
							&nbsp;&nbsp;
							<label for="needZhengshu0" class="checkbox">
								<input type="radio" id="needZhengshu0" name="needZhengshu" value="0" <c:if test="${bean.needZhengshu!='1'}">checked="checked"</c:if>>不需要
							</label>
						</td>
						<td class="lyt_view_note">
							发票
						</td>
						<td>
							<label for="needFapiao1" class="checkbox">
								<input type="radio" id="needFapiao1" name="needFapiao" value="1" <c:if test="${bean.needFapiao=='1'}">checked="checked"</c:if>>需要
							</label>
							&nbsp;&nbsp;
							<label for="needFapiao0" class="checkbox">
								<input type="radio" id="needFapiao0" name="needFapiao" value="0" <c:if test="${bean.needFapiao!='1'}">checked="checked"</c:if>>不需要
							</label>
						</td>
					</tr>
				</table>
				<div style="width:780px;height:30px;line-height:30px;margin:5px 0px 0px 5px;font-weight:bold;background-color:#d0ddf1;overflow:hidden;">
					<div style="float:left;padding-left:8px;overflow:hidden;">
						捐赠人
						<label for="personType1" class="checkbox" style="margin-left:40px;" onclick="personTypeChange();">
							<input type="radio" id="personType1" name="personType" value="1" <c:if test="${bean.personType!='2'}">checked="checked"</c:if>>&nbsp;个人
						</label>
						<label for="personType2" class="checkbox" onclick="personTypeChange();">
							<input type="radio" id="personType2" name="personType" value="2" <c:if test="${bean.personType=='2'}">checked="checked"</c:if>>&nbsp;集体
							<span id="personCountSpan" <c:if test="${bean.personType!='2'}">style="display:none;"</c:if>>（
							实捐
							<input type="text" id="personCount" name="personCount" class="txt" style="width:60px;vertical-align:0px;" value="<c:out value="${bean.personCount}" default="1"/>" maxlength="50"/>人<span style="color:red;">*</span>
							）
							</span>
						</label>
					</div>
				</div>
				<table class="lyt_view">
					<tr>
						<td class="lyt_view_area" style="width:70px;">
							姓名<span>*</span>
						</td>
						<td style="line-height:24px;">
							<input type="text" id="person" name="person" class="txt" style="width:260px;" value="<c:out value="${bean.personName}" />" maxlength="50"/>
							<label for="personSex1" class="checkbox" style="margin-left:30px;">
								<input type="radio" id="personSex1" name="personSex" value="1" <c:if test="${bean.personSex=='1'}">checked="checked"</c:if>/>先生
							</label>
							&nbsp;&nbsp;
							<label for="personSex2" class="checkbox">
								<input type="radio" id="personSex2" name="personSex" value="2" <c:if test="${bean.personSex=='2'}">checked="checked"</c:if>/>女士
							</label>
							
							<label for="niMing" class="checkbox" style="margin-left:30px;" title="匿名：您所填写的个人信息将以匿名的方式展示在捐赠信息栏中。注意：匿名也要填写其他信息包括姓名联系方式等，只是不对外显示">
								<input type="checkbox" id="niMing" name="niMing" value="1" <c:if test="${bean.niMing=='1'}">checked="checked"</c:if>/>匿名捐赠
							</label>
							
							<label for="alumniFlag1" class="checkbox" onclick="alumniEnable();" style="margin-left:30px;">
								<input type="radio" id="alumniFlag1" name="alumniFlag" value="1" <c:if test="${bean.alumniFlag=='1'}">checked="checked"</c:if>/>校友
							</label>
							&nbsp;&nbsp;
							<label for="alumniFlag0" class="checkbox" onclick="alumniEnable();">
								<input type="radio" id="alumniFlag0" name="alumniFlag" value="0" <c:if test="${bean.alumniFlag!='1'}">checked="checked"</c:if>/>非校友
							</label>
						</td>
					</tr>
					<tr>
						<td class="lyt_view_area">
							邮箱<span>*</span>
						</td>
						<td style="line-height:24px;">
							<input type="text" id="personEmail" name="personEmail" class="txt" style="width:260px;" value="<c:out value="${bean.personEmail}" />" maxlength="50"/>
							<span style="margin-left:30px;font-size:13px;">手机：</span>
							<input type="text" id="personPhone" name="personPhone" class="txt" style="width:140px;" value="<c:out value="${bean.personPhone}" />" maxlength="50"/>
							<span style="color:#ff0000;">*</span>
							<span style="margin-left:30px;font-size:13px;">电话：</span>
							<input type="text" id="personTel" name="personTel" class="txt" style="width:129px;" value="<c:out value="${bean.personTel}" />" maxlength="50"/>
						</td>
					</tr>
					<tr>
						<td class="lyt_view_area">
							地址
						</td>
						<td style="line-height:24px;">
							<input type="text" id="sheng" name="sheng" class="txt" style="width:60px;" value="<c:out value="${bean.addressSheng}" />" maxlength="50"/>
							<span style="color:#777;">省</span>
							&nbsp;&nbsp;
							<input type="text" id="shi" name="shi" class="txt" style="width:60px;" value="<c:out value="${bean.addressShi}" />" maxlength="50"/>
							<span style="color:#777;">市</span>
							&nbsp;&nbsp;
							<input type="text" id="qu" name="qu" class="txt" style="width:60px;" value="<c:out value="${bean.addressQu}" />" maxlength="50"/>
							<span style="color:#777;">区/县</span>
							<span style="color:#777;margin-left:16px;">详细：</span>
							<input type="text" id="address" name="address" class="txt" style="width:241px;" value="<c:out value="${bean.addressDetail}" />" maxlength="50"/>
							&nbsp;&nbsp;
							<span style="color:#777;">邮编：</span>
							<input type="text" id="zip" name="zip" class="txt" style="width:60px;" value="<c:out value="${bean.addressZip}" />" maxlength="50"/>
						</td>
					</tr>
					<tr id="academyTr" <c:if test="${bean.alumniFlag!='1'}">style="display:none;"</c:if>>
						<td class="lyt_view_area">
							校友资料
						</td>
						<td style="line-height:24px;">
							<input type="text" id="academy" name="academy" class="txt" style="width:244px;" value="<c:out value="${bean.studyAcademy}" />" maxlength="50"/>
							<span style="color:#777;">院系</span>
							<span style="color:#777;margin-left:21px;">专业：</span>
							<input type="text" id="major" name="major" class="txt" style="width:120px;" value="<c:out value="${bean.studyMajor}" />" maxlength="50"/>
							&nbsp;&nbsp;
							<span style="color:#777;">班级：</span>
							<input type="text" id="clazz" name="clazz" class="txt" style="width:65px;" value="<c:out value="${bean.studyClass}" />" maxlength="50"/>
							&nbsp;&nbsp;
							<span style="color:#777;">学号：</span>
							<input type="text" id="studyno" name="studyno" class="txt" style="width:60px;" value="<c:out value="${bean.studyNum}" />" maxlength="50"/>
							<div style="height: 4px;"></div>
							<select id="academyBeg" name="academyBeg" style="width:113px;">
								<option value="">入学年</option>
								<c:forEach begin="1900" end="${nowYear}" var="temp" varStatus="status">
			 					<c:set var="iyear" value="${nowYear+1900-temp}"/>
								<option value="${iyear}" <c:if test="${bean.studyYearin==iyear}">selected</c:if>>${iyear}年</option>
								</c:forEach>
							</select>
							~
							<select id="academyEnd" name="academyEnd" style="width:114px;">
								<option value="">离校年</option>
								<c:forEach begin="1900" end="${nowYear}" var="temp" varStatus="status">
			 					<c:set var="iyear" value="${nowYear+1900-temp}"/>
								<option value="${iyear}" <c:if test="${bean.studyYearover==iyear}">selected</c:if>>${iyear}年</option>
								</c:forEach>
							</select>
							<span style="color:#777;margin-left:25px;">校友身份：</span>
							<input type="text" id="academyDegree" name="academyDegree" class="txt" style="width:357px;" value="<c:out value="${bean.studyDegree}" />" maxlength="50"/>
						</td>
					</tr>
					<tr>
						<td class="lyt_view_area">
							工作资料
						</td>
						<td style="line-height:24px;">
							<input type="text" id="company" name="company" class="txt" style="width:244px;" value="<c:out value="${bean.workCompany}" />" maxlength="50"/>
							<span style="color:#777;">单位</span>
							<span style="color:#777;margin-left:21px;">部门：</span>
							<input type="text" id="department" name="department" class="txt" style="width:140px;" value="<c:out value="${bean.workDepart}" />" maxlength="50"/>
							<span style="color:#777;margin-left:33px;">职务：</span>
							<input type="text" id="duty" name="duty" class="txt" style="width:140px;" value="<c:out value="${bean.workDuty}" />" maxlength="50"/>
						</td>
					</tr>
					<tr>
						<td class="lyt_view_area">
							其他信息/<br/>捐赠留言
						</td>
						<td style="line-height:24px;">
							<textarea id="orderMemo" name="orderMemo" style="width:694px;height:60px;" onclick="myArea('orderMemo',1000);" onkeyup="myArea('orderMemo',1000);" onkeydown="myArea('orderMemo',1000);" onchange="myArea('orderMemo',1000);"><c:out value="${bean.orderMemo}" /></textarea>
						</td>
					</tr>
				</table>
				<div style="width:780px;height:30px;line-height:30px;margin:5px 0px 0px 5px;font-weight:bold;background-color:#d0ddf1;overflow:hidden;">
					<div style="float:left;padding-left:8px;overflow:hidden;">
						标记信息
					</div>
				</div>
				<table class="lyt_view">
					<tr>
						<td class="lyt_view_area" style="width:70px;">
							<b style="color:#ff0000;">标记信息</b>
						</td>
						<td>
							<textarea id="mark" name="mark" style="width:694px;height:60px;" onclick="myArea('mark',1000);" onkeyup="myArea('mark',1000);" onkeydown="myArea('mark',1000);" onchange="myArea('mark',1000);"><c:out value="${bean.mark}" /></textarea>
						</td>
					</tr>
					<tr>
						<td class="lyt_view_area">
							<b style="color:#ff0000;">回执信息</b>
						</td>
						<td>
							<textarea id="receipt" name="receipt" style="width:692px;height:200px;"><c:out value="${bean.receipt}" /></textarea>
						</td>
					</tr>
					<tr>
						<td class="lyt_view_area">
							<b style="color:#ff0000;">备注说明</b>
						</td>
						<td>
							<textarea id="memo" name="memo" style="width:694px;height:60px;" onclick="myArea('memo',1000);" onkeyup="myArea('memo',1000);" onkeydown="myArea('memo',1000);" onchange="myArea('memo',1000);"><c:out value="${bean.memo}" /></textarea>
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
			$(document).ready(function(){
			  	KindEditor.ready(function(K) {
					receiptEditor = K.create("textarea[name='receipt']", {
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
			function alumniEnable(){
				if($('#alumniFlag1').attr('checked')){
					$('#academyTr').show();
				}
				if($('#alumniFlag0').attr('checked')){
					$('#academyTr').hide();
				}
			}
			function orderStatusEnable(){
				if($('#orderStatus1').attr('checked')){
					$('#orderTypeTr').show();
				}
				if($('#orderStatus0').attr('checked')){
					$('#orderTypeTr').hide();
				}
			}
			function personTypeChange(){
				if($('#personType1').attr('checked')){
					$('#personCountSpan').hide();
				}else{
					$('#personCountSpan').show();
					$('#personCount').focus();
				}
			}
			function checkForm(){
				formFormat("formId");
				if($('#projName').val()==''){
					MyMsg.alertFn('请输入捐赠项目名称！',function(){
						$('#projName').focus();
					});
					return false;
				}
				if($("#amountView").val() == '' || !/^\d+(.\d{1,2})?$/.test($("#amountView").val())){
					MyMsg.alertFn('请输入捐赠币种总金额（最多两位小数的正数）！',function(){
						$("#amountView").focus();
					});
					return false;
				}
				if($('#amountUnit').val()==''){
					MyMsg.alertFn('请输入捐赠币种总金额计量单位！',function(){
						$('#amountUnit').focus();
					});
					return false;
				}
				if($("#amount").val() == '' || !/^\d+(.\d{1,2})?$/.test($("#amount").val())){
					MyMsg.alertFn('请输入实算总金额（最多两位小数的正数）！',function(){
						$("#amount").focus();
					});
					return false;
				}
				if($('#personType2').attr('checked')){
					if($("#personCount").val() == '' || !/^\d+$/.test($("#personCount").val())){
						MyMsg.alertFn('请输入集体捐赠实捐人数）！',function(){
							$("#personCount").focus();
						});
						return false;
					}
				}
				if($('#person').val()==''){
					MyMsg.alertFn('请输入捐赠人姓名！',function(){
						$('#person').focus();
					});
					return false;
				}
				if($('#personEmail').val()==''){
					MyMsg.alertFn('请输入捐赠人电子邮箱！',function(){
						$('#personEmail').focus();
					});
					return false;
				}
				if($('#personPhone').val()==''){
					MyMsg.alertFn('请输入捐赠人手机！',function(){
						$('#personPhone').focus();
					});
					return false;
				}
				if($("#orderMemo").val().length > 1000){
					MyMsg.alertFn('其他信息/捐赠留言必须在1000字以内！',function(){
						$("#orderMemo").focus();
					});
					return false;
				}
				if($("#mark").val().length > 1000){
					MyMsg.alertFn('标记信息必须在1000字以内！',function(){
						$("#mark").focus();
					});
					return false;
				}
				if($("#memo").val().length > 1000){
					MyMsg.alertFn('备注说明必须在1000字以内！',function(){
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