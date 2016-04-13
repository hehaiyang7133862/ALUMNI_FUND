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
		<style>
</style>
		<%@ include file="../common/head.jsp"%>
		<%@ include file="../common/calendar.jsp"%>
		<%@ include file="../common/kindeditor.jsp"%>
		<script type="text/javascript">
		<c:if test="${not empty bean.foundId && opt=='insert'}">
			parent.updateTabTitle('项目【${bean.foundId}】管理', '项目【${bean.foundId}】管理');
		</c:if>
		var memoEditor;
		$(document).ready(function(){
		  	KindEditor.ready(function(K) {
				memoEditor = K.create("textarea[name='foundMemo']", {
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
		// 验证表单
		function checkForm(form){
			formFormat("formId");
			if($("#foundName").val() == ""){
				MyMsg.alertFn('请输入项目名称！',function(){
					$("#foundName").focus();
				});
				return false;
			}
			if($("#foundDis").val().length > 1000){
				MyMsg.alertFn('项目摘要必须在1000字以内！',function(){
					$("#foundDis").focus();
				});
				return false;
			}
			memoEditor.sync();
			return true;
		}
		</script>
	</head>
	<body>
		<div class="page">
		<%@ include file="../common/navigation.jsp"%>
		<c:if test="${not empty bean.foundId}"><%@ include file="fundMenu.jsp"%></c:if>
		<form id="formId" name="formId" action="<%=basePath%>found!saveOrUpdate.action" method="post" onsubmit="return checkForm(this);">
			<input type="hidden" name="foundId" value="${bean.foundId}" />
			<table class="lyt_view">
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						项目名称
						<span>*</span>
					</td>
					<td colspan="3">
						<input style="width: 415px" id="foundName" name="foundName" type="text" value='<c:out value="${bean.foundName}" />' maxlength="100" />
					</td>
					<td class="lyt_view_note" style="width: 80px;">
						创建时间
					</td>
					<td style="width: 160px;">
						<input style="width: 132px;" type="text" name="foundCreateDate" id="foundCreateDate" readonly="true" class="lyt_search_date" value='<c:out value="${bean.foundCreateDate}" />' />
						<img onClick="WdatePicker({el:'foundCreateDate'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle" />
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						项目编号
					</td>
					<td style="width: 160px;">
						<input style="width: 155px;" id="foundCode" name="foundCode" type="text" value='<c:out value="${bean.foundCode}" />' maxlength="100" />
					</td>
					<td class="lyt_view_note" style="width: 80px;">
						项目类别
					</td>
					<td>
						<select id="foundtypeFid" name="foundtypeFid">
							<option value="">--请选择--</option>
							<my:fundField type="OPTION" lead="FOUNDATION_TYPE" value="${bean.foundtypeFid}" />
						</select>
					</td>
					<td class="lyt_view_note" style="width: 80px;">
						项目级别
					</td>
					<td>
						<select id="foundLevel" name="foundLevel">
							<option value="">--请选择--</option>
							<my:fundField type="OPTION" lead="FOUNDATION_LEVEL" value="${bean.foundLevel}" />
						</select>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						所属分类
					</td>
					<td colspan="3">
						<select style="width: 415px" name="typeId" id="typeId">
							<option value="">--请选择--</option>
							<c:forEach items="${typeList}" var="temp" varStatus="status">
								<option value="${temp.typeId}" <c:if test="${temp.typeId==bean.foundType}">selected</c:if> >${temp.typeName}</option>
							</c:forEach>
						</select>
					</td>
					<td class="lyt_view_note" style="width: 80px;">
						是否留本
					</td>
					<td style="width: 160px;">
						<select id="isKeep" name="isKeep">
							<option value="">--请选择--</option>
							<option value="1" <c:if test="${bean.isKeep=='1'}">selected</c:if>>是</option>
							<option value="2" <c:if test="${bean.isKeep=='2'}">selected</c:if>>否</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						受惠对象
					</td>
					<td colspan="3">
						<select style="width: 415px" id="foundObject" name="foundObject">
							<option value="">--请选择--</option>
							<my:fundField type="OPTION" lead="ACCEPT_OBJECT" value="${bean.foundObject}" />
						</select>
					</td>
					<td class="lyt_view_note" style="width: 80px;">
						项目状态
					</td>
					<td>
						<select id="status" name="status">
							<option value="">--请选择--</option>
							<my:fundField type="OPTION" lead="FOUNDATION_STATUS" value="${bean.foundStatus}" />
						</select>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area" style="width:80px;">
						项目摘要
					</td>
					<td colspan="5">
						<textarea id="foundDis" name="foundDis" style="width:670px;height:80px;" onclick="myArea('foundDis',1000);" onkeyup="myArea('foundDis',1000);" onkeydown="myArea('foundDis',1000);" onchange="myArea('foundDis',1000);"><c:out value='${bean.foundDis}' /></textarea>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area" style="width: 80px;">
						项目说明
					</td>
					<td colspan="5">
						<textarea name="foundMemo" id="foundMemo" style="width: 670px; height: 300px;visibility:hidden;"><c:out value='${bean.foundMemo}' /></textarea>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area" style="width:80px;">
						附件
					</td>
					<td colspan="5">
						<input id="updateId" name="relIds" type="hidden" value="" />
						<iframe class="frm_file" marginheight="0" frameborder="0" src="<%=basePath %>fileList.action?name=TbFoundation&ele=updateId&id=${foundId}">
						</iframe>
					</td>
				</tr>
			</table>
			<my:timeView idField="foundId" table="TbFoundation" value="${bean.foundId}"></my:timeView>
			<div class="lyt_submit" style="width: 760px; text-align: right;">
				<input type="submit" value="提交" />
				<input type="reset" value="重置" />
			</div>
		</form>
		</div>
	</body>
</html>