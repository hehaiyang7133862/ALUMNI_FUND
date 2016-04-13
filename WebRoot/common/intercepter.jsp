<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script language="javascript">
    parent.parent.location.href="<%=basePath%>login.action";
</script>
