package com.jbrown.db.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.utils.SystemProperty;

public class GuestbookServlet extends HttpServlet {
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    String url = null;
    try {
      if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
        Class.forName("com.mysql.jdbc.GoogleDriver");
        url = "jdbc:google:mysql://javabrownservices:jbcloud/guestbook?user=xxxx";
      } else {
        // Local MySQL instance to use during development.
        Class.forName("com.mysql.jdbc.Driver");
        url = "jdbc:mysql://xxxxxxxxxxxx:3306/guestbook?user=xxxx";
      }
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }

    PrintWriter out = resp.getWriter();
    try {
      Connection conn = DriverManager.getConnection(url);
      try {
        String fname = req.getParameter("fname");
        String content = req.getParameter("content");
        if (fname == "" || content == "") {
          out.println("<html><head></head><body>You are missing either a message or a name! Try again! "
              + "Redirecting in 3 seconds...</body></html>");
        } else {
          String statement = "INSERT INTO entries (guestName, content) VALUES( ? , ? )";
          PreparedStatement stmt = conn.prepareStatement(statement);
          stmt.setString(1, fname);
          stmt.setString(2, content);
          int success = 2;
          success = stmt.executeUpdate();
          if (success == 1) {
            out.println("<html><head></head><body>Success! Redirecting in 3 seconds...</body></html>");
          } else if (success == 0) {
            out.println("<html><head></head><body>Failure! Please try again! "
                + "Redirecting in 3 seconds...</body></html>");
          }
        }
      } finally {
        conn.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    resp.setHeader("Refresh", "3; url=/guestbook.jsp");
  }
}