package com.jbrown.db.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jbrown.db.core.DbConnectionManager;
import com.jbrown.db.core.DbUtil;
import com.jbrown.user.BrownUser;
import com.jbrown.user.BrownUserI;
import com.jbrown.web.ws.BrownRequestI;

public class UserDao extends AbstractDao implements UserDaoI {
  private List<BrownUserI> users;
  //private Connection connection;
  private Statement statement;
  private ResultSet rs;

  static String INSERT_SQL = "insert into users (name, email, phone, password, domain) "
      + "values ('%s', '%s', '%s', '%s', '%s')";

  static String SELECT_ALL_SQL = "select id, name, email, phone, password, domain from users";

  static String SELECT_BY_EMAIL_SQL = "select id, name, email, phone, password, domain from users"
      + " where email='%s'";
  
  static String SELECT_BY_EMAIL_PASSWORD_SQL = "select id, name, email, phone, password, domain from users"
	      + " where email='%s' AND password='%s'";

  static String UPDATE_SQL = "update users set name='%s', phone='%s', password='%s', domain='%s'"
      + " where email='%s'";

  static String DELETE_SQL = "delete from users where email='%s'";

  public UserDao(BrownRequestI request) {
	  super(request);
  }

  @Override
  public boolean createUser(BrownUserI user) {
    String insertSql = String.format(INSERT_SQL, user.getName(),
        user.getEmail(), user.getPhone(), user.getPassword(), user.getDomain());

    try {
      statement = getConnection().createStatement();
      int row = statement.executeUpdate(insertSql);
      
      if(row > 0){
        return true;
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      DbUtil.close(statement);
    }

    return false;
  }

  @Override
  public List<BrownUserI> getAllUsers() {
    List<BrownUserI> userList = new ArrayList<BrownUserI>();

    String selectSql = String.format(SELECT_ALL_SQL);

    try {
      statement = getConnection().createStatement();
      rs = statement.executeQuery(selectSql);

      while (rs.next()) {
        int id = rs.getInt(1);
        String name = rs.getString(2);
        String email = rs.getString(3);
        String phone = rs.getString(4);
        String password = rs.getString(5);
        String domain = rs.getString(6);
        
        userList.add(new BrownUser(id + "", name, email, phone, password, domain));
      }
    } catch (SQLException ex) {
      System.out.printf("Error in getAllUsers => %s", ex);
    } finally {
      DbUtil.close(rs);
      DbUtil.close(statement);
    }

    return userList;
  }

  @Override
  public BrownUserI getUserByEmail(String email) {
    BrownUserI user = null;
    String selectSql = String.format(SELECT_BY_EMAIL_SQL, email);

    try {
      statement = getConnection().createStatement();
      rs = statement.executeQuery(selectSql);

      while (rs.next()) {
        int id = rs.getInt(1);
        String name = rs.getString(2);
        String mail = rs.getString(3);
        String phone = rs.getString(4);
        String password = rs.getString(5);
        String domain = rs.getString(6);
        
        user = new BrownUser(id + "", name, mail, phone, password, domain);
      }
    } catch (SQLException ex) {
      System.out.printf("Error in getUser => %s", ex);
    } finally {
      DbUtil.close(rs);
      DbUtil.close(statement);
    }

    return user;
  }

  @Override
  public boolean updateUser(BrownUserI user) {
    String updateQuery = String.format(UPDATE_SQL, user.getName(),
        user.getPhone(), user.getPassword(), user.getEmail());

    try {
      statement = getConnection().createStatement();
      int rowEffected = statement.executeUpdate(updateQuery);

      if (rowEffected > 0) {
        return true;
      }

    } catch (SQLException ex) {
      System.out.printf("Error in updateUser => %s", ex);
    } finally {
      DbUtil.close(statement);
    }

    return false;
  }

  @Override
  public boolean deleteUser(BrownUserI user) {
    String deleteQuery = String.format(DELETE_SQL, user.getEmail());

    try {
      statement = getConnection().createStatement();
      int rowEffected = statement.executeUpdate(deleteQuery);

      if (rowEffected > 0) {
        return true;
      }

    } catch (SQLException ex) {
      System.out.printf("Error in deleteUser => %s", ex);
    } finally {
      DbUtil.close(statement);
    }

    return false;
  }
  
  @Override
  public BrownUserI findUser(String email, String password) {
    String selectSql = String.format(SELECT_BY_EMAIL_PASSWORD_SQL, email, password);
    BrownUserI user = null;
    
    try {
        statement = getConnection().createStatement();
        rs = statement.executeQuery(selectSql);

        while (rs.next()) {
          int id = rs.getInt(1);
          String name = rs.getString(2);
          String mail = rs.getString(3);
          String phone = rs.getString(4);
          String passwrd = rs.getString(5);
          String domain = rs.getString(6);
 
          user = new BrownUser(id + "", name, mail, phone, passwrd, domain);
        }
      } catch (SQLException ex) {
        System.out.printf("Error in getUser => %s", ex);
      } finally {
        DbUtil.close(rs);
        DbUtil.close(statement);
      }

    return user;
  }
}