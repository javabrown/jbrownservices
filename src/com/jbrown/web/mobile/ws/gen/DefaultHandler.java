package com.jbrown.web.mobile.ws.gen;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultHandler {
	@RequestMapping("/")
	public void handle(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Default handler");
	}
}
