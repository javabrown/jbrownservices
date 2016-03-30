package com.jbrown.providers;

import com.jbrown.providers.ext.flickr.FlickerProvider;
import com.jbrown.providers.ext.flickr.ImageSize;
import com.jbrown.web.ws.BrownRequestI;

public interface ProviderActionI {
   
}

class Trigger {
	public Trigger(BrownRequestI request){
		Action a = Action.SEARCH_IMAGES;
	}
}

enum Action0{
	
}

enum Action {
	SEARCH_IMAGES("searchImages") {
		@Override
		public BrownRequestI getRequest() {
			// TODO Auto-generated method stub
			return null;
		}
	};

	public String code;

	private Action(String code) {
		this.code = code;
	}

	public static Action getInstance(String code) {
		for (Action action : Action.values()) {
			if (code == action.code) {
				return action;
			}
		}

		return null;
	}

	public boolean typeOf(Action action) {
		return getInstance(this.code).equals(action);
	}
	
	public abstract BrownRequestI getRequest();
}