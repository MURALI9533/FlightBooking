package com.me.uilayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.me.businesslayer.FilterFlights;
import com.me.businesslayer.Journey;
import com.me.businesslayer.User;
import com.me.servicelayer.ServiceForApp;
import com.me.businesslayer.Flights;

public class App {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public boolean registerNewUser() {
		User userN = new User();
		try {
			System.out.println("Enter Name");
			userN.setName(br.readLine());
			System.out.println("Enter MobileNumber");
			userN.setMobNum(br.readLine());
			System.out.println("Enter Date Of Birth");
			userN.setDob(br.readLine());
			System.out.println("Enter Gender");
			userN.setGender(br.readLine().substring(0,1));
			System.out.println("Enter Password");
			String passw = br.readLine();
			System.out.println("Confirm Password");
			String cnfmPassw = br.readLine();
			if(passw.equals(cnfmPassw)) {
				userN.setPassword(passw);
				System.out.println("Enter email");
				userN.setEmail(br.readLine());
				
				int act = new ServiceForApp().serveNewUserRegistration(userN);
				if(act==1) {
					System.out.println("Successfully Registered...");
					return true;
				}
				else if(act==0) {
					System.out.println("Record already present with the mobile number or error occured\nTry Login/Try again...");
					return false;
				}
			}
			else {
				System.out.println("Password Missmatch Try again...");
			}
		} catch (IOException e) {
			System.out.println("Error while reading inputs\nTry again...");
		}
		return false;
		
	}
	public User loginUser() {
		User user = new User();
		try {
			System.out.println("Enter MobileNumber");
			user.setMobNum(br.readLine());
			System.out.println("Enter Password");
			user.setPassword(br.readLine());
			user = new ServiceForApp().login(user);
			return user;
		} catch (IOException e) {
			return null;
		}
	}
	
	public Journey findJourney(User user) {
		FilterFlights flights = new FilterFlights();
		Journey journey=null;
		try {
			System.out.println("Enter Souce place to Start");
			flights.setFlightOrigin(br.readLine());
			System.out.println("Enter Destination place");
			flights.setFlightDestination(br.readLine());
			List<Flights> availFlights = new ServiceForApp().servegetDesiredFlights(flights);
			int flightInd=0;
			if(availFlights!=null) {
				if(availFlights.isEmpty()) {
					System.out.println("No flights availble on th root\nPlease select another root");
					return null;
				}
				for(Flights eachAvailFlights:availFlights) {
					System.out.println("Choice:"+ ++flightInd +"-->"+eachAvailFlights);
				}
				System.out.println("Enter your option of flight");
				int choiceOfFlight=Integer.parseInt(br.readLine());
				if(choiceOfFlight>0 && choiceOfFlight<=availFlights.size()) {
					journey = new Journey();
					journey.setFlightNumber(availFlights.get(choiceOfFlight-1).getFlightNumber());
					journey.setUserMobile(user.getMobNum());
					System.out.println("Enter Journee Name");
					journey.setJourneeName(br.readLine());
					System.out.println("Enter Journee Age");
					journey.setJourneeAge(Integer.parseInt(br.readLine()));
					System.out.println("Enter Journee Mobile Number");
					journey.setJourneeMobilenum(br.readLine());
					System.out.println("Enter Journey Date");
					journey.setJourneyDate(br.readLine());
				}
				else {
					System.out.println("Invalid Choice of Flight Try booking again");
				}
				
			}
			else {
				System.out.println("Error occured...");
			}
			
		} catch (IOException | NumberFormatException e) {
			System.out.println("Invalid Entry");
			return null;
		}
		return journey;
	}
	
	public boolean addJourneyToUser(Journey journey) {
		int act = new ServiceForApp().serveBookJourney(journey);
		if(act==1) {
			System.out.println("Journey added to your Account");
			return true;
		}
		
		System.out.println("Journey Not added due some error \nTry again...");
		return false;
	
	}
	public boolean journeyHistory(User user) {
		List<Journey> userJourneys = new ServiceForApp().journeyHistory(user);
		if(userJourneys!=null) {
			if(userJourneys.isEmpty()) {
				System.out.println("No Journeys Found...");
				return true;
			}
			for(Journey journey:userJourneys) {
				System.out.println(journey);
			}
			return true;
		}
		System.out.println("Error occured...");
		return false;
	}
	
	public static void main(String[] args) {
		int choice1 = 0;
		do {
			System.out.println("1.Register\n2.Login\n3.Exit");
			try {
				choice1 = Integer.parseInt(br.readLine());
			} catch (IOException | NumberFormatException e) {
				System.out.println("Invalid entry\nTry Again...");
				continue;
			}
			switch(choice1) {
			case 1: new App().registerNewUser();
				break;
			case 2:	{
				User user = new App().loginUser();
				if(user!=null) {
					int choice2=0;
					do {
						System.out.println("1.Book Ticket\n2.History of Journeys\n3.Exit");
						try {
							choice2 = Integer.parseInt(br.readLine());
						} catch (IOException | NumberFormatException e) {
							System.out.println("Invalid entry\nTry Again...");
							continue;
						} 
						switch(choice2) {
						case 1: Journey journey = new App().findJourney(user);
								if(journey!=null) {
									new App().addJourneyToUser(journey);
								}
							break;
						case 2: new App().journeyHistory(user);
							break;
						case 3: System.out.println("Exiting");
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								Thread.currentThread().interrupt();
								e.printStackTrace();
							}
							System.out.println("Log out successful...");
							break;
							default:System.out.println("Invalid Choice...\nTry Again");
							break;
						}
					} while(choice2!=3);
				}
			}
				break;
			case 3: System.out.println("Exiting ");
				break;
				default:System.out.println("Invalid Choice\nTry Again...");
			}
		} while(choice1!=3);
	}
}
