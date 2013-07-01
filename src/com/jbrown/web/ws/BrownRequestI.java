package com.jbrown.web.ws;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jbrown.errors.BrownErrorsI;
import com.jbrown.web.servlet.RequestI;

public interface BrownRequestI extends RequestI {
	HttpServletRequest getHttpServletRequest();

	HttpServletResponse getHttpServletResponse();

	Object getCache(String key);

	void putCache(String key, Object value);

	String get(String key);

	void set(String key, String value);
	
	BrownErrorsI getErrors();
}
