package com.jbrown.web.ws.responder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jbrown.cache.BrownCache;
import com.jbrown.core.util.BrownKeysI;
import com.jbrown.core.util.StringUtil;
import com.jbrown.errors.BrownErrorsI;
import com.jbrown.ext.capsule.BrownCapsule;
import com.jbrown.ext.capsule.airport.data.AirportCapsuleI;
import com.jbrown.web.ws.BrownRequestI;
import com.jbrown.web.ws.Responder;

public class AirportInfoResponder extends Responder {

	@Override
	protected Map<String, Object> perform(BrownRequestI request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String iata = request.get(IATA);
		
		try {
			List<Map<String, String>> info = getAirportCapsule().getAirportByIATACode(iata.toUpperCase());
			map.put(RESPONSE_K, info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}

	@Override
	protected BrownErrorsI validate(BrownRequestI request) {
		BrownErrorsI errors = request.getErrors();
		String iata = request.get(IATA);
		
		if(StringUtil.isEmpty(iata)){
			errors.add("airport/iata-code missing in request header");
		}
		
		return errors;
	}

	public AirportCapsuleI getAirportCapsule() {
		AirportCapsuleI airportCapsule = (AirportCapsuleI) BrownCache.getInstance()
				.get(BrownKeysI.AIRPORT_CAPSULE_K);

		if (airportCapsule == null) {
			BrownCapsule capsule = getCapsule();

			if (capsule != null) {
				try {
					airportCapsule = (AirportCapsuleI) capsule.getAirportData();
					BrownCache.getInstance().set(BrownKeysI.AIRPORT_CAPSULE_K, airportCapsule);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return airportCapsule;
	}
	
	public BrownCapsule getCapsule(){
		BrownCapsule capsule = (BrownCapsule) BrownCache.getInstance().get(BrownKeysI.CAPSULE_K);
		
		if(capsule == null){
			capsule = new BrownCapsule();
			BrownCache.getInstance().set(BrownKeysI.CAPSULE_K, capsule);
		}
		
		return capsule;
	}
	 
}
