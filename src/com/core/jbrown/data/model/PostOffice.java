package com.core.jbrown.data.model;

import java.io.Serializable;

public class PostOffice implements PostalI, Serializable {
	private static final long serialVersionUID = 1L;
	String name;
	String zipCode;
	String districtId;
	
	public PostOffice(String name, String zipCode, String districtId) {
		this.name = name;
		this.zipCode = zipCode;
		this.districtId = districtId;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getZipCode() {
		return zipCode;
	}
}
