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
	// ��������
	public String execute() throws Exception {
		HttpServletRequest request=this.getRequest();
		HttpServletResponse response=this.getResponse();
		String[] exportInfos = request.getParameterValues("exportInfo");
		HSSFWorkbook excelWB = new HSSFWorkbook();
		HSSFSheet excelSheet = excelWB.createSheet("sheet1");
		excelSheet.setAutobreaks(true);//�Զ�����
		//����Ĭ���и�
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
				
				//�ϲ���Ԫ��
				excelSheet.addMergedRegion(new Region(cellRowBeg,cellColBeg,cellRowEnd,cellColEnd));
				excelSheet.setColumnWidth(cellColBeg, cellWidth);//�����п�
				HSSFRow excelRow = excelSheet.createRow(cellRowBeg);//�½���
				excelRow.setHeight(cellHeight);//�����и�
				HSSFCell excelCell = excelRow.createCell(cellColBeg);//�½���Ԫ��
				excelCell.setEncoding(HSSFCell.ENCODING_UTF_16);// ����
				
				// ��Ԫ����ʽ
				HSSFCellStyle excelCellStyle = excelWB.createCellStyle();
				// ��������
				HSSFFont excelCellFont = excelWB.createFont(); 
				excelCellFont.setBoldweight(cellFontWeight);
				excelCellStyle.setFont(excelCellFont);
				// ƽ��λ��
				if("left".equals(cellAlign)){
					excelCellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
				}else if("right".equals(cellAlign)){
					excelCellStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
				}else{
					excelCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				}
				// ��ֱλ��
				if("top".equals(cellAlign)){
					excelCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
				}else if("bottom".equals(cellAlign)){
					excelCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);
				}else{
					excelCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				}
				excelCellStyle.setWrapText(true);
				excelCell.setCellStyle(excelCellStyle);
				//��ֵ
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
		
		// ����
		return null;
	}
}
