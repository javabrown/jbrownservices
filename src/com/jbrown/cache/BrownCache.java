package com.jbrown.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.memcache.Stats;
import com.jbrown.cache.AbstractCacheRouter.CacheRouter;
import com.jbrown.core.exception.BorwnException;

public class BrownCache extends AbstractCacheRouter {
	private static BrownCache _instance;
	
	private CacheRouter[] _cacheRouters;
	
	@SuppressWarnings("rawtypes")
	public static BrownCacheI getInstance() {
		if (_instance == null) {
			_instance = new BrownCache();
		}

		return (BrownCacheI) _instance.getCacheRouter();
	}
	
	private BrownCache() {
		_cacheRouters = new CacheRouter[] {
				new GoogleMemcachedCache(),
				new InMemoryCache(),
		};
	}
 
	@Override
	public CacheRouter getCacheRouter() {
		for (CacheRouter router : _cacheRouters) {
			if (router.isAvailable()) {
				System.out.printf("%s Select", router);
				return router;
			}
		}

		throw new BorwnException("Attension required!! No Cache Router");
	}

	public CacheRoute getRoute() {
		return null;
	}
}


class InMemoryCache<K, V> implements CacheRouter, BrownCacheI<K, V> {
	Map<K, V> _map;

	public InMemoryCache() {
		_map = new HashMap<K, V>();
	}

	@Override
	public CacheRoute getRoute() {
		return CacheRoute.MEMORY_CACHED;
	}

	@Override
	public boolean set(K key, V value) {
		if(key != null && value != null){
			_map.put(key, value);
			return true;
		}
		
		return false;
	}

	@Override
	public V get(K key) {
		return _map.get(key);
	}

	@Override
	public boolean isAvailable() {
		return true;
	}
}

class GoogleMemcachedCache<K, V> implements CacheRouter, BrownCacheI<K, V> {
	private static MemcacheService _c;

	public GoogleMemcachedCache(){
		_c = getMemcacheService();
	}

	private MemcacheService getMemcacheService() {
		try {
			MemcacheService c = MemcacheServiceFactory.getMemcacheService("jbrownservices_");
			c.put("__ping", "__ping");
			return c;
		} catch (Exception ex) {
			System.out.println("Error during Google->getMemcacheService()!!");
		}
		
		return null;
	}
	
	@Override
	public CacheRoute getRoute() {
		return CacheRoute.GOOGLE_MEMCACHED;
	}

	@Override
	public boolean set(K key, V value) {
		if(key != null && value != null){
			_c.put(key, value);
			return true;
		}
		
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V get(K key) {
		return (V) _c.get(key);
	}

	@SuppressWarnings("unchecked")
	public Map<K, V> getAll(Collection<K> keys) {
		return (Map<K, V>) _c.getAll(keys);
	}
 

	public void putAll(Map<K, V> values) {
		 _c.putAll(values);
	}

	public boolean remove(K key) {
		return _c.delete(key);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean deleteAll(Collection<K> keys) {
		Set<K> deletedKeys = _c.deleteAll(keys);
		if (deletedKeys.size() == keys.size())
			return true;
		return false;
	}

	public static Stats getStatistics() {
		return _c.getStatistics();
	}

	@Override
	public boolean isAvailable() {
		if (_c == null) {
			_c = getMemcacheService(); // Retry to connect to google memcached.
		}
		
		return _c != null;
	}
}

//class GoogleCache {
//	private static MemcacheService c = MemcacheServiceFactory
//			.getMemcacheService();
//
//	public static boolean containsKey(Object key) {
//		return c.contains(key);
//	}
//
//	public static Object get(Object key) {
//		System.out.printf("memcached get requested for key %s!!", key);
//		return c.get(key);
//	}
//
//	public static Map<String, Object> getAll(Collection<String> keys) {
//		return c.getAll(keys);
//	}
//
//	public static void put(Object key, Object value) {
//		c.put(key, value);
//		System.out.printf("memcached put done for key %s!!", key);
//	}
//
//	public static void putAll(Map<String, Object> values) {
//		c.putAll(values);
//	}
//
//	public static boolean remove(Object key) {
//		return c.delete(key);
//	}
//
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	public static boolean deleteAll(Collection keys) {
//		Set deletedKeys = c.deleteAll(keys);
//		if (deletedKeys.size() == keys.size())
//			return true;
//		return false;
//	}
//
//	public static Stats getStatistics() {
//		return c.getStatistics();
//	}
//}