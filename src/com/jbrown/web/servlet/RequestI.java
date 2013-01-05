package com.jbrown.web.servlet;

import java.io.InputStream;

import com.jbrown.web.BrownContextI;

public interface RequestI {
	//RequestContext getRequestContext();
	BrownContextI getBrownContext();
	InputStream getStaticResorceStream(String lookupResourceKey);
}
