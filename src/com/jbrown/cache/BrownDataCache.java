package com.jbrown.cache;

import java.io.Serializable;

import org.apache.commons.collections.map.LRUMap;

public class BrownDataCache implements Serializable {
	private final int CACHE_SIZE = 1000;
	private static BrownDataCache _instance = new BrownDataCache();
	private final DayTracker _dayTracker;

	private BrownDataCache() {
		_dayTracker = new DayTracker();
		new LRUMap(CACHE_SIZE);
	}

	public synchronized static BrownDataCache getInstance() {
		if (_instance._dayTracker.isNewDay(false)) {
			_instance = new BrownDataCache();
		}
		return _instance;
	}
	  
	public String[] getAllCities(){
		return new String[]{"Delhi", "Kolkata"};
	}
	
}
