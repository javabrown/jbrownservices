//package com.jbrown.cache;
//
//import java.util.Collection;
//import java.util.Map;
//import java.util.Set;
//import com.google.appengine.api.memcache.MemcacheService;
//import com.google.appengine.api.memcache.MemcacheServiceFactory;
//import com.google.appengine.api.memcache.Stats;
//
//public class GoogleCache {
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