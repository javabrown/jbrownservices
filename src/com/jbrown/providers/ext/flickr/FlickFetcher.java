package com.jbrown.providers.ext.flickr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jbrown.core.util.BrownKeysI;
import com.jbrown.core.util.BrownParser;
import com.jbrown.core.util.BrownUtil;
import com.jbrown.core.util.Pair;
import com.jbrown.web.ws.BrownRequestI;

public class FlickFetcher {
	private BrownRequestI request;
	
	public FlickFetcher(BrownRequestI request) {
		this.request = request;
	}

	public Pair[] getImagePath(ImageSize size) {
		FlickrPhoto[] flicks = getPublicPhotos(request);
		List<Pair<String, String>> result = new ArrayList<Pair<String, String>>();
 
		for (FlickrPhoto photo : flicks) {
			result.add(new Pair<String, String>(photo.getName(), photo
					.getPhotoUrl(size)));
		}
		
		return result.toArray(new Pair[0]);
	}
	
	private FlickrPhoto[] getPublicPhotos(BrownRequestI request) {
		List<FlickrPhoto> result = new ArrayList<FlickrPhoto>();

		Map<String, String> flicData = request.getBrownContext()
				.getStaticData().getProvidersData()
				.getProviderData(BrownKeysI.PROVIDER_FLICKR_K);
		String url = flicData.get(BrownKeysI.PROVIDER_FLICKR_PUBLIC_URL_K);

		try {
			String xml = BrownUtil.httpGet(url);
			System.out.println(xml);
			BrownParser parser = new BrownParser(xml);
			List<Map<String, String>> list = parser.getAttributes("photo");

			for (Map<String, String> map : list) {
				FlickrPhoto photo = new FlickrPhoto(map);
				result.add(photo);
			}

			return result.toArray(new FlickrPhoto[0]);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return new FlickrPhoto[0];
	}
}
