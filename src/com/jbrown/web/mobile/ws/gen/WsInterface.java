
package com.jbrown.web.mobile.ws.gen;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

/**
 * @author rkhan
 * @category API
 * 
 * This file was auto-generated from api-endpoints.xml during the project build.
 * Please do not edit directly.
 */
 
	
@Controller
@RequestMapping("/ws")
public interface WsInterface{
			
     @RequestMapping(value = "/v1/user/register", method = RequestMethod.POST)    
     public ModelAndView register
      (@RequestBody String body, HttpServletRequest req, HttpServletResponse res);
      
     @RequestMapping(value = "/v1/user/info/{userName}/{email}", method = RequestMethod.GET)
     public ModelAndView getUserInfo(
	      @PathVariable String userName,
	      @PathVariable String email,HttpServletRequest req, HttpServletResponse res, ModelMap model);
     @RequestMapping(value = "/v1/user/auth", method = RequestMethod.POST)    
     public ModelAndView auth
      (@RequestBody String body, HttpServletRequest req, HttpServletResponse res);
      
     @RequestMapping(value = "/v1/user/logout", method = RequestMethod.GET)
     public ModelAndView logout(HttpServletRequest req, HttpServletResponse res, ModelMap model);
     @RequestMapping(value = "/v1/countryinfo", method = RequestMethod.GET)
     public ModelAndView getIsoCountries(HttpServletRequest req, HttpServletResponse res, ModelMap model);
     @RequestMapping(value = "/v1/countryinfo/{countryName}", method = RequestMethod.GET)
     public ModelAndView getCountryInfo(
	      @PathVariable String countryName,HttpServletRequest req, HttpServletResponse res, ModelMap model);
     @RequestMapping(value = "/v1/countryinfo/{countryName}/{stateName}", method = RequestMethod.GET)
     public ModelAndView getStateInfo(
	      @PathVariable String countryName,
	      @PathVariable String stateName,HttpServletRequest req, HttpServletResponse res, ModelMap model);
     @RequestMapping(value = "/v1/countryinfo/{countryName}/{stateName}/{cityName}", method = RequestMethod.GET)
     public ModelAndView getCityInfo(
	      @PathVariable String countryName,
	      @PathVariable String stateName,
	      @PathVariable String cityName,HttpServletRequest req, HttpServletResponse res, ModelMap model);
     @RequestMapping(value = "/v1/airport", method = RequestMethod.GET)
     public ModelAndView getAllAirport(HttpServletRequest req, HttpServletResponse res, ModelMap model);
     @RequestMapping(value = "/v1/airport/{iata}", method = RequestMethod.GET)
     public ModelAndView getAirporByIata(
	      @PathVariable String iata,HttpServletRequest req, HttpServletResponse res, ModelMap model);
     @RequestMapping(value = "/v1/distance/location-a/{countryName1}/location-b/{countryName1}", method = RequestMethod.GET)
     public ModelAndView getDistance(
	      @PathVariable String countryName1,
	      @PathVariable String countryName2,HttpServletRequest req, HttpServletResponse res, ModelMap model);
     @RequestMapping(value = "/v2/ui/linechart", method = RequestMethod.GET)
     public ModelAndView getLineChart(HttpServletRequest req, HttpServletResponse res, ModelMap model);
     @RequestMapping(value = "/v1/util/encode", method = RequestMethod.POST)    
     public ModelAndView encode
      (@RequestBody String body, HttpServletRequest req, HttpServletResponse res);
      
     @RequestMapping(value = "/v1/media/search-images/{pattern}/{size}", method = RequestMethod.GET)
     public ModelAndView getPublicPhotos(
	      @PathVariable String pattern,
	      @PathVariable String size,HttpServletRequest req, HttpServletResponse res, ModelMap model);
     @RequestMapping(value = "/v1/media/all-image-size", method = RequestMethod.GET)
     public ModelAndView getAllPhotoSizeConstants(HttpServletRequest req, HttpServletResponse res, ModelMap model);
     @RequestMapping(value = "/v1/media/text-to-audio", method = RequestMethod.GET)
     public ModelAndView textToAudio(
	      @PathVariable String textToSpeech,HttpServletRequest req, HttpServletResponse res, ModelMap model);
     @RequestMapping(value = "/v1/data/cache/set", method = RequestMethod.POST)    
     public ModelAndView setCache
      (@RequestBody String body, HttpServletRequest req, HttpServletResponse res);
      
     @RequestMapping(value = "/v1/data/cache/get", method = RequestMethod.POST)    
     public ModelAndView getCache
      (@RequestBody String body, HttpServletRequest req, HttpServletResponse res);
      
     @RequestMapping(value = "/v1/data/quiz/question/all", method = RequestMethod.GET)
     public ModelAndView getAllQuestions(HttpServletRequest req, HttpServletResponse res, ModelMap model);
}
			