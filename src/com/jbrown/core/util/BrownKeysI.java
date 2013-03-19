package com.jbrown.core.util;

import org.springframework.web.servlet.ModelAndView;

public interface BrownKeysI {
	String HTTP_METHOD_K = "HTTP_METHOD";
	String HTTP_METHOD_POST_K = "POST";
	String ERRORS_K = "ERRORS";
	String ACTION_K = "action";
	String COUNTRY_NAME_K = "countryName";
	
	ModelAndView EMPTY_VIEW = null;
	
	String BROWN_REQUEST_OBJ_K = "BROWN_REQUEST_OBJ";  
	String JAVABROWN_AUTH_K = "JAVABROWN-AUTH";
	String INVALID_AUTH_K = "INVALID-AUTH";
}
