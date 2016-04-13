package com.laungee.proj.manage.web.action;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.laungee.proj.common.core.BaseAction;

public class QueryTableExportAction extends BaseAction{
	// 处理请求
	public String execute() throws Exception {
		HttpServletRequest request=this.getRequest();
		HttpServletResponse response=this.getResponse();
		String[] exportInfos = request.getParameterValues("exportInfo");
		HSSFWorkbook excelWB = new HSSFWorkbook();
		HSSFSheet excelSheet = excelWB.createSheet("sheet1");
		excelSheet.setAutobreaks(true);//自动换行
		//设置默认行高
		for(int i=0;i<exportInfos.length;i++){
			String row = exportInfos[i];
			String[] cells = row.split("#CELL#");
			for(int j=0;j<cells.length;j++){
				String[] cellInfo = cells[j].split("#INFO#");
				String cellValue = cellInfo[0];
				short cellRowBeg = Short.parseShort(cellInfo[1]);
				short cellRowEnd = Short.parseShort(cellInfo[2]);
				short cellColBeg = Short.parseShort(cellInfo[3]);
				short cellColEnd = Short.parseShort(cellInfo[4]);
				String cellAlign = cellInfo[5].toLowerCase();
				String cellValign = cellInfo[6].toLowerCase();
				short cellFontWeight = Short.parseShort(cellInfo[7]);
				short cellWidth = (short)(Short.parseShort(cellInfo[8])*40);//*35.7
				short cellHeight = (short)(Short.parseShort(cellInfo[9])*16);//*15.625
				
				//合并单元格
				excelSheet.addMergedRegion(new Region(cellRowBeg,cellColBeg,cellRowEnd,cellColEnd));
				excelSheet.setColumnWidth(cellColBeg, cellWidth);//设置列宽
				HSSFRow excelRow = excelSheet.createRow(cellRowBeg);//新建行
				excelRow.setHeight(cellHeight);//设置行高
				HSSFCell excelCell = excelRow.createCell(cellColBeg);//新建单元格
				excelCell.setEncoding(HSSFCell.ENCODING_UTF_16);// 中文
				
				// 单元格样式
				HSSFCellStyle excelCellStyle = excelWB.createCellStyle();
				// 设置字体
				HSSFFont excelCellFont = excelWB.createFont(); 
				excelCellFont.setBoldweight(cellFontWeight);
				excelCellStyle.setFont(excelCellFont);
				// 平行位置
				if("left".equals(cellAlign)){
					excelCellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
				}else if("right".equals(cellAlign)){
					excelCellStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
				}else{
					excelCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				}
				// 垂直位置
				if("top".equals(cellAlign)){
					excelCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
				}else if("bottom".equals(cellAlign)){
					excelCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);
				}else{
					excelCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				}
				excelCellStyle.setWrapText(true);
				excelCell.setCellStyle(excelCellStyle);
				//赋值
				excelCell.setCellValue(cellValue);
			}
		}
		try {
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=\"exportExcel.xls\"");
			ServletOutputStream fos = response.getOutputStream();
			excelWB.write(fos);
			fos.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		// 返回
		return null;
	}
}
