package com.jbrown.core.util.model;

import java.io.Serializable;

public class ExcelColumn implements Serializable{
	private static final long serialVersionUID = 1L;
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

	public int getColumnIndex(String columnName){
		for(int index=0; index < _columnName.length; index++){
			if(_columnName[index].equalsIgnoreCase(columnName)){
				return index;
			}
		}
		
		return -1;
	}	
}
