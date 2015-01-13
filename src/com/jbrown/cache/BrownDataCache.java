package com.jbrown.cache;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LRUMap;

import com.jbrown.core.data.CountryData;
import com.jbrown.core.data.model.CountryI;
import com.jbrown.core.util.StrKey;
import com.jbrown.ext.capsule.BrownCapsule;
import com.jbrown.ext.capsule.BrownCapsuleI;
import com.jbrown.ext.capsule.impl.BrownGeoCapsuleI;
 
import com.jbrown.web.servlet.RequestI;

public class BrownDataCache implements Serializable {
	private static final long serialVersionUID = 1L;
	private final int CACHE_SIZE = 1000;
	private static BrownDataCache _instance;
	private final DayTracker _dayTracker;

	private CountryI[] _countryData;
	private Map<String, String[]> _countryStateMap;
	private Map<StrKey, String[]> _stateCityMap; // key = countryCode +
													// stateName
	private Map<StrKey, String[]> _cityPostOfficeMap; // key = countryCode +
	
	private BrownCapsuleI _brownCapsule;
	
	// stateName + cityName
	private BrownDataCache(RequestI request) {
		_dayTracker = new DayTracker();
		new LRUMap(CACHE_SIZE);
		_countryData = new CountryData(request).getCountryData();
		try {
			_brownCapsule = new BrownCapsule();
		} catch (Exception ex) {
			System.out.println("Error while loading brown-capsule");
			ex.printStackTrace();
		}
	}

	public synchronized static void initializeBrownDataCache(RequestI request) {
		System.out.println("initializeBrownDataCache");
		if (_instance == null || _instance._dayTracker.isNewDay(false)) {
			_instance = new BrownDataCache(request);
		}
	}

	public synchronized static BrownDataCache getInstance(RequestI request) {
		 if (_instance == null /* || _instance._dayTracker.isNewDay(false)*/) {
		 _instance = new BrownDataCache(request);
		 }
		 if (_instance == null /* || _instance._dayTracker.isNewDay(false) */) {
		 _instance = new BrownDataCache(request);
		 }
		return _instance;
	}
	
	public BrownGeoCapsuleI getBrownGeoCapsule(String countryCode) {
		try {
			return new BrownCapsule().getGeoCapsule(countryCode);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	

////	public String[] getStatesName(String countryIsoCode) {
////		if (_countryStateMap == null
////				|| _countryStateMap.get(countryIsoCode) == null) {
////			if (_countryStateMap == null) {
////				_countryStateMap = new ConcurrentHashMap<String, String[]>();
////			}
////
////			List<String> countryList = new ArrayList<String>();
////
////			for (CountryI country : _countryData) {
////				if (country.getIsoCode().equalsIgnoreCase(countryIsoCode)) {
////					for (StateI state : country.getStates()) {
////						countryList.add(state.getStateName());
////					}
////				}
////			}
////			_countryStateMap.put(countryIsoCode,
////					countryList.toArray(new String[0]));
////		}
////		_geoCapsule.getAllIndianStates()
////		return _countryStateMap.get(countryIsoCode);
////	}
//	
//	/**
//	 * FOR INDIA ONLY
//	 * @param countryIsoCode
//	 * @return
//	 */
//	public List<Map<String, String>> getIndianStates() {
////		if (_countryStateMap == null
////				|| _countryStateMap.get(countryIsoCode) == null) {
////			if (_countryStateMap == null) {
////				_countryStateMap = new ConcurrentHashMap<String, String[]>();
////			}
////
////			List<String> countryList = new ArrayList<String>();
////			List<Map<String, String>> indianStates
////			  = _geoCapsule.getAllIndianStates();
////			
////			for (Map<String, String> states : indianStates) {
////				String stateName = states.get("stateName");
////				
////				if ("IN".equalsIgnoreCase(countryIsoCode)) {
////				   countryList.add(stateName);
////				}
////			}
////			_countryStateMap.put(countryIsoCode,
////					countryList.toArray(new String[0]));
////		}
//	 
//		return _brownCapsule.getAllIndianStates();
//	}	
//	
//	
//
////	public String[] getCities(String countryIsoCode, String stateName) {
////		StrKey key = new StrKey(countryIsoCode, stateName);
////
////		if (_stateCityMap == null || _stateCityMap.get(key) == null) {
////			if (_stateCityMap == null) {
////				_stateCityMap = new ConcurrentHashMap<StrKey, String[]>();
////			}
////
////			for (CountryI country : _countryData) {
////				if (country.getIsoCode().equalsIgnoreCase(countryIsoCode)) {
////					for (StateI state : country.getStates()) {
////						if (state.getStateName().equalsIgnoreCase(stateName)) {
////							List<String> list = new ArrayList<String>();
////
////							for (DistrictI district : state.getDistricts()) {
////								list.add(district.getDistrictName());
////							}
////							
////							_stateCityMap.put(key, list.toArray(new String[0]));
////							break;
////						}
////					}
////				}
////			}
////		}
////
////		return _stateCityMap.get(key);
////	}
//
//	public List<Map<String, String>> getCities(String countryIsoCode,
//			String stateId) {
//		return _geoCapsule.getCitiesForStateId(stateId);
//	}
////	
////	public String[] getPostalOffices(String countryIsoCode, String stateName,
////			String cityName) {
////
////		StrKey key = new StrKey(countryIsoCode, stateName, cityName);
////		if (_cityPostOfficeMap == null || _cityPostOfficeMap.get(key) == null) {
////			if (_cityPostOfficeMap == null) {
////				_cityPostOfficeMap = new ConcurrentHashMap<StrKey, String[]>();
////			}
////
////			for (CountryI country : _countryData) {
////				if (country.getIsoCode().equalsIgnoreCase(countryIsoCode)) {
////					for (StateI state : country.getStates()) {
////						if (state.getStateName().equalsIgnoreCase(stateName)) {
////							List<String> list = new ArrayList<String>();
////
////							for (DistrictI district : state.getDistricts()) {
////								if (district.getDistrictName()
////										.equalsIgnoreCase(cityName)) {
////									List<String> postList = new ArrayList<String>();
////
////									for (PostOfficeI postOffice : district
////											.getPostOffices()) {
////										postList.add(postOffice.getName().trim() + ":"
////												+ postOffice.getZipCode().trim());
////									}
////
////									_cityPostOfficeMap.put(key,
////											postList.toArray(new String[0]));
////									break;
////								}
////							}
////						}
////					}
////				}
////			}
////		}
////
////		return _cityPostOfficeMap.get(key);
////	}
//	
//	public List<Map<String, String>> getPostalOffices(String lookupCityId) {
//		return _geoCapsule.getPostOfficesForCityId(lookupCityId);
//	}
}
