package com.jbrown.core.util.io;

import java.io.File;
import java.util.logging.Logger;

public abstract class JBStaticDataFile {
	private static Logger logger = Logger.getLogger(JBStaticDataFile.class
			.getName());

	private static String SEPERATOR = File.separator;

	private final BrownFileReader.FieldsProcessorI _fieldProcessor;

	// private final InputStream _inputStream;

	protected JBStaticDataFile(String dataFile,
			BrownFileReader.FieldsProcessorI fieldProcessor) {
		_fieldProcessor = fieldProcessor;

		String fileName = "";// baseDir + File.separator + dataFile;
		try {
			BrownFileReader reader = new BrownFileReader(dataFile, fileName,
					"\\t", fieldProcessor);
			reader.read();
		} catch (Exception e) {
			logger.info("Error reading " + dataFile);
			System.out.println("Error reading " + dataFile);
			e.printStackTrace();
		}
	}

	// protected JBStaticDataFile(String dataFile,
	// BrownFileReader.FieldsProcessorI fieldProcessor) {
	// _fieldProcessor = fieldProcessor;
	//
	// String baseDir = System.getenv("CATALINA_BASE");
	//
	// baseDir = baseDir + SEPERATOR + "browndata";
	//
	// if (!new File(baseDir).isDirectory()) {
	// new File(baseDir).mkdirs();
	// }
	//
	// String fileName = baseDir + File.separator + dataFile;
	// try {
	// BrownFileReader reader = new BrownFileReader(dataFile, fileName,
	// "\\t", fieldProcessor);
	// reader.read();
	// } catch (Exception e) {
	// logger.info("Error reading " + dataFile);
	// System.out.println("Error reading " + dataFile);
	// e.printStackTrace();
	// }
	// }

	// protected JBStaticDataFile(InputStream inputStream, String dataFile,
	// BrownFileReader.FieldsProcessorI fieldProcessor) {
	// _fieldProcessor = fieldProcessor;
	// _inputStream = inputStream;
	//
	// String baseDir = System.getenv("CATALINA_BASE");
	//
	// baseDir = baseDir + SEPERATOR + "browndata";
	//
	// if (!new File(baseDir).isDirectory()) {
	// new File(baseDir).mkdirs();
	// }
	//
	// String fileName = baseDir + File.separator + dataFile;
	// try {
	// // BrownFileReader reader = new BrownFileReader(dataFile, fileName,
	// // "\\t", fieldProcessor);
	// // BrownFileReader reader = new BrownFileReader(dataFile, fileName,
	// // "\\t", fieldProcessor);
	// BrownFileReader reader = new BrownFileReader(dataFile,
	// _inputStream, "\\t", fieldProcessor);
	//
	// reader.read();
	// } catch (Exception e) {
	// logger.info("Error reading " + dataFile);
	// System.out.println("Error reading " + dataFile);
	// e.printStackTrace();
	// }
	// }

	//
	// protected JBStaticDataFile(String dataFile,
	// BrownFileReader.FieldsProcessorI fieldProcessor) {
	// _fieldProcessor = fieldProcessor;
	// _inputStream = null;
	//
	// String baseDir = System.getenv("CATALINA_BASE");
	//
	// baseDir = baseDir + SEPERATOR + "browndata";
	//
	// if (!new File(baseDir).isDirectory()) {
	// new File(baseDir).mkdirs();
	// }
	//
	// String fileName = baseDir + File.separator + dataFile;
	// try {
	// BrownFileReader reader = new BrownFileReader(dataFile, fileName,
	// "\\t", fieldProcessor);
	// reader.read();
	// } catch (Exception e) {
	// logger.info("Error reading " + dataFile);
	// System.out.println("Error reading " + dataFile);
	// e.printStackTrace();
	// }
	// }

	// protected JBStaticDataFile(String dataFile, InputStream inputStream,
	// BrownFileReader.FieldsProcessorI fieldProcessor) {
	// _fieldProcessor = fieldProcessor;
	// _inputStream = inputStream;
	// // String baseDir = System.getenv("CATALINA_BASE");
	// // if (baseDir == null ) {
	// // baseDir = System.getProperty("user.home");
	// // }
	// // baseDir = baseDir + SEPERATOR + "data";
	//
	// String baseDir = System.getenv("CATALINA_BASE");
	//
	// baseDir = baseDir + SEPERATOR + "browndata";
	//
	// if (!new File(baseDir).isDirectory()) {
	// new File(baseDir).mkdirs();
	// }
	//
	// String fileName = baseDir + File.separator + dataFile;
	// try {
	// BrownFileReader reader = new BrownFileReader(dataFile, fileName,
	// "\\t", fieldProcessor);
	// reader.read();
	// } catch (Exception e) {
	// logger.info("Error reading " + dataFile);
	// System.out.println("Error reading " + dataFile);
	// e.printStackTrace();
	// }
	// }

	protected BrownFileReader.FieldsProcessorI getFieldProcessor() {
		return _fieldProcessor;
	}
}