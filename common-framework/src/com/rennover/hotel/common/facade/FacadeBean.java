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
package com.rennover.hotel.common.facade;


/**
 * 
 */
public abstract class FacadeBean //implements SessionBean
{
	/**
	 *//*
	public static final String INTERNAL_LOGIN_GUEST = "kernel identity";

	public static final String EXTERNAL_LOGIN_GUEST = "<anonymous>";

	*//**
	 *//*
	public static final String MSG_GUEST_INTERDIT = "Guest non autorisé";

	*//**
	 *//*
	private SessionContext m_sessionContext;

	*//**
	 * Initialise le contexte de requète.
	 *//*
	protected final void initRequestContext(boolean guestEnabled)
	{
		EjbRequestContext context = new EjbRequestContext(m_sessionContext);
		RequestContextManager.setRequestContext(context);

		if (!guestEnabled
		        && context.getCallerPrincipal() != null
		        && (INTERNAL_LOGIN_GUEST.equalsIgnoreCase(context.getCallerPrincipal().getName()) || EXTERNAL_LOGIN_GUEST
		                .equalsIgnoreCase(context.getCallerPrincipal().getName())))
		{
			throw new SecurityException(MSG_GUEST_INTERDIT);
		}
	}

	*//**
	 * Nettoie le contexte de requète.
	 *//*
	protected final void cleanRequestContext()
	{
		RequestContextManager.cleanRequestContext();
	}

	public void setSessionContext(SessionContext sessionContext) throws EJBException, RemoteException
	{
		this.m_sessionContext = sessionContext;
	}

	*//**
	 * Retourne le contexte de session EJB.
	 * 
	 * @return SessionContext
	 *//*
	protected SessionContext getSessionContext()
	{
		return m_sessionContext;
	}

	*//**
	 * 
	 * @throws EJBException
	 * @throws RemoteException
	 *//*
	public void ejbActivate() throws EJBException, RemoteException
	{
	}

	*//**
	 * 
	 * @throws EJBException
	 * @throws RemoteException
	 *//*
	public void ejbCreate() throws EJBException, RemoteException
	{
	}

	*//**
	 * 
	 * @throws EJBException
	 * @throws RemoteException
	 *//*
	public void ejbPassivate() throws EJBException, RemoteException
	{
	}

	*//**
	 * 
	 * @throws EJBException
	 * @throws RemoteException
	 *//*
	public void ejbRemove() throws EJBException, RemoteException
	{
	}
*/
}
