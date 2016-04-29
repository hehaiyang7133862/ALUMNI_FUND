<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"; %>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<%@ include file="../common/calendar.jsp"%>
<%@ include file="../common/kindeditor.jsp"%>
<script type="text/javascript">
	<c:if test="${not empty bean.foundId && opt=='insert'}">
		parent.updateTabTitle('项目【${bean.foundId}】管理', '项目【${bean.foundId}】管理');
	</c:if>
	var memoEditor,proEditor;
	$(document).ready(function() {
		KindEditor.ready(function(K) {
			roEditor = K.create("textarea[name='proContent']", {
						items : [ 'source', '|', 'undo', 'redo', '|',
								'preview', 'print', 'cut', 'copy', 'paste',
								'plainpaste', 'wordpaste', '|', 'justifyleft',
								'justifycenter', 'justifyright', 'justifyfull',
								'insertorderedlist', 'insertunorderedlist',
								'indent', 'outdent', 'subscript',
								'superscript', 'clearhtml', 'quickformat',
								'selectall', '|', 'fullscreen', '/',
								'formatblock', 'fontname', 'fontsize', '|',
								'forecolor', 'hilitecolor', 'bold', 'italic',
								'underline', 'strikethrough', 'lineheight',
								'removeformat', '|', 'image', 'flash', 'media',
								'insertfile', 'table', 'hr', 'emoticons',
								'map', 'link', 'unlink' ],
						langType : 'zh_CN',
						resizeType : 1,
						themeType : 'simple',
						allowFileManager : false,
						imageTabIndex : 1
					});
			memoEditor = K.create("textarea[name='foundMemo']", {
						items : [ 'source', '|', 'undo', 'redo', '|',
								'preview', 'print', 'cut', 'copy', 'paste',
								'plainpaste', 'wordpaste', '|', 'justifyleft',
								'justifycenter', 'justifyright', 'justifyfull',
								'insertorderedlist', 'insertunorderedlist',
								'indent', 'outdent', 'subscript',
								'superscript', 'clearhtml', 'quickformat',
								'selectall', '|', 'fullscreen', '/',
								'formatblock', 'fontname', 'fontsize', '|',
								'forecolor', 'hilitecolor', 'bold', 'italic',
								'underline', 'strikethrough', 'lineheight',
								'removeformat', '|', 'image', 'flash', 'media',
								'insertfile', 'table', 'hr', 'emoticons',
								'map', 'link', 'unlink' ],
						langType : 'zh_CN',
						resizeType : 1,
						themeType : 'simple',
						allowFileManager : false,
						imageTabIndex : 1
					});
			});
		});
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
			var str="found!findObject.action?comIds="+jzfId+"&oldType=${beanProtocol.jzfxz}&type="+type;
			MyFormWin.showMyWin("捐赠方选择",str,750,550);
		}
		function setJzfNames(ids,name,type){
		    document.getElementById("jzfObject").value=ids;
		    document.getElementById("jzfObjectView").value=name;
		    $("#jzfxz").val(type);
		}
	// 验证表单
	function checkForm(form) {
		formFormat("formId");
		if ($("#foundName").val() == "") {
			detailOn($("#title1"));
			MyMsg.alertFn('请输入项目名称！', function() {
				$("#foundName").focus();
			});
			return false;
		}
		if ($("#foundDis").val().length > 1000) {
			detailOn($("#title1"));
			MyMsg.alertFn('项目摘要必须在1000字以内！', function() {
				$("#foundDis").focus();
			});
			return false;
		}
		<c:if test="${empty bean.foundId}">
		if ($("#proName").val() == "") {
			detailOn($("#title2"));
			MyMsg.alertFn('请选择协议名称！', function() {
				$("#proName").focus();
			});
			return false;
		}
		</c:if>
		memoEditor.sync();
		return true;
	}
	
	function detailOn(obj){
		$('#detailTitleUl').children().css({'color':'','background':'url(<%=basePath%>UI/images/nav_point.png) no-repeat top left'});
		$('#detailContentDiv').children().hide();
		
		var titleObj = $(obj);
		var contentObj = $('#detailContentDiv table:eq('+titleObj.prevAll().length+')');
		
		titleObj.css({'color':'#fff','background':'url(<%=basePath%>UI/images/nav_point_on.png) no-repeat top left'
				});
		contentObj.show();
		contentObj.find('input[name="detailTitle"]').select();
	}
