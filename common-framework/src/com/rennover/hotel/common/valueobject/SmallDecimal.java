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
package com.rennover.hotel.common.valueobject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

import com.rennover.hotel.common.exception.TechnicalException;
import com.rennover.hotel.common.helper.PrimitiveHelper;
import com.rennover.hotel.common.helper.StringHelper;

public class SmallDecimal extends Number implements Comparable, Cloneable
{
	public static final SmallDecimal ZERO = new SmallDecimal(0, 0);

	public static final SmallDecimal ONE = new SmallDecimal(1, 0);

	public static final SmallDecimal MINUS_ONE = new SmallDecimal(-1, 0);

	public static final SmallDecimal TEN = new SmallDecimal(10, 0);

	public static final SmallDecimal HUNDRED = new SmallDecimal(100, 0);

	public static final SmallDecimal DISIEME = new SmallDecimal(1, 1);

	public static final SmallDecimal CENTIEME = new SmallDecimal(1, 2);

	// transient private static final NumberFormat s_numberFormat =
	// NumberFormat.getIntegerInstance(Locale.FRANCE);
	// Rounding Modes

	/**
	 * Rounding mode to round away from zero. Always increments the digit prior
	 * to a non-zero discarded fraction. Note that this rounding mode never
	 * decreases the magnitude of the calculated value.
	 */
	public static final int ROUND_UP = 0;

	/**
	 * Rounding mode to round towards zero. Never increments the digit prior to
	 * a discarded fraction (i.e., truncates). Note that this rounding mode
	 * never increases the magnitude of the calculated value.
	 */
	public static final int ROUND_DOWN = 1;

	/**
	 * Rounding mode to round towards positive infinity. If the SmallDecimal is
	 * positive, behaves as for <tt>ROUND_UP</tt>; if negative, behaves as
	 * for <tt>ROUND_DOWN</tt>. Note that this rounding mode never decreases
	 * the calculated value.
	 */
	public static final int ROUND_CEILING = 2;

	/**
	 * Rounding mode to round towards negative infinity. If the SmallDecimal is
	 * positive, behave as for <tt>ROUND_DOWN</tt>; if negative, behave as
	 * for <tt>ROUND_UP</tt>. Note that this rounding mode never increases
	 * the calculated value.
	 */
	public static final int ROUND_FLOOR = 3;

	/**
	 * Rounding mode to round towards "nearest neighbor" unless both neighbors
	 * are equidistant, in which case round up. Behaves as for <tt>ROUND_UP</tt>
	 * if the discarded fraction is &gt;= .5; otherwise, behaves as for
	 * <tt>ROUND_DOWN</tt>. Note that this is the rounding mode that most of
	 * us were taught in grade school.
	 */
	public static final int ROUND_HALF_UP = 4;

	/**
	 * Rounding mode to round towards "nearest neighbor" unless both neighbors
	 * are equidistant, in which case round down. Behaves as for
	 * <tt>ROUND_UP</tt> if the discarded fraction is &gt; .5; otherwise,
	 * behaves as for <tt>ROUND_DOWN</tt>.
	 */
	public static final int ROUND_HALF_DOWN = 5;

	/**
	 * Rounding mode to round towards the "nearest neighbor" unless both
	 * neighbors are equidistant, in which case, round towards the even
	 * neighbor. Behaves as for ROUND_HALF_UP if the digit to the left of the
	 * discarded fraction is odd; behaves as for ROUND_HALF_DOWN if it's even.
	 * Note that this is the rounding mode that minimizes cumulative error when
	 * applied repeatedly over a sequence of calculations.
	 */
	public static final int ROUND_HALF_EVEN = 6;

	/**
	 * Rounding mode to assert that the requested operation has an exact result,
	 * hence no rounding is necessary. If this rounding mode is specified on an
	 * operation that yields an inexact result, an <tt>ArithmeticException</tt>
	 * is thrown.
	 */
	public static final int ROUND_UNNECESSARY = 7;

	/**
	 * The unscale of this SmallDecimal, as returned by unscaleValue().
	 * 
	 * @serial
	 */
	private long m_unscaledValue;

	/**
	 * The scale of this SmallDecimal, as returned by scaleValue().
	 * 
	 * @serial
	 */
	private int m_scale = 0;

