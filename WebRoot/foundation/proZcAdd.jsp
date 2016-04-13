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
<script type="text/javascript"
	src="<%=basePath%>UI/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" src="<%=basePath%>UI/js/kind-editor.js"></script>
<script type="text/javascript">
//选择所属项目 
function openwindowssxm(){
	MyFormWin.showMyWin('添加','<%=basePath%>found!chooseFound.action?flag=pro',750,550);
}
function setFound(id,name){
    document.getElementById("foundId").value=id;
    document.getElementById("foundName").value=name;
}
// 验证表单
function checkForm(form){
	formFormat("formId");
	if($("#foundName").val() == ""){
		$("#foundName").focus();
		form.foundName.style.backgroundColor=error;
		return false;
	}
	if($("#proName").val() == ""){
		$("#proName").focus();
		form.proName.style.backgroundColor=error;
		return false;
	}
	// 简介
	if(!(getLength(form.proIntro.value)<=2000)){
		msg+="简介不能超过1000个字\n";
		form.proIntro.style.backgroundColor=error;
	}
	return true;
	
}
function getByteLen(str) {    //传入一个字符串
   var len = 0;
   if(typeof(str)!="undefined"){
	   var len=0;
		if(str =='') {
			return len;
		} else {
			var out = str.replace(/[^\x00-\xff]/g,'**');
			len=out.length;
			return len;
		}
   }
   return len;
}
var  proContent;
$(document).ready(function(){
	KindEditor.ready(function(K) {
		proContent = K.create("textarea[name='proContent']", {
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
</script>
	</head>
	<body>
		<div class="page">
		<%@ include file="../common/navigation.jsp"%>
		<form id="formId" name="formId" method="post" action="<%=basePath%>found!saveOrUpdateProZc.action" onsubmit="return checkForm(this);">
			<input type="hidden" id="foundId" name="foundId" value="${foundId}" />
			<input type="hidden" name="proId" value="${proId}" />
			<table class="lyt_view">
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						所属项目
					</td>
					<td>
						<input style="width: 180px;" readonly="readonly" id="foundName" name="foundName" type="text" value='<c:out value="${beanFound.foundName}" />'/><span class="fnt_warn">*</span>
						<div style="width: 58px;float: right">
							<input type="button" value="选择" style="width: 58px;" onclick="openwindowssxm();"/>
						</div>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						众筹编号
					</td>
					<td style="width: 245px;">
						<input style="width: 180px;" id="proCode" name="proCode" type="text" value='<c:out value="${bean.proCode}" />' maxlength="100" />
					</td>
					<td class="lyt_view_note" style="width: 80px;">
						众筹名称
					</td>
					<td style="width: 245px;">
						<input style="width: 180px;" id="proName" name="proName" type="text" value='<c:out value="${bean.proName}" />' maxlength="100" />
						<span class="fnt_warn">*</span>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						执行日期
					</td>
					<td>
						<input style="width: 70px;" type="text" name="startTime" id="startTime" readonly="true" class="lyt_search_date" value='<c:out value="${bean.startTime}" />' />
						<img onClick="WdatePicker({el:'startTime'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle" />
						至
						<input style="width: 70px;" type="text" name="endTime" id="endTime" readonly="true" class="lyt_search_date" value='<c:out value="${bean.endTime}" />' />
						<img onClick="WdatePicker({el:'endTime'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle" />
					</td>
					<td class="lyt_view_note" style="width: 80px;">
						目标金额
					</td>
					<td colspan="1" style="width: 245px;">
						<input style="width: 180px;" id="proAmount" name="proAmount" onkeyup="limit(this)" type="text" value='<c:out value="${bean.proAmount}" />' maxlength="100" />元
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						最低捐赠金额
					</td>
					<td colspan="1" style="width: 245px;">
						<input style="width: 180px;" id="donationMin" name="donationMin" onkeyup="limit(this)" type="text" value='<c:out value="${bean.donationMin}" />' maxlength="100" />元
					</td>
					<td class="lyt_view_note" style="width: 80px;">
						是否热门
					</td>
					<td>
						<select name="isHot" style="width: 180px;">
							<option <c:if test="${bean.isHot==1}">selected</c:if> value="1">是</option>
							<option <c:if test="${bean.isHot==0}">selected</c:if> value="0">否</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px; vertical-align: top; padding-top: 10px;">
						简介
					</td>
					<td colspan="5">
						<textarea name="proIntro" id="proIntro" style="width: 585px;"><c:out value='${bean.proIntro}' /></textarea>
					</td>
				</tr>
				<c:if test="${not empty proId}">
				<tr>
					<td colspan="4" class="lyt_view_note">
						<span style="text-align: right; float: left;">捐赠明细</span>
						<span style="text-align: right; float: right;">
						<a href="javascript:;" onclick="MyFormWin.showMyWin('捐赠明细','<%=basePath%>found!toProDetailPage.action?proId=${proId}',650,360);return false;">新增</a>
						</span>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<iframe name="iframePro" id="iframePro" width="100%" height="100%" frameborder="0" scrolling="auto" src="<%=basePath%>found!proDetailList.action?proId=${proId}"></iframe>
					</td>
				</tr>
				</c:if>
				<tr>
					<td class="lyt_view_note" style="width: 80px; vertical-align: top; padding-top: 10px;">
						众筹主要内容
					</td>
					<td colspan="5">
						<textarea name="proContent" id="proContent" style="width: 690px; height: 300px; visibility: hidden;"><c:out value='${bean.proContent}' /></textarea>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px; vertical-align: top; padding-top: 10px;">
						附件
					</td>
					<td colspan="5">
						<input id="updateId" name="relIds" type="hidden" value="" />
						<iframe class="frm_file" marginheight="0" frameborder="0" src="<%=basePath %>fileList.action?name=TbProtocol&ele=updateId&id=${bean.proId}">
						</iframe>
					</td>
				</tr>
			</table>
			<my:timeView idField="proId" table="TbProtocol" value="${bean.proId}"></my:timeView>
			<div class="lyt_submit" style="width: 790px; text-align: right;">
				<input type="submit" class="btn_save" value="提交" />
				&nbsp;
				<input type="button" onclick="location.href='<%=basePath%>found!proclZc.action?proType=${proType}&foundId=${foundId}&num=${num}&sub=${sub}'" value="返回" />
			</div>
		</form>
		</div>
	</body>
</html>