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
package com.rennover.hotel.common.log;

import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Category;
import org.apache.log4j.PropertyConfigurator;

import com.rennover.hotel.common.exception.ExceptionHelper;
import com.rennover.hotel.common.facade.RequestContext;
import com.rennover.hotel.common.facade.RequestContextManager;
import com.rennover.hotel.common.helper.ReflectionHelper;
import com.rennover.hotel.common.helper.Wrapper;

/**
 * Simple Logger for reporting error, log information and debug messages.
 * 
 * @change some method signatures must be homogenized:   * Choose between String
 *         Object message or message.
 */
public class Logger {
	private static boolean s_debugEnabled = false;

	private static final String LINE_SEPARATOR = System
			.getProperty("line.separator");

	private static final int INITIAL_BUFFER_LENGTH = 100;

	static {
		BasicConfigurator.configure();
	}

	public static void init(Properties properties) {
		Logger.log(Logger.class, "Initialize the logger with " + properties);
		PropertyConfigurator.configure(properties);
		s_debugEnabled = Category.getRoot().isDebugEnabled();
	}

	public static boolean isDebugEnabled() {
		return s_debugEnabled;
	}

	public static void debug(Object caller, Object message) {
		if (s_debugEnabled) {
			StringBuffer buffer = beginMessage(caller);
			appendObject(buffer, message);
			endMessage(buffer);
			getCallerCategory(caller).debug(buffer.toString());
		}
	}

	public static void debug(Object caller, Object msgPartA, Object msgPartB) {
		if (s_debugEnabled) {
			StringBuffer buffer = beginMessage(caller);
			appendObject(buffer, msgPartA);
			appendObject(buffer, msgPartB);
			endMessage(buffer);
			getCallerCategory(caller).debug(buffer.toString());
		}
	}

	public static void debug(Object caller, Object msgPartA, Object msgPartB,
			Object msgPartC) {
		if (s_debugEnabled) {
			StringBuffer buffer = beginMessage(caller);
			appendObject(buffer, msgPartA);
			appendObject(buffer, msgPartB);
			appendObject(buffer, msgPartC);
			endMessage(buffer);
			getCallerCategory(caller).debug(buffer.toString());
		}
	}

	public static void debug(Object caller, Object msgPartA, Object msgPartB,
			Object msgPartC, Object msgPartD) {
		if (s_debugEnabled) {
			StringBuffer buffer = beginMessage(caller);
			appendObject(buffer, msgPartA);
			appendObject(buffer, msgPartB);
			appendObject(buffer, msgPartC);
			appendObject(buffer, msgPartD);
			endMessage(buffer);
			getCallerCategory(caller).debug(buffer.toString());
		}
	}

	public static void debug(Object caller, Object msgPartA, Object msgPartB,
			Object msgPartC, Object msgPartD, Object msgPartE) {
		if (s_debugEnabled) {
			StringBuffer buffer = beginMessage(caller);
			appendObject(buffer, msgPartA);
			appendObject(buffer, msgPartB);
			appendObject(buffer, msgPartC);
			appendObject(buffer, msgPartD);
			appendObject(buffer, msgPartE);
			endMessage(buffer);
			getCallerCategory(caller).debug(buffer.toString());
		}
	}

	public static void debugEnter(Object caller) {
		if (s_debugEnabled) {
			StringBuffer buffer = beginMessage(caller);
			buffer.append("ENTERING");
			endMessage(buffer);
			getCallerCategory(caller).debug(buffer.toString());
		}
	}

	public static void debugExit(Object caller) {
		if (s_debugEnabled) {
			StringBuffer buffer = beginMessage(caller);
			buffer.append("EXITING");
			endMessage(buffer);
			getCallerCategory(caller).debug(buffer.toString());
		}
	}

	public static void debugExit(Object caller, Object returnedValue) {
		if (s_debugEnabled) {
			StringBuffer buffer = beginMessage(caller);
			buffer.append("EXITING, RETURNED VALUE = ");
			appendObject(buffer, returnedValue);
			endMessage(buffer);
			getCallerCategory(caller).debug(buffer.toString());
		}
	}

	public static void debugExit(Object caller, int returnedValue) {
		if (s_debugEnabled) {
			debugExit(caller, Wrapper.toString(returnedValue));
		}
	}

	public static void debugExit(Object caller, long returnedValue) {
		if (s_debugEnabled) {
			debugExit(caller, Wrapper.toString(returnedValue));
		}
	}

	public static void debugExit(Object caller, short returnedValue) {
		if (s_debugEnabled) {
			debugExit(caller, Wrapper.toString(returnedValue));
		}
	}

	public static void debugExit(Object caller, byte returnedValue) {
		if (s_debugEnabled) {
			debugExit(caller, Wrapper.toString(returnedValue));
		}
	}

	public static void debugExit(Object caller, float returnedValue) {
		if (s_debugEnabled) {
			debugExit(caller, Wrapper.toString(returnedValue));
		}
	}

	public static void debugExit(Object caller, double returnedValue) {
		if (s_debugEnabled) {
			debugExit(caller, Wrapper.toString(returnedValue));
		}
	}

	public static void debugExit(Object caller, char returnedValue) {
		if (s_debugEnabled) {
			debugExit(caller, Wrapper.toString(returnedValue));
		}
	}

