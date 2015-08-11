package com.jbrown.web.ws.responder;

import static com.jbrown.core.util.BrownConstant.FB_ACCESS_TOKEN;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.jbrown.core.util.BrownKeysI;
import com.jbrown.core.util.StringUtil;
import com.jbrown.errors.BrownErrorsI;
import com.jbrown.user.Authenticator;
import com.jbrown.web.ws.BrownRequestI;
import com.jbrown.web.ws.Responder;

public class AuthResponder extends Responder {
  @Override
  protected Map<String, Object> perform(BrownRequestI request) {
    Map<String, Object> map = new HashMap<String, Object>();

    String action = (String) request.get("action");
    if (action.equals(ACTION_AUTH_K)) {
      map.putAll(authenticate(request));
    } else if (action.equals(ACTION_REGISTER_K)) {
      map.putAll(createNewUser(request));
    }

    return map;
  }

  private Map<String, Object> authenticate(BrownRequestI request) {
    Map<String, Object> map = new HashMap<String, Object>();
    // String token = (String) request.get(TOKEN_K);
    String token = request.getHeadersMap().get(BrownKeysI.AUTH_CODE_K);
    String fbAccessToken = request.getHeadersMap().get(FB_ACCESS_TOKEN);
    boolean isValid = false;

    if (!StringUtils.isEmpty(token)) {
      isValid = new Authenticator().autheticate(token);
    } else if (!StringUtils.isEmpty(fbAccessToken)) {
      isValid = new Authenticator().autheticateFb(fbAccessToken);
    }

    map.put(RESPONSE_K, isValid);

    if (isValid) {
      request.putCache(TOKEN_K, token);
    }

    return map;
  }

  private Map<String, Object> createNewUser(BrownRequestI request) {
    Map<String, Object> map = new HashMap<String, Object>();

    String name = (String) request.get(NAME_K);
    String email = (String) request.get(EMAIL_K);
    String phone = (String) request.get(PHONE_K);
    String password = (String) request.get(PASSWORD_K);

    String result = new Authenticator().registerNewUser(name, email, phone,
        password, request.getDomain());
    map.put(RESPONSE_K, result);

    return map;
  }

  @Override
  protected BrownErrorsI validate(BrownRequestI request) {
    BrownErrorsI errors = request.getErrors();

    if (request.getHeadersMap() != null) {
      String token = request.getHeadersMap().get(BrownKeysI.AUTH_CODE_K);
      //String fbAccessToken = request.getHeadersMap().get(FB_ACCESS_TOKEN);
      //StringBuilder error = new StringBuilder();

      // if(StringUtil.isEmpty(token)){
      // errors.add(AUTH_CODE_K +"");
      // errors.add("Error.JSONData");
      // }

      if (StringUtil.isEmpty(token)) {
        errors.add(AUTH_CODE_K + " is missing in request header");
      }
    }

    return errors;
  }
}
