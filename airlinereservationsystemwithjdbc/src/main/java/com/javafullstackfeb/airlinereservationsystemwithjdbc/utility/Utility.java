package com.javafullstackfeb.airlinereservationsystemwithjdbc.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.javafullstackfeb.airlinereservationsystemwithjdbc.exception.AirlineException;



public class Utility {

	public Connection getConnection() {
		Connection conn = null;
		try {
			FileInputStream inputStream = new FileInputStream("propertiesfile.properties");
			Properties properties = new Properties();
			properties.load(inputStream);
			Class.forName(properties.getProperty("Driver"));
			conn =  DriverManager.getConnection(properties.getProperty("dburl"));
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getQuery(String baseQuery) {
		String query = null;
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream("propertiesfile.properties");
			Properties properties = new Properties();
			properties.load(inputStream);
			query = properties.getProperty(baseQuery);

			return query;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


}