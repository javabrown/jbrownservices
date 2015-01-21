package com.jbrown.cache;

import java.util.HashMap;
import java.util.Map;

import com.jbrown.cache.AbstractCacheRouter.CacheRouter;

public class BrownCache extends AbstractCacheRouter {
	@Override
	public CacheRouter getCacheRouter() {
		// TODO Auto-generated method stub
		return null;
	}

 
	public CacheRoute getRoute() {
		// TODO Auto-generated method stub
		return null;
	}

	 
}


class InMemoryCache implements CacheRouter, BrownCacheI {
	Map<String, ? extends Object> _map;
	
	public InMemoryCache(){
		_map = new HashMap<String,  Object>();
	}
	
	@Override
	public CacheRoute getRoute() {
		return CacheRoute.MEMORY_CACHED;
	}

	@Override
	public <T extends Object> void set(String key, T value) {
		//_map.put(key, (Object) value);
	}

	@Override
	public <T> void get(String key) {
		_map.get(key);
	}
}