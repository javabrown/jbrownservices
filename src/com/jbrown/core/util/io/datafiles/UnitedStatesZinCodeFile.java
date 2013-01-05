package com.jbrown.core.util.io.datafiles;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import com.jbrown.core.data.model.Country;
import com.jbrown.core.data.model.CountryI;
import com.jbrown.core.data.model.District;
import com.jbrown.core.data.model.DistrictI;
import com.jbrown.core.data.model.PostOffice;
import com.jbrown.core.data.model.PostOfficeI;
import com.jbrown.core.data.model.State;
import com.jbrown.core.data.model.StateI;
import com.jbrown.core.util.BrownConstant;
import com.jbrown.core.util.BrownUtil;
import com.jbrown.core.util.MultiTreeMap;
import com.jbrown.core.util.RecursiveTreeMap;
import com.jbrown.core.util.StrKey;
import com.jbrown.core.util.io.BrownFileReader;
import com.jbrown.core.util.io.JBStaticDataFile;
import com.jbrown.web.servlet.Request;
import com.jbrown.web.servlet.RequestI;

public class UnitedStatesZinCodeFile extends JBStaticDataFile {

	private static UnitedStatesZinCodeFile _usaZipCodeFileInstance;
	private USAPinCodeFileProcessor _fieldProcessor;
	private static final String DATA_FILE = "usa-postal-code.tsv";

	public static synchronized UnitedStatesZinCodeFile getInstance(RequestI request) {
		if (_usaZipCodeFileInstance == null) {
			_usaZipCodeFileInstance = new UnitedStatesZinCodeFile(request);
		}

		return _usaZipCodeFileInstance;
	}

	private UnitedStatesZinCodeFile(RequestI request) {
		super(DATA_FILE, new USAPinCodeFileProcessor(request));
		_fieldProcessor = ((USAPinCodeFileProcessor) getFieldProcessor());
	}

	public List<USAZipCodeData> getUSAZipCodeDataFiles() {
		return _fieldProcessor.getUSAZipCodeDataList();
	}
	
	public CountryI getCountryInfo() {
		List<USAZipCodeData> usaList = getUSAZipCodeDataFiles();

		MultiTreeMap<StrKey, PostOfficeI> zipCodeAndCityMap = 
			new RecursiveTreeMap<StrKey, PostOfficeI>();

		MultiTreeMap<StrKey, DistrictI> cityAndStateMap = 
			new RecursiveTreeMap<StrKey, DistrictI>();

		MultiTreeMap<StrKey, StateI> stateAndCountryMap = 
			new RecursiveTreeMap<StrKey, StateI>();
		
		String USA_ISO_CODE = "US";

		for (USAZipCodeData data : usaList) {
			StrKey zipCodeAndCityKey = new StrKey(data.getCityName(),
					data.getStateName());

			StrKey cityAndStateKey = new StrKey(USA_ISO_CODE,
					data.getStateName());

			StrKey stateAndCountryKey = new StrKey(USA_ISO_CODE);

			zipCodeAndCityMap.insert(zipCodeAndCityKey,
					new PostOffice(data.getCityName(), data.getZipCode(),
							data.getCityName()));
			cityAndStateMap
					.insert(cityAndStateKey, new District(data.getStateName(),
							data.getCityName(), null));

			stateAndCountryMap.insert(stateAndCountryKey, new State(
					USA_ISO_CODE, data.getStateName(), null));
		}

		Map<StrKey, Set<DistrictI>> stateNCityMap = cityAndStateMap
				.getMap();

		for (StrKey key : stateNCityMap.keySet()) {
			Set<DistrictI> distList = stateNCityMap.get(key);

			for (DistrictI dist : distList) {
				StrKey postAndDistKey = new StrKey(dist.getDistrictName(),
						dist.getStateName());
				Set<PostOfficeI> postOfficeList = zipCodeAndCityMap
						.getValue(postAndDistKey);
				dist.updatePostOffice(postOfficeList
						.toArray(new PostOfficeI[0]));
			}
		}

		Map<StrKey, Set<StateI>> stateNCountryMap = stateAndCountryMap.getMap();
		for (StrKey key : stateAndCountryMap.keySet()) {
			Set<StateI> stateList = stateNCountryMap.get(key);

			for (StateI state : stateList) {

				StrKey distAndStateKey = new StrKey(USA_ISO_CODE,
						state.getStateName());

				Set<DistrictI> distList = cityAndStateMap
						.getValue(distAndStateKey);

				state.updateDistricts(distList.toArray(new DistrictI[0]));
			}
		}

		Set<StateI> usaStates = stateNCountryMap.get(new StrKey(
				USA_ISO_CODE));

		Country india = new Country(USA_ISO_CODE, "USA",
				usaStates.toArray(new StateI[0]));
		
		return india;
	}

