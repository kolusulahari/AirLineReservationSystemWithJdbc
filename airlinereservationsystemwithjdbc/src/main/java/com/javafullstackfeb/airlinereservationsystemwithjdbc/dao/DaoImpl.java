package com.javafullstackfeb.airlinereservationsystemwithjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.javafullstackfeb.airlinereservationsystemwithjdbc.dto.AirlineUsers;
import com.javafullstackfeb.airlinereservationsystemwithjdbc.dto.BookingStatus;
import com.javafullstackfeb.airlinereservationsystemwithjdbc.dto.FlightDetails;
import com.javafullstackfeb.airlinereservationsystemwithjdbc.exception.AirlineException;
import com.javafullstackfeb.airlinereservationsystemwithjdbc.utility.Utility;


public class DaoImpl implements Dao {
	
	Utility utility=new Utility();
// ************************Admin Operations ***************//
	@Override
	public boolean addingFlights(FlightDetails flightDetails) {
		try (Connection connect = utility.getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(utility.getQuery("addFlight"));) {
			preparedStatement.setInt(1, flightDetails.getFlightId());
			preparedStatement.setString(2, flightDetails.getFlightName());
			preparedStatement.setString(3, flightDetails.getSource());
			preparedStatement.setString(4, flightDetails.getDestination());
			preparedStatement.setInt(5, flightDetails.getNoofseatsavailable());
			preparedStatement.setDate(6, java.sql.Date.valueOf(flightDetails.getArrivalDate()));
			preparedStatement.setTime(7, java.sql.Time.valueOf(flightDetails.getArrivalTime()));
			preparedStatement.setDate(8, java.sql.Date.valueOf(flightDetails.getDepartureDate()));
			preparedStatement.setTime(9, java.sql.Time.valueOf(flightDetails.getDepartureTime()));
			

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw new AirlineException("This Flight Already Exists");
		}
		return true;
	}

	@Override
	public boolean deletingFlight(int flightId) {
		try { 
				Connection connect = utility.getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(utility.getQuery("removeFlight"));
			preparedStatement.setInt(1, flightId);
			int result = preparedStatement.executeUpdate();
			if (result != 0) {
				return true;
			}

		} catch (Exception e) {
			throw new AirlineException(e.getMessage());

		}
		throw new  AirlineException("Cannot Find Any Flight With this Id");
	}

	@Override
	public List<FlightDetails> getFlightDetails() {
		List<FlightDetails> listOfFlights = new ArrayList<FlightDetails>();
		try (Connection connect = utility.getConnection();
				Statement statement = connect.createStatement();
				ResultSet resultSet = statement.executeQuery(utility.getQuery("showFlights"))) {
			while (resultSet.next()) {
				FlightDetails fDetails = new FlightDetails();
				fDetails.setFlightId(resultSet.getInt("flight_id"));
				fDetails.setFlightName(resultSet.getString("flight_name"));
				fDetails.setSource(resultSet.getString("source"));
				fDetails.setDestination(resultSet.getString("destination"));
				fDetails.setNoofseatsavailable(resultSet.getInt("seats_available"));
				fDetails.setArrivalDate(resultSet.getDate("arrival_date").toLocalDate());
				fDetails.setArrivalTime(resultSet.getTime("arrival_time").toLocalTime());
				fDetails.setDepartureDate(resultSet.getDate("departure_date").toLocalDate());
				fDetails.setDepartureTime(resultSet.getTime("departur_time").toLocalTime());
				
				listOfFlights.add(fDetails);
			}
			if (listOfFlights.isEmpty()) {
				throw new AirlineException("No Flight Present in the Airline");
			} else {
				return listOfFlights;
			}
		} catch (Exception e) {
			throw new AirlineException(e.getMessage());
		}
	}

	@Override
	public List<BookingStatus> bookingStatus() {
		List<BookingStatus> bookingList = new LinkedList<BookingStatus>();
		try (Connection connect = utility.getConnection();
				Statement statement = connect.createStatement();
				ResultSet resultSet = statement.executeQuery(utility.getQuery("showBooking"))) {
			while (resultSet.next()) {
				BookingStatus fDetails = new BookingStatus();
				fDetails.setFlightId(resultSet.getInt("flight_id"));
				fDetails.setId(resultSet.getInt("id"));
				fDetails.setNoofseatsbooked(resultSet.getInt("no_of_seats_booked"));
				fDetails.setTicketId(resultSet.getInt("ticket_id"));
				
				bookingList.add(fDetails);
			}
			if (bookingList.isEmpty()) {
				throw new AirlineException("No Booking Status Present in the Airline");
			} else {
				return bookingList;
			}
		} catch (Exception e) {
			throw new AirlineException(e.getMessage());
		}
	}

//************** User Operations *******************//
	
