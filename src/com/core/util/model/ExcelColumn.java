package com.core.util.model;

import java.io.Serializable;

public class ExcelColumn implements Serializable{
	String[] _columnName;
	
	public ExcelColumn(){
		this(new String[0]);
	}
	
	public ExcelColumn(String[] columns){
		_columnName = columns;
	}
	
	public String[] getColumnName(){
		return _columnName;
	}
}
