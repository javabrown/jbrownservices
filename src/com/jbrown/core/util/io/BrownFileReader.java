package com.jbrown.core.util.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class BrownFileReader {

	private final String _name;
	private final BufferedReader _in;
	private final String _separatorJB;
	private final FieldsProcessorI _fieldsProcessor;

	private static Logger logger = Logger.getLogger(BrownFileReader.class
			.getName());

	// public BrownFileReader(String name, String fileName, String separatorRE,
	// FieldsProcessorI fieldsProcessor) {
	// _name = name;
	// _in = getFileReader(fileName);
	// _separatorJB = separatorRE;
	// _fieldsProcessor = fieldsProcessor;
	// }

	public BrownFileReader(String name, String fileName, String separatorRE,
			FieldsProcessorI fieldsProcessor) {
		_name = name;
		// _in = getFileReader(fileName);
		_separatorJB = separatorRE;
		_fieldsProcessor = fieldsProcessor;
		_in = getBufferedReader(_fieldsProcessor.getInputStream());
	}

	public BrownFileReader(String name, InputStream in, String separatorRE,
			FieldsProcessorI fieldsProcessor) {
		_name = name;
		_in = new BufferedReader(new InputStreamReader(in));
		_separatorJB = separatorRE;
		_fieldsProcessor = fieldsProcessor;
	}

	private BufferedReader getBufferedReader(InputStream inputStream) {
		BufferedReader in = null;
		try {
			// in = new BufferedReader(new FileReader(fileName));
			in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return in;
	}

	private BufferedReader getFileReader(String fileName) {
		BufferedReader in = null;
		try {
			InputStream stream = null;
			in = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return in;
	}

	public void read() {
		try {
			int lineN = 1;
			_in.readLine(); // discard header
			lineN++;
			for (String line = _in.readLine(); line != null; line = _in
					.readLine()) {
				String[] fields = line.split(_separatorJB, -1);
				String status = _fieldsProcessor.processFields(fields);
				if (status != null) {
					logger.error(_name + ":" + lineN + ": " + status);
				}
				lineN++;
			} // for
			logger.info(_name + ": read " + (lineN - 1) + " lines from "
					+ _name);
		} // try
		catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (_in != null) {
				try {
					_in.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public interface FieldsProcessorI {
		String processFields(String[] fields);

		InputStream getInputStream();
	}

}
