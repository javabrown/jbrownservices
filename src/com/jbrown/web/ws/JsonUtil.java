package com.jbrown.web.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.util.JSONUtils;

/**
 * 
 * @author rkhan JSON Utility
 */
public class JsonUtil {
	@Deprecated
	/***
	 * Pending. Method uses needs to be reviewed.
	 */
	public static String toJSON(Object jsonString) {
		JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonString);
		return json.toString();
	}

	/***
	 * Java to JSon
	 * 
	 * @param map
	 * @return
	 */
	public static String toJSON(Map<String, Object> map) {
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(map);
		return jsonObject.toString();
	}

	public static JSONObject toJSONObject(Object jsonString) {
		JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonString);
		return json;
	}

	public static Object jsonToJava(JSONObject json) {
		return JSONSerializer.toJava(json);
	}

	public static boolean isValidJson(String jsonString) {
		return JSONUtils.mayBeJSON(jsonString);
	}
	
	
	public static String getPostBody(HttpServletRequest request)
			throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(
						inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}

		return stringBuilder.toString();
	}	
}
