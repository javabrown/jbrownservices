package com.jbrown;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

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
  
  
  private void init(){
    if(!isGAEProduction()){
       String propValue = getDevProperty("dbDriver");
       System.out.printf("***[%s]**", propValue);
    }
    else{
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
