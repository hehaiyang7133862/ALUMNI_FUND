package com.laungee.proj.common.util;

import java.io.Reader;
import java.sql.Clob;

public class StringUtil {
	
	public static String login_user="41";
	
	/*
	 * 除去最后的where,and,or
	 */
	public static String toQuery(String hql) {
		String temp = hql.trim();
		if(temp.length()>5){
			// 移除最后Where
			String sub1 = temp.substring(temp.length() - 5, temp.length());
			if (sub1.equalsIgnoreCase("where")) {
				hql = temp.substring(0, temp.length() - 5);
			}
			// 移除最后And
			String sub2 = temp.substring(temp.length() - 3, temp.length());
			if (sub2.equalsIgnoreCase("and")) {
				hql = temp.substring(0, temp.length() - 3);
			}
			// 移除最后Or
			String sub3 = temp.substring(temp.length() - 2, temp.length());
			if (sub3.equalsIgnoreCase("or")) {
				hql = temp.substring(0, temp.length() - 2);
			}
		}
		// 返回
		return hql.trim();
	}
	
	public static String toQuery(String hql,String c){
		String temp=hql.trim();
		String sub = temp.substring(temp.length() - 1, temp.length());
		if (sub.equalsIgnoreCase(",")) {
			hql = temp.substring(0, temp.length() - 1);
		}
		return hql.trim();
	}
	
	
	public static String toQuery(StringBuffer sb){
		return toQuery(sb.toString());
	}

	/*
	 * 排序操作
	 */
	public static String toStort(String sort) {
		if (null == sort || sort.equals("")) {
			return null;
		}
		StringBuffer sorts = new StringBuffer("");
		String[] splits = sort.split("@");
		int nsplit = splits.length;
		if (nsplit == 1 || nsplit == 2) {
			for (int i = 0; i < nsplit; i++) {
				sorts.append(splits[i] + " ");
			}
		}
		return sorts.toString().trim();
	}

	public static String toCount(String hql) {
		int ifrom = (" " + hql).toLowerCase().indexOf(" from ");
		int isort = hql.toLowerCase().lastIndexOf(" order by");
		String hql1;
		if (isort == -1) {
			hql1 = "select count(*) " + hql.substring(ifrom);
		} else {
			hql1 = "select count(*) " + hql.substring(ifrom, isort);
		}
		return hql1.trim();
	}
	/** 
     * 将Clob转成String ,静态方法 
     *  
     * @param clob 
     *            字段 
     * @return 内容字串，如果出现错误，返回 null 
     */  
    public static String clobToString(Clob clob) {  
        if (clob == null)  
            return null;  
        StringBuffer sb = new StringBuffer();  
        Reader clobStream = null;  
        try {  
            clobStream = clob.getCharacterStream();  
            char[] b = new char[60000];// 每次获取60K  
            int i = 0;  
            while ((i = clobStream.read(b)) != -1) {  
                sb.append(b, 0, i);  
            }  
        } catch (Exception ex) {  
            sb = null;  
        } finally {  
            try {  
                if (clobStream != null) {  
                    clobStream.close();  
                }  
            } catch (Exception e) {  
            }  
        }  
        if (sb == null)  
            return null;  
        else  
            return sb.toString();  
    }  
}
