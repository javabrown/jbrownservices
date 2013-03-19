package com.jbrown.web.ws;

import com.jbrown.ext.capsule.CapsuleType;
import com.jbrown.ext.capsule.impl.BrownGeoCapsule;

public enum OutputFormat {
	XML_OUTPUT("XML"), JSON_OUTPUT("JSON");

	public final String formatName;

	private OutputFormat(String formatName) {
		this.formatName = formatName;
	}

	public static OutputFormat getInstance(String formatName) {
		if (formatName != null) {
			for (OutputFormat c : OutputFormat.values()) {
				if (formatName.trim().equalsIgnoreCase(c.formatName)){
					return c;
				}
			}
		}
		return JSON_OUTPUT;
	}

	public boolean typeOf(OutputFormat format) {
		return getInstance(this.formatName).equals(format);
	}
}