package com.jbrown.web.servlet;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jbrown.core.exception.BorwnException;
import com.jbrown.core.util.BrownConstant;
import com.jbrown.web.BrownContext;
import com.jbrown.web.BrownContextI;
import com.jbrown.web.resources.StaticData;

public class Request implements RequestI {
	private RequestContext _requestContext;
	private ApplicationContext _applicationContext;

	public Request(RequestContext request) {
		_requestContext = request;
		_applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(_requestContext.getServletContext());
		System.out.println(getBrownContext().getStaticData().getCountryData()
				.getDataFiles().get("INDIA"));
	}

//	@Override
//	public RequestContext getRequestContext() {
//		return _requestContext;
//	}

	public BrownContextI getBrownContext() {
		BrownContextI context = (BrownContextI) _applicationContext
				.getBean(BrownContext.class);
		return context;
	}
	
	public InputStream getStaticResorceStream(String lookupResourceKey) {
		ServletContext context = _requestContext.getServletContext();
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

}
