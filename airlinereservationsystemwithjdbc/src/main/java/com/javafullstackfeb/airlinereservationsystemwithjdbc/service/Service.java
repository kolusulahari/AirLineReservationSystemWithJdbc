package com.javafullstackfeb.airlinereservationsystemwithjdbc.service;

import java.util.List;

import com.javafullstackfeb.airlinereservationsystemwithjdbc.dto.AirlineUsers;
import com.javafullstackfeb.airlinereservationsystemwithjdbc.dto.BookingStatus;
import com.javafullstackfeb.airlinereservationsystemwithjdbc.dto.FlightDetails;



public interface Service {
	// ******************Register And Login ***********************//
	
	public boolean register(AirlineUsers admin);
	public AirlineUsers check(String email, String password);
	
//  **********Admin Operations**********************//
	boolean addingFlights(FlightDetails flightDetails);
	boolean deletingFlight(int flightId);
	List<FlightDetails> getFlightDetails();
	List<BookingStatus> bookingStatus();
	
	
// ***************User Operations*************************//
	
	List<FlightDetails> searchFlightByNameInUser(String flightname);
	List<FlightDetails> searchFlightBySourceInUser(String source);
	List<FlightDetails> searchFlightByDestinationInUser(String destination);
	List<FlightDetails> getFlightDetailsInUser();
	BookingStatus requestBook(BookingStatus status);
	boolean cancelTicket(int userId);
	List<BookingStatus> getTicketDetails(int userId);
	
	
	//**************Service Validations ********************//
	boolean validatingFlightID(int flightid);
	boolean validatingFlightName(String flightname);
	boolean validatingSource(String source);
	boolean validatingDestination(String destination);
	boolean validatingName(String checkName);
	boolean validatingMobile(long checkMobile);
	boolean validatingEmail(String checkEmail);
	boolean validatingPassword(String checkPassword);



}

