package com.jbrown.user;

/**
 * 
 * @author rkhan
 *
 */
public class BrownUser implements BrownUserI {
	private String firstName;
	private String lastName;
	private String email;
	private String password;

	public BrownUser(String firstName, String lastName, String userEmail,
			String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = userEmail;
		this.password = password;
	}

	@Override
	public String getFirstName() {
		return this.firstName;
	}

	@Override
	public String getLastName() {
		return this.lastName;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getEncodeString(){
		String userId ="1"; //Not implemented yet
		return userId + "__" + this.lastName + "__" + this.email;	
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BrownUser other = (BrownUser) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BrownUser [firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", password=" + password + "]";
	}

	@Override
	public String getBrownUserId() {
		return null;
	}
}
