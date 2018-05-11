package com.hotel.client.common.security;

import com.hotel.client.main.ClientInitializer;
import com.rennover.hotel.common.idgen.IdGenerator;
import com.rennover.hotel.common.properties.CommonProperties;

public class DeconnexionApp
{
	public static void main(String[] args)
	{
		if (args.length > 0)
		{
			CommonProperties.setNoServerMode();
			IdGenerator.setUnplugged(true);
			ClientInitializer.init();
			//ClientFacadeInitializer.init(null, null);
			//FacadeFactory.getGestionConnexion().deconnecter(args[0]);
		}
		System.exit(0);
	}
}
