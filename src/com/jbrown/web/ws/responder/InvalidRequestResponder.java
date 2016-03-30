package com.jbrown.web.ws.responder;

import java.util.HashMap;
import java.util.Map;

import com.jbrown.core.util.BrownKeysI;
import com.jbrown.core.util.StringUtil;
import com.jbrown.errors.BrownErrorsI;
import com.jbrown.web.ws.BrownRequestI;
import com.jbrown.web.ws.Responder;

public class InvalidRequestResponder extends Responder {

  @Override
  protected Map<String, Object> perform(BrownRequestI jsonRequest) {
    Map<String, Object> map = new HashMap<String, Object>();

    return map;
  }

  @Override
  protected BrownErrorsI validate(BrownRequestI request) {
    BrownErrorsI errors = request.getErrors();

    return errors;
  }

}
