package com.hotel.client.common.security;

import java.util.TimerTask;

/**
 * Closes the client without asking for confirmation. Freeing resources that is
 * provided by the server.
 * @author Piyush
 */
public class AutomaticLogoutTask extends TimerTask {
	/**
	 * 
	 */
	public void run() {
		System.exit(0);
	}
}
