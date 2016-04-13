package com.laungee.proj.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public class SpringUtil {
	public final static String COMMONBIZ= "commonBiz";
	public final static String COMMONDAO= "commonDao";
	public final static String operateUpdated= "operateUpdated";
	public final static String SAVEACTION= "saveAction";
	/**
	 * Spring容器
	 */
	public static ApplicationContext context;

	/**
	 * 设置Spring容器
	 */
	public static void setApplicationContext(ApplicationContext acx) {
		context = acx;
	}

	/**
	 * 得到Spring容器
	 */
	public static ApplicationContext getApplicationContext() {
		return context;
	}

	/**
	 * 得到Spring的Bean
	 */
	public static Object getBean(String name) throws BeansException {
		return context.getBean(name);
	}
}
