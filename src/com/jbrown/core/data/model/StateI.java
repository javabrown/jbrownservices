package com.jbrown.core.data.model;

public interface StateI {
	String getCountryIsoCode();
	String getStateName();
	DistrictI[] getDistricts();
	void updateDistricts(DistrictI[] districts);
}
