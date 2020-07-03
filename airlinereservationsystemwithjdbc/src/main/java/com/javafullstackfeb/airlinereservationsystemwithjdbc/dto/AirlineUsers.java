package com.javafullstackfeb.airlinereservationsystemwithjdbc.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;
@SuppressWarnings("serial")
@Data
public class AirlineUsers implements Serializable{
		private int id;
		private String name;
		private String emailId;
		@ToString.Exclude
		private String password;
		private long mobileNo;
		private String role;

	}