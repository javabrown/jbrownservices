package com.jbrown.web.ws;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jbrown.errors.BrownErrorsI;
import com.jbrown.web.servlet.RequestI;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public interface BrownRequestI extends RequestI {
	HttpServletRequest getHttpServletRequest();

	HttpServletResponse getHttpServletResponse();

	Object getCache(String key);

	void putCache(String key, Object value);

    Object getSessionCache(String key);

	void putSessionCache(String key, Object value);
	 
	String get(String key);

	void set(String key, String value);
	
	BrownErrorsI getErrors();
	
	Map<String, String> getHeadersMap();
	
	JSONObject getJson();
	
	String getDomain();
}
