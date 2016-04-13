<%@ include file="../common/include.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript">
var strSubMenus;
var allMenuClickNum;
$(document).ready(function(){
	var strSubMenu = "<%=request.getAttribute("list_sub_all")%>";
	strSubMenus = strSubMenu.split("===");
	allMenuClickNum = new Array();
	for(i=0;i<strSubMenus.length;i++){
		allMenuClickNum[i] = 1;
	}	
});
function contains(string,substr,isIgnoreCase)
{
    if(isIgnoreCase)
    {
     string=string.toLowerCase();
     substr=substr.toLowerCase();
    }
     var startChar=substr.substring(0,1);
     var strLen=substr.length;
         for(var j=0;j<string.length-strLen+1;j++)
         {
             if(string.charAt(j)==startChar)//如果匹配起始字符,开始查找
             {
                   if(string.substring(j,j+strLen)==substr)//如果从j开始的字符与str匹配，那ok
                   {
                         return true;
                   }  
             }
         }
         return false;
}

function allChecked(level){
	if(allMenuClickNum[level] % 2 == 0){
		var eles=document.getElementsByName("menuId");
		for(var i=0;i<eles.length;i++){
			if(contains(strSubMenus[level],","+eles[i].value+",",true)==true && eles[i].disabled==""){
				eles[i].checked = false;
			}
		}
	}else{
		var eles=document.getElementsByName("menuId");
		for(var i=0;i<eles.length;i++){
			if(contains(strSubMenus[level],","+eles[i].value+",",true)==true && eles[i].disabled==""){
				eles[i].checked = true;
			}
		}	
	}
	allMenuClickNum[level]++;
}
</script>
</head> 
<body>
<%@ include file="roleInfo.jsp"%>
<form action="<%=basePath%>menuPrivilege.action" method="post">
<input type="hidden" name="roleId" value="<c:out value='${bean_role.roleId}' />" />
<input type="hidden" name="grandId" value="<c:out value='${grandId}' />" />
<input type="hidden" name="code" value='<%=request.getParameter("code")%>' />
<c:forEach items="${list_menu}" var="tmp1">
<c:if test="${tmp1.numLevel==1}">
<div style="font-weight:bold;margin-top:5px;margin-left:5px;">
<c:choose>
<c:when test='${fn:contains(list_sel,tmp1.menuId)}'>
${tmp1.menuName}
<input name="menuId" type="checkbox" value="${tmp1.menuId}"  checked/>
</c:when>
<c:otherwise>
${tmp1.menuName}<input name="menuId" type="checkbox" value="${tmp1.menuId}"/>
</c:otherwise>
</c:choose>
</div>
    <c:forEach items="${list_menu}" var="tmp2">
	<c:if test="${tmp2.numLevel==2 && tmp2.parentId==tmp1.menuId}">
    <div style="font-weight:bold;margin-top:5px;margin-left:10px;">
    <c:choose>
	<c:when test='${fn:contains(list_sel,tmp2.menuId)}'>
	   ${tmp2.menuName}<input name="menuId" type="checkbox" value="${tmp2.menuId}" checked/>
	</c:when>
	<c:otherwise>
	   ${tmp2.menuName}<input name="menuId" type="checkbox" value="${tmp2.menuId}"/>
	</c:otherwise>
	</c:choose>
    </div>
        <div style="margin-top:5px;margin-left:15px;">
        <c:forEach items="${list_menu}" var="tmp3">
		<c:if test="${tmp3.numLevel==3 && tmp3.parentId==tmp2.menuId}">
		<c:choose>
		<c:when test='${fn:contains(list_sel,tmp3.menuId)}'>
		<span style="width:150px;">
		<input name="menuId" type="checkbox" value="${tmp3.menuId}" checked/> ${tmp3.menuName}
		</span>
		</c:when>
		<c:otherwise>
		<span style="width:150px;">
		<input name="menuId" type="checkbox" value="${tmp3.menuId}"/> ${tmp3.menuName}
		</span>
		</c:otherwise>
		</c:choose>
        </c:if>
        </c:forEach>
       </div>
    </c:if>
    </c:forEach>
</c:if>
</c:forEach>
<div class="lyt_submit">
	<input type="submit" value="确定" />
    <input type="reset" value="重置" />
</div>
</form>
</body>
</html>