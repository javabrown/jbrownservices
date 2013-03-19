package com.jbrown.web.ws;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.jbrown.core.util.BrownKeysI;
import com.jbrown.errors.BrownErrorsI;
import com.jbrown.web.MimeTypesI;

/***
 * 
 * @author rkhan Responder for Mobile REST API.
 */
public abstract class Responder implements ResponderI, BrownKeysI {

	@Override
	public void respond(BrownRequestI jsonRequest) {
		BrownResponseI jsonResponse = new JsonResponse();

		BrownErrorsI errors = validate(jsonRequest);

		if (errors == null || errors.nErrors() == 0) {
			Map<String, Object> response = perform(jsonRequest);
			if (response != null && response.size() > 0) {
				jsonResponse.addAll(response);
			}
		}

		if(errors != null && errors.nErrors() > 0){
		  jsonResponse.add("Errors", errors.getErrorMessages());
		}
		
		buildResponse(jsonRequest, jsonResponse);
	}

	void buildResponse(BrownRequestI jsonRequest, BrownResponseI jsonResponse){
		HttpServletResponse httpResponse = jsonRequest.getHttpServletResponse();
		try {
			httpResponse.setContentType(MimeTypesI.PLAIN_TEXT);
			httpResponse.setStatus(HttpServletResponse.SC_OK);
			PrintWriter writer = httpResponse.getWriter();

			OutputFormat outputFormat = OutputFormat.getInstance(jsonRequest
					.getHttpServletRequest().getParameter("output"));
			writer.print(jsonResponse.transform(outputFormat));
			
			writer.flush();
			writer.close();
		} catch (IOException e) {
			System.out.println("Error while preparing the response");
			e.printStackTrace();
		}
	}
		
	protected abstract Map<String, Object> perform(BrownRequestI jsonRequest);
	protected abstract BrownErrorsI validate(BrownRequestI jsonRequest);
}
