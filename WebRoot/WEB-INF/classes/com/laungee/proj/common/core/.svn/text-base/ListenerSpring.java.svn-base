package com.laungee.proj.common.core;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.laungee.proj.common.util.SpringUtil;

public class ListenerSpring extends ContextLoaderListener {

	public void contextInitialized(ServletContextEvent event) {
		// 调用父类方法
		super.contextInitialized(event);
		// Servlet容器
		ServletContext context = event.getServletContext();
		// Spring容器
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(context);
		// 设置Spring容器
		SpringUtil.setApplicationContext(ctx);
	}
}
