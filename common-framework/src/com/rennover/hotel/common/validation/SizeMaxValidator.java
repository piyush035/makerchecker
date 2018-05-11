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
 * Vérifie le respect d'une taille max.
 */
public class SizeMaxValidator extends SizeValidator
{
	private long m_max;

	public SizeMaxValidator(long max)
	{
		this(max, null, false);
	}

	public SizeMaxValidator(long max, String specificMessage, boolean warning)
	{
		super(specificMessage, warning);
		m_max = max;
	}

	protected boolean isSizeInvalid(long size)
	{
		return size > m_max;
	}
}