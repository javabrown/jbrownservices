package com.jbrown.core.exception;
 
public class BrownExcelException extends BorwnException  {
	
	public BrownExcelException(String message, Throwable cause) {
		super(message, cause);
		cause.printStackTrace();
		System.out.println(message);
	}

	public BrownExcelException(String message) {
		super(message);
		System.out.println(message);
	}

	public BrownExcelException(Throwable cause) {
		super(cause);
		cause.printStackTrace();
	}

}
