package com.jbrown.web.mobile.ws.gen;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jbrown.cache.BrownDataCache;
import com.jbrown.web.ws.JsonRequest;
import com.jbrown.web.ws.JsonRequestI;
import com.jbrown.web.ws.JsonResponseI;
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
 
public class RestServices extends BrownServices implements WsInterface {
	
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
	public ModelAndView getUserInfo(String userName, String email,
			HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		initialize(req, res);
		ResponderI respoder = getResponderFactory().getResponder(
				"authResponder");
		JsonResponseI response = respoder.respond(new JsonRequest(new String(
				"Raja Khann"), respoder));

		return new ModelAndView(VIEW, "response", response.toJson());
	}

	@Override
	public ModelAndView getCountryInfo(@PathVariable String countryName,
			HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		initialize(req, res);
		ResponderI respoder = getResponderFactory().getResponder(
				ResponderK.GEO_RESPONDER);
		
		String[][] internalRequest = {
			{"action", "getCountryInfo"},
			{"countryName", countryName},
		};
		
		JsonRequestI request = new JsonRequest(respoder, internalRequest);
		
		JsonResponseI response = respoder.respond(request);
		
		return new ModelAndView(VIEW, "response", response.toJson());
	}
	
	@RequestMapping(value = "/countryinfo/{countryName}/{stateName}", method = RequestMethod.GET)
	public String getAllCountry(@PathVariable String countryName,
			@PathVariable String stateName, ModelMap model) {
		String[] cities = BrownDataCache.getInstance().getCities(countryName,
				stateName);
 
		
		JSONObject json = new JSONObject();
		json.put(stateName, Arrays.toString(cities));
		model.addAttribute("response", Arrays.toString(cities));

		return "list";
	}

	@Override
	public ModelAndView getStateInfo(@PathVariable String countryName,
			@PathVariable String stateName, HttpServletRequest req,
			HttpServletResponse res, ModelMap model) {
		ResponderI respoder = getResponderFactory().getResponder(
				ResponderK.GEO_RESPONDER);
		
		String[][] internalRequest = {
				{"action", "getStateInfo"},
				{"countryName", countryName},
				{"stateName", stateName},
			};
		
		JsonRequestI request = new JsonRequest(respoder, internalRequest);
		
		JsonResponseI response = respoder.respond(request);
		
		return new ModelAndView(VIEW, "response", response.toJson());
	}

	@Override
	public ModelAndView getCityInfo(@PathVariable String countryName,
			@PathVariable String stateName, @PathVariable String cityName,
			HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		ResponderI respoder = getResponderFactory().getResponder(
				ResponderK.GEO_RESPONDER);
		
		String[][] internalRequest = {
				{"action", "getCityInfo"},
				{"countryName", countryName},
				{"stateName", stateName},
				{"cityName", cityName},
			};
		
		JsonRequestI request = new JsonRequest(respoder, internalRequest);
		
		JsonResponseI response = respoder.respond(request);
		
		return new ModelAndView(VIEW, "response", response.toJson());
	}
}
