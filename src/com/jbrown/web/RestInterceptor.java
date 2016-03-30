package com.jbrown.web;
 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jbrown.core.util.BrownAuthUtil;
import com.jbrown.core.util.BrownKeysI;
import com.jbrown.web.ws.BrownRequestI;
import com.jbrown.web.ws.ResponderI;
import com.jbrown.web.ws.responder.ErrorResponder;

public class RestInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler) 
         throws Exception {
    System.out.println("Pre Handle Called");
    return true;
  }

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
 
		System.out.println("afterCompletion Handle Called");
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("Post Handle Called");
		
		BrownRequestI brownRequest = (BrownRequestI) request
				.getAttribute(BrownKeysI.BROWN_REQUEST_OBJ_K);
		
		if (brownRequest != null && brownRequest.getErrors() != null
				&& brownRequest.getErrors().nErrors() > 0) {
			ResponderI error = new ErrorResponder();
			error.respond(brownRequest);
		}
		
		super.postHandle(request, response, handler, modelAndView);
	}
}
