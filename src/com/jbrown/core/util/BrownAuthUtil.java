package com.jbrown.core.util;

import com.jbrown.user.Authenticator;
import com.jbrown.web.ws.BrownRequestI;

public class BrownAuthUtil {

	public static boolean isValidUser(BrownRequestI request) {
		
		// String resp = Cookies.getCookieValue(request.getHttpServletRequest(),
		// BrownKeysI.JAVABROWN_AUTH_K);
		//
		// String authId = request.getHttpServletRequest().getParameter(
		// BrownKeysI.AUTH_ID);
		// if (!StringUtil.isEmpty(resp)
		// && !resp.equalsIgnoreCase(BrownKeysI.INVALID_AUTH_K)) {
		// return true;
		// }
		
		
		String token = request.getHttpServletRequest().getParameter(
				BrownKeysI.TOKEN_K);//request.getCache(BrownKeysI.TOKEN_K)

		if (!StringUtil.isEmpty(token)) {
			return new Authenticator().autheticate(token);
		}

		return false;
	}

}
