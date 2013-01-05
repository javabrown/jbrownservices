package com.jbrown.core.test;

import com.jbrown.cache.BrownDataCache;
import com.jbrown.core.data.CountryData;
import com.jbrown.core.util.BrownConstant;
import java.util.Arrays;

public class Test {
 public static void main(String[] xx){
	 String[] states = 
		 BrownDataCache.getInstance().getStatesName(BrownConstant.ISO_INDIA);
	 String[] cities = 
		 BrownDataCache.getInstance().getCities(BrownConstant.ISO_INDIA, 
				 "Jharkhand");	 
	 
	 System.out.println(Arrays.toString(states));
	 System.out.println(Arrays.toString(cities));
	 
	 System.out.println("Done");
 }
}
