package com.jbrown.web.ws.responder;

import java.util.HashMap;
import java.util.Map;

import com.jbrown.cache.BrownCache;
import com.jbrown.core.util.BrownConstant;
import com.jbrown.core.util.BrownKeysI;
import com.jbrown.core.util.CacheData;
import com.jbrown.core.util.CacheDataI;
import com.jbrown.core.util.StringUtil;
import com.jbrown.errors.BrownErrorsI;
import com.jbrown.web.ws.BrownRequestI;
import com.jbrown.web.ws.Responder;

public class CacheGetResponder extends Responder {
	@Override
	protected Map<String, Object> perform(BrownRequestI request) {
		String cacheKey = request.get(BrownConstant.CACHE_KEY);
		CacheDataI fecther = new CacheData(cacheKey, request);
		
		CacheDataI cacheValue = 
		   (CacheDataI) BrownCache.getInstance().get(fecther.getPrivateKey());
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(cacheValue != null){
		  map.put(BrownConstant.CACHE_KEY, cacheKey);
		  map.put(BrownConstant.CACHE_VALUE, cacheValue.getValue());
		}
		else {
			map.put(RESPONSE_K, String.format("No data found for key: %s", cacheKey));
		}
		return map;
	}

	@Override
	protected BrownErrorsI validate(BrownRequestI request) {
		BrownErrorsI errors = request.getErrors();
		
		if(request.getHeadersMap()!= null) {
			String cacheKey = request.get(BrownConstant.CACHE_KEY);
 
			CacheDataI fecthData = new CacheData(cacheKey, request);
			
			if(StringUtil.isEmpty(cacheKey)) {
				errors.add(String.format("%s missing", BrownConstant.CACHE_KEY));
			}
			else if(!fecthData.isFetchable()) {
				errors.add(String.format("Invalid cache or user for %s", BrownConstant.CACHE_KEY));
			}
		}
		
		return errors;
	}
}
