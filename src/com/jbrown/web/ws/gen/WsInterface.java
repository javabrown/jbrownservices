package com.jbrown.web.ws.gen;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author rkhan
 * @category API
 * 
 *           This file was auto-generated from api-endpoints.xml during the
 *           project build. Please do not edit directly.
 */

@Controller
@RequestMapping("/RE")
public interface WsInterface {

	@RequestMapping(value = "/v1/user/register", method = RequestMethod.POST)
	public ModelAndView register(@RequestBody String body,
			HttpServletRequest req, HttpServletResponse res);

	@RequestMapping(value = "/v1/user/info/{email}", method = RequestMethod.GET)
	public ModelAndView userinfo(@PathVariable String email,
			HttpServletRequest req, HttpServletResponse res);
}
