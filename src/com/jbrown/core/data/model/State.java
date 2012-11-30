package com.jbrown.core.data.model;

import java.io.Serializable;

public class State implements StateI, Serializable {
	private static final long serialVersionUID = 1L;
	String countryIsoCode;
	String stateId;
	String stateName;
	District[] districts;

	public State(String countryIsoCode, String stateId, String stateName,
			District[] districts) {
		this.countryIsoCode = countryIsoCode;
		this.stateName = stateName;
		this.districts = districts;
	}
	
    @Override
	public String getCountryIsoCode() {
		return countryIsoCode;
	}

    @Override
	public String getStateName() {
		return stateName;
	}

    @Override
	public District[] getDistricts() {
		return districts;
	}
}
