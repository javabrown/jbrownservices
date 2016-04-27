package com.jbrown.core.util;

import org.springframework.web.servlet.ModelAndView;

public interface BrownKeysI {
  String HTTP_METHOD_K = "HTTP_METHOD";
  String HTTP_METHOD_POST_K = "POST";
  String ERRORS_K = "ERRORS";
  String ACTION_K = "action";
  String COUNTRY_NAME_K = "countryName";
  String BROWN_RESULT_K = "brown-result";

  String ACTION_AUTH_K = "authenticate";
  String ACTION_REGISTER_K = "register";
  String ACTION_ENCODE_K = "encode";
  String ACTION_DECODE_K = "decode";
  String USER_INFO_ACTION_K = "userInfo";
  String GEO_GET_DISTANCE_K = "getDistance";
  String ACTION_GET_PUBLIC_IMAGE_K = "getPublicImages";
  String ACTION_GET_ALL_IMAGE_CONSTANTS = "getAllPhotoSizeConstants";

  ModelAndView EMPTY_VIEW = null;

  String BROWN_REQUEST_OBJ_K = "BROWN_REQUEST_OBJ";
  String JAVABROWN_AUTH_K = "JAVABROWN-AUTH";
  String INVALID_AUTH_K = "INVALID-AUTH";
  String AUTH_ID = "authid";

  String RESPONSE_K = "response";

  String NAME_K = "name";
  String EMAIL_K = "email";
  String PHONE_K = "phone";
  String PASSWORD_K = "password";
  String TOKEN_K = "token";
  String DATA_K = "data";
  String AUTH_CODE_K = "auth-code";
  String DOMAIN_K = "domain";
  
  String EMPTY_K = "";

  String PROVIDER_FLICKR_K = "flickr";
  String PROVIDER_FLICKR_PUBLIC_URL_K = "public_url";

  String IATA = "iata";

  String TEXT_TO_SPEECH = "textToSpeech";

  String CAPSULE_K = "Brown_Capsule_K";
  String AIRPORT_CAPSULE_K = "AirportCapsule_K";

  String AIRPORT_DATA_K = "AirportDataK";

  String QUESTIONS_K = "questions";
  String ALL_QUIZ_QUESTIONS_K = "ALL_QUIZ_QUESTIONS_K";
  
  String MANDATORY_FIELDS_MISSING = "MANDATORY_FIELDS_MISSING";
  
  String DB_DRIVER = "dbDriver";
  String DB_URL = "dbURL";
  String DB_PORT = "dbPort";
  String DB_USER = "dbUser";
  String DB_PASSWORD = "dbPassword";
  
  String DEV_PREFIX = "dev.";
  String PROD_PREFIX = "gae.";
}
