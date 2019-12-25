package com.assignment.core.models;

import java.sql.Date;
import java.sql.Time;

public class ApplicationForm {
	private Date date;
	private Time time;
	private String firstName;
	private String lastName;
	private String nationality;
	private String gender;
	private String age;
	private String gotSeaLegs;
	private String partOfOurTeam;
	private String friendOrRelativeWorkingWithUs;
	private String areasOfInterest;
	private String learnAboutThisJob;
	private String status;
	private int id;
	
	public ApplicationForm(Date date,Time time, String firstName, String lastName, String nationality, String gender,
			String age, String gotSeaLegs, String partOfOurTeam, String friendOrRelativeWorkingWithUs,
			String areasOfInterest, String learnAboutThisJob, String status,int id) {
		super();
		this.date = date;
		this.time= time;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationality = nationality;
		this.gender = gender;
		this.age = age;
		this.gotSeaLegs = gotSeaLegs;
		this.partOfOurTeam = partOfOurTeam;
		this.friendOrRelativeWorkingWithUs = friendOrRelativeWorkingWithUs;
		this.areasOfInterest = areasOfInterest;
		this.learnAboutThisJob = learnAboutThisJob;
		this.status = status;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public Date getDate() {
		return date;
	}
	public Time getTime() {
		return time;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGotSeaLegs() {
		return gotSeaLegs;
	}
	public void setGotSeaLegs(String gotSeaLegs) {
		this.gotSeaLegs = gotSeaLegs;
	}
	public String getPartOfOurTeam() {
		return partOfOurTeam;
	}
	public void setPartOfOurTeam(String partOfOurTeam) {
		this.partOfOurTeam = partOfOurTeam;
	}
	public String getFriendOrRelativeWorkingWithUs() {
		return friendOrRelativeWorkingWithUs;
	}
	public void setFriendOrRelativeWorkingWithUs(String friendOrRelativeWorkingWithUs) {
		this.friendOrRelativeWorkingWithUs = friendOrRelativeWorkingWithUs;
	}
	public String getAreasOfInterest() {
		return areasOfInterest;
	}
	public void setAreasOfInterest(String areasOfInterest) {
		this.areasOfInterest = areasOfInterest;
	}
	public String getLearnAboutThisJob() {
		return learnAboutThisJob;
	}
	public void setLearnAboutThisJob(String learnAboutThisJob) {
		this.learnAboutThisJob = learnAboutThisJob;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
	
}
