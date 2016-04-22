package com.laungee.proj.common.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class SchemeUtil implements IScheme{

	public static String getValue(String name) {
		String val="";
		try {
			Locale locale = Locale.getDefault(); 
			ResourceBundle rb = ResourceBundle.getBundle("conf/scheme", locale);
			val=new String(rb.getString(name).getBytes("ISO8859-1"),"GB2312");
		} catch (Exception e) {
		}
		return val;
	}
}
