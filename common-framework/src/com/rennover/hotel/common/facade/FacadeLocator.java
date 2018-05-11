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
 * Classe non statique pour être utilisée à la fois par le client et le serveur
 * 
 * @author dattias
 */
public class FacadeLocator
{/*
	private Context m_context;

	private String m_userLogin;

	
	 * This hashmap will save contract-proxy pair.With this approach ,the system
	 * will not search for the home object using jndi lookup everytime there is
	 * an ejb hit.Point to be noted here is that the ServerProxy which is stored
	 * as a value in this hashmap is not the EJBObject.ServerProxy is like a
	 * business delegate.The system passes the EJBObject in the constructor of
	 * the ServerProxy when the object of ServerProxy is created using java
	 * reflection.
	 * 
	 * commeneted by Nabeel
	 
	private HashMap m_proxyMap = new HashMap(20);

	public FacadeLocator(String login, String password)
	{
		if (login != null)
		{
			login = login.trim();
		}

		m_userLogin = login;

		if (CommonProperties.isNoServerMode())
		{
			*//**
			 * TODO (dattias, le 13 sept. 2003) : faire l'authentification sans
			 * le serveur.
			 *//*
		} else
		{
			try
			{
				String clusterUrl = CommonProperties.getClusterUrl();
				Logger.debug(FacadeLocator.class, "New facade locator, login=", login, ", url=", clusterUrl);

				Properties p = new Properties();
				p.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.T3InitialContextFactory");
				p.put(Context.PROVIDER_URL, clusterUrl);
				if (!PropertyHelper.isNull(login))
				{
					p.put(Context.SECURITY_PRINCIPAL, login);
					p.put(Context.SECURITY_CREDENTIALS, password);
				}

				m_context = new InitialContext(p);
			} catch (NamingException e)
			{
				throw new TechnicalException("Impossible de se connecter au serveur", e);
			}
		}
	}

	public FacadeProxy getProxy(Class contractClass)
	{
		FacadeProxy proxy = (FacadeProxy) m_proxyMap.get(contractClass);

		if (proxy == null)
		{
			synchronized (m_proxyMap)
			{
				proxy = (FacadeProxy) m_proxyMap.get(contractClass);

				if (proxy == null)
				{
					if (CommonProperties.isNoServerMode())
					{
						proxy = createDummyProxy(contractClass);
					} else
					{
						proxy = createServerProxy(contractClass);
					}

					m_proxyMap.put(contractClass, proxy);
				}
			}
		}

		return proxy;
	}

	private FacadeProxy createServerProxy(Class contractClass)
	{
		try
		{
			StringBuffer buffer = toFacade(contractClass);
			EJBHome home = getEJBHome(buffer.toString().replace('.', '/'));
			Method method = home.getClass().getMethod("create", null);
			FacadeRemote remote = (FacadeRemote) method.invoke(home, null);
			buffer.append("ServerProxy");
			Class proxyClass = Class.forName(buffer.toString());
			Constructor ctor = proxyClass.getConstructor(new Class[] { FacadeRemote.class, FacadeLocator.class });
			return (FacadeProxy) ctor.newInstance(new Object[] { remote, this });
		} catch (Exception exc)
		{
			throw new TechnicalException("Impossible d'initialiser un proxy serveur pour " + contractClass.getName(),
			        exc);
		}
	}

	private FacadeProxy createDummyProxy(Class contractClass)
	{
		try
		{
			StringBuffer buffer = toFacade(contractClass).append("DummyProxy");
			Class proxyClass = Class.forName(buffer.toString());
			Constructor ctor = proxyClass.getConstructor(new Class[] { FacadeLocator.class });
			return (FacadeDummyProxy) ctor.newInstance(new Object[] { this });
		} catch (Exception exc)
		{
			throw new TechnicalException("Impossible d'initialiser un dummy proxy pour " + contractClass.getName(), exc);
		}
	}

	*//**
	 * Retourne un StringBuffer contenant le nom complet du contrat dont le
	 * package "contract" remplacé par "facade".
	 * 
	 * @param contractClass
	 *//*
	private StringBuffer toFacade(Class contractClass)
	{
		StringBuffer buffer = new StringBuffer(contractClass.getName());
		int index = contractClass.getName().indexOf("contract");
		return buffer.replace(index, index + "contract".length(), "facade");
	}

	*//**
	 * TODO (dattias, le 29 août 2003) : mettre le .replace('.', '/') dans cette
	 * méthode
	 * 
	 * @param homeName
	 * @return
	 *//*
	private EJBHome getEJBHome(String homeName)
	{
		try
		{
			return (EJBHome) getContext().lookup(homeName);
		} catch (Exception exc)
		{
			throw new TechnicalException("Impossible de récupérer la home : " + homeName, exc);
		}
	}

	private Context getContext()
	{
		if (m_context == null)
		{
			throw new IllegalStateException("FacadeLocator non initialisé ou utilisé sans serveur");
		}
		return m_context;
	}

	private EventListenerList m_listenerList = new EventListenerList();

	public void addCallListener(CallListener listener)
	{
		m_listenerList.add(CallListener.class, listener);
	}

	public void removeCallListener(CallListener listener)
	{
		m_listenerList.remove(CallListener.class, listener);
	}

	public void fireBeforeCall(CallEvent e)
	{
		Object[] listeners = m_listenerList.getListenerList();
		for (int i = listeners.length - 2; i >= 0; i -= 2)
		{
			if (listeners[i] == CallListener.class)
			{
				if (e == null)
				{
					e = new CallEvent();
				}
				((CallListener) listeners[i + 1]).doBefore(e);
			}
		}
	}

	public void fireAfterCall(CallEvent e)
	{
		Object[] listeners = m_listenerList.getListenerList();
		for (int i = listeners.length - 2; i >= 0; i -= 2)
		{
			if (listeners[i] == CallListener.class)
			{
				if (e == null)
				{
					e = new CallEvent();
				}
				((CallListener) listeners[i + 1]).doAfter(e);
			}
		}
	}

	public void fireOnCallError(CallEvent e)
	{
		Object[] listeners = m_listenerList.getListenerList();
		for (int i = listeners.length - 2; i >= 0; i -= 2)
		{
			if (listeners[i] == CallListener.class)
			{
				if (e == null)
				{
					e = new CallEvent();
				}
				((CallListener) listeners[i + 1]).doOnError(e);
			}
		}
	}

	public String getUserLogin()
	{
		return m_userLogin;
	}

*/}
