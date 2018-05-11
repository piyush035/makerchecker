package com.hotel.base.common.facade;

import com.rennover.hotel.common.exception.TechnicalException;
import com.rennover.hotel.common.log.Logger;

/**
 * ...
 * 
 * @author Piyush
 */
public class ServerFacadeInitializer {
	private static boolean s_initialized;

	public static void main(String[] args) {
		if (!s_initialized) {
			synchronized (ServerFacadeInitializer.class) {
				if (!s_initialized) {
					// WARNING
					// do not set s_initialized to true
					// after initWithServerMode or it
					// goes into an infinite loop
					s_initialized = true;
					initWithServerMode();
				}
			}
		}
	}

	private static void initWithServerMode() {
		try {
			Logger.log(ServerFacadeInitializer.class,
					"Server initialization ...");
			FacadeFactoryBase.init(null, null);
			Logger.log(ServerFacadeInitializer.class,
					"Server initialization successful!");
		} catch (Exception exc) {
			Logger.error(ServerFacadeInitializer.class,
					"Server initialization error", exc);
			throw new TechnicalException("Server initialization error", exc);
		}
	}
}