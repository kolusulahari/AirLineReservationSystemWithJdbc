package com.javafullstackfeb.airlinereservationsystemwithjdbc.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.javafullstackfeb.airlinereservationsystemwithjdbc.dto.AirlineUsers;
import com.javafullstackfeb.airlinereservationsystemwithjdbc.dto.BookingStatus;
import com.javafullstackfeb.airlinereservationsystemwithjdbc.dto.FlightDetails;
import com.javafullstackfeb.airlinereservationsystemwithjdbc.exception.AirlineException;
import com.javafullstackfeb.airlinereservationsystemwithjdbc.factory.AirlineFactory;
import com.javafullstackfeb.airlinereservationsystemwithjdbc.service.Service;

import lombok.extern.log4j.Log4j;
@Log4j
public  class SubAirlineController {
	
public static void arsController() {
	boolean exit = false;
	int checkId = 0;
	String checkName = null;
	long checkMobile = 0;
	String role = null;
	String checkEmail = null;
	String checkPassword = null;
	int flightId = 0;
	String flightName = null;
	String flightSource = null;
	String flightDestination = null;
    int SeatsAvailable = 0;
    LocalDate arrivalDate = null;
	LocalDate departureDate = null;
	LocalTime departureTime=null;
	LocalTime arrivalTime=null;
	

    Service service =AirlineFactory.getServiceImplInstance();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		do {
			try {
				log.info("************* Welcome To Airline Reservation System *************");
				log.info("1. Search Flight By Source");
				log.info("2. Search Flight By Destination");
				log.info("3. Search Flight By Name");
				log.info("4. View All Flight Details");
				log.info("5. Register");
				log.info("6. Login");
				int choice = scanner.nextInt();
				switch (choice) {
				
				case 1:
					log.info("Search Flight Details by Source : ");
					String source = scanner.next();

					FlightDetails bean3 = new FlightDetails();
					bean3.setSource(source);
					List<FlightDetails> flightSource1 = service.searchFlightBySourceInUser(source);
					for (FlightDetails flightBean : flightSource1) {
						if (flightBean!= null) {
							log.info( flightBean.getFlightId()+"       " + flightBean.getFlightName()+"        " +flightBean.getSource()+"   to    " + flightBean.getDestination()+"        " +java.sql.Date.valueOf(flightBean.getArrivalDate())+"        " +java.sql.Time.valueOf(flightBean.getArrivalTime())+"      "+java.sql.Date.valueOf(flightBean.getDepartureDate())+"     "+java.sql.Time.valueOf(flightBean.getDepartureTime())+"    "+flightBean.getNoofseatsavailable()+"(Seats Available)");
							
						} else {
							log.info("No Flights are available with this Source");
						}
					}
					break;

				case 2:

					log.info("Search flight by Destination : ");
					String destination= scanner.next();

					FlightDetails bean4 = new FlightDetails();
					bean4.setDestination(destination);
					List<FlightDetails> flightDestination1 = service.searchFlightByDestinationInUser(destination);
					
					for (FlightDetails flightBean : flightDestination1) {
						if (flightBean != null) {
							log.info( flightBean.getFlightId()+"       " + flightBean.getFlightName()+"        " +flightBean.getSource()+"   to    " + flightBean.getDestination()+"        " +java.sql.Date.valueOf(flightBean.getArrivalDate())+"        " +java.sql.Time.valueOf(flightBean.getArrivalTime())+"      "+java.sql.Date.valueOf(flightBean.getDepartureDate())+"     "+java.sql.Time.valueOf(flightBean.getDepartureTime())+"    "+flightBean.getNoofseatsavailable()+"(Seats Available)");
							
						
						} else {
							log.info("No Flights are available with this Destination");
						}
					}
					break;
				case 3:
					log.info(" Search Flight by Name : ");
					String name = scanner.next();

					FlightDetails bean5 = new FlightDetails();
					bean5.setFlightName(name);
					List<FlightDetails> fname = service.searchFlightByNameInUser(name);
					
					for (FlightDetails flightBean : fname) {
						if (flightBean!= null ) {
							log.info( flightBean.getFlightId()+"       " + flightBean.getFlightName()+"        " +flightBean.getSource()+"   to    " + flightBean.getDestination()+"        " +java.sql.Date.valueOf(flightBean.getArrivalDate())+"        " +java.sql.Time.valueOf(flightBean.getArrivalTime())+"      "+java.sql.Date.valueOf(flightBean.getDepartureDate())+"     "+java.sql.Time.valueOf(flightBean.getDepartureTime())+"    "+flightBean.getNoofseatsavailable()+"(Seats Available)");
							
						
						} else {
							log.info("No Flights are available with this Name");
						}
					}
					break;
				case 4:
					List<FlightDetails> info = service.getFlightDetails();
					
					for (FlightDetails flightBean : info) {
						if (flightBean != null) {
							log.info( flightBean.getFlightId()+"       " + flightBean.getFlightName()+"        " +flightBean.getSource()+"   to    " + flightBean.getDestination()+"        " +java.sql.Date.valueOf(flightBean.getArrivalDate())+"        " +java.sql.Time.valueOf(flightBean.getArrivalTime())+"      "+java.sql.Date.valueOf(flightBean.getDepartureDate())+"     "+java.sql.Time.valueOf(flightBean.getDepartureTime())+"    "+flightBean.getNoofseatsavailable()+"(Seats Available)");
							
						
						} else {
							log.info("No Flight are available in the Flight Details");
						}
					}
					break;
				case 5:
					try {
						do {
							try {
								log.info("Enter ID : ");
								checkId = scanner.nextInt();
								exit = true;
							} catch (InputMismatchException e) {
								log.error("ID should consist of only digits");
								exit = false;
								scanner.next();
							} catch (AirlineException e) {
								exit = false;
								log.error(e.getMessage());
							}
						} while (!exit);
						do {
							try {
								log.info("Enter Your Name : ");
								checkName = scanner.next();
								service.validatingName(checkName);
								exit = true;
							} catch (InputMismatchException e) {
								exit = false;
								log.error("Name should consists of only Alphabates");
							} catch (AirlineException e) {
								exit = false;
								log.error(e.getMessage());
							}
						} while (!exit);
						do {
							try {
								log.info("Enter Your MobileNumber : ");
								checkMobile = scanner.nextLong();
								service.validatingMobile(checkMobile);
								exit = true;
							} catch (InputMismatchException e) {
								log.error("Mobile Number  should consists of only numbers");
								exit = false;
								scanner.next();
							} catch (AirlineException e) {
								exit = false;
								log.error(e.getMessage());
							}
						} while (!exit);
						do {
							try {
								log.info("Enter Email to Register : ");
								checkEmail = scanner.next();
								service.validatingEmail(checkEmail);
								exit = true;
							} catch (InputMismatchException e) {
								exit = false;
								log.error(" Email should consist of numbers and alphabets");
							} catch (AirlineException e) {
								exit = false;
								log.error(e.getMessage());
							}
						} while (!exit);
						do {
							try {
								log.info("Enter Password :");
								checkPassword = scanner.next();
								service.validatingPassword(checkPassword);
								exit = true;
							} catch (InputMismatchException e) {
								exit = false;
								log.error("Password doesnt accept spaces ");
							} catch (AirlineException e) {
								exit = false;
								log.error(e.getMessage());
							}
						} while (!exit);
						role = "user";
						
						AirlineUsers bean1 = new AirlineUsers();
						bean1.setId(checkId);
						bean1.setName(checkName);
						bean1.setMobileNo(checkMobile);
						bean1.setEmailId(checkEmail);
						bean1.setPassword(checkPassword);
						bean1.setRole(role);

						boolean check = service.register(bean1);
						if (check) {
							log.info("Registered Successfully");
						} else {
							log.info("Already registered");
						}
						break;
						
	                  }catch (InputMismatchException e) {
	      				log.error("Invalid entry ");
	    				scanner.next();
	    				break;
	    			}
	                  catch (AirlineException e) {
						log.info(e.getMessage());
						break;
					}
				case 6 :
					log.info("Enter registered email to login : ");
					checkEmail = scanner.next();
					log.info("Enter registered Password to login : ");
					checkPassword = scanner.next();

					try {
						AirlineUsers authBean = service.check(checkEmail, checkPassword);
						if (authBean != null) {
							String admin="admin";
							String user="user";
							if(authBean.getRole().equals(admin)) {
								log.info("Logged in successfullyyy...!!!");
								do {
									try {
										log.info("1.   Add Flights");
										log.info("2.   Remove Flight");
										log.info("3.   View All FlightDetails");
										log.info("4.   Booking Status ");
										log.info("5.   Logout");
										int choice2 = scanner.nextInt();
										switch (choice2) {
										case 1:

											do {
												log.info("Enter FlightID to add : ");
												flightId = scanner.nextInt();
												try {
													service.validatingFlightID(flightId);
													exit = true;
												} catch (InputMismatchException e) {
													exit = false;
													log.error("Id should contains only digits");
												} catch (AirlineException e) {
													exit = false;
													log.error(e.getMessage());
												}
											} while (!exit);
											do {
												log.info("Enter FlightName : ");
												flightName= scanner.next();
												try {
													service.validatingFlightName(flightName);
													exit = true;
												} catch (InputMismatchException e) {
													exit = false;
													log.error("FlightName should contains only Alphabets");
												} catch (AirlineException e) {
													exit = false;
													log.error(e.getMessage());
												}
											} while (!exit);
											do {
												try {
												log.info("Enter Source : ");
												flightSource = scanner.next();
												
													service.validatingSource(flightSource);
													exit = true;
												} catch (InputMismatchException e) {
													exit = false;
													log.error("Source should contains only Alphabates");
												} catch (AirlineException e) {
													exit = false;
													log.error(e.getMessage());
												}
											} while (!exit);
											do {
												log.info("Enter Destination : ");
												flightDestination = scanner.next();
												try {
													service.validatingDestination(flightDestination);
													exit = true;
												} catch (InputMismatchException e) {
													exit = false;
													log.error("Destination should contains only Alphabates");
												} catch (AirlineException e) {
													exit = false;
													log.error(e.getMessage());
												}
											} while (!exit);
											do {
												log.info("Enter No.of seat Available in the Flight : ");
												SeatsAvailable = scanner.nextInt();
												try {
							
													exit = true;
												} catch (InputMismatchException e) {
													exit = false;
													log.error("noofSeatsAvailable should contains only digits");
												} catch (AirlineException e) {
													exit = false;
													log.error(e.getMessage());
												}
											} while (!exit);
											do {
												log.info("Enter  Flight Arrival Date  : ");

												try {
													arrivalDate = LocalDate.of(scanner.nextInt(), scanner.nextInt(),scanner.nextInt());
	                                                
													exit = true;
												} catch (InputMismatchException e) {
													exit = false;
													log.error("its should be in the form of yyyy,mm,dd only in digits");
													scanner.next();
												} catch (AirlineException e) {
													exit = false;
													log.error(e.getMessage());
												}
											} while (!exit);
											do {
												log.info("Enter  Flight Arrival Time : ");

												try {
													arrivalTime = LocalTime.of(scanner.nextInt(), scanner.nextInt(),scanner.nextInt());
	                                                
													exit = true;
												} catch (InputMismatchException e) {
													exit = false;
													log.error("its should contains only digits");
													scanner.next();
												} catch (AirlineException e) {
													exit = false;
													log.error(e.getMessage());
												}
											} while (!exit);
											do {
												log.info("Enter  Flight departure Date  : ");

												try {
													departureDate = LocalDate.of(scanner.nextInt(),scanner.nextInt(), scanner.nextInt());
													exit = true;
												} catch (InputMismatchException e) {
													exit = false;
													log.error("its should contains only digits ");
													scanner.next();
												} catch (AirlineException e) {
													exit = false;
													log.error(e.getMessage());
												}
											} while (!exit);
											do {
												log.info("Enter  Flight departure Time : ");

												try {
													departureTime = LocalTime.of(scanner.nextInt(),	scanner.nextInt(), scanner.nextInt());
													exit = true;
												} catch (InputMismatchException e) {
													exit = false;
													log.error("its should contains only digits ");
													scanner.next();
												} catch (AirlineException e) {
													exit = false;
													log.error(e.getMessage());
												}
											} while (!exit);
											FlightDetails bean1 = new FlightDetails();
											bean1.setFlightId(flightId);
											bean1.setFlightName(flightName);
											bean1.setSource(flightSource);
											bean1.setDestination(flightDestination);
											bean1.setNoofseatsavailable(SeatsAvailable);
											bean1.setArrivalDate(arrivalDate);
											bean1.setArrivalTime(arrivalTime);
											bean1.setDepartureDate(departureDate);
											bean1.setDepartureTime(departureTime);
											boolean check2 = service.addingFlights(bean1);
											if (check2) {
												log.info("Flight added of id = " + flightId);
											} else {
												log.info("Flight already exist of id = " + flightId);
											}
											break;
									
										case 2: 
											log.info("Remove Flight ");
											log.info("Enter Flight Id");
											int flightId3 = scanner.nextInt();
											service.validatingFlightID(flightId3);
											try {
											if (flightId3 == 0) {
												log.info("Please Enter the Valid FlightId");
											} else {
												FlightDetails bean6 = new FlightDetails();
												bean6.setFlightId(flightId3);
												boolean remove = service.deletingFlight(flightId3);
												if (remove) {
													log.info("The Flight is removed of Id = " + flightId3);
												} else {
													log.error("Not Found Any Flight With This Id");
												}
											}
											} catch (InputMismatchException e) {
												log.error("Invalid entry valid Id");
												scanner.nextLine();
											} catch (AirlineException e) {
												log.info(e.getMessage());
											}
											break;
											
										case 3 :
											List<FlightDetails> info1 = service.getFlightDetails();
											for (FlightDetails flightBean : info1) {
												if (flightBean != null) {
													log.info( flightBean.getFlightId()+"       " + flightBean.getFlightName()+"        " +flightBean.getSource()+"   to    " + flightBean.getDestination()+"        " +java.sql.Date.valueOf(flightBean.getArrivalDate())+"        " +java.sql.Time.valueOf(flightBean.getArrivalTime())+"      "+java.sql.Date.valueOf(flightBean.getDepartureDate())+"     "+java.sql.Time.valueOf(flightBean.getDepartureTime())+"    "+flightBean.getNoofseatsavailable()+"(Seats Available)");
													
												} else {
													log.info("No Flight are available in the Flight Details");
												}
											}
											break;
										case 4 :
											List<BookingStatus> booking = service.bookingStatus();
											for (BookingStatus request : booking) {
												if (request != null) {
													log.info("-----------------------------------------");
													log.info("TICKET ID  ---->  "+request.getTicketId());
													log.info("FLIGHT ID  ---->  "+request.getFlightId());
													log.info("USER ID    ---->  "+request.getId());
													log.info("SEATS BOOKED -->  "+request.getNoofseatsbooked());
													
												} else {
													log.info("Request not found in booking status");
												}
											}
											break;
										case 5 :
											log.info("Logged Out Successfullyyyyyyyyyy.....!!!");
											arsController();
										
										default:
											log.info("Invalid!! Please Enter Valid Choice");
											break;
										}
									} catch (InputMismatchException e) {
										log.error("Invalid entry please choose from above options");
										scanner.nextLine();
									}
									catch (AirlineException e) {
										log.info(e.getMessage());
										
									}catch (Exception e) {
										log.error("Invalid Credentials");
									}
								} while(true);
							}
							else if(authBean.getRole().equals(user)) {
								log.info("Logged In Successfully....!!!");
								do {
								try {
									
									log.info("1.   Search Flight By Source");
									log.info("2.   Search Flight By Destination");
									log.info("3.   Search Flight By Name");
									log.info("4.   View All Flight Details");
									log.info("5.   Book The Flight");
									log.info("6.   Cancel The Tickets");
									log.info("7.   Logout");
									int choice2 = scanner.nextInt();
									switch(choice2) {
									case 1:
										log.info("Search Flight Details by Source : ");
										String source1 = scanner.next();

										FlightDetails bean = new FlightDetails();
										bean.setSource(source1);
										List<FlightDetails> flightSource2 = service.searchFlightBySourceInUser(source1);
										for (FlightDetails flightBean : flightSource2) {
											if (flightBean!= null) {
												log.info( flightBean.getFlightId()+"       " + flightBean.getFlightName()+"        " +flightBean.getSource()+"   to    " + flightBean.getDestination()+"        " +java.sql.Date.valueOf(flightBean.getArrivalDate())+"        " +java.sql.Time.valueOf(flightBean.getArrivalTime())+"      "+java.sql.Date.valueOf(flightBean.getDepartureDate())+"     "+java.sql.Time.valueOf(flightBean.getDepartureTime())+"    "+flightBean.getNoofseatsavailable()+"(Seats Available)");
												
											} else {
												log.info("No Flights are available with this Source");
											}
										}
										break;
									case 2:
										log.info("Search flight by Destination : ");
										String destination1= scanner.next();

										FlightDetails bean6 = new FlightDetails();
										bean6.setDestination(destination1);
										List<FlightDetails> flightDestination2 = service.searchFlightByDestinationInUser(destination1);
										
										for (FlightDetails flightBean : flightDestination2) {
											if (flightBean != null) {
												log.info( flightBean.getFlightId()+"       " + flightBean.getFlightName()+"        " +flightBean.getSource()+"   to    " + flightBean.getDestination()+"        " +java.sql.Date.valueOf(flightBean.getArrivalDate())+"        " +java.sql.Time.valueOf(flightBean.getArrivalTime())+"      "+java.sql.Date.valueOf(flightBean.getDepartureDate())+"     "+java.sql.Time.valueOf(flightBean.getDepartureTime())+"    "+flightBean.getNoofseatsavailable()+"(Seats Available)");
												
											
											} else {
												log.info("No Flights are available with this Destination");
											}
										}
										break;
									case 3:
										log.info(" Search Flight by Name : ");
										String name1 = scanner.next();

										FlightDetails bean7 = new FlightDetails();
										bean7.setFlightName(name1);
										List<FlightDetails> fname1 = service.searchFlightByNameInUser(name1);
										
										for (FlightDetails flightBean : fname1) {
											if (flightBean!= null ) {
												log.info( flightBean.getFlightId()+"       " + flightBean.getFlightName()+"        " +flightBean.getSource()+"   to    " + flightBean.getDestination()+"        " +java.sql.Date.valueOf(flightBean.getArrivalDate())+"        " +java.sql.Time.valueOf(flightBean.getArrivalTime())+"      "+java.sql.Date.valueOf(flightBean.getDepartureDate())+"     "+java.sql.Time.valueOf(flightBean.getDepartureTime())+"    "+flightBean.getNoofseatsavailable()+"(Seats Available)");
												
											
											} else {
												log.info("No Flights are available with this Name");
											}
										}
										break;
									case 4:
										List<FlightDetails> info1 = service.getFlightDetails();
										
										for (FlightDetails flightBean : info1) {
											if (flightBean != null) {
												log.info( flightBean.getFlightId()+"       " + flightBean.getFlightName()+"        " +flightBean.getSource()+"   to    " + flightBean.getDestination()+"        " +java.sql.Date.valueOf(flightBean.getArrivalDate())+"        " +java.sql.Time.valueOf(flightBean.getArrivalTime())+"      "+java.sql.Date.valueOf(flightBean.getDepartureDate())+"     "+java.sql.Time.valueOf(flightBean.getDepartureTime())+"    "+flightBean.getNoofseatsavailable()+"(Seats Available)");
												
											
											} else {
												log.info("No Flight are available in the Flight Details");
											}
										}
										break;
									case 5:
										
										try {
											log.info("Enter User Id : ");
											int userId2 = scanner.nextInt();
											AirlineUsers userBean = new AirlineUsers();
											userBean.setId(userId2);
											log.info("Enter Flight Id : ");
											int flightId2 = scanner.nextInt();
											FlightDetails flightDetails1 = new FlightDetails();
											flightDetails1.setFlightId(flightId2);
											log.info("Enter No of seats : ");
											int bookingId = (int) (Math.random() * 10000);
											if (bookingId <= 1000) {
												bookingId = bookingId + 1000;
											}
											int seats = scanner.nextInt();
											BookingStatus bookingStatus = new BookingStatus();
											bookingStatus.setTicketId(bookingId);
											bookingStatus.setId(userId2);
											bookingStatus.setFlightId(flightId2);
											bookingStatus.setNoofseatsbooked(seats);
											
											try {
												
												BookingStatus request = service.requestBook(bookingStatus);
												log.info("Request placed to Airline Management ");
												log.info("Booking Successfull");
												log.info("TICKET ID  ---->  "+request.getTicketId());
												log.info("FLIGHT ID  ---->  "+request.getFlightId());
												log.info("USER ID    ---->  "+request.getId());
												log.info("SEATS BOOKED -->  "+request.getNoofseatsbooked());
														
											} catch (Exception e) {
												log.info("Invalid Request of booking");
											}
										} catch (InputMismatchException e) {
											log.error("Invalid entry  ");
											scanner.nextLine();
										} catch (Exception e) {
											log.info("Invalid request");
										}
										break;
										
									case 6:
										log.info("Enter User Id To Cancel The Ticket ");
										try {
											int userId = scanner.nextInt();
											service.cancelTicket(userId);
											if (userId == 0) {
												log.info("Please Enter the Valid User Id");
											} else {
												BookingStatus book = new BookingStatus();
												book.setId(userId);
												boolean remove = service.cancelTicket(userId);
												if (remove) {
													log.info("The Cancel user Id = " + userId);
												} else {
													log.info("The User Id is not removed = " + userId);
												}
											}
										} catch (InputMismatchException e) {
											log.error("Invalid entry valid Id");
											scanner.nextLine();
										} catch (AirlineException e) {
											log.info(e.getMessage());
										}
										break;
									case 7:
										log.info("Logged Out Successfullyyyyyyyyyy.....!!!");
										arsController();
									
									default:
										log.info("Invalid!! Please Enter Valid Choice");
										break;
									
									}
										
										
										
									} catch (InputMismatchException e) {
										log.error("Invalid entry please choose from above options");
										scanner.nextLine();
									}
									catch (AirlineException e) {
										log.info(e.getMessage());
										
									}catch (Exception e) {
										log.error("Invalid Credentials");
									}
									
								} while(true);
							}
						} else {
							log.info("emailid and password should not be null ");
						}
					} catch (AirlineException e) {
						log.info(e.getMessage());
					}
					break;
					
				default:
					log.info("Invalid Choice");
					log.error("Invalid entry please choose from above options");
					break;
					
				} // switch 1 close 
				
			}  //try 1 closing 
			catch (InputMismatchException e) { 
			log.error("Invalid entry please choose from above options");
			scanner.nextLine();
		}
		catch (AirlineException e) {
			log.info(e.getMessage());
			
		}catch (Exception e) {
			log.error("Invalid Credentials");
		}
		} while(true); //  do 1 closing
	
	

	} // Method Closing

}// Class Closing