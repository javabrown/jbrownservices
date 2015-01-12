package com.jbrown.web.ws;

import java.util.HashMap;
import java.util.Map;

import org.apache.xmlbeans.impl.common.XmlStreamUtils;
import org.springframework.util.StringUtils;

import com.thoughtworks.xstream.XStream;

public class JsonResponse implements BrownResponseI {
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
	public String transform(OutputFormat format, String callback) {
		String response = null;
		
		if(format.typeOf(OutputFormat.XML)){
			StringBuilder output = new StringBuilder("<?xml version=\"1.0\"?>");
			output.append(new XStream().toXML(jsonMap));
			response = output.toString();
		}
		else {
			response = JsonUtil.toJSON(jsonMap);
		}
		
		if(!StringUtils.isEmpty(callback)) {
			response = callback + "("+ response+")";
		}
		
		return response;
	}
}
