package com.jbrown.web.ws.responder;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.jbrown.errors.BrownErrorsI;
import com.jbrown.ext.crypter.Encoder;
import com.jbrown.web.ws.BrownRequestI;
import com.jbrown.web.ws.Responder;
import com.jbrown.web.ws.ResponderI;

public class UtilityResponder extends Responder {

	@Override
	protected Map<String, Object> perform(BrownRequestI request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String action = (String) request.get("action");
		
		if (action.equals(ACTION_ENCODE_K)) {
			map.putAll(encode(request));
		} else if (action.equals(ACTION_DECODE_K)) {
			map.putAll(encode(request));
		}
		
		return map;
	}

	@Override
	protected BrownErrorsI validate(BrownRequestI request) {
		BrownErrorsI  errors = request.getErrors();
		//validation pending
		
		return errors;
	}

	private Map<String, Object> encode(BrownRequestI request){
	  Map<String, Object> map = new HashMap<String, Object>();
	  String encodingString = (String)request.get(DATA_K);
	  
	  map.put("encoded response", new Encoder().encode(encodingString));
	  return map;	
	}
	 
}
