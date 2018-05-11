/*
 * 
 */
package com.hotel.client.common.exception;

import java.awt.Component;
import java.rmi.ConnectException;

import com.rennover.client.framework.window.ExceptionManager;
import com.rennover.client.framework.window.WindowManager;
import com.rennover.hotel.common.exception.ExceptionBaseInterface;
import com.rennover.hotel.common.exception.TechnicalException;
import com.rennover.hotel.common.log.Logger;
import com.rennover.hotel.common.properties.CommonProperties;

/**
 * @author Piyush
 * 
 */
public class ClientExceptionManager implements ExceptionManager {
	/**
	 * @param throwable
	 *            Throwable
	 * @param parent
	 *            Component
	 */
	public void showException(final Throwable throwable, final Component parent) {
		Logger.error(WindowManager.class, throwable);
		String msg = "A technical problem occured. \n Please contact your system administrator.";
		boolean warning = false;
		if (throwable instanceof ExceptionBaseInterface) {
			ExceptionBaseInterface exc = (ExceptionBaseInterface) throwable;
			if (exc.isUserShowable()) {
				msg = exc.getUserShowableMessage();
			}
			warning = exc.isWarning();
		}
		if (throwable instanceof TechnicalException) {
			TechnicalException technicalException = (TechnicalException) throwable;
			Exception cause = technicalException.getCausedByException();
			if (cause instanceof ConnectException) {
				msg = "A problem connecting to the server occurred. \n The server or network access is unavailable.";
			}
		}
		if (warning) {
			WindowManager.showWarningMessage(msg, parent);
		} else if (CommonProperties.isApplicationModeDebug()) {
			WindowManager.showDetailledException(msg, throwable, parent);
		} else {
			WindowManager.showErrorMessage(msg, parent);
		}
	}
}