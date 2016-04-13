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
	<script type="text/javascript">
    <!--
    function iframeAutoFit()
    {
        try
        {
            if(window!=parent)
            {
                var a = parent.document.getElementsByTagName("IFRAME");
                for(var i=0; i<a.length; i++) //author:meizz
                {
                    if(a[i].contentWindow==window)
                    {
                        var h1=0, h2=0, d=document, dd=d.documentElement;
                        a[i].parentNode.style.height = a[i].offsetHeight +"px";
                        a[i].style.height = "10px";

                        if(dd && dd.scrollHeight) h1=dd.scrollHeight;
                        if(d.body) h2=d.body.scrollHeight;
                        var h=Math.max(h1, h2);

                        if(document.all) {h += 4;}
                        if(window.opera) {h += 1;}
                        a[i].style.height = a[i].parentNode.style.height = h +"px";
                    }
                }
            }
        }
        catch (ex){}
    }
    if(window.attachEvent)
    {
        window.attachEvent("onload",  iframeAutoFit);
        //window.attachEvent("onresize",  iframeAutoFit);
    }
    else if(window.addEventListener)
    {
        window.addEventListener('load',  iframeAutoFit,  false);
        //window.addEventListener('resize',  iframeAutoFit,  false);
    }
    $(document).ready(function(){
    	<c:if test="${not empty alert}">
    		//刷新
    		setTimeout(parent.refreshFrame(),200);
    	</c:if>
    });
    //-->
    </script>
	</head>
	<body>
		<div class="page">
			<table width="100%" border="0" cellspacing="0" class="lyt_result">
				<tr>
					<th width="60">序号</th>
					<th>姓名</th>
					<th>职务</th>
					<th>工作联系内容</th>
					<th>办公电话</th>
					<th>手机</th>
					<th>邮箱</th>
					<th>是否校友</th>
					<th>人物属性</th>
					<th width="80">操作</th>
				</tr>
				<c:forEach items="${list}" var="temp" varStatus="status">
					<tr>
						<td>
							<b><c:out value="${status.count}" /></b>
						</td>
						<td>
							<c:choose>
							<c:when test="${not empty temp.tbAlumni}"><c:out value="${temp.tbAlumni.nameCn}"></c:out></c:when>
							<c:otherwise><c:out value="${temp.nameCn}" /></c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
							<c:when test="${not empty temp.tbWork}"><c:out value="${temp.tbWork.dutyName}"></c:out></c:when>
							<c:otherwise><c:out value="${temp.dutyName}" /></c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:out value="${temp.tbWork.dutyContent}"></c:out>
							<c:choose>
							<c:when test="${not empty temp.tbWork}"><c:out value="${temp.tbWork.dutyContent}"></c:out></c:when>
							<c:otherwise><c:out value="${temp.dutyContent}" /></c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
							<c:when test="${not empty temp.tbAlumni}"><c:out value="${temp.tbAlumni.telephoneFirst}"></c:out></c:when>
							<c:otherwise><c:out value="${temp.telephone}" /></c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
							<c:when test="${not empty temp.tbAlumni}"><c:out value="${temp.tbAlumni.handsetFirst}"></c:out></c:when>
							<c:otherwise><c:out value="${temp.handset}" /></c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
							<c:when test="${not empty temp.tbAlumni}"><c:out value="${temp.tbAlumni.mailFirst}"></c:out></c:when>
							<c:otherwise><c:out value="${temp.mail}" /></c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
							<c:when test="${temp.isStu=='0'}">
								否
							</c:when>
							<c:otherwise>
								<c:choose>
								<c:when test="${not empty temp.tbAlumni}">
									<c:set var="typeFid" value="${temp.tbAlumni.typeFid}" />
									<c:set var="typeFidIndex" value="${myFn:lastIndexOf(typeFid,',')+1}" />
									<my:fieldView value="${fn:substring(typeFid,typeFidIndex,fn:length(typeFid))}" />
								</c:when>
								<c:otherwise>
									<my:fieldView value="${temp.isStu}" />
								</c:otherwise>
								</c:choose>
							</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${not empty temp.tbAlumni}">
									<c:set var="gradeFid" value="${temp.tbAlumni.gradeFid}" />
									<c:set var="gradeFidIndex" value="${myFn:lastIndexOf(gradeFid,',')+1}" />
									<my:fieldView value="${fn:substring(gradeFid,gradeFidIndex,fn:length(gradeFid))}" />
								</c:when>
								<c:otherwise>
									<my:fieldView value="${temp.gradeFid}"></my:fieldView>
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<a href="javascript:;" onclick="parent.MyFormWin.showMyWin('单位人员详细','<%=basePath%>donate!toMember.action?comId=${comId}&memId=${temp.memId}',590,390);return false;">编辑</a>
							<a href="javascript:;" onclick="if(confirm('是否确定删除？')){location='<%=basePath%>donate!delCompanyMember.action?memId=${temp.memId}'}">删除</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</html>