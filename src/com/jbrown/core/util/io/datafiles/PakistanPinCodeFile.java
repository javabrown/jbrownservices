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
import com.jbrown.core.util.MultiTreeMap;
import com.jbrown.core.util.RecursiveTreeMap;
import com.jbrown.core.util.StrKey;
import com.jbrown.core.util.io.BrownFileReader;
import com.jbrown.core.util.io.JBStaticDataFile;
import com.jbrown.core.util.io.datafiles.IndianPinCodeFile.IndiaPinCodeData;
import com.jbrown.web.servlet.Request;
import com.jbrown.web.servlet.RequestI;

public class PakistanPinCodeFile extends JBStaticDataFile {

	private static PakistanPinCodeFile _pakistanPinCodeFileInstance;
	private PakistanPinCodeFileProcessor _fieldProcessor;
	private static final String DATA_FILE = "pak-postal-code.tsv";

	public static synchronized PakistanPinCodeFile getInstance(RequestI request) {
		if (_pakistanPinCodeFileInstance == null) {
			_pakistanPinCodeFileInstance = new PakistanPinCodeFile(request);
		}

		return _pakistanPinCodeFileInstance;
	}

	private PakistanPinCodeFile(RequestI request) {
		super(DATA_FILE, new PakistanPinCodeFileProcessor(request));
		_fieldProcessor = ((PakistanPinCodeFileProcessor) getFieldProcessor());
	}

	public List<PakistanPinCodeData> getPakistanPinCodeDataFiles() {
		return _fieldProcessor.getPakistanPinCodeList();
	}

	public CountryI getCountryInfo() {
		List<PakistanPinCodeData> pakistanList = getPakistanPinCodeDataFiles();

		MultiTreeMap<StrKey, PostOfficeI> postAndDistMap = new RecursiveTreeMap<StrKey, PostOfficeI>();

		MultiTreeMap<StrKey, DistrictI> distAndStateMap = new RecursiveTreeMap<StrKey, DistrictI>();

		MultiTreeMap<StrKey, StateI> stateAndCountryMap = new RecursiveTreeMap<StrKey, StateI>();

		String PAKISTAN_ISO_CODE = "PK";

		for (PakistanPinCodeData data : pakistanList) {
			StrKey postAndDistKey = new StrKey(data.getDistName(),
					data.getStateName());

			StrKey distAndStateKey = new StrKey(PAKISTAN_ISO_CODE,
					data.getStateName());

			StrKey stateAndCountryKey = new StrKey(PAKISTAN_ISO_CODE);

			postAndDistMap.insert(postAndDistKey,
					new PostOffice(data.getPostOfficeName(), data.getPinCode(),
							data.getDistName()));
			distAndStateMap
					.insert(distAndStateKey, new District(data.getStateName(),
							data.getDistName(), null));

			stateAndCountryMap.insert(stateAndCountryKey, new State(
					PAKISTAN_ISO_CODE, data.getStateName(), null));
		}

		Map<StrKey, Set<DistrictI>> stateNDistrictMap = distAndStateMap
				.getMap();

		for (StrKey key : stateNDistrictMap.keySet()) {
			Set<DistrictI> distList = stateNDistrictMap.get(key);

			for (DistrictI dist : distList) {
				StrKey postAndDistKey = new StrKey(dist.getDistrictName(),
						dist.getStateName());
				Set<PostOfficeI> postOfficeList = postAndDistMap
						.getValue(postAndDistKey);
				dist.updatePostOffice(postOfficeList
						.toArray(new PostOfficeI[0]));
			}
		}

		Map<StrKey, Set<StateI>> stateNCountryMap = stateAndCountryMap.getMap();
		for (StrKey key : stateAndCountryMap.keySet()) {
			Set<StateI> stateList = stateNCountryMap.get(key);

			for (StateI state : stateList) {

				StrKey distAndStateKey = new StrKey(PAKISTAN_ISO_CODE,
						state.getStateName());

				Set<DistrictI> distList = distAndStateMap
						.getValue(distAndStateKey);

				state.updateDistricts(distList.toArray(new DistrictI[0]));
			}
		}

		Set<StateI> pakistanStates = stateNCountryMap.get(new StrKey(
				PAKISTAN_ISO_CODE));

		Country pakistan = new Country(PAKISTAN_ISO_CODE, "PAKISTAN",
				pakistanStates.toArray(new StateI[0]));

		return pakistan;
	}

	private static class PakistanPinCodeFileProcessor implements
			BrownFileReader.FieldsProcessorI {

		private static final int N_FIELDS = 4;

		private static final int POST_OFFICE_NAME_INDEX = 0;
		private static final int PIN_CODE_INDEX = 1;
		private static final int DISTRICT_INDEX = 2;
		private static final int STATE_NAME_INDEX = 3;

		private List<PakistanPinCodeData> _list;
		private RequestI _request;

		PakistanPinCodeFileProcessor(RequestI request) {
			_list = new ArrayList<PakistanPinCodeData>();
			_request = request;
		}

		public String processFields(String[] fields) {

			if (fields.length != N_FIELDS) {
				// throw new Exception("Bad " +DATA_FILE+" file format");
				System.out.println("Bad " + DATA_FILE + " file format");
			}

			PakistanPinCodeData rec = new PakistanPinCodeData(
					fields[POST_OFFICE_NAME_INDEX], fields[PIN_CODE_INDEX],
					fields[DISTRICT_INDEX].trim(),
					fields[STATE_NAME_INDEX].trim());

			// System.out.println(rec);

			if (rec.isEmpty()) {
				System.out.println("PakistanPinCodeData file contains either "
						+ "\"Empty Row\" Or \"Mandatory Fileds Missing\"");
				return null;
			}

			_list.add(rec);

			return null;
		}

		public List<PakistanPinCodeData> getPakistanPinCodeList() {
			return _list;
		}

		@Override
		public InputStream getInputStream() {
			if (_request == null) {
				System.out.println("_request object is null, init failed !!");
			}
			
			return _request.getStaticResorceStream(BrownConstant.ISO_PAKISTAN);
		}

	}

	public static class PakistanPinCodeData {
		private String postOfficeName;
		private String pinCode;
		private String distName;
		private String stateName;

		public PakistanPinCodeData(String postOfficeName, String pinCode,
				String distName, String stateName) {
			this.postOfficeName = postOfficeName;
			this.pinCode = pinCode;
			this.distName = distName;
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
			return distName;
		}

		public String getStateName() {
			return stateName;
		}

		public boolean isEmpty(String s) {
			return (s == null) || s.trim().equals("");
		}

		public boolean isEmpty() {
			return isEmpty(this.postOfficeName) || isEmpty(this.pinCode)
					|| isEmpty(this.distName) || isEmpty(this.distName)
					|| isEmpty(this.stateName);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
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
			PakistanPinCodeData other = (PakistanPinCodeData) obj;
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
			return "PakistanPinCodeData [postOfficeName=" + postOfficeName
					+ ", pinCode=" + pinCode + ", distName=" + distName
					+ ", stateName=" + stateName + "]";
		}
	}
}
