package com.jbrown.core.util;

import java.util.Arrays;

public class StrKey {
	private String[] keys;

	public StrKey(String... strings) {
		keys = strings;
	}

	public String getKey(){
	  return Arrays.toString(keys);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(keys);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StrKey other = (StrKey) obj;
		if (!Arrays.equals(keys, other.keys))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StrKey [keys=" + Arrays.toString(keys) + "]";
	}

}
