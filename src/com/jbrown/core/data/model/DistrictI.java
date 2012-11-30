package com.jbrown.core.data.model;

public interface DistrictI {
	String getDistrictId();
	String getStateId();
	String getDistrictName();
	PostalI[] getPostOffices();
}
