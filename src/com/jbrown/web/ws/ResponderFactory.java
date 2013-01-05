package com.jbrown.web.ws;

import java.util.Map;

import com.jbrown.core.exception.BorwnException;

public class ResponderFactory {
	private Map<String, ResponderI> _responders;

	public void setResponders(Map<String, ResponderI> responders) {
		_responders = responders;
	}

	public ResponderI getResponder(String responderName) {
		ResponderI responder = _responders.get(responderName);

		if (responder == null) {
			throw new BorwnException("No responder found for :" + responderName);
		}

		return responder;
	}
}
