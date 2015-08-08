package com.jbrown.db.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

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
		ds.setMaxIdle(20);
		ds.setMaxWait(1000);
		ds.setRemoveAbandoned(true);
		ds.setRemoveAbandonedTimeout(180);
		ds.setTestOnBorrow(true);
		ds.setValidationQuery("SELECT 1");
		_ds = ds;
	}

	public static synchronized DbConnectionManager getInstance() {
		if (_instance == null){
			_instance = new DbConnectionManager();
		}
		
		return _instance;
	}
	  
	public Connection get() {
		Connection c = null;

		try {
			c = _ds.getConnection();
		} catch (SQLException se) {
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
		map.put("dev.dbDriver", "org.gjt.mm.mysql.Driver");
		map.put("dev.dbURL", "173.194.226.233");
		map.put("dev.dbPort", "3006");
		map.put("dev.dbUser", "root");
		map.put("dev.dbPassword", "xxxx");

		return map;
	}

	private String getConfig(String key) {
		boolean isDev = true; // Add GAE Plateform info

		String prefix = isDev ? "dev" : "gae";

		return _configMap.get(String.format("%s.%s", prefix, key));
	}
}