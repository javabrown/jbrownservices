package com.core.jbrown.data.model;

public interface CountryI {
	public String getIsoCode();
	public String getCountryName();
	public StateI[] getStates();
}
