package com.jbrown.cache;

import java.io.Serializable;

public enum CacheRoute implements Serializable {
	LOCAL_MEMCACHED, GOOGLE_MEMCACHED, MEMORY_CACHED;

	public boolean typeOf(CacheRoute route) {
		return this.equals(route);
	}
}