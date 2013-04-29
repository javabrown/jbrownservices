package com.jbrown.web.ws.responder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.jbrown.cache.BrownDataCache;
import com.jbrown.errors.BrownErrorsI;
import com.jbrown.ext.capsule.BrownCapsule;
import com.jbrown.ext.capsule.impl.BrownGeoCapsuleI;
import com.jbrown.web.ws.BrownRequestI;
import com.jbrown.web.ws.Responder;
import com.jbrown.web.ws.Error;

public class CountryInfoResponder extends Responder {
	@Override
	protected Map<String, Object> perform(BrownRequestI brownRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
         
		String action = (String) brownRequest.get("action");
		 
		//@0
		if(action.equalsIgnoreCase("getIsoCountries")){
			BrownGeoCapsuleI geoCapsule = BrownDataCache.getInstance()
					.getBrownGeoCapsule("IN");
			
			List<Map<String, String>> countries = 
				geoCapsule.getCapsuleData().getIsoCountries();
			map.put("ISO Countries", countries);
		}
		
		//@1
		if(action.equalsIgnoreCase("getCountryInfo")){
			String countryName = (String) brownRequest.get("countryName");
			BrownGeoCapsuleI geoCapsule = BrownDataCache.getInstance()
					.getBrownGeoCapsule(countryName);
			
			List<Map<String, String>> stateList = 
				geoCapsule.getCapsuleData().getAllStates();
			map.put(countryName, stateList);
		}
		
		//@2
		if (action.equalsIgnoreCase("getStateInfo")) {
			String countryName = (String) brownRequest.get("countryName");
			String stateName = (String) brownRequest.get("stateName");
			
			BrownGeoCapsuleI  geoCapsule = 
			    BrownDataCache.getInstance().getBrownGeoCapsule(countryName);
			
			List<Map<String, String>> stateCityList = 
				 geoCapsule.getCapsuleData().getCitiesForStateCode(stateName);
		 
			map.put(countryName + "/" + stateName, stateCityList);
		}
		
		//@3
		if (action.equalsIgnoreCase("getCityInfo")) {
			String countryName = (String) brownRequest.get("countryName");
			String stateName = (String) brownRequest.get("stateName");
			String cityName = (String) brownRequest.get("cityName");
			
			BrownGeoCapsuleI  geoCapsule = 
				  BrownDataCache.getInstance().getBrownGeoCapsule(countryName);
			
			List<Map<String, String>> stateCityList = geoCapsule
					.getCapsuleData().getPostalLocationsForCityNameOrZipCode(
							stateName, cityName);
			  
			map.put(countryName, stateCityList);
		}
 
		
		return map;
	}

	@Override
	protected BrownErrorsI validate(BrownRequestI request) {
		BrownErrorsI  errors = request.getErrors();
		//validation pending
		
		return errors;
	}
}