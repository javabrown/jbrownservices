package com.jbrown.web.ws;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.jbrown.core.util.BrownKeysI;
import com.jbrown.errors.BrownErrorsI;
import com.jbrown.web.MimeTypesI;

/***
 * 
 * @author rkhan Responder for REST API.
 */
public abstract class Responder implements ResponderI, BrownKeysI {

	@Override
	public void respond(BrownRequestI request) {
		BrownResponseI brownResponse = new BrownResponse();

		BrownErrorsI errors = validate(request);

		if (errors == null || errors.nErrors() == 0) {
			Map<String, Object> response = perform(request);
			if (response != null && response.size() > 0) {
			  brownResponse.addAll(response);
			}
		}

		if(errors != null && errors.nErrors() > 0){
		  brownResponse.add("Errors", errors.getErrorMessages());
		}
		
		buildResponse(request, brownResponse);
	}

	void buildResponse(BrownRequestI request, BrownResponseI response){
		HttpServletResponse httpResponse = request.getHttpServletResponse();
		this.addCrosHeader(httpResponse);
		
		try {
			OutputFormat outputFormat = OutputFormat.getInstance(request
					.getHttpServletRequest().getParameter("output"));
			String callBack = 
			    request.getHttpServletRequest().getParameter("callback");

			//httpResponse.setContentType(MimeTypesI.PLAIN_TEXT);
			httpResponse.setContentType(outputFormat.getMimeType());
			
			httpResponse.setStatus(HttpServletResponse.SC_OK);
			PrintWriter writer = httpResponse.getWriter();


			writer.print(response.transform(outputFormat, callBack));
			
			writer.flush();
			writer.close();
		} catch (IOException e) {
			System.out.println("Error while preparing the response");
			e.printStackTrace();
		}
	}
	
	private void addCrosHeader(HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");
    }
		
	protected abstract Map<String, Object> perform(BrownRequestI jsonRequest);
	protected abstract BrownErrorsI validate(BrownRequestI jsonRequest);
}

