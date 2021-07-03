package com.me.businesslayer;

public class FilterFlights {
	private String flightOrigin;
	private String flightDestination;
	
	public FilterFlights() {}
	public FilterFlights(String flightOrigin, String flightDestination) {
		super();
		this.flightOrigin = flightOrigin;
		this.flightDestination = flightDestination;
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
	
}
