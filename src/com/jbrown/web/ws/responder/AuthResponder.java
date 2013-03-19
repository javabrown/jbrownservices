package com.jbrown.web.ws.responder;

import java.util.HashMap;
import java.util.Map;

import com.jbrown.errors.BrownErrorsI;
import com.jbrown.user.Authenticator;
import com.jbrown.web.ws.BrownRequestI;
import com.jbrown.web.ws.Responder;

public class AuthResponder extends Responder {
	@Override
	protected Map<String, Object> perform(BrownRequestI request) {
		Map<String, Object> map = new HashMap<String, Object>();

		String action = (String) request.get("action");
		if (action.equals(ACTION_AUTH_K)) {
			map.putAll(authenticate(request));
		} else if (action.equals(ACTION_REGISTER_K)) {
			map.putAll(createNewUser(request));
		}

		return map;
	}

	private Map<String, Object> authenticate(BrownRequestI request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String token = (String) request.get(TOKEN_K);
		boolean isValid = new Authenticator().autheticate(token);
		map.put(RESPONSE_K, isValid);
		
		if(isValid){
			request.putCache(TOKEN_K, token);
		}
		
		return map;
	}

	private Map<String, Object> createNewUser(BrownRequestI request) {
		Map<String, Object> map = new HashMap<String, Object>();

		String firstName = (String) request.get(FIRST_NAME_K);
		String lastName = (String) request.get(LAST_NAME_K);
		String email = (String) request.get(EMAIL_K);
		String password = (String) request.get(PASSWORD_K);

		String result = new Authenticator().registerNewUser(firstName,
				lastName, email, password);
		map.put(RESPONSE_K, result);

		return map;
	}

	@Override
	protected BrownErrorsI validate(BrownRequestI request) {
		BrownErrorsI errors = request.getErrors();
		// validation pending

		return errors;
	}
}
