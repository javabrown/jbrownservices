package com.jbrown.core.util;

public interface CacheDataI {
	String getPrivateKey();
	String getKey();
	String getValue();
	boolean isStorable();
	boolean isFetchable();
}