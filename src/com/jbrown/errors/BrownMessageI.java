package com.jbrown.errors;

import java.util.Locale;
import java.util.ResourceBundle;

public interface BrownMessageI {
	public String getMessage(ResourceBundle bundle);
	public String getKey();
	public Object[] getArgs();
}
