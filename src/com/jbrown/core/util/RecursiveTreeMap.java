package com.jbrown.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class RecursiveTreeMap<K, V> extends java.util.HashMap implements
		MultiTreeMap<K, V> {
	private Map<K, Set<V>> _alteredMap = null;

	public RecursiveTreeMap() {
		_alteredMap = new HashMap<K, Set<V>>();
	}

	@Override
	public void insert(K key, V value) {
		if (_alteredMap.get(key) != null) {
			Set<V> existingValue = _alteredMap.get(key);
			existingValue.add(value);
			_alteredMap.put(key, existingValue);
		} else {
			Set<V> newSet = new HashSet<V>();
			newSet.add(value);
			_alteredMap.put(key, newSet);
		}
	}

	@Override
	public Set<V> getValue(K key) {
		return _alteredMap.get(key);
	}

	@Override
	public Map<K, Set<V>> getMap() {
		return new HashMap(_alteredMap);
	}

	@Override
	public int size() {
		return _alteredMap.size();
	}

	@Override
	public Set<K> keySet() {
		return _alteredMap.keySet();
	}
}
