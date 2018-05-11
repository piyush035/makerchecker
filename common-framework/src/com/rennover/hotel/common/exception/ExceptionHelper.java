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
package com.rennover.hotel.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author dattias
 */
public class ExceptionHelper
{
	/**
	 * Retourne la trace d'exécution de l'exception passé en paramètre.
	 * 
	 */
	public static String getStackTrace(Throwable throwable)
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		throwable.printStackTrace(pw);

		return sw.toString();
	}

	public static String getInternalMessage(Throwable throwable)
	{
		if (throwable instanceof WrapperException)
		{
			return ((WrapperException) throwable).getInternalMessage();
		}

		return throwable.getMessage();
	}

	/**
	 * Retourne la trace d'exécution AVEC le message.
	 * 
	 * Exemple :
	 * 
	 * java.lang.reflect.InvocationTargetException at
	 * sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) at
	 * sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
	 * at
	 * sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	 * at java.lang.reflect.Method.invoke(Method.java:324) ...
	 */
	public static String getInternalStackTrace(Throwable throwable)
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		if (throwable instanceof WrapperException)
		{
			((WrapperException) throwable).printInternalStackTrace(pw);
		} else
		{
			throwable.printStackTrace(pw);
		}

		return sw.toString();
	}

	/**
	 * Retourne la trace d'exécution SANS le message.
	 * 
	 * Exemple :
	 * 
	 * at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) at
	 * sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
	 * at
	 * sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	 * at java.lang.reflect.Method.invoke(Method.java:324) ...
	 */
	public static String getInternalStackTraceOnly(Throwable throwable)
	{
		String stackTrace = getInternalStackTrace(throwable);

		if (stackTrace.indexOf("\tat") != -1)
		{
			return stackTrace.substring(stackTrace.indexOf("\tat"));
		}

		return "";
	}
}
