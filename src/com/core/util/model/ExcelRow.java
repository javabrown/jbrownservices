package com.core.util.model;

import java.io.Serializable;

public class ExcelRow implements Serializable{
	String[] _row;
	
	public ExcelRow(){
		this(new String[0]);
	}
	
	public ExcelRow(String[] row){
		_row = row;
	}
	
	public String[] getRow(){
		return _row;
	}
}