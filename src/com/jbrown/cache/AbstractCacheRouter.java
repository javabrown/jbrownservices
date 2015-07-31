package com.jbrown.cache;

public abstract class AbstractCacheRouter {
	private CacheRoute _cacheRoute;
	
	public AbstractCacheRouter(){
		_cacheRoute = this.getCacheRouter().getRoute();
	}
	
	public abstract CacheRouter getCacheRouter();
	
	public interface CacheRouter {
		CacheRoute getRoute();
		boolean isAvailable();
	}
}

 