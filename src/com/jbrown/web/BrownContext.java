package com.jbrown.web;

import com.jbrown.web.resources.StaticData;

public class BrownContext implements BrownContextI{
	private StaticData staticData;

	@Override
	public StaticData getStaticData() {
		return this.staticData;
	}

	public void setStaticData(StaticData staticData) {
		this.staticData = staticData;
	}
}
