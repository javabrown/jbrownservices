package com.jbrown.core.data.model;

import java.io.Serializable;

public class PostOffice implements PostOfficeI, Serializable {
	private static final long serialVersionUID = 1L;
	String name;
	String zipCode;
	String districtName;
	
	public PostOffice(String name, String zipCode, String districtId) {
		this.name = name;
		this.zipCode = zipCode;
		this.districtName = districtId;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getZipCode() {
		return zipCode;
	}
 
	public String getDistrictName() {
		return districtName;
	}

	@Override
	public String getCityName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((districtName == null) ? 0 : districtName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
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
		PostOffice other = (PostOffice) obj;
		if (districtName == null) {
			if (other.districtName != null)
				return false;
		} else if (!districtName.equals(other.districtName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}	
	
	
}
