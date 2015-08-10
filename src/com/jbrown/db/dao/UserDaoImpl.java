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

public class UserDaoImpl implements UserDao {
  List<BrownUserI> users;
  private Connection connection;
  private Statement statement;
  private ResultSet rs;

  static String INSERT_SQL = "insert into users (name, email, phone, password) "
      + "values ('%s', '%s', '%s', '%s')";

  static String SELECT_ALL_SQL = "select id, name, email, phone, password from users";

  static String SELECT_BY_EMAIL_SQL = "select id, name, email, phone, password from users"
      + " where email='%s'";

  static String UPDATE_SQL = "update users set name='?', phone='?', password='?'"
      + " where email='%s'";

  static String DELETE_SQL = "delete from users where email='%s'";

  public UserDaoImpl() {
  }

  @Override
  public boolean createUser(BrownUserI user) {
    String insertSql = String.format(INSERT_SQL, user.getName(),
        user.getEmail(), user.getPhone(), user.getPassword());

    try {
      connection = DbConnectionManager.getInstance().get();
      statement = connection.createStatement();
      int row = statement.executeUpdate(insertSql);
      
      if(row > 0){
        return true;
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      DbUtil.close(statement);
      DbConnectionManager.getInstance().put(connection);
    }

    return false;
  }

  @Override
  public List<BrownUserI> getAllUsers() {
    List<BrownUserI> userList = new ArrayList<BrownUserI>();

    String selectSql = String.format(SELECT_ALL_SQL);

    try {
      connection = DbConnectionManager.getInstance().get();
      statement = connection.createStatement();
      rs = statement.executeQuery(selectSql);

      while (rs.next()) {
        int id = rs.getInt(1);
        String name = rs.getString(2);
        String email = rs.getString(3);
        String phone = rs.getString(4);
        String password = rs.getString(5);

        userList.add(new BrownUser(id + "", name, email, phone, password));
      }
    } catch (SQLException ex) {
      System.out.printf("Error in getAllUsers => %s", ex);
    } finally {
      DbUtil.close(rs);
      DbUtil.close(statement);
      DbConnectionManager.getInstance().put(connection);
    }

    return userList;
  }

  @Override
  public BrownUserI getUserByEmail(String email) {
    BrownUserI user = null;
    String selectSql = String.format(SELECT_BY_EMAIL_SQL, email);

    try {
      connection = DbConnectionManager.getInstance().get();
      statement = connection.createStatement();
      rs = statement.executeQuery(selectSql);

      while (rs.next()) {
        int id = rs.getInt(1);
        String name = rs.getString(2);
        String mail = rs.getString(3);
        String phone = rs.getString(4);
        String password = rs.getString(5);

        user = new BrownUser(id + "", name, mail, phone, password);
      }
    } catch (SQLException ex) {
      System.out.printf("Error in getUser => %s", ex);
    } finally {
      DbUtil.close(rs);
      DbUtil.close(statement);
      DbConnectionManager.getInstance().put(connection);
    }

    return user;
  }

  @Override
  public boolean updateUser(BrownUserI user) {
    String updateQuery = String.format(UPDATE_SQL, user.getName(),
        user.getPhone(), user.getPassword(), user.getEmail());

    try {
      connection = DbConnectionManager.getInstance().get();
      statement = connection.createStatement();
      int rowEffected = statement.executeUpdate(updateQuery);

      if (rowEffected > 0) {
        return true;
      }

    } catch (SQLException ex) {
      System.out.printf("Error in updateUser => %s", ex);
    } finally {
      DbUtil.close(statement);
      DbConnectionManager.getInstance().put(connection);
    }

    return false;
  }

  @Override
  public boolean deleteUser(BrownUserI user) {
    String deleteQuery = String.format(DELETE_SQL, user.getEmail());

    try {
      connection = DbConnectionManager.getInstance().get();
      statement = connection.createStatement();
      int rowEffected = statement.executeUpdate(deleteQuery);

      if (rowEffected > 0) {
        return true;
      }

    } catch (SQLException ex) {
      System.out.printf("Error in deleteUser => %s", ex);
    } finally {
      DbUtil.close(statement);
      DbConnectionManager.getInstance().put(connection);
    }

    return false;
  }
}