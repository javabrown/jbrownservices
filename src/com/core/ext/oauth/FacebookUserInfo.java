package com.core.ext.oauth;

import java.io.Serializable;

public class FacebookUserInfo implements Serializable {
	private static final long serialVersionUID = 1L;
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
}