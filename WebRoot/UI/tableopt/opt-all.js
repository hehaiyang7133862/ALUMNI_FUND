/*表格分析功能格式说明
<div class="lytTbOpt" -->通过样式lytAnalysisOpt来识别是否本功能
	 table="table1"   -->指定分析表格ID，如：table_id1,table_id2..
	 ></div>
*/
//获取根目录
function getBasePath() {
	var els = document.getElementsByTagName('script'), src;
	for (var i = 0, len = els.length; i < len; i++) {
		src = els[i].src || '';
		if (/UI\/tableopt\/opt-all.js/.test(src)) {
			return src.replace(/UI\/tableopt\/opt-all.js/,"");
		}
	}
	return '';
}
//初始化分析数据列表，获取每个单元格实际的行号列号
function InitLytAnalysisTable(tableObj){
	if(tableObj.attr("hasInited") != "true"){
		tableObj.attr("hasInited","true");
		//遍历分析数据列表，获取每个单元格实际的行号列号
		tableObj.find("tr").each(function(rowIndex){
			$(this).children().each(function(colIndex){
				var rowIndexBeg = $(this).parent().prevAll().length;
				var rowIndexEnd = $(this).attr("rowspan")+rowIndexBeg-1;
				var colIndexBeg;
				var colIndexEnd;
				if(rowIndexBeg==0 && $(this).prevAll().length==0){
					colIndexBeg = 0;
				}else if($(this).prevAll().length>0){
					colIndexBeg = parseInt($(this).prev().attr("colIndexEnd"))+1;
				}else{
					var nowRowObj = $(this).parent();
					nowRowObj.prev().children().each(function(i){
						var prevColIndexBeg = parseInt($(this).attr("colIndexBeg"));
						if(rowIndexBeg>parseInt($(this).attr("rowIndexEnd"))){
							if(i==0){
								colIndexBeg = 0;
								var preRowCount = nowRowObj.prevAll().length;
								var nowRowIndex = preRowCount+1;
								while(preRowCount>0){
									nowRowObj = nowRowObj.prev();
									nowRowObj.children().each(function(){
										if($(this).attr("rowspan")>(nowRowIndex-preRowCount)){
											colIndexBeg = colIndexBeg+$(this).attr("colspan");
											return true;
										}
										return false;
									});
									preRowCount=preRowCount-1;
								}
							}else{
								colIndexBeg = prevColIndexBeg;
							}
							return false;
						}
						return true;
					});
				}
				colIndexEnd = $(this).attr("colspan")+parseInt(colIndexBeg)-1;
				$(this).attr("rowIndexBeg",rowIndexBeg);
				$(this).attr("rowIndexEnd",rowIndexEnd);
				$(this).attr("colIndexBeg",colIndexBeg);
				$(this).attr("colIndexEnd",colIndexEnd);
			});
		});
	}
	tableObj.css("display","");
}
$(document).ready(function(){
	//分析功能菜单初始化内容
	$(".lytTbOpt").each(function(){
		var tableId = $(this).attr("table")+"";
		if(tableId!="undefined"){
			var tableObj = $("#"+tableId);
			if(tableObj.length>0){
				if(tableObj.css("display")!="none"){
					InitLytAnalysisTable(tableObj);
				}
				var exportWin = tableId+"_ExportWin";
				var exportForm = tableId+"_ExportForm";
				var printWin = tableId+"_PrintWin";
				var optHtm = "<a href='javascript:;' class='optExport' table='"+tableId+"' title='导出Excel'></a>" + 
							 "<form id='"+exportForm+"' action='"+getBasePath()+"queryTableExport.action' method='post' target='"+exportWin+"' style='display:none;'></form>" + 
					 		 "<iframe id='"+exportWin+"' name='"+exportWin+"' style='display:none;'></iframe>" + 
							 "<a href='javascript:;' class='optPrint' table='"+tableId+"' title='打印'></a>" + 
							 "<iframe id='"+printWin+"' name='"+printWin+"' style='display:none;'></iframe>";
				
				$(this).append(optHtm);
			}
		}
	});
	//分析功能菜单-导出
	$(".lytTbOpt .optExport").click(function(){
		var tableId = $(this).attr("table");
		var exportFormObj = $("#"+tableId+"_ExportForm");
		var tableObj = $("#"+tableId);
		var exportForm = "";
		tableObj.find("tr").each(function(){
			var rowInfo = "";
			$(this).find("th,td").each(function(){
				if($(this).nextAll().length>0){
					if(rowInfo!=""){
						rowInfo+="#CELL#";
					}
					rowInfo+=$(this).text().replace(/(^\s*)|(\s*$)/g, "");
					rowInfo+="#INFO#";
					rowInfo+=$(this).attr("rowIndexBeg");
					rowInfo+="#INFO#";
					rowInfo+=$(this).attr("rowIndexEnd");
					rowInfo+="#INFO#";
					rowInfo+=$(this).attr("colIndexBeg");
					rowInfo+="#INFO#";
					rowInfo+=$(this).attr("colIndexEnd");
					rowInfo+="#INFO#";
					rowInfo+=$(this).css("text-align");
					rowInfo+="#INFO#";
					rowInfo+=$(this).css("vertical-align");
					rowInfo+="#INFO#";
					rowInfo+=$(this).css("font-weight");
					rowInfo+="#INFO#";
					rowInfo+=$(this).outerWidth();
					rowInfo+="#INFO#";
					rowInfo+=$(this).outerHeight();
				}
			});
					
			exportForm+="<input type='hidden' name='exportInfo' value='"+rowInfo+"'/>";
		});
		exportFormObj.empty();
		exportFormObj.append(exportForm);
		exportFormObj.submit();
	});
	//分析功能菜单-打印
	$(".lytTbOpt .optPrint").click(function(){
		var tableId = $(this).attr("table");
		var tableObj = $("#"+tableId);
		var printHtm = "<html>" + 
					      "<head>" + 
						     "<style type='text/css'>" + 
							    "table{border-left:1px solid #000000;border-right:1px solid #000000;border-bottom:1px solid #000000;border-spacing:0px;cellspacing:0px;padding:0px;margin:0;border-collapse:collapse;font-size:12px;}" + 
								"table th{border-left:1px solid #000000;border-top:1px solid #000000;padding:5px;border-spacing:0px;text-align:center;font-weight:bold;background-color:#f3f3f1;}" + 
								"table td{border-spacing:0px;padding:5px;border-left:1px solid #000000;border-top:1px solid #000000;text-align:left;}" + 
							 "</style>" + 
						  "</head>" + 
					      "<body>" + 
						     "<table>" + 
							    tableObj.html() + 
							 "</table>" + 
				 		  "</body>" + 
					   "</html>";	
		var win=window.open("about:blank");      //打开一个空页面 
		win.document.open();
		win.document.write(printHtm);
		win.focus();
        win.print();          
		win.document.close();
	});
});