package com.hotel.common.facade;

import com.rennover.hotel.common.log.Logger;

/**
 * ...
 * 
 * @author Piyush
 */
public class ClientFacadeInitializer {
	/**
	 * 
	 * @param login
	 * @param password
	 */
	public static void init(String login, String password) {
		Logger.log(ClientFacadeInitializer.class,
				"Initialize the Client Facade ...");
		FacadeFactory.init(login, password);
		Logger.log(ClientFacadeInitializer.class,
				"Initialization successful customer panel!");
	}

}