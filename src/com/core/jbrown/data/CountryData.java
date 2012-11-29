package com.core.jbrown.data;

import java.util.ArrayList;
import java.util.List;

import com.core.jbrown.data.model.CountryI;
import com.core.jbrown.data.model.IndiaPinCodeData;
import com.core.util.model.ExcelColumn;
import com.core.util.model.ExcelRow;
import com.core.util.model.ExcelSheet;
import com.jbrown.core.util.BrownUtil;

public class CountryData {
	private CountryI[] countryData; 
	
	public CountryData(){
		int POST_OFFICE_NAME = 0;
		int PINCODE = 1;
		int DISTRICT_NAME = 2;
		int CITY_NAME = 3;
		int STATE_NAME = 4;
		
		ExcelSheet sheet =
			new ExcelSheet("India", "data/Pincodes_of_India.xlsx");
		
	    
		ExcelRow[] rows = sheet.getRows();
		
		List<IndiaPinCodeData> list = new ArrayList<IndiaPinCodeData>();
		
		for(ExcelRow row : rows){
			String[] cell = row.getRow();
			String postOfficeName = cell[POST_OFFICE_NAME];
			
			String pinCode = cell[PINCODE];
			String distName = cell[DISTRICT_NAME];
			String cityName = cell[CITY_NAME];
			String stateName = cell[STATE_NAME];
			IndiaPinCodeData data = 
				new IndiaPinCodeData(pinCode, distName, cityName, stateName);
			list.add(
			  new IndiaPinCodeData(pinCode, distName, cityName, stateName));
			
			System.out.println(data);
		}
		
		String[] indianStates = sheet.getRowsForAColumn("State"); 
		
	}
	
	public CountryData(CountryI[] countryData){
		this.countryData = countryData;
	}
}
