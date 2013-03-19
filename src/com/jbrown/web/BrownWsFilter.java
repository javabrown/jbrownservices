package com.jbrown.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jbrown.core.util.BrownAuthUtil;
import com.jbrown.core.util.BrownKeysI;
import com.jbrown.core.util.BrownUtil;
import com.jbrown.errors.BrownErrors;
import com.jbrown.errors.BrownErrorsI;
import com.jbrown.web.ws.BrownRequest;
import com.jbrown.web.ws.BrownRequestI;

/**
 * 
 * @author rkhan
 *
 */
public class BrownWsFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("Filtered request");
		HttpServletRequest request  = (HttpServletRequest)req;
		HttpServletResponse response  = (HttpServletResponse)res;
		
		//Initialize error object
		BrownErrorsI errors = new BrownErrors();
		BrownRequestI brownRequest = new BrownRequest(request, response, errors);
		
		request.setAttribute(BrownKeysI.BROWN_REQUEST_OBJ_K, brownRequest);
		
		if(!BrownAuthUtil.isValidUser(brownRequest)){
			errors.add("INVALID_USER", "Invalid Service User");
		}
		
		try {
			chain.doFilter(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
