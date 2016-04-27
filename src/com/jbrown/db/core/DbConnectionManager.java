package com.jbrown.db.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.google.appengine.api.utils.SystemProperty;
import com.jbrown.ApplicationProperties;
import com.jbrown.core.exception.BorwnException;
import com.jbrown.core.util.BrownKeysI;

public class DbConnectionManager {
  private static DbConnectionManager _instance = null;
  //private static Map<String, String> _configMap = init();
  private static Map<String, String> _dbConf = 
      ApplicationProperties.getInstance().getDbPropertiesMap();
  
  private BasicDataSource _ds = null;

  private DbConnectionManager() {
    BasicDataSource ds = new BasicDataSource();
    ds.setDriverClassName(_dbConf.get(BrownKeysI.DB_DRIVER));
    ds.setUsername(_dbConf.get(BrownKeysI.DB_USER));
    ds.setPassword(_dbConf.get(BrownKeysI.DB_PASSWORD));
    ds.setUrl(_dbConf.get(BrownKeysI.DB_URL));
    
    ds.setMaxActive(20);
    ds.setMaxIdle(200);
    ds.setMaxWait(100000);
    ds.setRemoveAbandoned(true);
    ds.setRemoveAbandonedTimeout(18000);
    ds.setTestOnBorrow(true);
    ds.setValidationQuery("SELECT 1");

    _ds = ds;
  }

//  private DbConnectionManager() {
//    BasicDataSource ds = new BasicDataSource();
//    ds.setDriverClassName(getConfig("dbDriver"));
//    ds.setUsername(getConfig("dbUser"));
//    ds.setPassword(getConfig("dbPassword"));
//    ds.setUrl(getConfig("dbURL"));
//    
//    ds.setMaxActive(20);
//    ds.setMaxIdle(200);
//    ds.setMaxWait(100000);
//    ds.setRemoveAbandoned(true);
//    ds.setRemoveAbandonedTimeout(18000);
//    ds.setTestOnBorrow(true);
//    ds.setValidationQuery("SELECT 1");
//
//    _ds = ds;
//  }
  
  
  
  private Connection getConnection() throws Exception {
    String driver = _dbConf.get(BrownKeysI.DB_DRIVER);
    String url = _dbConf.get(BrownKeysI.DB_URL);
    String user = _dbConf.get(BrownKeysI.DB_USER);
    String password = _dbConf.get(BrownKeysI.DB_PASSWORD);
    
    //System.out.printf("SELECT DB=>[%s,%s,%s,%s]", driver, url, user, password);
    
    Class.forName(driver);
    Connection conn = DriverManager.getConnection(url, user, password);

    return conn;
  }

  public static synchronized DbConnectionManager getInstance() {
    if (_instance == null) {
      _instance = new DbConnectionManager();
    }

    return _instance;
  }

  public Connection get() {
    Connection c = null;

    try {
      //c = _ds.getConnection();
      c = this.getConnection();
    } catch (Exception se) {
      throw new BorwnException(se);
    }

    return c;
  }

  public void put(Connection c) {
    try {
      if (c != null)
        c.close();
    } catch (SQLException se) {
      se.printStackTrace();
    }
  }

//  private static Map<String, String> init() {
//    Map<String, String> map = new HashMap<String, String>();
//    // DEV Environment
//    map.put("dev.dbDriver", "com.mysql.jdbc.Driver");
//    map.put("dev.dbURL",
//        "jdbc:mysql://173.194.226.233:3306/jbrowndb?user=rkhan&password=rkhan");
//    map.put("dev.dbPort", "3006");
//    map.put("dev.dbUser", "rkhan");
//    map.put("dev.dbPassword", "rkhan");
//
//    // Prod Environment
//    map.put("gae.dbDriver", "com.mysql.jdbc.GoogleDriver");
//    map.put(
//        "gae.dbURL",
//        "jdbc:google:mysql://javabrownservices:jb-cloud/jbrowndb?user=root&database=guestbook");
//    map.put("gae.dbPort", "3006");
//    map.put("gae.dbUser", "rkhan");
//    map.put("gae.dbPassword", "rkhan");
//
//    return map;
//  }

//  private String getConfig(String key) {
//    String prefix = "dev";
//
//    if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
//      prefix = "gae";
//    } else {
//      prefix = "dev";
//    }
//
//    return _configMap.get(String.format("%s.%s", prefix, key));
//  }
}