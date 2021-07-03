package com.me.businesslayer;

import com.me.textdesign.DisplayDesign;

public class Journey {
	private String userMobile;
	private String journeeName;
	private int journeeAge;
	private String journeeMobilenum;
	private String journeyDate;
	private int flightNumber;
	public Journey() {}
	public Journey(String userMobile, String journeeName, int journeeAge, String journeeMobilenum, String journeyDate,
			int flightNumber) {
		super();
		this.userMobile = userMobile;
		this.journeeName = journeeName;
		this.journeeAge = journeeAge;
		this.journeeMobilenum = journeeMobilenum;
		this.journeyDate = journeyDate;
		this.flightNumber = flightNumber;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getJourneeName() {
		return journeeName;
	}
	public void setJourneeName(String journeeName) {
		this.journeeName = journeeName;
	}
	public int getJourneeAge() {
		return journeeAge;
	}
	public void setJourneeAge(int journeeAge) {
		this.journeeAge = journeeAge;
	}
	public String getJourneeMobilenum() {
		return journeeMobilenum;
	}
	public void setJourneeMobilenum(String journeeMobilenum) {
		this.journeeMobilenum = journeeMobilenum;
	}
	public String getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(String journeyDate) {
		this.journeyDate = journeyDate;
	}
	public int getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}
	@Override
	public String toString() {
		return "Journee Name      : " + DisplayDesign.design(getJourneeName()) +
			   "Journee Age       : " + getJourneeAge() + "\n"
			 + "flightNumber      : " + DisplayDesign.design(String.valueOf(getFlightNumber())) + 
			   "Journey Date      : " + getJourneyDate() +"\n";
	}


}
