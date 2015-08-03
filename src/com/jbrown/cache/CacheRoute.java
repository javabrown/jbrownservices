package com.jbrown.cache;

import java.io.Serializable;

public enum CacheRoute implements Serializable {
	LOCAL_MEMCACHED("local-memcached"), 
	GOOGLE_MEMCACHED("google-memcached"), 
	MEMORY_CACHED("in-memory-cache");

	String _name;

	CacheRoute(String name) {
		_name = name;
	}

	public boolean typeOf(CacheRoute route) {
		return this.equals(route);
	}

	public String getName() {
		return _name;
	}
}