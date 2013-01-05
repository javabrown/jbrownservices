package com.jbrown.core.test;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

public interface HelloJaxWsI {
  @WebMethod String getHelloWorldAsString(); 
}
