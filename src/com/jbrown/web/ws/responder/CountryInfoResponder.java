package com.jbrown.web.ws.responder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.jbrown.cache.BrownDataCache;
import com.jbrown.errors.BrownErrorsI;
import com.jbrown.web.ws.BrownRequestI;
import com.jbrown.web.ws.Responder;
import com.jbrown.web.ws.Error;

public class CountryInfoResponder extends Responder {
	@Override
	protected Map<String, Object> perform(BrownRequestI brownRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
         
		String action = (String) brownRequest.get("action");
		
		//@1
		if(action.equalsIgnoreCase("getCountryInfo")){
			String countryName = (String) brownRequest.get("countryName");
			Object  states = BrownDataCache.getInstance().getIndianStates();
			
			map.put(countryName, states);
		}
		
		//@2
		if (action.equalsIgnoreCase("getStateInfo")) {
			String countryName = (String) brownRequest.get("countryName");
			String stateName = (String) brownRequest.get("stateName");
			Object cities = BrownDataCache.getInstance().getCities(
					countryName, stateName);
			map.put(countryName, cities);
		}	
		
		//@3
		if (action.equalsIgnoreCase("getCityInfo")) {
			String countryName = (String) brownRequest.get("countryName");
			String stateName = (String) brownRequest.get("stateName");
			String cityName = (String) brownRequest.get("cityName");
			Object postOffices = BrownDataCache.getInstance()
					.getPostalOffices(  cityName);
			
			map.put(countryName, postOffices);
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
