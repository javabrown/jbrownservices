package com.jbrown.cache;

import com.jbrown.cache.AbstractCacheRouter.CacheRouter;

public interface BrownCacheI extends CacheRouter{
	public <T extends Object> void set(String key, T value);
	public <T extends Object> void get(String key);
}
