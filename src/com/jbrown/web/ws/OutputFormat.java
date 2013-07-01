package com.jbrown.web.ws;
/**
 * 
 * @author rkhan
 *
 * This class defines the output format.
 */
public enum OutputFormat {
	XML("XML", "text/xml"),
	JSON("JSON", "application/json"),
	HTML("HTML", "text/html"),
	PDF("PDF", "application/pdf"),
	CSS("CSS", "text/css"),
	JAVASCRIPT("JAVASCRIPT", "text/javascript"),
	SOAP("SOAP", "text/soap+xml"),
	PLAIN_TEXT("PLAIN_TEXT", "text/plain");
	 
	
	public final String formatName;
	public final String mimeType; 
	
	private OutputFormat(String formatName, String mimeType) {
		this.formatName = formatName;
		this.mimeType = mimeType;
	}
	
	public String getFormatName(){
		return this.formatName;
	}
	
	public String getMimeType(){
		return this.mimeType;
	}
	
	public static OutputFormat getInstance(String formatName) {
		if (formatName != null) {
			for (OutputFormat c : OutputFormat.values()) {
				if (formatName.trim().equalsIgnoreCase(c.formatName)){
					return c;
				}
			}
		}
		
		return JSON;
	}

	public boolean typeOf(OutputFormat format) {
		return getInstance(this.formatName).equals(format);
	}
}