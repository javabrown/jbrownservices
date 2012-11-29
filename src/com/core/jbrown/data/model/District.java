package com.core.jbrown.data.model;

public class District {
	String districtName;
	PostOffice[] posts;

	public District(String districtName, PostOffice[] posts) {
		this.districtName = districtName;
		this.posts = posts;
	}

	public String getDistrictName() {
		return districtName;
	}

	public PostOffice[] getPostOffices() {
		return posts;
	}

}
