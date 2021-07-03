package com.me.servicelayer;

import java.util.List;

import com.me.businesslayer.FilterFlights;
import com.me.businesslayer.Flights;
import com.me.businesslayer.Journey;
import com.me.businesslayer.User;
import com.me.datalayer.DbLayerForFlightBookApp;

public class ServiceForApp {
	private DbLayerForFlightBookApp dbLayerForFlightBookApp = DbLayerForFlightBookApp.getDbLayerForFlightBookApp();
	
	public int serveNewUserRegistration(User user) {
		return dbLayerForFlightBookApp.newUserRegistration(user);
	}
	public int serveBookJourney(Journey journey) {
		return dbLayerForFlightBookApp.bookJourney(journey);
	}
	public List<Flights> servegetDesiredFlights(FilterFlights filterFlights) {
		return dbLayerForFlightBookApp.getDesiredFlights(filterFlights);
	}
	public User login(User user) {
		return dbLayerForFlightBookApp.verifyLogin(user);
	}
	public List<Journey> journeyHistory(User user) {
		return dbLayerForFlightBookApp.travelHistory(user);
	}
	
}
