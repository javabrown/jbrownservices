package com.jbrown.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Cookies {
	private static final String DEFAULT_PATH = "/";
	private static final int COOKIE_EXPIRATION_IN_DAYS_K = 30;
	private static final int DEFAULT_MAX_AGE =
			                 1 * COOKIE_EXPIRATION_IN_DAYS_K * 24 * 60 * 60; //30 days
	  
	public static String getCookieValue(HttpServletRequest request,
			String cookieName) {
		Cookie cookie = getCookie(request, cookieName);
		return (cookie != null) ? cookie.getValue() : null;
	}
	
	  
	public static void setCookie(HttpServletResponse response,
			String cookieName, String value) {
		Cookie cookie = makeCookie(cookieName, value, DEFAULT_PATH,
				DEFAULT_MAX_AGE, false);
		response.addCookie(cookie);
	}
	  
	
	private static Cookie getCookie(HttpServletRequest request,
			String cookieName) {
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName))
					return cookie;
			}
		}
		return null;
	}
	  
	private static Cookie makeCookie(String cookieName, String value,
			String path, int maxAge, boolean isSecure) {
		Cookie cookie = new Cookie(cookieName, value);
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		cookie.setSecure(isSecure);
		return cookie;
	}
	
 
}
