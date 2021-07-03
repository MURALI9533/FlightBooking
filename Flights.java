package com.me.businesslayer;

public class Flights {
	private int flightNumber;
	private String flightName;
	private String flightOrigin;
	private String flightDestination;
	private String startTime;
	private String endTime;
	private int price;
	
	public Flights() {}
	public Flights(int flightNumber, String flightName, String flightOrigin, String flightDestination,
			String startTime, String endTime, int price) {
		super();
		this.flightNumber = flightNumber;
		this.flightName = flightName;
		this.flightOrigin = flightOrigin;
		this.flightDestination = flightDestination;
		this.startTime = startTime;
		this.endTime = endTime;
		this.price = price;
	}
	public int getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public String getFlightOrigin() {
		return flightOrigin;
	}
	public void setFlightOrigin(String flightOrigin) {
		this.flightOrigin = flightOrigin;
	}
	public String getFlightDestination() {
		return flightDestination;
	}
	public void setFlightDestination(String flightDestination) {
		this.flightDestination = flightDestination;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "\n[flightNumber=" + flightNumber + ", flightName=" + flightName + ", flightOrigin=" + flightOrigin
				+ ", flightDestination=" + flightDestination + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", price=" + price + "]";
	}
	
	
		
}
