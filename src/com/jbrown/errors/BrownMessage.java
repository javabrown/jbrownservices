package com.jbrown.errors;

import java.util.MissingResourceException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringEscapeUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class BrownMessage implements BrownMessageI {
	private String _key;
	private Object[] _args;

	private static Set<Class> KLASSES = new HashSet<Class>() {
		{
			add(Date.class);
			add(Integer.class);
		}
	};

	public BrownMessage(String key, Object... args) {
		_key = key;
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				if (args[i] != null
						&& !KLASSES.contains(args[i].getClass())) {
					String argString = args[i].toString();
					args[i] = StringEscapeUtils.escapeHtml(argString);
				}
			}
		}
		_args = args;
	}

	public String getKey() {
		return _key;
	}

	public Object[] getArgs() {
		return _args;
	}

 
	public String getMessage(ResourceBundle bundle) {
		String pattern = null;
		try {
			pattern = bundle.getString(_key);
		} catch (MissingResourceException e) {
			// intentional ignore
		}
		return (pattern == null) ? _key : MessageFormat.format(pattern, _args);
	}

	public String toString() {
		return _key ;//+ Arrays.toString(_args);
	}

}