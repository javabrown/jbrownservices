package com.jbrown.db.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.jbrown.db.core.DbConnectionManager;

public class DataStore {
    private Connection _connection;
	
    public DataStore(){
       _connection = null; //lazy load
    }
	
    public Connection getDbConnection(){
        if(_connection == null){
           _connection = DbConnectionManager.getInstance().get();
        }
    	
        return _connection;
    }
	
    public void closeDbConnection(){
       DbConnectionManager.getInstance().put(_connection);
    }
    
    public boolean hasValidDbConnection(){
      try {
          return _connection != null && !_connection.isClosed();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    	
      return false;
    }
}
