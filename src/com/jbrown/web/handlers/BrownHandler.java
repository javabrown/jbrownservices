package com.jbrown.web.handlers;

import org.springframework.web.servlet.mvc.AbstractController;

public abstract class BrownHandler extends AbstractController {
	private String _viewName;

	public void setViewName(String viewName) {
		_viewName = viewName;
	}

	public String getView() {
		return _viewName;
	}
}
