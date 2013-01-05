package com.jbrown.web.ws;

import static javax.servlet.http.HttpServletResponse.SC_OK;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jbrown.web.BrownContextI;

/**
 * @author rkhan
 * 
 */
public abstract class ReServices {

	private BrownContextI _brownContext;
	private ResponderFactory _responderFactory;

	protected final String VIEW = "response";

	public void setSharedContext(BrownContextI context) {
		_brownContext = context;
	}

	public void setResponderFactory(ResponderFactory responderFactory) {
		_responderFactory = responderFactory;
	}

	public ResponderFactory getResponderFactory() {
		return _responderFactory;
	}

	public void init() {
	}

	protected void initialize(HttpServletRequest request,
			HttpServletResponse resp) {
		String STATUS = "responseStatus";
		resp.setContentType("application/json");
		request.setAttribute(STATUS, SC_OK);
	}
}
