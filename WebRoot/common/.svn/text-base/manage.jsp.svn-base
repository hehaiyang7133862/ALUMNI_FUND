<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
	<%@ include file="head.jsp"%>
	<script type="text/javascript" src="<%=basePathHead%>common/manage.js"></script>
	<style type="text/css">
		#winpop {
			width: 360px;
			height: 0px;
			position: absolute;
			right: 5px;
			bottom: 5px;
			border: 1px solid #999999;
			margin: 0;
			padding: 1px;
			overflow: hidden;
			display: none;
			background: #FFFFFF
		}
		
		#winpop .title {
			width: 100%;
			height: 25px;
			line-height: 25px;
			background: #E6F0FC;
			font-weight: bold;
			text-align: left;
			font-size: 12px;
			overflow: hidden;
			padding-left: 8px;
		}
		
		#winpop .con {
			width: 100%;
			height: 80px;
			font-weight: bold;
			font-size: 12px;
			text-align: left;
			overflow-y:auto;
			line-height: 18px;
		}
		
		#silu {
			font-size: 13px;
			color: #999999;
			position: absolute;
			right: 0;
			text-align: right;
			text-decoration: underline;
			line-height: 22px;
			overflow: hidden;
		}
		
		.close {
			display:inline-block;
			position: absolute;
			right: 4px;
			width:47px;
			height:26px;
			top: -1px;
			color: red;
			cursor: pointer;
			background-image:url("../UI/images/panel.jpg");
			background-position: right 1px;
			background-repeat: no-repeat;
		}
		.up {
			display:inline-block;
			position: absolute;
			right: 4px;
			width:47px;
			height:26px;
			top: -1px;
			color: red;
			cursor: pointer;
			background-image:url("../UI/images/panel.jpg");
			background-position: right -24px;
			background-repeat: no-repeat;
		}
		#winpop .title span a:hover{
			text-decoration: none;
		}
	</style>
  </head>
  
  <body class="glbLyt">
  	<div class="lytContainer">
	  	<div class="lytHeader">
	  		<div class="headInfo">
		  		<div class="banner"></div>
		  		<div class="userinfo">
		  			<ul>
	  					<li>欢迎您：<c:out value="${fund_user_name}"/></li>
                        <li><a href="javascript:;" onClick='MyFormWin.show("<%=basePath%>prepasswords!prepasswords.action?id=${fund_user_id}","修改个人信息",600,300);return false;'>修改个人信息</a></li>
	  					<li><a href="javascript:;" onClick="MyMsg.confirm('确定要退出系统','<%=basePath%>login!exit.action')">退出</a></li>
	  				</ul>
		  		</div>
	  		</div>
	  		<div class="menuInfo">
	  			<div class="memu">
		  			<div class="left"></div>
		  			<div class="cnt">
		  				<ul>
		  					<c:forEach items="${list_menu1}" var="tmp" varStatus="status">
								<li><c:out value="${tmp.menuName}" /></li>
							</c:forEach>
		  				</ul>
		  			</div>
		  			<div class="right"></div>
		  		</div>
		  		<div class="memuDetail">
		  			<c:forEach items="${list_menu1}" var="tmp1" varStatus="status1">
		  			<ul <c:if test="${status1.index>0}">style="display:none;"</c:if>>
 					<c:forEach items="${list_menu2[status1.index]}" var="tmp2" varStatus="status2">
					<c:forEach items="${list_menu3[status1.index][status2.index]}" var="tmp3" varStatus="status3">
					<li map="<c:out value='${tmp2.menuName}'/> > <c:out value='${tmp3.menuName}'/>" title="<c:out value='${tmp3.menuName}'/>" url="<c:out value='${tmp3.urlLink}'/>" code="<c:out value='${tmp3.menuId}'/>"><c:out value="${tmp3.menuName}" /></li>
					</c:forEach>
					</c:forEach>
					</ul>
					</c:forEach>
		  		</div>
	  		</div>
	  	</div>
	  	<div class="lytBody">
	  		<div id="tabsContainer">
	  			<div id="homePage">
                   	<c:choose>
                    	<c:when test="${empty loginIndexList}">
                     		<div style="background-color:#4074a6;" align="center">
                     			<img src="<%=basePath%>/UI/images/lytcenterbody.jpg" style="margin-top:20px;" />
                    		</div>
                    	</c:when>
                    	<c:otherwise>
                   			<c:set var="loginIndexUrl" value="${loginIndexList[0].tbMenu.urlLink}"/>
                    		<c:choose>
                    			<c:when test="${empty loginIndexUrl}">
                     				<div style="background-color:#4074a6;" align="center">
                    					<img src="<%=basePath%>/UI/images/lytcenterbody.jpg" style="margin-top:20px;" />
                    				</div>
                    			</c:when>
                    			<c:otherwise>
		                        <iframe width="100%" height="100%" name="loginIndex" id="loginIndex" frameborder="0" scrolling="auto" src="<%=basePath%>${loginIndexUrl}">
		                        </iframe>
                    			</c:otherwise>
                    		</c:choose>
                       </c:otherwise>
                      </c:choose>
	  			</div>
	  		</div>
	  	</div>
	  	<div class="lytFooter">
		  	<div class="rightInfo">
		  		<div class="left"></div>
		  		<div class="cnt">版权所有© <my:titleTag/></div>
		  		<div class="right"></div>
		  	</div>
	  	</div>
  	</div>
  </body>
</html>
