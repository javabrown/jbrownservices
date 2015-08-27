package com.jbrown.web.ws.responder;

import java.util.HashMap;
import java.util.Map;
 
import com.jbrown.core.util.BrownAuthUtil;
import com.jbrown.core.util.BrownKeysI;
import com.jbrown.core.util.StringUtil;
import com.jbrown.db.dao.UserDao;
import com.jbrown.errors.BrownErrorsI;
import com.jbrown.user.BrownUserI;
import com.jbrown.web.ws.BrownRequestI;
import com.jbrown.web.ws.Responder;

import static com.jbrown.core.util.StringUtil.isEmpty;
import static com.jbrown.core.util.StringUtil.isUnsafeString;

public class LoginResponder extends Responder {
	@Override
	protected Map<String, Object> perform(BrownRequestI request) {
		String email = (String) request.get(EMAIL_K); 
		String password = (String) request.get(PASSWORD_K);

		String authCode = this.doAuth(request, email, password);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(RESPONSE_K, authCode != null);
		result.put(AUTH_CODE_K, authCode);

		return result;
	}

	@Override
	protected BrownErrorsI validate(BrownRequestI request) {
		BrownErrorsI errors = request.getErrors();
 
		String email = (String) request.get(EMAIL_K);
		String password = (String) request.get(PASSWORD_K);
		String authCode = (String) request.get(AUTH_CODE_K);

		if (isEmpty(email, password) &&  isEmpty(authCode)) {
			errors.add(String.format(
					"Mandatory Fields Missing :[%s | %s ] OR [ %s ]", EMAIL_K, 
					PASSWORD_K, AUTH_CODE_K));
		} else if (isUnsafeString(email, password, authCode)) {
			errors.add(String.format("Special chars not allowed:",
					StringUtil.UNSAFE_STRING));
		} else if (!StringUtil.isEmpty(email) && !StringUtil.isValidEmail(email)) {
			errors.add(String.format("Invalid Email Format %s", email));
		}
		else if(isUserAlreadyLogin(request)){
		  String code = BrownAuthUtil.getBrownUser(request).getEncryptedKey();
			errors.add(String.format("Already authenticated. %s=%s", AUTH_CODE_K, code));
		}

		return errors;
	}

	private boolean isUserAlreadyLogin(BrownRequestI req) {
	  BrownUserI user = BrownAuthUtil.getBrownUser(req);
	 
		    
		if (user != null) {
			return true;
		}

		return false;
	}
	
	private String doAuth(BrownRequestI req, String email, String password) {
		BrownUserI user = new UserDao(req).findUser(email, password);
		
		if (user != null) {
			req.putSessionCache(BrownKeysI.JAVABROWN_AUTH_K, user);
			
			return user.getEncryptedKey();
		}
		
		return null;
	}
}