	public static void debugExit(Object caller, boolean returnedValue) {
		if (s_debugEnabled) {
			debugExit(caller, Wrapper.toString(returnedValue));
		}
	}

	public static void debugValue(Object caller, String valueName, Object value) {
		if (s_debugEnabled) {
			StringBuffer buffer = beginMessage(caller);
			buffer.append(valueName).append(" = ");
			appendObject(buffer, value);
			endMessage(buffer);
			getCallerCategory(caller).debug(buffer.toString());
		}
	}

	public static void debugValue(Object caller, String valueName, short value) {
		if (s_debugEnabled) {
			debugValue(caller, valueName, Wrapper.toString(value));
		}
	}

	public static void debugValue(Object caller, String valueName, int value) {
		if (s_debugEnabled) {
			debugValue(caller, valueName, Wrapper.toString(value));
		}
	}

	public static void debugValue(Object caller, String valueName, long value) {
		if (s_debugEnabled) {
			debugValue(caller, valueName, Wrapper.toString(value));
		}
	}

	public static void debugValue(Object caller, String valueName, byte value) {
		if (s_debugEnabled) {
			debugValue(caller, valueName, Wrapper.toString(value));
		}
	}

	public static void debugValue(Object caller, String valueName, boolean value) {
		if (s_debugEnabled) {
			debugValue(caller, valueName, Wrapper.toString(value));
		}
	}

	public static void debugValue(Object caller, String valueName, char value) {
		if (s_debugEnabled) {
			debugValue(caller, valueName, Wrapper.toString(value));
		}
	}

	public static void debugValue(Object caller, String valueName, double value) {
		if (s_debugEnabled) {
			debugValue(caller, valueName, Wrapper.toString(value));
		}
	}

	public static void debugValue(Object caller, String valueName, float value) {
		if (s_debugEnabled) {
			debugValue(caller, valueName, Wrapper.toString(value));
		}
	}

	public static void log(Object caller, Object message) {
		StringBuffer buffer = beginMessage(caller);
		appendObject(buffer, message);
		endMessage(buffer);
		getCallerCategory(caller).info(buffer.toString());
	}

	public static void warn(Object caller, String message) {
		StringBuffer buffer = beginMessage(caller);
		buffer.append(message);
		endMessage(buffer);
		getCallerCategory(caller).warn(buffer.toString());
	}

	public static void warn(Object caller, Throwable throwable) {
		StringBuffer buffer = beginMessage(caller);
		buffer.append(throwable.getClass().getName());
		endMessage(buffer);
		appendThrowable(buffer);
		getCallerCategory(caller).warn(buffer.toString(), throwable);
	}

	public static void warn(Object caller, String message, Throwable throwable) {
		StringBuffer buffer = beginMessage(caller);
		buffer.append(message);
		endMessage(buffer);
		appendThrowable(buffer);
		getCallerCategory(caller).warn(buffer.toString(), throwable);
	}

	public static void error(Object caller, Object message) {
		StringBuffer buffer = beginMessage(caller);
		appendObject(buffer, message);
		endMessage(buffer);
		getCallerCategory(caller).error(buffer.toString());
	}

	public static void error(Object caller, Throwable throwable) {
		StringBuffer buffer = beginMessage(caller);
		buffer.append(throwable.getClass().getName());
		endMessage(buffer);
		appendThrowable(buffer);
		getCallerCategory(caller).error(buffer.toString(), throwable);
	}

	public static void error(Object caller, String message, Throwable throwable) {
		StringBuffer buffer = beginMessage(caller);
		buffer.append(message);
		endMessage(buffer);
		appendThrowable(buffer);
		getCallerCategory(caller).error(buffer.toString(), throwable);
	}

	private static Category getCallerCategory(Object caller) {
		if (caller != null) {
			return Category.getInstance(caller.getClass().getPackage()
					.getName());
		}

		return Category.getRoot();
	}

	private static String getCallerStackLocation(Object caller) {
		Throwable throwable = new Throwable();

		String stackTrace = ExceptionHelper.getInternalStackTrace(throwable);
		String callerClassName = ReflectionHelper.getClassShortName(caller
				.getClass());
		int start = stackTrace.indexOf(callerClassName);

		if (start == -1) {
			return "Unavailable stack location";
		}

		int end = stackTrace.indexOf(LINE_SEPARATOR, start);

		if (end == -1) {
			return stackTrace.substring(start);
		}

		return stackTrace.substring(start, end);
	}

	private static StringBuffer beginMessage(Object caller) {
		StringBuffer buffer = new StringBuffer(INITIAL_BUFFER_LENGTH);
		buffer.append('<').append(getUserOrThreadName()).append("> <");

		return buffer.append(getCallerStackLocation(caller)).append("> <");
	}

	private static Object getUserOrThreadName() {
		RequestContext context = RequestContextManager.getRequestContext();

		if (context == null) {
			return Thread.currentThread().getName();
		}

		return context.getCallerPrincipal().getName();
	}

	private static StringBuffer appendObject(StringBuffer buffer, Object object) {
		return ReflectionHelper.dump(buffer, object);
	}

	/**
	 * Is not used yet. To remove, if it continues, with all references.
	 */
	private static StringBuffer appendThrowable(StringBuffer buffer) {
		return buffer;
	}

	private static StringBuffer endMessage(StringBuffer buffer) {
		return buffer.append('>');
	}
}
