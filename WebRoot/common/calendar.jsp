
<%
	String pathCalendar = request.getContextPath();
	String basePathCalendar = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ pathCalendar + "/";
%>
<%@ page language="java" pageEncoding="UTF-8"%>
<script language="JavaScript" type="text/javascript"
	src="<%=basePathCalendar%>UI/calendar/WdatePicker.js"></script>