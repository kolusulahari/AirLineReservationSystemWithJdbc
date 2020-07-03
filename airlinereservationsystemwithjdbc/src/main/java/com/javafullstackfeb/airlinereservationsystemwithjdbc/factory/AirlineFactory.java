package com.javafullstackfeb.airlinereservationsystemwithjdbc.factory;

import com.javafullstackfeb.airlinereservationsystemwithjdbc.dao.Dao;
import com.javafullstackfeb.airlinereservationsystemwithjdbc.dao.DaoImpl;
import com.javafullstackfeb.airlinereservationsystemwithjdbc.service.Service;
import com.javafullstackfeb.airlinereservationsystemwithjdbc.service.ServiceImpl;

public class AirlineFactory {
	private AirlineFactory() {
		
	}
    public static Dao getDaoImplInstance() {
    	Dao admindao=new DaoImpl();
    	return admindao;
    }
    public static Service getServiceImplInstance() {
    	Service adminService=new ServiceImpl();
    	return adminService;
    }
}