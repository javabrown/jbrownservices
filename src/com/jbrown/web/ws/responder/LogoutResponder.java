package com.jbrown.web.ws.responder;

import java.util.HashMap;
import java.util.Map;

import com.jbrown.core.util.BrownAuthUtil;
import com.jbrown.core.util.BrownKeysI;
import com.jbrown.core.util.StringUtil;
import com.jbrown.errors.BrownErrorsI;
import com.jbrown.user.BrownUserI;
import com.jbrown.web.ws.BrownRequestI;
import com.jbrown.web.ws.Responder;

public class LogoutResponder extends Responder {
  @Override
  protected Map<String, Object> perform(BrownRequestI request) {
    this.doLogout(request);

    Map<String, Object> result = new HashMap<String, Object>();
    result.put(RESPONSE_K, true);

    return result;
  }

  @Override
  protected BrownErrorsI validate(BrownRequestI request) {
    BrownErrorsI errors = request.getErrors();
    
    if(!isUserAlreadyLogin(request)){
      errors.add(String.format("No Active Login Found"));
    }

    return errors;
  }

  private boolean isUserAlreadyLogin(BrownRequestI req) {
    BrownUserI user = BrownAuthUtil.getBrownUser(req);
    //String authCode =  (String) req.getSessionCache(BrownKeysI.JAVABROWN_AUTH_K);

    if (user != null) {
      return true;
    }

    return false;
  }
  
  private void doLogout(BrownRequestI req) {
    req.clearSession();
  }
}
