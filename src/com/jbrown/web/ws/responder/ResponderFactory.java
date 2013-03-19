package com.jbrown.web.ws.responder;

import java.util.Map;

import com.jbrown.core.exception.BorwnException;
import com.jbrown.web.ws.ResponderI;

public class ResponderFactory {
	private Map<String, ResponderI> _responders;

	public void setResponders(Map<String, ResponderI> responders) {
		_responders = responders;
	}

	public ResponderI getResponder(String responderName) {
		ResponderI responder = _responders.get(responderName);

		if (responder == null) {
			throw new BorwnException("No responder found for :" + responderName);
			//responder = new ErrorResponder();
		}

		return responder;
	}
}
