package com.laungee.proj.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public  static Date toDate(String str){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date = sdf.parse(str);
		} catch (Exception e) {
		} 
		return date;
	}
	public  static Date toDate(String str, String format){
		if(format==null || "".equals(format)){
			format = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		Date date=null;
		try {
			date = sdf.parse(str);
		} catch (Exception e) {
		} 
		return date;
	}
	public  static Date toDates(String str){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		Date date=null;
		try {
			date = sdf.parse(str);
		} catch (Exception e) {
		} 
		return date;
	}
	public  static String format(Date date, String format){
		if(date == null){
			return "";
		}
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		try {
			return sdf.format(date);
		} catch (Exception e) {
			return "";
		} 
	}
	public  static String format(Date date){
		if(date == null){
			return "";
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.format(date);
		} catch (Exception e) {
			return "";
		} 
	}
}
