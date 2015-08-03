package com.jbrown.web.ws.responder;
 
import java.util.HashMap;
import java.util.Map;

import com.jbrown.cache.BrownCache;
import com.jbrown.core.util.BrownConstant;
import com.jbrown.core.util.BrownKeysI;
import com.jbrown.core.util.StringUtil;
import com.jbrown.errors.BrownErrorsI;
import com.jbrown.web.ws.BrownRequestI;
import com.jbrown.web.ws.Responder;

public class CachePushResponder extends Responder {
	@Override
	protected Map<String, Object> perform(BrownRequestI request) {
		String cacheKey = request.get(BrownConstant.CACHE_KEY);
		String cacheValue = request.get(BrownConstant.CACHE_VALUE);
		Map<String, Object> map = new HashMap<String, Object>();

		boolean flag = BrownCache.getInstance().set(cacheKey, cacheValue);
		map.put(BrownKeysI.RESPONSE_K, flag);
		return map;
	}

	@Override
	protected BrownErrorsI validate(BrownRequestI request) {
		BrownErrorsI errors = request.getErrors();
		
		if(request.getHeadersMap()!= null) {
			String cacheKey = request.get(BrownConstant.CACHE_KEY);
			String cacheValue = request.get(BrownConstant.CACHE_VALUE);
			 
			if(StringUtil.isEmpty(cacheKey)) {
				errors.add(String.format("%s missing", BrownConstant.CACHE_KEY));
			}
			
			if(StringUtil.isEmpty(cacheValue)) {
				errors.add(String.format("%s missing", BrownConstant.CACHE_VALUE));
			}
		}
		
		return errors;
	}
}