	private static class USAPinCodeFileProcessor implements
			BrownFileReader.FieldsProcessorI {

		private static final int N_FIELDS = 3;

		private static final int ZIP_CODE_INDEX = 0;
		private static final int CITY_NAME_INDEX = 1;
		private static final int STATE_CODE_INDEX = 2;

		private List<USAZipCodeData> _list;
		private RequestI _request;

		USAPinCodeFileProcessor(RequestI request) {
			_list = new ArrayList<USAZipCodeData>();
			_request = request;
		}

		public String processFields(String[] fields) {

			if (fields.length != N_FIELDS) {
				// throw new Exception("Bad " +DATA_FILE+" file format");
				System.out.println("Bad " + DATA_FILE + " file format");
			}

			USAZipCodeData rec = new USAZipCodeData(
					fields[ZIP_CODE_INDEX].trim(),
					fields[CITY_NAME_INDEX].trim(),
					fields[STATE_CODE_INDEX].trim());

			// System.out.println(rec);

			if (rec.isEmpty()) {
				System.out.println("IndiaPinCodeData file contains either "
						+ "\"Empty Row\" Or \"Mandatory Fileds Missing\"");
				return null;
			}

			_list.add(rec);

			return null;
		}

		public List<USAZipCodeData> getUSAZipCodeDataList() {
			return _list;
		}

		@Override
		public InputStream getInputStream() {
			if (_request == null) {
				System.out.println("_request object is null, init failed !!");
			}

			return _request.getStaticResorceStream(BrownConstant.ISO_USA);
		}

	}

	public static class USAZipCodeData {
		private String zipCode;
		private String cityName;
		private String stateCode;
		private String stateName;

		public USAZipCodeData(String zipCode, String cityName,
				String stateCode) {
			this.zipCode = zipCode;
			this.cityName = cityName;
			this.stateCode = stateCode;
			this.stateName = getStateName(stateCode);
		}

		public String getZipCode() {
			return zipCode;
		}

		public String getCityName() {
			return cityName;
		}

		public String getStateCode() {
			return stateCode;
		}

		public String getStateName() {
			return stateName;
		}

		public boolean isEmpty() {
			return BrownUtil.isEmpty(this.zipCode, this.cityName,
					this.stateCode, this.stateName);
		}

		private String getStateName(String stateCode) {
			String[] stateNames = new String[] { "Alabama,AL				",
					"Alaska,AK              ", "Arizona,AZ             ",
					"Arkansas,AR            ", "American Samoa,AS      ",
					"California,CA          ", "Colorado,CO            ",
					"Connecticut,CT         ", "Delaware,DE            ",
					"Florida,FL             ", "Georgia,GA             ",
					"Hawaii,HI              ", "Idaho,ID               ",
					"Illinois,IL            ", "Indiana,IN             ",
					"Iowa,IA                ", "Kansas,KS              ",
					"Kentucky,KY            ", "Louisiana,LA           ",
					"Maine,ME               ", "Maryland,MD            ",
					"Massachusetts,MA       ", "Michigan,MI            ",
					"Minnesota,MN           ", "Mississippi,MS         ",
					"Missouri,MO            ", "Montana,MT             ",
					"Nebraska,NE            ", "Nevada,NV              ",
					"New Hampshire,NH       ", "New Jersey,NJ          ",
					"New Mexico,NM          ", "New York,NY            ",
					"North Carolina,NC      ", "North Dakota,ND        ",
					"Ohio,OH                ", "Oklahoma,OK            ",
					"Oregon,OR              ", "Pennsylvania,PA        ",
					"Rhode Island,RI        ", "South Carolina,SC      ",
					"South Dakota,SD        ", "Tennessee,TN           ",
					"Texas,TX               ", "Utah,UT                ",
					"Vermont,VT             ", "Virginia,VA            ",
					"Washington,WA          ", "West Virginia,WV       ",
					"Wisconsin,WI           ", "Wyoming,WY             ", };
			
			for(String stateName : stateNames){
				String[] stateNzip = stateName.split(",");
				if(stateNzip[1].trim().equalsIgnoreCase(stateCode)){
					return stateNzip[0].trim();
				}
			}
			
			return "Not Available";
		}

		
		@Override
		public String toString() {
			return "USAZipCodeData [zipCode=" + zipCode + ", cityName="
					+ cityName + ", stateCode=" + stateCode + ", stateName="
					+ stateName + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((cityName == null) ? 0 : cityName.hashCode());
			result = prime * result
					+ ((stateCode == null) ? 0 : stateCode.hashCode());
			result = prime * result
					+ ((stateName == null) ? 0 : stateName.hashCode());
			result = prime * result
					+ ((zipCode == null) ? 0 : zipCode.hashCode());
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
			USAZipCodeData other = (USAZipCodeData) obj;
			if (cityName == null) {
				if (other.cityName != null)
					return false;
			} else if (!cityName.equals(other.cityName))
				return false;
			if (stateCode == null) {
				if (other.stateCode != null)
					return false;
			} else if (!stateCode.equals(other.stateCode))
				return false;
			if (stateName == null) {
				if (other.stateName != null)
					return false;
			} else if (!stateName.equals(other.stateName))
				return false;
			if (zipCode == null) {
				if (other.zipCode != null)
					return false;
			} else if (!zipCode.equals(other.zipCode))
				return false;
			return true;
		}
	}
}
