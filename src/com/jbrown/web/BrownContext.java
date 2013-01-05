package com.jbrown.web;

import com.jbrown.web.resources.StaticData;

public class BrownContext implements BrownContextI{
	private StaticData _staticData;

	@Override
	public StaticData getStaticData() {
		return _staticData;
	}

	public void setStaticData(StaticData _staticData) {
		this._staticData = _staticData;
	}
}
