package com.jbrown.web.ws.responder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.jbrown.cache.BrownDataCache;
import com.jbrown.web.ws.Error;
import com.jbrown.web.ws.JsonRequestI;
import com.jbrown.web.ws.Responder;

public class CountryInfoResponder extends Responder {
	@Override
	protected Map<String, Object> perform(JsonRequestI jsonRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
        
		Map<String, Object> requestMap = jsonRequest.getAttributes();
		
		String action = (String) requestMap.get("action");
		
		//@1
		if(action.equalsIgnoreCase("getCountryInfo")){
			String countryName = (String) requestMap.get("countryName");
			String[] states = BrownDataCache.getInstance().getStatesName(
					countryName);
			
			map.put(countryName, states);
		}
		
		//@2
		if (action.equalsIgnoreCase("getStateInfo")) {
			String countryName = (String) requestMap.get("countryName");
			String stateName = (String) requestMap.get("stateName");
			String[] cities = BrownDataCache.getInstance().getCities(
					countryName, stateName);
			map.put(countryName, cities);
		}	
		
		//@3
		if (action.equalsIgnoreCase("getCityInfo")) {
			String countryName = (String) requestMap.get("countryName");
			String stateName = (String) requestMap.get("stateName");
			String cityName = (String) requestMap.get("cityName");
			String[] postOffices = BrownDataCache.getInstance()
					.getPostalOffices(countryName, stateName, cityName);
			
			map.put(countryName, postOffices);
		}
 
		
		return map;
	}

	@Override
	protected Error[] validate(JsonRequestI jsonRequest) {
		// TODO Auto-generated method stub
		return null;
	}
}
