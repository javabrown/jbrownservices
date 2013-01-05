package com.jbrown.web.ws;

import java.util.Map;

/***
 * 
 * @author rkhan Responder for Mobile REST API.
 */
public abstract class Responder implements ResponderI {

	@Override
	public JsonResponseI respond(JsonRequestI jsonRequest) {
		JsonResponseI jsonResponse = new JsonResponse();

		Error[] errors = validate(jsonRequest);

		if (errors == null || errors.length == 0) {
			Map<String, Object> response = perform(jsonRequest);
			if (response != null && response.size() > 0) {
				jsonResponse.addAll(response);
			}
		}

		jsonResponse.add("Errors", errors);

		return jsonResponse;
	}

	protected abstract Map<String, Object> perform(JsonRequestI jsonRequest);

	protected abstract Error[] validate(JsonRequestI jsonRequest);
}
