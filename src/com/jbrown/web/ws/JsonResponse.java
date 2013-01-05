package com.jbrown.web.ws;

import java.util.HashMap;
import java.util.Map;

public class JsonResponse implements JsonResponseI {
	private Map<String, Object> jsonMap;

	public JsonResponse() {
		this.jsonMap = new HashMap<String, Object>();
	}

	@Override
	public void add(String jsonKey, Object object) {
		this.jsonMap.put(jsonKey, object);
	}

	@Override
	public void addAll(Map<String, Object> jsonMap) {
		this.jsonMap.putAll(jsonMap);
	}

	@Override
	public String toJson() {
		return JsonUtil.toJSON(jsonMap);
	}

}
