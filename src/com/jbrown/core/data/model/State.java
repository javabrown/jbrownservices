package com.jbrown.core.data.model;

import java.io.Serializable;
import java.util.Arrays;

public class State implements StateI, Serializable {
	private static final long serialVersionUID = 1L;
	String countryIsoCode;
	String stateName;
	DistrictI[] districts;

	public State(String countryIsoCode, String stateName,
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
	public DistrictI[] getDistricts() {
		return districts;
	}

	@Override
	public void updateDistricts(DistrictI[] districts) {
		this.districts = districts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((countryIsoCode == null) ? 0 : countryIsoCode.hashCode());
		result = prime * result + Arrays.hashCode(districts);
		result = prime * result
				+ ((stateName == null) ? 0 : stateName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (countryIsoCode == null) {
			if (other.countryIsoCode != null)
				return false;
		} else if (!countryIsoCode.equals(other.countryIsoCode))
			return false;
		if (!Arrays.equals(districts, other.districts))
			return false;
		if (stateName == null) {
			if (other.stateName != null)
				return false;
		} else if (!stateName.equals(other.stateName))
			return false;
		return true;
	}
}
