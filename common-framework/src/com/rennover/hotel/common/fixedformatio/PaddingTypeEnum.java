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

import java.util.ArrayList;
import java.util.List;

import com.rennover.hotel.common.valueobject.EnumObject;

/**
 * @author Gkarachi
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PaddingTypeEnum extends EnumObject
{
	public static final PaddingTypeEnum LEFT_PADDING = new PaddingTypeEnum("LEFT");

	public static final PaddingTypeEnum RIGHT_PADDING = new PaddingTypeEnum("RIGHT");

	/**
	 * @param code
	 */

	private PaddingTypeEnum(String paddingType)
	{
		super(paddingType);
	}

	public static List getAllInstances()
	{
		return new ArrayList(getAllInstances(PaddingTypeEnum.class));
	}
}
