package com.jbrown.core.data.model;

import java.io.Serializable;

public class District implements DistrictI, Serializable{
	private static final long serialVersionUID = 1L;
	String stateId;
	String districtId;
	String districtName;
	PostalI[] postOffices;

	public District(String districtId, String stateId, String districtName,
			PostOffice[] postOffices) {
		this.districtId = districtId;
		this.stateId = stateId;
		this.districtName = districtName;
		this.postOffices = postOffices;
	}

	@Override
	public String getDistrictId() {
		return districtId;
	}
	
	@Override
	public String getDistrictName() {
		return districtName;
	}

	@Override
	public PostalI[] getPostOffices() {
		return postOffices;
	}

	@Override
	public String getStateId() {
		return stateId;
	}
}
