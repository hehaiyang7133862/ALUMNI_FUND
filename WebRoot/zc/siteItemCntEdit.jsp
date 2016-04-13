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
		<script type="text/javascript">
		function checkContentType(){
			if($('input[name="contentType"]:checked').val()=='1'){
				$('#contentCntTr').show();
			}else{
				$('#contentCntTr').hide();
			}
			if($('input[name="contentType"]:checked').val()=='9'){
				$('#linkUrlTr').show();
				$('#linkUrl').focus();
			}else{
				$('#linkUrlTr').hide();
			}
		}
		var cntEditor;
		$(document).ready(function(){
		  	KindEditor.ready(function(K) {
				cntEditor = K.create("textarea[name='contentCnt']", {
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
			var logoVal = $('#contentLogo').val();
			if(logoVal != ""){
	   			var logoValype = logoVal.split(".")[logoVal.split(".").length-1].toLowerCase();
				if(logoValype!="gif" && logoValype!="jpg" && logoValype!="jpeg" && logoValype!="png" && logoValype!="bmp"){
					MyMsg.alert("图标只允许jpg,png,bmp,jpeg,gif格式!");
					return false;
				}
			}
			if($("#contentIntro").val().length > 1000){
				MyMsg.alertFn('简介必须在1000字以内！',function(){
					$("#contentIntro").focus();
				});
				return false;
			}
			if($("#memo").val().length > 1000){
				MyMsg.alertFn('备注说明必须在1000字以内！',function(){
					$("#memo").focus();
				});
				return false;
			}
			cntEditor.sync();
			return true;
		}
		</script>
	</head>
	<body>
		<div class="float">
		<form id="formId" name="formId" action="<%=basePath%>siteItemCnt!doEdit.action" method="post" enctype="multipart/form-data" onsubmit="return checkForm(this);">
			<input type="hidden" name="contentId" value="${bean.contentId}" />
			<input type="hidden" name="itemId" value="${bean.itemId}" />
			<table class="lyt_view">
				<c:if test="${not empty bean.contentLogo}">
				<tr>
					<td class="lyt_view_area" style="width:80px;">
						当前图标
					</td>
					<td>
						<img alt="" src="<%=basePath%><c:out value="${bean.contentLogo}"/>" style="height:50px;"/>
					</td>
				</tr>
				</c:if>
				<tr>
					<td class="lyt_view_area" style="width:80px;">
						图标
					</td>
					<td width="710">
						<input type="file" id="contentLogo" name="contentLogo"/>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area" style="width:80px;">
						标题
					</td>
					<td>
						<input type="text" id="contentTitle" name="contentTitle" style="width:700px;" value="<c:out value="${bean.contentTitle}"/>" maxLength="100"/>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area" style="width:80px;">
						简介
					</td>
					<td>
						<textarea id="contentIntro" name="contentIntro" style="width:700px;height:60px;" onclick="myArea('contentIntro',1000);" onkeyup="myArea('contentIntro',1000);" onkeydown="myArea('contentIntro',1000);" onchange="myArea('contentIntro',1000);"><c:out value='${bean.contentIntro}' /></textarea>
					</td>
				</tr>
			  	<tr>
			    	<td class="lyt_view_note">类型</td>
			    	<td>
			    		<label for="contentType1">
			    			<input type="radio" id="contentType1" name=contentType value="1" <c:if test="${empty bean.contentType || bean.contentType=='1'}">checked</c:if> onclick="checkContentType();" class="checkbox" style="width:auto;height:auto;" />
			    			有内容
			    		</label>
			    		<label for="contentType2">
			    			<input type="radio" id="contentType2" name="contentType" value="2" <c:if test="${bean.contentType=='2'}">checked</c:if> onclick="checkContentType();" class="checkbox" style="width:auto;height:auto;" />
			    			无内容
			    		</label>
			    		<label for="contentType9">
			    			<input type="radio" id="contentType9" name="contentType" value="9" <c:if test="${bean.contentType=='9'}">checked</c:if> onclick="checkContentType();" class="checkbox" style="width:auto;height:auto;" />
			    			链接
			    		</label>
			    	</td>
			  	</tr>
			  	<tr id="linkUrlTr" <c:if test="${bean.contentType!='9'}">style="display:none;"</c:if>>
			    	<td class="lyt_view_note">链接地址</td>
			    	<td>
			    		<input type="text" id="linkUrl" name="linkUrl" value="<c:out value="${bean.linkUrl}"/>" style="width:700px;" maxlength="100" />
			    		<span class="fnt_warn">*</span>
			    	</td>
			  	</tr>
				<tr id="contentCntTr" <c:if test="${not empty bean.contentType && bean.contentType!='1'}">style="display:none;"</c:if>>
					<td class="lyt_view_area">
						正文内容
					</td>
					<td>
						<textarea id="contentCnt" name="contentCnt" style="width:698px;height:300px;visibility:hidden;"><c:out value='${bean.contentCnt}'/></textarea>
					</td>
				</tr>
			  	<tr>
			    	<td class="lyt_view_note">对外发布</td>
			    	<td>
			    		<label for="publishFlag">
			    			<input type="checkbox" id="publishFlag" name="publishFlag" value="1" <c:if test="${bean.publishFlag=='1'}">checked</c:if> class="checkbox" style="width:auto;height:auto;" />
			    			对外显示发布
			    		</label>
			    		<span style="margin-left:20px;">
			    			发布时间
			    			<input style="width:132px;" type="text" id="publishTime" name="publishTime" readonly="true" class="lyt_search_date" value='<fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${bean.publishTime}'/>' />
							<img onClick="WdatePicker({el:'publishTime',dateFmt:'yyyy-MM-dd HH:mm'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle" />
			    		</span>
			    	</td>
			  	</tr>
				<tr>
					<td class="lyt_view_area">
						备注
					</td>
					<td>
						<textarea id="memo" name="memo" style="width:700px;height:50px;" onclick="myArea('memo',1000);" onkeyup="myArea('memo',1000);" onkeydown="myArea('memo',1000);" onchange="myArea('memo',1000);"><c:out value='${bean.memo}' /></textarea>
					</td>
				</tr>
			</table>
			<my:timeView idField="contentId" table="TbSiteContent" value="${bean.contentId}"></my:timeView>
			<div class="lyt_submit" style="width: 780px; text-align: right;">
				<input type="submit" value="提交" />
				<input type="button" value="取消" onclick="parent.MyFormWin.close();return false;" />
			</div>
		</form>
		</div>
	</body>
</html>