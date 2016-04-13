package com.laungee.proj.common.core;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*
 * ×Ö·û×ª»»¹ýÂËÆ÷
 */
public class FilterEncoding implements Filter {
	private String encoding = null;
	private FilterConfig fiterConfig = null;
	private boolean ignore = true;

	public void destroy() {
		this.encoding = null;
		this.fiterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		// ÅÐ¶ÏÊÇ·ñÐèÒª±àÂëÉèÖÃ
		if (!ignore || request.getCharacterEncoding() == null) {
			String encoding = this.selectEncoding(request);
			if (encoding != null) {
				request.setCharacterEncoding(encoding);
			}
		}
		filterChain.doFilter(request, response);
	}

	public void init(FilterConfig fiterConfig) throws ServletException {
		this.fiterConfig = fiterConfig;
		this.encoding = fiterConfig.getInitParameter("encoding");
		String value = fiterConfig.getInitParameter("ignore");
		if (value == null) {
			this.ignore = false;
		} else if (value.equalsIgnoreCase("false")) {
			this.ignore = true;
		} else if (value.equalsIgnoreCase("no"))
			this.ignore = true;
		else
			this.ignore = false;
	}

	protected String selectEncoding(ServletRequest request) {
		return (this.encoding);
	}

}