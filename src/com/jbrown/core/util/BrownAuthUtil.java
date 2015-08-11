package com.jbrown.core.util;

import com.jbrown.user.Authenticator;
import com.jbrown.web.ws.BrownRequestI;
import static com.jbrown.core.util.BrownConstant.*;


public class BrownAuthUtil {

	public static boolean isValidUser(BrownRequestI request) {
		try {
			String token = request.getHeadersMap().get(BrownKeysI.AUTH_CODE_K);
			String fbAccessToken = request.getHeadersMap().get(FB_ACCESS_TOKEN);

			if (!StringUtil.isEmpty(token)) {
				 
				return new Authenticator().autheticate(token);
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

	
	public static String getBrownUserId(BrownRequestI request) {
		try {
			String token = request.getHeadersMap().get(BrownKeysI.AUTH_CODE_K);
			String fbAccessToken = request.getHeadersMap().get(FB_ACCESS_TOKEN);

			if (!StringUtil.isEmpty(token)) {
				return new Authenticator().getBrownUserId(token);
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
