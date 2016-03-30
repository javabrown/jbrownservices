package com.jbrown.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RecursiveHashMap<K, V> extends java.util.HashMap implements
		MultiMap<K, V> {
	private Map<K, List<V>> _alteredMap = null;

	public RecursiveHashMap() {
		_alteredMap = new HashMap<K, List<V>>();
	}

	@Override
	public void insert(K key, V value) {
		if (_alteredMap.get(key) != null) {
			List<V> existingValue = _alteredMap.get(key);
			existingValue.add(value);
			_alteredMap.put(key, existingValue);
		} else {
			ArrayList<V> newList = new ArrayList<V>();
			newList.add(value);
			_alteredMap.put(key, newList);
		}
	}

	@Override
	public List<V> getValue(K key) {
		return _alteredMap.get(key);
	}

	@Override
	public Map<K, List<V>> getMap() {
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
