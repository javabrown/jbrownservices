package com.core.ext.oauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.jbrown.core.util.BrownConstant;
import com.jbrown.core.util.StringUtil;

import static com.jbrown.core.util.BrownConstant.*;

public class FBConnection {
	private String _accessToken;
	private FacebookUserInfo _fbUserInfo;

	public FBConnection(String accessToken) {
		_accessToken = accessToken;
		_fbUserInfo = fetchFbUserInfo(_accessToken);
	}
	
	public String getAccessToken(){
		return _accessToken;
	}
	
	public FacebookUserInfo getFbUserInfo(){
		return _fbUserInfo;
	}
	
	private FacebookUserInfo fetchFbUserInfo(String accessToken) {
		String jsonResponse = "";
		JSONObject jsonObject = null;
		FacebookUserInfo facebookUserInfo = null;
		
		if (!StringUtil.isEmpty(accessToken)) {
			URL fbGraphURL;

			try {
				fbGraphURL = new URL(BrownConstant.FB_TOKEN_ACCESS_URL
						+ accessToken);
			} catch (MalformedURLException e) {
				e.printStackTrace();
				throw new RuntimeException("Invalid code received " + e);
			}

			URLConnection fbConnection;
			StringBuffer b = null;
			try {
				fbConnection = fbGraphURL.openConnection();
				BufferedReader in;
				in = new BufferedReader(new InputStreamReader(
						fbConnection.getInputStream()));
				String inputLine;
				b = new StringBuffer();
				while ((inputLine = in.readLine()) != null)
					b.append(inputLine + "\n");
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
				//throw new RuntimeException("Unable to connect with Facebook "
				//		+ e);
				System.out.println("Unable to connect with Facebook" + e);
				return null;
			}

			jsonResponse = b.toString();
			jsonObject = JSONObject.fromObject(jsonResponse);

			if (jsonObject != null) {
				facebookUserInfo = new FacebookUserInfo(
						jsonObject.getString(FB_ID),
						jsonObject.getString(FB_EMAIL),
						jsonObject.getString(FB_FIRST_NAME),
						jsonObject.getString(FB_LAST_NAME),
						jsonObject.getString(FB_GENDER),
						jsonObject.getString(FB_NAME), "",
						jsonObject.getString(FB_LOCALE));
			}
		}
		
		return facebookUserInfo;
	}

	@Override
	public String toString() {
		return "FBConnection [_accessToken=" + _accessToken + ", _fbUserInfo="
				+ _fbUserInfo + "]";
	}
}