package com.laungee.proj.common.util;

import java.io.File;
import java.util.Hashtable;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.laungee.proj.common.core.BaseAction;

/**
 * 事件管理器实现
 * 
 * @author 
 * 
 */
public class TableManagerImpl extends BaseAction {

	Hashtable<String, TableMemo> tables;
	
	String path="/WEB-INF/table.xml";
	
	private void loadTableMemo() {
		tables = new Hashtable<String, TableMemo>();
//		String filename="E:\\MyEclipseWorkSpace\\test\\WebRoot\\WEB-INF\\table.xml";
		String filename=ServletActionContext.getServletContext().getRealPath(path);
		File file = new File(filename);

		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		Element element = (Element) document.selectSingleNode("/Tables");
		for (Object o : element.elements()) {
			Element e = (Element) o;
			if (e.attributeValue("tableName") != null) {

				if (!tables.containsKey(e.attributeValue("tableName"))) {
					TableMemo bm = new TableMemo();
					bm.setTableName(e.attributeValue("tableName"));
					for (Object o2 : e.elements()) {
						Element e2 = (Element) o2;
						if (e2.getName().equals("message"))
							bm.setMessage(e2.getText().trim());
						else if (e2.getName().equals("tabletype"))
							bm.setTableType(e2.getText());
						tables.put(e.attributeValue("tableName").toLowerCase(), bm);
					}
				}

			}
		}

	}

	/**
	 * 得到所有的数据表信息
	 */
	public Hashtable<String, TableMemo> getTableMemos() {
		loadTableMemo();
		return tables;
	}
}
