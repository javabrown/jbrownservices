package com.jbrown.core.data;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jbrown.core.data.model.Country;
import com.jbrown.core.data.model.CountryI;
import com.jbrown.core.data.model.District;
import com.jbrown.core.data.model.DistrictI;
import com.jbrown.core.data.model.PostOffice;
import com.jbrown.core.data.model.PostOfficeI;
import com.jbrown.core.data.model.State;
import com.jbrown.core.data.model.StateI;
import com.jbrown.core.util.MultiTreeMap;
import com.jbrown.core.util.RecursiveTreeMap;
import com.jbrown.core.util.StrKey;
import com.jbrown.core.util.io.datafiles.IndianPinCodeFile;
import com.jbrown.core.util.io.datafiles.IndianPinCodeFile.IndiaPinCodeData;
import com.jbrown.core.util.io.datafiles.IsoCountryFile;
import com.jbrown.core.util.io.datafiles.PakistanPinCodeFile;
import com.jbrown.core.util.io.datafiles.UnitedStatesZinCodeFile;
import com.jbrown.web.servlet.Request;
import com.jbrown.web.servlet.RequestI;

public class CountryData {
	private CountryI[] countryData;
	private RequestI _request;

	public CountryData(RequestI request) {
		this._request = request;
		this.countryData = getCountryData();
	}

	public StateI[] getState() {
		return countryData[0].getStates();
	}

	public CountryI[] getCountryData() {
		IndianPinCodeFile indianFile = IndianPinCodeFile.getInstance(_request);
		PakistanPinCodeFile pakFile = PakistanPinCodeFile.getInstance(_request);
		UnitedStatesZinCodeFile usaFile =
			UnitedStatesZinCodeFile.getInstance(_request);
		
		IsoCountryFile isoFile = IsoCountryFile.getInstance(_request);
		isoFile.getIsoCountryList();
 
		
		CountryI india = indianFile.getCountryInfo();
		CountryI pak = null;//pakFile.getCountryInfo();
		CountryI usa = usaFile.getCountryInfo();
		
		return new CountryI[] { india, pak, usa };
	}

}
