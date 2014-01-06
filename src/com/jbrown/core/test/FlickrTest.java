package com.jbrown.core.test;

 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jbrown.core.util.BrownKeysI;
import com.jbrown.core.util.BrownParser;
import com.jbrown.core.util.BrownUtil;
import com.jbrown.core.util.StringUtil;
 

public class FlickrTest {
	static final String apiKey = "801ba291f728d037ce22b7664975667a";
	static final String secret = "191bb0fe52cd3017";
	static final String token =null;
	
	static final String public_url = "http://api.flickr.com/services/rest/?&method=flickr.people.getPublicPhotos&api_key=801ba291f728d037ce22b7664975667a&user_id=80622574@N07";
   
	public static String[] getImagePath(BrownParser parser, String size){
		List<Map<String, String>> list = parser.getAttributes("photo");
		
		List<String> result = new ArrayList<String>();
		
		/** 
		 *  http://farm{farm-id}.staticflickr.com/{server-id}/{photo-id}_{secret}_m.jpg 
		 * 	s	small square 75x75
			q	large square 150x150
			t	thumbnail, 100 on longest side
			m	small, 240 on longest side
			n	small, 320 on longest side
			-	medium, 500 on longest side
			z	medium 640, 640 on longest side
			c	medium 800, 800 on longest side†
			b	large, 1024 on longest side*
			o	original image, either a jpg, gif or png, depending on source format
		 */
		 
		for(Map<String, String> map : list) {
			StringBuilder url = new StringBuilder();			
			url.append("http://farm").append(map.get("farm"));
			url.append(".staticflickr.com/").append(map.get("server"));
			url.append("/").append(
					map.get("id")).append("_").append(map.get("secret"));
			
			if(StringUtil.isEmpty(size)){
				size = BrownKeysI.EMPTY_K;
			}
			
			String pic_url = url.append("_").append(size).append(".jpg").toString();
			System.out.println(pic_url);
			result.add(pic_url);
		}
		
		return result.toArray(new String[0]);
	}
	
	public static void main(String[] xx) throws IOException{
		String xml = BrownUtil.httpGet(public_url);
		System.out.println(xml);
		BrownParser parser = new BrownParser(xml);
		System.out.println(getImagePath(parser, null)); 
	}
}
