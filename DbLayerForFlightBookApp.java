package com.me.datalayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.me.businesslayer.FilterFlights;
import com.me.businesslayer.Flights;
import com.me.businesslayer.Journey;
import com.me.businesslayer.User;

public class DbLayerForFlightBookApp {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
	private DbLayerForFlightBookApp() {}
	
	public static DbLayerForFlightBookApp getDbLayerForFlightBookApp() {
		return new DbLayerForFlightBookApp();
	}
	
	private void establishConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/flight_book","root","12345");
	
	}
	private void prepareTheStatement(String query) throws SQLException {
		
		preparedStatement = connection.prepareStatement(query);
		
	}
	private void closeConnections() {
		try {
			if(connection != null) connection.close();
			if(preparedStatement != null) preparedStatement.close();
			if(resultSet != null) resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private int signUpNewUser(User user) {
		String query = "insert into user_data values(?,?,?,?,?,?);";
		
		try {
			establishConnection();
			prepareTheStatement(query);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getMobNum());
			preparedStatement.setString(3, user.getDob());
			preparedStatement.setString(4, user.getGender().substring(0,1));
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.setString(6, user.getEmail());
			
			return preparedStatement.executeUpdate();	
		} catch (SQLException | ClassNotFoundException e) {
			return 0;
		}
		finally {
			closeConnections();
		}	
	}
	
	private User checkLogin(User user) {
		String query = "select * from user_data where mobile_num=? and password=?";
		
		try {
			establishConnection();
			prepareTheStatement(query);
			preparedStatement.setString(1, user.getMobNum());
			preparedStatement.setString(2, user.getPassword());
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				user.setName(resultSet.getString("name"));
				user.setDob(resultSet.getString("dateofbirth"));
				user.setGender(resultSet.getString("gender"));
				user.setEmail(resultSet.getString("email"));
				return user;
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			closeConnections();
		}
		return null;
				
	}
	
	private int addJourneyForUser(Journey journey) {
		String query = "insert into journey values(?, ?, ?, ?, ?, ?);";
		
		try {
			establishConnection();
			prepareTheStatement(query);
			preparedStatement.setString(1, journey.getUserMobile());
			preparedStatement.setString(2, journey.getJourneeName());
			preparedStatement.setInt(3, journey.getJourneeAge());
			preparedStatement.setString(4, journey.getJourneeMobilenum());
			preparedStatement.setString(5, journey.getJourneyDate());
			preparedStatement.setInt(6, journey.getFlightNumber());
			
			return preparedStatement.executeUpdate();
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			closeConnections();
		}
		return 0;
	}
	private List<Flights> filterFlights(FilterFlights filterFlights) {
		String query = "select * from flight_details where flight_origin=? and flight_destination=?";
		List<Flights> searchedFlights=null;
		try {
			establishConnection();
			prepareTheStatement(query);
			preparedStatement.setString(1, filterFlights.getFlightOrigin());
			preparedStatement.setString(2, filterFlights.getFlightDestination());
			
			
			resultSet = preparedStatement.executeQuery();
			searchedFlights = new ArrayList<>();
			while(resultSet.next()) {
				Flights flight = new Flights();
				flight.setFlightNumber(resultSet.getInt("flight_number"));
				flight.setFlightName(resultSet.getString("flight_name"));
				flight.setFlightOrigin(resultSet.getString("flight_origin"));
				flight.setFlightDestination(resultSet.getString("flight_destination"));
				flight.setStartTime(resultSet.getString("start_time"));
				flight.setEndTime(resultSet.getString("end_time"));
				flight.setPrice(resultSet.getInt("price"));
				searchedFlights.add(flight);
			}
			
			 
				return searchedFlights;
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			closeConnections();
		}
		return null;
	}
	
	private List<Journey> jouneyHistory(User user) {
		String query = "select * from journey where user_mobile=? order by journey_date desc";
		try {
			establishConnection();
			prepareTheStatement(query);
			preparedStatement.setString(1, user.getMobNum());
			resultSet = preparedStatement.executeQuery();
			List<Journey> journeyList = new ArrayList<>();
			while(resultSet.next()) {
				Journey journey = new Journey();
				journey.setUserMobile(user.getMobNum());
				journey.setJourneeName(resultSet.getString("journee_name"));
				journey.setJourneeAge(resultSet.getInt("journee_age"));
				journey.setJourneeMobilenum(resultSet.getString("journee_mobilenum"));
				journey.setJourneyDate(resultSet.getString("journey_date"));
				journey.setFlightNumber(resultSet.getInt("flight_number"));
				journeyList.add(journey);
			}
				return journeyList;
					
		} catch (SQLException | ClassNotFoundException e) {
		}
		finally {
			closeConnections();
		}
		return null;
	}
	
	public int newUserRegistration(User user) {
		return signUpNewUser(user);
	}
	public int bookJourney(Journey journey) {
		return addJourneyForUser(journey);
	}
	public List<Flights> getDesiredFlights(FilterFlights filterFlights) {
		return filterFlights(filterFlights);
	}
	public User verifyLogin(User user) {
		return checkLogin(user);
	}
	public List<Journey> travelHistory(User user) {
		return jouneyHistory(user);
	}
}
