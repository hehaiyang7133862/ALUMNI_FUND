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
	// 导入
	public static List imports(InputStream fis) throws Exception{
		// 定义流
		List mainList=null;
		try{
		POIFSFileSystem fs= new POIFSFileSystem(fis);
		HSSFWorkbook workbook = new HSSFWorkbook(fs);
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		// 长度
		int length=sheet.getRow(0).getLastCellNum();
		// 保存集合
		mainList=new ArrayList(sheet.getPhysicalNumberOfRows());
		for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
			// 行
			HSSFRow row = sheet.getRow(i);
			List subList = new ArrayList();
			for (int j = 0; j < length; j++) {
				String cell = null;
				try {
					cell= row.getCell((short) j).toString().trim();
					//读取公式值
					if(HSSFCell.CELL_TYPE_FORMULA == row.getCell((short)j).getCellType()){
						   double a=row.getCell((short) j).getNumericCellValue();
						   cell=new Double(a).toString();
					}
					if(!cell.startsWith("0")){
						if(cell.endsWith(".0")){
					    if(cell.matches("^\\d*.0$")){
					    //只有是.0格式才去除
					    if(cell.substring(cell.indexOf("."),cell.length()).length()==2){
						 cell=cell.substring(0,cell.lastIndexOf(".0"));
					    }
					  }
					}
					//科学计数法
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
			// 添加
			mainList.add(subList);
		}
		} catch (Exception e) {
			mainList=null;
			e.printStackTrace();
		}
		// 返回
		return mainList;
	}
}
