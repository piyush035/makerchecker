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

import java.security.Principal;

/**
 * Un DummyRequestContext ne doit pas être utilisé dans un contexte EJB. Il sera
 * utiliser dans les tests pour simuler un contexte de requète en donnant le nom
 * de l'utilisateur.
 * 
 * @author dattias
 */
public class DummyRequestContext //implements RequestContext
{/*
	private Principal m_principal;

	private boolean m_rollBackOnly;

	public DummyRequestContext(String userLogin)
	{
		m_principal = new DummyPrincipal(userLogin);
	}

	public Principal getCallerPrincipal()
	{
		return m_principal;
	}

	public boolean getRollbackOnly()
	{
		return m_rollBackOnly;
	}

	public UserTransaction getUserTransaction()
	{
		throw new IllegalStateException(IMPOSSIBLE_HORS_J2EE_EXCEPTION);
	}

	public boolean isCallerInRole(String roleName)
	{
		throw new IllegalStateException(IMPOSSIBLE_HORS_J2EE_EXCEPTION);
	}

	public void setRollbackOnly()
	{
		m_rollBackOnly = true;
	}
*/}

class DummyPrincipal implements Principal
{
	private String m_userLogin;

	DummyPrincipal(String userLogin)
	{
		m_userLogin = userLogin;
	}

	public String getName()
	{
		return m_userLogin;
	}

	public boolean equals(Object another)
	{
		if (another instanceof Principal)
		{
			return m_userLogin.equals(((Principal) another).getName());
		}

		return false;
	}

	public String toString()
	{
		return m_userLogin;
	}

	public int hashCode()
	{
		return m_userLogin.hashCode();
	}
}