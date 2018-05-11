/*
 * Copyright (c) 2013 Rennover Technologies, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Rennover Technologies, Inc.
 */

/*
 * 
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.rennover.client.framework.application;

/**
 * @author Piyush
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public abstract class ApplicationHelper
{
	public static boolean isOptionDefined(String[] args, String option)
	{
		for (int i = 0; i < args.length; i++)
		{
			if (args[i].equals(option))
			{
				return true;
			}
		}
		return false;
	}

	public static int getNoOptionArgsCount(String[] args)
	{
		int nbArgs = 0;
		for (int i = 0; i < args.length; i++)
		{
			if (!args[i].startsWith("-"))
			{
				nbArgs++;
			}
		}
		return nbArgs;
	}

	public static String[] getNoOptionArgs(String[] args)
	{
		int nbArgs = getNoOptionArgsCount(args);

		String[] noOptionArgs = new String[nbArgs];

		if (nbArgs > 0)
		{
			int argsIndex = 0;
			for (int i = 0; i < args.length; i++)
			{
				String arg = args[i];
				if (!arg.startsWith("-"))
				{
					noOptionArgs[argsIndex] = arg;
					argsIndex++;
				}
			}
		}

		return noOptionArgs;
	}

	public static String getArgumentNoOption(String[] args, int index)
	{
		for (int i = 0; i < args.length; i++)
		{
			String arg = args[i];
			if (!arg.startsWith("-"))
			{
				if (index == 0)
				{
					return arg;
				} else
				{
					--index;
				}
			}
		}

		return "";
	}
}