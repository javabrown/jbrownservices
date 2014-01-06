package com.jbrown.web.ws;

import com.jbrown.ext.capsule.CapsuleType;

public enum WsActionType {

	IMAGE_SIZE_CONSTANTS ("GET_IMAGE_SIZE_CONSTANTS", "MEDIA"),
	PUBLIC_IMAGES ("PUBLIC_IMAGES", "MEDIA");
	
	private final String actionName;
	private final String serviceCategory;
	
	private WsActionType(String actionName, String serviceCategory){
		this.actionName = actionName;
		this.serviceCategory = serviceCategory;
	}
	
	public static WsActionType getInstance(String actionName) {
		for (WsActionType c : WsActionType.values()) {
			if (actionName.equalsIgnoreCase(c.actionName))
				return c;
		}

		return null;
	}

	public boolean typeOf(WsActionType wsActionType) {
		return getInstance(wsActionType.actionName).equals(wsActionType);
	}
	
	public String getActionName(){
		return this.actionName;
	}
}
