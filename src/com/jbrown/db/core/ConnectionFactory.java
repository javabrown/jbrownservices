package com.jbrown.db.core;

import java.sql.Connection;
import java.sql.DriverManager;

import com.google.appengine.api.utils.SystemProperty;

public class ConnectionFactory {
  public static Connection getConnection() throws Exception {
    String url = null;
    if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {

      Class.forName("com.mysql.jdbc.GoogleDriver");
      url = "jdbc:google:mysql://javabrownservices:jbcloud?user=root";
    } else {
      // Connecting from an external network.
      Class.forName("com.mysql.jdbc.Driver");
      url = "jdbc:mysql://xxxxxxxx:3306?user=root";
    }

    return DriverManager.getConnection(url);
  }
}
