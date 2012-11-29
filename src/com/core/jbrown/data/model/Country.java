package com.core.jbrown.data.model;

import java.io.Serializable;

public class Country implements CountryI, Serializable{
	private static final long serialVersionUID = 1L;
	private String isoCode;
	private String countryName;
	private State[] states;

	public Country(String isoCode, String countryName, State[] states) {
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