	@Override
	public List<FlightDetails> searchFlightByNameInUser(String flightname) {
		FlightDetails flight = null;
		List<FlightDetails> searchList = new ArrayList<FlightDetails>();
		try (Connection connect = utility.getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(utility.getQuery("searchFlightByName"));) {
			preparedStatement.setString(1, flightname);
			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				if (resultSet.next()) {
					flight = new FlightDetails();
					flight.setFlightId(resultSet.getInt("flight_Id"));
					flight.setFlightName(resultSet.getString("flight_name"));
					flight.setSource(resultSet.getString("source"));
					flight.setDestination(resultSet.getString("destination"));
					flight.setNoofseatsavailable(resultSet.getInt("seats_available"));
					flight.setArrivalDate(resultSet.getDate("arrival_date").toLocalDate());
					flight.setArrivalTime(resultSet.getTime("arrival_time").toLocalTime());
					flight.setDepartureDate(resultSet.getDate("departure_date").toLocalDate());
					flight.setDepartureTime(resultSet.getTime("departur_time").toLocalTime());

					searchList.add(flight);
					return searchList;
				}
			}
		} catch (Exception e) {
			throw new AirlineException(e.getMessage());
		}
		throw new AirlineException("Cannot find any Flight With This Name");
	}

	@Override
	public List<FlightDetails> searchFlightBySourceInUser(String source) {
		FlightDetails flight = null;
		List<FlightDetails> searchList = new ArrayList<FlightDetails>();
		try (Connection connect = utility.getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(utility.getQuery("searchFlightBySource"));) {
			preparedStatement.setString(1, source);
			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				if (resultSet.next()) {
					flight = new FlightDetails();
					flight.setFlightId(resultSet.getInt("flight_Id"));
					flight.setFlightName(resultSet.getString("flight_name"));
					flight.setSource(resultSet.getString("source"));
					flight.setDestination(resultSet.getString("destination"));
					flight.setNoofseatsavailable(resultSet.getInt("seats_available"));
					flight.setArrivalDate(resultSet.getDate("arrival_date").toLocalDate());
					flight.setArrivalTime(resultSet.getTime("arrival_time").toLocalTime());
					flight.setDepartureDate(resultSet.getDate("departure_date").toLocalDate());
					flight.setDepartureTime(resultSet.getTime("departur_time").toLocalTime());
					searchList.add(flight);
					return searchList;
				}
			}
		} catch (Exception e) {
			throw new AirlineException(e.getMessage());
		}
		throw new AirlineException("Cannot find any Flight With This Source");
	}

	@Override
	public List<FlightDetails> searchFlightByDestinationInUser(String destination) {
		FlightDetails flight = null;
		List<FlightDetails> searchList = new ArrayList<FlightDetails>();
		try (Connection connect = utility.getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(utility.getQuery("searchFlightByDestination"));) {
			preparedStatement.setString(1, destination);
			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				if (resultSet.next()) {
					flight = new FlightDetails();
					flight.setFlightId(resultSet.getInt("flight_Id"));
					flight.setFlightName(resultSet.getString("flight_name"));
					flight.setSource(resultSet.getString("source"));
					flight.setDestination(resultSet.getString("destination"));
					flight.setNoofseatsavailable(resultSet.getInt("seats_available"));
					flight.setArrivalDate(resultSet.getDate("arrival_date").toLocalDate());
					flight.setArrivalTime(resultSet.getTime("arrival_time").toLocalTime());
					flight.setDepartureDate(resultSet.getDate("departure_date").toLocalDate());
					flight.setDepartureTime(resultSet.getTime("departur_time").toLocalTime());
					searchList.add(flight);
					return searchList;
				}
			}
		} catch (Exception e) {
			throw new AirlineException(e.getMessage());
		}
		throw new AirlineException("Cannot find any Flight With This Destination");
	}

	@Override
	public List<FlightDetails> getFlightDetailsInUser() {
		List<FlightDetails> listOfFlights = new LinkedList<FlightDetails>();
		try (Connection connect = utility.getConnection();
				Statement statement = connect.createStatement();
				ResultSet resultSet = statement.executeQuery(utility.getQuery("showFlights"))) {
			while (resultSet.next()) {
				FlightDetails fDetails = new FlightDetails();
				fDetails.setFlightId(resultSet.getInt("flight_id"));
				fDetails.setFlightName(resultSet.getString("flight_name"));
				fDetails.setSource(resultSet.getString("source"));
				fDetails.setDestination(resultSet.getString("destination"));
				fDetails.setNoofseatsavailable(resultSet.getInt("seats_available"));
				fDetails.setArrivalDate(resultSet.getDate("arrival_date").toLocalDate());
				fDetails.setArrivalTime(resultSet.getTime("arrival_time").toLocalTime());
				fDetails.setDepartureDate(resultSet.getDate("departure_date").toLocalDate());
				fDetails.setDepartureTime(resultSet.getTime("departur_time").toLocalTime());

				listOfFlights.add(fDetails);
			}
			if (listOfFlights.isEmpty()) {
				throw new AirlineException("No Flight Present in the Airline");
			} else {
				return listOfFlights;
			}
		} catch (Exception e) {
			throw new AirlineException(e.getMessage());
		}
	}

	@Override
	public BookingStatus requestBook(BookingStatus status) {
		int userId = status.getId();

		try (

			Connection connect = utility.getConnection();
			PreparedStatement preparedStatement = connect.prepareStatement(utility.getQuery("getFlight"));){

			preparedStatement.setInt(1, status.getFlightId());
			

			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				
				while (resultSet.next()) {
					
					int bookFlightId = resultSet.getInt("flight_id");
					
					if (status.getFlightId() == bookFlightId) {
						

						try (
							Connection conne = utility.getConnection();
							PreparedStatement preparedStatement1 = conne.prepareStatement(utility.getQuery("getUser"));){
							
							preparedStatement1.setInt(1, status.getId());
							
							try (ResultSet getUser = preparedStatement1.executeQuery();) {
								
								while (getUser.next()) {
								
									int user = getUser.getInt("id");

									if (userId == user) {
										try (
											Connection conn1 = utility.getConnection();
											PreparedStatement preparedStatement2 = conn1.prepareStatement(utility.getQuery("requestBooked"));){
											preparedStatement2.setInt(1, status.getTicketId());
											preparedStatement2.setInt(2, status.getId());
											preparedStatement2.setInt(3, status.getFlightId());
											preparedStatement2.setInt(4, status.getNoofseatsbooked());
											
											preparedStatement2.executeUpdate();
											
											return status;

										} catch (Exception e) {
											throw new AirlineException("Can't request flight");
										}

									}
								}
							}
						} catch (Exception e) {
							throw new AirlineException(e.getMessage());
						}
					}
				}
			}
		} catch (AirlineException e) {
			throw new AirlineException(e.getMessage());
		} catch (Exception e) {
			throw new AirlineException(e.getMessage());
		}
		return null;
		
	}
	
	@Override
	public boolean cancelTicket(int userId) {
		try (Connection connect = utility.getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(utility.getQuery("cancelTicket"));) {
			preparedStatement.setInt(1, userId);
			int result = preparedStatement.executeUpdate();
			if (result != 0) {
				return true;
			}

		} catch (Exception e) {
			throw new AirlineException(e.getMessage());

		}
		return true;
	
	}

