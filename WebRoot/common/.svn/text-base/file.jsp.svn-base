<%@ include file="include.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String pathFrame = request.getContextPath();
String basePathFrame = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathFrame+"/";
// 得到参数
Object tab=request.getParameter("tab");
Object id=request.getParameter("id");
if(null==id){
	id="";
}
String fd="0";
if(null!=id && !"".equals(id)){
	fd="1";
}
else{
	fd="0";
}
%>
<input id="uploadEleNameId" name="uploadEleName" type="hidden" value="" />
<iframe marginheight="0" frameborder="0" width="100%" height="23" onload="setFrmHeight(this)" onresize="setFrmHeight(this)"
	src="<%=basePathFrame%>fileList.action?tab=<%=tab%>&id=<%=id%>&fd=<%=fd%>">
</iframe>