package com.hotel.base.common.helper;



/**
 * This class offers a unique management of null objects
 * 
 * @author pkGupta
 * 
 */
public class PropertyHelper {
	
	/** Represents a zero value for the basic type int */
	public static final int NULL_INT = Integer.MIN_VALUE;

	/** Represents a zero value for the basic type long */
	public static final long NULL_LONG = Long.MIN_VALUE;

	/** Represents a zero value for the basic type float */
	public static final float NULL_FLOAT = Float.MIN_VALUE;

	/** Represents a zero value for the basic type double */
	public static final double NULL_DOUBLE = Double.MIN_VALUE;

	/** Represents a zero value for the basic type String */
	public static final String EMPTY_STRING = "";

	/**
	 * This method will return an empty string if the passed argument is null
	 * otherwise will return the argument that is passed
	 * 
	 */
	public static final String nullToEmpty(String property) {
		return property == null ? EMPTY_STRING : property;
	}

	
	public static final boolean isNull(String s) {
		return (s == null || s.equals(EMPTY_STRING));
	}

	
	public static final boolean isNull(Object obj) {
		if (obj instanceof String) {
			return isNull((String) obj);
		}

		return (obj == null);
	}

	/**
	 * Function to validate whether a string is empty or not
	 */
	public static final boolean isNull(int i) {
		return (i == NULL_INT);
	}

	/**
	 * function to validate whether long is empty or not
	 */
	public static final boolean isNull(long l) {
		return (l == NULL_LONG);
	}

	/**
	 * function to validate whether float is empty or not
	 */
	public static final boolean isNull(float f) {
		return (f == NULL_FLOAT);
	}

	/**
	 * function to validate whether double is empty or not
	 */
	public static final boolean isNull(double d) {
		return (d == NULL_DOUBLE);
	}

	// ///////////////////////////////
	// /// Static equals methods /////
	// ///////////////////////////////

	public static boolean equals(Object value1, Object value2) {
		if (isNull(value1) && isNull(value2)) {
			return true;
		} else if (isNull(value1) || isNull(value2)) {
			return false;
		} else {
			return value1.equals(value2);
		}
	}

	public static boolean equals(byte value1, byte value2) {
		return value1 == value2;
	}

	public static boolean equals(short value1, short value2) {
		return value1 == value2;
	}

	public static boolean equals(int value1, int value2) {
		return value1 == value2;
	}

	public static boolean equals(long value1, long value2) {
		return value1 == value2;
	}

	public static boolean equals(double value1, double value2) {
		return value1 == value2;
	}

	public static boolean equals(float value1, float value2) {
		return value1 == value2;
	}

	public static boolean equals(char value1, char value2) {
		return value1 == value2;
	}

	public static boolean equals(boolean value1, boolean value2) {
		return value1 == value2;
	}
}
