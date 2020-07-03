package com.javafullstackfeb.airlinereservationsystemwithjdbc.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.javafullstackfeb.airlinereservationsystemwithjdbc.exception.AirlineException;



public class Validations {

	public boolean idValidation(int id) throws AirlineException {
		String idRegEx = "[0-9]{1}[0-9]{3}";
		boolean result = false;
		if (Pattern.matches(idRegEx, String.valueOf(id))) {
			result = true;
		} else {
			throw new AirlineException("Invalid Id! Id should contain exactly 4 positive digits");
		}
		return result;
	}
	
	public boolean nameValidation(String name) throws AirlineException {
		String nameRegEx = "^(?=.{4,20}$)(?![_.-])(?!.*[.]{2})[a-zA-Z._-]+(?<![_.-])";
		boolean result = false;
		Pattern pattern = Pattern.compile(nameRegEx);
		Matcher matcher = pattern.matcher(name);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new AirlineException("Invalid Name! Name should have atleast 4 characters");
		}
		return result;
	}

	public boolean mobileValidation(long mobile) throws AirlineException {
		String mobileRegEx = "(0/91)?[6-9][0-9]{9}";
		boolean result = false;
		if (Pattern.matches(mobileRegEx, String.valueOf(mobile))) {
			result = true;
		} else {
			throw new AirlineException(
					"Enter a mobile number whose length is 10 digits and should start with 6,7,8,9 digits only");
		}
		return result;
	}

	public boolean emailValidation(String email) throws AirlineException {
		String emailRegEx = "^[a-z0-9](\\.?[a-z0-9]){5,}@g(oogle)?mail\\.(com|org)";
		boolean result = false;
		Pattern pattern = Pattern.compile(emailRegEx);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new AirlineException("Enter proper email such that it should consist of numbers and alphabets");
		}
		return result;
	}

	public boolean passwordValidation(String password) throws AirlineException {
		String passwordRegEx = "((?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%]).{6,20})";
		boolean result = false;
		if (Pattern.matches(passwordRegEx, String.valueOf(password))) {
			result = true;
		} else {
			throw new AirlineException("Password should contain atleast 6 characters ,one uppercase,one lowercase,one number,one special symbol(@#$%) ");
		}
		return result;
	}

	public boolean sourceValidation(String source) throws AirlineException {
		String nameRegEx = "^(?=.{4,20}$)(?![_.-])(?!.*[.]{2})[a-zA-Z._-]+(?<![_.-])";
		boolean result = false;
		Pattern pattern = Pattern.compile(nameRegEx);
		Matcher matcher = pattern.matcher(source);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new AirlineException("Invalid Source! Source should have atleast 4 characters");
		}
		return result;
	}
	public boolean destinationValidation(String Destination) throws AirlineException {
		String nameRegEx = "^(?=.{4,20}$)(?![_.-])(?!.*[.]{2})[a-zA-Z._-]+(?<![_.-])";
		boolean result = false;
		Pattern pattern = Pattern.compile(nameRegEx);
		Matcher matcher = pattern.matcher(Destination);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new AirlineException("Invalid Destination! Destination should have atleast 4 characters");
		}
		return result;
	}

	public boolean validatedRole(String role) throws AirlineException {
		String roleRegEx = "^(?i)(admin|user)$" ;
		boolean result = false;
		if(Pattern.matches(roleRegEx, String.valueOf(role))) {
			result = true;
		} else {
			throw new AirlineException("Enter admin or user as role ");
		}
		return result;
	}

	public boolean validatedPasswordLogin(String password) throws AirlineException {
		String passwordRegEx = "((?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%]).{6,20})";
		boolean result = false;
		if (Pattern.matches(passwordRegEx, String.valueOf(password))) {
			result = true;
		} else {
			throw new AirlineException(
					"Password should contain atleast 6 characters ,atleast one uppercase,atleast one lowercase,atleast one number,atleast one special symbol(@#$%) ");
		  
		}
		return result;
	}


}

