package com.jbrown.web.ws.responder;

import java.util.HashMap;
import java.util.Map;

import com.jbrown.core.util.StringUtil;
import com.jbrown.db.dao.UserDaoI;
import com.jbrown.db.dao.UserDao;
import com.jbrown.errors.BrownErrorsI;
import com.jbrown.user.BrownUser;
import com.jbrown.user.BrownUserI;
import com.jbrown.web.ws.BrownRequestI;
import com.jbrown.web.ws.Responder;

public class RegisterResponder extends Responder {
  @Override
  protected Map<String, Object> perform(BrownRequestI request) {
    String name = (String) request.get(NAME_K);
    String email = (String) request.get(EMAIL_K);
    String phone = (String) request.get(PHONE_K);
    String password = (String) request.get(PASSWORD_K);
    String domain = (String) request.get(DOMAIN_K);
    
    BrownUserI newUser = new BrownUser("", name, email, phone, password, domain);
    boolean success = new UserDao(request).createUser(newUser);
    
    Map<String, Object> result = new HashMap<String, Object>();
    result.put(RESPONSE_K, success);
    result.put(AUTH_CODE_K, newUser.getEncryptedKey());
    
    return result;
  }

  @Override
  protected BrownErrorsI validate(BrownRequestI request) {
    BrownErrorsI errors = request.getErrors();

    String name = (String) request.get(NAME_K);
    String email = (String) request.get(EMAIL_K);
    String phone = (String) request.get(PHONE_K);
    String password = (String) request.get(PASSWORD_K);
    String domain = (String) request.get(DOMAIN_K);
    
    if (StringUtil.isEmpty(name, email, phone, password, domain)) {
      errors.add(String.format(
          "Mandatory Fields Missing :%s | %s | %s | %s | %s", NAME_K, EMAIL_K,
          PHONE_K, PASSWORD_K, DOMAIN_K));
    } else if (StringUtil.isUnsafeString(name, email, phone, password)) {
      errors.add(String.format("Special chars not allowed:", StringUtil.UNSAFE_STRING));
    } else if (!StringUtil.isValidEmail(email)) {
      errors.add(String.format("Invalid Email Format %s", email));
    } else if (isUserAlreadyExists(request, email)) {
      errors.add(String.format("Email address '%s' already taken.", email));
    }

    return errors;
  }

  private boolean isUserAlreadyExists(BrownRequestI request, String email){
    BrownUserI user = new UserDao(request).getUserByEmail(email);
    
    if(user != null){
      return true;
    }
    
    return false;
  }
}
