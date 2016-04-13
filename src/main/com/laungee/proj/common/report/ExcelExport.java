package com.laungee.proj.common.report;

import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.laungee.proj.common.biz.ICommonBiz;
import com.laungee.proj.common.util.SpringUtil;

public class ExcelExport {
	// 导出
	public static HSSFWorkbook export(List headList,List dataList){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("sheet1");
		// 设置列宽 
		/* 
		for(int i=0;i<headList.size();i++){
			sheet.setColumnWidth((short)i, (short)5000);  
		}
	    */
	    sheet.setColumnWidth((short)1, (short)5000);   
	    sheet.setColumnWidth((short)2, (short)5000);   
	    sheet.setColumnWidth((short)3, (short)5000);   
	    sheet.setColumnWidth((short)4, (short)7000);   
	    sheet.setColumnWidth((short)5, (short)7000);  
	     
		// 标题样式 --------------------------------
		HSSFCellStyle headStyle = wb.createCellStyle();
		// 设置字体
		HSSFFont headFont = wb.createFont(); 
		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headFont.setColor(HSSFColor.BLUE.index);
		headStyle.setFont(headFont);
		// 居中
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 标题样式 --------------------------------
		HSSFCellStyle rowStyle = wb.createCellStyle();
		//换行
		rowStyle.setWrapText(true);
		//设置顶部对齐
		rowStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		// 设置标题 --------------------------------
		HSSFRow headRow = sheet.createRow(0);
		for(int i=0;i<headList.size();i++){ 
			//标题
			HSSFCell headCell = headRow.createCell((short) i);
			headCell.setEncoding(HSSFCell.ENCODING_UTF_16);// 中文
			headCell.setCellStyle(headStyle);
			headCell.setCellValue(headList.get(i).toString());
		}
		int tagIndex=0;
		// 读取数据 ---------------------------------
		for (int i = 0; i < dataList.size(); i++) {
			// 新建行
			HSSFRow row = sheet.createRow(++tagIndex);
			if(i!=0&&i%1999==0){
				tagIndex=0;
				//新建页sheet
				sheet = wb.createSheet("sheet"+(2000*(i/1999)+1)+"-"+(2000*(i/1999)+2000));
				sheet.setColumnWidth((short)1, (short)5000);   
			    sheet.setColumnWidth((short)2, (short)5000);   
			    sheet.setColumnWidth((short)3, (short)5000);   
			    sheet.setColumnWidth((short)4, (short)7000);   
			    sheet.setColumnWidth((short)5, (short)7000);  
			     
				// 标题样式 --------------------------------
				headStyle = wb.createCellStyle();
				// 设置字体
				headFont = wb.createFont(); 
				headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				headFont.setColor(HSSFColor.BLUE.index);
				headStyle.setFont(headFont);
				// 居中
				headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				// 标题样式 --------------------------------
				rowStyle = wb.createCellStyle();
				//换行
				rowStyle.setWrapText(true);
				//设置顶部对齐
				rowStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
				// 设置标题 --------------------------------
				headRow = sheet.createRow(0);
				for(int n=0;n<headList.size();n++){ 
					//标题
					HSSFCell headCell = headRow.createCell((short) n);
					headCell.setEncoding(HSSFCell.ENCODING_UTF_16);// 中文
					headCell.setCellStyle(headStyle);
					headCell.setCellValue(headList.get(n).toString());
				}
			}
			short cellIndex=0;
			ICommonBiz biz=(ICommonBiz)SpringUtil.getBean("commonBiz");
			// 行数据
			Object[] data= (Object[]) dataList.get(i);
			for(int j=0;j<data.length;j++){
				
				String cellValue=data[j]==null?"":data[j].toString();
				if(cellValue.startsWith("tag-")){
					//所属校友会
					String organIds=cellValue.substring(4);
					try{
						//所属学院校友会
						List list=biz.findSQL("select a.name from tb_community a where a.organ_type=2 and a.organ_level=3 and instr(',"+organIds+",',','||a.community_id||',')!=0");
						HSSFCell cell = row.createCell((short) (cellIndex++));
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						cell.setCellStyle(rowStyle);
						cell.setCellValue(listToString(list));
					
						//所属地方校友会
						list=biz.findSQL("select a.name from tb_community a where a.organ_type=3 and a.organ_level=3 and instr(',"+organIds+",',','||a.community_id||',')!=0");
						cell = row.createCell((short) (cellIndex++));
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						cell.setCellStyle(rowStyle);
						cell.setCellValue(listToString(list));
					
						//所属行业俱乐部
						list=biz.findSQL("select a.name from tb_community a where a.organ_type=4 and a.organ_level=3 and instr(',"+organIds+",',','||a.community_id||',')!=0");
						cell = row.createCell((short) (cellIndex++));
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						cell.setCellStyle(rowStyle);
						cell.setCellValue(listToString(list));
					
						//所属特色社区
						list=biz.findSQL("select a.name from tb_community a where a.organ_type=7 and a.organ_level=3 and instr(',"+organIds+",',','||a.community_id||',')!=0");
						cell = row.createCell((short) (cellIndex++));
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						cell.setCellStyle(rowStyle);
						cell.setCellValue(listToString(list));
					}
					catch (Exception e) {
					}
				}else if(cellValue.startsWith("msg-")){
					//QQ,MSN
					String msgs=cellValue.substring(4);
					try {
						if(msgs!=null&&!"".equals(msgs)){
							String[] strs=msgs.split("·");
							String qq="";
							String msn="";
							if(strs!=null&&strs.length!=0){
								for (int k = 0; k < strs.length; k++) {
									if(k==0){
										qq=strs[k];
									}else{
										msn=strs[k];
									}
								}
							}
							//QQ
							HSSFCell cell = row.createCell((short) (cellIndex++));
							cell.setEncoding(HSSFCell.ENCODING_UTF_16);
							cell.setCellStyle(rowStyle);
							cell.setCellValue(qq);
							//MSN
							cell = row.createCell((short) (cellIndex++));
							cell.setEncoding(HSSFCell.ENCODING_UTF_16);
							cell.setCellStyle(rowStyle);
							cell.setCellValue(msn);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}else if(cellValue.startsWith("fid-")){
					String fid=cellValue.substring(4);
					String fieldName="";
					Object obj=null;
					//将id变成中文显示
					try {
						obj=biz.getSQLUnique("select a.field_name from tb_field a where a.field_id="+fid);
					} catch (Exception e) {
						// TODO: handle exception
					}
					fieldName=obj==null?"":obj+"";
					HSSFCell cell = row.createCell((short) (cellIndex++));
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(rowStyle);
					cell.setCellValue(fieldName);
				}else{
					// 新建单元格
					HSSFCell cell = row.createCell((short) (cellIndex++));
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(rowStyle);
					try{
						cell.setCellValue(cellValue);
					}
					catch (Exception e) {
					}
				}
			}
		}
		// 返回
		return wb;
	}
	// 导出
	public static HSSFWorkbook export(List headList,List dataList,Set set){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("sheet1");
		// 标题样式 --------------------------------
		HSSFCellStyle headStyle = wb.createCellStyle();
		// 设置字体
		HSSFFont headFont = wb.createFont(); 
		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headFont.setColor(HSSFColor.BLUE.index);
		headStyle.setFont(headFont);
		// 居中
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 标题样式 --------------------------------
		HSSFCellStyle rowStyle = wb.createCellStyle();
		// 居中
		rowStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置标题 --------------------------------
		HSSFRow headRow = sheet.createRow(0);
		for(int i=0,i0=0;i<headList.size();i++){
			if(set.contains(i+"")){
				//标题
				HSSFCell headCell = headRow.createCell((short) i0);
				headCell.setEncoding(HSSFCell.ENCODING_UTF_16);// 中文
				headCell.setCellStyle(headStyle);
				headCell.setCellValue(headList.get(i).toString());
				i0++;
			}
		}
		// 读取数据 ---------------------------------
		for (int i = 0; i < dataList.size(); i++) {
			// 新建行
			HSSFRow row = sheet.createRow(i + 1);
			// 行数据
			Object[] data= (Object[]) dataList.get(i);
			for(int j=0,j0=0;j<data.length;j++){
				if(set.contains(j+"")){
					// 新建单元格
					HSSFCell cell = row.createCell((short) j0);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(rowStyle);
					j0++;
					try{
						cell.setCellValue(data[j].toString());
					}
					catch (Exception e) {
					}
				}
			}
		}
		// 返回
		return wb;
	}
	// 导出
	public static HSSFWorkbook exportutil(List headList,List dataList){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("sheet1");
		// 设置列宽 
		/* 
		for(int i=0;i<headList.size();i++){
			sheet.setColumnWidth((short)i, (short)5000);  
		}
	    */
		// 标题样式 --------------------------------
		HSSFCellStyle headStyle = wb.createCellStyle();
		// 设置字体
		HSSFFont headFont = wb.createFont(); 
		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headFont.setColor(HSSFColor.BLUE.index);
		headStyle.setFont(headFont);
		// 居中
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 标题样式 --------------------------------
		HSSFCellStyle rowStyle = wb.createCellStyle();
		//换行
		//rowStyle.setWrapText(true);
		//设置顶部对齐
		rowStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		// 设置标题 --------------------------------
		HSSFRow headRow = sheet.createRow(0);
		for(int i=0;i<headList.size();i++){ 
			//标题
			HSSFCell headCell = headRow.createCell((short) i);
			headCell.setEncoding(HSSFCell.ENCODING_UTF_16);// 中文
			headCell.setCellStyle(headStyle);
			headCell.setCellValue(headList.get(i).toString());
		}
		// 读取数据 ---------------------------------
		for (int i = 0; i < dataList.size(); i++) {
			// 新建行
			HSSFRow row = sheet.createRow(i + 1);
			// 行数据
			Object[] data= (Object[]) dataList.get(i);
			for(int j=0;j<data.length;j++){
				// 新建单元格
				HSSFCell cell = row.createCell((short) j);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellStyle(rowStyle);
				try{
					cell.setCellValue(data[j].toString());
				}
				catch (Exception e) {
				}
			}
		}
		// 返回
		return wb;
	}
	
	/**
	 * 分页导出
	 * @param headList
	 * @param dataList
	 * @return
	 */
	public static HSSFWorkbook export(List headList,List dataList,HSSFWorkbook wb,String sheetName){
		if(wb==null){
			wb = new HSSFWorkbook();
		}
		HSSFSheet sheet = wb.createSheet(sheetName);
		// 设置列宽 
		/* 
		for(int i=0;i<headList.size();i++){
			sheet.setColumnWidth((short)i, (short)5000);  
		}
	    */
	    sheet.setColumnWidth((short)1, (short)5000);   
	    sheet.setColumnWidth((short)2, (short)5000);   
	    sheet.setColumnWidth((short)3, (short)5000);   
	    sheet.setColumnWidth((short)4, (short)7000);   
	    sheet.setColumnWidth((short)5, (short)7000);  
	     
		// 标题样式 --------------------------------
		HSSFCellStyle headStyle = wb.createCellStyle();
		// 设置字体
		HSSFFont headFont = wb.createFont(); 
		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headFont.setColor(HSSFColor.BLUE.index);
		headStyle.setFont(headFont);
		// 居中
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 标题样式 --------------------------------
		HSSFCellStyle rowStyle = wb.createCellStyle();
		//换行
		rowStyle.setWrapText(true);
		//设置顶部对齐
		rowStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		// 设置标题 --------------------------------
		HSSFRow headRow = sheet.createRow(0);
		for(int i=0;i<headList.size();i++){ 
			//标题
			HSSFCell headCell = headRow.createCell((short) i);
			headCell.setEncoding(HSSFCell.ENCODING_UTF_16);// 中文
			headCell.setCellStyle(headStyle);
			headCell.setCellValue(headList.get(i).toString());
		}
		int tagIndex=0;
		ICommonBiz biz=(ICommonBiz)SpringUtil.getBean("commonBiz");
		// 读取数据 ---------------------------------
		for (int i = 0; i < dataList.size(); i++) {
			// 新建行
			HSSFRow row = sheet.createRow(++tagIndex);
			// 行数据
			Object[] data= (Object[]) dataList.get(i);
			short cellIndex=0;
			for(int j=0;j<data.length;j++){
				String cellValue=data[j]==null?"":data[j].toString();
				if(cellValue.startsWith("tag-")){
					//所属校友会
					String organIds=cellValue.substring(4);
					try{
						//所属学院校友会
						List list=biz.findSQL("select a.name from tb_community a where a.organ_type=2 and a.organ_level=3 and instr(',"+organIds+",',','||a.community_id||',')!=0");
						HSSFCell cell = row.createCell((short) (cellIndex++));
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						cell.setCellStyle(rowStyle);
						cell.setCellValue(listToString(list));
					
						//所属地方校友会
						list=biz.findSQL("select a.name from tb_community a where a.organ_type=3 and a.organ_level=3 and instr(',"+organIds+",',','||a.community_id||',')!=0");
						cell = row.createCell((short) (cellIndex++));
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						cell.setCellStyle(rowStyle);
						cell.setCellValue(listToString(list));
					
						//所属行业俱乐部
						list=biz.findSQL("select a.name from tb_community a where a.organ_type=4 and a.organ_level=3 and instr(',"+organIds+",',','||a.community_id||',')!=0");
						cell = row.createCell((short) (cellIndex++));
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						cell.setCellStyle(rowStyle);
						cell.setCellValue(listToString(list));
					
						//所属特色社区
						list=biz.findSQL("select a.name from tb_community a where a.organ_type=7 and a.organ_level=3 and instr(',"+organIds+",',','||a.community_id||',')!=0");
						cell = row.createCell((short) (cellIndex++));
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						cell.setCellStyle(rowStyle);
						cell.setCellValue(listToString(list));
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}else if(cellValue.startsWith("msg-")){
					//QQ,MSN
					String msgs=cellValue.substring(4);
					try {
						if(msgs!=null&&!"".equals(msgs)){
							String[] strs=msgs.split("·");
							String qq="";
							String msn="";
							if(strs!=null&&strs.length!=0){
								for (int k = 0; k < strs.length; k++) {
									if(k==0){
										qq=strs[k];
									}else{
										msn=strs[k];
									}
								}
							}
							//QQ
							HSSFCell cell = row.createCell((short) (cellIndex++));
							cell.setEncoding(HSSFCell.ENCODING_UTF_16);
							cell.setCellStyle(rowStyle);
							cell.setCellValue(qq);
							//MSN
							cell = row.createCell((short) (cellIndex++));
							cell.setEncoding(HSSFCell.ENCODING_UTF_16);
							cell.setCellStyle(rowStyle);
							cell.setCellValue(msn);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}else if(cellValue.startsWith("fid-")){
					String fid=cellValue.substring(4);
					String fieldName="";
					Object obj=null;
					//将id变成中文显示
					try {
						obj=biz.getSQLUnique("select a.field_name from tb_field a where a.field_id="+fid);
					} catch (Exception e) {
						// TODO: handle exception
					}
					fieldName=obj==null?"":obj+"";
					HSSFCell cell = row.createCell((short) (cellIndex++));
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(rowStyle);
					cell.setCellValue(fieldName);
				}else{
					// 新建单元格
					HSSFCell cell = row.createCell((short) (cellIndex++));
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(rowStyle);
					try{
						cell.setCellValue(cellValue);
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		// 返回
		return wb;
	}
	
	private static String listToString(List list){
		String result="";
		if(list!=null&&!list.isEmpty()){
			for (int i = 0; i < list.size(); i++) {
				if("".equals(result)){
					result+=list.get(i);
				}else{
					result+=","+list.get(i);
				}
				
			}
		}
		return result;
	}
}
