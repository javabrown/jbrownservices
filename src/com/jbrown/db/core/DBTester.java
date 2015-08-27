package com.jbrown.db.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jbrown.core.exception.BorwnException;
import com.jbrown.db.dao.UserDao;
import com.jbrown.db.dao.model.User;
import com.jbrown.user.BrownUser;

public class DBTester {
  public static final String EntityKind = "TestComments";
  static String SELECT_SQL = "select * from users";

  public static String[] read() {
    DbConnectionManager h = DbConnectionManager.getInstance();
    Connection c = null;
    List<String> resultList = new ArrayList<String>();

    // store();
    // /retrieve();
 
    try {
      c = h.get();
      PreparedStatement s = c.prepareStatement(SELECT_SQL,
          ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

      ResultSet rs = s.executeQuery();
      while (rs.next()) {
        resultList
            .add(String.format("%s %s", rs.getString(1), rs.getString(2)));
      }

      s.close();
    } catch (SQLException e) {
      throw new BorwnException(e);
    } finally {
      if (c != null)
        h.put(c);
    }
 
    
   
    return resultList.toArray(new String[0]);
  }

  // private static void store() {
  // Key commentKey = KeyFactory.createKey(EntityKind, "getrk@yahoo.com");
  // Entity commentEntity = new Entity("Comment", commentKey);
  // commentEntity.setProperty("email", "getrk@yahoo.com");
  // commentEntity.setProperty("date", "10/20/2014");
  // commentEntity.setProperty("text", "This is test conext");
  //
  // DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
  // Key key = datastore.put(commentEntity);
  //
  // System.out.printf("DATA STORED: %s", key.getId());
  // }
  //
  // private static String retrieve() {
  // DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
  // Query query = new Query("Comment");
  // List<Entity> entities = datastore.prepare(query).asList(
  // FetchOptions.Builder.withLimit(10));
  // List<String> comments = new ArrayList<String>();
  //
  // String res = "";
  //
  // if (entities != null && entities.size() > 0) {
  // for (Entity entity : entities) {
  // res = res
  // + String.format("%s.%s.%s", (String) entity.getProperty("email"),
  // (String) entity.getProperty("text"),
  // (String) entity.getProperty("date"));
  // }
  // }
  //
  // return "NO-SQL[ " + res + " ]";
  // }
}
