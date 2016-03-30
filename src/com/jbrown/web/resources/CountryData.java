package com.jbrown.web.resources;

import java.util.Map;

public class CountryData {
	private Map<String, String> _dataFiles;
	 
	public void setDataFiles(Map<String, String> dataFiles) {
		_dataFiles = dataFiles;
	}
	
	public Map<String, String> getDataFiles() {
		return _dataFiles;
	}
}
