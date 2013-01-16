
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
     @RequestMapping(value = "/v1.1/util/text2image/{text}/{width}/{height}", method = RequestMethod.GET)
     public ModelAndView getImageForText(
	      @PathVariable String text,
	      @PathVariable int width,
	      @PathVariable int height,HttpServletRequest req, HttpServletResponse res, ModelMap model);
}
			