package com.jbrown.web.ws;

import java.util.Map;

public interface JsonResponseI {
	public void add(String jsonKey, Object object);

	public void addAll(Map<String, Object> jsonMap);

	public String toJson();
}
