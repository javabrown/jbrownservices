package com.jbrown.web.servlet;

import javax.servlet.ServletContext;

import com.jbrown.web.BrownContextI;

public class RequestContext {
	ServletContext _servletContext;

	public RequestContext(ServletContext context) {
		_servletContext = context;
	}

	public ServletContext getServletContext() {
		return _servletContext;
	}
}
