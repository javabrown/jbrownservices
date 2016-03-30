package com.jbrown.core.data.model;

public interface CountryI {
	public String getIsoCode();
	public String getCountryName();
	public StateI[] getStates();
}
