package com.core.jbrown.data.model;

public interface DistrictI {
	String getDistrictId();
	String getStateId();
	String getDistrictName();
	PostalI[] getPostOffices();
}
