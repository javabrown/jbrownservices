package com.jbrown.web.mobile.ws.gen;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jbrown.core.util.BrownKeysI;
import com.jbrown.web.ws.BrownRequestI;
import com.jbrown.web.ws.BrownServices;
import com.jbrown.web.ws.ResponderI;
import com.jbrown.web.ws.WsActionType;
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
  public ModelAndView register(String body, HttpServletRequest req,
      HttpServletResponse res) {
    ResponderI respoder = getResponderFactory().getResponder(
        ResponderK.REGISTER_RESPONDER);
    BrownRequestI request = super.getBrownRequest(req);

    // clear all the errors if any. (for auth & register only)
    request.getErrors().clear();

    request.set(ACTION_K, ACTION_REGISTER_K);

    respoder.respond(request);
    return EMPTY_VIEW;
  }

  @Override
  public ModelAndView getUserInfo(String userName, @PathVariable String email,
      HttpServletRequest req, HttpServletResponse res, ModelMap model) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ModelAndView auth(String body, HttpServletRequest req, HttpServletResponse res) {
    ResponderI respoder = getResponderFactory().getResponder(
        ResponderK.LOGIN_RESPONDER);
    BrownRequestI request = super.getBrownRequest(req);

    // clear all the errors if any. (for auth & register only)
    request.getErrors().clear();

    request.set(ACTION_K, ACTION_AUTH_K);

    request.getErrors().clear();
    respoder.respond(request);
    return EMPTY_VIEW;
  }

  @Override
  public ModelAndView logout(HttpServletRequest req, HttpServletResponse res,
      ModelMap model) {
    ResponderI respoder = getResponderFactory().getResponder(
        ResponderK.LOGOUT_RESPONDER);
    BrownRequestI request = super.getBrownRequest(req);
    request.getErrors().clear();
    respoder.respond(request);
    return EMPTY_VIEW;
  }
  
  @RequestMapping(value = "/v1/countryinfo", method = RequestMethod.GET)
  public ModelAndView getIsoCountries(HttpServletRequest req,
      HttpServletResponse res, ModelMap model) {
    ResponderI respoder = getResponderFactory().getResponder(
        ResponderK.GEO_RESPONDER);

    BrownRequestI request = super.getBrownRequest(req);
    request.set(ACTION_K, "getIsoCountries");

    respoder.respond(request);

    return EMPTY_VIEW;
  }

  @Override
  public ModelAndView getCountryInfo(@PathVariable String countryName,
      HttpServletRequest req, HttpServletResponse res, ModelMap model) {
    // initialize(req, res);
    ResponderI respoder = getResponderFactory().getResponder(
        ResponderK.GEO_RESPONDER);

    BrownRequestI request = super.getBrownRequest(req);
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

    BrownRequestI request = super.getBrownRequest(req);
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

    BrownRequestI request = super.getBrownRequest(req);// new BrownRequest(req,
                                                       // res);
    request.set("action", "getCityInfo");
    request.set("countryName", countryName);
    request.set("stateName", stateName);
    request.set("cityName", cityName);

    respoder.respond(request);

    return EMPTY_VIEW;
  }

  //
  //
  // @Override
  // public ModelAndView getImageForText(@PathVariable String text,
  // @PathVariable int width, @PathVariable int height,
  // HttpServletRequest req, HttpServletResponse res, ModelMap model) {
  // SwingUtil.texttoImage(text, width, height, res);
  // return null;
  // }

  @Override
  public ModelAndView getLineChart(HttpServletRequest req,
      HttpServletResponse res, ModelMap model) {
    // TODO Auto-generated method stub
    return new ModelAndView("google-chart");
  }

  @Override
  public ModelAndView getDistance(@PathVariable String countryName1,
      @PathVariable String countryName2, HttpServletRequest req,
      HttpServletResponse res, ModelMap model) {
    ResponderI respoder = getResponderFactory().getResponder(
        ResponderK.GEO_RESPONDER);

    BrownRequestI request = super.getBrownRequest(req);
    request.set(ACTION_K, GEO_GET_DISTANCE_K);
    request.set("location1", countryName1);
    request.set("location2", countryName2);

    respoder.respond(request);
    return EMPTY_VIEW;
  }

  @Override
  public ModelAndView encode(@RequestBody String body, HttpServletRequest req,
      HttpServletResponse res) {
    ResponderI respoder = getResponderFactory().getResponder(
        ResponderK.UTIL_RESPONDER);
    BrownRequestI request = super.getBrownRequest(req);
    request.set(ACTION_K, ACTION_ENCODE_K);

    respoder.respond(request);
    return EMPTY_VIEW;
  }

  @Override
  public ModelAndView getPublicPhotos(@PathVariable String pattern,
      @PathVariable String size, HttpServletRequest req,
      HttpServletResponse res, ModelMap model) {

    ResponderI respoder = getResponderFactory().getResponder(
        ResponderK.MEDIA_RESPONDER);
    BrownRequestI request = super.getBrownRequest(req);
    request.set(ACTION_K, WsActionType.PUBLIC_IMAGES.getActionName());

    respoder.respond(request);
    return EMPTY_VIEW;
  }

  @Override
  public ModelAndView getAllPhotoSizeConstants(HttpServletRequest req,
      HttpServletResponse res, ModelMap model) {
    ResponderI respoder = getResponderFactory().getResponder(
        ResponderK.MEDIA_RESPONDER);
    BrownRequestI request = super.getBrownRequest(req);
    request.set(ACTION_K, WsActionType.PUBLIC_IMAGES.getActionName());

    respoder.respond(request);
    return EMPTY_VIEW;
  }

  @Override
  public ModelAndView getAllAirport(HttpServletRequest req,
      HttpServletResponse res, ModelMap model) {
    BrownRequestI request = super.getBrownRequest(req);
    ResponderI respoder = getResponderFactory().getResponder(
        ResponderK.AIRPORT_RESPONDER);

    respoder.respond(request);

    return EMPTY_VIEW;
  }

  @Override
  public ModelAndView getAirporByIata(@PathVariable String iata,
      HttpServletRequest req, HttpServletResponse res, ModelMap model) {
    BrownRequestI request = super.getBrownRequest(req);
    ResponderI respoder = getResponderFactory().getResponder(
        ResponderK.AIRPORT_IATA_RESPONDER);
    request.set(IATA, iata);
    respoder.respond(request);

    return EMPTY_VIEW;
  }

  public ModelAndView textToAudio(@PathVariable String textToSpeech,
      HttpServletRequest req, HttpServletResponse res, ModelMap model) {
    BrownRequestI request = super.getBrownRequest(req);
    ResponderI respoder = getResponderFactory().getResponder(
        ResponderK.MEDIA_RESPONDER);
    request.set(TEXT_TO_SPEECH, textToSpeech);
    respoder.respond(request);
    // PENDING.....
    return EMPTY_VIEW;
  }

  public ModelAndView setCache(String body, HttpServletRequest req,
      HttpServletResponse res) {
    BrownRequestI request = super.getBrownRequest(req);
    ResponderI respoder = getResponderFactory().getResponder(
        ResponderK.CACHE_PUSH_RESPONDER);
    respoder.respond(request);
    //MailUtil.sendEmail();
    return EMPTY_VIEW;
  }

  public ModelAndView getCache(String body, HttpServletRequest req,
      HttpServletResponse res) {
    BrownRequestI request = super.getBrownRequest(req);
    
    ResponderI respoder = getResponderFactory().getResponder(
        ResponderK.CACHE_GET_RESPONDER);
    respoder.respond(request);

    return EMPTY_VIEW;
  }

  @Override
  public ModelAndView getAllQuestions(HttpServletRequest req,
      HttpServletResponse res, ModelMap model) {
    BrownRequestI request = super.getBrownRequest(req);
    
    ResponderI respoder = getResponderFactory().getResponder(
        ResponderK.QUIZ_ALL_QUESTIONS_RESPONDER);
    respoder.respond(request);

    return EMPTY_VIEW;
  }
}
