package com.jbrown.providers.ext.flickr;

import java.io.Serializable;
import java.util.Map;

public class FlickrPhoto implements Serializable{
	private String id;
	private String owner;
	private String secret;
	private String server;
	private String farm;
	private String title;
	private String isPublic;
	private String isFriend;
	private String isFamily;

	public FlickrPhoto(Map<String, String> map) {
		this.id = map.get("id");
		this.owner = map.get("owner");
		this.secret =  map.get("secret");;
		this.server =  map.get("server");;
		this.farm =  map.get("farm");;
		this.title =  map.get("title");;
		this.isPublic =  map.get("isPublic");;
		this.isFriend =  map.get("isFriend");;
		this.isFamily =  map.get("isFamily");;
	}
	
	public String getPhotoUrl(ImageSize imageSize) {
		StringBuilder url = new StringBuilder("http://farm");			
		url.append(this.farm).append(".staticflickr.com/");
		url.append(this.server).append("/");
		url.append(this.id).append("_").append(this.secret);
		
		if(imageSize != null && !imageSize.typeOf(ImageSize.SIZE_DEFAULT)){
			url.append("_").append(imageSize.getFlickrCode());
		}
		
		String pic_url = url.append(".jpg").toString();
		
		System.out.println(pic_url);
		
		return pic_url;
	}
	
	public String getName(){
	  return this.title;	
	}
	
	@Override
	public String toString() {
		return "FlickrPhoto [id=" + id + ", owner=" + owner + ", secret="
				+ secret + ", server=" + server + ", farm=" + farm + ", title="
				+ title + ", isPublic=" + isPublic + ", isFriend=" + isFriend
				+ ", isFamily=" + isFamily + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((farm == null) ? 0 : farm.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((isFamily == null) ? 0 : isFamily.hashCode());
		result = prime * result
				+ ((isFriend == null) ? 0 : isFriend.hashCode());
		result = prime * result
				+ ((isPublic == null) ? 0 : isPublic.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((secret == null) ? 0 : secret.hashCode());
		result = prime * result + ((server == null) ? 0 : server.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		FlickrPhoto other = (FlickrPhoto) obj;
		if (farm == null) {
			if (other.farm != null)
				return false;
		} else if (!farm.equals(other.farm))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isFamily == null) {
			if (other.isFamily != null)
				return false;
		} else if (!isFamily.equals(other.isFamily))
			return false;
		if (isFriend == null) {
			if (other.isFriend != null)
				return false;
		} else if (!isFriend.equals(other.isFriend))
			return false;
		if (isPublic == null) {
			if (other.isPublic != null)
				return false;
		} else if (!isPublic.equals(other.isPublic))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (secret == null) {
			if (other.secret != null)
				return false;
		} else if (!secret.equals(other.secret))
			return false;
		if (server == null) {
			if (other.server != null)
				return false;
		} else if (!server.equals(other.server))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}
