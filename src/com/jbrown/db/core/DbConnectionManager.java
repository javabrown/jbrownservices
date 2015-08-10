package com.jbrown.db.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.google.appengine.api.utils.SystemProperty;
import com.jbrown.core.exception.BorwnException;

public class DbConnectionManager {
  private static DbConnectionManager _instance = null;
  private static Map<String, String> _configMap = init();

  private DataSource _ds = null;

  private DbConnectionManager() {
    BasicDataSource ds = new BasicDataSource();
    ds.setDriverClassName(getConfig("dbDriver"));
    ds.setUsername(getConfig("dbUser"));
    ds.setPassword(getConfig("dbPassword"));

    ds.setUrl(getConfig("dbURL"));
    ds.setMaxActive(20);
    ds.setMaxIdle(200);
    ds.setMaxWait(100000);
    ds.setRemoveAbandoned(true);
    ds.setRemoveAbandonedTimeout(18000);
    ds.setTestOnBorrow(true);
    ds.setValidationQuery("SELECT 1");

    _ds = ds;
  }

  private Connection getConnection() throws Exception {
    Class.forName(getConfig("dbDriver"));
    Connection conn = DriverManager.getConnection(getConfig("dbURL"));
    // , getConfig("dbUser"), getConfig("dbPassword"));

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
      c = this.getConnection();// _ds.getConnection();
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

  private static Map<String, String> init() {
    Map<String, String> map = new HashMap<String, String>();
    // DEV Environment
    map.put("dev.dbDriver", "com.mysql.jdbc.Driver");
    map.put("dev.dbURL",
        "jdbc:mysql://173.194.226.233:3306/jbrowndb?user=rkhan&password=rkhan");
    map.put("dev.dbPort", "3006");
    map.put("dev.dbUser", "rkhan");
    map.put("dev.dbPassword", "rkhan");

    // Prod Environment
    map.put("gae.dbDriver", "com.mysql.jdbc.GoogleDriver");
    map.put(
        "gae.dbURL",
        "jdbc:google:mysql://javabrownservices:jb-cloud/jbrowndb?user=root&database=guestbook");
    map.put("gae.dbPort", "3006");
    map.put("gae.dbUser", "rkhan");
    map.put("gae.dbPassword", "rkhan");

    return map;
  }

  private String getConfig(String key) {
    String prefix = "dev";

    if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
      prefix = "gae";
    } else {
      prefix = "dev";
    }

    return _configMap.get(String.format("%s.%s", prefix, key));
  }
}