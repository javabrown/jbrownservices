package com.jbrown.core.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MultiMap<K,V> {

	void insert(K key, V value);
	 
	public List<V> getValue(K key);

	public Map<K, List<V>> getMap();

	public int size();

	public Set<K> keySet();
}
