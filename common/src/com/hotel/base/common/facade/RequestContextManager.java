package com.hotel.base.common.facade;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hotel.base.common.domain.user.dto.UserContext;
import com.rennover.hotel.common.facade.TransactionManaged;

/**
 * 
 * @author Piyush
 * 
 */
public abstract class RequestContextManager {
	
	/** */
	private static List<ThreadLocal<RequestContext>> registeredThreadLocalList = new ArrayList<ThreadLocal<RequestContext>>();

	/** */
	private static ThreadLocal<RequestContext> requestContext = new ThreadLocal<RequestContext>();
	
	/** */
	private static List<ThreadLocal<UserContext>> registeredUserList = new ArrayList<ThreadLocal<UserContext>>();

	
	/** */	
	private static ThreadLocal<UserContext> userContext = new ThreadLocal<UserContext>();

	/**
	 * 
	 * @param context
	 */
	public static void setUserContext(UserContext context) {
		userContext.set(context);
	}
	
	/**
	 * 
	 * @return
	 */
	public static UserContext getUserContext() {
		return (UserContext) userContext.get();
	}


	
	/**
	 * 
	 * @param context
	 */
	public static void setRequestContext(RequestContext context) {
		requestContext.set(context);
	}
	
	/**
	 * 
	 * @return
	 */
	public static RequestContext getRequestContext() {
		return (RequestContext) requestContext.get();
	}

	/**
	 * Returns the login of the user who made ​​the query. The login is in the
	 * query context. If the query context is not yet initialized, an
	 * IllegalStateException is thrown.
	 * 
	 * @return the user login
	 * @throws IllegalStateException
	 *             if the query context is not yet initialized
	 */
	public static String getUserLogin() throws IllegalStateException {
		RequestContext context = getRequestContext();
		if (context == null) {
			throw new IllegalStateException(
					"Query context uninitialized");
		}
		return context.getCallerPrincipal().getName();
	}
	/**
	 * 
	 * @param threadLocal
	 */
	public static void registerThreadLocal(ThreadLocal<RequestContext> threadLocal) {
		synchronized (registeredThreadLocalList) {
			registeredThreadLocalList.add(threadLocal);
		}
	}
	
	/**
	 * 
	 * @param threadLocal
	 */
	public static void registerUserLocal(ThreadLocal<UserContext> threadLocal) {
		synchronized (registeredUserList) {
			registeredUserList.add(threadLocal);
		}
	}
	
	
	/**
	 * 
	 */
	public static void cleanRequestContext() {
		synchronized (registeredThreadLocalList) {
			for (Iterator<ThreadLocal<RequestContext>> iter = registeredThreadLocalList.iterator(); iter
					.hasNext();) {
				ThreadLocal<RequestContext> threadLocal =  iter.next();
				Object object = threadLocal.get();
				if (object instanceof TransactionManaged) {
					((TransactionManaged) object).close();
				}
				threadLocal.set(null);
			}
		}
		requestContext.set(null);
	}
	
	/**
	 * 
	 */
	public static void cleanUserRequestContext() {
		synchronized (registeredUserList) {
			for (Iterator<ThreadLocal<UserContext>> iter = registeredUserList.iterator(); iter
					.hasNext();) {
				ThreadLocal<UserContext> threadLocal =  iter.next();
				Object object = threadLocal.get();
				if (object instanceof TransactionManaged) {
					((TransactionManaged) object).close();
				}
				threadLocal.set(null);
			}
		}
		userContext.set(null);
	}
}