// ******************* Register And Login *********************//
	@Override
	public boolean register(AirlineUsers airlineRegister) {
		try {
			Connection connect = utility.getConnection();
			PreparedStatement preparedStatement = connect.prepareStatement(utility.getQuery("addUser"));
			
			preparedStatement.setInt(1, airlineRegister.getId());
			preparedStatement.setString(2, airlineRegister.getName());
			preparedStatement.setString(3, airlineRegister.getEmailId());
			preparedStatement.setLong(4, airlineRegister.getMobileNo());
			preparedStatement.setString(5, airlineRegister.getPassword());
			preparedStatement.setString(6, airlineRegister.getRole());

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw new AirlineException("Can't Add This User Already Exists");
		}
		return true;
	}

	@Override
	public AirlineUsers check(String email, String password) {
		AirlineUsers user = new AirlineUsers();

			try {
				Connection connect = utility.getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(utility.getQuery("userLogin"));
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, password);
				try  {
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						user.setEmailId(rs.getString("email_id"));
						user.setPassword(rs.getString("password"));
	                    user.setRole(rs.getString("role"));
					}
					return user;
			} catch (Exception e) {
				e.printStackTrace();
				throw new AirlineException("Invalid Login Credentials, Please Enter Correctly or Register");
			
			}
				} catch (Exception e) {
					e.printStackTrace();
					throw new AirlineException("Invalid Login Credentials, Please Enter Correctly or Register");
				
				}
	}

	
}