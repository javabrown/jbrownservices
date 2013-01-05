package com.jbrown.web.ws.gen;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.jbrown.web.ws.JsonRequest;
import com.jbrown.web.ws.JsonResponseI;
import com.jbrown.web.ws.ReServices;
import com.jbrown.web.ws.ResponderI;

/**
 * 
 * @author rkhan
 * @category REG API
 * 
 */
public class RestServices extends ReServices implements WsInterface {
	@Override
	public ModelAndView register(@RequestBody String body,
			HttpServletRequest req, HttpServletResponse resp) {
		initialize(req, resp);
		ResponderI respoder = getResponderFactory().getResponder(
				"authResponder");
		JsonResponseI response = respoder.respond(new JsonRequest(body,
				respoder));

		return new ModelAndView(VIEW, "response", response.toJson());
	}

	@Override
	public ModelAndView userinfo(@PathVariable String email,
			HttpServletRequest req, HttpServletResponse res) {
		initialize(req, res);
		System.out.println("email=" + email);
		return new ModelAndView(VIEW, "response", email);
	}
}
