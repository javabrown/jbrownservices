package com.jbrown.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
  public static final String UNSAFE_STRING = ".*[%<>&;'\0-].*";
  private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
      + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

  public static boolean isEmpty(String s) {
    return (s == null) || s.trim().equals("");
  }

  public static boolean isEmpty(String... stringArray) {
    for (String s : stringArray) {
      if (isEmpty(s)) {
        return true;
      }
    }
    return false;
  }

  public static boolean isUnsafeString(String s) {
     return s != null && s.matches(UNSAFE_STRING);
  }

  public static boolean isUnsafeString(String... stringArray) {
    for (String s : stringArray) {
      if (isUnsafeString(s)) {
        return true;
      }
    }

    return false;
  }

  public static boolean isValidEmail(final String email) {
    Matcher matcher = Pattern.compile(EMAIL_PATTERN).matcher(email);
    return matcher.matches();
  }

}
