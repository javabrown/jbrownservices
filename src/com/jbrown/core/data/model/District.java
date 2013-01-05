package com.jbrown.core.data.model;

import java.io.Serializable;
import java.util.Arrays;

public class District implements DistrictI, Serializable{
	private static final long serialVersionUID = 1L;
	String stateName;
	String districtName;
	PostOfficeI[] postOffices;

	public District(String stateName, String districtName,
			PostOffice[] postOffices) {
		this.stateName = stateName;
		this.districtName = districtName;
		this.postOffices = postOffices;
	}
 
	@Override
	public String getDistrictName() {
		return districtName;
	}

	@Override
	public PostOfficeI[] getPostOffices() {
		return postOffices;
	}

	@Override
	public String getStateName() {
		return stateName;
	}

	@Override
	public void updatePostOffice(PostOfficeI[] postOfficeI) {
		postOffices = postOfficeI;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((districtName == null) ? 0 : districtName.hashCode());
		result = prime * result + Arrays.hashCode(postOffices);
		result = prime * result
				+ ((stateName == null) ? 0 : stateName.hashCode());
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
		District other = (District) obj;
		if (districtName == null) {
			if (other.districtName != null)
				return false;
		} else if (!districtName.equals(other.districtName))
			return false;
		if (!Arrays.equals(postOffices, other.postOffices))
			return false;
		if (stateName == null) {
			if (other.stateName != null)
				return false;
		} else if (!stateName.equals(other.stateName))
			return false;
		return true;
	}
	
	
}
