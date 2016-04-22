package com.laungee.proj.common.util;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.commons.lang.math.NumberUtils;
/**

 *
 */

public class StringUtils extends org.apache.commons.lang.StringUtils {

	public static String arrayToSqlString(String[] s) {
		String result = join(s, "','");
		if (result != null && !"".equals(result)) {
			result = "'" + result + "'";
		}
		return result;
	}


	/**
	 * 
	 * @param i
	 * @return
	 */
	public static String convertIntToTwoChar(int i) {
		if (i > 0 && i < 10) {
			return "0" + String.valueOf(i);
		}
		return String.valueOf(i);
	}

	/**
	 * remove new lines, and convert the double quotes into single quotes, so
	 * the string can be embedded in certain Javascript files
	 * 
	 * @param s
	 * @return
	 */
	public static String escapeXmlForJs(String s) {
		s = StringUtils.replaceChars(s, "\r\n", null);
		return StringUtils.replaceChars(s, '"', '\'');
	}

	public static int getNumericAfterStr(String s, String str) {
		return Integer.parseInt(StringUtils.substringAfter(s, str));
	}

	public static boolean isStrNumeric(String s, String str) {
		if (isNotBlank(s) && s.startsWith(str)) {
			return NumberUtils.isDigits(s.substring(1));
		}
		return false;
	}

	public static boolean isXNumeric(String s) {
		return isStrNumeric(s, "X");
	}

	public static String removeNull(String s) {
		return s != null ? s : "";
	}

	public static String trimObjectToEmpty(Object o) {
		if (o != null)
			return trimToEmpty(o.toString());
		return "";
	}

	public static Object trimToEmptyIfString(Object s) {
		if (s instanceof String) {
			return trimToEmpty((String) s);
		}
		return s;
	}
	
	/**
	 * 按字节截取
	 * @param str
	 * @param start
	 * @param end
	 * @return
	 */
	public static String substrByBytes(String str, int start,int end) {
		String temp = "";
		int k = 0;
		for (int i = 0; i < str.length(); i++) {
			byte[] b = (str.charAt(i) + "").getBytes(); // 每循环一次，将str里的值放入byte数组
			k = k + b.length;
			if (k > end) { // 如果数组长度大于6，随机跳出循环
				break;
			}
			if(i>=start){
				temp = temp + str.charAt(i); // 拼接新字符串
			}
		}
		return temp;
	}
	
	/**
	 * 按字节截取
	 * @param str
	 * @param end
	 * @return
	 */
	public static String substrByBytes(String str, int end) {
		String temp = "";
		int k = 0;
		for (int i = 0; i < str.length(); i++) {
			byte[] b = (str.charAt(i) + "").getBytes(); // 每循环一次，将str里的值放入byte数组
			k = k + b.length;
			if (k > end) { // 如果数组长度大于6，随机跳出循环
				break;
			}
			temp = temp + str.charAt(i); // 拼接新字符串
		}
		return temp;
	}
	//截取分页参数
	public static String subpagepar(String strs){
		String temp[]=strs.split("&");
		  String str="";
		  String num="-1";
		  for(int i=0;i<temp.length;i++){
			   if(temp[i]!=null&&!temp[i].equals("")){ 
			   String sub=temp[i].substring(0,temp[i].indexOf("=")+1);
			   for(int j=i+1;j<temp.length;j++){
				   String sub1=temp[j].substring(0,temp[j].indexOf("=")+1); 
				   if(sub.equals(sub1)){
				    	 num=i+"";
					}
			   }
			   if(!num.equals(i+"")){
		    		  str+=temp[i]+"&";  
		    	} 
			  }
			  
		  }
		return str;
	}
	
	/** 
     * 格式化金额         
     * @param s 
     * @return 
     */  
    public static String formatMoney(String s) {
		if (s == null || s.length() < 1) {
			return "";
		}
		NumberFormat formater = null;
		double num = Double.parseDouble(s);
		if (s.lastIndexOf(".") == -1) {
			formater = new DecimalFormat("###,###");

		} else {
			StringBuffer buff = new StringBuffer();
			buff.append("###,###.");
			for (int i = 0; i < (s.length() - s.lastIndexOf(".") - 1); i++) {
				buff.append("#");
			}
			formater = new DecimalFormat(buff.toString());
		}
		String result = formater.format(num);
		if (result.indexOf(".") == -1) {
			result = result + ".00";
		} else {
			result = result;
		}
		return result;
	}
}

