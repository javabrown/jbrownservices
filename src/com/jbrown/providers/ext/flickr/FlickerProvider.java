package com.jbrown.providers.ext.flickr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jbrown.cache.BrownDataCache;
import com.jbrown.ext.capsule.impl.BrownGeoCapsuleI;
import com.jbrown.providers.ProviderI;
import com.jbrown.web.ws.BrownRequestI;

public class FlickerProvider implements ProviderI {
	private FlickFetcher flickFetcher;

	public FlickerProvider(BrownRequestI request) {
		this.flickFetcher = new FlickFetcher(request);
	}

	@Override
	public List<Map<String, Object>> getResult() {
		Map<String, Object> map = new HashMap<String, Object>();

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		map.put("images",
				  this.flickFetcher.getImagePath(ImageSize.SIZE_75x75));
		
		list.add(map);

		return list;
	}
}