package com.core.jbrown.data.model;

public class State {
	String countryIsoCode;
	String stateName;
	District[] districts;

	public State(String countryIsoCode, String stateName, District[] districts) {
		this.countryIsoCode = countryIsoCode;
		this.stateName = stateName;
		this.districts = districts;
	}

	public String getCountryIsoCode() {
		return countryIsoCode;
	}

	public String getStateName() {
		return stateName;
	}

	public District[] getDistricts() {
		return districts;
	}
}
