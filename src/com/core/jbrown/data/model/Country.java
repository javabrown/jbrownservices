package com.core.jbrown.data.model;

public class Country {
	private String isoCode;
	private String countryName;
	private State[] states;

	public Country(String isoCode, String countryName, State[] states) {
		super();
		this.isoCode = isoCode;
		this.countryName = countryName;
		this.states = states;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public State[] getStates() {
		return states;
	}
}
