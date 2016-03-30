package com.jbrown.core.data.model;

import java.io.Serializable;

public interface DistrictI extends Serializable{
	String getStateName();
	String getDistrictName();
	PostOfficeI[] getPostOffices();
	void updatePostOffice(PostOfficeI[] postOfficeI);
}
