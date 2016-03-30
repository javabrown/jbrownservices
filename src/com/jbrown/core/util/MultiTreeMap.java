package com.jbrown.core.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MultiTreeMap<K,V> {

	void insert(K key, V value);
	 
	public Set<V> getValue(K key);

	public Map<K, Set<V>> getMap();

	public int size();

	public Set<K> keySet();
}
