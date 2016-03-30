package com.jbrown.web.ws.responder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.jbrown.cache.BrownDataCache;
import com.jbrown.core.util.BrownKeysI;
import com.jbrown.errors.BrownErrorsI;
import com.jbrown.ext.capsule.BrownCapsule;
import com.jbrown.ext.capsule.impl.BrownGeoCapsuleI;
import com.jbrown.providers.ProviderI;
import com.jbrown.providers.ext.flickr.FlickFetcher;
import com.jbrown.providers.ext.flickr.FlickerProvider;
import com.jbrown.providers.ext.flickr.ImageSize;
import com.jbrown.web.ws.BrownRequestI;
import com.jbrown.web.ws.Responder;
import com.jbrown.web.ws.Error;
import com.jbrown.web.ws.WsActionType;

public class MediaResponder extends Responder {
	@Override
	protected Map<String, Object> perform(BrownRequestI brownRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
         
		String action = (String) brownRequest.get(ACTION_K);
		WsActionType actionType = WsActionType.getInstance(action);
		
		//@0
		if(actionType.typeOf(WsActionType.PUBLIC_IMAGES)) {
			ProviderI provider = new FlickerProvider(brownRequest);
			 
			map.put(BROWN_RESULT_K, provider.getResult());
		}
		
		//@1
		if(actionType.typeOf(WsActionType.IMAGE_SIZE_CONSTANTS)) {
			ProviderI provider = new FlickerProvider(brownRequest);
			 
			map.put(BROWN_RESULT_K, provider.getResult());
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
