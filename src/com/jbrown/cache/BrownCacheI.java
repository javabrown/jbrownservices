package com.jbrown.cache;

import com.jbrown.cache.AbstractCacheRouter.CacheRouter;

public interface BrownCacheI<K, V> extends CacheRouter {
	void set(K key, V value);
	V get(K key);
}
