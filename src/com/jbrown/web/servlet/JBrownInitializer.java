package com.jbrown.web.servlet;

import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import com.jbrown.cache.BrownDataCache;

public class JBrownInitializer extends HttpServlet {
	public void init() {
		ServletContext context = getServletContext();
		RequestI request = new Request(new RequestContext(context));
		BrownDataCache.initializeBrownDataCache(request);
		//Object stateNames = BrownDataCache.getInstance().getIndianStates();

		//System.out.println( stateNames );
	}
}
