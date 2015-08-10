package com.jbrown.user;

import com.core.ext.oauth.FBConnection;
import com.jbrown.core.util.StringUtil;
import com.jbrown.ext.crypter.Crypter;

public class Authenticator {

  public String registerNewUser(String name, String email, String phone,
      String password) {
    boolean isUserAlredayExists = false; // Impl. pending

    if (!isUserAlredayExists) {
      BrownUserI user = new BrownUser("", name, email, phone, password);
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

  // @Deprecated
  // public String autheticate(String email, String password) {
  // boolean isValidUser = false;
  //
  // if (email.equalsIgnoreCase("getrk@yahoo.com")) { // Hard-coded for now.
  // isValidUser = true;
  // }
  //
  // if (isValidUser) {
  // return new AuthenticatorCookie(email, password).encode();
  // } else {
  //
  // }
  // return null;
  // }
  //
  // @Deprecated
  // public String autheticate(String encodeKey) {
  // boolean isValidUser = true;
  //
  // if (isValidUser) {
  // return new AuthenticatorCookie(encodeKey).email;
  // }
  //
  // return null;
  // }
  //
  // @Deprecated
  // private class AuthenticatorCookie {
  // final String authenticator;
  // final String email;
  // final String name;
  // private final String DELIM = "|";
  // private final String DELIM_RE = Pattern.quote(DELIM);
  //
  // AuthenticatorCookie(String encodedValue) {
  // String[] vals = (encodedValue == null) ? null : encodedValue
  // .split(DELIM_RE);
  // if (vals == null || vals.length != 3) {
  // authenticator = BrownKeysI.JAVABROWN_AUTH_K;
  // email = "";
  // name = "";
  // } else {
  // authenticator = vals[0];
  // email = vals[1];
  // name = vals[2];
  // }
  // }
  //
  // AuthenticatorCookie(String email, String name) {
  // this.authenticator = BrownKeysI.JAVABROWN_AUTH_K;
  // this.email = email;
  // this.name = name;
  // }
  //
  // String encode() {
  // return String.format("%s%s%s%s%s", authenticator, DELIM, email,
  // DELIM, name);
  // }
  //
  // }
}
