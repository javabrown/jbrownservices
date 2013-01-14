package com.jbrown.web.ws;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class JsonRequest implements JsonRequestI {
	private Map<String, Object> requestMap;
	private ResponderI responder;

	public JsonRequest(String jsonBody, ResponderI responder) {
		this.requestMap = getPostAttributes(jsonBody);
		this.responder = responder;
	}
	
	public JsonRequest(ResponderI responder, String[]...keyValueParams) {
		this.requestMap = getGetAttributes(keyValueParams);
		this.responder = responder;
	}
	
	@Override
	public ResponderI getResponder() {
		return responder;
	}
	
	@Override
	public Map<String, Object> getAttributes(){
		return requestMap;
	}
	
	public Map<String, Object> getPostAttributes(String postedBody) {
		if (postedBody == null) {
			return null;
		}

		try {
			Map<String, Object> lowerKeyMap = new HashMap<String, Object>();

			JSONObject json = new JSONObject(postedBody);
			Iterator<String> iterator = json.keys();

			while (iterator.hasNext()) {
				String key = iterator.next();
				lowerKeyMap.put(key.toLowerCase(), (String) json.get(key));
			}

			return lowerKeyMap;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	 
	public Map<String, Object> getGetAttributes(String[]...keyValueParams) {
		Map<String, Object> lowerKeyMap = new HashMap<String, Object>();
		
		for(String[] param : keyValueParams){
			lowerKeyMap.put(param[0], param[1]);
		}
 
		return lowerKeyMap;
	}
}
