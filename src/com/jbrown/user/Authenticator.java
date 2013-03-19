package com.jbrown.user;

import java.util.regex.Pattern;

import com.jbrown.core.util.BrownConstant;
import com.jbrown.core.util.BrownKeysI;

public class Authenticator {
	
	
	public String autheticate(String email, String password){
		boolean isValidUser = false;
		
		if(email.equalsIgnoreCase("getrk@yahoo.com")){ //Hard-coded for now.
			isValidUser = true;
		}
		
		if(isValidUser){
		   return new AuthenticatorCookie(email, password).encode();
		}
		else{
			
		}
		return null;
	}
	
	public String autheticate(String encodeKey){
		boolean isValidUser = true;
		
		if(isValidUser){
			return new AuthenticatorCookie(encodeKey).email;
		}
		
		return null;
	}
	
	private class AuthenticatorCookie {
		final String authenticator;
		final String email;
		final String name;
		private final String DELIM = "|";
		private final String DELIM_RE = Pattern.quote(DELIM);

		AuthenticatorCookie(String encodedValue) {
			String[] vals = (encodedValue == null) ? null : encodedValue
					.split(DELIM_RE);
			if (vals == null || vals.length != 3) {
				authenticator = BrownKeysI.JAVABROWN_AUTH_K;
				email = "";
				name = "";
			} else {
				authenticator = vals[0];
				email = vals[1];
				name = vals[2];
			}
		}

		AuthenticatorCookie(String email, String name) {
			this.authenticator = BrownKeysI.JAVABROWN_AUTH_K;
			this.email = email;
			this.name = name;
		}

		String encode() {
			return String.format("%s%s%s%s%s", authenticator, DELIM, email,
					DELIM, name);
		}

	}
}
