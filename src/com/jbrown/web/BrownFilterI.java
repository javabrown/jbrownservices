package com.jbrown.web;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BrownFilterI extends Filter{
	void preAction(HttpServletRequest request, HttpServletResponse response );
	void postAction(HttpServletRequest request, HttpServletResponse response );
}