</script>
</head>
<body>
	<div class="page">
		<%@ include file="../common/navigation.jsp"%>
		<c:choose>
			<c:when test="${not empty bean.foundId}">
				<%@ include file="fundMenu.jsp"%>
			</c:when>
			<c:otherwise>
				<table width="875" style="margin-left:5px;margin-top:5px;" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td class="nav_GA_left" width="2" height="28"></td>
						<td class="nav_GA_center">
							<div class="nav_GA">
								<ul id="detailTitleUl">
									<li onclick="detailOn(this);" id="title1"
										style="line-height:22px;text-align:center;cursor:pointer;color:#fff;background:url(<%=basePath%>UI/images/nav_point_on.png) no-repeat top left;">
										基本信息</li>
									<li onclick="detailOn(this);" id="title2"
										style="line-height:22px;text-align:center;cursor:pointer;">
										协议信息</li>
								</ul>
							</div>
						</td>
					</tr>
				</table>
			</c:otherwise>
		</c:choose>
		<c:if test="${not empty bean.foundId}"></c:if>
		<form id="formId" name="formId"
			action="<%=basePath%>found!saveOrUpdate.action" method="post"
			onsubmit="return checkForm(this);">
			<input type="hidden" name="foundId" value="${bean.foundId}" />
			<div id="detailContentDiv" style="overflow:hidden;">
				<table class="lyt_view">
					<tr>
						<td class="lyt_view_note" style="width: 80px;">项目名称 <span>*</span>
						</td>
						<td colspan="3"><input style="width: 415px" id="foundName"
							name="foundName" type="text"
							value='<c:out value="${bean.foundName}" />' maxlength="100" /></td>
						<td class="lyt_view_note" style="width: 80px;">创建时间</td>
						<td style="width: 160px;"><input style="width: 132px;"
							type="text" name="foundCreateDate" id="foundCreateDate"
							readonly="true" class="lyt_search_date"
							value='<c:out value="${bean.foundCreateDate}" />' /> <img
							onClick="WdatePicker({el:'foundCreateDate'})"
							src="<%=basePath%>UI/images/date_picker.gif" width="16"
							height="22" align="absmiddle" /></td>
					</tr>
					<tr>
						<td class="lyt_view_note" style="width: 80px;">项目编号</td>
						<td style="width: 160px;"><input style="width: 155px;"
							id="foundCode" name="foundCode" type="text"
							value='<c:out value="${bean.foundCode}" />' maxlength="100" /></td>
						<td class="lyt_view_note" style="width: 80px;">项目类别</td>
						<td><select id="foundtypeFid" name="foundtypeFid">
								<option value="">--请选择--</option>
								<my:fundField type="OPTION" lead="FOUNDATION_TYPE"
									value="${bean.foundtypeFid}" />
						</select>
						</td>
						<td class="lyt_view_note" style="width: 80px;">项目级别</td>
						<td><select id="foundLevel" name="foundLevel">
								<option value="">--请选择--</option>
								<my:fundField type="OPTION" lead="FOUNDATION_LEVEL"
									value="${bean.foundLevel}" />
						</select>
						</td>
					</tr>
					<tr>
						<td class="lyt_view_note" style="width: 80px;">所属分类</td>
						<td colspan="3"><select style="width: 415px" name="typeId"
							id="typeId">
								<option value="">--请选择--</option>
								<c:forEach items="${typeList}" var="temp" varStatus="status">
									<option value="${temp.typeId}"
										<c:if test="${temp.typeId==bean.foundType}">selected</c:if>>${temp.typeName}</option>
								</c:forEach>
						</select>
						</td>
						<td class="lyt_view_note" style="width: 80px;">是否留本</td>
						<td style="width: 160px;"><select id="isKeep" name="isKeep">
								<option value="">--请选择--</option>
								<option value="1"
									<c:if test="${bean.isKeep=='1'}">selected</c:if>>是</option>
								<option value="2"
									<c:if test="${bean.isKeep=='2'}">selected</c:if>>否</option>
						</select>
						</td>
					</tr>
					<tr>
						<td class="lyt_view_note" style="width: 80px;">受惠对象</td>
						<td colspan="3"><select style="width: 415px" id="foundObject"
							name="foundObject">
								<option value="">--请选择--</option>
								<my:fundField type="OPTION" lead="ACCEPT_OBJECT"
									value="${bean.foundObject}" />
						</select>
						</td>
						<td class="lyt_view_note" style="width: 80px;">项目状态</td>
						<td><select id="status" name="status">
								<option value="">--请选择--</option>
								<my:fundField type="OPTION" lead="FOUNDATION_STATUS"
									value="${bean.foundStatus}" />
						</select>
						</td>
					</tr>
					<tr>
						<td class="lyt_view_area" style="width:80px;">项目摘要</td>
						<td colspan="5"><textarea id="foundDis" name="foundDis"
								style="width:670px;height:80px;"
								onclick="myArea('foundDis',1000);"
								onkeyup="myArea('foundDis',1000);"
								onkeydown="myArea('foundDis',1000);"
								onchange="myArea('foundDis',1000);"><c:out value='${bean.foundDis}' /></textarea>
						</td>
					</tr>
					<tr>
						<td class="lyt_view_area" style="width: 80px;">项目说明</td>
						<td colspan="5"><textarea name="foundMemo" id="foundMemo"
								style="width: 670px; height: 300px;visibility:hidden;"><c:out value='${bean.foundMemo}' /></textarea>
						</td>
					</tr>
					<tr>
						<td class="lyt_view_area" style="width:80px;">附件</td>
						<td colspan="5"><input id="updateId" name="relIds"
							type="hidden" value="" /> <iframe class="frm_file"
								marginheight="0" frameborder="0"
								src="<%=basePath %>fileList.action?name=TbFoundation&ele=updateId&id=${foundId}">
							</iframe>
						</td>
					</tr>
				</table>
				<table class="lyt_view" style="display:none;">
					<tr>
						<td class="lyt_view_note">协议编号</td>
						<td colspan="3"><input style="width: 350px;" id="proCode"
							name="proCode" type="text" maxlength="100" />
						</td>
						<td class="lyt_view_note">协议状态</td>
						<td><select style="width:200px;" name="proStatus">
								<option value="">--请选择--</option>
								<my:fundField type="OPTION" lead="PROTOCOL_STATUS" />
						</select>
						</td>
					</tr>
					<tr>
						<td class="lyt_view_note">协议名称 <span>*</span>
						</td>
						<td colspan="3"><input style="width: 350px;" id="proName"
							name="proName" type="text" maxlength="100" />
						</td>
						<td class="lyt_view_note">捐赠方性质</td>
						<td><select id="jzfxz" style="width:200px;" name="jzfxz">
								<option value="">--请选择--</option>
								<option value="1">单位</option>
								<option value="2">个人</option>
						</select>
						</td>
					</tr>
					<tr>
						<td class="lyt_view_note">捐赠方</td>
						<td colspan="3"><input style="width: 296px;" type="text"
							name="jzfObjectView" id="jzfObjectView" value="${jzfObject}"
							readonly="readonly" /> <input type="hidden" name="jzfObject"
							id="jzfObject" value="${beanProtocol.jzfObject}" /> <input
							type="button" value="选择"
							style="width:50px;height:22px;line-height:18px;vertical-align:top;"
							onclick="openwindowjzf();" />
						</td>
						<td class="lyt_view_note">捐赠类型</td>
						<td><select style="width:200px;" name="jzType">
								<option value="">--请选择--</option>
								<my:fundField type="OPTION" lead="TYPE_DOCATION" />
						</select>
						</td>
					</tr>
					<tr>
						<td class="lyt_view_note">受赠方</td>
						<td colspan="3"><input style="width: 350px;" id="szfObject"
							name="szfObject" value='<my:titleTag/>' type="text"
							maxlength="100" />
						</td>
						<td class="lyt_view_note">捐赠来源</td>
						<td><select style="width:200px;" name="jzSource">
								<option value="">--请选择--</option>
								<my:fundField type="OPTION" lead="DONATION_SOURCE" />
						</select>
						</td>
					</tr>
					<tr>
						<td class="lyt_view_area">使用方</td>
						<td colspan="5"><input id="syfObject" name="syfObject"
							type="hidden" value='<c:out value="${beanProtocol.syfObject}" />' />
							<div id="syfNames"
								style="width: 638px;border: 1px solid #BDC7D8;background-color:#E5E5E5;height: 50px;line-height: 20px;float: left;overflow:auto;">
								<c:forEach var="temp" items="${syfList}">
									<a href="javascript:;"
										onclick="MyFormWin.showMyWin('接受单位详细','<%=basePath%>donate!toAcceptAdd.action?comId=${temp.comId}',580,380);return false;"
										style="padding-left: 5px;">${temp.comName}-${temp.relationName}</a>
								</c:forEach>
							</div>
							<div style="width: 58px;float: left;margin-left:2px;">
								<input type="button" value="选择"
									style="width:50px;height:22px;line-height:18px;vertical-align:top;"
									onclick="openwindowsyf()" />
							</div>
						</td>
					</tr>
					<tr>
						<td class="lyt_view_note" style="width: 80px;">协议金额</td>
						<td style="width: 146px;"><input style="width: 120px;"
							id="proAmount" name="proAmount" onkeyup="limit(this)" type="text"
							maxlength="100" /> 元</td>
						<td class="lyt_view_note" style="width: 60px;">签约日期</td>
						<td style="width: 156px;"><input style="width: 100px;"
							type="text" name="dealDate" id="dealDate" readonly="true"
							class="lyt_search_date" /> <img
							onClick="WdatePicker({el:'dealDate'})"
							src="<%=basePath%>UI/images/date_picker.gif" width="16"
							height="22" align="absmiddle" />
						</td>
						<td class="lyt_view_note" style="width: 80px;">执行日期</td>
						<td style="width: 245px;"><input style="width: 70px;"
							type="text" name="startTime" id="startTime" readonly="true"
							class="lyt_search_date" /> <img
							onClick="WdatePicker({el:'startTime'})"
							src="<%=basePath%>UI/images/date_picker.gif" width="16"
							height="22" align="absmiddle" /> 至 <input style="width: 70px;"
							type="text" name="endTime" id="endTime" readonly="true"
							class="lyt_search_date" /> <img
							onClick="WdatePicker({el:'endTime'})"
							src="<%=basePath%>UI/images/date_picker.gif" width="16"
							height="22" align="absmiddle" />
						</td>
					</tr>
					<tr>
						<td class="lyt_view_area">协议主要内容</td>
						<td colspan="5"><textarea name="proContent" id="proContent"
								style="width: 690px; height: 300px; visibility: hidden;"></textarea>
						</td>
					</tr>
				</table>
			</div>
			<my:timeView idField="foundId" table="TbFoundation"
				value="${bean.foundId}"></my:timeView>
			<div class="lyt_submit" style="width: 760px; text-align: right;">
				<input type="submit" value="提交" /> <input type="reset" value="重置" />
			</div>
		</form>
	</div>
</body>
</html>