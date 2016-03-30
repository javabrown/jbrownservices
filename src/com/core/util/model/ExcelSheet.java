package com.core.util.model;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelSheet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String _title;
	private final ExcelColumn _column;
	private final ExcelRow[] _rows;

	public ExcelSheet(String title, String excelFile){
		this("", null, new ExcelRow[0]);
		this.loadExcelSheet(title, excelFile);
	}
	
	private ExcelSheet(String title, ExcelColumn column, ExcelRow[] rows) {
		_title = title;
		_column = column;
		_rows = rows;
	}
	
	public String getTitle(){
		return _title;
	}
	public ExcelColumn getColumn() {
		return _column;
	}
	public ExcelRow[] getRows() {
		return _rows;
	}
	
	public void loadExcelSheet(String title, String excelFile){
		ExcelSheet excelSheet = null;
		ExcelColumn column = null;

		List<String> columnList = new ArrayList<String>();
		List<ExcelRow> rowList = new ArrayList<ExcelRow>();		
		
		try {
			InputStream inp = new FileInputStream(excelFile);
			Workbook wb = WorkbookFactory.create(inp);
			Sheet sheet = wb.getSheetAt(0);
			boolean isHeaderDone = false;
			
			for (Iterator<Row> rit = sheet.rowIterator(); rit.hasNext();) {
				List<String> rowDataList = new ArrayList<String>();
				Row row = rit.next();
				
				for (Iterator<Cell> cit = row.cellIterator(); cit.hasNext();) {
					Cell cell = cit.next();
					
					String cellData = null;

					switch (cell.getCellType()) {
					 case Cell.CELL_TYPE_NUMERIC:
						cellData = cell.getNumericCellValue()+"";
						break;
					 case Cell.CELL_TYPE_STRING:
						 cellData = cell.getStringCellValue()+"";
						break;
					 default:
						cellData = cell.getStringCellValue()+"";
					}
					
					//System.out.println(cellData);
					
					if(!isHeaderDone){
					  columnList.add(cellData);
					}
					else{
						rowDataList.add(cellData);
					}
				}
				
				if (!isHeaderDone) {
					isHeaderDone = true;
					column = new ExcelColumn(columnList.toArray(new String[0]));
				}
				else{
					rowList.add(new ExcelRow(rowDataList.toArray(new String[0])));
				}
			}
			
			excelSheet = new ExcelSheet(title, column, rowList.toArray(new ExcelRow[0]));
			/*_title = title;
			_column = column;
			_rows = rowList.toArray(new ExcelRow[0]);*/
			
			inp.close();
		} catch (Exception ex) {
			System.out.println("***ExcelSheet object creation failed ****");
			ex.printStackTrace();
		}
		
		//return excelSheet;
	}
}
