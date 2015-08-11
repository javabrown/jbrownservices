package com.jbrown.web.ws;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.jbrown.core.exception.BorwnException;
import com.jbrown.core.util.BrownConstant;
import com.jbrown.core.util.StringUtil;
import com.jbrown.errors.BrownErrorsI;
import com.jbrown.web.BrownContextI;

public class BrownRequest implements BrownRequestI {
	private HttpServletRequest request;
	private HttpServletResponse response;
    private BrownErrorsI errors;
	private BrownContextI brownContext;
	
	private JSONObject json;
	private Map<String, String[]> requestMap;

	public BrownRequest(HttpServletRequest request,
			HttpServletResponse response, BrownErrorsI errors,
			BrownContextI brownContext) {
		//ServletContext context = ;
 
		this.request = request;
		this.response = response;		
		this.requestMap = new HashMap<String, String[]>();
		this.errors = errors;
		this.brownContext = brownContext;
		
		this.json = getJson(request, isPost());
		this.extractJsonAttributes();

		if (isPost() && this.isEmpty()) {
			errors.add("Error.IncorrectJSONData");
		}
	}

	boolean isPost() {
		return this.request.getMethod().equalsIgnoreCase(
				BrownConstant.HTTP_METHOD_POST_K);
	}
//	
//	public BrownRequest(HttpServletRequest request,
//			HttpServletResponse response, String[]... keyValueParams) {
//		this.request = request;
//		this.response = response;
//		this.requestMap = getGetAttributes(keyValueParams);
//	}

	@Override
	public HttpServletRequest getHttpServletRequest() {
		return this.request;
	}

	@Override
	public HttpServletResponse getHttpServletResponse() {
		return this.response;
	}

	@Override
	public BrownErrorsI getErrors(){
	  return errors;	
	}
	
	public Object getCache(String key) {
		return this.request.getAttribute(key);
	}

	public void putCache(String key, Object value) {
		this.request.setAttribute(key, value);
	}

	public String get(String key) {
		String[] values = this.requestMap.get(key);
		return (values != null) ? values[0] : null;
	}

	public void set(String key, String value) {
		this.requestMap.put(key, new String[] { value });
	}

	public String getRequestURL() {
		return this.request.getRequestURL().toString();
	}

	public String getQueryString() {
		return this.request.getQueryString();
	}

	public String getRemoteAddress() {
		return this.request.getRemoteAddr();
	}

	@Override
	public Object getSessionCache(String key) {
		HttpSession session = this.request.getSession(true);
		return (session == null) ? null : session.getAttribute(key);
	}

	@Override
	public void putSessionCache(String key, Object value) {
		HttpSession session = this.request.getSession(true);
		if (session != null) {
			session.setAttribute(key, value);
		}
	}
//
//	@Override
//	public Map<String, Object> getAttributes() {
//		return requestMap;
//	}

//	public Map<String, Object> getPostAttributes(String postedBody) {
//		if (postedBody == null) {
//			return null;
//		}
//
//		try {
//			Map<String, Object> lowerKeyMap = new HashMap<String, Object>();
//
//			JSONObject json = new JSONObject(postedBody);
//			Iterator<String> iterator = json.keys();
//
//			while (iterator.hasNext()) {
//				String key = iterator.next();
//				lowerKeyMap.put(key.toLowerCase(), (String) json.get(key));
//			}
//
//			return lowerKeyMap;
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	public Map<String, Object> getGetAttributes(String[]... keyValueParams) {
		Map<String, Object> lowerKeyMap = new HashMap<String, Object>();

		for (String[] param : keyValueParams) {
			lowerKeyMap.put(param[0], param[1]);
		}

		return lowerKeyMap;
	}

	public boolean isEmpty() {
		return json == null && (requestMap == null || requestMap.size() == 0);
	}

	@Deprecated
	public JSONObject getJson(HttpServletRequest request, boolean isPost) {
		JSONObject json = null;

		if (!isPost) {
			return null;
		}

		try {
			String posedBody = JsonUtil.getPostBody(request);
			json = new JSONObject(posedBody);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return json;
	}

	private Map<String, String> extractJsonAttributes() {
		Map<String, String> map = new HashMap<String, String>();
		if (this.json != null) {
			try {
				parse0(this.json, map);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		for(String key : map.keySet()){
			this.set(key, map.get(key));
		}
		return map;
	}

	private void parse0(JSONObject json, Map<String, String> attributes)
			throws JSONException {
		Iterator<String> keys = json.keys();

		while (keys.hasNext()) {
			String key = keys.next();
			Object jsonObject = json.get(key);

			if (isAutoBoxInstance(jsonObject)) {
				attributes.put(key, getValue(jsonObject));
			} else {
				parse0((JSONObject) jsonObject, attributes);
			}
		}

		for (String key : attributes.keySet()) {
			String val = attributes.get(key);
		}
	}

	private boolean isAutoBoxInstance(Object object) {
		if (object instanceof String || object instanceof Integer
				|| object instanceof Double || object instanceof Float
				|| object instanceof JSONArray) {
			return true;
		}

		if (object instanceof JSONObject) {
			return false;
		}

		throw new InternalError("Unknown dataType found in json:" + object);
	}

	private String getValue(Object object) throws JSONException {
		if (object instanceof String) {
			return (String) object;
		} else if (object instanceof Integer) {
			return ((Integer) object).toString();
		} else if (object instanceof Double) {
			return ((Double) object).toString();
		} else if (object instanceof Float) {
			return ((Float) object).toString();
		} else if (object instanceof JSONArray) {
			JSONArray array = (JSONArray) object;
			StringBuffer buff = new StringBuffer();
			for (int i = 0; i < array.length(); i++) {
				if (!isAutoBoxInstance(array.get(i))) {
					return null;
				}
				String val = getValue(array.get(i)).trim();
				if (!StringUtil.isEmpty(val)) {
					buff.append(val).append(",");
				}
			}
			return buff.toString();
		} else if (object instanceof JSONObject) {
			return null;
		}
		throw new InternalError("Unknown data found in json request:" + object);
	}

	@Override
	public BrownContextI getBrownContext() {
		return this.brownContext;
	}

	@Override
	public InputStream getStaticResorceStream(String lookupResourceKey) {
		//WebApplicationContext applicationContext = WebApplicationContextUtils
		//		.getWebApplicationContext(this.request.getSession()
		//				.getServletContext());
		
		ServletContext context = this.request.getSession().getServletContext();
		Map<String, String> staticDataFiles1 = 
			getBrownContext().getStaticData().getCountryData().getDataFiles();
		
		for(String resourceKey : staticDataFiles1.keySet()){
			if(lookupResourceKey.equalsIgnoreCase(resourceKey)){
				String resourceName = staticDataFiles1.get(lookupResourceKey);
				return context.getResourceAsStream(BrownConstant.TSV_DIR
						+ resourceName);
			}
		}
		
		throw new BorwnException("No resource found for the resource-key:"
				+ lookupResourceKey);
	}
	
	@Override
	public Map<String, String> getHeadersMap() {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> headerNames = request.getHeaderNames();

		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}

		return map;
	}

	@Override
	public JSONObject getJson() {
		return this.json;
	}

	@Override
	public String getDomain() {
		return this.request.getServerName().replaceAll(".*\\.(?=.*\\.)", "");
	}
}
