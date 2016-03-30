package com.jbrown.web.ws;

import org.springframework.web.servlet.DispatcherServlet;

public class BrownURLDispatcher extends DispatcherServlet {
  private static final long serialVersionUID = 1L;
  
  public BrownURLDispatcher(){
    setThrowExceptionIfNoHandlerFound(true);
  }
  
  
}
