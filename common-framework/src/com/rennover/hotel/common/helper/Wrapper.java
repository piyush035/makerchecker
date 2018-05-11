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
package com.rennover.hotel.common.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 */
public abstract class Wrapper
{
	public static final Integer wrap(int i)
	{
		return new Integer(i);
	}

	public static final Long wrap(long l)
	{
		return new Long(l);
	}

	public static final Byte wrap(byte b)
	{
		return new Byte(b);
	}

	public static final Short wrap(short s)
	{
		return new Short(s);
	}

	public static final Character wrap(char c)
	{
		return new Character(c);
	}

	public static final Double wrap(double d)
	{
		return new Double(d);
	}

	public static final Float wrap(float f)
	{
		return new Float(f);
	}

	public static final Boolean wrap(boolean b)
	{
		return (b ? Boolean.TRUE : Boolean.FALSE);
	}

	public static final Object wrap(Object o)
	{
		return o;
	}

	public static final String toString(int i)
	{
		return "" + i;
	}

	public static final String toString(long l)
	{
		return "" + l;
	}

	public static final String toString(byte b)
	{
		return "" + b;
	}

	public static final String toString(short s)
	{
		return "" + s;
	}

	public static final String toString(char c)
	{
		return "" + c;
	}

	public static final String toString(double d)
	{
		return "" + d;
	}

	public static final String toString(float f)
	{
		return "" + f;
	}

	public static final String toString(boolean b)
	{
		return "" + b;
	}

	public static final String toString(Object o)
	{
		return "" + o;
	}

	public static List toList(Object obj1)
	{
		List params = new ArrayList(1);
		params.add(obj1);
		return params;
	}

	public static List toList(Object obj1, Object obj2)
	{
		List params = new ArrayList(2);
		params.add(obj1);
		params.add(obj2);
		return params;
	}

	public static List toList(Object obj1, Object obj2, Object obj3)
	{
		List params = new ArrayList(3);
		params.add(obj1);
		params.add(obj2);
		params.add(obj3);
		return params;
	}

	public static List toList(Object obj1, Object obj2, Object obj3, Object obj4)
	{
		List params = new ArrayList(4);
		params.add(obj1);
		params.add(obj2);
		params.add(obj3);
		params.add(obj4);
		return params;
	}

	public static List toList(Object obj1, Object obj2, Object obj3, Object obj4, Object obj5)
	{
		List params = new ArrayList(5);
		params.add(obj1);
		params.add(obj2);
		params.add(obj3);
		params.add(obj4);
		params.add(obj5);
		return params;
	}

	public static List toList(Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6)
	{
		List params = new ArrayList(6);
		params.add(obj1);
		params.add(obj2);
		params.add(obj3);
		params.add(obj4);
		params.add(obj5);
		params.add(obj6);
		return params;
	}

	public static List toList(Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7)
	// ME-1185: AS in some access classes, 7 objects are passed as params, this
	// is created.
	{
		List params = new ArrayList(7);
		params.add(obj1);
		params.add(obj2);
		params.add(obj3);
		params.add(obj4);
		params.add(obj5);
		params.add(obj6);
		params.add(obj7);
		return params;
	}

	

	

	public static List toList(Object[] objectArray)
	{
		return Arrays.asList(objectArray);
	}
}