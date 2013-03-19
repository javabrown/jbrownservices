package com.jbrown.core.test;

import com.jbrown.cache.BrownDataCache;
import com.jbrown.core.data.CountryData;
import com.jbrown.ext.capsule.BrownGeoCapsule;
 
 
import com.jbrown.core.util.BrownConstant;
import java.util.Arrays;

public class Test {
 public static void main(String[] xx) throws Exception {
	 BrownGeoCapsule capsule = new BrownGeoCapsule();
	 System.out.println(capsule.getPostOfficesForCityId("1"));
 }
}
