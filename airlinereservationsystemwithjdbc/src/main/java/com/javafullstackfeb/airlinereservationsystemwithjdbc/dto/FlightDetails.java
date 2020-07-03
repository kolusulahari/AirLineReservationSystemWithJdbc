package com.javafullstackfeb.airlinereservationsystemwithjdbc.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;
@SuppressWarnings("serial")
@Data
public class FlightDetails implements Serializable{
	private int flightId;
	private String flightName;
	private String source;
	private String destination;
	private int noofseatsavailable;
	private LocalDate arrivalDate;
	private LocalTime arrivalTime;
	private LocalDate departureDate;
	private LocalTime departureTime;
	
}