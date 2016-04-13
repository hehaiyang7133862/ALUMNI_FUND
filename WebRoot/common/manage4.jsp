<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="head.jsp"%>
<frameset rows="110,*" cols="*" frameborder="no" border="0" framespacing="0">
    <frame src="<%=basePath%>common/manageTop.jsp" name="manageTop" scrolling="No" noresize="noresize" />
    <frameset cols="230,10,*" frameborder="no" border="0" framespacing="0" id="manageSetId">
        <frame src="<%=basePath%>menuRead.action?forward=4" name="manageLeft"  scrolling="No" noresize="noresize" id="manageLeftId" />
        <frame src="<%=basePath%>common/manageFlex.jsp" name="manageFlex"  scrolling="No" noresize="noresize"  id="manageFlexId" />
        <frame src="<%=basePath%>common/manageMain.jsp" name="manageMain" id="manageMainId" />
    </frameset>
</frameset>