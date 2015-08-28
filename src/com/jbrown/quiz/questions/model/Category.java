package com.jbrown.quiz.questions.model;

import com.jbrown.core.exception.BorwnException;
import com.jbrown.core.util.StringUtil;

public enum Category {
	MULTI_CHOICE("multi-choice"),
	SINGLE_CHOICE("single-choice"),
	FILL_IN_THE_BLANK("fill-in-the-blank"),
	MULTI_FILL_IN_THE_BLANK("multi-fill-in-the-blank"),
	YES_NO("YES_NO");
	
	private String _name;
	
	Category(String categoryName){
		_name = categoryName;
	}
	
	public String getName(){
	    return _name;
	}
	
	public boolean typeOf(Category cat){
		for(Category c : this.values()){
			if(c == cat){
				return true;
			}
		}
		
		return false;
	}
	
  public static Category find(String name) {
    for (Category c : Category.values()) {
      if (c.getName().equalsIgnoreCase(name)) {
        return c;
      }
    }

    throw new BorwnException(String.format("Unknown category %s found", name));
  }
}
