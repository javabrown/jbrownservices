package com.jbrown.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

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

	public static boolean checkCookieExist(ServletRequest req, List<String> list) {
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

	public static String httpGet(String urlStr) throws IOException {
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		if (conn.getResponseCode() != 200) {
			throw new IOException(conn.getResponseMessage());
		}

		// Buffer the result into a string
		BufferedReader rd = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));

		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();

		conn.disconnect();
		return sb.toString();
	}

	public static String httpPost(String urlStr, String[] paramName,
			String[] paramVal) throws Exception {
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setAllowUserInteraction(false);
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");

		// Create the form content
		OutputStream out = conn.getOutputStream();
		Writer writer = new OutputStreamWriter(out, "UTF-8");
		for (int i = 0; i < paramName.length; i++) {
			writer.write(paramName[i]);
			writer.write("=");
			writer.write(URLEncoder.encode(paramVal[i], "UTF-8"));
			writer.write("&");
		}
		writer.close();
		out.close();

		if (conn.getResponseCode() != 200) {
			throw new IOException(conn.getResponseMessage());
		}

		// Buffer the result into a string
		BufferedReader rd = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();

		conn.disconnect();
		return sb.toString();
	}
	
	public void compileClass() throws Exception{
		// Prepare source somehow.
		String source = "package test; public class Test { static { System.out.println(\"hello\"); } public Test() { System.out.println(\"world\"); } }";

		// Save source in .java file.
		File root = new File("/java"); // On Windows running on C:\, this is C:\java.
		File sourceFile = new File(root, "test/Test.java");
		sourceFile.getParentFile().mkdirs();
		new FileWriter(sourceFile).append(source).close();

		// Compile source file.
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		compiler.run(null, null, null, sourceFile.getPath());

		// Load and instantiate compiled class.
		URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
		Class<?> cls = Class.forName("test.Test", true, classLoader); // Should print "hello".
		Object instance = cls.newInstance(); // Should print "world".
		System.out.println(instance); // Should print "test.Test@hashcode".
	}
}
