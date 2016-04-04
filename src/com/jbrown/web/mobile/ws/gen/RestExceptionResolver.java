package com.jbrown.web.mobile.ws.gen;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpStatus;

import com.jbrown.core.util.BrownKeysI;
import com.jbrown.web.ws.BrownRequestI;
import com.jbrown.web.ws.BrownServices;
import com.jbrown.web.ws.ResponderI;
import com.jbrown.web.ws.responder.ResponderK;

@ControllerAdvice
class RestExceptionResolver {
  private BrownServices _services;
  
  public RestExceptionResolver(BrownServices services){
    _services = services;
  }
  
  @ResponseStatus(value=HttpStatus.NOT_FOUND)
  @ExceptionHandler(Exception.class)
  public ModelAndView resourceNotFound(Exception ex, HttpServletRequest req,
      HttpServletResponse resp) {
    System.out.println("resourceNotFound");

    ResponderI respoder = _services.getResponderFactory().getResponder(
        ResponderK.INAVLID_REQUEST_RESPONDER);
    BrownRequestI request = _services.getBrownRequest(req);
    respoder.respond(request);
    
    return BrownKeysI.EMPTY_VIEW;
  }
}
