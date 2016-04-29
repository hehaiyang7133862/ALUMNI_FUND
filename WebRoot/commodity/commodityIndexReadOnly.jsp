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
	var glbPath = "<%=basePath%>";
	var glbId="${bean.typeId}";
	var glbName="全部分类";
	var glbCount="${count}";
	Ext.onReady(function(){
		new Ext.Viewport({
			layout: 'border',
 			items: [{
   				region: 'west',
             	layout: 'fit',
            	split: true,
             	width: 200,
             	minWidth: 200,
             	maxWidth: 300,
             	border: false,
              	padding: '2 0 0 0',
				style: 'background-color:#fff',
				html:'<div id="tree-panel" style="overflow:hidden;"></div>'
			},{
              	region: 'center',
              	border: false,
             	layout: 'border',
             	items: [{
			        region: 'north',
             		border: false,
					html: '<div id="myMap" class="lyt_nav"><c:out value="${bean.typeName}"/></div>'
				},{
	              	region: 'center',
             		border: false,
              		padding: '0 5 5 5',
	             	html: '<iframe id="myFrame" name="myFrame" width="100%" height="100%" scrolling="auto" frameborder="0" src="<%=basePath%>commodityReadOnly!list.action?commType=${commType}"></iframe>'
	         	}]
            }]
		});
	});
</script>
<script type="text/javascript" src="<%=basePath%>dwr/engine.js"></script>
<script type="text/javascript" src="<%=basePath%>dwr/util.js"></script>
<!-- 需替换 -->
<script type="text/javascript" src="<%=basePath%>dwr/interface/CommodityTypeManager.js"></script>
<script type="text/javascript" src="<%=basePath%>commodity/commodityIndex.js"></script>
</head>
<body></body>
</html>