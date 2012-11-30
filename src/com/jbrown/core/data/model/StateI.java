package com.jbrown.core.data.model;

public interface StateI {
	String getCountryIsoCode();
	String getStateName();
	District[] getDistricts();
}
