package com.core.ext.oauth;

import java.io.Serializable;

import com.jbrown.user.BrownUserI;

public class FacebookUserInfo implements BrownUserI, Serializable {
	private static final long serialVersionUID = 1L;
	private static final String NAMESPACE = "JB-Facebook";
	private String id;
	private String email;
	private String firstName;
	private String lastName;
	private String gender;
	private String fullName;
	private String fbUserId;
	private String locale;

	public FacebookUserInfo(String id, String email, String firstName,
			String lastName, String gender, String fullName, String fbUserId,
			String locale) {
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.fullName = fullName;
		this.fbUserId = fbUserId;
		this.locale = locale;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}


  @Override
  public String getName() {
    return String.format("%s %s", firstName , lastName);
  }

  @Override
  public String getPhone() {
    return "";
  }
  
	public String getGender() {
		return gender;
	}

	public String getFullName() {
		return fullName;
	}

	public String getFbUserId() {
		return fbUserId;
	}

	public String getLocale() {
		return locale;
	}

	@Override
	public String getBrownUserId() {
		return String.format("%s|%s", NAMESPACE, email);
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getEncodeString() {
		return null;
	}

	@Override
	public String getDomain() {
		return null;
	}

	@Override
	public String getEncryptedKey() {
		return null;
	}

}