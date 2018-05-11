/**
 * 
 */
package com.hotel.base.common.facade;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.hotel.base.common.helper.PropertyHelper;
import com.rennover.hotel.common.exception.TechnicalException;
import com.rennover.hotel.common.log.Logger;
import com.rennover.hotel.common.properties.CommonProperties;

/**
 * @author Piyush
 * 
 */
public class FacadeLocator {

	private Context context;

	private String userLogin;

	/*
	 * This hashmap will save contract-proxy pair.With this approach ,the system
	 * will not search for the home object using jndi lookup everytime there is
	 * an ejb hit.Point to be noted here is that the ServerProxy which is stored
	 * as a value in this hashmap is not the EJBObject.ServerProxy is like a
	 * business delegate.The system passes the EJBObject in the constructor of
	 * the ServerProxy when the object of ServerProxy is created using java
	 * reflection.
	 */
	private Map<String, Object> proxyMap = new HashMap<String, Object>();

	public FacadeLocator(String login, String password) {
		if (login != null) {
			login = login.trim();
		}

		userLogin = login;

		if (CommonProperties.isNoServerMode()) {

		} else {
			try {
				String clusterUrl = CommonProperties.getClusterUrl();
				Logger.debug(FacadeLocator.class, "New facade locator, login=",
						login, ", url=", clusterUrl);

				Properties prop = new Properties();
				prop.put(Context.INITIAL_CONTEXT_FACTORY,
						"org.jnp.interfaces.NamingContextFactory");
				prop.put(Context.URL_PKG_PREFIXES,
						"org.jboss.naming:org.jnp.interfaces");
				prop.put(Context.PROVIDER_URL, "jnp://localhost:1099");
				/*if (!PropertyHelper.isNull(login)) {
					prop.put(Context.SECURITY_PRINCIPAL, login);
					prop.put(Context.SECURITY_CREDENTIALS, password);
				}
*/
				context = new InitialContext(prop);
			} catch (NamingException e) {
				throw new TechnicalException("Unable to connect to server", e);
			}
		}
	}

	public Object getProxy(Class contractClass) {
		Object remote = (Object) proxyMap.get(contractClass.getName());

		if (remote == null) {
			synchronized (proxyMap) {
				remote = (Object) proxyMap.get(contractClass);

				if (remote == null) {
					remote = createServerProxy(contractClass);
					proxyMap.put(contractClass.getName(), remote);
				}
			}
		}
		return remote;
	}

	/**
	 * 
	 * @param contractClass
	 * @return
	 */
	private Object createServerProxy(Class contractClass) {
		try {
			String facadeName = toFacade(contractClass);
			Object remote = (Object) getContext().lookup(facadeName);
			return remote;
		} catch (Exception exc) {
			throw new TechnicalException(
					"Unable to initialize a proxy server for"
							+ contractClass.getName(), exc);
		}
	}

	/**
	 * Returns a StringBuffer containing the full name of the contract.
	 * 
	 * @param contractClass
	 */
	private String toFacade(Class contractClass) {
		String buffer = new String(contractClass.getSimpleName());
		buffer = buffer.substring(0, 1).toLowerCase() + buffer.substring(1)
				+ "/remote";
		return buffer;
	}

	private Context getContext() {
		if (context == null) {
			throw new IllegalStateException(
					"FacadeLocator uninitialized or used without server");
		}
		return context;
	}

	public String getUserLogin() {
		return userLogin;
	}
}