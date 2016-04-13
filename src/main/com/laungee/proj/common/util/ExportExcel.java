package com.laungee.proj.common.util;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

public class ExportExcel {

	private HSSFWorkbook wb = null;

	private HSSFSheet sheet = null;

	/**
	 * @param wb
	 * @param sheet
	 */
	public ExportExcel(HSSFWorkbook wb, HSSFSheet sheet) {
		super();
		this.wb = wb;
		this.sheet = sheet;
	}

	/**
	 * @return the sheet
	 */
	public HSSFSheet getSheet() {
		return sheet;
	}

	/**
	 * @param sheet the sheet to set
	 */
	public void setSheet(HSSFSheet sheet) {
		this.sheet = sheet;
	}

	/**
	 * @return the wb
	 */
	public HSSFWorkbook getWb() {
		return wb;
	}

	/**
	 * @param wb the wb to set
	 */
	public void setWb(HSSFWorkbook wb) {
		this.wb = wb;
	}

	/**
	 * ����ͨ��EXCELͷ��
	 * 
	 * @param headString ͷ����ʾ���ַ�
	 * @param colSum �ñ��������
	 */
	public void createNormalHead(String headString, int colSum) {

		HSSFRow row = sheet.createRow(0);

		// ���õ�һ��
		HSSFCell cell = row.createCell((short) 0);
		row.setHeight((short) 400);

		// ���嵥Ԫ��Ϊ�ַ�������
		cell.setCellType(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(new HSSFRichTextString(headString));

		// ָ���ϲ�����
		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) colSum));

		HSSFCellStyle cellStyle = wb.createCellStyle();

		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ָ����Ԫ����ж���
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// ָ����Ԫ��ֱ���ж���
		cellStyle.setWrapText(true);// ָ����Ԫ���Զ�����

		// ���õ�Ԫ������
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("����");
		font.setFontHeight((short) 300);
		cellStyle.setFont(font);

		cell.setCellStyle(cellStyle);
	}

	/**
	 * ����ͨ�ñ���ڶ���
	 * 
	 * @param params ͳ����������
	 * @param colSum ��Ҫ�ϲ�����������
	 */
	public void createNormalTwoRow(String[] params, int colSum) {
		HSSFRow row1 = sheet.createRow(1);
		row1.setHeight((short) 300);

		HSSFCell cell2 = row1.createCell((short) 0);

		cell2.setCellType(HSSFCell.ENCODING_UTF_16);
		cell2.setCellValue(new HSSFRichTextString("ͳ��ʱ�䣺" + params[0] + "��"
				+ params[1]));

		// ָ���ϲ�����
		sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) colSum));

		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ָ����Ԫ����ж���
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// ָ����Ԫ��ֱ���ж���
		cellStyle.setWrapText(true);// ָ����Ԫ���Զ�����

		// ���õ�Ԫ������
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("����");
		font.setFontHeight((short) 250);
		cellStyle.setFont(font);

		cell2.setCellStyle(cellStyle);

	}
	
	public void createNormalTwoRow(int colSum,String title) {
		HSSFRow row1 = sheet.createRow(1);
		row1.setHeight((short) 400);

		HSSFCell cell2 = row1.createCell((short) 0);

		cell2.setCellType(HSSFCell.ENCODING_UTF_16);
		cell2.setCellValue(new HSSFRichTextString(title));

		// ָ���ϲ�����
		sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) colSum));

		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT); // ָ����Ԫ����ж���
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// ָ����Ԫ��ֱ���ж���
		cellStyle.setWrapText(true);// ָ����Ԫ���Զ�����

		// ���õ�Ԫ������
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("����");
		font.setFontHeight((short) 250);
		font.setColor((short)12);
		cellStyle.setFont(font);

		cell2.setCellStyle(cellStyle);

	}
	
	public void createNormalRow(int rowIndex,int colSum,String title) {
		HSSFRow row1 = sheet.createRow(rowIndex);
		row1.setHeight((short) 400);

		HSSFCell cell2 = row1.createCell((short) 0);

		cell2.setCellType(HSSFCell.ENCODING_UTF_16);
		cell2.setCellValue(new HSSFRichTextString(title));

		// ָ���ϲ�����
		sheet.addMergedRegion(new Region(rowIndex, (short) 0, rowIndex, (short) colSum));

		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT); // ָ����Ԫ����ж���
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// ָ����Ԫ��ֱ���ж���
		cellStyle.setWrapText(true);// ָ����Ԫ���Զ�����

		// ���õ�Ԫ������
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("����");
		font.setFontHeight((short) 250);
		font.setColor((short)12);
		cellStyle.setFont(font);

		cell2.setCellStyle(cellStyle);

	}

	/**
	 * ���ñ������
	 * 
	 * @param columHeader �����ַ�������
	 */
	public void createColumHeader(String[] columHeader) {

		// ������ͷ
		HSSFRow row2 = sheet.createRow(2);

		// ָ���и�
		row2.setHeight((short) 600);

		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ָ����Ԫ����ж���
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// ָ����Ԫ��ֱ���ж���
		cellStyle.setWrapText(true);// ָ����Ԫ���Զ�����

		// ��Ԫ������
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("����");
		font.setFontHeight((short) 250);
		cellStyle.setFont(font);
		
		/*cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // ���õ��޸�ı߿�Ϊ����
		cellStyle.setBottomBorderColor(HSSFColor.BLACK.index); // ���õ�Ԫ��ı߿���ɫ��
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setTopBorderColor(HSSFColor.BLACK.index);*/

		// ���õ�Ԫ�񱳾�ɫ
		cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		HSSFCell cell3 = null;

		for (int i = 0; i < columHeader.length; i++) {
			cell3 = row2.createCell((short) i);
			cell3.setCellType(HSSFCell.ENCODING_UTF_16);
			cell3.setCellStyle(cellStyle);
			cell3.setCellValue(new HSSFRichTextString(columHeader[i]));
		}

	}

	/**
	 * �������ݵ�Ԫ��
	 * 
	 * @param wb HSSFWorkbook
	 * @param row HSSFRow
	 * @param col short�͵�������
	 * @param align ���뷽ʽ
	 * @param val ��ֵ
	 * @param fontType ��������
	 */
	public void createCell(HSSFWorkbook wb, HSSFRow row, int col,
			short align,short valign, String val,Long fontType) {
		HSSFCell cell = row.createCell((short) col);
		cell.setCellType(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(new HSSFRichTextString(val));
		HSSFCellStyle cellstyle = wb.createCellStyle();
		cellstyle.setAlignment(align);
//		if(val.equals("����")){
//			cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
//		}
		cellstyle.setVerticalAlignment(valign);
		if (fontType==16) {
			HSSFFont font=wb.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setFontName("����");
			cellstyle.setFont(font);
		}
		cell.setCellStyle(cellstyle);
	}
	
	/**
	 * �������ݵ�Ԫ��
	 * 
	 * @param wb HSSFWorkbook
	 * @param row HSSFRow
	 * @param col short�͵�������
	 * @param align ���뷽ʽ
	 * @param val ��ֵ
	 */
	public void createCell(HSSFWorkbook wb, HSSFRow row, int col,
			short align,short valign, String val) {
		HSSFCell cell = row.createCell((short) col);
		cell.setCellType(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(new HSSFRichTextString(val));
		HSSFCellStyle cellstyle = wb.createCellStyle();
		cellstyle.setAlignment(align);
//		if(val.equals("����")){
//			cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
//		}
		cellstyle.setVerticalAlignment(valign);
		cell.setCellStyle(cellstyle);
	}

	/**
	 * �������ݵ�Ԫ��
	 * 
	 * @param wb HSSFWorkbook
	 * @param row HSSFRow
	 * @param col short�͵�������
	 * @param align ���뷽ʽ
	 * @param val ��ֵ
	 * @param fontType ��������
	 */
	public void createLastCell(HSSFWorkbook wb, HSSFRow row, int col,
			short align,short valign, String val) {
		HSSFCell cell = row.createCell((short) col);
		cell.setCellType(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(new HSSFRichTextString(val));
		HSSFCellStyle cellstyle = wb.createCellStyle();
		cellstyle.setAlignment(align);
		cellstyle.setVerticalAlignment(valign);
		HSSFFont font=wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("����");
		font.setColor((short)2);
		cellstyle.setFont(font);
		cell.setCellStyle(cellstyle);
	}
	
	/**
	 * �����ϼ���
	 * @param colSum ��Ҫ�ϲ�����������
	 * @param cellValue
	 */
	public void createLastSumRow(int colSum, String[] cellValue) {

		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ָ����Ԫ����ж���
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// ָ����Ԫ��ֱ���ж���
		cellStyle.setWrapText(true);// ָ����Ԫ���Զ�����

		// ��Ԫ������
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("����");
		font.setFontHeight((short) 250);
		cellStyle.setFont(font);

		HSSFRow lastRow = sheet.createRow((short) (sheet.getLastRowNum() + 1));
		HSSFCell sumCell = lastRow.createCell((short) 0);

		sumCell.setCellValue(new HSSFRichTextString("�ϼ�"));
		sumCell.setCellStyle(cellStyle);
		sheet.addMergedRegion(new Region(sheet.getLastRowNum(), (short) 0,
				sheet.getLastRowNum(), (short) colSum));// ָ���ϲ�����

		for (int i = 2; i < (cellValue.length + 2); i++) {
			sumCell = lastRow.createCell((short) i);
			sumCell.setCellStyle(cellStyle);
			sumCell.setCellValue(new HSSFRichTextString(cellValue[i-2]));

		}

	}

	/**
	 * ����EXCEL�ļ�
	 * 
	 * @param fileName �ļ���
	 */
	public void outputExcel(String fileName) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(fileName));
			wb.write(fos);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}