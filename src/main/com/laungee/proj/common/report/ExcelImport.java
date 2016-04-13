package com.laungee.proj.common.report;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ExcelImport {
	// ����
	public static List imports(InputStream fis) throws Exception{
		// ������
		List mainList=null;
		try{
		POIFSFileSystem fs= new POIFSFileSystem(fis);
		HSSFWorkbook workbook = new HSSFWorkbook(fs);
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		// ����
		int length=sheet.getRow(0).getLastCellNum();
		// ���漯��
		mainList=new ArrayList(sheet.getPhysicalNumberOfRows());
		for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
			// ��
			HSSFRow row = sheet.getRow(i);
			List subList = new ArrayList();
			for (int j = 0; j < length; j++) {
				String cell = null;
				try {
					cell= row.getCell((short) j).toString().trim();
					//��ȡ��ʽֵ
					if(HSSFCell.CELL_TYPE_FORMULA == row.getCell((short)j).getCellType()){
						   double a=row.getCell((short) j).getNumericCellValue();
						   cell=new Double(a).toString();
					}
					if(!cell.startsWith("0")){
						if(cell.endsWith(".0")){
					    if(cell.matches("^\\d*.0$")){
					    //ֻ����.0��ʽ��ȥ��
					    if(cell.substring(cell.indexOf("."),cell.length()).length()==2){
						 cell=cell.substring(0,cell.lastIndexOf(".0"));
					    }
					  }
					}
					//��ѧ������
					if(cell.matches("[\\+\\-]?[\\d]+([\\.][\\d]*)?([Ee][+-]?[\\d]+)?")){
						BigDecimal nf = new BigDecimal(cell); 
						cell=nf.toPlainString();
					}
				  }
				} catch (Exception e) {
					cell="";
				}
			
				subList.add(cell);
				
			}
			// ���
			mainList.add(subList);
		}
		} catch (Exception e) {
			mainList=null;
			e.printStackTrace();
		}
		// ����
		return mainList;
	}
}
