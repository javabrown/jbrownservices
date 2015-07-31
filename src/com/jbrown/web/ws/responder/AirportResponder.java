package com.jbrown.web.ws.responder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jbrown.cache.BrownCache;
import com.jbrown.core.util.BrownKeysI;
import com.jbrown.errors.BrownErrorsI;
import com.jbrown.ext.capsule.BrownCapsule;
import com.jbrown.web.ws.BrownRequestI;
import com.jbrown.web.ws.Responder;

public class AirportResponder extends Responder {

	@Override
	protected Map<String, Object> perform(BrownRequestI request) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			List<Map<String, String>> airportData = (List<Map<String, String>>) BrownCache
					.getInstance().get(BrownKeysI.AIRPORT_DATA_K);

			if (airportData == null) {
				BrownCapsule casule = (BrownCapsule) BrownCache.getInstance()
						.get(BrownKeysI.CAPSULE_K);

				if (casule == null) {
					casule = new BrownCapsule();
					BrownCache.getInstance().set(BrownKeysI.CAPSULE_K, casule);
				}

				airportData = casule.getAirportData().getAirportsList();

				BrownCache.getInstance().set(BrownKeysI.AIRPORT_DATA_K,
						airportData);
				System.out.println("Reloaded into Memcached");
			}

			map.put(RESPONSE_K, airportData);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	@Override
	protected BrownErrorsI validate(BrownRequestI request) {
		BrownErrorsI errors = request.getErrors();
		//String iata = request.get(IATA);
		
		//if(StringUtil.isEmpty(iata)){
		//	errors.add("airport/iata-code missing in request header");
		//}
		
		return errors;
	}

}
