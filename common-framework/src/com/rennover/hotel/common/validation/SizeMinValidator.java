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
package com.rennover.hotel.common.validation;

/**
 * Vérifie le respect d'une taille min.
 */
public class SizeMinValidator extends SizeValidator
{

	private long m_min;

	public SizeMinValidator(long min)
	{
		this(min, null, false);
	}

	public SizeMinValidator(long min, String specificMessage, boolean warning)
	{
		super(specificMessage, warning);
		m_min = min;
	}

	protected boolean isSizeInvalid(long size)
	{
		return size < m_min;
	}
}