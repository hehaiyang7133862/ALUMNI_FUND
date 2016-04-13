<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*,com.laungee.proj.common.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<style type="text/css">
.tab_bar_bg{
    background-color: #F2F9FD;
	BORDER-LEFT: #6fb4db 0px solid;
	BORDER-RIGHT:#6fb4db 0px solid; 
	BORDER-TOP:#6fb4db 0px solid; 
	BORDER-BOTTOM: #6fb4db 1px solid; 
	FONT-WEIGHT:bold; FONT-SIZE: 14px;
	TEXT-INDENT: 0px; 
	FONT-FAMILY: 宋体; 
	HEIGHT: 24px;
	padding:3px 3px 3px 0px;
	width:100%;
	color:#0080FF;
	
}
    </style>
<%@ include file="../common/head.jsp"%>
<script language="javascript">
</script>
</head>
<body>
<div class="page">
<br>
<c:if test='${fn:contains(role_menu,"serch_alumni")||fn:contains(role_menu,"check_unalumni")}'>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr  >
    <td  class="tab_bar_bg" align="left">校友信息审核</td>
    <td></td>
  </tr>
</table>
<br>
</c:if>

 <c:if test='${fn:contains(role_menu,"check_unalumni")}'>
    <c:if test="${unalumnicounta!='0'}">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td width="16%">您有<font color="#FF0033"><c:out value="${unalumnicounta}"></c:out></font>条待审核校友
      </td>
      <td width="50%" align="left"><a href="<%=basePath%>apprecordNav.action?url=checkUnList.action?stateCid=3&code=check_unalumni"><font color="#6633FF">点击进入 >></font></a></td>
    </tr>
    </table>
    <br>
    </c:if>
    <c:if test="${unalumnicounta=='0'}">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td width="16%">目前没有待审核校友
      </td>
      <td width="50%" align="left"><a href="<%=basePath%>apprecordNav.action?url=checkUnList.action&code=check_unalumni"><font color="#6633FF">点击进入 >></font></a></td>
    </tr>
    </table>
    <br>
    </c:if>
    </c:if>
  <c:if test='${fn:contains(role_menu,"check_unalumni")}'>
     <c:if test="${unalumnicountb!='0'}">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td width="16%">您有<font color="#FF0033"><c:out value="${unalumnicountb}"></c:out></font>条再次审核校友
      </td>
      <td width="50%" align="left"><a href="<%=basePath%>apprecordNav.action?url=checkUnList.action?stateCid=2&code=check_unalumni"><font color="#6633FF">点击进入 >></font></a></td>
    </tr>
  </table>
  <br>
  </c:if>
  <c:if test="${unalumnicountb=='0'}">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
       <td width="16%">目前没有再次审核校友
      </td>
      <td width="50%" align="left"><a href="<%=basePath%>apprecordNav.action?url=checkUnList.action&code=check_unalumni"><font color="#6633FF">点击进入 >></font></a></td>
    </tr>
  </table>
  <br>
  </c:if>
   </c:if>
<c:if test='${fn:contains(role_menu,"email_app")||fn:contains(role_menu,"community_app")||fn:contains(role_menu,"colligate_service")||fn:contains(role_menu,"manuscript")}'>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr  >
    <td  class="tab_bar_bg" align="left">校友服务</td>
    <td  ></td>
  </tr>
</table>
<br>
</c:if>  

