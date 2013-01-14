package com.jbrown.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.map.LRUMap;

import com.jbrown.core.data.CountryData;
import com.jbrown.core.data.model.CountryI;
import com.jbrown.core.data.model.DistrictI;
import com.jbrown.core.data.model.PostOfficeI;
import com.jbrown.core.data.model.StateI;
import com.jbrown.core.util.StrKey;
import com.jbrown.web.servlet.Request;
import com.jbrown.web.servlet.RequestI;

public class BrownDataCache implements Serializable {
	private final int CACHE_SIZE = 1000;
	private static BrownDataCache _instance;
	private final DayTracker _dayTracker;

	private CountryI[] _countryData;
	private Map<String, String[]> _countryStateMap;
	private Map<StrKey, String[]> _stateCityMap; // key = countryCode +
													// stateName
	private Map<StrKey, String[]> _cityPostOfficeMap; // key = countryCode +

	// stateName + cityName
	private BrownDataCache(RequestI request) {
		_dayTracker = new DayTracker();
		new LRUMap(CACHE_SIZE);
		_countryData = new CountryData(request).getCountryData();
	}

	public synchronized static void initializeBrownDataCache(RequestI request) {
		System.out.println("initializeBrownDataCache");
		if (_instance == null /*|| _instance._dayTracker.isNewDay(false)*/) {
			_instance = new BrownDataCache(request);
		}
	}

	public synchronized static BrownDataCache getInstance() {
		// if (_instance == null || _instance._dayTracker.isNewDay(false)) {
		// _instance = new BrownDataCache(_request);
		// }
		return _instance;
	}

	public String[] getStatesName(String countryIsoCode) {
		if (_countryStateMap == null
				|| _countryStateMap.get(countryIsoCode) == null) {
			if (_countryStateMap == null) {
				_countryStateMap = new ConcurrentHashMap<String, String[]>();
			}

			List<String> countryList = new ArrayList<String>();

			for (CountryI country : _countryData) {
				if (country.getIsoCode().equalsIgnoreCase(countryIsoCode)) {
					for (StateI state : country.getStates()) {
						countryList.add(state.getStateName());
					}
				}
			}
			_countryStateMap.put(countryIsoCode,
					countryList.toArray(new String[0]));
		}

		return _countryStateMap.get(countryIsoCode);
	}

	public String[] getCities(String countryIsoCode, String stateName) {
		StrKey key = new StrKey(countryIsoCode, stateName);

		if (_stateCityMap == null || _stateCityMap.get(key) == null) {
			if (_stateCityMap == null) {
				_stateCityMap = new ConcurrentHashMap<StrKey, String[]>();
			}

			for (CountryI country : _countryData) {
				if (country.getIsoCode().equalsIgnoreCase(countryIsoCode)) {
					for (StateI state : country.getStates()) {
						if (state.getStateName().equalsIgnoreCase(stateName)) {
							List<String> list = new ArrayList<String>();

							for (DistrictI district : state.getDistricts()) {
								list.add(district.getDistrictName());
							}
							
							_stateCityMap.put(key, list.toArray(new String[0]));
							break;
						}
					}
				}
			}
		}

		return _stateCityMap.get(key);
	}

	public String[] getPostalOffices(String countryIsoCode, String stateName,
			String cityName) {

		StrKey key = new StrKey(countryIsoCode, stateName, cityName);
		if (_cityPostOfficeMap == null || _cityPostOfficeMap.get(key) == null) {
			if (_cityPostOfficeMap == null) {
				_cityPostOfficeMap = new ConcurrentHashMap<StrKey, String[]>();
			}

			for (CountryI country : _countryData) {
				if (country.getIsoCode().equalsIgnoreCase(countryIsoCode)) {
					for (StateI state : country.getStates()) {
						if (state.getStateName().equalsIgnoreCase(stateName)) {
							List<String> list = new ArrayList<String>();

							for (DistrictI district : state.getDistricts()) {
								if (district.getDistrictName()
										.equalsIgnoreCase(cityName)) {
									List<String> postList = new ArrayList<String>();

									for (PostOfficeI postOffice : district
											.getPostOffices()) {
										postList.add(postOffice.getName().trim() + ":"
												+ postOffice.getZipCode().trim());
									}

									_cityPostOfficeMap.put(key,
											postList.toArray(new String[0]));
									break;
								}
							}
						}
					}
				}
			}
		}

		return _cityPostOfficeMap.get(key);
	}
}
