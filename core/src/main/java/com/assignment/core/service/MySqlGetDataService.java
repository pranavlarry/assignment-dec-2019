package com.assignment.core.service;

import java.util.ArrayList;

public interface MySqlGetDataService {
	public ArrayList getData(String query);
	
	public void insert(String firstName,String lastName,String nationality,String gender,String age,String gotSeaLegs,String partOfOurTeam,String friendOrRelativeWorkingWithUs,String areasOfInterest,String learnAboutThisJob);
	
	public void setStatus(String[] id,String[] status);
}
