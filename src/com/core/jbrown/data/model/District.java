package com.core.jbrown.data.model;

public class District {
	String districtName;
	Post[] posts;

	public District(String districtName, Post[] posts) {
		this.districtName = districtName;
		this.posts = posts;
	}

	public String getDistrictName() {
		return districtName;
	}

	public Post[] getPosts() {
		return posts;
	}

}
