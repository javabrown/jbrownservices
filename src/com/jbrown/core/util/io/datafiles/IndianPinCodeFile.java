package com.jbrown.core.util.io.datafiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jbrown.core.util.io.BrownFileReader;
import com.jbrown.core.util.io.JBStaticDataFile;
import com.jbrown.core.util.io.BrownFileReader.FieldsProcessorI;
 
public class IndianPinCodeFile extends JBStaticDataFile {

	  private static IndianPinCodeFile _indianPinCodeFileInstance;
	  private IndianPinCodeFileProcessor _fieldProcessor; 
	  private static final String DATA_FILE = "indian-postal-code.tsv";
	 
	  
	  public static synchronized IndianPinCodeFile getInstance() {
	    if (_indianPinCodeFileInstance == null){
	    	_indianPinCodeFileInstance = new IndianPinCodeFile();
	    }
	    
	    return _indianPinCodeFileInstance;
	  }
	  
	  private IndianPinCodeFile(){
	    super(DATA_FILE, new IndianPinCodeFileProcessor());
	    _fieldProcessor = ((IndianPinCodeFileProcessor) getFieldProcessor());
	  }
	  
	  public List<IndiaPinCodeData> getIndianPinCodeDataFiles(){
	    return _fieldProcessor.getIndiaPinCodeList();
	  }
	
	private static class IndianPinCodeFileProcessor 
                                implements BrownFileReader.FieldsProcessorI {
		
		private static final int N_FIELDS = 5;
		
		private static final int POST_OFFICE_NAME_INDEX = 0;
		private static final int PIN_CODE = 1;
		private static final int DISTRICT_NAME_INDEX = 2;
		private static final int CITY_NAME_INDEX = 3;
		private static final int STATE_NAME_INDEX = 4;
		
		private List<IndiaPinCodeData> _list;
		
		IndianPinCodeFileProcessor() {
			_list = new ArrayList<IndiaPinCodeData>();
		}
		
		public String processFields(String[] fields) {
			
			if (fields.length != N_FIELDS) {
		        //throw new Exception("Bad " +DATA_FILE+" file format");
				System.out.println("Bad " +DATA_FILE+" file format");
			}
			
			IndiaPinCodeData rec = new IndiaPinCodeData(
					fields[POST_OFFICE_NAME_INDEX], fields[PIN_CODE],
					fields[DISTRICT_NAME_INDEX].trim(),
					fields[CITY_NAME_INDEX].trim(),
					fields[STATE_NAME_INDEX].trim());
			 
			System.out.println(rec);
				
        if (rec.isEmpty()) {
          System.out.println("IndiaPinCodeData file contains either "
            + "\"Empty Row\" Or \"Mandatory Fileds Missing\"");
          return null;
        }
				
        _list.add(rec);		
			
		 return null;				
		}
		
		public List<IndiaPinCodeData> getIndiaPinCodeList() {
		      return _list;
		}
		
		
			
	}	

	
	public static class IndiaPinCodeData {
		private String postOfficeName;
		private String pinCode;
		private String distName;
		private String cityName;
		private String stateName;

		public IndiaPinCodeData(String postOfficeName, String pinCode,
				String distName, String cityName, String stateName) {
			this.postOfficeName = postOfficeName;
			this.pinCode = pinCode;
			this.distName = distName;
			this.cityName = cityName;
			this.stateName = stateName;
		}

		public String getPostOfficeName() {
			return postOfficeName;
		}
		
		public String getPinCode() {
			return pinCode;
		}

		public String getDistName() {
			return distName;
		}

		public String getCityName() {
			return cityName;
		}

		public String getStateName() {
			return stateName;
		}

		public boolean isEmpty(String s) {
			    return (s == null) || s.trim().equals("");
		}
			  
		  
		public boolean isEmpty() {
			return isEmpty(this.postOfficeName) || isEmpty(this.pinCode)
					|| isEmpty(this.distName) || isEmpty(this.cityName)
					|| isEmpty(this.stateName);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((cityName == null) ? 0 : cityName.hashCode());
			result = prime * result
					+ ((distName == null) ? 0 : distName.hashCode());
			result = prime * result
					+ ((pinCode == null) ? 0 : pinCode.hashCode());
			result = prime
					* result
					+ ((postOfficeName == null) ? 0 : postOfficeName.hashCode());
			result = prime * result
					+ ((stateName == null) ? 0 : stateName.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			IndiaPinCodeData other = (IndiaPinCodeData) obj;
			if (cityName == null) {
				if (other.cityName != null)
					return false;
			} else if (!cityName.equals(other.cityName))
				return false;
			if (distName == null) {
				if (other.distName != null)
					return false;
			} else if (!distName.equals(other.distName))
				return false;
			if (pinCode == null) {
				if (other.pinCode != null)
					return false;
			} else if (!pinCode.equals(other.pinCode))
				return false;
			if (postOfficeName == null) {
				if (other.postOfficeName != null)
					return false;
			} else if (!postOfficeName.equals(other.postOfficeName))
				return false;
			if (stateName == null) {
				if (other.stateName != null)
					return false;
			} else if (!stateName.equals(other.stateName))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "IndiaPinCodeData [postOfficeName=" + postOfficeName
					+ ", pinCode=" + pinCode + ", distName=" + distName
					+ ", cityName=" + cityName + ", stateName=" + stateName
					+ "]";
		}
	}
}
