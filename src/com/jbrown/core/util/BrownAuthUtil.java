package com.jbrown.core.util;

import com.jbrown.core.exception.BorwnException;
import com.jbrown.web.Cookies;
import com.jbrown.web.ws.BrownRequestI;

public class BrownAuthUtil {

	public static boolean isValidUser(BrownRequestI request) {
		String resp = Cookies.getCookieValue(request.getHttpServletRequest(),
				BrownKeysI.JAVABROWN_AUTH_K);

		if (!StringUtil.isEmpty(resp)
				&& !resp.equalsIgnoreCase(BrownKeysI.INVALID_AUTH_K)) {
			return true;
		}

		return false;
	}

}
