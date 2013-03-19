package com.jbrown.core.util;

 
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class BrownUtil implements Serializable {
 
	public static boolean isEmpty(String... stringArray) {
		for (String s : stringArray) {
			if (isEmpty(s)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isEmpty(String s) {
		return (s == null) || s.trim().equals("");
	}

	public static String htmlEncode(String htm) {
		htm = htm.replaceAll("\\&", "&amp;");
		htm = htm.replaceAll("<", "&lt;");
		htm = htm.replaceAll(">", "&gt;");
		htm = htm.replaceAll("\\\"", "&quot;");
		htm = htm.replaceAll("'", "&#039;");
		htm = htm.replaceAll("\\\\", "&#092;");
		htm = htm.replaceAll("\\n", "<br\\>");
		return htm;
	}

	/**
	 * return unique key of price after checking the existing key content
	 * present in list. price key format will be : A-1 , A-2 , B-1, B-2 etc.
	 * 
	 * @param keyList
	 *            List
	 * @param key
	 *            String
	 * @return List
	 */
	public static String getStringKey(List<String> keyList, String key,
			boolean isReadOnly) {
		boolean keyAllocated = false;
		int keySeqIndex = 1;
		while (!keyAllocated) {
			if (keyList.contains(key + "-" + keySeqIndex))
				keySeqIndex++;
			else
				keyAllocated = true;
		}

		if (!isReadOnly)
			keyList.add(key + "-" + keySeqIndex);

		return key + "-" + keySeqIndex;
	}
 
	public static boolean hasCookie(ServletRequest req, String cookieName,
			String cookieValue) {
		boolean hasCookie = false;
		Cookie namedCookie = getCookie(req, cookieName);

		if (namedCookie != null) {
			// cookie exist case - check its value
			if (cookieValue == null) {
				hasCookie = true;
			} else if (namedCookie.getValue().equals(cookieValue)) {
				hasCookie = true;
			}
		}
		return hasCookie;
	}

	public static Cookie getCookie(ServletRequest req, String cookieName) {
		Cookie result = null;
		Map<String, Cookie> cookieMap = getCookieMap(req);
		if (cookieMap.containsKey(cookieName)) {
			result = cookieMap.get(cookieName);
		}
		return result;
	}

	public static Map<String, Cookie> getCookieMap(ServletRequest req) {
		HttpServletRequest request = (HttpServletRequest) req;
		Cookie[] allCookies = request.getCookies();
		Map<String, Cookie> result = new HashMap<String, Cookie>();
		if (allCookies != null) {
			for (Cookie c : allCookies) {
				result.put(c.getName(), c);
			}
		}
		return result;
	}

	public static boolean checkCookieExist(ServletRequest req, 
			List<String> list) {
		boolean isOk = true;
		Map<String, Cookie> cookies = getCookieMap(req);
		for (String s : list) {
			if (!cookies.containsKey(s)) {
				isOk = false;
				break;
			}
		}
		return isOk;
	}
	
	public static float getDistanceBetweenLocation(float lat1, float lng1,
			float lat2, float lng2) {
		double earthRadius = 3958.75;
		double dLat = Math.toRadians(lat2 - lat1);
		double dLng = Math.toRadians(lng2 - lng1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLng / 2)
				* Math.sin(dLng / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double dist = earthRadius * c;

		int meterConversion = 1609;

		return new Float(dist * meterConversion).floatValue();
	}
}
