package com.jbrown.web.mobile.ws.gen;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.jbrown.core.util.BrownKeysI;
import com.jbrown.web.ws.BrownRequest;
import com.jbrown.web.ws.BrownRequestI;
import com.jbrown.web.ws.BrownServices;
import com.jbrown.web.ws.ResponderI;
import com.jbrown.web.ws.responder.ResponderK;

/**
 * 
 * @author rkhan
 * @category JBrown API
 * 
 */

@Controller
 
public class RestServices extends BrownServices implements WsInterface,
		BrownKeysI {
	
	@Override
	public ModelAndView auth(String body, HttpServletRequest req,
			HttpServletResponse res) {
		ResponderI respoder = getResponderFactory().getResponder(
				ResponderK.AUTH_RESPONDER);
		BrownRequestI request = super.getBrownRequest(req);//new BrownRequest(req, res);
		request.getErrors().clear();
		respoder.respond(request);
		return EMPTY_VIEW;
	}

	@Override
	public ModelAndView getCountryInfo(@PathVariable String countryName, 
			HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		initialize(req, res);
		ResponderI respoder = getResponderFactory().getResponder(
				ResponderK.GEO_RESPONDER);

		BrownRequestI request = super.getBrownRequest(req);//new BrownRequest(req, res);
		request.set(ACTION_K, "getCountryInfo");
		request.set(COUNTRY_NAME_K, countryName);
		
		
		respoder.respond(request);
		
		return EMPTY_VIEW;
	}
	

	@Override
	public ModelAndView getStateInfo(@PathVariable String countryName,
			@PathVariable String stateName, HttpServletRequest req,
			HttpServletResponse res, ModelMap model) {
		ResponderI respoder = getResponderFactory().getResponder(
				ResponderK.GEO_RESPONDER);
	
		BrownRequestI request = super.getBrownRequest(req);//new BrownRequest(req, res);
		request.set("action", "getStateInfo");
		request.set("countryName", countryName);
		request.set("stateName", stateName);
		
		respoder.respond(request);
		return EMPTY_VIEW;
	}

	@Override
	public ModelAndView getCityInfo(@PathVariable String countryName,
			@PathVariable String stateName, @PathVariable String cityName,
			HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		ResponderI respoder = getResponderFactory().getResponder(
				ResponderK.GEO_RESPONDER);

		BrownRequestI request = super.getBrownRequest(req);//new BrownRequest(req, res);
		request.set("action", "getCityInfo");
		request.set("countryName", countryName);
		request.set("stateName", stateName);
		request.set("cityName", cityName);
		
		respoder.respond(request);
		
		return EMPTY_VIEW;
	}
//
//
//	@Override
//	public ModelAndView getImageForText(@PathVariable String text,
//			@PathVariable int width, @PathVariable int height,
//			HttpServletRequest req, HttpServletResponse res, ModelMap model) {
//		SwingUtil.texttoImage(text, width, height, res);
//		return null;
//	}
}
