package com.jbrown.db.dao;

import java.sql.Connection;

public abstract class AbstractDao {
  private Connection _connection;
  
  public Connection getConnection(){
    return _connection;
  }
  
  public void setConnection(Connection connection){
    _connection = connection;
  }
}
