package com.jbrown.web.ws;

import java.util.HashMap;
import java.util.Map;

import org.apache.xmlbeans.impl.common.XmlStreamUtils;
import org.springframework.util.StringUtils;

import com.thoughtworks.xstream.XStream;

public class BrownResponse implements BrownResponseI {
	private Map<String, Object> _fieldMap;

	public BrownResponse() {
	  _fieldMap = new HashMap<String, Object>();
	}

	@Override
	public void add(String jsonKey, Object object) {
	  _fieldMap.put(jsonKey, object);
	}

	@Override
	public void addAll(Map<String, Object> jsonMap) {
	  _fieldMap.putAll(jsonMap);
	}

	@Override
	public String transform(OutputFormat format, String callback) {
		String response = null;
		
		if(format.typeOf(OutputFormat.XML)){
			StringBuilder output = new StringBuilder("<?xml version=\"1.0\"?>");
			output.append(new XStream().toXML(_fieldMap));
			response = output.toString();
		}
		else {
			response = JsonUtil.toJSON(_fieldMap);
		}
		
		if(!StringUtils.isEmpty(callback)) {
			response = callback + "("+ response+")";
		}
		
		return response;
	}
}