	// //////////////////////
	// /// Constructors /////
	// //////////////////////

	public SmallDecimal(long unscaledValue, int scale)
	{
		if (scale < 0)
		{
			throw new IllegalArgumentException("negative scale");
		}
		m_unscaledValue = unscaledValue;
		m_scale = scale;
		trimZeros();
	}

	public SmallDecimal(String value)
	{
		value = StringHelper.removeAllSpaces(value);

		if (value == null || value.length() == 0)
		{
			throw new IllegalArgumentException("String argument null or empty");
		}
		// added for removing exponential 'E' in large numbers.
		if (value.indexOf('E') != -1)
		{
			value = (new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
		}
		value = value.replace('.', ',');

		if (value.charAt(0) == '-' && value.length() == 1)
		{
			m_scale = 0;
			m_unscaledValue = 0;
		} else
		{
			// Deal with leading plus sign if present
			if (value.charAt(0) == '+')
			{
				// "+" illegal!
				// "+-123.456" illegal!
				if (value.length() == 1 || value.charAt(1) == '-')
				{
					throw new NumberFormatException(value + " is not a number");
				}

				// discard leading '+'
				value = value.substring(1);
			}

			int pointIndex = value.indexOf(',');

			// e.g. "123"
			if (pointIndex != -1 && value.length() == 1)
			{
				m_scale = 0;
				m_unscaledValue = 0;
			} else if (pointIndex == -1)
			{
				m_unscaledValue = Long.parseLong(value);
			} else if (value.length() == 1)
			{
				m_scale = 0;
				m_unscaledValue = 0;
			} else
			{
				String integerValue = value.substring(0, pointIndex);
				String decimalValue = value.substring(pointIndex + 1);
				m_unscaledValue = Long.parseLong(integerValue + decimalValue);
				m_scale = value.length() - pointIndex - 1;
			}
		}

		trimZeros();
	}

	/**
	 * Called by constructors to remove the unnecessary zeros.
	 * 
	 * new SmallDecimal("10.0100") => 10.01 new SmallDecimal(1010, 3) => 1.01
	 * 
	 */
	private void trimZeros()
	{
		while ((m_scale > 0) && ((m_unscaledValue % 10) == 0))
		{
			--m_scale;
			m_unscaledValue = m_unscaledValue / 10;
		}
	}

	// //////////////////////////////////
	// //// Static Factory Methods //////
	// //////////////////////////////////

	/**
	 * Translates a long unscaled value and an int m_scale into a SmallDecimal.
	 * This "static factory method" is provided in preference to a (long, int)
	 * constructor because it allows for reuse of frequently used BigDecimals.
	 * 
	 * @param unscaledVal
	 *            unscaled value of the SmallDecimal.
	 * @param m_scale
	 *            m_scale of the SmallDecimal.
	 * @return a SmallDecimal whose value is
	 *         <tt>(unscaledVal/10<sup>m_scale</sup>)</tt>.
	 */
	public static SmallDecimal valueOf(long unscaledValue, int scale)
	{
		return new SmallDecimal(unscaledValue, scale);
	}

	public static SmallDecimal valueOf(long value)
	{
		return new SmallDecimal(value, 0);
	}

	public static SmallDecimal valueOf(String value)
	{
		return new SmallDecimal(value);
	}

	public static SmallDecimal valueOf(BigInteger value)
	{
		// 63 because bitLength excludes the signbit
		if (value.bitLength() > 63)
		{
			throw new TechnicalException("BigInteger too big to fit into a SmallDecimal");
		}
		return new SmallDecimal(value.longValue(), 0);
	}

	public static SmallDecimal valueOf(BigDecimal value)
	{
		// 63 because bitLength excludes the signbit
		if (value.unscaledValue().bitLength() > 63)
		{
			throw new TechnicalException("BigDecimal too big to fit into a SmallDecimal");
		}
		return new SmallDecimal(value.unscaledValue().longValue(), value.scale());
	}

	// Arithmetic Operations

	/**
	 * Returns a SmallDecimal whose value is <tt>(this - value)</tt>, and
	 * whose m_scale is <tt>max(this.m_scale(), value.m_scale())</tt>.
	 * 
	 * @param value
	 *            value to be subtracted from this SmallDecimal.
	 * @return <tt>this - value</tt>
	 */
	public SmallDecimal subtract(SmallDecimal sd)
	{
		if (sd == null)
		{
			return this;
		}
		if (m_scale < sd.m_scale)
		{
			int expo = sd.m_scale - m_scale;
			long unscale = m_unscaledValue * (long) Math.pow(10, expo);
			return new SmallDecimal(unscale - sd.m_unscaledValue, sd.m_scale);
		} else if (m_scale > sd.m_scale)
		{
			int expo = m_scale - sd.m_scale;
			long unscale = sd.m_unscaledValue * (long) Math.pow(10, expo);
			return new SmallDecimal(m_unscaledValue - unscale, m_scale);
		} else
		{
			return new SmallDecimal(m_unscaledValue - sd.m_unscaledValue, m_scale);
		}
	}

	/**
	 * Returns a SmallDecimal whose value is <tt>(this - value)</tt>, and
	 * whose m_scale is <tt>max(this.m_scale(), value.m_scale())</tt>.
	 * 
	 * @param value
	 *            value to be subtracted from this SmallDecimal.
	 * @return <tt>this - value</tt>
	 */
	public SmallDecimal add(SmallDecimal sd)
	{
		if (sd == null)
		{
			return this;
		}
		if (m_scale < sd.m_scale)
		{
			int expo = sd.m_scale - m_scale;
			long unscale = m_unscaledValue * (long) Math.pow(10, expo);
			return new SmallDecimal(unscale + sd.m_unscaledValue, sd.m_scale);
		} else if (m_scale > sd.m_scale)
		{
			int expo = m_scale - sd.m_scale;
			long unscale = sd.m_unscaledValue * (long) Math.pow(10, expo);
			return new SmallDecimal(m_unscaledValue + unscale, m_scale);
		} else
		{
			return new SmallDecimal(m_unscaledValue + sd.m_unscaledValue, m_scale);
		}
	}

	/**
	 * Returns a SmallDecimal whose value is <tt>(this * value)</tt>, and
	 * whose m_scale is <tt>(this.m_scale() + value.m_scale())</tt>.
	 * 
	 * @param value
	 *            value to be multiplied by this SmallDecimal.
	 * @return <tt>this * value</tt>
	 */
	public SmallDecimal multiply(SmallDecimal value)
	{
		return new SmallDecimal(m_unscaledValue * value.m_unscaledValue, m_scale + value.m_scale);
	}

	public SmallDecimal multiply(SmallDecimal value, int roundValue)
	{
		SmallDecimal multDecimal = new SmallDecimal(m_unscaledValue * value.m_unscaledValue, m_scale + value.m_scale);

		double doubleExtendedDecimal = (multDecimal.doubleValue()) * Math.pow(10, roundValue);

		if ((doubleExtendedDecimal * 10) % 10 > 5)
		{
			doubleExtendedDecimal++;
		}

		SmallDecimal decimal = new SmallDecimal((long) doubleExtendedDecimal, roundValue);
		return decimal;
	}

	// Commmented code removed

	/**
	 * Returns a SmallDecimal whose value is <tt>(this / value)</tt>, and
	 * whose m_scale is as specified. If rounding must be performed to generate
	 * a result with the specified m_scale, the specified rounding mode is
	 * applied.
	 * 
	 * @param value
	 *            value by which this SmallDecimal is to be divided.
	 * @param m_scale
	 *            m_scale of the SmallDecimal quotient to be returned.
	 * @param roundingMode
	 *            rounding mode to apply.
	 * @return <tt>this / value</tt>
	 * @throws ArithmeticException
	 *             <tt>value</tt> is zero, <tt>m_scale</tt> is negative, or
	 *             <tt>roundingMode==ROUND_UNNECESSARY</tt> and the specified
	 *             m_scale is insufficient to represent the result of the
	 *             division exactly.
	 * @throws IllegalArgumentException
	 *             <tt>roundingMode</tt> does not represent a valid rounding
	 *             mode.
	 * @see #ROUND_UP
	 * @see #ROUND_DOWN
	 * @see #ROUND_CEILING
	 * @see #ROUND_FLOOR
	 * @see #ROUND_HALF_UP
	 * @see #ROUND_HALF_DOWN
	 * @see #ROUND_HALF_EVEN
	 * @see #ROUND_UNNECESSARY
	 */

	// Commmented code removed
	/**
	 * Returns a SmallDecimal whose value is <tt>(this / value)</tt>, and
	 * whose m_scale is <tt>this.m_scale()</tt>. If rounding must be
	 * performed to generate a result with the given m_scale, the specified
	 * rounding mode is applied.
	 * 
	 * @param value
	 *            value by which this SmallDecimal is to be divided.
	 * @param roundingMode
	 *            rounding mode to apply.
	 * @return <tt>this / value</tt>
	 * @throws ArithmeticException
	 *             <tt>value==0</tt>, or
	 *             <tt>roundingMode==ROUND_UNNECESSARY</tt> and
	 *             <tt>this.m_scale()</tt> is insufficient to represent the
	 *             result of the division exactly.
	 * @throws IllegalArgumentException
	 *             <tt>roundingMode</tt> does not represent a valid rounding
	 *             mode.
	 * @see #ROUND_UP
	 * @see #ROUND_DOWN
	 * @see #ROUND_CEILING
	 * @see #ROUND_FLOOR
	 * @see #ROUND_HALF_UP
	 * @see #ROUND_HALF_DOWN
	 * @see #ROUND_HALF_EVEN
	 * @see #ROUND_UNNECESSARY
	 */

	// Commmented code removed
	/**
	 * Returns a SmallDecimal whose value is the absolute value of this
	 * SmallDecimal, and whose m_scale is <tt>this.m_scale()</tt>.
	 * 
	 * @return <tt>abs(this)</tt>
	 */
	public SmallDecimal abs()
	{
		return isNegative() ? negate() : this;
	}

	/**
	 * Returns a SmallDecimal whose value is <tt>(-this)</tt>, and whose
	 * scale is <tt>this.scale()</tt>.
	 * 
	 * @return <tt>-this</tt>
	 */
	public SmallDecimal negate()
	{
		return new SmallDecimal(-m_unscaledValue, m_scale);
	}

	/**
	 * Returns true if the number is negative.
	 */
	public boolean isNegative()
	{
		return m_unscaledValue < 0;
	}

	/**
	 * Returns the <i>m_scale</i> of this SmallDecimal. (The m_scale is the
	 * number of digits to the right of the decimal point.)
	 * 
	 * @return the m_scale of this SmallDecimal.
	 */
	public int scale()
	{
		return m_scale;
	}

	/**
	 * Returns a BigInteger whose value is the <i>unscaled value</i> of this
	 * SmallDecimal. (Computes <tt>(this * 10<sup>this.m_scale()</sup>)</tt>.)
	 * 
	 * @return the unscaled value of this SmallDecimal.
	 * @since 1.2
	 */
	public long unscaledValue()
	{
		return m_unscaledValue;
	}

	// Comparison Operations

	/**
	 * Compares this SmallDecimal with the specified Object. If the Object is a
	 * SmallDecimal, this method behaves like {@link #compareTo}. Otherwise, it
	 * throws a <tt>ClassCastException</tt> (as BigDecimals are comparable
	 * only to other BigDecimals).
	 * 
	 * @param o
	 *            Object to which this SmallDecimal is to be compared.
	 * @return a negative number, zero, or a positive number as this
	 *         SmallDecimal is numerically less than, equal to, or greater than
	 *         <tt>o</tt>, which must be a SmallDecimal.
	 * @throws ClassCastException
	 *             <tt>o</tt> is not a SmallDecimal.
	 * @see #compareTo(java.math.SmallDecimal)
	 * @see Comparable
	 * @since 1.2
	 */
	public int compareTo(Object o)
	{
		return compareTo((SmallDecimal) o);
	}

	public int compareTo(SmallDecimal sd)
	{
		sd = zeroIfNull(sd);
		int expo = Math.abs(m_scale - sd.m_scale);

		if (m_scale < sd.m_scale)
		{
			long unscaled = m_unscaledValue * PrimitiveHelper.pow(10, expo);
			return PrimitiveHelper.compare(unscaled, sd.m_unscaledValue);
		} else if (m_scale > sd.m_scale)
		{
			long unscaled = sd.m_unscaledValue * PrimitiveHelper.pow(10, expo);
			return PrimitiveHelper.compare(m_unscaledValue, unscaled);
		} else
		{
			return PrimitiveHelper.compare(m_unscaledValue, sd.m_unscaledValue);
		}
	}

	public boolean superior(SmallDecimal smallDecimal)
	{
		return compareTo(smallDecimal) > 0;
	}

	public boolean inferior(SmallDecimal smallDecimal)
	{
		return compareTo(smallDecimal) < 0;
	}

	/**
	 * Compares this SmallDecimal with the specified Object for equality. Unlike
	 * {@link #compareTo}, this method considers two BigDecimals equal only if
	 * they are equal in value and m_scale (thus 2.0 is not equal to 2.00 when
	 * compared by this method).
	 * 
	 * @param x
	 *            Object to which this SmallDecimal is to be compared.
	 * @return <tt>true</tt> if and only if the specified Object is a
	 *         SmallDecimal whose value and m_scale are equal to this
	 *         SmallDecimal's.
	 * @see #compareTo(java.math.SmallDecimal)
	 */
	public boolean equals(Object obj)
	{
		if (obj != null)
		{
			if (obj instanceof SmallDecimal)
			{
				return compareTo((SmallDecimal) obj) == 0;
			}
		}
		return false;
	}

	/**
	 * Returns the minimum of this SmallDecimal and <tt>value</tt>.
	 * 
	 * @param value
	 *            value with with the minimum is to be computed.
	 * @return the SmallDecimal whose value is the lesser of this SmallDecimal
	 *         and <tt>value</tt>. If they are equal, as defined by the
	 *         {@link #compareTo} method, either may be returned.
	 * @see #compareTo(java.math.SmallDecimal)
	 */
	public SmallDecimal min(SmallDecimal value)
	{
		return (compareTo(value) < 0 ? this : value);
	}

	/**
	 * Returns the maximum of this SmallDecimal and <tt>value</tt>.
	 * 
	 * @param value
	 *            value with with the maximum is to be computed.
	 * @return the SmallDecimal whose value is the greater of this SmallDecimal
	 *         and <tt>value</tt>. If they are equal, as defined by the
	 *         {@link #compareTo} method, either may be returned.
	 * @see #compareTo(java.math.SmallDecimal)
	 */
	public SmallDecimal max(SmallDecimal value)
	{
		return (compareTo(value) > 0 ? this : value);
	}

	// Hash Function

	/**
	 * Returns the hash code for this SmallDecimal. Note that two BigDecimals
	 * that are numerically equal but differ in m_scale (like 2.0 and 2.00) will
	 * generally <i>not</i> have the same hash code.
	 * 
	 * @return hash code for this SmallDecimal.
	 */
	public int hashCode()
	{
		return (int) (m_unscaledValue ^ (m_unscaledValue >>> 32));
	}

	// Format Converters

	/**
	 * Returns the string representation of this SmallDecimal. The digit-to-
	 * character mapping provided by {@link Character#forDigit} is used. A
	 * leading minus sign is used to indicate sign, and the number of digits to
	 * the right of the decimal point is used to indicate m_scale. (This
	 * representation is compatible with the (String) constructor.)
	 * 
	 * @return String representation of this SmallDecimal.
	 * @see Character#forDigit
	 * @see #SmallDecimal(java.lang.String)
	 */
	public String toString()
	{
		StringBuffer buffer = new StringBuffer(Long.toString(Math.abs(m_unscaledValue)));
		if (m_scale > 0)
		{
			int pointIndex = buffer.length() - m_scale;

			for (; pointIndex < 0; pointIndex++)
			{
				buffer.insert(0, 0);
			}
			if (pointIndex == 0)
			{
				buffer.insert(pointIndex, "0.");
			} else
			{
				buffer.insert(pointIndex, ".");
			}
		}
		if (isNegative())
		{
			buffer.insert(0, "-");
		}
		return buffer.toString();
	}

	// Commmented code removed

	public String format(int digit)
	{
		String tmp = toString().replace('.', ',');
		int length = tmp.length();
		StringBuffer buffer = new StringBuffer(tmp);
		int index = tmp.indexOf(",");
		if (index == -1 && digit == 0)
		{
			return tmp;
		} else if (index == -1)
		{
			buffer.append(",");
			index = length++;
		}
		// Commmented code removed

		tmp = buffer.toString();
		index = tmp.indexOf(',');
		while (length++ - index - 1 < digit)
		{
			buffer.append("0");
		}
		StringTokenizer tokens = new StringTokenizer(buffer.toString(), ",");
		buffer = new StringBuffer(tokens.nextToken());
		buffer.reverse();
		int n = (int) (index / 3);
		if (index % 3 == 0)
		{
			n--;
		}
		for (; n > 0; n--)
		{
			buffer.insert(n * 3, " ");
		}
		String decimal = tokens.nextToken();
		while (decimal.endsWith("0") && decimal.length() > digit)
		{
			decimal = decimal.substring(0, decimal.length() - 1);
		}
		return buffer.reverse().append(",").append(decimal).toString();
	}

	/**
	 * Converts this SmallDecimal to an int. This is standard <i>narrowing
	 * primitive conversion</i> as defined in <i>The Java Language
	 * Specification</i>: any fractional part of this SmallDecimal will be
	 * discarded, and if the resulting "BigInteger" is too big to fit in an int,
	 * only the low-order 32 bits are returned.
	 * 
	 * @return this SmallDecimal converted to an int.
	 */
	public long longValue()
	{
		if (m_scale == 0)
		{
			return m_unscaledValue;
		} else
		{
			return m_unscaledValue / (long) Math.pow(10, m_scale);
		}
	}

	/**
	 * Converts this SmallDecimal to a long. This is standard <i>narrowing
	 * primitive conversion</i> as defined in <i>The Java Language
	 * Specification</i>: any fractional part of this SmallDecimal will be
	 * discarded, and if the resulting "BigInteger" is too big to fit in a long,
	 * only the low-order 64 bits are returned.
	 * 
	 * @return this SmallDecimal converted to an int.
	 */
	public int intValue()
	{
		return (int) longValue();
	}

	/**
	 * Converts this SmallDecimal to a float. Similar to the double-to-float
	 * <i>narrowing primitive conversion</i> defined in <i>The Java Language
	 * Specification</i>: if this SmallDecimal has too great a magnitude to
	 * represent as a float, it will be converted to
	 * <tt>Float.NEGATIVE_INFINITY</tt> or <tt>Float.POSITIVE_INFINITY</tt>
	 * as appropriate.
	 * 
	 * @return this SmallDecimal converted to a float.
	 */
	public float floatValue()
	{
		return (float) doubleValue();
	}

	/**
	 * Converts this SmallDecimal to a double. Similar to the double-to-float
	 * <i>narrowing primitive conversion</i> defined in <i>The Java Language
	 * Specification</i>: if this SmallDecimal has too great a magnitude to
	 * represent as a double, it will be converted to
	 * <tt>Double.NEGATIVE_INFINITY</tt> or <tt>Double.POSITIVE_INFINITY</tt>
	 * as appropriate.
	 * 
	 * @return this SmallDecimal converted to a double.
	 * @deprecated ne jamais utiliser cette méthode
	 */
	public double doubleValue()
	{
		return m_unscaledValue * Math.pow(10, -m_scale);
	}

	public Object clone()
	{
		try
		{
			return super.clone();
		} catch (CloneNotSupportedException cnse)
		{
			throw new TechnicalException(cnse);
		}
	}

	// Scaling/Rounding Operations
	public SmallDecimal floor(int digit)
	{
		if (m_scale > digit)
		{
			int expo = m_scale - digit;
			return new SmallDecimal((int) Math.floor(m_unscaledValue / Math.pow(10, expo)), digit);
		}
		return this;
	}

	public SmallDecimal ceil(int digit)
	{
		if (m_scale > digit)
		{
			int expo = m_scale - digit;
			return new SmallDecimal((int) Math.ceil(m_unscaledValue / Math.pow(10, expo)), digit);
		}
		return this;
	}

	public SmallDecimal round(int digit)
	{
		if (m_scale > digit)
		{
			int expo = m_scale - digit;
			return new SmallDecimal(Math.round(m_unscaledValue / Math.pow(10, expo)), digit);
		}
		return this;
	}

	/**
	 */
	public SmallDecimal divide(SmallDecimal value)
	{
		BigDecimal b1 = new BigDecimal(toString());
		BigDecimal b2 = new BigDecimal(value.toString());
		BigDecimal result = b1.divide(b2, 10, BigDecimal.ROUND_HALF_EVEN);
		return new SmallDecimal(result.unscaledValue().longValue(), result.scale());
	}

	public SmallDecimal divide(SmallDecimal value, int roundValue)
	{
		BigDecimal b1 = new BigDecimal(toString());
		BigDecimal b2 = new BigDecimal(value.toString());
		BigDecimal result = b1.divide(b2, 10, BigDecimal.ROUND_HALF_EVEN);

		double doubleDecimal = (double) (result.unscaledValue().longValue()) / Math.pow(10, result.scale());

		double doubleExtendedDecimal = doubleDecimal * Math.pow(10, roundValue);

		if ((doubleExtendedDecimal * 10) % 10 > 5)
		{
			doubleExtendedDecimal++;
		}

		SmallDecimal decimal = new SmallDecimal((long) doubleExtendedDecimal, roundValue);

		return decimal;
	}

	public static void main(String[] args)
	{
		if (true)
		{
			SmallDecimal sd = SmallDecimal.valueOf(1960, 2);

			System.out.println(sd.multiply(new SmallDecimal(11, 2)));
			System.out.println(sd.divide(new SmallDecimal(10000, 2)));
			System.out.println(sd.divide(new SmallDecimal(10000, 2), 3));
			System.out.println(sd.multiply(new SmallDecimal(11, 2), 1));
			// Commmented code removed

			return;
		}
		for (int i = 0; i < 100000; i++)
		{
			int scale1 = (int) (Math.random() * 5);
			int scale2 = (int) (Math.random() * 5);

			long unscale1 = (long) (Math.random() * Math.sqrt(Long.MAX_VALUE) / Math.pow(10, scale1));
			long unscale2 = (long) (Math.random() * Math.sqrt(Long.MAX_VALUE) / Math.pow(10, scale1));

			if (Math.random() > 0.5)
			{
				unscale1 = -unscale1;
			}
			if (Math.random() > 0.5)
			{
				unscale2 = -unscale2;
			}
			SmallDecimal sd1 = new SmallDecimal(unscale1, scale1);
			SmallDecimal sd2 = new SmallDecimal(unscale2, scale2);

			if (i % 1000 == 0)
			{
				System.out.println(i);
			}

			BigDecimal bd1 = new BigDecimal(BigInteger.valueOf(unscale1), scale1);
			BigDecimal bd2 = new BigDecimal(BigInteger.valueOf(unscale2), scale2);

			try
			{
				SmallDecimal sd = new SmallDecimal(1114, 3);
				sd.floor(2);
				System.out.println(sd.floor(2));
				System.out.println(sd.ceil(2));
				System.out.println(sd.round(2));
				return;
			} catch (Exception e)
			{
				sd1.toString();
			}

			// toString
			sd1.toString();
			sd1.format((int) (Math.random() * 10));
			// doubleValue
			if ((sd1.doubleValue() - bd1.doubleValue()) > 0.000005)
			{
				throw new RuntimeException("Pb in doubleValue");
			}

			// compareTo
			if (sd1.compareTo(sd2) != bd1.compareTo(bd2))
			{
				throw new RuntimeException("Pb in compareTo");
			}
			if (sd1.compareTo(sd1) != 0)
			{
				throw new RuntimeException("Pb in compareTo =");
			}

			// add
			if ((sd1.add(sd2).doubleValue() - bd1.add(bd2).doubleValue()) > 0.000005)
			{
				throw new RuntimeException("Pb in add");
			}

			// sub
			if ((sd1.subtract(sd2).doubleValue() - bd1.subtract(bd2).doubleValue()) > 0.000005)
			{
				throw new RuntimeException("Pb in sub");
			}

			// mutliply
			if ((new Double(sd1.multiply(sd2).toString()).doubleValue() - new Double(bd1.multiply(bd2).toString())
			        .doubleValue()) > 0.000005)
			{
				throw new RuntimeException("Pb in multiply");
			}
			// Commmented code removed
		}

	}

	static public SmallDecimal zeroIfNull(SmallDecimal decimal)
	{
		return (decimal == null) ? SmallDecimal.ZERO : decimal;
	}
}