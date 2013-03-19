package com.jbrown.web.ws.responder;


	import java.util.HashMap;
	import java.util.Map;

	import org.datanucleus.util.StringUtils;

	import com.jbrown.cache.BrownDataCache;
	import com.jbrown.core.util.BrownConstant;
	import com.jbrown.core.util.BrownKeysI;
	import com.jbrown.errors.BrownErrorsI;
	import com.jbrown.user.Authenticator;
	import com.jbrown.web.Cookies;
	import com.jbrown.web.ws.Error;
	import com.jbrown.web.ws.BrownRequestI;
	import com.jbrown.web.ws.Responder;
 
	public class ErrorResponder extends Responder {
		@Override
		protected Map<String, Object> perform(BrownRequestI request) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Error", request.getErrors());

			return map;
		}

		@Override
		protected BrownErrorsI validate(BrownRequestI request) {
			BrownErrorsI  errors = request.getErrors();
			//validation pending
			
			return errors;
		}
	}