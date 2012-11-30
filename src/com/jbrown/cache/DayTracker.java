package com.jbrown.cache;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DayTracker {
	  private Calendar _currentCalendar = null;

	  public DayTracker() {
	    _currentCalendar = new GregorianCalendar();
	  }
	  
	  public DayTracker(Calendar calendar) {
	    _currentCalendar = calendar;
	  }

	  public boolean isNewDay(boolean isDestructive) {    
	    Calendar rightNow = new GregorianCalendar();

	    int year2 = rightNow.get(Calendar.YEAR);
	    int day2 = rightNow.get(Calendar.DAY_OF_YEAR);
	    int year1 = 0;
	    int day1 = 0;

	    if (_currentCalendar != null) {
	      year1 = _currentCalendar.get(Calendar.YEAR);
	      day1 = _currentCalendar.get(Calendar.DAY_OF_YEAR);
	    }

	    if ( _currentCalendar == null || 
	         (year2 > year1 || (day2 > day1 && year1 == year2))) {
	      if (isDestructive) 
	        _currentCalendar = rightNow;
	      return true;
	    }
	    return false;
	  }
	}