<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td align="left" valign="top">
<c:if test='${fn:contains(role_menu,"serch_alumni")}'>
<c:if test="${seekcount!='0'}">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td width="16%">您有<font color="#FF0033"><c:out value="${seekcount}"></c:out></font>条校友查询申请
      </td>
      <td width="50%" align="left"><a href="<%=basePath%>apprecordNav.action?url=dolistTbseek!dolistTbseek.action?appstatus=1&code=serch_alumni"><font color="#6633FF">点击进入 >></font></a></td>
    </tr>
   </table>
   <br>
    </c:if>
    <c:if test="${seekcount=='0'}">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
       <td width="16%">目前没有校友查询申请
      </td>
      <td width="50%" align="left"><a href="<%=basePath%>apprecordNav.action?url=dolistTbseek!dolistTbseek.action&code=serch_alumni"><font color="#6633FF">点击进入 >></font></a></td>
    </tr>
   </table>
   <br>
    </c:if>
    </c:if>
   <c:if test='${fn:contains(role_menu,"email_app")}'>
    <c:if test="${emailcount!='0'}">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
     <tr>
     <td width="16%">您有<font color="#FF0033"><c:out value="${emailcount}"></c:out></font>条电子邮箱申请
      </td>
      <td width="50%" align="left"><a href="<%=basePath%>apprecordNav.action?url=dolistEmailapp!dolistEmailapp.action?appstatus=1&code=email_app"><font color="#6633FF">点击进入 >></font></a></td>
    </tr>
   </table>
   <br>
   </c:if>
   <c:if test="${emailcount=='0'}">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
     <tr>
      <td width="16%">目前没有电子邮箱申请
      </td>
      <td width="50%" align="left"><a href="<%=basePath%>apprecordNav.action?url=dolistEmailapp!dolistEmailapp.action&code=email_app"><font color="#6633FF">点击进入 >></font></a></td>
    </tr>
   </table>
   <br>
   </c:if>
    </c:if>
  <c:if test='${fn:contains(role_menu,"colligate_service")}'>
  <c:if test="${sercount!='0'}">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td width="16%">您有<font color="#FF0033"><c:out value="${sercount}"></c:out></font>条校友服务申请
      </td>
      <td width="50%" align="left"><a href="<%=basePath%>apprecordNav.action?url=dolistTbalumniservice!dolistTbalumniservice.action?appstatus=1&code=colligate_service"><font color="#6633FF">点击进入 >></font></a></td>
    </tr>
   </table>
   <br>
    </c:if>
    <c:if test="${sercount=='0'}">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
       <td width="16%">目前没有校友服务申请
      </td>
      <td width="50%" align="left"><a href="<%=basePath%>apprecordNav.action?url=dolistTbalumniservice!dolistTbalumniservice.action&code=colligate_service"><font color="#6633FF">点击进入 >></font></a></td>
    </tr>
   </table>
   <br>
    </c:if>
    </c:if>
 <c:if test='${fn:contains(role_menu,"manuscript")}'>
  <c:if test="${manucount!='0'}">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td width="16%">您有<font color="#FF0033"><c:out value="${manucount}"></c:out></font>条稿件申请
      </td>
      <td width="50%" align="left"><a href="<%=basePath%>apprecordNav.action?url=manuscriptList.action?stateFid1=261&code=manuscript"><font color="#6633FF">点击进入 >></font></a></td>
    </tr>
   </table>
   <br>
    </c:if>
    <c:if test="${manucount=='0'}">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
       <td width="16%">目前没有校友投稿
      </td>
      <td width="50%" align="left"><a href="<%=basePath%>apprecordNav.action?url=manuscriptList.action&code=manuscript"><font color="#6633FF">点击进入 >></font></a></td>
    </tr>
   </table>
   <br>
    </c:if>
    </c:if>
   
    
<c:if test='${fn:contains(role_menu,"community_app")}'>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr  >
    <td  class="tab_bar_bg" align="left">校友社区</td>
    <td  ></td>
  </tr>
</table>
<br>
</c:if>
<c:if test='${fn:contains(role_menu,"community_app")}'>
  <c:if test="${commcount!='0'}">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td width="16%">您有<font color="#FF0033"><c:out value="${commcount}"></c:out></font>条校友社区申请
      </td>
      <td width="50%" align="left"><a href="<%=basePath%>apprecordNav.action?url=dolistcommunityappcl!dolistcommunityappcl.action?appstatus=1&code=community_app"><font color="#6633FF">点击进入 >></font></a></td>
    </tr>
   </table>
   <br>
    </c:if>
    <c:if test="${commcount=='0'}">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
       <td width="16%">目前没有校友社区申请
      </td>
      <td width="50%" align="left"><a href="<%=basePath%>apprecordNav.action?url=dolistcommunityappcl!dolistcommunityappcl.action&code=community_app"><font color="#6633FF">点击进入 >></font></a></td>
    </tr>
   </table>
   <br>
    </c:if>
    </c:if>
</div>
</body>
</html>