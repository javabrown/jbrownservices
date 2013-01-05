package com.jbrown.web.ws;

import java.util.Map;

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
}
