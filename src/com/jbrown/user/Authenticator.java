package com.jbrown.user;

import com.core.ext.oauth.FBConnection;
import com.jbrown.core.util.BrownKeysI;
import com.jbrown.core.util.StringUtil;
import com.jbrown.db.dao.UserDaoImpl;
import com.jbrown.ext.crypter.Crypter;
import com.jbrown.web.ws.BrownRequestI;

public class Authenticator {

  public String registerNewUser(String name, String email, String phone,
      String password, String domain) {
    boolean isUserAlredayExists = false; // Impl. pending

    if (!isUserAlredayExists) {
      BrownUserI user = new BrownUser("", name, email, phone, password, domain);
      
      String encryptedString;
      try {
        encryptedString = new Crypter().encrypt(user.getEncodeString());
        return encryptedString;
      } catch (Exception e) {
        e.printStackTrace();
      }

    }

    return null;
  }

  public void doAuth(BrownRequestI req, String email, String password){
	  BrownUserI user = new UserDaoImpl().findUser(email, password);
	  if(user != null){
		 req.putSessionCache(BrownKeysI.JAVABROWN_AUTH_K, user.getEncryptedKey());
	  }
  }
  
  public boolean autheticate(String token) {
    String encryptedString = null;
    String email = null;

    if (StringUtil.isEmpty(token)) {
      return autheticateFb(token);
    }

    try {
      encryptedString = new Crypter().decrypt(token);
      email = encryptedString.split("__")[2];
      if (email.equalsIgnoreCase("getrk@yahoo.com")) {
        return true;
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return false;
  }

  public boolean autheticateFb(String access_token) {
    FBConnection conn = new FBConnection(access_token);
    String email = null;

    if (conn.getFbUserInfo() != null) {
      email = conn.getFbUserInfo().getEmail();
      System.out.println("FB Login Success: " + conn.getFbUserInfo());

      return true;
    } else {
      System.out.println("FB Login Failed for: " + access_token);
    }

    return false;
  }

  public String getBrownUserId(String accessToken) {
    FBConnection conn = new FBConnection(accessToken);

    if (conn.getFbUserInfo() != null) {
      String brownUserId = conn.getFbUserInfo().getBrownUserId();
      System.out.println("BrownUserId: " + brownUserId);

      return brownUserId;
    }

    return null;
  }
}
