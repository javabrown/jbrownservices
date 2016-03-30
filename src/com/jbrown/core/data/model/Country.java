package com.jbrown.core.data.model;

import java.io.Serializable;
import java.util.Arrays;

public class Country implements CountryI, Serializable{
	private static final long serialVersionUID = 1L;
	private String isoCode;
	private String countryName;
	private StateI[] states;

	public Country(String isoCode, String countryName, StateI[] states) {
		this.isoCode = isoCode;
		this.countryName = countryName;
		this.states = states;
	}

	@Override
	public String getIsoCode() {
		return isoCode;
	}

	@Override
	public String getCountryName() {
		return countryName;
	}

	@Override
	public StateI[] getStates() {
		return states;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((countryName == null) ? 0 : countryName.hashCode());
		result = prime * result + ((isoCode == null) ? 0 : isoCode.hashCode());
		result = prime * result + Arrays.hashCode(states);
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
		Country other = (Country) obj;
		if (countryName == null) {
			if (other.countryName != null)
				return false;
		} else if (!countryName.equals(other.countryName))
			return false;
		if (isoCode == null) {
			if (other.isoCode != null)
				return false;
		} else if (!isoCode.equals(other.isoCode))
			return false;
		if (!Arrays.equals(states, other.states))
			return false;
		return true;
	}
	
	
}
