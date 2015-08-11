package com.jbrown.user;

import com.jbrown.core.exception.BorwnException;
import com.jbrown.core.util.BrownCrypter;
import com.jbrown.core.util.BrownKeysI;
import com.jbrown.core.util.StringUtil;

/**
 * 
 * @author rkhan
 * 
 */
public class BrownUser implements BrownUserI {
	private String id;
	private String name;
	private String email;
	private String phone;
	private String password;
	private String domain;

	public BrownUser(String id, String name, String email, String phone,
			String password, String domain) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.domain = domain;
	}

	@Override
	public String getBrownUserId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getPassword() {
		return password;
	}

	public String getDomain() {
		return domain;
	}

	@Override
	public String getEncodeString() {
		return this.id + "__" + this.name + "__" + this.email;
	}
	
	@Override
	public String getEncryptedKey(){
		return new AuthCrypter().getEncryptedCode();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((domain == null) ? 0 : domain.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
		if (domain == null) {
			if (other.domain != null)
				return false;
		} else if (!domain.equals(other.domain))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BrownUser [id=" + id + ", name=" + name + ", email=" + email
				+ ", phone=" + phone + ", password=" + password + ", domain="
				+ domain + "]";
	}

	class AuthCrypter {
		private String SAPERATOR = "#KhAn#";
		 
		public String getEncryptedCode() {
			try {
				return new BrownCrypter(getKey()).encrypt(getValue());
			} catch (Exception e) {
				throw new BorwnException("Error during auth encryption." + e);
			}
		}
		
		private String getKey() {
			return String.format("%semail=%s%spassword=%s%s", SAPERATOR, email, SAPERATOR, phone, SAPERATOR);
		}
		
		private String getValue() {
			return  String.format("%sname=%s%s", SAPERATOR, name, SAPERATOR);
		}
		
		private String getParsedName(String encodedStr) {
			if(isValidEncode(encodedStr)){
				String[] str = encodedStr.split(SAPERATOR);
				return str[0];
			}
			
			return BrownKeysI.EMPTY_K;
		}
		
		private String getParsedEmail(String encodedStr) {
			if(isValidEncode(encodedStr)){
				String[] str = encodedStr.split(SAPERATOR);
				return str[1];
			}
			
			return BrownKeysI.EMPTY_K;
		}

		private String getParsedDomain(String encodedStr) {
			if(isValidEncode(encodedStr)){
				String[] str = encodedStr.split(SAPERATOR);
				return str[2];
			}
			
			return BrownKeysI.EMPTY_K;
		}
		
		private boolean isValidEncode(String encodedStr) {
			if(!StringUtil.isEmpty(encodedStr)) {
				String[] str = encodedStr.split(SAPERATOR);
				if(str.length == 3){
					return true;
				}
			}
			 
			return false;
		}
	}
}


