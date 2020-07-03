package com.javafullstackfeb.airlinereservationsystemwithjdbc.service;

import java.util.List;


import com.javafullstackfeb.airlinereservationsystemwithjdbc.dao.Dao;
import com.javafullstackfeb.airlinereservationsystemwithjdbc.dto.AirlineUsers;
import com.javafullstackfeb.airlinereservationsystemwithjdbc.dto.BookingStatus;
import com.javafullstackfeb.airlinereservationsystemwithjdbc.dto.FlightDetails;
import com.javafullstackfeb.airlinereservationsystemwithjdbc.exception.AirlineException;
import com.javafullstackfeb.airlinereservationsystemwithjdbc.factory.AirlineFactory;
import com.javafullstackfeb.airlinereservationsystemwithjdbc.validation.Validations;


public class ServiceImpl implements Service{
	Validations validation = new Validations();
	Dao daoRef = AirlineFactory.getDaoImplInstance();
	
	// ***************************** Admin Operations ********************//
	@Override
	public boolean addingFlights(FlightDetails flightDetails) {
		if(flightDetails!=null) {
			return daoRef.addingFlights(flightDetails);
			}
			throw new AirlineException("Enter Correct details");
			
	}
	@Override
	public boolean deletingFlight(int flightId) {
		if (validation.idValidation(flightId)) {
			return daoRef.deletingFlight(flightId);
		}
		return false;
	}
	@Override
	public List<FlightDetails> getFlightDetails() {
		return daoRef.getFlightDetails();
	}
	@Override
	public List<BookingStatus> bookingStatus() {
		return daoRef.bookingStatus();
	}

	//************************* User Operations *************************//

	@Override
	public List<FlightDetails> searchFlightByNameInUser(String flightname) {
		if (validation.nameValidation(flightname)) {
			return daoRef.searchFlightByNameInUser(flightname);
		}
		return null;
	}
	@Override
	public List<FlightDetails> searchFlightBySourceInUser(String source) {
		if (validation.sourceValidation(source)) {
			return daoRef.searchFlightBySourceInUser(source);
		}
		return null;
	}
	@Override
	public List<FlightDetails> searchFlightByDestinationInUser(String destination) {
		if (validation.destinationValidation(destination)) {
			return daoRef.searchFlightByDestinationInUser(destination);
		}
		return null;
	}
	@Override
	public List<FlightDetails> getFlightDetailsInUser() {
		return daoRef.getFlightDetailsInUser();

	}
	@Override
	public BookingStatus requestBook(BookingStatus status) {
				return daoRef.requestBook(status);
			
	}
	
	@Override
	public boolean cancelTicket(int userId) {
		if (validation.idValidation(userId)) {
			return daoRef.cancelTicket(userId);
		}
		return false;
	}
	
	//************************ Service Validation *********************//
	
	@Override
	public boolean validatingFlightID(int flightid) {
		if(validation.idValidation(flightid)){
		  return true;
		}
		throw new AirlineException("Invalid Id! Id should contain exactly 4 positive digits");
	}
	@Override
	public boolean validatingFlightName(String flightname) {
	    if(validation.nameValidation(flightname)) {
	    	return true;
	    }
	    throw new AirlineException("Invalid Name! Name should have atleast 4 characters");
	}
	@Override
	public boolean validatingSource(String source) {
      if(validation.sourceValidation(source)) {
    	  return true;
      }
      
      throw new AirlineException("Invalid Source! Source should have atleast 4 characters");
	}

	@Override
	public boolean validatingDestination(String destination) {
		if(validation.destinationValidation(destination)) {
			return true;
		}
		throw new AirlineException("Invalid Destination! Destination should have atleast 4 characters");
	}

	@Override
	public boolean validatingName(String checkName) {
		 if(validation.nameValidation(checkName)) {
		    	return true;
		    }
		    throw new AirlineException("Invalid Name! Name should have atleast 4 characters");
	}
	
	@Override
	public boolean validatingMobile(long checkMobile) {
		if(validation.mobileValidation(checkMobile)) {
			return true;
		}
		throw new AirlineException("Enter Proper Mobile Number");
	}

	@Override
	public boolean validatingEmail(String checkEmail) {
		if(validation.emailValidation(checkEmail)) {
		return false;
		}
		throw new AirlineException("Enter proper email such that it should consist of numbers and alphabets For ex:- chandu3@gmail.com");
	}

	@Override
	public boolean validatingPassword(String checkPassword) {
		if(validation.passwordValidation(checkPassword)) {
		return false;
		}
		throw new AirlineException("Password should contain atleast 6 characters ,one uppercase,one lowercase,one number,one special symbol(@#$%) For ex:- @Chandu123");
	}


	//*************Register And Login **********************//
	@Override
	public boolean register(AirlineUsers register) {
		if(validation.idValidation(register.getId())) {
			if(validation.nameValidation(register.getName())) {
				if(validation.mobileValidation(register.getMobileNo())) {
					if(validation.emailValidation(register.getEmailId())) {
						if(validation.passwordValidation(register.getPassword())) {
							if(validation.validatedRole(register.getRole())) {
							return daoRef.register(register);
							}
						}
					}
					
				}
			}
		}
		throw new AirlineException("invalid inputs");
	}

	@Override
	public AirlineUsers check(String email, String password) {
		if (validation.emailValidation(email)) {
			if (validation.passwordValidation(password)) {
				return daoRef.check(email, password);
			} 
		} 
		return null;
	}

}
