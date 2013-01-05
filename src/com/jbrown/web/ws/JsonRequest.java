package com.jbrown.web.ws;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class JsonRequest implements JsonRequestI {
	private String jsonBody;
	private ResponderI responder;

	public JsonRequest(String jsonBody, ResponderI responder) {
		this.jsonBody = jsonBody;
		this.responder = responder;
	}

	public String getJsonBody() {
		return jsonBody;
	}

	@Override
	public ResponderI getResponder() {
		return responder;
	}

	@Override
	public Map<String, String> getAttributes(String postedBody) {
		if (postedBody == null) {
			return null;
		}

		try {
			Map<String, String> lowerKeyMap = new HashMap<String, String>();

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

}
