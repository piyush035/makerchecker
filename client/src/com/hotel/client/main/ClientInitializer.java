
package com.hotel.client.main;

import com.hotel.client.common.exception.ClientExceptionManager;
import com.hotel.client.common.message.MessageConstants;
import com.rennover.client.framework.message.MessageFactory;
import com.rennover.client.framework.window.LookAndFeelManager;
import com.rennover.client.framework.window.WindowManager;
import com.rennover.hotel.common.log.Logger;
import com.rennover.hotel.common.properties.PropertiesManager;
import com.rennover.hotel.common.validation.ValidationService;

/**
 * move the client hands in this class for 
  * Centralize client initialization here.
 * 
 * @author Piyush
 */
public class ClientInitializer
{
	public static void init()
	{
		try
		{
			Logger.log(ClientInitializer.class, "Client initialization ...");
			Logger.log(ClientInitializer.class, "Deactivation of the Security Manager client ...");
			System.setSecurityManager(null);
			// PropertiesManager
			PropertiesManager.use(ClientInitializer.class, "common.context.properties");
			PropertiesManager.use(ClientInitializer.class, "client.context.properties");
			PropertiesManager.use(ClientInitializer.class, "client.properties");

			// Logger initialization
			Logger.init(PropertiesManager.load(ClientInitializer.class, "client.logger.properties"));

			// ValidationService initialization
			ValidationService.init();

			// WindowsManager initialization
			WindowManager.init();
			WindowManager.setDefaultTitle("Hotel Management");
			WindowManager.setExceptionManager(new ClientExceptionManager());

			// initialization look and feel
			LookAndFeelManager.setAlloyLookAndFeel();

			// MessageFactory initialization
			MessageFactory.addAllConstantsMessages(MessageConstants.class);

			// AcessManager initialization
			//AccessService.setAccessManager(UserContextManager.createAccessManager());

			// initialization facade client
			// initialization
			//FacadeFactory.addCallListener(new WindowFreezer());

			// initialization cache
			//CacheInitializer.init();

			Logger.debug(ClientInitializer.class, "Initialization successful customer!");
		} catch (RuntimeException e)
		{
			Logger.error(ClientInitializer.class, "Error initializing the customer", e);
			throw e;
		}
	}
}