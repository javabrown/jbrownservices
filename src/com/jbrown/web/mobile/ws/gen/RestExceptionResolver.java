package com.jbrown.web.mobile.ws.gen;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 


import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
import org.springframework.http.HttpStatus;

import com.jbrown.core.util.BrownKeysI;
import com.jbrown.errors.BrownMessage;
import com.jbrown.web.ws.BrownRequestI;
import com.jbrown.web.ws.BrownServices;
import com.jbrown.web.ws.ResponderI;
import com.jbrown.web.ws.responder.ResponderK;


@ControllerAdvice
class RestExceptionResolver  {
  private BrownServices _services;
  
  public RestExceptionResolver(BrownServices services) {
    _services = services;
  }
  
  @ResponseStatus(value=HttpStatus.NOT_FOUND)
  @ExceptionHandler(Exception.class)
  public ModelAndView resourceNotFound(Exception ex, HttpServletRequest req,
      HttpServletResponse resp) {

    if(ex instanceof HttpRequestMethodNotSupportedException){
      HttpRequestMethodNotSupportedException notFoundEx = (HttpRequestMethodNotSupportedException) ex;
      ResponderI respoder = _services.getResponderFactory().getResponder(
          ResponderK.INAVLID_REQUEST_RESPONDER);
      
      BrownRequestI request = _services.getBrownRequest(req);
      
      HttpServletResponse httpResponse = request.getHttpServletResponse();
      
      
      httpResponse.setStatus(HttpStatus.NOT_FOUND.value(), 
          HttpStatus.NOT_FOUND.getReasonPhrase());
      
      String docMsg = String.format(
          "Visit at [%s] for the list of services", getWsUrl(req));
  
      String errMsg = String.format("%s. Allowed HTTP methods are %s", 
          ex.getMessage(), java.util.Arrays.toString(notFoundEx.getSupportedMethods()));
      
      request.getErrors().addError(
          new BrownMessage(HttpStatus.NOT_FOUND.name(), errMsg, docMsg));
      
      respoder.respond(request);
    }
    
    
    return BrownKeysI.EMPTY_VIEW;
  }
       
  public String getWsUrl(HttpServletRequest request) {
    String uri = String.format("%s://%s:%s/%s", request.getScheme(),
        request.getServerName(), request.getServerPort(),
        request.getRequestURI());

    if (request.getServerName().startsWith("localhost")) {
      uri = String.format("%s://%s:%s/jbrownservices/", request.getScheme(),
          request.getServerName(), request.getServerPort());
    }

    return uri;
  }
}
