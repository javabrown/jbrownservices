package com.jbrown;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import static com.jbrown.core.util.BrownKeysI.*;

import com.google.appengine.api.utils.SystemProperty;

public class ApplicationProperties {
  private static ApplicationProperties _instance;
  private static String DEV_FILE = "environment.properties"; //seats in the class dir.
  private static String PROD_FILE = ""; // RK TODO: It should move to data-store
  
  private ApplicationProperties() {
    init();
  }
  
  public boolean isGAEProduction() {
    return
        SystemProperty.environment.value() == 
        SystemProperty.Environment.Value.Production;
  }
 
  public static ApplicationProperties getInstance() {
      if (_instance == null) {
          _instance = new ApplicationProperties();
      }

      return _instance;
  }
  
  public Map<String, String> getDbPropertiesMap() {
    Map<String, String> map = new HashMap<String, String>();
    
    String prefix = isGAEProduction() ? PROD_PREFIX : DEV_PREFIX;
    
    if(!isGAEProduction()) {
      String dbDriver = getDevProperty(prefix + DB_DRIVER);
      String dbUrl = getDevProperty(prefix + DB_URL);
      String dbPort = getDevProperty(prefix + DB_PORT);
      String dbUser = getDevProperty(prefix + DB_USER);
      String dbPassword = getDevProperty(prefix + DB_PASSWORD);
      
      map.put(DB_DRIVER, dbDriver);
      map.put(DB_URL, dbUrl);
      map.put(DB_PORT, dbPort);
      map.put(DB_USER, dbUser);
      map.put(DB_PASSWORD, dbPassword);
      
      System.out.printf("***[%s, %s, %s, %s]**", dbDriver, dbUrl, dbPort, dbUser);
   }
   else {
      System.err.printf("Data File in GAE Production Environment is Pending");
   }
   
    return map;
  }
//  
//  String DB_DRIVER = "dbDriver";
//  String DB_URL = "dbURL";
//  String DB_USER = "dbUser";
//  String DB_PASSWORD = "dbPassword";
//  
  private void init(){
    if(!isGAEProduction()){
       String propValue = getDevProperty("dbDriver");
       System.out.printf("***[%s]**", propValue);
    }
    else {
      System.out.printf("***[%s]**", "Prod Found");
    }
    
  }
  
  private String getDevProperty(String propertyName) {// dbDriver
    Properties prop = new Properties();
    String propValue = "";

    try {
      InputStream inputStream = ApplicationProperties.class.getClassLoader()
          .getResourceAsStream(DEV_FILE);
 
      prop.load(inputStream);
      propValue = prop.getProperty(propertyName);

    } catch (IOException e) {
      e.printStackTrace();
    }

    return propValue;

  }
  
}
