package com.jbrown.web;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jbrown.core.util.BrownAuthUtil;
import com.jbrown.core.util.BrownKeysI;
import com.jbrown.db.dao.DataStore;
import com.jbrown.errors.BrownErrors;
import com.jbrown.errors.BrownErrorsI;
import com.jbrown.web.ws.BrownRequest;
import com.jbrown.web.ws.BrownRequestI;

/**
 * 
 * @author rkhan
 *
 */
public class BrownWsFilter implements BrownFilterI {
	private BrownContext brownContext;
	
	public void setBrownContext(BrownContext brownContext){
		this.brownContext = brownContext;
	}
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request  = (HttpServletRequest)req;
		HttpServletResponse response  = (HttpServletResponse)res;
		
		this.preAction(request, response);
		
		try {
			chain.doFilter(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.postAction(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preAction(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("Filtered request");
		
		//Initialize error object
		BrownErrorsI errors = new BrownErrors();
		BrownRequestI brownRequest = new BrownRequest(request, response,
				errors, this.brownContext);
				
		request.setAttribute(BrownKeysI.BROWN_REQUEST_OBJ_K, brownRequest);
		
		if(!BrownAuthUtil.isValidUser(brownRequest)){
			errors.add("INVALID_USER", "Invalid Service User");
		}
	}

	@Override
	public void postAction(HttpServletRequest request,
			HttpServletResponse response) {
        BrownRequestI brownRequest = (BrownRequestI) 
            request.getAttribute(BrownKeysI.BROWN_REQUEST_OBJ_K);
        
       
        if(brownRequest != null){
        	DataStore dataStore = brownRequest.getDataStore();
        	
        	//Close Database connection if opened
        	if(dataStore.hasValidDbConnection()){
        		dataStore.closeDbConnection();
        	}
        }
	}
}
