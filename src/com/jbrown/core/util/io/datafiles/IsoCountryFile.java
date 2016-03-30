package com.jbrown.core.util.io.datafiles;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import com.jbrown.core.util.BrownConstant;
import com.jbrown.core.util.StringUtil;
import com.jbrown.core.util.io.BrownFileReader;
import com.jbrown.core.util.io.JBStaticDataFile;
import com.jbrown.web.servlet.Request;
import com.jbrown.web.servlet.RequestI;

public class IsoCountryFile extends JBStaticDataFile {

	private static IsoCountryFile _isoCountryFileInstance;
	private IsoCountryFileProcessor _fieldProcessor;
	private static final String DATA_FILE = "iso-country-codes.tsv";

	public static synchronized IsoCountryFile getInstance(RequestI request) {
		if (_isoCountryFileInstance == null) {
			_isoCountryFileInstance = new IsoCountryFile(request);
		}

		return _isoCountryFileInstance;
	}

	private IsoCountryFile(RequestI request) {
		super(DATA_FILE, new IsoCountryFileProcessor(request));
		_fieldProcessor = ((IsoCountryFileProcessor) getFieldProcessor());
	}

	public List<IsoCountryData> getIsoCountryList() {
		return _fieldProcessor.getIsoCountryList();
	}

	private static class IsoCountryFileProcessor implements
			BrownFileReader.FieldsProcessorI {
		private static final int N_FIELDS = 5;

		private static final int ENGLISH_NAME_INDEX = 0;
		private static final int ALPHA_2_CODE_INDEX = 1;
		private static final int ALPHA_3_CODE_INDEX = 2;
		private static final int NUMERIC_CODE_INDEX = 3;
		private static final int ISO_3166_2_CODES_INDEX = 4;

		private List<IsoCountryData> _list;
		private RequestI _request;
		
		IsoCountryFileProcessor(RequestI request) {
			_list = new ArrayList<IsoCountryData>();
			_request = request;
		}

		public String processFields(String[] fields) {
			if (fields.length != N_FIELDS) {
				// throw new Exception("Bad " +DATA_FILE+" file format");
				System.out.println("Bad " + DATA_FILE + " file format");
			}

			IsoCountryData rec = new IsoCountryData(fields[ENGLISH_NAME_INDEX],
					fields[ALPHA_2_CODE_INDEX],
					fields[ALPHA_3_CODE_INDEX].trim(),
					fields[NUMERIC_CODE_INDEX].trim(),
					fields[ISO_3166_2_CODES_INDEX].trim());

			// System.out.println(rec);

			if (rec.isEmpty()) {
				System.out.println("IsoCountryData file contains either "
						+ "\"Empty Row\" Or \"Mandatory Fileds Missing\"");
				return null;
			}

			_list.add(rec);

			return null;
		}

		public List<IsoCountryData> getIsoCountryList() {
			return _list;
		}

		@Override
		public InputStream getInputStream() {
			if (_request == null) {
				System.out.println("_request object is null, init failed !!");
			}
			return _request.getStaticResorceStream(BrownConstant.ISO_COUNTRY);
		}

	}

	public static class IsoCountryData {
		private String englishName;
		private String alpha_2_code;
		private String alpha_3_code;
		private String numeric_code;
		private String iso_3166_2_code;

		public IsoCountryData(String englishName, String alpha_2_code,
				String alpha_3_code, String numeric_code, String iso_3166_2_code) {
			this.englishName = englishName;
			this.alpha_2_code = alpha_2_code;
			this.alpha_3_code = alpha_3_code;
			this.numeric_code = numeric_code;
			this.iso_3166_2_code = iso_3166_2_code;
		}

		public String getEnglishName() {
			return englishName;
		}

		public String getAlpha_2_code() {
			return alpha_2_code;
		}

		public String getAlpha_3_code() {
			return alpha_3_code;
		}

		public String getNumeric_code() {
			return numeric_code;
		}

		public String getIso_3166_2_code() {
			return iso_3166_2_code;
		}

		public boolean isEmpty() {
			return StringUtil.isEmpty(this.englishName, this.alpha_2_code);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((alpha_2_code == null) ? 0 : alpha_2_code.hashCode());
			result = prime * result
					+ ((alpha_3_code == null) ? 0 : alpha_3_code.hashCode());
			result = prime * result
					+ ((englishName == null) ? 0 : englishName.hashCode());
			result = prime
					* result
					+ ((iso_3166_2_code == null) ? 0 : iso_3166_2_code
							.hashCode());
			result = prime * result
					+ ((numeric_code == null) ? 0 : numeric_code.hashCode());
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
			IsoCountryData other = (IsoCountryData) obj;
			if (alpha_2_code == null) {
				if (other.alpha_2_code != null)
					return false;
			} else if (!alpha_2_code.equals(other.alpha_2_code))
				return false;
			if (alpha_3_code == null) {
				if (other.alpha_3_code != null)
					return false;
			} else if (!alpha_3_code.equals(other.alpha_3_code))
				return false;
			if (englishName == null) {
				if (other.englishName != null)
					return false;
			} else if (!englishName.equals(other.englishName))
				return false;
			if (iso_3166_2_code == null) {
				if (other.iso_3166_2_code != null)
					return false;
			} else if (!iso_3166_2_code.equals(other.iso_3166_2_code))
				return false;
			if (numeric_code == null) {
				if (other.numeric_code != null)
					return false;
			} else if (!numeric_code.equals(other.numeric_code))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "IsoCountryData [englishName=" + englishName
					+ ", alpha_2_code=" + alpha_2_code + ", alpha_3_code="
					+ alpha_3_code + ", numeric_code=" + numeric_code
					+ ", iso_3166_2_code=" + iso_3166_2_code + "]";
		}
	}
}
