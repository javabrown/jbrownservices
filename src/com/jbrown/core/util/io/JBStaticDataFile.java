package com.jbrown.core.util.io;

import java.io.File;
import java.util.logging.Logger;
 
public abstract class JBStaticDataFile {
	  private static Logger logger =
	    Logger.getLogger(JBStaticDataFile.class.getName());
	  
	  private static String SEPERATOR = File.separator;
	  
	  private final BrownFileReader.FieldsProcessorI _fieldProcessor;
	    
	  protected JBStaticDataFile(String dataFile, 
			  BrownFileReader.FieldsProcessorI fieldProcessor) {
	    _fieldProcessor = fieldProcessor;
	    
	    //String baseDir = System.getenv("CATALINA_BASE");
	    //if  (baseDir == null ) {
	    //  baseDir = System.getProperty("user.home");      
	    //}
	    //baseDir = baseDir + SEPERATOR + "data";
	    String baseDir = "data";
	    
	    if (!new File(baseDir).isDirectory()) {
	      new File(baseDir).mkdirs();
	    }
	    
	    String fileName = baseDir + File.separator + dataFile;    
	    try {
	    	BrownFileReader reader = 
	        new BrownFileReader(dataFile, fileName, "\\t", fieldProcessor);
	      reader.read();
	    }
	    catch(Exception e) {
	      logger.info("Error reading " + dataFile);
	      System.out.println("Error reading " + dataFile);
	      e.printStackTrace();
	    }    
	  }
	  
	  protected BrownFileReader.FieldsProcessorI getFieldProcessor() {
	    return _fieldProcessor;
	  }
	}