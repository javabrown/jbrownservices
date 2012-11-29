package com.core.util.model;

import java.io.Serializable;

import com.jbrown.core.exception.BrownExcelException;

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
	
	/*
	 * Column index start with 0
	 */
	public String getCellValue(int columnIndex){
		if(columnIndex >= _row.length){
			throw new BrownExcelException("No row defined for column-index:"
					+ columnIndex);
		}
		
		return _row[columnIndex];
	}
}
