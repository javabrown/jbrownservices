package com.jbrown.web.servlet;

import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import com.jbrown.cache.BrownDataCache;

public class JBrownInitializer extends HttpServlet {
	public void init() {
		// System.out.println("INIT xxxxxxxxxxxxx");
		ServletContext context = getServletContext();
		RequestI request = new Request(new RequestContext(context));
		BrownDataCache.initializeBrownDataCache(request);
		String[] stateNames = BrownDataCache.getInstance().getStatesName("IN");

		System.out.println(Arrays.toString(stateNames));
	}
}
