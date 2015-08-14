package com.jbrown.web.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.jbrown.core.util.BrownKeysI;
import com.jbrown.web.ws.BrownRequestI;

public class TestHandler extends AbstractController {
	private String _viewName;
	
	public void setViewName(String viewName){
		_viewName = viewName;
	}
	
	public String getView(){
		return _viewName;
	}
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BrownRequestI req = (BrownRequestI) request
				.getAttribute(BrownKeysI.BROWN_REQUEST_OBJ_K);

		ModelAndView model = new ModelAndView(getView());
		model.addObject("firstName", "Raja");
		model.addObject("lastName", "Khan");

		return model;
	}

}
