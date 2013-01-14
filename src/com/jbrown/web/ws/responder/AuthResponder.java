package com.jbrown.web.ws.responder;

import java.util.HashMap;
import java.util.Map;

import com.jbrown.web.ws.Error;
import com.jbrown.web.ws.JsonRequestI;
import com.jbrown.web.ws.Responder;

public class AuthResponder extends Responder {
	@Override
	protected Map<String, Object> perform(JsonRequestI jsonRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
        
		map.put("name", "Raja Khan");
		map.put("email", "rkhan@raileurope.com");

		
		return map;
	}

	@Override
	protected Error[] validate(JsonRequestI jsonRequest) {
		// TODO Auto-generated method stub
		return null;
	}
}
