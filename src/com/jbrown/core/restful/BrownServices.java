package com.jbrown.core.restful;

import java.util.Arrays;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jbrown.cache.BrownDataCache;

@Controller
@RequestMapping("/ws")
public class BrownServices {
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String getMovie(@PathVariable String name, ModelMap model) {
		model.addAttribute("response", name);
		return "list";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getDefaultMovie(ModelMap model) {
		model.addAttribute("response", "this is default ws");
		return "list";
	}

	@RequestMapping(value = "/countryinfo/{countryName}", method = RequestMethod.GET)
	public String getAllCountry(@PathVariable String countryName, ModelMap model) {
		String[] states = BrownDataCache.getInstance().getStatesName(
				countryName);

		JSONObject json = new JSONObject();
		json.put(countryName, Arrays.toString(states));
		model.addAttribute("response", Arrays.toString(states));

		return "list";
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

	@RequestMapping(value = "/countryinfo/{countryName}/{stateName}/{cityName}", method = RequestMethod.GET)
	public String getAllCountry(@PathVariable String countryName,
			@PathVariable String stateName, @PathVariable String cityName,
			ModelMap model) {
		String[] postOffices = BrownDataCache.getInstance().getPostalOffices(
				countryName, stateName, cityName);

		JSONObject json = new JSONObject();
		json.put(cityName, postOffices);
		model.addAttribute("response", json.toString());

		return "list";
	}
}