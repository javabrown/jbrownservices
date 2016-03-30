package com.jbrown.core.test;

import javax.jws.WebService;

@WebService (endpointInterface = "com.jbrown.core.test.HelloJaxWs")
public class HelloJaxWs implements HelloJaxWsI{
	@Override
	public String getHelloWorldAsString() {
		return "Hello World JAX-WS";
	}
}
