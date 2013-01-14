package com.jbrown.web.ws;

import java.util.Map;

public interface JsonRequestI {
	ResponderI getResponder();
	Map<String, Object> getAttributes();
}
