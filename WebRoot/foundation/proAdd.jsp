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
		var proEditor;
		$(document).ready(function(){
			<c:if test="${not empty bean.proId}">
				parent.updateTabTitle('协议【${bean.proId}】管理', '协议【${bean.proId}】管理');			
			</c:if>
			KindEditor.ready(function(K) {
				proEditor = K.create("textarea[name='proContent']", {
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
			if($("#proName").val() == ""){
				MyMsg.alertFn('请选择协议所属项目！',function(){
					$("#proName").focus();
				});
				return false;
			}
			if($("#foundName").val() == ""){
				MyMsg.alertFn('请输入协议名称！',function(){
					$("#foundName").focus();
				});
				return false;
			}
			return true;
		}
		//选择所属项目 
		function openwindowssxm(){
			MyFormWin.showMyWin('项目选择','<%=basePath%>found!chooseFound.action?flag=pro',750,550);
		}
		function setFound(id,name){
		    document.getElementById("foundId").value=id;
		    document.getElementById("foundName").value=name;
		}
		//选择使用方
		function openwindowsyf(){
			var syfId=document.getElementById("syfObject").value;
			syfId=syfId.trim();
			var str="found!findManyObject.action?comIds="+syfId;
			MyFormWin.showMyWin("使用方选择",str,650,440);
		}
		function setNames(ids,name){
		    document.getElementById("syfObject").value=ids;
		   	var arrIds=ids.split(',');
		    var arrName=name.split(',');
		    var inner1='';
		    var base='<%=basePath%>';
		    for(var i=0;i<arrName.length;i++){
		    	var bean=arrName[i];
		    	var beanid=arrIds[i];
		    	inner1+="<a href=javascript:; style=\"padding-left: 5px;\" onclick=\"MyFormWin.showMyWin('接受单位详细','<%=basePath%>donate!toAcceptAdd.action?comId="+beanid+"',580,380);return false;\">"+bean+
				"</a>";
		    }
		    document.getElementById("syfNames").innerHTML=inner1;
		}
		//选择捐赠方
		function openwindowjzf(){
			var jzfId=document.getElementById("jzfObject").value;
			jzfId=jzfId.trim();
			var type=$("#jzfxz").val();
			if(type==""){
				type="1";
			}
			var str="found!findObject.action?comIds="+jzfId+"&oldType=${bean.jzfxz}&type="+type;
			MyFormWin.showMyWin("捐赠方选择",str,750,550);
		}
		function setJzfNames(ids,name,type){
		    document.getElementById("jzfObject").value=ids;
		    document.getElementById("jzfObjectView").value=name;
		    $("#jzfxz").val(type);
		}
		</script>
	</head>
	<body>
		<div class="page">
		<%@ include file="../common/navigation.jsp"%>
		<c:if test="${not empty bean.proId}">
		<%@ include file="proMenu.jsp"%>
		</c:if>
		<form id="formId" name="formId" action="<%=basePath%>found!saveOrUpdatePro.action" method="post" onsubmit="return checkForm(this);">
			<input type="hidden" id="foundId" name="foundId" value="${beanFound.foundId}"/>
			<input type="hidden" name="proId" value="${bean.proId}"/>
			<table class="lyt_view">
				<tr>
					<td class="lyt_view_note">
						所属项目
						<span>*</span>
					</td>
					<td colspan="5">
						<input style="width: 638px;" readonly="readonly" id="foundName" name="foundName" type="text" value='<c:out value="${beanFound.foundName}" />'/>
						<input type="button" value="选择" style="width:50px;height:22px;line-height:18px;vertical-align:top;" onclick="openwindowssxm();"/>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						协议编号
					</td>
					<td colspan="3">
						<input style="width: 350px;" id="proCode" name="proCode" type="text" value='<c:out value="${bean.proCode}" />' maxlength="100" />
					</td>
					<td class="lyt_view_note">
						协议状态
					</td>
					<td>
						<select style="width:200px;" name="proStatus">
							<option value="">--请选择--</option>
							<my:fundField type="OPTION" lead="PROTOCOL_STATUS" value="${bean.proStatus}" />
						</select>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						协议名称
						<span>*</span>
					</td>
					<td colspan="3">
						<input style="width: 350px;" id="proName" name="proName" type="text" value='<c:out value="${bean.proName}" />' maxlength="100" />
					</td>
					<td class="lyt_view_note">
						捐赠方性质
					</td>
					<td>
						<select id="jzfxz" style="width:200px;" name="jzfxz">
							<option value="">--请选择--</option>
							<option value="1" <c:if test="${bean.jzfxz=='1'}">selected</c:if>>单位</option>
							<option value="2" <c:if test="${bean.jzfxz=='2'}">selected</c:if>>个人</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						捐赠方
					</td>
					<td colspan="3">
						<input style="width: 296px;" type="text" name="jzfObjectView" id="jzfObjectView" value="${jzfObject}" readonly="readonly" />
						<input type="hidden" name="jzfObject" id="jzfObject" value="${bean.jzfObject}" />
						<input type="button" value="选择" style="width:50px;height:22px;line-height:18px;vertical-align:top;" onclick="openwindowjzf();"/>
					</td>
					<td class="lyt_view_note">
						捐赠类型
					</td>
					<td>
						<select style="width:200px;" name="jzType">
							<option value="">--请选择--</option>
							<my:fundField type="OPTION" lead="TYPE_DOCATION" value="${bean.jzType}" />
						</select>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note">
						受赠方
					</td>
					<td colspan="3">
						<c:choose>
							<c:when test="${not empty proid}">
								<input style="width: 350px;" id="szfObject" name="szfObject" type="text" value='<c:out value="${bean.szfObject}" />' maxlength="100" />
							</c:when>
							<c:otherwise>
								<input style="width: 350px;" id="szfObject" name="szfObject" type="text" value='<my:titleTag/>' maxlength="100" />
							</c:otherwise>
						</c:choose>
						
					</td>
					<td class="lyt_view_note">
						捐赠来源
					</td>
					<td>
						<select style="width:200px;" name="jzSource">
							<option value="">--请选择--</option>
							<my:fundField type="OPTION" lead="DONATION_SOURCE" value="${bean.jzSource}" />
						</select>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">
						使用方
					</td>
					<td colspan="5">
						<input id="syfObject" name="syfObject" type="hidden" value='<c:out value="${bean.syfObject}" />' />
						<div id="syfNames" style="width: 638px;border: 1px solid #BDC7D8;background-color:#E5E5E5;height: 50px;line-height: 20px;float: left;overflow:auto;">
							<c:forEach var="temp" items="${syfList}">
								<a href="javascript:;" onclick="MyFormWin.showMyWin('接受单位详细','<%=basePath%>donate!toAcceptAdd.action?comId=${temp.comId}',580,380);return false;" style="padding-left: 5px;">${temp.comName}-${temp.relationName}</a>
							</c:forEach>
						</div>
						<div style="width: 58px;float: left;margin-left:2px;">
							<input type="button" value="选择" style="width:50px;height:22px;line-height:18px;vertical-align:top;" onclick="openwindowsyf()"/>
						</div>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_note" style="width: 80px;">
						协议金额
					</td>
					<td style="width: 146px;">
						<input style="width: 120px;" id="proAmount" name="proAmount" onkeyup="limit(this)" type="text" value='<c:out value="${bean.proAmount}" />' maxlength="100" />
						元
					</td>
					<td class="lyt_view_note" style="width: 60px;">
						签约日期
					</td>
					<td style="width: 156px;">
						<input style="width: 100px;" type="text" name="dealDate" id="dealDate" readonly="true" class="lyt_search_date" value='<c:out value="${bean.dealDate}" />' />
						<img onClick="WdatePicker({el:'dealDate'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle" />
					</td>
					<td class="lyt_view_note" style="width: 80px;">
						执行日期
					</td>
					<td style="width: 245px;">
						<input style="width: 70px;" type="text" name="startTime" id="startTime" readonly="true" class="lyt_search_date" value='<c:out value="${bean.startTime}" />' />
						<img onClick="WdatePicker({el:'startTime'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle" />
						至
						<input style="width: 70px;" type="text" name="endTime" id="endTime" readonly="true" class="lyt_search_date" value='<c:out value="${bean.endTime}" />' />
						<img onClick="WdatePicker({el:'endTime'})" src="<%=basePath%>UI/images/date_picker.gif" width="16" height="22" align="absmiddle" />
					</td>
					<!-- 
					<td class="lyt_view_note" style="width: 80px;">
						实际金额
					</td>
					<td>
						<input style="width: 169px;" id="toAmount" name="toAmount" onkeyup="limit(this)" type="text" value='<c:out value="${bean.toAmount}" />' maxlength="100" />元
					</td>
					 
					 
					<td class="lyt_view_note" style="width: 80px;">
						是否上架
					</td>
					<td>
						<select name="isShelves">
							<option <c:if test="${bean.isShelves==1}">selected</c:if> value="1">是</option>
							<option <c:if test="${bean.isShelves==0}">selected</c:if> value="0">否</option>
						</select>
					</td>-->
				</tr>
				<tr>
					<td class="lyt_view_area">
						协议主要内容
					</td>
					<td colspan="5">
						<textarea name="proContent" id="proContent" style="width: 690px; height: 300px; visibility: hidden;"><c:out value='${bean.proContent}' /></textarea>
					</td>
				</tr>
				<tr>
					<td class="lyt_view_area">
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
				<input type="reset" value="重置" />
			</div>
		</form>
		</div>
	</body>
</html>