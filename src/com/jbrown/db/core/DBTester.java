package com.jbrown.db.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.jbrown.core.exception.BorwnException;

public class DBTester {
 
	static String SELECT_SQL = "SELECT * FROM Constants";

	public void read() {
		DbConnectionManager h = DbConnectionManager.getInstance();
		Connection c = null;

		try {
			c = h.get();
			PreparedStatement s = c
					.prepareStatement(SELECT_SQL,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				System.out.printf("%s %s", rs.getString(1), rs.getString(2));
			}

			s.close();
		} catch (SQLException e) {
			throw new BorwnException(e);
		} finally {
			if (c != null)
				h.put(c);
		}

	}
}
