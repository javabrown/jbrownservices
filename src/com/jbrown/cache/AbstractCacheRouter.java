package com.jbrown.cache;

public abstract class AbstractCacheRouter {
	public abstract CacheRouter getCacheRouter();
	
	public interface CacheRouter {
		CacheRoute getRoute();
		boolean isAvailable();
	}
}

 