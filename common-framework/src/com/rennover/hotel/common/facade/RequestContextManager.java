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
 */package com.rennover.hotel.common.facade;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.rennover.hotel.common.properties.CommonProperties;

/**
 * @audit dattias 18/10/02
 */
// Defect# 12125 : reversed the changed done to fix defect#11117.
public abstract class RequestContextManager
{
	private static List s_registeredThreadLocalList = new ArrayList();

	private static ThreadLocal s_requestContext = new ThreadLocal();

	public static void setRequestContext(RequestContext context)
	{
		s_requestContext.set(context);
	}

	public static RequestContext getRequestContext()
	{
		return (RequestContext) s_requestContext.get();
	}

	/**
	 * Retourne le login de l'utilisateur qui a fait la requète. Le login se
	 * trouve dans le contexte de requète. Si le contexte de requète n'est pas
	 * encore initialisé, une IllegalStateException est levé.
	 * 
	 * @return le login de l'utilisateur
	 * @throws IllegalStateException
	 *             si le contexte de requète n'est pas encore initialisé
	 */
	public static String getUserLogin() throws IllegalStateException
	{
		RequestContext context = getRequestContext();

		if (context == null)
		{
			throw new IllegalStateException("Contexte de requète non initialisé");
		}

		return context.getCallerPrincipal().getName();
	}

	public static void registerThreadLocal(ThreadLocal threadLocal)
	{
		synchronized (s_registeredThreadLocalList)
		{
			s_registeredThreadLocalList.add(threadLocal);
		}
	}

	public static void cleanRequestContext()
	{
		synchronized (s_registeredThreadLocalList)
		{
			for (Iterator iter = s_registeredThreadLocalList.iterator(); iter.hasNext();)
			{
				ThreadLocal threadLocal = (ThreadLocal) iter.next();
				Object object = threadLocal.get();

				if (object instanceof TransactionManaged)
				{
					((TransactionManaged) object).close();
				}

				threadLocal.set(null);
			}
		}

		s_requestContext.set(null);
	}

	public static void commitRollbackRequest()
	{
		if (CommonProperties.isNoServerMode())
		{
			boolean rollback = getRequestContext().getRollbackOnly();

			synchronized (s_registeredThreadLocalList)
			{
				for (Iterator iter = s_registeredThreadLocalList.iterator(); iter.hasNext();)
				{
					ThreadLocal threadLocal = (ThreadLocal) iter.next();
					Object object = threadLocal.get();

					if (object instanceof TransactionManaged)
					{
						TransactionManaged resource = (TransactionManaged) object;

						if (rollback)
						{
							resource.rollback();
						} else
						{
							resource.commit();
						}

						resource.close();
						threadLocal.set(null);
					}
				}
			}

			setRequestContext(null);
		} else
		{
			throw new IllegalStateException("Interdit en contexte J2EE");
		}
	}
}
