package com.jbrown.web.ws.responder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.jbrown.errors.BrownErrorsI;
import com.jbrown.web.ws.BrownRequestI;
import com.jbrown.web.ws.Responder;

public class InvalidRequestResponder extends Responder {

  @Override
  protected Map<String, Object> perform(BrownRequestI request) {
    Map<String, Object> map = new HashMap<String, Object>();
    
    return map;
  }

  @Override
  protected BrownErrorsI validate(BrownRequestI request) {
    BrownErrorsI errors = request.getErrors();
   
 
    request.getHttpServletResponse().setStatus(HttpServletResponse.SC_NOT_FOUND);
   
    
    return errors;
  }

}
