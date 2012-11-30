package com.core.jbrown.data.model;

public class IndiaPinCodeData {
	private String pinCode;
	private String distName;
	private String cityName;
	private String stateName;
	
	public IndiaPinCodeData(String pinCode, String distName, String cityName,
			String stateName) {
		this.pinCode = pinCode;
		this.distName = distName;
		this.cityName = cityName;
		this.stateName = stateName;
	}

	public String getPinCode() {
		return pinCode;
	}

	public String getDistName() {
		return distName;
	}

	public String getCityName() {
		return cityName;
	}

	public String getStateName() {
		return stateName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cityName == null) ? 0 : cityName.hashCode());
		result = prime * result
				+ ((distName == null) ? 0 : distName.hashCode());
		result = prime * result + ((pinCode == null) ? 0 : pinCode.hashCode());
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
		IndiaPinCodeData other = (IndiaPinCodeData) obj;
		if (cityName == null) {
			if (other.cityName != null)
				return false;
		} else if (!cityName.equals(other.cityName))
			return false;
		if (distName == null) {
			if (other.distName != null)
				return false;
		} else if (!distName.equals(other.distName))
			return false;
		if (pinCode == null) {
			if (other.pinCode != null)
				return false;
		} else if (!pinCode.equals(other.pinCode))
			return false;
		if (stateName == null) {
			if (other.stateName != null)
				return false;
		} else if (!stateName.equals(other.stateName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IndiaPinCodeData [pinCode=" + pinCode + ", distName="
				+ distName + ", cityName=" + cityName + ", stateName="
				+ stateName + "]";
	}
	
	
}
