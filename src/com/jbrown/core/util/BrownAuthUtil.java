package com.jbrown.core.util;

import com.jbrown.user.Authenticator;
import com.jbrown.user.BrownUserI;
import com.jbrown.web.ws.BrownRequestI;

import static com.jbrown.core.util.BrownConstant.*;


public class BrownAuthUtil {

	public static boolean isValidUser(BrownRequestI request) {
		try {
			String token = request.getHeadersMap().get(BrownKeysI.AUTH_CODE_K);
			String fbAccessToken = request.getHeadersMap().get(FB_ACCESS_TOKEN);

			if (request.getSessionCache(BrownKeysI.JAVABROWN_AUTH_K) != null) {
				return true;
			} else if (!StringUtil.isEmpty(fbAccessToken)) {
				return new Authenticator().autheticateFb(fbAccessToken);
			} else {
				System.out.println(BrownKeysI.AUTH_CODE_K + " missing in Header");
			}

		} catch (Exception ex) {
			System.out.println("Invalid user/authcode/facebook-code" + ex);
		}
		return false;
	}

	public static BrownUserI getBrownUser(BrownRequestI request) {
	  return (BrownUserI) request.getSessionCache(BrownKeysI.JAVABROWN_AUTH_K);
	}
	
	public static String getBrownUserId(BrownRequestI request) {
		try {
			String token = request.getHeadersMap().get(BrownKeysI.AUTH_CODE_K);
			String fbAccessToken = request.getHeadersMap().get(FB_ACCESS_TOKEN);

			BrownUserI user = getBrownUser(request);
			
			if (user != null) {
				return user.getEmail();
			} else if (!StringUtil.isEmpty(fbAccessToken)) {
				return new Authenticator().getBrownUserId(fbAccessToken);
			} else {
				System.out.println(BrownKeysI.AUTH_CODE_K
						+ " missing in Header");
			}
		} catch (Exception ex) {
			System.out.println("Invalid user/authcode/facebook-code" + ex);
		}

		return null;
	}
}
