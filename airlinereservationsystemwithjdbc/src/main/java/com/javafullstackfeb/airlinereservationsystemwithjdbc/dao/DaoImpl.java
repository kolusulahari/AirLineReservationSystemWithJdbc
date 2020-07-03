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
		try (Connection conn = utility.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(utility.getQuery("addFlight"));) {
			pstmt.setInt(1, flightDetails.getFlightId());
			pstmt.setString(2, flightDetails.getFlightName());
			pstmt.setString(3, flightDetails.getSource());
			pstmt.setString(4, flightDetails.getDestination());
			pstmt.setInt(5, flightDetails.getNoofseatsavailable());
			pstmt.setDate(6, java.sql.Date.valueOf(flightDetails.getArrivalDate()));
			pstmt.setTime(7, java.sql.Time.valueOf(flightDetails.getArrivalTime()));
			pstmt.setDate(8, java.sql.Date.valueOf(flightDetails.getDepartureDate()));
			pstmt.setTime(9, java.sql.Time.valueOf(flightDetails.getDepartureTime()));
			

			pstmt.executeUpdate();

		} catch (Exception e) {
			throw new AirlineException("This Flight Credentials Already Exists");
		}
		return true;
	}

	@Override
	public boolean deletingFlight(int flightId) {
		try { 
				Connection conn = utility.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(utility.getQuery("removeFlight"));
			pstmt.setInt(1, flightId);
			int result = pstmt.executeUpdate();
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
		List<FlightDetails> flightList = new ArrayList<FlightDetails>();
		try (Connection conn = utility.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery(utility.getQuery("showFlights"))) {
			while (resultSet.next()) {
				FlightDetails info = new FlightDetails();
				info.setFlightId(resultSet.getInt("flight_id"));
				info.setFlightName(resultSet.getString("flight_name"));
				info.setSource(resultSet.getString("source"));
				info.setDestination(resultSet.getString("destination"));
				info.setNoofseatsavailable(resultSet.getInt("seats_available"));
				info.setArrivalDate(resultSet.getDate("arrival_date").toLocalDate());
				info.setArrivalTime(resultSet.getTime("arrival_time").toLocalTime());
				info.setDepartureDate(resultSet.getDate("departure_date").toLocalDate());
				info.setDepartureTime(resultSet.getTime("departur_time").toLocalTime());
				
				flightList.add(info);
			}
			if (flightList.isEmpty()) {
				throw new AirlineException("No Flight Present in the Airline");
			} else {
				return flightList;
			}
		} catch (Exception e) {
			throw new AirlineException(e.getMessage());
		}
	}

	@Override
	public List<BookingStatus> bookingStatus() {
		List<BookingStatus> bookingList = new LinkedList<BookingStatus>();
		try (Connection conn = utility.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery(utility.getQuery("showBooking"))) {
			while (resultSet.next()) {
				BookingStatus info = new BookingStatus();
				info.setFlightId(resultSet.getInt("flight_id"));
				info.setId(resultSet.getInt("id"));
				info.setNoofseatsbooked(resultSet.getInt("no_of_seats_booked"));
//				info.setTicketId(resultSet.getInt("ticket_id"));
				
				bookingList.add(info);
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
		try (Connection conn = utility.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(utility.getQuery("searchFlightByName"));) {
			pstmt.setString(1, flightname);
			try (ResultSet resultSet = pstmt.executeQuery();) {
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
		try (Connection conn = utility.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(utility.getQuery("searchFlightBySource"));) {
			pstmt.setString(1, source);
			try (ResultSet resultSet = pstmt.executeQuery();) {
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
		try (Connection conn = utility.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(utility.getQuery("searchFlightByDestination"));) {
			pstmt.setString(1, destination);
			try (ResultSet resultSet = pstmt.executeQuery();) {
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
		List<FlightDetails> flightList = new LinkedList<FlightDetails>();
		try (Connection conn = utility.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery(utility.getQuery("showFlights"))) {
			while (resultSet.next()) {
				FlightDetails info = new FlightDetails();
				info.setFlightId(resultSet.getInt("flight_id"));
				info.setFlightName(resultSet.getString("flight_name"));
				info.setSource(resultSet.getString("source"));
				info.setDestination(resultSet.getString("destination"));
				info.setNoofseatsavailable(resultSet.getInt("seats_available"));
				info.setArrivalDate(resultSet.getDate("arrival_date").toLocalDate());
				info.setArrivalTime(resultSet.getTime("arrival_time").toLocalTime());
				info.setDepartureDate(resultSet.getDate("departure_date").toLocalDate());
				info.setDepartureTime(resultSet.getTime("departur_time").toLocalTime());

				flightList.add(info);
			}
			if (flightList.isEmpty()) {
				throw new AirlineException("No Flight Present in the Airline");
			} else {
				return flightList;
			}
		} catch (Exception e) {
			throw new AirlineException(e.getMessage());
		}
	}

	@Override
	public BookingStatus requestBook(BookingStatus status) {
		int userId = status.getId();

		try (

			Connection conn = utility.getConnection();
			PreparedStatement getFlightPstmt = conn.prepareStatement(utility.getQuery("getFlight"));){

			getFlightPstmt.setInt(1, status.getFlightId());
			

			try (ResultSet getReqSet = getFlightPstmt.executeQuery();) {
				
				while (getReqSet.next()) {
					
					int bookFlightId = getReqSet.getInt("flight_id");
					
					if (status.getFlightId() == bookFlightId) {
						

						try (
							Connection conne = utility.getConnection();
							PreparedStatement getUserPstmt = conne.prepareStatement(utility.getQuery("getUser"));){
							
							getUserPstmt.setInt(1, status.getId());
							
							try (ResultSet getUser = getUserPstmt.executeQuery();) {
								
								while (getUser.next()) {
								
									int user = getUser.getInt("id");

									if (userId == user) {
										try (
											Connection conn1 = utility.getConnection();
											PreparedStatement getRequestPstmt = conn1.prepareStatement(utility.getQuery("requestBooked"));){
											getRequestPstmt.setInt(1, status.getId());
											getRequestPstmt.setInt(2, status.getFlightId());
											getRequestPstmt.setInt(3, status.getNoofseatsbooked());
											getRequestPstmt.executeUpdate();
											
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
		try (Connection conn = utility.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(utility.getQuery("cancelTicket"));) {
			pstmt.setInt(1, userId);
			int result = pstmt.executeUpdate();
			if (result != 0) {
				return true;
			}

		} catch (Exception e) {
			throw new AirlineException(e.getMessage());

		}
		throw new  AirlineException("Successfully Cancelled Your Ticket...!!");
	
	}

	@Override
	public List<BookingStatus> getTicketDetails(int userId) {
		List<BookingStatus> tickets = new ArrayList<BookingStatus>();
		try (Connection connection =utility.getConnection();
				PreparedStatement myStmt = connection.prepareStatement(utility.getQuery("ticketdetails"));) {
			myStmt.setInt(1, userId);
		ResultSet	rs = myStmt.executeQuery();
			while (rs.next()) {
				BookingStatus ticketBean1 = new BookingStatus();
				ticketBean1.setId(rs.getInt("id"));
				ticketBean1.setFlightId(rs.getInt("flight_id"));
				ticketBean1.setNoofseatsbooked(rs.getInt("noofseatsbooked"));
//				ticketBean1.setTicketId(rs.getInt("ticket_id"));
				tickets.add(ticketBean1);
			}
			if (tickets.isEmpty()) {
				throw new AirlineException("No Ticket Found with that user Id");
			} else {
				return tickets;
			}
		} catch (Exception e) {
			e.getMessage();
			throw new AirlineException("No tickets with this userid");
		}
	}
// ******************* Register And Login *********************//
	@Override
	public boolean register(AirlineUsers airlineRegister) {
		try {
			Connection conn = utility.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(utility.getQuery("addUser"));
			
			pstmt.setInt(1, airlineRegister.getId());
			pstmt.setString(2, airlineRegister.getName());
			pstmt.setString(3, airlineRegister.getEmailId());
			pstmt.setLong(4, airlineRegister.getMobileNo());
			pstmt.setString(5, airlineRegister.getPassword());
			pstmt.setString(6, airlineRegister.getRole());

			pstmt.executeUpdate();

		} catch (Exception e) {
			throw new AirlineException("Can't Add New admin, as Admin Already Exists");
		}
		return true;
	}

	@Override
	public AirlineUsers check(String email, String password) {
		AirlineUsers user = new AirlineUsers();

			try {
				Connection conn = utility.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(utility.getQuery("userLogin"));
				pstmt.setString(1, email);
				pstmt.setString(2, password);
				try  {
					ResultSet rs = pstmt.executeQuery();
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