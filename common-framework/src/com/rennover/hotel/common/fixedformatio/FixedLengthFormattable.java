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
package com.rennover.hotel.common.fixedformatio;

import java.util.List;

/**
 * @author Gkarachi
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface FixedLengthFormattable
{

	/**
	 * 
	 * @return Should return the list of property names in the order that needs
	 *         to appear in the generated fixed length file.
	 */
	public List getPropertyNameList();

	/**
	 * 
	 * @return left padding or right padding
	 */
	public PaddingTypeEnum getPaddingTypeToUse();

	/**
	 * 
	 * @return the character that should be used for padding if the length of
	 *         value is less than the required length in the fixed format
	 */
	public char getPaddingCharToUse();

	/**
	 * 
	 * @return the character that should be used as a seperator between two
	 *         fields. :) I know this does not make sence for fixed format- but,
	 *         some require it
	 */
	public char getSeperatorToUse();
}
