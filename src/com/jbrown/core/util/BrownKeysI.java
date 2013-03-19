package com.jbrown.core.util;

import org.springframework.web.servlet.ModelAndView;

public interface BrownKeysI {
	String HTTP_METHOD_K = "HTTP_METHOD";
	String HTTP_METHOD_POST_K = "POST";
	String ERRORS_K = "ERRORS";
	String ACTION_K = "action";
	String COUNTRY_NAME_K = "countryName";
	
	String ACTION_AUTH_K = "authenticate";
	String ACTION_REGISTER_K = "register";
	String USER_INFO_ACTION_K = "userInfo";
	String GEO_GET_DISTANCE_K = "getDistance";
 
	ModelAndView EMPTY_VIEW = null;
	
	String BROWN_REQUEST_OBJ_K = "BROWN_REQUEST_OBJ";  
	String JAVABROWN_AUTH_K = "JAVABROWN-AUTH";
	String INVALID_AUTH_K = "INVALID-AUTH";
	String AUTH_ID = "authid";
	
	String RESPONSE_K = "response";
	
	String FIRST_NAME_K = "first_name";
	String LAST_NAME_K = "last_name";
	String EMAIL_K = "email";
	String PASSWORD_K = "password";
	String TOKEN_K = "token";
	
}
