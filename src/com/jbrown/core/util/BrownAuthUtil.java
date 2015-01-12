package com.jbrown.core.util;

import com.jbrown.user.Authenticator;
import com.jbrown.web.ws.BrownRequestI;
import static com.jbrown.core.util.BrownConstant.*;


public class BrownAuthUtil {

	public static boolean isValidUser(BrownRequestI request) {
		String token = request.getHeadersMap().get(BrownKeysI.AUTH_CODE_K);
		String fbAccessToken = request.getHeadersMap().get(FB_ACCESS_TOKEN);
				
		if (!StringUtil.isEmpty(token)) {
			//new AuthResponder().respond(request);
			return new Authenticator().autheticate(token);
		}
		else if(!StringUtil.isEmpty(fbAccessToken)){
			return new Authenticator().autheticateFb(fbAccessToken);
		}
		else{
			System.out.println(BrownKeysI.AUTH_CODE_K+ " missing in Header");
		}

		return false;
	}

}
