package com.laungee.proj.common.core;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterDispatcher extends org.apache.struts2.dispatcher.FilterDispatcher{
	private  static String encoding="UTF-8";
    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);
        String encodingParam = filterConfig.getInitParameter("encoding");
        if (encodingParam != null && encodingParam.trim().length() != 0) {
            encoding = encodingParam;
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        super.doFilter(request, response, chain);
    }

}
