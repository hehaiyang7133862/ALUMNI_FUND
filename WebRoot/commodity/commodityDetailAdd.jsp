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
	if($("#detailName").val()==""){
		MyMsg.alertFn('请输入型号名称！',function(){
			$("#detailName").focus();
		});
		return false;
	}
	var picVal = $('#detailPic').val();
	if(picVal != ""){
  		var picValType = picVal.split(".")[picVal.split(".").length-1].toLowerCase();
		if(picValType!="gif" && picValType!="jpg" && picValType!="jpeg" && picValType!="png" && picValType!="bmp"){
			MyMsg.alert("图片只允许jpg,png,bmp,jpeg,gif格式!");
			return false;
		}
	}
	if($("#detailIntro").val().length > 1000){
		MyMsg.alertFn('型号简介必须在1000字以内！',function(){
			$("#detailIntro").focus();
		});
		return false;
	}
	if($("#stockNum").val() == "" || !/^\d+$/.test($("#stockNum").val())){
		MyMsg.alertFn('请输入库存数量（正整数）！',function(){
			$("#stockNum").focus();
		});
		return false;
	}
	if($("#costFee").val() == "" || !/^\d+(.\d{1,2})?$/.test($("#costFee").val())){
		MyMsg.alertFn('请输入成本价（最多两位小数的正整数）！',function(){
			$("#costFee").focus();
		});
		return false;
	}
	if($("#saleFee").val() == "" || !/^\d+(.\d{1,2})?$/.test($("#saleFee").val())){
		MyMsg.alertFn('请输入售价（最多两位小数的正整数）！',function(){
			$("#saleFee").focus();
		});
		return false;
	}
	if($("#isShelves").val()==""){
		MyMsg.alert('请选择是否上架！');
		return false;
	}
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
	<div class="float">
		<form id="formId" action="<%=basePath%>commodity!saveOrUpdateDetail.action" method="post" onSubmit="return checkForm(this)" enctype="multipart/form-data">
			<input name="commId" type="hidden" value="${bean.commId}" />
			<input name="detailId" type="hidden" value="${bean.detailId}" />
			<table border="0" cellspacing="0" cellpadding="0" class="lyt_view">
				<tr>
					<td class="lyt_view_note" style="width:80px;">
						型号名称
						<span>*</span>
					</td>
					<td width="400"><input type="text" style="width:400px;" id="detailName" name="detailName" value="<c:out value='${bean.detailName}'/>" /></td>
				</tr>
				<c:if test="${not empty bean.detailPic}">
					<tr>
						<td class="lyt_view_note">当前型号图片</td>
						<td><img width="100px" height="100px" src="<%=basePath%>${bean.detailPic}" alt="当前型号图片" /></td>
					</tr>
				</c:if>
				<tr>
					<td class="lyt_view_note">型号图片</td>
					<td><input type="file" style="width:400px;" id="detailPic" name="detailPic"/></td>
				</tr>
				<tr>
					<td class="lyt_view_area">型号简介</td>
					<td><textarea id="detailIntro" name="detailIntro" style="width:400px;height:80px;" onclick="myArea('detailIntro',1000);" onkeyup="myArea('detailIntro',1000);" onkeydown="myArea('detailIntro',1000);" onchange="myArea('detailIntro',1000);"><c:out value='${bean.detailIntro}'/></textarea></td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						库存数量
						<span>*</span>
					</td>
					<td><input type="text" style="width:150px;" id="stockNum" name="stockNum" value="<fmt:formatNumber value='${bean.stockNum}' pattern='0' type='number'/>"/></td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						成本价
						<span>*</span>
					</td>
					<td><input type="text" style="width:150px;" id="costFee" name="costFee" value="<fmt:formatNumber value='${bean.costFee}' pattern='0.##' type='number'/>"/> 元</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						售价
						<span>*</span>
					</td>
					<td><input type="text" style="width:150px;" id="saleFee" name="saleFee" value="<fmt:formatNumber value='${bean.saleFee}' pattern='0.##' type='number'/>"/> 元</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						是否上架
						<span>*</span>
					</td>
					<td>
						<select id="isShelves" name="isShelves" style="width:150px;">
							<option value="">--请选择--</option>
							<option value="1" <c:if test="${bean.isShelves=='1'}">selected</c:if>>是</option>
							<option value="0" <c:if test="${bean.isShelves=='0'}">selected</c:if>>否</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						排序值
					</td>
					<td><input type="text" style="width:150px;" id="numOrder" name="numOrder" value="<c:out value='${bean.numOrder}'/>"/></td>
				</tr>
				<tr>
					<td class="lyt_view_area">备注</td>
					<td><textarea id="memo" name="memo" style="width:400px;height:80px;" onclick="myArea('memo',1000);" onkeyup="myArea('memo',1000);" onkeydown="myArea('memo',1000);" onchange="myArea('memo',1000);"><c:out value='${bean.memo}'/></textarea></td>
				</tr>
			</table>
			<my:timeView idField="detailId" table="TbCommodityDetail" value="${bean.detailId}"></my:timeView>
			<div class="lyt_submit" style="width: 490px; text-align: right;">
				<input type="submit" value="提交" />
				<input type="button" value="取消" onclick="parent.MyFormWin.close();return false;" />
			</div>
		</form>
	</div>
</body>
</html>
