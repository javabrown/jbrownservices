
/**
 * IOExceptionException0.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:35 LKT)
 */

package com.core.service;

public class IOExceptionException0 extends java.lang.Exception{
    
    private com.core.service.StockQuoteServiceStub.IOExceptionE faultMessage;
    
    public IOExceptionException0() {
        super("IOExceptionException0");
    }
           
    public IOExceptionException0(java.lang.String s) {
       super(s);
    }
    
    public IOExceptionException0(java.lang.String s, java.lang.Throwable ex) {
      super(s, ex);
    }
    
    public void setFaultMessage(com.core.service.StockQuoteServiceStub.IOExceptionE msg){
       faultMessage = msg;
    }
    
    public com.core.service.StockQuoteServiceStub.IOExceptionE getFaultMessage(){
       return faultMessage;
    }
}
    