package com.jbrown.providers.ext.flickr;

public enum ImageSize {
/**
 * 			s	small square 75x75.
			q	large square 150x150.
			t	thumbnail, 100 on longest side.
			m	small, 240 on longest side
			n	small, 320 on longest side
			-	medium, 500 on longest side
			z	medium 640, 640 on longest side
			c	medium 800, 800 on longest side†
			b	large, 1024 on longest side*
			o	original image, either a jpg, gif or png, depending on source format
 */
	
	SIZE_75x75 ('s', "Small square 75x75", "75x75"), 
	SIZE_150x150 ('q', "Large square 150x150", "150x150"), 
	SIZE_100_PLUS ('t', "Thumbnail, 100 on longest side", "100_PLUS"), 
	SIZE_240_PLUS ('m', "Small, 240 on longest side", "240_PLUS"), 
	SIZE_320_PLUS ('n', "Small, 320 on longest side", "320_PLUS"), 
	SIZE_DEFAULT (' ', "Medium, 500 on longest side", "DEFAULT"),
	SIZE_640x640 ('z', "Medium 640, 640 on longest side", "640x640"),
	SIZE_800x800 ('c', "Medium 800, 800 on longest side", "800x800"),
	SIZE_1024_PLUS ('c', "Large, 1024 on longest side", "1024_PLUS"),
	SIZE_ORIGINAL ('o', "Original image, either a jpg, gif or png, depending on source format", "ORIGINAL");
	
	public char flickCode;
	public String desc;
	public String brownCode;
	
	private ImageSize(char code, String desc, String kode) {
		this.flickCode = code;
		this.desc = desc;
		this.brownCode = kode;
	}
	
	public char getFlickrCode() {
		return this.flickCode;
	}
	
	public String getBrownCode() {
		return this.brownCode;
	}
	
	public String getDesc() {
		return desc;
	}

	public static ImageSize getInstance(char flickrcode) {
		for (ImageSize imageSize : ImageSize.values()) {
			if (flickrcode == imageSize.getFlickrCode()){
				return imageSize;
			}
		}
		
		return null;
	}
	
	public static ImageSize getInstance(String brownCode) {
		for (ImageSize imageSize : ImageSize.values()) {
			if (brownCode == imageSize.getBrownCode()){
				return imageSize;
			}
		}
		
		return null;
	}
	
	public boolean typeOf(ImageSize imageSize) {
		return getInstance(this.getFlickrCode()).equals(imageSize);
	}
}
