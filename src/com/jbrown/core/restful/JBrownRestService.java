//package com.jbrown.core.restful;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//@RequestMapping("/movie")
//public class JBrownRestService {
//	// DI via Spring
//	String message;
//
//	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
//	public @ResponseBody
//	String getMovie(@PathVariable String name, ModelMap model) {
//
//		model.addAttribute("movie", name);
//		model.addAttribute("message", this.message);
//
//		// return to jsp page, configured in mvc-dispatcher-servlet.xml, view
//		// resolver
//		return "list";
//
//	}
//
//	@RequestMapping
//	public @ResponseBody
//	String mydefault() {
//		return "Hi Book!";
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}
// }
