package com.jbrown.db.dao;

import java.sql.Connection;

import com.jbrown.web.ws.BrownRequestI;

public abstract class AbstractDao implements DaoI {
  private Connection _connection;
  
  public AbstractDao(BrownRequestI request){
	 _connection = request.getDataStore().getDbConnection();
  }
  
  public Connection getConnection(){
    return _connection;
  }
}
