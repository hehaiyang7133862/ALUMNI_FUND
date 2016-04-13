package com.laungee.proj.common.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

public class PropertyUtil {
	
	/**
	 * Method copy 把Map对象复制到实体类
	 * @param obj
	 * @param map
	 * @return <Object>
	 * @author wuxianjun
	 */
	public static Object copy(Object obj, Map map) {
		Class clazz = obj.getClass();
		Field[] field = clazz.getDeclaredFields();
		for (int i = 0; i < field.length; i++) {
			String propertyName = field[i].getName();
			if (map.containsKey(propertyName)) {
				String value = ((String[]) map.get(propertyName))[0];
				if(value!=null&&value!="")
				PropertyUtil.setPropertyValue(obj, propertyName, value);
			}
		}
		return obj;
	}

	/**
	 * Method 把oldobj对象的值复制到newobj对象中。
	 * @param Object newobj 
	 * @param Object oldobj
	 * @return <Object>
	 * @author wuxianjun
	 */
	public static Object copy(Object newobj, Object oldobj) {
		Class newclazz = newobj.getClass();
		Field[] newfield = newclazz.getDeclaredFields();
		Class oldobjclazz = oldobj.getClass();
		Field[] oldfield = oldobjclazz.getDeclaredFields();
		for (int i = 0; i < newfield.length; i++) {
			String newpropertyName = newfield[i].getName();
			for (int j = 0; j < oldfield.length; j++) {
				String oldpropertyName = oldfield[j].getName();
				if (oldpropertyName == newpropertyName) {
					Object oldValue = getPropertyValue(oldobj, newpropertyName);
					if (oldValue != null) {
						setPropertyValue(newobj, newpropertyName, oldValue);
					}
				}
			}
		}
		return newobj;
	}

	private static PropertyDescriptor getPropertyDescriptor(Class clazz,
			String name) {
		PropertyDescriptor propertyDescriptor = null;
		String propertyName = null;
		Method writeMethod = null;
		Method readMethod = null;
		String first = null;
		String two = null;
		StringBuffer buffer = new StringBuffer();
		String getString = null;
		String setString = null;
		try {
			Field field = clazz.getDeclaredField(name);
			if (field != null) {
				first = name.substring(0, 1);
				first = first.toUpperCase();
				two = name.substring(1);
				buffer.append("set").append(first).append(two);
				setString = buffer.toString();
				Class[] cla = { field.getType() };
				writeMethod = clazz.getMethod(setString, cla);
				buffer.delete(0, buffer.length()); //
				buffer.append("get").append(first).append(two);
				getString = buffer.toString();
				readMethod = clazz.getMethod(getString, null);
				propertyDescriptor = new PropertyDescriptor(name, readMethod,
						writeMethod);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return propertyDescriptor;
	}

	public static void setPropertyValue(Object obj, String name, Object value) {
		try {
			Class clazz = obj.getClass();
			PropertyDescriptor desc = getPropertyDescriptor(clazz, name);
			Method read = desc.getWriteMethod();
			Object[] objArr = { PropertyUtil.convertPropertyValue(obj, name,
					value) };
			obj = read.invoke(obj, objArr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param obj
	 * @param fieldName
	 *            <String>
	 * @param value
	 *            <String>
	 * @return Object
	 */
	public static Object convertPropertyValue(Object obj, String fieldName,
			Object value) {
		Object object = null;
		Class clazz = null;
		Field field = null;
		try {
			clazz = obj.getClass();
			field = clazz.getDeclaredField(fieldName);
			if (field != null) {
				String dataType = field.getType().getName();
				if ("java.lang.Integer".equals(dataType)) {
					object = new Integer(value.toString());
				} else if ("int".equals(dataType)) {
					object = new Integer(value.toString());
				} else if ("java.lang.Long".equals(dataType)) {
					object = new Long(value.toString());
				} else if ("java.lang.Double".equals(dataType)) {
					object = new Double(value.toString());
				} else if ("java.lang.Boolean".equals(dataType)) {
					object = new Boolean(value.toString());
				} else if ("java.util.Date".equals(dataType)) {
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date date = df.parse(value.toString());
					object = date;
				} else if ("java.sql.Date".equals(dataType)) {
					
					String tvalue =null;
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					if(value instanceof java.sql.Timestamp) {
						java.sql.Timestamp dae = (java.sql.Timestamp)value;
						tvalue = dae.toLocaleString();
					}
					else{
						tvalue=value.toString();
					}	
					java.util.Date dateUtil = df.parse(tvalue);
					java.sql.Date dateSql = new Date(dateUtil.getTime());
					object = dateSql;

				} else if ("java.lang.Float".equals(dataType)) {
					object = new Float(value.toString());
				} else if ("java.lang.Short".equals(dataType)) {
					object = new Short(value.toString());
				} else {
					object = value;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	public static Object getPropertyValue(Object object, String name) {
		Object obj = null;
		try {
			if(object!=null){
			Class clazz = object.getClass();
			PropertyDescriptor desc = getPropertyDescriptor(clazz, name);
			Method read = desc.getReadMethod();
			obj = read.invoke(object, null);
			if (obj == "Integer") {
				obj = new Integer(obj.toString());
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static Object setValue(Object obj, String name) {
		Object reust = null;
		try {
			Class clazz = obj.getClass();
			PropertyDescriptor desc = getPropertyDescriptor(clazz, name);
			Method read = desc.getWriteMethod();
			reust = read.invoke(obj, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reust;
	}
	

	/***
	 *  验证字段是否为空。
	 *  @param obj
	 *   @param map
	 *   @return string
	 */
	public static String validate(Object obj, Map map) {
		Class clazz = obj.getClass();
		String rs =null;
		Field[] field = clazz.getDeclaredFields();
		for (int i = 0; i < field.length; i++) {
			String propertyName = field[i].getName();
			if (map.containsKey(propertyName)) {
			    Object ovalue=  PropertyUtil.getPropertyValue(obj, propertyName);
			    if(ovalue==null || ovalue==""){
			    	 rs = (String) map.get(propertyName);
			    	 rs +=",";
			    }
			}
		}
		return rs;
	}
	
	
	/***
	 *  验证字段是否为空。
	 *  @param obj  实体对象(form对象)
	 *  @param map (key需要验证字段名， value提示语句)
	 *  @return string
	 */
	public static ActionErrors validate(Object obj, Map map,ActionErrors errors) {
		Class clazz = obj.getClass();
		Field[] field = clazz.getDeclaredFields();
		for (int i = 0; i < field.length; i++) {
			String propertyName = field[i].getName();
			if (map.containsKey(propertyName)){
			    Object ovalue=  PropertyUtil.getPropertyValue(obj, propertyName);
			    if(ovalue == null || ovalue ==""){
			    	 String rs = (String) map.get(propertyName);
			    	 ActionMessage msg = new ActionMessage("error.required",rs);
			    	 errors.add(propertyName,msg);
			    }
			}
		}
		return errors;
	}
}
