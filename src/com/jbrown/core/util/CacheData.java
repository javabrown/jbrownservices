package com.jbrown.core.util;

import java.io.Serializable;

import com.jbrown.web.ws.BrownRequestI;

public class CacheData implements CacheDataI, Serializable {
	private String _brownUserId;
	private String _key;
	private String _value;

	public CacheData(String key, String value, BrownRequestI request) {
		_brownUserId = BrownAuthUtil.getBrownUserId(request);
		_key = key;
		_value = value;
	}
	
	public CacheData(String key, BrownRequestI request) {
		_brownUserId = BrownAuthUtil.getBrownUserId(request);
		_key = key;
	}

	public String getBrownUserId() {
		return _brownUserId;
	}

	@Override
	public String getPrivateKey() {
		return String.format("%s|%s", _brownUserId, _key);
	}

	@Override
	public String getKey() {
		return _key;
	}
	
	@Override
	public String getValue() {
		return _value;
	}

	@Override
	public boolean isStorable() {
		return !StringUtil.isEmpty(_brownUserId, _key, _value);
	}
	
	@Override
	public boolean isFetchable() {
		return !StringUtil.isEmpty(_brownUserId, _key);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((_brownUserId == null) ? 0 : _brownUserId.hashCode());
		result = prime * result + ((_key == null) ? 0 : _key.hashCode());
		result = prime * result + ((_value == null) ? 0 : _value.hashCode());
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
		CacheData other = (CacheData) obj;
		if (_brownUserId == null) {
			if (other._brownUserId != null)
				return false;
		} else if (!_brownUserId.equals(other._brownUserId))
			return false;
		if (_key == null) {
			if (other._key != null)
				return false;
		} else if (!_key.equals(other._key))
			return false;
		if (_value == null) {
			if (other._value != null)
				return false;
		} else if (!_value.equals(other._value))
			return false;
		return true;
	}
}
