package com.jbrown.web.resources;

public class StaticData {
	private CountryData countryData;
	private ProvidersData providersData;
	
	public CountryData getCountryData() {
		return countryData;
	}
	
	public ProvidersData getProvidersData() {
		return providersData;
	}
	
	public void setProvidersData(ProvidersData providersData) {
		this.providersData = providersData;
	}
	
	public void setCountryData(CountryData countryData) {
		this.countryData = countryData;
	}
}
