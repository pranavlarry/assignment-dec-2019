package com.assignment.core.service;

import java.util.ArrayList;

public interface MySqlGetDataService {
	public ArrayList getData(String query);
	
	public void setStatus(String[] id,String[] status);
}
